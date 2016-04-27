/*
 * Copyright © 2016 Azige <zzb12@qq.com>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package io.github.azige.brainfuck;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 *
 * @author Azige
 */
public class BrainfuckTest{

    private static Interpreter interpreter = new Interpreter();

    public BrainfuckTest(){
    }

    @BeforeClass
    public static void setUpClass(){
    }

    @AfterClass
    public static void tearDownClass(){
    }

    @Before
    public void setUp(){
    }

    @After
    public void tearDown(){
    }

    @Test
    public void testHelloWorld(){
        runAndCheck("++++++++++[>+++++++>++++++++++>+++>+<<<<-]"
            + ">++.>+.+++++++..+++.>++.<<+++++++++++++++."
            + ">.+++.------.--------.>+.>.",
            "",
            "Hello World!\n");
    }

    @Test
    public void testToUpperCase(){
        runAndCheck(",----------[----------------------.,----------]",
            "qwertyuiop\n",
            "QWERTYUIOP");
    }

    @Test
    public void testPlus(){
        runAndCheck(",>++++++[<-------->-],,[<+>-],<.>.",
            "4+3\n",
            "7\n");
    }

    @Test
    public void testMultiply(){
        runAndCheck(",>,,>++++++++[<------<------>>-]"
            + "<<[>[>+>+<<-]>>[<<+>>-]<<<-]"
            + ">>>++++++[<++++++++>-],<.>.",
            "3*3\n",
            "9\n");
    }

    @Test
    public void test0to9(){
        runAndCheck("++++++[>++++++++<-]++++++++++[>.+<-]",
            "",
            "0123456789");
    }

    @Test
    public void testQuine(){
        /**
         * A quine by Brian Raiter
         */
        String code = "->+>+++>>+>++>+>+++>>+>++>>>+>+>+>++>+>>>>+++>+>>++>+>++"
            + "+>>++>++>>+>>+>++>++>+>>>>+++>+>>>>++>++>>>>+>>++>+>+++>>>++>>++"
            + "++++>>+>>++>+>>>>+++>>+++++>>+>+++>>>++>>++>>+>>++>+>+++>>>++>>+"
            + "++++++++++++>>+>>++>+>+++>+>+++>>>++>>++++>>+>>++>+>>>>+++>>++++"
            + "+>>>>++>>>>+>+>++>>+++>+>>>>+++>+>>>>+++>+>>>>+++>>++>++>+>+++>+"
            + ">++>++>>>>>>++>+>+++>>>>>+++>>>++>+>+++>+>+>++>>>>>>++>>>+>>>++>"
            + "+>>>>+++>+>>>+>>++>+>++++++++++++++++++>>>>+>+>>>+>>++>+>+++>>>+"
            + "+>>++++++++>>+>>++>+>>>>+++>>++++++>>>+>++>>+++>+>+>++>+>+++>>>>"
            + ">+++>>>+>+>>++>+>+++>>>++>>++++++++>>+>>++>+>>>>+++>>++++>>+>+++"
            + ">>>>>>++>+>+++>>+>++>>>>+>+>++>+>>>>+++>>+++>>>+[[->>+<<]<+]++++"
            + "+[->+++++++++<]>.[+]>>[<<+++++++[->+++++++++<]>-.---------------"
            + "---->-[-<.<+>>]<[+]<+>>>]<<<[-[-[-[>>+<++++++[->+++++<]]>+++++++"
            + "+++++++<]>+++<]++++++[->+++++++<]>+<<<-[->>>++<<<]>[->>.<<]<<]";
        runAndCheck(code, "", code);
    }

    private void runAndCheck(String code, String input, String output){
        BrainfuckVM vm = new BrainfuckVM();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        vm.setIo(new IODevice(new ByteArrayInputStream(input.getBytes()), outputStream));
        vm.run(interpreter.interpret(code));
        assertEquals(output, outputStream.toString());
    }
}
