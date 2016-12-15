model consig_init
//CONSIG CONSIG_1(ZERO=0.);
  parameter Real ZERO;
  parameter Real SNREF;
  parameter Real SN;
  parameter Real PN;
  parameter Real PNALT;
  Modelica.Blocks.Sources.Constant Constant_1 (k=1.0); //Eurostag Block number: 1
  Modelica.Blocks.Interfaces.RealOutput pin_PV;
equation
  connect(pin_PV, Constant_1.y);
end consig_init;