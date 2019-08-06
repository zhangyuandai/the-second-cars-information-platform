package com.keshe.controller;

import com.keshe.dao.UserDao;
import com.keshe.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class Usercontroller {

    @Autowired
    private UserDao userDao;

    //查询所有用户列表
    @GetMapping("/users")
    public String userList(Model model){
        List<User> users = userDao.findAll();
        model.addAttribute("users",users);
        return "userList";
    }

    //添加一个成员
    @PostMapping("/useradd")
    public String useradd(@RequestParam("username") String username,
                        @RequestParam("password") String password,@RequestParam("age") Integer age){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setAge(age);

        userDao.save(user);

        return "redirect:/users";
    }

    //查询一个成员
    @GetMapping("/user/{id}")
    public User userFindOne(@PathVariable("id") Long id){
        return userDao.findById(id).get();
    }

    //删除一个成员
    @RequestMapping("/delete")
    public String userDelete(Long id){
        userDao.deleteById(id);
        return"redirect:/users";
    }

    //更新一个成员
    @GetMapping(value = "/toEdit")
    public String userUpdate(Model model,Long id){
        User user = userDao.findUserById(id);
        model.addAttribute("user",user);
        return "userupdate";
    }

    @PostMapping("/useredit")
    public String edit (User  user){
        userDao.save(user);
        return "redirect:/users";
    }
}
