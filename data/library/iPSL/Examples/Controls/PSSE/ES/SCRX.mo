within iPSL.Examples.Controls.PSSE.ES;
model SCRX "SMIB model example of GENROU with Excitation System EXST1"
  extends iPSL.Examples.SMIBpartial;
  extends Modelica.Icons.Example;
  Modelica.Blocks.Sources.Constant const2(k=0) annotation (Placement(transformation(extent={{-110,166},{-94,182}})));
  iPSL.Electrical.Machines.PSSE.GENROU gENROE(
    M_b=100,
    Tpd0=5,
    Tppd0=0.07,
    Tpq0=0.9,
    Tppq0=0.09,
    D=0,
    Xd=1.84,
    Xq=1.75,
    Xpd=0.41,
    Xpq=0.6,
    Xppd=0.2,
    Xl=0.12,
    S10=0.11,
    S12=0.39,
    V_0=1,
    P_0=40,
    angle_0=4.046276,
    Q_0=5.416582,
    Xppq=0.2,
    R_a=0,
    Xpp=0.2,
    H=4.28) annotation (Placement(transformation(extent={{-112,-20},{-72,20}})));
  Modelica.Blocks.Sources.Constant const(k=0) annotation (Placement(transformation(
        extent={{4,-4},{-4,4}},
        rotation=0,
        origin={-52,-36})));
  iPSL.Electrical.Controls.PSSE.ES.SCRX sCRX(
    T_AT_B=0.1,
    T_B=1,
    K=100,
    E_MIN=-10,
    E_MAX=10,
    C_SWITCH=false,
    r_cr_fd=0,
    T_E=0.005) annotation (Placement(transformation(extent={{-74,-58},{-108,-30}})));
equation
  connect(gENROE.PMECH, gENROE.PMECH0) annotation (Line(points={{-111.6,10},{-116,10},{-116,26},{-62,26},{-62,-6},{-70.4,-6}}, color={0,0,127}));
  connect(sCRX.EFD, gENROE.EFD) annotation (Line(points={{-108.85,-42.4444},{-116,-42.4444},{-116,-10},{-111.6,-10}}, color={0,0,127}));
  connect(gENROE.ETERM, sCRX.ECOMP) annotation (Line(points={{-70.4,10},{-66,10},{-66,-42.4444},{-74,-42.4444}}, color={0,0,127}));
  connect(const.y, sCRX.VOTHSG) annotation (Line(points={{-56.4,-36},{-74,-36},{-74,-35.4444}}, color={0,0,127}));
  connect(sCRX.VOEL, sCRX.VOTHSG) annotation (Line(points={{-85.05,-58},{-70,-58},{-70,-36},{-74,-36},{-74,-35.4444}}, color={0,0,127}));
  connect(sCRX.VUEL, sCRX.VOTHSG) annotation (Line(points={{-79.95,-58},{-70,-58},{-70,-36},{-74,-36},{-74,-35.4444}}, color={0,0,127}));
  connect(sCRX.XADIFD, gENROE.XADIFD) annotation (Line(points={{-74,-47.8889},{-64,-47.8889},{-64,-18},{-70.4,-18}}, color={0,0,127}));
  connect(sCRX.EFD0, gENROE.EFD0) annotation (Line(points={{-74,-52.5556},{-62,-52.5556},{-62,-14},{-70.4,-14}}, color={0,0,127}));
  connect(gENROE.p, GEN1.p) annotation (Line(points={{-70,0},{-70,0},{-40,0}}, color={0,0,255}));
  annotation (
    Diagram(coordinateSystem(preserveAspectRatio=false, extent={{-120,-100},{120,100}})),
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
</html>"),
    Icon(coordinateSystem(extent={{-120,-100},{120,100}})));
end SCRX;
