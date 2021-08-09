package models;

import java.util.ArrayList;

public class Squad {
    private String name;
    private int size;
    private String cause;
    private int id;
    private ArrayList<Hero> heroes;
    private static ArrayList<Squad> instances = new ArrayList<>();

    public Squad(String name, int size, String cause){
        instances.add(this);
        this.name = name;
        this.size = size;
        this.cause = cause;
        this.id = instances.size();

    }

    public static ArrayList<Squad> getAll() {
        return instances;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public void setHeroes(ArrayList<Hero> heroes) {
        this.heroes = heroes;
    }

    public ArrayList<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(Hero hero) {
        heroes.add(hero);
    }

    public static Squad findById(int id){
        return instances.get(id-1);
    }

    public void updateName(String newName) {
        this.name = newName;
    }

    public static void clearAllSquads(){
        instances.clear();
    }
    public void deleteSquad(){
        instances.remove(id-1); //same reason
    }

}
