within ;
model _GEN___70_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___70_SM
   (
	PNALT = 1008.0,
	p0 = 0.0,
	omega_0 = 1.0,
	IENR = 4,
	TX = 0,
	Saturated = true,
	HIn = 5.4,
	TSD0 = 0.058,
	XPD = 0.407,
	SNREF = 100.0,
	TPD0 = 9.651,
	rStatIn = 0.00357,
	uNResTfo = 138.0,
	XPQ = 0.454,
	TSQ0 = 0.06,
	SN = 1120.0,
	TPQ0 = 1.009,
	ui0 = 0.37990140193773303,
	nDSat = 5.57,
	q0 = 0.10061772,
	mDSatIn = 0.084,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.3,
	transformerIncluded = true,
	XD = 2.57,
	uBMac = 24.0,
	ur0 = 0.9077064383904252,
	lStatIn = 0.219,
	nQSat = 5.57,
	XSQ = 0.301,
	mQSatIn = 0.084,
	XQ = 2.57,
	sNTfo = 1120.0,
	PN = 1008.0,
	IWLMDV = 3,
	uNMacTfo = 24.0,
	pPuWLMDV = 1008.0,
	xTfoIn = 0.1
    ) annotation (Placement(transformation()));
  sexs_init sexs__GEN___70_SM
 (
	PNALT = 1008.0,
	K = 200.,
	EMIN = 0.,
	TA = 3.,
	TB = 10.,
	SNREF = 100.0,
	TE = 0.05,
	EFDMIN = -999.,
	KC = 1.,
	EFDMAX = 999.,
	SN = 1120.0,
	PN = 1008.0,
	EMAX = 4.
  ) annotation (Placement(transformation()));
  pssi3e2b_init pssi3e2b__GEN___70_SM
 (
	PNALT = 1008.0,
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
	SN = 1120.0,
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
	PN = 1008.0
  ) annotation (Placement(transformation()));
  gsteam0_init gsteam0__GEN___70_SM
 (
	DT = 0.,
	RR = 0.05,
	PNALT = 1008.0,
	VMIN = 0.,
	VMAX = 1.,
	SN = 1120.0,
	T1 = 0.5,
	T2 = 3.,
	SNREF = 100.0,
	PN = 1008.0,
	T3 = 10.
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__GEN___70_SM.pin_TerminalVoltage, sexs__GEN___70_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___70_SM.pin_EFD, sexs__GEN___70_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___70_SM.pin_ActivePowerSN, pssi3e2b__GEN___70_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___70_SM.pin_OMEGA, gsteam0__GEN___70_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___70_SM.pin_CM, gsteam0__GEN___70_SM.pin_CM) annotation (Line());
	sexs__GEN___70_SM.pin_VS=0;
end _GEN___70_SM_Initialization;
