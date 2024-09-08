package net.archers.client.armor;

import mod.azure.azurelibarmor.common.api.client.model.GeoModel;
import net.archers.ArchersMod;
import net.archers.item.armor.ArcherArmor;
import net.minecraft.util.Identifier;

public class ArcherArmorModel extends GeoModel<ArcherArmor> {
    @Override
    public Identifier getModelResource(ArcherArmor armor) {
        var textureId = armor.getFirstLayerId();
        return Identifier.of(ArchersMod.ID, "geo/" + textureId.getPath() + ".geo.json");
    }

    @Override
    public Identifier getTextureResource(ArcherArmor armor) {
        var textureId = armor.getFirstLayerId();
        return Identifier.of(textureId.getNamespace(), "textures/armor/" + textureId.getPath() + ".png");
    }

    @Override
    public Identifier getAnimationResource(ArcherArmor animatable) {
        return null;
    }
}
