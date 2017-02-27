@ECHO OFF
ECHO.
SET PSM_DATA=./data
SET "PATH=%PATH%;.\helmflow\windows"

:Check1
IF ["%OPENMODELICAHOME%"] == [] GOTO MissingOpenModelicaHome
:Check2
IF ["%DYMOLAHOME%"] == [] GOTO MissingDymola
:Check3
IF ["%OPENMODELICAHOME%%DYMOLAHOME%"] == [] GOTO NoDynEngine
GOTO Start

:MissingOpenModelicaHome
ECHO Variable OPENMODELICAHOME is not defined
GOTO Check2

:MissingDymola
ECHO Variable DYMOLAHOME is not defined
GOTO Check3

:NoDynEngine
ECHO Neither OPENMODELICAHOME nor DYMOLAHOME are defined
ECHO Will exit
EXIT

:Start
ECHO.
ECHO OPENMODELICAHOME is %OPENMODELICAHOME%
ECHO DYMOLAHOME is %DYMOLAHOME%
ECHO "PATH is %PATH%"
ECHO.

SET PSM_OPTIONS=-Ditesla.config.dir=%PSM_DATA%/test/cfg
SET PSM_HELMFLOW_JAR=./lib/helmflow_core-2.2.5.201702061800.0.jar
SET PSM_CLASSPATH=-cp ./lib/gui-0.1-SNAPSHOT-jar-with-dependencies.jar;./lib/psm-services.jar;./lib/modelica_java.jar;%PSM_HELMFLOW_JAR%;%PSM_DATA%/test/cfg
SET PSM_MAIN=org.power_systems_modelica.psm.gui.MainApp
ECHO Java options    : %PSM_OPTIONS%
ECHO Java classpath  : %PSM_CLASSPATH%
ECHO Java main class : %PSM_MAIN%
ECHO.

java %PSM_CLASSPATH% %PSM_OPTIONS% %PSM_MAIN% 1> psm.log 2>&1
TYPE psm.log
