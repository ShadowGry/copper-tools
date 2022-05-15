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
import java.util.Map;

import com.github.shadowgry.coppertools.common.items.ModItems;
import com.github.shadowgry.coppertools.common.items.ModTiers;
import com.mojang.logging.LogUtils;

import org.slf4j.Logger;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

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
    
    public ModEventHandler() {
        isMapInitialized  = false;
        oxidizeData = null;
    }
    
    /**
     * Puts the copper pickaxe oxidization orderings into a map, for quick future lookup. Sets {@code isMapInitialized}
     * to true, as this only needs to be run once.
     * 
     * TODO: Run earlier during mod loading, rather than on first BreakEvent. 
     */
    private void initializeMap() {
        oxidizeData = new HashMap<>();
        oxidizeData.put(ModItems.COPPER_PICKAXE.get().asItem(),
                        new OxidizeData(ModItems.EXPOSED_COPPER_PICKAXE.get().getDefaultInstance(),
                            ModTiers.COPPER.getUses()/4,
                            ModTiers.EXPOSED_COPPER.getUses()/4));
        oxidizeData.put(ModItems.EXPOSED_COPPER_PICKAXE.get().asItem(),
                        new OxidizeData(ModItems.WEATHERED_COPPER_PICKAXE.get().getDefaultInstance(),
                            ModTiers.EXPOSED_COPPER.getUses() * 1/2,
                            ModTiers.WEATHERED_COPPER.getUses() * 1/2));
        oxidizeData.put(ModItems.WEATHERED_COPPER_PICKAXE.get().asItem(),
                        new OxidizeData(ModItems.OXIDIZED_COPPER_PICKAXE.get().getDefaultInstance(),
                                ModTiers.WEATHERED_COPPER.getUses() * 3/4,
                                ModTiers.OXIDIZED_COPPER.getUses() * 3/4));
        isMapInitialized = true;
    }
    
    /**
     * Oxidizes copper pickaxes to the next stage when they are damaged enough.
     */
    @SubscribeEvent
    public void onBreakEvent(BreakEvent event) {
        if(!isMapInitialized) {
            initializeMap();
        }
        
        Player player = event.getPlayer();
        if(!player.isCreative()) {
            ItemStack item = player.getMainHandItem();
            OxidizeData data = oxidizeData.get(item.getItem());

            if(data != null && item.getDamageValue() > data.getOxidizeDamageValue()) {
                ItemStack nextPickaxe = data.getNextTool();
                nextPickaxe.setDamageValue(data.getStartDamageValue());
                player.setItemInHand(InteractionHand.MAIN_HAND, nextPickaxe);
            }
        }
    }
    
    /**
     * Stores information to appropriately oxidize a copper tool to the next tool. Copper tools oxidize in a linear way.
     * The current (or previous tool) oxidizes to {@nextTool}, however, previous tool is not stored in this class - it
     * is recommended to use a map to store it and map it to the appropriate instance of this class.
     */
    private class OxidizeData {
        private ItemStack nextTool;
        private int oxidizeDamageValue;
        private int startDamageValue;
        
        /**
         * Creates a new cache to store information about a particular copper tool oxidization.
         * 
         * @param nextTool the new tool the old one turns into
         * @param oxidizeDamageValue the damage value required for the previous tool to oxidize into {@code nextTool}
         * @param startDamageValue the initial damage of {@code nextTool}
         */
        OxidizeData(ItemStack nextTool, int oxidizeDamageValue, int startDamageValue) {
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
