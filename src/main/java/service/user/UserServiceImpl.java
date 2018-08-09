package service.user;


import dao.user.UserDao;
import dao.user.UserDaoImpl;
import entity.Users;
import uite.PageUtil;

import java.io.Serializable;
import java.util.List;

public class UserServiceImpl implements UserService {

    //耦合 加上 静态代理
    private UserDao userDao = new UserDaoImpl();

    @Override
    public int add(Users users) {
        return userDao.add(users);
    }

    @Override
    public int deleteByCondition(Serializable id) {
        return 0;
    }

    @Override
    public int update(Users users) {
        return 0;
    }

    @Override
    public Users findByCondition(Serializable id) {
        return null;
    }

    @Override
    public List<Users> findAll() {
        return null;
    }

    @Override
    public int findRownum() {
        return 0;
    }

    @Override
    public List<Users> findAllByPage(PageUtil util, Object... params) {
        return null;
    }
}