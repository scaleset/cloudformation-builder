package com.scaleset.cfbuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.scaleset.cfbuilder.core.CloudFormationJsonModule;
import com.scaleset.cfbuilder.core.Module;
import com.scaleset.cfbuilder.core.Template;
import com.scaleset.cfbuilder.ec2.Instance;
import com.scaleset.cfbuilder.ec2.SecurityGroup;
import com.scaleset.cfbuilder.ec2.SecurityGroupIngress;
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
            //Object groupId = fnGetAtt("SecurityGroup", "GroupId");

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

}
