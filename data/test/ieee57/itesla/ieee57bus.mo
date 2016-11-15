within ;
model ieee57bus
  parameter Real SNREF = 100.0;
  Modelica.Blocks.Interfaces.RealOutput omegaRef;


// BUSES
  iPSL.Electrical.Buses.Bus bus__BEAV___6_TN (
	 V_0 = 0.98,
	 angle_0 = -8.674126
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___10_TN (
	 V_0 = 0.98624223,
	 angle_0 = -11.449654
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___13_TN (
	 V_0 = 0.9788874,
	 angle_0 = -9.803517
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___14_TN (
	 V_0 = 0.9701768,
	 angle_0 = -9.3503065
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___15_TN (
	 V_0 = 0.98803157,
	 angle_0 = -7.1901693
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___16_TN (
	 V_0 = 1.0133687,
	 angle_0 = -8.858935
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___17_TN (
	 V_0 = 1.0174543,
	 angle_0 = -5.3958926
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___19_TN (
	 V_0 = 0.97015786,
	 angle_0 = -13.226504
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___20_TN (
	 V_0 = 0.9637902,
	 angle_0 = -13.444308
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___21_TN (
	 V_0 = 1.0084982,
	 angle_0 = -12.929008
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___22_TN (
	 V_0 = 1.0097439,
	 angle_0 = -12.874311
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___23_TN (
	 V_0 = 1.0083299,
	 angle_0 = -12.93956
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___24_TN (
	 V_0 = 0.999233,
	 angle_0 = -13.292125
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___25_TN (
	 V_0 = 0.98252076,
	 angle_0 = -18.17323
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___26_TN (
	 V_0 = 0.9588182,
	 angle_0 = -12.981262
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___27_TN (
	 V_0 = 0.9815411,
	 angle_0 = -11.513625
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___28_TN (
	 V_0 = 0.99667805,
	 angle_0 = -10.481612
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___29_TN (
	 V_0 = 1.0102198,
	 angle_0 = -9.771785
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___30_TN (
	 V_0 = 0.9626613,
	 angle_0 = -18.71965
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___31_TN (
	 V_0 = 0.93593234,
	 angle_0 = -19.383808
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___32_TN (
	 V_0 = 0.9498747,
	 angle_0 = -18.51234
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___33_TN (
	 V_0 = 0.9475806,
	 angle_0 = -18.552011
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___34_TN (
	 V_0 = 0.9592,
	 angle_0 = -14.148968
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___35_TN (
	 V_0 = 0.96621186,
	 angle_0 = -13.906195
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___36_TN (
	 V_0 = 0.97582805,
	 angle_0 = -13.634819
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___37_TN (
	 V_0 = 0.98488647,
	 angle_0 = -13.445923
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___38_TN (
	 V_0 = 1.0128124,
	 angle_0 = -12.734626
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___39_TN (
	 V_0 = 0.9828231,
	 angle_0 = -13.491035
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___40_TN (
	 V_0 = 0.9728106,
	 angle_0 = -13.658242
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___42_TN (
	 V_0 = 0.966526,
	 angle_0 = -15.532791
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___44_TN (
	 V_0 = 1.0167985,
	 angle_0 = -11.856466
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___45_TN (
	 V_0 = 1.0360049,
	 angle_0 = -9.270095
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___46_TN (
	 V_0 = 1.0597974,
	 angle_0 = -11.116074
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___47_TN (
	 V_0 = 1.0332513,
	 angle_0 = -12.511594
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___48_TN (
	 V_0 = 1.0273505,
	 angle_0 = -12.61066
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___49_TN (
	 V_0 = 1.0362455,
	 angle_0 = -12.936085
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___50_TN (
	 V_0 = 1.0233357,
	 angle_0 = -13.412712
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___51_TN (
	 V_0 = 1.0522612,
	 angle_0 = -12.533393
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___52_TN (
	 V_0 = 0.98036855,
	 angle_0 = -11.497566
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___53_TN (
	 V_0 = 0.97094554,
	 angle_0 = -12.252597
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___54_TN (
	 V_0 = 0.9963188,
	 angle_0 = -11.709659
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___56_TN (
	 V_0 = 0.96836853,
	 angle_0 = -16.065071
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___57_TN (
	 V_0 = 0.964826,
	 angle_0 = -16.5837
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS____5_TN (
	 V_0 = 0.9764987,
	 angle_0 = -8.546415
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS____7_TN (
	 V_0 = 0.9842016,
	 angle_0 = -7.6014037
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__CLIN___8_TN (
	 V_0 = 1.005,
	 angle_0 = -4.4779196
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__GLEN__12_TN (
	 V_0 = 1.0150001,
	 angle_0 = -10.47121
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__KANA___1_TN (
	 V_0 = 1.0400001,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__LOGA___3_TN (
	 V_0 = 0.98499995,
	 angle_0 = -5.9881263
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SALT__55_TN (
	 V_0 = 1.030786,
	 angle_0 = -10.8011265
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SALT___9_TN (
	 V_0 = 0.98,
	 angle_0 = -9.584669
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SPRI__18_TN (
	 V_0 = 1.0006592,
	 angle_0 = -11.729641
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SPRI___4_TN (
	 V_0 = 0.9807796,
	 angle_0 = -7.337369
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__TAZE__11_TN (
	 V_0 = 0.9739623,
	 angle_0 = -10.193248
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__TAZE__41_TN (
	 V_0 = 0.9962168,
	 angle_0 = -14.07668
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__TAZE__43_TN (
	 V_0 = 1.0095638,
	 angle_0 = -11.354389
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__TURN___2_TN (
	 V_0 = 1.01,
	 angle_0 = -1.1881632
	 ) annotation (Placement(transformation()));

// LOADS
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BEAV___6_EC (
	 V_0 = 0.98,
	 P_0 = 75.0,
	 Q_0 = 2.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -8.674126
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___10_EC (
	 V_0 = 0.98624223,
	 P_0 = 5.0,
	 Q_0 = 2.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -11.449654
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___13_EC (
	 V_0 = 0.9788874,
	 P_0 = 18.0,
	 Q_0 = 2.3,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -9.803517
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___14_EC (
	 V_0 = 0.9701768,
	 P_0 = 10.5,
	 Q_0 = 5.3,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -9.3503065
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___15_EC (
	 V_0 = 0.98803157,
	 P_0 = 22.0,
	 Q_0 = 5.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -7.1901693
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___16_EC (
	 V_0 = 1.0133687,
	 P_0 = 43.0,
	 Q_0 = 3.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -8.858935
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___17_EC (
	 V_0 = 1.0174543,
	 P_0 = 42.0,
	 Q_0 = 8.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -5.3958926
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___19_EC (
	 V_0 = 0.97015786,
	 P_0 = 3.3,
	 Q_0 = 0.6,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -13.226504
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___20_EC (
	 V_0 = 0.9637902,
	 P_0 = 2.3,
	 Q_0 = 1.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -13.444308
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___23_EC (
	 V_0 = 1.0083299,
	 P_0 = 6.3,
	 Q_0 = 2.1,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -12.93956
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___25_EC (
	 V_0 = 0.98252076,
	 P_0 = 6.3,
	 Q_0 = 3.2,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -18.17323
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___27_EC (
	 V_0 = 0.9815411,
	 P_0 = 9.3,
	 Q_0 = 0.5,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -11.513625
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___28_EC (
	 V_0 = 0.99667805,
	 P_0 = 4.6,
	 Q_0 = 2.3,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -10.481612
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___29_EC (
	 V_0 = 1.0102198,
	 P_0 = 17.0,
	 Q_0 = 2.6,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -9.771785
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___30_EC (
	 V_0 = 0.9626613,
	 P_0 = 3.6,
	 Q_0 = 1.8,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -18.71965
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___31_EC (
	 V_0 = 0.93593234,
	 P_0 = 5.8,
	 Q_0 = 2.9,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -19.383808
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___32_EC (
	 V_0 = 0.9498747,
	 P_0 = 1.6,
	 Q_0 = 0.8,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -18.51234
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___33_EC (
	 V_0 = 0.9475806,
	 P_0 = 3.8,
	 Q_0 = 1.9,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -18.552011
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___35_EC (
	 V_0 = 0.96621186,
	 P_0 = 6.0,
	 Q_0 = 3.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -13.906195
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___38_EC (
	 V_0 = 1.0128124,
	 P_0 = 14.0,
	 Q_0 = 7.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -12.734626
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___42_EC (
	 V_0 = 0.966526,
	 P_0 = 7.1,
	 Q_0 = 4.4,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -15.532791
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___44_EC (
	 V_0 = 1.0167985,
	 P_0 = 12.0,
	 Q_0 = 1.8,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -11.856466
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___47_EC (
	 V_0 = 1.0332513,
	 P_0 = 29.7,
	 Q_0 = 11.6,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -12.511594
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___49_EC (
	 V_0 = 1.0362455,
	 P_0 = 18.0,
	 Q_0 = 8.5,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -12.936085
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___50_EC (
	 V_0 = 1.0233357,
	 P_0 = 21.0,
	 Q_0 = 10.5,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -13.412712
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___51_EC (
	 V_0 = 1.0522612,
	 P_0 = 18.0,
	 Q_0 = 5.3,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -12.533393
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___52_EC (
	 V_0 = 0.98036855,
	 P_0 = 4.9,
	 Q_0 = 2.2,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -11.497566
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___53_EC (
	 V_0 = 0.97094554,
	 P_0 = 20.0,
	 Q_0 = 10.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -12.252597
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___54_EC (
	 V_0 = 0.9963188,
	 P_0 = 4.1,
	 Q_0 = 1.4,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -11.709659
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___56_EC (
	 V_0 = 0.96836853,
	 P_0 = 7.6,
	 Q_0 = 2.2,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -16.065071
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___57_EC (
	 V_0 = 0.964826,
	 P_0 = 6.7,
	 Q_0 = 2.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -16.5837
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS____5_EC (
	 V_0 = 0.9764987,
	 P_0 = 13.0,
	 Q_0 = 4.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -8.546415
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__CLIN___8_EC (
	 V_0 = 1.005,
	 P_0 = 150.0,
	 Q_0 = 22.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -4.4779196
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__GLEN__12_EC (
	 V_0 = 1.0150001,
	 P_0 = 377.0,
	 Q_0 = 24.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -10.47121
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__KANA___1_EC (
	 V_0 = 1.0400001,
	 P_0 = 55.0,
	 Q_0 = 17.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOGA___3_EC (
	 V_0 = 0.98499995,
	 P_0 = 41.0,
	 Q_0 = 21.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -5.9881263
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__SALT__55_EC (
	 V_0 = 1.030786,
	 P_0 = 6.8,
	 Q_0 = 3.4,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -10.8011265
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__SALT___9_EC (
	 V_0 = 0.98,
	 P_0 = 121.0,
	 Q_0 = 26.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -9.584669
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__SPRI__18_EC (
	 V_0 = 1.0006592,
	 P_0 = 27.2,
	 Q_0 = 9.8,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -11.729641
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__TAZE__41_EC (
	 V_0 = 0.9962168,
	 P_0 = 6.3,
	 Q_0 = 3.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -14.07668
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__TAZE__43_EC (
	 V_0 = 1.0095638,
	 P_0 = 2.0,
	 Q_0 = 1.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -11.354389
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__TURN___2_EC (
	 V_0 = 1.01,
	 P_0 = 3.0,
	 Q_0 = 88.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -1.1881632
	 ) annotation (Placement(transformation()));

// TAP CHANGER TRANSFORMERS
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__BUS___10_BUS___51_1_PT (
	 r = 1.0752677,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.07119995870707947
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__BUS___13_BUS___49_1_PT (
	 r = 1.1173185,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.19099997296745394
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__BUS___14_BUS___46_1_PT (
	 r = 1.1111112,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.07350010342068142
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__BUS___15_BUS___45_1_PT (
	 r = 1.0471205,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.10419995107768494
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__BUS___21_BUS___20_1_PT (
	 r = 0.9587728,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.7767005454119951
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__BUS___24_BUS___25_1_PT (
	 r = 1.0,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 1.181999899791487
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__BUS___24_BUS___25_2_PT (
	 r = 1.0,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 1.229999863184496
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__BUS___24_BUS___26_1_PT (
	 r = 0.9587728,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.047299996310256
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__BUS___34_BUS___32_1_PT (
	 r = 1.025641,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.9529998874816571
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__BUS___39_BUS___57_1_PT (
	 r = 1.0204082,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 1.3549999815174274
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__BUS___40_BUS___56_1_PT (
	 r = 1.0438414,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 1.1949998364611842
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__BUS____7_BUS___29_1_PT (
	 r = 1.0341262,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.0648000447261023
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__SALT___9_SALT__55_1_PT (
	 r = 1.0638298,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.12050011174935388
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__SPRI___4_SPRI__18_1_PT (
	 r = 1.0309278,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.554998975802688
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__SPRI___4_SPRI__18_2_PT (
	 r = 1.0224949,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.430000019265783
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__TAZE__11_TAZE__41_1_PT (
	 r = 1.0471205,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.7490002834329603
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__TAZE__11_TAZE__43_1_PT (
	 r = 1.0438414,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.1530000035010122
	 ) annotation (Placement(transformation()));

// LINES
  iPSL.Electrical.Branches.PwLine_2 line__BEAV___6_BUS____7_1_AC (
	 R = 0.02,
	 X = 0.10199999,
	 G = 0.0,
	 B = 0.013799997
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BEAV___6_CLIN___8_1_AC (
	 R = 0.033900023,
	 X = 0.17300001,
	 G = 0.0,
	 B = 0.02350001
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___10_GLEN__12_1_AC (
	 R = 0.027700063,
	 X = 0.12619996,
	 G = 0.0,
	 B = 0.016400002
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___13_BUS___14_1_AC (
	 R = 0.0132,
	 X = 0.04339992,
	 G = 0.0,
	 B = 0.0055000028
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___13_BUS___15_1_AC (
	 R = 0.02690002,
	 X = 0.08690002,
	 G = 0.0,
	 B = 0.011500006
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___14_BUS___15_1_AC (
	 R = 0.0171,
	 X = 0.05470006,
	 G = 0.0,
	 B = 0.0073999986
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___19_BUS___20_1_AC (
	 R = 0.2829999,
	 X = 0.43400022,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___21_BUS___22_1_AC (
	 R = 0.0736,
	 X = 0.117,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___22_BUS___23_1_AC (
	 R = 0.0099,
	 X = 0.015199999,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___22_BUS___38_1_AC (
	 R = 0.0192,
	 X = 0.029499998,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___23_BUS___24_1_AC (
	 R = 0.16600001,
	 X = 0.256,
	 G = 0.0,
	 B = 0.0041999957
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___25_BUS___30_1_AC (
	 R = 0.135,
	 X = 0.20200011,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___26_BUS___27_1_AC (
	 R = 0.165,
	 X = 0.25400022,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___27_BUS___28_1_AC (
	 R = 0.061800044,
	 X = 0.095400125,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___28_BUS___29_1_AC (
	 R = 0.041799992,
	 X = 0.05869985,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___29_BUS___52_1_AC (
	 R = 0.14419976,
	 X = 0.18700011,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___30_BUS___31_1_AC (
	 R = 0.3259998,
	 X = 0.4970001,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___31_BUS___32_1_AC (
	 R = 0.5070001,
	 X = 0.7549989,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___32_BUS___33_1_AC (
	 R = 0.03920001,
	 X = 0.036000002,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___34_BUS___35_1_AC (
	 R = 0.051999997,
	 X = 0.078,
	 G = 0.0,
	 B = 0.0015999995
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___35_BUS___36_1_AC (
	 R = 0.043,
	 X = 0.0537,
	 G = 0.0,
	 B = 7.9999975E-4
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___36_BUS___37_1_AC (
	 R = 0.029000001,
	 X = 0.0366,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___36_BUS___40_1_AC (
	 R = 0.03,
	 X = 0.046600003,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___37_BUS___38_1_AC (
	 R = 0.0651,
	 X = 0.1009,
	 G = 0.0,
	 B = 0.001
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___37_BUS___39_1_AC (
	 R = 0.0239,
	 X = 0.0379,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___38_BUS___44_1_AC (
	 R = 0.0289,
	 X = 0.0585,
	 G = 0.0,
	 B = 0.001
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___38_BUS___48_1_AC (
	 R = 0.031200001,
	 X = 0.0482,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___38_BUS___49_1_AC (
	 R = 0.114999995,
	 X = 0.177,
	 G = 0.0,
	 B = 0.0015000001
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___44_BUS___45_1_AC (
	 R = 0.062400002,
	 X = 0.1242,
	 G = 0.0,
	 B = 0.0020000036
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___46_BUS___47_1_AC (
	 R = 0.023,
	 X = 0.068,
	 G = 0.0,
	 B = 0.0015999995
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___47_BUS___48_1_AC (
	 R = 0.0182,
	 X = 0.023300001,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___48_BUS___49_1_AC (
	 R = 0.083399996,
	 X = 0.129,
	 G = 0.0,
	 B = 0.0023999976
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___49_BUS___50_1_AC (
	 R = 0.08009999,
	 X = 0.128,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___50_BUS___51_1_AC (
	 R = 0.13859999,
	 X = 0.22000001,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___52_BUS___53_1_AC (
	 R = 0.07619985,
	 X = 0.09840002,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___53_BUS___54_1_AC (
	 R = 0.18779983,
	 X = 0.23200011,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___54_SALT__55_1_AC (
	 R = 0.17319997,
	 X = 0.22650021,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___56_BUS___42_1_AC (
	 R = 0.21249999,
	 X = 0.3540002,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___56_TAZE__41_1_AC (
	 R = 0.55299836,
	 X = 0.5490023,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___57_BUS___56_1_AC (
	 R = 0.17400022,
	 X = 0.26000002,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS____5_BEAV___6_1_AC (
	 R = 0.030199956,
	 X = 0.064099975,
	 G = 0.0,
	 B = 0.006199988
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS____7_CLIN___8_1_AC (
	 R = 0.0139,
	 X = 0.07119996,
	 G = 0.0,
	 B = 0.00969999
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__CLIN___8_SALT___9_1_AC (
	 R = 0.0099,
	 X = 0.050499894,
	 G = 0.0,
	 B = 0.027400034
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__GLEN__12_BUS___13_1_AC (
	 R = 0.0178,
	 X = 0.058,
	 G = 0.0,
	 B = 0.030199977
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__GLEN__12_BUS___16_1_AC (
	 R = 0.018000001,
	 X = 0.08129994,
	 G = 0.0,
	 B = 0.010799996
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__GLEN__12_BUS___17_1_AC (
	 R = 0.039700065,
	 X = 0.179,
	 G = 0.0,
	 B = 0.023800002
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__KANA___1_BUS___15_1_AC (
	 R = 0.0178,
	 X = 0.091,
	 G = 0.0,
	 B = 0.049399897
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__KANA___1_BUS___16_1_AC (
	 R = 0.045399915,
	 X = 0.206,
	 G = 0.0,
	 B = 0.02730005
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__KANA___1_BUS___17_1_AC (
	 R = 0.02380004,
	 X = 0.108,
	 G = 0.0,
	 B = 0.014299997
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__KANA___1_TURN___2_1_AC (
	 R = 0.0083,
	 X = 0.028,
	 G = 0.0,
	 B = 0.064499885
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__LOGA___3_BUS___15_1_AC (
	 R = 0.0162,
	 X = 0.053,
	 G = 0.0,
	 B = 0.02720007
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__LOGA___3_SPRI___4_1_AC (
	 R = 0.0112,
	 X = 0.036600083,
	 G = 0.0,
	 B = 0.019000009
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SALT___9_BUS___10_1_AC (
	 R = 0.03690002,
	 X = 0.16790003,
	 G = 0.0,
	 B = 0.022000011
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SALT___9_BUS___13_1_AC (
	 R = 0.04809998,
	 X = 0.15799999,
	 G = 0.0,
	 B = 0.0203
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SALT___9_GLEN__12_1_AC (
	 R = 0.06480004,
	 X = 0.29499894,
	 G = 0.0,
	 B = 0.038600046
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SALT___9_TAZE__11_1_AC (
	 R = 0.025800042,
	 X = 0.08480004,
	 G = 0.0,
	 B = 0.0109
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SPRI__18_BUS___19_1_AC (
	 R = 0.46099982,
	 X = 0.6849979,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SPRI___4_BEAV___6_1_AC (
	 R = 0.042999998,
	 X = 0.148,
	 G = 0.0,
	 B = 0.017400002
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SPRI___4_BUS____5_1_AC (
	 R = 0.062500104,
	 X = 0.132,
	 G = 0.0,
	 B = 0.012900001
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__TAZE__11_BUS___13_1_AC (
	 R = 0.022299936,
	 X = 0.07319996,
	 G = 0.0,
	 B = 0.0094
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__TAZE__41_BUS___42_1_AC (
	 R = 0.2070001,
	 X = 0.35200012,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__TAZE__41_TAZE__43_1_AC (
	 R = 0.0,
	 X = 0.41200012,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__TURN___2_LOGA___3_1_AC (
	 R = 0.02980004,
	 X = 0.085,
	 G = 0.0,
	 B = 0.040900085
	 ) annotation (Placement(transformation()));

// CAPACITORS
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__BUS___25_SC (
	 B = 0.0590000274576146,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__BUS___53_SC (
	 B = 0.06300003052159577,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__SPRI__18_SC (
	 B = 0.10000004822175698,
	 nsteps = 1
	 ) annotation (Placement(transformation()));

// FIXED INJECTIONS

// GENERATORS
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__BEAV___6_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9687908704778265, 
	 ui0 = -0.14779812807626758, 
	 transformerIncluded = true, 
	 V2 = 69.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = -0.1513949441481888, 
	 init_omega = 1.0, 
	 init_efd = 0.3221131386385286, 
	 WLMDVPu = 0.717262019773382, 
	 init_lambdaad = -0.980253308734645, 
	 init_cm = 2.500801680561139e-09, 
	 init_lambdaq1 = 5.011770288573731e-09, 
	 init_lambdaq2 = 5.011770288573731e-09, 
	 init_iq = 2.567041743344219e-08, 
	 init_id = 0.008892928398310759, 
	 init_lambdaaq = 5.011770288573731e-09, 
	 init_lambdad = -0.980253308734645, 
	 init_lambdaf = -1.078518867908249,
	 PNALT = 1008.0,
	 IENR = 4,
	 DIn = 0.0,
	 HIn = 5.4,
	 TSD0 = 0.058,
	 XPD = 0.407,
	 RTfoPu = 0.0,
	 TPD0 = 9.651,
	 XTfoPu = 0.1,
	 rStatIn = 0.00357,
	 U1N = 24.0,
	 md = 0.084,
	 XPQ = 0.454,
	 TSQ0 = 0.06,
	 SN = 1120.0,
	 V1 = 24.0,
	 TPQ0 = 1.009,
	 mq = 0.084,
	 XSD = 0.3,
	 snd = 5.57,
	 XD = 2.57,
	 U2N = 69.0,
	 SNtfo = 1120.0,
	 lStatIn = 0.219,
	 XSQ = 0.301,
	 snq = 5.57,
	 XQ = 2.57,
	 PN = 1008.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__CLIN___8_SM (
	 SNREF = SNREF, 
	 ur0 = 1.001932228925315, 
	 ui0 = -0.07846527293219246, 
	 transformerIncluded = true, 
	 V2 = 69.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.4896756278096736, 
	 init_omega = 1.0, 
	 init_efd = 0.5144098793605182, 
	 WLMDVPu = 0.6296504227510634, 
	 init_lambdaad = -0.9211241315554678, 
	 init_cm = 0.4132768018223802, 
	 init_lambdaq1 = 0.4553167019128942, 
	 init_lambdaq2 = 0.4553167019128942, 
	 init_iq = 3.442627829547225, 
	 init_id = 2.929015264541527, 
	 init_lambdaaq = 0.4553167019128942, 
	 init_lambdad = -0.9211241315554678, 
	 init_lambdaf = -1.104511197131554,
	 PNALT = 1090.0,
	 IENR = 4,
	 DIn = 0.0,
	 HIn = 5.4,
	 TSD0 = 0.08,
	 XPD = 0.384,
	 RTfoPu = 0.0,
	 TPD0 = 8.094,
	 XTfoPu = 0.1,
	 rStatIn = 0.002796,
	 U1N = 24.0,
	 md = 0.215,
	 XPQ = 0.393,
	 TSQ0 = 0.084,
	 SN = 1211.0,
	 V1 = 24.0,
	 TPQ0 = 1.572,
	 mq = 0.215,
	 XSD = 0.264,
	 snd = 6.995,
	 XD = 2.22,
	 U2N = 69.0,
	 SNtfo = 1211.0,
	 lStatIn = 0.202,
	 XSQ = 0.262,
	 snq = 6.995,
	 XQ = 2.22,
	 PN = 1090.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GLEN__12_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9980966537794731, 
	 ui0 = -0.18446756535961684, 
	 transformerIncluded = true, 
	 V2 = 69.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.187949795571106, 
	 init_omega = 1.0, 
	 init_efd = 0.3930917460136051, 
	 WLMDVPu = 0.7304917626978259, 
	 init_lambdaad = -0.9953402770485482, 
	 init_cm = 0.2015655688875682, 
	 init_lambdaq1 = 0.3171830588209664, 
	 init_lambdaq2 = 0.3171830588209664, 
	 init_iq = 2.387610255062257, 
	 init_id = 2.287667743413821, 
	 init_lambdaaq = 0.3171830588209664, 
	 init_lambdad = -0.9953402770485482, 
	 init_lambdaf = -1.161820825865159,
	 PNALT = 1539.0,
	 IENR = 4,
	 DIn = 0.0,
	 HIn = 5.112,
	 TSD0 = 0.065,
	 XPD = 0.527,
	 RTfoPu = 0.0,
	 TPD0 = 10.041,
	 XTfoPu = 0.1,
	 rStatIn = 0.003275,
	 U1N = 20.0,
	 md = 0.05,
	 XPQ = 0.623,
	 TSQ0 = 0.094,
	 SN = 1710.0,
	 V1 = 20.0,
	 TPQ0 = 1.22,
	 mq = 0.05,
	 XSD = 0.367,
	 snd = 9.285,
	 XD = 2.91,
	 U2N = 69.0,
	 SNtfo = 1710.0,
	 lStatIn = 0.265,
	 XSQ = 0.391,
	 snq = 9.285,
	 XQ = 2.72,
	 PN = 1539.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__KANA___1_SM (
	 SNREF = SNREF, 
	 ur0 = 1.040000081062317, 
	 ui0 = 0.0, 
	 transformerIncluded = true, 
	 V2 = 69.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.5163874912750059, 
	 init_omega = 1.0, 
	 init_efd = 0.438135425962496, 
	 WLMDVPu = 0.7304917626978259, 
	 init_lambdaad = -0.976545082774525, 
	 init_cm = 0.3113053295074774, 
	 init_lambdaq1 = 0.4417588338889414, 
	 init_lambdaq2 = 0.4417588338889414, 
	 init_iq = 3.390688552284602, 
	 init_id = 3.349856697011082, 
	 init_lambdaaq = 0.4417588338889414, 
	 init_lambdad = -0.976545082774525, 
	 init_lambdaf = -1.162102339852397,
	 PNALT = 1539.0,
	 IENR = 4,
	 DIn = 0.0,
	 HIn = 5.112,
	 TSD0 = 0.065,
	 XPD = 0.527,
	 RTfoPu = 0.0,
	 TPD0 = 10.041,
	 XTfoPu = 0.1,
	 rStatIn = 0.003275,
	 U1N = 20.0,
	 md = 0.05,
	 XPQ = 0.623,
	 TSQ0 = 0.094,
	 SN = 1710.0,
	 V1 = 20.0,
	 TPQ0 = 1.22,
	 mq = 0.05,
	 XSD = 0.367,
	 snd = 9.285,
	 XD = 2.91,
	 U2N = 69.0,
	 SNtfo = 1710.0,
	 lStatIn = 0.265,
	 XSQ = 0.391,
	 snq = 9.285,
	 XQ = 2.72,
	 PN = 1539.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__LOGA___3_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9796253378752589, 
	 ui0 = -0.10275752114912451, 
	 transformerIncluded = true, 
	 V2 = 69.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = -0.03666374451876937, 
	 init_omega = 1.0, 
	 init_efd = 0.3670662984465843, 
	 WLMDVPu = 0.6296504227510634, 
	 init_lambdaad = -0.983285273739676, 
	 init_cm = 0.03670074261957467, 
	 init_lambdaq1 = 0.05666464780586502, 
	 init_lambdaq2 = 0.05666464780586502, 
	 init_iq = 0.4057800044663102, 
	 init_id = 0.01836403867162738, 
	 init_lambdaaq = 0.05666464780586502, 
	 init_lambdad = -0.983285273739676, 
	 init_lambdaf = -1.114144368879289,
	 PNALT = 1090.0,
	 IENR = 4,
	 DIn = 0.0,
	 HIn = 5.4,
	 TSD0 = 0.08,
	 XPD = 0.384,
	 RTfoPu = 0.0,
	 TPD0 = 8.094,
	 XTfoPu = 0.1,
	 rStatIn = 0.002796,
	 U1N = 24.0,
	 md = 0.215,
	 XPQ = 0.393,
	 TSQ0 = 0.084,
	 SN = 1211.0,
	 V1 = 24.0,
	 TPQ0 = 1.572,
	 mq = 0.215,
	 XSD = 0.264,
	 snd = 6.995,
	 XD = 2.22,
	 U2N = 69.0,
	 SNtfo = 1211.0,
	 lStatIn = 0.202,
	 XSQ = 0.262,
	 snq = 6.995,
	 XQ = 2.22,
	 PN = 1090.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__SALT___9_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9663198322817156, 
	 ui0 = -0.16317481154599422, 
	 transformerIncluded = true, 
	 V2 = 69.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = -0.167288572664636, 
	 init_omega = 1.0, 
	 init_efd = 0.2914527576710677, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -0.9805038092705392, 
	 init_cm = 7.031435297629252e-09, 
	 init_lambdaq1 = 1.455099944663627e-08, 
	 init_lambdaq2 = 1.455099944663627e-08, 
	 init_iq = 1.061465033378242e-07, 
	 init_id = 0.02334982607591935, 
	 init_lambdaaq = 1.455099944663627e-08, 
	 init_lambdad = -0.9805038092705392, 
	 init_lambdaf = -1.100418235096613,
	 PNALT = 1485.0,
	 IENR = 4,
	 DIn = 0.0,
	 HIn = 5.625,
	 TSD0 = 0.065,
	 XPD = 0.509,
	 RTfoPu = 0.0,
	 TPD0 = 10.041,
	 XTfoPu = 0.1,
	 rStatIn = 0.00316,
	 U1N = 20.0,
	 md = 0.05,
	 XPQ = 0.601,
	 TSQ0 = 0.094,
	 SN = 1650.0,
	 V1 = 20.0,
	 TPQ0 = 1.22,
	 mq = 0.05,
	 XSD = 0.354,
	 snd = 9.285,
	 XD = 2.81,
	 U2N = 69.0,
	 SNtfo = 1650.0,
	 lStatIn = 0.256,
	 XSQ = 0.377,
	 snq = 9.285,
	 XQ = 2.62,
	 PN = 1485.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__TURN___2_SM (
	 SNREF = SNREF, 
	 ur0 = 1.0097828290220543, 
	 ui0 = -0.020943231555695743, 
	 transformerIncluded = true, 
	 V2 = 69.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = -0.02073594041718238, 
	 init_omega = 1.0, 
	 init_efd = 0.302550211164383, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -1.009838730473549, 
	 init_cm = 7.204400342246272e-10, 
	 init_lambdaq1 = 1.434175131730817e-09, 
	 init_lambdaq2 = 1.434175131730817e-09, 
	 init_iq = 1.060491481926656e-08, 
	 init_id = -0.00747412878343688, 
	 init_lambdaaq = 1.434175131730817e-09, 
	 init_lambdad = -1.009838730473549, 
	 init_lambdaf = -1.13431905841843,
	 PNALT = 1485.0,
	 IENR = 4,
	 DIn = 0.0,
	 HIn = 5.625,
	 TSD0 = 0.065,
	 XPD = 0.509,
	 RTfoPu = 0.0,
	 TPD0 = 10.041,
	 XTfoPu = 0.1,
	 rStatIn = 0.00316,
	 U1N = 20.0,
	 md = 0.05,
	 XPQ = 0.601,
	 TSQ0 = 0.094,
	 SN = 1650.0,
	 V1 = 20.0,
	 TPQ0 = 1.22,
	 mq = 0.05,
	 XSD = 0.354,
	 snd = 9.285,
	 XD = 2.81,
	 U2N = 69.0,
	 SNtfo = 1650.0,
	 lStatIn = 0.256,
	 XSQ = 0.377,
	 snq = 9.285,
	 XQ = 2.62,
	 PN = 1485.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));

// REGULATORS
  pssi3e2b reg_pssi3e2b__BEAV___6_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_APREF = 0.0,
	 T4 = 0.015,
	 T6 = 0.,
	 VSTMIN = -0.1,
	 T7 = 2.,
	 T8 = 0.,
	 T9 = 0.,
	 KS1 = 10.,
	 VSI2MAX = 999.,
	 VSI1MAX = 999.,
	 KS3 = 1.,
	 KS2 = 0.1564,
	 TW2 = 2.,
	 TW1 = 2.,
	 TW3 = 2.,
	 T10 = 0.,
	 VSTMAX = 0.1,
	 T11 = 0.,
	 VSI2MIN = -999.,
	 VSI1MIN = -999.,
	 T1 = 0.25,
	 T2 = 0.03,
	 T3 = .1500000
	 ) annotation (Placement(transformation()));
  sexs reg_sexs__BEAV___6_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_EFD = 0.3221131386385286,
	 init_VREF = 0.9816899859130929,
	 init_YLL = 0.001610565693192643,
	 TE = 0.05,
	 EFDMIN = -999.,
	 KC = 1.,
	 EFDMAX = 999.,
	 K = 200.,
	 EMIN = 0.,
	 TA = 3.,
	 TB = 10.,
	 EMAX = 4.
	 ) annotation (Placement(transformation()));
  gsteam0 reg_gsteam0__BEAV___6_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_REF = 1.25040084028057e-10,
	 init_PMECH = 2.500801680561139e-09,
	 init_CM = 2.500801680561139e-09,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__CLIN___8_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_APREF = 0.3715937241948803,
	 T4 = 0.015,
	 T6 = 0.,
	 VSTMIN = -0.1,
	 T7 = 2.,
	 T8 = 0.,
	 T9 = 0.,
	 KS1 = 10.,
	 VSI2MAX = 999.,
	 VSI1MAX = 999.,
	 KS3 = 1.,
	 KS2 = 0.1564,
	 TW2 = 2.,
	 TW1 = 2.,
	 TW3 = 2.,
	 T10 = 0.,
	 VSTMAX = 0.1,
	 T11 = 0.,
	 VSI2MIN = -999.,
	 VSI1MIN = -999.,
	 T1 = 0.25,
	 T2 = 0.03,
	 T3 = .1500000
	 ) annotation (Placement(transformation()));
  sexs reg_sexs__CLIN___8_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_EFD = 0.5144098793605182,
	 init_VREF = 1.013350983257707,
	 init_YLL = 0.002572049396802591,
	 TE = 0.05,
	 EFDMIN = -999.,
	 KC = 1.,
	 EFDMAX = 999.,
	 K = 200.,
	 EMIN = 0.,
	 TA = 3.,
	 TB = 10.,
	 EMAX = 4.
	 ) annotation (Placement(transformation()));
  gsteam0 reg_gsteam0__CLIN___8_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_REF = 0.02066384009111901,
	 init_PMECH = 0.4132768018223802,
	 init_CM = 0.4132768018223802,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GLEN__12_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_APREF = 0.1812865497076024,
	 T4 = 0.015,
	 T6 = 0.,
	 VSTMIN = -0.1,
	 T7 = 2.,
	 T8 = 0.,
	 T9 = 0.,
	 KS1 = 10.,
	 VSI2MAX = 999.,
	 VSI1MAX = 999.,
	 KS3 = 1.,
	 KS2 = 0.1564,
	 TW2 = 2.,
	 TW1 = 2.,
	 TW3 = 2.,
	 T10 = 0.,
	 VSTMAX = 0.1,
	 T11 = 0.,
	 VSI2MIN = -999.,
	 VSI1MIN = -999.,
	 T1 = 0.25,
	 T2 = 0.03,
	 T3 = .1500000
	 ) annotation (Placement(transformation()));
  sexs reg_sexs__GLEN__12_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_EFD = 0.3930917460136051,
	 init_VREF = 1.024532668829836,
	 init_YLL = 0.001965458730068026,
	 TE = 0.05,
	 EFDMIN = -999.,
	 KC = 1.,
	 EFDMAX = 999.,
	 K = 200.,
	 EMIN = 0.,
	 TA = 3.,
	 TB = 10.,
	 EMAX = 4.
	 ) annotation (Placement(transformation()));
  gsteam0 reg_gsteam0__GLEN__12_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_REF = 0.01007827844437841,
	 init_PMECH = 0.2015655688875682,
	 init_CM = 0.2015655688875682,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__KANA___1_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_APREF = 0.279920350877193,
	 T4 = 0.015,
	 T6 = 0.,
	 VSTMIN = -0.1,
	 T7 = 2.,
	 T8 = 0.,
	 T9 = 0.,
	 KS1 = 10.,
	 VSI2MAX = 999.,
	 VSI1MAX = 999.,
	 KS3 = 1.,
	 KS2 = 0.1564,
	 TW2 = 2.,
	 TW1 = 2.,
	 TW3 = 2.,
	 T10 = 0.,
	 VSTMAX = 0.1,
	 T11 = 0.,
	 VSI2MIN = -999.,
	 VSI1MIN = -999.,
	 T1 = 0.25,
	 T2 = 0.03,
	 T3 = .1500000
	 ) annotation (Placement(transformation()));
  sexs reg_sexs__KANA___1_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_EFD = 0.438135425962496,
	 init_VREF = 1.049781836163326,
	 init_YLL = 0.00219067712981248,
	 TE = 0.05,
	 EFDMIN = -999.,
	 KC = 1.,
	 EFDMAX = 999.,
	 K = 200.,
	 EMIN = 0.,
	 TA = 3.,
	 TB = 10.,
	 EMAX = 4.
	 ) annotation (Placement(transformation()));
  gsteam0 reg_gsteam0__KANA___1_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_REF = 0.01556526647537387,
	 init_PMECH = 0.3113053295074774,
	 init_CM = 0.3113053295074774,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__LOGA___3_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_APREF = 0.03303055326176715,
	 T4 = 0.015,
	 T6 = 0.,
	 VSTMIN = -0.1,
	 T7 = 2.,
	 T8 = 0.,
	 T9 = 0.,
	 KS1 = 10.,
	 VSI2MAX = 999.,
	 VSI1MAX = 999.,
	 KS3 = 1.,
	 KS2 = 0.1564,
	 TW2 = 2.,
	 TW1 = 2.,
	 TW3 = 2.,
	 T10 = 0.,
	 VSTMAX = 0.1,
	 T11 = 0.,
	 VSI2MIN = -999.,
	 VSI1MIN = -999.,
	 T1 = 0.25,
	 T2 = 0.03,
	 T3 = .1500000
	 ) annotation (Placement(transformation()));
  sexs reg_sexs__LOGA___3_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_EFD = 0.3670662984465843,
	 init_VREF = 0.9867651171798655,
	 init_YLL = 0.001835331492232921,
	 TE = 0.05,
	 EFDMIN = -999.,
	 KC = 1.,
	 EFDMAX = 999.,
	 K = 200.,
	 EMIN = 0.,
	 TA = 3.,
	 TB = 10.,
	 EMAX = 4.
	 ) annotation (Placement(transformation()));
  gsteam0 reg_gsteam0__LOGA___3_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_REF = 0.001835037130978734,
	 init_PMECH = 0.03670074261957467,
	 init_CM = 0.03670074261957467,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__SALT___9_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_APREF = -2.628368902995162e-20,
	 T4 = 0.015,
	 T6 = 0.,
	 VSTMIN = -0.1,
	 T7 = 2.,
	 T8 = 0.,
	 T9 = 0.,
	 KS1 = 10.,
	 VSI2MAX = 999.,
	 VSI1MAX = 999.,
	 KS3 = 1.,
	 KS2 = 0.1564,
	 TW2 = 2.,
	 TW1 = 2.,
	 TW3 = 2.,
	 T10 = 0.,
	 VSTMAX = 0.1,
	 T11 = 0.,
	 VSI2MIN = -999.,
	 VSI1MIN = -999.,
	 T1 = 0.25,
	 T2 = 0.03,
	 T3 = .1500000
	 ) annotation (Placement(transformation()));
  sexs reg_sexs__SALT___9_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_EFD = 0.2914527576710677,
	 init_VREF = 0.9815987969592729,
	 init_YLL = 0.001457263788355338,
	 TE = 0.05,
	 EFDMIN = -999.,
	 KC = 1.,
	 EFDMAX = 999.,
	 K = 200.,
	 EMIN = 0.,
	 TA = 3.,
	 TB = 10.,
	 EMAX = 4.
	 ) annotation (Placement(transformation()));
  gsteam0 reg_gsteam0__SALT___9_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_REF = 3.515717648814627e-10,
	 init_PMECH = 7.031435297629252e-09,
	 init_CM = 7.031435297629252e-09,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__TURN___2_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_APREF = 0.0,
	 T4 = 0.015,
	 T6 = 0.,
	 VSTMIN = -0.1,
	 T7 = 2.,
	 T8 = 0.,
	 T9 = 0.,
	 KS1 = 10.,
	 VSI2MAX = 999.,
	 VSI1MAX = 999.,
	 KS3 = 1.,
	 KS2 = 0.1564,
	 TW2 = 2.,
	 TW1 = 2.,
	 TW3 = 2.,
	 T10 = 0.,
	 VSTMAX = 0.1,
	 T11 = 0.,
	 VSI2MIN = -999.,
	 VSI1MIN = -999.,
	 T1 = 0.25,
	 T2 = 0.03,
	 T3 = .1500000
	 ) annotation (Placement(transformation()));
  sexs reg_sexs__TURN___2_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_EFD = 0.302550211164383,
	 init_VREF = 1.011467443768876,
	 init_YLL = 0.001512751055821915,
	 TE = 0.05,
	 EFDMIN = -999.,
	 KC = 1.,
	 EFDMAX = 999.,
	 K = 200.,
	 EMIN = 0.,
	 TA = 3.,
	 TB = 10.,
	 EMAX = 4.
	 ) annotation (Placement(transformation()));
  gsteam0 reg_gsteam0__TURN___2_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_REF = 3.602200171123136e-11,
	 init_PMECH = 7.204400342246272e-10,
	 init_CM = 7.204400342246272e-10,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
iPSL.Electrical.Events.PwFault pwFault(R=0.5,X=0.5,t1=1.,t2=1.2);
equation
  omegaRef = (gen_pwGeneratorM2S__BEAV___6_SM.omega*gen_pwGeneratorM2S__BEAV___6_SM.SN*gen_pwGeneratorM2S__BEAV___6_SM.HIn + gen_pwGeneratorM2S__CLIN___8_SM.omega*gen_pwGeneratorM2S__CLIN___8_SM.SN*gen_pwGeneratorM2S__CLIN___8_SM.HIn + gen_pwGeneratorM2S__GLEN__12_SM.omega*gen_pwGeneratorM2S__GLEN__12_SM.SN*gen_pwGeneratorM2S__GLEN__12_SM.HIn + gen_pwGeneratorM2S__KANA___1_SM.omega*gen_pwGeneratorM2S__KANA___1_SM.SN*gen_pwGeneratorM2S__KANA___1_SM.HIn + gen_pwGeneratorM2S__LOGA___3_SM.omega*gen_pwGeneratorM2S__LOGA___3_SM.SN*gen_pwGeneratorM2S__LOGA___3_SM.HIn + gen_pwGeneratorM2S__SALT___9_SM.omega*gen_pwGeneratorM2S__SALT___9_SM.SN*gen_pwGeneratorM2S__SALT___9_SM.HIn + gen_pwGeneratorM2S__TURN___2_SM.omega*gen_pwGeneratorM2S__TURN___2_SM.SN*gen_pwGeneratorM2S__TURN___2_SM.HIn) / (gen_pwGeneratorM2S__BEAV___6_SM.SN*gen_pwGeneratorM2S__BEAV___6_SM.HIn + gen_pwGeneratorM2S__CLIN___8_SM.SN*gen_pwGeneratorM2S__CLIN___8_SM.HIn + gen_pwGeneratorM2S__GLEN__12_SM.SN*gen_pwGeneratorM2S__GLEN__12_SM.HIn + gen_pwGeneratorM2S__KANA___1_SM.SN*gen_pwGeneratorM2S__KANA___1_SM.HIn + gen_pwGeneratorM2S__LOGA___3_SM.SN*gen_pwGeneratorM2S__LOGA___3_SM.HIn + gen_pwGeneratorM2S__SALT___9_SM.SN*gen_pwGeneratorM2S__SALT___9_SM.HIn + gen_pwGeneratorM2S__TURN___2_SM.SN*gen_pwGeneratorM2S__TURN___2_SM.HIn);

  connect(gen_pwGeneratorM2S__BEAV___6_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__CLIN___8_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GLEN__12_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__KANA___1_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__LOGA___3_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__SALT___9_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__TURN___2_SM.omegaRef, omegaRef);

// Connecting REGULATORS and MACHINES
  connect(reg_pssi3e2b__BEAV___6_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__BEAV___6_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__BEAV___6_SM.pin_OMEGA, gen_pwGeneratorM2S__BEAV___6_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__BEAV___6_SM.pin_EFD, gen_pwGeneratorM2S__BEAV___6_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__BEAV___6_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__BEAV___6_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__BEAV___6_SM.pin_CM, gen_pwGeneratorM2S__BEAV___6_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__BEAV___6_SM.pin_OMEGA, gen_pwGeneratorM2S__BEAV___6_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__CLIN___8_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__CLIN___8_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__CLIN___8_SM.pin_OMEGA, gen_pwGeneratorM2S__CLIN___8_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__CLIN___8_SM.pin_EFD, gen_pwGeneratorM2S__CLIN___8_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__CLIN___8_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__CLIN___8_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__CLIN___8_SM.pin_CM, gen_pwGeneratorM2S__CLIN___8_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__CLIN___8_SM.pin_OMEGA, gen_pwGeneratorM2S__CLIN___8_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GLEN__12_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GLEN__12_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GLEN__12_SM.pin_OMEGA, gen_pwGeneratorM2S__GLEN__12_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GLEN__12_SM.pin_EFD, gen_pwGeneratorM2S__GLEN__12_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GLEN__12_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GLEN__12_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GLEN__12_SM.pin_CM, gen_pwGeneratorM2S__GLEN__12_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GLEN__12_SM.pin_OMEGA, gen_pwGeneratorM2S__GLEN__12_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__KANA___1_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__KANA___1_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__KANA___1_SM.pin_OMEGA, gen_pwGeneratorM2S__KANA___1_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__KANA___1_SM.pin_EFD, gen_pwGeneratorM2S__KANA___1_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__KANA___1_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__KANA___1_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__KANA___1_SM.pin_CM, gen_pwGeneratorM2S__KANA___1_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__KANA___1_SM.pin_OMEGA, gen_pwGeneratorM2S__KANA___1_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__LOGA___3_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__LOGA___3_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__LOGA___3_SM.pin_OMEGA, gen_pwGeneratorM2S__LOGA___3_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__LOGA___3_SM.pin_EFD, gen_pwGeneratorM2S__LOGA___3_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__LOGA___3_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__LOGA___3_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__LOGA___3_SM.pin_CM, gen_pwGeneratorM2S__LOGA___3_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__LOGA___3_SM.pin_OMEGA, gen_pwGeneratorM2S__LOGA___3_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__SALT___9_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__SALT___9_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__SALT___9_SM.pin_OMEGA, gen_pwGeneratorM2S__SALT___9_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__SALT___9_SM.pin_EFD, gen_pwGeneratorM2S__SALT___9_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__SALT___9_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__SALT___9_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__SALT___9_SM.pin_CM, gen_pwGeneratorM2S__SALT___9_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__SALT___9_SM.pin_OMEGA, gen_pwGeneratorM2S__SALT___9_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__TURN___2_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__TURN___2_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__TURN___2_SM.pin_OMEGA, gen_pwGeneratorM2S__TURN___2_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__TURN___2_SM.pin_EFD, gen_pwGeneratorM2S__TURN___2_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__TURN___2_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__TURN___2_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__TURN___2_SM.pin_CM, gen_pwGeneratorM2S__TURN___2_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__TURN___2_SM.pin_OMEGA, gen_pwGeneratorM2S__TURN___2_SM.pin_OMEGA) annotation (Line());

// Connecting REGULATORS and REGULATORS
  connect(reg_pssi3e2b__BEAV___6_SM.pin_VS, reg_sexs__BEAV___6_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__BEAV___6_SM.pin_OMEGA, reg_gsteam0__BEAV___6_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__CLIN___8_SM.pin_VS, reg_sexs__CLIN___8_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__CLIN___8_SM.pin_OMEGA, reg_gsteam0__CLIN___8_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GLEN__12_SM.pin_VS, reg_sexs__GLEN__12_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GLEN__12_SM.pin_OMEGA, reg_gsteam0__GLEN__12_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__KANA___1_SM.pin_VS, reg_sexs__KANA___1_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__KANA___1_SM.pin_OMEGA, reg_gsteam0__KANA___1_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__LOGA___3_SM.pin_VS, reg_sexs__LOGA___3_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__LOGA___3_SM.pin_OMEGA, reg_gsteam0__LOGA___3_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__SALT___9_SM.pin_VS, reg_sexs__SALT___9_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__SALT___9_SM.pin_OMEGA, reg_gsteam0__SALT___9_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__TURN___2_SM.pin_VS, reg_sexs__TURN___2_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__TURN___2_SM.pin_OMEGA, reg_gsteam0__TURN___2_SM.pin_OMEGA) annotation (Line());

// Connecting LINES
  connect(bus__BEAV___6_TN.p, line__BEAV___6_BUS____7_1_AC.p) annotation (Line());
  connect(line__BEAV___6_BUS____7_1_AC.n, bus__BUS____7_TN.p) annotation (Line());
  connect(bus__BEAV___6_TN.p, line__BEAV___6_CLIN___8_1_AC.p) annotation (Line());
  connect(line__BEAV___6_CLIN___8_1_AC.n, bus__CLIN___8_TN.p) annotation (Line());
  connect(bus__BUS___10_TN.p, line__BUS___10_GLEN__12_1_AC.p) annotation (Line());
  connect(line__BUS___10_GLEN__12_1_AC.n, bus__GLEN__12_TN.p) annotation (Line());
  connect(bus__BUS___13_TN.p, line__BUS___13_BUS___14_1_AC.p) annotation (Line());
  connect(line__BUS___13_BUS___14_1_AC.n, bus__BUS___14_TN.p) annotation (Line());
  connect(bus__BUS___13_TN.p, line__BUS___13_BUS___15_1_AC.p) annotation (Line());
  connect(line__BUS___13_BUS___15_1_AC.n, bus__BUS___15_TN.p) annotation (Line());
  connect(bus__BUS___14_TN.p, line__BUS___14_BUS___15_1_AC.p) annotation (Line());
  connect(line__BUS___14_BUS___15_1_AC.n, bus__BUS___15_TN.p) annotation (Line());
  connect(bus__BUS___19_TN.p, line__BUS___19_BUS___20_1_AC.p) annotation (Line());
  connect(line__BUS___19_BUS___20_1_AC.n, bus__BUS___20_TN.p) annotation (Line());
  connect(bus__BUS___21_TN.p, line__BUS___21_BUS___22_1_AC.p) annotation (Line());
  connect(line__BUS___21_BUS___22_1_AC.n, bus__BUS___22_TN.p) annotation (Line());
  connect(bus__BUS___22_TN.p, line__BUS___22_BUS___23_1_AC.p) annotation (Line());
  connect(line__BUS___22_BUS___23_1_AC.n, bus__BUS___23_TN.p) annotation (Line());
  connect(bus__BUS___22_TN.p, line__BUS___22_BUS___38_1_AC.p) annotation (Line());
  connect(line__BUS___22_BUS___38_1_AC.n, bus__BUS___38_TN.p) annotation (Line());
  connect(bus__BUS___23_TN.p, line__BUS___23_BUS___24_1_AC.p) annotation (Line());
  connect(line__BUS___23_BUS___24_1_AC.n, bus__BUS___24_TN.p) annotation (Line());
  connect(bus__BUS___25_TN.p, line__BUS___25_BUS___30_1_AC.p) annotation (Line());
  connect(line__BUS___25_BUS___30_1_AC.n, bus__BUS___30_TN.p) annotation (Line());
  connect(bus__BUS___26_TN.p, line__BUS___26_BUS___27_1_AC.p) annotation (Line());
  connect(line__BUS___26_BUS___27_1_AC.n, bus__BUS___27_TN.p) annotation (Line());
  connect(bus__BUS___27_TN.p, line__BUS___27_BUS___28_1_AC.p) annotation (Line());
  connect(line__BUS___27_BUS___28_1_AC.n, bus__BUS___28_TN.p) annotation (Line());
  connect(bus__BUS___28_TN.p, line__BUS___28_BUS___29_1_AC.p) annotation (Line());
  connect(line__BUS___28_BUS___29_1_AC.n, bus__BUS___29_TN.p) annotation (Line());
  connect(bus__BUS___29_TN.p, line__BUS___29_BUS___52_1_AC.p) annotation (Line());
  connect(line__BUS___29_BUS___52_1_AC.n, bus__BUS___52_TN.p) annotation (Line());
  connect(bus__BUS___30_TN.p, line__BUS___30_BUS___31_1_AC.p) annotation (Line());
  connect(line__BUS___30_BUS___31_1_AC.n, bus__BUS___31_TN.p) annotation (Line());
  connect(bus__BUS___31_TN.p, line__BUS___31_BUS___32_1_AC.p) annotation (Line());
  connect(line__BUS___31_BUS___32_1_AC.n, bus__BUS___32_TN.p) annotation (Line());
  connect(bus__BUS___32_TN.p, line__BUS___32_BUS___33_1_AC.p) annotation (Line());
  connect(line__BUS___32_BUS___33_1_AC.n, bus__BUS___33_TN.p) annotation (Line());
  connect(bus__BUS___34_TN.p, line__BUS___34_BUS___35_1_AC.p) annotation (Line());
  connect(line__BUS___34_BUS___35_1_AC.n, bus__BUS___35_TN.p) annotation (Line());
  connect(bus__BUS___35_TN.p, line__BUS___35_BUS___36_1_AC.p) annotation (Line());
  connect(line__BUS___35_BUS___36_1_AC.n, bus__BUS___36_TN.p) annotation (Line());
  connect(bus__BUS___36_TN.p, line__BUS___36_BUS___37_1_AC.p) annotation (Line());
  connect(line__BUS___36_BUS___37_1_AC.n, bus__BUS___37_TN.p) annotation (Line());
  connect(bus__BUS___36_TN.p, line__BUS___36_BUS___40_1_AC.p) annotation (Line());
  connect(line__BUS___36_BUS___40_1_AC.n, bus__BUS___40_TN.p) annotation (Line());
  connect(bus__BUS___37_TN.p, line__BUS___37_BUS___38_1_AC.p) annotation (Line());
  connect(line__BUS___37_BUS___38_1_AC.n, bus__BUS___38_TN.p) annotation (Line());
  connect(bus__BUS___37_TN.p, line__BUS___37_BUS___39_1_AC.p) annotation (Line());
  connect(line__BUS___37_BUS___39_1_AC.n, bus__BUS___39_TN.p) annotation (Line());
  connect(bus__BUS___38_TN.p, line__BUS___38_BUS___44_1_AC.p) annotation (Line());
  connect(line__BUS___38_BUS___44_1_AC.n, bus__BUS___44_TN.p) annotation (Line());
  connect(bus__BUS___38_TN.p, line__BUS___38_BUS___48_1_AC.p) annotation (Line());
  connect(line__BUS___38_BUS___48_1_AC.n, bus__BUS___48_TN.p) annotation (Line());
  connect(bus__BUS___38_TN.p, line__BUS___38_BUS___49_1_AC.p) annotation (Line());
  connect(line__BUS___38_BUS___49_1_AC.n, bus__BUS___49_TN.p) annotation (Line());
  connect(bus__BUS___44_TN.p, line__BUS___44_BUS___45_1_AC.p) annotation (Line());
  connect(line__BUS___44_BUS___45_1_AC.n, bus__BUS___45_TN.p) annotation (Line());
  connect(bus__BUS___46_TN.p, line__BUS___46_BUS___47_1_AC.p) annotation (Line());
  connect(line__BUS___46_BUS___47_1_AC.n, bus__BUS___47_TN.p) annotation (Line());
  connect(bus__BUS___47_TN.p, line__BUS___47_BUS___48_1_AC.p) annotation (Line());
  connect(line__BUS___47_BUS___48_1_AC.n, bus__BUS___48_TN.p) annotation (Line());
  connect(bus__BUS___48_TN.p, line__BUS___48_BUS___49_1_AC.p) annotation (Line());
  connect(line__BUS___48_BUS___49_1_AC.n, bus__BUS___49_TN.p) annotation (Line());
  connect(bus__BUS___49_TN.p, line__BUS___49_BUS___50_1_AC.p) annotation (Line());
  connect(line__BUS___49_BUS___50_1_AC.n, bus__BUS___50_TN.p) annotation (Line());
  connect(bus__BUS___50_TN.p, line__BUS___50_BUS___51_1_AC.p) annotation (Line());
  connect(line__BUS___50_BUS___51_1_AC.n, bus__BUS___51_TN.p) annotation (Line());
  connect(bus__BUS___52_TN.p, line__BUS___52_BUS___53_1_AC.p) annotation (Line());
  connect(line__BUS___52_BUS___53_1_AC.n, bus__BUS___53_TN.p) annotation (Line());
  connect(bus__BUS___53_TN.p, line__BUS___53_BUS___54_1_AC.p) annotation (Line());
  connect(line__BUS___53_BUS___54_1_AC.n, bus__BUS___54_TN.p) annotation (Line());
  connect(bus__BUS___54_TN.p, line__BUS___54_SALT__55_1_AC.p) annotation (Line());
  connect(line__BUS___54_SALT__55_1_AC.n, bus__SALT__55_TN.p) annotation (Line());
  connect(bus__BUS___56_TN.p, line__BUS___56_BUS___42_1_AC.p) annotation (Line());
  connect(line__BUS___56_BUS___42_1_AC.n, bus__BUS___42_TN.p) annotation (Line());
  connect(bus__BUS___56_TN.p, line__BUS___56_TAZE__41_1_AC.p) annotation (Line());
  connect(line__BUS___56_TAZE__41_1_AC.n, bus__TAZE__41_TN.p) annotation (Line());
  connect(bus__BUS___57_TN.p, line__BUS___57_BUS___56_1_AC.p) annotation (Line());
  connect(line__BUS___57_BUS___56_1_AC.n, bus__BUS___56_TN.p) annotation (Line());
  connect(bus__BUS____5_TN.p, line__BUS____5_BEAV___6_1_AC.p) annotation (Line());
  connect(line__BUS____5_BEAV___6_1_AC.n, bus__BEAV___6_TN.p) annotation (Line());
  connect(bus__BUS____7_TN.p, line__BUS____7_CLIN___8_1_AC.p) annotation (Line());
  connect(line__BUS____7_CLIN___8_1_AC.n, bus__CLIN___8_TN.p) annotation (Line());
  connect(bus__CLIN___8_TN.p, line__CLIN___8_SALT___9_1_AC.p) annotation (Line());
  connect(line__CLIN___8_SALT___9_1_AC.n, bus__SALT___9_TN.p) annotation (Line());
  connect(bus__GLEN__12_TN.p, line__GLEN__12_BUS___13_1_AC.p) annotation (Line());
  connect(line__GLEN__12_BUS___13_1_AC.n, bus__BUS___13_TN.p) annotation (Line());
  connect(bus__GLEN__12_TN.p, line__GLEN__12_BUS___16_1_AC.p) annotation (Line());
  connect(line__GLEN__12_BUS___16_1_AC.n, bus__BUS___16_TN.p) annotation (Line());
  connect(bus__GLEN__12_TN.p, line__GLEN__12_BUS___17_1_AC.p) annotation (Line());
  connect(line__GLEN__12_BUS___17_1_AC.n, bus__BUS___17_TN.p) annotation (Line());
  connect(bus__KANA___1_TN.p, line__KANA___1_BUS___15_1_AC.p) annotation (Line());
  connect(line__KANA___1_BUS___15_1_AC.n, bus__BUS___15_TN.p) annotation (Line());
  connect(bus__KANA___1_TN.p, line__KANA___1_BUS___16_1_AC.p) annotation (Line());
  connect(line__KANA___1_BUS___16_1_AC.n, bus__BUS___16_TN.p) annotation (Line());
  connect(bus__KANA___1_TN.p, line__KANA___1_BUS___17_1_AC.p) annotation (Line());
  connect(line__KANA___1_BUS___17_1_AC.n, bus__BUS___17_TN.p) annotation (Line());
  connect(bus__KANA___1_TN.p, line__KANA___1_TURN___2_1_AC.p) annotation (Line());
  connect(line__KANA___1_TURN___2_1_AC.n, bus__TURN___2_TN.p) annotation (Line());
  connect(bus__LOGA___3_TN.p, line__LOGA___3_BUS___15_1_AC.p) annotation (Line());
  connect(line__LOGA___3_BUS___15_1_AC.n, bus__BUS___15_TN.p) annotation (Line());
  connect(bus__LOGA___3_TN.p, line__LOGA___3_SPRI___4_1_AC.p) annotation (Line());
  connect(line__LOGA___3_SPRI___4_1_AC.n, bus__SPRI___4_TN.p) annotation (Line());
  connect(bus__SALT___9_TN.p, line__SALT___9_BUS___10_1_AC.p) annotation (Line());
  connect(line__SALT___9_BUS___10_1_AC.n, bus__BUS___10_TN.p) annotation (Line());
  connect(bus__SALT___9_TN.p, line__SALT___9_BUS___13_1_AC.p) annotation (Line());
  connect(line__SALT___9_BUS___13_1_AC.n, bus__BUS___13_TN.p) annotation (Line());
  connect(bus__SALT___9_TN.p, line__SALT___9_GLEN__12_1_AC.p) annotation (Line());
  connect(line__SALT___9_GLEN__12_1_AC.n, bus__GLEN__12_TN.p) annotation (Line());
  connect(bus__SALT___9_TN.p, line__SALT___9_TAZE__11_1_AC.p) annotation (Line());
  connect(line__SALT___9_TAZE__11_1_AC.n, bus__TAZE__11_TN.p) annotation (Line());
  connect(bus__SPRI__18_TN.p, line__SPRI__18_BUS___19_1_AC.p) annotation (Line());
  connect(line__SPRI__18_BUS___19_1_AC.n, bus__BUS___19_TN.p) annotation (Line());
  connect(bus__SPRI___4_TN.p, line__SPRI___4_BEAV___6_1_AC.p) annotation (Line());
  connect(line__SPRI___4_BEAV___6_1_AC.n, bus__BEAV___6_TN.p) annotation (Line());
  connect(bus__SPRI___4_TN.p, line__SPRI___4_BUS____5_1_AC.p) annotation (Line());
  connect(line__SPRI___4_BUS____5_1_AC.n, bus__BUS____5_TN.p) annotation (Line());
  connect(bus__TAZE__11_TN.p, line__TAZE__11_BUS___13_1_AC.p) annotation (Line());
  connect(line__TAZE__11_BUS___13_1_AC.n, bus__BUS___13_TN.p) annotation (Line());
  connect(bus__TAZE__41_TN.p, line__TAZE__41_BUS___42_1_AC.p) annotation (Line());
  connect(line__TAZE__41_BUS___42_1_AC.n, bus__BUS___42_TN.p) annotation (Line());
  connect(bus__TAZE__41_TN.p, line__TAZE__41_TAZE__43_1_AC.p) annotation (Line());
  connect(line__TAZE__41_TAZE__43_1_AC.n, bus__TAZE__43_TN.p) annotation (Line());
  connect(bus__TURN___2_TN.p, line__TURN___2_LOGA___3_1_AC.p) annotation (Line());
  connect(line__TURN___2_LOGA___3_1_AC.n, bus__LOGA___3_TN.p) annotation (Line());

// COUPLING DEVICES

// Connecting LOADS
  connect(bus__BEAV___6_TN.p, load__BEAV___6_EC.p) annotation (Line());
  connect(bus__BUS___10_TN.p, load__BUS___10_EC.p) annotation (Line());
  connect(bus__BUS___13_TN.p, load__BUS___13_EC.p) annotation (Line());
  connect(bus__BUS___14_TN.p, load__BUS___14_EC.p) annotation (Line());
  connect(bus__BUS___15_TN.p, load__BUS___15_EC.p) annotation (Line());
  connect(bus__BUS___16_TN.p, load__BUS___16_EC.p) annotation (Line());
  connect(bus__BUS___17_TN.p, load__BUS___17_EC.p) annotation (Line());
  connect(bus__BUS___19_TN.p, load__BUS___19_EC.p) annotation (Line());
  connect(bus__BUS___20_TN.p, load__BUS___20_EC.p) annotation (Line());
  connect(bus__BUS___23_TN.p, load__BUS___23_EC.p) annotation (Line());
  connect(bus__BUS___25_TN.p, load__BUS___25_EC.p) annotation (Line());
  connect(bus__BUS___27_TN.p, load__BUS___27_EC.p) annotation (Line());
  connect(bus__BUS___28_TN.p, load__BUS___28_EC.p) annotation (Line());
  connect(bus__BUS___29_TN.p, load__BUS___29_EC.p) annotation (Line());
  connect(bus__BUS___30_TN.p, load__BUS___30_EC.p) annotation (Line());
  connect(bus__BUS___31_TN.p, load__BUS___31_EC.p) annotation (Line());
  connect(bus__BUS___32_TN.p, load__BUS___32_EC.p) annotation (Line());
  connect(bus__BUS___33_TN.p, load__BUS___33_EC.p) annotation (Line());
  connect(bus__BUS___35_TN.p, load__BUS___35_EC.p) annotation (Line());
  connect(bus__BUS___38_TN.p, load__BUS___38_EC.p) annotation (Line());
  connect(bus__BUS___42_TN.p, load__BUS___42_EC.p) annotation (Line());
  connect(bus__BUS___44_TN.p, load__BUS___44_EC.p) annotation (Line());
  connect(bus__BUS___47_TN.p, load__BUS___47_EC.p) annotation (Line());
  connect(bus__BUS___49_TN.p, load__BUS___49_EC.p) annotation (Line());
  connect(bus__BUS___50_TN.p, load__BUS___50_EC.p) annotation (Line());
  connect(bus__BUS___51_TN.p, load__BUS___51_EC.p) annotation (Line());
  connect(bus__BUS___52_TN.p, load__BUS___52_EC.p) annotation (Line());
  connect(bus__BUS___53_TN.p, load__BUS___53_EC.p) annotation (Line());
  connect(bus__BUS___54_TN.p, load__BUS___54_EC.p) annotation (Line());
  connect(bus__BUS___56_TN.p, load__BUS___56_EC.p) annotation (Line());
  connect(bus__BUS___57_TN.p, load__BUS___57_EC.p) annotation (Line());
  connect(bus__BUS____5_TN.p, load__BUS____5_EC.p) annotation (Line());
  connect(bus__CLIN___8_TN.p, load__CLIN___8_EC.p) annotation (Line());
  connect(bus__GLEN__12_TN.p, load__GLEN__12_EC.p) annotation (Line());
  connect(bus__KANA___1_TN.p, load__KANA___1_EC.p) annotation (Line());
  connect(bus__LOGA___3_TN.p, load__LOGA___3_EC.p) annotation (Line());
  connect(bus__SALT__55_TN.p, load__SALT__55_EC.p) annotation (Line());
  connect(bus__SALT___9_TN.p, load__SALT___9_EC.p) annotation (Line());
  connect(bus__SPRI__18_TN.p, load__SPRI__18_EC.p) annotation (Line());
  connect(bus__TAZE__41_TN.p, load__TAZE__41_EC.p) annotation (Line());
  connect(bus__TAZE__43_TN.p, load__TAZE__43_EC.p) annotation (Line());
  connect(bus__TURN___2_TN.p, load__TURN___2_EC.p) annotation (Line());

// Connecting Capacitors
  connect(bus__BUS___25_TN.p, cap_pwCapacitorBank__BUS___25_SC.p) annotation (Line());
  connect(bus__BUS___53_TN.p, cap_pwCapacitorBank__BUS___53_SC.p) annotation (Line());
  connect(bus__SPRI__18_TN.p, cap_pwCapacitorBank__SPRI__18_SC.p) annotation (Line());

// Connecting GENERATORS
  connect(bus__BEAV___6_TN.p, gen_pwGeneratorM2S__BEAV___6_SM.sortie) annotation (Line());
  connect(bus__CLIN___8_TN.p, gen_pwGeneratorM2S__CLIN___8_SM.sortie) annotation (Line());
  connect(bus__GLEN__12_TN.p, gen_pwGeneratorM2S__GLEN__12_SM.sortie) annotation (Line());
  connect(bus__KANA___1_TN.p, gen_pwGeneratorM2S__KANA___1_SM.sortie) annotation (Line());
  connect(bus__LOGA___3_TN.p, gen_pwGeneratorM2S__LOGA___3_SM.sortie) annotation (Line());
  connect(bus__SALT___9_TN.p, gen_pwGeneratorM2S__SALT___9_SM.sortie) annotation (Line());
  connect(bus__TURN___2_TN.p, gen_pwGeneratorM2S__TURN___2_SM.sortie) annotation (Line());

// Connecting DETAILED TRANSFORMERS
  connect(bus__BUS___10_TN.p, trafo__BUS___10_BUS___51_1_PT.p) annotation (Line());
  connect(trafo__BUS___10_BUS___51_1_PT.n, bus__BUS___51_TN.p) annotation (Line());
  connect(bus__BUS___13_TN.p, trafo__BUS___13_BUS___49_1_PT.p) annotation (Line());
  connect(trafo__BUS___13_BUS___49_1_PT.n, bus__BUS___49_TN.p) annotation (Line());
  connect(bus__BUS___14_TN.p, trafo__BUS___14_BUS___46_1_PT.p) annotation (Line());
  connect(trafo__BUS___14_BUS___46_1_PT.n, bus__BUS___46_TN.p) annotation (Line());
  connect(bus__BUS___15_TN.p, trafo__BUS___15_BUS___45_1_PT.p) annotation (Line());
  connect(trafo__BUS___15_BUS___45_1_PT.n, bus__BUS___45_TN.p) annotation (Line());
  connect(bus__BUS___21_TN.p, trafo__BUS___21_BUS___20_1_PT.p) annotation (Line());
  connect(trafo__BUS___21_BUS___20_1_PT.n, bus__BUS___20_TN.p) annotation (Line());
  connect(bus__BUS___24_TN.p, trafo__BUS___24_BUS___25_1_PT.p) annotation (Line());
  connect(trafo__BUS___24_BUS___25_1_PT.n, bus__BUS___25_TN.p) annotation (Line());
  connect(bus__BUS___24_TN.p, trafo__BUS___24_BUS___25_2_PT.p) annotation (Line());
  connect(trafo__BUS___24_BUS___25_2_PT.n, bus__BUS___25_TN.p) annotation (Line());
  connect(bus__BUS___24_TN.p, trafo__BUS___24_BUS___26_1_PT.p) annotation (Line());
  connect(trafo__BUS___24_BUS___26_1_PT.n, bus__BUS___26_TN.p) annotation (Line());
  connect(bus__BUS___34_TN.p, trafo__BUS___34_BUS___32_1_PT.p) annotation (Line());
  connect(trafo__BUS___34_BUS___32_1_PT.n, bus__BUS___32_TN.p) annotation (Line());
  connect(bus__BUS___39_TN.p, trafo__BUS___39_BUS___57_1_PT.p) annotation (Line());
  connect(trafo__BUS___39_BUS___57_1_PT.n, bus__BUS___57_TN.p) annotation (Line());
  connect(bus__BUS___40_TN.p, trafo__BUS___40_BUS___56_1_PT.p) annotation (Line());
  connect(trafo__BUS___40_BUS___56_1_PT.n, bus__BUS___56_TN.p) annotation (Line());
  connect(bus__BUS____7_TN.p, trafo__BUS____7_BUS___29_1_PT.p) annotation (Line());
  connect(trafo__BUS____7_BUS___29_1_PT.n, bus__BUS___29_TN.p) annotation (Line());
  connect(bus__SALT___9_TN.p, trafo__SALT___9_SALT__55_1_PT.p) annotation (Line());
  connect(trafo__SALT___9_SALT__55_1_PT.n, bus__SALT__55_TN.p) annotation (Line());
  connect(bus__SPRI___4_TN.p, trafo__SPRI___4_SPRI__18_1_PT.p) annotation (Line());
  connect(trafo__SPRI___4_SPRI__18_1_PT.n, bus__SPRI__18_TN.p) annotation (Line());
  connect(bus__SPRI___4_TN.p, trafo__SPRI___4_SPRI__18_2_PT.p) annotation (Line());
  connect(trafo__SPRI___4_SPRI__18_2_PT.n, bus__SPRI__18_TN.p) annotation (Line());
  connect(bus__TAZE__11_TN.p, trafo__TAZE__11_TAZE__41_1_PT.p) annotation (Line());
  connect(trafo__TAZE__11_TAZE__41_1_PT.n, bus__TAZE__41_TN.p) annotation (Line());
  connect(bus__TAZE__11_TN.p, trafo__TAZE__11_TAZE__43_1_PT.p) annotation (Line());
  connect(trafo__TAZE__11_TAZE__43_1_PT.n, bus__TAZE__43_TN.p) annotation (Line());

// Connecting OTHERS
connect(bus__TAZE__11_TN.p, pwFault.p);
end ieee57bus;

