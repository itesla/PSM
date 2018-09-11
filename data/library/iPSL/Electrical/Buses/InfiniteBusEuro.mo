within iPSL.Electrical.Buses;
model InfiniteBusEuro  "Infinite bus model 2016/05/12"

  iPSL.Connectors.PwPin p(vr(start=Vo_real), vi(start=Vo_img));
  iPSL.Connectors.PwPin n(vr(start=Vo_real), vi(start=Vo_img));
  parameter Real R;
  parameter Real X;
  parameter Real Z2 = (R * R + X * X);
  parameter Real Y1 = R / Z2;
  parameter Real Y2 = X / Z2;
  parameter Real V_0 = 1 "Voltage magnitude (pu)" annotation(Dialog(group = "Power flow data"));
  parameter Real angle_0 = 0 "Voltage angle (deg)" annotation(Dialog(group = "Power flow data"));
  parameter Real Vo_real = V_0 * cos(angle_0 * Modelica.Constants.pi / 180) "Initial voltage at node in p.u. (Real part)";
  parameter Real Vo_img = V_0 * sin(angle_0 * Modelica.Constants.pi / 180) "Initial voltage at node in p.u. (Imaginary part)";
  Real V (start = V_0) "Bus voltage magnitude (pu)";
  Real Irn;
  Real Iin;
equation
  V = sqrt(p.vr ^ 2 + p.vi ^ 2);
  Irn = Y1 * Vo_real + Y2 * Vo_img + n.ir; 
  Iin = Y1 * Vo_img - Y2 * Vo_real + n.ii; 
  p.ii + Iin  = (R * p.vi - X * p.vr) / Z2;
  p.ir + Irn  = (R * p.vr + X * p.vi) / Z2;
  n.vr = p.vr;
  n.vi = p.vi;
  annotation (
    Icon(coordinateSystem(
        extent={{-100,-100},{100,100}},
        preserveAspectRatio=true,
        initialScale=0.1,
        grid={2,2}), graphics={Rectangle(
          visible=true,
          fillPattern=FillPattern.Solid,
          extent={{-10.0,-100.0},{10.0,100.0}}),Text(
          visible=true,
          origin={0.9738,119.0625},
          fillPattern=FillPattern.Solid,
          extent={{-39.0262,-16.7966},{39.0262,16.7966}},
          textString="%name",
          fontName="Arial"),Text(
          origin={0.9738,-114.937},
          fillPattern=FillPattern.Solid,
          extent={{-39.0262,-16.7966},{39.0262,16.7966}},
          fontName="Arial",
          textString=DynamicSelect("0.0", String(v, significantDigits=3)),
          lineColor={238,46,47}),Text(
          origin={0.9738,-140.937},
          fillPattern=FillPattern.Solid,
          extent={{-39.0262,-16.7966},{39.0262,16.7966}},
          fontName="Arial",
          textString=DynamicSelect("0.0", String(anglevdeg, significantDigits=3)),
          lineColor={238,46,47})}),
    Diagram(coordinateSystem(
        extent={{-148.5,-105.0},{148.5,105.0}},
        preserveAspectRatio=true,
        initialScale=0.1,
        grid={5,5})),
    uses(Modelica(version="3.2.1")),
    Documentation(info="<html>
<table cellspacing=\"1\" cellpadding=\"1\" border=\"1\"><tr>
<td align=center  width=50%><p>Development level</p></td>
<td align=center width=25% bgcolor= #00FF00><p> 4 </p></td>
</tr> 
</table> 
<p></p>      
<table cellspacing=\"1\" cellpadding=\"1\" border=\"1\">
<tr>
<td><p>Reference</p></td>
<td>Eurostag/Model Editor User's Manual</td>
</tr>
<tr>
<td><p>Last update</p></td>
<td>2016/12/20 by AIA </td>
</tr>
<tr>
<td><p>Author</p></td>
<td><p>RTE-AIA</p></td>
</tr>
<tr>
<td><p>Contact</p></td>
<td><p>modelica@aia.es<a href=\"mailto:modelica@aia.es\"></a></p></td>
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
end InfiniteBusEuro;
