within iPSL.Examples.Controls.PSSE.TG;
model IEESGO "Simple Machine Infinite Bus with Machine, Governor and Excitation system"
  extends iPSL.Examples.SMIBpartial;
  extends Modelica.Icons.Example;
  iPSL.Electrical.Machines.PSSE.GENSAL generator(
    Xppd=0.2,
    Xppq=0.2,
    Xl=0.12,
    V_0=1,
    angle_0=4.05,
    P_0=39.99995,
    Q_0=5.416571,
    M_b=100,
    Tpd0=6.7000,
    Tppd0=0.028,
    Tppq0=0.0358,
    H=4.4100,
    D=0,
    Xd=1.2200,
    Xq=0.76000,
    Xpd=0.29700,
    S10=0.18600,
    S12=0.802) annotation (Placement(transformation(extent={{-90,-10},{-70,10}})));
  iPSL.Electrical.Controls.PSSE.ES.SCRX SCRX( 
    T_B=10,
    K=100,
    T_E=0.05,
    E_MIN=0,
    E_MAX=5,
    r_cr_fd=0,
    C_SWITCH=false,
    T_AT_B=0.1) annotation (Placement(transformation(
        extent={{-18,-16},{18,16}},
        rotation=180,
        origin={-80,-34})));
  Modelica.Blocks.Sources.Constant const(k=0) annotation (Placement(visible = true, transformation(origin = {-50, -32}, extent = {{-2, -2}, {2, 2}}, rotation = 180)));
  iPSL.Electrical.Controls.PSSE.TG.IEESGO iEESGO(
    T_1=0.01,
    T_2=0.0,
    T_3=0.15,
    T_4=0.3,
    T_5=8.0,
    T_6=0.4,
    K_2=0.7,
    K_3=0.43,
    P_MAX=1.0,
    P_MIN=0.0,
    K_1=0.1) annotation (Placement(transformation(extent={{-64,24},{-96,38}})));
equation
  connect(SCRX.XADIFD, generator.XADIFD) annotation(
    Line(points = {{-62, -30}, {-58, -30}, {-58, -10}, {-70, -10}, {-70, -8}}, color = {0, 0, 127}));
  connect(SCRX.VOTHSG, const.y) annotation(
    Line(points = {{-62, -44}, {-54, -44}, {-54, -32}, {-52, -32}}, color = {0, 0, 127}));
  connect(SCRX.VUEL, const.y) annotation(
    Line(points = {{-68, -18}, {-68, -16}, {-56, -16}, {-56, -32}, {-52, -32}}, color = {0, 0, 127}));
  connect(const.y, SCRX.VOEL) annotation(
    Line(points = {{-52, -32}, {-54, -32}, {-54, -14}, {-74, -14}, {-74, -18}}, color = {0, 0, 127}));
  connect(generator.ETERM, SCRX.ECOMP) annotation(
    Line(points = {{-70, 6}, {-44, 6}, {-44, -36}, {-62, -36}, {-62, -36}}, color = {0, 0, 127}));
  connect(generator.EFD0, SCRX.EFD0) annotation(
    Line(points = {{-70, -6}, {-50, -6}, {-50, -24}, {-62, -24}, {-62, -24}}, color = {0, 0, 127}));
  connect(SCRX.EFD, generator.EFD) annotation(
    Line(points = {{-98, -36}, {-122, -36}, {-122, -4}, {-90, -4}, {-90, -4}}, color = {0, 0, 127}));
  connect(iEESGO.SPEED, generator.SPEED) annotation (Line(points={{-64.8,35},{-62,35},{-62,9},{-69.2,9}}, color={0,0,127}));
  connect(iEESGO.PMECH, generator.PMECH) annotation (Line(points={{-97,32},{-104,32},{-104,5},{-89.8,5}}, color={0,0,127}));
  connect(iEESGO.PMECH0, generator.PMECH0) annotation (Line(points={{-64.8,28},{-62,28},{-62,-3},{-69.2,-3}}, color={0,0,127}));
  connect(generator.p, GEN1.p) annotation (Line(points={{-69,0},{-54.5,0},{-40,0}}, color={0,0,255}));
  annotation (
    Diagram(coordinateSystem(preserveAspectRatio=false, extent={{-100,-120},{100,80}})),
    Icon(coordinateSystem(extent={{-100,-120},{100,80}})),
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
end IEESGO;
