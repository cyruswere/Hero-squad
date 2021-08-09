package models;

import java.util.ArrayList;

public class Hero {
    private String name;
    private int age;
    private String weakness;
    private String powers;
    private static ArrayList<Hero> instances = new ArrayList<>();
    private int id;

    public Hero(String name, int age,String weakness, String powers){
        instances.add(this);
        this.name = name;
        this.age = age;
        this.weakness = weakness;
        this.powers = powers;
        this.id = instances.size();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getWeakness() {
        return weakness;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }

    public String getPowers() {
        return powers;
    }

    public void setPowers(String powers) {
        this.powers = powers;
    }

    public static ArrayList<Hero> getInstances() {
        return instances;
    }

    public static void setInstances(ArrayList<Hero> instances) {
        Hero.instances = instances;
    }

    public int getId() {
        return id;
    }


    public static ArrayList<Hero> getAll(){
        return instances;
    }

    public static Hero findById(int id){
        return instances.get(id-1);
    }

    public void updateName(String newName) {
        this.name = newName;
    }

    public static void clearAllHeroes(){
        instances.clear();
    }
    public void deleteHero(){
        instances.remove(id-1); //same reason
    }
}
