package com.scaleset.cfbuilder.core;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.scaleset.cfbuilder.autoscaling.AutoScalingTag;

public interface Taggable extends Resource {

    default Taggable tag(String key, String value) {
        Tag tag = new Tag(key, value);
        //node.withArray("Tags").addObject().put(key, value);
        ((ArrayNode) getProperties().withArray("Tags")).add(tag.toNode());
        return this;
    }

    Taggable tags(Tag... values);
}
