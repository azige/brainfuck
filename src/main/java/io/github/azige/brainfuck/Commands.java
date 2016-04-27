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
public class Commands{

    public static Command next(){
        return vm -> vm.nextPosition();
    }

    public static Command previous(){
        return vm -> vm.previousPosition();
    }

    public static Command inc(){
        return vm -> vm.inc();
    }

    public static Command dec(){
        return vm -> vm.dec();
    }

    public static Command input(){
        return vm -> vm.input();
    }

    public static Command output(){
        return vm -> vm.output();
    }

    public static Command jumpTo(int position, boolean ifZero){
        return vm -> vm.jumpTo(position, ifZero);
    }
}
