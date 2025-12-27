package pl.nosignhitbox.mixin;

import net.minecraft.block.AbstractSignBlock;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.block.BlockState;
import pl.nosignhitbox.NoSignHitBox;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class NoSignOutlineMixin {

    @Inject(
            method = "drawBlockOutline",
            at = @At("HEAD"),
            cancellable = true
    )
    private void removeSignOutline(BlockState state, CallbackInfo ci) {
        if (!NoSignHitBox.ENABLED) return;

        if (state.getBlock() instanceof AbstractSignBlock) {
            ci.cancel();
        }
    }
}
