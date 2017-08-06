package com.sl.rest.service;

public class KinderRESTResource {
    private final long id;
    private final String name;

    public KinderRESTResource(long _id, String _name) {
        this.id = _id;
        this.name = _name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}