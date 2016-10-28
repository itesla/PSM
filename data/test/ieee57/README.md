These CIM files:

	ieee57bus_EQ.xml
	ieee57bus_ME.xml
	ieee57bus_SV.xml
	ieee57bus_TP.xml
	
are the source CIM files sent by RTE because there is not a but regarding the Nominal Voltage.

In the folder 
	
	itesla/
	
the following Modelica files:

	- ieee57bus.mo : the ieee57bus system converted to Modelica using the iTesla module modelica-export.
	- ieee57bus_no_lf.mo : the ieee57bus system converted to Modelica using the iTesla module modelica-export WITHOUT running a LoadFlow.