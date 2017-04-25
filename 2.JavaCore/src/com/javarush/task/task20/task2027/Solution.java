package com.javarush.task.task20.task2027;

import java.util.ArrayList;

import java.util.List;

/* 
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        List<Word> list = detectAllWords(crossword, "home", "same", "emoh", "emas", "fderlk", "klredf",
                "fulmp", "poeejj", "jjeeop", "pmluf", "kovhj", "jhvok", "lprr", "rrpl", "lprr", "voel",
                "lock", "r", "re", "eo", "oe", null, "", " ");
        System.out.println("List size: " + list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }
    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        int height = crossword.length;
        int width = crossword[0].length;
        int countTrueWords = 0;
        // Переносим в новый массив слов те из них, которые не null и не пустые.
        for (int i = 0; i < words.length; i++) {
            if (words[i] != null && words[i] != "") {
                countTrueWords++;
            }
        }
        String[] words1 = new String[countTrueWords]; // Это новый массив уменьшенный на
        // количество пустых и null строк
        countTrueWords = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i] != null && words[i] != "") {
                words1[countTrueWords] = words[i];
                countTrueWords++;
            }
        }
        // Список для результирующих элементов
        List<Word> result = new ArrayList<>();
        for (int i = 0; i < words1.length; i++) { // Начинаем перебирать слова из массива
            char[] word = words1[i].toCharArray(); // Разбиваем очередное слово на массив символов
            int sizeWord = word.length;
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (word[0] == crossword[y][x]) { // Ищем первую букву слова в матрице
                        if (sizeWord > 1) {  // Если слово больше чем из одной буквы, то идем по true
                            boolean left = false, right = false, up = false, down = false;
                            int count, trueCount, currentX, currentY;
                            // Выясняю возможные направления разгадывания в кроссворде исходя из длины слова
                            if (x + 1 - sizeWord >= 0) {left = true;}
                            if (x + sizeWord <= width) {right = true;}
                            if (y + 1 - sizeWord >= 0) {up = true;}
                            if (y + sizeWord <= height) {down = true;}
                            // Прощупываю доступные направления
                            if (left) { // Можно налево
                                count = 1;
                                trueCount = 1;
                                currentX = x - 1;
                                currentY = y;
                                // Здесь проверяю дальнейшее совпадение всех букв из слова
                                while (count < sizeWord) {
                                    if (word[count] == crossword[currentY][currentX]) {
                                        trueCount++;
                                        currentX--;
                                    } else break;
                                    count++;
                                }
                                currentX++;
                                // Добавляю слово в результирующий список в случае полного совпадения
                                if (trueCount == sizeWord) {
                                    Word tempWord = new Word(words1[i]);
                                    tempWord.setStartPoint(x, y);
                                    tempWord.setEndPoint(currentX, currentY);
                                    result.add(tempWord);
                                }
                            }
                            if (left && up) { // Влево и вверх
                                count = 1;
                                trueCount = 1;
                                currentX = x - 1;
                                currentY = y - 1;
                                // Здесь проверяю дальнейшее совпадение всех букв из слова
                                while (count < sizeWord) {
                                    if (word[count] == crossword[currentY][currentX]) {
                                        trueCount++;
                                        currentX--;
                                        currentY--;
                                    } else break;
                                    count++;
                                }
                                currentX++;
                                currentY++;
                                // Добавляю слово в результирующий список в случае полного совпадения
                                if (trueCount == sizeWord) {
                                    Word tempWord = new Word(words1[i]);
                                    tempWord.setStartPoint(x, y);
                                    tempWord.setEndPoint(currentX, currentY);
                                    result.add(tempWord);
                                }
                            }
                            if (up) { // Вверх
                                count = 1;
                                trueCount = 1;
                                currentX = x;
                                currentY = y - 1;
                                // Здесь проверяю дальнейшее совпадение всех букв из слова
                                while (count < sizeWord) {
                                    if (word[count] == crossword[currentY][currentX]) {
                                        trueCount++;
                                        currentY--;
                                    } else break;
                                    count++;
                                }
                                currentY++;
                                // Добавляю слово в результирующий список в случае полного совпадения
                                if (trueCount == sizeWord) {
                                    Word tempWord = new Word(words1[i]);
                                    tempWord.setStartPoint(x, y);
                                    tempWord.setEndPoint(currentX, currentY);
                                    result.add(tempWord);
                                }
                            }
                            if (right && up) { // Вправо и вверх
                                count = 1;
                                trueCount = 1;
                                currentX = x + 1;
                                currentY = y - 1;
                                // Здесь проверяю дальнейшее совпадение всех букв из слова
                                while (count < sizeWord) {
                                    if (word[count] == crossword[currentY][currentX]) {
                                        trueCount++;
                                        currentX++;
                                        currentY--;
                                    } else break;
                                    count++;
                                }
                                currentX--;
                                currentY++;
                                // Добавляю слово в результирующий список в случае полного совпадения
                                if (trueCount == sizeWord) {
                                    Word tempWord = new Word(words1[i]);
                                    tempWord.setStartPoint(x, y);
                                    tempWord.setEndPoint(currentX, currentY);
                                    result.add(tempWord);
                                }
                            }
                            if (right) { // Вправо
                                count = 1;
                                trueCount = 1;
                                currentX = x + 1;
                                currentY = y;
                                // Здесь проверяю дальнейшее совпадение всех букв из слова
                                while (count < sizeWord) {
                                    if (word[count] == crossword[currentY][currentX]) {
                                        trueCount++;
                                        currentX++;
                                    } else break;
                                    count++;
                                }
                                currentX--;
                                // Добавляю слово в результирующий список в случае полного совпадения
                                if (trueCount == sizeWord) {
                                    Word tempWord = new Word(words1[i]);
                                    tempWord.setStartPoint(x, y);
                                    tempWord.setEndPoint(currentX, currentY);
                                    result.add(tempWord);
                                }
                            }
                            if (right && down) { // Вправо и вниз
                                count = 1;
                                trueCount = 1;
                                currentX = x + 1;
                                currentY = y + 1;
                                // Здесь проверяю дальнейшее совпадение всех букв из слова
                                while (count < sizeWord) {
                                    if (word[count] == crossword[currentY][currentX]) {
                                        trueCount++;
                                        currentX++;
                                        currentY++;
                                    } else break;
                                    count++;
                                }
                                currentX--;
                                currentY--;
                                // Добавляю слово в результирующий список в случае полного совпадения
                                if (trueCount == sizeWord) {
                                    Word tempWord = new Word(words1[i]);
                                    tempWord.setStartPoint(x, y);
                                    tempWord.setEndPoint(currentX, currentY);
                                    result.add(tempWord);
                                }
                            }
                            if (down) { // Вниз
                                count = 1;
                                trueCount = 1;
                                currentX = x;
                                currentY = y + 1;
                                // Здесь проверяю дальнейшее совпадение всех букв из слова
                                while (count < sizeWord) {
                                    if (word[count] == crossword[currentY][currentX]) {
                                        trueCount++;
                                        currentY++;
                                    } else break;
                                    count++;
                                }
                                currentY--;
                                // Добавляю слово в результирующий список в случае полного совпадения
                                if (trueCount == sizeWord) {
                                    Word tempWord = new Word(words1[i]);
                                    tempWord.setStartPoint(x, y);
                                    tempWord.setEndPoint(currentX, currentY);
                                    result.add(tempWord);
                                }
                            }
                            if (left && down) { // Влево и вниз
                                count = 1;
                                trueCount = 1;
                                currentX = x - 1;
                                currentY = y + 1;
                                // Здесь проверяю дальнейшее совпадение всех букв из слова
                                while (count < sizeWord) {
                                    if (word[count] == crossword[currentY][currentX]) {
                                        trueCount++;
                                        currentX--;
                                        currentY++;
                                    } else break;
                                    count++;
                                }
                                currentX++;
                                currentY--;
                                // Добавляю слово в результирующий список в случае полного совпадения
                                if (trueCount == sizeWord) {
                                    Word tempWord = new Word(words1[i]);
                                    tempWord.setStartPoint(x, y);
                                    tempWord.setEndPoint(currentX, currentY);
                                    result.add(tempWord);
                                }
                            }
                        }
                        else { // Здесь добавляю слова из одного символа, найденные в кроссворде
                            Word tempWord = new Word(words1[i]);
                            tempWord.setStartPoint(x, y);
                            tempWord.setEndPoint(x, y);
                            result.add(tempWord);
                        }
                    }
                }
            }
        }
        return result;
    }
    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;
        public Word(String text) {
            this.text = text;
        }
        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }
        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }
        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}