package com.mitchell.daniel.brickinventory;

import java.util.Hashtable;
import java.util.List;

public class Set {
    //private attributes
    //Hashtable contains a Part and an integer array with two ints
    //[1] is the total amount of parts in the set
    //[0] is the amount 'checked'
    private String set_num;
    private String name;
    private int year;
    private int theme_id;
    private int num_parts;
    private String set_img_url;
    private String set_url;
    private List<Part> parts;
    private Hashtable<Part, Integer[]> parts_obtained;

    //Setters
    public void setSet_num(String set_num){this.set_num = set_num;}
    public void setName(String name){this.name = name;}
    public void setYear(int year){this.year = year;}
    public void setTheme_id(int theme_id){this.theme_id = theme_id;}
    public void setNum_parts(int num_parts){this.num_parts = num_parts;}
    public void setSet_img_url(String set_img_url){this.set_img_url = set_img_url;}
    public void setSet_url(String set_url){this.set_url = set_url;}
    public void setParts(List<Part> parts){this.parts = parts;}
    public void setParts_obtained(Hashtable<Part, Integer[]> parts_obtained){this.parts_obtained = parts_obtained;}

    //Getters
    public String getSet_num(){return set_num;}
    public String getName(){return name;}
    public int getYear(){return year;}
    public int getTheme_id(){return theme_id;}
    public int getNum_parts(){return num_parts;}
    public String getSet_img_url(){return set_img_url;}
    public String getSet_url(){return set_url;}
    public List<Part> getParts(){return parts;}
    public Hashtable<Part, Integer[]> getParts_obtained(){return parts_obtained;}

    //Adds one of a specific part to the parts_obtained
    public boolean addPart(Part part){
        if(parts_obtained.containsKey(part)){
            ++parts_obtained.get(part)[0];
            return true;
        }
        else return false;
    }

    public String toString(){
        String s = "";
        s += "Set number " + set_num + ", also called " + name;
        s += " contains " + Integer.toString(num_parts) + " parts.\n";
        return s;
    }
}