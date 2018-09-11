within iPSL.Electrical.Controls.PSSE.TG;
model IEESGO "IEEE Standard Model for Turbine-Governor"
  input Modelica.Blocks.Interfaces.RealInput SPEED "Machine speed deviation from nominal (pu)"
    annotation (Placement(transformation(extent={{-140,-2},{-128,10}}), iconTransformation(extent={{-160,22},{-144,38}})));
  input Modelica.Blocks.Interfaces.RealInput PMECH0 "Initial value of turbine mechanical power (pu)"
    annotation (Placement(transformation(extent={{-140,24},{-128,36}}), iconTransformation(extent={{-160,-48},{-144,-32}})));
  parameter Real T_1=0.2 "Controller lag (s)";
  parameter Real T_2=0 "Controller lead compensation (s)";
  parameter Real T_3=0.5 "Governor lag (s)";
  parameter Real T_4=0.12 "Delay due to steam inlet volumes associated with steam chest and inlet piping (s)";
  parameter Real T_5=5 "Reheater delay including hot and cold leads (s)";
  parameter Real T_6=0.5 "Delay due to IP-LP turbine, crossover pipes, and LP end hoods (s)";
  parameter Real K_1=20 "1/p.u. regulation ";
  parameter Real K_2=0.59 "Fraction ";
  parameter Real K_3=0.43 "Fraction ";
  parameter Real P_MAX=0.98 "Upper power limit";
  parameter Real P_MIN=0 "Lower power limit";
  iPSL.NonElectrical.Continuous.SimpleLag imSimpleLag(
    K=K_1,
    T=T_1,
    y_start=0) annotation (Placement(transformation(extent={{-114,-6},{-94,14}})));
  iPSL.NonElectrical.Continuous.LeadLag imLeadLag(
    K=1,
    T1=T_2,
    T2=T_3,
    y_start=0) annotation (Placement(transformation(extent={{-82,-6},{-62,14}})));
  iPSL.NonElectrical.Continuous.SimpleLag imSimpleLag1(
    K=1,
    T=T_4,
    y_start=p0) annotation (Placement(transformation(extent={{26,14},{46,34}})));
  iPSL.NonElectrical.Continuous.SimpleLag imSimpleLag2(
    K=K_2,
    T=T_5,
    y_start=p0*K_2) annotation (Placement(transformation(extent={{6,-66},{26,-46}})));
  iPSL.NonElectrical.Continuous.SimpleLag imSimpleLag3(
    K=K_3,
    T=T_6,
    y_start=p0*K_2*K_3) annotation (Placement(transformation(extent={{46,-66},{66,-46}})));
  output Modelica.Blocks.Interfaces.RealOutput PMECH "Turbine mechanical power (pu)"
    annotation (Placement(transformation(extent={{146,-22},{156,-10}}), iconTransformation(extent={{160,-10},{180,10}})));
  Modelica.Blocks.Math.Add add(k2=-1) annotation (Placement(transformation(extent={{-44,14},{-24,34}})));
  Modelica.Blocks.Nonlinear.Limiter limiter(uMax=P_MAX, uMin=P_MIN) annotation (Placement(transformation(extent={{-14,14},{6,34}})));
  Modelica.Blocks.Math.Gain gain(k=1 - K_2) annotation (Placement(transformation(extent={{66,14},{86,34}})));
  Modelica.Blocks.Math.Gain gain1(k=1 - K_3) annotation (Placement(transformation(extent={{46,-26},{66,-6}})));
  Modelica.Blocks.Math.Add3 add3_1 annotation (Placement(transformation(extent={{106,-26},{126,-6}})));
protected
  parameter Real p0(fixed=false);
initial algorithm
  p0 := PMECH0;
equation
  connect(SPEED, imSimpleLag.u) annotation (Line(points={{-134,4},{-134,4},{-116,4}}, color={0,0,127}));
  connect(imSimpleLag.y, imLeadLag.u) annotation (Line(points={{-93,4},{-84,4}}, color={0,0,127}));
  connect(imLeadLag.y, add.u2) annotation (Line(points={{-61,4},{-58,4},{-58,18},{-46,18}}, color={0,0,127}));
  connect(PMECH0, add.u1) annotation (Line(points={{-134,30},{-46,30}}, color={0,0,127}));
  connect(add.y, limiter.u) annotation (Line(points={{-23,24},{-19.5,24},{-16,24}}, color={0,0,127}));
  connect(limiter.y, imSimpleLag1.u) annotation (Line(points={{7,24},{15.5,24},{24,24}}, color={0,0,127}));
  connect(imSimpleLag2.y, imSimpleLag3.u) annotation (Line(points={{27,-56},{35.5,-56},{44,-56}}, color={0,0,127}));
  connect(imSimpleLag1.y, imSimpleLag2.u) annotation (Line(points={{47,24},{54,24},{54,4},{-2,4},{-2,-56},{4,-56}}, color={0,0,127}));
  connect(gain.u, imSimpleLag2.u) annotation (Line(points={{64,24},{54,24},{54,4},{-2,4},{-2,-56},{4,-56}}, color={0,0,127}));
  connect(gain1.u, imSimpleLag3.u) annotation (Line(points={{44,-16},{34,-16},{34,-56},{44,-56}}, color={0,0,127}));
  connect(gain.y, add3_1.u1) annotation (Line(points={{87,24},{96,24},{96,-8},{104,-8}}, color={0,0,127}));
  connect(gain1.y, add3_1.u2) annotation (Line(points={{67,-16},{85.5,-16},{104,-16}}, color={0,0,127}));
  connect(imSimpleLag3.y, add3_1.u3) annotation (Line(points={{67,-56},{96,-56},{96,-24},{104,-24}}, color={0,0,127}));
  connect(add3_1.y, PMECH) annotation (Line(points={{127,-16},{151,-16}}, color={0,0,127}));
  annotation (
    Diagram(coordinateSystem(preserveAspectRatio=false, extent={{-160,-80},{160,60}}), graphics={Text(
          extent={{-50,70},{46,18}},
          lineColor={0,0,255},
          textString="Pm0*(1-K_2)+Pm0*K_2*(1-K_3)+Pm0*K_3*K_2
=0+0+0+Pm0")}),
    Icon(coordinateSystem(preserveAspectRatio=false, extent={{-160,-80},{160,60}}), graphics={Rectangle(extent={{-160,60},{160,-80}}, lineColor={0,0,255}),Text(
          extent={{-52,20},{68,-20}},
          lineColor={0,0,255},
          textString="IEESGO"),Text(
          extent={{-142,36},{-118,22}},
          lineColor={0,0,255},
          textString="SPEED"),Text(
          extent={{-142,-32},{-116,-48}},
          lineColor={0,0,255},
          textString="PMECH0"),Text(
          extent={{134,6},{156,-6}},
          lineColor={0,0,255},
          textString="PMECH")}),
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
end IEESGO;
