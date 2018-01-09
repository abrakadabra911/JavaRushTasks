package com.javarush.task.task26.task2603;

import java.util.Comparator;
import java.util.List;

/*
Убежденному убеждать других не трудно
*/
public class Solution {
    public static class CustomizedComparator<T> implements Comparator<T> {
        private Comparator<T>[] comparators;

        public CustomizedComparator(Comparator<T>... comparators) {
            this.comparators = comparators;
        }

        public int compare(T o1, T o2) {
            for (Comparator<T> comparator : comparators) {
                int comparison = comparator.compare(o1, o2);
                if (comparison != 0) return comparison;
            }
            return 0;
        }

    }
    public static void main(String[] args) {

    }
}
