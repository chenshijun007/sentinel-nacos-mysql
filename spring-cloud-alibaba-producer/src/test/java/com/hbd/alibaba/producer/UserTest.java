package com.hbd.alibaba.producer;

import static org.junit.Assert.*;

import com.hbd.alibaba.producer.controller.HelloCotroller;
import com.hbd.alibaba.producer.controller.HelloService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Autowired
    HelloService helloService;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * 测试断言
     */
    @Test
    public void testAssert() {
        String obj1 = "junit";
        String obj2 = "junit";
        String obj3 = "test";
        String obj4 = "test";
        String obj5 = null;
        int var1 = 1;
        int var2 = 2;
        int[] arithmetic1 = {1, 2, 3};
        int[] arithmetic2 = {1, 2, 3};

        assertEquals(obj1, obj2);

        assertSame(obj3, obj4);

        assertNotSame(obj2, obj4);

        assertNotNull(obj1);

        assertNull(obj5);

        assertTrue("为真", var1 != var2);

        assertArrayEquals(arithmetic1, arithmetic2);
    }

    @Test
    public void say() {
      String s =helloService.helloNacos("csj");
        System.out.println(s);
    }

}
