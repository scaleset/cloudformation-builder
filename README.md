cloudformation-builder
======================

CloudFormation-Builder is a Java 8 DSL for creating AWS CloudFormation templates.

Quick Start
-----------

### Dependency

```xml
<dependency>
    <groupId>com.scaleset</groupId>
    <artifactId>cloudformation-builder</artifactId>
    <version>0.2.0</version>
</dependency>
```

### Example

```java
    class TestModule extends Module {

        public void build() throws Exception {

            Object instanceType = option("instanceType").orElseGet(
                    () -> strParam("InstanceType").defaultValue("m1.small").description(ns("Instance") + " instance type"));
            Object nodeCount = option("nodeCount").orElseGet(
                    () -> numParam("NodeCount").defaultValue(2).description("Number of elasticsearch nodes to create"));

            Object clusterName = option("clusterName").orElse("elasticsearch");
            Object cidrIp = template.ref("OpenCidrIp");
            Object keyName = template.ref("KeyName");
            Object imageId = template.ref("ImageId");
            Object az = template.ref("MyAZ");
            Object instanceProfile = ref("InstanceProfile");
            Object vpcId = template.ref("VpcId");

            SecurityGroup securityGroup = resource(SecurityGroup.class, "SecurityGroup")
                    .vpcId(vpcId)
                    .ingress(ingress -> ingress.cidrIp(cidrIp), "tcp", 22, 9200, 9300, range(27018, 27019));

            Object groupId = securityGroup.fnGetAtt("GroupId");

            resource(SecurityGroupIngress.class, "SelfReferenceIngress")
                    .sourceSecurityGroupId(groupId)
                    .groupId(groupId)
                    .ipProtocol("tcp")
                    .port(9300);

            resource(Instance.class, "Instance")
                    .name(ns("Instance"))
                    .availabilityZone(az)
                    .keyName(keyName)
                    .imageId(imageId)
                    .instanceProfile(instanceProfile)
                    .instanceType(instanceType)
                    .securityGroupIds(securityGroup);
        }
    }

```


### License

CloudFormation-Builder is licensed under the [Apache License 2.0](LICENSE).
