within iPSL.Electrical.Wind.PSAT.PSAT_Type_3;
model WindBlk
  import Modelica.Constants.pi;
  Modelica.Blocks.Interfaces.RealInput vw "Real voltage" annotation (Placement(
      transformation(
        extent={{-102.0,54.0},{-62.0,94.0}},
        origin={-43.0,7.7602},
        rotation=0),
      visible=true,
      iconTransformation(
        origin={2.0,-2.4053},
        extent={{-102.0,54.0},{-62.0,94.0}},
        rotation=0)));
  Modelica.Blocks.Interfaces.RealInput theta_p "Real voltage" annotation (Placement(
      transformation(
        extent={{-102.0,54.0},{-62.0,94.0}},
        origin={-43.0,-34.0},
        rotation=0),
      visible=true,
      iconTransformation(
        origin={2.0,-76.5688},
        extent={{-102.0,54.0},{-62.0,94.0}},
        rotation=0)));
  Modelica.Blocks.Interfaces.RealInput omega_m "Real voltage" annotation (Placement(
      transformation(
        extent={{-102.0,54.0},{-62.0,94.0}},
        origin={-43.0,-74.0},
        rotation=0),
      visible=true,
      iconTransformation(
        origin={2.0,-146.3226},
        extent={{-102.0,54.0},{-62.0,94.0}},
        rotation=0)));
  Modelica.Blocks.Interfaces.RealOutput Tm "Real voltage" annotation (Placement(
      transformation(
        extent={{102.0,54.0},{62.0,94.0}},
        origin={43.0,-74.0},
        rotation=0),
      visible=true,
      iconTransformation(
        origin={-2.0,-71.3573},
        extent={{102.0,54.0},{62.0,94.0}},
        rotation=0)));
  parameter Real vw_base=15 "Vw Nominal (m/s)";
  parameter Real rho=1.225 "Air Density (kg/m^3)";
  parameter Real Sbase=100 "Power Rating [Normalization Factor] (MVA)";
  parameter Real l=75 "Blade length (m)";
  parameter Real ngb=0.01123596 "gear box ratio";
  parameter Real Radapt=ngb*l "r_{gearbox}*r";
  parameter Real poles=2 "Number of poles-pair";
  parameter Real freq=50 "frequency rating (Hz)";
  parameter Real wbase=2*pi*freq;
  parameter Real Ar=pi*l^2 "blades area";
  Real lambda "Tip speed Ratio";
  Real lambdai "Tip Speed Ratio optimal";
  Real cp "Capacity coefficient";
  Real Pw "Power in the Wind";
equation
  lambda = omega_m*wbase*Radapt/(vw*vw_base);
  lambdai = 1/(1/(lambda + 0.08*theta_p) - 0.035/(theta_p^3 + 1));
  cp = 0.22*(116/lambdai - 0.4*theta_p - 5)*exp(-12.5/lambdai);
  Pw = 0.5*rho*cp*Ar*(vw*vw_base)^3/(Sbase*1000000.0);
  Tm = Pw/omega_m;
  annotation (
    Icon(coordinateSystem(
        extent={{-100.0,-100.0},{100.0,100.0}},
        preserveAspectRatio=true,
        initialScale=0.1,
        grid={10,10}), graphics={Rectangle(
          visible=true,
          fillColor={255,255,255},
          extent={{-100.0,-100.0},{100.0,100.0}}),Text(
          visible=true,
          origin={5.2917,-1.4606},
          fillPattern=FillPattern.Solid,
          extent={{-39.3935,-41.4606},{39.3935,41.4606}},
          textString="wind",
          fontName="Arial")}),
    Diagram(coordinateSystem(
        extent={{-148.5,-105.0},{148.5,105.0}},
        preserveAspectRatio=true,
        initialScale=0.1,
        grid={5,5})),
    Documentation(info="<html>
<table cellspacing=\"1\" cellpadding=\"1\" border=\"1\"><tr>
<td align=center  width=50%><p>Development level</p></td>
<td align=center width=25% bgcolor=yellow><p> 2 </p></td>
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
</html>"));
end WindBlk;
