package com.testservlet.dismas;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Dmitry on 28.01.2019.
 */
public class DumbPasswordEncoderTest {
    @Test
    public void encode() throws Exception {
        DumbPasswordEncoder encoder = new DumbPasswordEncoder();

        Assert.assertEquals("secret: 'mypwd'",encoder.encode("mypwd"));
        Assert.assertThat(encoder.encode("mypwd"), Matchers.containsString("mypwd"));
    }

}