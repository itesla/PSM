within iPSL.Examples.Machines.PSSE;
model GENSAL "SMIB system with one load and GENROE model"
  extends Modelica.Icons.Example;

  extends iPSL.Examples.SMIBpartial;
  iPSL.Electrical.Machines.PSSE.GENSAL gENROE(
    M_b=100,
    Tpd0=5,
    Tppd0=0.07,
    Tppq0=0.09,
    H=4.28,
    D=0,
    Xd=1.84,
    Xq=1.75,
    Xpd=0.41,
    Xppd=0.2,
    Xl=0.12,
    S10=0.11,
    S12=0.39,
    V_0=1,
    P_0=40,
    angle_0=4.046276,
    Q_0=5.416582,
    Xppq=0.2,
    R_a=0) annotation (Placement(visible = true, transformation(extent = {{-100, -20}, {-60, 20}}, rotation = 0)));
equation
  connect(gENROE.p, GEN1.p) annotation(Line(points = {{-58, 0}, {-40, 0}}, color = {0, 0, 255}));
  connect(gENROE.EFD, gENROE.EFD0) annotation(Line(points = {{-99.6, -10}, {-108, -10}, {-108, -30}, {-50, -30}, {-50, -14}, {-58.4, -14}}, color = {0, 0, 127}));
  connect(gENROE.PMECH, gENROE.PMECH0) annotation(Line(points = {{-99.6, 10}, {-108, 10}, {-108, 26}, {-50, 26}, {-50, -6}, {-58.4, -6}}, color = {0, 0, 127}));
  annotation (Diagram(coordinateSystem(preserveAspectRatio=false, extent={{-100,-100},{100,100}})), Documentation(revisions="<html>
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
end GENSAL;
