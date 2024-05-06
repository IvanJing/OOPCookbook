import java.util.*;

class RecipeManager {
    private List<Recipe> recipes;

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    
    public void deleteRecipe(int recipeId) {
        Iterator<Recipe> iterator = recipes.iterator();
        while (iterator.hasNext()) {
            Recipe recipe = iterator.next(); 
            if (recipe.getId() == recipeId) {
                iterator.remove();
                return;
            }
        }
        System.out.println("Recipe not found.");
    }
    
    public List<Recipe> getUserRecipes(String userId) {
        List<Recipe> userRecipes = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getUserId().equals(userId)) {
                userRecipes.add(recipe);
            }
        }
        return userRecipes;
    }

    public List<Recipe> searchByName(String name, String username) {
        List<Recipe> matchedRecipes = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getName().equalsIgnoreCase(name) && recipe.getUserId().equalsIgnoreCase(username)) {
                matchedRecipes.add(recipe);
            }
        }
        return matchedRecipes;
    }

    public List<Recipe> searchByDescription(String description, String username) {
        List<Recipe> matchedRecipes = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getDescription().contains(description) && recipe.getUserId().equalsIgnoreCase(username)) {
                matchedRecipes.add(recipe);
            }
        }
        return matchedRecipes;
    }

    // browse stuff
    public void displayRecipes(List<Recipe> recipes) {
        for (Recipe recipe : recipes) {
            System.out.println("\nRecipe: " + recipe.getName());
            System.out.println("------------------------------");
            System.out.println("ID: " + recipe.getId());
            System.out.println("Cooking Time: " + recipe.getTime());
            System.out.println("Difficulty rating: " + recipe.getDifficulty());
            System.out.println("Description: " + recipe.getDescription());

        }
    }

    // browse faves
    public void displayFaveRecipes(List<Recipe> recipes) {
        for (Recipe recipe : recipes) {
            if(recipe.getFavorite() == true){
                System.out.println("\nRecipe: " + recipe.getName());
                System.out.println("------------------------------");
                System.out.println("ID: " + recipe.getId());
                System.out.println("Cooking Time: " + recipe.getTime());
                System.out.println("Difficulty rating: " + recipe.getDifficulty());
                System.out.println("Description: " + recipe.getDescription());
            }
        }
    }
}
