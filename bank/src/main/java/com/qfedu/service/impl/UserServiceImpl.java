package com.qfedu.service.impl;

import com.qfedu.dao.UserDao;
import com.qfedu.entity.User;
import com.qfedu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User login(String bankCode, String password) {

        User u = userDao.findByCode(bankCode);
        if(u == null){
            throw new RuntimeException("卡号错误");
        }
        if(!u.getPassword().equals(password)){
            throw new RuntimeException("密码错误");
        }
        return u;
    }

    @Override
    public Double findBalance(String bankCode) {

        User user = userDao.findByCode(bankCode);
        if(user == null){
            throw new RuntimeException("账号不存在");
        }

        return user.getBalance();
    }
}









