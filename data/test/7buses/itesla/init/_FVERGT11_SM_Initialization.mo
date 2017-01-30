within ;
model _FVERGT11_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S_Init gen_pwGeneratorM2S__FVERGT11_SM
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
	ui0 = -3.237054760117397E-4,
	nDSat = 6.995,
	q0 = 0.074815795,
	mDSatIn = 0.215,
	uNomNw = 380.0,
	rTfoIn = 0.0025,
	XSD = 0.264,
	transformerIncluded = true,
	XD = 2.22,
	uBMac = 24.0,
	ur0 = 1.069605301511254,
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
  iPSL.Electrical.Controls.Eurostag.pssi3e3b_Init pssi3e3b__FVERGT11_SM
 (
	PNALT = 1090.0,
	VSTMIN = -0.1,
	KS1 = -0.602,
	KS2 = 30.12000,
	TW2 = .3000000,
	SNREF = 100.0,
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
	SN = 1211.0,
	T1 = 0.012,
	T2 = 0.012,
	PN = 1090.0
  ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.sexs_Init sexs__FVERGT11_SM
 (
	PNALT = 1090.0,
	K = 200.,
	EMIN = 0.,
	TA = 3.,
	TB = 10.,
	SNREF = 100.0,
	TE = 0.05,
	EFDMIN = -999.,
	KC = 1.,
	EFDMAX = 999.,
	SN = 1211.0,
	PN = 1090.0,
	EMAX = 4.
  ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.gsteam1_Init gsteam1__FVERGT11_SM
 (
	PNALT = 1090.0,
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
	SNREF = 100.0,
	PMAX = 1.,
	UO = 1.,
	SN = 1211.0,
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
	DB2 = 0.,
	PN = 1090.0
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__FVERGT11_SM.pin_ActivePowerSN, pssi3e3b__FVERGT11_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__FVERGT11_SM.pin_TerminalVoltage, sexs__FVERGT11_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__FVERGT11_SM.pin_EFD, sexs__FVERGT11_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__FVERGT11_SM.pin_CM, gsteam1__FVERGT11_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__FVERGT11_SM.pin_OMEGA, gsteam1__FVERGT11_SM.pin_OMEGA) annotation (Line());
	sexs__FVERGT11_SM.pin_VS=0;
end _FVERGT11_SM_Initialization;
