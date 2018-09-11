#!/bin/bash
#
# Copyright (c) 2017 - 2018, RTE (http://www.rte-france.com)
# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at http://mozilla.org/MPL/2.0/.
#
# @author Luma Zamarreño <zamarrenolm at aia.es>

# Maven wrapper for Travis integration

export MAVEN_FLAGS=

projectDir=$(dirname $(readlink -f $0))

if [ -d "/home/travis/build/iTesla-RTE/psm" ]; then
	export PSM_DATA=/home/travis/build/iTesla-RTE/psm/data
fi
if [ "$PSM_DATA" = "" ];
then
	echo ""
	echo "missing PSM_DATA variable,"
	echo "will assume current data folder " $(dirname $(readlink -f $0))
	export PSM_DATA=${projectDir}/data
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
	-Ditools.config.dir=${PSM_DATA}/cfg \
	$*
