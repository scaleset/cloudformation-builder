package com.scaleset.cfbuilder.autoscaling;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.scaleset.cfbuilder.annotations.Type;
import com.scaleset.cfbuilder.core.Resource;
import com.scaleset.cfbuilder.core.Taggable;


@Type("AWS::AutoScaling::AutoScalingGroup")
public interface AutoScalingGroup extends Taggable {

    AutoScalingGroup availabilityZone(Object... values);

    AutoScalingGroup cooldown(Object value);

    AutoScalingGroup desiredCapacity(Object value);

    AutoScalingGroup healthCheckGracePeriod(Object value);

    AutoScalingGroup healthCheckType(Object value);

    AutoScalingGroup instanceId(Object value);

    AutoScalingGroup launchConfigurationName(Object value);

    AutoScalingGroup loadBalancerNames(Object... values);

    AutoScalingGroup maxSize(Object value);

    AutoScalingGroup metricsCollection(MetricsCollection... metricsCollection);

    default AutoScalingGroup metricsCollection(String granularity, String... metrics) {
        return metricsCollection(new MetricsCollection(granularity, metrics));
    }

    AutoScalingGroup minSize(Object value);

    AutoScalingGroup notificationConfiguration(NotificationConfiguration value);

    default AutoScalingGroup notificationConfiguration(String topicARN, String[] notificationTypes) {
        return notificationConfiguration(new NotificationConfiguration(topicARN, notificationTypes));
    }

    AutoScalingGroup placementGroup(Object value);

    default AutoScalingGroup tag(String key, String value, boolean propagateAtLaunch) {
        tags(new AutoScalingTag(key, value, propagateAtLaunch));
        return this;
    }

    AutoScalingGroup terminationPolicies(Object... values);

    AutoScalingGroup VPCZoneIdentifier(Object... values);
}
