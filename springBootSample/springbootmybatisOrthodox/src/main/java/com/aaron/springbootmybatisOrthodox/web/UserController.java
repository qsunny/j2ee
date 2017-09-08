package com.aaron.springbootmybatisOrthodox.web;

import com.aaron.springbootmybatisOrthodox.bean.User;
import com.aaron.springbootmybatisOrthodox.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Aaron.qiu on 2017/9/3.
 */
@RestController
public class UserController {
    @Autowired
    private UserDao userDao;

    @RequestMapping("/getUsers")
    public List<User> getUsers() {
        List<User> users=userDao.getAll();
        return users;
    }

    @RequestMapping("/getUser")
    public User getUser(String id) {
        User user=userDao.getOne(id);
        return user;
    }

    @RequestMapping("/add")
    public void save(User user) {
        userDao.insert(user);
    }

    @RequestMapping(value="update")
    public User update(User user) {
        userDao.update(user);
        User u=userDao.getOne(user.getId());
        return u;
    }

    @RequestMapping(value="/delete/{id}")
    public void delete(@PathVariable("id") String id) {
        userDao.delete(id);
    }
}
