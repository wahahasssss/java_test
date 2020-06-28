package com.hdu;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/4/19
 * @Time 4:05 PM
 */
@MicronautTest
public class HelloControllerTest {
    @Inject
    @Client("/")
    RxHttpClient client;
    @Test
    public void testHello(){
        HttpRequest request = HttpRequest.GET("/hello");
        String body = client.toBlocking().retrieve(request);

        assertNotNull(body);
        assertEquals(body,"Hello World");
    }
}
