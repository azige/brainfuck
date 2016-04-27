/*
 * Copyright © 2016 Azige <zzb12@qq.com>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package io.github.azige.brainfuck;

import java.util.List;

/**
 *
 * @author Azige
 */
public class BrainfuckVM{

    private long memoryPosition = 0;
    private Memory memory = new Memory();
    private IODevice io = new IODevice();

    private int commandPosition = 0;

    public Memory getMemory(){
        return memory;
    }

    public void setMemory(Memory memory){
        this.memory = memory;
    }

    public IODevice getIo(){
        return io;
    }

    public void setIo(IODevice io){
        this.io = io;
    }

    public void nextPosition(){
        memoryPosition++;
    }

    public void previousPosition(){
        memoryPosition--;
    }

    public void inc(){
        memory.addTo(memoryPosition, (byte)1);
    }

    public void dec(){
        memory.addTo(memoryPosition, (byte)-1);
    }

    public void input(){
        memory.set(memoryPosition, io.read());
    }

    public void output(){
        io.write(memory.get(memoryPosition));
    }

    public void jumpTo(int commandPosition, boolean ifZero){
        if (memory.get(memoryPosition) != 0 ^ ifZero){
            this.commandPosition = commandPosition - 1;
        }
    }

    public void run(List<Command> commandList){
        commandPosition = 0;

        while (commandPosition < commandList.size()){
            commandList.get(commandPosition).excute(this);
            commandPosition++;
        }
    }
}
