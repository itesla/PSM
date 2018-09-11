within ;
model ieee30bus
  inner iPSL.Electrical.SystemBase SysData (
    S_b = 100,
    fn = 50
  ) annotation (Placement(transformation()));
    iPSL.Electrical.Machines.Eurostag.omegaRef omegaRef (
	nGenerators = 6
	) annotation (Placement(transformation()));

// BUSES
  iPSL.Electrical.Buses.Bus bus__BLAI___7_TN (
	 V_0 = 1.0020001,
	 angle_0 = -13.12
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___14_TN (
	 V_0 = 1.042,
	 angle_0 = -16.13
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___15_TN (
	 V_0 = 1.0380001,
	 angle_0 = -16.22
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___16_TN (
	 V_0 = 1.0450001,
	 angle_0 = -15.83
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___17_TN (
	 V_0 = 1.04,
	 angle_0 = -16.14
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___18_TN (
	 V_0 = 1.028,
	 angle_0 = -16.82
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___19_TN (
	 V_0 = 1.026,
	 angle_0 = -17.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___20_TN (
	 V_0 = 1.0300001,
	 angle_0 = -16.8
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___21_TN (
	 V_0 = 1.033,
	 angle_0 = -16.42
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___22_TN (
	 V_0 = 1.033,
	 angle_0 = -16.41
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___23_TN (
	 V_0 = 1.027,
	 angle_0 = -16.61
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___24_TN (
	 V_0 = 1.021,
	 angle_0 = -16.78
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___25_TN (
	 V_0 = 1.0170001,
	 angle_0 = -16.35
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___26_TN (
	 V_0 = 1.0,
	 angle_0 = -16.77
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___29_TN (
	 V_0 = 1.003,
	 angle_0 = -17.06
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BUS___30_TN (
	 V_0 = 0.992,
	 angle_0 = -17.94
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__CLAY___2_TN (
	 V_0 = 1.043,
	 angle_0 = -5.48
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__CLOV__27_TN (
	 V_0 = 1.023,
	 angle_0 = -15.82
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__CLOV__28_TN (
	 V_0 = 1.007,
	 angle_0 = -11.97
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__FIEL___5_TN (
	 V_0 = 1.0100001,
	 angle_0 = -14.37
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__GLEN___1_TN (
	 V_0 = 1.06,
	 angle_0 = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__HANC__12_TN (
	 V_0 = 1.057,
	 angle_0 = -15.24
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__HANC__13_TN (
	 V_0 = 1.071,
	 angle_0 = -15.24
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__HANC___4_TN (
	 V_0 = 1.012,
	 angle_0 = -9.62
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__KUMI___3_TN (
	 V_0 = 1.021,
	 angle_0 = -7.96
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__REUS___8_TN (
	 V_0 = 1.0100001,
	 angle_0 = -12.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__ROAN__10_TN (
	 V_0 = 1.0450001,
	 angle_0 = -15.97
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__ROAN__11_TN (
	 V_0 = 1.082,
	 angle_0 = -14.39
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__ROAN___6_TN (
	 V_0 = 1.0100001,
	 angle_0 = -11.34
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__ROAN___9_TN (
	 V_0 = 1.051,
	 angle_0 = -14.38
	 ) annotation (Placement(transformation()));

// LOADS
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__10_EC (
	 V_0 = 1.0450001,
	 P_0 = 5.8,
	 Q_0 = 2.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -15.97
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__12_EC (
	 V_0 = 1.057,
	 P_0 = 11.2,
	 Q_0 = 7.5,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -15.24
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__14_EC (
	 V_0 = 1.042,
	 P_0 = 6.2,
	 Q_0 = 1.6,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -16.13
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__15_EC (
	 V_0 = 1.0380001,
	 P_0 = 8.2,
	 Q_0 = 2.5,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -16.22
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__16_EC (
	 V_0 = 1.0450001,
	 P_0 = 3.5,
	 Q_0 = 1.8,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -15.83
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__17_EC (
	 V_0 = 1.04,
	 P_0 = 9.0,
	 Q_0 = 5.8,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -16.14
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__18_EC (
	 V_0 = 1.028,
	 P_0 = 3.2,
	 Q_0 = 0.9,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -16.82
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__19_EC (
	 V_0 = 1.026,
	 P_0 = 9.5,
	 Q_0 = 3.4,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -17.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__20_EC (
	 V_0 = 1.0300001,
	 P_0 = 2.2,
	 Q_0 = 0.7,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -16.8
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__21_EC (
	 V_0 = 1.033,
	 P_0 = 17.5,
	 Q_0 = 11.2,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -16.42
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__23_EC (
	 V_0 = 1.027,
	 P_0 = 3.2,
	 Q_0 = 1.6,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -16.61
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__24_EC (
	 V_0 = 1.021,
	 P_0 = 8.7,
	 Q_0 = 6.7,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -16.78
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__26_EC (
	 V_0 = 1.0,
	 P_0 = 3.5,
	 Q_0 = 2.3,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -16.77
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__29_EC (
	 V_0 = 1.003,
	 P_0 = 2.4,
	 Q_0 = 0.9,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -17.06
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD__30_EC (
	 V_0 = 0.992,
	 P_0 = 10.6,
	 Q_0 = 1.9,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -17.94
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD___2_EC (
	 V_0 = 1.043,
	 P_0 = 21.7,
	 Q_0 = 12.7,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -5.48
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD___3_EC (
	 V_0 = 1.021,
	 P_0 = 2.4,
	 Q_0 = 1.2,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -7.96
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD___4_EC (
	 V_0 = 1.012,
	 P_0 = 7.6,
	 Q_0 = 1.6,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -9.62
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD___5_EC (
	 V_0 = 1.0100001,
	 P_0 = 94.2,
	 Q_0 = 19.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -14.37
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD___7_EC (
	 V_0 = 1.0020001,
	 P_0 = 22.8,
	 Q_0 = 10.9,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -13.12
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOAD___8_EC (
	 V_0 = 1.0100001,
	 P_0 = 30.0,
	 Q_0 = 30.0,
	 alpha = 1.5,
	 beta = 2.5,
	 angle_0 = -12.1
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
  iPSL.Electrical.Banks.PwCapacitorBank cap__BANK__10_SC (
	 B = 0.19000000443309545,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap__BANK__24_SC (
	 B = 0.04300003628712148,
	 nsteps = 1
	 ) annotation (Placement(transformation()));

// FIXED INJECTIONS

// GENERATORS
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___11_SM (
	 ur0 = 1.048053946455139, 
	 ui0 = -0.2688995397306048, 
	 transformerIncluded = true, 
	 V2 = 33.0, 
	 Saturated = false, 
	 TX = 0, 
	 md = 0, 
	 XPQ = 0, 
	 TPQ0 = 0, 
	 mq = 0, 
	 snd = 0, 
	 snq = 0, 
	 init_theta = -0.2514719609261382, 
	 init_omega = 1.0, 
	 init_efd = 0.6259813536516522, 
	 WLMDVPu = 0.6941167416525881, 
	 init_lambdaad = -1.153805934590451, 
	 init_cm = 0.0002111481651486607, 
	 init_lambdaq1 = 0.0001818363953779318, 
	 init_lambdaq2 = 0.0001818363953779318, 
	 init_iq = 4.777470977756354e-05, 
	 init_id = 0.149722725677131, 
	 init_lambdaaq = 0.0001818363953779318, 
	 init_lambdad = -1.153805934590451, 
	 init_lambdaf = -1.280086336023334,
	 PNALT = 39.2,
	 IENR = 3,
	 DIn = 0.0,
	 HIn = 5.22,
	 TSD0 = 0.033,
	 XPD = 0.253,
	 RTfoPu = 0.0,
	 TPD0 = 5.51,
	 XTfoPu = 0.1,
	 rStatIn = 0.0018,
	 U1N = 11.0,
	 TSQ0 = 0.034,
	 SN = 49.0,
	 V1 = 11.0,
	 XSD = 0.171,
	 XD = 2.07,
	 U2N = 33.0,
	 SNtfo = 49.0,
	 lStatIn = 0.135,
	 XSQ = 0.198,
	 XQ = 2.0,
	 PN = 39.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___13_SM (
	 ur0 = 1.0333363577505341, 
	 ui0 = -0.28152607135518076, 
	 transformerIncluded = true, 
	 V2 = 33.0, 
	 Saturated = false, 
	 TX = 0, 
	 md = 0, 
	 XPQ = 0, 
	 TPQ0 = 0, 
	 mq = 0, 
	 snd = 0, 
	 snq = 0, 
	 init_theta = -0.266656709699008, 
	 init_omega = 1.0, 
	 init_efd = 0.7468280840201449, 
	 WLMDVPu = 0.6325921276173984, 
	 init_lambdaad = -1.167145418252866, 
	 init_cm = 0.0002623832374269654, 
	 init_lambdaq1 = 6.805771091191288e-05, 
	 init_lambdaq2 = 6.805771091191288e-05, 
	 init_iq = 6.616721894218275e-05, 
	 init_id = 0.09897290783764841, 
	 init_lambdaaq = 6.805771091191288e-05, 
	 init_lambdad = -1.167145418252866, 
	 init_lambdaf = -1.385464434159541,
	 PNALT = 32.0,
	 IENR = 3,
	 DIn = 0.0,
	 HIn = 3.657,
	 TSD0 = 0.15,
	 XPD = 0.38,
	 RTfoPu = 0.0,
	 TPD0 = 9.4,
	 XTfoPu = 0.1,
	 rStatIn = 0.003,
	 U1N = 11.0,
	 TSQ0 = 0.2,
	 SN = 35.0,
	 V1 = 11.0,
	 XSD = 0.29,
	 XD = 1.54,
	 U2N = 33.0,
	 SNtfo = 35.0,
	 lStatIn = 0.24,
	 XSQ = 0.4,
	 XQ = 0.6,
	 PN = 32.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN____1_SM (
	 ur0 = 1.059999942779541, 
	 ui0 = 0.0, 
	 transformerIncluded = true, 
	 V2 = 132.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.3560600302799834, 
	 init_omega = 1.0, 
	 init_efd = 0.4543799922082246, 
	 WLMDVPu = 0.6296504227510634, 
	 init_lambdaad = -1.012164886788259, 
	 init_cm = 0.2475092470587476, 
	 init_lambdaq1 = 0.3087124065870414, 
	 init_lambdaq2 = 0.3087124065870414, 
	 init_iq = 2.44422195118198, 
	 init_id = 0.7252548121150275, 
	 init_lambdaaq = 0.3087124065870414, 
	 init_lambdad = -1.012164886788259, 
	 init_lambdaf = -1.174151304178101,
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
	 ur0 = 1.0382330497765389, 
	 ui0 = -0.09960471145053192, 
	 transformerIncluded = true, 
	 V2 = 132.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = -0.02339832032387095, 
	 init_omega = 1.0, 
	 init_efd = 0.390976885703851, 
	 WLMDVPu = 0.717262019773382, 
	 init_lambdaad = -1.054796920759688, 
	 init_cm = 0.03969445772464725, 
	 init_lambdaq1 = 0.06553889799168312, 
	 init_lambdaq2 = 0.06553889799168312, 
	 init_iq = 0.3479052019530076, 
	 init_id = 0.5058186686682057, 
	 init_lambdaaq = 0.06553889799168312, 
	 init_lambdad = -1.054796920759688, 
	 init_lambdaf = -1.174070423417886,
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
	 ur0 = 0.9784004845124216, 
	 ui0 = -0.2506645436522971, 
	 transformerIncluded = true, 
	 V2 = 132.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = -0.2508695375920037, 
	 init_omega = 1.0, 
	 init_efd = 0.3226375731236257, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -1.017904101626006, 
	 init_cm = 1.730761759244152e-06, 
	 init_lambdaq1 = 3.242852943455253e-06, 
	 init_lambdaq2 = 3.242852943455253e-06, 
	 init_iq = 2.408265806809473e-05, 
	 init_id = 0.3663365930925052, 
	 init_lambdaaq = 3.242852943455253e-06, 
	 init_lambdad = -1.017904101626006, 
	 init_lambdaf = -1.150649111967276,
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
	 ur0 = 0.9875611753282938, 
	 ui0 = -0.21171477634486136, 
	 transformerIncluded = true, 
	 V2 = 132.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = -0.2112510879474213, 
	 init_omega = 1.0, 
	 init_efd = 0.3134639695073154, 
	 WLMDVPu = 0.7304917626978259, 
	 init_lambdaad = -1.017882978502718, 
	 init_cm = 1.697271801238953e-06, 
	 init_lambdaq1 = 3.301623365543857e-06, 
	 init_lambdaq2 = 3.301623365543857e-06, 
	 init_iq = 2.446420786143821e-05, 
	 init_id = 0.369306889780962, 
	 init_lambdaaq = 3.301623365543857e-06, 
	 init_lambdad = -1.017882978502718, 
	 init_lambdaf = -1.150639909186287,
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
  iPSL.Electrical.Controls.Eurostag.pssi3e2b reg_pssi3e2b__GEN___11_SM (
	 SN = 49.0,
	 PN = 39.0,
	 PNALT = 39.2,
	 init_APREF = 2.832201593431522e-17,
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
  iPSL.Electrical.Controls.Eurostag.sexs reg_sexs__GEN___11_SM (
	 SN = 49.0,
	 PN = 39.0,
	 PNALT = 39.2,
	 init_EFD = 0.6259813536516522,
	 init_VREF = 1.115685583791395,
	 init_YLL = 0.003129906768258261,
	 TE = 0.05,
	 KC = 1.,
	 K = 200.,
	 EMIN = 0.,
	 TA = 3.,
	 TB = 10.,
	 EMAX = 4.
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.gsteam0 reg_gsteam0__GEN___11_SM (
	 SN = 49.0,
	 PN = 39.0,
	 PNALT = 39.2,
	 init_REF = 1.055740825743304e-05,
	 init_PMECH = 0.0002111481651486607,
	 init_CM = 0.0002111481651486607,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.pssi3e2b reg_pssi3e2b__GEN___13_SM (
	 SN = 35.0,
	 PN = 32.0,
	 PNALT = 32.0,
	 init_APREF = 9.912705577010326e-18,
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
  iPSL.Electrical.Controls.Eurostag.sexs reg_sexs__GEN___13_SM (
	 SN = 35.0,
	 PN = 32.0,
	 PNALT = 32.0,
	 init_EFD = 0.7468280840201449,
	 init_VREF = 1.103012100380174,
	 init_YLL = 0.003734140420100725,
	 TE = 0.05,
	 KC = 1.,
	 K = 200.,
	 EMIN = 0.,
	 TA = 3.,
	 TB = 10.,
	 EMAX = 4.
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.gsteam0 reg_gsteam0__GEN___13_SM (
	 SN = 35.0,
	 PN = 32.0,
	 PNALT = 32.0,
	 init_REF = 1.311916187134827e-05,
	 init_PMECH = 0.0002623832374269654,
	 init_CM = 0.0002623832374269654,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.pssi3e2b reg_pssi3e2b__GEN____1_SM (
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_APREF = 0.2226548307184145,
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
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_EFD = 0.4543799922082246,
	 init_VREF = 1.061057829415572,
	 init_YLL = 0.002271899961041123,
	 TE = 0.05,
	 KC = 1.,
	 K = 200.,
	 EMIN = 0.,
	 TA = 3.,
	 TB = 10.,
	 EMAX = 4.
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.gsteam0 reg_gsteam0__GEN____1_SM (
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_REF = 0.01237546235293738,
	 init_PMECH = 0.2475092470587476,
	 init_CM = 0.2475092470587476,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.pssi3e2b reg_pssi3e2b__GEN____2_SM (
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_APREF = 0.03571428571428571,
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
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_EFD = 0.390976885703851,
	 init_VREF = 1.049240700767265,
	 init_YLL = 0.001954884428519255,
	 TE = 0.05,
	 KC = 1.,
	 K = 200.,
	 EMIN = 0.,
	 TA = 3.,
	 TB = 10.,
	 EMAX = 4.
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.gsteam0 reg_gsteam0__GEN____2_SM (
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_REF = 0.001984722886232363,
	 init_PMECH = 0.03969445772464725,
	 init_CM = 0.03969445772464725,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.pssi3e2b reg_pssi3e2b__GEN____5_SM (
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_APREF = -8.410780489584519e-19,
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
  iPSL.Electrical.Controls.Eurostag.sexs reg_sexs__GEN____5_SM (
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_EFD = 0.3226375731236257,
	 init_VREF = 1.01383351931928,
	 init_YLL = 0.001613187865618129,
	 TE = 0.05,
	 KC = 1.,
	 K = 200.,
	 EMIN = 0.,
	 TA = 3.,
	 TB = 10.,
	 EMAX = 4.
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.gsteam0 reg_gsteam0__GEN____5_SM (
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_REF = 8.653808796220762e-08,
	 init_PMECH = 1.730761759244152e-06,
	 init_CM = 1.730761759244152e-06,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.pssi3e2b reg_pssi3e2b__GEN____8_SM (
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
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
  iPSL.Electrical.Controls.Eurostag.sexs reg_sexs__GEN____8_SM (
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_EFD = 0.3134639695073154,
	 init_VREF = 1.013727118938745,
	 init_YLL = 0.001567319847536577,
	 TE = 0.05,
	 KC = 1.,
	 K = 200.,
	 EMIN = 0.,
	 TA = 3.,
	 TB = 10.,
	 EMAX = 4.
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.Eurostag.gsteam0 reg_gsteam0__GEN____8_SM (
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_REF = 8.486359006194766e-08,
	 init_PMECH = 1.697271801238953e-06,
	 init_CM = 1.697271801238953e-06,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));

equation
  connect(omegaRef.pin_HIn[1], gen_pwGeneratorM2S__GEN___11_SM.pin_HIn);
  connect(omegaRef.pin_HIn[2], gen_pwGeneratorM2S__GEN___13_SM.pin_HIn);
  connect(omegaRef.pin_HIn[3], gen_pwGeneratorM2S__GEN____1_SM.pin_HIn);
  connect(omegaRef.pin_HIn[4], gen_pwGeneratorM2S__GEN____2_SM.pin_HIn);
  connect(omegaRef.pin_HIn[5], gen_pwGeneratorM2S__GEN____5_SM.pin_HIn);
  connect(omegaRef.pin_HIn[6], gen_pwGeneratorM2S__GEN____8_SM.pin_HIn);

  connect(omegaRef.pin_SN[1], gen_pwGeneratorM2S__GEN___11_SM.pin_SN);
  connect(omegaRef.pin_SN[2], gen_pwGeneratorM2S__GEN___13_SM.pin_SN);
  connect(omegaRef.pin_SN[3], gen_pwGeneratorM2S__GEN____1_SM.pin_SN);
  connect(omegaRef.pin_SN[4], gen_pwGeneratorM2S__GEN____2_SM.pin_SN);
  connect(omegaRef.pin_SN[5], gen_pwGeneratorM2S__GEN____5_SM.pin_SN);
  connect(omegaRef.pin_SN[6], gen_pwGeneratorM2S__GEN____8_SM.pin_SN);

  connect(omegaRef.pin_omega[1], gen_pwGeneratorM2S__GEN___11_SM.pin_OMEGA);
  connect(omegaRef.pin_omega[2], gen_pwGeneratorM2S__GEN___13_SM.pin_OMEGA);
  connect(omegaRef.pin_omega[3], gen_pwGeneratorM2S__GEN____1_SM.pin_OMEGA);
  connect(omegaRef.pin_omega[4], gen_pwGeneratorM2S__GEN____2_SM.pin_OMEGA);
  connect(omegaRef.pin_omega[5], gen_pwGeneratorM2S__GEN____5_SM.pin_OMEGA);
  connect(omegaRef.pin_omega[6], gen_pwGeneratorM2S__GEN____8_SM.pin_OMEGA);

  connect(omegaRef.omegaRef, gen_pwGeneratorM2S__GEN___11_SM.omegaRef);
  connect(omegaRef.omegaRef, gen_pwGeneratorM2S__GEN___13_SM.omegaRef);
  connect(omegaRef.omegaRef, gen_pwGeneratorM2S__GEN____1_SM.omegaRef);
  connect(omegaRef.omegaRef, gen_pwGeneratorM2S__GEN____2_SM.omegaRef);
  connect(omegaRef.omegaRef, gen_pwGeneratorM2S__GEN____5_SM.omegaRef);
  connect(omegaRef.omegaRef, gen_pwGeneratorM2S__GEN____8_SM.omegaRef);
 
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
  connect(bus__ROAN__10_TN.p, cap__BANK__10_SC.p) annotation (Line());
  connect(bus__BUS___24_TN.p, cap__BANK__24_SC.p) annotation (Line());

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
// Modelica version required
  annotation (uses(Modelica(version="3.2.1")));
end ieee30bus;

