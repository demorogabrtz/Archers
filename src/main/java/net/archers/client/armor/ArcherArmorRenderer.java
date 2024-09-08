package net.archers.client.armor;

import mod.azure.azurelibarmor.common.api.client.renderer.GeoArmorRenderer;
import net.archers.item.armor.ArcherArmor;

public class ArcherArmorRenderer extends GeoArmorRenderer<ArcherArmor> {
    public ArcherArmorRenderer() {
        super(new ArcherArmorModel());
    }
}
