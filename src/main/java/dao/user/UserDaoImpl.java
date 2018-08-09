package dao.user;

import entity.Users;
import org.junit.jupiter.api.Test;
import uite.BaseDao;
import uite.PageUtil;

import java.io.Serializable;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {

    @Test
    public void text() {
        Users users = new Users();
        users.setUserName("dd");
        users.setEmail("dd");
        users.setUserType(2);
        users.setPassword("dd");
        System.out.println(add(users));
    }

    @Override
    public int add(Users users) {
        String sql = "INSERT INTO users(userName,`password`,email,userType) VALUES(?,?,?,?)";
        Object[] params = {users.getUserName(), users.getPassword(), users.getEmail(), users.getUserType()};
        return executeUpdate(sql, params);
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
