within ;
model _GEN___13_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S_Init gen_pwGeneratorM2S__GEN___13_SM
   (
	PNALT = 32.0,
	p0 = 0.0,
	omega_0 = 1.0,
	IENR = 3,
	TX = 0,
	Saturated = false,
	HIn = 3.657,
	TSD0 = 0.15,
	XPD = 0.38,
	SNREF = 100.0,
	TPD0 = 9.4,
	rStatIn = 0.003,
	uNResTfo = 33.0,
	XPQ = 0,
	TSQ0 = 0.2,
	SN = 35.0,
	TPQ0 = 0,
	ui0 = -0.2761862259322773,
	nDSat = 0,
	q0 = 0.10630558,
	mDSatIn = 0,
	uNomNw = 33.0,
	rTfoIn = 0.0,
	XSD = 0.29,
	transformerIncluded = true,
	XD = 1.54,
	uBMac = 11.0,
	ur0 = 1.034776365069819,
	lStatIn = 0.24,
	nQSat = 0,
	XSQ = 0.4,
	mQSatIn = 0,
	XQ = 0.6,
	sNTfo = 35.0,
	PN = 32.0,
	IWLMDV = 3,
	uNMacTfo = 11.0,
	pPuWLMDV = 32.0,
	xTfoIn = 0.1
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.pssi3e2b_Init pssi3e2b__GEN___13_SM
 (
	PNALT = 32.0,
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
	SN = 35.0,
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
	PN = 32.0
  ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.gsteam0_Init gsteam0__GEN___13_SM
 (
	DT = 0.,
	RR = 0.05,
	PNALT = 32.0,
	VMIN = 0.,
	VMAX = 1.,
	SN = 35.0,
	T1 = 0.5,
	T2 = 3.,
	SNREF = 100.0,
	PN = 32.0,
	T3 = 10.
  ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.sexs_Init sexs__GEN___13_SM
 (
	PNALT = 32.0,
	K = 200.,
	EMIN = 0.,
	TA = 3.,
	TB = 10.,
	SNREF = 100.0,
	TE = 0.05,
	EFDMIN = -999.,
	KC = 1.,
	EFDMAX = 999.,
	SN = 35.0,
	PN = 32.0,
	EMAX = 4.
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__GEN___13_SM.pin_ActivePowerSN, pssi3e2b__GEN___13_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___13_SM.pin_OMEGA, gsteam0__GEN___13_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___13_SM.pin_CM, gsteam0__GEN___13_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___13_SM.pin_TerminalVoltage, sexs__GEN___13_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___13_SM.pin_EFD, sexs__GEN___13_SM.pin_EFD) annotation (Line());
	sexs__GEN___13_SM.pin_VS=0;
end _GEN___13_SM_Initialization;
