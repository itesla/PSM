model sexs_init
//SEXS SEXS_1(EMAX=4.,EMIN=0.,K=200.,TA=3.,TB=10.,TE=0.05);
//SEXS SEXS_2(EMAX=6.,EMIN=0.,K=400.,TA=1.,TB=10.,TE=0.0001);
//SEXS SEXS_3(EMAX=4.,EMIN=0.,K=100.,TA=1.,TB=10.,TE=0.0001);
//SEXS SEXS_4(EMAX=6.,EMIN=0.,K=100.,TA=1.,TB=10.,TE=0.0001);
//SEXS SEXS_5(EMAX=4.5,EMIN=0.,K=100.,TA=1.,TB=10.,TE=0.0001);
//SEXS SEXS_6(EMAX=4.,EMIN=0.,K=100.,TA=1.,TB=10.,TE=0.1);
//SEXS SEXS_7(EMAX=6.,EMIN=0.,K=100.,TA=1.,TB=10.,TE=0.05);
//SEXS SEXS_8(EMAX=4.5,EMIN=0.,K=100.,TA=1.,TB=10.,TE=0.1);
//SEXS SEXS_9(EMAX=6.,EMIN=0.,K=400.,TA=1.,TB=10.,TE=0.05);
//SEXS SEXS_10(EMAX=4.,EMIN=0.,K=100.,TA=1.,TB=10.,TE=0.1);
//SEXS SEXS_11(EMAX=6.,EMIN=0.,K=100.,TA=1.,TB=10.,TE=0.05);
//SEXS SEXS_12(EMAX=4.5,EMIN=0.,K=100.,TA=1.,TB=10.,TE=0.1);
//SEXS SEXS_13(EMAX=6.,EMIN=0.,K=400.,TA=1.,TB=10.,TE=0.05);
//SEXS SEXS_14(EMAX=5.,EMIN=-2.,K=80.,TA=0.045,TB=0.02,TE=0.05);
//SEXS SEXS_15(EMAX=5.,EMIN=-2.,K=50.,TA=1.,TB=10.,TE=0.05);
//SEXS SEXS_16(EMAX=4.900000,EMIN=-4.90000,K=200.,TA=1.,TB=0.02,TE=5.);
//SEXS SEXS_17(EMAX=4.5,EMIN=-5.80000,K=138.7600,TA=.9570000,TB=9.570000,TE=0.003);
//SEXS SEXS_18(EMAX=4.5,EMIN=-5.80000,K=138.7600,TA=.4785000,TB=9.570000,TE=0.003);
//SEXS SEXS_19(EMAX=5.014000,EMIN=0.,K=60.,TA=0.2,TB=10.,TE=0.025);
//SEXS SEXS_20(EMAX=4.074000,EMIN=0.,K=200.,TA=0.1,TB=40.,TE=.4000000);
//SEXS SEXS_21(EMAX=4.080000,EMIN=0.,K=200.,TA=0.1,TB=40.,TE=.4000000);
//SEXS SEXS_22(EMAX=5.039000,EMIN=0.,K=60.,TA=0.2,TB=10.,TE=0.025);
//SEXS SEXS_23(EMAX=4.843000,EMIN=0.,K=50.,TA=0.2,TB=10.,TE=0.34);
//SEXS SEXS_24(EMAX=4.798000,EMIN=0.,K=50.,TA=0.2,TB=10.,TE=0.243);
//SEXS SEXS_25(EMAX=3.097000,EMIN=0.,K=60.,TA=0.2,TB=10.,TE=0.025);
//SEXS SEXS_26(EMAX=3.082000,EMIN=0.,K=60.,TA=0.2,TB=10.,TE=0.025);
//SEXS SEXS_27(EMAX=3.109000,EMIN=0.,K=60.,TA=0.2,TB=10.,TE=0.025);
//SEXS SEXS_28(EMAX=3.146000,EMIN=0.,K=60.,TA=0.2,TB=10.,TE=0.025);
//SEXS SEXS_29(EMAX=3.013000,EMIN=0.,K=60.,TA=0.2,TB=10.,TE=0.025);
//SEXS SEXS_30(EMAX=3.189000,EMIN=0.,K=60.,TA=0.2,TB=10.,TE=0.025);
//SEXS SEXS_31(EMAX=3.152000,EMIN=0.,K=60.,TA=0.2,TB=10.,TE=0.025);
//SEXS SEXS_32(EMAX=3.388000,EMIN=0.,K=60.,TA=0.2,TB=10.,TE=0.025);
//SEXS SEXS_33(EMAX=3.624000,EMIN=0.,K=60.,TA=0.2,TB=10.,TE=0.025);
//SEXS SEXS_34(EMAX=2.920000,EMIN=0.,K=60.,TA=0.2,TB=10.,TE=0.025);
//SEXS SEXS_35(EMAX=3.263000,EMIN=0.,K=60.,TA=0.2,TB=10.,TE=0.025);
//SEXS SEXS_36(EMAX=3.284000,EMIN=0.,K=60.,TA=0.2,TB=10.,TE=0.025);
//SEXS SEXS_37(EMAX=3.371000,EMIN=0.,K=60.,TA=0.2,TB=10.,TE=0.025);
//SEXS SEXS_38(EMAX=3.268000,EMIN=0.,K=60.,TA=0.2,TB=10.,TE=0.025);
//SEXS SEXS_39(EMAX=5.075000,EMIN=0.,K=60.,TA=0.2,TB=10.,TE=0.025);
//SEXS SEXS_40(EMAX=3.927000,EMIN=0.,K=60.,TA=0.2,TB=10.,TE=0.025);
  parameter Real EMAX;
  parameter Real EMIN;
  parameter Real K;
  parameter Real TA;
  parameter Real TB;
  parameter Real TE;
  parameter Real SNREF;
  parameter Real SN;
  parameter Real PN;
  parameter Real PNALT;
  Modelica.Blocks.Math.Gain Gain_1 (k=1/K); //Eurostag Block number: 1
  Modelica.Blocks.Math.MultiSum MultiSum_2 (nu =3, k={+1, +1, -1}); //Eurostag Block number: 2
  Modelica.Blocks.Interfaces.RealInput pin_EFD;
  Modelica.Blocks.Interfaces.RealInput pin_VS;
  Modelica.Blocks.Interfaces.RealInput pin_TerminalVoltage;
  Modelica.Blocks.Interfaces.RealOutput pin_YLL; //isInitValue
  Modelica.Blocks.Interfaces.RealOutput pin_VREF; //isInitValue
  Modelica.Blocks.Interfaces.RealOutput pin_At_V;
equation
  connect(Gain_1.y, MultiSum_2.u[2]);
  connect(pin_TerminalVoltage, MultiSum_2.u[1]);
  connect(pin_EFD, Gain_1.u);
  connect(pin_VS, MultiSum_2.u[3]);
  connect(pin_YLL, Gain_1.y);
  connect(pin_VREF, MultiSum_2.y);
  connect(pin_At_V, pin_TerminalVoltage);
end sexs_init;