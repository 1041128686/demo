package com.test;

import com.my.DemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class redis {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void Redis(){
        stringRedisTemplate.boundValueOps("String").set("String");
        stringRedisTemplate.boundHashOps("hash").put("hash","hash");
        stringRedisTemplate.boundListOps("List").leftPush("list");
        stringRedisTemplate.boundSetOps("Set").add("Set","set");
        stringRedisTemplate.boundZSetOps("ZSet").add("Zset1",1);
    }
}
