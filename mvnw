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

mvn ${MAVEN_FLAGS} \
	-DskipTest.regex="(dymola-integration|openmodelica-integration)" \
	-DfailIfNoTests=false \
	-Dtest='*,!LoadFlowTest' \
	-Ditesla.config.dir=${PSM_DATA}/test/cfg \
	$*
