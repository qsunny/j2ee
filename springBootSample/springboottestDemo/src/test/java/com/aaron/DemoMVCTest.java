package com.aaron;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class DemoMVCTest {


    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        // 在每个测试方法执行前进行一些初始化操作
    }

    @Test
    public void testGetMethod() throws Exception {

        /**
         * get请求
         */
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/test/start") // 发起GET请求，访问路径为"/example"
                .accept(MediaType.APPLICATION_JSON)// 接受JSON格式的响应
                .param("id", "123") //参数
                .param("first_flag", String.valueOf(true)); //参数


        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())  // 验证请求的HTTP状态码为200
                //.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("success"))  // 验证响应中的JSON字段"message"的值为"success"
                .andReturn();// 返回MockMvcResult对象

        MockHttpServletResponse response = result.getResponse(); //得到返回值
        //response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE); //设置响应 Content-Type
        response.setContentType(MediaType.TEXT_PLAIN_VALUE); //设置响应 Content-Type
        System.out.println(response.getContentAsString()); // 打印

    }

    public void testPostMethod() {
        MockHttpServletRequestBuilder postRequest = MockMvcRequestBuilders.post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"john\", \"password\": \"123456\"}");
    }

    public void testPutMethod() {
        MockHttpServletRequestBuilder putRequest = MockMvcRequestBuilders.put("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"john\", \"password\": \"654321\"}");
    }

    public void testDeleteMethod() {
        MockHttpServletRequestBuilder deleteRequest = MockMvcRequestBuilders.delete("/api/users/1");
    }

}
