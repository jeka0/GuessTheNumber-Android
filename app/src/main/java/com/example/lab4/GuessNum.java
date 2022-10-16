package com.example.lab4;

import java.util.Random;

public class GuessNum {
    static private int min =10,max=99;
    static public int rnd_comp_num()
    {
        int dif=max-min;
        Random random = new Random();
        int a =random.nextInt(dif+1)+min;
        return a;
    }

    public static void setMax(int max) {
        GuessNum.max = max;
    }

    public static void setMin(int min) {
        GuessNum.min = min;
    }
}
