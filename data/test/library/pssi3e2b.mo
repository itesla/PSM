model pssi3e2b
//PSSI3E2B PSSI3E2B_1(KS1=10.,KS2=0.1564,KS3=1.,T1=0.25,T10=0.,T11=0.,T2=0.03,T3=.1500000,T4=0.015,T6=0.,T7=2.,T8=0.,T9=0.,TW1=2.,TW2=2.,TW3=2.,VSI1MAX=999.,VSI1MIN=-999.,VSI2MAX=999.,VSI2MIN=-999.,VSTMAX=0.1,VSTMIN=-0.1);
  parameter Real init_3=0;
  parameter Real init_2=0;
  parameter Real init_15=0;
  parameter Real init_4=init_APREF;
  parameter Real init_7=0;
  parameter Real init_5=0;
  parameter Real init_8=0;
  parameter Real init_10=0;
  parameter Real init_12=0;
  parameter Real init_13=0;
  parameter Real init_14=0;
  parameter Real init_17=0;
  parameter Real init_18=0;
  parameter Real init_11=0;
  parameter Real init_6=0;
  parameter Real KS1;
  parameter Real KS2;
  parameter Real KS3;
  parameter Real T1;
  parameter Real T10;
  parameter Real T11;
  parameter Real T2;
  parameter Real T3;
  parameter Real T4;
  parameter Real T6;
  parameter Real T7;
  parameter Real T8;
  parameter Real T9;
  parameter Real TW1;
  parameter Real TW2;
  parameter Real TW3;
  parameter Real VSI1MAX;
  parameter Real VSI1MIN;
  parameter Real VSI2MAX;
  parameter Real VSI2MIN;
  parameter Real VSTMAX;
  parameter Real VSTMIN;
  parameter Real SNREF;
  parameter Real SN;
  parameter Real PN;
  parameter Real PNALT;
  parameter Real init_APREF;
  iPSL.NonElectrical.Continuous.DerivativeLag DerivativeLag_3 (K=TW1, T=TW1, y_start=0, x_start=init_3); //Eurostag Block number: 3
  Modelica.Blocks.Math.MultiSum MultiSum_16 (nu =2, k={1, -1}); //Eurostag Block number: 16
  iPSL.NonElectrical.Continuous.SimpleLag SimpleLag_2 (K=KS2, T=T7, y_start=init_2, initType = Modelica.Blocks.Types.Init.SteadyState); //Eurostag Block number: 2
  iPSL.NonElectrical.Continuous.DerivativeLag DerivativeLag_15 (K=TW2, T=TW2, y_start=0, x_start=init_15); //Eurostag Block number: 15
  Modelica.Blocks.Math.MultiSum MultiSum_20 (nu =2, k={KS3, 1}); //Eurostag Block number: 20
  Modelica.Blocks.Math.MultiSum MultiSum_27 (nu =2, k={-1, 1}); //Eurostag Block number: 27
  iPSL.NonElectrical.Continuous.DerivativeLag DerivativeLag_4 (K=TW3, T=TW3, y_start=0, x_start=init_4); //Eurostag Block number: 4
  iPSL.NonElectrical.Continuous.SimpleLag SimpleLag_7 (K=1, T=T6, y_start=init_7, initType = Modelica.Blocks.Types.Init.SteadyState); //Eurostag Block number: 7
  iPSL.NonElectrical.Continuous.LeadLag LeadLag_5 (K=KS1, T1=T1, T2=T2, y_start=init_5); //Eurostag Block number: 5
  iPSL.NonElectrical.Continuous.LeadLag LeadLag_8 (K=1, T1=T3, T2=T4, y_start=init_8); //Eurostag Block number: 8
  Modelica.Blocks.Nonlinear.Limiter Limiter_10 (uMax=VSTMAX, uMin=VSTMIN); //Eurostag Block number: 10
  iPSL.NonElectrical.Continuous.SimpleLag SimpleLag_12 (K=1, T=T9, y_start=init_12, initType = Modelica.Blocks.Types.Init.SteadyState); //Eurostag Block number: 12
  iPSL.NonElectrical.Continuous.SimpleLag SimpleLag_13 (K=1, T=T9, y_start=init_13, initType = Modelica.Blocks.Types.Init.SteadyState); //Eurostag Block number: 13
  iPSL.NonElectrical.Continuous.SimpleLag SimpleLag_14 (K=1, T=T9, y_start=init_14, initType = Modelica.Blocks.Types.Init.SteadyState); //Eurostag Block number: 14
  iPSL.NonElectrical.Continuous.SimpleLag SimpleLag_17 (K=1, T=T9, y_start=init_17, initType = Modelica.Blocks.Types.Init.SteadyState); //Eurostag Block number: 17
  iPSL.NonElectrical.Continuous.SimpleLag SimpleLag_18 (K=1, T=T9, y_start=init_18, initType = Modelica.Blocks.Types.Init.SteadyState); //Eurostag Block number: 18
  iPSL.NonElectrical.Continuous.LeadLag LeadLag_11 (K=1, T1=T8, T2=T9, y_start=init_11); //Eurostag Block number: 11
  iPSL.NonElectrical.Continuous.LeadLag LeadLag_6 (K=1, T1=T10, T2=T11, y_start=init_6); //Eurostag Block number: 6
  Modelica.Blocks.Nonlinear.Limiter Limiter_9 (uMax=VSI2MAX, uMin=VSI2MIN); //Eurostag Block number: 9
  Modelica.Blocks.Nonlinear.Limiter Limiter_19 (uMax=VSI1MAX, uMin=VSI1MIN); //Eurostag Block number: 19
  Modelica.Blocks.Interfaces.RealInput pin_ActivePowerSN;
  Modelica.Blocks.Interfaces.RealInput pin_OMEGA;
  Modelica.Blocks.Interfaces.RealOutput pin_At_IN;
  Modelica.Blocks.Interfaces.RealOutput pin_VS;//Start = 0
  Modelica.Blocks.Interfaces.RealOutput pin_At_OUT;
equation
  connect(LeadLag_11.y, SimpleLag_12.u);
  connect(SimpleLag_2.y, MultiSum_20.u[1]);
  connect(SimpleLag_7.y, MultiSum_20.u[2]);
  connect(DerivativeLag_3.y, DerivativeLag_15.u);
  connect(DerivativeLag_15.y, SimpleLag_7.u);
  connect(pin_ActivePowerSN, Limiter_9.u);
  connect(Limiter_9.y, DerivativeLag_4.u);
  connect(MultiSum_16.y, Limiter_19.u);
  connect(Limiter_19.y, DerivativeLag_3.u);
  connect(DerivativeLag_4.y, SimpleLag_2.u);
  connect(MultiSum_27.y, LeadLag_5.u);
  connect(MultiSum_20.y, LeadLag_11.u);
  connect(SimpleLag_2.y, MultiSum_27.u[1]);
  connect(LeadLag_6.y, Limiter_10.u);
  connect(LeadLag_5.y, LeadLag_8.u);
  connect(LeadLag_8.y, LeadLag_6.u);
  connect(SimpleLag_17.y, SimpleLag_18.u);
  connect(SimpleLag_14.y, SimpleLag_17.u);
  connect(SimpleLag_13.y, SimpleLag_14.u);
  connect(SimpleLag_12.y, SimpleLag_13.u);
  connect(pin_OMEGA, MultiSum_16.u[1]);
  connect(pin_At_OUT, MultiSum_27.u[2]);
  connect(pin_At_IN, MultiSum_20.y);
  connect(pin_VS, Limiter_10.y);
  connect(pin_At_OUT, SimpleLag_18.y);
  MultiSum_16.u[2] =1;
end pssi3e2b;