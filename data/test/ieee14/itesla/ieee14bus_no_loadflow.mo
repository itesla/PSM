within ;
model ieee14bus
  parameter Real SNREF = 100.0;
  Modelica.Blocks.Interfaces.RealOutput omegaRef;
  iPSL.Electrical.Buses.Bus bus__BUS___10_TN (
    V_0 = 1.051,
    angle_0 = -15.1
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___11_TN (
    V_0 = 1.057,
    angle_0 = -14.79
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___12_TN (
    V_0 = 1.055,
    angle_0 = -15.07
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___13_TN (
    V_0 = 1.05,
    angle_0 = -15.16
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___14_TN (
    V_0 = 1.036,
    angle_0 = -16.04
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS____1_TN (
    V_0 = 1.06,
    angle_0 = 0.0
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS____2_TN (
    V_0 = 1.0450001,
    angle_0 = -4.98
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS____3_TN (
    V_0 = 1.01,
    angle_0 = -12.72
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS____4_TN (
    V_0 = 1.0189999,
    angle_0 = -10.33
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS____5_TN (
    V_0 = 1.02,
    angle_0 = -8.78
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS____6_TN (
    V_0 = 1.0699999,
    angle_0 = -14.22
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS____7_TN (
    V_0 = 1.0619999,
    angle_0 = -13.37
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS____8_TN (
    V_0 = 1.09,
    angle_0 = -13.36
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS____9_TN (
    V_0 = 1.056,
    angle_0 = -14.94
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__10_EC (
    V_0 = 1.051,
    P_0 = 9.0,
    Q_0 = 5.8,
    alpha = 1.5,
    beta = 2.5,
    angle_0 = -15.1
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__11_EC (
    V_0 = 1.057,
    P_0 = 3.5,
    Q_0 = 1.8,
    alpha = 1.5,
    beta = 2.5,
    angle_0 = -14.79
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__12_EC (
    V_0 = 1.055,
    P_0 = 6.1,
    Q_0 = 1.6,
    alpha = 1.5,
    beta = 2.5,
    angle_0 = -15.07
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__13_EC (
    V_0 = 1.05,
    P_0 = 13.5,
    Q_0 = 5.8,
    alpha = 1.5,
    beta = 2.5,
    angle_0 = -15.16
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__14_EC (
    V_0 = 1.036,
    P_0 = 14.9,
    Q_0 = 5.0,
    alpha = 1.5,
    beta = 2.5,
    angle_0 = -16.04
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD___2_EC (
    V_0 = 1.0450001,
    P_0 = 21.7,
    Q_0 = 12.7,
    alpha = 1.5,
    beta = 2.5,
    angle_0 = -4.98
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD___3_EC (
    V_0 = 1.01,
    P_0 = 94.2,
    Q_0 = 19.0,
    alpha = 1.5,
    beta = 2.5,
    angle_0 = -12.72
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD___4_EC (
    V_0 = 1.0189999,
    P_0 = 47.8,
    Q_0 = -3.9,
    alpha = 1.5,
    beta = 2.5,
    angle_0 = -10.33
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD___5_EC (
    V_0 = 1.02,
    P_0 = 7.6,
    Q_0 = 1.6,
    alpha = 1.5,
    beta = 2.5,
    angle_0 = -8.78
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD___6_EC (
    V_0 = 1.0699999,
    P_0 = 11.2,
    Q_0 = 7.5,
    alpha = 1.5,
    beta = 2.5,
    angle_0 = -14.22
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD___9_EC (
    V_0 = 1.056,
    P_0 = 29.5,
    Q_0 = 16.6,
    alpha = 1.5,
    beta = 2.5,
    angle_0 = -14.94
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__BUS____4_BUS____7_1_PT (
    r = 1.0204082,
    B0 = 0.0,
    G0 = 0.0,
    theta = 0.0,
    R = 0.0,
    X = 0.20911995
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__BUS____4_BUS____9_1_PT (
    r = 1.0416667,
    B0 = 0.0,
    G0 = 0.0,
    theta = 0.0,
    R = 0.0,
    X = 0.55617946
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__BUS____5_BUS____6_1_PT (
    r = 1.0638298,
    B0 = 0.0,
    G0 = 0.0,
    theta = 0.0,
    R = 0.0,
    X = 0.2520206
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___10_BUS___11_1_AC (
    R = 0.082049996,
    X = 0.19206995,
    G = 0.0,
    B = 0.0
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___12_BUS___13_1_AC (
    R = 0.22091998,
    X = 0.19987975,
    G = 0.0,
    B = 0.0
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___13_BUS___14_1_AC (
    R = 0.17092995,
    X = 0.34801987,
    G = 0.0,
    B = 0.0
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS____1_BUS____2_1_AC (
    R = 0.019380003,
    X = 0.059169922,
    G = 0.0,
    B = 0.026399983
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS____1_BUS____5_1_AC (
    R = 0.054030035,
    X = 0.22303928,
    G = 0.0,
    B = 0.024600087
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS____2_BUS____3_1_AC (
    R = 0.046989918,
    X = 0.19796997,
    G = 0.0,
    B = 0.021900006
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS____2_BUS____4_1_AC (
    R = 0.05811006,
    X = 0.17632009,
    G = 0.0,
    B = 0.017000008
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS____2_BUS____5_1_AC (
    R = 0.05695001,
    X = 0.17388007,
    G = 0.0,
    B = 0.017299999
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS____3_BUS____4_1_AC (
    R = 0.06701008,
    X = 0.17103004,
    G = 0.0,
    B = 0.006399998
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS____4_BUS____5_1_AC (
    R = 0.013349989,
    X = 0.04211006,
    G = 0.0,
    B = 0.0
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS____6_BUS___11_1_AC (
    R = 0.094980046,
    X = 0.19889992,
    G = 0.0,
    B = 0.0
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS____6_BUS___12_1_AC (
    R = 0.122910105,
    X = 0.25581023,
    G = 0.0,
    B = 0.0
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS____6_BUS___13_1_AC (
    R = 0.066149965,
    X = 0.1302699,
    G = 0.0,
    B = 0.0
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS____7_BUS____8_1_AC (
    R = 0.0,
    X = 0.10353704,
    G = 0.0,
    B = 0.0
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS____7_BUS____9_1_AC (
    R = 0.0,
    X = 0.110009976,
    G = 0.0,
    B = 0.0
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS____9_BUS___10_1_AC (
    R = 0.03181002,
    X = 0.08450011,
    G = 0.0,
    B = 0.0
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS____9_BUS___14_1_AC (
    R = 0.12710984,
    X = 0.27038017,
    G = 0.0,
    B = 0.0
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap__BANK___9_SC (
    B = 0.1900001,
    nsteps = 1
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN____1_SM (
    SNREF = SNREF,
    ur0 = 1.059999942779541,
    ui0 = 0.0,
    transformerIncluded = true,
    V2 = 69.0,
    Saturated = true,
    TX = 0,
    init_theta = 0.3099274420089914,
    init_omega = 1.0,
    init_efd = 0.447517996150594,
    WLMDVPu = 0.6296504227510634,
    init_lambdaad = -1.022795582023207,
    init_cm = 0.2132854180200577,
    init_lambdaq1 = 0.2701049319060519,
    init_lambdaq2 = 0.2701049319060519,
    init_iq = 2.137395441636327,
    init_id = 0.5134761542402783,
    init_lambdaaq = 0.2701049319060519,
    init_lambdad = -1.022795582023207,
    init_lambdaf = -1.182335698571067,
    PNALT = 1090.0,
    IENR = 4,
    DIn = 0.0,
    HIn = 5.4,
    TSD0 = 0.08,
    XPD = 0.384,
    RTfoPu = 0.0,
    TPD0 = 8.094,
    XTfoPu = 0.1,
    rStatIn = 0.002796,
    U1N = 24.0,
    md = 0.215,
    XPQ = 0.393,
    TSQ0 = 0.084,
    SN = 1211.0,
    V1 = 24.0,
    TPQ0 = 1.572,
    mq = 0.215,
    XSD = 0.264,
    snd = 6.995,
    XD = 2.22,
    U2N = 69.0,
    SNtfo = 1211.0,
    lStatIn = 0.202,
    XSQ = 0.262,
    snq = 6.995,
    XQ = 2.22,
    PN = 1090.0,
    IWLMDV = 3
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN____2_SM (
    SNREF = SNREF,
    ur0 = 1.0410552638935078,
    ui0 = -0.09071437027268746,
    transformerIncluded = true,
    V2 = 69.0,
    Saturated = true,
    TX = 0,
    init_theta = -0.0138530277141805,
    init_omega = 1.0,
    init_efd = 0.3860165611885247,
    WLMDVPu = 0.717262019773382,
    init_lambdaad = -1.054574030794462,
    init_cm = 0.03969231358150358,
    init_lambdaq1 = 0.06637748916565042,
    init_lambdaq2 = 0.06637748916565042,
    init_iq = 0.3523243707642097,
    init_id = 0.4300612969312733,
    init_lambdaaq = 0.06637748916565042,
    init_lambdad = -1.054574030794462,
    init_lambdaf = -1.172334310290633,
    PNALT = 1008.0,
    IENR = 4,
    DIn = 0.0,
    HIn = 5.4,
    TSD0 = 0.058,
    XPD = 0.407,
    RTfoPu = 0.0,
    TPD0 = 9.651,
    XTfoPu = 0.1,
    rStatIn = 0.00357,
    U1N = 24.0,
    md = 0.084,
    XPQ = 0.454,
    TSQ0 = 0.06,
    SN = 1120.0,
    V1 = 24.0,
    TPQ0 = 1.009,
    mq = 0.084,
    XSD = 0.3,
    snd = 5.57,
    XD = 2.57,
    U2N = 69.0,
    SNtfo = 1120.0,
    lStatIn = 0.219,
    XSQ = 0.301,
    snq = 5.57,
    XQ = 2.57,
    PN = 1008.0,
    IWLMDV = 3
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN____3_SM (
    SNREF = SNREF,
    ur0 = 0.9852123100750539,
    ui0 = -0.22238858966312725,
    transformerIncluded = true,
    V2 = 69.0,
    Saturated = true,
    TX = 0,
    init_theta = -0.2219937331583718,
    init_omega = 1.0,
    init_efd = 0.3160225085854042,
    WLMDVPu = 0.7262486573072185,
    init_lambdaad = -1.015252392938435,
    init_cm = 7.642939028554166e-07,
    init_lambdaq1 = 1.46030494009004e-06,
    init_lambdaq2 = 1.46030494009004e-06,
    init_iq = 1.082909823069134e-05,
    init_id = 0.2434399525528285,
    init_lambdaaq = 1.46030494009004e-06,
    init_lambdad = -1.015252392938435,
    init_lambdaf = -1.145275721480425,
    PNALT = 1485.0,
    IENR = 4,
    DIn = 0.0,
    HIn = 5.625,
    TSD0 = 0.065,
    XPD = 0.509,
    RTfoPu = 0.0,
    TPD0 = 10.041,
    XTfoPu = 0.1,
    rStatIn = 0.00316,
    U1N = 20.0,
    md = 0.05,
    XPQ = 0.601,
    TSQ0 = 0.094,
    SN = 1650.0,
    V1 = 20.0,
    TPQ0 = 1.22,
    mq = 0.05,
    XSD = 0.354,
    snd = 9.285,
    XD = 2.81,
    U2N = 69.0,
    SNtfo = 1650.0,
    lStatIn = 0.256,
    XSQ = 0.377,
    snq = 9.285,
    XQ = 2.62,
    PN = 1485.0,
    IWLMDV = 3
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN____6_SM (
    SNREF = SNREF,
    ur0 = 1.0372147717748634,
    ui0 = -0.262840967795376,
    transformerIncluded = false,
    Saturated = true,
    TX = 0,
    XPQ = 0,
    TPQ0 = 0,
    init_theta = -0.2491728425631222,
    init_omega = 1.0,
    init_efd = 0.9137979532585252,
    WLMDVPu = 0.3981683145836558,
    init_lambdaad = -1.088360366498992,
    init_cm = 0.0001444041874566966,
    init_lambdaq1 = 2.467777795992708e-05,
    init_lambdaq2 = 2.467777795992708e-05,
    init_iq = 9.199946319254915e-05,
    init_id = 0.1440015029604922,
    init_lambdaaq = 2.467777795992708e-05,
    init_lambdad = -1.088360366498992,
    init_lambdaf = -1.458803442290588,
    PNALT = 74.4,
    IENR = 3,
    DIn = 0.0,
    HIn = 4.975,
    TSD0 = 0.04,
    XPD = 0.225,
    TPD0 = 3.0,
    rStatIn = 0.004,
    md = 0.16,
    TSQ0 = 0.06,
    SN = 80.0,
    mq = 0.16,
    XSD = 0.154,
    snd = 5.7,
    XD = 0.75,
    lStatIn = 0.102,
    XSQ = 0.154,
    snq = 5.7,
    XQ = 0.45,
    PN = 71.8,
    IWLMDV = 3
    ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN____8_SM (
    SNREF = SNREF,
    ur0 = 1.0605018355416467,
    ui0 = -0.2518649034267171,
    transformerIncluded = false,
    Saturated = false,
    TX = 0,
    md = 0,
    XPQ = 0,
    TPQ0 = 0,
    mq = 0,
    snd = 0,
    snq = 0,
    init_theta = -0.2336569957365508,
    init_omega = 1.0,
    init_efd = 0.5586415725542784,
    WLMDVPu = 0.6682075874254081,
    init_lambdaad = -1.096985602212581,
    init_cm = 1.666476401932118e-05,
    init_lambdaq1 = 1.231336890260276e-05,
    init_lambdaq2 = 1.231336890260276e-05,
    init_iq = 3.498116165510934e-05,
    init_id = 0.1587622573395195,
    init_lambdaaq = 1.231336890260276e-05,
    init_lambdad = -1.096985602212581,
    init_lambdaf = -1.295727988105895,
    PNALT = 228.0,
    IENR = 3,
    DIn = 0.0,
    HIn = 2.748,
    TSD0 = 0.096,
    XPD = 0.31,
    TPD0 = 8.4,
    rStatIn = 0.004,
    TSQ0 = 0.1,
    SN = 250.0,
    XSD = 0.275,
    XD = 1.53,
    lStatIn = 0.11,
    XSQ = 0.346,
    XQ = 0.99,
    PN = 242.0,
    IWLMDV = 3
    ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN____1_SM (
    SNREF = 100.0,
    SN = 1211.0,
    PN = 1090.0,
    PNALT = 1090.0,
    init_APREF = 0.1918823616845583,
    T4 = 0.015,
    T6 = 0.,
    VSTMIN = -0.1,
    T7 = 2.,
    T8 = 0.,
    T9 = 0.,
    KS1 = 10.,
    VSI2MAX = 999.,
    VSI1MAX = 999.,
    KS3 = 1.,
    KS2 = 0.1564,
    TW2 = 2.,
    TW1 = 2.,
    TW3 = 2.,
    T10 = 0.,
    VSTMAX = 0.1,
    T11 = 0.,
    VSI2MIN = -999.,
    VSI1MIN = -999.,
    T1 = 0.25,
    T2 = 0.03,
    T3 = .1500000
    ) annotation (Placement(transformation()));
  sexs reg_sexs__GEN____1_SM (
    SNREF = 100.0,
    SN = 1211.0,
    PN = 1090.0,
    PNALT = 1090.0,
    init_EFD = 0.447517996150594,
    init_VREF = 1.061047354586989,
    init_YLL = 0.00223758998075297,
    TE = 0.05,
    EFDMIN = -999.,
    KC = 1.,
    EFDMAX = 999.,
    K = 200.,
    EMIN = 0.,
    TA = 3.,
    TB = 10.,
    EMAX = 4.
    ) annotation (Placement(transformation()));
  gsteam0 reg_gsteam0__GEN____1_SM (
    SNREF = 100.0,
    SN = 1211.0,
    PN = 1090.0,
    PNALT = 1090.0,
    init_REF = 0.01066427090100288,
    init_PMECH = 0.2132854180200577,
    init_CM = 0.2132854180200577,
    DT = 0.,
    RR = 0.05,
    VMIN = 0.,
    VMAX = 1.,
    T1 = 0.5,
    T2 = 3.,
    T3 = 10.
    ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN____2_SM (
    SNREF = 100.0,
    SN = 1120.0,
    PN = 1008.0,
    PNALT = 1008.0,
    init_APREF = 0.03571428571428571,
    T4 = 0.015,
    T6 = 0.,
    VSTMIN = -0.1,
    T7 = 2.,
    T8 = 0.,
    T9 = 0.,
    KS1 = 10.,
    VSI2MAX = 999.,
    VSI1MAX = 999.,
    KS3 = 1.,
    KS2 = 0.1564,
    TW2 = 2.,
    TW1 = 2.,
    TW3 = 2.,
    T10 = 0.,
    VSTMAX = 0.1,
    T11 = 0.,
    VSI2MIN = -999.,
    VSI1MIN = -999.,
    T1 = 0.25,
    T2 = 0.03,
    T3 = .1500000
    ) annotation (Placement(transformation()));
  sexs reg_sexs__GEN____2_SM (
    SNREF = 100.0,
    SN = 1120.0,
    PN = 1008.0,
    PNALT = 1008.0,
    init_EFD = 0.3860165611885247,
    init_VREF = 1.050535708267707,
    init_YLL = 0.001930082805942623,
    TE = 0.05,
    EFDMIN = -999.,
    KC = 1.,
    EFDMAX = 999.,
    K = 200.,
    EMIN = 0.,
    TA = 3.,
    TB = 10.,
    EMAX = 4.
    ) annotation (Placement(transformation()));
  gsteam0 reg_gsteam0__GEN____2_SM (
    SNREF = 100.0,
    SN = 1120.0,
    PN = 1008.0,
    PNALT = 1008.0,
    init_REF = 0.001984615679075179,
    init_PMECH = 0.03969231358150358,
    init_CM = 0.03969231358150358,
    DT = 0.,
    RR = 0.05,
    VMIN = 0.,
    VMAX = 1.,
    T1 = 0.5,
    T2 = 3.,
    T3 = 10.
    ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN____3_SM (
    SNREF = 100.0,
    SN = 1650.0,
    PN = 1485.0,
    PNALT = 1485.0,
    init_APREF = 0.0,
    T4 = 0.015,
    T6 = 0.,
    VSTMIN = -0.1,
    T7 = 2.,
    T8 = 0.,
    T9 = 0.,
    KS1 = 10.,
    VSI2MAX = 999.,
    VSI1MAX = 999.,
    KS3 = 1.,
    KS2 = 0.1564,
    TW2 = 2.,
    TW1 = 2.,
    TW3 = 2.,
    T10 = 0.,
    VSTMAX = 0.1,
    T11 = 0.,
    VSI2MIN = -999.,
    VSI1MIN = -999.,
    T1 = 0.25,
    T2 = 0.03,
    T3 = .1500000
    ) annotation (Placement(transformation()));
  sexs reg_sexs__GEN____3_SM (
    SNREF = 100.0,
    SN = 1650.0,
    PN = 1485.0,
    PNALT = 1485.0,
    init_EFD = 0.3160225085854042,
    init_VREF = 1.013055496659479,
    init_YLL = 0.001580112542927021,
    TE = 0.05,
    EFDMIN = -999.,
    KC = 1.,
    EFDMAX = 999.,
    K = 200.,
    EMIN = 0.,
    TA = 3.,
    TB = 10.,
    EMAX = 4.
    ) annotation (Placement(transformation()));
  gsteam0 reg_gsteam0__GEN____3_SM (
    SNREF = 100.0,
    SN = 1650.0,
    PN = 1485.0,
    PNALT = 1485.0,
    init_REF = 3.821469514277083e-08,
    init_PMECH = 7.642939028554166e-07,
    init_CM = 7.642939028554166e-07,
    DT = 0.,
    RR = 0.05,
    VMIN = 0.,
    VMAX = 1.,
    T1 = 0.5,
    T2 = 3.,
    T3 = 10.
    ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN____6_SM (
    SNREF = 100.0,
    SN = 80.0,
    PN = 71.8,
    PNALT = 74.4,
    init_APREF = 0.0,
    T4 = 0.015,
    T6 = 0.,
    VSTMIN = -0.1,
    T7 = 2.,
    T8 = 0.,
    T9 = 0.,
    KS1 = 10.,
    VSI2MAX = 999.,
    VSI1MAX = 999.,
    KS3 = 1.,
    KS2 = 0.1564,
    TW2 = 2.,
    TW1 = 2.,
    TW3 = 2.,
    T10 = 0.,
    VSTMAX = 0.1,
    T11 = 0.,
    VSI2MIN = -999.,
    VSI1MIN = -999.,
    T1 = 0.25,
    T2 = 0.03,
    T3 = .1500000
    ) annotation (Placement(transformation()));
  sexs reg_sexs__GEN____6_SM (
    SNREF = 100.0,
    SN = 80.0,
    PN = 71.8,
    PNALT = 74.4,
    init_EFD = 0.9137979532585252,
    init_VREF = 1.07456892300909,
    init_YLL = 0.004568989766292626,
    TE = 0.05,
    EFDMIN = -999.,
    KC = 1.,
    EFDMAX = 999.,
    K = 200.,
    EMIN = 0.,
    TA = 3.,
    TB = 10.,
    EMAX = 4.
    ) annotation (Placement(transformation()));
  gsteam0 reg_gsteam0__GEN____6_SM (
    SNREF = 100.0,
    SN = 80.0,
    PN = 71.8,
    PNALT = 74.4,
    init_REF = 7.220209372834829e-06,
    init_PMECH = 0.0001444041874566966,
    init_CM = 0.0001444041874566966,
    DT = 0.,
    RR = 0.05,
    VMIN = 0.,
    VMAX = 1.,
    T1 = 0.5,
    T2 = 3.,
    T3 = 10.
    ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN____8_SM (
    SNREF = 100.0,
    SN = 250.0,
    PN = 242.0,
    PNALT = 228.0,
    init_APREF = 2.775557561562892e-18,
    T4 = 0.015,
    T6 = 0.,
    VSTMIN = -0.1,
    T7 = 2.,
    T8 = 0.,
    T9 = 0.,
    KS1 = 10.,
    VSI2MAX = 999.,
    VSI1MAX = 999.,
    KS3 = 1.,
    KS2 = 0.1564,
    TW2 = 2.,
    TW1 = 2.,
    TW3 = 2.,
    T10 = 0.,
    VSTMAX = 0.1,
    T11 = 0.,
    VSI2MIN = -999.,
    VSI1MIN = -999.,
    T1 = 0.25,
    T2 = 0.03,
    T3 = .1500000
    ) annotation (Placement(transformation()));
  sexs reg_sexs__GEN____8_SM (
    SNREF = 100.0,
    SN = 250.0,
    PN = 242.0,
    PNALT = 228.0,
    init_EFD = 0.5586415725542784,
    init_VREF = 1.092793241241373,
    init_YLL = 0.002793207862771392,
    TE = 0.05,
    EFDMIN = -999.,
    KC = 1.,
    EFDMAX = 999.,
    K = 200.,
    EMIN = 0.,
    TA = 3.,
    TB = 10.,
    EMAX = 4.
    ) annotation (Placement(transformation()));
  gsteam0 reg_gsteam0__GEN____8_SM (
    SNREF = 100.0,
    SN = 250.0,
    PN = 242.0,
    PNALT = 228.0,
    init_REF = 8.332382009660589e-07,
    init_PMECH = 1.666476401932118e-05,
    init_CM = 1.666476401932118e-05,
    DT = 0.,
    RR = 0.05,
    VMIN = 0.,
    VMAX = 1.,
    T1 = 0.5,
    T2 = 3.,
    T3 = 10.
    ) annotation (Placement(transformation()));
equation
  omegaRef = (gen_pwGeneratorM2S__GEN____1_SM.omega*gen_pwGeneratorM2S__GEN____1_SM.SN*gen_pwGeneratorM2S__GEN____1_SM.HIn + gen_pwGeneratorM2S__GEN____2_SM.omega*gen_pwGeneratorM2S__GEN____2_SM.SN*gen_pwGeneratorM2S__GEN____2_SM.HIn + gen_pwGeneratorM2S__GEN____3_SM.omega*gen_pwGeneratorM2S__GEN____3_SM.SN*gen_pwGeneratorM2S__GEN____3_SM.HIn + gen_pwGeneratorM2S__GEN____6_SM.omega*gen_pwGeneratorM2S__GEN____6_SM.SN*gen_pwGeneratorM2S__GEN____6_SM.HIn + gen_pwGeneratorM2S__GEN____8_SM.omega*gen_pwGeneratorM2S__GEN____8_SM.SN*gen_pwGeneratorM2S__GEN____8_SM.HIn) / (gen_pwGeneratorM2S__GEN____1_SM.SN*gen_pwGeneratorM2S__GEN____1_SM.HIn + gen_pwGeneratorM2S__GEN____2_SM.SN*gen_pwGeneratorM2S__GEN____2_SM.HIn + gen_pwGeneratorM2S__GEN____3_SM.SN*gen_pwGeneratorM2S__GEN____3_SM.HIn + gen_pwGeneratorM2S__GEN____6_SM.SN*gen_pwGeneratorM2S__GEN____6_SM.HIn + gen_pwGeneratorM2S__GEN____8_SM.SN*gen_pwGeneratorM2S__GEN____8_SM.HIn);
  connect(reg_pssi3e2b__GEN____1_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN____1_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN____1_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____1_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN____1_SM.pin_EFD, gen_pwGeneratorM2S__GEN____1_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN____1_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN____1_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN____1_SM.pin_CM, gen_pwGeneratorM2S__GEN____1_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN____1_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____1_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____2_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN____2_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN____2_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____2_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN____2_SM.pin_EFD, gen_pwGeneratorM2S__GEN____2_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN____2_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN____2_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN____2_SM.pin_CM, gen_pwGeneratorM2S__GEN____2_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN____2_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____2_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____3_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN____3_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN____3_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____3_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN____3_SM.pin_EFD, gen_pwGeneratorM2S__GEN____3_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN____3_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN____3_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN____3_SM.pin_CM, gen_pwGeneratorM2S__GEN____3_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN____3_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____3_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____6_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN____6_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN____6_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____6_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN____6_SM.pin_EFD, gen_pwGeneratorM2S__GEN____6_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN____6_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN____6_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN____6_SM.pin_CM, gen_pwGeneratorM2S__GEN____6_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN____6_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____6_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____8_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN____8_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN____8_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____8_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN____8_SM.pin_EFD, gen_pwGeneratorM2S__GEN____8_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN____8_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN____8_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN____8_SM.pin_CM, gen_pwGeneratorM2S__GEN____8_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN____8_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____8_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____1_SM.pin_VS, reg_sexs__GEN____1_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN____1_SM.pin_OMEGA, reg_gsteam0__GEN____1_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____2_SM.pin_VS, reg_sexs__GEN____2_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN____2_SM.pin_OMEGA, reg_gsteam0__GEN____2_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____3_SM.pin_VS, reg_sexs__GEN____3_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN____3_SM.pin_OMEGA, reg_gsteam0__GEN____3_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____6_SM.pin_VS, reg_sexs__GEN____6_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN____6_SM.pin_OMEGA, reg_gsteam0__GEN____6_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____8_SM.pin_VS, reg_sexs__GEN____8_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN____8_SM.pin_OMEGA, reg_gsteam0__GEN____8_SM.pin_OMEGA) annotation (Line());
  connect(bus__BUS___10_TN.p, line__BUS___10_BUS___11_1_AC.p) annotation (Line());
  connect(line__BUS___10_BUS___11_1_AC.n, bus__BUS___11_TN.p) annotation (Line());
  connect(bus__BUS___12_TN.p, line__BUS___12_BUS___13_1_AC.p) annotation (Line());
  connect(line__BUS___12_BUS___13_1_AC.n, bus__BUS___13_TN.p) annotation (Line());
  connect(bus__BUS___13_TN.p, line__BUS___13_BUS___14_1_AC.p) annotation (Line());
  connect(line__BUS___13_BUS___14_1_AC.n, bus__BUS___14_TN.p) annotation (Line());
  connect(bus__BUS____1_TN.p, line__BUS____1_BUS____2_1_AC.p) annotation (Line());
  connect(line__BUS____1_BUS____2_1_AC.n, bus__BUS____2_TN.p) annotation (Line());
  connect(bus__BUS____1_TN.p, line__BUS____1_BUS____5_1_AC.p) annotation (Line());
  connect(line__BUS____1_BUS____5_1_AC.n, bus__BUS____5_TN.p) annotation (Line());
  connect(bus__BUS____2_TN.p, line__BUS____2_BUS____3_1_AC.p) annotation (Line());
  connect(line__BUS____2_BUS____3_1_AC.n, bus__BUS____3_TN.p) annotation (Line());
  connect(bus__BUS____2_TN.p, line__BUS____2_BUS____4_1_AC.p) annotation (Line());
  connect(line__BUS____2_BUS____4_1_AC.n, bus__BUS____4_TN.p) annotation (Line());
  connect(bus__BUS____2_TN.p, line__BUS____2_BUS____5_1_AC.p) annotation (Line());
  connect(line__BUS____2_BUS____5_1_AC.n, bus__BUS____5_TN.p) annotation (Line());
  connect(bus__BUS____3_TN.p, line__BUS____3_BUS____4_1_AC.p) annotation (Line());
  connect(line__BUS____3_BUS____4_1_AC.n, bus__BUS____4_TN.p) annotation (Line());
  connect(bus__BUS____4_TN.p, line__BUS____4_BUS____5_1_AC.p) annotation (Line());
  connect(line__BUS____4_BUS____5_1_AC.n, bus__BUS____5_TN.p) annotation (Line());
  connect(bus__BUS____6_TN.p, line__BUS____6_BUS___11_1_AC.p) annotation (Line());
  connect(line__BUS____6_BUS___11_1_AC.n, bus__BUS___11_TN.p) annotation (Line());
  connect(bus__BUS____6_TN.p, line__BUS____6_BUS___12_1_AC.p) annotation (Line());
  connect(line__BUS____6_BUS___12_1_AC.n, bus__BUS___12_TN.p) annotation (Line());
  connect(bus__BUS____6_TN.p, line__BUS____6_BUS___13_1_AC.p) annotation (Line());
  connect(line__BUS____6_BUS___13_1_AC.n, bus__BUS___13_TN.p) annotation (Line());
  connect(bus__BUS____7_TN.p, line__BUS____7_BUS____8_1_AC.p) annotation (Line());
  connect(line__BUS____7_BUS____8_1_AC.n, bus__BUS____8_TN.p) annotation (Line());
  connect(bus__BUS____7_TN.p, line__BUS____7_BUS____9_1_AC.p) annotation (Line());
  connect(line__BUS____7_BUS____9_1_AC.n, bus__BUS____9_TN.p) annotation (Line());
  connect(bus__BUS____9_TN.p, line__BUS____9_BUS___10_1_AC.p) annotation (Line());
  connect(line__BUS____9_BUS___10_1_AC.n, bus__BUS___10_TN.p) annotation (Line());
  connect(bus__BUS____9_TN.p, line__BUS____9_BUS___14_1_AC.p) annotation (Line());
  connect(line__BUS____9_BUS___14_1_AC.n, bus__BUS___14_TN.p) annotation (Line());
  connect(bus__BUS___10_TN.p, load__LOAD__10_EC.p) annotation (Line());
  connect(bus__BUS___11_TN.p, load__LOAD__11_EC.p) annotation (Line());
  connect(bus__BUS___12_TN.p, load__LOAD__12_EC.p) annotation (Line());
  connect(bus__BUS___13_TN.p, load__LOAD__13_EC.p) annotation (Line());
  connect(bus__BUS___14_TN.p, load__LOAD__14_EC.p) annotation (Line());
  connect(bus__BUS____2_TN.p, load__LOAD___2_EC.p) annotation (Line());
  connect(bus__BUS____3_TN.p, load__LOAD___3_EC.p) annotation (Line());
  connect(bus__BUS____4_TN.p, load__LOAD___4_EC.p) annotation (Line());
  connect(bus__BUS____5_TN.p, load__LOAD___5_EC.p) annotation (Line());
  connect(bus__BUS____6_TN.p, load__LOAD___6_EC.p) annotation (Line());
  connect(bus__BUS____9_TN.p, load__LOAD___9_EC.p) annotation (Line());
  connect(bus__BUS____9_TN.p, cap__BANK___9_SC.p) annotation (Line());
  connect(bus__BUS____1_TN.p, gen_pwGeneratorM2S__GEN____1_SM.sortie) annotation (Line());
  connect(bus__BUS____2_TN.p, gen_pwGeneratorM2S__GEN____2_SM.sortie) annotation (Line());
  connect(bus__BUS____3_TN.p, gen_pwGeneratorM2S__GEN____3_SM.sortie) annotation (Line());
  connect(bus__BUS____6_TN.p, gen_pwGeneratorM2S__GEN____6_SM.sortie) annotation (Line());
  connect(bus__BUS____8_TN.p, gen_pwGeneratorM2S__GEN____8_SM.sortie) annotation (Line());
  connect(bus__BUS____4_TN.p, trafo__BUS____4_BUS____7_1_PT.p) annotation (Line());
  connect(trafo__BUS____4_BUS____7_1_PT.n, bus__BUS____7_TN.p) annotation (Line());
  connect(bus__BUS____4_TN.p, trafo__BUS____4_BUS____9_1_PT.p) annotation (Line());
  connect(trafo__BUS____4_BUS____9_1_PT.n, bus__BUS____9_TN.p) annotation (Line());
  connect(bus__BUS____5_TN.p, trafo__BUS____5_BUS____6_1_PT.p) annotation (Line());
  connect(trafo__BUS____5_BUS____6_1_PT.n, bus__BUS____6_TN.p) annotation (Line());
end ieee14bus;
