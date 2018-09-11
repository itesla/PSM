within iPSL.Electrical.Controls.PSSE.ES;

model EXST1 "IEEE Type AC2A Excitation System"
  extends iPSL.Electrical.Controls.PSSE.ES.BaseClasses.BaseExciter;
  parameter Real T_R = 0.02;
  parameter Real V_IMAX = 0.2;
  parameter Real V_IMIN = 0;
  parameter Real T_C = 1;
  parameter Real T_B = 1;
  parameter Real K_A = 80;
  parameter Real T_A = 0.05;
  parameter Real V_RMAX = 8;
  parameter Real V_RMIN = -3;
  parameter Real K_C = 0.2;
  parameter Real K_F = 0.1;
  parameter Real T_F = 1;
  iPSL.NonElectrical.Continuous.LeadLag imLeadLag(K = 1, T1 = T_C, T2 = T_B, y_start = Efd0 / K_A, x_start = Efd0 / K_A) annotation(
    Placement(transformation(extent = {{40, -10}, {60, 10}})));
  Modelica.Blocks.Nonlinear.Limiter limiter(uMax = V_IMAX, uMin = V_IMIN) annotation(
    Placement(transformation(extent = {{0, -10}, {20, 10}})));
  NonElectrical.Continuous.SimpleLag Vm1(y_start = Efd0, K = 1, T = T_A) annotation(
    Placement(transformation(extent = {{-10, -10}, {10, 10}}, rotation = 0, origin = {130, 0})));
  Modelica.Blocks.Math.Gain K_a(k = K_A) annotation(
    Placement(transformation(extent = {{-10, -10}, {10, 10}}, rotation = 0, origin = {90, 0})));
  Modelica.Blocks.Continuous.Derivative imDerivativeLag(k = K_F, T = T_F, y_start = 0, initType = Modelica.Blocks.Types.Init.InitialOutput) annotation(
    Placement(transformation(extent = {{92, -70}, {72, -50}})));
  NonElectrical.Continuous.SimpleLag TransducerDelay(K = 1, T = T_R, y_start = ECOMP0) annotation(
    Placement(transformation(extent = {{-170, -10}, {-150, 10}})));
  Modelica.Blocks.Math.Add3 add3_2 annotation(
    Placement(transformation(extent = {{-80, -10}, {-60, 10}})));
  Modelica.Blocks.Math.Add Limiters annotation(
    Placement(transformation(extent = {{-10, -10}, {10, 10}}, rotation = 90, origin = {-100, -150})));
  Modelica.Blocks.Interfaces.RealInput XADIFD annotation(
    Placement(visible = true, transformation(origin = {-200, -70}, extent = {{-20, -20}, {20, 20}}, rotation = 0), iconTransformation(origin = {-190, -70}, extent = {{-10, -10}, {10, 10}}, rotation = 0)));
  Modelica.Blocks.Math.Feedback feedback annotation(
    Placement(transformation(extent = {{-40, -10}, {-20, 10}})));
protected
initial equation
  V_REF = Efd0 / K_A + ECOMP0;
equation
  connect(imDerivativeLag.u, Vm1.y) annotation(
    Line(points = {{94, -60}, {178, -60}, {178, 0}, {142, 0}, {142, 0}}, color = {0, 0, 127}));
  connect(Vm1.y, EFD) annotation(
    Line(points = {{142, 0}, {204, 0}, {204, 0}, {210, 0}}, color = {0, 0, 127}));
//if EFD > ECOMP * V_RMAX - K_C * XADIFD then
//EFD = ECOMP * V_RMAX - K_C * XADIFD;
//elseif EFD < ECOMP * V_RMIN - K_C * XADIFD then
//EFD = ECOMP * V_RMIN - K_C * XADIFD;
//else
// EFD = EFD1;
//end if;
  connect(imLeadLag.y, K_a.u) annotation(
    Line(points = {{61, 0}, {70, 0}, {78, 0}}, color = {0, 0, 127}));
  connect(imLeadLag.u, limiter.y) annotation(
    Line(points = {{38, 0}, {30, 0}, {21, 0}}, color = {0, 0, 127}));
  connect(K_a.y, Vm1.u) annotation(
    Line(points = {{101, 0}, {118, 0}}, color = {0, 0, 127}));
  connect(ECOMP, TransducerDelay.u) annotation(
    Line(points = {{-200, 0}, {-186, 0}, {-172, 0}}, color = {0, 0, 127}));
  connect(TransducerDelay.y, DiffV.u2) annotation(
    Line(points = {{-149, 0}, {-132, 0}, {-132, -6}, {-122, -6}}, color = {0, 0, 127}));
  connect(VOTHSG, add3_2.u1) annotation(
    Line(points = {{-200, 90}, {-148, 90}, {-90, 90}, {-90, 8}, {-82, 8}}, color = {0, 0, 127}));
  connect(DiffV.y, add3_2.u2) annotation(
    Line(points = {{-99, 0}, {-90.5, 0}, {-82, 0}}, color = {0, 0, 127}));
  connect(VUEL, Limiters.u1) annotation(
    Line(points = {{-130, -200}, {-130, -172}, {-106, -172}, {-106, -162}}, color = {0, 0, 127}));
  connect(Limiters.u2, VOEL) annotation(
    Line(points = {{-94, -162}, {-94, -174}, {-70, -174}, {-70, -200}}, color = {0, 0, 127}));
  connect(Limiters.y, add3_2.u3) annotation(
    Line(points = {{-100, -139}, {-100, -139}, {-100, -20}, {-90, -20}, {-90, -8}, {-82, -8}}, color = {0, 0, 127}));
  connect(feedback.y, limiter.u) annotation(
    Line(points = {{-21, 0}, {-11.5, 0}, {-2, 0}}, color = {0, 0, 127}));
  connect(feedback.u1, add3_2.y) annotation(
    Line(points = {{-38, 0}, {-59, 0}, {-59, 0}}, color = {0, 0, 127}));
  connect(feedback.u2, imDerivativeLag.y) annotation(
    Line(points = {{-30, -8}, {-30, -8}, {-30, -60}, {71, -60}}, color = {0, 0, 127}));
  annotation(
    Diagram(coordinateSystem(extent = {{-200, -200}, {200, 160}}, initialScale = 0.1)),
    Icon(coordinateSystem(extent = {{-200, -200}, {200, 160}}, initialScale = 0.1), graphics = {Text(origin = {12, 0}, lineColor = {28, 108, 200}, extent = {{-186, -62}, {-112, -82}}, textString = "XADIFD")}),
    Documentation(revisions = "<html>
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
end EXST1;
