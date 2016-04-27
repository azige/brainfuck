/*
 * Copyright © 2016 Azige <zzb12@qq.com>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package io.github.azige.brainfuck;

/**
 *
 * @author Azige
 */
public class BrainfuckException extends RuntimeException{

    public BrainfuckException(){
    }

    public BrainfuckException(String message){
        super(message);
    }

    public BrainfuckException(String message, Throwable cause){
        super(message, cause);
    }

    public BrainfuckException(Throwable cause){
        super(cause);
    }

}
