within iPSL.Examples.Machines.PSAT;
model Order4test2
  extends Modelica.Icons.Example;

  extends iPSL.Examples.BaseTest(pwLine1.startTime = 3, pwLine1.endTime = 3.15);
  iPSL.Electrical.Machines.PSAT.Order4 Generator(
    Sn=100,
    Vn=20,
    V_b=400,
    V_0=1,
    angle_0=0,
    ra=0.001,
    M=10,
    D=0,
    xd1=0.302,
    P_0=16.0352698692006,
    Q_0=11.859436505981) annotation (Placement(visible = true, transformation(extent = {{-65, -15}, {-35, 15}}, rotation = 0)));
equation
  connect(Generator.pm0, Generator.pm) annotation(
    Line(points = {{-62, -16}, {-62, -16}, {-62, -28}, {-79, -28}, {-79, -5}, {-63, -5}, {-63, -5}}, color = {0, 0, 127}));
  connect(Generator.p, bus.p) annotation(
    Line(points = {{-33.5, 0}, {0, 0}}, color = {0, 0, 255}));
  connect(Generator.vf0, Generator.vf) annotation(
    Line(points = {{-62, 16.5}, {-62, 20}, {-70, 20}, {-70, 7.5}, {-65, 7.5}}, color = {0, 0, 127}));
  annotation (
    Diagram(coordinateSystem(
        extent={{-100,-100},{100,100}},
        preserveAspectRatio=false,
        initialScale=0.1,
        grid={1,1})),
    experiment(StopTime=20),
    __Dymola_experimentSetupOutput,
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
end Order4test2;
