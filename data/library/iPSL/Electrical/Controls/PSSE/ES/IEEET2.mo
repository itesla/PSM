within iPSL.Electrical.Controls.PSSE.ES;

model IEEET2 "IEEE Type 2 excitation system"
  extends iPSL.Electrical.Controls.PSSE.ES.BaseClasses.BaseExciter;
  import iPSL.Electrical.Controls.PSSE.ES.BaseClasses.calculate_dc_exciter_params;
  parameter Real T_R = 0.02 "Voltage input time constant (s)";
  parameter Real K_A = 200.0 "AVR gain";
  parameter Real T_A = 0.001 "AVR time constant (s)";
  parameter Real V_RMAX = 6.08 "Maximum AVR output (pu)";
  parameter Real V_RMIN = -6.08 "Minimum AVR output (pu)";
  parameter Real K_E = 1 "Exciter field gain";
  parameter Real T_E = 0.55 "Exciter time constant s";
  parameter Real K_F = 0.06 "Rate feedback gain (pu)";
  parameter Real T_F1 = 0.3 "Rate feedback time constant (s)";
  parameter Real T_F2 = 0.6 "Rate feedback time constant (s)";
  parameter Real E_1 = 2.85 "Exciter saturation point 1 (pu)";
  parameter Real S_EE_1 = 0.3 "Saturation at E_1";
  parameter Real E_2 = 3.8 "Exciter saturation point 2 (pu)";
  parameter Real S_EE_2 = 0.6 "Saturation at E_2";
  Modelica.Blocks.Math.Add3 add3_1 annotation(Placement(transformation(extent = {{-60, -10}, {-40, 10}})));
  Modelica.Blocks.Math.Add add(k2 = -1) annotation(Placement(transformation(extent = {{-20, -10}, {0, 10}})));
  NonElectrical.Continuous.SimpleLag simpleLag(K = 1, T = T_F2, y_start = 0) annotation(Placement(transformation(extent = {{20, -60}, {0, -40}})));
  Modelica.Blocks.Continuous.Derivative derivativeLag(k = K_F, y_start = 0, T = T_F1, initType = Modelica.Blocks.Types.Init.InitialOutput, x_start = VR0) annotation(Placement(transformation(extent = {{80, -60}, {60, -40}})));
  NonElectrical.Continuous.SimpleLagLim simpleLagLim(K = K_A, T = T_A, y_start = VR0, outMax = V_RMAX0, outMin = V_RMIN0) annotation(Placement(transformation(extent = {{60, -10}, {80, 10}})));
  BaseClasses.RotatingExciter rotatingExciter(T_E = T_E, K_E = K_E0, E_1 = E_1, E_2 = E_2, S_EE_1 = S_EE_1, S_EE_2 = S_EE_2, Efd0 = Efd0) annotation(Placement(transformation(extent = {{140, -10}, {160, 10}})));
  NonElectrical.Continuous.SimpleLag TransducerDelay(K = 1, T = T_R, y_start = ECOMP0) annotation(Placement(transformation(extent = {{-170, -10}, {-150, 10}})));
  Modelica.Blocks.Math.Add Limiters annotation(Placement(transformation(extent = {{-10, -10}, {10, 10}}, rotation = 90, origin = {-100, -150})));
protected
  parameter Real V_RMAX0(fixed = false);
  parameter Real V_RMIN0(fixed = false);
  parameter Real K_E0(fixed = false);
  parameter Real Efd0(fixed = false);
  parameter Real SE_Efd0(fixed = false);
  parameter Real VR0(fixed = false);
initial equation
  SE_Efd0 = iPSL.NonElectrical.Functions.SE(EFD0, S_EE_1, S_EE_2, E_1, E_2);
  (V_RMAX0, V_RMIN0, K_E0) = calculate_dc_exciter_params(V_RMAX, V_RMIN, K_E, E_2, S_EE_2, Efd0, SE_Efd0);
  VR0 = Efd0 * (K_E0 + SE_Efd0);
  V_REF = VR0 / K_A + ECOMP0;
equation
  connect(add3_1.y, add.u1) annotation(Line(points = {{-39, 0}, {-33.65, 0}, {-33.65, 6}, {-22, 6}}, color = {0, 0, 127}));
  connect(simpleLag.y, add.u2) annotation(Line(points = {{-1, -50}, {-34, -50}, {-34, -6}, {-22, -6}}, color = {0, 0, 127}));
  connect(simpleLag.u, derivativeLag.y) annotation(Line(points = {{22, -50}, {59, -50}}, color = {0, 0, 127}));
  connect(add.y, simpleLagLim.u) annotation(Line(points = {{1, 0}, {58, 0}}, color = {0, 0, 127}));
  connect(rotatingExciter.EFD, EFD) annotation(Line(points = {{161.25, 0}, {210, 0}}, color = {0, 0, 127}));
  connect(ECOMP, TransducerDelay.u) annotation(Line(points = {{-200, 0}, {-172, 0}, {-172, 0}}, color = {0, 0, 127}));
  connect(TransducerDelay.y, DiffV.u2) annotation(Line(points = {{-149, 0}, {-132, 0}, {-132, -6}, {-122, -6}}, color = {0, 0, 127}));
  connect(simpleLagLim.y, rotatingExciter.I_C) annotation(Line(points = {{81, 0}, {122, 0}, {138.75, 0}}, color = {0, 0, 127}));
  connect(DiffV.y, add3_1.u2) annotation(Line(points = {{-99, 0}, {-62, 0}, {-62, 0}}, color = {0, 0, 127}));
  connect(VOTHSG, add3_1.u1) annotation(Line(points = {{-200, 90}, {-80, 90}, {-80, 8}, {-62, 8}}, color = {0, 0, 127}));
  connect(VUEL, Limiters.u1) annotation(Line(points = {{-130, -200}, {-130, -172}, {-106, -172}, {-106, -162}}, color = {0, 0, 127}));
  connect(Limiters.u2, VOEL) annotation(Line(points = {{-94, -162}, {-94, -172}, {-70, -172}, {-70, -200}}, color = {0, 0, 127}));
  connect(Limiters.y, add3_1.u3) annotation(Line(points = {{-100, -139}, {-100, -139}, {-100, -20}, {-80, -20}, {-80, -8}, {-62, -8}}, color = {0, 0, 127}));
  connect(derivativeLag.u, rotatingExciter.I_C) annotation(Line(points = {{82, -50}, {120, -50}, {120, 0}, {138.75, 0}}, color = {0, 0, 127}));
  annotation(Diagram(coordinateSystem(extent = {{-200, -200}, {200, 160}}, initialScale = 0.1)), Icon(coordinateSystem(preserveAspectRatio = true, extent = {{-200, -200}, {200, 160}}, initialScale = 0.1), graphics = {Text(extent = {{-100, 152}, {100, 92}}, lineColor = {28, 108, 200}, textString = "IEEET2")}), Documentation(revisions = "<html>
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
</html>", info = "<html>
<table cellspacing=\"1\" cellpadding=\"1\" border=\"1\"><tr>
<td align=center  width=50%><p>Development level</p></td>
<td align=center width=25% bgcolor=cyan><p> 3 </p></td>
</tr> 
</table> 
<p></p>
<table cellspacing=\"1\" cellpadding=\"1\" border=\"1\">
<tr>
<td><p>Reference</p></td>
<td>PSS/E Manual</td>
</tr>
<tr>
<td><p>Last update</p></td>
<td>2016-04-29</td>
</tr>
<tr>
<td><p>Author</p></td>
<td><p>Tin Rabuzin,SmarTS Lab, KTH Royal Institute of Technology</p></td>
</tr>
<tr>
<td><p>Contact</p></td>
<td><p><a href=\"mailto:luigiv@kth.se\">luigiv@kth.se</a></p></td>
</tr>
</table>
</html>"));
end IEEET2;
