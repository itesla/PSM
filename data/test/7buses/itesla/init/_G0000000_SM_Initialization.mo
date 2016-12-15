within ;
model _G0000000_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S_Init gen_pwGeneratorM2S__G0000000_SM
   (
	PNALT = 970.0,
	p0 = 9.60013,
	omega_0 = 1.0,
	IENR = 4,
	TX = 0,
	Saturated = true,
	HIn = 6.3,
	TSD0 = 0.058,
	XPD = 0.3925,
	SNREF = 100.0,
	TPD0 = 9.627,
	rStatIn = 0.00344,
	uNResTfo = 415.0,
	XPQ = 0.437,
	TSQ0 = 0.06,
	SN = 1078.0,
	TPQ0 = 1.006,
	ui0 = 0.0,
	nDSat = 5.57,
	q0 = 0.0235231,
	mDSatIn = 0.084,
	uNomNw = 380.0,
	rTfoIn = 0.0025,
	XSD = 0.289,
	transformerIncluded = true,
	XD = 2.47,
	uBMac = 24.0,
	ur0 = 1.0696053504943848,
	lStatIn = 0.211,
	nQSat = 5.57,
	XSQ = 0.29,
	mQSatIn = 0.084,
	XQ = 2.47,
	sNTfo = 1080.0,
	PN = 1215.0,
	IWLMDV = 3,
	uNMacTfo = 24.0,
	pPuWLMDV = 970.0,
	xTfoIn = 0.137
    ) annotation (Placement(transformation()));
  consig_init consig__G0000000_SM
 (
	PNALT = 970.0,
	ZERO = 0.,
	SN = 1078.0,
	SNREF = 100.0,
	PN = 1215.0
  ) annotation (Placement(transformation()));
  rqb_alst_init rqb_alst__G0000000_SM
 (
	PNALT = 970.0,
	VFTM = 4.,
	DELTAS = 87.84000,
	DEUXPINU = 314.1570,
	PLINP = 1.900000,
	SIBV = 0.006,
	SNREF = 100.0,
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
	SN = 1078.0,
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
	TQ = 0.04,
	PN = 1215.0
  ) annotation (Placement(transformation()));
  edftur1c_init edftur1c__G0000000_SM
 (
	PNALT = 970.0,
	PR = 0.05,
	TVMP = 0.25,
	SAS = -0.02,
	PLIM = 0.025,
	SACC = 0.024182,
	ALPHA = 0.34,
	PMAXSPN = 1.025641,
	SNREF = 100.0,
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
	SN = 1078.0,
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
	TR = 4.,
	PN = 1215.0
  ) annotation (Placement(transformation()));
  tkj_comp_init tkj_comp__G0000000_SM
 (
	PNALT = 970.0,
	KDD = .4200000,
	PLEXP = 4.,
	TDOEX = .4000000,
	SEA = 1.100000,
	SNREF = 100.0,
	RFE = 100.,
	RC = 0.13,
	SEB = 7.400000,
	PLEXM = -3.46,
	RIN = 0.25,
	KE = .4900000,
	SN = 1078.0,
	FIEEE = {0.,1.,0.433,0.7489,0.5,.7071000,.5500000,.6690000,.6000000,.6245000,.6500000,.5723000,.7000000,.5099000,0.75,0.433,1.,0.},
	PN = 1215.0
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__G0000000_SM.pin_ActivePowerPNALT, rqb_alst__G0000000_SM.pin_ActivePowerPNALT) annotation (Line());
	connect(gen_pwGeneratorM2S__G0000000_SM.pin_OMEGA, rqb_alst__G0000000_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__G0000000_SM.pin_UI, rqb_alst__G0000000_SM.pin_UI) annotation (Line());
	connect(gen_pwGeneratorM2S__G0000000_SM.pin_UR, rqb_alst__G0000000_SM.pin_UR) annotation (Line());
	connect(gen_pwGeneratorM2S__G0000000_SM.pin_TETA, rqb_alst__G0000000_SM.pin_TETA) annotation (Line());
	connect(gen_pwGeneratorM2S__G0000000_SM.pin_TerminalVoltage, rqb_alst__G0000000_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__G0000000_SM.pin_CM, edftur1c__G0000000_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__G0000000_SM.pin_ActivePowerPN, edftur1c__G0000000_SM.pin_ActivePowerPN) annotation (Line());
	connect(gen_pwGeneratorM2S__G0000000_SM.pin_FieldCurrent, tkj_comp__G0000000_SM.pin_FieldCurrent) annotation (Line());
	connect(gen_pwGeneratorM2S__G0000000_SM.pin_EFD, tkj_comp__G0000000_SM.pin_EFD) annotation (Line());
	connect(consig__G0000000_SM.pin_PV, edftur1c__G0000000_SM.pin_PV) annotation (Line());
	connect(rqb_alst__G0000000_SM.pin_IEX, tkj_comp__G0000000_SM.pin_LA) annotation (Line());
end _G0000000_SM_Initialization;