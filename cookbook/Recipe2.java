// Recipe.java (version that use w/ main for now --> can change)

class Recipe {
    private int id;
    private String name;
    private String description;
    private String userId;
    private String time;
    private float difficulty;

    public Recipe(int id, String name, String description, String time, float difficulty, String userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.time = time;
        this.difficulty = difficulty;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public float getDifficulty() {
        return difficulty;
    }
}
