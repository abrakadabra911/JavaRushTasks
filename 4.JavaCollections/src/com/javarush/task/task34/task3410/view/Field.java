package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.controller.EventListener;
import com.javarush.task.task34.task3410.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Set;

public class Field extends JPanel {
    private View view;
    EventListener eventListener;

    public Field(View view) {
        this.view = view;
        KeyHandler keyHandler = new KeyHandler();
        addKeyListener(keyHandler);
        setFocusable(true);
    }

    @Override
    public void paint(Graphics g) {
        Set<GameObject> allGameObjects = view.getGameObjects().getAll();
        Set<Home> homes = view.getGameObjects().getHomes();
        Player player = view.getGameObjects().getPlayer();
        allGameObjects.remove(player);
        allGameObjects.removeAll(homes);
        g.setColor(new Color(230, 220, 200));
        g.fillRect(0, 0, 540, 580);
        g.setColor(Color.black);
        g.setFont(new Font("MyFont", Font.ITALIC, 12));
        // logo
        g.drawString("The game was created by Aliaksei Zayats", 280, 538);
        g.drawString("on the base of JavaRush online java-course", 280, 550);
        for (GameObject object : allGameObjects) object.draw(g);
        for (Home home : homes) home.draw(g); // we need to have homes draw above boxes
        player.draw(g);// we need to have player draw above homes
    }


    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case (KeyEvent.VK_LEFT):
                    eventListener.move(Direction.LEFT);
                    break;
                case (KeyEvent.VK_RIGHT):
                    eventListener.move(Direction.RIGHT);
                    break;
                case (KeyEvent.VK_UP):
                    eventListener.move(Direction.UP);
                    break;
                case (KeyEvent.VK_DOWN):
                    eventListener.move(Direction.DOWN);
                    break;
                case (KeyEvent.VK_R):
                    eventListener.restart();
                    break;
            }
        }
    }
}

