package com.training.model.entity;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class Street {
    private int id;
    private Map<String, String> names = new ConcurrentHashMap<>();
    //coordinates
    private double x;
    private double y;

    public Street(){

    }

    public Street(int id, String name_en, String name_ua, double x, double y) {
        this.id = id;
        names.put("en", name_en);
        names.put("ua", name_en);
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Optional<String> getName(String lang) {
        return Optional.ofNullable(names.getOrDefault(lang, null));
    }

    public String getNameString(String lang) {
        return names.getOrDefault(lang,
                names.getOrDefault("en", "noname"));
    }

    public void setName(String lang, String name) {
        names.put(lang, name);
    }
}
