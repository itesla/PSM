within ;
model _G0000001_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S_Init gen_pwGeneratorM2S__G0000001_SM
   (
	PNALT = 1539.0,
	p0 = 4.8000603,
	omega_0 = 1.0,
	IENR = 4,
	TX = 0,
	Saturated = true,
	HIn = 5.112,
	TSD0 = 0.065,
	XPD = 0.527,
	SNREF = 100.0,
	TPD0 = 10.041,
	rStatIn = 0.003275,
	uNResTfo = 405.0,
	XPQ = 0.623,
	TSQ0 = 0.094,
	SN = 1710.0,
	TPQ0 = 1.22,
	ui0 = -0.0011654515051647814,
	nDSat = 9.285,
	q0 = 0.0645498,
	mDSatIn = 0.05,
	uNomNw = 380.0,
	rTfoIn = 0.0029,
	XSD = 0.367,
	transformerIncluded = true,
	XD = 2.91,
	uBMac = 20.0,
	ur0 = 1.0696047155510324,
	lStatIn = 0.265,
	nQSat = 9.285,
	XSQ = 0.391,
	mQSatIn = 0.05,
	XQ = 2.72,
	sNTfo = 1710.0,
	PN = 1539.0,
	IWLMDV = 3,
	uNMacTfo = 20.0,
	pPuWLMDV = 1539.0,
	xTfoIn = 0.1583
    ) annotation (Placement(transformation()));
  rqb_alst_init rqb_alst__G0000001_SM
 (
	PNALT = 1539.0,
	VFTM = 4.,
	DELTAS = 87.84000,
	DEUXPINU = 314.1570,
	PLINP = 2.,
	SIBV = 0.006,
	SNREF = 100.0,
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
	SN = 1710.0,
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
	TQ = 0.04,
	PN = 1539.0
  ) annotation (Placement(transformation()));
  edftur2c_init edftur2c__G0000001_SM
 (
	PNALT = 1539.0,
	PR = 0.05,
	TVMP = 0.2,
	SAS = -0.02,
	PLIM = 0.025,
	SACC = 0.03968,
	ALPHA = 0.34,
	PMAXSPN = 1.010000,
	SNREF = 100.0,
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
	SN = 1710.0,
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
	TR = 5.,
	PN = 1539.0
  ) annotation (Placement(transformation()));
  tkj_comp_init tkj_comp__G0000001_SM
 (
	PNALT = 1539.0,
	KDD = .4160000,
	PLEXP = 4.600000,
	TDOEX = .4500000,
	SEA = .6560000,
	SNREF = 100.0,
	RFE = 100.,
	RC = 0.211,
	SEB = 5.170000,
	PLEXM = -4.,
	RIN = 0.25,
	KE = .4570000,
	SN = 1710.0,
	FIEEE = {0.,1.,0.433,0.7489,0.5,.7071000,.5500000,.6690000,.6000000,.6245000,.6500000,.5723000,.7000000,.5099000,0.75,0.433,1.,0.},
	PN = 1539.0
  ) annotation (Placement(transformation()));
  consig_init consig__G0000001_SM
 (
	PNALT = 1539.0,
	ZERO = 0.,
	SN = 1710.0,
	SNREF = 100.0,
	PN = 1539.0
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__G0000001_SM.pin_ActivePowerPNALT, rqb_alst__G0000001_SM.pin_ActivePowerPNALT) annotation (Line());
	connect(gen_pwGeneratorM2S__G0000001_SM.pin_OMEGA, rqb_alst__G0000001_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__G0000001_SM.pin_UI, rqb_alst__G0000001_SM.pin_UI) annotation (Line());
	connect(gen_pwGeneratorM2S__G0000001_SM.pin_UR, rqb_alst__G0000001_SM.pin_UR) annotation (Line());
	connect(gen_pwGeneratorM2S__G0000001_SM.pin_TETA, rqb_alst__G0000001_SM.pin_TETA) annotation (Line());
	connect(gen_pwGeneratorM2S__G0000001_SM.pin_TerminalVoltage, rqb_alst__G0000001_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__G0000001_SM.pin_ActivePowerPN, edftur2c__G0000001_SM.pin_ActivePowerPN) annotation (Line());
	connect(gen_pwGeneratorM2S__G0000001_SM.pin_CM, edftur2c__G0000001_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__G0000001_SM.pin_FieldCurrent, tkj_comp__G0000001_SM.pin_FieldCurrent) annotation (Line());
	connect(gen_pwGeneratorM2S__G0000001_SM.pin_EFD, tkj_comp__G0000001_SM.pin_EFD) annotation (Line());
	connect(rqb_alst__G0000001_SM.pin_IEX, tkj_comp__G0000001_SM.pin_LA) annotation (Line());
	connect(edftur2c__G0000001_SM.pin_PV, consig__G0000001_SM.pin_PV) annotation (Line());
end _G0000001_SM_Initialization;