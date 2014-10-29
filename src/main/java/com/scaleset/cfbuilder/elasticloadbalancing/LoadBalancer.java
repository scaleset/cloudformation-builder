package com.scaleset.cfbuilder.elasticloadbalancing;

import com.scaleset.cfbuilder.annotations.Type;
import com.scaleset.cfbuilder.core.Resource;

@Type("AWS::ElasticLoadBalancing::LoadBalancer")
public interface LoadBalancer extends Resource {

    LoadBalancer accessLoggingPolicy(Object value);

    LoadBalancer appCookieStickinessPolicy(Object... values);

    LoadBalancer availabilityZones(Object... values);
}
