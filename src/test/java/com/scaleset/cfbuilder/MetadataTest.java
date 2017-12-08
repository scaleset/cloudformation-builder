package com.scaleset.cfbuilder;

import com.scaleset.cfbuilder.core.Fn;
import com.scaleset.cfbuilder.core.Module;
import com.scaleset.cfbuilder.core.Template;
import com.scaleset.cfbuilder.ec2.Instance;
import com.scaleset.cfbuilder.ec2.SecurityGroup;
import com.scaleset.cfbuilder.ec2.UserData;
import com.scaleset.cfbuilder.ec2.metadata.*;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MetadataTest {
    @Test
    public void metadataTest() throws Exception {
        Template lampTemplate = new Template();
        new MetadataModule().id("").template(lampTemplate).build();

        assertNotNull(lampTemplate);
        System.err.println(lampTemplate.toString(true));
    }

    class MetadataModule extends Module {
        private static final String KEYNAME_DESCRIPTION = "Name of an existing EC2 KeyPair to enable SSH access to the instances";
        private static final String KEYNAME_TYPE = "AWS::EC2::KeyPair::KeyName";
        private static final String KEYNAME_CONSTRAINT_DESCRIPTION = "must be the name of an existing EC2 KeyPair.";

        private static final String CFNINIT_CONFIGSET = "InstallAndRun";
        private static final String CFNINIT_CONFIG_INSTALL = "Install";
        private static final String CFNINIT_CONFIG_CONFIGURE = "Configure";

        public void build() throws Exception {

            Object keyName = option("KeyName").orElseGet(
                    () -> strParam("KeyName").type(KEYNAME_TYPE).description(KEYNAME_DESCRIPTION).constraintDescription(KEYNAME_CONSTRAINT_DESCRIPTION));

            Object cidrIp = "0.0.0.0/0";
            Object keyNameVar = template.ref("KeyName");

            CFNPackage cfnPackage = new CFNPackage("apt")
                    .addPackage("apache2")
                    .addPackage("php")
                    .addPackage("libapache2-mod-php7.0");

            CFNFile indexFile = new CFNFile("/var/www/html/index.php")
                    .setContent("<php?\nphpinfo()\n?>")
                    .setMode("000600")
                    .setOwner("www-data")
                    .setGroup("www-data");

            CFNService service = new CFNService().addService(new SimpleService("apache2")
                    .setEnabled(true)
                    .setEnsureRunning(true)
                    .addPackage("apt", "libapache2-mod-php7.0"));

            Config install = new Config(CFNINIT_CONFIG_INSTALL)
                    .putFile(indexFile)
                    .putPackage(cfnPackage)
                    .putService(service);

            CFNCommand configure_mysql = new CFNCommand("configure_myphp", "sh /tmp/configure_myphpapp.sh")
                    .addEnv("database_name", "mydatabase");
            Config configure = new Config(CFNINIT_CONFIG_CONFIGURE).putCommand(configure_mysql);
            CFNInit cfnInit = new CFNInit(CFNINIT_CONFIGSET)
                    .addConfig(CFNINIT_CONFIGSET, install)
                    .addConfig(CFNINIT_CONFIGSET, configure);

            SecurityGroup webServerSecurityGroup = resource(SecurityGroup.class, "WebServerSecurityGroup")
                    .groupDescription("Enable ports 80 and 22")
                    .ingress(ingress -> ingress.cidrIp(cidrIp), "tcp", 80, 22);

            Object groupId = webServerSecurityGroup.fnGetAtt("GroupId");

            Instance webServerInstance = resource(Instance.class, "WebServerInstance")
                    .addCFNInit(cfnInit)
                    .imageId("ami-0def3275")
                    .instanceType("t2.micro")
                    .securityGroupIds(webServerSecurityGroup)
                    .keyName(keyNameVar)
                    .userData(new UserData(new Fn("Join", "", "eins", "zwei")));


            Object publicDNSName = webServerInstance.fnGetAtt("PublicDnsName");

            output("websiteURL", publicDNSName, "URL for newly created LAMP stack");
        }
    }
}
