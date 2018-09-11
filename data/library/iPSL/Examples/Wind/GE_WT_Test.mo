within iPSL.Examples.Wind;
model GE_WT_Test
  extends Modelica.Icons.Example;

  iPSL.Electrical.Wind.GE.Type_3.GE_WT GE_WT_init1 annotation (Placement(visible=true, transformation(
        origin={75.0,1.7487},
        extent={{-10.0,-10.0},{10.0,10.0}},
        rotation=0)));
  iPSL.Electrical.Buses.InfiniteBus infBus2(angle_0=-0.000216626610049175, V_0=1.05999999985841)
    annotation (Placement(visible=true, transformation(
        origin={-42.5798,1.9703},
        extent={{-10.0,-10.0},{10.0,10.0}},
        rotation=0)));
  iPSL.Electrical.Branches.PwLine pwLine2(
    R=0.009,
    X=0.065,
    B=0.063,
    G=0) annotation (Placement(visible=true, transformation(
        origin={-1.6888,1.6478},
        extent={{-10.0,-10.0},{10.0,10.0}},
        rotation=0)));
  iPSL.Electrical.Branches.PwLine pwLine3(
    R=0.0006,
    X=0.0333,
    G=0,
    B=0) annotation (Placement(visible=true, transformation(
        origin={16.9703,1.7398},
        extent={{-10.0,-10.0},{10.0,10.0}},
        rotation=0)));
  iPSL.Electrical.Branches.PwLine pwLine4(
    R=0.05,
    X=0.015,
    B=0.045,
    G=0) annotation (Placement(visible=true, transformation(
        origin={35.0,1.6888},
        extent={{-10.0,-10.0},{10.0,10.0}},
        rotation=0)));
  iPSL.Electrical.Branches.PwLine pwLine5(
    R=0.00222,
    X=0.0222,
    G=0,
    B=0) annotation (Placement(visible=true, transformation(
        origin={53.0297,1.8373},
        extent={{-10.0,-10.0},{10.0,10.0}},
        rotation=0)));
  iPSL.Electrical.Wind.WindGenerator windGenerator1 annotation (Placement(visible=true, transformation(
        origin={50.0,17.493},
        extent={{-10.0,-10.0},{10.0,10.0}},
        rotation=0)));
  iPSL.Electrical.Branches.PwLine pwLine1(
    R=0.013,
    X=0.13,
    G=0,
    B=0) annotation (Placement(visible=true, transformation(
        origin={-20.0,1.7863},
        extent={{-10.0,-10.0},{10.0,10.0}},
        rotation=0)));
  iPSL.Electrical.Events.PwFault pwFault1(
    R=1/99999.999,
    startTime=10,
    endTime=10.1,
    X=1/99999.999) annotation (Placement(visible=true, transformation(
        origin={0.9428,15.0},
        extent={{-14.0572,-11.7143},{14.0572,11.7143}},
        rotation=0)));
equation
  connect(infBus2.p, pwLine1.p) annotation (Line(
      visible=true,
      origin={-30.0532,1.8477},
      points={{-1.5266,0.1226},{-1.5266,-0.0614},{3.0532,-0.0614}},
      color={0,0,255}));
  connect(pwLine1.n, pwFault1.p) annotation (Line(
      visible=true,
      origin={-11.6324,11.3764},
      points={{-1.3676,-9.5901},{-1.3676,3.6236},{-3.82487,3.6236}},
      color={0,0,255}));
  connect(pwLine1.n, pwLine2.p) annotation (Line(
      visible=true,
      origin={-11.5629,1.694},
      points={{-1.4371,0.0923},{-1.4371,-0.0462},{2.8741,-0.0462}},
      color={0,0,255}));
  connect(pwLine2.n, pwLine3.p) annotation (Line(
      visible=true,
      origin={6.8642,1.7091},
      points={{-1.553,-0.0613},{-1.553,0.0307},{3.1061,0.0307}},
      color={0,0,255}));
  connect(windGenerator1.Vw, GE_WT_init1.Wind_Speed)
    annotation (Line(
      visible=true,
      origin={70.3333,14.8446},
      points={{-9.3333,2.6484},{4.6667,2.6484},{4.6667,-5.29687}},
      color={0,0,127}));
  connect(pwLine5.n, GE_WT_init1.pwPin1) annotation (Line(
      visible=true,
      origin={61.3531,1.7782},
      points={{-1.3234,0.0591},{-1.3234,-0.0295},{2.6469,-0.0295}},
      color={0,0,255}));
  connect(pwLine4.n, pwLine5.p) annotation (Line(
      visible=true,
      origin={43.3432,1.7878},
      points={{-1.3432,-0.099},{-1.3432,0.0495},{2.6865,0.0495}},
      color={0,0,255}));
  connect(pwLine3.n, pwLine4.p) annotation (Line(
      visible=true,
      origin={25.3135,1.7058},
      points={{-1.3432,0.034},{-1.3432,-0.017},{2.6865,-0.017}},
      color={0,0,255}));
  annotation (Diagram(coordinateSystem(
        extent={{-148.5,-105},{148.5,105}},
        preserveAspectRatio=false,
        initialScale=0.1,
        grid={5,5}), graphics), Documentation(revisions="<html>
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
end GE_WT_Test;
