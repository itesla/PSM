within ;
model case3
  parameter Real SNREF = 100.0;
  Modelica.Blocks.Interfaces.RealOutput omegaRef;


// BUSES
  iPSL.Electrical.Buses.Bus bus__GEN______TN (
	 V_0 = 0.9916667,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__GRID_____TN (
	 V_0 = 1.05,
	 angle_0 = -9.248519
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__INF______TN (
	 V_0 = 1.05,
	 angle_0 = -9.248519
	 ) annotation (Placement(transformation()));

// LOADS
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__GRID_____EC (
	 V_0 = 1.05,
	 P_0 = 475.0,
	 Q_0 = 76.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = -9.248519
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
	 X = 0.0,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));

// FIXED INJECTIONS
  iPSL.Electrical.Loads.Eurostag.PwLoadPQ fixinj_pwLoadPQ_GEN__INF______SM (
	 V_0 = 1.05, 
	 angle_0 = -9.248519, 
	 P = -0.0, 
	 Q = -0.01565781
	 ) annotation (Placement(transformation()));

// GENERATORS
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN______SM (
	 SNREF = SNREF, 
	 ur0 = 0.9916666746139526, 
	 ui0 = 0.0, 
	 transformerIncluded = false, 
	 Saturated = false, 
	 TX = 0, 
	 md = 0, 
	 mq = 0, 
	 snd = 0, 
	 snq = 0, 
	 init_theta = 0.836839085742245, 
	 init_omega = 1.0, 
	 init_efd = 2.510674010289587, 
	 WLMDVPu = 1.85, 
	 init_lambdaad = -0.8027146594682057, 
	 init_cm = 1.00161, 
	 init_lambdaq1 = 0.6749800473751795, 
	 init_lambdaq2 = 0.6749800473751795, 
	 init_iq = 2.045394082955089, 
	 init_id = 4.616106353571301, 
	 init_lambdaaq = 0.6749800473751795, 
	 init_lambdad = -0.8027146594682057, 
	 init_lambdaf = -1.124789499326352,
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
	 init_EFD = 2.510674010289587,
	 init_VREF = 1.004220044665401,
	 init_YLL = 0.01255337005144794,
	 TE = 0.05,
	 K = 200.,
	 EMIN = 0.,
	 TA = 3.,
	 TB = 10.,
	 EMAX = 4.
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.TGOV1 reg_tgov1__GEN______SM (
	 SNREF = 100.0,
	 SN = 500.0,
	 PN = 475.0,
	 PNALT = 475.0,
	 init_REF = 0.05008050000000001,
	 init_PMECH = 1.00161,
	 init_CM = 1.00161,
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
	 init_AP = 0.9515295000000003,
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

// Connecting LOADS
  connect(bus__GRID_____TN.p, load__GRID_____EC.p) annotation (Line());

// Connecting GENERATORS
  connect(bus__GEN______TN.p, gen_pwGeneratorM2S__GEN______SM.sortie) annotation (Line());

// Connecting GENERATORS AS FIXED INYECTIONS
  connect(bus__INF______TN.p, fixinj_pwLoadPQ_GEN__INF______SM.p) annotation (Line());

// Connecting FIXED TRANSFORMERS
  connect(bus__GEN______TN.p, trafo__GEN______GRID_____1_PT.p) annotation (Line());
  connect(trafo__GEN______GRID_____1_PT.n, bus__GRID_____TN.p) annotation (Line());

// Connecting OTHERS
annotation (uses(Modelica(version="3.2.1")));
end case3;

