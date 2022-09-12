package com.supermartijn642.rechiseled.texture;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created 24/01/2022 by SuperMartijn642
 */
public class TextureMappingTool {

    public static Map<Integer,Integer> createPaletteMap(BufferedImage from, BufferedImage to){
        if(from.getWidth() != to.getWidth() || from.getHeight() != to.getHeight())
            throw new IllegalArgumentException("Old palette image must be the same size as new palette image!");

        Map<Integer,Integer> palette = new HashMap<>();
        for(int x = 0; x < from.getWidth(); x++){
            for(int y = 0; y < from.getHeight(); y++){
                palette.put(from.getRGB(x, y), to.getRGB(x, y));
            }
        }
        return palette;
    }

    public static void applyPaletteMap(BufferedImage image, Map<Integer,Integer> palette, String name){
        for(int x = 0; x < image.getWidth(); x++){
            for(int y = 0; y < image.getHeight(); y++){
                int color = image.getRGB(x, y);
                if(color == 0)
                    continue;
                if(!palette.containsKey(color))
                    throw new IllegalStateException("Palette is missing color '" + new Color(color) + "' for pattern '" + name + "'");

                image.setRGB(x, y, palette.get(color));
            }
        }
    }

    /**
     * Finds the suffixes of all rechiseled textures whose names start with {@code name}
     */
    public static List<String> getSuffixes(String name){
        List<String> suffixes = new ArrayList<>();

        ModContainer container = Loader.instance().getActiveModList().stream().filter(modContainer -> modContainer.getModId().equals("rechiseled")).findAny().orElse(null);
        Path source = container.getSource().toPath().resolve("assets").resolve("rechiseled").resolve("textures").resolve("block");
        try(Stream<Path> paths = Files.walk(source, 1)){
            paths
                .map(Path::getFileName)
                .map(Object::toString)
                .filter(s -> s.startsWith(name))
                .filter(s -> s.endsWith(".png"))
                .map(s -> s.substring(name.length(), s.length() - ".png".length()))
                .forEach(suffixes::add);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        return suffixes;
    }
}
