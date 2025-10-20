package controller;

import domain.User;
import service.UserService;

import java.util.List;

public class UserController {
    private UserService userService = new UserService();
    public boolean createUser (User u){
        return userService.create(u);
    }
    public List<User> toList(){
        return userService.toList();
    }
}
