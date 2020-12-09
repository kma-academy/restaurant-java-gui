/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kma.qlbh.utils;

/**
 *
 * @author MSI
 */
public enum FoodCategory {
    FOOD("food","Đồ ăn"),
    MILKTEA("milktea","Trà sữa"),
    COFFEE("coffee","Cà phê"),
    TOPPING("toppong","Topping");
    private String id,name;

    private FoodCategory(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public static FoodCategory getById(String id) {
        for (FoodCategory e : values()) {
            if (e.id.equals(id)) {
                return e;
            }
        }
        return null;
    }

    public static FoodCategory getByName(String name) {
        for (FoodCategory e : values()) {
            if (e.name.equals(name)) {
                return e;
            }
        }
        return null;
    }
}
