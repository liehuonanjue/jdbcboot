package dao.user;

import entity.Users;
import org.junit.jupiter.api.Test;
import uite.BaseDao;
import uite.PageUtil;
import uite.ResultSetUtil;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class UserDaoImpl extends BaseDao implements UserDao {

    @Test
    public void text() throws SQLException, ClassNotFoundException {
/*        String sql = "SELECT * FROM users WHERE userName=?";
        Object[] params = {"dd"};
        rs = executeQuery(sql, params);
        ResultSetMetaData data = rs.getMetaData();
        for (int i = 1; i <= data.getColumnCount(); i++) {
            System.out.println(data.getColumnName(i));
        }
        Class c = Class.forName("entity.Users");  //加载
        Field[] fields = c.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            System.out.println(fields[i].getName());
        }*/
        System.out.println(deleteByCondition(22));
    }

    @Override
    public int add(Users users) {
        String sql = "INSERT INTO users(userName,`password`,email,userType,FILE) VALUES(?,?,?,?,?)";
        Object[] params = {users.getUserName(), users.getPassword(), users.getEmail(), users.getUserType(), users.getFile()};
        return executeUpdate(sql, params);
    }

    @Override
    public int deleteByCondition(Serializable id) {
        String sql = "delete from users where id=?";
        int num = executeUpdate(sql, id);
        return num;
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
        String sql = "SELECT COUNT(id) as count FROM users";
        rs = executeQuery(sql);
        int count = 0;
        try {
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<Users> findAllByPage(PageUtil util, Object... params) {
        String sql = "SELECT id as user_id,userName,PASSWORD,email,userType FROM users limit ?,?";
        Object[] param = {(util.getPageIndex() - 1) * util.getPageSize(), util.getPageSize()};
        rs = executeQuery(sql, param);
        List<Users> list = ResultSetUtil.eachList(rs, Users.class);
        return list;
    }

    @Override
    public String isusername(String userName) {
        String sql = "SELECT PASSWORD FROM users WHERE userName=?";
        Object[] params = {userName};
        rs = executeQuery(sql, params);
        String password = "";
        try {
            if (rs.next()) {
                password = rs.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return password; //有值返回1
    }

    @Override
    public Users login(String userName, String password) {
        String sql = "SELECT id AS user_id ,userName,PASSWORD,email,userType FROM users WHERE userName=? AND PASSWORD=?";
        Object[] params = {userName, password};
        rs = executeQuery(sql, params);
        Users users = ResultSetUtil.eachOne(rs, Users.class);
        return users;
    }


}
