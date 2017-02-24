within ;
model _GEN__100_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN__100_SM
   (
	PNALT = 1539.0,
	p0 = 2.52,
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
	uNResTfo = 138.0,
	XPQ = 0.623,
	TSQ0 = 0.094,
	SN = 1710.0,
	TPQ0 = 1.22,
	ui0 = 0.48049580034204487,
	nDSat = 9.285,
	q0 = 0.92007357,
	mDSatIn = 0.05,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.367,
	transformerIncluded = true,
	XD = 2.91,
	uBMac = 20.0,
	ur0 = 0.8963329205061978,
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
	xTfoIn = 0.1
    ) annotation (Placement(transformation()));
  gsteam0_init gsteam0__GEN__100_SM
 (
	DT = 0.,
	RR = 0.05,
	PNALT = 1539.0,
	VMIN = 0.,
	VMAX = 1.,
	SN = 1710.0,
	T1 = 0.5,
	T2 = 3.,
	SNREF = 100.0,
	PN = 1539.0,
	T3 = 10.
  ) annotation (Placement(transformation()));
  sexs_init sexs__GEN__100_SM
 (
	PNALT = 1539.0,
	K = 200.,
	EMIN = 0.,
	TA = 3.,
	TB = 10.,
	SNREF = 100.0,
	TE = 0.05,
	EFDMIN = -999.,
	KC = 1.,
	EFDMAX = 999.,
	SN = 1710.0,
	PN = 1539.0,
	EMAX = 4.
  ) annotation (Placement(transformation()));
  pssi3e2b_init pssi3e2b__GEN__100_SM
 (
	PNALT = 1539.0,
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
	SN = 1710.0,
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
	PN = 1539.0
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__GEN__100_SM.pin_OMEGA, gsteam0__GEN__100_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN__100_SM.pin_CM, gsteam0__GEN__100_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN__100_SM.pin_TerminalVoltage, sexs__GEN__100_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN__100_SM.pin_EFD, sexs__GEN__100_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN__100_SM.pin_ActivePowerSN, pssi3e2b__GEN__100_SM.pin_ActivePowerSN) annotation (Line());
	sexs__GEN__100_SM.pin_VS=0;
end _GEN__100_SM_Initialization;
