within ;
model Nordic32
  parameter Real SNREF = 100.0;
  Modelica.Blocks.Interfaces.RealOutput omegaRef;


// BUSES
  iPSL.Electrical.Buses.Bus bus__N1011____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N1012____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N1013____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N1014____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N1021____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N1022____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N1041____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N1042____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N1043____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N1044____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N1045____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N2031____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N2032____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N4011____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N4012____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N4021____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N4022____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N4031____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N4032____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N4041____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N4042____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N4043____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N4044____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N4045____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N4046____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N4047____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N4051____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N4061____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N4062____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N4063____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N4071____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N4072____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__NG10_____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__NG11_____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__NG12_____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__NG13_____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__NG14_____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__NG15_____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__NG16_____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__NG17_____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__NG18_____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__NG19_____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__NG1______TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__NG20_____TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__NG2______TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__NG3______TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__NG4______TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__NG5______TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__NG6______TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__NG7______TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__NG8______TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__NG9______TN (
	 V_0 = 1.0,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));

// LOADS
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__N1011____EC (
	 V_0 = 1.0,
	 P_0 = 200.0,
	 Q_0 = 80.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__N1012____EC (
	 V_0 = 1.0,
	 P_0 = 300.0,
	 Q_0 = 100.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__N1013____EC (
	 V_0 = 1.0,
	 P_0 = 100.0,
	 Q_0 = 40.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__N1022____EC (
	 V_0 = 1.0,
	 P_0 = 280.0,
	 Q_0 = 95.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__N1041____EC (
	 V_0 = 1.0,
	 P_0 = 400.0,
	 Q_0 = 180.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__N1042____EC (
	 V_0 = 1.0,
	 P_0 = 330.0,
	 Q_0 = 90.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__N1043____EC (
	 V_0 = 1.0,
	 P_0 = 260.0,
	 Q_0 = 100.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__N1044____EC (
	 V_0 = 1.0,
	 P_0 = 840.0,
	 Q_0 = 300.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__N1045____EC (
	 V_0 = 1.0,
	 P_0 = 720.0,
	 Q_0 = 230.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__N2031____EC (
	 V_0 = 1.0,
	 P_0 = 100.0,
	 Q_0 = 30.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__N2032____EC (
	 V_0 = 1.0,
	 P_0 = 200.0,
	 Q_0 = 50.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__N4041____EC (
	 V_0 = 1.0,
	 P_0 = 540.0,
	 Q_0 = 160.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__N4042____EC (
	 V_0 = 1.0,
	 P_0 = 400.0,
	 Q_0 = 149.4,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__N4043____EC (
	 V_0 = 1.0,
	 P_0 = 900.0,
	 Q_0 = 303.2,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__N4046____EC (
	 V_0 = 1.0,
	 P_0 = 700.0,
	 Q_0 = 250.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__N4047____EC (
	 V_0 = 1.0,
	 P_0 = 100.0,
	 Q_0 = 50.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__N4051____EC (
	 V_0 = 1.0,
	 P_0 = 800.0,
	 Q_0 = 302.4,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__N4061____EC (
	 V_0 = 1.0,
	 P_0 = 500.0,
	 Q_0 = 149.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__N4062____EC (
	 V_0 = 1.0,
	 P_0 = 300.0,
	 Q_0 = 100.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__N4063____EC (
	 V_0 = 1.0,
	 P_0 = 590.0,
	 Q_0 = 300.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__N4071____EC (
	 V_0 = 1.0,
	 P_0 = 300.0,
	 Q_0 = 100.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__N4072____EC (
	 V_0 = 1.0,
	 P_0 = 2000.0,
	 Q_0 = 500.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));

// FIXED TRANSFORMERS
  iPSL.Electrical.Branches.Eurostag.PwTransformer_2 trafo__N1011____N4011____1_PT (
	 R = 0.0,
	 X = 0.00799998,
	 G = 0.0,
	 B = 0.0,
	 r = 1.0526
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwTransformer_2 trafo__N1012____N4012____1_PT (
	 R = 0.0,
	 X = 0.00799998,
	 G = 0.0,
	 B = 0.0,
	 r = 1.0526
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwTransformer_2 trafo__N1022____N4022____1_PT (
	 R = 0.0,
	 X = 0.012000032,
	 G = 0.0,
	 B = 0.0,
	 r = 1.0753
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwTransformer_2 trafo__N1044____N4044____1_PT (
	 R = 0.0,
	 X = 0.009999976,
	 G = 0.0,
	 B = 0.0,
	 r = 0.97089994
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwTransformer_2 trafo__N1044____N4044____2_PT (
	 R = 0.0,
	 X = 0.009999976,
	 G = 0.0,
	 B = 0.0,
	 r = 0.97089994
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwTransformer_2 trafo__N1045____N4045____1_PT (
	 R = 0.0,
	 X = 0.009999999,
	 G = 0.0,
	 B = 0.0,
	 r = 0.9615
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwTransformer_2 trafo__N1045____N4045____2_PT (
	 R = 0.0,
	 X = 0.009999999,
	 G = 0.0,
	 B = 0.0,
	 r = 0.9615
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwTransformer_2 trafo__N2031____N4031____1_PT (
	 R = 0.0,
	 X = 0.012000002,
	 G = 0.0,
	 B = 0.0,
	 r = 1.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwTransformer_2 trafo__NG10_____N4012____1_PT (
	 R = 0.0,
	 X = 0.018750018,
	 G = 0.0,
	 B = 0.0,
	 r = 0.95239997
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwTransformer_2 trafo__NG11_____N4021____1_PT (
	 R = 0.0,
	 X = 0.049999885,
	 G = 0.0,
	 B = 0.0,
	 r = 0.95239997
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwTransformer_2 trafo__NG12_____N4031____1_PT (
	 R = 0.0,
	 X = 0.042859863,
	 G = 0.0,
	 B = 0.0,
	 r = 0.95239997
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwTransformer_2 trafo__NG13_____N4041____1_PT (
	 R = 0.0,
	 X = 0.033329986,
	 G = 0.0,
	 B = 0.0,
	 r = 0.95239997
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwTransformer_2 trafo__NG14_____N4042____1_PT (
	 R = 0.0,
	 X = 0.021430014,
	 G = 0.0,
	 B = 0.0,
	 r = 0.95239997
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwTransformer_2 trafo__NG15_____N4047____1_PT (
	 R = 0.0,
	 X = 0.012500012,
	 G = 0.0,
	 B = 0.0,
	 r = 0.95239997
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwTransformer_2 trafo__NG16_____N4051____1_PT (
	 R = 0.0,
	 X = 0.021430014,
	 G = 0.0,
	 B = 0.0,
	 r = 0.95239997
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwTransformer_2 trafo__NG17_____N4062____1_PT (
	 R = 0.0,
	 X = 0.024999984,
	 G = 0.0,
	 B = 0.0,
	 r = 0.95239997
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwTransformer_2 trafo__NG18_____N4063____1_PT (
	 R = 0.0,
	 X = 0.012500012,
	 G = 0.0,
	 B = 0.0,
	 r = 0.95239997
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwTransformer_2 trafo__NG19_____N4071____1_PT (
	 R = 0.0,
	 X = 0.030000012,
	 G = 0.0,
	 B = 0.0,
	 r = 0.95239997
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwTransformer_2 trafo__NG1______N1012____1_PT (
	 R = 0.0,
	 X = 0.018750003,
	 G = 0.0,
	 B = 0.0,
	 r = 1.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwTransformer_2 trafo__NG20_____N4072____1_PT (
	 R = 0.0,
	 X = 0.0033299997,
	 G = 0.0,
	 B = 0.0,
	 r = 0.95239997
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwTransformer_2 trafo__NG2______N1013____1_PT (
	 R = 0.0,
	 X = 0.024999999,
	 G = 0.0,
	 B = 0.0,
	 r = 1.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwTransformer_2 trafo__NG3______N1014____1_PT (
	 R = 0.0,
	 X = 0.02143,
	 G = 0.0,
	 B = 0.0,
	 r = 1.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwTransformer_2 trafo__NG4______N1021____1_PT (
	 R = 0.0,
	 X = 0.024999999,
	 G = 0.0,
	 B = 0.0,
	 r = 1.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwTransformer_2 trafo__NG5______N1022____1_PT (
	 R = 0.0,
	 X = 0.060000177,
	 G = 0.0,
	 B = 0.0,
	 r = 0.95239997
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwTransformer_2 trafo__NG6______N1042____1_PT (
	 R = 0.0,
	 X = 0.03749999,
	 G = 0.0,
	 B = 0.0,
	 r = 0.95239997
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwTransformer_2 trafo__NG7______N1043____1_PT (
	 R = 0.0,
	 X = 0.074999824,
	 G = 0.0,
	 B = 0.0,
	 r = 0.95239997
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwTransformer_2 trafo__NG8______N2032____1_PT (
	 R = 0.0,
	 X = 0.01765001,
	 G = 0.0,
	 B = 0.0,
	 r = 0.9524
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwTransformer_2 trafo__NG9______N4011____1_PT (
	 R = 0.0,
	 X = 0.015000006,
	 G = 0.0,
	 B = 0.0,
	 r = 0.95239997
	 ) annotation (Placement(transformation()));

// LINES
  iPSL.Electrical.Branches.PwLine_2 line__N1011____N1013____1_AC (
	 R = 0.010000001,
	 X = 0.07,
	 G = 0.0,
	 B = 0.0069999965
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N1011____N1013____2_AC (
	 R = 0.010000001,
	 X = 0.07,
	 G = 0.0,
	 B = 0.0069999965
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N1012____N1014____1_AC (
	 R = 0.0139999995,
	 X = 0.09,
	 G = 0.0,
	 B = 0.00900001
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N1012____N1014____2_AC (
	 R = 0.0139999995,
	 X = 0.09,
	 G = 0.0,
	 B = 0.00900001
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N1013____N1014____1_AC (
	 R = 0.0069999998,
	 X = 0.049999997,
	 G = 0.0,
	 B = 0.0050000004
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N1013____N1014____2_AC (
	 R = 0.0069999998,
	 X = 0.049999997,
	 G = 0.0,
	 B = 0.0050000004
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N1021____N1022____1_AC (
	 R = 0.030000001,
	 X = 0.19999999,
	 G = 0.0,
	 B = 0.015150005
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N1021____N1022____2_AC (
	 R = 0.030000001,
	 X = 0.19999999,
	 G = 0.0,
	 B = 0.015150005
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N1041____N1043____1_AC (
	 R = 0.010000001,
	 X = 0.060000002,
	 G = 0.0,
	 B = 0.0059999987
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N1041____N1043____2_AC (
	 R = 0.010000001,
	 X = 0.060000002,
	 G = 0.0,
	 B = 0.0059999987
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N1041____N1045____1_AC (
	 R = 0.015000001,
	 X = 0.120000005,
	 G = 0.0,
	 B = 0.0125
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N1041____N1045____2_AC (
	 R = 0.015000001,
	 X = 0.120000005,
	 G = 0.0,
	 B = 0.0125
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N1042____N1044____1_AC (
	 R = 0.038,
	 X = 0.28,
	 G = 0.0,
	 B = 0.030000035
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N1042____N1044____2_AC (
	 R = 0.038,
	 X = 0.28,
	 G = 0.0,
	 B = 0.030000035
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N1042____N1045____1_AC (
	 R = 0.049999997,
	 X = 0.3,
	 G = 0.0,
	 B = 0.030000035
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N1043____N1044____1_AC (
	 R = 0.010000001,
	 X = 0.080000006,
	 G = 0.0,
	 B = 0.008000004
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N1043____N1044____2_AC (
	 R = 0.010000001,
	 X = 0.080000006,
	 G = 0.0,
	 B = 0.008000004
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N2031____N2032____1_AC (
	 R = 0.012,
	 X = 0.09,
	 G = 0.0,
	 B = 0.0076000104
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N2031____N2032____2_AC (
	 R = 0.012,
	 X = 0.09,
	 G = 0.0,
	 B = 0.0076000104
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N4011____N4012____1_AC (
	 R = 0.001,
	 X = 0.008,
	 G = 0.0,
	 B = 0.100499995
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N4011____N4021____1_AC (
	 R = 0.006,
	 X = 0.06,
	 G = 0.0,
	 B = 0.9
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N4011____N4022____1_AC (
	 R = 0.004,
	 X = 0.04,
	 G = 0.0,
	 B = 0.6006
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N4011____N4071____1_AC (
	 R = 0.005,
	 X = 0.045,
	 G = 0.0,
	 B = 0.70000005
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N4012____N4022____1_AC (
	 R = 0.004,
	 X = 0.035,
	 G = 0.0,
	 B = 0.525
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N4012____N4071____1_AC (
	 R = 0.005,
	 X = 0.05,
	 G = 0.0,
	 B = 0.7489
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N4021____N4032____1_AC (
	 R = 0.004,
	 X = 0.04,
	 G = 0.0,
	 B = 0.6006
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N4021____N4042____1_AC (
	 R = 0.01,
	 X = 0.06,
	 G = 0.0,
	 B = 1.5004
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N4022____N4031____1_AC (
	 R = 0.004,
	 X = 0.04,
	 G = 0.0,
	 B = 0.6006
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N4022____N4031____2_AC (
	 R = 0.004,
	 X = 0.04,
	 G = 0.0,
	 B = 0.6006
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N4031____N4032____1_AC (
	 R = 0.001,
	 X = 0.01,
	 G = 0.0,
	 B = 0.15079999
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N4031____N4041____1_AC (
	 R = 0.006,
	 X = 0.04,
	 G = 0.0,
	 B = 1.198904
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N4031____N4041____2_AC (
	 R = 0.006,
	 X = 0.04,
	 G = 0.0,
	 B = 1.198904
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N4032____N4042____1_AC (
	 R = 0.01,
	 X = 0.04,
	 G = 0.0,
	 B = 1.000296
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N4032____N4044____1_AC (
	 R = 0.006,
	 X = 0.05,
	 G = 0.0,
	 B = 1.2
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N4041____N4044____1_AC (
	 R = 0.003,
	 X = 0.03,
	 G = 0.0,
	 B = 0.45
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N4041____N4061____1_AC (
	 R = 0.006,
	 X = 0.045,
	 G = 0.0,
	 B = 0.65000004
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N4042____N4043____1_AC (
	 R = 0.002,
	 X = 0.015,
	 G = 0.0,
	 B = 0.24880001
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N4042____N4044____1_AC (
	 R = 0.002,
	 X = 0.02,
	 G = 0.0,
	 B = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N4043____N4044____1_AC (
	 R = 0.001,
	 X = 0.01,
	 G = 0.0,
	 B = 0.15
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N4043____N4046____1_AC (
	 R = 0.001,
	 X = 0.01,
	 G = 0.0,
	 B = 0.15
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N4043____N4047____1_AC (
	 R = 0.002,
	 X = 0.02,
	 G = 0.0,
	 B = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N4044____N4045____1_AC (
	 R = 0.002,
	 X = 0.02,
	 G = 0.0,
	 B = 0.29909998
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N4044____N4045____2_AC (
	 R = 0.002,
	 X = 0.02,
	 G = 0.0,
	 B = 0.29909998
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N4045____N4051____1_AC (
	 R = 0.004,
	 X = 0.04,
	 G = 0.0,
	 B = 0.6
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N4045____N4051____2_AC (
	 R = 0.004,
	 X = 0.04,
	 G = 0.0,
	 B = 0.6
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N4045____N4062____1_AC (
	 R = 0.011,
	 X = 0.08,
	 G = 0.0,
	 B = 1.2
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N4046____N4047____1_AC (
	 R = 0.001,
	 X = 0.015,
	 G = 0.0,
	 B = 0.25
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N4061____N4062____1_AC (
	 R = 0.002,
	 X = 0.02,
	 G = 0.0,
	 B = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N4062____N4063____1_AC (
	 R = 0.003,
	 X = 0.03,
	 G = 0.0,
	 B = 0.45
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N4062____N4063____2_AC (
	 R = 0.003,
	 X = 0.03,
	 G = 0.0,
	 B = 0.45
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N4071____N4072____1_AC (
	 R = 0.003,
	 X = 0.03,
	 G = 0.0,
	 B = 1.5004
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N4071____N4072____2_AC (
	 R = 0.003,
	 X = 0.03,
	 G = 0.0,
	 B = 1.5004
	 ) annotation (Placement(transformation()));

// CAPACITORS
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__N1022____SC (
	 B = 0.500000006519258,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__N1041____SC (
	 B = 1.9999967208132148,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__N1043____SC (
	 B = 1.500000019557774,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__N1044____SC (
	 B = 1.9999967208132148,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__N1045____SC (
	 B = 1.9999967208132148,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__N4012____SC (
	 B = -0.9999999776482582,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__N4041____SC (
	 B = 1.9999999552965164,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__N4043____SC (
	 B = 1.9999999552965164,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__N4046____SC (
	 B = 0.9999999776482582,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__N4051____SC (
	 B = 0.9999999776482582,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__N4071____SC (
	 B = -3.999999910593033,
	 nsteps = 1
	 ) annotation (Placement(transformation()));

// FIXED INJECTIONS

// GENERATORS
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__G10______SM (
	 SNREF = SNREF, 
	 ur0 = 1.0, 
	 ui0 = 0.0, 
	 transformerIncluded = false, 
	 Saturated = false, 
	 TX = 0, 
	 md = 0, 
	 XPQ = 0, 
	 TPQ0 = 0, 
	 mq = 0, 
	 snd = 0, 
	 snq = 0, 
	 init_theta = 0.4069114786283813, 
	 init_omega = 1.0, 
	 init_efd = 1.559748294665931, 
	 WLMDVPu = 0.9500000000000001, 
	 init_lambdaad = -1.005811296631593, 
	 init_cm = 0.75, 
	 init_lambdaq1 = 0.3109659803387503, 
	 init_lambdaq2 = 0.3109659803387503, 
	 init_iq = 4.523141532200004, 
	 init_id = 4.66473261502601, 
	 init_lambdaaq = 0.3109659803387503, 
	 init_lambdad = -1.005811296631593, 
	 init_lambdaf = -1.204697988877575,
	 PNALT = 0.0,
	 IENR = 3,
	 DIn = 0.0,
	 HIn = 3.0,
	 TSD0 = 0.05,
	 XPD = 0.25,
	 TPD0 = 5.0,
	 rStatIn = 0.0,
	 TSQ0 = 0.1,
	 SN = 800.0,
	 XSD = 0.2,
	 XD = 1.1,
	 lStatIn = 0.15,
	 XSQ = 0.2,
	 XQ = 0.7,
	 PN = 800.0,
	 IWLMDV = 2
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__G11______SM (
	 SNREF = SNREF, 
	 ur0 = 1.0, 
	 ui0 = 0.0, 
	 transformerIncluded = false, 
	 Saturated = false, 
	 TX = 0, 
	 md = 0, 
	 XPQ = 0, 
	 TPQ0 = 0, 
	 mq = 0, 
	 snd = 0, 
	 snq = 0, 
	 init_theta = 0.4794833678822945, 
	 init_omega = 1.0, 
	 init_efd = 1.480057428308171, 
	 WLMDVPu = 0.9500000000000001, 
	 init_lambdaad = -0.9680730180704422, 
	 init_cm = 0.8333333333333333, 
	 init_lambdaq1 = 0.3624663930407691, 
	 init_lambdaq2 = 0.3624663930407691, 
	 init_iq = 1.977089416586014, 
	 init_id = 1.616792874434933, 
	 init_lambdaaq = 0.3624663930407691, 
	 init_lambdad = -0.9680730180704422, 
	 init_lambdaf = -1.156798165106653,
	 PNALT = 0.0,
	 IENR = 3,
	 DIn = 0.0,
	 HIn = 3.0,
	 TSD0 = 0.05,
	 XPD = 0.25,
	 TPD0 = 5.0,
	 rStatIn = 0.0,
	 TSQ0 = 0.1,
	 SN = 300.0,
	 XSD = 0.2,
	 XD = 1.1,
	 lStatIn = 0.15,
	 XSQ = 0.2,
	 XQ = 0.7,
	 PN = 300.0,
	 IWLMDV = 2
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__G12______SM (
	 SNREF = SNREF, 
	 ur0 = 1.0, 
	 ui0 = 0.0, 
	 transformerIncluded = false, 
	 Saturated = false, 
	 TX = 0, 
	 md = 0, 
	 XPQ = 0, 
	 TPQ0 = 0, 
	 mq = 0, 
	 snd = 0, 
	 snq = 0, 
	 init_theta = 0.4479633498548661, 
	 init_omega = 1.0, 
	 init_efd = 1.734356962243129, 
	 WLMDVPu = 0.9500000000000001, 
	 init_lambdaad = -1.01492554168245, 
	 init_cm = 0.8857142857142858, 
	 init_lambdaq1 = 0.3403170080760746, 
	 init_lambdaq2 = 0.3403170080760746, 
	 init_iq = 2.165653687756839, 
	 init_id = 2.650536812591973, 
	 init_lambdaaq = 0.3403170080760746, 
	 init_lambdad = -1.01492554168245, 
	 init_lambdaf = -1.23607694192694,
	 PNALT = 0.0,
	 IENR = 3,
	 DIn = 0.0,
	 HIn = 3.0,
	 TSD0 = 0.05,
	 XPD = 0.25,
	 TPD0 = 5.0,
	 rStatIn = 0.0,
	 TSQ0 = 0.1,
	 SN = 350.0,
	 XSD = 0.2,
	 XD = 1.1,
	 lStatIn = 0.15,
	 XSQ = 0.2,
	 XQ = 0.7,
	 PN = 350.0,
	 IWLMDV = 2
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__G13______SM (
	 SNREF = SNREF, 
	 ur0 = 1.0, 
	 ui0 = 0.0, 
	 transformerIncluded = false, 
	 Saturated = false, 
	 TX = 0, 
	 md = 0, 
	 XPQ = 0, 
	 TPQ0 = 0, 
	 mq = 0, 
	 snd = 0, 
	 snq = 0, 
	 init_theta = 0.0, 
	 init_omega = 1.0, 
	 init_efd = 1.180988323, 
	 WLMDVPu = 1.4, 
	 init_lambdaad = -1.017514999, 
	 init_cm = 0.0, 
	 init_lambdaq1 = 0.0, 
	 init_lambdaq2 = 0.0, 
	 init_iq = 0.0, 
	 init_id = 0.35029998, 
	 init_lambdaaq = 0.0, 
	 init_lambdad = -1.017514999, 
	 init_lambdaf = -1.169283401210729,
	 PNALT = 0.0,
	 IENR = 3,
	 DIn = 0.0,
	 HIn = 2.0,
	 TSD0 = 0.05,
	 XPD = 0.3,
	 TPD0 = 7.0,
	 rStatIn = 0.0,
	 TSQ0 = 0.1,
	 SN = 300.0,
	 XSD = 0.2,
	 XD = 1.55,
	 lStatIn = 0.15,
	 XSQ = 0.2,
	 XQ = 1.0,
	 PN = 300.0,
	 IWLMDV = 2
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__G14______SM (
	 SNREF = SNREF, 
	 ur0 = 1.0, 
	 ui0 = 0.0, 
	 transformerIncluded = false, 
	 Saturated = false, 
	 TX = 0, 
	 md = 0, 
	 mq = 0, 
	 snd = 0, 
	 snq = 0, 
	 init_theta = 0.7865821025825294, 
	 init_omega = 1.0, 
	 init_efd = 2.726206622197425, 
	 WLMDVPu = 2.05, 
	 init_lambdaad = -0.8439921262727968, 
	 init_cm = 0.9, 
	 init_lambdaq1 = 0.6548476975744857, 
	 init_lambdaq2 = 0.6548476975744857, 
	 init_iq = 2.477802098930486, 
	 init_id = 6.427073888523119, 
	 init_lambdaaq = 0.6548476975744857, 
	 init_lambdad = -0.8439921262727968, 
	 init_lambdaf = -1.0822122721037,
	 PNALT = 0.0,
	 IENR = 4,
	 DIn = 0.0,
	 HIn = 6.0,
	 TSD0 = 0.05,
	 XPD = 0.3,
	 TPD0 = 7.0,
	 rStatIn = 0.0,
	 XPQ = 0.4,
	 TSQ0 = 0.05,
	 SN = 700.0,
	 TPQ0 = 1.5,
	 XSD = 0.2,
	 XD = 2.2,
	 lStatIn = 0.15,
	 XSQ = 0.2,
	 XQ = 2.0,
	 PN = 700.0,
	 IWLMDV = 2
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__G15______SM (
	 SNREF = SNREF, 
	 ur0 = 1.0, 
	 ui0 = 0.0, 
	 transformerIncluded = false, 
	 Saturated = false, 
	 TX = 0, 
	 md = 0, 
	 mq = 0, 
	 snd = 0, 
	 snq = 0, 
	 init_theta = 0.8429346304675475, 
	 init_omega = 1.0, 
	 init_efd = 2.585499765156583, 
	 WLMDVPu = 2.05, 
	 init_lambdaad = -0.79619913857068, 
	 init_cm = 0.9, 
	 init_lambdaq1 = 0.6906037673933093, 
	 init_lambdaq2 = 0.6906037673933093, 
	 init_iq = 4.479592004713356, 
	 init_id = 10.47395488733211, 
	 init_lambdaaq = 0.6906037673933093, 
	 init_lambdad = -0.79619913857068, 
	 init_lambdaf = -1.022124101882857,
	 PNALT = 0.0,
	 IENR = 4,
	 DIn = 0.0,
	 HIn = 6.0,
	 TSD0 = 0.05,
	 XPD = 0.3,
	 TPD0 = 7.0,
	 rStatIn = 0.0,
	 XPQ = 0.4,
	 TSQ0 = 0.05,
	 SN = 1200.0,
	 TPQ0 = 1.5,
	 XSD = 0.2,
	 XD = 2.2,
	 lStatIn = 0.15,
	 XSQ = 0.2,
	 XQ = 2.0,
	 PN = 1200.0,
	 IWLMDV = 2
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__G16______SM (
	 SNREF = SNREF, 
	 ur0 = 1.0, 
	 ui0 = 0.0, 
	 transformerIncluded = false, 
	 Saturated = false, 
	 TX = 0, 
	 md = 0, 
	 mq = 0, 
	 snd = 0, 
	 snq = 0, 
	 init_theta = 0.8173789999460626, 
	 init_omega = 1.0, 
	 init_efd = 2.517040368567408, 
	 WLMDVPu = 2.05, 
	 init_lambdaad = -0.8091060023320484, 
	 init_cm = 0.8571428571428571, 
	 init_lambdaq1 = 0.6746535772047378, 
	 init_lambdaq2 = 0.6746535772047378, 
	 init_iq = 2.552743265099009, 
	 init_id = 5.831971006657325, 
	 init_lambdaaq = 0.6746535772047378, 
	 init_lambdad = -0.8091060023320484, 
	 init_lambdaf = -1.029048877820734,
	 PNALT = 0.0,
	 IENR = 4,
	 DIn = 0.0,
	 HIn = 6.0,
	 TSD0 = 0.05,
	 XPD = 0.3,
	 TPD0 = 7.0,
	 rStatIn = 0.0,
	 XPQ = 0.4,
	 TSQ0 = 0.05,
	 SN = 700.0,
	 TPQ0 = 1.5,
	 XSD = 0.2,
	 XD = 2.2,
	 lStatIn = 0.15,
	 XSQ = 0.2,
	 XQ = 2.0,
	 PN = 700.0,
	 IWLMDV = 2
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__G17______SM (
	 SNREF = SNREF, 
	 ur0 = 1.0, 
	 ui0 = 0.0, 
	 transformerIncluded = false, 
	 Saturated = false, 
	 TX = 0, 
	 md = 0, 
	 mq = 0, 
	 snd = 0, 
	 snq = 0, 
	 init_theta = 0.9962005251905384, 
	 init_omega = 1.0, 
	 init_efd = 2.260762947039864, 
	 WLMDVPu = 2.05, 
	 init_lambdaad = -0.6605819597871543, 
	 init_cm = 0.8833333333333334, 
	 init_lambdaq1 = 0.7764561471865379, 
	 init_lambdaq2 = 0.7764561471865379, 
	 init_iq = 2.51823615303742, 
	 init_id = 4.683456548056713, 
	 init_lambdaaq = 0.7764561471865379, 
	 init_lambdad = -0.6605819597871543, 
	 init_lambdaf = -0.8581309183099094,
	 PNALT = 0.0,
	 IENR = 4,
	 DIn = 0.0,
	 HIn = 6.0,
	 TSD0 = 0.05,
	 XPD = 0.3,
	 TPD0 = 7.0,
	 rStatIn = 0.0,
	 XPQ = 0.4,
	 TSQ0 = 0.05,
	 SN = 600.0,
	 TPQ0 = 1.5,
	 XSD = 0.2,
	 XD = 2.2,
	 lStatIn = 0.15,
	 XSQ = 0.2,
	 XQ = 2.0,
	 PN = 600.0,
	 IWLMDV = 2
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__G18______SM (
	 SNREF = SNREF, 
	 ur0 = 1.0, 
	 ui0 = 0.0, 
	 transformerIncluded = false, 
	 Saturated = false, 
	 TX = 0, 
	 md = 0, 
	 mq = 0, 
	 snd = 0, 
	 snq = 0, 
	 init_theta = 0.7728231392689388, 
	 init_omega = 1.0, 
	 init_efd = 2.01603841173972, 
	 WLMDVPu = 2.05, 
	 init_lambdaad = -0.8045854259766552, 
	 init_cm = 0.6625, 
	 init_lambdaq1 = 0.6457972818307823, 
	 init_lambdaq2 = 0.6457972818307823, 
	 init_iq = 5.585273788806765, 
	 init_id = 9.455242815711721, 
	 init_lambdaaq = 0.6457972818307823, 
	 init_lambdad = -0.8045854259766552, 
	 init_lambdaf = -0.9807499765778236,
	 PNALT = 0.0,
	 IENR = 4,
	 DIn = 0.0,
	 HIn = 6.0,
	 TSD0 = 0.05,
	 XPD = 0.3,
	 TPD0 = 7.0,
	 rStatIn = 0.0,
	 XPQ = 0.4,
	 TSQ0 = 0.05,
	 SN = 1600.0,
	 TPQ0 = 1.5,
	 XSD = 0.2,
	 XD = 2.2,
	 lStatIn = 0.15,
	 XSQ = 0.2,
	 XQ = 2.0,
	 PN = 1600.0,
	 IWLMDV = 2
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__G19______SM (
	 SNREF = SNREF, 
	 ur0 = 1.0, 
	 ui0 = 0.0, 
	 transformerIncluded = false, 
	 Saturated = false, 
	 TX = 0, 
	 md = 0, 
	 XPQ = 0, 
	 TPQ0 = 0, 
	 mq = 0, 
	 snd = 0, 
	 snq = 0, 
	 init_theta = 0.3453177168103761, 
	 init_omega = 1.0, 
	 init_efd = 1.412107484290442, 
	 WLMDVPu = 0.9500000000000001, 
	 init_lambdaad = -1.005214253879387, 
	 init_cm = 0.5999999999999999, 
	 init_lambdaq1 = 0.2659608723077653, 
	 init_lambdaq2 = 0.2659608723077653, 
	 init_iq = 2.417826111888776, 
	 init_id = 2.14154331795292, 
	 init_lambdaaq = 0.2659608723077653, 
	 init_lambdad = -1.005214253879387, 
	 init_lambdaf = -1.185274964704831,
	 PNALT = 0.0,
	 IENR = 3,
	 DIn = 0.0,
	 HIn = 3.0,
	 TSD0 = 0.05,
	 XPD = 0.25,
	 TPD0 = 5.0,
	 rStatIn = 0.0,
	 TSQ0 = 0.1,
	 SN = 500.0,
	 XSD = 0.2,
	 XD = 1.1,
	 lStatIn = 0.15,
	 XSQ = 0.2,
	 XQ = 0.7,
	 PN = 500.0,
	 IWLMDV = 2
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__G1_______SM (
	 SNREF = SNREF, 
	 ur0 = 1.0, 
	 ui0 = 0.0, 
	 transformerIncluded = false, 
	 Saturated = false, 
	 TX = 0, 
	 md = 0, 
	 XPQ = 0, 
	 TPQ0 = 0, 
	 mq = 0, 
	 snd = 0, 
	 snq = 0, 
	 init_theta = 0.4648748978682532, 
	 init_omega = 1.0, 
	 init_efd = 1.329453222522508, 
	 WLMDVPu = 0.9500000000000001, 
	 init_lambdaad = -0.9532743252508278, 
	 init_cm = 0.7500000000000001, 
	 init_lambdaq1 = 0.3522443406064292, 
	 init_lambdaq2 = 0.3522443406064292, 
	 init_iq = 5.123554045184426, 
	 init_id = 3.167822292814152, 
	 init_lambdaaq = 0.3522443406064292, 
	 init_lambdad = -0.9532743252508278, 
	 init_lambdaf = -1.12279562226234,
	 PNALT = 0.0,
	 IENR = 3,
	 DIn = 0.0,
	 HIn = 3.0,
	 TSD0 = 0.05,
	 XPD = 0.25,
	 TPD0 = 5.0,
	 rStatIn = 0.0,
	 TSQ0 = 0.1,
	 SN = 800.0,
	 XSD = 0.2,
	 XD = 1.1,
	 lStatIn = 0.15,
	 XSQ = 0.2,
	 XQ = 0.7,
	 PN = 800.0,
	 IWLMDV = 2
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__G20______SM (
	 SNREF = SNREF, 
	 ur0 = 1.0, 
	 ui0 = 0.0, 
	 transformerIncluded = false, 
	 Saturated = false, 
	 TX = 0, 
	 md = 0, 
	 XPQ = 0, 
	 TPQ0 = 0, 
	 mq = 0, 
	 snd = 0, 
	 snq = 0, 
	 init_theta = 0.3037894063026331, 
	 init_omega = 1.0, 
	 init_efd = 1.197335562008405, 
	 WLMDVPu = 0.9500000000000001, 
	 init_lambdaad = -0.987363301389762, 
	 init_cm = 0.4738888888888889, 
	 init_lambdaq1 = 0.2350371841218582, 
	 init_lambdaq2 = 0.2350371841218582, 
	 init_iq = 19.23031506451568, 
	 init_id = 9.946054450356755, 
	 init_lambdaaq = 0.2350371841218582, 
	 init_lambdad = -0.987363301389762, 
	 init_lambdaf = -1.1400380055396,
	 PNALT = 0.0,
	 IENR = 3,
	 DIn = 0.0,
	 HIn = 3.0,
	 TSD0 = 0.05,
	 XPD = 0.25,
	 TPD0 = 5.0,
	 rStatIn = 0.0,
	 TSQ0 = 0.1,
	 SN = 4500.0,
	 XSD = 0.2,
	 XD = 1.1,
	 lStatIn = 0.15,
	 XSQ = 0.2,
	 XQ = 0.7,
	 PN = 4500.0,
	 IWLMDV = 2
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__G2_______SM (
	 SNREF = SNREF, 
	 ur0 = 1.0, 
	 ui0 = 0.0, 
	 transformerIncluded = false, 
	 Saturated = false, 
	 TX = 0, 
	 md = 0, 
	 XPQ = 0, 
	 TPQ0 = 0, 
	 mq = 0, 
	 snd = 0, 
	 snq = 0, 
	 init_theta = 0.331256221976816, 
	 init_omega = 1.0, 
	 init_efd = 1.150742019285923, 
	 WLMDVPu = 0.9500000000000001, 
	 init_lambdaad = -0.9736037306577445, 
	 init_cm = 0.5, 
	 init_lambdaq1 = 0.2555388090986809, 
	 init_lambdaq2 = 0.2555388090986809, 
	 init_iq = 2.787696099258337, 
	 init_id = 1.118768138704286, 
	 init_lambdaaq = 0.2555388090986809, 
	 init_lambdad = -0.9736037306577445, 
	 init_lambdaf = -1.12033719696569,
	 PNALT = 0.0,
	 IENR = 3,
	 DIn = 0.0,
	 HIn = 3.0,
	 TSD0 = 0.05,
	 XPD = 0.25,
	 TPD0 = 5.0,
	 rStatIn = 0.0,
	 TSQ0 = 0.1,
	 SN = 600.0,
	 XSD = 0.2,
	 XD = 1.1,
	 lStatIn = 0.15,
	 XSQ = 0.2,
	 XQ = 0.7,
	 PN = 600.0,
	 IWLMDV = 2
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__G3_______SM (
	 SNREF = SNREF, 
	 ur0 = 1.0, 
	 ui0 = 0.0, 
	 transformerIncluded = false, 
	 Saturated = false, 
	 TX = 0, 
	 md = 0, 
	 XPQ = 0, 
	 TPQ0 = 0, 
	 mq = 0, 
	 snd = 0, 
	 snq = 0, 
	 init_theta = 0.4948741744645899, 
	 init_omega = 1.0, 
	 init_efd = 1.316978416004052, 
	 WLMDVPu = 0.9500000000000001, 
	 init_lambdaad = -0.9396125569850445, 
	 init_cm = 0.7857142857142856, 
	 init_lambdaq1 = 0.3731521553389281, 
	 init_lambdaq2 = 0.3731521553389281, 
	 init_iq = 4.749209249768177, 
	 init_id = 2.780590540140059, 
	 init_lambdaaq = 0.3731521553389281, 
	 init_lambdad = -0.9396125569850445, 
	 init_lambdaf = -1.107543165926341,
	 PNALT = 0.0,
	 IENR = 3,
	 DIn = 0.0,
	 HIn = 3.0,
	 TSD0 = 0.05,
	 XPD = 0.25,
	 TPD0 = 5.0,
	 rStatIn = 0.0,
	 TSQ0 = 0.1,
	 SN = 700.0,
	 XSD = 0.2,
	 XD = 1.1,
	 lStatIn = 0.15,
	 XSQ = 0.2,
	 XQ = 0.7,
	 PN = 700.0,
	 IWLMDV = 2
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__G4_______SM (
	 SNREF = SNREF, 
	 ur0 = 1.0, 
	 ui0 = 0.0, 
	 transformerIncluded = false, 
	 Saturated = false, 
	 TX = 0, 
	 md = 0, 
	 XPQ = 0, 
	 TPQ0 = 0, 
	 mq = 0, 
	 snd = 0, 
	 snq = 0, 
	 init_theta = 0.4248778952731367, 
	 init_omega = 1.0, 
	 init_efd = 1.258408161826277, 
	 WLMDVPu = 0.9500000000000001, 
	 init_lambdaad = -0.9584507664806839, 
	 init_cm = 0.6666666666666666, 
	 init_lambdaq1 = 0.3238789215626567, 
	 init_lambdaq2 = 0.3238789215626567, 
	 init_iq = 3.533224598865348, 
	 init_id = 1.894467760077432, 
	 init_lambdaaq = 0.3238789215626567, 
	 init_lambdad = -0.9584507664806839, 
	 init_lambdaf = -1.118912962629185,
	 PNALT = 0.0,
	 IENR = 3,
	 DIn = 0.0,
	 HIn = 3.0,
	 TSD0 = 0.05,
	 XPD = 0.25,
	 TPD0 = 5.0,
	 rStatIn = 0.0,
	 TSQ0 = 0.1,
	 SN = 600.0,
	 XSD = 0.2,
	 XD = 1.1,
	 lStatIn = 0.15,
	 XSQ = 0.2,
	 XQ = 0.7,
	 PN = 600.0,
	 IWLMDV = 2
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__G5_______SM (
	 SNREF = SNREF, 
	 ur0 = 1.0, 
	 ui0 = 0.0, 
	 transformerIncluded = false, 
	 Saturated = false, 
	 TX = 0, 
	 md = 0, 
	 XPQ = 0, 
	 TPQ0 = 0, 
	 mq = 0, 
	 snd = 0, 
	 snq = 0, 
	 init_theta = 0.453083189995137, 
	 init_omega = 1.0, 
	 init_efd = 1.496554662970433, 
	 WLMDVPu = 0.9500000000000001, 
	 init_lambdaad = -0.9805725959352307, 
	 init_cm = 0.8, 
	 init_lambdaq1 = 0.3439383449913007, 
	 init_lambdaq2 = 0.3439383449913007, 
	 init_iq = 1.563356113596821, 
	 init_id = 1.35784754482948, 
	 init_lambdaaq = 0.3439383449913007, 
	 init_lambdad = -0.9805725959352307, 
	 init_lambdaf = -1.171401339073713,
	 PNALT = 0.0,
	 IENR = 3,
	 DIn = 0.0,
	 HIn = 3.0,
	 TSD0 = 0.05,
	 XPD = 0.25,
	 TPD0 = 5.0,
	 rStatIn = 0.0,
	 TSQ0 = 0.1,
	 SN = 250.0,
	 XSD = 0.2,
	 XD = 1.1,
	 lStatIn = 0.15,
	 XSQ = 0.2,
	 XQ = 0.7,
	 PN = 250.0,
	 IWLMDV = 2
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__G6_______SM (
	 SNREF = SNREF, 
	 ur0 = 1.0, 
	 ui0 = 0.0, 
	 transformerIncluded = false, 
	 Saturated = false, 
	 TX = 0, 
	 md = 0, 
	 mq = 0, 
	 snd = 0, 
	 snq = 0, 
	 init_theta = 0.8206249255446966, 
	 init_omega = 1.0, 
	 init_efd = 2.638324015688045, 
	 WLMDVPu = 2.05, 
	 init_lambdaad = -0.8151659705758215, 
	 init_cm = 0.9000000000000001, 
	 init_lambdaq1 = 0.6767041225615088, 
	 init_lambdaq2 = 0.6767041225615088, 
	 init_iq = 1.46314404878164, 
	 init_id = 3.557381551438484, 
	 init_lambdaaq = 0.6767041225615088, 
	 init_lambdad = -0.8151659705758215, 
	 init_lambdaf = -1.045706798497289,
	 PNALT = 0.0,
	 IENR = 4,
	 DIn = 0.0,
	 HIn = 6.0,
	 TSD0 = 0.05,
	 XPD = 0.3,
	 TPD0 = 7.0,
	 rStatIn = 0.0,
	 XPQ = 0.4,
	 TSQ0 = 0.05,
	 SN = 400.0,
	 TPQ0 = 1.5,
	 XSD = 0.2,
	 XD = 2.2,
	 lStatIn = 0.15,
	 XSQ = 0.2,
	 XQ = 2.0,
	 PN = 400.0,
	 IWLMDV = 2
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__G7_______SM (
	 SNREF = SNREF, 
	 ur0 = 1.0, 
	 ui0 = 0.0, 
	 transformerIncluded = false, 
	 Saturated = false, 
	 TX = 0, 
	 md = 0, 
	 mq = 0, 
	 snd = 0, 
	 snq = 0, 
	 init_theta = 0.8575628633568951, 
	 init_omega = 1.0, 
	 init_efd = 2.552752756678044, 
	 WLMDVPu = 2.05, 
	 init_lambdaad = -0.7837236488290045, 
	 init_cm = 0.9, 
	 init_lambdaq1 = 0.6995314669637108, 
	 init_lambdaq2 = 0.6995314669637108, 
	 init_iq = 0.7562502345553626, 
	 init_id = 1.725882056438087, 
	 init_lambdaaq = 0.6995314669637108, 
	 init_lambdad = -0.7837236488290045, 
	 init_lambdaf = -1.00678712795588,
	 PNALT = 0.0,
	 IENR = 4,
	 DIn = 0.0,
	 HIn = 6.0,
	 TSD0 = 0.05,
	 XPD = 0.3,
	 TPD0 = 7.0,
	 rStatIn = 0.0,
	 XPQ = 0.4,
	 TSQ0 = 0.05,
	 SN = 200.0,
	 TPQ0 = 1.5,
	 XSD = 0.2,
	 XD = 2.2,
	 lStatIn = 0.15,
	 XSQ = 0.2,
	 XQ = 2.0,
	 PN = 200.0,
	 IWLMDV = 2
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__G8_______SM (
	 SNREF = SNREF, 
	 ur0 = 1.0, 
	 ui0 = 0.0, 
	 transformerIncluded = false, 
	 Saturated = false, 
	 TX = 0, 
	 md = 0, 
	 XPQ = 0, 
	 TPQ0 = 0, 
	 mq = 0, 
	 snd = 0, 
	 snq = 0, 
	 init_theta = 0.4817429199335625, 
	 init_omega = 1.0, 
	 init_efd = 1.58844142503241, 
	 WLMDVPu = 0.9500000000000001, 
	 init_lambdaad = -0.981950462707352, 
	 init_cm = 0.8823529411764706, 
	 init_lambdaq1 = 0.3640406271082351, 
	 init_lambdaq2 = 0.3640406271082351, 
	 init_iq = 5.626082418945452, 
	 init_id = 5.426498083961045, 
	 init_lambdaaq = 0.3640406271082351, 
	 init_lambdad = -0.981950462707352, 
	 init_lambdaf = -1.18449587463797,
	 PNALT = 0.0,
	 IENR = 3,
	 DIn = 0.0,
	 HIn = 3.0,
	 TSD0 = 0.05,
	 XPD = 0.25,
	 TPD0 = 5.0,
	 rStatIn = 0.0,
	 TSQ0 = 0.1,
	 SN = 850.0,
	 XSD = 0.2,
	 XD = 1.1,
	 lStatIn = 0.15,
	 XSQ = 0.2,
	 XQ = 0.7,
	 PN = 850.0,
	 IWLMDV = 2
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__G9_______SM (
	 SNREF = SNREF, 
	 ur0 = 1.0, 
	 ui0 = 0.0, 
	 transformerIncluded = false, 
	 Saturated = false, 
	 TX = 0, 
	 md = 0, 
	 XPQ = 0, 
	 TPQ0 = 0, 
	 mq = 0, 
	 snd = 0, 
	 snq = 0, 
	 init_theta = 0.3912583401236177, 
	 init_omega = 1.0, 
	 init_efd = 1.400025506405413, 
	 WLMDVPu = 0.9500000000000001, 
	 init_lambdaad = -0.9892838647983327, 
	 init_cm = 0.6685000000000001, 
	 init_lambdaq1 = 0.2996336859982938, 
	 init_lambdaq2 = 0.2996336859982938, 
	 init_iq = 5.44788519996898, 
	 init_id = 4.323596227442954, 
	 init_lambdaaq = 0.2996336859982938, 
	 init_lambdad = -0.9892838647983327, 
	 init_lambdaf = -1.167803977935316,
	 PNALT = 0.0,
	 IENR = 3,
	 DIn = 0.0,
	 HIn = 3.0,
	 TSD0 = 0.05,
	 XPD = 0.25,
	 TPD0 = 5.0,
	 rStatIn = 0.0,
	 TSQ0 = 0.1,
	 SN = 1000.0,
	 XSD = 0.2,
	 XD = 1.1,
	 lStatIn = 0.15,
	 XSQ = 0.2,
	 XQ = 0.7,
	 PN = 1000.0,
	 IWLMDV = 2
	 ) annotation (Placement(transformation()));

// REGULATORS
  htgpsat3 reg_htgpsat3__G10______SM (
	 SNREF = 100.0,
	 SN = 800.0,
	 PN = 800.0,
	 PNALT = 0.0,
	 init_PREF = 0.75,
	 init_OMEGREF = 1.0,
	 init_CM = 0.75,
	 init_T1 = -1.0,
	 A21 = 1.5,
	 A11 = 0.5,
	 A23 = 1.,
	 A13 = 1.,
	 TW = 1.,
	 DELTA = .3000000,
	 PMIN = 0.,
	 UC = -0.1,
	 PMAX = 1.,
	 TG = 0.2,
	 SIGMA = 0.04,
	 UO = 0.1,
	 TP = 0.04,
	 TR = 5.
	 ) annotation (Placement(transformation()));
  oelpsat reg_oelpsat__G10______SM (
	 SNREF = 100.0,
	 SN = 800.0,
	 PN = 800.0,
	 PNALT = 0.0,
	 init_EFD = 1.559748294665931,
	 init_VREF = 1.0,
	 init_VV = 1.0,
	 XD = 1.1,
	 XQ = 0.7,
	 TE = 0.1,
	 VOELMAX = 1.100000,
	 IFDLIM = 3.,
	 K0 = 50.,
	 VFMAX = 4.,
	 V0 = 0.,
	 T0 = 10.,
	 T1 = 4.,
	 T2 = 20.,
	 TR = 0.001,
	 VFMIN = 0.
	 ) annotation (Placement(transformation()));
  htgpsat3 reg_htgpsat3__G11______SM (
	 SNREF = 100.0,
	 SN = 300.0,
	 PN = 300.0,
	 PNALT = 0.0,
	 init_PREF = 0.8333333333333333,
	 init_OMEGREF = 1.0,
	 init_CM = 0.8333333333333333,
	 init_T1 = -1.0,
	 A21 = 1.5,
	 A11 = 0.5,
	 A23 = 1.,
	 A13 = 1.,
	 TW = 1.,
	 DELTA = .3000000,
	 PMIN = 0.,
	 UC = -0.1,
	 PMAX = 1.,
	 TG = 0.2,
	 SIGMA = 0.04,
	 UO = 0.1,
	 TP = 0.04,
	 TR = 5.
	 ) annotation (Placement(transformation()));
  oelpsat reg_oelpsat__G11______SM (
	 SNREF = 100.0,
	 SN = 300.0,
	 PN = 300.0,
	 PNALT = 0.0,
	 init_EFD = 1.480057428308171,
	 init_VREF = 1.0,
	 init_VV = 1.0,
	 XD = 1.1,
	 XQ = 0.7,
	 TE = 0.1,
	 VOELMAX = 1.100000,
	 IFDLIM = 3.,
	 K0 = 50.,
	 VFMAX = 4.,
	 V0 = 0.,
	 T0 = 10.,
	 T1 = 4.,
	 T2 = 20.,
	 TR = 0.001,
	 VFMIN = 0.
	 ) annotation (Placement(transformation()));
  htgpsat3 reg_htgpsat3__G12______SM (
	 SNREF = 100.0,
	 SN = 350.0,
	 PN = 350.0,
	 PNALT = 0.0,
	 init_PREF = 0.8857142857142858,
	 init_OMEGREF = 1.0,
	 init_CM = 0.8857142857142858,
	 init_T1 = -1.0,
	 A21 = 1.5,
	 A11 = 0.5,
	 A23 = 1.,
	 A13 = 1.,
	 TW = 1.,
	 DELTA = .3000000,
	 PMIN = 0.,
	 UC = -0.1,
	 PMAX = 1.,
	 TG = 0.2,
	 SIGMA = 0.04,
	 UO = 0.1,
	 TP = 0.04,
	 TR = 5.
	 ) annotation (Placement(transformation()));
  oelpsat reg_oelpsat__G12______SM (
	 SNREF = 100.0,
	 SN = 350.0,
	 PN = 350.0,
	 PNALT = 0.0,
	 init_EFD = 1.734356962243129,
	 init_VREF = 1.0,
	 init_VV = 1.0,
	 XD = 1.1,
	 XQ = 0.7,
	 TE = 0.1,
	 VOELMAX = 1.100000,
	 IFDLIM = 3.,
	 K0 = 50.,
	 VFMAX = 4.,
	 V0 = 0.,
	 T0 = 10.,
	 T1 = 4.,
	 T2 = 20.,
	 TR = 0.001,
	 VFMIN = 0.
	 ) annotation (Placement(transformation()));
  oelpsat reg_oelpsat__G13______SM (
	 SNREF = 100.0,
	 SN = 300.0,
	 PN = 300.0,
	 PNALT = 0.0,
	 init_EFD = 1.180988323,
	 init_VREF = 1.0,
	 init_VV = 1.0,
	 XD = 1.55,
	 XQ = 1.0,
	 TE = 0.1,
	 VOELMAX = 1.100000,
	 IFDLIM = 3.,
	 K0 = 120.,
	 VFMAX = 5.,
	 V0 = 0.,
	 T0 = 10.,
	 T1 = 5.,
	 T2 = 50.,
	 TR = 0.001,
	 VFMIN = 0.
	 ) annotation (Placement(transformation()));
  govpsat1 reg_govpsat1__G13______SM (
	 SNREF = 100.0,
	 SN = 300.0,
	 PN = 300.0,
	 PNALT = 0.0,
	 init_CM = 0.0,
	 init_OMREF = 1.0,
	 T4 = 0.01,
	 PMAX = .9500000,
	 T5 = 6.,
	 RD = 0.04,
	 PMIN = -0.5,
	 T3 = 5.,
	 TC = 0.2,
	 TS = 5.
	 ) annotation (Placement(transformation()));
  oelpsat reg_oelpsat__G14______SM (
	 SNREF = 100.0,
	 SN = 700.0,
	 PN = 700.0,
	 PNALT = 0.0,
	 init_EFD = 2.726206622197425,
	 init_VREF = 1.0,
	 init_VV = 1.0,
	 XD = 2.2,
	 XQ = 2.0,
	 TE = 0.1,
	 VOELMAX = 1.100000,
	 IFDLIM = 3.,
	 K0 = 120.,
	 VFMAX = 5.,
	 V0 = 0.,
	 T0 = 10.,
	 T1 = 5.,
	 T2 = 50.,
	 TR = 0.001,
	 VFMIN = 0.
	 ) annotation (Placement(transformation()));
  govpsat1 reg_govpsat1__G14______SM (
	 SNREF = 100.0,
	 SN = 700.0,
	 PN = 700.0,
	 PNALT = 0.0,
	 init_CM = 0.9,
	 init_OMREF = 1.0,
	 T4 = 0.01,
	 PMAX = .9500000,
	 T5 = 6.,
	 RD = 0.04,
	 PMIN = 0.,
	 T3 = 5.,
	 TC = 0.2,
	 TS = 5.
	 ) annotation (Placement(transformation()));
  oelpsat reg_oelpsat__G15______SM (
	 SNREF = 100.0,
	 SN = 1200.0,
	 PN = 1200.0,
	 PNALT = 0.0,
	 init_EFD = 2.585499765156583,
	 init_VREF = 1.0,
	 init_VV = 1.0,
	 XD = 2.2,
	 XQ = 2.0,
	 TE = 0.1,
	 VOELMAX = 1.100000,
	 IFDLIM = 3.,
	 K0 = 120.,
	 VFMAX = 5.,
	 V0 = 0.,
	 T0 = 10.,
	 T1 = 5.,
	 T2 = 50.,
	 TR = 0.001,
	 VFMIN = 0.
	 ) annotation (Placement(transformation()));
  govpsat1 reg_govpsat1__G15______SM (
	 SNREF = 100.0,
	 SN = 1200.0,
	 PN = 1200.0,
	 PNALT = 0.0,
	 init_CM = 0.9,
	 init_OMREF = 1.0,
	 T4 = 0.01,
	 PMAX = .9500000,
	 T5 = 6.,
	 RD = 0.04,
	 PMIN = 0.,
	 T3 = 5.,
	 TC = 0.2,
	 TS = 5.
	 ) annotation (Placement(transformation()));
  oelpsat reg_oelpsat__G16______SM (
	 SNREF = 100.0,
	 SN = 700.0,
	 PN = 700.0,
	 PNALT = 0.0,
	 init_EFD = 2.517040368567408,
	 init_VREF = 1.0,
	 init_VV = 1.0,
	 XD = 2.2,
	 XQ = 2.0,
	 TE = 0.1,
	 VOELMAX = 1.100000,
	 IFDLIM = 3.,
	 K0 = 120.,
	 VFMAX = 5.,
	 V0 = 0.,
	 T0 = 10.,
	 T1 = 5.,
	 T2 = 50.,
	 TR = 0.001,
	 VFMIN = 0.
	 ) annotation (Placement(transformation()));
  govpsat1 reg_govpsat1__G16______SM (
	 SNREF = 100.0,
	 SN = 700.0,
	 PN = 700.0,
	 PNALT = 0.0,
	 init_CM = 0.8571428571428571,
	 init_OMREF = 1.0,
	 T4 = 0.01,
	 PMAX = .9500000,
	 T5 = 6.,
	 RD = 0.04,
	 PMIN = 0.,
	 T3 = 5.,
	 TC = 0.2,
	 TS = 5.
	 ) annotation (Placement(transformation()));
  oelpsat reg_oelpsat__G17______SM (
	 SNREF = 100.0,
	 SN = 600.0,
	 PN = 600.0,
	 PNALT = 0.0,
	 init_EFD = 2.260762947039864,
	 init_VREF = 1.0,
	 init_VV = 1.0,
	 XD = 2.2,
	 XQ = 2.0,
	 TE = 0.1,
	 VOELMAX = 1.100000,
	 IFDLIM = 3.,
	 K0 = 120.,
	 VFMAX = 5.,
	 V0 = 0.,
	 T0 = 10.,
	 T1 = 5.,
	 T2 = 50.,
	 TR = 0.001,
	 VFMIN = 0.
	 ) annotation (Placement(transformation()));
  govpsat1 reg_govpsat1__G17______SM (
	 SNREF = 100.0,
	 SN = 600.0,
	 PN = 600.0,
	 PNALT = 0.0,
	 init_CM = 0.8833333333333334,
	 init_OMREF = 1.0,
	 T4 = 0.01,
	 PMAX = .9500000,
	 T5 = 6.,
	 RD = 0.04,
	 PMIN = 0.,
	 T3 = 5.,
	 TC = 0.2,
	 TS = 5.
	 ) annotation (Placement(transformation()));
  oelpsat reg_oelpsat__G18______SM (
	 SNREF = 100.0,
	 SN = 1600.0,
	 PN = 1600.0,
	 PNALT = 0.0,
	 init_EFD = 2.01603841173972,
	 init_VREF = 1.0,
	 init_VV = 1.0,
	 XD = 2.2,
	 XQ = 2.0,
	 TE = 0.1,
	 VOELMAX = 1.100000,
	 IFDLIM = 3.,
	 K0 = 120.,
	 VFMAX = 5.,
	 V0 = 0.,
	 T0 = 10.,
	 T1 = 5.,
	 T2 = 50.,
	 TR = 0.001,
	 VFMIN = 0.
	 ) annotation (Placement(transformation()));
  govpsat1 reg_govpsat1__G18______SM (
	 SNREF = 100.0,
	 SN = 1600.0,
	 PN = 1600.0,
	 PNALT = 0.0,
	 init_CM = 0.6625,
	 init_OMREF = 1.0,
	 T4 = 0.01,
	 PMAX = .9500000,
	 T5 = 6.,
	 RD = 0.04,
	 PMIN = 0.,
	 T3 = 5.,
	 TC = 0.2,
	 TS = 5.
	 ) annotation (Placement(transformation()));
  htgpsat3 reg_htgpsat3__G19______SM (
	 SNREF = 100.0,
	 SN = 500.0,
	 PN = 500.0,
	 PNALT = 0.0,
	 init_PREF = 0.5999999999999999,
	 init_OMEGREF = 1.0,
	 init_CM = 0.5999999999999999,
	 init_T1 = -1.0,
	 A21 = 1.5,
	 A11 = 0.5,
	 A23 = 1.,
	 A13 = 1.,
	 TW = 1.,
	 DELTA = .3000000,
	 PMIN = 0.,
	 UC = -0.1,
	 PMAX = 1.,
	 TG = 0.2,
	 SIGMA = 0.04,
	 UO = 0.1,
	 TP = 0.04,
	 TR = 5.
	 ) annotation (Placement(transformation()));
  oelpsat reg_oelpsat__G19______SM (
	 SNREF = 100.0,
	 SN = 500.0,
	 PN = 500.0,
	 PNALT = 0.0,
	 init_EFD = 1.412107484290442,
	 init_VREF = 1.0,
	 init_VV = 1.0,
	 XD = 1.1,
	 XQ = 0.7,
	 TE = 0.1,
	 VOELMAX = 1.100000,
	 IFDLIM = 3.,
	 K0 = 50.,
	 VFMAX = 4.,
	 V0 = 0.,
	 T0 = 10.,
	 T1 = 4.,
	 T2 = 20.,
	 TR = 0.001,
	 VFMIN = 0.
	 ) annotation (Placement(transformation()));
  htgpsat3 reg_htgpsat3__G1_______SM (
	 SNREF = 100.0,
	 SN = 800.0,
	 PN = 800.0,
	 PNALT = 0.0,
	 init_PREF = 0.7500000000000001,
	 init_OMEGREF = 1.0,
	 init_CM = 0.7500000000000001,
	 init_T1 = -1.0,
	 A21 = 1.5,
	 A11 = 0.5,
	 A23 = 1.,
	 A13 = 1.,
	 TW = 1.,
	 DELTA = .3000000,
	 PMIN = 0.,
	 UC = -0.1,
	 PMAX = 1.,
	 TG = 0.2,
	 SIGMA = 0.04,
	 UO = 0.1,
	 TP = 0.04,
	 TR = 5.
	 ) annotation (Placement(transformation()));
  oelpsat reg_oelpsat__G1_______SM (
	 SNREF = 100.0,
	 SN = 800.0,
	 PN = 800.0,
	 PNALT = 0.0,
	 init_EFD = 1.329453222522508,
	 init_VREF = 1.0,
	 init_VV = 1.0,
	 XD = 1.1,
	 XQ = 0.7,
	 TE = 0.1,
	 VOELMAX = 1.100000,
	 IFDLIM = 3.,
	 K0 = 50.,
	 VFMAX = 4.,
	 V0 = 0.,
	 T0 = 10.,
	 T1 = 4.,
	 T2 = 20.,
	 TR = 0.001,
	 VFMIN = 0.
	 ) annotation (Placement(transformation()));
  htgpsat3 reg_htgpsat3__G20______SM (
	 SNREF = 100.0,
	 SN = 4500.0,
	 PN = 4500.0,
	 PNALT = 0.0,
	 init_PREF = 0.4738888888888889,
	 init_OMEGREF = 1.0,
	 init_CM = 0.4738888888888889,
	 init_T1 = -1.0,
	 A21 = 1.5,
	 A11 = 0.5,
	 A23 = 1.,
	 A13 = 1.,
	 TW = 1.,
	 DELTA = .3000000,
	 PMIN = 0.,
	 UC = -0.1,
	 PMAX = 1.,
	 TG = 0.2,
	 SIGMA = 0.04,
	 UO = 0.1,
	 TP = 0.04,
	 TR = 5.
	 ) annotation (Placement(transformation()));
  oelpsat reg_oelpsat__G20______SM (
	 SNREF = 100.0,
	 SN = 4500.0,
	 PN = 4500.0,
	 PNALT = 0.0,
	 init_EFD = 1.197335562008405,
	 init_VREF = 1.0,
	 init_VV = 1.0,
	 XD = 1.1,
	 XQ = 0.7,
	 TE = 0.1,
	 VOELMAX = 1.100000,
	 IFDLIM = 3.,
	 K0 = 50.,
	 VFMAX = 4.,
	 V0 = 0.,
	 T0 = 10.,
	 T1 = 4.,
	 T2 = 20.,
	 TR = 0.001,
	 VFMIN = 0.
	 ) annotation (Placement(transformation()));
  htgpsat3 reg_htgpsat3__G2_______SM (
	 SNREF = 100.0,
	 SN = 600.0,
	 PN = 600.0,
	 PNALT = 0.0,
	 init_PREF = 0.5,
	 init_OMEGREF = 1.0,
	 init_CM = 0.5,
	 init_T1 = -1.0,
	 A21 = 1.5,
	 A11 = 0.5,
	 A23 = 1.,
	 A13 = 1.,
	 TW = 1.,
	 DELTA = .3000000,
	 PMIN = 0.,
	 UC = -0.1,
	 PMAX = 1.,
	 TG = 0.2,
	 SIGMA = 0.04,
	 UO = 0.1,
	 TP = 0.04,
	 TR = 5.
	 ) annotation (Placement(transformation()));
  oelpsat reg_oelpsat__G2_______SM (
	 SNREF = 100.0,
	 SN = 600.0,
	 PN = 600.0,
	 PNALT = 0.0,
	 init_EFD = 1.150742019285923,
	 init_VREF = 1.0,
	 init_VV = 1.0,
	 XD = 1.1,
	 XQ = 0.7,
	 TE = 0.1,
	 VOELMAX = 1.100000,
	 IFDLIM = 3.,
	 K0 = 50.,
	 VFMAX = 4.,
	 V0 = 0.,
	 T0 = 10.,
	 T1 = 4.,
	 T2 = 20.,
	 TR = 0.001,
	 VFMIN = 0.
	 ) annotation (Placement(transformation()));
  htgpsat3 reg_htgpsat3__G3_______SM (
	 SNREF = 100.0,
	 SN = 700.0,
	 PN = 700.0,
	 PNALT = 0.0,
	 init_PREF = 0.7857142857142856,
	 init_OMEGREF = 1.0,
	 init_CM = 0.7857142857142856,
	 init_T1 = -1.0,
	 A21 = 1.5,
	 A11 = 0.5,
	 A23 = 1.,
	 A13 = 1.,
	 TW = 1.,
	 DELTA = .3000000,
	 PMIN = 0.,
	 UC = -0.1,
	 PMAX = 1.,
	 TG = 0.2,
	 SIGMA = 0.04,
	 UO = 0.1,
	 TP = 0.04,
	 TR = 5.
	 ) annotation (Placement(transformation()));
  oelpsat reg_oelpsat__G3_______SM (
	 SNREF = 100.0,
	 SN = 700.0,
	 PN = 700.0,
	 PNALT = 0.0,
	 init_EFD = 1.316978416004052,
	 init_VREF = 1.0,
	 init_VV = 1.0,
	 XD = 1.1,
	 XQ = 0.7,
	 TE = 0.1,
	 VOELMAX = 1.100000,
	 IFDLIM = 3.,
	 K0 = 50.,
	 VFMAX = 4.,
	 V0 = 0.,
	 T0 = 10.,
	 T1 = 4.,
	 T2 = 20.,
	 TR = 0.001,
	 VFMIN = 0.
	 ) annotation (Placement(transformation()));
  htgpsat3 reg_htgpsat3__G4_______SM (
	 SNREF = 100.0,
	 SN = 600.0,
	 PN = 600.0,
	 PNALT = 0.0,
	 init_PREF = 0.6666666666666666,
	 init_OMEGREF = 1.0,
	 init_CM = 0.6666666666666666,
	 init_T1 = -1.0,
	 A21 = 1.5,
	 A11 = 0.5,
	 A23 = 1.,
	 A13 = 1.,
	 TW = 1.,
	 DELTA = .3000000,
	 PMIN = 0.,
	 UC = -0.1,
	 PMAX = 1.,
	 TG = 0.2,
	 SIGMA = 0.04,
	 UO = 0.1,
	 TP = 0.04,
	 TR = 5.
	 ) annotation (Placement(transformation()));
  oelpsat reg_oelpsat__G4_______SM (
	 SNREF = 100.0,
	 SN = 600.0,
	 PN = 600.0,
	 PNALT = 0.0,
	 init_EFD = 1.258408161826277,
	 init_VREF = 1.0,
	 init_VV = 1.0,
	 XD = 1.1,
	 XQ = 0.7,
	 TE = 0.1,
	 VOELMAX = 1.100000,
	 IFDLIM = 3.,
	 K0 = 50.,
	 VFMAX = 4.,
	 V0 = 0.,
	 T0 = 10.,
	 T1 = 4.,
	 T2 = 20.,
	 TR = 0.001,
	 VFMIN = 0.
	 ) annotation (Placement(transformation()));
  htgpsat3 reg_htgpsat3__G5_______SM (
	 SNREF = 100.0,
	 SN = 250.0,
	 PN = 250.0,
	 PNALT = 0.0,
	 init_PREF = 0.8,
	 init_OMEGREF = 1.0,
	 init_CM = 0.8,
	 init_T1 = -1.0,
	 A21 = 1.5,
	 A11 = 0.5,
	 A23 = 1.,
	 A13 = 1.,
	 TW = 1.,
	 DELTA = .3000000,
	 PMIN = 0.,
	 UC = -0.1,
	 PMAX = 1.,
	 TG = 0.2,
	 SIGMA = 0.04,
	 UO = 0.1,
	 TP = 0.04,
	 TR = 5.
	 ) annotation (Placement(transformation()));
  oelpsat reg_oelpsat__G5_______SM (
	 SNREF = 100.0,
	 SN = 250.0,
	 PN = 250.0,
	 PNALT = 0.0,
	 init_EFD = 1.496554662970433,
	 init_VREF = 1.0,
	 init_VV = 1.0,
	 XD = 1.1,
	 XQ = 0.7,
	 TE = 0.1,
	 VOELMAX = 1.100000,
	 IFDLIM = 3.,
	 K0 = 50.,
	 VFMAX = 4.,
	 V0 = 0.,
	 T0 = 10.,
	 T1 = 4.,
	 T2 = 20.,
	 TR = 0.001,
	 VFMIN = 0.
	 ) annotation (Placement(transformation()));
  oelpsat reg_oelpsat__G6_______SM (
	 SNREF = 100.0,
	 SN = 400.0,
	 PN = 400.0,
	 PNALT = 0.0,
	 init_EFD = 2.638324015688045,
	 init_VREF = 1.0,
	 init_VV = 1.0,
	 XD = 2.2,
	 XQ = 2.0,
	 TE = 0.1,
	 VOELMAX = 1.100000,
	 IFDLIM = 3.,
	 K0 = 120.,
	 VFMAX = 5.,
	 V0 = 0.,
	 T0 = 10.,
	 T1 = 5.,
	 T2 = 50.,
	 TR = 0.001,
	 VFMIN = 0.
	 ) annotation (Placement(transformation()));
  govpsat1 reg_govpsat1__G6_______SM (
	 SNREF = 100.0,
	 SN = 400.0,
	 PN = 400.0,
	 PNALT = 0.0,
	 init_CM = 0.9000000000000001,
	 init_OMREF = 1.0,
	 T4 = 0.01,
	 PMAX = .9500000,
	 T5 = 6.,
	 RD = 0.04,
	 PMIN = 0.,
	 T3 = 5.,
	 TC = 0.2,
	 TS = 5.
	 ) annotation (Placement(transformation()));
  oelpsat reg_oelpsat__G7_______SM (
	 SNREF = 100.0,
	 SN = 200.0,
	 PN = 200.0,
	 PNALT = 0.0,
	 init_EFD = 2.552752756678044,
	 init_VREF = 1.0,
	 init_VV = 1.0,
	 XD = 2.2,
	 XQ = 2.0,
	 TE = 0.1,
	 VOELMAX = 1.100000,
	 IFDLIM = 3.,
	 K0 = 120.,
	 VFMAX = 5.,
	 V0 = 0.,
	 T0 = 10.,
	 T1 = 5.,
	 T2 = 50.,
	 TR = 0.001,
	 VFMIN = 0.
	 ) annotation (Placement(transformation()));
  govpsat1 reg_govpsat1__G7_______SM (
	 SNREF = 100.0,
	 SN = 200.0,
	 PN = 200.0,
	 PNALT = 0.0,
	 init_CM = 0.9,
	 init_OMREF = 1.0,
	 T4 = 0.01,
	 PMAX = .9500000,
	 T5 = 6.,
	 RD = 0.04,
	 PMIN = 0.,
	 T3 = 5.,
	 TC = 0.2,
	 TS = 5.
	 ) annotation (Placement(transformation()));
  htgpsat3 reg_htgpsat3__G8_______SM (
	 SNREF = 100.0,
	 SN = 850.0,
	 PN = 850.0,
	 PNALT = 0.0,
	 init_PREF = 0.8823529411764706,
	 init_OMEGREF = 1.0,
	 init_CM = 0.8823529411764706,
	 init_T1 = -1.0,
	 A21 = 1.5,
	 A11 = 0.5,
	 A23 = 1.,
	 A13 = 1.,
	 TW = 1.,
	 DELTA = .3000000,
	 PMIN = 0.,
	 UC = -0.1,
	 PMAX = 1.,
	 TG = 0.2,
	 SIGMA = 0.04,
	 UO = 0.1,
	 TP = 0.04,
	 TR = 5.
	 ) annotation (Placement(transformation()));
  oelpsat reg_oelpsat__G8_______SM (
	 SNREF = 100.0,
	 SN = 850.0,
	 PN = 850.0,
	 PNALT = 0.0,
	 init_EFD = 1.58844142503241,
	 init_VREF = 1.0,
	 init_VV = 1.0,
	 XD = 1.1,
	 XQ = 0.7,
	 TE = 0.1,
	 VOELMAX = 1.100000,
	 IFDLIM = 3.,
	 K0 = 50.,
	 VFMAX = 4.,
	 V0 = 0.,
	 T0 = 10.,
	 T1 = 4.,
	 T2 = 20.,
	 TR = 0.001,
	 VFMIN = 0.
	 ) annotation (Placement(transformation()));
  htgpsat3 reg_htgpsat3__G9_______SM (
	 SNREF = 100.0,
	 SN = 1000.0,
	 PN = 1000.0,
	 PNALT = 0.0,
	 init_PREF = 0.6685000000000001,
	 init_OMEGREF = 1.0,
	 init_CM = 0.6685000000000001,
	 init_T1 = -1.0,
	 A21 = 1.5,
	 A11 = 0.5,
	 A23 = 1.,
	 A13 = 1.,
	 TW = 1.,
	 DELTA = .3000000,
	 PMIN = 0.,
	 UC = -0.1,
	 PMAX = 1.,
	 TG = 0.2,
	 SIGMA = 0.04,
	 UO = 0.1,
	 TP = 0.04,
	 TR = 5.
	 ) annotation (Placement(transformation()));
  oelpsat reg_oelpsat__G9_______SM (
	 SNREF = 100.0,
	 SN = 1000.0,
	 PN = 1000.0,
	 PNALT = 0.0,
	 init_EFD = 1.400025506405413,
	 init_VREF = 1.0,
	 init_VV = 1.0,
	 XD = 1.1,
	 XQ = 0.7,
	 TE = 0.1,
	 VOELMAX = 1.100000,
	 IFDLIM = 3.,
	 K0 = 50.,
	 VFMAX = 4.,
	 V0 = 0.,
	 T0 = 10.,
	 T1 = 4.,
	 T2 = 20.,
	 TR = 0.001,
	 VFMIN = 0.
	 ) annotation (Placement(transformation()));

equation
  omegaRef = (gen_pwGeneratorM2S__G10______SM.omega*gen_pwGeneratorM2S__G10______SM.SN*gen_pwGeneratorM2S__G10______SM.HIn + gen_pwGeneratorM2S__G11______SM.omega*gen_pwGeneratorM2S__G11______SM.SN*gen_pwGeneratorM2S__G11______SM.HIn + gen_pwGeneratorM2S__G12______SM.omega*gen_pwGeneratorM2S__G12______SM.SN*gen_pwGeneratorM2S__G12______SM.HIn + gen_pwGeneratorM2S__G13______SM.omega*gen_pwGeneratorM2S__G13______SM.SN*gen_pwGeneratorM2S__G13______SM.HIn + gen_pwGeneratorM2S__G14______SM.omega*gen_pwGeneratorM2S__G14______SM.SN*gen_pwGeneratorM2S__G14______SM.HIn + gen_pwGeneratorM2S__G15______SM.omega*gen_pwGeneratorM2S__G15______SM.SN*gen_pwGeneratorM2S__G15______SM.HIn + gen_pwGeneratorM2S__G16______SM.omega*gen_pwGeneratorM2S__G16______SM.SN*gen_pwGeneratorM2S__G16______SM.HIn + gen_pwGeneratorM2S__G17______SM.omega*gen_pwGeneratorM2S__G17______SM.SN*gen_pwGeneratorM2S__G17______SM.HIn + gen_pwGeneratorM2S__G18______SM.omega*gen_pwGeneratorM2S__G18______SM.SN*gen_pwGeneratorM2S__G18______SM.HIn + gen_pwGeneratorM2S__G19______SM.omega*gen_pwGeneratorM2S__G19______SM.SN*gen_pwGeneratorM2S__G19______SM.HIn + gen_pwGeneratorM2S__G1_______SM.omega*gen_pwGeneratorM2S__G1_______SM.SN*gen_pwGeneratorM2S__G1_______SM.HIn + gen_pwGeneratorM2S__G20______SM.omega*gen_pwGeneratorM2S__G20______SM.SN*gen_pwGeneratorM2S__G20______SM.HIn + gen_pwGeneratorM2S__G2_______SM.omega*gen_pwGeneratorM2S__G2_______SM.SN*gen_pwGeneratorM2S__G2_______SM.HIn + gen_pwGeneratorM2S__G3_______SM.omega*gen_pwGeneratorM2S__G3_______SM.SN*gen_pwGeneratorM2S__G3_______SM.HIn + gen_pwGeneratorM2S__G4_______SM.omega*gen_pwGeneratorM2S__G4_______SM.SN*gen_pwGeneratorM2S__G4_______SM.HIn + gen_pwGeneratorM2S__G5_______SM.omega*gen_pwGeneratorM2S__G5_______SM.SN*gen_pwGeneratorM2S__G5_______SM.HIn + gen_pwGeneratorM2S__G6_______SM.omega*gen_pwGeneratorM2S__G6_______SM.SN*gen_pwGeneratorM2S__G6_______SM.HIn + gen_pwGeneratorM2S__G7_______SM.omega*gen_pwGeneratorM2S__G7_______SM.SN*gen_pwGeneratorM2S__G7_______SM.HIn + gen_pwGeneratorM2S__G8_______SM.omega*gen_pwGeneratorM2S__G8_______SM.SN*gen_pwGeneratorM2S__G8_______SM.HIn + gen_pwGeneratorM2S__G9_______SM.omega*gen_pwGeneratorM2S__G9_______SM.SN*gen_pwGeneratorM2S__G9_______SM.HIn) / (gen_pwGeneratorM2S__G10______SM.SN*gen_pwGeneratorM2S__G10______SM.HIn + gen_pwGeneratorM2S__G11______SM.SN*gen_pwGeneratorM2S__G11______SM.HIn + gen_pwGeneratorM2S__G12______SM.SN*gen_pwGeneratorM2S__G12______SM.HIn + gen_pwGeneratorM2S__G13______SM.SN*gen_pwGeneratorM2S__G13______SM.HIn + gen_pwGeneratorM2S__G14______SM.SN*gen_pwGeneratorM2S__G14______SM.HIn + gen_pwGeneratorM2S__G15______SM.SN*gen_pwGeneratorM2S__G15______SM.HIn + gen_pwGeneratorM2S__G16______SM.SN*gen_pwGeneratorM2S__G16______SM.HIn + gen_pwGeneratorM2S__G17______SM.SN*gen_pwGeneratorM2S__G17______SM.HIn + gen_pwGeneratorM2S__G18______SM.SN*gen_pwGeneratorM2S__G18______SM.HIn + gen_pwGeneratorM2S__G19______SM.SN*gen_pwGeneratorM2S__G19______SM.HIn + gen_pwGeneratorM2S__G1_______SM.SN*gen_pwGeneratorM2S__G1_______SM.HIn + gen_pwGeneratorM2S__G20______SM.SN*gen_pwGeneratorM2S__G20______SM.HIn + gen_pwGeneratorM2S__G2_______SM.SN*gen_pwGeneratorM2S__G2_______SM.HIn + gen_pwGeneratorM2S__G3_______SM.SN*gen_pwGeneratorM2S__G3_______SM.HIn + gen_pwGeneratorM2S__G4_______SM.SN*gen_pwGeneratorM2S__G4_______SM.HIn + gen_pwGeneratorM2S__G5_______SM.SN*gen_pwGeneratorM2S__G5_______SM.HIn + gen_pwGeneratorM2S__G6_______SM.SN*gen_pwGeneratorM2S__G6_______SM.HIn + gen_pwGeneratorM2S__G7_______SM.SN*gen_pwGeneratorM2S__G7_______SM.HIn + gen_pwGeneratorM2S__G8_______SM.SN*gen_pwGeneratorM2S__G8_______SM.HIn + gen_pwGeneratorM2S__G9_______SM.SN*gen_pwGeneratorM2S__G9_______SM.HIn);

  connect(gen_pwGeneratorM2S__G10______SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__G11______SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__G12______SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__G13______SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__G14______SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__G15______SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__G16______SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__G17______SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__G18______SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__G19______SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__G1_______SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__G20______SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__G2_______SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__G3_______SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__G4_______SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__G5_______SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__G6_______SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__G7_______SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__G8_______SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__G9_______SM.omegaRef, omegaRef);

// Connecting REGULATORS and MACHINES
  connect(reg_htgpsat3__G10______SM.pin_CM, gen_pwGeneratorM2S__G10______SM.pin_CM) annotation (Line());
  connect(reg_htgpsat3__G10______SM.pin_OMEGA, gen_pwGeneratorM2S__G10______SM.pin_OMEGA) annotation (Line());
  connect(reg_oelpsat__G10______SM.pin_EFD, gen_pwGeneratorM2S__G10______SM.pin_EFD) annotation (Line());
  connect(reg_oelpsat__G10______SM.pin_ActivePowerSNREF, gen_pwGeneratorM2S__G10______SM.pin_ActivePowerSNREF) annotation (Line());
  connect(reg_oelpsat__G10______SM.pin_TerminalVoltage, gen_pwGeneratorM2S__G10______SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_oelpsat__G10______SM.pin_ReactivePowerSNREF, gen_pwGeneratorM2S__G10______SM.pin_ReactivePowerSNREF) annotation (Line());
  connect(reg_htgpsat3__G11______SM.pin_CM, gen_pwGeneratorM2S__G11______SM.pin_CM) annotation (Line());
  connect(reg_htgpsat3__G11______SM.pin_OMEGA, gen_pwGeneratorM2S__G11______SM.pin_OMEGA) annotation (Line());
  connect(reg_oelpsat__G11______SM.pin_EFD, gen_pwGeneratorM2S__G11______SM.pin_EFD) annotation (Line());
  connect(reg_oelpsat__G11______SM.pin_ActivePowerSNREF, gen_pwGeneratorM2S__G11______SM.pin_ActivePowerSNREF) annotation (Line());
  connect(reg_oelpsat__G11______SM.pin_TerminalVoltage, gen_pwGeneratorM2S__G11______SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_oelpsat__G11______SM.pin_ReactivePowerSNREF, gen_pwGeneratorM2S__G11______SM.pin_ReactivePowerSNREF) annotation (Line());
  connect(reg_htgpsat3__G12______SM.pin_CM, gen_pwGeneratorM2S__G12______SM.pin_CM) annotation (Line());
  connect(reg_htgpsat3__G12______SM.pin_OMEGA, gen_pwGeneratorM2S__G12______SM.pin_OMEGA) annotation (Line());
  connect(reg_oelpsat__G12______SM.pin_EFD, gen_pwGeneratorM2S__G12______SM.pin_EFD) annotation (Line());
  connect(reg_oelpsat__G12______SM.pin_ActivePowerSNREF, gen_pwGeneratorM2S__G12______SM.pin_ActivePowerSNREF) annotation (Line());
  connect(reg_oelpsat__G12______SM.pin_TerminalVoltage, gen_pwGeneratorM2S__G12______SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_oelpsat__G12______SM.pin_ReactivePowerSNREF, gen_pwGeneratorM2S__G12______SM.pin_ReactivePowerSNREF) annotation (Line());
  connect(reg_oelpsat__G13______SM.pin_EFD, gen_pwGeneratorM2S__G13______SM.pin_EFD) annotation (Line());
  connect(reg_oelpsat__G13______SM.pin_ActivePowerSNREF, gen_pwGeneratorM2S__G13______SM.pin_ActivePowerSNREF) annotation (Line());
  connect(reg_oelpsat__G13______SM.pin_TerminalVoltage, gen_pwGeneratorM2S__G13______SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_oelpsat__G13______SM.pin_ReactivePowerSNREF, gen_pwGeneratorM2S__G13______SM.pin_ReactivePowerSNREF) annotation (Line());
  connect(reg_govpsat1__G13______SM.pin_CM, gen_pwGeneratorM2S__G13______SM.pin_CM) annotation (Line());
  connect(reg_govpsat1__G13______SM.pin_OMEGA, gen_pwGeneratorM2S__G13______SM.pin_OMEGA) annotation (Line());
  connect(reg_oelpsat__G14______SM.pin_EFD, gen_pwGeneratorM2S__G14______SM.pin_EFD) annotation (Line());
  connect(reg_oelpsat__G14______SM.pin_ActivePowerSNREF, gen_pwGeneratorM2S__G14______SM.pin_ActivePowerSNREF) annotation (Line());
  connect(reg_oelpsat__G14______SM.pin_TerminalVoltage, gen_pwGeneratorM2S__G14______SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_oelpsat__G14______SM.pin_ReactivePowerSNREF, gen_pwGeneratorM2S__G14______SM.pin_ReactivePowerSNREF) annotation (Line());
  connect(reg_govpsat1__G14______SM.pin_CM, gen_pwGeneratorM2S__G14______SM.pin_CM) annotation (Line());
  connect(reg_govpsat1__G14______SM.pin_OMEGA, gen_pwGeneratorM2S__G14______SM.pin_OMEGA) annotation (Line());
  connect(reg_oelpsat__G15______SM.pin_EFD, gen_pwGeneratorM2S__G15______SM.pin_EFD) annotation (Line());
  connect(reg_oelpsat__G15______SM.pin_ActivePowerSNREF, gen_pwGeneratorM2S__G15______SM.pin_ActivePowerSNREF) annotation (Line());
  connect(reg_oelpsat__G15______SM.pin_TerminalVoltage, gen_pwGeneratorM2S__G15______SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_oelpsat__G15______SM.pin_ReactivePowerSNREF, gen_pwGeneratorM2S__G15______SM.pin_ReactivePowerSNREF) annotation (Line());
  connect(reg_govpsat1__G15______SM.pin_CM, gen_pwGeneratorM2S__G15______SM.pin_CM) annotation (Line());
  connect(reg_govpsat1__G15______SM.pin_OMEGA, gen_pwGeneratorM2S__G15______SM.pin_OMEGA) annotation (Line());
  connect(reg_oelpsat__G16______SM.pin_EFD, gen_pwGeneratorM2S__G16______SM.pin_EFD) annotation (Line());
  connect(reg_oelpsat__G16______SM.pin_ActivePowerSNREF, gen_pwGeneratorM2S__G16______SM.pin_ActivePowerSNREF) annotation (Line());
  connect(reg_oelpsat__G16______SM.pin_TerminalVoltage, gen_pwGeneratorM2S__G16______SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_oelpsat__G16______SM.pin_ReactivePowerSNREF, gen_pwGeneratorM2S__G16______SM.pin_ReactivePowerSNREF) annotation (Line());
  connect(reg_govpsat1__G16______SM.pin_CM, gen_pwGeneratorM2S__G16______SM.pin_CM) annotation (Line());
  connect(reg_govpsat1__G16______SM.pin_OMEGA, gen_pwGeneratorM2S__G16______SM.pin_OMEGA) annotation (Line());
  connect(reg_oelpsat__G17______SM.pin_EFD, gen_pwGeneratorM2S__G17______SM.pin_EFD) annotation (Line());
  connect(reg_oelpsat__G17______SM.pin_ActivePowerSNREF, gen_pwGeneratorM2S__G17______SM.pin_ActivePowerSNREF) annotation (Line());
  connect(reg_oelpsat__G17______SM.pin_TerminalVoltage, gen_pwGeneratorM2S__G17______SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_oelpsat__G17______SM.pin_ReactivePowerSNREF, gen_pwGeneratorM2S__G17______SM.pin_ReactivePowerSNREF) annotation (Line());
  connect(reg_govpsat1__G17______SM.pin_CM, gen_pwGeneratorM2S__G17______SM.pin_CM) annotation (Line());
  connect(reg_govpsat1__G17______SM.pin_OMEGA, gen_pwGeneratorM2S__G17______SM.pin_OMEGA) annotation (Line());
  connect(reg_oelpsat__G18______SM.pin_EFD, gen_pwGeneratorM2S__G18______SM.pin_EFD) annotation (Line());
  connect(reg_oelpsat__G18______SM.pin_ActivePowerSNREF, gen_pwGeneratorM2S__G18______SM.pin_ActivePowerSNREF) annotation (Line());
  connect(reg_oelpsat__G18______SM.pin_TerminalVoltage, gen_pwGeneratorM2S__G18______SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_oelpsat__G18______SM.pin_ReactivePowerSNREF, gen_pwGeneratorM2S__G18______SM.pin_ReactivePowerSNREF) annotation (Line());
  connect(reg_govpsat1__G18______SM.pin_CM, gen_pwGeneratorM2S__G18______SM.pin_CM) annotation (Line());
  connect(reg_govpsat1__G18______SM.pin_OMEGA, gen_pwGeneratorM2S__G18______SM.pin_OMEGA) annotation (Line());
  connect(reg_htgpsat3__G19______SM.pin_CM, gen_pwGeneratorM2S__G19______SM.pin_CM) annotation (Line());
  connect(reg_htgpsat3__G19______SM.pin_OMEGA, gen_pwGeneratorM2S__G19______SM.pin_OMEGA) annotation (Line());
  connect(reg_oelpsat__G19______SM.pin_EFD, gen_pwGeneratorM2S__G19______SM.pin_EFD) annotation (Line());
  connect(reg_oelpsat__G19______SM.pin_ActivePowerSNREF, gen_pwGeneratorM2S__G19______SM.pin_ActivePowerSNREF) annotation (Line());
  connect(reg_oelpsat__G19______SM.pin_TerminalVoltage, gen_pwGeneratorM2S__G19______SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_oelpsat__G19______SM.pin_ReactivePowerSNREF, gen_pwGeneratorM2S__G19______SM.pin_ReactivePowerSNREF) annotation (Line());
  connect(reg_htgpsat3__G1_______SM.pin_CM, gen_pwGeneratorM2S__G1_______SM.pin_CM) annotation (Line());
  connect(reg_htgpsat3__G1_______SM.pin_OMEGA, gen_pwGeneratorM2S__G1_______SM.pin_OMEGA) annotation (Line());
  connect(reg_oelpsat__G1_______SM.pin_EFD, gen_pwGeneratorM2S__G1_______SM.pin_EFD) annotation (Line());
  connect(reg_oelpsat__G1_______SM.pin_ActivePowerSNREF, gen_pwGeneratorM2S__G1_______SM.pin_ActivePowerSNREF) annotation (Line());
  connect(reg_oelpsat__G1_______SM.pin_TerminalVoltage, gen_pwGeneratorM2S__G1_______SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_oelpsat__G1_______SM.pin_ReactivePowerSNREF, gen_pwGeneratorM2S__G1_______SM.pin_ReactivePowerSNREF) annotation (Line());
  connect(reg_htgpsat3__G20______SM.pin_CM, gen_pwGeneratorM2S__G20______SM.pin_CM) annotation (Line());
  connect(reg_htgpsat3__G20______SM.pin_OMEGA, gen_pwGeneratorM2S__G20______SM.pin_OMEGA) annotation (Line());
  connect(reg_oelpsat__G20______SM.pin_EFD, gen_pwGeneratorM2S__G20______SM.pin_EFD) annotation (Line());
  connect(reg_oelpsat__G20______SM.pin_ActivePowerSNREF, gen_pwGeneratorM2S__G20______SM.pin_ActivePowerSNREF) annotation (Line());
  connect(reg_oelpsat__G20______SM.pin_TerminalVoltage, gen_pwGeneratorM2S__G20______SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_oelpsat__G20______SM.pin_ReactivePowerSNREF, gen_pwGeneratorM2S__G20______SM.pin_ReactivePowerSNREF) annotation (Line());
  connect(reg_htgpsat3__G2_______SM.pin_CM, gen_pwGeneratorM2S__G2_______SM.pin_CM) annotation (Line());
  connect(reg_htgpsat3__G2_______SM.pin_OMEGA, gen_pwGeneratorM2S__G2_______SM.pin_OMEGA) annotation (Line());
  connect(reg_oelpsat__G2_______SM.pin_EFD, gen_pwGeneratorM2S__G2_______SM.pin_EFD) annotation (Line());
  connect(reg_oelpsat__G2_______SM.pin_ActivePowerSNREF, gen_pwGeneratorM2S__G2_______SM.pin_ActivePowerSNREF) annotation (Line());
  connect(reg_oelpsat__G2_______SM.pin_TerminalVoltage, gen_pwGeneratorM2S__G2_______SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_oelpsat__G2_______SM.pin_ReactivePowerSNREF, gen_pwGeneratorM2S__G2_______SM.pin_ReactivePowerSNREF) annotation (Line());
  connect(reg_htgpsat3__G3_______SM.pin_CM, gen_pwGeneratorM2S__G3_______SM.pin_CM) annotation (Line());
  connect(reg_htgpsat3__G3_______SM.pin_OMEGA, gen_pwGeneratorM2S__G3_______SM.pin_OMEGA) annotation (Line());
  connect(reg_oelpsat__G3_______SM.pin_EFD, gen_pwGeneratorM2S__G3_______SM.pin_EFD) annotation (Line());
  connect(reg_oelpsat__G3_______SM.pin_ActivePowerSNREF, gen_pwGeneratorM2S__G3_______SM.pin_ActivePowerSNREF) annotation (Line());
  connect(reg_oelpsat__G3_______SM.pin_TerminalVoltage, gen_pwGeneratorM2S__G3_______SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_oelpsat__G3_______SM.pin_ReactivePowerSNREF, gen_pwGeneratorM2S__G3_______SM.pin_ReactivePowerSNREF) annotation (Line());
  connect(reg_htgpsat3__G4_______SM.pin_CM, gen_pwGeneratorM2S__G4_______SM.pin_CM) annotation (Line());
  connect(reg_htgpsat3__G4_______SM.pin_OMEGA, gen_pwGeneratorM2S__G4_______SM.pin_OMEGA) annotation (Line());
  connect(reg_oelpsat__G4_______SM.pin_EFD, gen_pwGeneratorM2S__G4_______SM.pin_EFD) annotation (Line());
  connect(reg_oelpsat__G4_______SM.pin_ActivePowerSNREF, gen_pwGeneratorM2S__G4_______SM.pin_ActivePowerSNREF) annotation (Line());
  connect(reg_oelpsat__G4_______SM.pin_TerminalVoltage, gen_pwGeneratorM2S__G4_______SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_oelpsat__G4_______SM.pin_ReactivePowerSNREF, gen_pwGeneratorM2S__G4_______SM.pin_ReactivePowerSNREF) annotation (Line());
  connect(reg_htgpsat3__G5_______SM.pin_CM, gen_pwGeneratorM2S__G5_______SM.pin_CM) annotation (Line());
  connect(reg_htgpsat3__G5_______SM.pin_OMEGA, gen_pwGeneratorM2S__G5_______SM.pin_OMEGA) annotation (Line());
  connect(reg_oelpsat__G5_______SM.pin_EFD, gen_pwGeneratorM2S__G5_______SM.pin_EFD) annotation (Line());
  connect(reg_oelpsat__G5_______SM.pin_ActivePowerSNREF, gen_pwGeneratorM2S__G5_______SM.pin_ActivePowerSNREF) annotation (Line());
  connect(reg_oelpsat__G5_______SM.pin_TerminalVoltage, gen_pwGeneratorM2S__G5_______SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_oelpsat__G5_______SM.pin_ReactivePowerSNREF, gen_pwGeneratorM2S__G5_______SM.pin_ReactivePowerSNREF) annotation (Line());
  connect(reg_oelpsat__G6_______SM.pin_EFD, gen_pwGeneratorM2S__G6_______SM.pin_EFD) annotation (Line());
  connect(reg_oelpsat__G6_______SM.pin_ActivePowerSNREF, gen_pwGeneratorM2S__G6_______SM.pin_ActivePowerSNREF) annotation (Line());
  connect(reg_oelpsat__G6_______SM.pin_TerminalVoltage, gen_pwGeneratorM2S__G6_______SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_oelpsat__G6_______SM.pin_ReactivePowerSNREF, gen_pwGeneratorM2S__G6_______SM.pin_ReactivePowerSNREF) annotation (Line());
  connect(reg_govpsat1__G6_______SM.pin_CM, gen_pwGeneratorM2S__G6_______SM.pin_CM) annotation (Line());
  connect(reg_govpsat1__G6_______SM.pin_OMEGA, gen_pwGeneratorM2S__G6_______SM.pin_OMEGA) annotation (Line());
  connect(reg_oelpsat__G7_______SM.pin_EFD, gen_pwGeneratorM2S__G7_______SM.pin_EFD) annotation (Line());
  connect(reg_oelpsat__G7_______SM.pin_ActivePowerSNREF, gen_pwGeneratorM2S__G7_______SM.pin_ActivePowerSNREF) annotation (Line());
  connect(reg_oelpsat__G7_______SM.pin_TerminalVoltage, gen_pwGeneratorM2S__G7_______SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_oelpsat__G7_______SM.pin_ReactivePowerSNREF, gen_pwGeneratorM2S__G7_______SM.pin_ReactivePowerSNREF) annotation (Line());
  connect(reg_govpsat1__G7_______SM.pin_CM, gen_pwGeneratorM2S__G7_______SM.pin_CM) annotation (Line());
  connect(reg_govpsat1__G7_______SM.pin_OMEGA, gen_pwGeneratorM2S__G7_______SM.pin_OMEGA) annotation (Line());
  connect(reg_htgpsat3__G8_______SM.pin_CM, gen_pwGeneratorM2S__G8_______SM.pin_CM) annotation (Line());
  connect(reg_htgpsat3__G8_______SM.pin_OMEGA, gen_pwGeneratorM2S__G8_______SM.pin_OMEGA) annotation (Line());
  connect(reg_oelpsat__G8_______SM.pin_EFD, gen_pwGeneratorM2S__G8_______SM.pin_EFD) annotation (Line());
  connect(reg_oelpsat__G8_______SM.pin_ActivePowerSNREF, gen_pwGeneratorM2S__G8_______SM.pin_ActivePowerSNREF) annotation (Line());
  connect(reg_oelpsat__G8_______SM.pin_TerminalVoltage, gen_pwGeneratorM2S__G8_______SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_oelpsat__G8_______SM.pin_ReactivePowerSNREF, gen_pwGeneratorM2S__G8_______SM.pin_ReactivePowerSNREF) annotation (Line());
  connect(reg_htgpsat3__G9_______SM.pin_CM, gen_pwGeneratorM2S__G9_______SM.pin_CM) annotation (Line());
  connect(reg_htgpsat3__G9_______SM.pin_OMEGA, gen_pwGeneratorM2S__G9_______SM.pin_OMEGA) annotation (Line());
  connect(reg_oelpsat__G9_______SM.pin_EFD, gen_pwGeneratorM2S__G9_______SM.pin_EFD) annotation (Line());
  connect(reg_oelpsat__G9_______SM.pin_ActivePowerSNREF, gen_pwGeneratorM2S__G9_______SM.pin_ActivePowerSNREF) annotation (Line());
  connect(reg_oelpsat__G9_______SM.pin_TerminalVoltage, gen_pwGeneratorM2S__G9_______SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_oelpsat__G9_______SM.pin_ReactivePowerSNREF, gen_pwGeneratorM2S__G9_______SM.pin_ReactivePowerSNREF) annotation (Line());

// Connecting LINES
  connect(bus__N1011____TN.p, line__N1011____N1013____1_AC.p) annotation (Line());
  connect(line__N1011____N1013____1_AC.n, bus__N1013____TN.p) annotation (Line());
  connect(bus__N1011____TN.p, line__N1011____N1013____2_AC.p) annotation (Line());
  connect(line__N1011____N1013____2_AC.n, bus__N1013____TN.p) annotation (Line());
  connect(bus__N1012____TN.p, line__N1012____N1014____1_AC.p) annotation (Line());
  connect(line__N1012____N1014____1_AC.n, bus__N1014____TN.p) annotation (Line());
  connect(bus__N1012____TN.p, line__N1012____N1014____2_AC.p) annotation (Line());
  connect(line__N1012____N1014____2_AC.n, bus__N1014____TN.p) annotation (Line());
  connect(bus__N1013____TN.p, line__N1013____N1014____1_AC.p) annotation (Line());
  connect(line__N1013____N1014____1_AC.n, bus__N1014____TN.p) annotation (Line());
  connect(bus__N1013____TN.p, line__N1013____N1014____2_AC.p) annotation (Line());
  connect(line__N1013____N1014____2_AC.n, bus__N1014____TN.p) annotation (Line());
  connect(bus__N1021____TN.p, line__N1021____N1022____1_AC.p) annotation (Line());
  connect(line__N1021____N1022____1_AC.n, bus__N1022____TN.p) annotation (Line());
  connect(bus__N1021____TN.p, line__N1021____N1022____2_AC.p) annotation (Line());
  connect(line__N1021____N1022____2_AC.n, bus__N1022____TN.p) annotation (Line());
  connect(bus__N1041____TN.p, line__N1041____N1043____1_AC.p) annotation (Line());
  connect(line__N1041____N1043____1_AC.n, bus__N1043____TN.p) annotation (Line());
  connect(bus__N1041____TN.p, line__N1041____N1043____2_AC.p) annotation (Line());
  connect(line__N1041____N1043____2_AC.n, bus__N1043____TN.p) annotation (Line());
  connect(bus__N1041____TN.p, line__N1041____N1045____1_AC.p) annotation (Line());
  connect(line__N1041____N1045____1_AC.n, bus__N1045____TN.p) annotation (Line());
  connect(bus__N1041____TN.p, line__N1041____N1045____2_AC.p) annotation (Line());
  connect(line__N1041____N1045____2_AC.n, bus__N1045____TN.p) annotation (Line());
  connect(bus__N1042____TN.p, line__N1042____N1044____1_AC.p) annotation (Line());
  connect(line__N1042____N1044____1_AC.n, bus__N1044____TN.p) annotation (Line());
  connect(bus__N1042____TN.p, line__N1042____N1044____2_AC.p) annotation (Line());
  connect(line__N1042____N1044____2_AC.n, bus__N1044____TN.p) annotation (Line());
  connect(bus__N1042____TN.p, line__N1042____N1045____1_AC.p) annotation (Line());
  connect(line__N1042____N1045____1_AC.n, bus__N1045____TN.p) annotation (Line());
  connect(bus__N1043____TN.p, line__N1043____N1044____1_AC.p) annotation (Line());
  connect(line__N1043____N1044____1_AC.n, bus__N1044____TN.p) annotation (Line());
  connect(bus__N1043____TN.p, line__N1043____N1044____2_AC.p) annotation (Line());
  connect(line__N1043____N1044____2_AC.n, bus__N1044____TN.p) annotation (Line());
  connect(bus__N2031____TN.p, line__N2031____N2032____1_AC.p) annotation (Line());
  connect(line__N2031____N2032____1_AC.n, bus__N2032____TN.p) annotation (Line());
  connect(bus__N2031____TN.p, line__N2031____N2032____2_AC.p) annotation (Line());
  connect(line__N2031____N2032____2_AC.n, bus__N2032____TN.p) annotation (Line());
  connect(bus__N4011____TN.p, line__N4011____N4012____1_AC.p) annotation (Line());
  connect(line__N4011____N4012____1_AC.n, bus__N4012____TN.p) annotation (Line());
  connect(bus__N4011____TN.p, line__N4011____N4021____1_AC.p) annotation (Line());
  connect(line__N4011____N4021____1_AC.n, bus__N4021____TN.p) annotation (Line());
  connect(bus__N4011____TN.p, line__N4011____N4022____1_AC.p) annotation (Line());
  connect(line__N4011____N4022____1_AC.n, bus__N4022____TN.p) annotation (Line());
  connect(bus__N4011____TN.p, line__N4011____N4071____1_AC.p) annotation (Line());
  connect(line__N4011____N4071____1_AC.n, bus__N4071____TN.p) annotation (Line());
  connect(bus__N4012____TN.p, line__N4012____N4022____1_AC.p) annotation (Line());
  connect(line__N4012____N4022____1_AC.n, bus__N4022____TN.p) annotation (Line());
  connect(bus__N4012____TN.p, line__N4012____N4071____1_AC.p) annotation (Line());
  connect(line__N4012____N4071____1_AC.n, bus__N4071____TN.p) annotation (Line());
  connect(bus__N4021____TN.p, line__N4021____N4032____1_AC.p) annotation (Line());
  connect(line__N4021____N4032____1_AC.n, bus__N4032____TN.p) annotation (Line());
  connect(bus__N4021____TN.p, line__N4021____N4042____1_AC.p) annotation (Line());
  connect(line__N4021____N4042____1_AC.n, bus__N4042____TN.p) annotation (Line());
  connect(bus__N4022____TN.p, line__N4022____N4031____1_AC.p) annotation (Line());
  connect(line__N4022____N4031____1_AC.n, bus__N4031____TN.p) annotation (Line());
  connect(bus__N4022____TN.p, line__N4022____N4031____2_AC.p) annotation (Line());
  connect(line__N4022____N4031____2_AC.n, bus__N4031____TN.p) annotation (Line());
  connect(bus__N4031____TN.p, line__N4031____N4032____1_AC.p) annotation (Line());
  connect(line__N4031____N4032____1_AC.n, bus__N4032____TN.p) annotation (Line());
  connect(bus__N4031____TN.p, line__N4031____N4041____1_AC.p) annotation (Line());
  connect(line__N4031____N4041____1_AC.n, bus__N4041____TN.p) annotation (Line());
  connect(bus__N4031____TN.p, line__N4031____N4041____2_AC.p) annotation (Line());
  connect(line__N4031____N4041____2_AC.n, bus__N4041____TN.p) annotation (Line());
  connect(bus__N4032____TN.p, line__N4032____N4042____1_AC.p) annotation (Line());
  connect(line__N4032____N4042____1_AC.n, bus__N4042____TN.p) annotation (Line());
  connect(bus__N4032____TN.p, line__N4032____N4044____1_AC.p) annotation (Line());
  connect(line__N4032____N4044____1_AC.n, bus__N4044____TN.p) annotation (Line());
  connect(bus__N4041____TN.p, line__N4041____N4044____1_AC.p) annotation (Line());
  connect(line__N4041____N4044____1_AC.n, bus__N4044____TN.p) annotation (Line());
  connect(bus__N4041____TN.p, line__N4041____N4061____1_AC.p) annotation (Line());
  connect(line__N4041____N4061____1_AC.n, bus__N4061____TN.p) annotation (Line());
  connect(bus__N4042____TN.p, line__N4042____N4043____1_AC.p) annotation (Line());
  connect(line__N4042____N4043____1_AC.n, bus__N4043____TN.p) annotation (Line());
  connect(bus__N4042____TN.p, line__N4042____N4044____1_AC.p) annotation (Line());
  connect(line__N4042____N4044____1_AC.n, bus__N4044____TN.p) annotation (Line());
  connect(bus__N4043____TN.p, line__N4043____N4044____1_AC.p) annotation (Line());
  connect(line__N4043____N4044____1_AC.n, bus__N4044____TN.p) annotation (Line());
  connect(bus__N4043____TN.p, line__N4043____N4046____1_AC.p) annotation (Line());
  connect(line__N4043____N4046____1_AC.n, bus__N4046____TN.p) annotation (Line());
  connect(bus__N4043____TN.p, line__N4043____N4047____1_AC.p) annotation (Line());
  connect(line__N4043____N4047____1_AC.n, bus__N4047____TN.p) annotation (Line());
  connect(bus__N4044____TN.p, line__N4044____N4045____1_AC.p) annotation (Line());
  connect(line__N4044____N4045____1_AC.n, bus__N4045____TN.p) annotation (Line());
  connect(bus__N4044____TN.p, line__N4044____N4045____2_AC.p) annotation (Line());
  connect(line__N4044____N4045____2_AC.n, bus__N4045____TN.p) annotation (Line());
  connect(bus__N4045____TN.p, line__N4045____N4051____1_AC.p) annotation (Line());
  connect(line__N4045____N4051____1_AC.n, bus__N4051____TN.p) annotation (Line());
  connect(bus__N4045____TN.p, line__N4045____N4051____2_AC.p) annotation (Line());
  connect(line__N4045____N4051____2_AC.n, bus__N4051____TN.p) annotation (Line());
  connect(bus__N4045____TN.p, line__N4045____N4062____1_AC.p) annotation (Line());
  connect(line__N4045____N4062____1_AC.n, bus__N4062____TN.p) annotation (Line());
  connect(bus__N4046____TN.p, line__N4046____N4047____1_AC.p) annotation (Line());
  connect(line__N4046____N4047____1_AC.n, bus__N4047____TN.p) annotation (Line());
  connect(bus__N4061____TN.p, line__N4061____N4062____1_AC.p) annotation (Line());
  connect(line__N4061____N4062____1_AC.n, bus__N4062____TN.p) annotation (Line());
  connect(bus__N4062____TN.p, line__N4062____N4063____1_AC.p) annotation (Line());
  connect(line__N4062____N4063____1_AC.n, bus__N4063____TN.p) annotation (Line());
  connect(bus__N4062____TN.p, line__N4062____N4063____2_AC.p) annotation (Line());
  connect(line__N4062____N4063____2_AC.n, bus__N4063____TN.p) annotation (Line());
  connect(bus__N4071____TN.p, line__N4071____N4072____1_AC.p) annotation (Line());
  connect(line__N4071____N4072____1_AC.n, bus__N4072____TN.p) annotation (Line());
  connect(bus__N4071____TN.p, line__N4071____N4072____2_AC.p) annotation (Line());
  connect(line__N4071____N4072____2_AC.n, bus__N4072____TN.p) annotation (Line());

// COUPLING DEVICES

// Connecting LOADS
  connect(bus__N1011____TN.p, load__N1011____EC.p) annotation (Line());
  connect(bus__N1012____TN.p, load__N1012____EC.p) annotation (Line());
  connect(bus__N1013____TN.p, load__N1013____EC.p) annotation (Line());
  connect(bus__N1022____TN.p, load__N1022____EC.p) annotation (Line());
  connect(bus__N1041____TN.p, load__N1041____EC.p) annotation (Line());
  connect(bus__N1042____TN.p, load__N1042____EC.p) annotation (Line());
  connect(bus__N1043____TN.p, load__N1043____EC.p) annotation (Line());
  connect(bus__N1044____TN.p, load__N1044____EC.p) annotation (Line());
  connect(bus__N1045____TN.p, load__N1045____EC.p) annotation (Line());
  connect(bus__N2031____TN.p, load__N2031____EC.p) annotation (Line());
  connect(bus__N2032____TN.p, load__N2032____EC.p) annotation (Line());
  connect(bus__N4041____TN.p, load__N4041____EC.p) annotation (Line());
  connect(bus__N4042____TN.p, load__N4042____EC.p) annotation (Line());
  connect(bus__N4043____TN.p, load__N4043____EC.p) annotation (Line());
  connect(bus__N4046____TN.p, load__N4046____EC.p) annotation (Line());
  connect(bus__N4047____TN.p, load__N4047____EC.p) annotation (Line());
  connect(bus__N4051____TN.p, load__N4051____EC.p) annotation (Line());
  connect(bus__N4061____TN.p, load__N4061____EC.p) annotation (Line());
  connect(bus__N4062____TN.p, load__N4062____EC.p) annotation (Line());
  connect(bus__N4063____TN.p, load__N4063____EC.p) annotation (Line());
  connect(bus__N4071____TN.p, load__N4071____EC.p) annotation (Line());
  connect(bus__N4072____TN.p, load__N4072____EC.p) annotation (Line());

// Connecting Capacitors
  connect(bus__N1022____TN.p, cap_pwCapacitorBank__N1022____SC.p) annotation (Line());
  connect(bus__N1041____TN.p, cap_pwCapacitorBank__N1041____SC.p) annotation (Line());
  connect(bus__N1043____TN.p, cap_pwCapacitorBank__N1043____SC.p) annotation (Line());
  connect(bus__N1044____TN.p, cap_pwCapacitorBank__N1044____SC.p) annotation (Line());
  connect(bus__N1045____TN.p, cap_pwCapacitorBank__N1045____SC.p) annotation (Line());
  connect(bus__N4012____TN.p, cap_pwCapacitorBank__N4012____SC.p) annotation (Line());
  connect(bus__N4041____TN.p, cap_pwCapacitorBank__N4041____SC.p) annotation (Line());
  connect(bus__N4043____TN.p, cap_pwCapacitorBank__N4043____SC.p) annotation (Line());
  connect(bus__N4046____TN.p, cap_pwCapacitorBank__N4046____SC.p) annotation (Line());
  connect(bus__N4051____TN.p, cap_pwCapacitorBank__N4051____SC.p) annotation (Line());
  connect(bus__N4071____TN.p, cap_pwCapacitorBank__N4071____SC.p) annotation (Line());

// Connecting GENERATORS
  connect(bus__NG10_____TN.p, gen_pwGeneratorM2S__G10______SM.sortie) annotation (Line());
  connect(bus__NG11_____TN.p, gen_pwGeneratorM2S__G11______SM.sortie) annotation (Line());
  connect(bus__NG12_____TN.p, gen_pwGeneratorM2S__G12______SM.sortie) annotation (Line());
  connect(bus__NG13_____TN.p, gen_pwGeneratorM2S__G13______SM.sortie) annotation (Line());
  connect(bus__NG14_____TN.p, gen_pwGeneratorM2S__G14______SM.sortie) annotation (Line());
  connect(bus__NG15_____TN.p, gen_pwGeneratorM2S__G15______SM.sortie) annotation (Line());
  connect(bus__NG16_____TN.p, gen_pwGeneratorM2S__G16______SM.sortie) annotation (Line());
  connect(bus__NG17_____TN.p, gen_pwGeneratorM2S__G17______SM.sortie) annotation (Line());
  connect(bus__NG18_____TN.p, gen_pwGeneratorM2S__G18______SM.sortie) annotation (Line());
  connect(bus__NG19_____TN.p, gen_pwGeneratorM2S__G19______SM.sortie) annotation (Line());
  connect(bus__NG1______TN.p, gen_pwGeneratorM2S__G1_______SM.sortie) annotation (Line());
  connect(bus__NG20_____TN.p, gen_pwGeneratorM2S__G20______SM.sortie) annotation (Line());
  connect(bus__NG2______TN.p, gen_pwGeneratorM2S__G2_______SM.sortie) annotation (Line());
  connect(bus__NG3______TN.p, gen_pwGeneratorM2S__G3_______SM.sortie) annotation (Line());
  connect(bus__NG4______TN.p, gen_pwGeneratorM2S__G4_______SM.sortie) annotation (Line());
  connect(bus__NG5______TN.p, gen_pwGeneratorM2S__G5_______SM.sortie) annotation (Line());
  connect(bus__NG6______TN.p, gen_pwGeneratorM2S__G6_______SM.sortie) annotation (Line());
  connect(bus__NG7______TN.p, gen_pwGeneratorM2S__G7_______SM.sortie) annotation (Line());
  connect(bus__NG8______TN.p, gen_pwGeneratorM2S__G8_______SM.sortie) annotation (Line());
  connect(bus__NG9______TN.p, gen_pwGeneratorM2S__G9_______SM.sortie) annotation (Line());

// Connecting FIXED TRANSFORMERS
  connect(bus__N1011____TN.p, trafo__N1011____N4011____1_PT.p) annotation (Line());
  connect(trafo__N1011____N4011____1_PT.n, bus__N4011____TN.p) annotation (Line());
  connect(bus__N1012____TN.p, trafo__N1012____N4012____1_PT.p) annotation (Line());
  connect(trafo__N1012____N4012____1_PT.n, bus__N4012____TN.p) annotation (Line());
  connect(bus__N1022____TN.p, trafo__N1022____N4022____1_PT.p) annotation (Line());
  connect(trafo__N1022____N4022____1_PT.n, bus__N4022____TN.p) annotation (Line());
  connect(bus__N1044____TN.p, trafo__N1044____N4044____1_PT.p) annotation (Line());
  connect(trafo__N1044____N4044____1_PT.n, bus__N4044____TN.p) annotation (Line());
  connect(bus__N1044____TN.p, trafo__N1044____N4044____2_PT.p) annotation (Line());
  connect(trafo__N1044____N4044____2_PT.n, bus__N4044____TN.p) annotation (Line());
  connect(bus__N1045____TN.p, trafo__N1045____N4045____1_PT.p) annotation (Line());
  connect(trafo__N1045____N4045____1_PT.n, bus__N4045____TN.p) annotation (Line());
  connect(bus__N1045____TN.p, trafo__N1045____N4045____2_PT.p) annotation (Line());
  connect(trafo__N1045____N4045____2_PT.n, bus__N4045____TN.p) annotation (Line());
  connect(bus__N2031____TN.p, trafo__N2031____N4031____1_PT.p) annotation (Line());
  connect(trafo__N2031____N4031____1_PT.n, bus__N4031____TN.p) annotation (Line());
  connect(bus__NG10_____TN.p, trafo__NG10_____N4012____1_PT.p) annotation (Line());
  connect(trafo__NG10_____N4012____1_PT.n, bus__N4012____TN.p) annotation (Line());
  connect(bus__NG11_____TN.p, trafo__NG11_____N4021____1_PT.p) annotation (Line());
  connect(trafo__NG11_____N4021____1_PT.n, bus__N4021____TN.p) annotation (Line());
  connect(bus__NG12_____TN.p, trafo__NG12_____N4031____1_PT.p) annotation (Line());
  connect(trafo__NG12_____N4031____1_PT.n, bus__N4031____TN.p) annotation (Line());
  connect(bus__NG13_____TN.p, trafo__NG13_____N4041____1_PT.p) annotation (Line());
  connect(trafo__NG13_____N4041____1_PT.n, bus__N4041____TN.p) annotation (Line());
  connect(bus__NG14_____TN.p, trafo__NG14_____N4042____1_PT.p) annotation (Line());
  connect(trafo__NG14_____N4042____1_PT.n, bus__N4042____TN.p) annotation (Line());
  connect(bus__NG15_____TN.p, trafo__NG15_____N4047____1_PT.p) annotation (Line());
  connect(trafo__NG15_____N4047____1_PT.n, bus__N4047____TN.p) annotation (Line());
  connect(bus__NG16_____TN.p, trafo__NG16_____N4051____1_PT.p) annotation (Line());
  connect(trafo__NG16_____N4051____1_PT.n, bus__N4051____TN.p) annotation (Line());
  connect(bus__NG17_____TN.p, trafo__NG17_____N4062____1_PT.p) annotation (Line());
  connect(trafo__NG17_____N4062____1_PT.n, bus__N4062____TN.p) annotation (Line());
  connect(bus__NG18_____TN.p, trafo__NG18_____N4063____1_PT.p) annotation (Line());
  connect(trafo__NG18_____N4063____1_PT.n, bus__N4063____TN.p) annotation (Line());
  connect(bus__NG19_____TN.p, trafo__NG19_____N4071____1_PT.p) annotation (Line());
  connect(trafo__NG19_____N4071____1_PT.n, bus__N4071____TN.p) annotation (Line());
  connect(bus__NG1______TN.p, trafo__NG1______N1012____1_PT.p) annotation (Line());
  connect(trafo__NG1______N1012____1_PT.n, bus__N1012____TN.p) annotation (Line());
  connect(bus__NG20_____TN.p, trafo__NG20_____N4072____1_PT.p) annotation (Line());
  connect(trafo__NG20_____N4072____1_PT.n, bus__N4072____TN.p) annotation (Line());
  connect(bus__NG2______TN.p, trafo__NG2______N1013____1_PT.p) annotation (Line());
  connect(trafo__NG2______N1013____1_PT.n, bus__N1013____TN.p) annotation (Line());
  connect(bus__NG3______TN.p, trafo__NG3______N1014____1_PT.p) annotation (Line());
  connect(trafo__NG3______N1014____1_PT.n, bus__N1014____TN.p) annotation (Line());
  connect(bus__NG4______TN.p, trafo__NG4______N1021____1_PT.p) annotation (Line());
  connect(trafo__NG4______N1021____1_PT.n, bus__N1021____TN.p) annotation (Line());
  connect(bus__NG5______TN.p, trafo__NG5______N1022____1_PT.p) annotation (Line());
  connect(trafo__NG5______N1022____1_PT.n, bus__N1022____TN.p) annotation (Line());
  connect(bus__NG6______TN.p, trafo__NG6______N1042____1_PT.p) annotation (Line());
  connect(trafo__NG6______N1042____1_PT.n, bus__N1042____TN.p) annotation (Line());
  connect(bus__NG7______TN.p, trafo__NG7______N1043____1_PT.p) annotation (Line());
  connect(trafo__NG7______N1043____1_PT.n, bus__N1043____TN.p) annotation (Line());
  connect(bus__NG8______TN.p, trafo__NG8______N2032____1_PT.p) annotation (Line());
  connect(trafo__NG8______N2032____1_PT.n, bus__N2032____TN.p) annotation (Line());
  connect(bus__NG9______TN.p, trafo__NG9______N4011____1_PT.p) annotation (Line());
  connect(trafo__NG9______N4011____1_PT.n, bus__N4011____TN.p) annotation (Line());

// Connecting OTHERS
annotation (uses(Modelica(version="3.2.1")));
end Nordic32;

