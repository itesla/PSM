model ex_stat_init
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
  parameter Real PLEXM;
  parameter Real PLEXP;
  parameter Real RFE;
  parameter Real TE;
  parameter Real SNREF;
  parameter Real SN;
  parameter Real PN;
  parameter Real PNALT;
  Modelica.Blocks.Interfaces.RealInput pin_FieldCurrent;
  Modelica.Blocks.Interfaces.RealOutput pin_IEX;
equation
  connect(pin_IEX, pin_FieldCurrent);
end ex_stat_init;