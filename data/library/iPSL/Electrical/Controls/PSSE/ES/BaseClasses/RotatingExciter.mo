within iPSL.Electrical.Controls.PSSE.ES.BaseClasses;

model RotatingExciter
  extends iPSL.Electrical.Controls.PSSE.ES.BaseClasses.RotatingExciterBase(redeclare replaceable Modelica.Blocks.Continuous.Integrator sISO(k = 1 / T_E, initType = Modelica.Blocks.Types.Init.InitialOutput, y_start = Efd0));
  annotation(Icon(coordinateSystem(initialScale = 0.05, extent = {{-80, -80}, {80, 80}}), graphics = {Rectangle(extent = {{-80, 80}, {80, -80}}, lineColor = {28, 108, 200}, fillColor = {255, 255, 255}, fillPattern = FillPattern.Solid), Text(extent = {{-52, 70}, {44, 56}}, lineColor = {28, 108, 200}, fillColor = {255, 255, 255}, fillPattern = FillPattern.Solid, textString = "Rotating Exciter"), Text(extent = {{-81, 4}, {-57, -6}}, lineColor = {28, 108, 200}, textString = "I_C"), Text(extent = {{56, 6}, {77, -4}}, lineColor = {28, 108, 200}, textString = "V_E")}), Diagram(coordinateSystem(initialScale = 0.05, extent = {{-80, -80}, {80, 80}})), Documentation(revisions = "<html>
<!--DISCLAIMER-->

<p>iPSL:</p>
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
</html>
"));
end RotatingExciter;