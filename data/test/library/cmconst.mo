model cmconst
//CMCONST CMCONST_1(
  parameter Real init_1=init_CM;
  parameter Real SNREF;
  parameter Real SN;
  parameter Real PN;
  parameter Real PNALT;
  parameter Real init_CM;
  Modelica.Blocks.Sources.Constant Constant_1 (k=init_CM); //Eurostag Block number: 1
  Modelica.Blocks.Interfaces.RealOutput pin_CM; //CM
equation
  connect(pin_CM, Constant_1.y);
end cmconst;