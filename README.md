# Power Systems on Modelica

Modelica-based tool for power system dynamic simulation.

The software enables users to transform power system networks from CIM format to Modelica using dynamic models from the iTesla Modelica power system library [iPSL](https://github.com/itesla/ipsl). 

Modules from the iTesla platform [iPST](https://github.com/itesla/ipst) are used in the project.
 
## Setup

Clone the iTesla platform repository and build it:

	mkdir ipst
	cd ipst
	git clone https://github.com/itesla/ipst.git
	mvn clean install -DskipTests
	
This will put all the iPST artifacts in the local maven repository, so they can be referenced from psm project.

Clone the psm repository and build it:

	mkdir psm
	cd psm
	git clone https://bitbucket.org/rte-france/psm.git 
	./build
	
Set the environment variable that points to the sample data:

	export PSM_DATA=...
	
Build and test from the psm folder:

	./test_