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

import java.util.function.Supplier;

import com.github.shadowgry.coppertools.CopperTools;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public enum ModArmorMaterials implements ArmorMaterial {
	COPPER(25, new int[] {2, 5, 6, 2}, 12, SoundEvents.ARMOR_EQUIP_IRON,
			CopperTools.MOD_ID + ":copper", 1.0F, () -> {
				return Ingredient.of(Items.COPPER_INGOT);
			},
			0.0F
	),
	EXPOSED_COPPER(25, new int[] {2, 5, 6, 2}, 12, SoundEvents.ARMOR_EQUIP_IRON,
			CopperTools.MOD_ID + ":exposed_copper", 1.0F, () -> {
				return Ingredient.of(Items.COPPER_INGOT);
			},
			0.0F
	),
	WEATHERED_COPPER(25, new int[] {2, 5, 6, 2}, 12, SoundEvents.ARMOR_EQUIP_IRON,
			CopperTools.MOD_ID + ":weathered_copper", 1.0F, () -> {
				return Ingredient.of(Items.COPPER_INGOT);
			},
			0.0F
	),
	OXIDIZED_COPPER(25, new int[] {2, 5, 6, 2}, 12, SoundEvents.ARMOR_EQUIP_IRON,
			CopperTools.MOD_ID + ":oxidized_copper", 1.0F, () -> {
				return Ingredient.of(Items.COPPER_INGOT);
			},
			0.0F
	);
	
	private static int[] MAX_DAMAGE = new int[] {11, 16, 15, 13};
	private int maxDamageFactor;
	private int[] damageReductionAmount;
	private int enchantmentValue;
	private SoundEvent equipSound;
	private String name;
	private float toughness;
	private Supplier<Ingredient> repairIngredient;
	private float knockbackResistance;
	
	ModArmorMaterials(int maxDamageFactor, int[] damageReductionAmount, int enchantmentValue, SoundEvent equipSound,
					  String name, float toughness, Supplier<Ingredient> repairIngredient, float knockbackResistance) {
		this.maxDamageFactor = maxDamageFactor;
		this.damageReductionAmount = damageReductionAmount;
		this.enchantmentValue = enchantmentValue;
		this.equipSound = equipSound;
		this.name = name;
		this.toughness = toughness;
		this.repairIngredient = repairIngredient;
		this.knockbackResistance = knockbackResistance;
	}
	
	@Override
	public int getDurabilityForSlot(EquipmentSlot slotIn) {
		return MAX_DAMAGE[slotIn.getIndex()] * maxDamageFactor;
	}
	
	@Override
	public int getDefenseForSlot(EquipmentSlot slotIn) {
		return damageReductionAmount[slotIn.getIndex()];
	}
	
	@Override
	public int getEnchantmentValue() {
		return enchantmentValue;
	}
	
	@Override
	public SoundEvent getEquipSound() {
		return equipSound;
	}
	
	@Override
	public Ingredient getRepairIngredient() {
		return repairIngredient.get();
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public float getToughness() {
		return toughness;
	}
	
	@Override
	public float getKnockbackResistance() {
		return knockbackResistance;
	}
}
