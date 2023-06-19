package com.supermartijn642.rechiseled;

import com.mojang.blaze3d.platform.GlStateManager;
import com.supermartijn642.core.ClientUtils;
import com.supermartijn642.core.render.CustomItemRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;

/**
 * Created 18/06/2023 by SuperMartijn642
 */
public class ChiselItemRenderer implements CustomItemRenderer {

    @Override
    public void render(ItemStack stack){
        renderChisel(stack);
        ItemStack storedStack = ChiselItem.getStoredStack(stack);
        if(!storedStack.isEmpty()){
            GlStateManager.pushMatrix();
            GlStateManager.translatef(0.25f, 0.75f, 0.5f);
            GlStateManager.scalef(0.5f, 0.5f, 0.5f);
            ClientUtils.getItemRenderer().renderStatic(storedStack, ItemCameraTransforms.TransformType.GUI);
            GlStateManager.popMatrix();
        }
    }

    private static void renderChisel(ItemStack stack){
        ItemRenderer renderer = ClientUtils.getItemRenderer();
        IBakedModel model = renderer.getModel(stack);
        renderer.renderModelLists(model, stack);
        if(stack.hasFoil()){
            ItemRenderer.renderFoilLayer(ClientUtils.getTextureManager(), () -> {
                renderer.renderModelLists(model, -8372020);
            }, 8);
        }
    }
}