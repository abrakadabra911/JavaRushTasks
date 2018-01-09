package com.javarush.task.task34.task3410.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Wall extends CollisionObject {
    private static BufferedImage image;
    private  String RESOURCE_PATH =  getClass().getPackage().getName()
            .replaceAll("\\.", "/")
            .replace("model", "pic/wall1.jpg");

    public Wall(int x, int y) {
        super(x, y);
        try {
            image = ImageIO.read(new File(getClass().getClassLoader()
                    .getResource(RESOURCE_PATH).toURI()));
        }
        catch (Exception e){e.printStackTrace();}
    }

    @Override
    public void draw(Graphics graphics) {
      int width = Model.FIELD_CELL_SIZE;
        graphics.drawImage(image, getX()-width/2, getY()-width/2,null);
    }

}
