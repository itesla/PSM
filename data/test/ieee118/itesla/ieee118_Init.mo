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
model _GEN__103_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN__103_SM
   (
	PNALT = 1539.0,
	p0 = 0.4,
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
	ui0 = 0.41813260703661087,
	nDSat = 9.285,
	q0 = 0.74073607,
	mDSatIn = 0.05,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.367,
	transformerIncluded = true,
	XD = 2.91,
	uBMac = 20.0,
	ur0 = 0.9193830016204052,
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
  pssi3e2b_init pssi3e2b__GEN__103_SM
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
  sexs_init sexs__GEN__103_SM
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
  gsteam0_init gsteam0__GEN__103_SM
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
    equation
	connect(gen_pwGeneratorM2S__GEN__103_SM.pin_ActivePowerSN, pssi3e2b__GEN__103_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN__103_SM.pin_TerminalVoltage, sexs__GEN__103_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN__103_SM.pin_EFD, sexs__GEN__103_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN__103_SM.pin_OMEGA, gsteam0__GEN__103_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN__103_SM.pin_CM, gsteam0__GEN__103_SM.pin_CM) annotation (Line());
	sexs__GEN__103_SM.pin_VS=0;
end _GEN__103_SM_Initialization;
model _GEN__104_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN__104_SM
   (
	PNALT = 1485.0,
	p0 = 0.0,
	omega_0 = 1.0,
	IENR = 4,
	TX = 0,
	Saturated = true,
	HIn = 5.625,
	TSD0 = 0.065,
	XPD = 0.509,
	SNREF = 100.0,
	TPD0 = 10.041,
	rStatIn = 0.00316,
	uNResTfo = 138.0,
	XPQ = 0.601,
	TSQ0 = 0.094,
	SN = 1650.0,
	TPQ0 = 1.22,
	ui0 = 0.36192315944726283,
	nDSat = 9.285,
	q0 = -0.03164747,
	mDSatIn = 0.05,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.354,
	transformerIncluded = true,
	XD = 2.81,
	uBMac = 20.0,
	ur0 = 0.9010286661445892,
	lStatIn = 0.256,
	nQSat = 9.285,
	XSQ = 0.377,
	mQSatIn = 0.05,
	XQ = 2.62,
	sNTfo = 1650.0,
	PN = 1485.0,
	IWLMDV = 3,
	uNMacTfo = 20.0,
	pPuWLMDV = 1485.0,
	xTfoIn = 0.1
    ) annotation (Placement(transformation()));
  pssi3e2b_init pssi3e2b__GEN__104_SM
 (
	PNALT = 1485.0,
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
	SN = 1650.0,
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
	PN = 1485.0
  ) annotation (Placement(transformation()));
  sexs_init sexs__GEN__104_SM
 (
	PNALT = 1485.0,
	K = 200.,
	EMIN = 0.,
	TA = 3.,
	TB = 10.,
	SNREF = 100.0,
	TE = 0.05,
	EFDMIN = -999.,
	KC = 1.,
	EFDMAX = 999.,
	SN = 1650.0,
	PN = 1485.0,
	EMAX = 4.
  ) annotation (Placement(transformation()));
  gsteam0_init gsteam0__GEN__104_SM
 (
	DT = 0.,
	RR = 0.05,
	PNALT = 1485.0,
	VMIN = 0.,
	VMAX = 1.,
	SN = 1650.0,
	T1 = 0.5,
	T2 = 3.,
	SNREF = 100.0,
	PN = 1485.0,
	T3 = 10.
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__GEN__104_SM.pin_ActivePowerSN, pssi3e2b__GEN__104_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN__104_SM.pin_TerminalVoltage, sexs__GEN__104_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN__104_SM.pin_EFD, sexs__GEN__104_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN__104_SM.pin_OMEGA, gsteam0__GEN__104_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN__104_SM.pin_CM, gsteam0__GEN__104_SM.pin_CM) annotation (Line());
	sexs__GEN__104_SM.pin_VS=0;
end _GEN__104_SM_Initialization;
model _GEN__105_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN__105_SM
   (
	PNALT = 1485.0,
	p0 = 0.0,
	omega_0 = 1.0,
	IENR = 4,
	TX = 0,
	Saturated = true,
	HIn = 5.625,
	TSD0 = 0.065,
	XPD = 0.509,
	SNREF = 100.0,
	TPD0 = 10.041,
	rStatIn = 0.00316,
	uNResTfo = 138.0,
	XPQ = 0.601,
	TSQ0 = 0.094,
	SN = 1650.0,
	TPQ0 = 1.22,
	ui0 = 0.3426385846862053,
	nDSat = 9.285,
	q0 = -0.08,
	mDSatIn = 0.05,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.354,
	transformerIncluded = true,
	XD = 2.81,
	uBMac = 20.0,
	ur0 = 0.9044457022039211,
	lStatIn = 0.256,
	nQSat = 9.285,
	XSQ = 0.377,
	mQSatIn = 0.05,
	XQ = 2.62,
	sNTfo = 1650.0,
	PN = 1485.0,
	IWLMDV = 3,
	uNMacTfo = 20.0,
	pPuWLMDV = 1485.0,
	xTfoIn = 0.1
    ) annotation (Placement(transformation()));
  sexs_init sexs__GEN__105_SM
 (
	PNALT = 1485.0,
	K = 200.,
	EMIN = 0.,
	TA = 3.,
	TB = 10.,
	SNREF = 100.0,
	TE = 0.05,
	EFDMIN = -999.,
	KC = 1.,
	EFDMAX = 999.,
	SN = 1650.0,
	PN = 1485.0,
	EMAX = 4.
  ) annotation (Placement(transformation()));
  pssi3e2b_init pssi3e2b__GEN__105_SM
 (
	PNALT = 1485.0,
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
	SN = 1650.0,
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
	PN = 1485.0
  ) annotation (Placement(transformation()));
  gsteam0_init gsteam0__GEN__105_SM
 (
	DT = 0.,
	RR = 0.05,
	PNALT = 1485.0,
	VMIN = 0.,
	VMAX = 1.,
	SN = 1650.0,
	T1 = 0.5,
	T2 = 3.,
	SNREF = 100.0,
	PN = 1485.0,
	T3 = 10.
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__GEN__105_SM.pin_TerminalVoltage, sexs__GEN__105_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN__105_SM.pin_EFD, sexs__GEN__105_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN__105_SM.pin_ActivePowerSN, pssi3e2b__GEN__105_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN__105_SM.pin_OMEGA, gsteam0__GEN__105_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN__105_SM.pin_CM, gsteam0__GEN__105_SM.pin_CM) annotation (Line());
	sexs__GEN__105_SM.pin_VS=0;
end _GEN__105_SM_Initialization;
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
  cmconst_init cmconst__GEN__107_SM
 (
	PNALT = 228.0,
	SN = 250.0,
	SNREF = 100.0,
	PN = 242.0
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
model _GEN__110_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN__110_SM
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
	ui0 = 0.30539273775947867,
	nDSat = 5.57,
	q0 = -0.00942288,
	mDSatIn = 0.084,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.3,
	transformerIncluded = true,
	XD = 2.57,
	uBMac = 24.0,
	ur0 = 0.9238312920860297,
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
  pssi3e2b_init pssi3e2b__GEN__110_SM
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
  sexs_init sexs__GEN__110_SM
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
  gsteam0_init gsteam0__GEN__110_SM
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
	connect(gen_pwGeneratorM2S__GEN__110_SM.pin_ActivePowerSN, pssi3e2b__GEN__110_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN__110_SM.pin_TerminalVoltage, sexs__GEN__110_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN__110_SM.pin_EFD, sexs__GEN__110_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN__110_SM.pin_OMEGA, gsteam0__GEN__110_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN__110_SM.pin_CM, gsteam0__GEN__110_SM.pin_CM) annotation (Line());
	sexs__GEN__110_SM.pin_VS=0;
end _GEN__110_SM_Initialization;
model _GEN__111_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN__111_SM
   (
	PNALT = 1008.0,
	p0 = 0.36,
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
	ui0 = 0.3341752739160566,
	nDSat = 5.57,
	q0 = -0.018438019,
	mDSatIn = 0.084,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.3,
	transformerIncluded = true,
	XD = 2.57,
	uBMac = 24.0,
	ur0 = 0.9212637644492276,
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
  sexs_init sexs__GEN__111_SM
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
  pssi3e2b_init pssi3e2b__GEN__111_SM
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
  gsteam0_init gsteam0__GEN__111_SM
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
	connect(gen_pwGeneratorM2S__GEN__111_SM.pin_TerminalVoltage, sexs__GEN__111_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN__111_SM.pin_EFD, sexs__GEN__111_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN__111_SM.pin_ActivePowerSN, pssi3e2b__GEN__111_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN__111_SM.pin_OMEGA, gsteam0__GEN__111_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN__111_SM.pin_CM, gsteam0__GEN__111_SM.pin_CM) annotation (Line());
	sexs__GEN__111_SM.pin_VS=0;
end _GEN__111_SM_Initialization;
model _GEN__112_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN__112_SM
   (
	PNALT = 1485.0,
	p0 = -0.43,
	omega_0 = 1.0,
	IENR = 4,
	TX = 0,
	Saturated = true,
	HIn = 5.625,
	TSD0 = 0.065,
	XPD = 0.509,
	SNREF = 100.0,
	TPD0 = 10.041,
	rStatIn = 0.00316,
	uNResTfo = 138.0,
	XPQ = 0.601,
	TSQ0 = 0.094,
	SN = 1650.0,
	TPQ0 = 1.22,
	ui0 = 0.2555229967675234,
	nDSat = 9.285,
	q0 = 0.41511607,
	mDSatIn = 0.05,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.354,
	transformerIncluded = true,
	XD = 2.81,
	uBMac = 20.0,
	ur0 = 0.9409213806767108,
	lStatIn = 0.256,
	nQSat = 9.285,
	XSQ = 0.377,
	mQSatIn = 0.05,
	XQ = 2.62,
	sNTfo = 1650.0,
	PN = 1485.0,
	IWLMDV = 3,
	uNMacTfo = 20.0,
	pPuWLMDV = 1485.0,
	xTfoIn = 0.1
    ) annotation (Placement(transformation()));
  cmconst_init cmconst__GEN__112_SM
 (
	PNALT = 1485.0,
	SN = 1650.0,
	SNREF = 100.0,
	PN = 1485.0
  ) annotation (Placement(transformation()));
  sexs_init sexs__GEN__112_SM
 (
	PNALT = 1485.0,
	K = 200.,
	EMIN = 0.,
	TA = 3.,
	TB = 10.,
	SNREF = 100.0,
	TE = 0.05,
	EFDMIN = -999.,
	KC = 1.,
	EFDMAX = 999.,
	SN = 1650.0,
	PN = 1485.0,
	EMAX = 4.
  ) annotation (Placement(transformation()));
  pssi3e2b_init pssi3e2b__GEN__112_SM
 (
	PNALT = 1485.0,
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
	SN = 1650.0,
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
	PN = 1485.0
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__GEN__112_SM.pin_TerminalVoltage, sexs__GEN__112_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN__112_SM.pin_EFD, sexs__GEN__112_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN__112_SM.pin_ActivePowerSN, pssi3e2b__GEN__112_SM.pin_ActivePowerSN) annotation (Line());
	sexs__GEN__112_SM.pin_VS=0;
end _GEN__112_SM_Initialization;
model _GEN__113_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN__113_SM
   (
	PNALT = 1539.0,
	p0 = -0.06,
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
	ui0 = 0.23811802548488417,
	nDSat = 9.285,
	q0 = 0.05951313,
	mDSatIn = 0.05,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.367,
	transformerIncluded = true,
	XD = 2.91,
	uBMac = 20.0,
	ur0 = 0.9640273586222886,
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
  cmconst_init cmconst__GEN__113_SM
 (
	PNALT = 1539.0,
	SN = 1710.0,
	SNREF = 100.0,
	PN = 1539.0
  ) annotation (Placement(transformation()));
  pssi3e2b_init pssi3e2b__GEN__113_SM
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
  sexs_init sexs__GEN__113_SM
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
    equation
	connect(gen_pwGeneratorM2S__GEN__113_SM.pin_ActivePowerSN, pssi3e2b__GEN__113_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN__113_SM.pin_TerminalVoltage, sexs__GEN__113_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN__113_SM.pin_EFD, sexs__GEN__113_SM.pin_EFD) annotation (Line());
	sexs__GEN__113_SM.pin_VS=0;
end _GEN__113_SM_Initialization;
model _GEN__116_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN__116_SM
   (
	PNALT = 1539.0,
	p0 = -1.84,
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
	uNResTfo = 345.0,
	XPQ = 0.623,
	TSQ0 = 0.094,
	SN = 1710.0,
	TPQ0 = 1.22,
	ui0 = 0.46071002974096836,
	nDSat = 9.285,
	q0 = 0.32262135,
	mDSatIn = 0.05,
	uNomNw = 345.0,
	rTfoIn = 0.0,
	XSD = 0.367,
	transformerIncluded = true,
	XD = 2.91,
	uBMac = 20.0,
	ur0 = 0.8931804178953148,
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
  sexs_init sexs__GEN__116_SM
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
  pssi3e2b_init pssi3e2b__GEN__116_SM
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
  cmconst_init cmconst__GEN__116_SM
 (
	PNALT = 1539.0,
	SN = 1710.0,
	SNREF = 100.0,
	PN = 1539.0
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__GEN__116_SM.pin_TerminalVoltage, sexs__GEN__116_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN__116_SM.pin_EFD, sexs__GEN__116_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN__116_SM.pin_ActivePowerSN, pssi3e2b__GEN__116_SM.pin_ActivePowerSN) annotation (Line());
	sexs__GEN__116_SM.pin_VS=0;
end _GEN__116_SM_Initialization;
model _GEN___10_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___10_SM
   (
	PNALT = 1485.0,
	p0 = 4.5,
	omega_0 = 1.0,
	IENR = 4,
	TX = 0,
	Saturated = true,
	HIn = 5.625,
	TSD0 = 0.065,
	XPD = 0.509,
	SNREF = 100.0,
	TPD0 = 10.041,
	rStatIn = 0.00316,
	uNResTfo = 345.0,
	XPQ = 0.601,
	TSQ0 = 0.094,
	SN = 1650.0,
	TPQ0 = 1.22,
	ui0 = 0.6139140709688063,
	nDSat = 9.285,
	q0 = -0.51042444,
	mDSatIn = 0.05,
	uNomNw = 345.0,
	rTfoIn = 0.0,
	XSD = 0.354,
	transformerIncluded = true,
	XD = 2.81,
	uBMac = 20.0,
	ur0 = 0.8518271029561728,
	lStatIn = 0.256,
	nQSat = 9.285,
	XSQ = 0.377,
	mQSatIn = 0.05,
	XQ = 2.62,
	sNTfo = 1650.0,
	PN = 1485.0,
	IWLMDV = 3,
	uNMacTfo = 20.0,
	pPuWLMDV = 1485.0,
	xTfoIn = 0.1
    ) annotation (Placement(transformation()));
  sexs_init sexs__GEN___10_SM
 (
	PNALT = 1485.0,
	K = 200.,
	EMIN = 0.,
	TA = 3.,
	TB = 10.,
	SNREF = 100.0,
	TE = 0.05,
	EFDMIN = -999.,
	KC = 1.,
	EFDMAX = 999.,
	SN = 1650.0,
	PN = 1485.0,
	EMAX = 4.
  ) annotation (Placement(transformation()));
  gsteam0_init gsteam0__GEN___10_SM
 (
	DT = 0.,
	RR = 0.05,
	PNALT = 1485.0,
	VMIN = 0.,
	VMAX = 1.,
	SN = 1650.0,
	T1 = 0.5,
	T2 = 3.,
	SNREF = 100.0,
	PN = 1485.0,
	T3 = 10.
  ) annotation (Placement(transformation()));
  pssi3e2b_init pssi3e2b__GEN___10_SM
 (
	PNALT = 1485.0,
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
	SN = 1650.0,
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
	PN = 1485.0
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__GEN___10_SM.pin_TerminalVoltage, sexs__GEN___10_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___10_SM.pin_EFD, sexs__GEN___10_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___10_SM.pin_OMEGA, gsteam0__GEN___10_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___10_SM.pin_CM, gsteam0__GEN___10_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___10_SM.pin_ActivePowerSN, pssi3e2b__GEN___10_SM.pin_ActivePowerSN) annotation (Line());
	sexs__GEN___10_SM.pin_VS=0;
end _GEN___10_SM_Initialization;
model _GEN___12_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___12_SM
   (
	PNALT = 228.0,
	p0 = 0.85,
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
	ui0 = 0.2130475423559576,
	nDSat = 0,
	q0 = 0.90169364,
	mDSatIn = 0,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.275,
	transformerIncluded = true,
	XD = 1.53,
	uBMac = 18.0,
	ur0 = 0.9668043470949248,
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
  pssi3e2b_init pssi3e2b__GEN___12_SM
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
  sexs_init sexs__GEN___12_SM
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
  gsteam0_init gsteam0__GEN___12_SM
 (
	DT = 0.,
	RR = 0.05,
	PNALT = 228.0,
	VMIN = 0.,
	VMAX = 1.,
	SN = 250.0,
	T1 = 0.5,
	T2 = 3.,
	SNREF = 100.0,
	PN = 242.0,
	T3 = 10.
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__GEN___12_SM.pin_ActivePowerSN, pssi3e2b__GEN___12_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___12_SM.pin_TerminalVoltage, sexs__GEN___12_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___12_SM.pin_EFD, sexs__GEN___12_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___12_SM.pin_OMEGA, gsteam0__GEN___12_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___12_SM.pin_CM, gsteam0__GEN___12_SM.pin_CM) annotation (Line());
	sexs__GEN___12_SM.pin_VS=0;
end _GEN___12_SM_Initialization;
model _GEN___15_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___15_SM
   (
	PNALT = 245.7,
	p0 = 0.0,
	omega_0 = 1.0,
	IENR = 3,
	TX = 0,
	Saturated = false,
	HIn = 3.595,
	TSD0 = 0.03,
	XPD = 0.37,
	SNREF = 100.0,
	TPD0 = 7.4,
	rStatIn = 0.005697,
	uNResTfo = 138.0,
	XPQ = 0,
	TSQ0 = 0.08,
	SN = 270.0,
	TPQ0 = 0,
	ui0 = 0.19164674544702304,
	nDSat = 0,
	q0 = 0.030061008,
	mDSatIn = 0,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.235,
	transformerIncluded = true,
	XD = 1.61,
	uBMac = 15.5,
	ur0 = 0.9508793721936606,
	lStatIn = 0.17,
	nQSat = 0,
	XSQ = 0.29,
	mQSatIn = 0,
	XQ = 0.95,
	sNTfo = 270.0,
	PN = 252.0,
	IWLMDV = 3,
	uNMacTfo = 15.5,
	pPuWLMDV = 245.7,
	xTfoIn = 0.1
    ) annotation (Placement(transformation()));
  sexs_init sexs__GEN___15_SM
 (
	PNALT = 245.7,
	K = 200.,
	EMIN = 0.,
	TA = 3.,
	TB = 10.,
	SNREF = 100.0,
	TE = 0.05,
	EFDMIN = -999.,
	KC = 1.,
	EFDMAX = 999.,
	SN = 270.0,
	PN = 252.0,
	EMAX = 4.
  ) annotation (Placement(transformation()));
  gsteam0_init gsteam0__GEN___15_SM
 (
	DT = 0.,
	RR = 0.05,
	PNALT = 245.7,
	VMIN = 0.,
	VMAX = 1.,
	SN = 270.0,
	T1 = 0.5,
	T2 = 3.,
	SNREF = 100.0,
	PN = 252.0,
	T3 = 10.
  ) annotation (Placement(transformation()));
  pssi3e2b_init pssi3e2b__GEN___15_SM
 (
	PNALT = 245.7,
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
	SN = 270.0,
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
	PN = 252.0
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__GEN___15_SM.pin_TerminalVoltage, sexs__GEN___15_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___15_SM.pin_EFD, sexs__GEN___15_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___15_SM.pin_OMEGA, gsteam0__GEN___15_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___15_SM.pin_CM, gsteam0__GEN___15_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___15_SM.pin_ActivePowerSN, pssi3e2b__GEN___15_SM.pin_ActivePowerSN) annotation (Line());
	sexs__GEN___15_SM.pin_VS=0;
end _GEN___15_SM_Initialization;
model _GEN___18_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___18_SM
   (
	PNALT = 1485.0,
	p0 = 0.0,
	omega_0 = 1.0,
	IENR = 4,
	TX = 0,
	Saturated = true,
	HIn = 5.625,
	TSD0 = 0.065,
	XPD = 0.509,
	SNREF = 100.0,
	TPD0 = 10.041,
	rStatIn = 0.00316,
	uNResTfo = 138.0,
	XPQ = 0.601,
	TSQ0 = 0.094,
	SN = 1650.0,
	TPQ0 = 1.22,
	ui0 = 0.19714135807611474,
	nDSat = 9.285,
	q0 = 0.25259712,
	mDSatIn = 0.05,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.354,
	transformerIncluded = true,
	XD = 2.81,
	uBMac = 20.0,
	ur0 = 0.9528191147586608,
	lStatIn = 0.256,
	nQSat = 9.285,
	XSQ = 0.377,
	mQSatIn = 0.05,
	XQ = 2.62,
	sNTfo = 1650.0,
	PN = 1485.0,
	IWLMDV = 3,
	uNMacTfo = 20.0,
	pPuWLMDV = 1485.0,
	xTfoIn = 0.1
    ) annotation (Placement(transformation()));
  gsteam0_init gsteam0__GEN___18_SM
 (
	DT = 0.,
	RR = 0.05,
	PNALT = 1485.0,
	VMIN = 0.,
	VMAX = 1.,
	SN = 1650.0,
	T1 = 0.5,
	T2 = 3.,
	SNREF = 100.0,
	PN = 1485.0,
	T3 = 10.
  ) annotation (Placement(transformation()));
  pssi3e2b_init pssi3e2b__GEN___18_SM
 (
	PNALT = 1485.0,
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
	SN = 1650.0,
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
	PN = 1485.0
  ) annotation (Placement(transformation()));
  sexs_init sexs__GEN___18_SM
 (
	PNALT = 1485.0,
	K = 200.,
	EMIN = 0.,
	TA = 3.,
	TB = 10.,
	SNREF = 100.0,
	TE = 0.05,
	EFDMIN = -999.,
	KC = 1.,
	EFDMAX = 999.,
	SN = 1650.0,
	PN = 1485.0,
	EMAX = 4.
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__GEN___18_SM.pin_OMEGA, gsteam0__GEN___18_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___18_SM.pin_CM, gsteam0__GEN___18_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___18_SM.pin_ActivePowerSN, pssi3e2b__GEN___18_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___18_SM.pin_TerminalVoltage, sexs__GEN___18_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___18_SM.pin_EFD, sexs__GEN___18_SM.pin_EFD) annotation (Line());
	sexs__GEN___18_SM.pin_VS=0;
end _GEN___18_SM_Initialization;
model _GEN___19_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___19_SM
   (
	PNALT = 1485.0,
	p0 = 0.0,
	omega_0 = 1.0,
	IENR = 4,
	TX = 0,
	Saturated = true,
	HIn = 5.625,
	TSD0 = 0.065,
	XPD = 0.509,
	SNREF = 100.0,
	TPD0 = 10.041,
	rStatIn = 0.00316,
	uNResTfo = 138.0,
	XPQ = 0.601,
	TSQ0 = 0.094,
	SN = 1650.0,
	TPQ0 = 1.22,
	ui0 = 0.18732761599245695,
	nDSat = 9.285,
	q0 = -0.08,
	mDSatIn = 0.05,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.354,
	transformerIncluded = true,
	XD = 2.81,
	uBMac = 20.0,
	ur0 = 0.9449775606116433,
	lStatIn = 0.256,
	nQSat = 9.285,
	XSQ = 0.377,
	mQSatIn = 0.05,
	XQ = 2.62,
	sNTfo = 1650.0,
	PN = 1485.0,
	IWLMDV = 3,
	uNMacTfo = 20.0,
	pPuWLMDV = 1485.0,
	xTfoIn = 0.1
    ) annotation (Placement(transformation()));
  sexs_init sexs__GEN___19_SM
 (
	PNALT = 1485.0,
	K = 200.,
	EMIN = 0.,
	TA = 3.,
	TB = 10.,
	SNREF = 100.0,
	TE = 0.05,
	EFDMIN = -999.,
	KC = 1.,
	EFDMAX = 999.,
	SN = 1650.0,
	PN = 1485.0,
	EMAX = 4.
  ) annotation (Placement(transformation()));
  gsteam0_init gsteam0__GEN___19_SM
 (
	DT = 0.,
	RR = 0.05,
	PNALT = 1485.0,
	VMIN = 0.,
	VMAX = 1.,
	SN = 1650.0,
	T1 = 0.5,
	T2 = 3.,
	SNREF = 100.0,
	PN = 1485.0,
	T3 = 10.
  ) annotation (Placement(transformation()));
  pssi3e2b_init pssi3e2b__GEN___19_SM
 (
	PNALT = 1485.0,
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
	SN = 1650.0,
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
	PN = 1485.0
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__GEN___19_SM.pin_TerminalVoltage, sexs__GEN___19_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___19_SM.pin_EFD, sexs__GEN___19_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___19_SM.pin_OMEGA, gsteam0__GEN___19_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___19_SM.pin_CM, gsteam0__GEN___19_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___19_SM.pin_ActivePowerSN, pssi3e2b__GEN___19_SM.pin_ActivePowerSN) annotation (Line());
	sexs__GEN___19_SM.pin_VS=0;
end _GEN___19_SM_Initialization;
model _GEN___24_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___24_SM
   (
	PNALT = 1008.0,
	p0 = -0.13,
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
	ui0 = 0.35577545116743287,
	nDSat = 5.57,
	q0 = -0.15197472,
	mDSatIn = 0.084,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.3,
	transformerIncluded = true,
	XD = 2.57,
	uBMac = 24.0,
	ur0 = 0.9260063694063246,
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
  pssi3e2b_init pssi3e2b__GEN___24_SM
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
  sexs_init sexs__GEN___24_SM
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
  cmconst_init cmconst__GEN___24_SM
 (
	PNALT = 1008.0,
	SN = 1120.0,
	SNREF = 100.0,
	PN = 1008.0
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__GEN___24_SM.pin_ActivePowerSN, pssi3e2b__GEN___24_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___24_SM.pin_TerminalVoltage, sexs__GEN___24_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___24_SM.pin_EFD, sexs__GEN___24_SM.pin_EFD) annotation (Line());
	sexs__GEN___24_SM.pin_VS=0;
end _GEN___24_SM_Initialization;
model _GEN___25_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___25_SM
   (
	PNALT = 1090.0,
	p0 = 2.2,
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
	uNResTfo = 138.0,
	XPQ = 0.393,
	TSQ0 = 0.084,
	SN = 1211.0,
	TPQ0 = 1.572,
	ui0 = 0.4934859423320731,
	nDSat = 6.995,
	q0 = 0.50046057,
	mDSatIn = 0.215,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.264,
	transformerIncluded = true,
	XD = 2.22,
	uBMac = 24.0,
	ur0 = 0.9268071668825317,
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
  pssi3e2b_init pssi3e2b__GEN___25_SM
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
  gsteam0_init gsteam0__GEN___25_SM
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
  sexs_init sexs__GEN___25_SM
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
	connect(gen_pwGeneratorM2S__GEN___25_SM.pin_ActivePowerSN, pssi3e2b__GEN___25_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___25_SM.pin_OMEGA, gsteam0__GEN___25_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___25_SM.pin_CM, gsteam0__GEN___25_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___25_SM.pin_TerminalVoltage, sexs__GEN___25_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___25_SM.pin_EFD, sexs__GEN___25_SM.pin_EFD) annotation (Line());
	sexs__GEN___25_SM.pin_VS=0;
end _GEN___25_SM_Initialization;
model _GEN___26_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___26_SM
   (
	PNALT = 1539.0,
	p0 = 3.14,
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
	uNResTfo = 345.0,
	XPQ = 0.623,
	TSQ0 = 0.094,
	SN = 1710.0,
	TPQ0 = 1.22,
	ui0 = 0.5048875841156002,
	nDSat = 9.285,
	q0 = 0.09034688,
	mDSatIn = 0.05,
	uNomNw = 345.0,
	rTfoIn = 0.0,
	XSD = 0.367,
	transformerIncluded = true,
	XD = 2.91,
	uBMac = 20.0,
	ur0 = 0.8805188801874324,
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
  sexs_init sexs__GEN___26_SM
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
  gsteam0_init gsteam0__GEN___26_SM
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
  pssi3e2b_init pssi3e2b__GEN___26_SM
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
	connect(gen_pwGeneratorM2S__GEN___26_SM.pin_TerminalVoltage, sexs__GEN___26_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___26_SM.pin_EFD, sexs__GEN___26_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___26_SM.pin_OMEGA, gsteam0__GEN___26_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___26_SM.pin_CM, gsteam0__GEN___26_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___26_SM.pin_ActivePowerSN, pssi3e2b__GEN___26_SM.pin_ActivePowerSN) annotation (Line());
	sexs__GEN___26_SM.pin_VS=0;
end _GEN___26_SM_Initialization;
model _GEN___27_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___27_SM
   (
	PNALT = 1008.0,
	p0 = -0.09,
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
	ui0 = 0.256669310783447,
	nDSat = 5.57,
	q0 = 0.04111975,
	mDSatIn = 0.084,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.3,
	transformerIncluded = true,
	XD = 2.57,
	uBMac = 24.0,
	ur0 = 0.9333514101061969,
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
  sexs_init sexs__GEN___27_SM
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
  pssi3e2b_init pssi3e2b__GEN___27_SM
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
  cmconst_init cmconst__GEN___27_SM
 (
	PNALT = 1008.0,
	SN = 1120.0,
	SNREF = 100.0,
	PN = 1008.0
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__GEN___27_SM.pin_TerminalVoltage, sexs__GEN___27_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___27_SM.pin_EFD, sexs__GEN___27_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___27_SM.pin_ActivePowerSN, pssi3e2b__GEN___27_SM.pin_ActivePowerSN) annotation (Line());
	sexs__GEN___27_SM.pin_VS=0;
end _GEN___27_SM_Initialization;
model _GEN___31_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___31_SM
   (
	PNALT = 1008.0,
	p0 = 0.07,
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
	ui0 = 0.21421501341222052,
	nDSat = 5.57,
	q0 = 0.32430023,
	mDSatIn = 0.084,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.3,
	transformerIncluded = true,
	XD = 2.57,
	uBMac = 24.0,
	ur0 = 0.9429745186292423,
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
  pssi3e2b_init pssi3e2b__GEN___31_SM
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
  gsteam0_init gsteam0__GEN___31_SM
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
  sexs_init sexs__GEN___31_SM
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
    equation
	connect(gen_pwGeneratorM2S__GEN___31_SM.pin_ActivePowerSN, pssi3e2b__GEN___31_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___31_SM.pin_OMEGA, gsteam0__GEN___31_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___31_SM.pin_CM, gsteam0__GEN___31_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___31_SM.pin_TerminalVoltage, sexs__GEN___31_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___31_SM.pin_EFD, sexs__GEN___31_SM.pin_EFD) annotation (Line());
	sexs__GEN___31_SM.pin_VS=0;
end _GEN___31_SM_Initialization;
model _GEN___32_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___32_SM
   (
	PNALT = 1090.0,
	p0 = -0.04469648,
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
	uNResTfo = 138.0,
	XPQ = 0.393,
	TSQ0 = 0.084,
	SN = 1211.0,
	TPQ0 = 1.572,
	ui0 = 0.24599427331267498,
	nDSat = 6.995,
	q0 = -0.14897113,
	mDSatIn = 0.215,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.264,
	transformerIncluded = true,
	XD = 2.22,
	uBMac = 24.0,
	ur0 = 0.9310509205081001,
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
  sexs_init sexs__GEN___32_SM
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
  gsteam0_init gsteam0__GEN___32_SM
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
  pssi3e2b_init pssi3e2b__GEN___32_SM
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
    equation
	connect(gen_pwGeneratorM2S__GEN___32_SM.pin_TerminalVoltage, sexs__GEN___32_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___32_SM.pin_EFD, sexs__GEN___32_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___32_SM.pin_OMEGA, gsteam0__GEN___32_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___32_SM.pin_CM, gsteam0__GEN___32_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___32_SM.pin_ActivePowerSN, pssi3e2b__GEN___32_SM.pin_ActivePowerSN) annotation (Line());
	sexs__GEN___32_SM.pin_VS=0;
end _GEN___32_SM_Initialization;
model _GEN___34_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___34_SM
   (
	PNALT = 1485.0,
	p0 = 0.0,
	omega_0 = 1.0,
	IENR = 4,
	TX = 0,
	Saturated = true,
	HIn = 5.625,
	TSD0 = 0.065,
	XPD = 0.509,
	SNREF = 100.0,
	TPD0 = 10.041,
	rStatIn = 0.00316,
	uNResTfo = 138.0,
	XPQ = 0.601,
	TSQ0 = 0.094,
	SN = 1650.0,
	TPQ0 = 1.22,
	ui0 = 0.1960315803958879,
	nDSat = 9.285,
	q0 = -0.08,
	mDSatIn = 0.05,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.354,
	transformerIncluded = true,
	XD = 2.81,
	uBMac = 20.0,
	ur0 = 0.9655452070520543,
	lStatIn = 0.256,
	nQSat = 9.285,
	XSQ = 0.377,
	mQSatIn = 0.05,
	XQ = 2.62,
	sNTfo = 1650.0,
	PN = 1485.0,
	IWLMDV = 3,
	uNMacTfo = 20.0,
	pPuWLMDV = 1485.0,
	xTfoIn = 0.1
    ) annotation (Placement(transformation()));
  gsteam0_init gsteam0__GEN___34_SM
 (
	DT = 0.,
	RR = 0.05,
	PNALT = 1485.0,
	VMIN = 0.,
	VMAX = 1.,
	SN = 1650.0,
	T1 = 0.5,
	T2 = 3.,
	SNREF = 100.0,
	PN = 1485.0,
	T3 = 10.
  ) annotation (Placement(transformation()));
  pssi3e2b_init pssi3e2b__GEN___34_SM
 (
	PNALT = 1485.0,
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
	SN = 1650.0,
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
	PN = 1485.0
  ) annotation (Placement(transformation()));
  sexs_init sexs__GEN___34_SM
 (
	PNALT = 1485.0,
	K = 200.,
	EMIN = 0.,
	TA = 3.,
	TB = 10.,
	SNREF = 100.0,
	TE = 0.05,
	EFDMIN = -999.,
	KC = 1.,
	EFDMAX = 999.,
	SN = 1650.0,
	PN = 1485.0,
	EMAX = 4.
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__GEN___34_SM.pin_OMEGA, gsteam0__GEN___34_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___34_SM.pin_CM, gsteam0__GEN___34_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___34_SM.pin_ActivePowerSN, pssi3e2b__GEN___34_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___34_SM.pin_TerminalVoltage, sexs__GEN___34_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___34_SM.pin_EFD, sexs__GEN___34_SM.pin_EFD) annotation (Line());
	sexs__GEN___34_SM.pin_VS=0;
end _GEN___34_SM_Initialization;
model _GEN___36_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___36_SM
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
	ui0 = 0.1877180516920312,
	nDSat = 5.57,
	q0 = 0.02396707,
	mDSatIn = 0.084,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.3,
	transformerIncluded = true,
	XD = 2.57,
	uBMac = 24.0,
	ur0 = 0.9618534038266857,
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
  gsteam0_init gsteam0__GEN___36_SM
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
  pssi3e2b_init pssi3e2b__GEN___36_SM
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
  sexs_init sexs__GEN___36_SM
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
    equation
	connect(gen_pwGeneratorM2S__GEN___36_SM.pin_OMEGA, gsteam0__GEN___36_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___36_SM.pin_CM, gsteam0__GEN___36_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___36_SM.pin_ActivePowerSN, pssi3e2b__GEN___36_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___36_SM.pin_TerminalVoltage, sexs__GEN___36_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___36_SM.pin_EFD, sexs__GEN___36_SM.pin_EFD) annotation (Line());
	sexs__GEN___36_SM.pin_VS=0;
end _GEN___36_SM_Initialization;
model _GEN___40_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___40_SM
   (
	PNALT = 1539.0,
	p0 = -0.46,
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
	ui0 = 0.12680186625617323,
	nDSat = 9.285,
	q0 = 0.27844772,
	mDSatIn = 0.05,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.367,
	transformerIncluded = true,
	XD = 2.91,
	uBMac = 20.0,
	ur0 = 0.961676318840075,
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
  pssi3e2b_init pssi3e2b__GEN___40_SM
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
  cmconst_init cmconst__GEN___40_SM
 (
	PNALT = 1539.0,
	SN = 1710.0,
	SNREF = 100.0,
	PN = 1539.0
  ) annotation (Placement(transformation()));
  sexs_init sexs__GEN___40_SM
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
    equation
	connect(gen_pwGeneratorM2S__GEN___40_SM.pin_ActivePowerSN, pssi3e2b__GEN___40_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___40_SM.pin_TerminalVoltage, sexs__GEN___40_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___40_SM.pin_EFD, sexs__GEN___40_SM.pin_EFD) annotation (Line());
	sexs__GEN___40_SM.pin_VS=0;
end _GEN___40_SM_Initialization;
model _GEN___42_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___42_SM
   (
	PNALT = 1485.0,
	p0 = -0.59,
	omega_0 = 1.0,
	IENR = 4,
	TX = 0,
	Saturated = true,
	HIn = 5.625,
	TSD0 = 0.065,
	XPD = 0.509,
	SNREF = 100.0,
	TPD0 = 10.041,
	rStatIn = 0.00316,
	uNResTfo = 138.0,
	XPQ = 0.601,
	TSQ0 = 0.094,
	SN = 1650.0,
	TPQ0 = 1.22,
	ui0 = 0.14894295536129878,
	nDSat = 9.285,
	q0 = 0.41119796,
	mDSatIn = 0.05,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.354,
	transformerIncluded = true,
	XD = 2.81,
	uBMac = 20.0,
	ur0 = 0.9736739222184038,
	lStatIn = 0.256,
	nQSat = 9.285,
	XSQ = 0.377,
	mQSatIn = 0.05,
	XQ = 2.62,
	sNTfo = 1650.0,
	PN = 1485.0,
	IWLMDV = 3,
	uNMacTfo = 20.0,
	pPuWLMDV = 1485.0,
	xTfoIn = 0.1
    ) annotation (Placement(transformation()));
  sexs_init sexs__GEN___42_SM
 (
	PNALT = 1485.0,
	K = 200.,
	EMIN = 0.,
	TA = 3.,
	TB = 10.,
	SNREF = 100.0,
	TE = 0.05,
	EFDMIN = -999.,
	KC = 1.,
	EFDMAX = 999.,
	SN = 1650.0,
	PN = 1485.0,
	EMAX = 4.
  ) annotation (Placement(transformation()));
  pssi3e2b_init pssi3e2b__GEN___42_SM
 (
	PNALT = 1485.0,
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
	SN = 1650.0,
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
	PN = 1485.0
  ) annotation (Placement(transformation()));
  cmconst_init cmconst__GEN___42_SM
 (
	PNALT = 1485.0,
	SN = 1650.0,
	SNREF = 100.0,
	PN = 1485.0
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__GEN___42_SM.pin_TerminalVoltage, sexs__GEN___42_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___42_SM.pin_EFD, sexs__GEN___42_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___42_SM.pin_ActivePowerSN, pssi3e2b__GEN___42_SM.pin_ActivePowerSN) annotation (Line());
	sexs__GEN___42_SM.pin_VS=0;
end _GEN___42_SM_Initialization;
model _GEN___46_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___46_SM
   (
	PNALT = 1485.0,
	p0 = 0.19,
	omega_0 = 1.0,
	IENR = 4,
	TX = 0,
	Saturated = true,
	HIn = 5.625,
	TSD0 = 0.065,
	XPD = 0.509,
	SNREF = 100.0,
	TPD0 = 10.041,
	rStatIn = 0.00316,
	uNResTfo = 138.0,
	XPQ = 0.601,
	TSQ0 = 0.094,
	SN = 1650.0,
	TPQ0 = 1.22,
	ui0 = 0.32164035138791086,
	nDSat = 9.285,
	q0 = -0.05076183,
	mDSatIn = 0.05,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.354,
	transformerIncluded = true,
	XD = 2.81,
	uBMac = 20.0,
	ur0 = 0.9521409952179533,
	lStatIn = 0.256,
	nQSat = 9.285,
	XSQ = 0.377,
	mQSatIn = 0.05,
	XQ = 2.62,
	sNTfo = 1650.0,
	PN = 1485.0,
	IWLMDV = 3,
	uNMacTfo = 20.0,
	pPuWLMDV = 1485.0,
	xTfoIn = 0.1
    ) annotation (Placement(transformation()));
  gsteam0_init gsteam0__GEN___46_SM
 (
	DT = 0.,
	RR = 0.05,
	PNALT = 1485.0,
	VMIN = 0.,
	VMAX = 1.,
	SN = 1650.0,
	T1 = 0.5,
	T2 = 3.,
	SNREF = 100.0,
	PN = 1485.0,
	T3 = 10.
  ) annotation (Placement(transformation()));
  sexs_init sexs__GEN___46_SM
 (
	PNALT = 1485.0,
	K = 200.,
	EMIN = 0.,
	TA = 3.,
	TB = 10.,
	SNREF = 100.0,
	TE = 0.05,
	EFDMIN = -999.,
	KC = 1.,
	EFDMAX = 999.,
	SN = 1650.0,
	PN = 1485.0,
	EMAX = 4.
  ) annotation (Placement(transformation()));
  pssi3e2b_init pssi3e2b__GEN___46_SM
 (
	PNALT = 1485.0,
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
	SN = 1650.0,
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
	PN = 1485.0
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__GEN___46_SM.pin_OMEGA, gsteam0__GEN___46_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___46_SM.pin_CM, gsteam0__GEN___46_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___46_SM.pin_TerminalVoltage, sexs__GEN___46_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___46_SM.pin_EFD, sexs__GEN___46_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___46_SM.pin_ActivePowerSN, pssi3e2b__GEN___46_SM.pin_ActivePowerSN) annotation (Line());
	sexs__GEN___46_SM.pin_VS=0;
end _GEN___46_SM_Initialization;
model _GEN___49_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___49_SM
   (
	PNALT = 1008.0,
	p0 = 2.04,
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
	ui0 = 0.36924524414731286,
	nDSat = 5.57,
	q0 = 1.1605595,
	mDSatIn = 0.084,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.3,
	transformerIncluded = true,
	XD = 2.57,
	uBMac = 24.0,
	ur0 = 0.9561814162588516,
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
  pssi3e2b_init pssi3e2b__GEN___49_SM
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
  sexs_init sexs__GEN___49_SM
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
  gsteam0_init gsteam0__GEN___49_SM
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
	connect(gen_pwGeneratorM2S__GEN___49_SM.pin_ActivePowerSN, pssi3e2b__GEN___49_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___49_SM.pin_TerminalVoltage, sexs__GEN___49_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___49_SM.pin_EFD, sexs__GEN___49_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___49_SM.pin_OMEGA, gsteam0__GEN___49_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___49_SM.pin_CM, gsteam0__GEN___49_SM.pin_CM) annotation (Line());
	sexs__GEN___49_SM.pin_VS=0;
end _GEN___49_SM_Initialization;
model _GEN___54_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___54_SM
   (
	PNALT = 1008.0,
	p0 = 0.48,
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
	ui0 = 0.25426847713879897,
	nDSat = 5.57,
	q0 = 0.039010018,
	mDSatIn = 0.084,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.3,
	transformerIncluded = true,
	XD = 2.57,
	uBMac = 24.0,
	ur0 = 0.9205283242856171,
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
  pssi3e2b_init pssi3e2b__GEN___54_SM
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
  gsteam0_init gsteam0__GEN___54_SM
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
  sexs_init sexs__GEN___54_SM
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
    equation
	connect(gen_pwGeneratorM2S__GEN___54_SM.pin_ActivePowerSN, pssi3e2b__GEN___54_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___54_SM.pin_OMEGA, gsteam0__GEN___54_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___54_SM.pin_CM, gsteam0__GEN___54_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___54_SM.pin_TerminalVoltage, sexs__GEN___54_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___54_SM.pin_EFD, sexs__GEN___54_SM.pin_EFD) annotation (Line());
	sexs__GEN___54_SM.pin_VS=0;
end _GEN___54_SM_Initialization;
model _GEN___55_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___55_SM
   (
	PNALT = 245.7,
	p0 = 0.0,
	omega_0 = 1.0,
	IENR = 3,
	TX = 0,
	Saturated = false,
	HIn = 3.595,
	TSD0 = 0.03,
	XPD = 0.37,
	SNREF = 100.0,
	TPD0 = 7.4,
	rStatIn = 0.005697,
	uNResTfo = 138.0,
	XPQ = 0,
	TSQ0 = 0.08,
	SN = 270.0,
	TPQ0 = 0,
	ui0 = 0.24882444915930854,
	nDSat = 0,
	q0 = 0.04664985,
	mDSatIn = 0,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.235,
	transformerIncluded = true,
	XD = 1.61,
	uBMac = 15.5,
	ur0 = 0.9189071962194403,
	lStatIn = 0.17,
	nQSat = 0,
	XSQ = 0.29,
	mQSatIn = 0,
	XQ = 0.95,
	sNTfo = 270.0,
	PN = 252.0,
	IWLMDV = 3,
	uNMacTfo = 15.5,
	pPuWLMDV = 245.7,
	xTfoIn = 0.1
    ) annotation (Placement(transformation()));
  pssi3e2b_init pssi3e2b__GEN___55_SM
 (
	PNALT = 245.7,
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
	SN = 270.0,
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
	PN = 252.0
  ) annotation (Placement(transformation()));
  sexs_init sexs__GEN___55_SM
 (
	PNALT = 245.7,
	K = 200.,
	EMIN = 0.,
	TA = 3.,
	TB = 10.,
	SNREF = 100.0,
	TE = 0.05,
	EFDMIN = -999.,
	KC = 1.,
	EFDMAX = 999.,
	SN = 270.0,
	PN = 252.0,
	EMAX = 4.
  ) annotation (Placement(transformation()));
  gsteam0_init gsteam0__GEN___55_SM
 (
	DT = 0.,
	RR = 0.05,
	PNALT = 245.7,
	VMIN = 0.,
	VMAX = 1.,
	SN = 270.0,
	T1 = 0.5,
	T2 = 3.,
	SNREF = 100.0,
	PN = 252.0,
	T3 = 10.
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__GEN___55_SM.pin_ActivePowerSN, pssi3e2b__GEN___55_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___55_SM.pin_TerminalVoltage, sexs__GEN___55_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___55_SM.pin_EFD, sexs__GEN___55_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___55_SM.pin_OMEGA, gsteam0__GEN___55_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___55_SM.pin_CM, gsteam0__GEN___55_SM.pin_CM) annotation (Line());
	sexs__GEN___55_SM.pin_VS=0;
end _GEN___55_SM_Initialization;
model _GEN___56_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___56_SM
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
	uNResTfo = 138.0,
	XPQ = 0.393,
	TSQ0 = 0.084,
	SN = 1211.0,
	TPQ0 = 1.572,
	ui0 = 0.25234575366758016,
	nDSat = 6.995,
	q0 = -0.022863751,
	mDSatIn = 0.215,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.264,
	transformerIncluded = true,
	XD = 2.22,
	uBMac = 24.0,
	ur0 = 0.9200203799926611,
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
  gsteam0_init gsteam0__GEN___56_SM
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
  pssi3e2b_init pssi3e2b__GEN___56_SM
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
  sexs_init sexs__GEN___56_SM
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
	connect(gen_pwGeneratorM2S__GEN___56_SM.pin_OMEGA, gsteam0__GEN___56_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___56_SM.pin_CM, gsteam0__GEN___56_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___56_SM.pin_ActivePowerSN, pssi3e2b__GEN___56_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___56_SM.pin_TerminalVoltage, sexs__GEN___56_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___56_SM.pin_EFD, sexs__GEN___56_SM.pin_EFD) annotation (Line());
	sexs__GEN___56_SM.pin_VS=0;
end _GEN___56_SM_Initialization;
model _GEN___59_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___59_SM
   (
	PNALT = 1090.0,
	p0 = 1.55,
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
	uNResTfo = 138.0,
	XPQ = 0.393,
	TSQ0 = 0.084,
	SN = 1211.0,
	TPQ0 = 1.572,
	ui0 = 0.32947302203642087,
	nDSat = 6.995,
	q0 = 0.8060065,
	mDSatIn = 0.215,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.264,
	transformerIncluded = true,
	XD = 2.22,
	uBMac = 24.0,
	ur0 = 0.9282631299960783,
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
  sexs_init sexs__GEN___59_SM
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
  pssi3e2b_init pssi3e2b__GEN___59_SM
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
  gsteam0_init gsteam0__GEN___59_SM
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
    equation
	connect(gen_pwGeneratorM2S__GEN___59_SM.pin_TerminalVoltage, sexs__GEN___59_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___59_SM.pin_EFD, sexs__GEN___59_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___59_SM.pin_ActivePowerSN, pssi3e2b__GEN___59_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___59_SM.pin_OMEGA, gsteam0__GEN___59_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___59_SM.pin_CM, gsteam0__GEN___59_SM.pin_CM) annotation (Line());
	sexs__GEN___59_SM.pin_VS=0;
end _GEN___59_SM_Initialization;
model _GEN___61_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___61_SM
   (
	PNALT = 1485.0,
	p0 = 1.6,
	omega_0 = 1.0,
	IENR = 4,
	TX = 0,
	Saturated = true,
	HIn = 5.625,
	TSD0 = 0.065,
	XPD = 0.509,
	SNREF = 100.0,
	TPD0 = 10.041,
	rStatIn = 0.00316,
	uNResTfo = 138.0,
	XPQ = 0.601,
	TSQ0 = 0.094,
	SN = 1650.0,
	TPQ0 = 1.22,
	ui0 = 0.408227854305722,
	nDSat = 9.285,
	q0 = -0.51088226,
	mDSatIn = 0.05,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.354,
	transformerIncluded = true,
	XD = 2.81,
	uBMac = 20.0,
	ur0 = 0.9074001479270354,
	lStatIn = 0.256,
	nQSat = 9.285,
	XSQ = 0.377,
	mQSatIn = 0.05,
	XQ = 2.62,
	sNTfo = 1650.0,
	PN = 1485.0,
	IWLMDV = 3,
	uNMacTfo = 20.0,
	pPuWLMDV = 1485.0,
	xTfoIn = 0.1
    ) annotation (Placement(transformation()));
  sexs_init sexs__GEN___61_SM
 (
	PNALT = 1485.0,
	K = 200.,
	EMIN = 0.,
	TA = 3.,
	TB = 10.,
	SNREF = 100.0,
	TE = 0.05,
	EFDMIN = -999.,
	KC = 1.,
	EFDMAX = 999.,
	SN = 1650.0,
	PN = 1485.0,
	EMAX = 4.
  ) annotation (Placement(transformation()));
  gsteam0_init gsteam0__GEN___61_SM
 (
	DT = 0.,
	RR = 0.05,
	PNALT = 1485.0,
	VMIN = 0.,
	VMAX = 1.,
	SN = 1650.0,
	T1 = 0.5,
	T2 = 3.,
	SNREF = 100.0,
	PN = 1485.0,
	T3 = 10.
  ) annotation (Placement(transformation()));
  pssi3e2b_init pssi3e2b__GEN___61_SM
 (
	PNALT = 1485.0,
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
	SN = 1650.0,
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
	PN = 1485.0
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__GEN___61_SM.pin_TerminalVoltage, sexs__GEN___61_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___61_SM.pin_EFD, sexs__GEN___61_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___61_SM.pin_OMEGA, gsteam0__GEN___61_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___61_SM.pin_CM, gsteam0__GEN___61_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___61_SM.pin_ActivePowerSN, pssi3e2b__GEN___61_SM.pin_ActivePowerSN) annotation (Line());
	sexs__GEN___61_SM.pin_VS=0;
end _GEN___61_SM_Initialization;
model _GEN___62_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___62_SM
   (
	PNALT = 1485.0,
	p0 = 0.0,
	omega_0 = 1.0,
	IENR = 4,
	TX = 0,
	Saturated = true,
	HIn = 5.625,
	TSD0 = 0.065,
	XPD = 0.509,
	SNREF = 100.0,
	TPD0 = 10.041,
	rStatIn = 0.00316,
	uNResTfo = 138.0,
	XPQ = 0.601,
	TSQ0 = 0.094,
	SN = 1650.0,
	TPQ0 = 1.22,
	ui0 = 0.3996248932774912,
	nDSat = 9.285,
	q0 = 0.01256506,
	mDSatIn = 0.05,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.354,
	transformerIncluded = true,
	XD = 2.81,
	uBMac = 20.0,
	ur0 = 0.9144965156289554,
	lStatIn = 0.256,
	nQSat = 9.285,
	XSQ = 0.377,
	mQSatIn = 0.05,
	XQ = 2.62,
	sNTfo = 1650.0,
	PN = 1485.0,
	IWLMDV = 3,
	uNMacTfo = 20.0,
	pPuWLMDV = 1485.0,
	xTfoIn = 0.1
    ) annotation (Placement(transformation()));
  gsteam0_init gsteam0__GEN___62_SM
 (
	DT = 0.,
	RR = 0.05,
	PNALT = 1485.0,
	VMIN = 0.,
	VMAX = 1.,
	SN = 1650.0,
	T1 = 0.5,
	T2 = 3.,
	SNREF = 100.0,
	PN = 1485.0,
	T3 = 10.
  ) annotation (Placement(transformation()));
  pssi3e2b_init pssi3e2b__GEN___62_SM
 (
	PNALT = 1485.0,
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
	SN = 1650.0,
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
	PN = 1485.0
  ) annotation (Placement(transformation()));
  sexs_init sexs__GEN___62_SM
 (
	PNALT = 1485.0,
	K = 200.,
	EMIN = 0.,
	TA = 3.,
	TB = 10.,
	SNREF = 100.0,
	TE = 0.05,
	EFDMIN = -999.,
	KC = 1.,
	EFDMAX = 999.,
	SN = 1650.0,
	PN = 1485.0,
	EMAX = 4.
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__GEN___62_SM.pin_OMEGA, gsteam0__GEN___62_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___62_SM.pin_CM, gsteam0__GEN___62_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___62_SM.pin_ActivePowerSN, pssi3e2b__GEN___62_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___62_SM.pin_TerminalVoltage, sexs__GEN___62_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___62_SM.pin_EFD, sexs__GEN___62_SM.pin_EFD) annotation (Line());
	sexs__GEN___62_SM.pin_VS=0;
end _GEN___62_SM_Initialization;
model _GEN___65_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___65_SM
   (
	PNALT = 1539.0,
	p0 = 3.91,
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
	uNResTfo = 345.0,
	XPQ = 0.623,
	TSQ0 = 0.094,
	SN = 1710.0,
	TPQ0 = 1.22,
	ui0 = 0.4690100687138806,
	nDSat = 9.285,
	q0 = 0.641248,
	mDSatIn = 0.05,
	uNomNw = 345.0,
	rTfoIn = 0.0,
	XSD = 0.367,
	transformerIncluded = true,
	XD = 2.91,
	uBMac = 20.0,
	ur0 = 0.8888501256458111,
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
  pssi3e2b_init pssi3e2b__GEN___65_SM
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
  gsteam0_init gsteam0__GEN___65_SM
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
  sexs_init sexs__GEN___65_SM
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
    equation
	connect(gen_pwGeneratorM2S__GEN___65_SM.pin_ActivePowerSN, pssi3e2b__GEN___65_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___65_SM.pin_OMEGA, gsteam0__GEN___65_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___65_SM.pin_CM, gsteam0__GEN___65_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___65_SM.pin_TerminalVoltage, sexs__GEN___65_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___65_SM.pin_EFD, sexs__GEN___65_SM.pin_EFD) annotation (Line());
	sexs__GEN___65_SM.pin_VS=0;
end _GEN___65_SM_Initialization;
model _GEN___66_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___66_SM
   (
	PNALT = 1539.0,
	p0 = 3.92,
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
	ui0 = 0.48736416669379,
	nDSat = 9.285,
	q0 = 0.14263482,
	mDSatIn = 0.05,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.367,
	transformerIncluded = true,
	XD = 2.91,
	uBMac = 20.0,
	ur0 = 0.9300408963519113,
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
  sexs_init sexs__GEN___66_SM
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
  pssi3e2b_init pssi3e2b__GEN___66_SM
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
  gsteam0_init gsteam0__GEN___66_SM
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
    equation
	connect(gen_pwGeneratorM2S__GEN___66_SM.pin_TerminalVoltage, sexs__GEN___66_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___66_SM.pin_EFD, sexs__GEN___66_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___66_SM.pin_ActivePowerSN, pssi3e2b__GEN___66_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___66_SM.pin_OMEGA, gsteam0__GEN___66_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___66_SM.pin_CM, gsteam0__GEN___66_SM.pin_CM) annotation (Line());
	sexs__GEN___66_SM.pin_VS=0;
end _GEN___66_SM_Initialization;
model _GEN___69_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___69_SM
   (
	PNALT = 1008.0,
	p0 = 5.1856,
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
	ui0 = 0.5200765011539398,
	nDSat = 5.57,
	q0 = -0.69074845,
	mDSatIn = 0.084,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.3,
	transformerIncluded = true,
	XD = 2.57,
	uBMac = 24.0,
	ur0 = 0.8948437650527453,
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
  sexs_init sexs__GEN___69_SM
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
  gsteam0_init gsteam0__GEN___69_SM
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
  pssi3e2b_init pssi3e2b__GEN___69_SM
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
    equation
	connect(gen_pwGeneratorM2S__GEN___69_SM.pin_TerminalVoltage, sexs__GEN___69_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___69_SM.pin_EFD, sexs__GEN___69_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___69_SM.pin_OMEGA, gsteam0__GEN___69_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___69_SM.pin_CM, gsteam0__GEN___69_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___69_SM.pin_ActivePowerSN, pssi3e2b__GEN___69_SM.pin_ActivePowerSN) annotation (Line());
	sexs__GEN___69_SM.pin_VS=0;
end _GEN___69_SM_Initialization;
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
model _GEN___72_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___72_SM
   (
	PNALT = 1090.0,
	p0 = -0.12,
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
	uNResTfo = 138.0,
	XPQ = 0.393,
	TSQ0 = 0.084,
	SN = 1211.0,
	TPQ0 = 1.572,
	ui0 = 0.35282123333657117,
	nDSat = 6.995,
	q0 = -0.111126386,
	mDSatIn = 0.215,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.264,
	transformerIncluded = true,
	XD = 2.22,
	uBMac = 24.0,
	ur0 = 0.9142850839267227,
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
  pssi3e2b_init pssi3e2b__GEN___72_SM
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
  cmconst_init cmconst__GEN___72_SM
 (
	PNALT = 1090.0,
	SN = 1211.0,
	SNREF = 100.0,
	PN = 1090.0
  ) annotation (Placement(transformation()));
  sexs_init sexs__GEN___72_SM
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
	connect(gen_pwGeneratorM2S__GEN___72_SM.pin_ActivePowerSN, pssi3e2b__GEN___72_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___72_SM.pin_TerminalVoltage, sexs__GEN___72_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___72_SM.pin_EFD, sexs__GEN___72_SM.pin_EFD) annotation (Line());
	sexs__GEN___72_SM.pin_VS=0;
end _GEN___72_SM_Initialization;
model _GEN___73_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___73_SM
   (
	PNALT = 1090.0,
	p0 = -0.06,
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
	uNResTfo = 138.0,
	XPQ = 0.393,
	TSQ0 = 0.084,
	SN = 1211.0,
	TPQ0 = 1.572,
	ui0 = 0.3723852795969595,
	nDSat = 6.995,
	q0 = 0.09659927,
	mDSatIn = 0.215,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.264,
	transformerIncluded = true,
	XD = 2.22,
	uBMac = 24.0,
	ur0 = 0.9183736695506114,
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
  sexs_init sexs__GEN___73_SM
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
  pssi3e2b_init pssi3e2b__GEN___73_SM
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
  cmconst_init cmconst__GEN___73_SM
 (
	PNALT = 1090.0,
	SN = 1211.0,
	SNREF = 100.0,
	PN = 1090.0
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__GEN___73_SM.pin_TerminalVoltage, sexs__GEN___73_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___73_SM.pin_EFD, sexs__GEN___73_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___73_SM.pin_ActivePowerSN, pssi3e2b__GEN___73_SM.pin_ActivePowerSN) annotation (Line());
	sexs__GEN___73_SM.pin_VS=0;
end _GEN___73_SM_Initialization;
model _GEN___74_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___74_SM
   (
	PNALT = 1485.0,
	p0 = 0.0,
	omega_0 = 1.0,
	IENR = 4,
	TX = 0,
	Saturated = true,
	HIn = 5.625,
	TSD0 = 0.065,
	XPD = 0.509,
	SNREF = 100.0,
	TPD0 = 10.041,
	rStatIn = 0.00316,
	uNResTfo = 138.0,
	XPQ = 0.601,
	TSQ0 = 0.094,
	SN = 1650.0,
	TPQ0 = 1.22,
	ui0 = 0.355540551181822,
	nDSat = 9.285,
	q0 = -0.05579436,
	mDSatIn = 0.05,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.354,
	transformerIncluded = true,
	XD = 2.81,
	uBMac = 20.0,
	ur0 = 0.8895812556958526,
	lStatIn = 0.256,
	nQSat = 9.285,
	XSQ = 0.377,
	mQSatIn = 0.05,
	XQ = 2.62,
	sNTfo = 1650.0,
	PN = 1485.0,
	IWLMDV = 3,
	uNMacTfo = 20.0,
	pPuWLMDV = 1485.0,
	xTfoIn = 0.1
    ) annotation (Placement(transformation()));
  pssi3e2b_init pssi3e2b__GEN___74_SM
 (
	PNALT = 1485.0,
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
	SN = 1650.0,
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
	PN = 1485.0
  ) annotation (Placement(transformation()));
  gsteam0_init gsteam0__GEN___74_SM
 (
	DT = 0.,
	RR = 0.05,
	PNALT = 1485.0,
	VMIN = 0.,
	VMAX = 1.,
	SN = 1650.0,
	T1 = 0.5,
	T2 = 3.,
	SNREF = 100.0,
	PN = 1485.0,
	T3 = 10.
  ) annotation (Placement(transformation()));
  sexs_init sexs__GEN___74_SM
 (
	PNALT = 1485.0,
	K = 200.,
	EMIN = 0.,
	TA = 3.,
	TB = 10.,
	SNREF = 100.0,
	TE = 0.05,
	EFDMIN = -999.,
	KC = 1.,
	EFDMAX = 999.,
	SN = 1650.0,
	PN = 1485.0,
	EMAX = 4.
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__GEN___74_SM.pin_ActivePowerSN, pssi3e2b__GEN___74_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___74_SM.pin_OMEGA, gsteam0__GEN___74_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___74_SM.pin_CM, gsteam0__GEN___74_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___74_SM.pin_TerminalVoltage, sexs__GEN___74_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___74_SM.pin_EFD, sexs__GEN___74_SM.pin_EFD) annotation (Line());
	sexs__GEN___74_SM.pin_VS=0;
end _GEN___74_SM_Initialization;
model _GEN___76_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___76_SM
   (
	PNALT = 228.0,
	p0 = 0.0,
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
	ui0 = 0.3521304864905564,
	nDSat = 0,
	q0 = 0.05289536,
	mDSatIn = 0,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.275,
	transformerIncluded = true,
	XD = 1.53,
	uBMac = 18.0,
	ur0 = 0.8747874916556618,
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
  pssi3e2b_init pssi3e2b__GEN___76_SM
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
  gsteam0_init gsteam0__GEN___76_SM
 (
	DT = 0.,
	RR = 0.05,
	PNALT = 228.0,
	VMIN = 0.,
	VMAX = 1.,
	SN = 250.0,
	T1 = 0.5,
	T2 = 3.,
	SNREF = 100.0,
	PN = 242.0,
	T3 = 10.
  ) annotation (Placement(transformation()));
  sexs_init sexs__GEN___76_SM
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
	connect(gen_pwGeneratorM2S__GEN___76_SM.pin_ActivePowerSN, pssi3e2b__GEN___76_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___76_SM.pin_OMEGA, gsteam0__GEN___76_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___76_SM.pin_CM, gsteam0__GEN___76_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___76_SM.pin_TerminalVoltage, sexs__GEN___76_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___76_SM.pin_EFD, sexs__GEN___76_SM.pin_EFD) annotation (Line());
	sexs__GEN___76_SM.pin_VS=0;
end _GEN___76_SM_Initialization;
model _GEN___77_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___77_SM
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
	uNResTfo = 138.0,
	XPQ = 0.393,
	TSQ0 = 0.084,
	SN = 1211.0,
	TPQ0 = 1.572,
	ui0 = 0.4548866242376202,
	nDSat = 6.995,
	q0 = 0.11973465,
	mDSatIn = 0.215,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.264,
	transformerIncluded = true,
	XD = 2.22,
	uBMac = 24.0,
	ur0 = 0.8972815854103904,
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
  gsteam0_init gsteam0__GEN___77_SM
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
  sexs_init sexs__GEN___77_SM
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
  pssi3e2b_init pssi3e2b__GEN___77_SM
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
    equation
	connect(gen_pwGeneratorM2S__GEN___77_SM.pin_OMEGA, gsteam0__GEN___77_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___77_SM.pin_CM, gsteam0__GEN___77_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___77_SM.pin_TerminalVoltage, sexs__GEN___77_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___77_SM.pin_EFD, sexs__GEN___77_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___77_SM.pin_ActivePowerSN, pssi3e2b__GEN___77_SM.pin_ActivePowerSN) annotation (Line());
	sexs__GEN___77_SM.pin_VS=0;
end _GEN___77_SM_Initialization;
model _GEN___80_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___80_SM
   (
	PNALT = 1485.0,
	p0 = 4.77,
	omega_0 = 1.0,
	IENR = 4,
	TX = 0,
	Saturated = true,
	HIn = 5.625,
	TSD0 = 0.065,
	XPD = 0.509,
	SNREF = 100.0,
	TPD0 = 10.041,
	rStatIn = 0.00316,
	uNResTfo = 138.0,
	XPQ = 0.601,
	TSQ0 = 0.094,
	SN = 1650.0,
	TPQ0 = 1.22,
	ui0 = 0.506057907671313,
	nDSat = 9.285,
	q0 = 1.1339283,
	mDSatIn = 0.05,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.354,
	transformerIncluded = true,
	XD = 2.81,
	uBMac = 20.0,
	ur0 = 0.9085733667090173,
	lStatIn = 0.256,
	nQSat = 9.285,
	XSQ = 0.377,
	mQSatIn = 0.05,
	XQ = 2.62,
	sNTfo = 1650.0,
	PN = 1485.0,
	IWLMDV = 3,
	uNMacTfo = 20.0,
	pPuWLMDV = 1485.0,
	xTfoIn = 0.1
    ) annotation (Placement(transformation()));
  pssi3e2b_init pssi3e2b__GEN___80_SM
 (
	PNALT = 1485.0,
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
	SN = 1650.0,
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
	PN = 1485.0
  ) annotation (Placement(transformation()));
  gsteam0_init gsteam0__GEN___80_SM
 (
	DT = 0.,
	RR = 0.05,
	PNALT = 1485.0,
	VMIN = 0.,
	VMAX = 1.,
	SN = 1650.0,
	T1 = 0.5,
	T2 = 3.,
	SNREF = 100.0,
	PN = 1485.0,
	T3 = 10.
  ) annotation (Placement(transformation()));
  sexs_init sexs__GEN___80_SM
 (
	PNALT = 1485.0,
	K = 200.,
	EMIN = 0.,
	TA = 3.,
	TB = 10.,
	SNREF = 100.0,
	TE = 0.05,
	EFDMIN = -999.,
	KC = 1.,
	EFDMAX = 999.,
	SN = 1650.0,
	PN = 1485.0,
	EMAX = 4.
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__GEN___80_SM.pin_ActivePowerSN, pssi3e2b__GEN___80_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___80_SM.pin_OMEGA, gsteam0__GEN___80_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___80_SM.pin_CM, gsteam0__GEN___80_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___80_SM.pin_TerminalVoltage, sexs__GEN___80_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___80_SM.pin_EFD, sexs__GEN___80_SM.pin_EFD) annotation (Line());
	sexs__GEN___80_SM.pin_VS=0;
end _GEN___80_SM_Initialization;
model _GEN___85_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___85_SM
   (
	PNALT = 1485.0,
	p0 = 0.0,
	omega_0 = 1.0,
	IENR = 4,
	TX = 0,
	Saturated = true,
	HIn = 5.625,
	TSD0 = 0.065,
	XPD = 0.509,
	SNREF = 100.0,
	TPD0 = 10.041,
	rStatIn = 0.00316,
	uNResTfo = 138.0,
	XPQ = 0.601,
	TSQ0 = 0.094,
	SN = 1650.0,
	TPQ0 = 1.22,
	ui0 = 0.5317563884573837,
	nDSat = 9.285,
	q0 = -0.057716087,
	mDSatIn = 0.05,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.354,
	transformerIncluded = true,
	XD = 2.81,
	uBMac = 20.0,
	ur0 = 0.8291321089516965,
	lStatIn = 0.256,
	nQSat = 9.285,
	XSQ = 0.377,
	mQSatIn = 0.05,
	XQ = 2.62,
	sNTfo = 1650.0,
	PN = 1485.0,
	IWLMDV = 3,
	uNMacTfo = 20.0,
	pPuWLMDV = 1485.0,
	xTfoIn = 0.1
    ) annotation (Placement(transformation()));
  sexs_init sexs__GEN___85_SM
 (
	PNALT = 1485.0,
	K = 200.,
	EMIN = 0.,
	TA = 3.,
	TB = 10.,
	SNREF = 100.0,
	TE = 0.05,
	EFDMIN = -999.,
	KC = 1.,
	EFDMAX = 999.,
	SN = 1650.0,
	PN = 1485.0,
	EMAX = 4.
  ) annotation (Placement(transformation()));
  pssi3e2b_init pssi3e2b__GEN___85_SM
 (
	PNALT = 1485.0,
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
	SN = 1650.0,
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
	PN = 1485.0
  ) annotation (Placement(transformation()));
  gsteam0_init gsteam0__GEN___85_SM
 (
	DT = 0.,
	RR = 0.05,
	PNALT = 1485.0,
	VMIN = 0.,
	VMAX = 1.,
	SN = 1650.0,
	T1 = 0.5,
	T2 = 3.,
	SNREF = 100.0,
	PN = 1485.0,
	T3 = 10.
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__GEN___85_SM.pin_TerminalVoltage, sexs__GEN___85_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___85_SM.pin_EFD, sexs__GEN___85_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___85_SM.pin_ActivePowerSN, pssi3e2b__GEN___85_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___85_SM.pin_OMEGA, gsteam0__GEN___85_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___85_SM.pin_CM, gsteam0__GEN___85_SM.pin_CM) annotation (Line());
	sexs__GEN___85_SM.pin_VS=0;
end _GEN___85_SM_Initialization;
model _GEN___87_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___87_SM
   (
	PNALT = 1539.0,
	p0 = 0.04,
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
	ui0 = 0.5312945964476625,
	nDSat = 9.285,
	q0 = 0.11021636,
	mDSatIn = 0.05,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.367,
	transformerIncluded = true,
	XD = 2.91,
	uBMac = 20.0,
	ur0 = 0.8648417570521217,
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
  pssi3e2b_init pssi3e2b__GEN___87_SM
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
  gsteam0_init gsteam0__GEN___87_SM
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
  sexs_init sexs__GEN___87_SM
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
    equation
	connect(gen_pwGeneratorM2S__GEN___87_SM.pin_ActivePowerSN, pssi3e2b__GEN___87_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___87_SM.pin_OMEGA, gsteam0__GEN___87_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___87_SM.pin_CM, gsteam0__GEN___87_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___87_SM.pin_TerminalVoltage, sexs__GEN___87_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___87_SM.pin_EFD, sexs__GEN___87_SM.pin_EFD) annotation (Line());
	sexs__GEN___87_SM.pin_VS=0;
end _GEN___87_SM_Initialization;
model _GEN___89_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___89_SM
   (
	PNALT = 1090.0,
	p0 = 6.07,
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
	uNResTfo = 138.0,
	XPQ = 0.393,
	TSQ0 = 0.084,
	SN = 1211.0,
	TPQ0 = 1.572,
	ui0 = 0.6440996585856303,
	nDSat = 6.995,
	q0 = -0.11786947,
	mDSatIn = 0.215,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.264,
	transformerIncluded = true,
	XD = 2.22,
	uBMac = 24.0,
	ur0 = 0.7714665386298019,
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
  sexs_init sexs__GEN___89_SM
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
  gsteam0_init gsteam0__GEN___89_SM
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
  pssi3e2b_init pssi3e2b__GEN___89_SM
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
    equation
	connect(gen_pwGeneratorM2S__GEN___89_SM.pin_TerminalVoltage, sexs__GEN___89_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___89_SM.pin_EFD, sexs__GEN___89_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___89_SM.pin_OMEGA, gsteam0__GEN___89_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___89_SM.pin_CM, gsteam0__GEN___89_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___89_SM.pin_ActivePowerSN, pssi3e2b__GEN___89_SM.pin_ActivePowerSN) annotation (Line());
	sexs__GEN___89_SM.pin_VS=0;
end _GEN___89_SM_Initialization;
model _GEN___90_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___90_SM
   (
	PNALT = 1090.0,
	p0 = -0.85,
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
	uNResTfo = 138.0,
	XPQ = 0.393,
	TSQ0 = 0.084,
	SN = 1211.0,
	TPQ0 = 1.572,
	ui0 = 0.5429927046335163,
	nDSat = 6.995,
	q0 = 0.59299767,
	mDSatIn = 0.215,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.264,
	transformerIncluded = true,
	XD = 2.22,
	uBMac = 24.0,
	ur0 = 0.8218173966731945,
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
  cmconst_init cmconst__GEN___90_SM
 (
	PNALT = 1090.0,
	SN = 1211.0,
	SNREF = 100.0,
	PN = 1090.0
  ) annotation (Placement(transformation()));
  pssi3e2b_init pssi3e2b__GEN___90_SM
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
  sexs_init sexs__GEN___90_SM
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
	connect(gen_pwGeneratorM2S__GEN___90_SM.pin_ActivePowerSN, pssi3e2b__GEN___90_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___90_SM.pin_TerminalVoltage, sexs__GEN___90_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___90_SM.pin_EFD, sexs__GEN___90_SM.pin_EFD) annotation (Line());
	sexs__GEN___90_SM.pin_VS=0;
end _GEN___90_SM_Initialization;
model _GEN___91_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___91_SM
   (
	PNALT = 1090.0,
	p0 = -0.1,
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
	uNResTfo = 138.0,
	XPQ = 0.393,
	TSQ0 = 0.084,
	SN = 1211.0,
	TPQ0 = 1.572,
	ui0 = 0.5405042043660181,
	nDSat = 6.995,
	q0 = -0.14843822,
	mDSatIn = 0.215,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.264,
	transformerIncluded = true,
	XD = 2.22,
	uBMac = 24.0,
	ur0 = 0.8174688021243939,
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
  pssi3e2b_init pssi3e2b__GEN___91_SM
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
  cmconst_init cmconst__GEN___91_SM
 (
	PNALT = 1090.0,
	SN = 1211.0,
	SNREF = 100.0,
	PN = 1090.0
  ) annotation (Placement(transformation()));
  sexs_init sexs__GEN___91_SM
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
	connect(gen_pwGeneratorM2S__GEN___91_SM.pin_ActivePowerSN, pssi3e2b__GEN___91_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___91_SM.pin_TerminalVoltage, sexs__GEN___91_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___91_SM.pin_EFD, sexs__GEN___91_SM.pin_EFD) annotation (Line());
	sexs__GEN___91_SM.pin_VS=0;
end _GEN___91_SM_Initialization;
model _GEN___92_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___92_SM
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
	uNResTfo = 138.0,
	XPQ = 0.393,
	TSQ0 = 0.084,
	SN = 1211.0,
	TPQ0 = 1.572,
	ui0 = 0.5544580629189881,
	nDSat = 6.995,
	q0 = -0.03,
	mDSatIn = 0.215,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.264,
	transformerIncluded = true,
	XD = 2.22,
	uBMac = 24.0,
	ur0 = 0.8229125014393226,
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
  sexs_init sexs__GEN___92_SM
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
  gsteam0_init gsteam0__GEN___92_SM
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
  pssi3e2b_init pssi3e2b__GEN___92_SM
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
    equation
	connect(gen_pwGeneratorM2S__GEN___92_SM.pin_TerminalVoltage, sexs__GEN___92_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___92_SM.pin_EFD, sexs__GEN___92_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___92_SM.pin_OMEGA, gsteam0__GEN___92_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___92_SM.pin_CM, gsteam0__GEN___92_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___92_SM.pin_ActivePowerSN, pssi3e2b__GEN___92_SM.pin_ActivePowerSN) annotation (Line());
	sexs__GEN___92_SM.pin_VS=0;
end _GEN___92_SM_Initialization;
model _GEN___99_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN___99_SM
   (
	PNALT = 1539.0,
	p0 = -0.42,
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
	ui0 = 0.4616658868432344,
	nDSat = 9.285,
	q0 = -0.17536311,
	mDSatIn = 0.05,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.367,
	transformerIncluded = true,
	XD = 2.91,
	uBMac = 20.0,
	ur0 = 0.898312078100383,
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
  sexs_init sexs__GEN___99_SM
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
  pssi3e2b_init pssi3e2b__GEN___99_SM
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
  cmconst_init cmconst__GEN___99_SM
 (
	PNALT = 1539.0,
	SN = 1710.0,
	SNREF = 100.0,
	PN = 1539.0
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__GEN___99_SM.pin_TerminalVoltage, sexs__GEN___99_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___99_SM.pin_EFD, sexs__GEN___99_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___99_SM.pin_ActivePowerSN, pssi3e2b__GEN___99_SM.pin_ActivePowerSN) annotation (Line());
	sexs__GEN___99_SM.pin_VS=0;
end _GEN___99_SM_Initialization;
model _GEN____1_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN____1_SM
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
	uNResTfo = 138.0,
	XPQ = 0.393,
	TSQ0 = 0.084,
	SN = 1211.0,
	TPQ0 = 1.572,
	ui0 = 0.18082847764912863,
	nDSat = 6.995,
	q0 = -0.03647595,
	mDSatIn = 0.215,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.264,
	transformerIncluded = true,
	XD = 2.22,
	uBMac = 24.0,
	ur0 = 0.9377237951282184,
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
  pssi3e2b_init pssi3e2b__GEN____1_SM
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
  sexs_init sexs__GEN____1_SM
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
  gsteam0_init gsteam0__GEN____1_SM
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
    equation
	connect(gen_pwGeneratorM2S__GEN____1_SM.pin_ActivePowerSN, pssi3e2b__GEN____1_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____1_SM.pin_TerminalVoltage, sexs__GEN____1_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____1_SM.pin_EFD, sexs__GEN____1_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____1_SM.pin_OMEGA, gsteam0__GEN____1_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____1_SM.pin_CM, gsteam0__GEN____1_SM.pin_CM) annotation (Line());
	sexs__GEN____1_SM.pin_VS=0;
end _GEN____1_SM_Initialization;
model _GEN____4_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN____4_SM
   (
	PNALT = 1008.0,
	p0 = -0.09,
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
	ui0 = 0.26696485554092375,
	nDSat = 5.57,
	q0 = -0.27653372,
	mDSatIn = 0.084,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.3,
	transformerIncluded = true,
	XD = 2.57,
	uBMac = 24.0,
	ur0 = 0.9616307494722494,
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
  sexs_init sexs__GEN____4_SM
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
  cmconst_init cmconst__GEN____4_SM
 (
	PNALT = 1008.0,
	SN = 1120.0,
	SNREF = 100.0,
	PN = 1008.0
  ) annotation (Placement(transformation()));
  pssi3e2b_init pssi3e2b__GEN____4_SM
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
    equation
	connect(gen_pwGeneratorM2S__GEN____4_SM.pin_TerminalVoltage, sexs__GEN____4_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____4_SM.pin_EFD, sexs__GEN____4_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____4_SM.pin_ActivePowerSN, pssi3e2b__GEN____4_SM.pin_ActivePowerSN) annotation (Line());
	sexs__GEN____4_SM.pin_VS=0;
end _GEN____4_SM_Initialization;
model _GEN____6_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN____6_SM
   (
	PNALT = 1485.0,
	p0 = 0.0,
	omega_0 = 1.0,
	IENR = 4,
	TX = 0,
	Saturated = true,
	HIn = 5.625,
	TSD0 = 0.065,
	XPD = 0.509,
	SNREF = 100.0,
	TPD0 = 10.041,
	rStatIn = 0.00316,
	uNResTfo = 138.0,
	XPQ = 0.601,
	TSQ0 = 0.094,
	SN = 1650.0,
	TPQ0 = 1.22,
	ui0 = 0.22662369124287524,
	nDSat = 9.285,
	q0 = 0.1409923,
	mDSatIn = 0.05,
	uNomNw = 138.0,
	rTfoIn = 0.0,
	XSD = 0.354,
	transformerIncluded = true,
	XD = 2.81,
	uBMac = 20.0,
	ur0 = 0.9637124070141523,
	lStatIn = 0.256,
	nQSat = 9.285,
	XSQ = 0.377,
	mQSatIn = 0.05,
	XQ = 2.62,
	sNTfo = 1650.0,
	PN = 1485.0,
	IWLMDV = 3,
	uNMacTfo = 20.0,
	pPuWLMDV = 1485.0,
	xTfoIn = 0.1
    ) annotation (Placement(transformation()));
  pssi3e2b_init pssi3e2b__GEN____6_SM
 (
	PNALT = 1485.0,
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
	SN = 1650.0,
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
	PN = 1485.0
  ) annotation (Placement(transformation()));
  gsteam0_init gsteam0__GEN____6_SM
 (
	DT = 0.,
	RR = 0.05,
	PNALT = 1485.0,
	VMIN = 0.,
	VMAX = 1.,
	SN = 1650.0,
	T1 = 0.5,
	T2 = 3.,
	SNREF = 100.0,
	PN = 1485.0,
	T3 = 10.
  ) annotation (Placement(transformation()));
  sexs_init sexs__GEN____6_SM
 (
	PNALT = 1485.0,
	K = 200.,
	EMIN = 0.,
	TA = 3.,
	TB = 10.,
	SNREF = 100.0,
	TE = 0.05,
	EFDMIN = -999.,
	KC = 1.,
	EFDMAX = 999.,
	SN = 1650.0,
	PN = 1485.0,
	EMAX = 4.
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__GEN____6_SM.pin_ActivePowerSN, pssi3e2b__GEN____6_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____6_SM.pin_OMEGA, gsteam0__GEN____6_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____6_SM.pin_CM, gsteam0__GEN____6_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____6_SM.pin_TerminalVoltage, sexs__GEN____6_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____6_SM.pin_EFD, sexs__GEN____6_SM.pin_EFD) annotation (Line());
	sexs__GEN____6_SM.pin_VS=0;
end _GEN____6_SM_Initialization;
model _GEN____8_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GEN____8_SM
   (
	PNALT = 1539.0,
	p0 = -0.28,
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
	uNResTfo = 345.0,
	XPQ = 0.623,
	TSQ0 = 0.094,
	SN = 1710.0,
	TPQ0 = 1.22,
	ui0 = 0.3628401864290053,
	nDSat = 9.285,
	q0 = 0.7846437,
	mDSatIn = 0.05,
	uNomNw = 345.0,
	rTfoIn = 0.0,
	XSD = 0.367,
	transformerIncluded = true,
	XD = 2.91,
	uBMac = 20.0,
	ur0 = 0.9479303614046667,
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
  pssi3e2b_init pssi3e2b__GEN____8_SM
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
  sexs_init sexs__GEN____8_SM
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
  cmconst_init cmconst__GEN____8_SM
 (
	PNALT = 1539.0,
	SN = 1710.0,
	SNREF = 100.0,
	PN = 1539.0
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__GEN____8_SM.pin_ActivePowerSN, pssi3e2b__GEN____8_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____8_SM.pin_TerminalVoltage, sexs__GEN____8_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____8_SM.pin_EFD, sexs__GEN____8_SM.pin_EFD) annotation (Line());
	sexs__GEN____8_SM.pin_VS=0;
end _GEN____8_SM_Initialization;
