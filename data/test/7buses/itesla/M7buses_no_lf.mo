within ;
model M7buses
  parameter Real SNREF = 100.0;
  iPSL.Electrical.Machines.Eurostag.omegaRef omegaRef (
  nGenerators = 3
  ) annotation (Placement(transformation()));

// BUSES
  iPSL.Electrical.Buses.Bus bus__FP_AND11_TN (
	 V_0 = 1.0695947,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__FP_AND12_TN (
	 V_0 = 1.0695947,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__FS_BIS11_TN (
	 V_0 = 1.0695841,
	 angle_0 = -0.0208
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__FS_BIS12_TN (
	 V_0 = 1.0695841,
	 angle_0 = -0.0208
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__FSSV_O11_TN (
	 V_0 = 1.0696054,
	 angle_0 = 0.076305
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__FSSV_O12_TN (
	 V_0 = 1.0696054,
	 angle_0 = 0.076305
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__FTDPRA11_TN (
	 V_0 = 1.0695895,
	 angle_0 = -0.03468
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__FTDPRA12_TN (
	 V_0 = 1.0695895,
	 angle_0 = -0.03468
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__FTILL511_TN (
	 V_0 = 1.069579,
	 angle_0 = -0.01386
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__FTILL512_TN (
	 V_0 = 1.069579,
	 angle_0 = -0.01386
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__FVALDI11_TN (
	 V_0 = 1.0696054,
	 angle_0 = 0.013869
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__FVALDI12_TN (
	 V_0 = 1.0696054,
	 angle_0 = 0.013869
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__FVERGE11_TN (
	 V_0 = 1.0696054,
	 angle_0 = -0.01734
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__FVERGE12_TN (
	 V_0 = 1.0696054,
	 angle_0 = -0.01734
	 ) annotation (Placement(transformation()));

// LOADS
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__FP_ANC12_EC (
	 V_0 = 1.0695947,
	 P_0 = 240.0,
	 Q_0 = 2.4,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__FS_BIC11_EC (
	 V_0 = 1.0695841,
	 P_0 = 240.0,
	 Q_0 = 2.4,
	 alpha = 1,
	 beta = 2,
	 angle_0 = -0.0208
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__FTDPRC11_EC (
	 V_0 = 1.0695895,
	 P_0 = 480.0,
	 Q_0 = 4.8,
	 alpha = 1,
	 beta = 2,
	 angle_0 = -0.03468
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__FTILLC51_EC (
	 V_0 = 1.069579,
	 P_0 = 480.0,
	 Q_0 = 4.8,
	 alpha = 1,
	 beta = 2,
	 angle_0 = -0.01386
	 ) annotation (Placement(transformation()));

// TAP CHANGER TRANSFORMERS
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__FP_AND11_FTDPRA11_1_PT (
	 r = 1.0,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 6.9251664787778566E-6,
	 X = 6.925200887664203E-4
	 ) annotation (Placement(transformation()));

// LINES
  iPSL.Electrical.Branches.PwLine_2 line__FP_AND11_FVERGE11_1_AC (
	 R = 5.9999998E-6,
	 X = 6.9200003E-4,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__FP_AND11_FVERGE11_2_AC (
	 R = 5.9999998E-6,
	 X = 6.9200003E-4,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__FS_BIS11_FVALDI11_1_AC (
	 R = 5.9999998E-6,
	 X = 6.9200003E-4,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__FS_BIS11_FVALDI11_2_AC (
	 R = 5.9999998E-6,
	 X = 6.9200003E-4,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__FSSV_O11_FP_AND11_1_AC (
	 R = 5.9999998E-6,
	 X = 6.9200003E-4,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__FSSV_O11_FP_AND11_2_AC (
	 R = 5.9999998E-6,
	 X = 6.9200003E-4,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__FSSV_O11_FTILL511_1_AC (
	 R = 5.9999998E-6,
	 X = 6.9200003E-4,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__FSSV_O11_FTILL511_2_AC (
	 R = 5.9999998E-6,
	 X = 6.9200003E-4,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__FTDPRA11_FVERGE11_1_AC (
	 R = 5.9999998E-6,
	 X = 6.9200003E-4,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__FTDPRA11_FVERGE11_2_AC (
	 R = 5.9999998E-6,
	 X = 6.9200003E-4,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__FTILL511_FS_BIS11_1_AC (
	 R = 5.9999998E-6,
	 X = 6.9200003E-4,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__FTILL511_FS_BIS11_2_AC (
	 R = 5.9999998E-6,
	 X = 6.9200003E-4,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__FVALDI11_FTDPRA11_1_AC (
	 R = 5.9999998E-6,
	 X = 6.9200003E-4,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__FVALDI11_FTDPRA11_2_AC (
	 R = 5.9999998E-6,
	 X = 6.9200003E-4,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));

// FIXED INJECTIONS

// GENERATORS
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__FSSV_T11_SM (
	 SNREF = SNREF, 
	 ur0 = 1.0696044019574962, 
	 ui0 = 0.0014244716082464394, 
	 transformerIncluded = true, 
	 V2 = 380.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 1.145885495484075, 
	 init_omega = 1.0, 
	 init_efd = 0.8440509099364212, 
	 WLMDVPu = 0.7226456114257552, 
	 init_lambdaad = -0.7594306522245973, 
	 init_cm = 0.7944881870883267, 
	 init_lambdaq1 = 0.8372712689550665, 
	 init_lambdaq2 = 0.8372712689550665, 
	 init_iq = 3.690875035278939, 
	 init_id = 8.181419919437628, 
	 init_lambdaaq = 0.8372712689550665, 
	 init_lambdad = -0.7594306522245973, 
	 init_lambdaf = -1.028988379578985,
	 PNALT = 970.0,
	 IENR = 4,
	 DIn = 0.0,
	 HIn = 6.3,
	 TSD0 = 0.058,
	 XPD = 0.3925,
	 RTfoPu = 0.0025,
	 TPD0 = 9.627,
	 XTfoPu = 0.137,
	 rStatIn = 0.00344,
	 U1N = 24.0,
	 md = 0.084,
	 XPQ = 0.437,
	 TSQ0 = 0.06,
	 SN = 1078.0,
	 V1 = 24.0,
	 TPQ0 = 1.006,
	 mq = 0.084,
	 XSD = 0.289,
	 snd = 5.57,
	 XD = 2.47,
	 U2N = 415.0,
	 SNtfo = 1080.0,
	 lStatIn = 0.211,
	 XSQ = 0.29,
	 snq = 5.57,
	 XQ = 2.47,
	 PN = 1215.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__FVALDT11_SM (
	 SNREF = SNREF, 
	 ur0 = 1.0696053191587458, 
	 ui0 = 2.589083489311652E-4, 
	 transformerIncluded = true, 
	 V2 = 380.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.6464501831535526, 
	 init_omega = 1.0, 
	 init_efd = 0.3878776122684651, 
	 WLMDVPu = 0.7448638277959367, 
	 init_lambdaad = -0.9327392251225904, 
	 init_cm = 0.3124316272405734, 
	 init_lambdaq1 = 0.5454821737424532, 
	 init_lambdaq2 = 0.5454821737424532, 
	 init_iq = 3.546512131873081, 
	 init_id = 2.750505585118617, 
	 init_lambdaaq = 0.5454821737424532, 
	 init_lambdad = -0.9327392251225904, 
	 init_lambdaf = -1.104440750231353,
	 PNALT = 1539.0,
	 IENR = 4,
	 DIn = 0.0,
	 HIn = 5.112,
	 TSD0 = 0.065,
	 XPD = 0.527,
	 RTfoPu = 0.0029,
	 TPD0 = 10.041,
	 XTfoPu = 0.1583,
	 rStatIn = 0.003275,
	 U1N = 20.0,
	 md = 0.05,
	 XPQ = 0.623,
	 TSQ0 = 0.094,
	 SN = 1710.0,
	 V1 = 20.0,
	 TPQ0 = 1.22,
	 mq = 0.05,
	 XSD = 0.367,
	 snd = 9.285,
	 XD = 2.91,
	 U2N = 405.0,
	 SNtfo = 1710.0,
	 lStatIn = 0.265,
	 XSQ = 0.391,
	 snq = 9.285,
	 XQ = 2.72,
	 PN = 1539.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__FVERGT11_SM (
	 SNREF = SNREF, 
	 ur0 = 1.069605301511254, 
	 ui0 = -3.237054760117397E-4, 
	 transformerIncluded = true, 
	 V2 = 380.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = -0.000338232536209714, 
	 init_omega = 1.0, 
	 init_efd = 0.3908934470584032, 
	 WLMDVPu = 0.6686345878298443, 
	 init_lambdaad = -1.072049012096693, 
	 init_cm = 2.475294953622667e-07, 
	 init_lambdaq1 = 4.16202092971392e-07, 
	 init_lambdaq2 = 4.16202092971392e-07, 
	 init_iq = 2.489587079440298e-06, 
	 init_id = 0.06994710237568474, 
	 init_lambdaaq = 4.16202092971392e-07, 
	 init_lambdad = -1.072049012096693, 
	 init_lambdaf = -1.215364442229271,
	 PNALT = 1090.0,
	 IENR = 4,
	 DIn = 0.0,
	 HIn = 5.4,
	 TSD0 = 0.08,
	 XPD = 0.384,
	 RTfoPu = 0.0025,
	 TPD0 = 8.094,
	 XTfoPu = 0.1362,
	 rStatIn = 0.002796,
	 U1N = 24.0,
	 md = 0.215,
	 XPQ = 0.393,
	 TSQ0 = 0.084,
	 SN = 1211.0,
	 V1 = 24.0,
	 TPQ0 = 1.572,
	 mq = 0.215,
	 XSD = 0.264,
	 snd = 6.995,
	 XD = 2.22,
	 U2N = 415.0,
	 SNtfo = 1080.0,
	 lStatIn = 0.202,
	 XSQ = 0.262,
	 snq = 6.995,
	 XQ = 2.22,
	 PN = 1090.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));

// REGULATORS
  iPSL.Electrical.Controls.Eurostag.pssi3e3b reg_pssi3e3b__FSSV_T11_SM (
	 SNREF = 100.0,
	 SN = 1078.0,
	 PN = 1215.0,
	 PNALT = 970.0,
	 init_P = 0.8905500927643786,
	 VSTMIN = -0.1,
	 KS1 = -0.602,
	 KS2 = 30.12000,
	 TW2 = .3000000,
	 TW1 = .3000000,
	 A1 = 0.359,
	 A2 = .5860000,
	 TW3 = .6000000,
	 A3 = .4290000,
	 A4 = .5640000,
	 A5 = 0.,
	 VSTMAX = 0.1,
	 A6 = 0.,
	 A7 = 0.031,
	 A8 = 0.000001,
	 T1 = 0.012,
	 T2 = 0.012
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.sexs reg_sexs__FSSV_T11_SM (
	 SNREF = 100.0,
	 SN = 1078.0,
	 PN = 1215.0,
	 PNALT = 970.0,
	 init_EFD = 0.8440509099364212,
	 init_VREF = 0.9940318779558547,
	 init_YLL = 0.004220254549682106,
	 TE = 0.05,
	 EFDMIN = -999.,
	 KC = 1.,
	 EFDMAX = 999.,
	 K = 200.,
	 EMIN = 0.,
	 TA = 3.,
	 TB = 10.,
	 EMAX = 4.
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.tgov1 reg_tgov1__FSSV_T11_SM (
	 SNREF = 100.0,
	 SN = 1078.0,
	 PN = 1215.0,
	 PNALT = 970.0,
	 init_REF = 0.03972440935441634,
	 init_PMECH = 0.7944881870883267,
	 init_CM = 0.7944881870883267,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.010000,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.pssi3e3b reg_pssi3e3b__FVALDT11_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_P = 0.2807052807017545,
	 VSTMIN = -0.1,
	 KS1 = -0.602,
	 KS2 = 30.12000,
	 TW2 = .3000000,
	 TW1 = .3000000,
	 A1 = 0.359,
	 A2 = .5860000,
	 TW3 = .6000000,
	 A3 = .4290000,
	 A4 = .5640000,
	 A5 = 0.,
	 VSTMAX = 0.1,
	 A6 = 0.,
	 A7 = 0.031,
	 A8 = 0.000001,
	 T1 = 0.012,
	 T2 = 0.012
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.gsteam1 reg_gsteam1__FVALDT11_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_PGV = 0.3124316272405734,
	 init_OMEGREF = 1.0,
	 init_CM = 0.3124316272405734,
	 init_CM2 = 0.0,
	 K1 = 0.2,
	 K2 = 0.,
	 PMIN = 0.,
	 K3 = .3000000,
	 K4 = 0.,
	 K5 = 0.5,
	 K = 25.,
	 K6 = 0.,
	 K7 = 0.,
	 K8 = 0.,
	 UC = -10.,
	 PMAX = 1.,
	 UO = 1.,
	 T1 = 0.,
	 T2 = 0.,
	 T3 = 0.1,
	 T4 = .3000000,
	 T5 = 5.,
	 T6 = 0.5,
	 T7 = 0.,
	 SDB1 = 1.,
	 SDB2 = 1.,
	 VALVE = 0.,
	 F1 = {0.,0.,.4000000,0.75,0.5,.9100000,.6000000,.9800000,1.,1.},
	 DB1 = 0.,
	 DB2 = 0.
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.sexs reg_sexs__FVALDT11_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_EFD = 0.3878776122684651,
	 init_VREF = 1.007900698452688,
	 init_YLL = 0.001939388061342325,
	 TE = 0.05,
	 EFDMIN = -999.,
	 KC = 1.,
	 EFDMAX = 999.,
	 K = 200.,
	 EMIN = 0.,
	 TA = 3.,
	 TB = 10.,
	 EMAX = 4.
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.pssi3e3b reg_pssi3e3b__FVERGT11_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_P = 2.797796687875476e-22,
	 VSTMIN = -0.1,
	 KS1 = -0.602,
	 KS2 = 30.12000,
	 TW2 = .3000000,
	 TW1 = .3000000,
	 A1 = 0.359,
	 A2 = .5860000,
	 TW3 = .6000000,
	 A3 = .4290000,
	 A4 = .5640000,
	 A5 = 0.,
	 VSTMAX = 0.1,
	 A6 = 0.,
	 A7 = 0.031,
	 A8 = 0.000001,
	 T1 = 0.012,
	 T2 = 0.012
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.gsteam1 reg_gsteam1__FVERGT11_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_PGV = 2.475294953622667e-07,
	 init_OMEGREF = 1.0,
	 init_CM = 2.475294953622667e-07,
	 init_CM2 = 0.0,
	 K1 = 0.2,
	 K2 = 0.,
	 PMIN = 0.,
	 K3 = .3000000,
	 K4 = 0.,
	 K5 = 0.5,
	 K = 25.,
	 K6 = 0.,
	 K7 = 0.,
	 K8 = 0.,
	 UC = -10.,
	 PMAX = 1.,
	 UO = 1.,
	 T1 = 0.,
	 T2 = 0.,
	 T3 = 0.1,
	 T4 = .3000000,
	 T5 = 5.,
	 T6 = 0.5,
	 T7 = 0.,
	 SDB1 = 1.,
	 SDB2 = 1.,
	 VALVE = 0.,
	 F1 = {0.,0.,.4000000,0.75,0.5,.9100000,.6000000,.9800000,1.,1.},
	 DB1 = 0.,
	 DB2 = 0.
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.sexs reg_sexs__FVERGT11_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_EFD = 0.3908934470584032,
	 init_VREF = 0.9823154954438544,
	 init_YLL = 0.001954467235292016,
	 TE = 0.05,
	 EFDMIN = -999.,
	 KC = 1.,
	 EFDMAX = 999.,
	 K = 200.,
	 EMIN = 0.,
	 TA = 3.,
	 TB = 10.,
	 EMAX = 4.
	 ) annotation (Placement(transformation()));

equation
  connect(omegaRef.pin_HIn[1], gen_pwGeneratorM2S__FSSV_T11_SM.pin_HIn) annotation (Line());
  connect(omegaRef.pin_HIn[2], gen_pwGeneratorM2S__FVALDT11_SM.pin_HIn) annotation (Line());
  connect(omegaRef.pin_HIn[3], gen_pwGeneratorM2S__FVERGT11_SM.pin_HIn) annotation (Line());

  connect(omegaRef.pin_SN[1], gen_pwGeneratorM2S__FSSV_T11_SM.pin_SN) annotation (Line());
  connect(omegaRef.pin_SN[2], gen_pwGeneratorM2S__FVALDT11_SM.pin_SN) annotation (Line());
  connect(omegaRef.pin_SN[3], gen_pwGeneratorM2S__FVERGT11_SM.pin_SN) annotation (Line());

  connect(omegaRef.pin_omega[1], gen_pwGeneratorM2S__FSSV_T11_SM.pin_OMEGA) annotation (Line());
  connect(omegaRef.pin_omega[2], gen_pwGeneratorM2S__FVALDT11_SM.pin_OMEGA) annotation (Line());
  connect(omegaRef.pin_omega[3], gen_pwGeneratorM2S__FVERGT11_SM.pin_OMEGA) annotation (Line());

  connect(omegaRef.omegaRef, gen_pwGeneratorM2S__FSSV_T11_SM.omegaRef) annotation (Line());
  connect(omegaRef.omegaRef, gen_pwGeneratorM2S__FVALDT11_SM.omegaRef) annotation (Line());
  connect(omegaRef.omegaRef, gen_pwGeneratorM2S__FVERGT11_SM.omegaRef) annotation (Line());

// Connecting REGULATORS and MACHINES
  connect(reg_pssi3e3b__FSSV_T11_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__FSSV_T11_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e3b__FSSV_T11_SM.pin_OMEGA, gen_pwGeneratorM2S__FSSV_T11_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__FSSV_T11_SM.pin_EFD, gen_pwGeneratorM2S__FSSV_T11_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__FSSV_T11_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__FSSV_T11_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_tgov1__FSSV_T11_SM.pin_CM, gen_pwGeneratorM2S__FSSV_T11_SM.pin_CM) annotation (Line());
  connect(reg_tgov1__FSSV_T11_SM.pin_OMEGA, gen_pwGeneratorM2S__FSSV_T11_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e3b__FVALDT11_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__FVALDT11_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e3b__FVALDT11_SM.pin_OMEGA, gen_pwGeneratorM2S__FVALDT11_SM.pin_OMEGA) annotation (Line());
  connect(reg_gsteam1__FVALDT11_SM.pin_CM, gen_pwGeneratorM2S__FVALDT11_SM.pin_CM) annotation (Line());
  connect(reg_gsteam1__FVALDT11_SM.pin_OMEGA, gen_pwGeneratorM2S__FVALDT11_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__FVALDT11_SM.pin_EFD, gen_pwGeneratorM2S__FVALDT11_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__FVALDT11_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__FVALDT11_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_sexs__FVERGT11_SM.pin_EFD, gen_pwGeneratorM2S__FVERGT11_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__FVERGT11_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__FVERGT11_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam1__FVERGT11_SM.pin_CM, gen_pwGeneratorM2S__FVERGT11_SM.pin_CM) annotation (Line());
  connect(reg_gsteam1__FVERGT11_SM.pin_OMEGA, gen_pwGeneratorM2S__FVERGT11_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e3b__FVERGT11_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__FVERGT11_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e3b__FVERGT11_SM.pin_OMEGA, gen_pwGeneratorM2S__FVERGT11_SM.pin_OMEGA) annotation (Line());

// Connecting REGULATORS and REGULATORS
  connect(reg_pssi3e3b__FSSV_T11_SM.pin_VS, reg_sexs__FSSV_T11_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e3b__FSSV_T11_SM.pin_OMEGA, reg_tgov1__FSSV_T11_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e3b__FVALDT11_SM.pin_OMEGA, reg_gsteam1__FVALDT11_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e3b__FVALDT11_SM.pin_VS, reg_sexs__FVALDT11_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e3b__FVERGT11_SM.pin_VS, reg_sexs__FVERGT11_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e3b__FVERGT11_SM.pin_OMEGA, reg_gsteam1__FVERGT11_SM.pin_OMEGA) annotation (Line());

// Connecting LINES
  connect(bus__FP_AND11_TN.p, line__FP_AND11_FVERGE11_1_AC.p) annotation (Line());
  connect(line__FP_AND11_FVERGE11_1_AC.n, bus__FVERGE11_TN.p) annotation (Line());
  connect(bus__FP_AND11_TN.p, line__FP_AND11_FVERGE11_2_AC.p) annotation (Line());
  connect(line__FP_AND11_FVERGE11_2_AC.n, bus__FVERGE11_TN.p) annotation (Line());
  connect(bus__FS_BIS11_TN.p, line__FS_BIS11_FVALDI11_1_AC.p) annotation (Line());
  connect(line__FS_BIS11_FVALDI11_1_AC.n, bus__FVALDI11_TN.p) annotation (Line());
  connect(bus__FS_BIS11_TN.p, line__FS_BIS11_FVALDI11_2_AC.p) annotation (Line());
  connect(line__FS_BIS11_FVALDI11_2_AC.n, bus__FVALDI11_TN.p) annotation (Line());
  connect(bus__FSSV_O11_TN.p, line__FSSV_O11_FP_AND11_1_AC.p) annotation (Line());
  connect(line__FSSV_O11_FP_AND11_1_AC.n, bus__FP_AND11_TN.p) annotation (Line());
  connect(bus__FSSV_O11_TN.p, line__FSSV_O11_FP_AND11_2_AC.p) annotation (Line());
  connect(line__FSSV_O11_FP_AND11_2_AC.n, bus__FP_AND11_TN.p) annotation (Line());
  connect(bus__FSSV_O11_TN.p, line__FSSV_O11_FTILL511_1_AC.p) annotation (Line());
  connect(line__FSSV_O11_FTILL511_1_AC.n, bus__FTILL511_TN.p) annotation (Line());
  connect(bus__FSSV_O11_TN.p, line__FSSV_O11_FTILL511_2_AC.p) annotation (Line());
  connect(line__FSSV_O11_FTILL511_2_AC.n, bus__FTILL511_TN.p) annotation (Line());
  connect(bus__FTDPRA11_TN.p, line__FTDPRA11_FVERGE11_1_AC.p) annotation (Line());
  connect(line__FTDPRA11_FVERGE11_1_AC.n, bus__FVERGE11_TN.p) annotation (Line());
  connect(bus__FTDPRA11_TN.p, line__FTDPRA11_FVERGE11_2_AC.p) annotation (Line());
  connect(line__FTDPRA11_FVERGE11_2_AC.n, bus__FVERGE11_TN.p) annotation (Line());
  connect(bus__FTILL511_TN.p, line__FTILL511_FS_BIS11_1_AC.p) annotation (Line());
  connect(line__FTILL511_FS_BIS11_1_AC.n, bus__FS_BIS11_TN.p) annotation (Line());
  connect(bus__FTILL511_TN.p, line__FTILL511_FS_BIS11_2_AC.p) annotation (Line());
  connect(line__FTILL511_FS_BIS11_2_AC.n, bus__FS_BIS11_TN.p) annotation (Line());
  connect(bus__FVALDI11_TN.p, line__FVALDI11_FTDPRA11_1_AC.p) annotation (Line());
  connect(line__FVALDI11_FTDPRA11_1_AC.n, bus__FTDPRA11_TN.p) annotation (Line());
  connect(bus__FVALDI11_TN.p, line__FVALDI11_FTDPRA11_2_AC.p) annotation (Line());
  connect(line__FVALDI11_FTDPRA11_2_AC.n, bus__FTDPRA11_TN.p) annotation (Line());

// COUPLING DEVICES
  connect(bus__FP_AND11_TN.p, bus__FP_AND12_TN.p) annotation (Line());
  connect(bus__FS_BIS11_TN.p, bus__FS_BIS12_TN.p) annotation (Line());
  connect(bus__FSSV_O11_TN.p, bus__FSSV_O12_TN.p) annotation (Line());
  connect(bus__FTDPRA11_TN.p, bus__FTDPRA12_TN.p) annotation (Line());
  connect(bus__FTILL511_TN.p, bus__FTILL512_TN.p) annotation (Line());
  connect(bus__FVALDI11_TN.p, bus__FVALDI12_TN.p) annotation (Line());
  connect(bus__FVERGE11_TN.p, bus__FVERGE12_TN.p) annotation (Line());

// Connecting LOADS
  connect(bus__FP_AND11_TN.p, load__FP_ANC12_EC.p) annotation (Line());
  connect(bus__FS_BIS11_TN.p, load__FS_BIC11_EC.p) annotation (Line());
  connect(bus__FTDPRA11_TN.p, load__FTDPRC11_EC.p) annotation (Line());
  connect(bus__FTILL511_TN.p, load__FTILLC51_EC.p) annotation (Line());

// Connecting GENERATORS
  connect(bus__FSSV_O11_TN.p, gen_pwGeneratorM2S__FSSV_T11_SM.sortie) annotation (Line());
  connect(bus__FVALDI11_TN.p, gen_pwGeneratorM2S__FVALDT11_SM.sortie) annotation (Line());
  connect(bus__FVERGE11_TN.p, gen_pwGeneratorM2S__FVERGT11_SM.sortie) annotation (Line());

// Connecting DETAILED TRANSFORMERS
  connect(bus__FP_AND11_TN.p, trafo__FP_AND11_FTDPRA11_1_PT.p) annotation (Line());
  connect(trafo__FP_AND11_FTDPRA11_1_PT.n, bus__FTDPRA11_TN.p) annotation (Line());

// Connecting OTHERS

// Modelica version required
  annotation (uses(Modelica(version="3.2.1")));
end M7buses;

