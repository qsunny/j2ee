package com.aaron.springbootmybatisOrthodox.mapper;

import com.aaron.springbootmybatisOrthodox.bean.User;
import com.aaron.springbootmybatisOrthodox.dao.UserDao;
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
    private UserDao userDao;

    @Test
    public void testInsert() {
        userDao.insert(new User("1111dde3e", "吕布", "888888",23));
        userDao.insert(new User("22222de3d", "曹植", "888888",25));
        userDao.insert(new User("33333dfe3", "曹丕", "888888",23));

        Assert.assertEquals(17, userDao.getAll().size());
    }

    @Test
    public void testQuery() {
        List<User> list = userDao.getAll();
        System.out.println(list.toString());
    }

    @Test
    public void testUpdate() {
        userDao.update(new User("402881e458c2e9230158c2e923670000","诸葛亮","8888888",27));
        Assert.assertTrue(("诸葛亮".equals(userDao.getOne("402881e458c2e9230158c2e923670000").getUsername())));
    }

    @Test
    public void testDel() {
        int result = userDao.delete("22123fjdkfjdkfjse");
        System.out.println("result======" + result);
        Assert.assertNull(userDao.getOne("22123fjdkfjdkfjse"));
    }



}
