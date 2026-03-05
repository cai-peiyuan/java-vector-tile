package com.mapabc;

import no.ecc.vectortile.VectorTileDecoder;
import org.locationtech.jts.geom.Geometry;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

public class VectorTileTest {
    public static void main(String[] args) throws IOException {
        VectorTileDecoder decoder = new VectorTileDecoder();
        File file = new File("D:\\v3-4326\\1.pbf");
        byte[] bytes = Files.readAllBytes(file.toPath());
        VectorTileDecoder.FeatureIterable decode = decoder.decode(bytes);

        System.out.println("========== 矢量瓦片解码结果 ==========");
        int index = 0;
        for (VectorTileDecoder.Feature feature : decode) {
            index++;
            System.out.println("--- Feature #" + index + " ---");
            System.out.println("  图层名(layerName): " + feature.getLayerName());
            System.out.println("  ID: " + feature.getId());
            System.out.println("  范围(extent): " + feature.getExtent());
            Geometry geom = feature.getGeometry();
            System.out.println("  几何(geometry): " + (geom != null ? geom.toString() : "null"));
            Map<String, Object> attrs = feature.getAttributes();
            if (attrs != null && !attrs.isEmpty()) {
                System.out.println("  属性(attributes):");
                attrs.forEach((k, v) -> System.out.println("    " + k + " = " + v));
            } else {
                System.out.println("  属性(attributes): (无)");
            }
            System.out.println();
        }
        System.out.println("========== 共 " + index + " 个要素 ==========");
    }
}
