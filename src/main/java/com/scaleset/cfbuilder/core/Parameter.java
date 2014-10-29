package com.scaleset.cfbuilder.core;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.scaleset.cfbuilder.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parameter implements Referenceable {

    @JsonIgnore
    private String id;

    private String type;

    private String description;

    private Object defaultValue;

    private Boolean noEcho;

    private List<Object> allowedValues = new ArrayList<>();

    private String allowedPattern;

    protected Parameter() {
    }

    public Parameter(String id, String type, String defaultValue, String description) {
        this.id = id;
        this.type = type;
        this.defaultValue = defaultValue;
        this.description = description;
    }

    public Parameter(String id, String type, Number defaultValue, String description) {
        this.id = id;
        this.type = type;
        this.defaultValue = defaultValue;
        this.description = description;
    }

    public String getAllowedPattern() {
        return allowedPattern;
    }

    public List<Object> getAllowedValues() {
        return allowedValues;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public Boolean getNoEcho() {
        return noEcho;
    }

    public String getType() {
        return type;
    }

    public Ref ref() {
        return new Ref(id);
    }
}
