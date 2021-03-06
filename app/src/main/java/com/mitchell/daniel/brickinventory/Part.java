package com.mitchell.daniel.brickinventory;

public class Part {
    private String part_num;
    private String name;
    private int part_cat_id;
    private String part_url;
    private String part_img_url;
    private int part_color_id;
    private String part_color_name;
    private String part_color_rgb;
    private boolean part_color_trans;

    public void setPart_num(String part_num){this.part_num = part_num;}
    public void setName(String name){this.name = name;}
    public void setPart_cat_id(int part_cat_id){this.part_cat_id = part_cat_id;}
    public void setPart_url(String part_url){this.part_url = part_url;}
    public void setPart_img_url(String part_img_url){this.part_img_url = part_img_url;}
    public void setPart_color_id(int part_color_id){this.part_color_id = part_color_id;}
    public void setPart_color_name(String part_color_name){this.part_color_name = part_color_name;}
    public void setPart_color_rgb(String part_color_rgb){this.part_color_rgb = part_color_rgb;}
    public void setPart_color_trans(boolean part_color_trans){this.part_color_trans = part_color_trans;}

    public String getPart_num(){return part_num;}
    public String getName(){return name;}
    public int getPart_cat_id(){return part_cat_id;}
    public String getPart_url(){return part_url;}
    public String getPart_img_url(){return part_img_url;}
    public int getPart_color_id(){return part_color_id;}
    public String getPart_color_name(){return part_color_name;}
    public String getPart_color_rgb(){return part_color_rgb;}
    public boolean getPart_color_trans(){return part_color_trans;}

    public String toString(){
        String s = "";
        s += name += " is numbered " + part_num + "\n";
        s += "It is " + part_color_name + " with a rgb code of " + part_color_rgb + "\n\n";
        return s;
    }
}
