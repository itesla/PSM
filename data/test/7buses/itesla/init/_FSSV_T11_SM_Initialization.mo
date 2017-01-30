within ;
model _FSSV_T11_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S_Init gen_pwGeneratorM2S__FSSV_T11_SM
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
	ui0 = 0.0014244716082464394,
	nDSat = 5.57,
	q0 = 0.023523,
	mDSatIn = 0.084,
	uNomNw = 380.0,
	rTfoIn = 0.0025,
	XSD = 0.289,
	transformerIncluded = true,
	XD = 2.47,
	uBMac = 24.0,
	ur0 = 1.0696044019574962,
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
  iPSL.Electrical.Controls.Eurostag.tgov1_Init tgov1__FSSV_T11_SM
 (
	DT = 0.,
	RR = 0.05,
	PNALT = 970.0,
	VMIN = 0.,
	VMAX = 1.010000,
	SN = 1078.0,
	T1 = 0.5,
	T2 = 3.,
	SNREF = 100.0,
	PN = 1215.0,
	T3 = 10.
  ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.pssi3e3b_Init pssi3e3b__FSSV_T11_SM
 (
	PNALT = 970.0,
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
	SN = 1078.0,
	T1 = 0.012,
	T2 = 0.012,
	PN = 1215.0
  ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.sexs_Init sexs__FSSV_T11_SM
 (
	PNALT = 970.0,
	K = 200.,
	EMIN = 0.,
	TA = 3.,
	TB = 10.,
	SNREF = 100.0,
	TE = 0.05,
	EFDMIN = -999.,
	KC = 1.,
	EFDMAX = 999.,
	SN = 1078.0,
	PN = 1215.0,
	EMAX = 4.
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__FSSV_T11_SM.pin_OMEGA, tgov1__FSSV_T11_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__FSSV_T11_SM.pin_CM, tgov1__FSSV_T11_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__FSSV_T11_SM.pin_ActivePowerSN, pssi3e3b__FSSV_T11_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__FSSV_T11_SM.pin_TerminalVoltage, sexs__FSSV_T11_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__FSSV_T11_SM.pin_EFD, sexs__FSSV_T11_SM.pin_EFD) annotation (Line());
	sexs__FSSV_T11_SM.pin_VS=0;
end _FSSV_T11_SM_Initialization;
