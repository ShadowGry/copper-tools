/*
 * This file is part of Copper Tools.
 * Copyright (C) 2022  ShadowGry
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package io.github.shadowgry.coppertools.common.events;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.shadowgry.coppertools.common.items.ModArmorMaterials;
import io.github.shadowgry.coppertools.common.items.ModItems;
import io.github.shadowgry.coppertools.common.items.ModTiers;
import com.mojang.logging.LogUtils;

import org.slf4j.Logger;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.RegistryObject;

/**
 * Implements copper tool oxidizations.
 */
public class OxidizeEventHandler {
	
	public static final Logger LOGGER = LogUtils.getLogger();
	
	/**
	 * Flag to denote if copper tool and armor oxidization orders have been specified.
	 */
	private boolean mapsInitialized;
	
	/**
	 * Stores data to appropriately oxidize copper tools.
	 */
	private Map<Item, OxidizeData> toolOxidizeData;
	
	/**
	 * Stores data to appropriately oxidize copper armor.
	 */
	private Map<Item, OxidizeData> armorOxidizeData;
	
	/**
	 * The damage value required before the tool oxidizes to the next stage.
	 */
	private final int[] OXIDIZE_DAMAGE_TOOL = new int[] {
		ModTiers.COPPER.getUses() / 4,
		ModTiers.EXPOSED_COPPER.getUses() * 1/2,
		ModTiers.WEATHERED_COPPER.getUses() * 3/4
	};
	
	/**
	 * The initial damage of the next oxidized tool.
	 */
	private final int[] START_DAMAGE_TOOL = new int[] {
		ModTiers.EXPOSED_COPPER.getUses() / 4,
		ModTiers.WEATHERED_COPPER.getUses() * 1/2,
		ModTiers.OXIDIZED_COPPER.getUses() * 3/4
	};
	
	/**
	 * The damage value required before the armor oxidises to the next stage. First dimension is the armor material, the
	 * second dimension is the equipment slot.
	 */
	private final int[][] OXIDIZE_DAMAGE_ARMOR = new int [][] {
		{
			ModArmorMaterials.COPPER.getDurabilityForType(ArmorItem.Type.HELMET) / 4,
			ModArmorMaterials.COPPER.getDurabilityForType(ArmorItem.Type.CHESTPLATE) / 4,
			ModArmorMaterials.COPPER.getDurabilityForType(ArmorItem.Type.LEGGINGS) / 4,
			ModArmorMaterials.COPPER.getDurabilityForType(ArmorItem.Type.BOOTS) / 4
		},
		{
			ModArmorMaterials.EXPOSED_COPPER.getDurabilityForType(ArmorItem.Type.HELMET) * 1/2,
			ModArmorMaterials.EXPOSED_COPPER.getDurabilityForType(ArmorItem.Type.CHESTPLATE) * 1/2,
			ModArmorMaterials.EXPOSED_COPPER.getDurabilityForType(ArmorItem.Type.LEGGINGS) * 1/2,
			ModArmorMaterials.EXPOSED_COPPER.getDurabilityForType(ArmorItem.Type.BOOTS) * 1/2
		},
		{
			ModArmorMaterials.WEATHERED_COPPER.getDurabilityForType(ArmorItem.Type.HELMET) * 3/4,
			ModArmorMaterials.WEATHERED_COPPER.getDurabilityForType(ArmorItem.Type.CHESTPLATE) * 3/4,
			ModArmorMaterials.WEATHERED_COPPER.getDurabilityForType(ArmorItem.Type.LEGGINGS) * 3/4,
			ModArmorMaterials.WEATHERED_COPPER.getDurabilityForType(ArmorItem.Type.BOOTS) * 3/4
		}
	};
	
	private final int[][] START_DAMAGE_ARMOR = new int [][] {
		{
			ModArmorMaterials.EXPOSED_COPPER.getDurabilityForType(ArmorItem.Type.HELMET) / 4,
			ModArmorMaterials.EXPOSED_COPPER.getDurabilityForType(ArmorItem.Type.CHESTPLATE) / 4,
			ModArmorMaterials.EXPOSED_COPPER.getDurabilityForType(ArmorItem.Type.LEGGINGS) / 4,
			ModArmorMaterials.EXPOSED_COPPER.getDurabilityForType(ArmorItem.Type.BOOTS) / 4
		},
		{
			ModArmorMaterials.WEATHERED_COPPER.getDurabilityForType(ArmorItem.Type.HELMET) * 1/2,
			ModArmorMaterials.WEATHERED_COPPER.getDurabilityForType(ArmorItem.Type.CHESTPLATE) * 1/2,
			ModArmorMaterials.WEATHERED_COPPER.getDurabilityForType(ArmorItem.Type.LEGGINGS) * 1/2,
			ModArmorMaterials.WEATHERED_COPPER.getDurabilityForType(ArmorItem.Type.BOOTS) * 1/2
		},
		{
			ModArmorMaterials.OXIDIZED_COPPER.getDurabilityForType(ArmorItem.Type.HELMET) * 3/4,
			ModArmorMaterials.OXIDIZED_COPPER.getDurabilityForType(ArmorItem.Type.CHESTPLATE) * 3/4,
			ModArmorMaterials.OXIDIZED_COPPER.getDurabilityForType(ArmorItem.Type.LEGGINGS) * 3/4,
			ModArmorMaterials.OXIDIZED_COPPER.getDurabilityForType(ArmorItem.Type.BOOTS) * 3/4
		}
	};
	
	List<EquipmentSlot> EQUIPMENT_SLOTS = List.of(EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
	
	public OxidizeEventHandler() {
		mapsInitialized = false;
		toolOxidizeData = null;
		armorOxidizeData = null;
	}
	
	/**
	 * Puts the copper tool and armor oxidization orderings into a map, for quick future lookup. Sets
	 * {@code mapsInitialized} to true, as this only needs to be run once.
	 * 
	 * TODO: Run earlier during mod loading, rather than on first relevant event.
	 */
	private void initializeMaps() {
		List< List<RegistryObject<?>> > copper_tools = List.of(
			List.of(ModItems.COPPER_SHOVEL, ModItems.EXPOSED_COPPER_SHOVEL, ModItems.WEATHERED_COPPER_SHOVEL, ModItems.OXIDIZED_COPPER_SHOVEL),
			List.of(ModItems.COPPER_PICKAXE, ModItems.EXPOSED_COPPER_PICKAXE, ModItems.WEATHERED_COPPER_PICKAXE, ModItems.OXIDIZED_COPPER_PICKAXE),
			List.of(ModItems.COPPER_AXE, ModItems.EXPOSED_COPPER_AXE, ModItems.WEATHERED_COPPER_AXE, ModItems.OXIDIZED_COPPER_AXE),
			List.of(ModItems.COPPER_HOE, ModItems.EXPOSED_COPPER_HOE, ModItems.WEATHERED_COPPER_HOE, ModItems.OXIDIZED_COPPER_HOE),
			List.of(ModItems.COPPER_SWORD, ModItems.EXPOSED_COPPER_SWORD, ModItems.WEATHERED_COPPER_SWORD, ModItems.OXIDIZED_COPPER_SWORD)
		);
		
		List< List<RegistryObject<?>> > copper_armor = List.of(
			List.of(ModItems.COPPER_HELMET, ModItems.EXPOSED_COPPER_HELMET, ModItems.WEATHERED_COPPER_HELMET, ModItems.OXIDIZED_COPPER_HELMET),
			List.of(ModItems.COPPER_CHESTPLATE, ModItems.EXPOSED_COPPER_CHESTPLATE, ModItems.WEATHERED_COPPER_CHESTPLATE, ModItems.OXIDIZED_COPPER_CHESTPLATE),
			List.of(ModItems.COPPER_LEGGINGS, ModItems.EXPOSED_COPPER_LEGGINGS, ModItems.WEATHERED_COPPER_LEGGINGS, ModItems.OXIDIZED_COPPER_LEGGINGS),
			List.of(ModItems.COPPER_BOOTS, ModItems.EXPOSED_COPPER_BOOTS, ModItems.WEATHERED_COPPER_BOOTS, ModItems.OXIDIZED_COPPER_BOOTS)
		);
		
		toolOxidizeData = new HashMap<>();
		for(List<RegistryObject<?>> tool_set : copper_tools) {
			addToolOxidizeData(tool_set);
		}
		
		createArmorOxidizeData(copper_armor);
		
		mapsInitialized = true;
	}
	
	private void addToolOxidizeData(List<RegistryObject<?>> items) {
		int size = items.size() - 1;
		for(int i = 0; i < size; i++) {
			toolOxidizeData.put(((Item) items.get(i).get()).asItem(),
							new OxidizeData((Item) items.get(i+1).get(), OXIDIZE_DAMAGE_TOOL[i], START_DAMAGE_TOOL[i]));
		}
	}
	
	private void createArmorOxidizeData(List< List<RegistryObject<?>> > items) {
		armorOxidizeData = new HashMap<>();
		int equipmentSlot = 0;
		
		for(List<RegistryObject<?>> armor_set : items) {
			int size = armor_set.size() - 1;
			
			for(int i = 0; i < size; i++) {
				armorOxidizeData.put(((Item) armor_set.get(i).get()).asItem(),
									new OxidizeData((Item) armor_set.get(i+1).get(), OXIDIZE_DAMAGE_ARMOR[i][equipmentSlot], START_DAMAGE_ARMOR[i][equipmentSlot]));
			}
			equipmentSlot++;
		}
	}
	
	/**
	 * Oxidizes copper tools to the next stage when they are damaged enough from breaking blocks.
	 */
	@SubscribeEvent
	public void onBreakEvent(BlockEvent.BreakEvent event) {
		if(!event.getPlayer().level().isClientSide()) {
			Player player = event.getPlayer();
			oxidizeTool(player);
		}
	}
	
	/**
	 * Oxidizes copper tools to the next stage when they are damaged enough from modifying blocks.
	 */
	@SubscribeEvent
	public void onToolModificationEvent(BlockEvent.BlockToolModificationEvent event) {
		if(!event.getPlayer().level().isClientSide()) {
			Player player = event.getPlayer();
			oxidizeTool(player);
		}
	}
	
	/**
	 * Oxidizes copper tools to the next stage when they are damaged enough from attacking living entities.
	 * 
	 * @param event the LivingDamageEvent.
	 */
	@SubscribeEvent
	public void onLivingDamageEvent(LivingDamageEvent event) {
		if(!event.getEntity().level().isClientSide()) {
			Entity entity = event.getSource().getDirectEntity();
			if(entity instanceof Player) {
				oxidizeTool((Player) entity);
			}
		}
	}
	
	/**
	 * Oxidizes a tool in the selected player's hand if it is damaged enough.
	 * 
	 * @param player the player where an oxidization may occur.
	 */
	private void oxidizeTool(Player player) {
		if(!mapsInitialized) {
			initializeMaps();
		}
		
		if(!player.isCreative()) {
			ItemStack item = player.getMainHandItem();
			OxidizeData data = toolOxidizeData.get(item.getItem());
			
			if(data != null && item.getDamageValue() > data.getOxidizeDamageValue()) {
				ItemStack nextTool = data.getNextTool();
				copyModifications(item, nextTool);
				nextTool.setDamageValue(data.getStartDamageValue());
				player.setItemInHand(InteractionHand.MAIN_HAND, nextTool);
			}
		}
	}
	
	/**
	 * Copies (if any) custom hover name, enchantments, and current repair cost of an item to another.
	 * 
	 * @param oldItem the item with the custom name, enchantments, and repair cost to copy over.
	 * @param newItem the item to have the custom name, enchantments, and repair cost copied to.
	 */
	private void copyModifications(ItemStack oldItem, ItemStack newItem) {
		if(oldItem.hasCustomHoverName()) {
			newItem.setHoverName(oldItem.getHoverName());
		}
		if(oldItem.isEnchanted() && newItem.isEnchantable()) {
			Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(oldItem);
			for(Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
				newItem.enchant(entry.getKey(), entry.getValue());
			}
		}
		if(oldItem.isRepairable() && newItem.isRepairable()) {
			newItem.setRepairCost(oldItem.getBaseRepairCost());
		}
	}
	
	/**
	 * Oxidises copper armor to the next stage when they are damaged enough from taking damage.
	 * TODO: Improve documentation for this method.
	 * 
	 * @param event the LivingDamageEvent.
	 */
	@SubscribeEvent
	public void oxidizeArmor(LivingDamageEvent event) {
		if(!mapsInitialized) {
			initializeMaps();
		}
		
		LivingEntity hurtEntity = event.getEntity();
		if(!hurtEntity.level().isClientSide()) {
			
			for(EquipmentSlot equipmentSlot : EQUIPMENT_SLOTS) {
				if(hurtEntity.hasItemInSlot(equipmentSlot)) {
					ItemStack item = hurtEntity.getItemBySlot(equipmentSlot);
					OxidizeData data = armorOxidizeData.get(item.getItem());
					
					if(data != null && item.getDamageValue() > data.getOxidizeDamageValue()) {
						ItemStack nextItem = data.getNextTool();
						copyModifications(item, nextItem);
						nextItem.setDamageValue(data.getStartDamageValue());
						hurtEntity.setItemSlot(equipmentSlot, nextItem);
					}
				}
			}
		}
	}
	
	/**
	 * Stores information to appropriately oxidize a copper tool to the next tool. Copper tools oxidize in a linear way.
	 * The current (or previous tool) oxidizes to {@code nextTool}, however, previous tool is not stored in this class -
	 * it is recommended to use a map to store it and map it to the appropriate instance of this class.
	 */
	private class OxidizeData {
		private Item nextTool;
		private int oxidizeDamageValue;
		private int startDamageValue;
		
		/**
		 * Creates a new cache to store information about a particular copper tool oxidization.
		 * 
		 * @param nextTool the new tool the old one turns into
		 * @param oxidizeDamageValue the damage value required for the previous tool to oxidize into {@code nextTool}
		 * @param startDamageValue the initial damage of {@code nextTool}
		 */
		OxidizeData(Item nextTool, int oxidizeDamageValue, int startDamageValue) {
			this.nextTool = nextTool;
			this.oxidizeDamageValue = oxidizeDamageValue;
			this.startDamageValue = startDamageValue;
		}
		
		/**
		 * Returns the next tool the old one turns into.
		 * 
		 * @return the next tool the old one turns into.
		 */
		ItemStack getNextTool() {
			return this.nextTool.getDefaultInstance();
		}
		
		/**
		 * Returns the damage value required for the previous tool to oxidize into {@code nextTool}.
		 * 
		 * @return the damage value required for the previous tool to oxidize into {@code nextTool}
		 */
		int getOxidizeDamageValue() {
			return this.oxidizeDamageValue;
		}
		
		/**
		 * Returns the initial damage of {@code nextTool}.
		 * 
		 * @return the initial damage of {@code nextTool}
		 */
		int getStartDamageValue() {
			return this.startDamageValue;
		}
	}
}
