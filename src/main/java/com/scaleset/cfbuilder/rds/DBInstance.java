package com.scaleset.cfbuilder.rds;

import com.scaleset.cfbuilder.annotations.Type;
import com.scaleset.cfbuilder.core.Taggable;
import com.scaleset.cfbuilder.ec2.SecurityGroup;

@Type("AWS::RDS::DBInstance")
public interface DBInstance extends Taggable {

    DBInstance engine(String engine);

    DBInstance dBName(String dBName);

    DBInstance masterUsername(String masterUsername);

    DBInstance masterUserPassword(String masterUserPassword);

    DBInstance dBInstanceClass(String dBInstanceClass);

    DBInstance allocatedStorage(int allocatedStorage);

    DBInstance storageType(String storageType);

    DBInstance vPCSecurityGroups(Object... vPCSecurityGroup);

    default DBInstance name(String name) {
        tag("Name", name);
        return this;
    }

}
