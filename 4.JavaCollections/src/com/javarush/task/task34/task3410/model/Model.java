package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Model {
    public static final int FIELD_CELL_SIZE = 20; //dimention of game cell
    EventListener eventListener;

    private GameObjects gameObjects;

    private int currentLevel = 1;

    public  String RESOURCE_PATH =  getClass().getPackage().getName()
            .replaceAll("\\.", "/")
            .replace("model", "res/levels.txt");

    public Path getLevelsPath() {
        Path path = null;
        try{
             path = Paths.get(getClass().getClassLoader()
                    .getResource(RESOURCE_PATH).toURI());
        }
        catch (URISyntaxException e){}
        return path;
    }

    private LevelLoader levelLoader = new LevelLoader(getLevelsPath());



    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel((level));
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        currentLevel++;
        restartLevel(currentLevel);
    }

    public void move(Direction direction) {
        Player player = getGameObjects().getPlayer();
        if (checkWallCollision(player, direction)) return;
        if (checkBoxCollisionAndMoveIfAvaliable(direction)) return;
        player.move(direction);
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (Wall wall : getGameObjects().getWalls()) {
            if (gameObject.isCollision(wall, direction)) return true;
        }
        return false;
    }

    public boolean checkBoxCollisionAndMoveIfAvaliable(Direction direction) {
        Player player = getGameObjects().getPlayer();
        for (Box box : getGameObjects().getBoxes()) {
            if (player.isCollision(box, direction) && (checkBoxCollision(box, direction) || checkWallCollision(box, direction)))
                return true;
            if (player.isCollision(box, direction) &&
                    !checkBoxCollision(box, direction) &&
                    !checkWallCollision(box, direction)) {
                box.move(direction);
                return false;
            }
        }
        return false;
    }

    public boolean checkBoxCollision(CollisionObject gameObject, Direction direction) {
        for (GameObject box : getGameObjects().getBoxes()) {
            if (gameObject.isCollision(box, direction)) return true;
        }
        return false;
    }

    public void checkCompletion() {
        boolean isBoxPresent;
        for (GameObject home : getGameObjects().getHomes()) {
            isBoxPresent = false;
            for (GameObject box : getGameObjects().getBoxes()) {
                if (box.getX() == home.getX() && box.getY() == home.getY()) {
                    isBoxPresent = true;
                    break;
                }
            }
            if (!isBoxPresent) return;
        }
        eventListener.levelCompleted(currentLevel);
    }


}
