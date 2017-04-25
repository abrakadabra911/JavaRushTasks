package com.javarush.task.task34.task3412;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;


/* 
Добавление логирования в класс
*/

public class Solution {
    private static final Logger logger = LoggerFactory.getLogger(Solution.class);
    private int value1;
    private String value2;
    private Date value3;

    public Solution(int value1, String value2, Date value3) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        logger.debug("Solution.start", value1);
    }

    public void SayHello(String say){
        logger.info("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    }

    public static void main(String[] args)throws IOException {
       new Solution(12,"fdf",new Date()).SayHello("fsd");
    }

    public void calculateAndSetValue3(long value) {
        value -= 133;
        logger.debug("debug-1 id=", value);
        if (value > Integer.MAX_VALUE) {
            logger.trace("processTask id=", value);
            value1 = (int) (value / Integer.MAX_VALUE);
            logger.debug("value > Integer.MAX_VALUE", value1);
        } else {
            value1 = (int) value;
            logger.debug("value !> Integer.MAX_VALUE", value1);
        }
    }

    public void printString() {
        logger.debug("value2 != null", value2);
        if (value2 != null) {
            logger.trace("processTask id=", value2);
            System.out.println(value2.length());
        }

    }

    public void printDateAsLong() {
        logger.debug("value2 != null", value2);
        if (value3 != null) {
            logger.trace("processTask id=", value3);
            System.out.println(value3.getTime());
        }

    }

    public void divide(int number1, int number2) {
        logger.debug("value2 != null", value2);
        try {
            logger.trace("processTask id=", number2);
            System.out.println(number1 / number2);
        } catch (ArithmeticException e) {
    //        logger.error("ArithmeticException error", e);
        }

    }

    public void setValue1(int value1) {
        this.value1 = value1;

    }

    public void setValue2(String value2) {
        this.value2 = value2;

    }

    public void setValue3(Date value3) {
        this.value3 = value3;

    }
}
