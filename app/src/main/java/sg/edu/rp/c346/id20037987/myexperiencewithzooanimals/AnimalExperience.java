package sg.edu.rp.c346.id20037987.myexperiencewithzooanimals;

import java.io.Serializable;

public class AnimalExperience implements Serializable {

    private int id;
    private String name;
    private String exp;
    private String locate;
    private int stars;

    public AnimalExperience(String name, String exp, String locate, int stars) {
        this.name = name;
        this.exp = exp;
        this.locate = locate;
        this.stars = stars;
    }

    public AnimalExperience(int id, String name, String exp, String locate, int stars) {
        this.id = id;
        this.name = name;
        this.exp = exp;
        this.locate = locate;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public AnimalExperience setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AnimalExperience setName(String name) {
        this.name = name;
        return this;
    }

    public String getExp() {
        return exp;
    }

    public AnimalExperience setExp(String exp) {
        this.exp = exp;
        return this;
    }

    public String getLocation() {
        return locate;
    }

    public AnimalExperience setLocation(String locate) {
        this.locate = locate;
        return this;
    }

    public int getStars() {
        return stars;
    }

    public AnimalExperience setStars(int stars) {
        this.stars = stars;
        return this;
    }

    /*@Override
    public String toString() {
        String starsString = "";
        if (stars == 5){
            starsString = "* * * * *";
        } else if (stars == 4){
            starsString = "* * * *";
        } else if (stars == 3){
            starsString = "* * *";
        } else if (stars == 2){
            starsString = "* *";
        } else if (stars == 1){
            starsString = "*";
        }
        return starsString;
        //or
        //for(int i = 0; i < stars; i++){
            //starsString += "*";
        //}
        //return title + "\n" + singers + " - " + yearReleased + "\n" + starsString;

    }*/

}
