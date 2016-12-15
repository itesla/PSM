within ;
model case1
  parameter Real SNREF = 100.0;
  Modelica.Blocks.Interfaces.RealOutput omegaRef;


// BUSES
  iPSL.Electrical.Buses.Bus bus__GEN______TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__GRID_____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__INF______TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));

// FIXED TRANSFORMERS
  iPSL.Electrical.Branches.Eurostag.PwTransformer_2 trafo__GEN______GRID_____1_PT (
	 R = 3.647389E-4,
	 X = 0.03890383,
	 G = 0.0,
	 B = 0.0,
	 r = 1.1026316
	 ) annotation (Placement(transformation()));

// LINES
  iPSL.Electrical.Branches.PwLine_2 line__GRID_____INF______1_AC (
	 R = 0.0,
	 X = 0.06,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));

// FIXED INJECTIONS
  iPSL.Electrical.Loads.Eurostag.PwLoadPQ load_pwLoadPQ_GEN__INF______SM (
	 V_0 = 1.0, 
	 angle_0 = 0.0, 
	 P = -0.0, 
	 Q = -0.0
	 ) annotation (Placement(transformation()));

// GENERATORS
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN______SM (
	 SNREF = SNREF, 
	 ur0 = 1.0, 
	 ui0 = 0.0, 
	 transformerIncluded = false, 
	 Saturated = false, 
	 TX = 0, 
	 md = 0, 
	 mq = 0, 
	 snd = 0, 
	 snq = 0, 
	 init_theta = 0.0, 
	 init_omega = 1.0, 
	 init_efd = 1.0, 
	 WLMDVPu = 1.85, 
	 init_lambdaad = -1.0, 
	 init_cm = 0.0, 
	 init_lambdaq1 = 0.0, 
	 init_lambdaq2 = 0.0, 
	 init_iq = 0.0, 
	 init_id = 0.0, 
	 init_lambdaaq = 0.0, 
	 init_lambdad = -1.0, 
	 init_lambdaf = -1.128282221641749,
	 PNALT = 475.0,
	 IENR = 4,
	 DIn = 0.0,
	 HIn = 4.0,
	 TSD0 = 0.042,
	 XPD = 0.35,
	 TPD0 = 5.143,
	 rStatIn = 0.0,
	 XPQ = 0.5,
	 TSQ0 = 0.083,
	 SN = 500.0,
	 TPQ0 = 2.16,
	 XSD = 0.25,
	 XD = 2.0,
	 lStatIn = 0.15,
	 XSQ = 0.3,
	 XQ = 1.8,
	 PN = 475.0,
	 IWLMDV = 1
	 ) annotation (Placement(transformation()));

// REGULATORS
  iPSL.Electrical.Controls.Eurostag.sexs reg_sexs__GEN______SM (
	 SNREF = 100.0,
	 SN = 500.0,
	 PN = 475.0,
	 PNALT = 475.0,
	 init_EFD = 1.0,
	 init_VREF = 1.005,
	 init_YLL = 0.005,
	 TE = 0.05,
	 K = 200.,
	 EMIN = 0.,
	 TA = 3.,
	 TB = 10.,
	 EMAX = 4.
	 ) annotation (Placement(transformation()));
  tgov1 reg_tgov1__GEN______SM (
	 SNREF = 100.0,
	 SN = 500.0,
	 PN = 475.0,
	 PNALT = 475.0,
	 init_REF = 0.0,
	 init_PMECH = 0.0,
	 init_CM = 0.0,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.010000,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pss2ab reg_pss2ab__GEN______SM (
	 SNREF = 100.0,
	 SN = 500.0,
	 PN = 475.0,
	 PNALT = 475.0,
	 init_IN = 1.0,
	 init_AP = 0.0,
	 T4 = 0.015,
	 VSTMIN = -0.1,
	 T7 = 2.,
	 KS1 = 10.,
	 KS3 = 1.,
	 KS2 = 0.1564,
	 M = 0.,
	 N = 0.,
	 TW2 = 2.,
	 TW1 = 2.,
	 TW3 = 2.,
	 VSTMAX = 0.1,
	 T1 = 0.25,
	 T2 = 0.03,
	 T3 = .1500000
	 ) annotation (Placement(transformation()));

equation
  omegaRef = (gen_pwGeneratorM2S__GEN______SM.omega*gen_pwGeneratorM2S__GEN______SM.SN*gen_pwGeneratorM2S__GEN______SM.HIn) / (gen_pwGeneratorM2S__GEN______SM.SN*gen_pwGeneratorM2S__GEN______SM.HIn);

  connect(gen_pwGeneratorM2S__GEN______SM.omegaRef, omegaRef);

// Connecting REGULATORS and MACHINES
  connect(reg_sexs__GEN______SM.pin_EFD, gen_pwGeneratorM2S__GEN______SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN______SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN______SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_tgov1__GEN______SM.pin_CM, gen_pwGeneratorM2S__GEN______SM.pin_CM) annotation (Line());
  connect(reg_tgov1__GEN______SM.pin_OMEGA, gen_pwGeneratorM2S__GEN______SM.pin_OMEGA) annotation (Line());
  connect(reg_pss2ab__GEN______SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN______SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pss2ab__GEN______SM.pin_OMEGA, gen_pwGeneratorM2S__GEN______SM.pin_OMEGA) annotation (Line());

// Connecting REGULATORS and REGULATORS
  connect(reg_sexs__GEN______SM.pin_VS, reg_pss2ab__GEN______SM.pin_VS) annotation (Line());
  connect(reg_tgov1__GEN______SM.pin_OMEGA, reg_pss2ab__GEN______SM.pin_OMEGA) annotation (Line());

// Connecting LINES
  connect(bus__GRID_____TN.p, line__GRID_____INF______1_AC.p) annotation (Line());
  connect(line__GRID_____INF______1_AC.n, bus__INF______TN.p) annotation (Line());

// COUPLING DEVICES

// Connecting GENERATORS
  connect(bus__GEN______TN.p, gen_pwGeneratorM2S__GEN______SM.sortie) annotation (Line());

// Connecting GENERATORS AS FIXED INYECTIONS
  connect(bus__INF______TN.p, load_pwLoadPQ_GEN__INF______SM.p) annotation (Line());

// Connecting FIXED TRANSFORMERS
  connect(bus__GEN______TN.p, trafo__GEN______GRID_____1_PT.p) annotation (Line());
  connect(trafo__GEN______GRID_____1_PT.n, bus__GRID_____TN.p) annotation (Line());

// Connecting OTHERS
annotation (uses(Modelica(version="3.2.1")));
end case1;

