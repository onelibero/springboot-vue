package com.comic.springbootproject;

import com.comic.springbootproject.util.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class SpringbootprojectApplicationTests {

    @Test
    void contextLoads() {
        SimpleDateFormat sdfDatetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long time = Long.valueOf("1670253343538");
        System.out.println(time);
        String format = sdfDatetime.format(time);
        System.out.println(format);
        System.out.println(sdfDatetime.format(time));

    }
}
