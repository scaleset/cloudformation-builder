package com.scaleset.cfbuilder.ec2;

import com.scaleset.cfbuilder.annotations.Type;
import com.scaleset.cfbuilder.core.Resource;

@Type("AWS::EC2::Subnet")
public interface Subnet extends Resource {

    Subnet availabilityZone(String value);

    Subnet cidrBlock(String value);

    Subnet vpcId(String value);
}
