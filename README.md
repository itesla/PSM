[![Build Status](https://travis-ci.com/iTesla-RTE/psm.svg?token=9R2QPTT2RxGGn7SCGoBz&branch=master)](https://travis-ci.com/iTesla-RTE/psm)
[![Coverage Status](https://coveralls.io/repos/github/iTesla-RTE/psm/badge.svg?branch=master&t=FfZvS7)](https://coveralls.io/github/iTesla-RTE/psm?branch=master)

# Power Systems on Modelica

Modelica-based tool for power system dynamic simulation.

The software enables users to transform power system networks from CIM format to Modelica using dynamic models from the iTesla Modelica power system library [iPSL](https://github.com/itesla/ipsl). 

Modules from the iTesla platform [iPST core](https://github.com/itesla/ipst-core) and [iPST](https://github.com/itesla/ipst) are used in the project.
 
## Setup

Clone the iTesla platform repositories and build them:

	git clone https://github.com/itesla/ipst-core.git
	cd ipst-core
	mvn clean install -DskipTests
	
	git clone https://github.com/itesla/ipst.git
	cd ipst
	mvn clean install -DskipTests
	
This will put all the iPST artifacts in the local maven repository, so they can be referenced from psm project.

Clone the psm repository:

	git clone https://github.com/iTesla-RTE/psm
	
Set the environment variable that points to the sample data:

	export PSM_DATA=<folder where you have cloned>/data

Build psm and run unit tests (use maven wrapper script ```mvnw``` in project folder):

	cd psm
	./mvnw clean install

Currently some tests are skipped in the maven build wrapper because not all tools are yet available in the Travis integration server. Tests in `dymola-integration` and `modelica-parser` modules, and individual tests for Loadflow are skipped using:

	-DskipTest.regex="(dymola-integration|openmodelica-integration)"
	-Dtest='*,!LoadFlowTest'

These tests can be activated locally just removing these options from maven wrapper script.

### Hades2 integration

Hades2 integration libraries are expected in repository:

	<repository>
		<id>itesla-rte</id>
		<url>https://ns311581.ovh.net/nexus/content/repositories/thirdparty</url>
	</repository>

If not available, it can installed in the local maven repository from given jars (see script ```install_hades_integration_libs````)

### HELM Flow integration

HELM Flow integration libraries should be installed in the local maven repository from given jars.

See script ```install_helmflow_integration_libs```

### Dymola integration

Dymola interface library must be installed in the local maven repository to be able to build psm.

See script ```install_dymola_libs```

A Web Service connection is used to connect the psm tool to Dymola service. If this service is running in an external machine, some changes should be made in the client side (module ```dymola-integration```).

In the file ```dymservice.wsdl```, the address location should point to the IP of the machine where the Dymola service is running.

	<service name="SimulatorServerImplService">
		<port name="SimulatorServerImplPort" binding="tns:SimulatorServerImplPortBinding">
			<soap:address location="http://<IP of the machine where Dymola service is running>:8888/dymservice"></soap:address>
		</port>
	</service>
	
### OpenModelica integration

OpenModelica library must be installed in the local maven repository to be able to build psm.

See script ```install_openmodelica_libs```

Open Modelica installation folder should be defined in the corresponding environment variable for the user:

	export OPENMODELICAHOME=<Open Modelica installation>/bin

### Eclipse

Eclipse projects can be recreated by importing checkout folders for psm as maven projects.

If eclipse build fails with the error:

	Plugin execution not covered by lifecycle configuration

For the maven helper build plugin that allows to bypass tests for some modules, and that does not affect the maven build from the command line, one way to fix it is to:

  - Inside eclipse, go to Window > Preferences > Maven > Errors/Warnings.
  - Select "Plugin execution not covered by lifecycle configuration". 
  - Select Ignore / Warning as you wish.

This is a clean way to get rid of the error, as no modification is needed in the parent `pom.xml`.

Other solutions are possible: check http://stackoverflow.com/questions/6352208/how-to-solve-plugin-execution-not-covered-by-lifecycle-configuration-for-sprin
