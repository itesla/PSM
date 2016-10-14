# Power Systems on Modelica

Modelica-based tool for power system dynamic simulation.

The software enables users to transform power system networks from CIM format to Modelica using dynamic models from the iTesla Modelica power system library [iPSL](https://github.com/itesla/ipsl). 

Modules from the iTesla platform [iPST](https://github.com/itesla/ipst) are used in the project.
 
## Setup

Clone the iTesla platform repository and build it:

	git clone https://github.com/itesla/ipst.git
	cd ipst
	
Before compiling the iPST modules, apply the following change:

	diff --git a/iidm-network-api/src/main/java/eu/itesla_project/iidm/network/EquipmentTopologyVisitor.java b/iidm-network-api/src/main/java/eu/itesla_project/iidm/network/EquipmentTopologyVisitor.java
	index 7f1422d..17ebf04 100644
	--- a/iidm-network-api/src/main/java/eu/itesla_project/iidm/network/EquipmentTopologyVisitor.java
	+++ b/iidm-network-api/src/main/java/eu/itesla_project/iidm/network/EquipmentTopologyVisitor.java
	@@ -12,7 +12,7 @@ package eu.itesla_project.iidm.network;
	  */
	 public abstract class EquipmentTopologyVisitor extends AbstractTopologyVisitor {

	-    public abstract void visitEquipment(Connectable eq);
	+    public abstract void visitEquipment(Connectable<?> eq);

	     @Override
	     public void visitLine(Line line, TwoTerminalsConnectable.Side side) {

Then build the iPST
		 	
	mvn clean install -DskipTests
	
This will put all the iPST artifacts in the local maven repository, so they can be referenced from psm project.

Clone the psm repository and build it:

	git clone https://bitbucket.org/rte-france/psm.git 
	cd psm
	./build
	
Set the environment variable that points to the sample data:

	export PSM_DATA=<folder where you have cloned>/data
	
To validate the setup, build and test from the psm folder:

	mvn clean install -Ditesla.config.dir=$PSM_DATA/test/cfg

### Hades2 integration

Hades2 integration libraries are expected in repository:

	<repository>
		<id>itesla-rte</id>
		<url>https://ns311581.ovh.net/nexus/content/repositories/thirdparty</url>
	</repository>

If not available, it can installed in the local maven repository from given jars:

	mvn install:install-file -Dfile=adn-export-0.1.jar -DgroupId=com.rte_france.itesla -DartifactId=adn-export -Dversion=0.1 -Dpackaging=jar
	mvn install:install-file -Dfile=hades2-integration-0.1.jar -DgroupId=com.rte_france.itesla -DartifactId=hades2-integration -Dversion=0.1 -Dpackaging=jar

### Dymola integration

Set the environment variable that points to the Dymola installation directory:

	export DYMOLAHOME=<Dymola installation folder>

Dymola libraries are expected in that installation directory, as you can see in the pom.xml (module dymola-integration-service):

	<!-- explicit private dependency, jar from Dymola installation -->
	<dependency>
		<groupId>com.dassault_systemes.dymola</groupId>
		<artifactId>dymola_interface</artifactId>
		<version>2016</version>
		<scope>system</scope>
		<systemPath>${DYMOLAHOME}/Modelica/Library/java_interface/dymola_interface.jar</systemPath>
	</dependency>
	<!-- explicit dependency, jar from Dymola installation -->
	<dependency>
		<groupId>com.googlecode.json-simple</groupId>
		<artifactId>json-simple</artifactId>
		<version>1.1.1</version>
		<scope>system</scope>
		<systemPath>${DYMOLAHOME}/Modelica/Library/java_interface/json-simple-1.1.1.jar</systemPath>
	</dependency>

A Web Service connection is used to connect the psm tool to Dymola service. If this service is running in an external machine, some changes should be made in the client side (module dymola-integration).
In the file dymservice.wsdl, the address location should point to the IP of the machine where the Dymola service is running.

	<service name="SimulatorServerImplService">
		<port name="SimulatorServerImplPort" binding="tns:SimulatorServerImplPortBinding">
			<soap:address location="http://<IP of the machine where Dymola service is running>:8888/dymservice"></soap:address>
		</port>
	</service>
	
