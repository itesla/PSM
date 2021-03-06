within ;
model _GEN____6_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S_Init gen_pwGeneratorM2S__GEN____6_SM
   (
	PNALT = 74.4,
	p0 = 0.0,
	omega_0 = 1.0,
	IENR = 3,
	TX = 0,
	Saturated = true,
	HIn = 4.975,
	TSD0 = 0.04,
	XPD = 0.225,
	SNREF = 100.0,
	TPD0 = 3.0,
	rStatIn = 0.004,
	XPQ = 0,
	TSQ0 = 0.06,
	SN = 80.0,
	TPQ0 = 0,
	ui0 = -0.26285799994630404,
	nDSat = 5.7,
	q0 = 0.12730958,
	mDSatIn = 0.16,
	XSD = 0.154,
	transformerIncluded = false,
	XD = 0.75,
	ur0 = 1.0372104555025565,
	lStatIn = 0.102,
	nQSat = 5.7,
	XSQ = 0.154,
	mQSatIn = 0.16,
	XQ = 0.45,
	PN = 71.8,
	IWLMDV = 3,
	pPuWLMDV = 74.4
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.pssi3e2b_Init pssi3e2b__GEN____6_SM
 (
	PNALT = 74.4,
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
	SN = 80.0,
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
	PN = 71.8
  ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.sexs_Init sexs__GEN____6_SM
 (
	PNALT = 74.4,
	K = 200.,
	EMIN = 0.,
	TA = 3.,
	TB = 10.,
	SNREF = 100.0,
	TE = 0.05,
	EFDMIN = -999.,
	KC = 1.,
	EFDMAX = 999.,
	SN = 80.0,
	PN = 71.8,
	EMAX = 4.
  ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.gsteam0_Init gsteam0__GEN____6_SM
 (
	DT = 0.,
	RR = 0.05,
	PNALT = 74.4,
	VMIN = 0.,
	VMAX = 1.,
	SN = 80.0,
	T1 = 0.5,
	T2 = 3.,
	SNREF = 100.0,
	PN = 71.8,
	T3 = 10.
  ) annotation (Placement(transformation()));
  Modelica.Blocks.Sources.Constant zero__GEN____6_SM (k = 0) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__GEN____6_SM.pin_ActivePowerSN, pssi3e2b__GEN____6_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____6_SM.pin_TerminalVoltage, sexs__GEN____6_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____6_SM.pin_EFD, sexs__GEN____6_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____6_SM.pin_OMEGA, gsteam0__GEN____6_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____6_SM.pin_CM, gsteam0__GEN____6_SM.pin_CM) annotation (Line());
    connect(sexs__GEN____6_SM.pin_VS, zero__GEN____6_SM.y) annotation (Line());
end _GEN____6_SM_Initialization;
