package com.aaron.springbootmybatis;

import com.aaron.springbootmybatis.bean.User;
import com.aaron.springbootmybatis.dao.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        userMapper.insert(new User("1111333e", "曹操", "888888",32));
        userMapper.insert(new User("2222232d", "司马懿", "888888",25));
        userMapper.insert(new User("3333322", "张颌", "888888",16));

        Assert.assertEquals(17, userMapper.getAll().size());
    }

    @Test
    public void testQuery() {
        List<User> list = userMapper.getAll();
        System.out.println(list.toString());
    }

    @Test
    public void testUpdate() {
        userMapper.update(new User("402881e458c2e9230158c2e923670000","诸葛亮","3333333",24));
        Assert.assertTrue(("诸葛亮".equals(userMapper.getOne("402881e458c2e9230158c2e923670000").getUsername())));
    }

    @Test
    public void testDel() {
        int result = userMapper.delete("402881e44cb2841d014cb2841d490000");
        System.out.println("result======" + result);
        Assert.assertNull(userMapper.getOne("402881e44cb2841d014cb2841d490000"));
    }

}
