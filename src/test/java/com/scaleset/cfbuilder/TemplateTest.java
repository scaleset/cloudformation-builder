package com.scaleset.cfbuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.scaleset.cfbuilder.core.Module;
import com.scaleset.cfbuilder.core.Template;
import com.scaleset.cfbuilder.ec2.Instance;
import com.scaleset.cfbuilder.ec2.SecurityGroup;
import com.scaleset.cfbuilder.ec2.SecurityGroupIngress;
import com.scaleset.cfbuilder.core.CloudFormationJsonModule;
import org.junit.Assert;
import org.junit.Test;

import static com.scaleset.cfbuilder.ec2.SecurityGroup.PortRange.range;

public class TemplateTest extends Assert {

    @Test
    public void simpleTest() throws Exception {
        Template t = new Template();
        new TestModule().id("Test").template(t).build();
        new TestModule().id("Test2").template(t).build();

        System.err.println(t.toString());

        ObjectMapper mapper = new ObjectMapper().registerModule(new CloudFormationJsonModule().scanTypes());
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.PASCAL_CASE_TO_CAMEL_CASE);

        Template template = mapper.readValue(t.toString(), Template.class);
        assertNotNull(template);
        System.err.println(template.toString());

    }

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

}
