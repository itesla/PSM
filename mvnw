#!/bin/bash
# Maven wrapper for Travis integration

if [ -d "/home/travis/build/iTesla-RTE/psm" ]; then
	export PSM_DATA=/home/travis/build/iTesla-RTE/psm/data
fi
if [ -d "/Users/zamarrenolm/scratch/RTE_Modelica_tool/code/psm" ]; then
	export PSM_DATA=/Users/zamarrenolm/scratch/RTE_Modelica_tool/code/psm/data
fi

mvn -DskipTest.regex="(dymola-integration|openmodelica-integration)" \
	-DfailIfNoTests=false \
	-Dtest='*,!LoadFlowTest' \
	-Ditesla.config.dir=${PSM_DATA}/test/cfg \
	$*
