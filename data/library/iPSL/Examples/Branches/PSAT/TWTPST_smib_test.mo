within iPSL.Examples.Branches.PSAT;

model TWTPST_smib_test
  extends Modelica.Icons.Example;
  iPSL.Electrical.Machines.PSAT.Order2 order2_1(Sn = 100, D = 5, V_0 = 1, angle_0 = 0, Vn = 13800, V_b = 13800, P_0 = 0.050249405357958, Q_0 = 0.010496891745129, ra = 0.001, xd1 = 0.302, M = 10) annotation(
    Placement(transformation(extent = {{-122, -10}, {-102, 10}})));
  iPSL.Electrical.Loads.PSAT.LOADPQ lOADPQ2(Q_0 = 0.001, P_0 = 0.03) annotation(
    Placement(visible = true, transformation(extent = {{10, -46}, {30, -26}}, rotation = 0)));
  iPSL.Electrical.Branches.PSAT.TwoWindingTransformer twoWindingTransformer(Vn = 13800, x = 0.1, r = 0.01, V_b = 13800, kT = 13.8 / 20) annotation(
    Placement(transformation(extent = {{-80, -10}, {-60, 10}})));
  iPSL.Electrical.Buses.InfiniteBus infiniteBus(V_0 = 1, angle_0 = 0) annotation(
    Placement(transformation(extent = {{-124, -44}, {-104, -24}})));
  iPSL.Electrical.Branches.PwLine pwLine(R = 0.01, X = 0.1, G = 0, B = 0.001 / 2) annotation(
    Placement(transformation(extent = {{-22, -4}, {-2, 16}})));
  iPSL.Electrical.Branches.PwLine pwLine1(R = 0.01, X = 0.1, G = 0, B = 0.001) annotation(
    Placement(transformation(extent = {{-22, -22}, {-2, -2}})));
  iPSL.Electrical.Branches.PwLine pwLine2(R = 0.01, X = 0.1, G = 0, B = 0.001 / 2) annotation(
    Placement(transformation(extent = {{52, 0}, {72, 20}})));
  iPSL.Electrical.Branches.PSAT.PhaseShiftingTransformer.PSTransformer pSTransformer annotation(
    Placement(transformation(extent = {{54, -22}, {74, -2}})));
  iPSL.Electrical.Loads.PSAT.LOADPQ_variation lOADPQ1(P_0 = 0.02, Q_0 = 0.01, dP1 = 0.01, dP2 = -0.01, dQ1 = 0.005, dQ2 = -0.005, t_end_1 = 7, t_end_2 = 9, t_start_1 = 5, t_start_2 = 7) annotation(
    Placement(visible = true, transformation(origin = {128, 0},extent = {{-10, -10}, {10, 10}}, rotation = 90)));
  iPSL.Electrical.Buses.Bus bus1 annotation(
    Placement(transformation(extent = {{-100, -10}, {-80, 10}})));
  iPSL.Electrical.Buses.Bus bus2 annotation(
    Placement(transformation(extent = {{-56, -10}, {-36, 10}})));
  iPSL.Electrical.Buses.Bus bus3 annotation(
    Placement(transformation(extent = {{16, -10}, {36, 10}})));
  iPSL.Electrical.Buses.Bus bus4 annotation(
    Placement(transformation(extent = {{94, -10}, {114, 10}})));
  inner iPSL.Electrical.SystemBase SysData annotation(
    Placement(transformation(extent = {{-40, 60}, {2, 80}})));
equation
  connect(bus4.p, lOADPQ1.p) annotation(
    Line(points = {{104, 0}, {117, 0}}, color = {0, 0, 255}));
  connect(lOADPQ2.p, pwLine1.n) annotation(
    Line(points = {{20, -25}, {20, -12}, {-0.333333, -12}}, color = {0, 0, 255}));
  connect(pwLine.n, pwLine1.n) annotation(
    Line(points = {{-0.333333, 6}, {10, 6}, {10, -12}, {-0.333333, -12}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(pwLine.p, pwLine1.p) annotation(
    Line(points = {{-23.6667, 6}, {-32, 6}, {-32, -12}, {-23.6667, -12}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(infiniteBus.p, order2_1.p) annotation(
    Line(points = {{-125, -34}, {-98, -34}, {-98, 0.04964}, {-101, 0.04964}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(pSTransformer.n, pwLine2.n) annotation(
    Line(points = {{75, -12}, {82, -12}, {82, -14}, {94, -14}, {94, -4}, {92, -4}, {92, 10}, {73.6667, 10}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(bus4.p, pwLine2.n) annotation(
    Line(points = {{104, 0}, {92, 0}, {92, 10}, {73.6667, 10}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(pwLine2.p, bus3.p) annotation(
    Line(points = {{50.3333, 10}, {40, 10}, {40, 0}, {26, 0}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(pSTransformer.p, bus3.p) annotation(
    Line(points = {{52.8, -11.8}, {40, -11.8}, {40, 0}, {26, 0}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(bus3.p, pwLine1.n) annotation(
    Line(points = {{26, 0}, {10, 0}, {10, -12}, {-0.333333, -12}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(twoWindingTransformer.n, bus2.p) annotation(
    Line(points = {{-59, 0}, {-46, 0}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(twoWindingTransformer.p, bus1.p) annotation(
    Line(points = {{-81, 0}, {-90, 0}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(bus1.p, order2_1.p) annotation(
    Line(points = {{-90, 0}, {-98, 0}, {-98, 0.04964}, {-101, 0.04964}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(bus2.p, pwLine1.p) annotation(
    Line(points = {{-46, 0}, {-32, 0}, {-32, -12}, {-23.6667, -12}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(pSTransformer.pk, pSTransformer.u) annotation(
    Line(points = {{75, -16}, {80, -16}, {80, 0}, {46, 0}, {46, -6.4}, {51.6, -6.4}}, color = {0, 0, 127}, smooth = Smooth.None));
  connect(order2_1.vf0, order2_1.vf) annotation(
    Line(points = {{-120, 11}, {-120, 14}, {-130, 14}, {-130, 5}, {-122, 5}}, color = {0, 0, 127}));
  connect(order2_1.pm, order2_1.pm0) annotation(
    Line(points = {{-122, -5}, {-130, -5}, {-130, -16}, {-120, -16}, {-120, -11}}, color = {0, 0, 127}));
  annotation(
    Diagram(coordinateSystem(preserveAspectRatio = false, extent = {{-140, -100}, {140, 100}})),
    Documentation(revisions = "<html>
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
</html>"),
    Icon(coordinateSystem(extent = {{-100, -100}, {100, 100}})));
end TWTPST_smib_test;
