package com.aaron.springbootApp;

import com.aaron.springbootApp.core.service.HelloSerivce;
import com.aaron.springbootApp.web.controller.HelloController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.given;
/**
 * Created by aaron.qiu on 2016/11/25.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {HelloController.class})
public class SpringAppTest {

    private MockMvc mvc;

    @MockBean
    private HelloSerivce helloSerivce;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
    }

    @Test
    public void getHello() throws Exception {
        /*
        mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello World")));
    */
        given(this.helloSerivce.sayHello("aaron"))
                .willReturn("Hello,Aaron!");

        //this.mvc.perform(get("/").accept(MediaType.TEXT_PLAIN))
               // .andExpect(status().isOk()).andExpect(content().string(equalTo("Hello,Aaron!")));
    }

}
