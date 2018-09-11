within iPSL.Examples.Branches.PSAT;

model PhaseShiftingTransformer_Test
  extends Modelica.Icons.Example;
  iPSL.Electrical.Loads.PSAT.LOADPQ lOADPQ2(Q_0 = 0.001, P_0 = 0.03) annotation(
    Placement(transformation(extent = {{34, -44}, {54, -24}})));
  iPSL.Electrical.Branches.PwLine pwLine(R = 0.01, X = 0.1, G = 0, B = 0.001 / 2) annotation(
    Placement(transformation(extent = {{-24, 0}, {-4, 20}})));
  iPSL.Electrical.Branches.PwLine pwLine1(R = 0.01, X = 0.1, G = 0, B = 0.001) annotation(
    Placement(transformation(extent = {{-22, -20}, {-2, 0}})));
  iPSL.Electrical.Branches.PwLine pwLine2(R = 0.01, X = 0.1, G = 0, B = 0.001 / 2) annotation(
    Placement(transformation(extent = {{52, 0}, {72, 20}})));
  iPSL.Electrical.Branches.PSAT.PhaseShiftingTransformer.PSTransformer pSTransformer annotation(
    Placement(transformation(extent = {{54, -22}, {74, -2}})));
  iPSL.Electrical.Loads.PSAT.LOADPQ_variation lOADPQ1(P_0 = 0.02, Q_0 = 0.01, dP1 = 0.02, dP2 = -0.02, dQ1 = 0.01, dQ2 = -0.01,  t_end_1 = 7, t_end_2 = 9, t_start_1 = 5, t_start_2 = 8) annotation(
    Placement(transformation(extent = {{110, -10}, {130, 10}})));
  Modelica.Blocks.Sources.Sine sine1(amplitude = 0.001, freqHz = 0.2) annotation(
    Placement(visible = true, transformation(origin = {-135.783, 37.4652}, extent = {{-4.4802, -4.4802}, {4.4802, 4.4802}}, rotation = 0)));
  Modelica.Blocks.Math.Add add2(k2 = -1) annotation(
    Placement(visible = true, transformation(origin = {-113.106, 30.6621}, extent = {{-6.3229, -6.3229}, {6.3229, 6.3229}}, rotation = 0)));
  Modelica.Blocks.Sources.Sine sine2(amplitude = 0.001, freqHz = 0.2, startTime = 5) annotation(
    Placement(visible = true, transformation(origin = {-135.382, 20.4802}, extent = {{-4.4802, -4.4802}, {4.4802, 4.4802}}, rotation = 0)));
  iPSL.Electrical.Machines.PSAT.Order2b Gen1(D = 5, Sn = 100, Vn = 13800, V_b = 13800, V_0 = 1, P_0 = 0.050249405357958, Q_0 = 0.010496891745129, angle_0 = 0, ra = 0.001, xd1 = 0.302, M = 10) annotation(
    Placement(transformation(extent = {{-101, -11}, {-81, 9}})));
  Modelica.Blocks.Math.Add add annotation(
    Placement(transformation(extent = {{-97, 20}, {-77, 40}})));
  iPSL.Electrical.Branches.PSAT.TwoWindingTransformer twoWindingTransformer(Vn = 13800, x = 0.1, r = 0.01, V_b = 13800, kT = 13.8 / 20) annotation(
    Placement(transformation(extent = {{-60, -10}, {-40, 10}})));
  iPSL.Electrical.Buses.Bus B1 annotation(
    Placement(transformation(extent = {{-78, -10}, {-58, 10}})));
  iPSL.Electrical.Buses.Bus B2 annotation(
    Placement(transformation(extent = {{-42, -10}, {-22, 10}})));
  iPSL.Electrical.Buses.Bus B3 annotation(
    Placement(transformation(extent = {{14, -10}, {34, 10}})));
  iPSL.Electrical.Buses.Bus B4 annotation(
    Placement(transformation(extent = {{88, -10}, {108, 10}})));
  inner iPSL.Electrical.SystemBase SysData annotation(
    Placement(transformation(extent = {{-40, 60}, {2, 80}})));
equation
  connect(pwLine.n, pwLine1.n) annotation(
    Line(points = {{-2.33333, 10}, {10, 10}, {10, -10}, {-0.333333, -10}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(pwLine.p, pwLine1.p) annotation(
    Line(points = {{-25.6667, 10}, {-26, 10}, {-26, -10}, {-23.6667, -10}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(pSTransformer.pk, pSTransformer.u) annotation(
    Line(points = {{75, -16}, {84, -16}, {84, 4}, {44, 4}, {44, -6.4}, {51.6, -6.4}}, color = {0, 0, 127}, smooth = Smooth.None));
  connect(pSTransformer.n, pwLine2.n) annotation(
    Line(points = {{75, -12}, {92, -12}, {92, 10}, {73.6667, 10}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(sine1.y, add2.u1) annotation(
    Line(points = {{-130.855, 37.4652}, {-127.427, 37.4652}, {-127.427, 34.4558}, {-120.693, 34.4558}}, color = {0, 0, 127}, smooth = Smooth.None));
  connect(sine2.y, add2.u2) annotation(
    Line(points = {{-130.454, 20.4802}, {-127.227, 20.4802}, {-127.227, 26.8684}, {-120.693, 26.8684}}, color = {0, 0, 127}, smooth = Smooth.None));
  connect(add2.y, add.u1) annotation(
    Line(points = {{-106.151, 30.6621}, {-102.076, 30.6621}, {-102.076, 36}, {-99, 36}}, color = {0, 0, 127}, smooth = Smooth.None));
  connect(add.y, Gen1.vf) annotation(
    Line(points = {{-76, 30}, {-72, 30}, {-72, 14}, {-118, 14}, {-118, 4}, {-101, 4}}, color = {0, 0, 127}, smooth = Smooth.None));
  connect(Gen1.pm0, Gen1.pm) annotation(
    Line(points = {{-99, -12}, {-72, -12}, {-72, 8}, {-70, 8}, {-70, -22}, {-108, -22}, {-108, -6}, {-101, -6}}, color = {0, 0, 127}, smooth = Smooth.None));
  connect(Gen1.vf0, add.u2) annotation(
    Line(points = {{-99, 10}, {-78, 10}, {-78, 18}, {-106, 18}, {-106, 24}, {-99, 24}}, color = {0, 0, 127}, smooth = Smooth.None));
  connect(Gen1.p, B1.p) annotation(
    Line(points = {{-80, -0.95036}, {-74, -0.95036}, {-74, 0}, {-68, 0}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(B1.p, twoWindingTransformer.p) annotation(
    Line(points = {{-68, 0}, {-61, 0}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(twoWindingTransformer.n, B2.p) annotation(
    Line(points = {{-39, 0}, {-32, 0}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(B2.p, pwLine1.p) annotation(
    Line(points = {{-32, 0}, {-26, 0}, {-26, -10}, {-23.6667, -10}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(B3.p, pwLine1.n) annotation(
    Line(points = {{24, 0}, {10, 0}, {10, -10}, {-0.333333, -10}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(B3.p, lOADPQ2.p) annotation(
    Line(points = {{24, 0}, {44, 0}, {44, -23}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(pwLine2.p, lOADPQ2.p) annotation(
    Line(points = {{50.3333, 10}, {40, 10}, {40, 0}, {44, 0}, {44, -23}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(pSTransformer.p, lOADPQ2.p) annotation(
    Line(points = {{52.8, -11.8}, {40, -11.8}, {40, 0}, {44, 0}, {44, -23}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(B4.p, lOADPQ1.p) annotation(
    Line(points = {{98, 0}, {110, 0}, {110, 11}, {120, 11}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(B4.p, pwLine2.n) annotation(
    Line(points = {{98, 0}, {92, 0}, {92, 10}, {73.6667, 10}}, color = {0, 0, 255}, smooth = Smooth.None));
  annotation(
    Diagram(coordinateSystem(preserveAspectRatio = true, extent = {{-140, -100}, {140, 100}}), graphics),
    Icon(coordinateSystem(extent = {{-100, -100}, {100, 100}})),
    Documentation(info = "<html>
<table cellspacing=\"1\" cellpadding=\"1\" border=\"1\"><tr>
<td><p>Reference</p></td>
<td><p>KTH own Model, PSAT Manual 2.1.8</p></td>
</tr>
<tr>
<td><p>Last update</p></td>
<td><p>29/09/2015</p></td>
</tr>
<tr>
<td><p>Author</p></td>
<td><p>MAA Murad, SmarTS Lab, KTH Royal Institute of Technology</p></td>
</tr>
<tr>
<td><p>Contact</p></td>
<td><p><a href=\"mailto:luigiv@kth.se\">luigiv@kth.se</a></p></td>
</tr>
</table>
</html>", revisions = "<html>
<!--DISCLAIMER-->
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
</html>"));
end PhaseShiftingTransformer_Test;
