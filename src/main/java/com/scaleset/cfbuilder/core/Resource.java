package com.scaleset.cfbuilder.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@JsonPropertyOrder({ "Id", "Type", "Properties"})
public interface Resource extends Referenceable {

    @JsonProperty("Id")
    String getId();

    @JsonProperty("Type")
    String getType();

    @JsonProperty("Properties")
    JsonNode getProperties();

    Tag tag(String key, String value);

}
