within ;
model _GEN___11_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S_Init gen_pwGeneratorM2S__GEN___11_SM
   (
	PNALT = 39.2,
	p0 = 0.0,
	omega_0 = 1.0,
	IENR = 3,
	TX = 0,
	Saturated = false,
	HIn = 5.22,
	TSD0 = 0.033,
	XPD = 0.253,
	SNREF = 100.0,
	TPD0 = 5.51,
	rStatIn = 0.0018,
	uNResTfo = 33.0,
	XPQ = 0,
	TSQ0 = 0.034,
	SN = 49.0,
	TPQ0 = 0,
	ui0 = -0.2637708664092251,
	nDSat = 0,
	q0 = 0.16179806,
	mDSatIn = 0,
	uNomNw = 33.0,
	rTfoIn = 0.0,
	XSD = 0.171,
	transformerIncluded = true,
	XD = 2.07,
	uBMac = 11.0,
	ur0 = 1.0493564538236038,
	lStatIn = 0.135,
	nQSat = 0,
	XSQ = 0.198,
	mQSatIn = 0,
	XQ = 2.0,
	sNTfo = 49.0,
	PN = 39.0,
	IWLMDV = 3,
	uNMacTfo = 11.0,
	pPuWLMDV = 39.2,
	xTfoIn = 0.1
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.sexs_Init sexs__GEN___11_SM
 (
	PNALT = 39.2,
	K = 200.,
	EMIN = 0.,
	TA = 3.,
	TB = 10.,
	SNREF = 100.0,
	TE = 0.05,
	EFDMIN = -999.,
	KC = 1.,
	EFDMAX = 999.,
	SN = 49.0,
	PN = 39.0,
	EMAX = 4.
  ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.pssi3e2b_Init pssi3e2b__GEN___11_SM
 (
	PNALT = 39.2,
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
	SN = 49.0,
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
	PN = 39.0
  ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.gsteam0_Init gsteam0__GEN___11_SM
 (
	DT = 0.,
	RR = 0.05,
	PNALT = 39.2,
	VMIN = 0.,
	VMAX = 1.,
	SN = 49.0,
	T1 = 0.5,
	T2 = 3.,
	SNREF = 100.0,
	PN = 39.0,
	T3 = 10.
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__GEN___11_SM.pin_TerminalVoltage, sexs__GEN___11_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___11_SM.pin_EFD, sexs__GEN___11_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___11_SM.pin_ActivePowerSN, pssi3e2b__GEN___11_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___11_SM.pin_OMEGA, gsteam0__GEN___11_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN___11_SM.pin_CM, gsteam0__GEN___11_SM.pin_CM) annotation (Line());
	sexs__GEN___11_SM.pin_VS=0;
end _GEN___11_SM_Initialization;
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
model _GEN____1_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S_Init gen_pwGeneratorM2S__GEN____1_SM
   (
	PNALT = 1090.0,
	p0 = 2.609518,
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
	uNResTfo = 132.0,
	XPQ = 0.393,
	TSQ0 = 0.084,
	SN = 1211.0,
	TPQ0 = 1.572,
	ui0 = 0.0,
	nDSat = 6.995,
	q0 = -0.16526604,
	mDSatIn = 0.215,
	uNomNw = 132.0,
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
    equation
	connect(gen_pwGeneratorM2S__GEN____1_SM.pin_ActivePowerSN, pssi3e2b__GEN____1_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____1_SM.pin_TerminalVoltage, sexs__GEN____1_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____1_SM.pin_EFD, sexs__GEN____1_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____1_SM.pin_OMEGA, gsteam0__GEN____1_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____1_SM.pin_CM, gsteam0__GEN____1_SM.pin_CM) annotation (Line());
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
	uNResTfo = 132.0,
	XPQ = 0.454,
	TSQ0 = 0.06,
	SN = 1120.0,
	TPQ0 = 1.009,
	ui0 = -0.09724865231397534,
	nDSat = 5.57,
	q0 = 0.5,
	mDSatIn = 0.084,
	uNomNw = 132.0,
	rTfoIn = 0.0,
	XSD = 0.3,
	transformerIncluded = true,
	XD = 2.57,
	uBMac = 24.0,
	ur0 = 1.038456385128693,
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
    equation
	connect(gen_pwGeneratorM2S__GEN____2_SM.pin_ActivePowerSN, pssi3e2b__GEN____2_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____2_SM.pin_TerminalVoltage, sexs__GEN____2_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____2_SM.pin_EFD, sexs__GEN____2_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____2_SM.pin_OMEGA, gsteam0__GEN____2_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____2_SM.pin_CM, gsteam0__GEN____2_SM.pin_CM) annotation (Line());
	sexs__GEN____2_SM.pin_VS=0;
end _GEN____2_SM_Initialization;
model _GEN____5_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S_Init gen_pwGeneratorM2S__GEN____5_SM
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
	uNResTfo = 132.0,
	XPQ = 0.601,
	TSQ0 = 0.094,
	SN = 1650.0,
	TPQ0 = 1.22,
	ui0 = -0.2471983390577364,
	nDSat = 9.285,
	q0 = 0.3693602,
	mDSatIn = 0.05,
	uNomNw = 132.0,
	rTfoIn = 0.0,
	XSD = 0.354,
	transformerIncluded = true,
	XD = 2.81,
	uBMac = 20.0,
	ur0 = 0.9792819832436682,
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
  iPSL.Electrical.Controls.Eurostag.pssi3e2b_Init pssi3e2b__GEN____5_SM
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
  iPSL.Electrical.Controls.Eurostag.sexs_Init sexs__GEN____5_SM
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
  iPSL.Electrical.Controls.Eurostag.gsteam0_Init gsteam0__GEN____5_SM
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
	connect(gen_pwGeneratorM2S__GEN____5_SM.pin_ActivePowerSN, pssi3e2b__GEN____5_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____5_SM.pin_TerminalVoltage, sexs__GEN____5_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____5_SM.pin_EFD, sexs__GEN____5_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____5_SM.pin_OMEGA, gsteam0__GEN____5_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____5_SM.pin_CM, gsteam0__GEN____5_SM.pin_CM) annotation (Line());
	sexs__GEN____5_SM.pin_VS=0;
end _GEN____5_SM_Initialization;
model _GEN____8_SM_Initialization
    iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S_Init gen_pwGeneratorM2S__GEN____8_SM
   (
	PNALT = 1539.0,
	p0 = 0.0,
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
	uNResTfo = 132.0,
	XPQ = 0.623,
	TSQ0 = 0.094,
	SN = 1710.0,
	TPQ0 = 1.22,
	ui0 = -0.2067919827780116,
	nDSat = 9.285,
	q0 = 0.37218666,
	mDSatIn = 0.05,
	uNomNw = 132.0,
	rTfoIn = 0.0,
	XSD = 0.367,
	transformerIncluded = true,
	XD = 2.91,
	uBMac = 20.0,
	ur0 = 0.9886037109971286,
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
  iPSL.Electrical.Controls.Eurostag.pssi3e2b_Init pssi3e2b__GEN____8_SM
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
  iPSL.Electrical.Controls.Eurostag.sexs_Init sexs__GEN____8_SM
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
  iPSL.Electrical.Controls.Eurostag.gsteam0_Init gsteam0__GEN____8_SM
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
	connect(gen_pwGeneratorM2S__GEN____8_SM.pin_ActivePowerSN, pssi3e2b__GEN____8_SM.pin_ActivePowerSN) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____8_SM.pin_TerminalVoltage, sexs__GEN____8_SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____8_SM.pin_EFD, sexs__GEN____8_SM.pin_EFD) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____8_SM.pin_OMEGA, gsteam0__GEN____8_SM.pin_OMEGA) annotation (Line());
	connect(gen_pwGeneratorM2S__GEN____8_SM.pin_CM, gsteam0__GEN____8_SM.pin_CM) annotation (Line());
	sexs__GEN____8_SM.pin_VS=0;
end _GEN____8_SM_Initialization;
