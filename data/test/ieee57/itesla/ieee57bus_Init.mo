within ;
model _BEAV___6_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__BEAV___6_SM
   (
	PNALT = 1008.0,
	p0 = 0.0073869904,
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
	uNResTfo = 69.0,
	XPQ = 0.454,
	TSQ0 = 0.06,
	SN = 1120.0,
	TPQ0 = 1.009,
	ui0 = -0.14739017941960852,
	nDSat = 5.57,
	q0 = 0.00626911,
	mDSatIn = 0.084,
	uNomNw = 69.0,
	rTfoIn = 0.0,
	XSD = 0.3,
	transformerIncluded = true,
	XD = 2.57,
	uBMac = 24.0,
	ur0 = 0.9688530189841436,
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
  gsteam0_init gsteam0__BEAV___6_SM
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
  pssi3e2b_init pssi3e2b__BEAV___6_SM
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
  sexs_init sexs__BEAV___6_SM
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
	connect(gen_pwGeneratorM2S__BEAV___6_SM.pin_OMEGA, gsteam0__BEAV___6_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__BEAV___6_SM.pin_CM, gsteam0__BEAV___6_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__BEAV___6_SM.pin_ActivePowerSN, pssi3e2b__BEAV___6_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__BEAV___6_SM.pin_TerminalVoltage, sexs__BEAV___6_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__BEAV___6_SM.pin_EFD, sexs__BEAV___6_SM.pin_EFD) annotation (Line());
	sexs__BEAV___6_SM.pin_VS=0;
end _BEAV___6_SM_Initialization;
model _CLIN___8_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__CLIN___8_SM
   (
	PNALT = 1090.0,
	p0 = 4.5,
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
	ui0 = -0.07833591169516874,
	nDSat = 6.995,
	q0 = 0.6209517,
	mDSatIn = 0.215,
	uNomNw = 69.0,
	rTfoIn = 0.0,
	XSD = 0.264,
	transformerIncluded = true,
	XD = 2.22,
	uBMac = 24.0,
	ur0 = 1.0019423513129186,
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
  sexs_init sexs__CLIN___8_SM
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
  gsteam0_init gsteam0__CLIN___8_SM
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
  pssi3e2b_init pssi3e2b__CLIN___8_SM
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
	connect(gen_pwGeneratorM2S__CLIN___8_SM.pin_TerminalVoltage, sexs__CLIN___8_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__CLIN___8_SM.pin_EFD, sexs__CLIN___8_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__CLIN___8_SM.pin_OMEGA, gsteam0__CLIN___8_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__CLIN___8_SM.pin_CM, gsteam0__CLIN___8_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__CLIN___8_SM.pin_ActivePowerSN, pssi3e2b__CLIN___8_SM.pin_ActivePowerSN) annotation (Line());
	sexs__CLIN___8_SM.pin_VS=0;
end _CLIN___8_SM_Initialization;
model _GLEN__12_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__GLEN__12_SM
   (
	PNALT = 1539.0,
	p0 = 3.1,
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
	uNResTfo = 69.0,
	XPQ = 0.623,
	TSQ0 = 0.094,
	SN = 1710.0,
	TPQ0 = 1.22,
	ui0 = -0.1846853582364029,
	nDSat = 9.285,
	q0 = 1.286008,
	mDSatIn = 0.05,
	uNomNw = 69.0,
	rTfoIn = 0.0,
	XSD = 0.367,
	transformerIncluded = true,
	XD = 2.91,
	uBMac = 20.0,
	ur0 = 0.9980563768688507,
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
  gsteam0_init gsteam0__GLEN__12_SM
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
  sexs_init sexs__GLEN__12_SM
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
  pssi3e2b_init pssi3e2b__GLEN__12_SM
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
	connect(gen_pwGeneratorM2S__GLEN__12_SM.pin_OMEGA, gsteam0__GLEN__12_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GLEN__12_SM.pin_CM, gsteam0__GLEN__12_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__GLEN__12_SM.pin_TerminalVoltage, sexs__GLEN__12_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GLEN__12_SM.pin_EFD, sexs__GLEN__12_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GLEN__12_SM.pin_ActivePowerSN, pssi3e2b__GLEN__12_SM.pin_ActivePowerSN) annotation (Line());
	sexs__GLEN__12_SM.pin_VS=0;
end _GLEN__12_SM_Initialization;
model _KANA___1_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__KANA___1_SM
   (
	PNALT = 1539.0,
	p0 = 4.7786403,
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
	uNResTfo = 69.0,
	XPQ = 0.623,
	TSQ0 = 0.094,
	SN = 1710.0,
	TPQ0 = 1.22,
	ui0 = -5.276960412518907E-4,
	nDSat = 9.285,
	q0 = 1.29,
	mDSatIn = 0.05,
	uNomNw = 69.0,
	rTfoIn = 0.0,
	XSD = 0.367,
	transformerIncluded = true,
	XD = 2.91,
	uBMac = 20.0,
	ur0 = 1.0399999471858226,
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
  gsteam0_init gsteam0__KANA___1_SM
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
  sexs_init sexs__KANA___1_SM
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
  pssi3e2b_init pssi3e2b__KANA___1_SM
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
	connect(gen_pwGeneratorM2S__KANA___1_SM.pin_OMEGA, gsteam0__KANA___1_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__KANA___1_SM.pin_CM, gsteam0__KANA___1_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__KANA___1_SM.pin_TerminalVoltage, sexs__KANA___1_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__KANA___1_SM.pin_EFD, sexs__KANA___1_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__KANA___1_SM.pin_ActivePowerSN, pssi3e2b__KANA___1_SM.pin_ActivePowerSN) annotation (Line());
	sexs__KANA___1_SM.pin_VS=0;
end _KANA___1_SM_Initialization;
model _LOGA___3_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__LOGA___3_SM
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
	ui0 = -0.10290796233935176,
	nDSat = 6.995,
	q0 = -0.00954819,
	mDSatIn = 0.215,
	uNomNw = 69.0,
	rTfoIn = 0.0,
	XSD = 0.264,
	transformerIncluded = true,
	XD = 2.22,
	uBMac = 24.0,
	ur0 = 0.9796095457104788,
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
  pssi3e2b_init pssi3e2b__LOGA___3_SM
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
  gsteam0_init gsteam0__LOGA___3_SM
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
  sexs_init sexs__LOGA___3_SM
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
	connect(gen_pwGeneratorM2S__LOGA___3_SM.pin_ActivePowerSN, pssi3e2b__LOGA___3_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__LOGA___3_SM.pin_OMEGA, gsteam0__LOGA___3_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__LOGA___3_SM.pin_CM, gsteam0__LOGA___3_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__LOGA___3_SM.pin_TerminalVoltage, sexs__LOGA___3_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__LOGA___3_SM.pin_EFD, sexs__LOGA___3_SM.pin_EFD) annotation (Line());
	sexs__LOGA___3_SM.pin_VS=0;
end _LOGA___3_SM_Initialization;
model _SALT___9_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__SALT___9_SM
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
	uNResTfo = 69.0,
	XPQ = 0.601,
	TSQ0 = 0.094,
	SN = 1650.0,
	TPQ0 = 1.22,
	ui0 = -0.16319679921589386,
	nDSat = 9.285,
	q0 = 0.02300736,
	mDSatIn = 0.05,
	uNomNw = 69.0,
	rTfoIn = 0.0,
	XSD = 0.354,
	transformerIncluded = true,
	XD = 2.81,
	uBMac = 20.0,
	ur0 = 0.9663161191399638,
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
  gsteam0_init gsteam0__SALT___9_SM
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
  pssi3e2b_init pssi3e2b__SALT___9_SM
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
  sexs_init sexs__SALT___9_SM
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
	connect(gen_pwGeneratorM2S__SALT___9_SM.pin_OMEGA, gsteam0__SALT___9_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__SALT___9_SM.pin_CM, gsteam0__SALT___9_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__SALT___9_SM.pin_ActivePowerSN, pssi3e2b__SALT___9_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__SALT___9_SM.pin_TerminalVoltage, sexs__SALT___9_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__SALT___9_SM.pin_EFD, sexs__SALT___9_SM.pin_EFD) annotation (Line());
	sexs__SALT___9_SM.pin_VS=0;
end _SALT___9_SM_Initialization;
model _TURN___2_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.DYNModelM2S_INIT gen_pwGeneratorM2S__TURN___2_SM
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
	uNResTfo = 69.0,
	XPQ = 0.601,
	TSQ0 = 0.094,
	SN = 1650.0,
	TPQ0 = 1.22,
	ui0 = -0.02137062097636887,
	nDSat = 9.285,
	q0 = -0.0077206497,
	mDSatIn = 0.05,
	uNomNw = 69.0,
	rTfoIn = 0.0,
	XSD = 0.354,
	transformerIncluded = true,
	XD = 2.81,
	uBMac = 20.0,
	ur0 = 1.0097738743376476,
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
  gsteam0_init gsteam0__TURN___2_SM
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
  pssi3e2b_init pssi3e2b__TURN___2_SM
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
  sexs_init sexs__TURN___2_SM
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
	connect(gen_pwGeneratorM2S__TURN___2_SM.pin_OMEGA, gsteam0__TURN___2_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__TURN___2_SM.pin_CM, gsteam0__TURN___2_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__TURN___2_SM.pin_ActivePowerSN, pssi3e2b__TURN___2_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__TURN___2_SM.pin_TerminalVoltage, sexs__TURN___2_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__TURN___2_SM.pin_EFD, sexs__TURN___2_SM.pin_EFD) annotation (Line());
	sexs__TURN___2_SM.pin_VS=0;
end _TURN___2_SM_Initialization;
