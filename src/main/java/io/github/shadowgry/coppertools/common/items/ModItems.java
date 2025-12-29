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
package io.github.shadowgry.coppertools.common.items;

import io.github.shadowgry.coppertools.CopperTools;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
	
	private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CopperTools.MOD_ID);
	
	// Copper Items
	public static final DeferredItem<ShovelItem>  COPPER_SHOVEL  = ITEMS.register("copper_shovel",  () -> new ShovelItem( ModTiers.COPPER,  1.5F, -3.0F, new Item.Properties()));
	public static final DeferredItem<PickaxeItem> COPPER_PICKAXE = ITEMS.register("copper_pickaxe", () -> new PickaxeItem(ModTiers.COPPER,  1,    -2.8F, new Item.Properties()));
	public static final DeferredItem<AxeItem>     COPPER_AXE     = ITEMS.register("copper_axe",     () -> new AxeItem(    ModTiers.COPPER,  6.0F, -3.1F, new Item.Properties()));
	public static final DeferredItem<HoeItem>     COPPER_HOE     = ITEMS.register("copper_hoe",     () -> new HoeItem(    ModTiers.COPPER, -2,    -1.0F, new Item.Properties()));
	public static final DeferredItem<SwordItem>   COPPER_SWORD   = ITEMS.register("copper_sword",   () -> new SwordItem(  ModTiers.COPPER,  3,    -2.4F, new Item.Properties()));
	
	// Copper Armor
	public static final DeferredItem<ArmorItem> COPPER_HELMET     = ITEMS.register("copper_helmet",     () -> new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.HELMET,     new Item.Properties()));
	public static final DeferredItem<ArmorItem> COPPER_CHESTPLATE = ITEMS.register("copper_chestplate", () -> new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
	public static final DeferredItem<ArmorItem> COPPER_LEGGINGS   = ITEMS.register("copper_leggings",   () -> new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.LEGGINGS,   new Item.Properties()));
	public static final DeferredItem<ArmorItem> COPPER_BOOTS      = ITEMS.register("copper_boots",      () -> new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.BOOTS,      new Item.Properties()));
	
	// Exposed Copper Items
	public static final DeferredItem<ShovelItem>  EXPOSED_COPPER_SHOVEL  = ITEMS.register("exposed_copper_shovel",  () -> new ShovelItem( ModTiers.EXPOSED_COPPER,  1.5F, -3.0F, new Item.Properties()));
	public static final DeferredItem<PickaxeItem> EXPOSED_COPPER_PICKAXE = ITEMS.register("exposed_copper_pickaxe", () -> new PickaxeItem(ModTiers.EXPOSED_COPPER,  1,    -2.8F, new Item.Properties()));
	public static final DeferredItem<AxeItem>     EXPOSED_COPPER_AXE     = ITEMS.register("exposed_copper_axe",     () -> new AxeItem(    ModTiers.EXPOSED_COPPER,  6.0F, -3.1F, new Item.Properties()));
	public static final DeferredItem<HoeItem>     EXPOSED_COPPER_HOE     = ITEMS.register("exposed_copper_hoe",     () -> new HoeItem(    ModTiers.EXPOSED_COPPER, -2,    -1.0F, new Item.Properties()));
	public static final DeferredItem<SwordItem>   EXPOSED_COPPER_SWORD   = ITEMS.register("exposed_copper_sword",   () -> new SwordItem(  ModTiers.EXPOSED_COPPER,  3,    -2.4F, new Item.Properties()));
	
	// Exposed Copper Armor
	public static final DeferredItem<ArmorItem> EXPOSED_COPPER_HELMET     = ITEMS.register("exposed_copper_helmet",     () -> new ArmorItem(ModArmorMaterials.EXPOSED_COPPER, ArmorItem.Type.HELMET,     new Item.Properties()));
	public static final DeferredItem<ArmorItem> EXPOSED_COPPER_CHESTPLATE = ITEMS.register("exposed_copper_chestplate", () -> new ArmorItem(ModArmorMaterials.EXPOSED_COPPER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
	public static final DeferredItem<ArmorItem> EXPOSED_COPPER_LEGGINGS   = ITEMS.register("exposed_copper_leggings",   () -> new ArmorItem(ModArmorMaterials.EXPOSED_COPPER, ArmorItem.Type.LEGGINGS,   new Item.Properties()));
	public static final DeferredItem<ArmorItem> EXPOSED_COPPER_BOOTS      = ITEMS.register("exposed_copper_boots",      () -> new ArmorItem(ModArmorMaterials.EXPOSED_COPPER, ArmorItem.Type.BOOTS,      new Item.Properties()));
	
	// Weathered Copper Items
	public static final DeferredItem<ShovelItem>  WEATHERED_COPPER_SHOVEL  = ITEMS.register("weathered_copper_shovel",  () -> new ShovelItem( ModTiers.WEATHERED_COPPER,  1.5F, -3.0F, new Item.Properties()));
	public static final DeferredItem<PickaxeItem> WEATHERED_COPPER_PICKAXE = ITEMS.register("weathered_copper_pickaxe", () -> new PickaxeItem(ModTiers.WEATHERED_COPPER,  1,    -2.8F, new Item.Properties()));
	public static final DeferredItem<AxeItem>     WEATHERED_COPPER_AXE     = ITEMS.register("weathered_copper_axe",     () -> new AxeItem(    ModTiers.WEATHERED_COPPER,  6.0F, -3.1F, new Item.Properties()));
	public static final DeferredItem<HoeItem>     WEATHERED_COPPER_HOE     = ITEMS.register("weathered_copper_hoe",     () -> new HoeItem(    ModTiers.WEATHERED_COPPER, -2,    -1.0F, new Item.Properties()));
	public static final DeferredItem<SwordItem>   WEATHERED_COPPER_SWORD   = ITEMS.register("weathered_copper_sword",   () -> new SwordItem(  ModTiers.WEATHERED_COPPER,  3,    -2.4F, new Item.Properties()));
	
	// Weathered Copper Armor
	public static final DeferredItem<ArmorItem> WEATHERED_COPPER_HELMET     = ITEMS.register("weathered_copper_helmet",     () -> new ArmorItem(ModArmorMaterials.WEATHERED_COPPER, ArmorItem.Type.HELMET,     new Item.Properties()));
	public static final DeferredItem<ArmorItem> WEATHERED_COPPER_CHESTPLATE = ITEMS.register("weathered_copper_chestplate", () -> new ArmorItem(ModArmorMaterials.WEATHERED_COPPER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
	public static final DeferredItem<ArmorItem> WEATHERED_COPPER_LEGGINGS   = ITEMS.register("weathered_copper_leggings",   () -> new ArmorItem(ModArmorMaterials.WEATHERED_COPPER, ArmorItem.Type.LEGGINGS,   new Item.Properties()));
	public static final DeferredItem<ArmorItem> WEATHERED_COPPER_BOOTS      = ITEMS.register("weathered_copper_boots",      () -> new ArmorItem(ModArmorMaterials.WEATHERED_COPPER, ArmorItem.Type.BOOTS,      new Item.Properties()));
	
	// Oxidized Copper Items
	public static final DeferredItem<ShovelItem>  OXIDIZED_COPPER_SHOVEL  = ITEMS.register("oxidized_copper_shovel",  () -> new ShovelItem( ModTiers.OXIDIZED_COPPER,  1.5F, -3.0F, new Item.Properties()));
	public static final DeferredItem<PickaxeItem> OXIDIZED_COPPER_PICKAXE = ITEMS.register("oxidized_copper_pickaxe", () -> new PickaxeItem(ModTiers.OXIDIZED_COPPER,  1,    -2.8F, new Item.Properties()));
	public static final DeferredItem<AxeItem>     OXIDIZED_COPPER_AXE     = ITEMS.register("oxidized_copper_axe",     () -> new AxeItem(    ModTiers.OXIDIZED_COPPER,  6.0F, -3.1F, new Item.Properties()));
	public static final DeferredItem<HoeItem>     OXIDIZED_COPPER_HOE     = ITEMS.register("oxidized_copper_hoe",     () -> new HoeItem(    ModTiers.OXIDIZED_COPPER, -2,    -1.0F, new Item.Properties()));
	public static final DeferredItem<SwordItem>   OXIDIZED_COPPER_SWORD   = ITEMS.register("oxidized_copper_sword",   () -> new SwordItem(  ModTiers.OXIDIZED_COPPER,  3,    -2.4F, new Item.Properties()));
	
	// Oxidized Copper Armor
	public static final DeferredItem<ArmorItem> OXIDIZED_COPPER_HELMET     = ITEMS.register("oxidized_copper_helmet",     () -> new ArmorItem(ModArmorMaterials.OXIDIZED_COPPER, ArmorItem.Type.HELMET,     new Item.Properties()));
	public static final DeferredItem<ArmorItem> OXIDIZED_COPPER_CHESTPLATE = ITEMS.register("oxidized_copper_chestplate", () -> new ArmorItem(ModArmorMaterials.OXIDIZED_COPPER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
	public static final DeferredItem<ArmorItem> OXIDIZED_COPPER_LEGGINGS   = ITEMS.register("oxidized_copper_leggings",   () -> new ArmorItem(ModArmorMaterials.OXIDIZED_COPPER, ArmorItem.Type.LEGGINGS,   new Item.Properties()));
	public static final DeferredItem<ArmorItem> OXIDIZED_COPPER_BOOTS      = ITEMS.register("oxidized_copper_boots",      () -> new ArmorItem(ModArmorMaterials.OXIDIZED_COPPER, ArmorItem.Type.BOOTS,      new Item.Properties()));
	
	// Waxed Copper Items
	public static final DeferredItem<ShovelItem>  WAXED_COPPER_SHOVEL  = ITEMS.register("waxed_copper_shovel",  () -> new ShovelItem( ModTiers.COPPER,  1.5F, -3.0F, new Item.Properties()));
	public static final DeferredItem<PickaxeItem> WAXED_COPPER_PICKAXE = ITEMS.register("waxed_copper_pickaxe", () -> new PickaxeItem(ModTiers.COPPER,  1,    -2.8F, new Item.Properties()));
	public static final DeferredItem<AxeItem>     WAXED_COPPER_AXE     = ITEMS.register("waxed_copper_axe",     () -> new AxeItem(    ModTiers.COPPER,  6.0F, -3.1F, new Item.Properties()));
	public static final DeferredItem<HoeItem>     WAXED_COPPER_HOE     = ITEMS.register("waxed_copper_hoe",     () -> new HoeItem(    ModTiers.COPPER, -2,    -1.0F, new Item.Properties()));
	public static final DeferredItem<SwordItem>   WAXED_COPPER_SWORD   = ITEMS.register("waxed_copper_sword",   () -> new SwordItem(  ModTiers.COPPER,  3,    -2.4F, new Item.Properties()));
	
	// Waxed Copper Armor
	public static final DeferredItem<ArmorItem> WAXED_COPPER_HELMET     = ITEMS.register("waxed_copper_helmet",     () -> new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.HELMET,     new Item.Properties()));
	public static final DeferredItem<ArmorItem> WAXED_COPPER_CHESTPLATE = ITEMS.register("waxed_copper_chestplate", () -> new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
	public static final DeferredItem<ArmorItem> WAXED_COPPER_LEGGINGS   = ITEMS.register("waxed_copper_leggings",   () -> new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.LEGGINGS,   new Item.Properties()));
	public static final DeferredItem<ArmorItem> WAXED_COPPER_BOOTS      = ITEMS.register("waxed_copper_boots",      () -> new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.BOOTS,      new Item.Properties()));
	
	// Waxed Exposed Copper Items
	public static final DeferredItem<ShovelItem>  WAXED_EXPOSED_COPPER_SHOVEL  = ITEMS.register("waxed_exposed_copper_shovel",  () -> new ShovelItem( ModTiers.EXPOSED_COPPER,  1.5F, -3.0F, new Item.Properties()));
	public static final DeferredItem<PickaxeItem> WAXED_EXPOSED_COPPER_PICKAXE = ITEMS.register("waxed_exposed_copper_pickaxe", () -> new PickaxeItem(ModTiers.EXPOSED_COPPER,  1,    -2.8F, new Item.Properties()));
	public static final DeferredItem<AxeItem>     WAXED_EXPOSED_COPPER_AXE     = ITEMS.register("waxed_exposed_copper_axe",     () -> new AxeItem(    ModTiers.EXPOSED_COPPER,  6.0F, -3.1F, new Item.Properties()));
	public static final DeferredItem<HoeItem>     WAXED_EXPOSED_COPPER_HOE     = ITEMS.register("waxed_exposed_copper_hoe",     () -> new HoeItem(    ModTiers.EXPOSED_COPPER, -2,    -1.0F, new Item.Properties()));
	public static final DeferredItem<SwordItem>   WAXED_EXPOSED_COPPER_SWORD   = ITEMS.register("waxed_exposed_copper_sword",   () -> new SwordItem(  ModTiers.EXPOSED_COPPER,  3,    -2.4F, new Item.Properties()));
	
	// Waxed Exposed Copper Armor
	public static final DeferredItem<ArmorItem> WAXED_EXPOSED_COPPER_HELMET     = ITEMS.register("waxed_exposed_copper_helmet",     () -> new ArmorItem(ModArmorMaterials.EXPOSED_COPPER, ArmorItem.Type.HELMET,     new Item.Properties()));
	public static final DeferredItem<ArmorItem> WAXED_EXPOSED_COPPER_CHESTPLATE = ITEMS.register("waxed_exposed_copper_chestplate", () -> new ArmorItem(ModArmorMaterials.EXPOSED_COPPER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
	public static final DeferredItem<ArmorItem> WAXED_EXPOSED_COPPER_LEGGINGS   = ITEMS.register("waxed_exposed_copper_leggings",   () -> new ArmorItem(ModArmorMaterials.EXPOSED_COPPER, ArmorItem.Type.LEGGINGS,   new Item.Properties()));
	public static final DeferredItem<ArmorItem> WAXED_EXPOSED_COPPER_BOOTS      = ITEMS.register("waxed_exposed_copper_boots",      () -> new ArmorItem(ModArmorMaterials.EXPOSED_COPPER, ArmorItem.Type.BOOTS,      new Item.Properties()));
	
	// Waxed Weathered Copper Items
	public static final DeferredItem<ShovelItem>  WAXED_WEATHERED_COPPER_SHOVEL  = ITEMS.register("waxed_weathered_copper_shovel",  () -> new ShovelItem( ModTiers.WEATHERED_COPPER,  1.5F, -3.0F, new Item.Properties()));
	public static final DeferredItem<PickaxeItem> WAXED_WEATHERED_COPPER_PICKAXE = ITEMS.register("waxed_weathered_copper_pickaxe", () -> new PickaxeItem(ModTiers.WEATHERED_COPPER,  1,    -2.8F, new Item.Properties()));
	public static final DeferredItem<AxeItem>     WAXED_WEATHERED_COPPER_AXE     = ITEMS.register("waxed_weathered_copper_axe",     () -> new AxeItem(    ModTiers.WEATHERED_COPPER,  6.0F, -3.1F, new Item.Properties()));
	public static final DeferredItem<HoeItem>     WAXED_WEATHERED_COPPER_HOE     = ITEMS.register("waxed_weathered_copper_hoe",     () -> new HoeItem(    ModTiers.WEATHERED_COPPER, -2,    -1.0F, new Item.Properties()));
	public static final DeferredItem<SwordItem>   WAXED_WEATHERED_COPPER_SWORD   = ITEMS.register("waxed_weathered_copper_sword",   () -> new SwordItem(  ModTiers.WEATHERED_COPPER,  3,    -2.4F, new Item.Properties()));
	
	// Waxed Weathered Copper Armor
	public static final DeferredItem<ArmorItem> WAXED_WEATHERED_COPPER_HELMET     = ITEMS.register("waxed_weathered_copper_helmet",     () -> new ArmorItem(ModArmorMaterials.WEATHERED_COPPER, ArmorItem.Type.HELMET,     new Item.Properties()));
	public static final DeferredItem<ArmorItem> WAXED_WEATHERED_COPPER_CHESTPLATE = ITEMS.register("waxed_weathered_copper_chestplate", () -> new ArmorItem(ModArmorMaterials.WEATHERED_COPPER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
	public static final DeferredItem<ArmorItem> WAXED_WEATHERED_COPPER_LEGGINGS   = ITEMS.register("waxed_weathered_copper_leggings",   () -> new ArmorItem(ModArmorMaterials.WEATHERED_COPPER, ArmorItem.Type.LEGGINGS,   new Item.Properties()));
	public static final DeferredItem<ArmorItem> WAXED_WEATHERED_COPPER_BOOTS      = ITEMS.register("waxed_weathered_copper_boots",      () -> new ArmorItem(ModArmorMaterials.WEATHERED_COPPER, ArmorItem.Type.BOOTS,      new Item.Properties()));
	
	// Waxed Oxidized Copper Items
	public static final DeferredItem<ShovelItem>  WAXED_OXIDIZED_COPPER_SHOVEL  = ITEMS.register("waxed_oxidized_copper_shovel",  () -> new ShovelItem( ModTiers.OXIDIZED_COPPER,  1.5F, -3.0F, new Item.Properties()));
	public static final DeferredItem<PickaxeItem> WAXED_OXIDIZED_COPPER_PICKAXE = ITEMS.register("waxed_oxidized_copper_pickaxe", () -> new PickaxeItem(ModTiers.OXIDIZED_COPPER,  1,    -2.8F, new Item.Properties()));
	public static final DeferredItem<AxeItem>     WAXED_OXIDIZED_COPPER_AXE     = ITEMS.register("waxed_oxidized_copper_axe",     () -> new AxeItem(    ModTiers.OXIDIZED_COPPER,  6.0F, -3.1F, new Item.Properties()));
	public static final DeferredItem<HoeItem>     WAXED_OXIDIZED_COPPER_HOE     = ITEMS.register("waxed_oxidized_copper_hoe",     () -> new HoeItem(    ModTiers.OXIDIZED_COPPER, -2,    -1.0F, new Item.Properties()));
	public static final DeferredItem<SwordItem>   WAXED_OXIDIZED_COPPER_SWORD   = ITEMS.register("waxed_oxidized_copper_sword",   () -> new SwordItem(  ModTiers.OXIDIZED_COPPER,  3,    -2.4F, new Item.Properties()));
	
	// Waxed Oxidized Copper Armor
	public static final DeferredItem<ArmorItem> WAXED_OXIDIZED_COPPER_HELMET     = ITEMS.register("waxed_oxidized_copper_helmet",     () -> new ArmorItem(ModArmorMaterials.OXIDIZED_COPPER, ArmorItem.Type.HELMET,     new Item.Properties()));
	public static final DeferredItem<ArmorItem> WAXED_OXIDIZED_COPPER_CHESTPLATE = ITEMS.register("waxed_oxidized_copper_chestplate", () -> new ArmorItem(ModArmorMaterials.OXIDIZED_COPPER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
	public static final DeferredItem<ArmorItem> WAXED_OXIDIZED_COPPER_LEGGINGS   = ITEMS.register("waxed_oxidized_copper_leggings",   () -> new ArmorItem(ModArmorMaterials.OXIDIZED_COPPER, ArmorItem.Type.LEGGINGS,   new Item.Properties()));
	public static final DeferredItem<ArmorItem> WAXED_OXIDIZED_COPPER_BOOTS      = ITEMS.register("waxed_oxidized_copper_boots",      () -> new ArmorItem(ModArmorMaterials.OXIDIZED_COPPER, ArmorItem.Type.BOOTS,      new Item.Properties()));
	
	public static void register(IEventBus modEventBus) {
		ITEMS.register(modEventBus);
	}
}
