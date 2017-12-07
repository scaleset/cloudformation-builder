package com.scaleset.cfbuilder.ec2;

import com.scaleset.cfbuilder.annotations.Type;
import com.scaleset.cfbuilder.core.Taggable;
import com.scaleset.cfbuilder.ec2.metadata.CFNInit;

@Type("AWS::EC2::Instance")
public interface Instance extends Taggable {

    Instance availabilityZone(Object... values);

    Instance instanceType(Object value);

    Instance imageId(Object value);

    Instance instanceProfile(Object value);

    Instance keyName(Object value);

    Instance securityGroupIds(Object... values);

    Instance subnetId(Object subnetId);

    Instance userData(Object userData);

    // non property additions


    default Instance name(String name) {
        tag("Name", name);
        return this;
    }

    Instance addCFNInit(CFNInit cfnInit);
}
