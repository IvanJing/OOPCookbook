/*This is a support class that functions as a way to parse the json file used for storing login information.
It incorportates simple java io to read and write to the file.
*/
package util;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import models.User;
import java.io.*;
public class FileScanner {
    private String filePath;

    public FileScanner(String filePath) {
        this.filePath = filePath;
    }

    //Reads the json file of users and returns a list of users
    public List<User> readUsers(){
        List<User> users = new ArrayList<>();
        try(Scanner scanner = new Scanner(new File(filePath))){
            scanner.nextLine();
            scanner.nextLine();
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                if(line.equals("]")) break;
                String username = line.split(":")[1].split(",")[0].replace("\"", "");
                String password = line.split(":")[2].replace("\"", "").replace("}", "");
                users.add(new User(username, password));
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    return users;
    }

    
}
