package pl.nosignhitbox.mixin;

import net.minecraft.block.AbstractSignBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pl.nosignhitbox.NoSignHitBox;

@Mixin(AbstractSignBlock.class)
public class IgnoreSignRaycastMixin {

    @Inject(method = "getOutlineShape", at = @At("RETURN"), cancellable = true)
    private void shrinkSignHitbox(BlockState state, BlockView world, BlockPos pos, ShapeContext context, CallbackInfoReturnable<VoxelShape> cir) {
        if (!NoSignHitBox.ENABLED) return;

        // oryginalny kształt
        VoxelShape original = cir.getReturnValue();

        // zmniejszamy hitbox do np. 0.1 bloku szerokości i głębokości
        VoxelShape small = VoxelShapes.cuboid(0.45, 0, 0.45, 0.55, 1, 0.55);

        cir.setReturnValue(small);
    }
}
