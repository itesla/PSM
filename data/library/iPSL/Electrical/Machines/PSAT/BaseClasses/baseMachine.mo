within iPSL.Electrical.Machines.PSAT.BaseClasses;

partial model baseMachine
  extends iPSL.Electrical.Essentials.pfComponent;
  import Modelica.Constants.pi;
  import Modelica.Blocks.Interfaces.*;
  parameter Real Sn "Power rating (MVA)" annotation(
    Dialog(group = "Machine parameters"));
  parameter Real Vn "Voltage rating (kV)" annotation(
    Dialog(group = "Machine parameters"));
  parameter Real ra "Armature resistance (pu)" annotation(
    Dialog(group = "Machine parameters"));
  parameter Real xd1 "d-axis transient reactance (pu)" annotation(
    Dialog(group = "Machine parameters"));
  parameter Real M "Machanical starting time (2H), kWs/kVA" annotation(
    Dialog(group = "Machine parameters"));
  parameter Real D "Damping coefficient" annotation(
    Dialog(group = "Machine parameters"));
  //Initialization
  //Initialization of currents and voltages
  iPSL.Connectors.PwPin p(vr(start = vr0), vi(start = vi0), ir(start = ir0), ii(start = ii0)) annotation(
    Placement(visible = true, transformation(origin = {2.2777, 10.4683}, extent = {{-10, -10}, {10, 10}}, rotation = 0), iconTransformation(origin = {110, 0.4964}, extent = {{-10, -10}, {10, 10}}, rotation = 0)));
  Modelica.Blocks.Interfaces.RealOutput delta "Rotor angle (deg)" annotation(
    Placement(visible = true, transformation(origin = {161.092, -52.0898}, extent = {{-10, -10}, {10, 10}}, rotation = 0), iconTransformation(origin = {110, 60}, extent = {{-10, -10}, {10, 10}}, rotation = 0)));
  Modelica.Blocks.Interfaces.RealOutput w(start = 1) "Rotor speed (pu)" annotation(
    Placement(visible = true, transformation(origin = {160.109, -96.8871}, extent = {{-10, -10}, {10, 10}}, rotation = 0), iconTransformation(origin = {110, 90}, extent = {{-10, -10}, {10, 10}}, rotation = 0)));
  Modelica.Blocks.Interfaces.RealOutput v(start = V_0,fixed=true) "Generator terminal voltage (pu)" annotation(
    Placement(visible = true, transformation(origin = {146.6, 6.3277}, extent = {{-10, -10}, {10, 10}}, rotation = 0), iconTransformation(origin = {110, 30}, extent = {{-10, -10}, {10, 10}}, rotation = 0)));
  Modelica.Blocks.Interfaces.RealOutput P(start = P_0 / S_b) "Active power (pu)" annotation(
    Placement(visible = true, transformation(origin = {135.6, 56.3277}, extent = {{-10, -10}, {10, 10}}, rotation = 0), iconTransformation(origin = {110, -30.1615}, extent = {{-10, -10}, {10, 10}}, rotation = 0)));
  Modelica.Blocks.Interfaces.RealOutput Q(start = Q_0 / S_b) "Reactive power (pu)" annotation(
    Placement(visible = true, transformation(origin = {135.6, 36.3277}, extent = {{-10, -10}, {10, 10}}, rotation = 0), iconTransformation(origin = {110, -60}, extent = {{-10, -10}, {10, 10}}, rotation = 0)));
  Modelica.Blocks.Interfaces.RealInput vf "Field voltage (pu)" annotation(
    Placement(visible = true, transformation(origin = {-159.127, 67.3695}, extent = {{-20, -20}, {20, 20}}, rotation = 0), iconTransformation(origin = {-90, 50}, extent = {{-10, -9.99999}, {9.99996, 9.99998}}, rotation = 0)));
  RealOutput vf0 "Initial field voltage (pu)" annotation(
    Placement(visible = true, transformation(origin = {159.127, 97.5816}, extent = {{-10.0, -10.0}, {10.0, 10.0}}, rotation = 0), iconTransformation(origin = {-80, 110}, extent = {{-10.0, -10.0}, {10.0, 10.0}}, rotation = 90)));
  RealOutput pm0 "Initial mechanical power (pu)" annotation(
    Placement(visible = true, transformation(origin = {160.6004, 66.3277}, extent = {{-10.0, -10.0}, {10.0, 10.0}}, rotation = 0), iconTransformation(origin = {-80, -110}, extent = {{-10, -10}, {10, 10}}, rotation = 270)));
  Modelica.Blocks.Interfaces.RealInput pm "Mechanical power (pu)" annotation(
    Placement(visible = true, transformation(origin = {-163.038, -42.7137}, extent = {{-20, -20}, {20, 20}}, rotation = 0), iconTransformation(origin = {-90, -34}, extent = {{-10, -10}, {10, 10}}, rotation = 0)));
  Real anglev(start = angle_0 / 180 * pi) " Bus voltage angle";
  Real vd "d-axis voltage (pu)";
  Real vq "q-axis voltage (pu)";
  Real id "d-axis currrent (pu)";
  Real iq "q-axis current (pu)";
protected
  parameter Real vr0 = V_0 * cos(angle_0 / 180 * pi) "Initialitation";
  parameter Real vi0 = V_0 * sin(angle_0 / 180 * pi) "Initialitation";
  parameter Real ir0 = (P_0 / S_b * vr0 + Q_0 / S_b * vi0) / (vr0 ^ 2 + vi0 ^ 2) "Initialitation";
  parameter Real ii0 = (P_0 / S_b * vi0 - Q_0 / S_b * vr0) / (vr0 ^ 2 + vi0 ^ 2) "Initialitation";
  //Change of base constants
  parameter Real CoB = S_b * Vn / (Sn * V_b);
  parameter Real CoB2 = S_b / Sn;
  parameter Real m = M / CoB2 "Mechanical starting time (2H), kWs/kVA";
  parameter Real d = D / CoB2 "Damping coefficient";
  parameter Real Ra = ra * CoB "armature resistance, p.u.";
  parameter Real x1d = xd1 * CoB "d-axis transient reactance, p.u.";
  parameter Real w_b = 2 * pi * fn "Base frequency in rad/s";
protected
  Real pe;
initial equation
  w = 1;
equation
  v = sqrt(p.vr ^ 2 + p.vi ^ 2);
  anglev = atan2(p.vi, p.vr);
  der(delta) = w_b * (w - 1);
  der(w) = (pm - pe - d * (w - 1)) / m;
  [p.ir; p.ii] = -[sin(delta), cos(delta); -cos(delta), sin(delta)] * [id; iq];
  [p.vr; p.vi] = [sin(delta), cos(delta); -cos(delta), sin(delta)] * [vd; vq];
  // -P = p.vr * p.ir + p.vi * p.ii;
  // -Q = p.vi * p.ir - p.vr * p.ii;
  P = vd * id + vq * iq;
  Q = vq * id - vd * iq;
  pe = (vq + Ra * iq) * iq + (vd + Ra * id) * id;
  annotation(
    Icon(coordinateSystem(preserveAspectRatio = false, grid = {1, 1}, initialScale = 0.1), graphics = {Text(origin = {-69, 50.5}, lineColor = {28, 108, 200}, fillPattern = FillPattern.Solid, extent = {{-10, -10.5}, {10, 10.5}}, textString = "vf", fontName = "Arial"), Text(origin = {-66.3761, -32}, lineColor = {28, 108, 200}, fillPattern = FillPattern.Solid, extent = {{-9.6086, -11.4189}, {9.6086, 11.4189}}, textString = "pm", fontName = "Arial"), Text(origin = {-80, 90}, lineColor = {28, 108, 200}, fillPattern = FillPattern.Solid, extent = {{-10, -10}, {10, 10}}, textString = "vf0", fontName = "Arial"), Text(origin = {-80.6294, -89.5811}, lineColor = {28, 108, 200}, fillPattern = FillPattern.Solid, extent = {{-15.3706, -13.4189}, {15.3706, 13.4189}}, textString = "pm0", fontName = "Arial"), Text(origin = {80.5, 60.6367}, lineColor = {28, 108, 200}, fillPattern = FillPattern.Solid, extent = {{-23.5, -8.6367}, {23.5, 8.6367}}, textString = "delta", fontName = "Arial"), Text(origin = {90, 90.5}, lineColor = {28, 108, 200}, fillPattern = FillPattern.Solid, extent = {{-10, -10.5}, {10, 10.5}}, textString = "w", fontName = "Arial"), Text(origin = {90, 30}, lineColor = {28, 108, 200}, fillPattern = FillPattern.Solid, extent = {{-10, -10}, {10, 10}}, textString = "v", fontName = "Arial"), Rectangle(lineColor = {28, 108, 200}, fillColor = {255, 255, 255}, extent = {{-100, -100}, {100, 100}}), Ellipse(lineColor = {28, 108, 200}, extent = {{-40, 40}, {40, -40}}, endAngle = 360), Line(points = {{-20, 0}, {-9, 15}, {9, -15}, {20, 0}}, color = {28, 108, 200}, smooth = Smooth.Bezier), Text(origin = {4, -58.0978}, lineColor = {28, 108, 200}, fillPattern = FillPattern.Solid, extent = {{-57.2101, -15}, {57.2101, 15}}, textString = "%name", fontName = "Arial"), Text(origin = {90, -31}, lineColor = {28, 108, 200}, fillPattern = FillPattern.Solid, extent = {{-10, -10}, {10, 10}}, textString = "P", fontName = "Arial"), Text(origin = {90, -60}, lineColor = {28, 108, 200}, fillPattern = FillPattern.Solid, extent = {{-10, -10}, {10, 10}}, textString = "Q", fontName = "Arial")}),
    Diagram(coordinateSystem(preserveAspectRatio = false, extent = {{-100, -100}, {100, 100}}, grid = {1, 1})),
    Documentation(info="<html>
<table cellspacing=\"1\" cellpadding=\"1\" border=\"1\"><tr>
<td align=center  width=50%><p>Development level</p></td>
<td align=center width=25% bgcolor=yellow><p> 2 </p></td>
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
end baseMachine;
