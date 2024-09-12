//
// Created by BONNe
// Copyright - 2024
//


package lv.id.bonne.vaulthunters.borderextender.mixin;


import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import iskallia.vault.world.data.VirtualWorlds;
import net.minecraft.world.level.border.BorderChangeListener;
import net.minecraft.world.level.border.WorldBorder;


@Mixin(value = VirtualWorlds.class, remap = false)
public class MixinVirtualWorlds
{
    @Redirect(method = "load(Liskallia/vault/core/world/storage/VirtualWorld;)Liskallia/vault/core/world/storage/VirtualWorld;",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/border/WorldBorder;addListener(Lnet/minecraft/world/level/border/BorderChangeListener;)V"))
    private static void skipListener(WorldBorder instance, BorderChangeListener p_61930_)
    {
    }
}
