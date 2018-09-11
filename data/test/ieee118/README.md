WARNING:
Please note that this test case is currently too large to be usable with OpenModelica.

These CIM files:

	ieee118bus_EQ.xml
	ieee118bus_ME.xml
	ieee118bus_SV.xml
	ieee118bus_TP.xml
	
are a modified version from the original files with the following change/s:

	- A bug regarding the Nominal Voltage has been fixed.
	
the following Modelica files:

	- ieee118bus.mo : the ieee118bus system converted to Modelica using the iTesla module modelica-export.
	- ieee118bus_no_lf.mo : the ieee118bus system converted to Modelica using the iTesla module modelica-export WITHOUT running a LoadFlow.
