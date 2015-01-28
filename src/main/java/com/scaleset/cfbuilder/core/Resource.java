package com.scaleset.cfbuilder.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@JsonPropertyOrder({"Id", "Type", "Properties"})
public interface Resource extends Referenceable {

    @JsonProperty("Id")
    String getId();

    @JsonProperty("Type")
    String getType();

    @JsonProperty("Properties")
    JsonNode getProperties();

    default Fn fnGetAtt(String attributeName) {
        return new Fn("GetAtt", getId(), attributeName);
    }

}
