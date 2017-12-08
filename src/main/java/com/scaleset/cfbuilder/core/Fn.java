package com.scaleset.cfbuilder.core;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fn {

    private Map<String, List<Object>> properties = new HashMap<>();

    @JsonIgnore
    private String name;

    @JsonIgnore
    private List<Object> parameters = new ArrayList<>();

    public Fn(String name, Object... params) {
        this.name = name;
        for (Object param : params) {
            parameters.add(param);
        }
        properties.put("Fn::" + name, parameters);
    }

    public Fn(String name, String delimiter, Object... params) {
        this.name = name;
        List<Object> list = new ArrayList();
        for (Object param : params) {
            list.add(param);
        }
        parameters.add(delimiter);
        parameters.add(list);
        properties.put("Fn::" + name, parameters);
    }

    public static Fn fn(String name, Object... params) {
        return new Fn(name, params);
    }

    @JsonAnyGetter
    protected Map<String, List<Object>> anyGetter() {
        return properties;
    }

}
