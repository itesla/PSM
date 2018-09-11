@echo OFF
REM Copyright (c) 2018, RTE (http://www.rte-france.com)
REM This Source Code Form is subject to the terms of the Mozilla Public
REM License, v. 2.0. If a copy of the MPL was not distributed with this
REM file, You can obtain one at http://mozilla.org/MPL/2.0/.

REM @author Luma Zamarreño <zamarrenolm at aia.es>

SETLOCAL

set CPSEP=;
set DYMOLA_PATH=

if "%DYMOLAHOME%"=="" GOTO NODYMOLAHOME
set DYMOLA_PATH=%DYMOLAHOME%\Modelica\Library\java_interface\dymola_interface.jar
set JSON_PATH=%DYMOLAHOME%\Modelica\Library\java_interface\json-simple-1.1.1.jar

echo "Using Dymola from %DYMOLAHOME%"
echo "Dymola Path is %DYMOLA_PATH%"
echo "Json Path is %JSON_PATH%"

set PSM_CLASSPATH="%JSON_PATH%"%CPSEP%"%DYMOLA_PATH%"%CPSEP%"./lib/*"
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
ENDLOCAL
