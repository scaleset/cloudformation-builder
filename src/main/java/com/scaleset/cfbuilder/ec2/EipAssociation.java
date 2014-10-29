package com.scaleset.cfbuilder.ec2;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.scaleset.cfbuilder.annotations.Type;
import com.scaleset.cfbuilder.core.Resource;

@Type("EIPAssociation")
public interface EipAssociation {

    EipAssociation allocationId(Object value);

    EipAssociation EIP(Object value);

    EipAssociation instanceId(Object value);

    EipAssociation NetworkInterfaceId(Object value);

    EipAssociation privateIpAddress(Object value);

}
