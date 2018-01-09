package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;

public class Model{
    private static final int FIELD_WIDTH = 4;
    private  Tile [][] gameTiles;
    int score;
    int maxTile;
    boolean isSaveNeeded = true;

    private Stack previousStates = new Stack();
    private Stack previousScores = new Stack();

    public Model() {
        resetGameTiles();
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    // проверка возможности хода
    public boolean canMove() {
        if (!getEmptyTiles().isEmpty()) return true;

        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 1; j < gameTiles.length; j++) {
                if (gameTiles[i][j].value == gameTiles[i][j - 1].value) return true;
            }
        }

        for (int j = 0; j < gameTiles.length; j++) {
            for (int i = 1; i < gameTiles.length; i++) {
                if (gameTiles[i][j].value == gameTiles[i - 1][j].value) return true;
            }
        }
        return false;
    }

    public   ArrayList<Tile> getEmptyTiles() {
        ArrayList<Tile> emptyTiles = new ArrayList<>();

        for(int i = 0; i < FIELD_WIDTH; i++) {
            for(int j = 0; j < FIELD_WIDTH; j++) {
                if(gameTiles[i][j].value == 0) emptyTiles.add(gameTiles[i][j]);
            }
        }
        return emptyTiles;
    }

    private void addTile() {
        ArrayList<Tile> emptyTiles = getEmptyTiles();
        if (emptyTiles != null && emptyTiles.size() != 0) {
            emptyTiles.get((int) (Math.random() * emptyTiles.size())).value = Math.random() < 0.9 ? 2 : 4;
        }
    }

    void resetGameTiles() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for(int i = 0; i < FIELD_WIDTH; i++) {
            for(int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
        score = 0;
        maxTile = 2;
    }

    private boolean compressTiles(Tile[] tiles){
        boolean flag = false;
        Tile temp;
        for (int i = 0; i < tiles.length - 1; i++) {
            for (int j = 0; j < tiles.length - 1; j++) {
                if (tiles[j].value == 0 && tiles[j + 1].value != 0) {
                    temp = tiles[j];
                    tiles[j] = tiles[j + 1];
                    tiles[j + 1] = temp;
                    flag = true;
                }
            }
        }
        return flag;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean flag = false;
        for (int j = 0; j < tiles.length - 1; j++) {
            if (tiles[j].value != 0 && tiles[j].value == tiles[j + 1].value) {
                tiles[j].value = (tiles[j].value * 2);
                tiles[j + 1].value = 0;
                flag = true;
                if (tiles[j].value > maxTile) maxTile = tiles[j].value;
                score += tiles[j].value;
            }
        }

            Tile temp;
            for (int j = 0; j < tiles.length - 1; j++) {
                if (tiles[j].value == 0 && tiles[j + 1].value != 0) {
                    temp = tiles[j];
                    tiles[j] = tiles[j + 1];
                    tiles[j + 1] = temp;
                    flag = true;
                }
            }
        return flag;
    }

    public void left() {
        if (isSaveNeeded) saveState(gameTiles);

        boolean isChanged = false;
        for(int j = 0; j < FIELD_WIDTH; j++){
            if(compressTiles(gameTiles[j]) | mergeTiles(gameTiles[j])) {
                isChanged = true;
            }
        }
        if(isChanged) addTile();

        isSaveNeeded = false;
    }

    public void rotate() {
        for (int k = 0; k < 2; k++) {
            for (int j = k; j < 3 - k; j++) {
                Tile tmp = gameTiles[k][j];
                gameTiles[k][j] = gameTiles[j][3 - k];
                gameTiles[j][3 - k] = gameTiles[3 - k][3 - j];
                gameTiles[3 - k][3 - j] = gameTiles[3 - j][k];
                gameTiles[3 - j][k] = tmp;
            }
        }
    }


    public void right() {
        if (isSaveNeeded) saveState(gameTiles);
        rotate();
        rotate();
        left();
        rotate();
        rotate();
        isSaveNeeded = false;
    }

    public void up() {
        if (isSaveNeeded) saveState(gameTiles);
        rotate();
        left();
        rotate();
        rotate();
        rotate();
        isSaveNeeded = false;
    }

    public void down() {
        if (isSaveNeeded) saveState(gameTiles);
        rotate();
        rotate();
        rotate();
        left();
        rotate();
        isSaveNeeded = false;
    }

    private void saveState(Tile[][] tile) {

        Tile[][] stack = new Tile[FIELD_WIDTH][FIELD_WIDTH];

        for(int i = 0; i < FIELD_WIDTH; i++) {
            for(int j = 0; j < FIELD_WIDTH; j++) {
                stack[i][j] = new Tile(tile[i][j].value);
            }
        }

        previousStates.push(stack);
        previousScores.push(score);

        isSaveNeeded = false;
    }

    public void rollback () {

        if(!previousStates.isEmpty()) gameTiles = (Tile[][])previousStates.pop();

        if(!previousScores.isEmpty())  score = (int)previousScores.pop();
    }

    public void randomMove() {
       int n = ((int) (Math.random() * 100)) % 4; // random int [0...3]

        switch (n){
            case 0: left(); break;
            case 1: right(); break;
            case 2: up(); break;
            case 3: down(); break;
        }
    }

    public void autoMove() {
        PriorityQueue<MoveEfficiency> priorityQueue = new PriorityQueue<>(4, Collections.reverseOrder());
        priorityQueue.offer(getMoveEfficiency(() -> left()));
        priorityQueue.offer(getMoveEfficiency(() -> right()));
        priorityQueue.offer(getMoveEfficiency(() -> up()));
        priorityQueue.offer(getMoveEfficiency(() -> down()));

        priorityQueue.peek().getMove().move();
    }

    boolean hasBoardChanged() {
        int weightPrevious = 0;
        int weightThis = 0;

        if(!previousStates.isEmpty()) {
            Tile[][] prevGameTiles = (Tile[][])previousStates.peek();
            for (int i = 0; i < FIELD_WIDTH; i++) {
                for (int j = 0; j < FIELD_WIDTH; j++) {
                    weightThis += gameTiles[i][j].value;
                    weightPrevious += prevGameTiles[i][j].value;
                }
            }
        }
        return weightPrevious != weightThis;
    }

  public MoveEfficiency getMoveEfficiency(Move move) {
        MoveEfficiency moveEfficiency;
        move.move();
        if (hasBoardChanged()) moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);
        else moveEfficiency = new MoveEfficiency(-1, 0, move);
        rollback();

        return moveEfficiency;
    }
}


