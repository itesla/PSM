model consig
//CONSIG CONSIG_1(ZERO=0.);
  parameter Real init_1=0;
  parameter Real init_2=init_PV;
  parameter Real ZERO;
  parameter Real SNREF;
  parameter Real SN;
  parameter Real PN;
  parameter Real PNALT;
  parameter Real init_PV;
  Modelica.Blocks.Sources.Constant Constant_1 (k=ZERO); //Eurostag Block number: 1
  iPSL.NonElectrical.Eurostag.Math.ImSetPoint ImSetPoint_2 (V=init_2); //Eurostag Block number: 2
  Modelica.Blocks.Interfaces.RealOutput pin_APR;//Start = 0
  Modelica.Blocks.Interfaces.RealOutput pin_PV; //PV
equation
  connect(pin_APR, Constant_1.y);
  connect(pin_PV, ImSetPoint_2.y);
end consig;