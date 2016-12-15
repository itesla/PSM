within ;
model CIM_7buses
  parameter Real SNREF = 100.0;
  Modelica.Blocks.Interfaces.RealOutput omegaRef;


// BUSES
  iPSL.Electrical.Buses.Bus bus__N0000000_TN (
	 V_0 = 1.0695947,
	 angle_0 = -0.0763
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N0000001_TN (
	 V_0 = 1.0696054,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N0000002_TN (
	 V_0 = 1.0695841,
	 angle_0 = -0.09711
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N0000003_TN (
	 V_0 = 1.0695921,
	 angle_0 = -0.11098
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N0000004_TN (
	 V_0 = 1.069579,
	 angle_0 = -0.09017
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N0000005_TN (
	 V_0 = 1.0696054,
	 angle_0 = -0.06243
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N0000006_TN (
	 V_0 = 1.0696054,
	 angle_0 = -0.09365
	 ) annotation (Placement(transformation()));

// LOADS
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__L0000000_EC (
	 V_0 = 1.0695947,
	 P_0 = 240.0,
	 Q_0 = 2.4,
	 alpha = 1,
	 beta = 2,
	 angle_0 = -0.0763
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__L0000001_EC (
	 V_0 = 1.0695841,
	 P_0 = 240.0,
	 Q_0 = 2.4,
	 alpha = 1,
	 beta = 2,
	 angle_0 = -0.09711
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__L0000002_EC (
	 V_0 = 1.0695921,
	 P_0 = 480.0,
	 Q_0 = 4.8,
	 alpha = 1,
	 beta = 2,
	 angle_0 = -0.11098
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__L0000003_EC (
	 V_0 = 1.069579,
	 P_0 = 480.0,
	 Q_0 = 4.8,
	 alpha = 1,
	 beta = 2,
	 angle_0 = -0.09017
	 ) annotation (Placement(transformation()));

// TAP CHANGER TRANSFORMERS
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__N0000003_N0000000_1_PT (
	 r = 0.99994993,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.5729387,
	 R = 6.929986034403878E-6,
	 X = 6.925553826413987E-4
	 ) annotation (Placement(transformation()));

// LINES
  iPSL.Electrical.Branches.PwLine_2 line__N0000000_N0000001_1_AC (
	 R = 6.9999996E-6,
	 X = 6.929986E-4,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N0000000_N0000001_2_AC (
	 R = 6.9999996E-6,
	 X = 6.929986E-4,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N0000002_N0000004_1_AC (
	 R = 6.9999996E-6,
	 X = 6.929986E-4,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N0000002_N0000004_2_AC (
	 R = 6.9999996E-6,
	 X = 6.929986E-4,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N0000003_N0000005_1_AC (
	 R = 6.9999996E-6,
	 X = 6.929986E-4,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N0000003_N0000005_2_AC (
	 R = 6.9999996E-6,
	 X = 6.929986E-4,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N0000004_N0000001_1_AC (
	 R = 6.9999996E-6,
	 X = 6.929986E-4,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N0000004_N0000001_2_AC (
	 R = 6.9999996E-6,
	 X = 6.929986E-4,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N0000005_N0000002_1_AC (
	 R = 6.9999996E-6,
	 X = 6.929986E-4,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N0000005_N0000002_2_AC (
	 R = 6.9999996E-6,
	 X = 6.929986E-4,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N0000006_N0000000_1_AC (
	 R = 6.9999996E-6,
	 X = 6.929986E-4,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N0000006_N0000000_2_AC (
	 R = 6.9999996E-6,
	 X = 6.929986E-4,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N0000006_N0000003_1_AC (
	 R = 6.9999996E-6,
	 X = 6.929986E-4,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N0000006_N0000003_2_AC (
	 R = 6.9999996E-6,
	 X = 6.929986E-4,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));

// FIXED INJECTIONS

// GENERATORS
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__G0000000_SM (
	 SNREF = SNREF, 
	 ur0 = 1.0696053504943848, 
	 ui0 = 0.0, 
	 transformerIncluded = true, 
	 V2 = 380.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 1.144553712871937, 
	 init_omega = 1.0, 
	 init_efd = 0.8440509136082385, 
	 WLMDVPu = 0.7226456114257552, 
	 init_lambdaad = -0.7594306613102328, 
	 init_cm = 0.7944881870885486, 
	 init_lambdaq1 = 0.8372712653129762, 
	 init_lambdaq2 = 0.8372712653129762, 
	 init_iq = 3.690875024946076, 
	 init_id = 8.18141992435039, 
	 init_lambdaaq = 0.8372712653129762, 
	 init_lambdad = -0.7594306613102328, 
	 init_lambdaf = -1.028988389837259,
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
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__G0000001_SM (
	 SNREF = SNREF, 
	 ur0 = 1.0696047155510324, 
	 ui0 = -0.0011654515051647814, 
	 transformerIncluded = true, 
	 V2 = 380.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.6451185143407717, 
	 init_omega = 1.0, 
	 init_efd = 0.387877612268465, 
	 WLMDVPu = 0.7448638277959367, 
	 init_lambdaad = -0.9327392251225896, 
	 init_cm = 0.3124316272405734, 
	 init_lambdaq1 = 0.5454821737424536, 
	 init_lambdaq2 = 0.5454821737424536, 
	 init_iq = 3.546512131873081, 
	 init_id = 2.750505585118621, 
	 init_lambdaaq = 0.5454821737424536, 
	 init_lambdad = -0.9327392251225896, 
	 init_lambdaf = -1.104440750231352,
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
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__G0000002_SM (
	 SNREF = SNREF, 
	 ur0 = 1.0696039217196838, 
	 ui0 = -0.0017482700272833341, 
	 transformerIncluded = true, 
	 V2 = 380.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = -0.001670093230545487, 
	 init_omega = 1.0, 
	 init_efd = 0.3908934470584032, 
	 WLMDVPu = 0.6686345878298443, 
	 init_lambdaad = -1.072049012096693, 
	 init_cm = 2.47529495362268e-07, 
	 init_lambdaq1 = 4.162020929713304e-07, 
	 init_lambdaq2 = 4.162020929713304e-07, 
	 init_iq = 2.489587079440315e-06, 
	 init_id = 0.06994710237568473, 
	 init_lambdaaq = 4.162020929713304e-07, 
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
  rqb_alst reg_rqb_alst__G0000000_SM (
	 SNREF = 100.0,
	 SN = 1078.0,
	 PN = 1215.0,
	 PNALT = 970.0,
	 init_DU = 0.0,
	 init_M = -314.157,
	 init_PUISS = 0.98970412371134,
	 init_VSINIT = 0.9898116246881475,
	 init_CM = 0.7944881870885486,
	 init_IEX = 0.82579315925396,
	 init_VCONSI = 0.9898116246881475,
	 init_OMEGA = 1.0,
	 init_EPS = -0.21220684074604,
	 init_DIFSGA = 65.57808994257014,
	 VFTM = 4.,
	 DELTAS = 87.84000,
	 DEUXPINU = 314.1570,
	 PLINP = 1.900000,
	 SIBV = 0.006,
	 TOMSL = 0.04,
	 KI1 = -16.8100,
	 KI0 = 12.72000,
	 KI3 = -11.4500,
	 KI2 = 3.430000,
	 KI5 = 16.17000,
	 VFSA = 5.,
	 KI4 = 11.86000,
	 LSAT = 5.730000,
	 VFNP = 1.038000,
	 KLUS = 50.03000,
	 MESU = 0.02,
	 T4F = 0.045,
	 KLIR = 12.13000,
	 UCMIN = .9000000,
	 LUS = 0.12,
	 T4M = 5.,
	 TC = 0.02,
	 TE = 0.22,
	 T4MOM = 1.270000,
	 KDPM = 0.185,
	 TOMD = 0.02,
	 VFTM2 = 30.,
	 FDV1 = 0.0125,
	 FDV2 = 0.0125,
	 UCMAX = 1.100000,
	 TQ = 0.04
	 ) annotation (Placement(transformation()));
  edftur1c reg_edftur1c__G0000000_SM (
	 SNREF = 100.0,
	 SN = 1078.0,
	 PN = 1215.0,
	 PNALT = 970.0,
	 init_DELTAPE = -0.004354030709947998,
	 init_NIVP = 0.0,
	 init_Z = 0.7944881870885486,
	 init_CM = 0.7944881870885486,
	 init_C = 0.4413823261603048,
	 init_PVISEE = 965.3031473125866,
	 init_PCONSI = 0.7944881870885486,
	 init_OVHP = 0.7944881870885486,
	 init_B = 1.0,
	 PR = 0.05,
	 TVMP = 0.25,
	 SAS = -0.02,
	 PLIM = 0.025,
	 SACC = 0.024182,
	 ALPHA = 0.34,
	 PMAXSPN = 1.025641,
	 FHP = {0.,0.,0.5,.9000000,1.,1.050000},
	 PLM = 0.05,
	 VFHP = 3.600000,
	 RPP = 1.,
	 DELTMP = 0.02,
	 SCMP = 2.,
	 SRV = 0.015,
	 CFMP = 1.,
	 LSAT = 2.600000,
	 RTVP = 0.08,
	 REGFP = 1.,
	 THP = 0.02,
	 T60S = 60.,
	 TOHP = 0.07,
	 RMA = 0.1,
	 FMP = {0.,0.,0.5,.8500000,1.,1.},
	 DELTHP = 0.04,
	 TMA = 0.5,
	 VOHP = 4.5,
	 VRHP = .6500000,
	 TASP = 5.,
	 TVHP = .4000000,
	 TF = 0.085,
	 VOMP = 7.,
	 JASP = 0.,
	 TMP = 0.02,
	 SSV = 0.06,
	 VRMP = .8500000,
	 TOMP = 0.07,
	 TCONS = 3.,
	 VFMP = 3.5,
	 TR = 4.
	 ) annotation (Placement(transformation()));
  tkj_comp reg_tkj_comp__G0000000_SM (
	 SNREF = 100.0,
	 SN = 1078.0,
	 PN = 1215.0,
	 PNALT = 970.0,
	 init_EFD = 0.8440509136082385,
	 init_LA = 0.82579315925396,
	 init_KC = 0.224173636001593,
	 init_PHI = 0.4673409908648816,
	 RFE = 100.,
	 RC = 0.13,
	 SEB = 7.400000,
	 PLEXM = -3.46,
	 RIN = 0.25,
	 KDD = .4200000,
	 KE = .4900000,
	 PLEXP = 4.,
	 TDOEX = .4000000,
	 FIEEE = {0.,1.,0.433,0.7489,0.5,.7071000,.5500000,.6690000,.6000000,.6245000,.6500000,.5723000,.7000000,.5099000,0.75,0.433,1.,0.},
	 SEA = 1.100000
	 ) annotation (Placement(transformation()));
  consig reg_consig__G0000000_SM (
	 SNREF = 100.0,
	 SN = 1078.0,
	 PN = 1215.0,
	 PNALT = 970.0,
	 init_PV = 1.0,
	 ZERO = 0.
	 ) annotation (Placement(transformation()));
  rqb_alst reg_rqb_alst__G0000001_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_DU = 0.0,
	 init_M = -314.157,
	 init_PUISS = 0.3118947563352826,
	 init_VSINIT = 1.005961310391346,
	 init_CM = 0.3124316272405734,
	 init_IEX = 0.3762391598406951,
	 init_VCONSI = 1.005961310391346,
	 init_OMEGA = 1.0,
	 init_EPS = -0.616760840159305,
	 init_DIFSGA = 37.02499407830283,
	 VFTM = 4.,
	 DELTAS = 87.84000,
	 DEUXPINU = 314.1570,
	 PLINP = 2.,
	 SIBV = 0.006,
	 TOMSL = 0.04,
	 KI1 = -16.1400,
	 KI0 = 12.10000,
	 KI3 = -18.,
	 KI2 = 4.670000,
	 KI5 = 32.66000,
	 VFSA = 5.,
	 KI4 = 17.,
	 LSAT = 6.,
	 VFNP = .9930000,
	 KLUS = 52.40000,
	 MESU = 0.02,
	 T4F = 0.05,
	 KLIR = 12.70000,
	 UCMIN = .9000000,
	 LUS = 0.12,
	 T4M = 5.,
	 TC = 0.02,
	 TE = 0.22,
	 T4MOM = 1.270000,
	 KDPM = 0.17,
	 TOMD = 0.02,
	 VFTM2 = 30.,
	 FDV1 = 0.0125,
	 FDV2 = 0.0125,
	 UCMAX = 1.100000,
	 TQ = 0.04
	 ) annotation (Placement(transformation()));
  edftur2c reg_edftur2c__G0000001_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_DELTAPE = -0.0005368709052908094,
	 init_NIVP = 0.0,
	 init_Z = 0.3124316272405734,
	 init_CM = 0.3124316272405734,
	 init_C = 0.1874589763443441,
	 init_PVISEE = 480.8322743232425,
	 init_PCONSI = 0.3124316272405734,
	 init_OVHP = 0.3124316272405734,
	 init_B = 1.0,
	 PR = 0.05,
	 TVMP = 0.2,
	 SAS = -0.02,
	 PLIM = 0.025,
	 SACC = 0.03968,
	 ALPHA = 0.34,
	 PMAXSPN = 1.010000,
	 FHP = {0.,0.,.6000000,1.,1.,1.050000},
	 PLM = .3500000,
	 VFHP = .3500000,
	 RPP = .9000000,
	 DELTMP = 0.025,
	 SCMP = 1.140000,
	 SRV = 0.015,
	 CFMP = .6500000,
	 LSAT = 2.600000,
	 RTVP = 0.015,
	 REGFP = 1.,
	 RTVP2 = 0.03,
	 THP = 0.25,
	 T60S = 60.,
	 TOHP = 0.17,
	 RMA = 0.1,
	 FMP = {0.,0.,0.5,.8500000,1.,1.},
	 DELTHP = 0.04,
	 TMA = 0.5,
	 VOHP = 3.,
	 TASP = 10.,
	 TVHP = 0.2,
	 TF = 0.085,
	 TH = 0.13,
	 VOMP = 2.,
	 GAINLVA = 1.100000,
	 JASP = 0.,
	 TMP = .3000000,
	 SSV = 0.06,
	 TOMP = 1.270000,
	 TCONS = 3.,
	 VFMP = .8000000,
	 TR = 5.
	 ) annotation (Placement(transformation()));
  tkj_comp reg_tkj_comp__G0000001_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_EFD = 0.387877612268465,
	 init_LA = 0.3762391598406951,
	 init_KC = 0.3638510553564316,
	 init_PHI = 0.2146519433248997,
	 RFE = 100.,
	 RC = 0.211,
	 SEB = 5.170000,
	 PLEXM = -4.,
	 RIN = 0.25,
	 KDD = .4160000,
	 KE = .4570000,
	 PLEXP = 4.600000,
	 TDOEX = .4500000,
	 FIEEE = {0.,1.,0.433,0.7489,0.5,.7071000,.5500000,.6690000,.6000000,.6245000,.6500000,.5723000,.7000000,.5099000,0.75,0.433,1.,0.},
	 SEA = .6560000
	 ) annotation (Placement(transformation()));
  consig reg_consig__G0000001_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_PV = 1.0,
	 ZERO = 0.
	 ) annotation (Placement(transformation()));
  edftur2c reg_edftur2c__G0000002_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_DELTAPE = -2.47529495362268e-07,
	 init_NIVP = 0.0,
	 init_Z = 2.765692424014242e-07,
	 init_CM = 2.47529495362268e-07,
	 init_C = 1.485176972173608e-07,
	 init_PVISEE = 0.0002698071499448721,
	 init_PCONSI = 2.47529495362268e-07,
	 init_OVHP = 2.47529495362268e-07,
	 init_B = 0.6500002821836247,
	 PR = 0.05,
	 TVMP = 0.2,
	 SAS = -0.02,
	 PLIM = 0.025,
	 SACC = 0.039683,
	 ALPHA = 0.34,
	 PMAXSPN = 1.,
	 FHP = {0.,0.,.6000000,1.,1.,1.050000},
	 PLM = .3500000,
	 VFHP = .3500000,
	 RPP = .9000000,
	 DELTMP = 0.025,
	 SCMP = 1.140000,
	 SRV = 0.015,
	 CFMP = .6500000,
	 LSAT = 2.600000,
	 RTVP = 0.015,
	 REGFP = 1.,
	 RTVP2 = 0.03,
	 THP = 0.25,
	 T60S = 60.,
	 TOHP = 0.17,
	 RMA = 0.1,
	 FMP = {0.,0.,0.5,.8500000,1.,1.},
	 DELTHP = 0.04,
	 TMA = 0.5,
	 VOHP = 3.,
	 TASP = 10.,
	 TVHP = 0.2,
	 TF = 0.085,
	 TH = 0.13,
	 VOMP = 2.,
	 GAINLVA = 1.100000,
	 JASP = 0.,
	 TMP = .3000000,
	 SSV = 0.06,
	 TOMP = 1.270000,
	 TCONS = 3.,
	 VFMP = .8000000,
	 TR = 5.
	 ) annotation (Placement(transformation()));
  sep_abb reg_sep_abb__G0000002_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_EFD = 0.3908934470584032,
	 init_DU = 0.0,
	 init_PUISS = 0.0,
	 init_VSINIT = 0.9803610282085626,
	 init_DIFFI = 13.16213105883194,
	 init_IEX = 0.3908934470584031,
	 init_VCONSI = 1.026675417670459,
	 init_DIFDELT = 0.0,
	 init_DIFSGA = -0.002039295578353487,
	 VFTM = 4.,
	 DELTAS = 88.02000,
	 LIMP = 0.14,
	 PLINP = 1.600000,
	 K1 = 8.440000,
	 VFSA = 5.,
	 VFNP = 1.049000,
	 T1 = 1.,
	 MESU = 0.02,
	 T2 = 2.100000,
	 KJ = 1.87,
	 T3 = 2.,
	 T4 = 4.,
	 T5 = 0.066,
	 GI = 20.,
	 T6 = 0.16,
	 KQ = 0.08,
	 KS = 3.540000,
	 UCMIN = 0.94,
	 TMI = 0.04,
	 TI = 0.03,
	 VFTM2 = 30.,
	 TJ = 11.40000,
	 FDV1 = 0.0125,
	 FDV2 = 0.0125,
	 UCMAX = 1.150000,
	 TQ = 0.04,
	 TS = 1.5
	 ) annotation (Placement(transformation()));
  ex_stat reg_ex_stat__G0000002_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_EFD = 0.3908934470584032,
	 init_IEX = 0.3908934470584031,
	 RFE = 200.,
	 TE = 0.02,
	 PLEXM = -1.6,
	 PLEXP = 3.
	 ) annotation (Placement(transformation()));
  consig reg_consig__G0000002_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_PV = 1.0,
	 ZERO = 0.
	 ) annotation (Placement(transformation()));

equation
  omegaRef = (gen_pwGeneratorM2S__G0000000_SM.omega*gen_pwGeneratorM2S__G0000000_SM.SN*gen_pwGeneratorM2S__G0000000_SM.HIn + gen_pwGeneratorM2S__G0000001_SM.omega*gen_pwGeneratorM2S__G0000001_SM.SN*gen_pwGeneratorM2S__G0000001_SM.HIn + gen_pwGeneratorM2S__G0000002_SM.omega*gen_pwGeneratorM2S__G0000002_SM.SN*gen_pwGeneratorM2S__G0000002_SM.HIn) / (gen_pwGeneratorM2S__G0000000_SM.SN*gen_pwGeneratorM2S__G0000000_SM.HIn + gen_pwGeneratorM2S__G0000001_SM.SN*gen_pwGeneratorM2S__G0000001_SM.HIn + gen_pwGeneratorM2S__G0000002_SM.SN*gen_pwGeneratorM2S__G0000002_SM.HIn);

  connect(gen_pwGeneratorM2S__G0000000_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__G0000001_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__G0000002_SM.omegaRef, omegaRef);

// Connecting REGULATORS and MACHINES
  connect(reg_rqb_alst__G0000000_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__G0000000_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_rqb_alst__G0000000_SM.pin_UI, gen_pwGeneratorM2S__G0000000_SM.pin_UI) annotation (Line());
  connect(reg_rqb_alst__G0000000_SM.pin_UR, gen_pwGeneratorM2S__G0000000_SM.pin_UR) annotation (Line());
  connect(reg_rqb_alst__G0000000_SM.pin_ActivePowerPNALT, gen_pwGeneratorM2S__G0000000_SM.pin_ActivePowerPNALT) annotation (Line());
  connect(reg_rqb_alst__G0000000_SM.pin_TETA, gen_pwGeneratorM2S__G0000000_SM.pin_TETA) annotation (Line());
  connect(reg_rqb_alst__G0000000_SM.pin_OMEGA, gen_pwGeneratorM2S__G0000000_SM.pin_OMEGA) annotation (Line());
  connect(reg_edftur1c__G0000000_SM.pin_ActivePowerPN, gen_pwGeneratorM2S__G0000000_SM.pin_ActivePowerPN) annotation (Line());
  connect(reg_edftur1c__G0000000_SM.pin_CM, gen_pwGeneratorM2S__G0000000_SM.pin_CM) annotation (Line());
  connect(reg_edftur1c__G0000000_SM.pin_OMEGA, gen_pwGeneratorM2S__G0000000_SM.pin_OMEGA) annotation (Line());
  connect(reg_tkj_comp__G0000000_SM.pin_EFD, gen_pwGeneratorM2S__G0000000_SM.pin_EFD) annotation (Line());
  connect(reg_tkj_comp__G0000000_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__G0000000_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_tkj_comp__G0000000_SM.pin_FieldCurrent, gen_pwGeneratorM2S__G0000000_SM.pin_FieldCurrent) annotation (Line());
  connect(reg_rqb_alst__G0000001_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__G0000001_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_rqb_alst__G0000001_SM.pin_UI, gen_pwGeneratorM2S__G0000001_SM.pin_UI) annotation (Line());
  connect(reg_rqb_alst__G0000001_SM.pin_UR, gen_pwGeneratorM2S__G0000001_SM.pin_UR) annotation (Line());
  connect(reg_rqb_alst__G0000001_SM.pin_ActivePowerPNALT, gen_pwGeneratorM2S__G0000001_SM.pin_ActivePowerPNALT) annotation (Line());
  connect(reg_rqb_alst__G0000001_SM.pin_TETA, gen_pwGeneratorM2S__G0000001_SM.pin_TETA) annotation (Line());
  connect(reg_rqb_alst__G0000001_SM.pin_OMEGA, gen_pwGeneratorM2S__G0000001_SM.pin_OMEGA) annotation (Line());
  connect(reg_edftur2c__G0000001_SM.pin_ActivePowerPN, gen_pwGeneratorM2S__G0000001_SM.pin_ActivePowerPN) annotation (Line());
  connect(reg_edftur2c__G0000001_SM.pin_CM, gen_pwGeneratorM2S__G0000001_SM.pin_CM) annotation (Line());
  connect(reg_edftur2c__G0000001_SM.pin_OMEGA, gen_pwGeneratorM2S__G0000001_SM.pin_OMEGA) annotation (Line());
  connect(reg_tkj_comp__G0000001_SM.pin_EFD, gen_pwGeneratorM2S__G0000001_SM.pin_EFD) annotation (Line());
  connect(reg_tkj_comp__G0000001_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__G0000001_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_tkj_comp__G0000001_SM.pin_FieldCurrent, gen_pwGeneratorM2S__G0000001_SM.pin_FieldCurrent) annotation (Line());
  connect(reg_edftur2c__G0000002_SM.pin_ActivePowerPN, gen_pwGeneratorM2S__G0000002_SM.pin_ActivePowerPN) annotation (Line());
  connect(reg_edftur2c__G0000002_SM.pin_CM, gen_pwGeneratorM2S__G0000002_SM.pin_CM) annotation (Line());
  connect(reg_edftur2c__G0000002_SM.pin_OMEGA, gen_pwGeneratorM2S__G0000002_SM.pin_OMEGA) annotation (Line());
  connect(reg_sep_abb__G0000002_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__G0000002_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_sep_abb__G0000002_SM.pin_UI, gen_pwGeneratorM2S__G0000002_SM.pin_UI) annotation (Line());
  connect(reg_sep_abb__G0000002_SM.pin_UR, gen_pwGeneratorM2S__G0000002_SM.pin_UR) annotation (Line());
  connect(reg_sep_abb__G0000002_SM.pin_ActivePowerPNALT, gen_pwGeneratorM2S__G0000002_SM.pin_ActivePowerPNALT) annotation (Line());
  connect(reg_sep_abb__G0000002_SM.pin_TETA, gen_pwGeneratorM2S__G0000002_SM.pin_TETA) annotation (Line());
  connect(reg_ex_stat__G0000002_SM.pin_EFD, gen_pwGeneratorM2S__G0000002_SM.pin_EFD) annotation (Line());
  connect(reg_ex_stat__G0000002_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__G0000002_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_ex_stat__G0000002_SM.pin_FieldCurrent, gen_pwGeneratorM2S__G0000002_SM.pin_FieldCurrent) annotation (Line());

// Connecting REGULATORS and REGULATORS
  connect(reg_rqb_alst__G0000000_SM.pin_PMTURHP, reg_edftur1c__G0000000_SM.pin_PMTURHP) annotation (Line());
  connect(reg_rqb_alst__G0000000_SM.pin_OMEGA, reg_edftur1c__G0000000_SM.pin_OMEGA) annotation (Line());
  connect(reg_rqb_alst__G0000000_SM.pin_TerminalVoltage, reg_tkj_comp__G0000000_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_rqb_alst__G0000000_SM.pin_IEX, reg_tkj_comp__G0000000_SM.pin_IEX) annotation (Line());
  connect(reg_rqb_alst__G0000000_SM.pin_SREGUL, reg_tkj_comp__G0000000_SM.pin_SREGUL) annotation (Line());
  connect(reg_rqb_alst__G0000000_SM.pin_APR, reg_consig__G0000000_SM.pin_APR) annotation (Line());
  connect(reg_edftur1c__G0000000_SM.pin_PV, reg_consig__G0000000_SM.pin_PV) annotation (Line());
  connect(reg_rqb_alst__G0000001_SM.pin_PMTURHP, reg_edftur2c__G0000001_SM.pin_PMTURHP) annotation (Line());
  connect(reg_rqb_alst__G0000001_SM.pin_OMEGA, reg_edftur2c__G0000001_SM.pin_OMEGA) annotation (Line());
  connect(reg_rqb_alst__G0000001_SM.pin_TerminalVoltage, reg_tkj_comp__G0000001_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_rqb_alst__G0000001_SM.pin_IEX, reg_tkj_comp__G0000001_SM.pin_IEX) annotation (Line());
  connect(reg_rqb_alst__G0000001_SM.pin_SREGUL, reg_tkj_comp__G0000001_SM.pin_SREGUL) annotation (Line());
  connect(reg_rqb_alst__G0000001_SM.pin_APR, reg_consig__G0000001_SM.pin_APR) annotation (Line());
  connect(reg_edftur2c__G0000001_SM.pin_PV, reg_consig__G0000001_SM.pin_PV) annotation (Line());
  connect(reg_consig__G0000002_SM.pin_PV, reg_edftur2c__G0000002_SM.pin_PV) annotation (Line());
  connect(reg_consig__G0000002_SM.pin_APR, reg_sep_abb__G0000002_SM.pin_APR) annotation (Line());
  connect(reg_sep_abb__G0000002_SM.pin_TerminalVoltage, reg_ex_stat__G0000002_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_sep_abb__G0000002_SM.pin_IEX, reg_ex_stat__G0000002_SM.pin_IEX) annotation (Line());
  connect(reg_sep_abb__G0000002_SM.pin_SREGUL, reg_ex_stat__G0000002_SM.pin_SREGUL) annotation (Line());

// Connecting LINES
  connect(bus__N0000000_TN.p, line__N0000000_N0000001_1_AC.p) annotation (Line());
  connect(line__N0000000_N0000001_1_AC.n, bus__N0000001_TN.p) annotation (Line());
  connect(bus__N0000000_TN.p, line__N0000000_N0000001_2_AC.p) annotation (Line());
  connect(line__N0000000_N0000001_2_AC.n, bus__N0000001_TN.p) annotation (Line());
  connect(bus__N0000002_TN.p, line__N0000002_N0000004_1_AC.p) annotation (Line());
  connect(line__N0000002_N0000004_1_AC.n, bus__N0000004_TN.p) annotation (Line());
  connect(bus__N0000002_TN.p, line__N0000002_N0000004_2_AC.p) annotation (Line());
  connect(line__N0000002_N0000004_2_AC.n, bus__N0000004_TN.p) annotation (Line());
  connect(bus__N0000003_TN.p, line__N0000003_N0000005_1_AC.p) annotation (Line());
  connect(line__N0000003_N0000005_1_AC.n, bus__N0000005_TN.p) annotation (Line());
  connect(bus__N0000003_TN.p, line__N0000003_N0000005_2_AC.p) annotation (Line());
  connect(line__N0000003_N0000005_2_AC.n, bus__N0000005_TN.p) annotation (Line());
  connect(bus__N0000004_TN.p, line__N0000004_N0000001_1_AC.p) annotation (Line());
  connect(line__N0000004_N0000001_1_AC.n, bus__N0000001_TN.p) annotation (Line());
  connect(bus__N0000004_TN.p, line__N0000004_N0000001_2_AC.p) annotation (Line());
  connect(line__N0000004_N0000001_2_AC.n, bus__N0000001_TN.p) annotation (Line());
  connect(bus__N0000005_TN.p, line__N0000005_N0000002_1_AC.p) annotation (Line());
  connect(line__N0000005_N0000002_1_AC.n, bus__N0000002_TN.p) annotation (Line());
  connect(bus__N0000005_TN.p, line__N0000005_N0000002_2_AC.p) annotation (Line());
  connect(line__N0000005_N0000002_2_AC.n, bus__N0000002_TN.p) annotation (Line());
  connect(bus__N0000006_TN.p, line__N0000006_N0000000_1_AC.p) annotation (Line());
  connect(line__N0000006_N0000000_1_AC.n, bus__N0000000_TN.p) annotation (Line());
  connect(bus__N0000006_TN.p, line__N0000006_N0000000_2_AC.p) annotation (Line());
  connect(line__N0000006_N0000000_2_AC.n, bus__N0000000_TN.p) annotation (Line());
  connect(bus__N0000006_TN.p, line__N0000006_N0000003_1_AC.p) annotation (Line());
  connect(line__N0000006_N0000003_1_AC.n, bus__N0000003_TN.p) annotation (Line());
  connect(bus__N0000006_TN.p, line__N0000006_N0000003_2_AC.p) annotation (Line());
  connect(line__N0000006_N0000003_2_AC.n, bus__N0000003_TN.p) annotation (Line());

// COUPLING DEVICES

// Connecting LOADS
  connect(bus__N0000000_TN.p, load__L0000000_EC.p) annotation (Line());
  connect(bus__N0000002_TN.p, load__L0000001_EC.p) annotation (Line());
  connect(bus__N0000003_TN.p, load__L0000002_EC.p) annotation (Line());
  connect(bus__N0000004_TN.p, load__L0000003_EC.p) annotation (Line());

// Connecting GENERATORS
  connect(bus__N0000001_TN.p, gen_pwGeneratorM2S__G0000000_SM.sortie) annotation (Line());
  connect(bus__N0000005_TN.p, gen_pwGeneratorM2S__G0000001_SM.sortie) annotation (Line());
  connect(bus__N0000006_TN.p, gen_pwGeneratorM2S__G0000002_SM.sortie) annotation (Line());

// Connecting DETAILED TRANSFORMERS
  connect(bus__N0000003_TN.p, trafo__N0000003_N0000000_1_PT.p) annotation (Line());
  connect(trafo__N0000003_N0000000_1_PT.n, bus__N0000000_TN.p) annotation (Line());

// Connecting OTHERS
annotation (uses(Modelica(version="3.2.1")));
end CIM_7buses;

