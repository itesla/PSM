within iPSL.Examples.Machines.PSAT;
model InductiveMotorI_SIMBOpenline_Test "Order 1 inductive machine from PSAT, line opened at 2 s for 1 s"
  extends Modelica.Icons.Example;

  iPSL.Electrical.Machines.PSAT.MotorTypeI motorTypeI(
    Sup=0,
    V_0=1.0336,
    angle_0=-0.02173,
    P_0=0.5,
    Q_0=0.286) annotation (Placement(transformation(
        extent={{-27,-22},{27,22}},
        rotation=180,
        origin={65,-16})));
  iPSL.Electrical.Branches.PwLine pwLine1(
    G=0,
    B=0,
    R=0.01,
    X=0.1) annotation (Placement(transformation(
        extent={{-25,-16},{25,16}},
        rotation=180,
        origin={-9,-38})));
  iPSL.Electrical.Branches.PwLine2Openings pwLine2(
    G=0,
    B=0,
    R=0.01,
    X=0.1,
    startTime=2,
    endTime=3) annotation (Placement(transformation(
        extent={{-25,-16},{25,16}},
        rotation=180,
        origin={-9,4})));
  iPSL.Electrical.Buses.InfiniteBus infiniteBus(V_0=1.05, angle_0=0) annotation (Placement(transformation(
        extent={{-16.5,-13.5},{16.5,13.5}},
        rotation=0,
        origin={-86.5,-15.5})));
  inner iPSL.Electrical.SystemBase SysData annotation (Placement(transformation(extent={{40,60},{64,80}})));
equation
  connect(infiniteBus.p, pwLine2.n) annotation(Line(points = {{-104.65, -15.5}, {-48.175, -15.5}, {-48.175, 4}, {-26.5, 4}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(pwLine1.n, infiniteBus.p) annotation(Line(points = {{-38.1667, -38}, {-48, -38}, {-48, -15.5}, {-104.65, -15.5}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(pwLine2.p, motorTypeI.p) annotation(Line(points = {{8.5, 4}, {26, 4}, {26, -16}, {42.32, -16}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(pwLine1.p, motorTypeI.p) annotation(Line(points = {{20.1667, -38}, {26, -38}, {26, -16}, {42.32, -16}}, color = {0, 0, 255}, smooth = Smooth.None));
  annotation (Diagram(coordinateSystem(preserveAspectRatio=true, extent={{-100,-100},{100,100}}), graphics), Documentation(revisions="<html>
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
end InductiveMotorI_SIMBOpenline_Test;
