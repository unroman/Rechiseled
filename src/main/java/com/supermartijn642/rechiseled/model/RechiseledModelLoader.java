package com.supermartijn642.rechiseled.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.resources.IResourceManager;
import net.minecraftforge.client.model.IModelLoader;

/**
 * Created 21/12/2021 by SuperMartijn642
 */
@Deprecated
public class RechiseledModelLoader implements IModelLoader<RechiseledModel> {

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager){
    }

    @Override
    public RechiseledModel read(JsonDeserializationContext context, JsonObject json){
        return RechiseledModelDeserializer.deserialize(json, context);
    }
}
