within iPSL.Examples.Machines.PSAT;

model Order3test2_AVR
  extends Modelica.Icons.Example;

  extends iPSL.Examples.BaseTest;
  iPSL.Electrical.Machines.PSAT.Order3 order3_Inputs_Outputs1(
    V_b=400,
    V_0=1,
    angle_0=0,
    Sn=20,
    Vn=400,
    ra=0.01,
    xd1=0.302,
    M=10,
    D=0,
    xd=1.9,
    Td10=8,
    xq=1.7,
    P_0=16.0352698692006,
    Q_0=11.859436505981) annotation (Placement(visible=true, transformation(
        origin={-31.6887,-10.7513},
        extent={{-10.0,-10.0},{10.0,10.0}},
        rotation=0)));
  iPSL.Electrical.Controls.PSAT.AVR.AVRtypeIII AVRtypeIII1 annotation (Placement(visible=true, transformation(
        origin={-70,-5.99998},
        extent={{-10,-10},{10,10}},
        rotation=0)));
  Modelica.Blocks.Sources.Constant const(k=0) annotation (Placement(transformation(extent={{-99,-15},{-89,-5}})));
equation
  connect(AVRtypeIII1.vf0, order3_Inputs_Outputs1.vf0) annotation(
    Line(points = {{-70, 3}, {-70, 12}, {-39.6887, 12}, {-39.6887, 0.2487}}, color = {0, 0, 127}));
  connect(const.y, AVRtypeIII1.vs) annotation(
    Line(points = {{-88.5, -10}, {-79, -10}, {-79, -11}}, color = {0, 0, 127}));
  connect(AVRtypeIII1.v, order3_Inputs_Outputs1.v) annotation(
    Line(points = {{-79, -1}, {-88, -1}, {-88, 10}, {-15, 10}, {-15, -7.7513}, {-20.6887, -7.7513}}, color = {0, 0, 127}));
  connect(AVRtypeIII1.vf, order3_Inputs_Outputs1.vf) annotation(
    Line(points = {{-59, -6}, {-50.5, -6}, {-50.5, -5.7513}, {-41.6887, -5.7513}}, color = {0, 0, 127}));
  connect(order3_Inputs_Outputs1.p, bus.p) annotation (Line(points={{-20.6887,-10.7017},{-10.3444,-10.7017},{-10.3444,0},{0,0}}, color={0,0,255}));
  connect(order3_Inputs_Outputs1.pm0, order3_Inputs_Outputs1.pm) annotation (Line(points={{-39.6887,-21.7513},{-39.6887,-24},{-45,-24},{-45,-15.7513},{-41.6887,-15.7513}}, color={0,0,127}));
  annotation (
    Diagram(coordinateSystem(
        extent={{-100,-100},{100,100}},
        preserveAspectRatio=false,
        initialScale=0.1,
        grid={1,1})),
    Documentation(revisions="<html>
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
end Order3test2_AVR;
