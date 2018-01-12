package com.dave.dao;

import com.dave.api.IUserDao;
import com.dave.conn.ConnectionFactory;
import com.dave.model.User;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class UserDao implements IUserDao {

    @Override
    public User getUser(int id) {
        Connection connection = ConnectionFactory.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM jdbc_1 WHERE  id="+id);

            if(rs.next()){

                return extractUserFromResultSet(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<User> getAllUsers() {
        Connection connection = ConnectionFactory.getConnection();
        Set<User> users;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM jdbc_1");

            users = new HashSet<>();

            while (resultSet.next()){
                users.add(extractUserFromResultSet(resultSet));
            }

            return users;

        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User getUserByUserNameAndPassword(String name, String password) {

        Connection connection = ConnectionFactory.getConnection();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM jdbc_1 WHERE name=? AND password=?");

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return extractUserFromResultSet(resultSet);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean insertUser(String name, String password, int age) {

        Connection connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement preparedStatement
                    = connection.prepareStatement("INSERT INTO jdbc_1 VALUE (NULL, ?, ?, ?)");

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, age);

            int resultCode = preparedStatement.executeUpdate();

            if(resultCode == 1){
                return true;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateUser(String name, String pass, String nName, String nPass) {
        Connection connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE jdbc_1 SET name=?, password=? WHERE name=? AND password=? ");

            preparedStatement.setString(1, nName);
            preparedStatement.setString(2, nPass);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, pass);
            int updateCode = preparedStatement.executeUpdate();

            if(updateCode == 1){
                return true;
            }


        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteUser(int id) {

        Connection connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM jdbc_1 WHERE id=?");

            preparedStatement.setString(1, Integer.toString(id));
            int updateCode = preparedStatement.executeUpdate();

            if(updateCode == 1){
                return true;
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    private User extractUserFromResultSet(ResultSet rs) throws SQLException{
        User user = new User();

        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setPass(rs.getString("password"));
        user.setAge(rs.getInt("age"));

        return user;
    }
}
