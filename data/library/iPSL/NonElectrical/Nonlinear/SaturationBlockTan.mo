within iPSL.NonElectrical.Nonlinear;
model SaturationBlockTan "Block 1 in the OEL to decide the input signal of MIN Gate"
  parameter Real r "Exiciter parameter";
  parameter Real f "Exiciter parameter";
  Modelica.Blocks.Interfaces.RealInput p1 annotation (Placement(transformation(extent={{-86,-10},{-66,10}}), iconTransformation(extent={{-80,-10},{-60,10}})));
  Modelica.Blocks.Interfaces.RealOutput n1 annotation (Placement(transformation(extent={{46,-10},{66,10}}), iconTransformation(extent={{60,-6},{72,6}})));
equation
  if p1 <= (-0.1) then
    n1 = -1;
  elseif p1 > (-0.1) and p1 < 0 then
    n1 = 0;
  else
    n1 = tan(r)*p1 + f;
  end if;
  annotation (
    Diagram(coordinateSystem(preserveAspectRatio=true, extent={{-60,-60},{60,60}}), graphics),
    Icon(coordinateSystem(preserveAspectRatio=true, extent={{-60,-60},{60,60}}), graphics={Rectangle(
          extent={{-60,60},{60,-60}},
          lineColor={0,0,255},
          fillColor={255,255,255},
          fillPattern=FillPattern.Solid),Line(
          points={{-42,0},{36,0},{38,0},{40,0}},
          color={0,0,255},
          thickness=0.5,
          smooth=Smooth.None,
          arrow={Arrow.None,Arrow.Open}),Line(
          points={{0,-30},{0,36}},
          color={0,0,255},
          thickness=0.5,
          smooth=Smooth.None,
          arrow={Arrow.None,Arrow.Open}),Line(
          points={{0,12},{34,12}},
          color={0,0,255},
          smooth=Smooth.None),Text(
          extent={{22,22},{38,12}},
          lineColor={0,0,0},
          fillColor={0,0,255},
          fillPattern=FillPattern.Solid,
          textStyle={TextStyle.Italic},
          textString="r"),Text(
          extent={{-12,16},{4,6}},
          lineColor={0,0,0},
          fillColor={0,0,255},
          fillPattern=FillPattern.Solid,
          textString="f",
          textStyle={TextStyle.Italic}),Text(
          extent={{-2,0},{12,-8}},
          lineColor={0,0,0},
          fillColor={0,0,255},
          fillPattern=FillPattern.Solid,
          textString="0"),Text(
          extent={{-26,8},{-12,0}},
          lineColor={0,0,0},
          fillColor={0,0,255},
          fillPattern=FillPattern.Solid,
          textString="-0.1"),Line(
          points={{-20,-20},{-2,-20},{0,-20}},
          color={0,0,255},
          pattern=LinePattern.Dot,
          smooth=Smooth.None),Text(
          extent={{-2,-16},{12,-24}},
          lineColor={0,0,0},
          fillColor={0,0,255},
          fillPattern=FillPattern.Solid,
          textString="-1"),Line(
          points={{-34,-20},{-20,-20},{-20,0},{0,0},{0,12},{32,22}},
          color={0,0,0},
          thickness=0.5,
          smooth=Smooth.None)}),
    Documentation(info="<html>
<table cellspacing=\"1\" cellpadding=\"1\" border=\"1\"><tr>
<td align=center  width=50%><p>Development level</p></td>
<td align=center width=25% bgcolor=cyan><p> 3 </p></td>
</tr> 
</table> 
<p></p></html>", revisions="<html>
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
end SaturationBlockTan;
