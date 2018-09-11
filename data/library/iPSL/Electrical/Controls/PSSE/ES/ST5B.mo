within iPSL.Electrical.Controls.PSSE.ES;

model ST5B "IEEE 421.5 2005 ST5B Excitation System"
  extends iPSL.Electrical.Controls.PSSE.ES.BaseClasses.BaseExciter;
  parameter Real T_R = 0.025 "Regulator input filter time constant (s)";
  parameter Real T_C1 = 0.1 "Lead time constant of first lead-lag block (voltage regulator channel) (s)";
  parameter Real T_B1 = 0.2 "Lag time constant of first lead-lag block (voltage regulator channel) (s)";
  parameter Real T_C2 = 1 "Lead time constant of second lead-lag block (voltage regulator channel) (s)";
  parameter Real T_B2 = 1 "Lag time constant of second lead-lag block (voltage regulator channel) (s)";
  parameter Real K_R = 1 "Voltage regulator gain (pu)";
  parameter Real V_RMAX = 10 "Voltage regulator maximum limit (pu)";
  parameter Real V_RMIN = -10 "Voltage regulator minimum limit (pu)";
  parameter Real T_1 = 0.58 "voltage regulator time constant (s)";
  parameter Real K_C = 0.3 "(pu)";
  parameter Real T_UC1 = 1 "Lead time constant of first lead-lag block (under- excitation channel) (s)";
  parameter Real T_UB1 = 1 "Lag time constant of first lead-lag block (under- excitation channel) (s)";
  parameter Real T_UC2 = 1 "Lead time constant of second lead-lag block (under- excitation channel) (s)";
  parameter Real T_UB2 = 1 "Lag time constant of second lead-lag block (under- excitation channel) (s)";
  parameter Real T_OC1 = 1 "Lead time constant of first lead-lag block (over- excitation channel) (s)";
  parameter Real T_OB1 = 1 "Lag time constant of first lead-lag block (over- excitation channel) (s)";
  parameter Real T_OC2 = 1 "Lead time constant of second lead-lag block (over- excitation channel) (s)";
  parameter Real T_OB2 = 1 "Lag time constant of second lead-lag block (over- excitation channel) (s)";
  Modelica.Blocks.Math.Add VERR1 annotation(Placement(transformation(extent = {{-10, 10}, {10, -10}}, rotation = 0, origin = {-10, 0})));
  NonElectrical.Logical.LV_GATE lV_Gate annotation(Placement(transformation(extent = {{-48, 0}, {-26, 12}})));
  NonElectrical.Logical.HV_GATE hV_Gate annotation(Placement(transformation(extent = {{-84, -4}, {-58, 10}})));
  NonElectrical.Continuous.LeadLagLim imLimitedLeadLag(K = 1, outMax = V_RMAX / K_R, T1 = T_C1, T2 = T_B1, outMin = V_RMIN / K_R, y_start = VR0 / K_R) annotation(Placement(transformation(extent = {{20, -10}, {40, 10}})));
  NonElectrical.Continuous.LeadLagLim imLimitedLeadLag2(K = 1, outMax = V_RMAX / K_R, T1 = T_C2, T2 = T_B2, outMin = V_RMIN / K_R, y_start = VR0 / K_R) annotation(Placement(transformation(extent = {{50, -10}, {70, 10}})));
  Modelica.Blocks.Math.Gain K_r(k = K_R) annotation(Placement(transformation(extent = {{-10, -9.5}, {10, 9.5}}, rotation = 0, origin = {90, 0.5})));
  Modelica.Blocks.Nonlinear.Limiter limiter(uMax = V_RMAX, uMin = V_RMIN) annotation(Placement(transformation(extent = {{108, -10}, {128, 10}})));
  Modelica.Blocks.Math.Add VERR2(k1 = -1, k2 = 1) annotation(Placement(transformation(extent = {{-10, 10}, {10, -10}}, rotation = 0, origin = {150, 0})));
  Modelica.Blocks.Math.Gain K_c(k = K_C) annotation(Placement(transformation(extent = {{-10, -10}, {10, 10}}, rotation = 90, origin = {130, -92})));
  NonElectrical.Continuous.SimpleLagLimVar simpleLagLimVar(K = 1, T = T_1, y_start = Efd0) annotation(Placement(transformation(extent = {{174, -10}, {194, 10}})));
  Modelica.Blocks.Math.Gain high(k = V_RMAX) annotation(Placement(transformation(extent = {{-10, -10}, {10, 10}}, rotation = 0, origin = {106, -30})));
  Modelica.Blocks.Math.Gain low(k = V_RMIN) annotation(Placement(transformation(extent = {{-10, -10}, {10, 10}}, rotation = 0, origin = {104, -70})));
  NonElectrical.Continuous.SimpleLag TransducerDelay(K = 1, T = T_R, y_start = ECOMP0) annotation(Placement(transformation(extent = {{-170, -10}, {-150, 10}})));
  Modelica.Blocks.Interfaces.RealInput XADIFD annotation(Placement(transformation(extent = {{-20, -20}, {20, 20}}, rotation = 90, origin = {130, -140}), iconTransformation(extent = {{-10, -10}, {10, 10}}, origin = {-200, -60})));
protected
  parameter Real VR0(fixed = false);
initial equation
  VR0 = Efd0 + K_C * XADIFD;
  V_REF = VR0 / K_R + ECOMP;
equation
  connect(VERR1.y, imLimitedLeadLag.u) annotation(
    Line(points = {{1, 0}, {1, 0}, {18, 0}}, color = {0, 0, 127}));
  connect(imLimitedLeadLag.y, imLimitedLeadLag2.u) annotation(
    Line(points = {{41, 0}, {41, 0}, {48, 0}}, color = {0, 0, 127}));
  connect(VERR2.y, simpleLagLimVar.u) annotation(
    Line(points = {{161, 0}, {161, 0}, {172, 0}}, color = {0, 0, 127}));
  connect(low.y, simpleLagLimVar.outMin) annotation(
    Line(points = {{115, -70}, {176, -70}, {176, -14}}, color = {0, 0, 127}));
  connect(high.y, simpleLagLimVar.outMax) annotation(
    Line(points = {{117, -30}, {168, -30}, {168, 32}, {192, 32}, {192, 14}}, color = {0, 0, 127}));
  connect(K_c.y, VERR2.u1) annotation(
    Line(points = {{130, -81}, {130, -81}, {130, -6}, {138, -6}}, color = {0, 0, 127}));
  connect(imLimitedLeadLag2.y, K_r.u) annotation(
    Line(points = {{71, 0}, {78, 0}, {78, 0.5}}, color = {0, 0, 127}));
  connect(lV_Gate.p, VERR1.u2) annotation(
    Line(points = {{-27.375, 6}, {-22, 6}}, color = {0, 0, 127}));
  connect(hV_Gate.p, lV_Gate.n2) annotation(
    Line(points = {{-59.625, 3}, {-54.8125, 3}, {-54.8125, 3}, {-49.375, 3}}, color = {0, 0, 127}));
  connect(hV_Gate.n2, DiffV.y) annotation(
    Line(points = {{-85.625, -0.5}, {-91.8125, -0.5}, {-91.8125, 0}, {-99, 0}}, color = {0, 0, 127}));
  connect(hV_Gate.n1, VUEL) annotation(
    Line(points = {{-85.625, 6.5}, {-92, 6.5}, {-92, -160}, {-130, -160}, {-130, -200}}, color = {0, 0, 127}));
  connect(lV_Gate.n1, VOEL) annotation(
    Line(points = {{-49.375, 9}, {-54, 9}, {-54, -160}, {-70, -160}, {-70, -200}}, color = {0, 0, 127}));
  connect(K_r.y, limiter.u) annotation(
    Line(points = {{101, 0.5}, {104.5, 0.5}, {104.5, 0}, {106, 0}}, color = {0, 0, 127}));
  connect(limiter.y, VERR2.u2) annotation(
    Line(points = {{129, 0}, {132, 0}, {132, 6}, {138, 6}}, color = {0, 0, 127}));
  connect(simpleLagLimVar.y, EFD) annotation(
    Line(points = {{195, 0}, {210, 0}, {210, 0}}, color = {0, 0, 127}));
  connect(ECOMP, TransducerDelay.u) annotation(
    Line(points = {{-200, 0}, {-172, 0}, {-172, 0}}, color = {0, 0, 127}));
  connect(TransducerDelay.y, DiffV.u2) annotation(
    Line(points = {{-149, 0}, {-132, 0}, {-132, -6}, {-122, -6}}, color = {0, 0, 127}));
  connect(high.u, TransducerDelay.u) annotation(
    Line(points = {{94, -30}, {-178, -30}, {-178, 0}, {-172, 0}}, color = {0, 0, 127}));
  connect(low.u, TransducerDelay.u) annotation(
    Line(points = {{92, -70}, {80, -70}, {80, -30}, {-178, -30}, {-178, 0}, {-172, 0}}, color = {0, 0, 127}));
  connect(VOTHSG, VERR1.u1) annotation(
    Line(points = {{-200, 90}, {-26, 90}, {-26, -6}, {-22, -6}}, color = {0, 0, 127}));
  connect(XADIFD, K_c.u) annotation(
    Line(points = {{130, -140}, {130, -104}}, color = {0, 0, 127}));
  annotation(Diagram(coordinateSystem(preserveAspectRatio = true, extent = {{-200, -200}, {200, 160}}, grid = {2, 2})), Icon(coordinateSystem(extent = {{-200, -200}, {200, 160}}, preserveAspectRatio = true, grid = {2, 2}), graphics = {Text(extent = {{-60, 154}, {62, 110}}, lineColor = {28, 108, 200}, textString = "ST5B"), Text(extent = {{-184, -52}, {-114, -72}}, lineColor = {28, 108, 200}, textString = "XADIFD")}), Documentation(info="<html>
<table cellspacing=\"1\" cellpadding=\"1\" border=\"1\"><tr>
<td align=center  width=50%><p>Development level</p></td>
<td align=center width=25% bgcolor=cyan><p> 3 </p></td>
</tr> 
</table> 
<p></p></html>", revisions = "<html>
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
", info = "<html>
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
end ST5B;
