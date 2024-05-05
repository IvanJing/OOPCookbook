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
    public List<User> readUsers() {
        List<User> users = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            scanner.nextLine();
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.equals("]")) break; 
                String[] parts = line.split("\"");
                if (parts.length >= 6) { 
                    String username = parts[3];
                    String password = parts[7];
                    users.add(new User(username, password));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return users;
    }

    //Writes the list of users to the json file. This is used for adding new users.
    public void writeUsers(List<User> users) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filePath, false))) { 
            out.println("{");
            out.println("  \"users\": [");
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                out.print("    {\"username\": \"" + user.getUsername() + "\", \"password\": \"" + user.getPassword() + "\"}");
                if (i < users.size() - 1) {
                    out.println(",");
                } else {
                    out.println();
                }
            }
            out.println("  ]");
            out.println("}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
