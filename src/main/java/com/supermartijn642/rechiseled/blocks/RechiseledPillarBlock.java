package com.supermartijn642.rechiseled.blocks;

import com.supermartijn642.core.block.BlockProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;

import javax.annotation.Nullable;

/**
 * Created 04/05/2023 by SuperMartijn642
 */
public class RechiseledPillarBlock extends RechiseledBlock {

    public static final EnumProperty<Direction.Axis> AXIS_PROPERTY = BlockStateProperties.AXIS;

    public RechiseledPillarBlock(boolean connecting, BlockProperties properties){
        super(connecting, properties);
        this.registerDefaultState(this.defaultBlockState().setValue(AXIS_PROPERTY, Direction.Axis.Y));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context){
        return this.defaultBlockState().setValue(AXIS_PROPERTY, context.getClickedFace().getAxis());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation){
        if(rotation == Rotation.CLOCKWISE_90 || rotation == Rotation.COUNTERCLOCKWISE_90){
            Direction.Axis axis = state.getValue(AXIS_PROPERTY);
            if(axis != Direction.Axis.Y)
                return state.setValue(AXIS_PROPERTY, axis == Direction.Axis.X ? Direction.Axis.Z : Direction.Axis.X);
        }
        return state;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block,BlockState> builder){
        builder.add(AXIS_PROPERTY);
    }
}
