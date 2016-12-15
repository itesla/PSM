within ;
model _G0000002_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S_Init gen_pwGeneratorM2S__G0000002_SM
   (
	PNALT = 1090.0,
	p0 = 0.0,
	omega_0 = 1.0,
	IENR = 4,
	TX = 0,
	Saturated = true,
	HIn = 5.4,
	TSD0 = 0.08,
	XPD = 0.384,
	SNREF = 100.0,
	TPD0 = 8.094,
	rStatIn = 0.002796,
	uNResTfo = 415.0,
	XPQ = 0.393,
	TSQ0 = 0.084,
	SN = 1211.0,
	TPQ0 = 1.572,
	ui0 = -0.0017482700272833341,
	nDSat = 6.995,
	q0 = 0.074815795,
	mDSatIn = 0.215,
	uNomNw = 380.0,
	rTfoIn = 0.0025,
	XSD = 0.264,
	transformerIncluded = true,
	XD = 2.22,
	uBMac = 24.0,
	ur0 = 1.0696039217196838,
	lStatIn = 0.202,
	nQSat = 6.995,
	XSQ = 0.262,
	mQSatIn = 0.215,
	XQ = 2.22,
	sNTfo = 1080.0,
	PN = 1090.0,
	IWLMDV = 3,
	uNMacTfo = 24.0,
	pPuWLMDV = 1090.0,
	xTfoIn = 0.1362
    ) annotation (Placement(transformation()));
  edftur2c_init edftur2c__G0000002_SM
 (
	PNALT = 1090.0,
	PR = 0.05,
	TVMP = 0.2,
	SAS = -0.02,
	PLIM = 0.025,
	SACC = 0.039683,
	ALPHA = 0.34,
	PMAXSPN = 1.,
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
	SN = 1211.0,
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
	PN = 1090.0
  ) annotation (Placement(transformation()));
  sep_abb_init sep_abb__G0000002_SM
 (
	PNALT = 1090.0,
	VFTM = 4.,
	DELTAS = 88.02000,
	LIMP = 0.14,
	PLINP = 1.600000,
	K1 = 8.440000,
	SNREF = 100.0,
	VFSA = 5.,
	VFNP = 1.049000,
	SN = 1211.0,
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
	PN = 1090.0,
	TS = 1.5
  ) annotation (Placement(transformation()));
  ex_stat_init ex_stat__G0000002_SM
 (
	RFE = 200.,
	PNALT = 1090.0,
	TE = 0.02,
	PLEXM = -1.6,
	PLEXP = 3.,
	SN = 1211.0,
	SNREF = 100.0,
	PN = 1090.0
  ) annotation (Placement(transformation()));
  consig_init consig__G0000002_SM
 (
	PNALT = 1090.0,
	ZERO = 0.,
	SN = 1211.0,
	SNREF = 100.0,
	PN = 1090.0
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__G0000002_SM.pin_ActivePowerPN, edftur2c__G0000002_SM.pin_ActivePowerPN) annotation (Line());
	connect(gen_pwGeneratorM2S__G0000002_SM.pin_CM, edftur2c__G0000002_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__G0000002_SM.pin_TerminalVoltage, sep_abb__G0000002_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__G0000002_SM.pin_ActivePowerPNALT, sep_abb__G0000002_SM.pin_ActivePowerPNALT) annotation (Line());
	connect(gen_pwGeneratorM2S__G0000002_SM.pin_UI, sep_abb__G0000002_SM.pin_UI) annotation (Line());
	connect(gen_pwGeneratorM2S__G0000002_SM.pin_UR, sep_abb__G0000002_SM.pin_UR) annotation (Line());
	connect(gen_pwGeneratorM2S__G0000002_SM.pin_TETA, sep_abb__G0000002_SM.pin_TETA) annotation (Line());
	connect(gen_pwGeneratorM2S__G0000002_SM.pin_FieldCurrent, ex_stat__G0000002_SM.pin_FieldCurrent) annotation (Line());
	connect(edftur2c__G0000002_SM.pin_PV, consig__G0000002_SM.pin_PV) annotation (Line());
	connect(sep_abb__G0000002_SM.pin_IEX, ex_stat__G0000002_SM.pin_IEX) annotation (Line());
end _G0000002_SM_Initialization;