within ;
model _GEN__107_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN__107_SM
   (
	PNALT = 228.0,
	p0 = -0.22,
	omega_0 = 1.0,
	IENR = 3,
	TX = 0,
	Saturated = false,
	HIn = 2.748,
	TSD0 = 0.096,
	XPD = 0.31,
	SNREF = 100.0,
	TPD0 = 8.4,
	rStatIn = 0.004,
	uNResTfo = 138.0,
	XPQ = 0,
	TSQ0 = 0.1,
	SN = 250.0,
	TPQ0 = 0,
	ui0 = 0.2899440052046282,
	nDSat = 0,
	q0 = 0.04652861,
	mDSatIn = 0,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.275,
	transformerIncluded = true,
	XD = 1.53,
	uBMac = 18.0,
	ur0 = 0.9067725820784419,
	lStatIn = 0.11,
	nQSat = 0,
	XSQ = 0.346,
	mQSatIn = 0,
	XQ = 0.99,
	sNTfo = 250.0,
	PN = 242.0,
	IWLMDV = 3,
	uNMacTfo = 18.0,
	pPuWLMDV = 228.0,
	xTfoIn = 0.1
    ) annotation (Placement(transformation()));
  pssi3e2b_init pssi3e2b__GEN__107_SM
 (
	PNALT = 228.0,
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
	SN = 250.0,
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
	PN = 242.0
  ) annotation (Placement(transformation()));
  sexs_init sexs__GEN__107_SM
 (
	PNALT = 228.0,
	K = 200.,
	EMIN = 0.,
	TA = 3.,
	TB = 10.,
	SNREF = 100.0,
	TE = 0.05,
	EFDMIN = -999.,
	KC = 1.,
	EFDMAX = 999.,
	SN = 250.0,
	PN = 242.0,
	EMAX = 4.
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__GEN__107_SM.pin_ActivePowerSN, pssi3e2b__GEN__107_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN__107_SM.pin_TerminalVoltage, sexs__GEN__107_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN__107_SM.pin_EFD, sexs__GEN__107_SM.pin_EFD) annotation (Line());
	sexs__GEN__107_SM.pin_VS=0;
end _GEN__107_SM_Initialization;
