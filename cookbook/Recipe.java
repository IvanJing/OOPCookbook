// Recipe.java

import java.util.ArrayList;

public class Recipe {
    private int id;
    private String name;
    private float difficultyRatingOutOf10;
    private String description;
    private String userId;
    private String time;
    private ArrayList<String> ingredients = new ArrayList<String>();
    private ArrayList<String> steps = new ArrayList<String>();
    
    private boolean favorite;

    public Recipe(int id, String name, float difficultyRatingOutOf10, String description, String time,
     String userId,ArrayList<String>ingredients, ArrayList<String> steps) {
        this.id = id;
        this.name = name;
        this.difficultyRatingOutOf10=difficultyRatingOutOf10;
        this.description = description;
        this.time = time;
        this.userId = userId;
        this.ingredients=ingredients;
        this.steps=steps;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getDifficultyRatingOutOf10() {
        return difficultyRatingOutOf10;
    }

    public String getDescription() {
        return description;
    }

    public String getUserId() {
        return userId;
    }
    
    public String getTime() {
        return time;
    }

    public ArrayList<String> getIngredients(){
        return ingredients;
    }

    public ArrayList<String> getSteps(){
        return steps;
    }

    public boolean getFavorite(){
        return favorite;
    }

    public void setFavorite(){
        favorite=true;
    }
}
