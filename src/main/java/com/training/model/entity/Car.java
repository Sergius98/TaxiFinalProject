package com.training.model.entity;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class Car {
    private int id; // car class
    private Map<String, String> names = new ConcurrentHashMap<>();
    private long price; // price per 1 standard units, in cents

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
