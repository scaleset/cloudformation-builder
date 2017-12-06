package com.scaleset.cfbuilder.iam;

import com.scaleset.cfbuilder.annotations.Type;
import com.scaleset.cfbuilder.core.Resource;

@Type("AWS::IAM::User")
public interface User extends Resource {

    User path(Object value);

    User groups(Object... value);

    User loginProfile(Object profile);

    User policies(Object... policies);
}
