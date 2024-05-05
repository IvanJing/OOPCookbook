package Tests;
import java.util.List;

import util.FileScanner;
public class FileScannerTests {
    private FileScanner fs;
    public String filePath = "data\\users.json";

    //Testing successful read of users
    public void testReadUsers(){
        fs = new FileScanner(filePath);
        System.out.println("Testing readUsers()...");
        List<models.User> users = fs.readUsers();

        for(models.User user : users){
            System.out.println("Username: " + user.getUsername() + " Password: " + user.getPassword());
        }
    }

    public static void main(String[] args){
        FileScannerTests fst = new FileScannerTests();
        fst.testReadUsers();
    }
}

// Path: data\\users.json

