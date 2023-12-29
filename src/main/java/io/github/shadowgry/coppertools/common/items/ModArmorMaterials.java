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

import java.util.EnumMap;
import java.util.function.Supplier;

import io.github.shadowgry.coppertools.CopperTools;

import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public enum ModArmorMaterials implements ArmorMaterial {
	COPPER("copper", 25, Util.make(new EnumMap<>(ArmorItem.Type.class), (type) -> {
		type.put(ArmorItem.Type.BOOTS, 2);
		type.put(ArmorItem.Type.LEGGINGS, 5);
		type.put(ArmorItem.Type.CHESTPLATE, 6);
		type.put(ArmorItem.Type.HELMET, 2);
	}), 12, SoundEvents.ARMOR_EQUIP_IRON, 1.0F, 0.0F, () -> {
		return Ingredient.of(Items.COPPER_INGOT);
	}),
	EXPOSED_COPPER("exposed_copper", 25, Util.make(new EnumMap<>(ArmorItem.Type.class), (type) -> {
		type.put(ArmorItem.Type.BOOTS, 2);
		type.put(ArmorItem.Type.LEGGINGS, 5);
		type.put(ArmorItem.Type.CHESTPLATE, 6);
		type.put(ArmorItem.Type.HELMET, 2);
	}), 12, SoundEvents.ARMOR_EQUIP_IRON, 1.0F, 0.0F, () -> {
		return Ingredient.of(Items.COPPER_INGOT);
	}),
	WEATHERED_COPPER("weathered_copper", 25, Util.make(new EnumMap<>(ArmorItem.Type.class), (type) -> {
		type.put(ArmorItem.Type.BOOTS, 2);
		type.put(ArmorItem.Type.LEGGINGS, 5);
		type.put(ArmorItem.Type.CHESTPLATE, 6);
		type.put(ArmorItem.Type.HELMET, 2);
	}), 12, SoundEvents.ARMOR_EQUIP_IRON, 1.0F, 0.0F, () -> {
		return Ingredient.of(Items.COPPER_INGOT);
	}),
	OXIDIZED_COPPER("oxidized_copper", 25, Util.make(new EnumMap<>(ArmorItem.Type.class), (type) -> {
		type.put(ArmorItem.Type.BOOTS, 2);
		type.put(ArmorItem.Type.LEGGINGS, 5);
		type.put(ArmorItem.Type.CHESTPLATE, 6);
		type.put(ArmorItem.Type.HELMET, 2);
	}), 12, SoundEvents.ARMOR_EQUIP_IRON, 1.0F, 0.0F, () -> {
		return Ingredient.of(Items.COPPER_INGOT);
	});

	private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (type) -> {
		type.put(ArmorItem.Type.BOOTS, 13);
		type.put(ArmorItem.Type.LEGGINGS, 15);
		type.put(ArmorItem.Type.CHESTPLATE, 16);
		type.put(ArmorItem.Type.HELMET, 11);
	});
	private final String name;
	private final int durabilityMultiplier;
	private final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;
	private final int enchantmentValue;
	private final SoundEvent sound;
	private final float toughness;
	private final float knockbackResistance;
	private final Supplier<Ingredient> repairIngredient;

	ModArmorMaterials(String name, int durabilityMultiplier, EnumMap<ArmorItem.Type, Integer> protectionFunctionForType,
					  int enchantmentValue, SoundEvent sound, float toughness, float knockbackResistance,
					  Supplier<Ingredient> repairIngredient) {
		this.name = CopperTools.MOD_ID + ":" + name;
		this.durabilityMultiplier = durabilityMultiplier;
		this.protectionFunctionForType = protectionFunctionForType;
		this.enchantmentValue = enchantmentValue;
		this.sound = sound;
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
		this.repairIngredient = repairIngredient;
	}

	@Override
	public int getDurabilityForType(ArmorItem.Type type) {
		return HEALTH_FUNCTION_FOR_TYPE.get(type) * durabilityMultiplier;
	}

	@Override
	public int getDefenseForType(ArmorItem.Type type) {
		return protectionFunctionForType.get(type);
	}

	@Override
	public int getEnchantmentValue() {
		return enchantmentValue;
	}

	@Override
	public SoundEvent getEquipSound() {
		return sound;
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
