package com.scaleset.cfbuilder.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

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


    public static Fn fn(String name, Object... params) {
        return new Fn(name, params);
    }

    public static Fn fnDelimiter(String name, String delimiter, Object... params){
        List<Object> list = Arrays.asList(params);
        return new Fn(name, delimiter, list);
    }

    @JsonAnyGetter
    protected Map<String, List<Object>> anyGetter() {
        return properties;
    }

}
