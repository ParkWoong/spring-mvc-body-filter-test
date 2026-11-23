package io.github.parkwoong.example;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class BodyFilterTest {
    @Autowired MockMvc mvc;

    @Test
    void requestBodyReachesControllerAndResponseReachesClient() throws Exception {
        mvc.perform(post("/echo").contentType("text/plain").content("sample body"))
                .andExpect(status().isOk())
                .andExpect(content().string("sample body"));
    }
}
