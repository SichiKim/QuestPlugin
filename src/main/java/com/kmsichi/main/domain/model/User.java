package com.kmsichi.main.domain.model;

import java.util.UUID;

public class User {
    private UUID uuid;
    private String name;
    private String prefix;
    private int[] stats = new int[4];

    public User(String name) {
        this.name = name;
    }

    public User(UUID uuid, String name, String prefix, int[] stats) {
        this.uuid = uuid;
        this.name = name;
        this.prefix = prefix;
        this.stats = stats;
    }
}
