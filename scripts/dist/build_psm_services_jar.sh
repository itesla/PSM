#!/bin/bash

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
