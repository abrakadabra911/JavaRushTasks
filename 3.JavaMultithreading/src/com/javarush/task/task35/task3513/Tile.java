package com.javarush.task.task35.task3513;

import java.awt.*;
import java.util.ArrayList;

public class Tile {
    int value = 0;

    public Tile() {
    }

    public Tile(int value) {
        this.value = value;
    }

    public boolean isEmpty() {
        if(value == 0) return true;
        return false;
    }

    public Color getFontColor() {
        if (value < 16) return new Color(0x776e65);
        return new Color(0xf9f6f2);
    }

    public Color getTileColor() {
        Color result = new Color(0xff0000);
        switch (value) {
            case 0: result = new Color(0xcdc1b4); break;
            case 2: result = new Color(0xeee4da);break;
            case 4: result = new Color(0xede0c8);break;
            case 8: result = new Color(0xf2b179);break;
            case 16: result = new Color(0xf59563);break;
            case 32: result = new Color(0xf67c5f);break;
            case 64: result = new Color(0xf65e3b);break;
            case 128: result = new Color(0xedcf72);break;
            case 256: result = new Color(0xedcc61);break;
            case 512: result = new Color(0xedc850);break;
            case 1024: result = new Color(0xedc53f);break;
            case 2048: result = new Color(0xedc22e);break;
        }
        return result;
    }



}
