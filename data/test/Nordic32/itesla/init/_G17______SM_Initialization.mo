within ;
model _G17______SM_Initialization
    iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S_Init gen_pwGeneratorM2S__G17______SM
   (
	PNALT = 0.0,
	p0 = 5.3,
	omega_0 = 1.0,
	IENR = 4,
	TX = 0,
	Saturated = false,
	HIn = 6.0,
	TSD0 = 0.05,
	XPD = 0.3,
	SNREF = 100.0,
	TPD0 = 7.0,
	rStatIn = 0.0,
	XPQ = 0.4,
	TSQ0 = 0.05,
	SN = 600.0,
	TPQ0 = 1.5,
	ui0 = 0.0,
	nDSat = 0,
	q0 = 0.4316,
	mDSatIn = 0,
	XSD = 0.2,
	transformerIncluded = false,
	XD = 2.2,
	ur0 = 1.0,
	lStatIn = 0.15,
	nQSat = 0,
	XSQ = 0.2,
	mQSatIn = 0,
	XQ = 2.0,
	PN = 600.0,
	IWLMDV = 2,
	pPuWLMDV = 0.0
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.oelpsat_Init oelpsat__G17______SM
 (
	PNALT = 0.0,
	IFDLIM = 3.,
	K0 = 120.,
	VFMAX = 5.,
	SNREF = 100.0,
	TE = 0.1,
	VOELMAX = 1.100000,
	V0 = 0.,
	SN = 600.0,
	T0 = 10.,
	T1 = 5.,
	T2 = 50.,
	TR = 0.001,
	VFMIN = 0.,
	PN = 600.0
  ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.govpsat1_Init govpsat1__G17______SM
 (
	T4 = 0.01,
	PNALT = 0.0,
	PMAX = .9500000,
	T5 = 6.,
	RD = 0.04,
	PMIN = 0.,
	SN = 600.0,
	SNREF = 100.0,
	PN = 600.0,
	T3 = 5.,
	TC = 0.2,
	TS = 5.
  ) annotation (Placement(transformation()));
    equation
	connect(gen_pwGeneratorM2S__G17______SM.pin_TerminalVoltage, oelpsat__G17______SM.pin_TerminalVoltage) annotation (Line());
	connect(gen_pwGeneratorM2S__G17______SM.pin_OMEGA, govpsat1__G17______SM.pin_OMEGA) annotation (Line());
end _G17______SM_Initialization;
