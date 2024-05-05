//Login services for OOP Cookbook project
/*For the sake of simplicity, this uses a json file as a method to store username & login information.
This is NOT meant to be a secure, nor good way of storing this information. It is purely meant for brevity.
If it were to be expanded upon, it would be recommended to use a database for storage, as well as proper password hashing.

This file uses the support class, FileScanner to scan the json file for all login information.
When a login is prompted, it will check for matches in the json file, and return a boolean value based on the result.

Similarly, it will do the same when a new user is created, checking if the username already exists in the json file.
If it does not, it will add the new user to the json file alongside the password.
*/
package util;

import java.util.List;
import models.User;

public class LoginServices {
    private FileScanner fs;

    public LoginServices(String filePath){
        fs = new FileScanner(filePath);
    }

    public boolean login(String username, String password){
        List<User> users = fs.readUsers();
        for(User user : users){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    //This function performs the action of registering a new user.
    //It will check if the username already exists in the json file, and return false if it does.
    //Otherwise, it will add the new user to the json file.
    //Upon success, it will return true.
    public boolean register(String username, String password){
        List<User> users = fs.readUsers();
        for(User user : users){
            if(user.getUsername().equals(username)){
                return false; 
            }
        }

        users.add(new User(username, password));
        try {
            fs.writeUsers(users);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}