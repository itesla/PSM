within ;
model _LOGA___3_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S_Init gen_pwGeneratorM2S__LOGA___3_SM
   (
	PNALT = 1090.0,
	p0 = 0.4,
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
	uNResTfo = 69.0,
	XPQ = 0.393,
	TSQ0 = 0.084,
	SN = 1211.0,
	TPQ0 = 1.572,
	ui0 = -0.10275752114912451,
	nDSat = 6.995,
	q0 = -0.00905094,
	mDSatIn = 0.215,
	uNomNw = 69.0,
	rTfoIn = 0.0,
	XSD = 0.264,
	transformerIncluded = true,
	XD = 2.22,
	uBMac = 24.0,
	ur0 = 0.9796253378752589,
	lStatIn = 0.202,
	nQSat = 6.995,
	XSQ = 0.262,
	mQSatIn = 0.215,
	XQ = 2.22,
	sNTfo = 1211.0,
	PN = 1090.0,
	IWLMDV = 3,
	uNMacTfo = 24.0,
	pPuWLMDV = 1090.0,
	xTfoIn = 0.1
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.gsteam0_Init gsteam0__LOGA___3_SM
 (
	DT = 0.,
	RR = 0.05,
	PNALT = 1090.0,
	VMIN = 0.,
	VMAX = 1.,
	SN = 1211.0,
	T1 = 0.5,
	T2 = 3.,
	SNREF = 100.0,
	PN = 1090.0,
	T3 = 10.
  ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.pssi3e2b_Init pssi3e2b__LOGA___3_SM
 (
	PNALT = 1090.0,
	KS1 = 10.,
	VSI1MAX = 999.,
	KS3 = 1.,
	KS2 = 0.1564,
	TW2 = 2.,
	SNREF = 100.0,
	TW1 = 2.,
	TW3 = 2.,
	VSTMAX = 0.1,
	VSI1MIN = -999.,
	SN = 1211.0,
	T1 = 0.25,
	T2 = 0.03,
	T3 = .1500000,
	T4 = 0.015,
	T6 = 0.,
	VSTMIN = -0.1,
	T7 = 2.,
	T8 = 0.,
	T9 = 0.,
	VSI2MAX = 999.,
	T10 = 0.,
	T11 = 0.,
	VSI2MIN = -999.,
	PN = 1090.0
  ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.sexs_Init sexs__LOGA___3_SM
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
    equation
	connect(gen_pwGeneratorM2S__LOGA___3_SM.pin_OMEGA, gsteam0__LOGA___3_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__LOGA___3_SM.pin_CM, gsteam0__LOGA___3_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__LOGA___3_SM.pin_ActivePowerSN, pssi3e2b__LOGA___3_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__LOGA___3_SM.pin_TerminalVoltage, sexs__LOGA___3_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__LOGA___3_SM.pin_EFD, sexs__LOGA___3_SM.pin_EFD) annotation (Line());
	sexs__LOGA___3_SM.pin_VS=0;
end _LOGA___3_SM_Initialization;
