within iPSL.Examples.Controls.PSAT.TG;
model TGTypeI_Test
extends Modelica.Icons.Example;
  iPSL.Electrical.Loads.PSAT.LOADPQ pwLoadPQ2(
    P_0= 10,
    Q_0= 5,
    V_0=1,
    angle_0=0) annotation (Placement(visible=true, transformation(
        origin={122.283,26},
        extent={{-10.0,-10.0},{10.0,10.0}},
        rotation=0)));
  iPSL.Electrical.Branches.PwLine pwLine4(
    B=0.001/2,
    G=0,
    R=0.01,
    X=0.1) annotation (Placement(visible=true, transformation(
        origin={79,31},
        extent={{-10.0,-10.0},{10.0,10.0}},
        rotation=0)));
  iPSL.Electrical.Branches.PwLine pwLine3(
    B=0.001/2,
    G=0,
    R=0.01,
    X=0.1) annotation (Placement(visible=true, transformation(
        origin={84,-19},
        extent={{-10.0,-10.0},{10.0,10.0}},
        rotation=0)));
  iPSL.Electrical.Loads.PSAT.LOADPQ pwLoadPQ1(
    P_0= 10,
    Q_0= 5,
    V_0=1,
    angle_0=0) annotation (Placement(visible=true, transformation(
        origin={124,-19},
        extent={{-10.0,-10.0},{10.0,10.0}},
        rotation=0)));
  iPSL.Electrical.Branches.PwLine pwLine2(
    B=0.001/2,
    G=0,
    R=0.01,
    X=0.1) annotation (Placement(visible=true, transformation(
        origin={44,26},
        extent={{-10.0,-10.0},{10.0,10.0}},
        rotation=0)));
  iPSL.Electrical.Branches.PwLine pwLine1(
    B=0.001/2,
    G=0,
    R=0.01,
    X=0.1) annotation (Placement(visible=true, transformation(
        origin={44,-4},
        extent={{-10.0,-10.0},{10.0,10.0}},
        rotation=0)));
  iPSL.Electrical.Events.PwFaultPQ pwFault(
    X=0.001,
    startTime=3,
    endTime=3.1,
    R=10) annotation (Placement(transformation(extent={{115,-52},{135,-32}})));
  iPSL.Electrical.Machines.PSAT.Order6 order6Type2_Inputs_Outputs(
    V_0=1,
    angle_0=0,
    P_0=0.160352698692006,
    Q_0=0.11859436505981,
    V_b=200,
    Sn=370,
    Vn=200,
    ra=0.001,
    xd1=0.302,
    M=10,
    D=0) annotation (Placement(transformation(extent={{-68,-12},{12,66}})));
  parameter Real p0=0.160352698692006 "Power flow, node active power";
  iPSL.Electrical.Controls.PSAT.TG.TGTypeI tGTypeI(
    wref=1,
    pref=p0,
    R=0.2,
    pmax=1,
    pmin=0,
    Ts=0.1,
    Tc=1,
    T3=0.04,
    T4=5,
    T5=0.04) annotation (Placement(transformation(extent={{-2,-64},{-68,-20}})));
  inner iPSL.Electrical.SystemBase SysData annotation (Placement(transformation(extent={{80,80},{128,106}})));
  iPSL.Electrical.Branches.PwLine pwLine5(B = 0.001 / 2, G = 0, R = 0.01, X = 0.1, startTime = 8, endTime = 8.1) annotation(
    Placement(visible = true, transformation(origin = {79, 7}, extent = {{-10, -10}, {10, 10}}, rotation = 0)));
equation
  connect(pwLine5.p, pwLine4.p) annotation(
    Line(points = {{68, 8}, {66, 8}, {66, 32}, {68, 32}}, color = {0, 0, 255}));
  connect(pwLine5.n, pwLine4.n) annotation(
    Line(points = {{90, 8}, {90, 8}, {90, 32}, {90, 32}}, color = {0, 0, 255}));
  connect(pwLine4.n, pwLoadPQ2.p) annotation(
    Line(visible = true, origin = {101.213, 29}, points = {{-15.213, 2}, {3.0708, 2}, {3.0708, 8}, {21.07, 8}}));
  connect(pwLine3.n, pwLoadPQ1.p) annotation(
    Line(visible = true, origin = {107.5, -18.5}, points = {{-16.5, -0.5}, {3.5, -0.5}, {3.5, 10.5}, {16.5, 10.5}}));
  connect(pwLine2.n, pwLine4.p) annotation(
    Line(points = {{51, 26}, {64, 26}, {64, 31}, {72, 31}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(pwLine1.n, pwLine3.p) annotation(
    Line(points = {{51, -4}, {64, -4}, {64, -19}, {77, -19}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(pwLine2.n, pwLine1.n) annotation(
    Line(points = {{51, 26}, {51, -4}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(pwFault.p, pwLine3.n) annotation(
    Line(points = {{113.333, -42}, {104, -42}, {104, -19}, {91, -19}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(order6Type2_Inputs_Outputs.p, pwLine2.p) annotation(
    Line(points = {{16, 27.1936}, {27, 27.1936}, {27, 26}, {37, 26}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(pwLine1.p, pwLine2.p) annotation(
    Line(points = {{37, -4}, {37, 26}}, color = {0, 0, 255}, smooth = Smooth.None));
  connect(order6Type2_Inputs_Outputs.vf0, order6Type2_Inputs_Outputs.vf) annotation(
    Line(points = {{-60, 69.9}, {28, 69.9}, {28, 80}, {-88, 80}, {-88, 46.5}, {-68, 46.5}}, color = {0, 0, 127}, smooth = Smooth.None));
  connect(order6Type2_Inputs_Outputs.w, tGTypeI.w) annotation(
    Line(points = {{16, 62.1}, {30, 62.1}, {30, -38.7}, {-8.325, -38.7}}, color = {0, 0, 127}, smooth = Smooth.None));
  connect(tGTypeI.pm, order6Type2_Inputs_Outputs.pm) annotation(
    Line(points = {{-56.175, -39.14}, {-88, -39.14}, {-88, 7.5}, {-68, 7.5}}, color = {0, 0, 127}, smooth = Smooth.None));
  annotation (
    Diagram(coordinateSystem(extent={{-160,-100},{160,100}}, preserveAspectRatio=false)),
    Icon(coordinateSystem(extent={{-100,-100},{100,100}})),
    Documentation(info="<html>
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
end TGTypeI_Test;
