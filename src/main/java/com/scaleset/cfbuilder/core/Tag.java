package com.scaleset.cfbuilder.core;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Tag {

    private ObjectNode node = JsonNodeFactory.instance.objectNode();

    public Tag(String key, String value) {
        node.put("Key", key);
        node.put("Value", value);
    }

    public String getKey() {
        return node.get("Key").textValue();
    }

    public Boolean getPropagateAtLaunch() {
        return node.get("PropagateAtLaunch").asBoolean(false);
    }

    public String getValue() {
        return node.get("Value").textValue();
    }

    public Tag propagateAtLaunch() {
        node.put("PropagateAtLaunch", true);
        return this;
    }

    public ObjectNode toNode() {
        return node;
    }

}
