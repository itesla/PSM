within ;
model _GEN____1_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S_Init gen_pwGeneratorM2S__GEN____1_SM
   (
	PNALT = 1090.0,
	p0 = 2.3239326,
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
	ui0 = 0.0,
	nDSat = 6.995,
	q0 = -0.16549492,
	mDSatIn = 0.215,
	uNomNw = 69.0,
	rTfoIn = 0.0,
	XSD = 0.264,
	transformerIncluded = true,
	XD = 2.22,
	uBMac = 24.0,
	ur0 = 1.059999942779541,
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
  iPSL.Electrical.Controls.Eurostag.sexs_Init sexs__GEN____1_SM
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
  iPSL.Electrical.Controls.Eurostag.gsteam0_Init gsteam0__GEN____1_SM
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
  iPSL.Electrical.Controls.Eurostag.pssi3e2b_Init pssi3e2b__GEN____1_SM
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
	connect(gen_pwGeneratorM2S__GEN____1_SM.pin_TerminalVoltage, sexs__GEN____1_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____1_SM.pin_EFD, sexs__GEN____1_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____1_SM.pin_OMEGA, gsteam0__GEN____1_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____1_SM.pin_CM, gsteam0__GEN____1_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____1_SM.pin_ActivePowerSN, pssi3e2b__GEN____1_SM.pin_ActivePowerSN) annotation (Line());
	sexs__GEN____1_SM.pin_VS=0;
end _GEN____1_SM_Initialization;
model _GEN____2_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S_Init gen_pwGeneratorM2S__GEN____2_SM
   (
	PNALT = 1008.0,
	p0 = 0.4,
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
	ui0 = -0.09076125026210298,
	nDSat = 5.57,
	q0 = 0.4355726,
	mDSatIn = 0.084,
	uNomNw = 69.0,
	rTfoIn = 0.0,
	XSD = 0.3,
	transformerIncluded = true,
	XD = 2.57,
	uBMac = 24.0,
	ur0 = 1.0410511778511233,
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
  iPSL.Electrical.Controls.Eurostag.gsteam0_Init gsteam0__GEN____2_SM
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
  iPSL.Electrical.Controls.Eurostag.sexs_Init sexs__GEN____2_SM
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
  iPSL.Electrical.Controls.Eurostag.pssi3e2b_Init pssi3e2b__GEN____2_SM
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
	connect(gen_pwGeneratorM2S__GEN____2_SM.pin_OMEGA, gsteam0__GEN____2_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____2_SM.pin_CM, gsteam0__GEN____2_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____2_SM.pin_TerminalVoltage, sexs__GEN____2_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____2_SM.pin_EFD, sexs__GEN____2_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____2_SM.pin_ActivePowerSN, pssi3e2b__GEN____2_SM.pin_ActivePowerSN) annotation (Line());
	sexs__GEN____2_SM.pin_VS=0;
end _GEN____2_SM_Initialization;
model _GEN____3_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S_Init gen_pwGeneratorM2S__GEN____3_SM
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
	ui0 = -0.22247608639833844,
	nDSat = 9.285,
	q0 = 0.2507532,
	mDSatIn = 0.05,
	uNomNw = 69.0,
	rTfoIn = 0.0,
	XSD = 0.354,
	transformerIncluded = true,
	XD = 2.81,
	uBMac = 20.0,
	ur0 = 0.9851925556543035,
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
  iPSL.Electrical.Controls.Eurostag.sexs_Init sexs__GEN____3_SM
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
  iPSL.Electrical.Controls.Eurostag.pssi3e2b_Init pssi3e2b__GEN____3_SM
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
  iPSL.Electrical.Controls.Eurostag.gsteam0_Init gsteam0__GEN____3_SM
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
	connect(gen_pwGeneratorM2S__GEN____3_SM.pin_TerminalVoltage, sexs__GEN____3_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____3_SM.pin_EFD, sexs__GEN____3_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____3_SM.pin_ActivePowerSN, pssi3e2b__GEN____3_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____3_SM.pin_OMEGA, gsteam0__GEN____3_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____3_SM.pin_CM, gsteam0__GEN____3_SM.pin_CM) annotation (Line());
	sexs__GEN____3_SM.pin_VS=0;
end _GEN____3_SM_Initialization;
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
    equation
	connect(gen_pwGeneratorM2S__GEN____6_SM.pin_ActivePowerSN, pssi3e2b__GEN____6_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____6_SM.pin_TerminalVoltage, sexs__GEN____6_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____6_SM.pin_EFD, sexs__GEN____6_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____6_SM.pin_OMEGA, gsteam0__GEN____6_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____6_SM.pin_CM, gsteam0__GEN____6_SM.pin_CM) annotation (Line());
	sexs__GEN____6_SM.pin_VS=0;
end _GEN____6_SM_Initialization;
model _GEN____8_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S_Init gen_pwGeneratorM2S__GEN____8_SM
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
	uNResTfo = 13.8,
	XPQ = 0,
	TSQ0 = 0.1,
	SN = 250.0,
	TPQ0 = 0,
	ui0 = -0.2518577804600687,
	nDSat = 0,
	q0 = 0.17623426,
	mDSatIn = 0,
	uNomNw = 13.8,
	rTfoIn = 0.0,
	XSD = 0.275,
	transformerIncluded = true,
	XD = 1.53,
	uBMac = 18.0,
	ur0 = 1.0605034046672526,
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
  iPSL.Electrical.Controls.Eurostag.pssi3e2b_Init pssi3e2b__GEN____8_SM
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
  iPSL.Electrical.Controls.Eurostag.gsteam0_Init gsteam0__GEN____8_SM
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
  iPSL.Electrical.Controls.Eurostag.sexs_Init sexs__GEN____8_SM
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
	connect(gen_pwGeneratorM2S__GEN____8_SM.pin_ActivePowerSN, pssi3e2b__GEN____8_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____8_SM.pin_OMEGA, gsteam0__GEN____8_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____8_SM.pin_CM, gsteam0__GEN____8_SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____8_SM.pin_TerminalVoltage, sexs__GEN____8_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____8_SM.pin_EFD, sexs__GEN____8_SM.pin_EFD) annotation (Line());
	sexs__GEN____8_SM.pin_VS=0;
end _GEN____8_SM_Initialization;
