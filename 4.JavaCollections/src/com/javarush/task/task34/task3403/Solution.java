package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/
public class Solution {
    public void recursion(int n) {
       int starter = 2;
        while(starter<=n) {
            if (n % starter == 0) {
                if(((double)n/(double)starter==1.)){System.out.print(starter + " ");break;}
                else{
                System.out.print(starter + " ");
                recursion(n / starter);
                    break;}
            }
            starter++;
        }
    }
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recursion(40);
    }
}
