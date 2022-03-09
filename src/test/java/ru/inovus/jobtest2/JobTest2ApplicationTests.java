package ru.inovus.jobtest2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class JobTest2ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnFirstCarNumber() throws Exception {

        this.mockMvc.perform(get("/number/next"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("A000AA 116 RUS")));
    }

    @Test
    public void shouldReturnSecondCarNumber() throws Exception {

        this.mockMvc.perform(get("/number/next"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("A001AA 116 RUS")));
    }

    @Test
    public void shouldCheckHeader() throws Exception {
        this.mockMvc.perform(get("/number/random"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "text/plain;charset=UTF-8"));
    }

    @Test
    public void shouldReturnRandomCarNumber() throws Exception {
        this.mockMvc.perform(get("/number/random"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }
}