package com.javarush.task.task23.task2305;

/* 
Inner
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        Solution one = new Solution();
        InnerClass onecl = one.new InnerClass();
        InnerClass twocl = one.new InnerClass();
        one.innerClasses[0] = onecl;
        one.innerClasses[1] = twocl;
        Solution two = new Solution();
        InnerClass onecl1 = two.new InnerClass();
        InnerClass twocl1 = two.new InnerClass();
        two.innerClasses[0] = onecl1;
        two.innerClasses[1] = twocl1;

        Solution[] result = {one,two};
        return result;
    }

    public static void main(String[] args) {

    }
}
