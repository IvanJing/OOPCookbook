package Tests;
import util.LoginServices;

public class LoginServicesTests {
    //Check for successful login using the existing username/password of janedoe1 and password1
    public void testLoginSuccess(){
        LoginServices ls = new LoginServices("data\\users.json");
        System.out.println("Testing login() with existing user...");
        boolean result = ls.login("JaneDoe1", "password1");
        System.out.println("Login result: " + result);
    }
    
    //Failed Login testcase with incorrect password
    public void testLoginFailed(){
        LoginServices ls = new LoginServices("data\\users.json");
        System.out.println("Testing login() with incorrect password...");
        boolean result = ls.login("JaneDoe1", "password2");
        System.out.println("Login result: " + result);
    }

    //Failed Login testcase with incorrect username
    public void testLoginFailed2(){
        LoginServices ls = new LoginServices("data\\users.json");
        System.out.println("Testing login() with non-existing user...");
        boolean result = ls.login("JaneDoe2", "password1");
        System.out.println("Login result: " + result);
    }

    //Testing successful user registration
    public void testRegisterSuccess(){
        LoginServices ls = new LoginServices("data\\users.json");
        System.out.println("Testing register() with new user...");
        boolean result = ls.register("Jane2", "password");
        System.out.println("Register result: " + result);
    }

    //Testing failed user registration with existing username
    public void testRegisterFailed(){
        LoginServices ls = new LoginServices("data\\users.json");
        System.out.println("Testing register() with existing user...");
        boolean result = ls.register("JaneDoe1", "password1");
        System.out.println("Register result: " + result);
    }

    public static void main(String[] args){
        //Test Login
        LoginServicesTests lst = new LoginServicesTests();
        lst.testLoginSuccess();

        //Test Failed Login with password error
        lst.testLoginFailed();

        //Test Failed Login with username error
        lst.testLoginFailed2();

        //Test successful registration of a new user
        lst.testRegisterSuccess();

        //Test failed registration with existing username
        lst.testRegisterFailed();
    }
}
