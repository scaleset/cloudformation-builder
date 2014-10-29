package com.scaleset.cfbuilder.iam;

import com.scaleset.cfbuilder.annotations.Type;
import com.scaleset.cfbuilder.core.Resource;

@Type("AWS::IAM::Role")
public interface Role extends Resource {

    Role path(Object value);

    Role assumeRolePolicyDocument(Object value);

    Role policies(Object... policies);
}
