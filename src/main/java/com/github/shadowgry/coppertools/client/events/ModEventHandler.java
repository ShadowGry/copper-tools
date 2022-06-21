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
package com.github.shadowgry.coppertools.client.events;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.shadowgry.coppertools.common.items.ModItems;
import com.github.shadowgry.coppertools.common.items.ModTiers;
import com.mojang.logging.LogUtils;

import org.slf4j.Logger;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.RegistryObject;

/**
 * Implements copper tool oxidizations.
 */
public class ModEventHandler {
    
    public static final Logger LOGGER = LogUtils.getLogger();
    
    /**
     * Flag to denote if copper tool oxidization order has been specified.
     */
    private boolean isMapInitialized;
    
    /**
     * Stores data to appropriately oxidize copper tools.
     */
    private Map<Item, OxidizeData> oxidizeData;
    
    /**
     * The damage value required before the tool oxidizes to the next stage.
     */
    private final int[] OXIDIZE_DAMAGE = new int[] {
        ModTiers.COPPER.getUses() / 4,
        ModTiers.EXPOSED_COPPER.getUses() * 1/2,
        ModTiers.WEATHERED_COPPER.getUses() * 3/4
    };
    
    /**
     * The initial damage of the next oxidized tool.
     */
    private final int[] START_DAMAGE = new int[] {
        ModTiers.EXPOSED_COPPER.getUses() / 4,
        ModTiers.WEATHERED_COPPER.getUses() * 1/2,
        ModTiers.OXIDIZED_COPPER.getUses() * 3/4
    };
    
    public ModEventHandler() {
        isMapInitialized  = false;
        oxidizeData = null;
    }
    
    /**
     * Puts the copper tool oxidization orderings into a map, for quick future lookup. Sets {@code isMapInitialized} to
     * true, as this only needs to be run once.
     * 
     * TODO: Run earlier during mod loading, rather than on first BreakEvent. 
     */
    private void initializeMap() {
        List< List<RegistryObject<?>> > copper_tools = List.of(
            List.of(ModItems.COPPER_SHOVEL, ModItems.EXPOSED_COPPER_SHOVEL, ModItems.WEATHERED_COPPER_SHOVEL, ModItems.OXIDIZED_COPPER_SHOVEL),
            List.of(ModItems.COPPER_PICKAXE, ModItems.EXPOSED_COPPER_PICKAXE, ModItems.WEATHERED_COPPER_PICKAXE, ModItems.OXIDIZED_COPPER_PICKAXE),
            List.of(ModItems.COPPER_AXE, ModItems.EXPOSED_COPPER_AXE, ModItems.WEATHERED_COPPER_AXE, ModItems.OXIDIZED_COPPER_AXE),
            List.of(ModItems.COPPER_HOE, ModItems.EXPOSED_COPPER_HOE, ModItems.WEATHERED_COPPER_HOE, ModItems.OXIDIZED_COPPER_HOE),
            List.of(ModItems.COPPER_SWORD, ModItems.EXPOSED_COPPER_SWORD, ModItems.WEATHERED_COPPER_SWORD, ModItems.OXIDIZED_COPPER_SWORD)
        );
        
        oxidizeData = new HashMap<>();
        for(List<RegistryObject<?>> tool_set : copper_tools) {
            addOxidizeData(tool_set);
        }
        isMapInitialized = true;
    }
    
    private void addOxidizeData(List<RegistryObject<?>> items) {
        int size = items.size() - 1;
        for(int i = 0; i < size; i++) {
            oxidizeData.put(((Item) items.get(i).get()).asItem(),
                            new OxidizeData((Item) items.get(i+1).get(), OXIDIZE_DAMAGE[i], START_DAMAGE[i]));
        }
    }
    
    /**
     * Oxidizes copper tools to the next stage when they are damaged enough from breaking blocks.
     */
    @SubscribeEvent
    public void onBreakEvent(BreakEvent event) {
        Player player = event.getPlayer();
        oxidizeTool(player);
    }
    
    /**
     * Oxidizes copper tools to the next stage when they are damaged enough from modifying blocks.
     */
    @SubscribeEvent
    public void onToolModificationEvent(BlockEvent.BlockToolModificationEvent event) {
        Player player = event.getPlayer();
        oxidizeTool(player);
    }
    
    /**
     * Oxidizes a tool in the selected player's hand if it is damaged enough.
     * 
     * @param player the player where an oxidization may occur.
     */
    private void oxidizeTool(Player player) {
        if(!isMapInitialized) {
            initializeMap();
        }
        
        if(!player.isCreative()) {
            ItemStack item = player.getMainHandItem();
            OxidizeData data = oxidizeData.get(item.getItem());
            
            if(data != null && item.getDamageValue() > data.getOxidizeDamageValue()) {
                ItemStack nextTool = data.getNextTool().getDefaultInstance();
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
        Item getNextTool() {
            return this.nextTool;
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
