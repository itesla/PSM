#!/bin/bash
# Maven wrapper for Travis integration

export PSM_DATA=/home/travis/build/iTesla-RTE/psm/data
#export PSM_DATA=/Users/zamarrenolm/scratch/RTE_Modelica_tool/code/psm/data

mvn -DskipTest.regex="(dymola-integration|openmodelica-integration)" \
	-DfailIfNoTests=false \
	-Dtest='*,!LoadFlowTest' \
	-Ditesla.config.dir=${PSM_DATA}/test/cfg \
	$*
