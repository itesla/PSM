#!/bin/bash

PSM_ZIP_FILE=$1
BUILD=$2
DIST_TMP_FOLDER=dist_tmp

echo ""
echo "Creating ${PSM_ZIP_FILE}"
echo "Using temporal folder ${DIST_TMP_FOLDER}"
if [[ "${BUILD}" == "build" ]];
then
	echo "Performing build before preparing packaging"
else
	echo "Without building the project"
fi

echo "    Preparing temporal folder"
rm -rf $DIST_TMP_FOLDER
mkdir -p $DIST_TMP_FOLDER
mkdir -p $DIST_TMP_FOLDER/data
mkdir -p $DIST_TMP_FOLDER/hades2LF
mkdir -p $DIST_TMP_FOLDER/helmflow/linux
mkdir -p $DIST_TMP_FOLDER/helmflow/windows
mkdir -p $DIST_TMP_FOLDER/lib

if [[ "${BUILD}" == "build" ]];
then
	echo "    Building the project"
	mvnw clean install -DskipTests &> ${DIST_TMP_FOLDER}/build.log

	echo "    Building jar with dependencies in GUI module"
	mvnw -offline compile assembly:single --projects gui &> ${DIST_TMP_FOLDER}/gui.log
	echo "    Building jar with dependencies in DYMOLA-INTEGRATION-SERVICE module"
	mvnw -offline compile assembly:single --projects dymola-integration-service &> ${DIST_TMP_FOLDER}/dymola.log
fi

echo "    Preparing data files"
rsync -avP --exclude='tmp/*' --exclude='test_private/*' --exclude='kk*' --exclude='.*' data/* $DIST_TMP_FOLDER/data/. &> ${DIST_TMP_FOLDER}/data.log
echo "    Override configuration files with distribution-specific ones"
rsync -avP scripts/dist/cfg/* $DIST_TMP_FOLDER/data/test/cfg/. &> ${DIST_TMP_FOLDER}/data.log

echo "    Copying Hades2"
rsync -avP ../hades/hades2LF/* $DIST_TMP_FOLDER/hades2LF/. &> ${DIST_TMP_FOLDER}/hades.log

echo "    Copying HELM FLow"
rsync -avP ../helmflow/linux_binaries/* $DIST_TMP_FOLDER/helmflow/linux/. &> ${DIST_TMP_FOLDER}/helmflow.log
rsync -avP ../helmflow/windows_binaries/* $DIST_TMP_FOLDER/helmflow/windows/. &> ${DIST_TMP_FOLDER}/helmflow.log

echo "    Preparing additional Java libraries"
rsync -avP ../openmodelica/modelica_java.jar $DIST_TMP_FOLDER/lib/. &> ${DIST_TMP_FOLDER}/jars.log 2>&1
rsync -avP ../helmflow/*.jar $DIST_TMP_FOLDER/lib/. >> ${DIST_TMP_FOLDER}/jars.log 2>&1
rsync -avP gui/target/gui*with*depend*jar $DIST_TMP_FOLDER/lib/. >> ${DIST_TMP_FOLDER}/jars.log 2>&1
rsync -avP dymola-integration-service/target/dymola-integration-service*with*depend*jar $DIST_TMP_FOLDER/lib/. >> ${DIST_TMP_FOLDER}/jars.log 2>&1
scripts/dist/build_psm_services_jar.sh psm-services.jar >> ${DIST_TMP_FOLDER}/jars.log 2>&1

mv psm-services.jar $DIST_TMP_FOLDER/lib/.

echo "    Preparing scripts"
rsync -avP scripts/dist/psmgui $DIST_TMP_FOLDER/. &> ${DIST_TMP_FOLDER}/scripts.log
rsync -avP scripts/dist/dymola_integration_service.cmd $DIST_TMP_FOLDER/. >> ${DIST_TMP_FOLDER}/scripts.log 2>&1

echo "    Creating distribution package"
BAK_CD=$(pwd)
cd $DIST_TMP_FOLDER && zip -r psm.zip * --exclude \*.log &> zip.log
cd $BAK_CD
mv $DIST_TMP_FOLDER/psm.zip $PSM_ZIP_FILE

echo "    Delete temporal folder"
#rm -rf $DIST_TMP_FOLDER

echo ""
