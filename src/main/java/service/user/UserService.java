package service.user;

import entity.Users;
import service.IBaseService;

public interface UserService extends IBaseService<Users> {
    String isusername(String userName);

    Users login(String userName, String password);
}