package com.scaleset.cfbuilder.autoscaling;

import com.scaleset.cfbuilder.core.Tag;

public class AutoScalingTag extends Tag {

    public AutoScalingTag(String key, String value) {
        super(key, value);
    }

    public AutoScalingTag propagateAtLaunch() {
        node.put("PropagateAtLaunch", true);
        return this;
    }

}
