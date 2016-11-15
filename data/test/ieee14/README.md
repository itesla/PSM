These CIM files:

	ieee14bus_EQ.xml
	ieee14bus_ME.xml
	ieee14bus_SV.xml
	ieee14bus_TP.xml
	
are a modified version from the source CIM files sent by RTE with the following change/s:

	- A bug regarding the Nominal Voltage has been fixed by RTE.
	- The step voltage increment has been modified for some tap changer transformers in order to have the same ratio as in Eurostag.
	- The Qmin and Qmax of the slack bus have been modified to distribute Q in a similar way to Eurostag.

In the folder 
	
	itesla/
	
the following Modelica files:

	- ieee14bus.mo : the ieee14bus system converted to Modelica using the iTesla module modelica-export.
	- ieee14bus_no_lf.mo : the ieee14bus system converted to Modelica using the iTesla module modelica-export WITHOUT running a LoadFlow.
	- ieee14bus_Init.mo : model for the full model initialization.