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
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CopperTools.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeTabs {
	@SubscribeEvent
	static void buildContents(CreativeModeTabEvent.Register event) {
		event.registerCreativeModeTab(new ResourceLocation(CopperTools.MOD_ID, "coppertools"), builder ->
			builder.title(Component.translatable("itemGroup" + CopperTools.MOD_ID + ".copper_tools"))
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
		);
	}
}
