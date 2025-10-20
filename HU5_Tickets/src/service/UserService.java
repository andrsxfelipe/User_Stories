package service;

import dao.UserDAO;
import domain.User;

import java.util.List;

public class UserService {
    private final UserDAO userDAO = new UserDAO();
    public boolean create(User u){
        return userDAO.toInsert(u);
    }
    public List<User> toList(){
        return userDAO.toList();
    }
}
