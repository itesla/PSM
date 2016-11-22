# Maven wrapper for Travis integration
mvn -DskipTest.regex="(dymola-integration|openmodelica-integration)" -Ditesla.config.dir=data/test/cfg $*
