/*
 * Copyright © 2016 Azige <zzb12@qq.com>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package io.github.azige.brainfuck;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 *
 * @author Azige
 */
public class Interpreter{

    public static final char CHAR_NEXT = '>';
    public static final char CHAR_PREVIOUS = '<';
    public static final char CHAR_INC = '+';
    public static final char CHAR_DEC = '-';
    public static final char CHAR_OUTPUT = '.';
    public static final char CHAR_INPUT = ',';
    public static final char CHAR_BLOCK_START = '[';
    public static final char CHAR_BLOCK_END = ']';

    private char blockStart;
    private char blockEnd;

    private Map<Character, Supplier<Command>> charToCommandMap = new HashMap<>();

    public Interpreter(){
        this(CHAR_NEXT, CHAR_PREVIOUS, CHAR_INC, CHAR_DEC, CHAR_OUTPUT, CHAR_INPUT, CHAR_BLOCK_START, CHAR_BLOCK_END);
    }

    public Interpreter(
        char next,
        char previous,
        char inc,
        char dec,
        char output,
        char input,
        char blockStart,
        char blockEnd
    ){
        charToCommandMap.put(next, Commands::next);
        charToCommandMap.put(previous, Commands::previous);
        charToCommandMap.put(inc, Commands::inc);
        charToCommandMap.put(dec, Commands::dec);
        charToCommandMap.put(output, Commands::output);
        charToCommandMap.put(input, Commands::input);

        this.blockStart = blockStart;
        this.blockEnd = blockEnd;
    }

    public List<Command> interpret(String text){
        int count = 0;
        Deque<List<Command>> blockStack = new ArrayDeque<>();
        Deque<Integer> blockStartCountStack = new ArrayDeque<>();

        List<Command> currentList = new ArrayList<>();
        for (char c : text.toCharArray()){
            if (c == blockStart){
                blockStack.push(currentList);
                blockStartCountStack.push(count);
                currentList = new ArrayList<>();
                count++;
            }else if (c == blockEnd){
                if (blockStack.isEmpty()){
                    throw new BrainfuckException("孤立的']'，count=" + count);
                }
                blockStack.peek().add(Commands.jumpTo(count + 1, true));
                blockStack.peek().addAll(currentList);
                blockStack.peek().add(Commands.jumpTo(blockStartCountStack.pop() + 1, false));
                currentList = blockStack.pop();
                count++;
            }else{
                Supplier<Command> commandSupplier = charToCommandMap.get(c);
                if (commandSupplier != null){
                    currentList.add(commandSupplier.get());
                    count++;
                }
            }
        }

        if (!blockStack.isEmpty()){
            throw new BrainfuckException("孤立的'['，count=" + blockStartCountStack.peek());
        }

        return currentList;
    }
}
