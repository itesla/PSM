# Power Systems on Modelica (PSM)

Modelica-based tool for power system dynamic simulation.

The software enables users to transform power system networks from CIM format to Modelica using dynamic models from the iTesla Modelica power system library [iPSL](https://github.com/itesla/ipsl). 

Modules from [powsybl core](https://github.com/powsybl/powsybl-core) are used in the project.

Please refer to the user guide (doc/PSMuserguideV1.0.0.docx), for detailed usage information.

## Environment requirements

To build PSM you need:

  * JDK *(1.8 or greater)*
  * Maven 

To run PSM you need a modelica based simulator (either OpenModelica or Dymola). You might also need a loadflow engine (PSM has been designed to work with two alternative loadflow engines: HELM and Hades2; it is also possible to deactivate the loadflow computation).

The steps below describe the procedure to build and install PSM, with Hades2 and openmodelica, on a CentOS7 machine.

## OpenModelica + Hades2LF on CentOS7, installation scenario

#### 1 - Hades2LF 
Linux binaries for Hades Load-Flow can be downloaded from: http://www.rte.itesla-pst.org
Hades2 is assumed to be already installed in $HOME/hades2LF 

#### 2 - OpenModelica

To install OpenModelica v1.11,  on CentOS7:
( installation information for multiple platforms here: https://openmodelica.org/download/download-linux )

##### 2.1 - install OpenModelica repository and v.11 package
    sudo wget https://build.openmodelica.org/rpm/el7/omc.repo -O /etc/yum.repos.d/omc.repo
    sudo yum install openmodelica-1.11

OpenModelica will be installed in /opt/opt/openmodelica-1.11

##### 2.2 - build and install OpenModelica java integration .jar (i.e. modelica_java.jar, not included in the OpenModelica binaries distribution, nor available from any maven repositories)
    mkdir $HOME/Projects
    cd $HOME/Projects
    git clone https://github.com/OpenModelica/OMJava
    cp /opt/openmodelica-1.11/share/omc/java/antlr-3.2.jar  ./OMJava/3rdParty/antlr
    cd $HOME/Projects/OMJava
    make
    mvn install:install-file -Dfile=./modelica_java.jar -DgroupId=org.openmodelica -DartifactId=modelica_java -Dversion=1.11.0 -Dpackaging=jar

#### 3 - PSM


##### 3.1 - Clone the PSM repository
    cd $HOME/Projects
    git clone https://github.com/itesla/psm.git
    cd $HOME/Projects/psm

##### 3.2 - Build PSM, including module openmodelica-integration, and run unit tests (using maven wrapper script ```mvnw``` in project folder)

    cd $HOME/Projects/psm
    mvwm clean install -P openmodelica
   
##### 3.3 - build PSM distribution
    ./scripts/dist/prepare_dist.sh dist
    
##### 3.4 - install PSM distribution to $HOME/psm
    unzip psm-dist.zip -d $HOME/psm
    unzip psm-dist-validation_data.zip -d $HOME/psm
     
##### 3.5 - install Hades2 binaries&jars to the PSM installation
    cp -r $HOME/hades2LF/* $HOME/psm/hades2LF
    cp $HOME/psm/hades2LF/share/java/*.jar $HOME/psm/lib


##### 3.6 - Set the environment variable OPENMODELICA (needed to run PSM with OpenModelica): 
    export OPENMODELICAHOME=/opt/openmodelica-1.11
    
(appending the export statement to the $HOME/.bashrc file could be a good idea)    

#### Execute PSM (GUI version)
    cd $HOME/psm
    ./psmgui
    
#### Execute PSM (command line version)
    cd $HOME/psm
    ./psm


## Additional information

### HELM Flow integration

To use the HELM loadflow engine, HELM Flow integration libraries should be installed in the local maven repository from given jars.

See script ```install_helmflow_integration_libs```.

```helmflow``` binary must be in the current ```PATH```. HELM loadflow integration libraries should also be copied to $HOME/psm/lib.

### Dymola integration

The Dymola interface private library must be installed in the local maven repository, to be able to build PSM with Dymola support 

See script ```install_dymola_libs```

A Web Service connection is used to connect the PSM tool to Dymola service (exposed by dymola-integration-service module). If this service is running in an external machine, some changes should be made in the client side (module ```dymola-integration```).

In the file ```dymservice.wsdl```, the address location should point to the IP of the machine where the Dymola service is running.

	<service name="SimulatorServerImplService">
		<port name="SimulatorServerImplPort" binding="tns:SimulatorServerImplPortBinding">
			<soap:address location="http://<IP of the machine where Dymola service is running>:8888/dymservice"></soap:address>
		</port>
	</service>

Dymola related modules are by default disabled (Dymola private libraries are required). To enable them, uncomment these modules, in pom.xml
    dymola-integration-service
    dymola-integration-service-wardep
	

### Tests

Currently some tests are skipped in the maven build wrapper because not all tools are expected to be available (e.g. Dymola and OpenModelica). Tests in `dymola-integration` and `modelica-parser` modules, and individual tests for Loadflow are skipped using:

	-DskipTest.regex="(dymola-integration|openmodelica-integration)"
	-Dtest='*,!LoadFlowTest'

These tests can be activated locally just removing these options from maven wrapper script.

	
### Eclipse

Eclipse projects can be recreated by importing checkout folders for PSM as maven projects.

If eclipse build fails with the error:

	Plugin execution not covered by lifecycle configuration

For the maven helper build plugin that allows to bypass tests for some modules, and that does not affect the maven build from the command line, one way to fix it is to:

  - Inside eclipse, go to Window > Preferences > Maven > Errors/Warnings.
  - Select "Plugin execution not covered by lifecycle configuration". 
  - Select Ignore / Warning as you wish.

This is a clean way to get rid of the error, as no modification is needed in the parent `pom.xml`.

Other solutions are possible: check http://stackoverflow.com/questions/6352208/how-to-solve-plugin-execution-not-covered-by-lifecycle-configuration-for-sprin

