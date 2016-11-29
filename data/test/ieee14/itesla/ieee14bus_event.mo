within ;
model ieee14bus_event
  parameter Real SNREF = 100.0;
  Modelica.Blocks.Interfaces.RealOutput omegaRef;
  
// BUSES
  iPSL.Electrical.Buses.Bus bus__BUS___10_TN (
	 V_0 = 1.0509846,
	 angle_0 = -15.097279
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___11_TN (
	 V_0 = 1.0569065,
	 angle_0 = -14.790614
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___12_TN (
	 V_0 = 1.0551885,
	 angle_0 = -15.075579
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___13_TN (
	 V_0 = 1.0503817,
	 angle_0 = -15.15627
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___14_TN (
	 V_0 = 1.03553,
	 angle_0 = -16.033636
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS____1_TN (
	 V_0 = 1.06,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS____2_TN (
	 V_0 = 1.0450001,
	 angle_0 = -4.98258
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS____3_TN (
	 V_0 = 1.01,
	 angle_0 = -12.725089
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS____4_TN (
	 V_0 = 1.0176709,
	 angle_0 = -10.31289
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS____5_TN (
	 V_0 = 1.0195138,
	 angle_0 = -8.77384
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS____6_TN (
	 V_0 = 1.0699999,
	 angle_0 = -14.220942
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS____7_TN (
	 V_0 = 1.0615195,
	 angle_0 = -13.359616
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS____8_TN (
	 V_0 = 1.0899999,
	 angle_0 = -13.359616
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS____9_TN (
	 V_0 = 1.0559318,
	 angle_0 = -14.93851
	 ) annotation (Placement(transformation()));

// LOADS
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__10_EC (
	 V_0 = 1.0509846,
	 P_0 = 9.0,
	 Q_0 = 5.8,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -15.097279
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__11_EC (
	 V_0 = 1.0569065,
	 P_0 = 3.5,
	 Q_0 = 1.8,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -14.790614
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__12_EC (
	 V_0 = 1.0551885,
	 P_0 = 6.1,
	 Q_0 = 1.6,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -15.075579
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__13_EC (
	 V_0 = 1.0503817,
	 P_0 = 13.5,
	 Q_0 = 5.8,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -15.15627
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__14_EC (
	 V_0 = 1.03553,
	 P_0 = 14.9,
	 Q_0 = 5.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -16.033636
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD___2_EC (
	 V_0 = 1.0450001,
	 P_0 = 21.7,
	 Q_0 = 12.7,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -4.98258
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD___3_EC (
	 V_0 = 1.01,
	 P_0 = 94.2,
	 Q_0 = 19.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -12.725089
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD___4_EC (
	 V_0 = 1.0176709,
	 P_0 = 47.8,
	 Q_0 = -3.9,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -10.31289
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD___5_EC (
	 V_0 = 1.0195138,
	 P_0 = 7.6,
	 Q_0 = 1.6,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -8.77384
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD___6_EC (
	 V_0 = 1.0699999,
	 P_0 = 11.2,
	 Q_0 = 7.5,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -14.220942
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD___9_EC (
	 V_0 = 1.0559318,
	 P_0 = 29.5,
	 Q_0 = 16.6,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -14.93851
	 ) annotation (Placement(transformation()));

// TAP CHANGER TRANSFORMERS
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__BUS____4_BUS____7_1_PT (
	 r = 1.0224949,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.20911993960205447
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__BUS____4_BUS____9_1_PT (
	 r = 1.0319917,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.5561794406894949
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__BUS____5_BUS____6_1_PT (
	 r = 1.0729612,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.25202059995597903
	 ) annotation (Placement(transformation()));

// LINES
  iPSL.Electrical.Branches.PwLine_2 line__BUS___10_BUS___11_1_AC (
	 R = 0.082049996,
	 X = 0.19206995,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___12_BUS___13_1_AC (
	 R = 0.22091998,
	 X = 0.19987975,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___13_BUS___14_1_AC (
	 R = 0.17092995,
	 X = 0.34801987,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS____1_BUS____2_1_AC (
	 R = 0.019380003,
	 X = 0.059169922,
	 G = 0.0,
	 B = 0.026399983
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS____1_BUS____5_1_AC (
	 R = 0.054030035,
	 X = 0.22303928,
	 G = 0.0,
	 B = 0.024600087
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS____2_BUS____3_1_AC (
	 R = 0.046989918,
	 X = 0.19796997,
	 G = 0.0,
	 B = 0.021900006
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS____2_BUS____4_1_AC (
	 R = 0.05811006,
	 X = 0.17632009,
	 G = 0.0,
	 B = 0.017000008
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS____2_BUS____5_1_AC (
	 R = 0.05695001,
	 X = 0.17388007,
	 G = 0.0,
	 B = 0.017299999
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS____3_BUS____4_1_AC (
	 R = 0.06701008,
	 X = 0.17103004,
	 G = 0.0,
	 B = 0.006399998
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS____4_BUS____5_1_AC (
	 R = 0.013349989,
	 X = 0.04211006,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS____6_BUS___11_1_AC (
	 R = 0.094980046,
	 X = 0.19889992,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS____6_BUS___12_1_AC (
	 R = 0.122910105,
	 X = 0.25581023,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS____6_BUS___13_1_AC (
	 R = 0.066149965,
	 X = 0.1302699,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS____7_BUS____8_1_AC (
	 R = 0.0,
	 X = 0.17614998,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS____7_BUS____9_1_AC (
	 R = 0.0,
	 X = 0.110009976,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS____9_BUS___10_1_AC (
	 R = 0.03181002,
	 X = 0.08450011,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS____9_BUS___14_1_AC (
	 R = 0.12710984,
	 X = 0.27038017,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));

// CAPACITORS
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__BANK___9_SC (
	 B = 0.19000009587800412,
	 nsteps = 1
	 ) annotation (Placement(transformation()));

// FIXED INJECTIONS

// GENERATORS
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN____1_SM (
	 SNREF = SNREF, 
	 ur0 = 1.059999942779541, 
	 ui0 = 0.0, 
	 transformerIncluded = true, 
	 V2 = 69.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.3096031614131992, 
	 init_omega = 1.0, 
	 init_efd = 0.4480351212986738, 
	 WLMDVPu = 0.6296504227510634, 
	 init_lambdaad = -1.023044579351431, 
	 init_cm = 0.2133071547011774, 
	 init_lambdaq1 = 0.2698206705674612, 
	 init_lambdaq2 = 0.2698206705674612, 
	 init_iq = 2.135720204229346, 
	 init_id = 0.5192745537248935, 
	 init_lambdaaq = 0.2698206705674612, 
	 init_lambdad = -1.023044579351431, 
	 init_lambdaf = -1.182769050957867,
	 PNALT = 1090.0,
	 IENR = 4,
	 DIn = 0.0,
	 HIn = 5.4,
	 TSD0 = 0.08,
	 XPD = 0.384,
	 RTfoPu = 0.0,
	 TPD0 = 8.094,
	 XTfoPu = 0.1,
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
	 U2N = 69.0,
	 SNtfo = 1211.0,
	 lStatIn = 0.202,
	 XSQ = 0.262,
	 snq = 6.995,
	 XQ = 2.22,
	 PN = 1090.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN____2_SM (
	 SNREF = SNREF, 
	 ur0 = 1.0410511778511233, 
	 ui0 = -0.09076125026210298, 
	 transformerIncluded = true, 
	 V2 = 69.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = -0.01411203343905279, 
	 init_omega = 1.0, 
	 init_efd = 0.3870929434185773, 
	 WLMDVPu = 0.717262019773382, 
	 init_lambdaad = -1.054974167048663, 
	 init_cm = 0.03969266670610475, 
	 init_lambdaq1 = 0.06619350339053851, 
	 init_lambdaq2 = 0.06619350339053851, 
	 init_iq = 0.3514214633588694, 
	 init_id = 0.4435709443305758, 
	 init_lambdaaq = 0.06619350339053851, 
	 init_lambdad = -1.054974167048663, 
	 init_lambdaf = -1.173062813472578,
	 PNALT = 1008.0,
	 IENR = 4,
	 DIn = 0.0,
	 HIn = 5.4,
	 TSD0 = 0.058,
	 XPD = 0.407,
	 RTfoPu = 0.0,
	 TPD0 = 9.651,
	 XTfoPu = 0.1,
	 rStatIn = 0.00357,
	 U1N = 24.0,
	 md = 0.084,
	 XPQ = 0.454,
	 TSQ0 = 0.06,
	 SN = 1120.0,
	 V1 = 24.0,
	 TPQ0 = 1.009,
	 mq = 0.084,
	 XSD = 0.3,
	 snd = 5.57,
	 XD = 2.57,
	 U2N = 69.0,
	 SNtfo = 1120.0,
	 lStatIn = 0.219,
	 XSQ = 0.301,
	 snq = 5.57,
	 XQ = 2.57,
	 PN = 1008.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN____3_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9851925556543035, 
	 ui0 = -0.22247608639833844, 
	 transformerIncluded = true, 
	 V2 = 69.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = -0.2221400334734409, 
	 init_omega = 1.0, 
	 init_efd = 0.3162823106524267, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -1.015356615640392, 
	 init_cm = 7.949263871383294e-07, 
	 init_lambdaq1 = 1.517656197649605e-06, 
	 init_lambdaq2 = 1.517656197649605e-06, 
	 init_iq = 1.125502844448456e-05, 
	 init_id = 0.2482704971386382, 
	 init_lambdaaq = 1.517656197649605e-06, 
	 init_lambdad = -1.015356615640392, 
	 init_lambdaf = -1.145486836345435,
	 PNALT = 1485.0,
	 IENR = 4,
	 DIn = 0.0,
	 HIn = 5.625,
	 TSD0 = 0.065,
	 XPD = 0.509,
	 RTfoPu = 0.0,
	 TPD0 = 10.041,
	 XTfoPu = 0.1,
	 rStatIn = 0.00316,
	 U1N = 20.0,
	 md = 0.05,
	 XPQ = 0.601,
	 TSQ0 = 0.094,
	 SN = 1650.0,
	 V1 = 20.0,
	 TPQ0 = 1.22,
	 mq = 0.05,
	 XSD = 0.354,
	 snd = 9.285,
	 XD = 2.81,
	 U2N = 69.0,
	 SNtfo = 1650.0,
	 lStatIn = 0.256,
	 XSQ = 0.377,
	 snq = 9.285,
	 XQ = 2.62,
	 PN = 1485.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN____6_SM (
	 SNREF = SNREF, 
	 ur0 = 1.0372104555025565, 
	 ui0 = -0.26285799994630404, 
	 transformerIncluded = false, 
	 Saturated = true, 
	 TX = 0, 
	 XPQ = 0, 
	 TPQ0 = 0, 
	 init_theta = -0.2487346750983619, 
	 init_omega = 1.0, 
	 init_efd = 0.8960080751315095, 
	 WLMDVPu = 0.3981683145836558, 
	 init_lambdaad = -1.085170163897017, 
	 init_cm = 9.858259130886377e-05, 
	 init_lambdaq1 = 1.713217235642941e-05, 
	 init_lambdaq2 = 1.713217235642941e-05, 
	 init_iq = 6.334849726841174e-05, 
	 init_id = 0.1189809064468753, 
	 init_lambdaaq = 1.713217235642941e-05, 
	 init_lambdad = -1.085170163897017, 
	 init_lambdaf = -1.448401429738909,
	 PNALT = 74.4,
	 IENR = 3,
	 DIn = 0.0,
	 HIn = 4.975,
	 TSD0 = 0.04,
	 XPD = 0.225,
	 TPD0 = 3.0,
	 rStatIn = 0.004,
	 md = 0.16,
	 TSQ0 = 0.06,
	 SN = 80.0,
	 mq = 0.16,
	 XSD = 0.154,
	 snd = 5.7,
	 XD = 0.75,
	 lStatIn = 0.102,
	 XSQ = 0.154,
	 snq = 5.7,
	 XQ = 0.45,
	 PN = 71.8,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN____8_SM (
	 SNREF = SNREF, 
	 ur0 = 1.0605034046672526, 
	 ui0 = -0.2518577804600687, 
	 transformerIncluded = true, 
	 V2 = 13.8, 
	 Saturated = false, 
	 TX = 0, 
	 md = 0, 
	 XPQ = 0, 
	 TPQ0 = 0, 
	 mq = 0, 
	 snd = 0, 
	 snq = 0, 
	 init_theta = -0.2333922033666446, 
	 init_omega = 1.0, 
	 init_efd = 0.5625259180529054, 
	 WLMDVPu = 0.6682075874254081, 
	 init_lambdaad = -1.103581301302643, 
	 init_cm = 1.728352681394868e-05, 
	 init_lambdaq1 = 1.268666954691192e-05, 
	 init_lambdaq2 = 1.268666954691192e-05, 
	 init_iq = 3.6041674849073e-05, 
	 init_id = 0.1616828160538296, 
	 init_lambdaaq = 1.268666954691192e-05, 
	 init_lambdad = -1.103581301302643, 
	 init_lambdaf = -1.303705582368028,
	 PNALT = 228.0,
	 IENR = 3,
	 DIn = 0.0,
	 HIn = 2.748,
	 TSD0 = 0.096,
	 XPD = 0.31,
	 RTfoPu = 0.0,
	 TPD0 = 8.4,
	 XTfoPu = 0.1,
	 rStatIn = 0.004,
	 U1N = 18.0,
	 TSQ0 = 0.1,
	 SN = 250.0,
	 V1 = 18.0,
	 XSD = 0.275,
	 XD = 1.53,
	 U2N = 13.8,
	 SNtfo = 250.0,
	 lStatIn = 0.11,
	 XSQ = 0.346,
	 XQ = 0.99,
	 PN = 242.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));

// REGULATORS
  iPSL.Electrical.Controls.Eurostag.pssi3e2b reg_pssi3e2b__GEN____1_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_APREF = 0.1919019488026425,
	 T4 = 0.015,
	 T6 = 0.,
	 VSTMIN = -0.1,
	 T7 = 2.,
	 T8 = 0.,
	 T9 = 0.,
	 KS1 = 10.,
	 VSI2MAX = 999.,
	 VSI1MAX = 999.,
	 KS3 = 1.,
	 KS2 = 0.1564,
	 TW2 = 2.,
	 TW1 = 2.,
	 TW3 = 2.,
	 T10 = 0.,
	 VSTMAX = 0.1,
	 T11 = 0.,
	 VSI2MIN = -999.,
	 VSI1MIN = -999.,
	 T1 = 0.25,
	 T2 = 0.03,
	 T3 = .1500000
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.sexs reg_sexs__GEN____1_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_EFD = 0.4480351212986738,
	 init_VREF = 1.061105653259221,
	 init_YLL = 0.002240175606493369,
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
  iPSL.Electrical.Controls.Eurostag.gsteam0 reg_gsteam0__GEN____1_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_REF = 0.01066535773505887,
	 init_PMECH = 0.2133071547011774,
	 init_CM = 0.2133071547011774,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.pssi3e2b reg_pssi3e2b__GEN____2_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_APREF = 0.03571428571428572,
	 T4 = 0.015,
	 T6 = 0.,
	 VSTMIN = -0.1,
	 T7 = 2.,
	 T8 = 0.,
	 T9 = 0.,
	 KS1 = 10.,
	 VSI2MAX = 999.,
	 VSI1MAX = 999.,
	 KS3 = 1.,
	 KS2 = 0.1564,
	 TW2 = 2.,
	 TW1 = 2.,
	 TW3 = 2.,
	 T10 = 0.,
	 VSTMAX = 0.1,
	 T11 = 0.,
	 VSI2MIN = -999.,
	 VSI1MIN = -999.,
	 T1 = 0.25,
	 T2 = 0.03,
	 T3 = .1500000
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.sexs reg_sexs__GEN____2_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_EFD = 0.3870929434185773,
	 init_VREF = 1.050662679920702,
	 init_YLL = 0.001935464717092887,
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
  iPSL.Electrical.Controls.Eurostag.gsteam0 reg_gsteam0__GEN____2_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_REF = 0.001984633335305238,
	 init_PMECH = 0.03969266670610475,
	 init_CM = 0.03969266670610475,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.pssi3e2b reg_pssi3e2b__GEN____3_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_APREF = -4.20539024479226e-19,
	 T4 = 0.015,
	 T6 = 0.,
	 VSTMIN = -0.1,
	 T7 = 2.,
	 T8 = 0.,
	 T9 = 0.,
	 KS1 = 10.,
	 VSI2MAX = 999.,
	 VSI1MAX = 999.,
	 KS3 = 1.,
	 KS2 = 0.1564,
	 TW2 = 2.,
	 TW1 = 2.,
	 TW3 = 2.,
	 T10 = 0.,
	 VSTMAX = 0.1,
	 T11 = 0.,
	 VSI2MIN = -999.,
	 VSI1MIN = -999.,
	 T1 = 0.25,
	 T2 = 0.03,
	 T3 = .1500000
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.sexs reg_sexs__GEN____3_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_EFD = 0.3162823106524267,
	 init_VREF = 1.013086071697693,
	 init_YLL = 0.001581411553262134,
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
  iPSL.Electrical.Controls.Eurostag.gsteam0 reg_gsteam0__GEN____3_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_REF = 3.974631935691647e-08,
	 init_PMECH = 7.949263871383294e-07,
	 init_CM = 7.949263871383294e-07,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.pssi3e2b reg_pssi3e2b__GEN____6_SM (
	 SNREF = 100.0,
	 SN = 80.0,
	 PN = 71.8,
	 PNALT = 74.4,
	 init_APREF = 4.336808689942018e-18,
	 T4 = 0.015,
	 T6 = 0.,
	 VSTMIN = -0.1,
	 T7 = 2.,
	 T8 = 0.,
	 T9 = 0.,
	 KS1 = 10.,
	 VSI2MAX = 999.,
	 VSI1MAX = 999.,
	 KS3 = 1.,
	 KS2 = 0.1564,
	 TW2 = 2.,
	 TW1 = 2.,
	 TW3 = 2.,
	 T10 = 0.,
	 VSTMAX = 0.1,
	 T11 = 0.,
	 VSI2MIN = -999.,
	 VSI1MIN = -999.,
	 T1 = 0.25,
	 T2 = 0.03,
	 T3 = .1500000
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.sexs reg_sexs__GEN____6_SM (
	 SNREF = 100.0,
	 SN = 80.0,
	 PN = 71.8,
	 PNALT = 74.4,
	 init_EFD = 0.8960080751315095,
	 init_VREF = 1.074479973618456,
	 init_YLL = 0.004480040375657548,
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
  iPSL.Electrical.Controls.Eurostag.gsteam0 reg_gsteam0__GEN____6_SM (
	 SNREF = 100.0,
	 SN = 80.0,
	 PN = 71.8,
	 PNALT = 74.4,
	 init_REF = 4.929129565443189e-06,
	 init_PMECH = 9.858259130886377e-05,
	 init_CM = 9.858259130886377e-05,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.pssi3e2b reg_pssi3e2b__GEN____8_SM (
	 SNREF = 100.0,
	 SN = 250.0,
	 PN = 242.0,
	 PNALT = 228.0,
	 init_APREF = 2.775557561562892e-18,
	 T4 = 0.015,
	 T6 = 0.,
	 VSTMIN = -0.1,
	 T7 = 2.,
	 T8 = 0.,
	 T9 = 0.,
	 KS1 = 10.,
	 VSI2MAX = 999.,
	 VSI1MAX = 999.,
	 KS3 = 1.,
	 KS2 = 0.1564,
	 TW2 = 2.,
	 TW1 = 2.,
	 TW3 = 2.,
	 T10 = 0.,
	 VSTMAX = 0.1,
	 T11 = 0.,
	 VSI2MIN = -999.,
	 VSI1MIN = -999.,
	 T1 = 0.25,
	 T2 = 0.03,
	 T3 = .1500000
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.sexs reg_sexs__GEN____8_SM (
	 SNREF = 100.0,
	 SN = 250.0,
	 PN = 242.0,
	 PNALT = 228.0,
	 init_EFD = 0.5625259180529054,
	 init_VREF = 1.099279856562415,
	 init_YLL = 0.002812629590264527,
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
  iPSL.Electrical.Controls.Eurostag.gsteam0 reg_gsteam0__GEN____8_SM (
	 SNREF = 100.0,
	 SN = 250.0,
	 PN = 242.0,
	 PNALT = 228.0,
	 init_REF = 8.641763406974342e-07,
	 init_PMECH = 1.728352681394868e-05,
	 init_CM = 1.728352681394868e-05,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
iPSL.Electrical.Events.PwFault pwFault(R=0.5,X=0.5,t1=1.,t2=1.2);
equation
omegaRef = (gen_pwGeneratorM2S__GEN____1_SM.omega*gen_pwGeneratorM2S__GEN____1_SM.SN*gen_pwGeneratorM2S__GEN____1_SM.HIn + gen_pwGeneratorM2S__GEN____2_SM.omega*gen_pwGeneratorM2S__GEN____2_SM.SN*gen_pwGeneratorM2S__GEN____2_SM.HIn + gen_pwGeneratorM2S__GEN____3_SM.omega*gen_pwGeneratorM2S__GEN____3_SM.SN*gen_pwGeneratorM2S__GEN____3_SM.HIn + gen_pwGeneratorM2S__GEN____6_SM.omega*gen_pwGeneratorM2S__GEN____6_SM.SN*gen_pwGeneratorM2S__GEN____6_SM.HIn + gen_pwGeneratorM2S__GEN____8_SM.omega*gen_pwGeneratorM2S__GEN____8_SM.SN*gen_pwGeneratorM2S__GEN____8_SM.HIn) / (gen_pwGeneratorM2S__GEN____1_SM.SN*gen_pwGeneratorM2S__GEN____1_SM.HIn + gen_pwGeneratorM2S__GEN____2_SM.SN*gen_pwGeneratorM2S__GEN____2_SM.HIn + gen_pwGeneratorM2S__GEN____3_SM.SN*gen_pwGeneratorM2S__GEN____3_SM.HIn + gen_pwGeneratorM2S__GEN____6_SM.SN*gen_pwGeneratorM2S__GEN____6_SM.HIn + gen_pwGeneratorM2S__GEN____8_SM.SN*gen_pwGeneratorM2S__GEN____8_SM.HIn);

  connect(gen_pwGeneratorM2S__GEN____1_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN____2_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN____3_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN____6_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN____8_SM.omegaRef, omegaRef);

// Connecting REGULATORS and MACHINES
  connect(reg_pssi3e2b__GEN____1_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN____1_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN____1_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____1_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN____1_SM.pin_EFD, gen_pwGeneratorM2S__GEN____1_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN____1_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN____1_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN____1_SM.pin_CM, gen_pwGeneratorM2S__GEN____1_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN____1_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____1_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____2_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN____2_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN____2_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____2_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN____2_SM.pin_EFD, gen_pwGeneratorM2S__GEN____2_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN____2_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN____2_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN____2_SM.pin_CM, gen_pwGeneratorM2S__GEN____2_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN____2_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____2_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____3_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN____3_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN____3_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____3_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN____3_SM.pin_EFD, gen_pwGeneratorM2S__GEN____3_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN____3_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN____3_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN____3_SM.pin_CM, gen_pwGeneratorM2S__GEN____3_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN____3_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____3_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____6_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN____6_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN____6_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____6_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN____6_SM.pin_EFD, gen_pwGeneratorM2S__GEN____6_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN____6_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN____6_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN____6_SM.pin_CM, gen_pwGeneratorM2S__GEN____6_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN____6_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____6_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____8_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN____8_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN____8_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____8_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN____8_SM.pin_EFD, gen_pwGeneratorM2S__GEN____8_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN____8_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN____8_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN____8_SM.pin_CM, gen_pwGeneratorM2S__GEN____8_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN____8_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____8_SM.pin_OMEGA) annotation (Line());

// Connecting REGULATORS and REGULATORS
  connect(reg_pssi3e2b__GEN____1_SM.pin_VS, reg_sexs__GEN____1_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN____1_SM.pin_OMEGA, reg_gsteam0__GEN____1_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____2_SM.pin_VS, reg_sexs__GEN____2_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN____2_SM.pin_OMEGA, reg_gsteam0__GEN____2_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____3_SM.pin_VS, reg_sexs__GEN____3_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN____3_SM.pin_OMEGA, reg_gsteam0__GEN____3_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____6_SM.pin_VS, reg_sexs__GEN____6_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN____6_SM.pin_OMEGA, reg_gsteam0__GEN____6_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____8_SM.pin_VS, reg_sexs__GEN____8_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN____8_SM.pin_OMEGA, reg_gsteam0__GEN____8_SM.pin_OMEGA) annotation (Line());

// Connecting LINES
  connect(bus__BUS___10_TN.p, line__BUS___10_BUS___11_1_AC.p) annotation (Line());
  connect(line__BUS___10_BUS___11_1_AC.n, bus__BUS___11_TN.p) annotation (Line());
  connect(bus__BUS___12_TN.p, line__BUS___12_BUS___13_1_AC.p) annotation (Line());
  connect(line__BUS___12_BUS___13_1_AC.n, bus__BUS___13_TN.p) annotation (Line());
  connect(bus__BUS___13_TN.p, line__BUS___13_BUS___14_1_AC.p) annotation (Line());
  connect(line__BUS___13_BUS___14_1_AC.n, bus__BUS___14_TN.p) annotation (Line());
  connect(bus__BUS____1_TN.p, line__BUS____1_BUS____2_1_AC.p) annotation (Line());
  connect(line__BUS____1_BUS____2_1_AC.n, bus__BUS____2_TN.p) annotation (Line());
  connect(bus__BUS____1_TN.p, line__BUS____1_BUS____5_1_AC.p) annotation (Line());
  connect(line__BUS____1_BUS____5_1_AC.n, bus__BUS____5_TN.p) annotation (Line());
  connect(bus__BUS____2_TN.p, line__BUS____2_BUS____3_1_AC.p) annotation (Line());
  connect(line__BUS____2_BUS____3_1_AC.n, bus__BUS____3_TN.p) annotation (Line());
  connect(bus__BUS____2_TN.p, line__BUS____2_BUS____4_1_AC.p) annotation (Line());
  connect(line__BUS____2_BUS____4_1_AC.n, bus__BUS____4_TN.p) annotation (Line());
  connect(bus__BUS____2_TN.p, line__BUS____2_BUS____5_1_AC.p) annotation (Line());
  connect(line__BUS____2_BUS____5_1_AC.n, bus__BUS____5_TN.p) annotation (Line());
  connect(bus__BUS____3_TN.p, line__BUS____3_BUS____4_1_AC.p) annotation (Line());
  connect(line__BUS____3_BUS____4_1_AC.n, bus__BUS____4_TN.p) annotation (Line());
  connect(bus__BUS____4_TN.p, line__BUS____4_BUS____5_1_AC.p) annotation (Line());
  connect(line__BUS____4_BUS____5_1_AC.n, bus__BUS____5_TN.p) annotation (Line());
  connect(bus__BUS____6_TN.p, line__BUS____6_BUS___11_1_AC.p) annotation (Line());
  connect(line__BUS____6_BUS___11_1_AC.n, bus__BUS___11_TN.p) annotation (Line());
  connect(bus__BUS____6_TN.p, line__BUS____6_BUS___12_1_AC.p) annotation (Line());
  connect(line__BUS____6_BUS___12_1_AC.n, bus__BUS___12_TN.p) annotation (Line());
  connect(bus__BUS____6_TN.p, line__BUS____6_BUS___13_1_AC.p) annotation (Line());
  connect(line__BUS____6_BUS___13_1_AC.n, bus__BUS___13_TN.p) annotation (Line());
  connect(bus__BUS____7_TN.p, line__BUS____7_BUS____8_1_AC.p) annotation (Line());
  connect(line__BUS____7_BUS____8_1_AC.n, bus__BUS____8_TN.p) annotation (Line());
  connect(bus__BUS____7_TN.p, line__BUS____7_BUS____9_1_AC.p) annotation (Line());
  connect(line__BUS____7_BUS____9_1_AC.n, bus__BUS____9_TN.p) annotation (Line());
  connect(bus__BUS____9_TN.p, line__BUS____9_BUS___10_1_AC.p) annotation (Line());
  connect(line__BUS____9_BUS___10_1_AC.n, bus__BUS___10_TN.p) annotation (Line());
  connect(bus__BUS____9_TN.p, line__BUS____9_BUS___14_1_AC.p) annotation (Line());
  connect(line__BUS____9_BUS___14_1_AC.n, bus__BUS___14_TN.p) annotation (Line());

// COUPLING DEVICES

// Connecting LOADS
  connect(bus__BUS___10_TN.p, load__LOAD__10_EC.p) annotation (Line());
  connect(bus__BUS___11_TN.p, load__LOAD__11_EC.p) annotation (Line());
  connect(bus__BUS___12_TN.p, load__LOAD__12_EC.p) annotation (Line());
  connect(bus__BUS___13_TN.p, load__LOAD__13_EC.p) annotation (Line());
  connect(bus__BUS___14_TN.p, load__LOAD__14_EC.p) annotation (Line());
  connect(bus__BUS____2_TN.p, load__LOAD___2_EC.p) annotation (Line());
  connect(bus__BUS____3_TN.p, load__LOAD___3_EC.p) annotation (Line());
  connect(bus__BUS____4_TN.p, load__LOAD___4_EC.p) annotation (Line());
  connect(bus__BUS____5_TN.p, load__LOAD___5_EC.p) annotation (Line());
  connect(bus__BUS____6_TN.p, load__LOAD___6_EC.p) annotation (Line());
  connect(bus__BUS____9_TN.p, load__LOAD___9_EC.p) annotation (Line());

// Connecting Capacitors
  connect(bus__BUS____9_TN.p, cap_pwCapacitorBank__BANK___9_SC.p) annotation (Line());

// Connecting GENERATORS
  connect(bus__BUS____1_TN.p, gen_pwGeneratorM2S__GEN____1_SM.sortie) annotation (Line());
  connect(bus__BUS____2_TN.p, gen_pwGeneratorM2S__GEN____2_SM.sortie) annotation (Line());
  connect(bus__BUS____3_TN.p, gen_pwGeneratorM2S__GEN____3_SM.sortie) annotation (Line());
  connect(bus__BUS____6_TN.p, gen_pwGeneratorM2S__GEN____6_SM.sortie) annotation (Line());
  connect(bus__BUS____8_TN.p, gen_pwGeneratorM2S__GEN____8_SM.sortie) annotation (Line());

// Connecting DETAILED TRANSFORMERS
  connect(bus__BUS____4_TN.p, trafo__BUS____4_BUS____7_1_PT.p) annotation (Line());
  connect(trafo__BUS____4_BUS____7_1_PT.n, bus__BUS____7_TN.p) annotation (Line());
  connect(bus__BUS____4_TN.p, trafo__BUS____4_BUS____9_1_PT.p) annotation (Line());
  connect(trafo__BUS____4_BUS____9_1_PT.n, bus__BUS____9_TN.p) annotation (Line());
  connect(bus__BUS____5_TN.p, trafo__BUS____5_BUS____6_1_PT.p) annotation (Line());
  connect(trafo__BUS____5_BUS____6_1_PT.n, bus__BUS____6_TN.p) annotation (Line());

// Connecting OTHERS
connect(bus__BUS____4_TN.p, pwFault.p);
end ieee14bus_event;

