The Modelica file:

	ieee14bus_no_loadflow.mo
	
Comes from the iTesla existing Modelica export process, the module

	ipst/modelica-export

The version of the IEEE14 case used was the one received from RTE around October 10, that included a fix in the nominal voltages at the end points of two lines (different nominal voltages at end points were causing a mistake in the reactance per unit calculation).

The iTesla output has been arranged for easy comparison with the psm conversion workflow:

  - No Loadflow was run to obtain the Modelica export (values for V,A,P,Q are the ones in the original CIM file).
  - Comments have been removed, tabs have been replaced by spaces.
  - attribute X of three transformers and attribute B of a capacitor bank has been rounded to match current conversion calculations:
  
		  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__BUS____4_BUS____7_1_PT (
		    r = 1.0204082,
		    B0 = 0.0,
		    G0 = 0.0,
		    theta = 0.0,
		    R = 0.0,
		    X = 0.20911993960205447  >--->  0.20911995
		    );
		  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__BUS____4_BUS____9_1_PT (
		    r = 1.0416667,
		    B0 = 0.0,
		    G0 = 0.0,
		    theta = 0.0,
		    R = 0.0,
		    X = 0.5561794406894949  >--->  0.55617946
		    );
		  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__BUS____5_BUS____6_1_PT (
		    r = 1.0638298,
		    B0 = 0.0,
		    G0 = 0.0,
		    theta = 0.0,
		    R = 0.0,
		    X = 0.25202059995597903  >--->  0.2520206
		    );
			
		iPSL.Electrical.Banks.PwCapacitorBank cap__BANK___9_SC (
		    B = 0.19000009587800412,  >--->   0.1900001
		    nsteps = 1
		    );