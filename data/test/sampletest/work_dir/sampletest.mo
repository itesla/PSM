within ;
model sampletest
  parameter Real SNREF = 100.0;

   PwGeneratorM2S gen_pwGeneratorM2S__GEN____1_SM(
  SNREF = SNREF,
  ur0 = 1.0600,
  ui0 = 0.0,
  transformerIncluded = true,
  V2 = 69.0,
  Saturated = true,
  TX = 0,
  init_theta = 0.31162,
  init_omega = 1.0,
  init_efd = 0.44528,
  WLMDVPu = 0.6296504227510634,
  init_lambdaad = -1.0216,
  init_cm = 0.21337,
  init_lambdaq1 = 0.27158,
  init_lambdaq2 = 0.27158,
  init_iq = 2.1465,
  init_id = 0.48903,
  init_lambdaaq = 0.27158,
  init_lambdad = -1.0216,
  init_lambdaf = -1.1804,
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
  IWLMDV = 3);
   Modelica.Blocks.Interfaces.RealOutput omegaRef = 1;

equation
  connect(gen_pwGeneratorM2S__GEN____1_SM.omegaRef, omegaRef);
  gen_pwGeneratorM2S__GEN____1_SM.pin_EFD = 0.44528;
  gen_pwGeneratorM2S__GEN____1_SM.pin_CM = 0.21337;

  annotation (uses(Modelica(version="3.2.1")));
end test;
