package com.scaleset.cfbuilder.iam;

import com.scaleset.cfbuilder.annotations.Type;
import com.scaleset.cfbuilder.core.Resource;

@Type("AWS::IAM::Policy")
public interface Policy extends Resource {

    Policy groups(Object... groups);

    Policy policyDocument(Object value);

    Policy policyName(String value);

    Policy roles(Object... roles);

    Policy users(Object... users);
}
