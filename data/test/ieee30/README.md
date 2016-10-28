These CIM files:

	ieee30bus_EQ.xml
	ieee30bus_ME.xml
	ieee30bus_SV.xml
	ieee30bus_TP.xml
	
are a modified version from the source CIM files sent by RTE with the following change/s:

	- A bug regarding the Nominal Voltage has been fixed by RTE.
	
In the folder 
	
	itesla/
	
the following Modelica files:

	- ieee30bus.mo : the ieee30bus system converted to Modelica using the iTesla module modelica-export.
	- ieee30bus_no_lf.mo : the ieee30bus system converted to Modelica using the iTesla module modelica-export WITHOUT running a LoadFlow.