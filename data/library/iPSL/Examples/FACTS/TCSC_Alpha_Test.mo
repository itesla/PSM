within iPSL.Examples.FACTS;
model TCSC_Alpha_Test
  extends Modelica.Icons.Example;
  iPSL.Electrical.Machines.PSAT.Order2 Gen1(
    Sn=370,
    P_0=0.080101913348342,
    Q_0=0.060948619430301,
    D=5,
    angle_0=0,
    V_0=1,
    Vn=400,
    ra=0.001,
    xd1=0.302,
    M=10) annotation (Placement(transformation(extent={{-24,-10},{-4,10}})));
  Modelica.Blocks.Sources.Pulse pulse(
    period=10,
    startTime=4.2,
    amplitude=0.045,
    width=58,
    offset=1.005017533847740,
    nperiod=2) annotation (Placement(transformation(extent={{-126,6},{-106,26}})));
  Modelica.Blocks.Sources.Pulse pulse1(
    period=10,
    amplitude=-0.045,
    width=42,
    startTime=0,
    nperiod=2) annotation (Placement(transformation(extent={{-128,-28},{-108,-8}})));
  Modelica.Blocks.Math.Add3 add3_1(
    k3=0,
    k1=1,
    k2=1) annotation (Placement(transformation(extent={{-78,-10},{-58,10}})));
  iPSL.Electrical.Branches.PwLine pwLine1(
    R=0.01,
    G=0,
    B=0.001/2,
    X=0.1) annotation (Placement(transformation(extent={{56,-10},{76,10}})));
  iPSL.Electrical.Loads.PSAT.LOADPQ_variation lOADPQ(
    P_0=0.08,
    Q_0=0.06,
    t_start_1=2,
    t_end_1=10,
    dP1=0.01,
    dQ1=0.01,
    t_start_2=12,
    t_end_2=20,
    dP2=-0.01,
    dQ2=-0.01) annotation (Placement(visible = true, transformation(origin = {104, 0}, extent = {{-10, -10}, {10, 10}}, rotation = 90)));
  iPSL.Electrical.Controls.PSAT.FACTS.TCSC.TCSCAlpha tCSCAlpha annotation (Placement(transformation(extent={{18,-10},{38,10}})));
  inner iPSL.Electrical.SystemBase SysData annotation (Placement(transformation(extent={{-120,60},{-96,80}})));
equation
  connect(pwLine1.n, lOADPQ.p) annotation (Line(
      points = {{73, 0}, {93, 0}},
      color={0,0,255}));
  connect(pulse1.y, add3_1.u2) annotation (Line(
      points={{-107,-18},{-92,-18},{-92,0},{-80,0}},
      color={0,0,127},
      smooth=Smooth.None));
  connect(pulse.y, add3_1.u1) annotation (Line(
      points={{-105,16},{-94,16},{-94,8},{-80,8}},
      color={0,0,127},
      smooth=Smooth.None));
  connect(add3_1.y, Gen1.vf) annotation (Line(
      points={{-57,0},{-32,0},{-32,5},{-24,5}},
      color={0,0,127},
      smooth=Smooth.None));
  connect(Gen1.vf0, add3_1.u3) annotation (Line(
      points={{-22,11},{2,11},{2,22},{-42,22},{-42,-16},{-86,-16},{-86,-8},{-80,-8}},
      color={0,0,127},
      smooth=Smooth.None));
  connect(Gen1.pm0, Gen1.pm) annotation (Line(
      points={{-22,-11},{4,-11},{4,-20},{-32,-20},{-32,-5},{-24,-5}},
      color={0,0,127},
      smooth=Smooth.None));
  connect(tCSCAlpha.n, pwLine1.p) annotation (Line(
      points={{39,0},{59,0}},
      color={0,0,255},
      smooth=Smooth.None));
  connect(Gen1.p, tCSCAlpha.p) annotation (Line(
      points={{-3,0.04964},{7.5,0.04964},{7.5,0},{17.1,0}},
      color={0,0,255},
      smooth=Smooth.None));
  annotation (
    Diagram(coordinateSystem(preserveAspectRatio=true, extent={{-140,-100},{120,100}}), graphics),
    Icon(coordinateSystem(extent={{-100,-100},{100,100}})),
    Documentation(info="<html>
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
</html>", revisions="<html>
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
end TCSC_Alpha_Test;
