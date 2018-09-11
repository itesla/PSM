within iPSL.Examples.Controls.PSSE.ES;
model ESAC1A "SMIB system with one load and GENROE model"
  extends iPSL.Examples.SMIBpartial;
  extends Modelica.Icons.Example;
  iPSL.Electrical.Machines.PSSE.GENROE gENROE(
    M_b=100,
    Tpd0=5,
    Tppd0=0.07,
    Tpq0=0.9,
    Tppq0=0.09,
    H=4.28,
    D=0,
    Xd=1.84,
    Xq=1.75,
    Xpd=0.41,
    Xpq=0.6,
    Xppd=0.2,
    Xl=0.12,
    S10=0.11,
    S12=0.39,
    V_0=1,
    P_0=40,
    angle_0=4.046276,
    Q_0=5.416582,
    Xppq=0.2) annotation (Placement(transformation(extent={{-100,-20},{-60,20}})));
  Modelica.Blocks.Sources.Constant const(k=0) annotation (Placement(transformation(extent={{-28,-58},{-38,-48}})));
  Modelica.Blocks.Sources.Constant const1(k=-Modelica.Constants.inf) annotation (Placement(transformation(extent={{-28,-80},{-38,-70}})));
  Modelica.Blocks.Sources.Constant const2(k=Modelica.Constants.inf) annotation (Placement(transformation(extent={{-28,-100},{-38,-90}})));
  iPSL.Electrical.Controls.PSSE.ES.ESAC1A eSAC1A(
    T_R=0.04,
    T_B=2,
    T_C=10,
    K_A=400,
    T_A=0.02,
    V_AMAX=9,
    V_AMIN=-5.34,
    T_E=0.8,
    K_F=0.03,
    T_F=1,
    K_C=0.2,
    K_D=0.48,
    K_E=1,
    E_1=5.25,
    E_2=7,
    S_EE_1=0.03,
    S_EE_2=0.1,
    V_RMAX=3,
    V_RMIN=-3) annotation (Placement(transformation(extent={{-52,-58},{-106,-38}})));
  inner iPSL.Electrical.SystemBase SysData annotation (Placement(transformation(extent={{-100,80},{-40,100}})));
equation
  connect(gENROE.PMECH, gENROE.PMECH0) annotation (Line(points={{-99.6,10},{-110,10},{-110,26},{-48,26},{-48,-6},{-58.4,-6}}, color={0,0,127}));
  connect(eSAC1A.VOTHSG, const.y) annotation (Line(points={{-52.587,-53},{-52.587,-53},{-38.5,-53}}, color={0,0,127}));
  connect(gENROE.ETERM, eSAC1A.ECOMP) annotation (Line(points={{-58.4,10},{-32,10},{-32,-43},{-52.587,-43}}, color={0,0,127}));
  connect(eSAC1A.EFD0, gENROE.EFD0) annotation (Line(points={{-84.8696,-57},{-84.8696,-62},{-46,-62},{-46,-14},{-58.4,-14}}, color={0,0,127}));
  connect(gENROE.XADIFD, eSAC1A.XADIFD) annotation (Line(points={{-58.4,-18},{-44,-18},{-44,-64},{-77.7674,-64},{-77.7674,-57.15}}, color={0,0,127}));
  connect(eSAC1A.EFD, gENROE.EFD) annotation (Line(points={{-106.587,-48},{-110,-48},{-110,-10},{-99.6,-10}}, color={0,0,127}));
  connect(const1.y, eSAC1A.VUEL) annotation (Line(points={{-38.5,-75},{-63.7391,-75},{-63.7391,-57.1}}, color={0,0,127}));
  connect(const2.y, eSAC1A.VOEL) annotation (Line(points={{-38.5,-95},{-38.5,-95},{-70.7591,-95},{-70.7591,-57.15}}, color={0,0,127}));
  connect(gENROE.p, GEN1.p) annotation (Line(points={{-58,0},{-49,0},{-40,0}}, color={0,0,255}));
  annotation (Diagram(coordinateSystem(preserveAspectRatio=false, extent={{-100,-100},{100,100}})), Documentation(revisions="<html>
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
end ESAC1A;
