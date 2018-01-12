/*
* Java with JDBC connector and DAO (Data Access Objects) component.
* Connection to mySQL database.
**/
package com.dave;


import com.dave.dao.UserDao;
import com.dave.model.User;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
	// write your code here
        //Connection connection = ConnectionFactory.getConnection();

       //System.out.print(connection.getMetaData());

        UserDao userDao = new UserDao();
        //get user by its id
        User user = userDao.getUser(1);
        //System.out.println(user.getName());

        //get all users
        for(User u : userDao.getAllUsers()){
            System.out.println("name:" +u.getName()+" age: "+u.getAge());
        }

        //get user by his name and pass
        Scanner scanner = new Scanner(System.in);
        System.out.println("hit user");
        String name = scanner.next();
        System.out.println("hit password");
        String passw = scanner.next();

        User userNamePassword = userDao.getUserByUserNameAndPassword(name, passw);
        if(userNamePassword != null){
            System.out.println("user id "+userNamePassword.getId()+" user name: "+userNamePassword.getId());
        }else {
            System.out.println("Wrong user or password!");
        }

        //insert user into table
        System.out.println("hit name: ");
        name = scanner.next();
        System.out.println("hit pass");
        passw = scanner.next();
        System.out.println("hit age");
        int age = scanner.nextInt();

        if(userDao.insertUser(name, passw, age)){
            System.out.println("row inserted!");
        }else {
            System.out.println("insert failure ;(");
        }


        //update database row...
        boolean updateCode = userDao.updateUser("test1", "pass1", "john", "doe");
        if(updateCode){
            System.out.println("row updated!");
        }else {
            System.out.println("update failure ;(");
        }

        //delete database row...
        boolean deleteCode = userDao.deleteUser(2);
        if(deleteCode){
            System.out.println("row updated!");
        }else {
            System.out.println("update failure ;(");
        }

    }
}
