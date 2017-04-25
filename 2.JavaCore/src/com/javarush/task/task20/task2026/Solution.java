package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };

        int count = getRectangleCount(a);
        System.out.println("count = " + count + ". Должно быть 2");
    }

    public static int getRectangleCount(byte[][] a) {
        int[][] b = new int [a[0].length][a.length];
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < a[0].length; j++) {
                b[i][j] = a[i][j];
            }
        }
        for( int j = 0; j < b.length; j++) {
            for (int i = 0; i < b[0].length; i++) {
                System.out.print(b[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");


        int count = 1;
        for( int j = 0; j < b.length; j++) {
            for (int i = 0; i < b[0].length; i++) {

                if (b[i][j] ==1) {
                    count++;
                    int x = i;
                    int y = j;
                    while(b[i][y] == 1){
                    while (b[x][y] == 1) {
                        b[x][y] = count;
                        if(x == b[0].length-1) break;
                        x++;
                    }
                        if(y == b.length-1) break;
                        y++;
                        x=i;
                }
                }
            }
        }

        for( int j = 0; j < b.length; j++) {
            for (int i = 0; i < b[0].length; i++) {
                System.out.print(b[i][j] + " ");
            }
            System.out.print("\n");
        }


        return count-1;
    }
}
