package com.scaleset.cfbuilder.ec2;

import com.scaleset.cfbuilder.annotations.Type;
import com.scaleset.cfbuilder.core.Resource;

@Type("AWS::EC2::EIP")
public interface Eip extends Resource {

    Eip instanceId(Object value);

    Eip domain(Object value);
}
