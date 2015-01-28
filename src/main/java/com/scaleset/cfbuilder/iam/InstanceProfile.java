package com.scaleset.cfbuilder.iam;

import com.scaleset.cfbuilder.annotations.Type;

@Type("AWS::IAM::InstanceProfile")
public interface InstanceProfile {

    InstanceProfile path(Object value);

    InstanceProfile roles(Object... values);
}
