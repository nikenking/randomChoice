package com.dell.springboot.service;

import com.dell.springboot.entities.User;
import com.dell.springboot.mapper.LoginMapper;
import com.dell.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    UserMapper um;
    public List<User> getAll(int all) {
        List<User> list = um.getAll();
        int count = 0;
        for (User user : list) {
            count += user.getWeight();
        }
        if (count==0){
            refresh();
            um.changeTeacher(new User("郑创",1));
            list = um.getAll();
            for (User user : list) {
                count += user.getWeight();
            }
        }
        System.out.println("总权重为"+count);
        int choice = (int) (Math.random() * count) + 1;
        System.out.println("选择是"+choice);
        for (User user : list) {
            choice = choice - user.getWeight();
            if (choice<=0){
                System.out.println("添加一个用户到末尾:"+user.getName()+"当前访问时间:\n");
                System.out.println(new Date());
                list.add(user);
                um.changeTeacher(user);//清0
                break;
            }
        }
        return list;
        /**
         * 选人的时候可以点击头像选人*/
    }
    public void refresh(){
        um.refresh();
    }
    public String[] getAllTopic(){
        List<String> allTopic = um.getAllTopic();
        String[] result =  new String[allTopic.size()];
        for (int i = 0; i < allTopic.size(); i++) {
            result[i] = allTopic.get(i);
        }
        return result;
    }
}
