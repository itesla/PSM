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

:Start
ECHO.
ECHO "PATH is %PATH%"
ECHO.

SET PSM_OPTIONS=-Ditools.config.dir=%PSM_DATA%/cfg
SET PSM_CLASSPATH=-cp "./lib/*";%PSM_DATA%/cfg
SET PSM_MAIN=org.power_systems_modelica.psm.tools.Main
SET PSM_ARGS=%*

ECHO Java options    : %PSM_OPTIONS%
ECHO Java classpath  : %PSM_CLASSPATH%
ECHO Java main class : %PSM_MAIN%
ECHO.

java %PSM_CLASSPATH% %PSM_OPTIONS% %PSM_MAIN% %PSM_ARGS%
@ECHO OFF

ENDLOCAL
