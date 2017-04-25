package com.javarush.task.task34.task3411;

/* 
Ханойские башни
*/

import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        int count = 4;
        moveRing('A', 'B', 'C', count);
    }

    public static void moveRing(char a, char b, char c, int count) {
        ArrayList<Integer> aa = new ArrayList<>();
        ArrayList<Integer> bb = new ArrayList<>();
        ArrayList<Integer> cc = new ArrayList<>();
        for(int i = count; i > 0; i--) aa.add(i);
        if (count%2==1) {
            while (true) {
                // 1-2, 1-3, 2-3
                move(aa, bb, a, b);
                if (aa.size() == 0 && cc.size() == 0) break;
                move(aa, cc, a, c);
                if (aa.size() == 0 && cc.size() == 0) break;
                move(bb, cc, b, c);
                if (aa.size() == 0 && cc.size() == 0) break;
                //   if (aa.size()!=0 && cc.size()!=0 ) moveRing(a, b, c, count-1);
            }
        }
        if (count%2==0) {
            while (true) {
                //1-3, 1-2, 2-3
                move(aa, cc, a, c);
                if (aa.size() == 0 && cc.size() == 0) break;
                move(aa, bb, a, b);
                if (aa.size() == 0 && cc.size() == 0) break;
                move(bb, cc, b, c);
                if (aa.size() == 0 && cc.size() == 0) break;
                //   if (aa.size()!=0 && cc.size()!=0 ) moveRing(a, b, c, count-1);
            }
        }
    }
    static private void move(ArrayList<Integer> aa, ArrayList<Integer> bb, char a, char b){
        if (aa.size()>0&&(bb.size()==0||aa.get(aa.size()-1)<bb.get(bb.size()-1))) {
            bb.add(aa.get(aa.size() - 1));
           if(aa.size()>0) aa.remove(aa.size() - 1);
            System.out.print("from " + a + " to " + b + '\n');
            return;
        }
        if (bb.size()>0&&(aa.size()==0||bb.get(bb.size()-1)<aa.get(aa.size()-1))) {
            aa.add(bb.get(bb.size() - 1));
            if(bb.size()>0)  bb.remove(bb.size() - 1);
            System.out.print("from " + b + " to " + a + '\n');
            return;
        }
    }
}