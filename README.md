# Power Systems on Modelica

Modelica-based tool for power system dynamic simulation.

The software enables users to transform power system networks from CIM format to Modelica using dynamic models from the iTesla Modelica power system library [iPSL](https://github.com/itesla/ipsl). 

Modules from the iTesla platform [iPST](https://github.com/itesla/ipst) are used in the project.
 
## Setup

Clone the iTesla platform repository and build it:

	git clone https://github.com/itesla/ipst.git
	cd ipst
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

