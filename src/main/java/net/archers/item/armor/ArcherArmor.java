package net.archers.item.armor;

import mod.azure.azurelibarmor.common.api.client.renderer.GeoArmorRenderer;
import mod.azure.azurelibarmor.common.api.common.animatable.GeoItem;
import mod.azure.azurelibarmor.common.internal.client.RenderProvider;
import mod.azure.azurelibarmor.common.internal.common.util.AzureLibUtil;
import mod.azure.azurelibarmor.core.animatable.instance.AnimatableInstanceCache;
import mod.azure.azurelibarmor.core.animation.AnimatableManager;
import net.archers.ArchersMod;
import net.archers.client.armor.ArcherArmorRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.spell_engine.api.item.armor.Armor;

import java.util.function.Consumer;

public class ArcherArmor extends Armor.CustomItem implements GeoItem {
    public ArcherArmor(RegistryEntry<ArmorMaterial> material, Type slot, Settings settings) {
        super(material, slot, settings);
    }
    public static final Identifier ARCHER_ARMOR_MODEL = Identifier.of(ArchersMod.ID, "archer_armor");
    public static final Identifier RANGER_ARMOR_MODEL = Identifier.of(ArchersMod.ID, "ranger_armor");

    public Identifier customModelId = ARCHER_ARMOR_MODEL;

    public static ArcherArmor archer(RegistryEntry<ArmorMaterial> material, Type slot, Settings settings) {
        var armor = new ArcherArmor(material, slot, settings);
        armor.customModelId = ARCHER_ARMOR_MODEL;
        return armor;
    }

    public static ArcherArmor ranger(RegistryEntry<ArmorMaterial> material, Type slot, Settings settings) {
        var armor = new ArcherArmor(material, slot, settings);
        armor.customModelId = RANGER_ARMOR_MODEL;
        return armor;
    }

    // MARK: GeoItem

    @Override
    public void createRenderer(Consumer<RenderProvider> consumer) {
        consumer.accept(new RenderProvider() {
            private GeoArmorRenderer<?> renderer;
            @Override
            public BipedEntityModel<LivingEntity> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, BipedEntityModel<LivingEntity> original) {
                if (this.renderer == null) {
                    this.renderer = new ArcherArmorRenderer();
                }
                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                return this.renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) { }

    private final AnimatableInstanceCache cache = AzureLibUtil.createInstanceCache(this);

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
