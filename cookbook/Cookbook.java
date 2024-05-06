import util.LoginServices;
import java.util.*;
//import util.FileScanner;
//import models.User;

import java.util.List;
import java.util.Scanner;

//import com.apple.eio.FileManager;

public class Cookbook {
    static ArrayList<Recipe> recipes = new ArrayList<Recipe>();

    private static final String USER_DATA_FILE = "users.json";
    //private static final String RECIPE_DATA_FILE = "recipes.txt";
    private static Scanner scanner = new Scanner(System.in);
    private static LoginServices loginServices;
    //private static User currentUser;
    private static String currentUser;
    private static RecipeManager recipeManager = new RecipeManager();


    public static void main(String[] args) {
        FileManager.createRecipeFile();
        recipeManager = new RecipeManager();
        recipeManager.setRecipes(FileManager.readRecipesFromFile());
        // loginOrRegister();

        loginServices = new LoginServices(USER_DATA_FILE);
        displayMainMenu();
    }

    private static void displayMainMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (loginServices.login(username, password)) {
            System.out.println("Login successful!");
            // Proceed to main functionality
            currentUser = username;
            displayMainMenuAfterLogin();
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private static void register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (loginServices.register(username, password)) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Username already exists. Please choose a different username.");
        }
    }

    private static void displayMainMenuAfterLogin() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Add Recipe");
            System.out.println("2. Search for Recipe");
            System.out.println("3. Browse All Recipes");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Add Recipe
                    System.out.println("Option 1: Add Recipe");
                    enterNewRecipe();
                    break;
                case 2:
                    // Search for Recipe
                    System.out.println("Option 2: Search for Recipe");
                    searchForRecipe();
                    break;
                case 3:
                    // Browse All Recipes
                    System.out.println("Option 3: Browse All Recipes");
                    // Implement Browse All Recipes functionality here 
                    // implemented own browse to check, u can change
                    // browseAllRecipes()
                    break;
                case 4:
                    exit = true;
                    System.out.println("Logged out successfully.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void enterNewRecipe() {
        System.out.print("Enter recipe name: ");
        String name = scanner.nextLine();
        System.out.print("Enter recipe description: ");
        String description = scanner.nextLine();
        // NEW
        System.out.print("Enter estimated cooking time: ");
        float time = scanner.nextFloat();
        System.out.print("Enter difficulty rating (out of 10): ");
        float difficulty = scanner.nextFloat();

        int id = generateRecipeId();
        Recipe newRecipe = new Recipe(id, name, difficulty, description, time, currentUser);
        FileManager.writeRecipeToFile(newRecipe);
        recipeManager.addRecipe(newRecipe);
        System.out.println("Recipe added successfully!");
    }

    private static int generateRecipeId() {
        List<Recipe> recipes = FileManager.readRecipesFromFile();
        int maxId = 0;
        for (Recipe recipe : recipes) {
            if (recipe.getId() > maxId) {
                maxId = recipe.getId();
            }
        }
        maxId++;
        return maxId;
    }
    
    private static void searchForRecipe() {
        System.out.println("Search Menu:");
        System.out.println("1. Search by recipe name");
        System.out.println("2. Search by recipe description");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter recipe name: ");
                String name = scanner.nextLine();
                List<Recipe> matchedRecipesByName = recipeManager.searchByName(name, currentUser);
                displaySearchResults(matchedRecipesByName);
                break;
            case 2:
                System.out.print("Enter recipe description: ");
                String description = scanner.nextLine();
                List<Recipe> matchedRecipesByDescription = recipeManager.searchByDescription(description, currentUser);
                displaySearchResults(matchedRecipesByDescription);
                break;
            
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void displaySearchResults(List<Recipe> recipes) {
        recipeManager.displayRecipes(recipes);

        boolean exit = false;
        while (!exit) {
            System.out.println("\nOptions:");
            System.out.println("1. Favorite recipe");
            System.out.println("2. Delete recipe");
            System.out.println("3. Edit recipe");
            System.out.println("4. Return to main menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter the ID of the recipe to favorite: ");
                    // int favoriteId = scanner.nextInt();
                    // TO DO......

                    break;
                case 2:
                    System.out.print("Enter the ID of the recipe to delete: ");
                    int deleteId = scanner.nextInt();
                    deleteRecipe(deleteId);
                    break;
                case 3:
                    System.out.print("Enter the ID of the recipe to edit: ");
                    int editId = scanner.nextInt();
                    editRecipe(editId);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void editRecipe(int id){

        for (int i=0;i<recipes.size();i++){
            if(recipes.get(i).getId()==id){

                System.out.println("What would you like to edit?");
                System.out.println("1. Recipe Name");
                System.out.println("2. Recipe Description");
                System.out.println("3. Recipe cooking time");
                System.out.println("4. Recipe Difficulty");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                
                int editChoice = scanner.nextInt();
                scanner.nextLine();
                switch(editChoice){
                    case 1:
                    System.out.print("Enter edited recipe name: ");
                    String name = scanner.nextLine();
                    recipes.get(i).setName(name);

                    case 2:
                    System.out.print("Enter edited recipe description: ");
                    String description = scanner.nextLine();
                    recipes.get(i).setDescription(description);

                    case 3:
                    System.out.print("Enter edited estimated cooking time: ");
                    float time = scanner.nextFloat();
                    recipes.get(i).setTime(time);

                    case 4:
                    System.out.print("Enter edited difficulty rating (out of 10): ");
                    float difficulty = scanner.nextFloat();
                    recipes.get(i).setDifficulty(difficulty);

                }

            
                // FileManager.writeRecipeToFile(newRecipe);
                // recipeManager.addRecipe(newRecipe);
                System.out.println("Recipe Edited successfully!");
                return;
            }
        }
        System.out.println("Recipe with id "+id+" could not be found");
    }

    private static void deleteRecipe(int id){

        for (int i=0;i<recipes.size();i++){
            if(recipes.get(i).getId()==id){
                recipes.remove(i);
                return;
            }
        }
        System.out.println("Recipe with id "+id+" could not be found");


    }
/* 
    private static void browseAllRecipes() {
        List<Recipe> userRecipes = recipeManager.getUserRecipes(currentUser);
        recipeManager.displayRecipes(userRecipes);
    }
*/

}
