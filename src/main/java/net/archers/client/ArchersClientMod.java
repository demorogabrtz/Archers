package net.archers.client;

import net.archers.ArchersMod;
import net.archers.block.ArcherBlocks;
import net.archers.client.effect.HuntersMarkRenderer;
import net.archers.client.effect.RootsRenderer;
import net.archers.client.util.ArchersTooltip;
import net.archers.effect.Effects;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import net.spell_engine.api.effect.CustomModelStatusEffect;
import net.spell_engine.api.render.CustomModels;
import net.spell_engine.client.gui.SpellTooltip;

import java.util.List;

public class ArchersClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ArcherBlocks.WORKBENCH.block(), RenderLayer.getCutout());

        CustomModels.registerModelIds(List.of(
                HuntersMarkRenderer.modelId,
                RootsRenderer.modelId,
                Identifier.of(ArchersMod.ID, "projectile/magic_arrow")
        ));
        CustomModelStatusEffect.register(Effects.HUNTERS_MARK.effect, new HuntersMarkRenderer());
        CustomModelStatusEffect.register(Effects.ENTANGLING_ROOTS.effect, new RootsRenderer());

        ArchersTooltip.init();

        var config = ArchersMod.tweaksConfig.value;
        SpellTooltip.addDescriptionMutator(Identifier.of(ArchersMod.ID, "power_shot"), (args) -> {
            var description = args.description();
            var huntersMarkPercent = ((int)(config.hunters_mark_damage_per_stack * 100)) + "%";
            description = description.replace(SpellTooltip.placeholder("damage_taken"), "" + huntersMarkPercent);
            return description;
        });
    }
}
