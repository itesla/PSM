within ;
model ieee30bus
  parameter Real SNREF = 100.0;
  Modelica.Blocks.Interfaces.RealOutput omegaRef;


// BUSES
  iPSL.Electrical.Buses.Bus bus__BLAI___7_TN (
	 V_0 = 1.0024583,
	 angle_0 = -12.867454
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___14_TN (
	 V_0 = 1.0409689,
	 angle_0 = -15.8278475
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___15_TN (
	 V_0 = 1.0368334,
	 angle_0 = -15.927121
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___16_TN (
	 V_0 = 1.0438741,
	 angle_0 = -15.531325
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___17_TN (
	 V_0 = 1.0405275,
	 angle_0 = -15.865252
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___18_TN (
	 V_0 = 1.0280039,
	 angle_0 = -16.5441
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___19_TN (
	 V_0 = 1.0259176,
	 angle_0 = -16.719124
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___20_TN (
	 V_0 = 1.0302206,
	 angle_0 = -16.523445
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___21_TN (
	 V_0 = 1.033987,
	 angle_0 = -16.147226
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___22_TN (
	 V_0 = 1.0345523,
	 angle_0 = -16.132868
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___23_TN (
	 V_0 = 1.0274578,
	 angle_0 = -16.32024
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___24_TN (
	 V_0 = 1.0233569,
	 angle_0 = -16.499044
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___25_TN (
	 V_0 = 1.0217563,
	 angle_0 = -16.081306
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___26_TN (
	 V_0 = 1.0041584,
	 angle_0 = -16.497278
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___29_TN (
	 V_0 = 1.0095617,
	 angle_0 = -16.778797
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___30_TN (
	 V_0 = 0.9981611,
	 angle_0 = -17.65078
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__CLAY___2_TN (
	 V_0 = 1.043,
	 angle_0 = -5.348747
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__CLOV__27_TN (
	 V_0 = 1.0292721,
	 angle_0 = -15.563498
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__CLOV__28_TN (
	 V_0 = 1.0065662,
	 angle_0 = -11.695438
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__FIEL___5_TN (
	 V_0 = 1.0100001,
	 angle_0 = -14.165995
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__GLEN___1_TN (
	 V_0 = 1.06,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__HANC__12_TN (
	 V_0 = 1.0553659,
	 angle_0 = -14.93606
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__HANC__13_TN (
	 V_0 = 1.071,
	 angle_0 = -14.93606
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__HANC___4_TN (
	 V_0 = 1.0126953,
	 angle_0 = -9.291033
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__KUMI___3_TN (
	 V_0 = 1.021495,
	 angle_0 = -7.5382876
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__REUS___8_TN (
	 V_0 = 1.0100001,
	 angle_0 = -11.816617
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__ROAN__10_TN (
	 V_0 = 1.0462639,
	 angle_0 = -15.706151
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__ROAN__11_TN (
	 V_0 = 1.082,
	 angle_0 = -14.117668
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__ROAN___6_TN (
	 V_0 = 1.0103933,
	 angle_0 = -11.068517
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__ROAN___9_TN (
	 V_0 = 1.0509585,
	 angle_0 = -14.117668
	 ) annotation (Placement(transformation()));

// LOADS
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__10_EC (
	 V_0 = 1.0462639,
	 P_0 = 5.8,
	 Q_0 = 2.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -15.706151
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__12_EC (
	 V_0 = 1.0553659,
	 P_0 = 11.2,
	 Q_0 = 7.5,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -14.93606
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__14_EC (
	 V_0 = 1.0409689,
	 P_0 = 6.2,
	 Q_0 = 1.6,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -15.8278475
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__15_EC (
	 V_0 = 1.0368334,
	 P_0 = 8.2,
	 Q_0 = 2.5,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -15.927121
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__16_EC (
	 V_0 = 1.0438741,
	 P_0 = 3.5,
	 Q_0 = 1.8,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -15.531325
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__17_EC (
	 V_0 = 1.0405275,
	 P_0 = 9.0,
	 Q_0 = 5.8,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -15.865252
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__18_EC (
	 V_0 = 1.0280039,
	 P_0 = 3.2,
	 Q_0 = 0.9,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -16.5441
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__19_EC (
	 V_0 = 1.0259176,
	 P_0 = 9.5,
	 Q_0 = 3.4,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -16.719124
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__20_EC (
	 V_0 = 1.0302206,
	 P_0 = 2.2,
	 Q_0 = 0.7,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -16.523445
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__21_EC (
	 V_0 = 1.033987,
	 P_0 = 17.5,
	 Q_0 = 11.2,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -16.147226
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__23_EC (
	 V_0 = 1.0274578,
	 P_0 = 3.2,
	 Q_0 = 1.6,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -16.32024
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__24_EC (
	 V_0 = 1.0233569,
	 P_0 = 8.7,
	 Q_0 = 6.7,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -16.499044
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__26_EC (
	 V_0 = 1.0041584,
	 P_0 = 3.5,
	 Q_0 = 2.3,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -16.497278
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__29_EC (
	 V_0 = 1.0095617,
	 P_0 = 2.4,
	 Q_0 = 0.9,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -16.778797
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__30_EC (
	 V_0 = 0.9981611,
	 P_0 = 10.6,
	 Q_0 = 1.9,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -17.65078
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD___2_EC (
	 V_0 = 1.043,
	 P_0 = 21.7,
	 Q_0 = 12.7,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -5.348747
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD___3_EC (
	 V_0 = 1.021495,
	 P_0 = 2.4,
	 Q_0 = 1.2,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -7.5382876
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD___4_EC (
	 V_0 = 1.0126953,
	 P_0 = 7.6,
	 Q_0 = 1.6,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -9.291033
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD___5_EC (
	 V_0 = 1.0100001,
	 P_0 = 94.2,
	 Q_0 = 19.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -14.165995
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD___7_EC (
	 V_0 = 1.0024583,
	 P_0 = 22.8,
	 Q_0 = 10.9,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -12.867454
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD___8_EC (
	 V_0 = 1.0100001,
	 P_0 = 30.0,
	 Q_0 = 30.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -11.816617
	 ) annotation (Placement(transformation()));

// TAP CHANGER TRANSFORMERS
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__CLOV__28_CLOV__27_1_PT (
	 r = 1.0416667,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.3959997648268911
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__HANC___4_HANC__12_1_PT (
	 r = 1.0638298,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.25599977267346763
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__ROAN___6_ROAN__10_1_PT (
	 r = 1.0416667,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.5559997838828628
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__ROAN___6_ROAN___9_1_PT (
	 r = 1.0204082,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.20799989586471965
	 ) annotation (Placement(transformation()));

// LINES
  iPSL.Electrical.Branches.PwLine_2 line__BUS___14_BUS___15_1_AC (
	 R = 0.22099999,
	 X = 0.19969973,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___15_BUS___18_1_AC (
	 R = 0.10730027,
	 X = 0.21850047,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___15_BUS___23_1_AC (
	 R = 0.099999994,
	 X = 0.20199999,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___16_BUS___17_1_AC (
	 R = 0.052399997,
	 X = 0.19230027,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___18_BUS___19_1_AC (
	 R = 0.063899994,
	 X = 0.12920019,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___19_BUS___20_1_AC (
	 R = 0.033999998,
	 X = 0.067999996,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___21_BUS___22_1_AC (
	 R = 0.0116,
	 X = 0.023599999,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___22_BUS___24_1_AC (
	 R = 0.114999995,
	 X = 0.17899999,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___23_BUS___24_1_AC (
	 R = 0.132,
	 X = 0.26999998,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___24_BUS___25_1_AC (
	 R = 0.18849953,
	 X = 0.32920018,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___25_BUS___26_1_AC (
	 R = 0.25440037,
	 X = 0.37999997,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___25_CLOV__27_1_AC (
	 R = 0.10930027,
	 X = 0.2086997,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BUS___29_BUS___30_1_AC (
	 R = 0.2398999,
	 X = 0.45330027,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__CLAY___2_FIEL___5_1_AC (
	 R = 0.04720001,
	 X = 0.19830003,
	 G = 0.0,
	 B = 0.020900002
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__CLAY___2_HANC___4_1_AC (
	 R = 0.056999996,
	 X = 0.17370006,
	 G = 0.0,
	 B = 0.018400006
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__CLAY___2_ROAN___6_1_AC (
	 R = 0.058099743,
	 X = 0.17629993,
	 G = 0.0,
	 B = 0.01869996
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__CLOV__27_BUS___29_1_AC (
	 R = 0.21979982,
	 X = 0.41530028,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__CLOV__27_BUS___30_1_AC (
	 R = 0.32020018,
	 X = 0.6026997,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__FIEL___5_BLAI___7_1_AC (
	 R = 0.046,
	 X = 0.115999766,
	 G = 0.0,
	 B = 0.01020001
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__GLEN___1_CLAY___2_1_AC (
	 R = 0.019200012,
	 X = 0.057499997,
	 G = 0.0,
	 B = 0.026399976
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__GLEN___1_KUMI___3_1_AC (
	 R = 0.04520001,
	 X = 0.16519973,
	 G = 0.0,
	 B = 0.02040002
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__HANC__12_BUS___14_1_AC (
	 R = 0.12310009,
	 X = 0.2558999,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__HANC__12_BUS___15_1_AC (
	 R = 0.066199996,
	 X = 0.13040036,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__HANC__12_BUS___16_1_AC (
	 R = 0.094499536,
	 X = 0.19869973,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__HANC__12_HANC__13_1_AC (
	 R = 0.0,
	 X = 0.14,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__HANC___4_ROAN___6_1_AC (
	 R = 0.011900024,
	 X = 0.041400023,
	 G = 0.0,
	 B = 0.0045000007
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__KUMI___3_HANC___4_1_AC (
	 R = 0.01320001,
	 X = 0.037900023,
	 G = 0.0,
	 B = 0.004200003
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__REUS___8_CLOV__28_1_AC (
	 R = 0.063600205,
	 X = 0.19999999,
	 G = 0.0,
	 B = 0.021399982
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__ROAN__10_BUS___17_1_AC (
	 R = 0.0324,
	 X = 0.0845,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__ROAN__10_BUS___20_1_AC (
	 R = 0.093599625,
	 X = 0.20899999,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__ROAN__10_BUS___21_1_AC (
	 R = 0.034799997,
	 X = 0.0749,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__ROAN__10_BUS___22_1_AC (
	 R = 0.072699994,
	 X = 0.14989991,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__ROAN___6_BLAI___7_1_AC (
	 R = 0.026700012,
	 X = 0.082000114,
	 G = 0.0,
	 B = 0.008500002
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__ROAN___6_CLOV__28_1_AC (
	 R = 0.016900022,
	 X = 0.05990014,
	 G = 0.0,
	 B = 0.0064999973
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__ROAN___6_REUS___8_1_AC (
	 R = 0.011999999,
	 X = 0.042,
	 G = 0.0,
	 B = 0.0045000007
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__ROAN___9_ROAN__10_1_AC (
	 R = 0.0,
	 X = 0.11,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__ROAN___9_ROAN__11_1_AC (
	 R = 0.0,
	 X = 0.20799999,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));

// CAPACITORS
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__BANK__10_SC (
	 B = 0.19000000443309545,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__BANK__24_SC (
	 B = 0.04300003628712148,
	 nsteps = 1
	 ) annotation (Placement(transformation()));

// FIXED INJECTIONS

// GENERATORS
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___11_SM (
	 SNREF = SNREF, 
	 ur0 = 1.0493202047820007, 
	 ui0 = -0.2639150336448885, 
	 transformerIncluded = false, 
	 Saturated = false, 
	 TX = 0, 
	 md = 0, 
	 XPQ = 0, 
	 TPQ0 = 0, 
	 mq = 0, 
	 snd = 0, 
	 snq = 0, 
	 init_theta = -0.2467239643695314, 
	 init_omega = 1.0, 
	 init_efd = 0.6142867676866529, 
	 WLMDVPu = 0.6941167416525881, 
	 init_lambdaad = -1.123116873214723, 
	 init_cm = 0.000209784661966909, 
	 init_lambdaq1 = 0.0001841375309146513, 
	 init_lambdaq2 = 0.0001841375309146513, 
	 init_iq = 4.837929766655924e-05, 
	 init_id = 0.1492385202897147, 
	 init_lambdaaq = 0.0001841375309146513, 
	 init_lambdad = -1.123116873214723, 
	 init_lambdaf = -1.24703810372081,
	 PNALT = 39.2,
	 IENR = 3,
	 DIn = 0.0,
	 HIn = 5.22,
	 TSD0 = 0.033,
	 XPD = 0.253,
	 TPD0 = 5.51,
	 rStatIn = 0.0018,
	 TSQ0 = 0.034,
	 SN = 49.0,
	 XSD = 0.171,
	 XD = 2.07,
	 lStatIn = 0.135,
	 XSQ = 0.198,
	 XQ = 2.0,
	 PN = 39.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___13_SM (
	 SNREF = SNREF, 
	 ur0 = 1.0348152380158917, 
	 ui0 = -0.27604054099341246, 
	 transformerIncluded = false, 
	 Saturated = false, 
	 TX = 0, 
	 md = 0, 
	 XPQ = 0, 
	 TPQ0 = 0, 
	 mq = 0, 
	 snd = 0, 
	 snq = 0, 
	 init_theta = -0.2614416199953252, 
	 init_omega = 1.0, 
	 init_efd = 0.7602561199617732, 
	 WLMDVPu = 0.6325921276173984, 
	 init_lambdaad = -1.14757501354403, 
	 init_cm = 0.0003340309729625336, 
	 init_lambdaq1 = 8.708863436090241e-05, 
	 init_lambdaq2 = 8.708863436090241e-05, 
	 init_iq = 8.466950562860051e-05, 
	 init_id = 0.1116713145276887, 
	 init_lambdaaq = 8.708863436090241e-05, 
	 init_lambdad = -1.14757501354403, 
	 init_lambdaf = -1.369819424940058,
	 PNALT = 32.0,
	 IENR = 3,
	 DIn = 0.0,
	 HIn = 3.657,
	 TSD0 = 0.15,
	 XPD = 0.38,
	 TPD0 = 9.4,
	 rStatIn = 0.003,
	 TSQ0 = 0.2,
	 SN = 35.0,
	 XSD = 0.29,
	 XD = 1.54,
	 lStatIn = 0.24,
	 XSQ = 0.4,
	 XQ = 0.6,
	 PN = 32.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN____1_SM (
	 SNREF = SNREF, 
	 ur0 = 1.059999942779541, 
	 ui0 = 0.0, 
	 transformerIncluded = true, 
	 V2 = 132.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.3448392943611234, 
	 init_omega = 1.0, 
	 init_efd = 0.4534060957079337, 
	 WLMDVPu = 0.6296504227510634, 
	 init_lambdaad = -1.015129595923705, 
	 init_cm = 0.2394987523803437, 
	 init_lambdaq1 = 0.2993627466859302, 
	 init_lambdaq2 = 0.2993627466859302, 
	 init_iq = 2.370778949826689, 
	 init_id = 0.6810751365722156, 
	 init_lambdaaq = 0.2993627466859302, 
	 init_lambdad = -1.015129595923705, 
	 init_lambdaf = -1.176768819318002,
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
	 U2N = 132.0,
	 SNtfo = 1211.0,
	 lStatIn = 0.202,
	 XSQ = 0.262,
	 snq = 6.995,
	 XQ = 2.22,
	 PN = 1090.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN____2_SM (
	 SNREF = SNREF, 
	 ur0 = 1.0384584998705986, 
	 ui0 = -0.09722606768733962, 
	 transformerIncluded = true, 
	 V2 = 132.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = -0.02110752011005718, 
	 init_omega = 1.0, 
	 init_efd = 0.3909768857038512, 
	 WLMDVPu = 0.717262019773382, 
	 init_lambdaad = -1.054796920759688, 
	 init_cm = 0.03969445772464726, 
	 init_lambdaq1 = 0.06553889799168311, 
	 init_lambdaq2 = 0.06553889799168311, 
	 init_iq = 0.3479052019530076, 
	 init_id = 0.5058186686682055, 
	 init_lambdaaq = 0.06553889799168311, 
	 init_lambdad = -1.054796920759688, 
	 init_lambdaf = -1.174070423417887,
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
	 U2N = 132.0,
	 SNtfo = 1120.0,
	 lStatIn = 0.219,
	 XSQ = 0.301,
	 snq = 5.57,
	 XQ = 2.57,
	 PN = 1008.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN____5_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9792867827146917, 
	 ui0 = -0.24717932506353346, 
	 transformerIncluded = true, 
	 V2 = 132.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = -0.2473087434929279, 
	 init_omega = 1.0, 
	 init_efd = 0.3225579763070924, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -1.01787222182618, 
	 init_cm = 1.716828281405212e-06, 
	 init_lambdaq1 = 3.217496260514704e-06, 
	 init_lambdaq2 = 3.217496260514704e-06, 
	 init_iq = 2.389392983528915e-05, 
	 init_id = 0.3648590190047269, 
	 init_lambdaaq = 3.217496260514704e-06, 
	 init_lambdad = -1.01787222182618, 
	 init_lambdaf = -1.150584483098172,
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
	 U2N = 132.0,
	 SNtfo = 1650.0,
	 lStatIn = 0.256,
	 XSQ = 0.377,
	 snq = 9.285,
	 XQ = 2.62,
	 PN = 1485.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN____8_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9885962255301741, 
	 ui0 = -0.2068277650753125, 
	 transformerIncluded = true, 
	 V2 = 132.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = -0.2063045215155117, 
	 init_omega = 1.0, 
	 init_efd = 0.3132787003479323, 
	 WLMDVPu = 0.7304917626978259, 
	 init_lambdaad = -1.017807200651786, 
	 init_cm = 1.664796979201526e-06, 
	 init_lambdaq1 = 3.240264167394047e-06, 
	 init_lambdaq2 = 3.240264167394047e-06, 
	 init_iq = 2.400855191253548e-05, 
	 init_id = 0.3657567515749268, 
	 init_lambdaaq = 3.240264167394047e-06, 
	 init_lambdad = -1.017807200651786, 
	 init_lambdaf = -1.150485666926865,
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
	 U2N = 132.0,
	 SNtfo = 1710.0,
	 lStatIn = 0.265,
	 XSQ = 0.391,
	 snq = 9.285,
	 XQ = 2.72,
	 PN = 1539.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));

// REGULATORS
  pssi3e2b reg_pssi3e2b__GEN___11_SM (
	 SNREF = 100.0,
	 SN = 49.0,
	 PN = 39.0,
	 PNALT = 39.2,
	 init_APREF = -1.416100796715761e-17,
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
  sexs reg_sexs__GEN___11_SM (
	 SNREF = 100.0,
	 SN = 49.0,
	 PN = 39.0,
	 PNALT = 39.2,
	 init_EFD = 0.6142867676866529,
	 init_VREF = 1.085071451004571,
	 init_YLL = 0.003071433838433265,
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
  gsteam0 reg_gsteam0__GEN___11_SM (
	 SNREF = 100.0,
	 SN = 49.0,
	 PN = 39.0,
	 PNALT = 39.2,
	 init_REF = 1.048923309834545e-05,
	 init_PMECH = 0.000209784661966909,
	 init_CM = 0.000209784661966909,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___13_SM (
	 SNREF = 100.0,
	 SN = 35.0,
	 PN = 32.0,
	 PNALT = 32.0,
	 init_APREF = 1.982541115402065e-17,
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
  sexs reg_sexs__GEN___13_SM (
	 SNREF = 100.0,
	 SN = 35.0,
	 PN = 32.0,
	 PNALT = 32.0,
	 init_EFD = 0.7602561199617732,
	 init_VREF = 1.074801260572648,
	 init_YLL = 0.003801280599808866,
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
  gsteam0 reg_gsteam0__GEN___13_SM (
	 SNREF = 100.0,
	 SN = 35.0,
	 PN = 32.0,
	 PNALT = 32.0,
	 init_REF = 1.670154864812668e-05,
	 init_PMECH = 0.0003340309729625336,
	 init_CM = 0.0003340309729625336,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN____1_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_APREF = 0.2154526507018993,
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
  sexs reg_sexs__GEN____1_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_EFD = 0.4534060957079337,
	 init_VREF = 1.061137128644549,
	 init_YLL = 0.002267030478539669,
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
  gsteam0 reg_gsteam0__GEN____1_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_REF = 0.01197493761901719,
	 init_PMECH = 0.2394987523803437,
	 init_CM = 0.2394987523803437,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN____2_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_APREF = 0.03571428571428572,
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
  sexs reg_sexs__GEN____2_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_EFD = 0.3909768857038512,
	 init_VREF = 1.049240700767265,
	 init_YLL = 0.001954884428519256,
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
  gsteam0 reg_gsteam0__GEN____2_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_REF = 0.001984722886232363,
	 init_PMECH = 0.03969445772464726,
	 init_CM = 0.03969445772464726,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN____5_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_APREF = 8.410780489584519e-19,
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
  sexs reg_sexs__GEN____5_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_EFD = 0.3225579763070924,
	 init_VREF = 1.01382416634067,
	 init_YLL = 0.001612789881535462,
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
  gsteam0 reg_gsteam0__GEN____5_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_REF = 8.584141407026062e-08,
	 init_PMECH = 1.716828281405212e-06,
	 init_CM = 1.716828281405212e-06,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN____8_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_APREF = 1.623133076937363e-18,
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
  sexs reg_sexs__GEN____8_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_EFD = 0.3132787003479323,
	 init_VREF = 1.013705431550677,
	 init_YLL = 0.001566393501739662,
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
  gsteam0 reg_gsteam0__GEN____8_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_REF = 8.323984896007631e-08,
	 init_PMECH = 1.664796979201526e-06,
	 init_CM = 1.664796979201526e-06,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));

equation
  omegaRef = (gen_pwGeneratorM2S__GEN___11_SM.omega*gen_pwGeneratorM2S__GEN___11_SM.SN*gen_pwGeneratorM2S__GEN___11_SM.HIn + gen_pwGeneratorM2S__GEN___13_SM.omega*gen_pwGeneratorM2S__GEN___13_SM.SN*gen_pwGeneratorM2S__GEN___13_SM.HIn + gen_pwGeneratorM2S__GEN____1_SM.omega*gen_pwGeneratorM2S__GEN____1_SM.SN*gen_pwGeneratorM2S__GEN____1_SM.HIn + gen_pwGeneratorM2S__GEN____2_SM.omega*gen_pwGeneratorM2S__GEN____2_SM.SN*gen_pwGeneratorM2S__GEN____2_SM.HIn + gen_pwGeneratorM2S__GEN____5_SM.omega*gen_pwGeneratorM2S__GEN____5_SM.SN*gen_pwGeneratorM2S__GEN____5_SM.HIn + gen_pwGeneratorM2S__GEN____8_SM.omega*gen_pwGeneratorM2S__GEN____8_SM.SN*gen_pwGeneratorM2S__GEN____8_SM.HIn) / (gen_pwGeneratorM2S__GEN___11_SM.SN*gen_pwGeneratorM2S__GEN___11_SM.HIn + gen_pwGeneratorM2S__GEN___13_SM.SN*gen_pwGeneratorM2S__GEN___13_SM.HIn + gen_pwGeneratorM2S__GEN____1_SM.SN*gen_pwGeneratorM2S__GEN____1_SM.HIn + gen_pwGeneratorM2S__GEN____2_SM.SN*gen_pwGeneratorM2S__GEN____2_SM.HIn + gen_pwGeneratorM2S__GEN____5_SM.SN*gen_pwGeneratorM2S__GEN____5_SM.HIn + gen_pwGeneratorM2S__GEN____8_SM.SN*gen_pwGeneratorM2S__GEN____8_SM.HIn);

  connect(gen_pwGeneratorM2S__GEN___11_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___13_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN____1_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN____2_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN____5_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN____8_SM.omegaRef, omegaRef);

// Connecting REGULATORS and MACHINES
  connect(reg_pssi3e2b__GEN___11_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___11_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___11_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___11_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___11_SM.pin_EFD, gen_pwGeneratorM2S__GEN___11_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___11_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___11_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___11_SM.pin_CM, gen_pwGeneratorM2S__GEN___11_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___11_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___11_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___13_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___13_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___13_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___13_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___13_SM.pin_EFD, gen_pwGeneratorM2S__GEN___13_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___13_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___13_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___13_SM.pin_CM, gen_pwGeneratorM2S__GEN___13_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___13_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___13_SM.pin_OMEGA) annotation (Line());
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
  connect(reg_pssi3e2b__GEN____5_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN____5_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN____5_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____5_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN____5_SM.pin_EFD, gen_pwGeneratorM2S__GEN____5_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN____5_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN____5_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN____5_SM.pin_CM, gen_pwGeneratorM2S__GEN____5_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN____5_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____5_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____8_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN____8_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN____8_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____8_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN____8_SM.pin_EFD, gen_pwGeneratorM2S__GEN____8_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN____8_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN____8_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN____8_SM.pin_CM, gen_pwGeneratorM2S__GEN____8_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN____8_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____8_SM.pin_OMEGA) annotation (Line());

// Connecting REGULATORS and REGULATORS
  connect(reg_pssi3e2b__GEN___11_SM.pin_VS, reg_sexs__GEN___11_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___11_SM.pin_OMEGA, reg_gsteam0__GEN___11_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___13_SM.pin_VS, reg_sexs__GEN___13_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___13_SM.pin_OMEGA, reg_gsteam0__GEN___13_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____1_SM.pin_VS, reg_sexs__GEN____1_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN____1_SM.pin_OMEGA, reg_gsteam0__GEN____1_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____2_SM.pin_VS, reg_sexs__GEN____2_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN____2_SM.pin_OMEGA, reg_gsteam0__GEN____2_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____5_SM.pin_VS, reg_sexs__GEN____5_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN____5_SM.pin_OMEGA, reg_gsteam0__GEN____5_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____8_SM.pin_VS, reg_sexs__GEN____8_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN____8_SM.pin_OMEGA, reg_gsteam0__GEN____8_SM.pin_OMEGA) annotation (Line());

// Connecting LINES
  connect(bus__BUS___14_TN.p, line__BUS___14_BUS___15_1_AC.p) annotation (Line());
  connect(line__BUS___14_BUS___15_1_AC.n, bus__BUS___15_TN.p) annotation (Line());
  connect(bus__BUS___15_TN.p, line__BUS___15_BUS___18_1_AC.p) annotation (Line());
  connect(line__BUS___15_BUS___18_1_AC.n, bus__BUS___18_TN.p) annotation (Line());
  connect(bus__BUS___15_TN.p, line__BUS___15_BUS___23_1_AC.p) annotation (Line());
  connect(line__BUS___15_BUS___23_1_AC.n, bus__BUS___23_TN.p) annotation (Line());
  connect(bus__BUS___16_TN.p, line__BUS___16_BUS___17_1_AC.p) annotation (Line());
  connect(line__BUS___16_BUS___17_1_AC.n, bus__BUS___17_TN.p) annotation (Line());
  connect(bus__BUS___18_TN.p, line__BUS___18_BUS___19_1_AC.p) annotation (Line());
  connect(line__BUS___18_BUS___19_1_AC.n, bus__BUS___19_TN.p) annotation (Line());
  connect(bus__BUS___19_TN.p, line__BUS___19_BUS___20_1_AC.p) annotation (Line());
  connect(line__BUS___19_BUS___20_1_AC.n, bus__BUS___20_TN.p) annotation (Line());
  connect(bus__BUS___21_TN.p, line__BUS___21_BUS___22_1_AC.p) annotation (Line());
  connect(line__BUS___21_BUS___22_1_AC.n, bus__BUS___22_TN.p) annotation (Line());
  connect(bus__BUS___22_TN.p, line__BUS___22_BUS___24_1_AC.p) annotation (Line());
  connect(line__BUS___22_BUS___24_1_AC.n, bus__BUS___24_TN.p) annotation (Line());
  connect(bus__BUS___23_TN.p, line__BUS___23_BUS___24_1_AC.p) annotation (Line());
  connect(line__BUS___23_BUS___24_1_AC.n, bus__BUS___24_TN.p) annotation (Line());
  connect(bus__BUS___24_TN.p, line__BUS___24_BUS___25_1_AC.p) annotation (Line());
  connect(line__BUS___24_BUS___25_1_AC.n, bus__BUS___25_TN.p) annotation (Line());
  connect(bus__BUS___25_TN.p, line__BUS___25_BUS___26_1_AC.p) annotation (Line());
  connect(line__BUS___25_BUS___26_1_AC.n, bus__BUS___26_TN.p) annotation (Line());
  connect(bus__BUS___25_TN.p, line__BUS___25_CLOV__27_1_AC.p) annotation (Line());
  connect(line__BUS___25_CLOV__27_1_AC.n, bus__CLOV__27_TN.p) annotation (Line());
  connect(bus__BUS___29_TN.p, line__BUS___29_BUS___30_1_AC.p) annotation (Line());
  connect(line__BUS___29_BUS___30_1_AC.n, bus__BUS___30_TN.p) annotation (Line());
  connect(bus__CLAY___2_TN.p, line__CLAY___2_FIEL___5_1_AC.p) annotation (Line());
  connect(line__CLAY___2_FIEL___5_1_AC.n, bus__FIEL___5_TN.p) annotation (Line());
  connect(bus__CLAY___2_TN.p, line__CLAY___2_HANC___4_1_AC.p) annotation (Line());
  connect(line__CLAY___2_HANC___4_1_AC.n, bus__HANC___4_TN.p) annotation (Line());
  connect(bus__CLAY___2_TN.p, line__CLAY___2_ROAN___6_1_AC.p) annotation (Line());
  connect(line__CLAY___2_ROAN___6_1_AC.n, bus__ROAN___6_TN.p) annotation (Line());
  connect(bus__CLOV__27_TN.p, line__CLOV__27_BUS___29_1_AC.p) annotation (Line());
  connect(line__CLOV__27_BUS___29_1_AC.n, bus__BUS___29_TN.p) annotation (Line());
  connect(bus__CLOV__27_TN.p, line__CLOV__27_BUS___30_1_AC.p) annotation (Line());
  connect(line__CLOV__27_BUS___30_1_AC.n, bus__BUS___30_TN.p) annotation (Line());
  connect(bus__FIEL___5_TN.p, line__FIEL___5_BLAI___7_1_AC.p) annotation (Line());
  connect(line__FIEL___5_BLAI___7_1_AC.n, bus__BLAI___7_TN.p) annotation (Line());
  connect(bus__GLEN___1_TN.p, line__GLEN___1_CLAY___2_1_AC.p) annotation (Line());
  connect(line__GLEN___1_CLAY___2_1_AC.n, bus__CLAY___2_TN.p) annotation (Line());
  connect(bus__GLEN___1_TN.p, line__GLEN___1_KUMI___3_1_AC.p) annotation (Line());
  connect(line__GLEN___1_KUMI___3_1_AC.n, bus__KUMI___3_TN.p) annotation (Line());
  connect(bus__HANC__12_TN.p, line__HANC__12_BUS___14_1_AC.p) annotation (Line());
  connect(line__HANC__12_BUS___14_1_AC.n, bus__BUS___14_TN.p) annotation (Line());
  connect(bus__HANC__12_TN.p, line__HANC__12_BUS___15_1_AC.p) annotation (Line());
  connect(line__HANC__12_BUS___15_1_AC.n, bus__BUS___15_TN.p) annotation (Line());
  connect(bus__HANC__12_TN.p, line__HANC__12_BUS___16_1_AC.p) annotation (Line());
  connect(line__HANC__12_BUS___16_1_AC.n, bus__BUS___16_TN.p) annotation (Line());
  connect(bus__HANC__12_TN.p, line__HANC__12_HANC__13_1_AC.p) annotation (Line());
  connect(line__HANC__12_HANC__13_1_AC.n, bus__HANC__13_TN.p) annotation (Line());
  connect(bus__HANC___4_TN.p, line__HANC___4_ROAN___6_1_AC.p) annotation (Line());
  connect(line__HANC___4_ROAN___6_1_AC.n, bus__ROAN___6_TN.p) annotation (Line());
  connect(bus__KUMI___3_TN.p, line__KUMI___3_HANC___4_1_AC.p) annotation (Line());
  connect(line__KUMI___3_HANC___4_1_AC.n, bus__HANC___4_TN.p) annotation (Line());
  connect(bus__REUS___8_TN.p, line__REUS___8_CLOV__28_1_AC.p) annotation (Line());
  connect(line__REUS___8_CLOV__28_1_AC.n, bus__CLOV__28_TN.p) annotation (Line());
  connect(bus__ROAN__10_TN.p, line__ROAN__10_BUS___17_1_AC.p) annotation (Line());
  connect(line__ROAN__10_BUS___17_1_AC.n, bus__BUS___17_TN.p) annotation (Line());
  connect(bus__ROAN__10_TN.p, line__ROAN__10_BUS___20_1_AC.p) annotation (Line());
  connect(line__ROAN__10_BUS___20_1_AC.n, bus__BUS___20_TN.p) annotation (Line());
  connect(bus__ROAN__10_TN.p, line__ROAN__10_BUS___21_1_AC.p) annotation (Line());
  connect(line__ROAN__10_BUS___21_1_AC.n, bus__BUS___21_TN.p) annotation (Line());
  connect(bus__ROAN__10_TN.p, line__ROAN__10_BUS___22_1_AC.p) annotation (Line());
  connect(line__ROAN__10_BUS___22_1_AC.n, bus__BUS___22_TN.p) annotation (Line());
  connect(bus__ROAN___6_TN.p, line__ROAN___6_BLAI___7_1_AC.p) annotation (Line());
  connect(line__ROAN___6_BLAI___7_1_AC.n, bus__BLAI___7_TN.p) annotation (Line());
  connect(bus__ROAN___6_TN.p, line__ROAN___6_CLOV__28_1_AC.p) annotation (Line());
  connect(line__ROAN___6_CLOV__28_1_AC.n, bus__CLOV__28_TN.p) annotation (Line());
  connect(bus__ROAN___6_TN.p, line__ROAN___6_REUS___8_1_AC.p) annotation (Line());
  connect(line__ROAN___6_REUS___8_1_AC.n, bus__REUS___8_TN.p) annotation (Line());
  connect(bus__ROAN___9_TN.p, line__ROAN___9_ROAN__10_1_AC.p) annotation (Line());
  connect(line__ROAN___9_ROAN__10_1_AC.n, bus__ROAN__10_TN.p) annotation (Line());
  connect(bus__ROAN___9_TN.p, line__ROAN___9_ROAN__11_1_AC.p) annotation (Line());
  connect(line__ROAN___9_ROAN__11_1_AC.n, bus__ROAN__11_TN.p) annotation (Line());

// COUPLING DEVICES

// Connecting LOADS
  connect(bus__ROAN__10_TN.p, load__LOAD__10_EC.p) annotation (Line());
  connect(bus__HANC__12_TN.p, load__LOAD__12_EC.p) annotation (Line());
  connect(bus__BUS___14_TN.p, load__LOAD__14_EC.p) annotation (Line());
  connect(bus__BUS___15_TN.p, load__LOAD__15_EC.p) annotation (Line());
  connect(bus__BUS___16_TN.p, load__LOAD__16_EC.p) annotation (Line());
  connect(bus__BUS___17_TN.p, load__LOAD__17_EC.p) annotation (Line());
  connect(bus__BUS___18_TN.p, load__LOAD__18_EC.p) annotation (Line());
  connect(bus__BUS___19_TN.p, load__LOAD__19_EC.p) annotation (Line());
  connect(bus__BUS___20_TN.p, load__LOAD__20_EC.p) annotation (Line());
  connect(bus__BUS___21_TN.p, load__LOAD__21_EC.p) annotation (Line());
  connect(bus__BUS___23_TN.p, load__LOAD__23_EC.p) annotation (Line());
  connect(bus__BUS___24_TN.p, load__LOAD__24_EC.p) annotation (Line());
  connect(bus__BUS___26_TN.p, load__LOAD__26_EC.p) annotation (Line());
  connect(bus__BUS___29_TN.p, load__LOAD__29_EC.p) annotation (Line());
  connect(bus__BUS___30_TN.p, load__LOAD__30_EC.p) annotation (Line());
  connect(bus__CLAY___2_TN.p, load__LOAD___2_EC.p) annotation (Line());
  connect(bus__KUMI___3_TN.p, load__LOAD___3_EC.p) annotation (Line());
  connect(bus__HANC___4_TN.p, load__LOAD___4_EC.p) annotation (Line());
  connect(bus__FIEL___5_TN.p, load__LOAD___5_EC.p) annotation (Line());
  connect(bus__BLAI___7_TN.p, load__LOAD___7_EC.p) annotation (Line());
  connect(bus__REUS___8_TN.p, load__LOAD___8_EC.p) annotation (Line());

// Connecting Capacitors
  connect(bus__ROAN__10_TN.p, cap_pwCapacitorBank__BANK__10_SC.p) annotation (Line());
  connect(bus__BUS___24_TN.p, cap_pwCapacitorBank__BANK__24_SC.p) annotation (Line());

// Connecting GENERATORS
  connect(bus__ROAN__11_TN.p, gen_pwGeneratorM2S__GEN___11_SM.sortie) annotation (Line());
  connect(bus__HANC__13_TN.p, gen_pwGeneratorM2S__GEN___13_SM.sortie) annotation (Line());
  connect(bus__GLEN___1_TN.p, gen_pwGeneratorM2S__GEN____1_SM.sortie) annotation (Line());
  connect(bus__CLAY___2_TN.p, gen_pwGeneratorM2S__GEN____2_SM.sortie) annotation (Line());
  connect(bus__FIEL___5_TN.p, gen_pwGeneratorM2S__GEN____5_SM.sortie) annotation (Line());
  connect(bus__REUS___8_TN.p, gen_pwGeneratorM2S__GEN____8_SM.sortie) annotation (Line());

// Connecting DETAILED TRANSFORMERS
  connect(bus__CLOV__28_TN.p, trafo__CLOV__28_CLOV__27_1_PT.p) annotation (Line());
  connect(trafo__CLOV__28_CLOV__27_1_PT.n, bus__CLOV__27_TN.p) annotation (Line());
  connect(bus__HANC___4_TN.p, trafo__HANC___4_HANC__12_1_PT.p) annotation (Line());
  connect(trafo__HANC___4_HANC__12_1_PT.n, bus__HANC__12_TN.p) annotation (Line());
  connect(bus__ROAN___6_TN.p, trafo__ROAN___6_ROAN__10_1_PT.p) annotation (Line());
  connect(trafo__ROAN___6_ROAN__10_1_PT.n, bus__ROAN__10_TN.p) annotation (Line());
  connect(bus__ROAN___6_TN.p, trafo__ROAN___6_ROAN___9_1_PT.p) annotation (Line());
  connect(trafo__ROAN___6_ROAN___9_1_PT.n, bus__ROAN___9_TN.p) annotation (Line());

// Connecting OTHERS
end ieee30bus;

