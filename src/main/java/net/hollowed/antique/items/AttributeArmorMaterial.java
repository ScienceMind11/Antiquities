//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.hollowed.antique.items;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.item.Item;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.Map;

public record AttributeArmorMaterial(int durability, Map<EquipmentType, Integer> defense, int enchantmentValue, RegistryEntry<SoundEvent> equipSound, float toughness, float knockbackResistance, TagKey<Item> repairIngredient, RegistryKey<EquipmentAsset> modelId) {
    public AttributeArmorMaterial(int durability, Map<EquipmentType, Integer> defense, int enchantmentValue, RegistryEntry<SoundEvent> equipSound, float toughness, float knockbackResistance, TagKey<Item> repairIngredient, RegistryKey<EquipmentAsset> modelId) {
        this.durability = durability;
        this.defense = defense;
        this.enchantmentValue = enchantmentValue;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
        this.modelId = modelId;
    }

    public Item.Settings applySettings(Item.Settings settings, EquipmentType equipmentType, AttributeModifiersComponent component) {
        return settings.maxDamage(equipmentType.getMaxDamage(this.durability)).attributeModifiers(component).enchantable(this.enchantmentValue).component(DataComponentTypes.EQUIPPABLE, EquippableComponent.builder(equipmentType.getEquipmentSlot()).equipSound(this.equipSound).model(this.modelId).build()).repairable(this.repairIngredient);
    }

    public Item.Settings applyBodyArmorSettings(Item.Settings settings, RegistryEntryList<EntityType<?>> allowedEntities) {
        return settings.maxDamage(EquipmentType.BODY.getMaxDamage(this.durability)).attributeModifiers(this.createAttributeModifiers(EquipmentType.BODY)).repairable(this.repairIngredient).component(DataComponentTypes.EQUIPPABLE, EquippableComponent.builder(EquipmentSlot.BODY).equipSound(this.equipSound).model(this.modelId).allowedEntities(allowedEntities).build());
    }

    public Item.Settings applyBodyArmorSettings(Item.Settings settings, RegistryEntry<SoundEvent> equipSound, boolean damageOnHurt, RegistryEntryList<EntityType<?>> allowedEntities) {
        if (damageOnHurt) {
            settings = settings.maxDamage(EquipmentType.BODY.getMaxDamage(this.durability)).repairable(this.repairIngredient);
        }

        return settings.attributeModifiers(this.createAttributeModifiers(EquipmentType.BODY)).component(DataComponentTypes.EQUIPPABLE, EquippableComponent.builder(EquipmentSlot.BODY).equipSound(equipSound).model(this.modelId).allowedEntities(allowedEntities).damageOnHurt(damageOnHurt).build());
    }

    private AttributeModifiersComponent createAttributeModifiers(EquipmentType equipmentType) {
        int i = (Integer)this.defense.getOrDefault(equipmentType, 0);
        AttributeModifiersComponent.Builder builder = AttributeModifiersComponent.builder();
        AttributeModifierSlot attributeModifierSlot = AttributeModifierSlot.forEquipmentSlot(equipmentType.getEquipmentSlot());
        Identifier identifier = Identifier.ofVanilla("armor." + equipmentType.getName());
        builder.add(EntityAttributes.ARMOR, new EntityAttributeModifier(identifier, (double)i, Operation.ADD_VALUE), attributeModifierSlot);
        builder.add(EntityAttributes.ARMOR_TOUGHNESS, new EntityAttributeModifier(identifier, (double)this.toughness, Operation.ADD_VALUE), attributeModifierSlot);
        if (this.knockbackResistance > 0.0F) {
            builder.add(EntityAttributes.KNOCKBACK_RESISTANCE, new EntityAttributeModifier(identifier, (double)this.knockbackResistance, Operation.ADD_VALUE), attributeModifierSlot);
        }

        return builder.build();
    }

    public int durability() {
        return this.durability;
    }

    public Map<EquipmentType, Integer> defense() {
        return this.defense;
    }

    public int enchantmentValue() {
        return this.enchantmentValue;
    }

    public RegistryEntry<SoundEvent> equipSound() {
        return this.equipSound;
    }

    public float toughness() {
        return this.toughness;
    }

    public float knockbackResistance() {
        return this.knockbackResistance;
    }

    public TagKey<Item> repairIngredient() {
        return this.repairIngredient;
    }

}
