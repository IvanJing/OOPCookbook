import util.LoginServices;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Cookbook {
    static ArrayList<Recipe> recipes = new ArrayList<Recipe>();
    private static final String RECIPE_DATA_FILE = "recipes.txt";
    private static final String USER_DATA_FILE = "users.json";
    private static Scanner scanner = new Scanner(System.in);
    private static LoginServices loginServices;
    private static String currentUser;
    private static RecipeManager recipeManager = new RecipeManager();


    public static void main(String[] args) {
        FileManager.createRecipeFile();
        recipeManager = new RecipeManager();
        recipeManager.setRecipes(FileManager.readRecipesFromFile());

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
                    System.out.println("\nOption 1: Add Recipe");
                    enterNewRecipe();
                    break;
                case 2:
                    // Search for Recipe
                    System.out.println("\nOption 2: Search for Recipe");
                    searchForRecipe();
                    break;
                case 3:
                    // Browse All Recipes
                    System.out.println("\nOption 3: Browse All Recipes");
                    browseAllRecipes();
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
        String time = scanner.nextLine();
        System.out.print("Enter difficulty rating (out of 10): ");
        float difficulty = scanner.nextFloat();

        int id = generateRecipeId();
        Recipe newRecipe = new Recipe(id, name, description, time, difficulty, currentUser);
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
                    int favoriteId = scanner.nextInt();
                    favoriteRecipe(favoriteId);
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

        List<Recipe> userRecipes = recipeManager.getUserRecipes(currentUser);
        Iterator<Recipe> iterator = userRecipes.iterator();
        while (iterator.hasNext()) {
            Recipe recipe = iterator.next(); 

            if(recipe.getId()==id){

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
                    recipe.setName(name);

                    case 2:
                    System.out.print("Enter edited recipe description: ");
                    String description = scanner.nextLine();
                    recipe.setDescription(description);

                    case 3:
                    System.out.print("Enter edited estimated cooking time: ");
                    String time = scanner.nextLine();
                    recipe.setTime(time);

                    case 4:
                    System.out.print("Enter edited difficulty rating (out of 10): ");
                    float difficulty = scanner.nextFloat();
                    recipe.setDifficulty(difficulty);

                }

                System.out.println("Recipe Edited successfully!");
                return;
            }
        }
        System.out.println("Recipe with id "+id+" could not be found");
    }

    private static void favoriteRecipe(int id){
        List<Recipe> userRecipes = recipeManager.getUserRecipes(currentUser);
        Iterator<Recipe> iterator = userRecipes.iterator();
        while (iterator.hasNext()) {
            Recipe recipe = iterator.next(); 
            if (recipe.getId() == id) {
                recipe.setFavorite(true);
                System.out.println("Recipe "+id+" has been added to favorites");
                return;
            }
        }
    }

    private static void deleteRecipe(int recipeId) {
        List<Recipe> userRecipes = recipeManager.getUserRecipes(currentUser);
        Iterator<Recipe> iterator = userRecipes.iterator();
        while (iterator.hasNext()) {
            Recipe recipe = iterator.next(); 
            if (recipe.getId() == recipeId) {
                iterator.remove();
                recipeManager.deleteRecipe(recipeId);
                updateRecipeDataFile(userRecipes); 
                System.out.println("Recipe deleted successfully!");
                
                return;
            }
        }
        System.out.println("Recipe not found.");
    }

    private static void updateRecipeDataFile(List<Recipe> recipes) {
        try {
            FileWriter writer = new FileWriter(RECIPE_DATA_FILE);
            for (Recipe recipe : recipes) {
                writer.write(recipe.getId() + "," + recipe.getName() + "," + recipe.getDescription() + "," + recipe.getUserId() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void browseAllRecipes() {
        List<Recipe> userRecipes = recipeManager.getUserRecipes(currentUser);

        System.out.println("1. Browse all");
        System.out.println("2. Browse Favorites");
        System.out.print("Enter your choice: ");
        int choose = scanner.nextInt();
        scanner.nextLine();

        if(choose == 1){
            recipeManager.displayRecipes(userRecipes);
        }
        else if (choose ==2){
            recipeManager.displayFaveRecipes(userRecipes);
        }
        else{
            System.out.println("Invalid choice. Please try again.");
        }
    
    }

}
