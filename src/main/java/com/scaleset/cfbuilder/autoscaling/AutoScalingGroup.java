package com.scaleset.cfbuilder.autoscaling;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.scaleset.cfbuilder.annotations.Type;
import com.scaleset.cfbuilder.core.Resource;


@Type("AWS::AutoScaling::AutoScalingGroup")
public interface AutoScalingGroup extends Resource {

    AutoScalingGroup availabilityZone(Object... values);

    AutoScalingGroup cooldown(Object value);

    AutoScalingGroup desiredCapacity(Object value);

    AutoScalingGroup healthCheckGracePeriod(Object value);

    AutoScalingGroup healthCheckType(Object value);

    AutoScalingGroup instanceId(Object value);

    AutoScalingGroup launchConfigurationName(Object value);

    AutoScalingGroup loadBalancerNames(Object... values);

    AutoScalingGroup maxSize(Object value);

    // AutoScalingGroup metricsCollection(MetricsCollection ...values);

    AutoScalingGroup minSize(Object value);

    // AutoScalingGroup notificationConfiguration(NotificationConfiguration value);

    AutoScalingGroup placementGroup(Object value);

    default AutoScalingTag tag(String key, String value) {
        AutoScalingTag tag = new AutoScalingTag(key, value);
        //node.withArray("Tags").addObject().put(key, value);
        ((ArrayNode) getProperties().withArray("Tags")).add(tag.toNode());
        return tag;
    }

    AutoScalingGroup terminationPolicies(Object... values);

    AutoScalingGroup VPCZoneIdentifier(Object... values);
}
