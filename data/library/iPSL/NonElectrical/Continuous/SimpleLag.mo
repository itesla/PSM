within iPSL.NonElectrical.Continuous;
block SimpleLag "First order lag transfer function block"
  extends Modelica.Blocks.Interfaces.SISO(y(start=y_start));
  import Modelica.Blocks.Types.Init;
  Real state(start=y_start);
  parameter Real K "Gain";
  parameter Modelica.SIunits.Time T "Lag time constant";
  parameter Real y_start "Output start value";
  parameter Modelica.Blocks.Types.Init initType=Modelica.Blocks.Types.Init.InitialOutput;
protected
  parameter Real T_mod=if (T < Modelica.Constants.eps) then 1000 else T;
initial equation
  if initType == Init.SteadyState then
     der(state) = 0;
  else 
    state = y_start;
  end if;
equation
  T_mod*der(state) = K*u - state;
  if T <= Modelica.Constants.eps then
    y =  u*K;
  else
    y = state;
  end if;
  annotation (
    Icon(coordinateSystem(preserveAspectRatio=true, extent={{-100,-100},{100,100}}), graphics={Text(
          extent={{-18,68},{22,8}},
          lineColor={0,0,255},
          textString="K"),Text(
          extent={{-68,-20},{72,-80}},
          lineColor={0,0,255},
          textString="1 + Ts"),Line(
          points={{-78,0},{80,0}},
          color={0,0,255},
          smooth=Smooth.Bezier,
          thickness=0.5)}),
    Diagram(graphics),
    Documentation(info="<html>
<table cellspacing=\"1\" cellpadding=\"1\" border=\"1\"><tr>
<td align=center  width=50%><p>Development level</p></td>
<td align=center width=25% bgcolor=cyan><p> 3 </p></td>
</tr> 
</table> 
<p></p>    
<table cellspacing=\"1\" cellpadding=\"1\" border=\"1\">
<tr>
<td><p>Reference</p></td>
<td></td>
</tr>
<tr>
<td><p>Last update</p></td>
<td>2015-11-24</td>
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
end SimpleLag;
