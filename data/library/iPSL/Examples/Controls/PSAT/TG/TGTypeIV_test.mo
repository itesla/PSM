within iPSL.Examples.Controls.PSAT.TG;
model TGTypeIV_test
extends Modelica.Icons.UnderConstruction;
  iPSL.Electrical.Machines.PSAT.Order3 Generator(
    P_0=0.16041,
    Q_0=0.12012,
    V_b=400,
    V_0=1,
    angle_0=0,
    Sn=20,
    Vn=400,
    ra=0.001,
    xd1=0.302,
    M=10,
    D=0,
    xd=1.9,
    Td10=8,
    xq=1.7) annotation (Placement(visible=true, transformation(
        origin={-17.3443,2.6244},
        extent={{-16.6557,-16.6244},{16.6557,16.6244}},
        rotation=0)));
  iPSL.Electrical.Loads.PSAT.LOADPQ pwLoadPQ1(
    P_0=0.08,
    Q_0=0.06,
    V_0=1,
    angle_0=0) annotation (Placement(visible=true, transformation(
        origin={79.147,14.3286},
        extent={{-10.0,-10.0},{10.0,10.0}},
        rotation=0)));
  iPSL.Electrical.Branches.PwLine pwLine2(
    B=0.001/2,
    G=0,
    R=0.01,
    X=0.1) annotation (Placement(visible=true, transformation(
        origin={25.8634,14.3286},
        extent={{-10.0,-10.0},{10.0,10.0}},
        rotation=0)));
  iPSL.Electrical.Branches.PwLine pwLine3(
    G=0,
    X=0.1,
    R=0.01,
    B=0.001/2) annotation (Placement(visible=true, transformation(
        origin={51.5,-12},
        extent={{-10.0,-10.0},{10.0,10.0}},
        rotation=0)));
  iPSL.Electrical.Branches.PwLine pwLine4(
    B=0.001/2,
    G=0,
    R=0.01,
    X=0.1) annotation (Placement(visible=true, transformation(
        origin={54.863,15.3286},
        extent={{-10.0,-10.0},{10.0,10.0}},
        rotation=0)));
  iPSL.Electrical.Controls.PSAT.TG.TGTypeIV tGModel4_1(
    Ki=0.105,
    Tg=0.2,
    Tp=0.04,
    delta=0.3,
    sigma=0.05,
    Tr=5,
    vmin=-0.1,
    vmax=0.1,
    gmax=1,
    gmin=0,
    Tw=1,
    a11=0.5,
    a13=1,
    a21=1.5,
    a23=1,
    Kp=1.163,
    Pref=0.080199,
    wref=1) annotation (Placement(transformation(extent={{-82,-14},{-56,6}})));
  iPSL.Electrical.Branches.PwLine pwLine1(
    B=0.001/2,
    G=0,
    R=0.01,
    X=0.1) annotation (Placement(visible=true, transformation(
        origin={26.863,-12.6714},
        extent={{-10.0,-10.0},{10.0,10.0}},
        rotation=0)));
  iPSL.Electrical.Buses.InfiniteBus infiniteBus(V_0=1, angle_0=0) annotation (Placement(transformation(
        extent={{-5,-5},{5,5}},
        rotation=180,
        origin={77,-11})));
  inner iPSL.Electrical.SystemBase SysData annotation (Placement(transformation(extent={{0,60},{46,80}})));
equation
  connect(pwLine4.n, pwLoadPQ1.p) annotation (Line(
      visible=true,
      origin={79.076,17.3286},
      points={{-17.213,-2},{-14.642,-2},{0.071,-2},{0.071,8}}));
  connect(pwLine2.n, pwLine4.p) annotation (Line(
      visible=true,
      origin={39.1134,16.8286},
      points={{-6.25,-2.5},{4.75,-2.5},{4.75,-1.5},{8.7496,-1.5}}));
  connect(Generator.p, pwLine2.p) annotation (Line(
      visible=true,
      origin={11.2254,11.7135},
      points={{-10.2484,-9.00658},{1.638,-9.00658},{1.638,2.6151},{7.638,2.6151}}));
  connect(Generator.vf0, Generator.vf) annotation (Line(
      points={{-30.6689,20.9112},{8,20.9112},{8,26},{-44,26},{-44,10.9366},{-34,10.9366}},
      color={0,0,127},
      smooth=Smooth.None));
  connect(Generator.w, tGModel4_1.w) annotation (Line(
      points={{0.97697,17.5864},{0.97697,-6},{8,-6},{8,-20},{-90,-20},{-90,-4.2},{-84.34,-4.2}},
      color={0,0,127},
      smooth=Smooth.None));
  connect(tGModel4_1.Pm, Generator.pm) annotation (Line(
      points={{-54.7,-4},{-56,-4},{-56,-5.6878},{-34,-5.6878}},
      color={0,0,127},
      smooth=Smooth.None));
  connect(pwLine1.p, pwLine2.p) annotation (Line(
      points={{19.863,-12.6714},{16,-12.6714},{16,14.3286},{18.8634,14.3286}},
      color={0,0,255},
      smooth=Smooth.None));
  connect(pwLine2.n, pwLine1.n) annotation (Line(
      points={{32.8634,14.3286},{38,14.3286},{38,-12.6714},{33.863,-12.6714}},
      color={0,0,255},
      smooth=Smooth.None));
  connect(pwLine1.n, pwLine3.p) annotation (Line(
      points={{33.863,-12.6714},{44,-12.6714},{44,-12},{44.5,-12}},
      color={0,0,255},
      smooth=Smooth.None));
  connect(pwLine3.n, infiniteBus.p) annotation (Line(
      points={{58.5,-12},{72,-12},{72,-11},{71.5,-11}},
      color={0,0,255},
      smooth=Smooth.None));
  annotation (Diagram(coordinateSystem(preserveAspectRatio=false, extent={{-100,-100},{100,100}}), graphics={Text(
          extent={{-96,42},{-22,30}},
          lineColor={0,0,255},
          textString="Wref perturbation with sine signal 5-10s"),Text(
          extent={{68,-18},{92,-24}},
          lineColor={0,0,255},
          textString="Infinite bus"),Text(
          extent={{70,32},{84,20}},
          lineColor={0,0,255},
          textString="PQ load")}), Documentation(revisions="<html>
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
end TGTypeIV_test;
