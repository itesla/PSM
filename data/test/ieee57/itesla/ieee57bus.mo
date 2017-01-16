within ;
model ieee57bus
  parameter Real SNREF = 100.0;
  Modelica.Blocks.Interfaces.RealOutput omegaRef;


// BUSES
  iPSL.Electrical.Buses.Bus bus__BEAV___6_TN (
	 V_0 = 0.98,
	 angle_0 = -8.693763
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___10_TN (
	 V_0 = 0.9865109,
	 angle_0 = -11.455586
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___13_TN (
	 V_0 = 0.9785058,
	 angle_0 = -9.804242
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___14_TN (
	 V_0 = 0.9701344,
	 angle_0 = -9.3513975
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___15_TN (
	 V_0 = 0.9882648,
	 angle_0 = -7.1886787
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___16_TN (
	 V_0 = 1.0133654,
	 angle_0 = -8.862442
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___17_TN (
	 V_0 = 1.0174506,
	 angle_0 = -5.397715
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___19_TN (
	 V_0 = 0.97074735,
	 angle_0 = -13.246523
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___20_TN (
	 V_0 = 0.96523064,
	 angle_0 = -13.472799
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___21_TN (
	 V_0 = 1.0083237,
	 angle_0 = -12.9301405
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___22_TN (
	 V_0 = 1.0097879,
	 angle_0 = -12.877523
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___23_TN (
	 V_0 = 1.008431,
	 angle_0 = -12.942428
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___24_TN (
	 V_0 = 1.0002954,
	 angle_0 = -13.288797
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___25_TN (
	 V_0 = 0.98253226,
	 angle_0 = -18.19501
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___26_TN (
	 V_0 = 0.96281075,
	 angle_0 = -12.972139
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___27_TN (
	 V_0 = 0.98705727,
	 angle_0 = -11.518088
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___28_TN (
	 V_0 = 1.0027262,
	 angle_0 = -10.497748
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___29_TN (
	 V_0 = 1.016579,
	 angle_0 = -9.797416
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___30_TN (
	 V_0 = 0.9622156,
	 angle_0 = -18.738737
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___31_TN (
	 V_0 = 0.93437505,
	 angle_0 = -19.397327
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___32_TN (
	 V_0 = 0.94671154,
	 angle_0 = -18.50956
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___33_TN (
	 V_0 = 0.9444097,
	 angle_0 = -18.549496
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___34_TN (
	 V_0 = 0.9595767,
	 angle_0 = -14.157382
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___35_TN (
	 V_0 = 0.96640384,
	 angle_0 = -13.91304
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___36_TN (
	 V_0 = 0.97588605,
	 angle_0 = -13.639813
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___37_TN (
	 V_0 = 0.98487407,
	 angle_0 = -13.449605
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___38_TN (
	 V_0 = 1.0128006,
	 angle_0 = -12.739065
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___39_TN (
	 V_0 = 0.98274094,
	 angle_0 = -13.493594
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___40_TN (
	 V_0 = 0.97284454,
	 angle_0 = -13.663456
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___42_TN (
	 V_0 = 0.9636234,
	 angle_0 = -15.561301
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___44_TN (
	 V_0 = 1.015675,
	 angle_0 = -11.855596
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___45_TN (
	 V_0 = 1.0325425,
	 angle_0 = -9.247758
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___46_TN (
	 V_0 = 1.0599446,
	 angle_0 = -11.118097
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___47_TN (
	 V_0 = 1.0335755,
	 angle_0 = -12.517386
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___48_TN (
	 V_0 = 1.0277319,
	 angle_0 = -12.619442
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___49_TN (
	 V_0 = 1.0380026,
	 angle_0 = -12.969724
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___50_TN (
	 V_0 = 1.0246639,
	 angle_0 = -13.43365
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___51_TN (
	 V_0 = 1.0527878,
	 angle_0 = -12.536634
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___52_TN (
	 V_0 = 0.98553973,
	 angle_0 = -11.500712
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___53_TN (
	 V_0 = 0.9754529,
	 angle_0 = -12.24675
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___54_TN (
	 V_0 = 0.99888146,
	 angle_0 = -11.695811
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___56_TN (
	 V_0 = 0.96583176,
	 angle_0 = -16.101572
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___57_TN (
	 V_0 = 0.96272594,
	 angle_0 = -16.629856
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS____5_TN (
	 V_0 = 0.97651166,
	 angle_0 = -8.561587
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS____7_TN (
	 V_0 = 0.98371977,
	 angle_0 = -7.631977
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__CLIN___8_TN (
	 V_0 = 1.005,
	 angle_0 = -4.4957905
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__GLEN__12_TN (
	 V_0 = 1.0150001,
	 angle_0 = -10.476112
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__KANA___1_TN (
	 V_0 = 1.0400001,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__LOGA___3_TN (
	 V_0 = 0.98499995,
	 angle_0 = -5.988939
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SALT__55_TN (
	 V_0 = 1.0314634,
	 angle_0 = -10.782218
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SALT___9_TN (
	 V_0 = 0.98,
	 angle_0 = -9.590248
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SPRI__18_TN (
	 V_0 = 0.9998917,
	 angle_0 = -11.734555
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SPRI___4_TN (
	 V_0 = 0.98082316,
	 angle_0 = -7.3434114
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__TAZE__11_TN (
	 V_0 = 0.97383463,
	 angle_0 = -10.195794
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__TAZE__41_TN (
	 V_0 = 0.9930517,
	 angle_0 = -14.091535
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__TAZE__43_TN (
	 V_0 = 1.007061,
	 angle_0 = -11.360105
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__TURN___2_TN (
	 V_0 = 1.01,
	 angle_0 = -1.1883563
	 ) annotation (Placement(transformation()));

// LOADS
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BEAV___6_EC (
	 V_0 = 0.98,
	 P_0 = 75.0,
	 Q_0 = 2.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -8.693763
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___10_EC (
	 V_0 = 0.9865109,
	 P_0 = 5.0,
	 Q_0 = 2.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -11.455586
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___13_EC (
	 V_0 = 0.9785058,
	 P_0 = 18.0,
	 Q_0 = 2.3,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -9.804242
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___14_EC (
	 V_0 = 0.9701344,
	 P_0 = 10.5,
	 Q_0 = 5.3,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -9.3513975
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___15_EC (
	 V_0 = 0.9882648,
	 P_0 = 22.0,
	 Q_0 = 5.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -7.1886787
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___16_EC (
	 V_0 = 1.0133654,
	 P_0 = 43.0,
	 Q_0 = 3.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -8.862442
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___17_EC (
	 V_0 = 1.0174506,
	 P_0 = 42.0,
	 Q_0 = 8.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -5.397715
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___19_EC (
	 V_0 = 0.97074735,
	 P_0 = 3.3,
	 Q_0 = 0.6,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -13.246523
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___20_EC (
	 V_0 = 0.96523064,
	 P_0 = 2.3,
	 Q_0 = 1.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -13.472799
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___23_EC (
	 V_0 = 1.008431,
	 P_0 = 6.3,
	 Q_0 = 2.1,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -12.942428
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___25_EC (
	 V_0 = 0.98253226,
	 P_0 = 6.3,
	 Q_0 = 3.2,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -18.19501
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___27_EC (
	 V_0 = 0.98705727,
	 P_0 = 9.3,
	 Q_0 = 0.5,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -11.518088
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___28_EC (
	 V_0 = 1.0027262,
	 P_0 = 4.6,
	 Q_0 = 2.3,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -10.497748
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___29_EC (
	 V_0 = 1.016579,
	 P_0 = 17.0,
	 Q_0 = 2.6,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -9.797416
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___30_EC (
	 V_0 = 0.9622156,
	 P_0 = 3.6,
	 Q_0 = 1.8,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -18.738737
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___31_EC (
	 V_0 = 0.93437505,
	 P_0 = 5.8,
	 Q_0 = 2.9,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -19.397327
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___32_EC (
	 V_0 = 0.94671154,
	 P_0 = 1.6,
	 Q_0 = 0.8,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -18.50956
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___33_EC (
	 V_0 = 0.9444097,
	 P_0 = 3.8,
	 Q_0 = 1.9,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -18.549496
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___35_EC (
	 V_0 = 0.96640384,
	 P_0 = 6.0,
	 Q_0 = 3.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -13.91304
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___38_EC (
	 V_0 = 1.0128006,
	 P_0 = 14.0,
	 Q_0 = 7.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -12.739065
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___42_EC (
	 V_0 = 0.9636234,
	 P_0 = 7.1,
	 Q_0 = 4.4,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -15.561301
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___44_EC (
	 V_0 = 1.015675,
	 P_0 = 12.0,
	 Q_0 = 1.8,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -11.855596
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___47_EC (
	 V_0 = 1.0335755,
	 P_0 = 29.7,
	 Q_0 = 11.6,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -12.517386
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___49_EC (
	 V_0 = 1.0380026,
	 P_0 = 18.0,
	 Q_0 = 8.5,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -12.969724
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___50_EC (
	 V_0 = 1.0246639,
	 P_0 = 21.0,
	 Q_0 = 10.5,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -13.43365
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___51_EC (
	 V_0 = 1.0527878,
	 P_0 = 18.0,
	 Q_0 = 5.3,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -12.536634
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___52_EC (
	 V_0 = 0.98553973,
	 P_0 = 4.9,
	 Q_0 = 2.2,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -11.500712
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___53_EC (
	 V_0 = 0.9754529,
	 P_0 = 20.0,
	 Q_0 = 10.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -12.24675
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___54_EC (
	 V_0 = 0.99888146,
	 P_0 = 4.1,
	 Q_0 = 1.4,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -11.695811
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___56_EC (
	 V_0 = 0.96583176,
	 P_0 = 7.6,
	 Q_0 = 2.2,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -16.101572
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS___57_EC (
	 V_0 = 0.96272594,
	 P_0 = 6.7,
	 Q_0 = 2.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -16.629856
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BUS____5_EC (
	 V_0 = 0.97651166,
	 P_0 = 13.0,
	 Q_0 = 4.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -8.561587
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__CLIN___8_EC (
	 V_0 = 1.005,
	 P_0 = 150.0,
	 Q_0 = 22.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -4.4957905
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__GLEN__12_EC (
	 V_0 = 1.0150001,
	 P_0 = 377.0,
	 Q_0 = 24.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -10.476112
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
	 angle_0 = -5.988939
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__SALT__55_EC (
	 V_0 = 1.0314634,
	 P_0 = 6.8,
	 Q_0 = 3.4,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -10.782218
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__SALT___9_EC (
	 V_0 = 0.98,
	 P_0 = 121.0,
	 Q_0 = 26.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -9.590248
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__SPRI__18_EC (
	 V_0 = 0.9998917,
	 P_0 = 27.2,
	 Q_0 = 9.8,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -11.734555
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__TAZE__41_EC (
	 V_0 = 0.9930517,
	 P_0 = 6.3,
	 Q_0 = 3.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -14.091535
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__TAZE__43_EC (
	 V_0 = 1.007061,
	 P_0 = 2.0,
	 Q_0 = 1.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -11.360105
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__TURN___2_EC (
	 V_0 = 1.01,
	 P_0 = 3.0,
	 Q_0 = 88.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -1.1883563
	 ) annotation (Placement(transformation()));

// TAP CHANGER TRANSFORMERS
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__BUS___10_BUS___51_1_PT (
	 r = 1.0752689,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.07119995870707947
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__BUS___13_BUS___49_1_PT (
	 r = 1.1235955,
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
	 r = 1.0416667,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.10419995107768494
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__BUS___21_BUS___20_1_PT (
	 r = 0.9615385,
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
	 r = 0.9615385,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.047299996310256
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__BUS___34_BUS___32_1_PT (
	 r = 1.0204082,
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
	 r = 1.0416667,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 1.1949998364611842
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__BUS____7_BUS___29_1_PT (
	 r = 1.0416667,
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
	 r = 1.0204082,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.430000019265783
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__TAZE__11_TAZE__41_1_PT (
	 r = 1.0416667,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.7490002834329603
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__TAZE__11_TAZE__43_1_PT (
	 r = 1.0416667,
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
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___12_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9980808638758583, 
	 ui0 = -0.18455297916941468, 
	 transformerIncluded = true, 
	 V2 = 69.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.1876546846220507, 
	 init_omega = 1.0, 
	 init_efd = 0.3933131394420166, 
	 WLMDVPu = 0.7304917626978259, 
	 init_lambdaad = -0.9954958431268398, 
	 init_cm = 0.2015657109246752, 
	 init_lambdaq1 = 0.3170100384834133, 
	 init_lambdaq2 = 0.3170100384834133, 
	 init_iq = 2.386461938168805, 
	 init_id = 2.291357571445772, 
	 init_lambdaaq = 0.3170100384834133, 
	 init_lambdad = -0.9954958431268398, 
	 init_lambdaf = -1.162070155549124,
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
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN____1_SM (
	 SNREF = SNREF, 
	 ur0 = 1.040000081062317, 
	 ui0 = 0.0, 
	 transformerIncluded = true, 
	 V2 = 69.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.5165590129981826, 
	 init_omega = 1.0, 
	 init_efd = 0.4380237819237996, 
	 WLMDVPu = 0.7304917626978259, 
	 init_lambdaad = -0.9764253855808367, 
	 init_cm = 0.3113224608219401, 
	 init_lambdaq1 = 0.4418945871188635, 
	 init_lambdaq2 = 0.4418945871188635, 
	 init_iq = 3.391582301149885, 
	 init_id = 3.348367821310836, 
	 init_lambdaaq = 0.4418945871188635, 
	 init_lambdad = -0.9764253855808367, 
	 init_lambdaf = -1.161935359649023,
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
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN____2_SM (
	 SNREF = SNREF, 
	 ur0 = 1.009782758408562, 
	 ui0 = -0.02094663592500645, 
	 transformerIncluded = true, 
	 V2 = 69.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = -0.02073931311080029, 
	 init_omega = 1.0, 
	 init_efd = 0.3025505794878659, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -1.009838878720366, 
	 init_cm = 7.191160374893842e-10, 
	 init_lambdaq1 = 1.431537820736979e-09, 
	 init_lambdaq2 = 1.431537820736979e-09, 
	 init_iq = 1.058541421202863e-08, 
	 init_id = -0.007467257793273012, 
	 init_lambdaaq = 1.431537820736979e-09, 
	 init_lambdad = -1.009838878720366, 
	 init_lambdaf = -1.134319358207127,
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
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN____3_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9796238808339158, 
	 ui0 = -0.10277141071244383, 
	 transformerIncluded = true, 
	 V2 = 69.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = -0.03660485351629305, 
	 init_omega = 1.0, 
	 init_efd = 0.3666702110722196, 
	 WLMDVPu = 0.6296504227510634, 
	 init_lambdaad = -0.9831436363641016, 
	 init_cm = 0.03670074541980183, 
	 init_lambdaq1 = 0.05672586286474988, 
	 init_lambdaq2 = 0.05672586286474988, 
	 init_iq = 0.4061539571893519, 
	 init_id = 0.0128767839595918, 
	 init_lambdaaq = 0.05672586286474988, 
	 init_lambdad = -0.9831436363641016, 
	 init_lambdaf = -1.113861526398193,
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
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN____6_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9687401592151537, 
	 ui0 = -0.14813014989472023, 
	 transformerIncluded = true, 
	 V2 = 69.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = -0.1517391845975808, 
	 init_omega = 1.0, 
	 init_efd = 0.3224734489805003, 
	 WLMDVPu = 0.717262019773382, 
	 init_lambdaad = -0.9803865455860749, 
	 init_cm = 5.823752311549193e-09, 
	 init_lambdaq1 = 1.165814026140929e-08, 
	 init_lambdaq2 = 1.165814026140929e-08, 
	 init_iq = 5.971645862436897e-08, 
	 init_id = 0.01357083647043681, 
	 init_lambdaaq = 1.165814026140929e-08, 
	 init_lambdad = -0.9803865455860749, 
	 init_lambdaf = -1.078762022962269,
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
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN____8_SM (
	 SNREF = SNREF, 
	 ur0 = 1.0019077059973798, 
	 ui0 = -0.07877778290001027, 
	 transformerIncluded = true, 
	 V2 = 69.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.4888590376100229, 
	 init_omega = 1.0, 
	 init_efd = 0.5148347849774709, 
	 WLMDVPu = 0.6296504227510634, 
	 init_lambdaad = -0.9214911812440174, 
	 init_cm = 0.4132769747416124, 
	 init_lambdaq1 = 0.4549411083633015, 
	 init_lambdaq2 = 0.4549411083633015, 
	 init_iq = 3.440574573484474, 
	 init_id = 2.932818934752623, 
	 init_lambdaaq = 0.4549411083633015, 
	 init_lambdad = -0.9214911812440174, 
	 init_lambdaf = -1.105029725625947,
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
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN____9_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9663039378856879, 
	 ui0 = -0.16326891011654981, 
	 transformerIncluded = true, 
	 V2 = 69.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = -0.1673850081666511, 
	 init_omega = 1.0, 
	 init_efd = 0.2911961999780939, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -0.9803989257684679, 
	 init_cm = 4.408463139411852e-09, 
	 init_lambdaq1 = 9.130516247507322e-09, 
	 init_lambdaq2 = 9.130516247507322e-09, 
	 init_iq = 6.660234416842187e-08, 
	 init_id = 0.01848865270126467, 
	 init_lambdaaq = 9.130516247507322e-09, 
	 init_lambdad = -0.9803989257684679, 
	 init_lambdaf = -1.100207794286763,
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
  iPSL.Electrical.Controls.Eurostag.pssi3e2b reg_pssi3e2b__GEN___12_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_APREF = 0.1812865497076023,
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
  iPSL.Electrical.Controls.Eurostag.sexs reg_sexs__GEN___12_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_EFD = 0.3933131394420166,
	 init_VREF = 1.024560059342116,
	 init_YLL = 0.001966565697210083,
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
  iPSL.Electrical.Controls.Eurostag.gsteam0 reg_gsteam0__GEN___12_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_REF = 0.01007828554623376,
	 init_PMECH = 0.2015657109246752,
	 init_CM = 0.2015657109246752,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.pssi3e2b reg_pssi3e2b__GEN____1_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_APREF = 0.2799358128654971,
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
  iPSL.Electrical.Controls.Eurostag.sexs reg_sexs__GEN____1_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_EFD = 0.4380237819237996,
	 init_VREF = 1.049766552125989,
	 init_YLL = 0.002190118909618998,
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
  iPSL.Electrical.Controls.Eurostag.gsteam0 reg_gsteam0__GEN____1_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_REF = 0.01556612304109701,
	 init_PMECH = 0.3113224608219401,
	 init_CM = 0.3113224608219401,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.pssi3e2b reg_pssi3e2b__GEN____2_SM (
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
  iPSL.Electrical.Controls.Eurostag.sexs reg_sexs__GEN____2_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_EFD = 0.3025505794878659,
	 init_VREF = 1.011467487252858,
	 init_YLL = 0.001512752897439329,
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
  iPSL.Electrical.Controls.Eurostag.gsteam0 reg_gsteam0__GEN____2_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_REF = 3.595580187446921e-11,
	 init_PMECH = 7.191160374893842e-10,
	 init_CM = 7.191160374893842e-10,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.pssi3e2b reg_pssi3e2b__GEN____3_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_APREF = 0.03303055326176713,
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
  iPSL.Electrical.Controls.Eurostag.sexs reg_sexs__GEN____3_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_EFD = 0.3666702110722196,
	 init_VREF = 0.9867174751160251,
	 init_YLL = 0.001833351055361098,
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
  iPSL.Electrical.Controls.Eurostag.gsteam0 reg_gsteam0__GEN____3_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_REF = 0.001835037270990092,
	 init_PMECH = 0.03670074541980183,
	 init_CM = 0.03670074541980183,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.pssi3e2b reg_pssi3e2b__GEN____6_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_APREF = 3.872150616019659e-20,
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
  iPSL.Electrical.Controls.Eurostag.sexs reg_sexs__GEN____6_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_EFD = 0.3224734489805003,
	 init_VREF = 0.9817335545011617,
	 init_YLL = 0.001612367244902501,
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
  iPSL.Electrical.Controls.Eurostag.gsteam0 reg_gsteam0__GEN____6_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_REF = 2.911876155774597e-10,
	 init_PMECH = 5.823752311549193e-09,
	 init_CM = 5.823752311549193e-09,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.pssi3e2b reg_pssi3e2b__GEN____8_SM (
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
  iPSL.Electrical.Controls.Eurostag.sexs reg_sexs__GEN____8_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_EFD = 0.5148347849774709,
	 init_VREF = 1.013407331140911,
	 init_YLL = 0.002574173924887355,
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
  iPSL.Electrical.Controls.Eurostag.gsteam0 reg_gsteam0__GEN____8_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_REF = 0.02066384873708062,
	 init_PMECH = 0.4132769747416124,
	 init_CM = 0.4132769747416124,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.pssi3e2b reg_pssi3e2b__GEN____9_SM (
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
  iPSL.Electrical.Controls.Eurostag.sexs reg_sexs__GEN____9_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_EFD = 0.2911961999780939,
	 init_VREF = 0.9815680525139912,
	 init_YLL = 0.001455980999890469,
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
  iPSL.Electrical.Controls.Eurostag.gsteam0 reg_gsteam0__GEN____9_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_REF = 2.204231569705926e-10,
	 init_PMECH = 4.408463139411852e-09,
	 init_CM = 4.408463139411852e-09,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));

equation
  omegaRef = (gen_pwGeneratorM2S__GEN___12_SM.omega*gen_pwGeneratorM2S__GEN___12_SM.SN*gen_pwGeneratorM2S__GEN___12_SM.HIn + gen_pwGeneratorM2S__GEN____1_SM.omega*gen_pwGeneratorM2S__GEN____1_SM.SN*gen_pwGeneratorM2S__GEN____1_SM.HIn + gen_pwGeneratorM2S__GEN____2_SM.omega*gen_pwGeneratorM2S__GEN____2_SM.SN*gen_pwGeneratorM2S__GEN____2_SM.HIn + gen_pwGeneratorM2S__GEN____3_SM.omega*gen_pwGeneratorM2S__GEN____3_SM.SN*gen_pwGeneratorM2S__GEN____3_SM.HIn + gen_pwGeneratorM2S__GEN____6_SM.omega*gen_pwGeneratorM2S__GEN____6_SM.SN*gen_pwGeneratorM2S__GEN____6_SM.HIn + gen_pwGeneratorM2S__GEN____8_SM.omega*gen_pwGeneratorM2S__GEN____8_SM.SN*gen_pwGeneratorM2S__GEN____8_SM.HIn + gen_pwGeneratorM2S__GEN____9_SM.omega*gen_pwGeneratorM2S__GEN____9_SM.SN*gen_pwGeneratorM2S__GEN____9_SM.HIn) / (gen_pwGeneratorM2S__GEN___12_SM.SN*gen_pwGeneratorM2S__GEN___12_SM.HIn + gen_pwGeneratorM2S__GEN____1_SM.SN*gen_pwGeneratorM2S__GEN____1_SM.HIn + gen_pwGeneratorM2S__GEN____2_SM.SN*gen_pwGeneratorM2S__GEN____2_SM.HIn + gen_pwGeneratorM2S__GEN____3_SM.SN*gen_pwGeneratorM2S__GEN____3_SM.HIn + gen_pwGeneratorM2S__GEN____6_SM.SN*gen_pwGeneratorM2S__GEN____6_SM.HIn + gen_pwGeneratorM2S__GEN____8_SM.SN*gen_pwGeneratorM2S__GEN____8_SM.HIn + gen_pwGeneratorM2S__GEN____9_SM.SN*gen_pwGeneratorM2S__GEN____9_SM.HIn);

  connect(gen_pwGeneratorM2S__GEN___12_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN____1_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN____2_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN____3_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN____6_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN____8_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN____9_SM.omegaRef, omegaRef);

// Connecting REGULATORS and MACHINES
  connect(reg_pssi3e2b__GEN___12_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___12_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___12_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___12_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___12_SM.pin_EFD, gen_pwGeneratorM2S__GEN___12_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___12_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___12_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___12_SM.pin_CM, gen_pwGeneratorM2S__GEN___12_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___12_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___12_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____1_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN____1_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN____1_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____1_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN____1_SM.pin_EFD, gen_pwGeneratorM2S__GEN____1_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN____1_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN____1_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN____1_SM.pin_CM, gen_pwGeneratorM2S__GEN____1_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN____1_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____1_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____2_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN____2_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN____2_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____2_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN____2_SM.pin_EFD, gen_pwGeneratorM2S__GEN____2_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN____2_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN____2_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN____2_SM.pin_CM, gen_pwGeneratorM2S__GEN____2_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN____2_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____2_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____3_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN____3_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN____3_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____3_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN____3_SM.pin_EFD, gen_pwGeneratorM2S__GEN____3_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN____3_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN____3_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN____3_SM.pin_CM, gen_pwGeneratorM2S__GEN____3_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN____3_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____3_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____6_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN____6_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN____6_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____6_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN____6_SM.pin_EFD, gen_pwGeneratorM2S__GEN____6_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN____6_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN____6_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN____6_SM.pin_CM, gen_pwGeneratorM2S__GEN____6_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN____6_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____6_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____8_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN____8_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN____8_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____8_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN____8_SM.pin_EFD, gen_pwGeneratorM2S__GEN____8_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN____8_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN____8_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN____8_SM.pin_CM, gen_pwGeneratorM2S__GEN____8_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN____8_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____8_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____9_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN____9_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN____9_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____9_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN____9_SM.pin_EFD, gen_pwGeneratorM2S__GEN____9_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN____9_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN____9_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN____9_SM.pin_CM, gen_pwGeneratorM2S__GEN____9_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN____9_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____9_SM.pin_OMEGA) annotation (Line());

// Connecting REGULATORS and REGULATORS
  connect(reg_pssi3e2b__GEN___12_SM.pin_VS, reg_sexs__GEN___12_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___12_SM.pin_OMEGA, reg_gsteam0__GEN___12_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____1_SM.pin_VS, reg_sexs__GEN____1_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN____1_SM.pin_OMEGA, reg_gsteam0__GEN____1_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____2_SM.pin_VS, reg_sexs__GEN____2_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN____2_SM.pin_OMEGA, reg_gsteam0__GEN____2_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____3_SM.pin_VS, reg_sexs__GEN____3_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN____3_SM.pin_OMEGA, reg_gsteam0__GEN____3_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____6_SM.pin_VS, reg_sexs__GEN____6_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN____6_SM.pin_OMEGA, reg_gsteam0__GEN____6_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____8_SM.pin_VS, reg_sexs__GEN____8_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN____8_SM.pin_OMEGA, reg_gsteam0__GEN____8_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____9_SM.pin_VS, reg_sexs__GEN____9_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN____9_SM.pin_OMEGA, reg_gsteam0__GEN____9_SM.pin_OMEGA) annotation (Line());

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
  connect(bus__GLEN__12_TN.p, gen_pwGeneratorM2S__GEN___12_SM.sortie) annotation (Line());
  connect(bus__KANA___1_TN.p, gen_pwGeneratorM2S__GEN____1_SM.sortie) annotation (Line());
  connect(bus__TURN___2_TN.p, gen_pwGeneratorM2S__GEN____2_SM.sortie) annotation (Line());
  connect(bus__LOGA___3_TN.p, gen_pwGeneratorM2S__GEN____3_SM.sortie) annotation (Line());
  connect(bus__BEAV___6_TN.p, gen_pwGeneratorM2S__GEN____6_SM.sortie) annotation (Line());
  connect(bus__CLIN___8_TN.p, gen_pwGeneratorM2S__GEN____8_SM.sortie) annotation (Line());
  connect(bus__SALT___9_TN.p, gen_pwGeneratorM2S__GEN____9_SM.sortie) annotation (Line());

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
end ieee57bus;

