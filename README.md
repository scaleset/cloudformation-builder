cloudformation-builder
======================

CloudFormation-Builder is a Java 8 framework for creating AWS CloudFormation templates.

Quick Start
-----------

### Dependency

```xml
<dependency>
    <groupId>com.scaleset</groupId>
    <artifactId>cloudformation-builder</artifactId>
    <version>0.1.0-SNAPSHOT</version>
</dependency>
```

### Example

```java
    class TestModule extends Module {

        public void build() throws Exception {

            Object instanceType = option("instanceType").orElseGet(
                    () -> strParam("InstanceType", "m1.small", ns("Instance") + " instance type"));
            Object nodeCount = option("nodeCount").orElseGet(
                    () -> numParam("NodeCount", 2, "Number of elasticsearch nodes to create"));

            Object clusterName = option("clusterName").orElse("elasticsearch");

            SecurityGroup securityGroup = resource(SecurityGroup.class, "SecurityGroup")
                    .vpcId(template.ref("VpcId"))
                    .ingress(ingress -> ingress.setCidrIp(template.ref("OpenCidrIp")), "tcp", 22, 9200, 9300, range(27018, 27019));

            resource(SecurityGroupIngress.class, "SelfReferenceIngress")
                    .sourceSecurityGroupId(fnGetAtt("SecurityGroup", "GroupId"))
                    .groupId(fnGetAtt("SecurityGroup", "GroupId"))
                    .ipProtocol("tcp")
                    .port(9300);

            resource(Instance.class, "Instance")
                    .name(ns("Instance"))
                    .availabilityZone(template.ref("MyAZ"))
                    .keyName(template.ref("KeyName"))
                    .imageId(template.ref("ImageId"))
                    .instanceProfile(ref("InstanceProfile"))
                    .instanceType(instanceType)
                    .securityGroupIds(securityGroup);
        }
    }
```


### License

CloudFormat-Builder is [Apache 2.0 licensed](http://www.apache.org/licenses/LICENSE-2.0.html).
