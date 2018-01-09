package com.javarush.task.task34.task3410.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Player extends CollisionObject implements Movable {
    private static BufferedImage imageLeft;
    private static BufferedImage imageRight;
    private BufferedImage image = imageLeft;

    private boolean rotatedToLeft = false;

    private String RESOURCE_PATH_LEFT = getClass().getPackage().getName()
            .replaceAll("\\.", "/")
            .replace("model", "pic/player_left.png");

    private String RESOURCE_PATH_RIGHT = getClass().getPackage().getName()
            .replaceAll("\\.", "/")
            .replace("model", "pic/player_right.png");

    public Player(int x, int y) {
        super(x, y);
        try {
            imageLeft = ImageIO.read(new File(getClass().getClassLoader()
                    .getResource(RESOURCE_PATH_LEFT).toURI()));
            imageRight = ImageIO.read(new File(getClass().getClassLoader()
                    .getResource(RESOURCE_PATH_RIGHT).toURI()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics graphics) {
        int width = Model.FIELD_CELL_SIZE;
        graphics.drawImage(image, getX() - width / 2, getY() - width / 2, null);
    }


    @Override
    public void move(int x, int y) {
        setX(getX() + x);
        setY(getY() + y);
    }

    @Override
    public void move(Direction direction) {
        int x = 0, y = 0;
        if (direction == Direction.UP) {
            x = 0;
            y = -Model.FIELD_CELL_SIZE;
        }
        if (direction == Direction.DOWN) {
            x = 0;
            y = +Model.FIELD_CELL_SIZE;
        }
        if (direction == Direction.LEFT) {
            x = -Model.FIELD_CELL_SIZE;
            y = 0;
            image = imageLeft;
        }
        if (direction == Direction.RIGHT) {
            x = Model.FIELD_CELL_SIZE;
            y = 0;
            image = imageRight;
        }
        move(x, y);
    }
}
