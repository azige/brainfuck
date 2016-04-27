/*
 * Copyright © 2016 Azige <zzb12@qq.com>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package io.github.azige.brainfuck;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Azige
 */
public class IODevice{

    private InputStream in;
    private OutputStream out;

    public IODevice(){
        in = System.in;
        out = System.out;
    }

    public IODevice(InputStream in, OutputStream out){
        this.in = in;
        this.out = out;
    }

    public byte read(){
        try{
            int c = in.read();
            if (c == -1){
                c = 0;
            }
            return (byte)c;
        }catch (IOException ex){
            throw new BrainfuckException(ex);
        }
    }

    public void write(byte c){
        try{
            out.write(c);
            out.flush();
        }catch (IOException ex){
            throw new BrainfuckException(ex);
        }
    }
}
