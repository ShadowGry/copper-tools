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
package com.github.shadowgry.coppertools.common.items;

import com.github.shadowgry.coppertools.CopperTools;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CopperTools.MOD_ID);
    
    // Copper Items
    public static final RegistryObject<ShovelItem>  COPPER_SHOVEL  = ITEMS.register("copper_shovel",  () -> new ShovelItem( ModTiers.COPPER,  1.5F, -3.0F, new Item.Properties().tab(CopperTools.TAB_COPPER_TOOLS)));
    public static final RegistryObject<PickaxeItem> COPPER_PICKAXE = ITEMS.register("copper_pickaxe", () -> new PickaxeItem(ModTiers.COPPER,  1,    -2.8F, new Item.Properties().tab(CopperTools.TAB_COPPER_TOOLS)));
    public static final RegistryObject<AxeItem>     COPPER_AXE     = ITEMS.register("copper_axe",     () -> new AxeItem(    ModTiers.COPPER,  6.0F, -3.1F, new Item.Properties().tab(CopperTools.TAB_COPPER_TOOLS)));
    public static final RegistryObject<HoeItem>     COPPER_HOE     = ITEMS.register("copper_hoe",     () -> new HoeItem(    ModTiers.COPPER, -2,    -1.0F, new Item.Properties().tab(CopperTools.TAB_COPPER_TOOLS)));
    public static final RegistryObject<SwordItem>   COPPER_SWORD   = ITEMS.register("copper_sword",   () -> new SwordItem(  ModTiers.COPPER,  3,    -2.4F, new Item.Properties().tab(CopperTools.TAB_COPPER_TOOLS)));

    // Copper Armor
    public static final RegistryObject<ArmorItem> COPPER_HELMET     = ITEMS.register("copper_helmet",     () -> new ArmorItem(ModArmorMaterials.COPPER, EquipmentSlot.HEAD,  new Item.Properties().tab(CopperTools.TAB_COPPER_TOOLS)));
    public static final RegistryObject<ArmorItem> COPPER_CHESTPLATE = ITEMS.register("copper_chestplate", () -> new ArmorItem(ModArmorMaterials.COPPER, EquipmentSlot.CHEST, new Item.Properties().tab(CopperTools.TAB_COPPER_TOOLS)));
    public static final RegistryObject<ArmorItem> COPPER_LEGGINGS   = ITEMS.register("copper_leggings",   () -> new ArmorItem(ModArmorMaterials.COPPER, EquipmentSlot.LEGS,  new Item.Properties().tab(CopperTools.TAB_COPPER_TOOLS)));
    public static final RegistryObject<ArmorItem> COPPER_BOOTS      = ITEMS.register("copper_boots",      () -> new ArmorItem(ModArmorMaterials.COPPER, EquipmentSlot.FEET,  new Item.Properties().tab(CopperTools.TAB_COPPER_TOOLS)));
    
    public static void registerItems() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
