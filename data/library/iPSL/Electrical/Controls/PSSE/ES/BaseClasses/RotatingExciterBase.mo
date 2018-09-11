within iPSL.Electrical.Controls.PSSE.ES.BaseClasses;

model RotatingExciterBase
  parameter Real T_E "Exciter time constant (s)";
  parameter Real K_E "Exciter field gain";
  parameter Real E_1 "Exciter saturation point 1 (pu)";
  parameter Real E_2 "Exciter saturation point 2 (pu)";
  parameter Real S_EE_1 "Saturation at E_1";
  parameter Real S_EE_2 "Saturation at E_2";
  parameter Real Efd0;
  Modelica.Blocks.Interfaces.RealInput I_C annotation(Placement(transformation(extent = {{-120, -20}, {-80, 20}}), iconTransformation(extent = {{-100, -10}, {-80, 10}})));
  Modelica.Blocks.Interfaces.RealOutput EFD annotation(Placement(transformation(extent = {{80, -10}, {100, 10}}), iconTransformation(extent = {{80, -10}, {100, 10}})));
  Modelica.Blocks.Math.Gain gain(k = K_E) annotation(Placement(transformation(extent = {{-10, -10}, {10, 10}}, rotation = 180, origin = {30, -50})));
  replaceable Modelica.Blocks.Math.Add Sum annotation(Placement(transformation(extent = {{-10, 10}, {10, -10}}, rotation = 180, origin = {-30, -30})));
  Modelica.Blocks.Math.Product VE annotation(Placement(transformation(extent = {{-10, -10}, {10, 10}}, rotation = 180, origin = {30, -10})));
  NonElectrical.Functions.ImSE se1(SE1 = S_EE_1, SE2 = S_EE_2, E1 = E_1, E2 = E_2) annotation(Placement(transformation(extent = {{-9, -6}, {9, 6}}, rotation = 180, origin = {61, -4})));
  Modelica.Blocks.Math.Feedback feedback annotation(Placement(transformation(extent = {{-60, 10}, {-40, 30}})));
  replaceable Modelica.Blocks.Interfaces.SISO sISO annotation(Placement(transformation(extent = {{-10, 10}, {10, 30}})));
equation
  connect(Sum.y, feedback.u2) annotation(Line(points = {{-41, -30}, {-41, -30}, {-50, -30}, {-50, 12}}, color = {0, 0, 127}));
  connect(se1.VE_OUT, VE.u2) annotation(Line(points = {{51.46, -4}, {42, -4}}, color = {0, 0, 127}));
  connect(se1.VE_IN, EFD) annotation(Line(points = {{70.9, -4}, {76, -4}, {76, 0}, {90, 0}}, color = {0, 0, 127}));
  connect(VE.u1, EFD) annotation(Line(points = {{42, -16}, {76, -16}, {76, 0}, {90, 0}}, color = {0, 0, 127}));
  connect(gain.u, EFD) annotation(Line(points = {{42, -50}, {76, -50}, {76, 0}, {90, 0}}, color = {0, 0, 127}));
  connect(gain.y, Sum.u2) annotation(Line(points = {{19, -50}, {-12, -50}, {-12, -30}, {-18, -30}}, color = {0, 0, 127}));
  connect(VE.y, Sum.u1) annotation(Line(points = {{19, -10}, {-12, -10}, {-12, -22}, {-18, -22}}, color = {0, 0, 127}));
  connect(I_C, feedback.u1) annotation(Line(points = {{-100, 0}, {-68, 0}, {-68, 20}, {-58, 20}}, color = {0, 0, 127}));
  connect(feedback.y, sISO.u) annotation(Line(points = {{-41, 20}, {-12, 20}, {-12, 20}}, color = {0, 0, 127}));
  connect(sISO.y, EFD) annotation(Line(points = {{11, 20}, {76, 20}, {76, 0}, {90, 0}}, color = {0, 0, 127}));
  annotation(Icon(coordinateSystem(initialScale = 0.05, extent = {{-80, -80}, {80, 80}}), graphics = {Rectangle(extent = {{-80, 80}, {80, -80}}, lineColor = {28, 108, 200}, fillColor = {255, 255, 255}, fillPattern = FillPattern.Solid), Text(extent = {{-52, 70}, {44, 56}}, lineColor = {28, 108, 200}, fillColor = {255, 255, 255}, fillPattern = FillPattern.Solid, textString = "Rotating Exciter"), Text(extent = {{-81, 4}, {-57, -6}}, lineColor = {28, 108, 200}, textString = "I_C"), Text(extent = {{56, 6}, {77, -4}}, lineColor = {28, 108, 200}, textString = "V_E")}), Diagram(coordinateSystem(initialScale = 0.05, extent = {{-80, -80}, {80, 80}})), Documentation(revisions = "<html>
<!--DISCLAIMER-->

<p>iPSL:</p>
<p>Copyright 2015-2016 RTE (France), SmarTS Lab (Sweden), AIA (Spain) and DTU (Denmark)</p>
<ul>
<li>RTE: <a href=\"http://www.rte-france.com\">http://www.rte-france.com</a></li>
<li>SmarTS Lab, research group at KTH: <a href=\"https://www.kth.se/en\">https://www.kth.se/en</a></li>
<li>AIA: <a href=\"http://www.aia.es/en/energy\"> http://www.aia.es/en/energy</a></li>
<li>DTU: <a href=\"http://www.dtu.dk/english\"> http://www.dtu.dk/english</a></li>
</ul>
<p>The authors can be contacted by email: <a href=\"mailto:info@itesla-ipsl.org\">info@itesla-ipsl.org</a></p>

<p>This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. </p>
<p>If a copy of the MPL was not distributed with this file, You can obtain one at <a href=\"http://mozilla.org/MPL/2.0/\"> http://mozilla.org/MPL/2.0</a>.</p>
</html>
"));
end RotatingExciterBase;