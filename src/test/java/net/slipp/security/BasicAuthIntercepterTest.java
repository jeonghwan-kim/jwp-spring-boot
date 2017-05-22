package net.slipp.security;

import net.slipp.security.BasicAuthInterceptor;
import net.slipp.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Base64;

/**
 * Created by woowahan on 2017. 5. 22..
 */
@RunWith(MockitoJUnitRunner.class)
public class BasicAuthIntercepterTest {
//    @Mock
//    private UserService userService;

//    private BasicAuthInterceptor basicAuthInterceptor;

    @Test
    public void testFoo() throws Exception {
        String encodedBasicAuth = Base64.getEncoder().encodeToString("chris:chrispass".getBytes());

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Basic " + encodedBasicAuth);
    }
}
