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

import com.github.shadowgry.coppertools.CopperTools;
import com.github.shadowgry.coppertools.common.items.ModItems;
import com.mojang.logging.LogUtils;

import org.slf4j.Logger;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = CopperTools.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class ModEventHandler {
    
    public static final Logger LOGGER = LogUtils.getLogger();
    
    /**
     * Oxidizes a copper pickaxe into an exposed copper pickaxe after its 
     * damage value reaches 50.
     */
    @SubscribeEvent
    public static void onBreakEvent(BreakEvent event) {
        Player player = event.getPlayer();
        if(!player.isCreative()) {
            ItemStack item = player.getMainHandItem();
            if(item.getItem() == ModItems.COPPER_PICKAXE.get().asItem()) {
                if(item.getDamageValue() > 50) {
                    ItemStack oxidizedTool = ModItems.EXPOSED_COPPER_PICKAXE.get().getDefaultInstance();
                    oxidizedTool.setDamageValue(40);
                    player.setItemInHand(InteractionHand.MAIN_HAND, oxidizedTool);
                }
            }
        }
    }
}
