// Recipe.java

import java.util.ArrayList;

public class Recipe {
    private int id;
    private String name;
    private float difficulty;
    private String description;
    private String userId;
    private float time;
    
    private boolean favorite;

    public Recipe(int id, String name, float difficulty, String description, float time, String userId) {
        this.id = id;
        this.name = name;
        this.difficulty=difficulty;
        this.description = description;
        this.time = time;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getdifficulty() {
        return difficulty;
    }

    public String getDescription() {
        return description;
    }

    public String getUserId() {
        return userId;
    }
    
    public float getTime() {
        return time;
    }

    public boolean getFavorite(){
        return favorite;
    }

    public void setFavorite(){
        favorite=true;
    }
}
