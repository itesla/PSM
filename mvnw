#!/bin/bash
# Maven wrapper for Travis integration

export MAVEN_FLAGS=

if [ -d "/home/travis/build/iTesla-RTE/psm" ]; then
	export PSM_DATA=/home/travis/build/iTesla-RTE/psm/data
fi
if [ -d "/Users/zamarrenolm/scratch/RTE_Modelica_tool/code/psm" ]; then
	export PSM_DATA=/Users/zamarrenolm/scratch/RTE_Modelica_tool/code/psm/data
	# Options for Java Virtual Machine started by Maven
	# Optimize start JIT compilation in Java virtual machine for short runs 
	export MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"
	# Options for Maven 
	# Use two threads per core
	export MAVEN_FLAGS="--threads 2C"
fi
if [ "$PSM_DATA" = "" ];
then
	echo ""
	echo "missing PSM_DATA variable"
	echo "will assume current data folder"
	export PSM_DATA=data
fi

#export LF_TESTS=
export LF_TESTS=,!LoadFlowTest
export GUI_TESTS=,!CasesOverviewControllerTest,!DdrsOverviewControllerTest,!WorkflowStatusControllerTest,!ConversionNewControllerTest,!SimulationNewControllerTest,!SimulationDetailControllerTest,!CompareLoadflowsNewControllerTest,!CompareLoadflowsDetailControllerTest,!WorkflowTest
export VAL_TESTS=,!ValidationTest

mkdir -p $PSM_DATA/tmp

mvn java-formatter:format

mvn ${MAVEN_FLAGS} \
	-DskipTest.regex="(dymola-integration|openmodelica-integration)" \
	-DfailIfNoTests=false \
	-Dtest='*'${LF_TESTS}${GUI_TESTS}${VAL_TESTS} \
	-Ditesla.config.dir=${PSM_DATA}/test/cfg \
	$*
