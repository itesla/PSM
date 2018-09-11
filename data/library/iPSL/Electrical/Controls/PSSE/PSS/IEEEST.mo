within iPSL.Electrical.Controls.PSSE.PSS;

model IEEEST "IEEE Stabilizing Model with single Input"
  parameter Real A_1 = 0 "Filter Coefficient";
  parameter Real A_2 = 0 "Filter Coefficient";
  parameter Real A_3 = 0 "Filter Coefficient";
  parameter Real A_4 = 0 "Filter Coefficient";
  parameter Real A_5 = 0 "Filter Coefficient";
  parameter Real A_6 = 0 "Filter Coefficient";
  parameter Real T_1 = 0 "Lead Time Constant";
  parameter Real T_2 = 0 "Lag Time Constant";
  parameter Real T_3 = 0 "Lead Time Constant";
  parameter Real T_4 = 0 "Lag Time Constant";
  parameter Real T_5 = 1.65 "Washout Time Constant";
  parameter Real T_6 = 1.65 "Washout Time Constant";
  parameter Real K_S = 6.2 "Stabilizer Gain";
  parameter Real L_SMAX = 0.26 "Output Limits";
  parameter Real L_SMIN = -0.1 "Output Limits";
  parameter Real V_CU = 999 "Output Limits";
  parameter Real V_CL = -999 "Output Limits";
  Modelica.Blocks.Continuous.TransferFunction Filter1_1(initType = Modelica.Blocks.Types.Init.InitialOutput, y_start = V_S0, a = a1, b = b) annotation(Placement(transformation(extent = {{-90, -30}, {-80, -20}})));
  iPSL.NonElectrical.Continuous.LeadLag T_1_T_2(K = 1, T1 = T_1, T2 = T_2, x_start = V_S0, y_start = V_S0) annotation(Placement(transformation(extent = {{40, -5}, {50, 5}})));
  iPSL.NonElectrical.Continuous.LeadLag T_3_T_4(K = 1, T1 = T_3, T2 = T_4, x_start = V_S0, y_start = V_S0) annotation(Placement(transformation(extent = {{60, -5}, {70, 5}})));
  Modelica.Blocks.Interfaces.RealOutput VOTHSG(start = 0) "PSS output signal" annotation(Placement(transformation(extent = {{120, -5}, {130, 5}}, rotation = 0), iconTransformation(extent = {{120, -10}, {140, 10}})));
  Modelica.Blocks.Interfaces.RealInput V_S(start = 0) "PSS input signal" annotation(Placement(transformation(extent = {{-125, -5}, {-115, 5}}, rotation = 0), iconTransformation(extent = {{-128, -30}, {-108, -10}})));
  Modelica.Blocks.Nonlinear.Limiter VSS(uMax = L_SMAX, uMin = L_SMIN) annotation(Placement(transformation(extent = {{100, -5}, {110, 5}})));
  Modelica.Blocks.Interfaces.RealInput V_CT "Compensated machine terminal voltage (pu)" annotation(Placement(transformation(extent = {{-125, 30}, {-115, 40}}, rotation = 0), iconTransformation(extent = {{-128, 10}, {-108, 30}})));
  Modelica.Blocks.Continuous.Derivative imDerivativeLag(T = T_6, y_start = 0, initType = Modelica.Blocks.Types.Init.InitialOutput, x_start = V_S0, k = K_S * T_5) annotation(Placement(transformation(extent = {{80, -5}, {90, 5}})));
protected
  Modelica.Blocks.Interfaces.RealOutput Vs "Connector of Real output signal" annotation(Placement(transformation(extent = {{113, -5}, {123, 5}}, rotation = 0), iconTransformation(extent = {{32, -8}, {36, -4}})));
  parameter Real V_S0(fixed = false);
  parameter Boolean bypass_filter2(fixed = false);
  parameter Boolean bypass_filter1(fixed = false);
  parameter Integer n1 = if A_1 == 0 and A_2 == 0 then 4 elseif A_2 == 0 then 2 else 3;
  parameter Integer n2 = if A_3 == 0 and A_4 == 0 then 4 elseif A_4 == 0 then 2 else 3;
  parameter Integer n3 = if A_6 == 0 and A_5 == 0 then 1 elseif A_6 == 0 then 2 else 3;
  parameter Real a1[n1](each fixed = false);
  parameter Real a2[n2](each fixed = false);
  parameter Real b[n3](each fixed = false);
  Modelica.Blocks.Continuous.TransferFunction Filter2_1(initType = Modelica.Blocks.Types.Init.InitialOutput, y_start = V_S0, b = {1}, a = a2) annotation(Placement(transformation(extent = {{-60, -30}, {-50, -20}})));
  Modelica.Blocks.Logical.Switch swith_filter2 annotation(Placement(transformation(extent = {{-30, -26}, {-20, -16}})));
  Modelica.Blocks.Sources.BooleanConstant booleanConstant(k = bypass_filter2) annotation(Placement(transformation(extent = {{-60, -50}, {-50, -40}})));
  Modelica.Blocks.Continuous.TransferFunction Filter1_2(initType = Modelica.Blocks.Types.Init.InitialOutput, y_start = V_S0, a = a1, b = {1}) annotation(Placement(transformation(extent = {{-90, 10}, {-80, 20}})));
  Modelica.Blocks.Continuous.TransferFunction Filter2_2(initType = Modelica.Blocks.Types.Init.InitialOutput, y_start = V_S0, a = a2, b = b) annotation(Placement(transformation(extent = {{-60, 10}, {-50, 20}})));
  Modelica.Blocks.Logical.Switch swith_filter1 annotation(Placement(transformation(extent = {{-75, -5}, {-65, 5}})));
  Modelica.Blocks.Sources.BooleanConstant booleanConstant1(k = bypass_filter1) annotation(Placement(transformation(extent = {{-95, -15}, {-85, -5}})));
  Modelica.Blocks.Logical.Switch swith_filter3 annotation(Placement(transformation(extent = {{20, -5}, {30, 5}})));
  Modelica.Blocks.MathBoolean.And and1(nu = 2) annotation(Placement(transformation(extent = {{-30, -13}, {-20, -3}})));
  Modelica.Blocks.Logical.Switch swith_filter4 annotation(Placement(transformation(extent = {{-6, -5}, {4, 5}})));
initial equation
  if n3 == 1 then
    b = {1};
  elseif n3 == 2 then
    b = {A_5, 1};
  else
    b = {A_6, A_5, 1};
  end if;
  if n1 == 4 then
    a1 = {1, 1, 1, 1};
    bypass_filter1 = true;
  elseif n1 == 3 then
    a1 = {A_2, A_1, 1};
    bypass_filter1 = false;
  else
    a1 = {A_1, 1};
    bypass_filter1 = false;
  end if;
  if n2 == 4 then
    a2 = {1, 1, 1, 1};
    bypass_filter2 = true;
  elseif n2 == 3 then
    a2 = {A_4, A_3, 1};
    bypass_filter2 = false;
  else
    a2 = {A_3, 1};
    bypass_filter2 = false;
  end if;
  V_S0 = V_S;
equation
  if V_CU == 0 and not V_CL == 0 then
    if V_CT > V_CL then
      VOTHSG = Vs;
    else
      VOTHSG = 0;
    end if;
  elseif V_CL == 0 and not V_CU == 0 then
    if V_CT < V_CU then
      VOTHSG = Vs;
    else
      VOTHSG = 0;
    end if;
  elseif V_CU == 0 and V_CL == 0 then
    VOTHSG = Vs;
  elseif V_CT > V_CL and V_CT < V_CU then
    VOTHSG = Vs;
  else
    VOTHSG = 0;
  end if;
  connect(V_S, Filter1_1.u) annotation(Line(points = {{-120, 0}, {-100, 0}, {-100, -25}, {-91, -25}}, color = {0, 0, 127}, smooth = Smooth.None));
  connect(T_1_T_2.y, T_3_T_4.u) annotation(Line(points = {{50.5, 0}, {59, 0}}, color = {0, 0, 127}));
  connect(imDerivativeLag.y, VSS.u) annotation(Line(points = {{90.5, 0}, {99, 0}}, color = {0, 0, 127}));
  connect(T_3_T_4.y, imDerivativeLag.u) annotation(Line(points = {{70.5, 0}, {74, 0}, {79, 0}}, color = {0, 0, 127}));
  connect(Filter1_1.y, Filter2_1.u) annotation(Line(points = {{-79.5, -25}, {-72, -25}, {-61, -25}}, color = {0, 0, 127}));
  connect(swith_filter2.u3, Filter2_1.y) annotation(Line(points = {{-31, -25}, {-31, -25}, {-49.5, -25}}, color = {0, 0, 127}));
  connect(swith_filter2.u1, Filter2_1.u) annotation(Line(points = {{-31, -17}, {-66, -17}, {-66, -25}, {-61, -25}}, color = {0, 0, 127}));
  connect(booleanConstant.y, swith_filter2.u2) annotation(Line(points = {{-49.5, -45}, {-40, -45}, {-40, -21}, {-31, -21}}, color = {255, 0, 255}));
  connect(Filter1_2.u, Filter1_1.u) annotation(Line(points = {{-91, 15}, {-100, 15}, {-100, -25}, {-91, -25}}, color = {0, 0, 127}));
  connect(swith_filter1.y, Filter2_2.u) annotation(Line(points = {{-64.5, 0}, {-63, 0}, {-63, 15}, {-61, 15}}, color = {0, 0, 127}));
  connect(swith_filter1.u1, Filter1_1.u) annotation(Line(points = {{-76, 4}, {-95, 4}, {-95, 15}, {-100, 15}, {-100, -25}, {-91, -25}}, color = {0, 0, 127}));
  connect(swith_filter1.u3, Filter1_2.y) annotation(Line(points = {{-76, -4}, {-78, -4}, {-78, 15}, {-79.5, 15}}, color = {0, 0, 127}));
  connect(booleanConstant1.y, swith_filter1.u2) annotation(Line(points = {{-84.5, -10}, {-80, -10}, {-80, 0}, {-76, 0}}, color = {255, 0, 255}));
  connect(and1.y, swith_filter3.u2) annotation(Line(points = {{-19.25, -8}, {15, -8}, {15, 0}, {19, 0}}, color = {255, 0, 255}));
  connect(and1.u[1], swith_filter2.u2) annotation(Line(points = {{-30, -6.25}, {-35, -6.25}, {-40, -6.25}, {-40, -6}, {-40, -21}, {-31, -21}}, color = {255, 0, 255}));
  connect(and1.u[2], swith_filter1.u2) annotation(Line(points = {{-30, -9.75}, {-52, -9.75}, {-52, -10}, {-80, -10}, {-80, 0}, {-76, 0}}, color = {255, 0, 255}));
  connect(swith_filter3.u1, Filter1_1.u) annotation(Line(points = {{19, 4}, {15, 4}, {15, 30}, {-110, 30}, {-110, 0}, {-100, 0}, {-100, -25}, {-91, -25}}, color = {0, 0, 127}));
  connect(swith_filter4.y, swith_filter3.u3) annotation(Line(points = {{4.5, 0}, {12, 0}, {12, -4}, {19, -4}}, color = {0, 0, 127}));
  connect(swith_filter4.u2, swith_filter1.u2) annotation(Line(points = {{-7, 0}, {-52, 0}, {-52, -10}, {-80, -10}, {-80, 0}, {-76, 0}}, color = {255, 0, 255}));
  connect(Filter2_2.y, swith_filter4.u1) annotation(Line(points = {{-49.5, 15}, {-20, 15}, {-20, 4}, {-7, 4}}, color = {0, 0, 127}));
  connect(swith_filter2.y, swith_filter4.u3) annotation(Line(points = {{-19.5, -21}, {-12, -21}, {-12, -4}, {-7, -4}}, color = {0, 0, 127}));
  connect(swith_filter3.y, T_1_T_2.u) annotation(Line(points = {{30.5, 0}, {39, 0}}, color = {0, 0, 127}));
  connect(VSS.y, Vs) annotation(Line(points = {{110.5, 0}, {118, 0}, {118, 0}}, color = {0, 0, 127}));
  annotation(Diagram(coordinateSystem(preserveAspectRatio = false, extent = {{-120, -40}, {120, 40}}, grid = {1, 1})), Icon(coordinateSystem(preserveAspectRatio = false, extent = {{-120, -40}, {120, 40}}, grid = {1, 1}), graphics = {Rectangle(extent = {{-120, 40}, {120, -40}}, lineColor = {0, 0, 255}), Text(extent = {{-46, 40}, {54, -44}}, lineColor = {0, 0, 255}, textString = "IEEEST"), Text(extent = {{-106, -10}, {-84, -30}}, lineColor = {0, 0, 255}, textString = "V_S"), Text(extent = {{84, 14}, {118, -16}}, lineColor = {0, 0, 255}, textString = "VOTHSG"), Text(extent = {{-106, 32}, {-78, 8}}, lineColor = {0, 0, 255}, textString = "V_CT")}), Documentation(info = "<html>
<table cellspacing=\"1\" cellpadding=\"1\" border=\"1\"><tr>
<td align=center  width=50%><p>Development level</p></td>
<td align=center width=25% bgcolor=yellow><p> 2 </p></td>
</tr> 
</table> 
<p></p>  
<table cellspacing=\"1\" cellpadding=\"1\" border=\"1\">
<tr>
<td><p>Reference</p></td>
<td>PSS/E Manual</td>
</tr>
<tr>
<td><p>Last update</p></td>
<td>Unknown</td>
</tr>
<tr>
<td><p>Author</p></td>
<td><p>Mengjia Zhang,SmarTS Lab, KTH Royal Institute of Technology</p></td>
</tr>
<tr>
<td><p>Contact</p></td>
<td><p><a href=\"mailto:luigiv@kth.se\">luigiv@kth.se</a></p></td>
</tr>
</table>
</html>", revisions = "<html>
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
</html>"));
end IEEEST;
