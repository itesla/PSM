within ;
model _GEN______SM_Initialization
    iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S_Init gen_pwGeneratorM2S__GEN______SM
   (
        PNALT = 475.0,
        p0 = 3.8,
        omega_0 = 1.0,
        IENR = 4,
        TX = 0,
        Saturated = false,
        HIn = 4.0,
        TSD0 = 0.042,
        XPD = 0.35,
        SNREF = 100.0,
        TPD0 = 5.143,
        rStatIn = 0.0,
        XPQ = 0.5,
        TSQ0 = 0.083,
        SN = 500.0,
        TPQ0 = 2.16,
        ui0 = 0.0,
        nDSat = 0,
        q0 = 0.0,
        mDSatIn = 0,
        XSD = 0.25,
        transformerIncluded = false,
        XD = 2.0,
        ur0 = 1.0,
        lStatIn = 0.15,
        nQSat = 0,
        XSQ = 0.3,
        mQSatIn = 0,
        XQ = 1.8,
        PN = 475.0,
        IWLMDV = 1,
        pPuWLMDV = 475.0
    ) annotation (Placement(transformation()));
   iPSL.Electrical.Controls.Eurostag.pss2ab_Init pss2ab__GEN______SM
 (
        T4 = 0.015,
        PNALT = 475.0,
        VSTMIN = -0.1,
        T7 = 2.,
        KS1 = 10.,
        KS3 = 1.,
        KS2 = 0.1564,
        M = 0.,
        N = 0.,
        TW2 = 2.,
        SNREF = 100.0,
        TW1 = 2.,
        TW3 = 2.,
        VSTMAX = 0.1,
        SN = 500.0,
        T1 = 0.25,
        T2 = 0.03,
        PN = 475.0,
        T3 = .1500000
  ) annotation (Placement(transformation()));
   iPSL.Electrical.Controls.Eurostag.tgov1_Init tgov1__GEN______SM
 (
        DT = 0.,
        RR = 0.05,
        PNALT = 475.0,
        VMIN = 0.,
        VMAX = 1.010000,
        SN = 500.0,
        T1 = 0.5,
        T2 = 3.,
        SNREF = 100.0,
        PN = 475.0,
        T3 = 10.
  ) annotation (Placement(transformation()));
   iPSL.Electrical.Controls.Eurostag.sexs_Init sexs__GEN______SM
 (
        PNALT = 475.0,
        TE = 0.05,
    KC = 1,
        K = 200.,
        SN = 500.0,
        EMIN = 0.,
        TA = 3.,
        TB = 10.,
        SNREF = 100.0,
        PN = 475.0,
        EMAX = 4.
  ) annotation (Placement(transformation()));
  Modelica.Blocks.Sources.Constant zero__GEN______SM (k = 0) annotation (Placement(transformation()));
    equation
        connect(gen_pwGeneratorM2S__GEN______SM.pin_ActivePowerSN, pss2ab__GEN______SM.pin_ActivePowerSN) annotation (Line());
        connect(gen_pwGeneratorM2S__GEN______SM.pin_OMEGA, pss2ab__GEN______SM.pin_OMEGA) annotation (Line());
        connect(gen_pwGeneratorM2S__GEN______SM.pin_OMEGA, tgov1__GEN______SM.pin_OMEGA) annotation (Line());
        connect(gen_pwGeneratorM2S__GEN______SM.pin_CM, tgov1__GEN______SM.pin_CM) annotation (Line());
        connect(gen_pwGeneratorM2S__GEN______SM.pin_EFD, sexs__GEN______SM.pin_EFD) annotation (Line());
        connect(gen_pwGeneratorM2S__GEN______SM.pin_TerminalVoltage, sexs__GEN______SM.pin_TerminalVoltage) annotation (Line());
    connect(sexs__GEN______SM.pin_VS, zero__GEN______SM.y) annotation (Line());
end _GEN______SM_Initialization;
