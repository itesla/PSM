These CIM files:

	ieee57bus_EQ.xml
	ieee57bus_ME.xml
	ieee57bus_SV.xml
	ieee57bus_TP.xml
	
are a modified version from the source CIM files with the following change/s:

	- The step voltage increment has been modified for some tap changer transformers in order to have the same ratio as in Eurostag.
	- The Qmin and Qmax of the generator connected to the slack bus have been modified to distribute Q in a similar way to Eurostag.

In the folder 
	
	itesla/
	
the following Modelica files:

	- ieee57bus.mo : the ieee57bus system converted to Modelica using the iTesla module modelica-export.
	- ieee57bus_no_lf.mo : the ieee57bus system converted to Modelica using the iTesla module modelica-export WITHOUT running a LoadFlow.
	- ieee57bus_Init.mo : model for the full model initialization.