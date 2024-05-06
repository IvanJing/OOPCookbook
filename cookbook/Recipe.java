// Recipe.java

import java.util.ArrayList;

public class Recipe {
    private int id;
    private String name;
    private float difficulty;
    private String description;
    private String userId;
    private String time;
    
    private boolean favorite;

    //public Recipe(int id, String name, float difficulty, String description, float time, String userId) {
    public Recipe(int id, String name, String description, String time, float difficulty, String userId) {
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

    public float getDifficulty() {
        return difficulty;
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

    public boolean getFavorite(){
        return favorite;
    }

    public void setFavorite(boolean val){
        favorite=val;
    }

    public void setTime(String t){
        time=t;
    }

    public void setDescription(String d){
        description=d;
    }

    public void setDifficulty(float dif){
        difficulty=dif;
    }

    public void setName(String n){
        name=n;
    }

    

}
