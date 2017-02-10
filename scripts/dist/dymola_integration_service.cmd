@echo OFF

set CPSEP=;
set DYMOLA_PATH=

if "%DYMOLAHOME%"=="" GOTO NODYMOLAHOME
set DYMOLA_PATH=%DYMOLAHOME%\Modelica\Library\java_interface\dymola_interface.jar
set JSON_PATH=%DYMOLAHOME%\Modelica\Library\java_interface\json-simple-1.1.1.jar

echo "Using Dymola from %DYMOLAHOME%"
echo "Dymola Path is %DYMOLA_PATH%"
echo "Json Path is %JSON_PATH%"

set PSM_CLASSPATH="%JSON_PATH%"%CPSEP%"%DYMOLA_PATH%"%CPSEP%lib\dymola-integration-service-0.1-SNAPSHOT-jar-with-dependencies.jar
set PSM_MAIN="org.power_systems_modelica.psm.dymola.integration.proxy.service.ServiceMain"
set PSM_CLASS_OPTIONS="127.0.0.1"
echo "Java classpath  : %PSM_CLASSPATH%"
echo "Java main class : %PSM_MAIN%"
echo "Java main class : %PSM_CLASS_OPTIONS%"
echo ""
java -cp %PSM_CLASSPATH% %PSM_MAIN%

:NODYMOLAHOME
@echo The DYMOLAHOME environment variable is not found.
GOTO END

:END