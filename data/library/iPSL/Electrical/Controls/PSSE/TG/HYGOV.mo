within iPSL.Electrical.Controls.PSSE.TG;
model HYGOV
  parameter Real R=0.05 "Permanent droop (pu)";
  parameter Real r=0.3 "Temporary droop (pu)";
  parameter Real T_r=5 "Governor time constant (s)";
  parameter Real T_f=0.05 "Filter time constant (s)";
  parameter Real T_g=0.5 "Servo time constant (s)";
  parameter Real VELM=0.2 "Gate open/close velocity limit";
  parameter Real G_MAX=0.9 "Maximum gate limit";
  parameter Real G_MIN=0 "Minimum gate limit";
  parameter Real T_w=1.25 "Water time constant (s)";
  parameter Real A_t=1.2 "Turbine gain";
  parameter Real D_turb=0.2 "Turbine damping";
  parameter Real q_NL=0.08 "Water flow at no load";
  Modelica.Blocks.Sources.Constant n_ref(k=nref) annotation (Placement(transformation(extent={{-178,14},{-166,26}})));
  Modelica.Blocks.Interfaces.RealInput SPEED "Machine speed deviation from nominal (pu)"
    annotation (Placement(transformation(extent={{-206,-10},{-192,4}}), iconTransformation(extent={{-84,24},{-72,36}})));
  iPSL.NonElectrical.Continuous.SimpleLag SimpleLag1(K = 1, T = T_f, y_start = 0) annotation (Placement(transformation(extent={{-126,0},{-114,12}})));
  Modelica.Blocks.Math.Gain Gain3(k=R) annotation (Placement(transformation(extent={{-130,-22},{-142,-10}})));
  Modelica.Blocks.Math.Gain Gain4(k=D_turb) annotation (Placement(transformation(extent={{-62,-34},{-50,-22}})));
  Modelica.Blocks.Sources.Constant hs(k=1) annotation (Placement(transformation(extent={{20,-20},{32,-8}})));
  Modelica.Blocks.Continuous.Integrator q(y_start = q0, initType = Modelica.Blocks.Types.Init.InitialOutput,
    k=1/T_w) annotation (Placement(transformation(extent={{62,-4},{74,8}})));
  Modelica.Blocks.Sources.Constant qNL(k=q_NL) annotation (Placement(transformation(extent={{92,-18},{84,-10}})));
  Modelica.Blocks.Math.Gain Gain6(k=A_t) annotation (Placement(transformation(extent={{132,-2},{144,10}})));
  Modelica.Blocks.Interfaces.RealOutput PMECH "Turbine mechanical power (pu)" annotation (Placement(transformation(extent={{170,-6},{182,6}}), iconTransformation(extent={{80,-4},{88,4}})));
  iPSL.NonElectrical.Continuous.SimpleLag g(K = 1, T = T_g, y_start = g0) "servo_motor" annotation (Placement(transformation(extent={{-40,0},{-28,12}})));
  Modelica.Blocks.Interfaces.RealInput PMECH0 "Initial turbine mechanical power (pu)"
    annotation (Placement(transformation(extent={{-84,-62},{-76,-52}}), iconTransformation(extent={{-84,-36},{-72,-24}})));
  Modelica.Blocks.Nonlinear.Limiter Velocity_Limiter(uMin=-VELM, uMax=VELM) annotation (Placement(transformation(extent={{-86,0},{-74,12}})));
  Modelica.Blocks.Continuous.LimIntegrator Position_Limiter(
    outMin = G_MIN, outMax = G_MAX,
    k = 1, y_start = c0, initType=Modelica.Blocks.Types.Init.InitialOutput) annotation (Placement(transformation(extent={{-68,0},{-56,12}})));
  Real G "Gate opening (pu)";
  Real c "Desired gate opening (pu)";
  Real Q "Turbine flow (pu)";
  Real H "Turbine head (pu)";
  Modelica.Blocks.Math.Add add(k2=-1) annotation (Placement(transformation(extent={{-144,0},{-132,12}})));
  Modelica.Blocks.Math.Add add1 annotation (Placement(transformation(extent={{-168,-12},{-156,0}})));
  Modelica.Blocks.Math.Division division annotation (Placement(transformation(extent={{0,-4},{12,8}})));
  Modelica.Blocks.Math.Product product annotation (Placement(transformation(extent={{20,-4},{32,8}})));
  Modelica.Blocks.Math.Add add2(k1=-1) annotation (Placement(transformation(extent={{42,-4},{54,8}})));
  Modelica.Blocks.Math.Add add3(k2=-1) annotation (Placement(transformation(extent={{82,-4},{94,8}})));
  Modelica.Blocks.Math.Add add4(k2=-1) annotation (Placement(transformation(extent={{152,-6},{164,6}})));
  Modelica.Blocks.Math.Product product1 annotation (Placement(transformation(extent={{58,-30},{70,-18}})));
  Modelica.Blocks.Math.Product product2 annotation(Placement(transformation(extent = {{108, -2}, {120, 10}})));
  NonElectrical.Continuous.SimpleLead simpleLead(K = r * T_r, T = T_r, y_start = 0) annotation(Placement(transformation(extent = {{-106, 0}, {-94, 12}})));
protected
  parameter Real h0=1 "water head initial value";
  // Real T_w(start=T_w);//=1.25 "Water time constant, s";
  parameter Real q0(fixed=false);
  //=Pm0/(A_t*h0)+q_NL "water flow initial value";
  parameter Real g0(fixed=false);
  //=q0/sqrt(h0) "the gate opening initial value";
  parameter Real c0(fixed=false);
  //=g0 "desired gate position";
  parameter Real e0=0 "initial output for the filter";
  parameter Real nref(fixed=false);
  //=R*c0 "speed reference";
  parameter Real P_m0(fixed=false);
initial algorithm
  P_m0 := PMECH0;
  q0 := P_m0/(A_t*h0) + q_NL;
  g0 := q0/sqrt(h0);
  //
  //
  c0 := g0;
  //c0_calculation(g0,G_MIN,G_MAX);
  nref := R*c0;
  //
equation
  G = g.y;
  c = g.u;
  Q = q.y;
  H = product.y;
  connect(add.y, SimpleLag1.u) annotation (Line(points={{-131.4,6},{-127.2,6}}, color={0,0,127}));
  connect(n_ref.y, add.u1) annotation (Line(points={{-165.4,20},{-152,20},{-152,9.6},{-145.2,9.6}}, color={0,0,127}));
  connect(add1.y, add.u2) annotation (Line(points={{-155.4,-6},{-150,-6},{-150,2.4},{-145.2,2.4}}, color={0,0,127}));
  connect(add1.u1, SPEED) annotation (Line(points={{-169.2,-2.4},{-177.6,-2.4},{-177.6,-3},{-199,-3}}, color={0,0,127}));
  connect(Gain3.y, add1.u2) annotation (Line(points={{-142.6,-16},{-178,-16},{-178,-9.6},{-169.2,-9.6}}, color={0,0,127}));
  connect(Gain4.u, SPEED) annotation (Line(points={{-63.2,-28},{-188,-28},{-188,-3},{-199,-3}}, color={0,0,127}));
  connect(Velocity_Limiter.y, Position_Limiter.u) annotation (Line(points={{-73.4,6},{-69.2,6}}, color={0,0,127}));
  connect(Position_Limiter.y, Gain3.u) annotation (Line(points={{-55.4,6},{-52,6},{-52,-16},{-128.8,-16}}, color={0,0,127}));
  connect(g.u, Gain3.u) annotation (Line(points={{-41.2,6},{-52,6},{-52,-16},{-128.8,-16}}, color={0,0,127}));
  connect(division.y, product.u1) annotation (Line(points={{12.6,2},{14,2},{14,5.6},{18.8,5.6}}, color={0,0,127}));
  connect(product.u2, product.u1) annotation (Line(points={{18.8,-1.6},{14,-1.6},{14,5.6},{18.8,5.6}}, color={0,0,127}));
  connect(product.y, add2.u1) annotation (Line(points={{32.6,2},{36,2},{36,5.6},{40.8,5.6}}, color={0,0,127}));
  connect(hs.y, add2.u2) annotation (Line(points={{32.6,-14},{36,-14},{36,-1.6},{40.8,-1.6}}, color={0,0,127}));
  connect(add2.y, q.u) annotation (Line(points={{54.6,2},{60.8,2}}, color={0,0,127}));
  connect(q.y, add3.u1) annotation (Line(points={{74.6,2},{76,2},{76,5.6},{80.8,5.6}}, color={0,0,127}));
  connect(qNL.y, add3.u2) annotation (Line(points={{83.6,-14},{76,-14},{76,-1.6},{80.8,-1.6}}, color={0,0,127}));
  connect(Gain4.y, product1.u2) annotation (Line(points={{-49.4,-28},{4,-28},{4,-27.6},{56.8,-27.6}}, color={0,0,127}));
  connect(product1.y, add4.u2) annotation (Line(points={{70.6,-24},{144,-24},{144,-3.6},{150.8,-3.6}}, color={0,0,127}));
  connect(add4.y, PMECH) annotation (Line(points={{164.6,0},{176,0}}, color={0,0,127}));
  connect(product1.u1, g.y) annotation (Line(points={{56.8,-20.4},{-16,-20.4},{-16,6},{-27.4,6}}, color={0,0,127}));
  connect(division.u2, g.y) annotation (Line(points={{-1.2,-1.6},{-16,-1.6},{-16,6},{-27.4,6}}, color={0,0,127}));
  connect(division.u1, add3.u1) annotation (Line(points={{-1.2,5.6},{-6,5.6},{-6,20},{76,20},{76,5.6},{80.8,5.6}}, color={0,0,127}));
  connect(Gain6.y, add4.u1) annotation (Line(points={{144.6,4},{150.8,4},{150.8,3.6}}, color={0,0,127}));
  connect(product2.y, Gain6.u) annotation (Line(points={{120.6,4},{130.8,4},{130.8,4}}, color={0,0,127}));
  connect(add3.y, product2.u2) annotation (Line(points={{94.6,2},{100,2},{100,0.4},{106.8,0.4}}, color={0,0,127}));
  connect(product2.u1, add2.u1) annotation (Line(points={{106.8,7.6},{100,7.6},{100,16},{36,16},{36,5.6},{40.8,5.6}}, color={0,0,127}));
  connect(simpleLead.y, Velocity_Limiter.u) annotation (Line(points={{-93.4,6},{-87.2,6}}, color={0,0,127}));
  connect(simpleLead.u, SimpleLag1.y) annotation (Line(points={{-107.2,6},{-113.4,6}}, color={0,0,127}));
  annotation (
    Diagram(coordinateSystem(preserveAspectRatio=false, extent={{-80,-60},{80,60}}), graphics={Rectangle(
          extent={{-190,32},{-12,-38}},
          lineColor={255,128,0},
          pattern=LinePattern.Dash,
          lineThickness=1),Text(
          extent={{-126,40},{-82,34}},
          lineColor={255,128,0},
          textStyle={TextStyle.Bold},
          textString="Governor System"),Rectangle(
          extent={{-8,32},{166,-38}},
          lineColor={85,170,255},
          pattern=LinePattern.Dash,
          lineThickness=1),Text(
          extent={{38,42},{98,32}},
          lineColor={85,170,255},
          textStyle={TextStyle.Bold},
          textString="Hydrauli Turbine System")}),
    Icon(coordinateSystem(extent={{-80,-60},{80,60}}, preserveAspectRatio=false), graphics={Text(
          extent={{-48,12},{52,-14}},
          lineColor={0,0,255},
          fillPattern=FillPattern.Solid,
          textString="HYGOV"),Text(
          extent={{-70,-20},{-42,-40}},
          lineColor={0,0,255},
          fillPattern=FillPattern.Solid,
          textString="PMECH0"),Rectangle(extent={{-80,60},{80,-60}}, lineColor={0,0,255}),Text(
          extent={{-78,34},{-40,26}},
          lineColor={0,0,255},
          textString="SPEED"),Text(
          extent={{52,6},{78,-8}},
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
end HYGOV;
