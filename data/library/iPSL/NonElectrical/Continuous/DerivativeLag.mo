within iPSL.NonElectrical.Continuous;
block DerivativeLag "Derivative lag transfer function blcok"
  extends Modelica.Blocks.Interfaces.SISO;
  parameter Real K "Gain";
  parameter Modelica.SIunits.Time T "Time constan (s)";
  parameter Real y_start "Output start value" annotation (Dialog(group="Initialization"));
  parameter Real x_start=0 "Start value of state variable" annotation (Dialog(group="Initialization"));
  Modelica.Blocks.Sources.RealExpression par1(y=T) annotation (Placement(transformation(extent={{-80,54},{-60,74}})));
  Modelica.Blocks.Continuous.TransferFunction TF(
    initType=Modelica.Blocks.Types.Init.InitialState,
    x_start={x_start},
    b={K_dummy,0},
    y_start=y_start,
    a={T_dummy,1}) annotation (Placement(transformation(extent={{-8,-10},{12,10}})));
protected
  parameter Modelica.SIunits.Time T_dummy=if abs(T) < Modelica.Constants.eps then 1000 else T "Lead time constant";
  parameter Modelica.SIunits.Time K_dummy=if abs(K) < Modelica.Constants.eps then 1 else K "Lead time constant";
equation
    y = TF.y;
  connect(TF.u, u) annotation (Line(points={{-10,0},{-120,0}}, color={0,0,127}));
  annotation (
    Diagram(coordinateSystem(preserveAspectRatio=false, extent={{-100,-100},{100,100}})),
    Documentation(info="<html>
<table cellspacing=\"1\" cellpadding=\"1\" border=\"1\"><tr>
<td align=center  width=50%><p>Development level</p></td>
<td align=center width=25% bgcolor= #00FF00><p> 4 </p></td>
</tr> 
</table> 
<p></p> </html>", revisions="<html>
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
    Icon(graphics={Text(
          extent={{-60,82},{60,22}},
          lineColor={0,0,255},
          textString="Ks"),Line(
          points={{-62,0},{66,0}},
          color={0,0,255},
          smooth=Smooth.Bezier,
          thickness=0.5),Text(
          extent={{-60,-20},{60,-80}},
          lineColor={0,0,255},
          textString="1+sT")}));
end DerivativeLag;
