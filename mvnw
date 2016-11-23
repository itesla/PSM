#!/bin/bash
# Maven wrapper for Travis integration

export PSM_DATA=/home/travis/build/iTesla-RTE/psm/data
export SKIP_TESTS="(dymola-integration|openmodelica-integration)"

mvn -DskipTest.regex=${SKIP_TESTS} -Ditesla.config.dir=${PSM_DATA}/test/cfg $*
