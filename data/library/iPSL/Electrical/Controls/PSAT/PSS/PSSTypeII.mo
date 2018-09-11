within iPSL.Electrical.Controls.PSAT.PSS;
model PSSTypeII "PSAT PSS TypeII"
  parameter Real vsmax "Max stabilizer output signal (pu)";
  parameter Real vsmin "Min stabilizer output signal (pu)";
  parameter Real Kw "Stabilizer gain (pu/pu)";
  parameter Real Tw "Wash-out time constant (s)";
  parameter Real T1 "First stabilizer time constant (s)";
  parameter Real T2 "Second stabilizer time constant (s)";
  parameter Real T3 "Third stabilizer time constant (s)";
  parameter Real T4 "Fourth stabilizer time constant (s)";
  Modelica.Blocks.Interfaces.RealInput vSI "PSS input signal " annotation (Placement(transformation(extent={{-130,-20},{-90,20}})));
  Modelica.Blocks.Interfaces.RealOutput vs "PSS output signal" annotation (Placement(transformation(extent={{100,-10},{120,10}})));
  NonElectrical.Continuous.LeadLag imLeadLag(
    K=1,
    T1=T1,
    T2=T2,
    y_start=0) annotation (Placement(transformation(extent={{-10,-10},{10,10}})));
  NonElectrical.Continuous.LeadLag imLeadLag1(
    K=1,
    T1=T3,
    T2=T4,
    y_start=0) annotation (Placement(transformation(extent={{28,-10},{48,10}})));
  Modelica.Blocks.Nonlinear.Limiter limiter(uMax=vsmax, uMin=vsmin) annotation (Placement(transformation(extent={{60,-10},{80,10}})));
  NonElectrical.Continuous.DerivativeLag derivativeLag(
    K=Kw*Tw,
    T=Tw,
    y_start=0,
    x_start=0) annotation (Placement(transformation(extent={{-60,-10},{-40,10}})));
equation
  connect(vs, limiter.y) annotation (Line(points={{110,0},{96,0},{81,0}}, color={0,0,127}));
  connect(imLeadLag1.y, limiter.u) annotation (Line(points={{49,0},{53.5,0},{58,0}}, color={0,0,127}));
  connect(imLeadLag.y, imLeadLag1.u) annotation (Line(points={{11,0},{26,0}}, color={0,0,127}));
  connect(vSI, derivativeLag.u) annotation (Line(points={{-110,0},{-62,0},{-62,0}}, color={0,0,127}));
  connect(derivativeLag.y, imLeadLag.u) annotation (Line(points={{-39,0},{-12,0},{-12,0}}, color={0,0,127}));
  annotation (Diagram(coordinateSystem(preserveAspectRatio=false, extent={{-100,-100},{100,100}})), Documentation(info="<html>
<table cellspacing=\"1\" cellpadding=\"1\" border=\"1\"><tr>
<td align=center  width=50%><p>Development level</p></td>
<td align=center width=25% bgcolor=yellow><p> 2 </p></td>
</tr> 
</table> 
<p></p>
<table cellspacing=\"1\" cellpadding=\"1\" border=\"1\">
<tr>
<td><p>Reference</p></td>
<td>PSS Type II, PSAT manual</td>
</tr>
<tr>
<td><p>Last update</p></td>
<td>2015-08-24</td>
</tr>
<tr>
<td><p>Author</p></td>
<td><p>Tin Rabuzin, SmarTS Lab, KTH Royal Institute of Technology</p></td>
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
end PSSTypeII;
