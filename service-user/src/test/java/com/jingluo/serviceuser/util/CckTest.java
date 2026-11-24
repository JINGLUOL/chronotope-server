package com.jingluo.serviceuser.util;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;

public class CckTest {


    @Test
    void e() {
        Clock clock = Clock.systemUTC();
        String[] keys = new String[]{"JING_LUO", "12345", "你好"};
        String e_str = Cck.encryption("11235513431", keys, clock);
        System.out.println(e_str);
        System.out.println(Cck.decryption(e_str, keys));
        System.out.println(Cck.verify(e_str, keys, clock, 100L));
    }

    @Test
    void a() {
        System.out.println(Instant.now(Clock.systemUTC()));
    }

}
