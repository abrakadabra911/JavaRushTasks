package com.javarush.task.task34.task3410.model;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LevelLoader {
    private Path levels;

    public LevelLoader(Path levels){
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        Set walls = new HashSet();
        Set boxes = new HashSet();
        Set homes = new HashSet();
        Player player = new Player(200, 200);

        List<String> allLines = new ArrayList<>();

        try {

            allLines = Files.readAllLines(levels, StandardCharsets.UTF_8);

            int maxLevel = 0;
            for (Integer x : listOfLevelNumbers(allLines)) {  // maximum number of level
                if (x > maxLevel) maxLevel = x;
            }

            int searchingLevel = level <= maxLevel ? level : level % maxLevel;  // correction of levelNumber in case of level absent

            for (int i = 0; i < allLines.size(); i++) {
                if (allLines.get(i).equals("Maze: " + searchingLevel)) {
                    int j = i + 7;
                    int x0 = Model.FIELD_CELL_SIZE*3 / 2;
                    int y0 = Model.FIELD_CELL_SIZE*3 / 2;
                    int y = y0;
                    int x = x0;
                    while (!allLines.get(j).equals("*************************************")) {
                        char[] chars = allLines.get(j).toCharArray();
                        for (char character : chars) {
                            switch (character) {  // Символ ‘X’ – означает стену, ‘*’ - ящик, ‘.’ – дом, ‘&’ – ящик который стоит в доме, а ‘@’ - игрока.
                                case 'X':
                                    walls.add(new Wall(x, y));
                                    break;
                                case '*':
                                    boxes.add(new Box(x, y));
                                    break;
                                case '.':
                                    homes.add(new Home(x, y));
                                    break;
                                case '&':
                                    boxes.add(new Box(x, y));
                                    homes.add(new Home(x, y));
                                    break;
                                case '@':
                                    player = new Player(x, y);
                                    break;
                                default:
                                    break;
                            }
                            x = x + Model.FIELD_CELL_SIZE;
                        }
                        j++;
                        y = y + Model.FIELD_CELL_SIZE;
                        x = x0;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        GameObjects allGameObjects = new GameObjects(walls, boxes, homes, player);
        return allGameObjects;
    }

    private List<Integer> listOfLevelNumbers(List<String> listOfLines) {
        List<Integer> list = new ArrayList<>();
        try {
            for (String currentLine : listOfLines) {
                if (currentLine.contains("Maze: ")) list.add(Integer.parseInt(currentLine.substring(6)));
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong level number in levels.txt found!");
        }
        if (list.isEmpty()) {
            System.out.println("Please fill levels.txt file!");
            System.exit(0);
        }
        return list;
    }
}
