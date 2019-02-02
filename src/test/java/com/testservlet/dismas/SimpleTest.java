package com.testservlet.dismas;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Dmitry on 28.01.2019.
 */
public class SimpleTest {

    @Test
    public void test() {

        int x = 2;
        int y = 23;

        Assert.assertEquals(46, x * y);
        Assert.assertEquals(25, x + y);
        Assert.assertEquals(21, y - x);
    }

    @Test(expected = ArithmeticException.class)
    public void error(){
        int i = 0;
        int i1 = 1/i;
    }
}
