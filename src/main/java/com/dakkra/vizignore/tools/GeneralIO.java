package com.dakkra.vizignore.tools;

import com.dakkra.vizignore.VizIgnore;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GeneralIO {

    public static BufferedImage readResourceImage(String imageName) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(VizIgnore.class.getResourceAsStream("/" + imageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static ArrayList<String> readTextResource(String resourceName) {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(VizIgnore.class.getResourceAsStream("/" + resourceName)));
        try {
            String buff;
            while ((buff = reader.readLine()) != null) {
                list.add(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}
