within iPSL.Examples.Controls.PSAT.TG;

model TGTypeIII_test
extends Modelica.Icons.UnderConstruction;
  iPSL.Electrical.Machines.PSAT.Order3 order3_Inputs_Outputs1(P_0 = 0.16041, Q_0 = 0.12012, V_b = 400, V_0 = 1, angle_0 = 0, Sn = 20, Vn = 400, ra = 0.001, xd1 = 0.302, M = 10, D = 0, xd = 1.9, Td10 = 8, xq = 1.7) annotation(
    Placement(visible = true, transformation(origin = {-35.3443, 12.6244}, extent = {{-16.6557, -16.6244}, {16.6557, 16.6244}}, rotation = 0)));
  iPSL.Electrical.Controls.PSAT.TG.TGTypeIII tGTypeIII1(Tg = 0.2, Tp = 0.04, delta = 0.3, sigma = 0.04, Tr = 5, vmin = -0.1, vmax = 0.1, gmax = 1, gmin = 0, Tw = 1, a11 = 0.5, a13 = 1, a21 = 1.5, a23 = 1, int3 = 2.712336, P_0 = 0.1) annotation(
    Placement(transformation(extent = {{-88, -4}, {-66, 14}})));
  iPSL.Electrical.Branches.PwLine pwLine2(B = 0.001 / 2, G = 0, R = 0.01, X = 0.1) annotation(
    Placement(visible = true, transformation(origin = {9.8634, 12.3286}, extent = {{-10.0, -10.0}, {10.0, 10.0}}, rotation = 0)));
  iPSL.Electrical.Branches.PwLine pwLine3(B = 0.001 / 2, G = 0, R = 0.1, X = 1) annotation(
    Placement(visible = true, transformation(origin = {35.5, -14}, extent = {{-10.0, -10.0}, {10.0, 10.0}}, rotation = 0)));
  iPSL.Electrical.Branches.PwLine pwLine1(B = 0.001 / 2, G = 0, R = 0.01, X = 0.1) annotation(
    Placement(visible = true, transformation(origin = {10.863, -14.6714}, extent = {{-10.0, -10.0}, {10.0, 10.0}}, rotation = 0)));
  iPSL.Electrical.Buses.InfiniteBus infiniteBus(V_0 = 1, angle_0 = 0) annotation(
    Placement(visible = true, transformation(origin = {60, -14}, extent = {{-6, -6}, {6, 6}}, rotation = 180)));
  inner iPSL.Electrical.SystemBase SysData annotation(
    Placement(transformation(extent = {{40, 60}, {82, 80}})));
equation
  connect(pwLine3.n, infiniteBus.p) annotation(
    Line(points = {{42.5, -14}, {67, -14}}, color = {0, 0, 255}));
  connect(order3_Inputs_Outputs1.vf0, order3_Inputs_Outputs1.vf) annotation(
    Line(points = {{-48.6689, 30.9112}, {-12, 30.9112}, {-12, 36}, {-62, 36}, {-62, 20.9366}, {-52, 20.9366}}, color = {0, 0, 127}, smooth = Smooth.None));
  connect(order3_Inputs_Outputs1.w, tGTypeIII1.w) annotation(
    Line(points = {{-17.023, 27.5864}, {-10, 27.5864}, {-10, -12}, {-98, -12}, {-98, 5}, {-87.78, 5}}, color = {0, 0, 127}, smooth = Smooth.None));
  connect(tGTypeIII1.Pm, order3_Inputs_Outputs1.pm) annotation(
    Line(points = {{-64.46, 5.36}, {-61.16, 5.36}, {-61.16, 4.3122}, {-52, 4.3122}}, color = {0, 0, 127}, smooth = Smooth.None));
  connect(pwLine2.n, pwLine1.n) annotation(
    Line(points = {{16.8634, 12.3286}, {22, 12.3286}, {22, -14.6714}, {17.863, -14.6714}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(pwLine1.n, pwLine3.p) annotation(
    Line(points = {{17.863, -14.6714}, {28, -14.6714}, {28, -14}, {28.5, -14}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(order3_Inputs_Outputs1.p, pwLine2.p) annotation(
    Line(points = {{-17.023, 12.7069}, {-17.5115, 12.7069}, {-17.5115, 12.3286}, {2.8634, 12.3286}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(pwLine1.p, pwLine2.p) annotation(
    Line(points = {{3.863, -14.6714}, {-4, -14.6714}, {-4, 12.3286}, {2.8634, 12.3286}}, color = {0, 0, 255}, smooth = Smooth.None));
  annotation(
    Diagram(coordinateSystem(preserveAspectRatio = false, extent = {{-100, -100}, {100, 100}}), graphics = {Text(extent = {{44, 66}, {80, 38}}, lineColor = {0, 0, 255}, textStyle = {TextStyle.Bold}, textString = "Wref perturbation")}),
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
</html>"));
end TGTypeIII_test;
