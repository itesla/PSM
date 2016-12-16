model ex_stat
//EX_STAT EX_STAT_1(PLEXM=-1.6,PLEXP=3.,RFE=200.,TE=0.02);
//EX_STAT EX_STAT_2(PLEXM=-1.6,PLEXP=3.,RFE=200.,TE=0.02);
//EX_STAT EX_STAT_3(PLEXM=-1.6,PLEXP=3.,RFE=200.,TE=0.02);
//EX_STAT EX_STAT_4(PLEXM=-1.6,PLEXP=3.,RFE=200.,TE=0.02);
//EX_STAT EX_STAT_5(PLEXM=-1.6,PLEXP=3.,RFE=200.,TE=0.02);
//EX_STAT EX_STAT_6(PLEXM=-1.6,PLEXP=3.,RFE=200.,TE=0.02);
//EX_STAT EX_STAT_7(PLEXM=-1.6,PLEXP=3.,RFE=200.,TE=0.02);
//EX_STAT EX_STAT_8(PLEXM=-1.6,PLEXP=3.,RFE=200.,TE=0.02);
//EX_STAT EX_STAT_9(PLEXM=-1.09,PLEXP=1.260000,RFE=200.,TE=0.01);
//EX_STAT EX_STAT_10(PLEXM=-1.09,PLEXP=1.260000,RFE=200.,TE=0.01);
  parameter Real init_16=init_EFD;
  parameter Real init_3=init_IEX;
  parameter Real init_4=init_EFD;
  parameter Real PLEXM;
  parameter Real PLEXP;
  parameter Real RFE;
  parameter Real TE;
  parameter Real SNREF;
  parameter Real SN;
  parameter Real PN;
  parameter Real PNALT;
  parameter Real init_EFD;
  parameter Real init_IEX;
  Modelica.Blocks.Math.Gain Gain_8 (k=PLEXM); //Eurostag Block number: 8
  Modelica.Blocks.Math.Gain Gain_11 (k=PLEXP); //Eurostag Block number: 11
  Modelica.Blocks.Nonlinear.VariableLimiter VariableLimiter_13; //Eurostag Block number: 13
  Modelica.Blocks.Math.Gain Gain_10 (k=-RFE); //Eurostag Block number: 10
  iPSL.NonElectrical.Continuous.SimpleLag SimpleLag_16 (K=1., T=TE, y_start=init_16, initType = Modelica.Blocks.Types.Init.SteadyState); //Eurostag Block number: 16
  iPSL.NonElectrical.Logical.Relay Relay_19; //Eurostag Block number: 19
  iPSL.NonElectrical.Continuous.SimpleLag SimpleLag_3 (K=1, T=0.01, y_start=init_3, initType = Modelica.Blocks.Types.Init.SteadyState); //Eurostag Block number: 3
  iPSL.NonElectrical.Continuous.SimpleLag SimpleLag_4 (K=1, T=0.01, y_start=init_4, initType = Modelica.Blocks.Types.Init.SteadyState); //Eurostag Block number: 4
  Modelica.Blocks.Interfaces.RealInput pin_SREGUL;
  Modelica.Blocks.Interfaces.RealInput pin_TerminalVoltage;
  Modelica.Blocks.Interfaces.RealInput pin_FieldCurrent;
  Modelica.Blocks.Interfaces.RealOutput pin_IEX;
  Modelica.Blocks.Interfaces.RealOutput pin_EFD; //EFD
equation
  connect(Relay_19.y, SimpleLag_16.u);
  connect(VariableLimiter_13.y, Relay_19.u2);
  connect(Gain_8.y, VariableLimiter_13.limit2);
  connect(Gain_11.y, VariableLimiter_13.limit1);
  connect(Gain_10.y, Relay_19.u3);
  connect(pin_TerminalVoltage, Gain_11.u);
  connect(pin_TerminalVoltage, Gain_8.u);
  connect(SimpleLag_16.y, SimpleLag_4.u);
  connect(pin_FieldCurrent, Gain_10.u);
  connect(pin_FieldCurrent, SimpleLag_3.u);
  connect(pin_SREGUL, VariableLimiter_13.u);
  connect(pin_IEX, Relay_19.u1);
  connect(pin_IEX, SimpleLag_3.y);
  connect(pin_EFD, SimpleLag_4.y);
end ex_stat;