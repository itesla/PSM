within ;
model N44_h21
  parameter Real SNREF = 100.0;

// BUSES

  iPSL.Electrical.Buses.Bus bus__f17695ab_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 angle_0 = -1.4502187
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f17695b9_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 0.99625874,
	 angle_0 = -2.0914793
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f17695bf_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.006532,
	 angle_0 = 3.8220153
	 ) annotation (Placement(transformation()));

	Modelica.Blocks.Sources.Constant const(k=0);

	Modelica.Blocks.Sources.Constant const1(k=-9999);

	Modelica.Blocks.Sources.Constant const2(k=9999);

equation

// Connecting REGULATORS and MACHINES

  connect(reg_stab2a__f1769804_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENROU__f1769804_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_ieeet2__f1769804_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENROU__f1769804_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());

// Connecting OTHERS

end N44_h21;

