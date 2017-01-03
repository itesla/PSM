within ;
model case2
  parameter Real SNREF = 100.0;
 Modelica.Blocks.Sources.Constant omegaRef(k = 1);

// BUSES
  iPSL.Electrical.Buses.Bus bus__GEN______TN(
  V_0 = 1.0,
  angle_0 = 0.0)
    annotation (Placement(transformation));
  iPSL.Electrical.Buses.Bus bus__GRID_____TN(
  V_0 = 1.1026396751403809,
  angle_0= -8.519973700730901e-07)
    annotation (Placement(transformation));

 // INFINITE bBUS
  iPSL.Electrical.Buses.Bus bus__INF______TN(
  V_0 =  1.1026396751403809,
  angle_0= -8.519973700730901e-07)
    annotation (Placement(transformation));
 iPSL.Electrical.Buses.InfiniteBusEuro INF______TN(V_0 = 1.1026396751403809, angle_0 = -8.519973700730901e-07, R =  0.00438, X= 0.0438) annotation(Placement(transformation));

// LOADS
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__GEN______EC(
  V_0 = 1.0,
  P_0 = 379.9999952316284,
  Q_0 = 0.0,
  alpha = 2,
  beta = 2,
  angle_0 = 0.0)
    annotation (Placement(transformation));

// FIXED TRANSFORMERS
  iPSL.Electrical.Branches.Eurostag.PwTransformer_2 trafo__GEN______GRID_____1_PT(
  R = 3.647389E-4,
  X = 0.03890383,
  G = 0.0,
  B = 0.0,
  r = 1.1026316)
    annotation (Placement(transformation));

// LINES
  iPSL.Electrical.Branches.PwLine_2 line__GRID_____INF______1_AC(
  R = 0.0,
  X = 0.0002,
  G = 0.0,
  B = 0.0)
    annotation (Placement(transformation));

// FIXED INJECTIONS

// GENERATORS
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN______SM(
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
  init_theta = 0.93961,
  init_omega = 1.0,
  init_efd = 1.8171866249712054,
  WLMDVPu = 1.85,
  init_lambdaad = -0.682134,
  init_cm = 0.7999996381226613,
  init_lambdaq1 = 0.740050458750662,
  init_lambdaq2 = 0.740050458750662,
  init_iq = 2.24258,
  init_id = 3.06771,
  init_lambdaaq = 0.740050458750662,
  init_lambdad = -0.6821345172443088,
  init_lambdaf = -0.915247292514454,
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
  IWLMDV = 1,
  p0_0 = 3.7999981689453125,
  q0_0 = -0.00023)
    annotation (Placement(transformation));

// REGULATORS
  iPSL.Electrical.Controls.Eurostag.sexs reg_sexs__GEN______SM(
  SNREF = 100.0,
  SN = 500.0,
  PN = 475.0,
  PNALT = 475.0,
  init_EFD = 1.817186624971205,
  init_VREF = 1.009085836,
  init_YLL = 0.00908594,
  TE = 0.05,
  K = 200.,
  EMIN = 0.,
  TA = 3.,
  TB = 10.,
  EMAX = 4.)
    annotation (Placement(transformation));
  tgov1 reg_tgov1__GEN______SM(
  SNREF = 100.0,
  SN = 500.0,
  PN = 475.0,
  PNALT = 475.0,
  init_REF = 0.04,
  init_PMECH = 0.7999996381226613,
  init_CM = 0.7999996381226613,
  DT = 0.,
  RR = 0.05,
  VMIN = 0.,
  VMAX = 1.010000,
  T1 = 0.5,
  T2 = 3.,
  T3 = 10.)
    annotation (Placement(transformation));
  pss2ab reg_pss2ab__GEN______SM(
  SNREF = 100.0,
  SN = 500.0,
  PN = 475.0,
  PNALT = 475.0,
  init_IN = 1.0,
  init_AP = 0.76,
  T4 = 0.015,
  VSTMIN = -0.1,
  T7 = 2.,
  KS1 = 0.,
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
  T3 = 0.1500000)
    annotation (Placement(transformation));

equation
  connect(gen_pwGeneratorM2S__GEN______SM.omegaRef, omegaRef.y);

// Connecting REGULATORS and MACHINES
  connect(reg_sexs__GEN______SM.pin_EFD, gen_pwGeneratorM2S__GEN______SM.pin_EFD) annotation (Line);
  connect(reg_sexs__GEN______SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN______SM.pin_TerminalVoltage) annotation (Line);
  connect(reg_tgov1__GEN______SM.pin_CM, gen_pwGeneratorM2S__GEN______SM.pin_CM) annotation (Line);
  connect(reg_tgov1__GEN______SM.pin_OMEGA, gen_pwGeneratorM2S__GEN______SM.pin_OMEGA) annotation (Line);
  connect(reg_pss2ab__GEN______SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN______SM.pin_ActivePowerSN) annotation (Line);
  connect(reg_pss2ab__GEN______SM.pin_OMEGA, gen_pwGeneratorM2S__GEN______SM.pin_OMEGA) annotation (Line);

// Connecting REGULATORS and REGULATORS
  connect(reg_sexs__GEN______SM.pin_VS, reg_pss2ab__GEN______SM.pin_VS) annotation (Line);
  connect(reg_tgov1__GEN______SM.pin_OMEGA, reg_pss2ab__GEN______SM.pin_OMEGA) annotation (Line);

// Connecting LINES
  connect(bus__GRID_____TN.p, line__GRID_____INF______1_AC.p) annotation (Line);
  connect(line__GRID_____INF______1_AC.n,bus__INF______TN.p) annotation (Line);
  connect(bus__INF______TN.p, INF______TN.p);
// COUPLING DEVICES

// Connecting LOADS
  connect(bus__GEN______TN.p, load__GEN______EC.p) annotation (Line);

// Connecting GENERATORS
  connect(bus__GEN______TN.p, gen_pwGeneratorM2S__GEN______SM.sortie) annotation (Line);

// Connecting FIXED TRANSFORMERS
 connect(bus__GEN______TN.p,trafo__GEN______GRID_____1_PT.p) annotation (Line);
 connect(trafo__GEN______GRID_____1_PT.n, bus__GRID_____TN.p) annotation (Line);

// Connecting OTHERS
  annotation (uses(Modelica(version="3.2.1")));
end case2;

