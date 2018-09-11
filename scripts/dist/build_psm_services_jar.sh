#!/bin/bash
#
# Copyright (c) 2017 - 2018, RTE (http://www.rte-france.com)
# This Source Code Form is subject to the terms of the Mozilla Public
# License, v. 2.0. If a copy of the MPL was not distributed with this
# file, You can obtain one at http://mozilla.org/MPL/2.0/.
#
# @author Luma Zamarreño <zamarrenolm at aia.es>
#


JAR_FILE=$1
JAR_TMP_FOLDER="${JAR_FILE}_tmp"

rm -f $JAR_FILE
rm -rf $JAR_TMP_FOLDER
mkdir -p ${JAR_TMP_FOLDER}/META-INF/services
for p in $(find . -path "*META-INF/services*" -type f)
do
	echo $p
	cat $p >> ${JAR_TMP_FOLDER}/META-INF/services/$(basename $p)
done
jar cf $JAR_FILE -C ${JAR_TMP_FOLDER} META-INF
rm -rf $JAR_TMP_FOLDER
