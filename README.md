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

Tests for a set modules can be skipped from the maven command. For example to skip tests in `dymola-integration` and `modelica-parser` modules:

	mvn clean test -DskipTest.regex="(dymola-integration|modelica-parser)"

### Hades2 integration

Hades2 integration libraries are expected in repository:

	<repository>
		<id>itesla-rte</id>
		<url>https://ns311581.ovh.net/nexus/content/repositories/thirdparty</url>
	</repository>

If not available, it can installed in the local maven repository from given jars:

	FILE_PATH=adn-export-0.1.jar
	GROUP=com.rte_france.itesla
	ARTIFACT=adn-export
	VERSION=0.1
	mvn install:install-file -Dfile=$FILE_PATH -DgroupId=$GROUP -DartifactId=$ARTIFACT -Dversion=$VERSION -Dpackaging=jar

	FILE_PATH=hades2-integration-0.1.jar
	GROUP=com.rte_france.itesla
	ARTIFACT=hades2-integration
	VERSION=0.1
	mvn install:install-file -Dfile=$FILE_PATH -DgroupId=$GROUP -DartifactId=$ARTIFACT -Dversion=$VERSION -Dpackaging=jar

### HELM Flow integration

HELM Flow integration libraries should be installed in the local maven repository from given jars:

	FILE_PATH=helmflow_core-2.2.4.201606211030.0.jar
	GROUP=com.elequant.helmflow
	ARTIFACT=helmflow-core
	VERSION=2.2.4
	mvn install:install-file -Dfile=$FILE_PATH -DgroupId=$GROUP -DartifactId=$ARTIFACT -Dversion=$VERSION -Dpackaging=jar

	FILE_PATH=helmflow-api-0.1-SNAPSHOT.jar
	GROUP=com.elequant.helmflow
	ARTIFACT=helmflow-api
	VERSION=0.1-SNAPSHOT
	mvn install:install-file -Dfile=$FILE_PATH -DgroupId=$GROUP -DartifactId=$ARTIFACT -Dversion=$VERSION -Dpackaging=jar

	FILE_PATH=helmflow-api-impl-0.1-SNAPSHOT.jar
	GROUP=com.elequant.helmflow
	ARTIFACT=helmflow-api-impl
	VERSION=0.1-SNAPSHOT
	mvn install:install-file -Dfile=$FILE_PATH -DgroupId=$GROUP -DartifactId=$ARTIFACT -Dversion=$VERSION -Dpackaging=jar

	FILE_PATH=helmflow-ipst-integration-0.1-SNAPSHOT.jar
	GROUP=com.elequant.helmflow
	ARTIFACT=helmflow-ipst-integration
	VERSION=0.1-SNAPSHOT
	mvn install:install-file -Dfile=$FILE_PATH -DgroupId=$GROUP -DartifactId=$ARTIFACT -Dversion=$VERSION -Dpackaging=jar

### Dymola integration

Dymola interface library must be installed in the local maven repository to be able to build psm:

	FILE_PATH=dymola_interface.jar
	GROUP=com.dassault_systemes.dymola
	ARTIFACT=dymola_interface
	VERSION=2016
	mvn install:install-file -Dfile=$FILE_PATH -DgroupId=$GROUP -DartifactId=$ARTIFACT -Dversion=$VERSION -Dpackaging=jar

A Web Service connection is used to connect the psm tool to Dymola service. If this service is running in an external machine, some changes should be made in the client side (module dymola-integration).
In the file dymservice.wsdl, the address location should point to the IP of the machine where the Dymola service is running.

	<service name="SimulatorServerImplService">
		<port name="SimulatorServerImplPort" binding="tns:SimulatorServerImplPortBinding">
			<soap:address location="http://<IP of the machine where Dymola service is running>:8888/dymservice"></soap:address>
		</port>
	</service>
	
### Eclipse

Eclipse projects can be recreated by importing checkout folders for psm as maven projects.

If eclipse build fails with the error:

	Plugin execution not covered by lifecycle configuration

For the maven helper build plugin that allows to bypass tests for some modules, and that does not affect the maven build from the command line, one way to fix it is to:

  - Inside eclipse, go to Window > Preferences > Maven > Errors/Warnings.
  - Select "Plugin execution not covered by lifecycle configuration". 
  - Select Ignore / Warning as you wish.

This is a clean way to get rid of the error, as no modification is needed in the parent `pom.xml`.

Another solutions are possible: check http://stackoverflow.com/questions/6352208/how-to-solve-plugin-execution-not-covered-by-lifecycle-configuration-for-sprin
