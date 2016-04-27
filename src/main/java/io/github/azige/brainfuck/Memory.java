/*
 * Copyright © 2016 Azige <zzb12@qq.com>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See http://www.wtfpl.net/ for more details.
 */
package io.github.azige.brainfuck;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Azige
 */
public class Memory{

    private static class MemoryUnit{
        byte value = 0;
    }

    private Map<Long, MemoryUnit> map = new HashMap<>();

    public void addTo(long position, byte value){
        checkPosition(position);
        map.get(position).value += value;
    }

    public void set(long position, byte value){
        checkPosition(position);
        map.get(position).value = value;
    }

    public byte get(long position){
        checkPosition(position);
        return map.get(position).value;
    }

    private void checkPosition(long position){
        if (!map.containsKey(position)){
            map.put(position, new MemoryUnit());
        }
    }
}
