within iPSL.Examples;


partial model BaseTest
  Electrical.Branches.PwLine pwLine1(
    X=0.1,
    R=0.01,
    G=0,
    B=0.0005) annotation (Placement(visible=true, transformation(
        origin={20,-10},
        extent={{-6,-4},{6,4}},
        rotation=0)));
  Electrical.Branches.PwLine pwLinewithOpening1(
    G=0,
    R=0.01,
    X=0.1,
    opening=1,
    B=0.0005,
    startTime=2,
    endTime=2.15) annotation (Placement(visible=true, transformation(
        origin={60,10},
        extent={{-6,-4},{6,4}},
        rotation=0)));
  Electrical.Branches.PwLine pwLine2(
    G=0,
    R=0.01,
    X=0.1,
    B=0.0005) annotation (Placement(visible=true, transformation(
        origin={20,10},
        extent={{-6,-4},{6,4}},
        rotation=0)));
  Electrical.Branches.PwLine pwLine3(
    G=0,
    R=0.01,
    X=0.1,
    B=0.0005) annotation (Placement(visible=true, transformation(
        origin={60,-10},
        extent={{-6,-4.00001},{6,4.00002}},
        rotation=0)));
  Electrical.Branches.PwLine pwLine4(
    G=0,
    R=0.01,
    X=0.1,
    B=0.0005) annotation (Placement(visible=true, transformation(
        origin={60,32},
        extent={{-5.99999,-3.99998},{5.99999,4}},
        rotation=0)));
  Electrical.Loads.PSAT.LOADPQ pwLoadPQ1(
    P_0=8,
    Q_0=6,
    V_0=1,
    angle_0=0) annotation (Placement(visible=true, transformation(
        origin={94,20},
        extent={{-6,-6},{6,6}},
        rotation=90)));
  Electrical.Loads.PSAT.LOADPQ pwLoadPQ2(
    P_0=8,
    Q_0=6,
    V_0=1,
    angle_0=0) annotation (Placement(visible=true, transformation(
        origin={94.0335,-10.2889},
        extent={{-6.2889,-6.0335},{6.2889,6.0335}},
        rotation=90)));
  inner Electrical.SystemBase SysData annotation (Placement(transformation(extent={{-100,80},{-40,100}})));
  Electrical.Buses.Bus bus annotation (Placement(transformation(extent={{-10,-10},{10,10}})));
  Electrical.Buses.Bus bus1 annotation (Placement(transformation(extent={{30,-10},{50,10}})));
  Electrical.Buses.Bus bus2 annotation (Placement(transformation(extent={{70,10},{90,30}})));
  Electrical.Buses.Bus bus3 annotation (Placement(transformation(extent={{70,-20},{90,0}})));
equation
  connect(pwLine2.p, pwLine1.p) annotation (Line(points={{13,10},{8,10},{8,-10},{13,-10}}, color={0,0,255}));
  connect(pwLine2.n, pwLine1.n) annotation (Line(points={{27,10},{32,10},{32,-10},{27,-10}}, color={0,0,255}));
  connect(pwLine4.p, pwLinewithOpening1.p) annotation (Line(points={{53,32},{48,32},{48,10},{53,10}}, color={0,0,255}));
  connect(pwLine4.n, pwLinewithOpening1.n) annotation (Line(points={{67,32},{72,32},{72,10},{67,10}}, color={0,0,255}));
  connect(bus.p, pwLine1.p) annotation (Line(points={{0,0},{8,0},{8,-10},{13,-10}}, color={0,0,255}));
  connect(bus1.p, pwLine1.n) annotation (Line(points={{40,0},{32,0},{32,-10},{27,-10}}, color={0,0,255}));
  connect(bus1.p, pwLinewithOpening1.p) annotation (Line(points={{40,0},{44,0},{44,20},{48,20},{48,10},{53,10}}, color={0,0,255}));
  connect(pwLine3.p, pwLinewithOpening1.p) annotation (Line(points={{53,-10},{44,-10},{44,20},{48,20},{48,10},{53,10}}, color={0,0,255}));
  connect(bus2.p, pwLoadPQ1.p) annotation (Line(points={{80,20},{87.4,20}}, color={0,0,255}));
  connect(bus2.p, pwLinewithOpening1.n) annotation (Line(points={{80,20},{72,20},{72,10},{67,10}}, color={0,0,255}));
  connect(bus3.p, pwLoadPQ2.p) annotation (Line(points={{80,-10},{87.3966,-10},{87.3966,-10.2889}}, color={0,0,255}));
  connect(pwLine3.n, bus3.p) annotation (Line(points={{67,-10},{73.5,-10},{80,-10}}, color={0,0,255}));
  annotation (
    Icon(coordinateSystem(preserveAspectRatio=false)),
    Diagram(coordinateSystem(preserveAspectRatio=false)),
    Documentation(revisions="<html>
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
"));
end BaseTest;
