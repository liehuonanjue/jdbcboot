package dao.user;

import dao.IBaseDao;
import entity.Users;

import java.io.Serializable;

/**
 * 书写自己特有的功能的子接口
 */

public interface UserDao extends IBaseDao<Users> {


    String isusername(String userName);

    Users login(String userName, String password);

}
