import java.util.*;

public class UI {
    Scanner scn = new Scanner(System.in);
    

    public void deleteUI(){
        System.out.print("______________________________\n");
        System.out.print("Delete Recipe\n");
        System.out.println("______________________________");
        System.out.println("How would you like to delete a recipe?");
        System.out.println("1. By name");
        System.out.println("2. By id");
        int opt=scn.nextInt();
    }
}