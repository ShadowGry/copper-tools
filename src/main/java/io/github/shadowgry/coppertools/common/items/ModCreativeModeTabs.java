/*
 * This file is part of Copper Tools.
 * Copyright (C) 2023  ShadowGry
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
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {

	private static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CopperTools.MOD_ID);

	public static final RegistryObject<CreativeModeTab> COPPER_TOOLS = CREATIVE_MODE_TABS.register("coppertools", () -> CreativeModeTab.builder()
		.title(Component.translatable("item_group." + CopperTools.MOD_ID + ".copper_tools"))
		.icon(() -> new ItemStack(ModItems.COPPER_PICKAXE.get()))
		.displayItems((params, output) -> {
			output.accept(ModItems.COPPER_SHOVEL.get());
			output.accept(ModItems.COPPER_PICKAXE.get());
			output.accept(ModItems.COPPER_AXE.get());
			output.accept(ModItems.COPPER_HOE.get());
			output.accept(ModItems.COPPER_SWORD.get());
			
			output.accept(ModItems.COPPER_HELMET.get());
			output.accept(ModItems.COPPER_CHESTPLATE.get());
			output.accept(ModItems.COPPER_LEGGINGS.get());
			output.accept(ModItems.COPPER_BOOTS.get());
			
			output.accept(ModItems.EXPOSED_COPPER_SHOVEL.get());
			output.accept(ModItems.EXPOSED_COPPER_PICKAXE.get());
			output.accept(ModItems.EXPOSED_COPPER_AXE.get());
			output.accept(ModItems.EXPOSED_COPPER_HOE.get());
			output.accept(ModItems.EXPOSED_COPPER_SWORD.get());
			
			output.accept(ModItems.EXPOSED_COPPER_HELMET.get());
			output.accept(ModItems.EXPOSED_COPPER_CHESTPLATE.get());
			output.accept(ModItems.EXPOSED_COPPER_LEGGINGS.get());
			output.accept(ModItems.EXPOSED_COPPER_BOOTS.get());
			
			output.accept(ModItems.WEATHERED_COPPER_SHOVEL.get());
			output.accept(ModItems.WEATHERED_COPPER_PICKAXE.get());
			output.accept(ModItems.WEATHERED_COPPER_AXE.get());
			output.accept(ModItems.WEATHERED_COPPER_HOE.get());
			output.accept(ModItems.WEATHERED_COPPER_SWORD.get());
			
			output.accept(ModItems.WEATHERED_COPPER_HELMET.get());
			output.accept(ModItems.WEATHERED_COPPER_CHESTPLATE.get());
			output.accept(ModItems.WEATHERED_COPPER_LEGGINGS.get());
			output.accept(ModItems.WEATHERED_COPPER_BOOTS.get());
			
			output.accept(ModItems.OXIDIZED_COPPER_SHOVEL.get());
			output.accept(ModItems.OXIDIZED_COPPER_PICKAXE.get());
			output.accept(ModItems.OXIDIZED_COPPER_AXE.get());
			output.accept(ModItems.OXIDIZED_COPPER_HOE.get());
			output.accept(ModItems.OXIDIZED_COPPER_SWORD.get());
			
			output.accept(ModItems.OXIDIZED_COPPER_HELMET.get());
			output.accept(ModItems.OXIDIZED_COPPER_CHESTPLATE.get());
			output.accept(ModItems.OXIDIZED_COPPER_LEGGINGS.get());
			output.accept(ModItems.OXIDIZED_COPPER_BOOTS.get());
		})
		.build()
	);

	public static void register() {
		CREATIVE_MODE_TABS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
}
