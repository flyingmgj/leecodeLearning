package com.magee.meet;

import java.io.FileWriter;
import java.io.IOException;

import static java.lang.Math.*;

public class TestIO {
    public static void main(String[] args) {

        try {
            FileWriter fileWriter = new FileWriter("xxx");
            fileWriter.write("hello world");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        System.out.println(Math.random() *10);
    }
}
