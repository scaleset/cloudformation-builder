package com.scaleset.cfbuilder.core;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.scaleset.cfbuilder.autoscaling.AutoScalingTag;

public interface Taggable extends Resource {

    default Taggable tag(String key, String value) {
        tags(new Tag(key, value));
        return this;
    }

    Taggable tags(Tag... values);
}
