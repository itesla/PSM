@ECHO OFF
REM Copyright (c) 2018, RTE (http://www.rte-france.com)
REM This Source Code Form is subject to the terms of the Mozilla Public
REM License, v. 2.0. If a copy of the MPL was not distributed with this
REM file, You can obtain one at http://mozilla.org/MPL/2.0/.

REM @author Luma Zamarreño <zamarrenolm at aia.es>

SETLOCAL

ECHO.
SET PSM_DATA=./data
SET "PATH=%PATH%;.\helmflow\windows"

for /f tokens^=2-5^ delims^=.-_^" %%j in ('java -fullversion 2^>^&1') do set "version=%%j%%k%%l%%m"
if %version% LSS 18000 (
  echo At least Java 1.8 is needed
  EXIT
)

:Check1
IF ["%OPENMODELICAHOME%"] == [""] GOTO MissingOpenModelicaHome
ECHO Variable OPENMODELICAHOME is %OPENMODELICAHOME%
:Check2
IF ["%DYMOLAHOME%"] == [""] GOTO MissingDymola
ECHO Variable DYMOLAHOME is %DYMOLAHOME%
GOTO Start

:MissingOpenModelicaHome
ECHO Variable OPENMODELICAHOME is not defined
GOTO Check2

:MissingDymola
ECHO Variable DYMOLAHOME is not defined LOCALLY

:Start
ECHO.
ECHO "PATH is %PATH%"
ECHO.

SET PSM_OPTIONS=-Ditools.config.dir=%PSM_DATA%/cfg
SET PSM_CLASSPATH=-cp "./lib/*";%PSM_DATA%/cfg
SET PSM_MAIN=org.power_systems_modelica.psm.gui.MainApp
ECHO Java options    : %PSM_OPTIONS%
ECHO Java classpath  : %PSM_CLASSPATH%
ECHO Java main class : %PSM_MAIN%
ECHO.

java %PSM_CLASSPATH% %PSM_OPTIONS% %PSM_MAIN% 1> psm.log 2>&1@ECHO OFF

ENDLOCAL
