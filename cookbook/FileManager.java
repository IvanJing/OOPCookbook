// FileManager.java
import java.util.*;
import java.io.*;
//import models.User;

class FileManager {
    //private static final String USER_DATA_FILE = "users.txt";
    private static final String RECIPE_DATA_FILE = "recipes.txt";

    /* 
    public static boolean createUserFile() {
        try {
            File file = new File(USER_DATA_FILE);
            if (!file.exists()) {
                file.createNewFile();
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    */

    public static boolean createRecipeFile() {
        try {
            File file = new File(RECIPE_DATA_FILE);
            if (!file.exists()) {
                file.createNewFile();
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /* 
    public static boolean writeUserToFile(User user) {
        try {
            FileWriter writer = new FileWriter(USER_DATA_FILE, true);
            writer.write(user.getUsername() + "," + user.getPassword() + "\n");
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    */

    public static boolean writeRecipeToFile(Recipe recipe) {
        try {
            FileWriter writer = new FileWriter(RECIPE_DATA_FILE, true);
            writer.write(recipe.getId() + "/" + recipe.getName() + "/" + recipe.getDescription() + "/" + recipe.getTime() + "/" + recipe.getDifficulty() + "/" + recipe.getUserId() + "\n");
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /* 
    public static List<User> readUsersFromFile() {
        List<User> users = new ArrayList<>();
        try {
            FileReader reader = new FileReader(USER_DATA_FILE);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] userData = line.split(",");
                User user = new User(userData[0], userData[1]);
                users.add(user);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
    */

    public static List<Recipe> readRecipesFromFile() {
        List<Recipe> recipes = new ArrayList<>();
        try {
            FileReader reader = new FileReader(RECIPE_DATA_FILE);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] recipeData = line.split("/");
                if (recipeData.length >= 6) {
                    Recipe recipe = new Recipe(Integer.parseInt(recipeData[0]), recipeData[1], recipeData[2], recipeData[3], Float.parseFloat(recipeData[4]), recipeData[5]); // Include userId
                    recipes.add(recipe);
                } else {
                    System.out.println("Invalid recipe data format: " + line);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recipes;
    }
}
