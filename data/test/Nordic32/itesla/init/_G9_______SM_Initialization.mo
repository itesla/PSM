within ;
model _G9_______SM_Initialization
    iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S_Init gen_pwGeneratorM2S__G9_______SM
   (
	PNALT = 0.0,
	p0 = 6.685,
	omega_0 = 1.0,
	IENR = 3,
	TX = 0,
	Saturated = false,
	HIn = 3.0,
	TSD0 = 0.05,
	XPD = 0.25,
	SNREF = 100.0,
	TPD0 = 5.0,
	rStatIn = 0.0,
	XPQ = 0,
	TSQ0 = 0.1,
	SN = 1000.0,
	TPQ0 = 0,
	ui0 = 0.0,
	nDSat = 0,
	q0 = 1.9193,
	mDSatIn = 0,
	XSD = 0.2,
	transformerIncluded = false,
	XD = 1.1,
	ur0 = 1.0,
	lStatIn = 0.15,
	nQSat = 0,
	XSQ = 0.2,
	mQSatIn = 0,
	XQ = 0.7,
	PN = 1000.0,
	IWLMDV = 2,
	pPuWLMDV = 0.0
    ) annotation (Placement(transformation()));
  oelpsat_init oelpsat__G9_______SM
 (
	PNALT = 0.0,
	IFDLIM = 3.,
	K0 = 50.,
	VFMAX = 4.,
	SNREF = 100.0,
	TE = 0.1,
	VOELMAX = 1.100000,
	V0 = 0.,
	SN = 1000.0,
	T0 = 10.,
	T1 = 4.,
	T2 = 20.,
	TR = 0.001,
	VFMIN = 0.,
	PN = 1000.0
  ) annotation (Placement(transformation()));
  htgpsat3_init htgpsat3__G9_______SM
 (
	A21 = 1.5,
	PNALT = 0.0,
	A11 = 0.5,
	A23 = 1.,
	A13 = 1.,
	TW = 1.,
	DELTA = .3000000,
	PMIN = 0.,
	UC = -0.1,
	SNREF = 100.0,
	PMAX = 1.,
	TG = 0.2,
	SIGMA = 0.04,
	UO = 0.1,
	SN = 1000.0,
	TP = 0.04,
	TR = 5.,
	PN = 1000.0
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__G9_______SM.pin_TerminalVoltage, oelpsat__G9_______SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__G9_______SM.pin_CM, htgpsat3__G9_______SM.pin_CM) annotation (Line());
	connect(gen_pwGeneratorM2S__G9_______SM.pin_OMEGA, htgpsat3__G9_______SM.pin_OMEGA) annotation (Line());
end _G9_______SM_Initialization;
