within ;
model ieee118bus
  parameter Real SNREF = 100.0;
  Modelica.Blocks.Interfaces.RealOutput omegaRef;


// BUSES
  iPSL.Electrical.Buses.Bus bus__ADAM__20_TN (
	 V_0 = 0.95799994,
	 angle_0 = 11.93
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BAIL__96_TN (
	 V_0 = 0.993,
	 angle_0 = 27.51
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BEAV__85_TN (
	 V_0 = 0.98499995,
	 angle_0 = 32.51
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BELL__74_TN (
	 V_0 = 0.95799994,
	 angle_0 = 21.64
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BEQU___9_TN (
	 V_0 = 1.043,
	 angle_0 = 28.02
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BETS__84_TN (
	 V_0 = 0.98,
	 angle_0 = 30.95
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BLAI_108_TN (
	 V_0 = 0.967,
	 angle_0 = 19.38
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BRAD__98_TN (
	 V_0 = 1.0239999,
	 angle_0 = 27.4
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BREE__10_TN (
	 V_0 = 1.05,
	 angle_0 = 35.61
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__CABI__80_TN (
	 V_0 = 1.0400001,
	 angle_0 = 28.96
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__CALD__95_TN (
	 V_0 = 0.98100007,
	 angle_0 = 27.67
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__CAPI__79_TN (
	 V_0 = 1.0090001,
	 angle_0 = 26.72
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__CHEM__78_TN (
	 V_0 = 1.003,
	 angle_0 = 26.42
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__CLAY_103_TN (
	 V_0 = 1.001,
	 angle_0 = 24.44
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__CLIN__89_TN (
	 V_0 = 1.005,
	 angle_0 = 39.69
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__CLOV_106_TN (
	 V_0 = 0.96199995,
	 angle_0 = 20.32
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__COLL__23_TN (
	 V_0 = 1.0,
	 angle_0 = 21.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__CONC__13_TN (
	 V_0 = 0.968,
	 angle_0 = 11.35
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__CORE_117_TN (
	 V_0 = 0.97400004,
	 angle_0 = 10.67
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__CROO__47_TN (
	 V_0 = 1.017,
	 angle_0 = 20.73
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__DANR_111_TN (
	 V_0 = 0.98,
	 angle_0 = 19.74
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__DANV_112_TN (
	 V_0 = 0.975,
	 angle_0 = 14.99
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__DARR__76_TN (
	 V_0 = 0.943,
	 angle_0 = 21.77
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__DEER_113_TN (
	 V_0 = 0.993,
	 angle_0 = 13.74
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__DEER__31_TN (
	 V_0 = 0.967,
	 angle_0 = 12.75
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__DELA__32_TN (
	 V_0 = 0.964,
	 angle_0 = 14.8
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__EAST__37_TN (
	 V_0 = 0.992,
	 angle_0 = 11.77
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__EAST__38_TN (
	 V_0 = 0.9620001,
	 angle_0 = 16.91
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__FIEL_110_TN (
	 V_0 = 0.973,
	 angle_0 = 18.09
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__FRAN_109_TN (
	 V_0 = 0.967,
	 angle_0 = 18.93
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__FREM__88_TN (
	 V_0 = 0.9869999,
	 angle_0 = 35.64
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__FTWA__15_TN (
	 V_0 = 0.97,
	 angle_0 = 11.23
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__GLEN_100_TN (
	 V_0 = 1.017,
	 angle_0 = 28.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__GOSH__14_TN (
	 V_0 = 0.984,
	 angle_0 = 11.5
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__GRAN__29_TN (
	 V_0 = 0.963,
	 angle_0 = 12.63
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__HANC_104_TN (
	 V_0 = 0.971,
	 angle_0 = 21.69
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__HAVI__33_TN (
	 V_0 = 0.972,
	 angle_0 = 10.63
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__HAZA__86_TN (
	 V_0 = 0.9869999,
	 angle_0 = 31.14
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__HICK___3_TN (
	 V_0 = 0.968,
	 angle_0 = 11.56
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__HILL__72_TN (
	 V_0 = 0.98,
	 angle_0 = 20.98
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__HINT__99_TN (
	 V_0 = 1.01,
	 angle_0 = 27.04
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__HOLS__90_TN (
	 V_0 = 0.98499995,
	 angle_0 = 33.29
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__HOLS__91_TN (
	 V_0 = 0.98,
	 angle_0 = 33.31
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__HOWA__42_TN (
	 V_0 = 0.98499995,
	 angle_0 = 8.53
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__JACK___7_TN (
	 V_0 = 0.98899996,
	 angle_0 = 12.56
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__JAY___21_TN (
	 V_0 = 0.959,
	 angle_0 = 13.52
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__KAMM__64_TN (
	 V_0 = 0.984,
	 angle_0 = 24.52
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__KANA__81_TN (
	 V_0 = 0.997,
	 angle_0 = 28.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__KANK___6_TN (
	 V_0 = 0.98999995,
	 angle_0 = 13.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__KYGE_116_TN (
	 V_0 = 1.005,
	 angle_0 = 27.12
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__LINC__19_TN (
	 V_0 = 0.963,
	 angle_0 = 11.05
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__LOGA__82_TN (
	 V_0 = 0.98899996,
	 angle_0 = 27.24
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__MADI__27_TN (
	 V_0 = 0.968,
	 angle_0 = 15.35
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__MCKI__18_TN (
	 V_0 = 0.973,
	 angle_0 = 11.53
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__MEDF_115_TN (
	 V_0 = 0.96,
	 angle_0 = 14.46
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__MULL__28_TN (
	 V_0 = 0.96199995,
	 angle_0 = 13.62
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__MUSK__65_TN (
	 V_0 = 1.005,
	 angle_0 = 27.65
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__MUSK__66_TN (
	 V_0 = 1.05,
	 angle_0 = 27.48
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N_NE__45_TN (
	 V_0 = 0.9869999,
	 angle_0 = 15.67
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N__E__16_TN (
	 V_0 = 0.984,
	 angle_0 = 11.91
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__NATR__62_TN (
	 V_0 = 0.99799997,
	 angle_0 = 23.43
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__NEWC__51_TN (
	 V_0 = 0.967,
	 angle_0 = 16.28
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__NPOR__71_TN (
	 V_0 = 0.9869999,
	 angle_0 = 22.15
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__NWCA___4_TN (
	 V_0 = 0.99799997,
	 angle_0 = 15.28
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__NWLI__39_TN (
	 V_0 = 0.97,
	 angle_0 = 8.41
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__OLIV___5_TN (
	 V_0 = 1.002,
	 angle_0 = 15.73
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__OLIV___8_TN (
	 V_0 = 1.015,
	 angle_0 = 20.77
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__PHIL__49_TN (
	 V_0 = 1.025,
	 angle_0 = 20.94
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__PINE__87_TN (
	 V_0 = 1.0150001,
	 angle_0 = 31.4
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__POKA___2_TN (
	 V_0 = 0.971,
	 angle_0 = 11.22
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__PORT__70_TN (
	 V_0 = 0.984,
	 angle_0 = 22.58
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__RAND__22_TN (
	 V_0 = 0.97,
	 angle_0 = 16.08
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__REUS_107_TN (
	 V_0 = 0.952,
	 angle_0 = 17.53
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__RIVE___1_TN (
	 V_0 = 0.9549999,
	 angle_0 = 10.67
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__ROAN_105_TN (
	 V_0 = 0.965,
	 angle_0 = 20.57
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__ROCK__34_TN (
	 V_0 = 0.98599994,
	 angle_0 = 11.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__S_KE__43_TN (
	 V_0 = 0.97800004,
	 angle_0 = 11.28
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__S_TI__41_TN (
	 V_0 = 0.967,
	 angle_0 = 6.92
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SALT__92_TN (
	 V_0 = 0.993,
	 angle_0 = 33.8
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SARG__73_TN (
	 V_0 = 0.991,
	 angle_0 = 21.94
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SCOS__52_TN (
	 V_0 = 0.95699996,
	 angle_0 = 15.32
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SMYT_102_TN (
	 V_0 = 0.991,
	 angle_0 = 32.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SORE__17_TN (
	 V_0 = 0.995,
	 angle_0 = 13.74
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SORE__30_TN (
	 V_0 = 0.968,
	 angle_0 = 18.79
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SOUT__11_TN (
	 V_0 = 0.98499995,
	 angle_0 = 12.72
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SPOR__68_TN (
	 V_0 = 1.003,
	 angle_0 = 27.55
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SPOR__69_TN (
	 V_0 = 1.035,
	 angle_0 = 30.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SPRI__83_TN (
	 V_0 = 0.98499995,
	 angle_0 = 28.42
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__STER__36_TN (
	 V_0 = 0.98,
	 angle_0 = 10.87
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__STHP__75_TN (
	 V_0 = 0.967,
	 angle_0 = 22.91
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SUMM__67_TN (
	 V_0 = 1.02,
	 angle_0 = 24.84
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SUND__97_TN (
	 V_0 = 1.011,
	 angle_0 = 27.88
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SUNN__56_TN (
	 V_0 = 0.95399994,
	 angle_0 = 15.16
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SWIT__94_TN (
	 V_0 = 0.991,
	 angle_0 = 28.64
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SWKA__60_TN (
	 V_0 = 0.993,
	 angle_0 = 23.15
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__TANN__25_TN (
	 V_0 = 1.05,
	 angle_0 = 27.93
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__TANN__26_TN (
	 V_0 = 1.015,
	 angle_0 = 29.71
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__TAZE__93_TN (
	 V_0 = 0.9869999,
	 angle_0 = 30.79
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__TIDD__59_TN (
	 V_0 = 0.98499995,
	 angle_0 = 19.37
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__TIDD__63_TN (
	 V_0 = 0.969,
	 angle_0 = 22.75
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__TORR__54_TN (
	 V_0 = 0.9549999,
	 angle_0 = 15.26
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__TREN__24_TN (
	 V_0 = 0.992,
	 angle_0 = 20.89
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__TURN__77_TN (
	 V_0 = 1.006,
	 angle_0 = 26.72
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__TWIN__12_TN (
	 V_0 = 0.98999995,
	 angle_0 = 12.2
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__W_KA__61_TN (
	 V_0 = 0.995,
	 angle_0 = 24.04
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__W_LA__46_TN (
	 V_0 = 1.005,
	 angle_0 = 18.49
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__WAGE__55_TN (
	 V_0 = 0.952,
	 angle_0 = 14.97
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__WCAM__50_TN (
	 V_0 = 1.001,
	 angle_0 = 18.9
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__WEST__35_TN (
	 V_0 = 0.98100007,
	 angle_0 = 10.87
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__WEST__40_TN (
	 V_0 = 0.97,
	 angle_0 = 7.35
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__WHUN_118_TN (
	 V_0 = 0.94900006,
	 angle_0 = 21.92
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__WMED_114_TN (
	 V_0 = 0.96,
	 angle_0 = 14.46
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__WMVE__44_TN (
	 V_0 = 0.98499995,
	 angle_0 = 13.82
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__WNWP__57_TN (
	 V_0 = 0.971,
	 angle_0 = 16.36
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__WNWP__58_TN (
	 V_0 = 0.959,
	 angle_0 = 15.51
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__WOOS__53_TN (
	 V_0 = 0.94600004,
	 angle_0 = 14.35
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__WYTH_101_TN (
	 V_0 = 0.993,
	 angle_0 = 29.61
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__ZANE__48_TN (
	 V_0 = 1.0209999,
	 angle_0 = 19.93
	 ) annotation (Placement(transformation()));

// LOADS
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__ADAM__20_EC (
	 V_0 = 0.95799994,
	 P_0 = 18.0,
	 Q_0 = 3.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 11.93
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BAIL__96_EC (
	 V_0 = 0.993,
	 P_0 = 38.0,
	 Q_0 = 15.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 27.51
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BEAV__85_EC (
	 V_0 = 0.98499995,
	 P_0 = 24.0,
	 Q_0 = 15.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 32.51
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BELL__74_EC (
	 V_0 = 0.95799994,
	 P_0 = 68.0,
	 Q_0 = 27.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 21.64
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BETS__84_EC (
	 V_0 = 0.98,
	 P_0 = 11.0,
	 Q_0 = 7.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 30.95
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BLAI_108_EC (
	 V_0 = 0.967,
	 P_0 = 2.0,
	 Q_0 = 1.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 19.38
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BRAD__98_EC (
	 V_0 = 1.0239999,
	 P_0 = 34.0,
	 Q_0 = 8.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 27.4
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__CABI__80_EC (
	 V_0 = 1.0400001,
	 P_0 = 130.0,
	 Q_0 = 26.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 28.96
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__CALD__95_EC (
	 V_0 = 0.98100007,
	 P_0 = 42.0,
	 Q_0 = 31.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 27.67
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__CAPI__79_EC (
	 V_0 = 1.0090001,
	 P_0 = 39.0,
	 Q_0 = 32.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 26.72
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__CHEM__78_EC (
	 V_0 = 1.003,
	 P_0 = 71.0,
	 Q_0 = 26.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 26.42
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__CLAY_103_EC (
	 V_0 = 1.001,
	 P_0 = 23.0,
	 Q_0 = 16.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 24.44
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__CLOV_106_EC (
	 V_0 = 0.96199995,
	 P_0 = 43.0,
	 Q_0 = 16.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 20.32
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__COLL__23_EC (
	 V_0 = 1.0,
	 P_0 = 7.0,
	 Q_0 = 3.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 21.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__CONC__13_EC (
	 V_0 = 0.968,
	 P_0 = 34.0,
	 Q_0 = 16.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 11.35
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__CORE_117_EC (
	 V_0 = 0.97400004,
	 P_0 = 20.0,
	 Q_0 = 8.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 10.67
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__CROO__47_EC (
	 V_0 = 1.017,
	 P_0 = 34.0,
	 Q_0 = 0.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 20.73
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__DANV_112_EC (
	 V_0 = 0.975,
	 P_0 = 25.0,
	 Q_0 = 13.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 14.99
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__DARR__76_EC (
	 V_0 = 0.943,
	 P_0 = 68.0,
	 Q_0 = 36.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 21.77
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__DEER__31_EC (
	 V_0 = 0.967,
	 P_0 = 43.0,
	 Q_0 = 27.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 12.75
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__DELA__32_EC (
	 V_0 = 0.964,
	 P_0 = 59.0,
	 Q_0 = 23.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 14.8
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__FIEL_110_EC (
	 V_0 = 0.973,
	 P_0 = 39.0,
	 Q_0 = 30.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 18.09
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__FRAN_109_EC (
	 V_0 = 0.967,
	 P_0 = 8.0,
	 Q_0 = 3.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 18.93
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__FREM__88_EC (
	 V_0 = 0.9869999,
	 P_0 = 48.0,
	 Q_0 = 10.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 35.64
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__FTWA__15_EC (
	 V_0 = 0.97,
	 P_0 = 90.0,
	 Q_0 = 30.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 11.23
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__GLEN_100_EC (
	 V_0 = 1.017,
	 P_0 = 37.0,
	 Q_0 = 18.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 28.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__GOSH__14_EC (
	 V_0 = 0.984,
	 P_0 = 14.0,
	 Q_0 = 1.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 11.5
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__GRAN__29_EC (
	 V_0 = 0.963,
	 P_0 = 24.0,
	 Q_0 = 4.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 12.63
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__HANC_104_EC (
	 V_0 = 0.971,
	 P_0 = 38.0,
	 Q_0 = 25.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 21.69
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__HAVI__33_EC (
	 V_0 = 0.972,
	 P_0 = 23.0,
	 Q_0 = 9.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 10.63
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__HAZA__86_EC (
	 V_0 = 0.9869999,
	 P_0 = 21.0,
	 Q_0 = 10.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 31.14
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__HICK___3_EC (
	 V_0 = 0.968,
	 P_0 = 39.0,
	 Q_0 = 10.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 11.56
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__HOLS__90_EC (
	 V_0 = 0.98499995,
	 P_0 = 78.0,
	 Q_0 = 42.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 33.29
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__HOWA__42_EC (
	 V_0 = 0.98499995,
	 P_0 = 37.0,
	 Q_0 = 23.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 8.53
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__JACK___7_EC (
	 V_0 = 0.98899996,
	 P_0 = 19.0,
	 Q_0 = 2.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 12.56
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__JAY___21_EC (
	 V_0 = 0.959,
	 P_0 = 14.0,
	 Q_0 = 8.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 13.52
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__KANK___6_EC (
	 V_0 = 0.98999995,
	 P_0 = 52.0,
	 Q_0 = 22.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 13.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LINC__19_EC (
	 V_0 = 0.963,
	 P_0 = 45.0,
	 Q_0 = 25.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 11.05
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOGA__82_EC (
	 V_0 = 0.98899996,
	 P_0 = 54.0,
	 Q_0 = 27.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 27.24
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__MADI__27_EC (
	 V_0 = 0.968,
	 P_0 = 62.0,
	 Q_0 = 13.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 15.35
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__MCKI__18_EC (
	 V_0 = 0.973,
	 P_0 = 60.0,
	 Q_0 = 34.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 11.53
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__MEDF_115_EC (
	 V_0 = 0.96,
	 P_0 = 22.0,
	 Q_0 = 7.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 14.46
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__MULL__28_EC (
	 V_0 = 0.96199995,
	 P_0 = 17.0,
	 Q_0 = 7.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 13.62
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__MUSK__66_EC (
	 V_0 = 1.05,
	 P_0 = 39.0,
	 Q_0 = 18.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 27.48
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__N_NE__45_EC (
	 V_0 = 0.9869999,
	 P_0 = 53.0,
	 Q_0 = 22.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 15.67
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__N__E__16_EC (
	 V_0 = 0.984,
	 P_0 = 25.0,
	 Q_0 = 10.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 11.91
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__NATR__62_EC (
	 V_0 = 0.99799997,
	 P_0 = 77.0,
	 Q_0 = 14.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 23.43
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__NEWC__51_EC (
	 V_0 = 0.967,
	 P_0 = 17.0,
	 Q_0 = 8.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 16.28
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__NWCA___4_EC (
	 V_0 = 0.99799997,
	 P_0 = 30.0,
	 Q_0 = 12.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 15.28
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__NWLI__39_EC (
	 V_0 = 0.97,
	 P_0 = 27.0,
	 Q_0 = 11.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 8.41
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__PHIL__49_EC (
	 V_0 = 1.025,
	 P_0 = 87.0,
	 Q_0 = 30.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 20.94
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__POKA___2_EC (
	 V_0 = 0.971,
	 P_0 = 20.0,
	 Q_0 = 9.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 11.22
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__PORT__70_EC (
	 V_0 = 0.984,
	 P_0 = 66.0,
	 Q_0 = 20.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 22.58
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__RAND__22_EC (
	 V_0 = 0.97,
	 P_0 = 10.0,
	 Q_0 = 5.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 16.08
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__REUS_107_EC (
	 V_0 = 0.952,
	 P_0 = 28.0,
	 Q_0 = 12.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 17.53
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__RIVE___1_EC (
	 V_0 = 0.9549999,
	 P_0 = 51.0,
	 Q_0 = 27.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 10.67
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__ROAN_105_EC (
	 V_0 = 0.965,
	 P_0 = 31.0,
	 Q_0 = 26.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 20.57
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__ROCK__34_EC (
	 V_0 = 0.98599994,
	 P_0 = 59.0,
	 Q_0 = 26.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 11.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__S_KE__43_EC (
	 V_0 = 0.97800004,
	 P_0 = 18.0,
	 Q_0 = 7.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 11.28
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__S_TI__41_EC (
	 V_0 = 0.967,
	 P_0 = 37.0,
	 Q_0 = 10.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 6.92
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__SALT__92_EC (
	 V_0 = 0.993,
	 P_0 = 65.0,
	 Q_0 = 10.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 33.8
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__SCOS__52_EC (
	 V_0 = 0.95699996,
	 P_0 = 18.0,
	 Q_0 = 5.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 15.32
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__SMYT_102_EC (
	 V_0 = 0.991,
	 P_0 = 5.0,
	 Q_0 = 3.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 32.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__SORE__17_EC (
	 V_0 = 0.995,
	 P_0 = 11.0,
	 Q_0 = 3.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 13.74
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__SOUT__11_EC (
	 V_0 = 0.98499995,
	 P_0 = 70.0,
	 Q_0 = 23.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 12.72
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__SPRI__83_EC (
	 V_0 = 0.98499995,
	 P_0 = 20.0,
	 Q_0 = 10.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 28.42
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__STER__36_EC (
	 V_0 = 0.98,
	 P_0 = 31.0,
	 Q_0 = 17.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 10.87
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__STHP__75_EC (
	 V_0 = 0.967,
	 P_0 = 47.0,
	 Q_0 = 11.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 22.91
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__SUMM__67_EC (
	 V_0 = 1.02,
	 P_0 = 28.0,
	 Q_0 = 7.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 24.84
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__SUND__97_EC (
	 V_0 = 1.011,
	 P_0 = 15.0,
	 Q_0 = 9.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 27.88
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__SUNN__56_EC (
	 V_0 = 0.95399994,
	 P_0 = 84.0,
	 Q_0 = 18.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 15.16
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__SWIT__94_EC (
	 V_0 = 0.991,
	 P_0 = 30.0,
	 Q_0 = 16.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 28.64
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__SWKA__60_EC (
	 V_0 = 0.993,
	 P_0 = 78.0,
	 Q_0 = 3.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 23.15
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__TAZE__93_EC (
	 V_0 = 0.9869999,
	 P_0 = 12.0,
	 Q_0 = 7.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 30.79
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__TIDD__59_EC (
	 V_0 = 0.98499995,
	 P_0 = 277.0,
	 Q_0 = 113.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 19.37
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__TORR__54_EC (
	 V_0 = 0.9549999,
	 P_0 = 113.0,
	 Q_0 = 32.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 15.26
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__TURN__77_EC (
	 V_0 = 1.006,
	 P_0 = 61.0,
	 Q_0 = 28.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 26.72
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__TWIN__12_EC (
	 V_0 = 0.98999995,
	 P_0 = 47.0,
	 Q_0 = 10.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 12.2
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__W_LA__46_EC (
	 V_0 = 1.005,
	 P_0 = 28.0,
	 Q_0 = 10.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 18.49
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__WAGE__55_EC (
	 V_0 = 0.952,
	 P_0 = 63.0,
	 Q_0 = 22.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 14.97
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__WCAM__50_EC (
	 V_0 = 1.001,
	 P_0 = 17.0,
	 Q_0 = 4.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 18.9
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__WEST__35_EC (
	 V_0 = 0.98100007,
	 P_0 = 33.0,
	 Q_0 = 9.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 10.87
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__WEST__40_EC (
	 V_0 = 0.97,
	 P_0 = 20.0,
	 Q_0 = 23.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 7.35
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__WHUN_118_EC (
	 V_0 = 0.94900006,
	 P_0 = 33.0,
	 Q_0 = 15.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 21.92
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__WMED_114_EC (
	 V_0 = 0.96,
	 P_0 = 8.0,
	 Q_0 = 3.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 14.46
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__WMVE__44_EC (
	 V_0 = 0.98499995,
	 P_0 = 16.0,
	 Q_0 = 8.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 13.82
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__WNWP__57_EC (
	 V_0 = 0.971,
	 P_0 = 12.0,
	 Q_0 = 3.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 16.36
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__WNWP__58_EC (
	 V_0 = 0.959,
	 P_0 = 12.0,
	 Q_0 = 3.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 15.51
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__WOOS__53_EC (
	 V_0 = 0.94600004,
	 P_0 = 23.0,
	 Q_0 = 11.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 14.35
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__WYTH_101_EC (
	 V_0 = 0.993,
	 P_0 = 22.0,
	 Q_0 = 15.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 29.61
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__ZANE__48_EC (
	 V_0 = 1.0209999,
	 P_0 = 20.0,
	 Q_0 = 11.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 19.93
	 ) annotation (Placement(transformation()));

// TAP CHANGER TRANSFORMERS
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__EAST__38_EAST__37_1_PT (
	 r = 1.0638299,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.03750002371666437
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__KAMM__64_W_KA__61_1_PT (
	 r = 1.0204082,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.026800003804922153
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__KANA__81_CABI__80_1_PT (
	 r = 1.0638299,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.036999957848436515
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__MUSK__65_MUSK__66_1_PT (
	 r = 1.0638299,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.036999957848436515
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__OLIV___8_OLIV___5_1_PT (
	 r = 1.0204082,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.0267000220398321
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__SORE__30_SORE__17_1_PT (
	 r = 1.0416667,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.03880000459975091
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__SPOR__68_SPOR__69_1_PT (
	 r = 1.0638299,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.036999957848436515
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__TANN__26_TANN__25_1_PT (
	 r = 1.0416667,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.038199960171387645
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.Eurostag.PwPhaseTransformer trafo__TIDD__63_TIDD__59_1_PT (
	 r = 1.0416667,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.0,
	 X = 0.038599960945704684
	 ) annotation (Placement(transformation()));

// LINES
  iPSL.Electrical.Branches.PwLine_2 line__ADAM__20_JAY___21_1_AC (
	 R = 0.01829999,
	 X = 0.08490023,
	 G = 0.0,
	 B = 0.010800043
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BAIL__96_SUND__97_1_AC (
	 R = 0.01729999,
	 X = 0.08849979,
	 G = 0.0,
	 B = 0.012000005
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BEAV__85_CLIN__89_1_AC (
	 R = 0.02390002,
	 X = 0.17299989,
	 G = 0.0,
	 B = 0.02350001
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BEAV__85_FREM__88_1_AC (
	 R = 0.02,
	 X = 0.1020001,
	 G = 0.0,
	 B = 0.013800044
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BEAV__85_HAZA__86_1_AC (
	 R = 0.035,
	 X = 0.1229999,
	 G = 0.0,
	 B = 0.013800044
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BELL__74_STHP__75_1_AC (
	 R = 0.012299989,
	 X = 0.04059998,
	 G = 0.0,
	 B = 0.0051699984
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BEQU___9_BREE__10_1_AC (
	 R = 0.0025799957,
	 X = 0.032199956,
	 G = 0.0,
	 B = 0.61500216
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BETS__84_BEAV__85_1_AC (
	 R = 0.03020001,
	 X = 0.064099975,
	 G = 0.0,
	 B = 0.006169999
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BLAI_108_FRAN_109_1_AC (
	 R = 0.0105,
	 X = 0.02879999,
	 G = 0.0,
	 B = 0.003800002
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__BRAD__98_GLEN_100_1_AC (
	 R = 0.03970001,
	 X = 0.1790002,
	 G = 0.0,
	 B = 0.023799956
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__CABI__80_BAIL__96_1_AC (
	 R = 0.03559998,
	 X = 0.1820001,
	 G = 0.0,
	 B = 0.024699973
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__CABI__80_BRAD__98_1_AC (
	 R = 0.023799991,
	 X = 0.10799989,
	 G = 0.0,
	 B = 0.014300044
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__CABI__80_HINT__99_1_AC (
	 R = 0.04540002,
	 X = 0.20599978,
	 G = 0.0,
	 B = 0.027299954
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__CABI__80_SUND__97_1_AC (
	 R = 0.01829999,
	 X = 0.09340002,
	 G = 0.0,
	 B = 0.012699967
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__CALD__95_BAIL__96_1_AC (
	 R = 0.017099978,
	 X = 0.054700166,
	 G = 0.0,
	 B = 0.00737
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__CAPI__79_CABI__80_1_AC (
	 R = 0.015599979,
	 X = 0.07040013,
	 G = 0.0,
	 B = 0.009350004
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__CHEM__78_CAPI__79_1_AC (
	 R = 0.0054599876,
	 X = 0.02440002,
	 G = 0.0,
	 B = 0.0032400033
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__CLAY_103_FIEL_110_1_AC (
	 R = 0.03906002,
	 X = 0.18130013,
	 G = 0.0,
	 B = 0.02305
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__CLAY_103_HANC_104_1_AC (
	 R = 0.04659998,
	 X = 0.15840003,
	 G = 0.0,
	 B = 0.020350039
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__CLAY_103_ROAN_105_1_AC (
	 R = 0.05349979,
	 X = 0.16250001,
	 G = 0.0,
	 B = 0.020400027
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__CLIN__89_HOLS__90_1_AC (
	 R = 0.05179999,
	 X = 0.18799989,
	 G = 0.0,
	 B = 0.026400032
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__CLIN__89_HOLS__90_2_AC (
	 R = 0.023799991,
	 X = 0.09970017,
	 G = 0.0,
	 B = 0.05300002
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__CLIN__89_SALT__92_1_AC (
	 R = 0.00990002,
	 X = 0.050499998,
	 G = 0.0,
	 B = 0.027400034
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__CLIN__89_SALT__92_2_AC (
	 R = 0.03929999,
	 X = 0.15810019,
	 G = 0.0,
	 B = 0.020699972
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__CLOV_106_REUS_107_1_AC (
	 R = 0.052999895,
	 X = 0.18299988,
	 G = 0.0,
	 B = 0.023599992
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__COLL__23_DELA__32_1_AC (
	 R = 0.03170001,
	 X = 0.115299836,
	 G = 0.0,
	 B = 0.05865
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__COLL__23_TANN__25_1_AC (
	 R = 0.015599979,
	 X = 0.08,
	 G = 0.0,
	 B = 0.043199982
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__COLL__23_TREN__24_1_AC (
	 R = 0.0135,
	 X = 0.04920001,
	 G = 0.0,
	 B = 0.024900032
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__CONC__13_FTWA__15_1_AC (
	 R = 0.074399814,
	 X = 0.2443998,
	 G = 0.0,
	 B = 0.031340044
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__CROO__47_PHIL__49_1_AC (
	 R = 0.019099979,
	 X = 0.0625,
	 G = 0.0,
	 B = 0.00802
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__CROO__47_SPOR__69_1_AC (
	 R = 0.084399804,
	 X = 0.27779984,
	 G = 0.0,
	 B = 0.035460025
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__DARR__76_TURN__77_1_AC (
	 R = 0.044400018,
	 X = 0.1479999,
	 G = 0.0,
	 B = 0.018400028
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__DARR__76_WHUN_118_1_AC (
	 R = 0.01640002,
	 X = 0.054399814,
	 G = 0.0,
	 B = 0.0067799976
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__DEER__31_DELA__32_1_AC (
	 R = 0.029799988,
	 X = 0.09849979,
	 G = 0.0,
	 B = 0.012549996
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__DELA__32_DEER_113_1_AC (
	 R = 0.06150021,
	 X = 0.2029999,
	 G = 0.0,
	 B = 0.025900032
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__DELA__32_WMED_114_1_AC (
	 R = 0.0135,
	 X = 0.06119985,
	 G = 0.0,
	 B = 0.008139996
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__EAST__37_NWLI__39_1_AC (
	 R = 0.03209998,
	 X = 0.10599979,
	 G = 0.0,
	 B = 0.013500006
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__EAST__37_WEST__40_1_AC (
	 R = 0.059300043,
	 X = 0.1679999,
	 G = 0.0,
	 B = 0.021000009
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__EAST__38_MUSK__65_1_AC (
	 R = 0.00901004,
	 X = 0.0986003,
	 G = 0.0,
	 B = 0.523
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__FIEL_110_DANR_111_1_AC (
	 R = 0.022,
	 X = 0.07549989,
	 G = 0.0,
	 B = 0.010000004
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__FIEL_110_DANV_112_1_AC (
	 R = 0.024700008,
	 X = 0.06400021,
	 G = 0.0,
	 B = 0.031000014
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__FRAN_109_FIEL_110_1_AC (
	 R = 0.027799988,
	 X = 0.07619985,
	 G = 0.0,
	 B = 0.010099986
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__FREM__88_CLIN__89_1_AC (
	 R = 0.013900021,
	 X = 0.07119986,
	 G = 0.0,
	 B = 0.009669972
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__FTWA__15_HAVI__33_1_AC (
	 R = 0.038,
	 X = 0.12439981,
	 G = 0.0,
	 B = 0.015970012
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__FTWA__15_LINC__19_1_AC (
	 R = 0.012,
	 X = 0.03940002,
	 G = 0.0,
	 B = 0.005050002
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__FTWA__15_SORE__17_1_AC (
	 R = 0.01320001,
	 X = 0.043700013,
	 G = 0.0,
	 B = 0.022199972
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__GLEN_100_CLAY_103_1_AC (
	 R = 0.015999999,
	 X = 0.052500002,
	 G = 0.0,
	 B = 0.026799954
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__GLEN_100_CLOV_106_1_AC (
	 R = 0.06049989,
	 X = 0.22900021,
	 G = 0.0,
	 B = 0.031000014
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__GLEN_100_HANC_104_1_AC (
	 R = 0.045099977,
	 X = 0.2040002,
	 G = 0.0,
	 B = 0.027050002
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__GLEN_100_WYTH_101_1_AC (
	 R = 0.027700009,
	 X = 0.12619986,
	 G = 0.0,
	 B = 0.016400026
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__GOSH__14_FTWA__15_1_AC (
	 R = 0.059500102,
	 X = 0.195,
	 G = 0.0,
	 B = 0.025099993
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__GRAN__29_DEER__31_1_AC (
	 R = 0.01079999,
	 X = 0.03309998,
	 G = 0.0,
	 B = 0.004150002
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__HANC_104_ROAN_105_1_AC (
	 R = 0.009939981,
	 X = 0.037799988,
	 G = 0.0,
	 B = 0.0049299966
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__HAVI__33_EAST__37_1_AC (
	 R = 0.041500002,
	 X = 0.1420001,
	 G = 0.0,
	 B = 0.018300047
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__HAZA__86_PINE__87_1_AC (
	 R = 0.028279984,
	 X = 0.20740022,
	 G = 0.0,
	 B = 0.022249961
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__HICK___3_OLIV___5_1_AC (
	 R = 0.02409998,
	 X = 0.10799989,
	 G = 0.0,
	 B = 0.014199968
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__HICK___3_TWIN__12_1_AC (
	 R = 0.048400022,
	 X = 0.16,
	 G = 0.0,
	 B = 0.020300047
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__HINT__99_GLEN_100_1_AC (
	 R = 0.018000001,
	 X = 0.08130015,
	 G = 0.0,
	 B = 0.010800043
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__HOLS__90_HOLS__91_1_AC (
	 R = 0.025400022,
	 X = 0.08360008,
	 G = 0.0,
	 B = 0.010699967
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__HOLS__91_SALT__92_1_AC (
	 R = 0.03870001,
	 X = 0.12720017,
	 G = 0.0,
	 B = 0.016340038
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__HOWA__42_PHIL__49_1_AC (
	 R = 0.07150021,
	 X = 0.3229999,
	 G = 0.0,
	 B = 0.04300002
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__HOWA__42_PHIL__49_2_AC (
	 R = 0.07150021,
	 X = 0.3229999,
	 G = 0.0,
	 B = 0.04300002
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__JACK___7_TWIN__12_1_AC (
	 R = 0.008619986,
	 X = 0.033999998,
	 G = 0.0,
	 B = 0.004369998
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__JAY___21_RAND__22_1_AC (
	 R = 0.02090002,
	 X = 0.09700011,
	 G = 0.0,
	 B = 0.012300043
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__KAMM__64_MUSK__65_1_AC (
	 R = 0.002689998,
	 X = 0.030199958,
	 G = 0.0,
	 B = 0.19000019
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__KANK___6_JACK___7_1_AC (
	 R = 0.004590002,
	 X = 0.020799989,
	 G = 0.0,
	 B = 0.0027500014
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__LINC__19_ADAM__20_1_AC (
	 R = 0.02520001,
	 X = 0.1170001,
	 G = 0.0,
	 B = 0.014900026
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__LINC__19_ROCK__34_1_AC (
	 R = 0.075200066,
	 X = 0.2470001,
	 G = 0.0,
	 B = 0.031599995
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__LOGA__82_BAIL__96_1_AC (
	 R = 0.01620001,
	 X = 0.052999895,
	 G = 0.0,
	 B = 0.027199974
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__LOGA__82_SPRI__83_1_AC (
	 R = 0.011200011,
	 X = 0.03665002,
	 G = 0.0,
	 B = 0.018980013
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__MADI__27_DELA__32_1_AC (
	 R = 0.02290002,
	 X = 0.07549989,
	 G = 0.0,
	 B = 0.00962998
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__MADI__27_MEDF_115_1_AC (
	 R = 0.01640002,
	 X = 0.07409998,
	 G = 0.0,
	 B = 0.009860031
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__MADI__27_MULL__28_1_AC (
	 R = 0.019130014,
	 X = 0.0854999,
	 G = 0.0,
	 B = 0.010800043
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__MCKI__18_LINC__19_1_AC (
	 R = 0.011189981,
	 X = 0.04929999,
	 G = 0.0,
	 B = 0.0057100006
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__MULL__28_GRAN__29_1_AC (
	 R = 0.02370001,
	 X = 0.09430004,
	 G = 0.0,
	 B = 0.0119000245
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__MUSK__65_SPOR__68_1_AC (
	 R = 0.0013799957,
	 X = 0.016,
	 G = 0.0,
	 B = 0.3190001
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__MUSK__66_SUMM__67_1_AC (
	 R = 0.022400022,
	 X = 0.10150021,
	 G = 0.0,
	 B = 0.0134100225
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N_NE__45_PHIL__49_1_AC (
	 R = 0.06840002,
	 X = 0.18599978,
	 G = 0.0,
	 B = 0.022199972
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N_NE__45_W_LA__46_1_AC (
	 R = 0.04,
	 X = 0.1356002,
	 G = 0.0,
	 B = 0.016599989
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__N__E__16_SORE__17_1_AC (
	 R = 0.04540002,
	 X = 0.18009976,
	 G = 0.0,
	 B = 0.023299955
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__NATR__62_MUSK__66_1_AC (
	 R = 0.048200008,
	 X = 0.21799989,
	 G = 0.0,
	 B = 0.028900031
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__NATR__62_SUMM__67_1_AC (
	 R = 0.02579999,
	 X = 0.1170001,
	 G = 0.0,
	 B = 0.015500007
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__NEWC__51_SCOS__52_1_AC (
	 R = 0.02029999,
	 X = 0.058800146,
	 G = 0.0,
	 B = 0.0069799973
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__NEWC__51_WNWP__58_1_AC (
	 R = 0.025499998,
	 X = 0.07189981,
	 G = 0.0,
	 B = 0.008939996
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__NPOR__71_HILL__72_1_AC (
	 R = 0.044599976,
	 X = 0.18,
	 G = 0.0,
	 B = 0.022219969
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__NPOR__71_SARG__73_1_AC (
	 R = 0.008659998,
	 X = 0.04540002,
	 G = 0.0,
	 B = 0.005890005
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__NWCA___4_OLIV___5_1_AC (
	 R = 0.0017599978,
	 X = 0.007979994,
	 G = 0.0,
	 B = 0.0010500005
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__NWCA___4_SOUT__11_1_AC (
	 R = 0.02090002,
	 X = 0.068800144,
	 G = 0.0,
	 B = 0.008739996
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__NWLI__39_WEST__40_1_AC (
	 R = 0.01840002,
	 X = 0.06049989,
	 G = 0.0,
	 B = 0.0077600013
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__OLIV___5_KANK___6_1_AC (
	 R = 0.01190002,
	 X = 0.05400021,
	 G = 0.0,
	 B = 0.0071299975
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__OLIV___5_SOUT__11_1_AC (
	 R = 0.02029999,
	 X = 0.068199955,
	 G = 0.0,
	 B = 0.008689996
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__OLIV___8_BEQU___9_1_AC (
	 R = 0.0024400002,
	 X = 0.03049998,
	 G = 0.0,
	 B = 0.58099973
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__OLIV___8_SORE__30_1_AC (
	 R = 0.004310002,
	 X = 0.0504,
	 G = 0.0,
	 B = 0.25699997
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__PHIL__49_MUSK__66_1_AC (
	 R = 0.018000001,
	 X = 0.09189981,
	 G = 0.0,
	 B = 0.012400025
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__PHIL__49_MUSK__66_2_AC (
	 R = 0.018000001,
	 X = 0.09189981,
	 G = 0.0,
	 B = 0.012400025
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__PHIL__49_NEWC__51_1_AC (
	 R = 0.048599977,
	 X = 0.1370001,
	 G = 0.0,
	 B = 0.01709999
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__PHIL__49_SPOR__69_1_AC (
	 R = 0.09849979,
	 X = 0.3240002,
	 G = 0.0,
	 B = 0.041400038
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__PHIL__49_TORR__54_1_AC (
	 R = 0.072999895,
	 X = 0.2890002,
	 G = 0.0,
	 B = 0.036900036
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__PHIL__49_TORR__54_2_AC (
	 R = 0.08689981,
	 X = 0.29099977,
	 G = 0.0,
	 B = 0.036500018
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__PHIL__49_WCAM__50_1_AC (
	 R = 0.02670001,
	 X = 0.075200066,
	 G = 0.0,
	 B = 0.009370001
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__POKA___2_TWIN__12_1_AC (
	 R = 0.01870001,
	 X = 0.061599977,
	 G = 0.0,
	 B = 0.007860002
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__PORT__70_BELL__74_1_AC (
	 R = 0.04009998,
	 X = 0.13229993,
	 G = 0.0,
	 B = 0.016840037
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__PORT__70_NPOR__71_1_AC (
	 R = 0.008819995,
	 X = 0.0355,
	 G = 0.0,
	 B = 0.004390004
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__PORT__70_STHP__75_1_AC (
	 R = 0.04279999,
	 X = 0.14099978,
	 G = 0.0,
	 B = 0.018000009
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__RAND__22_COLL__23_1_AC (
	 R = 0.03420001,
	 X = 0.15900022,
	 G = 0.0,
	 B = 0.020199971
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__RIVE___1_HICK___3_1_AC (
	 R = 0.012900021,
	 X = 0.04240002,
	 G = 0.0,
	 B = 0.0054100007
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__RIVE___1_POKA___2_1_AC (
	 R = 0.03029999,
	 X = 0.09990023,
	 G = 0.0,
	 B = 0.012699967
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__ROAN_105_BLAI_108_1_AC (
	 R = 0.026099978,
	 X = 0.070299834,
	 G = 0.0,
	 B = 0.00922
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__ROAN_105_CLOV_106_1_AC (
	 R = 0.014,
	 X = 0.054700166,
	 G = 0.0,
	 B = 0.0071699996
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__ROAN_105_REUS_107_1_AC (
	 R = 0.052999895,
	 X = 0.18299988,
	 G = 0.0,
	 B = 0.023599992
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__ROCK__34_EAST__37_1_AC (
	 R = 0.0025599978,
	 X = 0.009400021,
	 G = 0.0,
	 B = 0.0049199983
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__ROCK__34_S_KE__43_1_AC (
	 R = 0.041299988,
	 X = 0.1681002,
	 G = 0.0,
	 B = 0.021129984
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__ROCK__34_STER__36_1_AC (
	 R = 0.008709988,
	 X = 0.026799988,
	 G = 0.0,
	 B = 0.0028400032
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__S_KE__43_WMVE__44_1_AC (
	 R = 0.06080025,
	 X = 0.24540013,
	 G = 0.0,
	 B = 0.030340046
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__S_TI__41_HOWA__42_1_AC (
	 R = 0.041,
	 X = 0.135,
	 G = 0.0,
	 B = 0.017199969
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SALT__92_GLEN_100_1_AC (
	 R = 0.064799935,
	 X = 0.295,
	 G = 0.0,
	 B = 0.023599992
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SALT__92_SMYT_102_1_AC (
	 R = 0.012299989,
	 X = 0.055900022,
	 G = 0.0,
	 B = 0.00732
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SALT__92_SWIT__94_1_AC (
	 R = 0.04809998,
	 X = 0.15799989,
	 G = 0.0,
	 B = 0.020300047
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SALT__92_TAZE__93_1_AC (
	 R = 0.02579999,
	 X = 0.08479993,
	 G = 0.0,
	 B = 0.010900024
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SCOS__52_WOOS__53_1_AC (
	 R = 0.0405,
	 X = 0.16349979,
	 G = 0.0,
	 B = 0.020289954
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SORE__17_DEER_113_1_AC (
	 R = 0.009130015,
	 X = 0.03009998,
	 G = 0.0,
	 B = 0.0038400036
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SORE__17_DEER__31_1_AC (
	 R = 0.04740002,
	 X = 0.15630014,
	 G = 0.0,
	 B = 0.01995002
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SORE__17_MCKI__18_1_AC (
	 R = 0.012299989,
	 X = 0.050499998,
	 G = 0.0,
	 B = 0.006489995
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SORE__30_EAST__38_1_AC (
	 R = 0.00464,
	 X = 0.053999998,
	 G = 0.0,
	 B = 0.21099979
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SOUT__11_CONC__13_1_AC (
	 R = 0.022249999,
	 X = 0.07310019,
	 G = 0.0,
	 B = 0.009379999
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SOUT__11_TWIN__12_1_AC (
	 R = 0.00595001,
	 X = 0.019599978,
	 G = 0.0,
	 B = 0.002509999
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SPOR__68_KANA__81_1_AC (
	 R = 0.0017500021,
	 X = 0.020200042,
	 G = 0.0,
	 B = 0.404
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SPOR__68_KYGE_116_1_AC (
	 R = 3.4E-4,
	 X = 0.004049998,
	 G = 0.0,
	 B = 0.0819999
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SPOR__69_PORT__70_1_AC (
	 R = 0.03,
	 X = 0.12700011,
	 G = 0.0,
	 B = 0.061000027
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SPOR__69_STHP__75_1_AC (
	 R = 0.0405,
	 X = 0.122000106,
	 G = 0.0,
	 B = 0.06200003
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SPOR__69_TURN__77_1_AC (
	 R = 0.030900022,
	 X = 0.10099979,
	 G = 0.0,
	 B = 0.05190004
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SPRI__83_BEAV__85_1_AC (
	 R = 0.042999998,
	 X = 0.1479999,
	 G = 0.0,
	 B = 0.017400028
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SPRI__83_BETS__84_1_AC (
	 R = 0.0625,
	 X = 0.1320001,
	 G = 0.0,
	 B = 0.012900026
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__STHP__75_TURN__77_1_AC (
	 R = 0.06009977,
	 X = 0.19990022,
	 G = 0.0,
	 B = 0.024890034
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__STHP__75_WHUN_118_1_AC (
	 R = 0.0145,
	 X = 0.04809998,
	 G = 0.0,
	 B = 0.0059900046
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SUNN__56_TIDD__59_1_AC (
	 R = 0.082499996,
	 X = 0.25099978,
	 G = 0.0,
	 B = 0.028450022
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SUNN__56_TIDD__59_2_AC (
	 R = 0.08029983,
	 X = 0.23900022,
	 G = 0.0,
	 B = 0.026799954
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SUNN__56_WNWP__57_1_AC (
	 R = 0.03429999,
	 X = 0.096599974,
	 G = 0.0,
	 B = 0.012099987
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SUNN__56_WNWP__58_1_AC (
	 R = 0.03429999,
	 X = 0.096599974,
	 G = 0.0,
	 B = 0.012099987
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SWIT__94_BAIL__96_1_AC (
	 R = 0.02690002,
	 X = 0.08689981,
	 G = 0.0,
	 B = 0.011500006
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SWIT__94_CALD__95_1_AC (
	 R = 0.01320001,
	 X = 0.04340002,
	 G = 0.0,
	 B = 0.0055500027
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SWIT__94_GLEN_100_1_AC (
	 R = 0.01779999,
	 X = 0.057999894,
	 G = 0.0,
	 B = 0.030199977
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SWKA__60_NATR__62_1_AC (
	 R = 0.012299989,
	 X = 0.05610008,
	 G = 0.0,
	 B = 0.007339996
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__SWKA__60_W_KA__61_1_AC (
	 R = 0.0026400022,
	 X = 0.0135,
	 G = 0.0,
	 B = 0.007279997
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__TANN__25_MADI__27_1_AC (
	 R = 0.03179999,
	 X = 0.1629999,
	 G = 0.0,
	 B = 0.0882
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__TANN__26_SORE__30_1_AC (
	 R = 0.007990003,
	 X = 0.08600042,
	 G = 0.0,
	 B = 0.45400003
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__TAZE__93_SWIT__94_1_AC (
	 R = 0.022299988,
	 X = 0.07319996,
	 G = 0.0,
	 B = 0.009379999
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__TIDD__59_SWKA__60_1_AC (
	 R = 0.03170001,
	 X = 0.145,
	 G = 0.0,
	 B = 0.018800046
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__TIDD__59_W_KA__61_1_AC (
	 R = 0.03279999,
	 X = 0.14999999,
	 G = 0.0,
	 B = 0.019400027
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__TIDD__63_KAMM__64_1_AC (
	 R = 0.0017200001,
	 X = 0.02,
	 G = 0.0,
	 B = 0.10799971
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__TORR__54_SUNN__56_1_AC (
	 R = 0.00275,
	 X = 0.009549989,
	 G = 0.0,
	 B = 0.00366
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__TORR__54_TIDD__59_1_AC (
	 R = 0.05029999,
	 X = 0.22930004,
	 G = 0.0,
	 B = 0.029900031
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__TORR__54_WAGE__55_1_AC (
	 R = 0.016900022,
	 X = 0.07069996,
	 G = 0.0,
	 B = 0.010099986
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__TREN__24_HILL__72_1_AC (
	 R = 0.04879999,
	 X = 0.19599979,
	 G = 0.0,
	 B = 0.024400031
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__TREN__24_PORT__70_1_AC (
	 R = 0.002209998,
	 X = 0.4115002,
	 G = 0.0,
	 B = 0.050990023
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__TURN__77_CABI__80_1_AC (
	 R = 0.016999999,
	 X = 0.048499998,
	 G = 0.0,
	 B = 0.023599992
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__TURN__77_CABI__80_2_AC (
	 R = 0.02940002,
	 X = 0.105000004,
	 G = 0.0,
	 B = 0.011400024
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__TURN__77_CHEM__78_1_AC (
	 R = 0.003759998,
	 X = 0.012400021,
	 G = 0.0,
	 B = 0.006319999
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__TURN__77_LOGA__82_1_AC (
	 R = 0.029799988,
	 X = 0.08529983,
	 G = 0.0,
	 B = 0.040870044
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__TWIN__12_CORE_117_1_AC (
	 R = 0.03290002,
	 X = 0.14,
	 G = 0.0,
	 B = 0.017900027
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__TWIN__12_GOSH__14_1_AC (
	 R = 0.021499999,
	 X = 0.07069996,
	 G = 0.0,
	 B = 0.009079998
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__TWIN__12_N__E__16_1_AC (
	 R = 0.02120001,
	 X = 0.08340002,
	 G = 0.0,
	 B = 0.010699967
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__W_KA__61_NATR__62_1_AC (
	 R = 0.008240023,
	 X = 0.03759998,
	 G = 0.0,
	 B = 0.0049000024
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__W_LA__46_CROO__47_1_AC (
	 R = 0.038,
	 X = 0.12700011,
	 G = 0.0,
	 B = 0.015800046
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__W_LA__46_ZANE__48_1_AC (
	 R = 0.06009977,
	 X = 0.1890002,
	 G = 0.0,
	 B = 0.023599992
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__WAGE__55_SUNN__56_1_AC (
	 R = 0.004879999,
	 X = 0.015099978,
	 G = 0.0,
	 B = 0.0018699971
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__WAGE__55_TIDD__59_1_AC (
	 R = 0.04738999,
	 X = 0.21580026,
	 G = 0.0,
	 B = 0.028229969
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__WCAM__50_WNWP__57_1_AC (
	 R = 0.04740002,
	 X = 0.1340002,
	 G = 0.0,
	 B = 0.016599989
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__WEST__35_EAST__37_1_AC (
	 R = 0.011,
	 X = 0.04970001,
	 G = 0.0,
	 B = 0.006590005
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__WEST__35_STER__36_1_AC (
	 R = 0.0022400022,
	 X = 0.010200011,
	 G = 0.0,
	 B = 0.0013400025
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__WEST__40_HOWA__42_1_AC (
	 R = 0.055499893,
	 X = 0.18299988,
	 G = 0.0,
	 B = 0.023299955
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__WEST__40_S_TI__41_1_AC (
	 R = 0.0145,
	 X = 0.048700012,
	 G = 0.0,
	 B = 0.006110001
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__WMED_114_MEDF_115_1_AC (
	 R = 0.0023,
	 X = 0.0104000205,
	 G = 0.0,
	 B = 0.0013800045
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__WMVE__44_N_NE__45_1_AC (
	 R = 0.022400022,
	 X = 0.09009977,
	 G = 0.0,
	 B = 0.011199967
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__WOOS__53_TORR__54_1_AC (
	 R = 0.02629999,
	 X = 0.122000106,
	 G = 0.0,
	 B = 0.015500007
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__WYTH_101_SMYT_102_1_AC (
	 R = 0.024599979,
	 X = 0.1120001,
	 G = 0.0,
	 B = 0.0146999685
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line__ZANE__48_PHIL__49_1_AC (
	 R = 0.01790002,
	 X = 0.050499998,
	 G = 0.0,
	 B = 0.0062900046
	 ) annotation (Placement(transformation()));

// CAPACITORS
  iPSL.Electrical.Banks.PwCapacitorBank cap__BELL__74_SC (
	 B = 0.12000005091307685,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap__CAPI__79_SC (
	 B = 0.20000008116010576,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap__EAST__37_SC (
	 B = -0.25000011807773265,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap__FIEL_110_SC (
	 B = 0.060000025456538424,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap__LOGA__82_SC (
	 B = 0.20000008116010576,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap__N_NE__45_SC (
	 B = 0.10000004058005288,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap__OLIV___5_SC (
	 B = -0.4000001623202115,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap__REUS_107_SC (
	 B = 0.060000025456538424,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap__ROAN_105_SC (
	 B = 0.20000008116010576,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap__ROCK__34_SC (
	 B = 0.14000006124610082,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap__SPRI__83_SC (
	 B = 0.10000004058005288,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap__W_LA__46_SC (
	 B = 0.10000004058005288,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap__WMVE__44_SC (
	 B = 0.10000004058005288,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap__ZANE__48_SC (
	 B = 0.1500000664126128,
	 nsteps = 1
	 ) annotation (Placement(transformation()));

// FIXED INJECTIONS

// GENERATORS
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN__100_SM (
	 SNREF = SNREF, 
	 ur0 = 0.8977075490129279, 
	 ui0 = 0.47792266631193964, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.8528993367303587, 
	 init_omega = 1.0, 
	 init_efd = 0.3212865492524267, 
	 WLMDVPu = 0.7304917626978259, 
	 init_lambdaad = -0.9697385366962621, 
	 init_cm = 0.1638190975222241, 
	 init_lambdaq1 = 0.3125043446294383, 
	 init_lambdaq2 = 0.3125043446294383, 
	 init_iq = 2.315805405765683, 
	 init_id = 0.8814282749673303, 
	 init_lambdaaq = 0.3125043446294383, 
	 init_lambdad = -0.9697385366962621, 
	 init_lambdaf = -1.105808453170896,
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
	 U2N = 138.0,
	 SNtfo = 1710.0,
	 lStatIn = 0.265,
	 XSQ = 0.391,
	 snq = 9.285,
	 XQ = 2.72,
	 PN = 1539.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN__103_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9113054770150611, 
	 ui0 = 0.41415386164552076, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.4893354357682955, 
	 init_omega = 1.0, 
	 init_efd = 0.2910939393773114, 
	 WLMDVPu = 0.7304917626978259, 
	 init_lambdaad = -0.999639734109446, 
	 init_cm = 0.02599289032340846, 
	 init_lambdaq1 = 0.05429060539858881, 
	 init_lambdaq2 = 0.05429060539858881, 
	 init_iq = 0.398813237325655, 
	 init_id = 0.02506922786582994, 
	 init_lambdaaq = 0.05429060539858881, 
	 init_lambdad = -0.999639734109446, 
	 init_lambdaf = -1.122922604523741,
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
	 U2N = 138.0,
	 SNtfo = 1710.0,
	 lStatIn = 0.265,
	 XSQ = 0.391,
	 snq = 9.285,
	 XQ = 2.72,
	 PN = 1539.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN__104_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9022503896499245, 
	 ui0 = 0.358866639484845, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.3785619139671325, 
	 init_omega = 1.0, 
	 init_efd = 0.2866157015426111, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -0.9710000157356262, 
	 init_cm = 0.0, 
	 init_lambdaq1 = -5.551115123125783e-17, 
	 init_lambdaq2 = -5.551115123125783e-17, 
	 init_iq = 0.0, 
	 init_id = 0.0, 
	 init_lambdaaq = -5.551115123125783e-17, 
	 init_lambdad = -0.9710000157356262, 
	 init_lambdaf = -1.088924298071663,
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
	 U2N = 138.0,
	 SNtfo = 1650.0,
	 lStatIn = 0.256,
	 XSQ = 0.377,
	 snq = 9.285,
	 XQ = 2.62,
	 PN = 1485.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN__105_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9034750843890293, 
	 ui0 = 0.33905415684219625, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.3590142130851747, 
	 init_omega = 1.0, 
	 init_efd = 0.2842607629535722, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -0.9649999737739564, 
	 init_cm = 0.0, 
	 init_lambdaq1 = 5.551115123125783e-17, 
	 init_lambdaq2 = 5.551115123125783e-17, 
	 init_iq = 0.0, 
	 init_id = 0.0, 
	 init_lambdaaq = 5.551115123125783e-17, 
	 init_lambdad = -0.9649999737739564, 
	 init_lambdaf = -1.081955347422984,
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
	 U2N = 138.0,
	 SNtfo = 1650.0,
	 lStatIn = 0.256,
	 XSQ = 0.377,
	 snq = 9.285,
	 XQ = 2.62,
	 PN = 1485.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN__107_SM (
	 SNREF = SNREF, 
	 ur0 = 0.907788534323596, 
	 ui0 = 0.28674730811975135, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = false, 
	 TX = 0, 
	 md = 0, 
	 XPQ = 0, 
	 TPQ0 = 0, 
	 mq = 0, 
	 snd = 0, 
	 snq = 0, 
	 init_theta = 0.2004716666439646, 
	 init_omega = 1.0, 
	 init_efd = 0.452783545823648, 
	 WLMDVPu = 0.6682075874254081, 
	 init_lambdaad = -0.948384634765252, 
	 init_cm = -0.09087378266977451, 
	 init_lambdaq1 = -0.08089239525209201, 
	 init_lambdaq2 = -0.08089239525209201, 
	 init_iq = -0.2298079410570797, 
	 init_id = 0.02433150618358471, 
	 init_lambdaaq = -0.08089239525209201, 
	 init_lambdad = -0.948384634765252, 
	 init_lambdaf = -1.109466957691519,
	 PNALT = 228.0,
	 IENR = 3,
	 DIn = 0.0,
	 HIn = 2.748,
	 TSD0 = 0.096,
	 XPD = 0.31,
	 RTfoPu = 0.0,
	 TPD0 = 8.4,
	 XTfoPu = 0.1,
	 rStatIn = 0.004,
	 U1N = 18.0,
	 TSQ0 = 0.1,
	 SN = 250.0,
	 V1 = 18.0,
	 XSD = 0.275,
	 XD = 1.53,
	 U2N = 138.0,
	 SNtfo = 250.0,
	 lStatIn = 0.11,
	 XSQ = 0.346,
	 XQ = 0.99,
	 PN = 242.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN__110_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9249045414727811, 
	 ui0 = 0.30212674439148457, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.3157300651073456, 
	 init_omega = 1.0, 
	 init_efd = 0.3182601408803978, 
	 WLMDVPu = 0.717262019773382, 
	 init_lambdaad = -0.9729999899864198, 
	 init_cm = 0.0, 
	 init_lambdaq1 = -5.551115123125783e-17, 
	 init_lambdaq2 = -5.551115123125783e-17, 
	 init_iq = 0.0, 
	 init_id = 0.0, 
	 init_lambdaaq = -5.551115123125783e-17, 
	 init_lambdad = -0.9729999899864198, 
	 init_lambdaf = -1.070090133028916,
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
	 U2N = 138.0,
	 SNtfo = 1120.0,
	 lStatIn = 0.219,
	 XSQ = 0.301,
	 snq = 5.57,
	 XQ = 2.57,
	 PN = 1008.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN__111_SM (
	 SNREF = SNREF, 
	 ur0 = 0.922410298458315, 
	 ui0 = 0.33099739981165394, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.4281836869463247, 
	 init_omega = 1.0, 
	 init_efd = 0.3226169107496577, 
	 WLMDVPu = 0.717262019773382, 
	 init_lambdaad = -0.977563800259424, 
	 init_cm = 0.03571855290933589, 
	 init_lambdaq1 = 0.07147054135891683, 
	 init_lambdaq2 = 0.07147054135891683, 
	 init_iq = 0.3660622835301587, 
	 init_id = 0.03069483265425733, 
	 init_lambdaaq = 0.07147054135891683, 
	 init_lambdad = -0.977563800259424, 
	 init_lambdaf = -1.075983042851238,
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
	 U2N = 138.0,
	 SNtfo = 1120.0,
	 lStatIn = 0.219,
	 XSQ = 0.301,
	 snq = 5.57,
	 XQ = 2.57,
	 PN = 1008.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN__112_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9418217354761206, 
	 ui0 = 0.2521841887913911, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.1898361238157184, 
	 init_omega = 1.0, 
	 init_efd = 0.2890264670431515, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -0.973086983451559, 
	 init_cm = -0.02895372051261449, 
	 init_lambdaq1 = -0.06043688651087418, 
	 init_lambdaq2 = -0.06043688651087418, 
	 init_iq = -0.4398896789269298, 
	 init_id = 0.03163347757003217, 
	 init_lambdaaq = -0.06043688651087418, 
	 init_lambdad = -0.973086983451559, 
	 init_lambdaf = -1.092003143727295,
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
	 U2N = 138.0,
	 SNtfo = 1650.0,
	 lStatIn = 0.256,
	 XSQ = 0.377,
	 snq = 9.285,
	 XQ = 2.62,
	 PN = 1485.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN__113_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9645838278048031, 
	 ui0 = 0.23585372876111246, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.2301947987383874, 
	 init_omega = 1.0, 
	 init_efd = 0.2871019667937185, 
	 WLMDVPu = 0.7304917626978259, 
	 init_lambdaad = -0.9929549125268872, 
	 init_cm = -0.003898590043621592, 
	 init_lambdaq1 = -0.008256209953888725, 
	 init_lambdaq2 = -0.008256209953888725, 
	 init_iq = -0.06042017042906463, 
	 init_id = 0.0005808631456323977, 
	 init_lambdaaq = -0.008256209953888725, 
	 init_lambdad = -0.9929549125268872, 
	 init_lambdaf = -1.114547119646119,
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
	 U2N = 138.0,
	 SNtfo = 1710.0,
	 lStatIn = 0.265,
	 XSQ = 0.391,
	 snq = 9.285,
	 XQ = 2.72,
	 PN = 1539.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN__116_SM (
	 SNREF = SNREF, 
	 ur0 = 0.894503996883412, 
	 ui0 = 0.45813490368577464, 
	 transformerIncluded = true, 
	 V2 = 345.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.1942972758195617, 
	 init_omega = 1.0, 
	 init_efd = 0.3053621487743324, 
	 WLMDVPu = 0.7304917626978259, 
	 init_lambdaad = -0.9765545315226769, 
	 init_cm = -0.1195164408126235, 
	 init_lambdaq1 = -0.239141734007813, 
	 init_lambdaq2 = -0.239141734007813, 
	 init_iq = -1.760031213849127, 
	 init_id = 0.5042681784544238, 
	 init_lambdaaq = -0.239141734007813, 
	 init_lambdad = -0.9765545315226769, 
	 init_lambdaf = -1.10588021345651,
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
	 U2N = 345.0,
	 SNtfo = 1710.0,
	 lStatIn = 0.265,
	 XSQ = 0.391,
	 snq = 9.285,
	 XQ = 2.72,
	 PN = 1539.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___10_SM (
	 SNREF = SNREF, 
	 ur0 = 0.8536490611582456, 
	 ui0 = 0.6113780992543362, 
	 transformerIncluded = true, 
	 V2 = 345.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 1.18022244747844, 
	 init_omega = 1.0, 
	 init_efd = 0.3888908890213766, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -0.9400485935515892, 
	 init_cm = 0.3032671801718116, 
	 init_lambdaq1 = 0.4786262602546215, 
	 init_lambdaq2 = 0.4786262602546215, 
	 init_iq = 3.634025608919973, 
	 init_id = 2.271828884560718, 
	 init_lambdaaq = 0.4786262602546215, 
	 init_lambdad = -0.9400485935515892, 
	 init_lambdaf = -1.100052664326655,
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
	 U2N = 345.0,
	 SNtfo = 1650.0,
	 lStatIn = 0.256,
	 XSQ = 0.377,
	 snq = 9.285,
	 XQ = 2.62,
	 PN = 1485.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___12_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9676416855820924, 
	 ui0 = 0.20921154172130269, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = false, 
	 TX = 0, 
	 md = 0, 
	 XPQ = 0, 
	 TPQ0 = 0, 
	 mq = 0, 
	 snd = 0, 
	 snq = 0, 
	 init_theta = 0.5739790054386907, 
	 init_omega = 1.0, 
	 init_efd = 0.5294879219751814, 
	 WLMDVPu = 0.6682075874254081, 
	 init_lambdaad = -0.9529339114317953, 
	 init_cm = 0.3517270543809101, 
	 init_lambdaq1 = 0.2827370124822131, 
	 init_lambdaq2 = 0.2827370124822131, 
	 init_iq = 0.8032301490971961, 
	 init_id = 0.3033003110905469, 
	 init_lambdaaq = 0.2827370124822131, 
	 init_lambdad = -0.9529339114317953, 
	 init_lambdaf = -1.141304591461727,
	 PNALT = 228.0,
	 IENR = 3,
	 DIn = 0.0,
	 HIn = 2.748,
	 TSD0 = 0.096,
	 XPD = 0.31,
	 RTfoPu = 0.0,
	 TPD0 = 8.4,
	 XTfoPu = 0.1,
	 rStatIn = 0.004,
	 U1N = 18.0,
	 TSQ0 = 0.1,
	 SN = 250.0,
	 V1 = 18.0,
	 XSD = 0.275,
	 XD = 1.53,
	 U2N = 138.0,
	 SNtfo = 250.0,
	 lStatIn = 0.11,
	 XSQ = 0.346,
	 XQ = 0.99,
	 PN = 242.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___15_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9514277491011294, 
	 ui0 = 0.1889055153885253, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = false, 
	 TX = 0, 
	 md = 0, 
	 XPQ = 0, 
	 TPQ0 = 0, 
	 mq = 0, 
	 snd = 0, 
	 snq = 0, 
	 init_theta = NaN, 
	 init_omega = NaN, 
	 init_efd = NaN, 
	 WLMDVPu = NaN, 
	 init_lambdaad = NaN, 
	 init_cm = NaN, 
	 init_lambdaq1 = NaN, 
	 init_lambdaq2 = NaN, 
	 init_iq = NaN, 
	 init_id = NaN, 
	 init_lambdaaq = NaN, 
	 init_lambdad = NaN, 
	 init_lambdaf = NaN,
	 PNALT = 245.7,
	 IENR = 3,
	 DIn = 0.0,
	 HIn = 3.595,
	 TSD0 = 0.03,
	 XPD = 0.37,
	 RTfoPu = 0.0,
	 TPD0 = 7.4,
	 XTfoPu = 0.1,
	 rStatIn = 0.005697,
	 U1N = 15.5,
	 TSQ0 = 0.08,
	 SN = 270.0,
	 V1 = 15.5,
	 XSD = 0.235,
	 XD = 1.61,
	 U2N = 138.0,
	 SNtfo = 270.0,
	 lStatIn = 0.17,
	 XSQ = 0.29,
	 XQ = 0.95,
	 PN = 252.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___18_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9533650279721573, 
	 ui0 = 0.1944841997521149, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.2012364566326141, 
	 init_omega = 1.0, 
	 init_efd = 0.2874090816220181, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -0.9729999899864197, 
	 init_cm = 0.0, 
	 init_lambdaq1 = -2.775557561562891e-17, 
	 init_lambdaq2 = -2.775557561562891e-17, 
	 init_iq = 0.0, 
	 init_id = 0.0, 
	 init_lambdaaq = -2.775557561562891e-17, 
	 init_lambdad = -0.9729999899864197, 
	 init_lambdaf = -1.091250698181124,
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
	 U2N = 138.0,
	 SNtfo = 1650.0,
	 lStatIn = 0.256,
	 XSQ = 0.377,
	 snq = 9.285,
	 XQ = 2.62,
	 PN = 1485.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___19_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9451462646020333, 
	 ui0 = 0.18457393529541127, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.1928588896989823, 
	 init_omega = 1.0, 
	 init_efd = 0.2834839784566625, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -0.9629999995231627, 
	 init_cm = 0.0, 
	 init_lambdaq1 = 2.775557561562891e-17, 
	 init_lambdaq2 = 2.775557561562891e-17, 
	 init_iq = 0.0, 
	 init_id = 0.0, 
	 init_lambdaaq = 2.775557561562891e-17, 
	 init_lambdad = -0.9629999995231627, 
	 init_lambdaf = -1.079635775348921,
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
	 U2N = 138.0,
	 SNtfo = 1650.0,
	 lStatIn = 0.256,
	 XSQ = 0.377,
	 snq = 9.285,
	 XQ = 2.62,
	 PN = 1485.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___24_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9267925818384506, 
	 ui0 = 0.35372231776308916, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.3351750110497198, 
	 init_omega = 1.0, 
	 init_efd = 0.3270804825935426, 
	 WLMDVPu = 0.717262019773382, 
	 init_lambdaad = -0.9916386424827281, 
	 init_cm = -0.01289628233029736, 
	 init_lambdaq1 = -0.02545248831298359, 
	 init_lambdaq2 = -0.02545248831298359, 
	 init_iq = -0.1309916633819531, 
	 init_id = 0.003855443741149371, 
	 init_lambdaaq = -0.02545248831298359, 
	 init_lambdad = -0.9916386424827281, 
	 init_lambdaf = -1.091419566206224,
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
	 U2N = 138.0,
	 SNtfo = 1120.0,
	 lStatIn = 0.219,
	 XSQ = 0.301,
	 snq = 5.57,
	 XQ = 2.57,
	 PN = 1008.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___25_SM (
	 SNREF = SNREF, 
	 ur0 = 0.927696475698244, 
	 ui0 = 0.4918121072536302, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.7827963051317185, 
	 init_omega = 1.0, 
	 init_efd = 0.4474582315510471, 
	 WLMDVPu = 0.6296504227510634, 
	 init_lambdaad = -1.020213288261835, 
	 init_cm = 0.2019278517603634, 
	 init_lambdaq1 = 0.2557558505404011, 
	 init_lambdaq2 = 0.2557558505404011, 
	 init_iq = 2.004529798850993, 
	 init_id = 0.609822400362712, 
	 init_lambdaaq = 0.2557558505404011, 
	 init_lambdad = -1.020213288261835, 
	 init_lambdaf = -1.179732098736511,
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
	 U2N = 138.0,
	 SNtfo = 1211.0,
	 lStatIn = 0.202,
	 XSQ = 0.262,
	 snq = 6.995,
	 XQ = 2.22,
	 PN = 1090.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___26_SM (
	 SNREF = SNREF, 
	 ur0 = 0.8815732093706765, 
	 ui0 = 0.5030443792355727, 
	 transformerIncluded = true, 
	 V2 = 345.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.9631304682790813, 
	 init_omega = 1.0, 
	 init_efd = 0.3331067854540082, 
	 WLMDVPu = 0.7304917626978259, 
	 init_lambdaad = -0.9452625012754243, 
	 init_cm = 0.2041476878096319, 
	 init_lambdaq1 = 0.3771832684621197, 
	 init_lambdaq2 = 0.3771832684621197, 
	 init_iq = 2.792854345624776, 
	 init_id = 1.330526756908419, 
	 init_lambdaaq = 0.3771832684621197, 
	 init_lambdad = -0.9452625012754243, 
	 init_lambdaf = -1.086338474045916,
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
	 U2N = 345.0,
	 SNtfo = 1710.0,
	 lStatIn = 0.265,
	 XSQ = 0.391,
	 snq = 9.285,
	 XQ = 2.72,
	 PN = 1539.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___27_SM (
	 SNREF = SNREF, 
	 ur0 = 0.93346831745291, 
	 ui0 = 0.256243809987498, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.2463336262378102, 
	 init_omega = 1.0, 
	 init_efd = 0.3160829350816005, 
	 WLMDVPu = 0.717262019773382, 
	 init_lambdaad = -0.9678022217121871, 
	 init_cm = -0.008928298075492282, 
	 init_lambdaq1 = -0.0182342529760483, 
	 init_lambdaq2 = -0.0182342529760483, 
	 init_iq = -0.09295357005842719, 
	 init_id = 0.002005729677271653, 
	 init_lambdaaq = -0.0182342529760483, 
	 init_lambdad = -0.9678022217121871, 
	 init_lambdaf = -1.064228174693517,
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
	 U2N = 138.0,
	 SNtfo = 1120.0,
	 lStatIn = 0.219,
	 XSQ = 0.301,
	 snq = 5.57,
	 XQ = 2.57,
	 PN = 1008.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___31_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9431560300514457, 
	 ui0 = 0.21341442719002301, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.2393496520707236, 
	 init_omega = 1.0, 
	 init_efd = 0.3156321676755371, 
	 WLMDVPu = 0.717262019773382, 
	 init_lambdaad = -0.9669209686786849, 
	 init_cm = 0.006944610148365915, 
	 init_lambdaq1 = 0.01420322467261489, 
	 init_lambdaq2 = 0.01420322467261489, 
	 init_iq = 0.07237859105636815, 
	 init_id = 0.001217534757432043, 
	 init_lambdaaq = 0.01420322467261489, 
	 init_lambdad = -0.9669209686786849, 
	 init_lambdaf = -1.063209408141004,
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
	 U2N = 138.0,
	 SNtfo = 1120.0,
	 lStatIn = 0.219,
	 XSQ = 0.301,
	 snq = 5.57,
	 XQ = 2.57,
	 PN = 1008.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___32_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9320177314468934, 
	 ui0 = 0.2462497157907184, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.2583087384700776, 
	 init_omega = 1.0, 
	 init_efd = 0.3508239020139175, 
	 WLMDVPu = 0.6296504227510634, 
	 init_lambdaad = -0.9639999866485596, 
	 init_cm = 0.0, 
	 init_lambdaq1 = 2.775557561562891e-17, 
	 init_lambdaq2 = 2.775557561562891e-17, 
	 init_iq = 0.0, 
	 init_id = 0.0, 
	 init_lambdaaq = 2.775557561562891e-17, 
	 init_lambdad = -0.9639999866485596, 
	 init_lambdaf = -1.08906866924125,
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
	 U2N = 138.0,
	 SNtfo = 1211.0,
	 lStatIn = 0.202,
	 XSQ = 0.262,
	 snq = 6.995,
	 XQ = 2.22,
	 PN = 1090.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___34_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9668859967556536, 
	 ui0 = 0.19320288444647712, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.1972222030162811, 
	 init_omega = 1.0, 
	 init_efd = 0.2926749980932662, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -0.9859999418258667, 
	 init_cm = 0.0, 
	 init_lambdaq1 = 2.775557561562891e-17, 
	 init_lambdaq2 = 2.775557561562891e-17, 
	 init_iq = 0.0, 
	 init_id = 0.0, 
	 init_lambdaaq = 2.775557561562891e-17, 
	 init_lambdad = -0.9859999418258667, 
	 init_lambdaf = -1.106417242490458,
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
	 U2N = 138.0,
	 SNtfo = 1650.0,
	 lStatIn = 0.256,
	 XSQ = 0.377,
	 snq = 9.285,
	 XQ = 2.62,
	 PN = 1485.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___36_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9624164546244605, 
	 ui0 = 0.18480964599316035, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.1897172927856446, 
	 init_omega = 1.0, 
	 init_efd = 0.3214282132403766, 
	 WLMDVPu = 0.717262019773382, 
	 init_lambdaad = -0.9800000190734862, 
	 init_cm = 0.0, 
	 init_lambdaq1 = -2.775557561562891e-17, 
	 init_lambdaq2 = -2.775557561562891e-17, 
	 init_iq = 0.0, 
	 init_id = 0.0, 
	 init_lambdaaq = -2.775557561562891e-17, 
	 init_lambdad = -0.9800000190734862, 
	 init_lambdaf = -1.078056631236962,
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
	 U2N = 138.0,
	 SNtfo = 1120.0,
	 lStatIn = 0.219,
	 XSQ = 0.301,
	 snq = 5.57,
	 XQ = 2.57,
	 PN = 1008.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___40_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9620297133975022, 
	 ui0 = 0.1240922481227806, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.05055107851499536, 
	 init_omega = 1.0, 
	 init_efd = 0.2789140189614809, 
	 WLMDVPu = 0.7304917626978259, 
	 init_lambdaad = -0.9677665870979975, 
	 init_cm = -0.02988674001047319, 
	 init_lambdaq1 = -0.06522392575738412, 
	 init_lambdaq2 = -0.06522392575738412, 
	 init_iq = -0.4727948605499436, 
	 init_id = 0.03682483293361053, 
	 init_lambdaaq = -0.06522392575738412, 
	 init_lambdad = -0.9677665870979975, 
	 init_lambdaf = -1.085891069283759,
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
	 U2N = 138.0,
	 SNtfo = 1710.0,
	 lStatIn = 0.265,
	 XSQ = 0.391,
	 snq = 9.285,
	 XQ = 2.72,
	 PN = 1539.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___42_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9741042162510335, 
	 ui0 = 0.14610231566230472, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.0528331246536091, 
	 init_omega = 1.0, 
	 init_efd = 0.29376822501874, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -0.9815855852626962, 
	 init_cm = -0.03972601263701636, 
	 init_lambdaq1 = -0.08158243077289408, 
	 init_lambdaq2 = -0.08158243077289408, 
	 init_iq = -0.5962243009063221, 
	 init_id = 0.05744016526994478, 
	 init_lambdaaq = -0.08158243077289408, 
	 init_lambdad = -0.9815855852626962, 
	 init_lambdaf = -1.102452679849683,
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
	 U2N = 138.0,
	 SNtfo = 1650.0,
	 lStatIn = 0.256,
	 XSQ = 0.377,
	 snq = 9.285,
	 XQ = 2.62,
	 PN = 1485.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___46_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9531209115681604, 
	 ui0 = 0.3187248317076221, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.3522625346070626, 
	 init_omega = 1.0, 
	 init_efd = 0.3009122037838883, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -1.004717921200558, 
	 init_cm = 0.01279507374243481, 
	 init_lambdaq1 = 0.02561844130820659, 
	 init_lambdaq2 = 0.02561844130820659, 
	 init_iq = 0.1889721852822569, 
	 init_id = 0.005585972695191073, 
	 init_lambdaaq = 0.02561844130820659, 
	 init_lambdad = -1.004717921200558, 
	 init_lambdaf = -1.1285243124289,
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
	 U2N = 138.0,
	 SNtfo = 1650.0,
	 lStatIn = 0.256,
	 XSQ = 0.377,
	 snq = 9.285,
	 XQ = 2.62,
	 PN = 1485.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___49_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9573040491865796, 
	 ui0 = 0.3663248674812708, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.7685279780931401, 
	 init_omega = 1.0, 
	 init_efd = 0.3733486013773169, 
	 WLMDVPu = 0.717262019773382, 
	 init_lambdaad = -0.9656810083423429, 
	 init_cm = 0.2025062094616498, 
	 init_lambdaq1 = 0.3501419612966183, 
	 init_lambdaq2 = 0.3501419612966183, 
	 init_iq = 1.830758984054212, 
	 init_id = 0.7806359700733065, 
	 init_lambdaaq = 0.3501419612966183, 
	 init_lambdad = -0.9656810083423429, 
	 init_lambdaf = -1.079576732174319,
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
	 U2N = 138.0,
	 SNtfo = 1120.0,
	 lStatIn = 0.219,
	 XSQ = 0.301,
	 snq = 5.57,
	 XQ = 2.57,
	 PN = 1008.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___54_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9213279643899563, 
	 ui0 = 0.2513555973349899, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.3844787607778851, 
	 init_omega = 1.0, 
	 init_efd = 0.3125570666033807, 
	 WLMDVPu = 0.717262019773382, 
	 init_lambdaad = -0.9501894372524045, 
	 init_cm = 0.04762703612314544, 
	 init_lambdaq1 = 0.09836590177617137, 
	 init_lambdaq2 = 0.09836590177617137, 
	 init_iq = 0.4991142955361793, 
	 init_id = 0.05924199764277657, 
	 init_lambdaaq = 0.09836590177617137, 
	 init_lambdad = -0.9501894372524045, 
	 init_lambdaf = -1.045539769912722,
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
	 U2N = 138.0,
	 SNtfo = 1120.0,
	 lStatIn = 0.219,
	 XSQ = 0.301,
	 snq = 5.57,
	 XQ = 2.57,
	 PN = 1008.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___55_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9196902920352356, 
	 ui0 = 0.24591422996534626, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = false, 
	 TX = 0, 
	 md = 0, 
	 XPQ = 0, 
	 TPQ0 = 0, 
	 mq = 0, 
	 snd = 0, 
	 snq = 0, 
	 init_theta = NaN, 
	 init_omega = NaN, 
	 init_efd = NaN, 
	 WLMDVPu = NaN, 
	 init_lambdaad = NaN, 
	 init_cm = NaN, 
	 init_lambdaq1 = NaN, 
	 init_lambdaq2 = NaN, 
	 init_iq = NaN, 
	 init_id = NaN, 
	 init_lambdaaq = NaN, 
	 init_lambdad = NaN, 
	 init_lambdaf = NaN,
	 PNALT = 245.7,
	 IENR = 3,
	 DIn = 0.0,
	 HIn = 3.595,
	 TSD0 = 0.03,
	 XPD = 0.37,
	 RTfoPu = 0.0,
	 TPD0 = 7.4,
	 XTfoPu = 0.1,
	 rStatIn = 0.005697,
	 U1N = 15.5,
	 TSQ0 = 0.08,
	 SN = 270.0,
	 V1 = 15.5,
	 XSD = 0.235,
	 XD = 1.61,
	 U2N = 138.0,
	 SNtfo = 270.0,
	 lStatIn = 0.17,
	 XSQ = 0.29,
	 XQ = 0.95,
	 PN = 252.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___56_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9208000757886092, 
	 ui0 = 0.24948566977602313, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.2645919024944305, 
	 init_omega = 1.0, 
	 init_efd = 0.3437011269583479, 
	 WLMDVPu = 0.6296504227510634, 
	 init_lambdaad = -0.953999936580658, 
	 init_cm = 0.0, 
	 init_lambdaq1 = -2.775557561562891e-17, 
	 init_lambdaq2 = -2.775557561562891e-17, 
	 init_iq = 0.0, 
	 init_id = 0.0, 
	 init_lambdaaq = -2.775557561562891e-17, 
	 init_lambdad = -0.953999936580658, 
	 init_lambdaf = -1.076529350647201,
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
	 U2N = 138.0,
	 SNtfo = 1211.0,
	 lStatIn = 0.202,
	 XSQ = 0.262,
	 snq = 6.995,
	 XQ = 2.22,
	 PN = 1090.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___59_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9292454495039592, 
	 ui0 = 0.3266922180525773, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.5947954102765939, 
	 init_omega = 1.0, 
	 init_efd = 0.3798544569396177, 
	 WLMDVPu = 0.6296504227510634, 
	 init_lambdaad = -0.9630339447162979, 
	 init_cm = 0.1422542863127093, 
	 init_lambdaq1 = 0.2122413447997115, 
	 init_lambdaq2 = 0.2122413447997115, 
	 init_iq = 1.522031941888072, 
	 init_id = 0.3995606789425607, 
	 init_lambdaaq = 0.2122413447997115, 
	 init_lambdad = -0.9630339447162979, 
	 init_lambdaf = -1.098452016956185,
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
	 U2N = 138.0,
	 SNtfo = 1211.0,
	 lStatIn = 0.202,
	 XSQ = 0.262,
	 snq = 6.995,
	 XQ = 2.22,
	 PN = 1090.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___61_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9086949654345006, 
	 ui0 = 0.40533747579399976, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.6691499606053525, 
	 init_omega = 1.0, 
	 init_efd = 0.3074438652148564, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -0.9730406068341747, 
	 init_cm = 0.1077774557972122, 
	 init_lambdaq1 = 0.2122113367880142, 
	 init_lambdaq2 = 0.2122113367880142, 
	 init_iq = 1.558219939284994, 
	 init_id = 0.3971698428619492, 
	 init_lambdaaq = 0.2122113367880142, 
	 init_lambdad = -0.9730406068341747, 
	 init_lambdaf = -1.099534364743977,
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
	 U2N = 138.0,
	 SNtfo = 1650.0,
	 lStatIn = 0.256,
	 XSQ = 0.377,
	 snq = 9.285,
	 XQ = 2.62,
	 PN = 1485.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___62_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9157114233974839, 
	 ui0 = 0.39683311540734667, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.4089306592941284, 
	 init_omega = 1.0, 
	 init_efd = 0.2977167213580232, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -0.9979999661445619, 
	 init_cm = 0.0, 
	 init_lambdaq1 = -5.551115123125783e-17, 
	 init_lambdaq2 = -5.551115123125783e-17, 
	 init_iq = 0.0, 
	 init_id = 0.0, 
	 init_lambdaaq = -5.551115123125783e-17, 
	 init_lambdad = -0.9979999661445619, 
	 init_lambdaf = -1.120491617915995,
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
	 U2N = 138.0,
	 SNtfo = 1650.0,
	 lStatIn = 0.256,
	 XSQ = 0.377,
	 snq = 9.285,
	 XQ = 2.62,
	 PN = 1485.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___65_SM (
	 SNREF = SNREF, 
	 ur0 = 0.8902279374280885, 
	 ui0 = 0.4663895473079392, 
	 transformerIncluded = true, 
	 V2 = 345.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 1.028496062983906, 
	 init_omega = 1.0, 
	 init_efd = 0.3489532765299156, 
	 WLMDVPu = 0.7304917626978259, 
	 init_lambdaad = -0.9026801580177228, 
	 init_cm = 0.2542494426507539, 
	 init_lambdaq1 = 0.4512073229380114, 
	 init_lambdaq2 = 0.4512073229380114, 
	 init_iq = 3.325071242311891, 
	 init_id = 2.019965194780391, 
	 init_lambdaaq = 0.4512073229380114, 
	 init_lambdad = -0.9026801580177228, 
	 init_lambdaf = -1.050467369469485,
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
	 U2N = 345.0,
	 SNtfo = 1710.0,
	 lStatIn = 0.265,
	 XSQ = 0.391,
	 snq = 9.285,
	 XQ = 2.72,
	 PN = 1539.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___66_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9315305228896494, 
	 ui0 = 0.48451087169343826, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.9787211152517624, 
	 init_omega = 1.0, 
	 init_efd = 0.3629391779046922, 
	 WLMDVPu = 0.7304917626978259, 
	 init_lambdaad = -0.9606816676034892, 
	 init_cm = 0.254884299542758, 
	 init_lambdaq1 = 0.43294671776114, 
	 init_lambdaq2 = 0.43294671776114, 
	 init_iq = 3.277909609762444, 
	 init_id = 1.786921272445778, 
	 init_lambdaaq = 0.43294671776114, 
	 init_lambdad = -0.9606816676034892, 
	 init_lambdaf = -1.11439212867873,
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
	 U2N = 138.0,
	 SNtfo = 1710.0,
	 lStatIn = 0.265,
	 XSQ = 0.391,
	 snq = 9.285,
	 XQ = 2.72,
	 PN = 1539.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___69_SM (
	 SNREF = SNREF, 
	 ur0 = 0.8963362564699628, 
	 ui0 = 0.5174999963707341, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 1.430265970782625, 
	 init_omega = 1.0, 
	 init_efd = 0.468167259743469, 
	 WLMDVPu = 0.717262019773382, 
	 init_lambdaad = -0.7376521577907011, 
	 init_cm = 0.5152579139307082, 
	 init_lambdaq1 = 0.7104671074765034, 
	 init_lambdaq2 = 0.7104671074765034, 
	 init_iq = 3.709348732145709, 
	 init_id = 3.459119571186246, 
	 init_lambdaaq = 0.7104671074765034, 
	 init_lambdad = -0.7376521577907011, 
	 init_lambdaf = -0.880473769352134,
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
	 U2N = 138.0,
	 SNtfo = 1120.0,
	 lStatIn = 0.219,
	 XSQ = 0.301,
	 snq = 5.57,
	 XQ = 2.57,
	 PN = 1008.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___70_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9085708266159871, 
	 ui0 = 0.37782946750091845, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.3940953314304352, 
	 init_omega = 1.0, 
	 init_efd = 0.3232572624961278, 
	 WLMDVPu = 0.717262019773382, 
	 init_lambdaad = -0.9840000271797179, 
	 init_cm = 0.0, 
	 init_lambdaq1 = 5.551115123125783e-17, 
	 init_lambdaq2 = 5.551115123125783e-17, 
	 init_iq = 0.0, 
	 init_id = 0.0, 
	 init_lambdaaq = 5.551115123125783e-17, 
	 init_lambdad = -0.9840000271797179, 
	 init_lambdaf = -1.08261461890476,
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
	 U2N = 138.0,
	 SNtfo = 1120.0,
	 lStatIn = 0.219,
	 XSQ = 0.301,
	 snq = 5.57,
	 XQ = 2.57,
	 PN = 1008.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___72_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9150313716592225, 
	 ui0 = 0.3508812138936413, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.3455100185845145, 
	 init_omega = 1.0, 
	 init_efd = 0.3629122036180329, 
	 WLMDVPu = 0.6296504227510634, 
	 init_lambdaad = -0.9798256943438247, 
	 init_cm = -0.01100885671459143, 
	 init_lambdaq1 = -0.01719184555110395, 
	 init_lambdaq2 = -0.01719184555110395, 
	 init_iq = -0.1224228451952326, 
	 init_id = 0.002529623242132205, 
	 init_lambdaaq = -0.01719184555110395, 
	 init_lambdad = -0.9798256943438247, 
	 init_lambdaf = -1.109203855132644,
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
	 U2N = 138.0,
	 SNtfo = 1211.0,
	 lStatIn = 0.202,
	 XSQ = 0.262,
	 snq = 6.995,
	 XQ = 2.22,
	 PN = 1090.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___73_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9192274478304701, 
	 ui0 = 0.3702727272422879, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.3729306172942908, 
	 init_omega = 1.0, 
	 init_efd = 0.3716224721000666, 
	 WLMDVPu = 0.6296504227510634, 
	 init_lambdaad = -0.9909516125643056, 
	 init_cm = -0.005504509509492535, 
	 init_lambdaq1 = -0.008394571111267367, 
	 init_lambdaq2 = -0.008394571111267367, 
	 init_iq = -0.06054188037459637, 
	 init_id = 0.0006051135334656128, 
	 init_lambdaaq = -0.008394571111267367, 
	 init_lambdad = -0.9909516125643056, 
	 init_lambdaf = -1.123434983111705,
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
	 U2N = 138.0,
	 SNtfo = 1211.0,
	 lStatIn = 0.202,
	 XSQ = 0.262,
	 snq = 6.995,
	 XQ = 2.22,
	 PN = 1090.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___74_SM (
	 SNREF = SNREF, 
	 ur0 = 0.890479402321959, 
	 ui0 = 0.35328505213270817, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.3776892423629762, 
	 init_omega = 1.0, 
	 init_efd = 0.2815592094859837, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -0.9579999446868896, 
	 init_cm = 0.0, 
	 init_lambdaq1 = 5.551115123125783e-17, 
	 init_lambdaq2 = 5.551115123125783e-17, 
	 init_iq = 0.0, 
	 init_id = 0.0, 
	 init_lambdaaq = 5.551115123125783e-17, 
	 init_lambdad = -0.9579999446868896, 
	 init_lambdaf = -1.0738437994898,
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
	 U2N = 138.0,
	 SNtfo = 1650.0,
	 lStatIn = 0.256,
	 XSQ = 0.377,
	 snq = 9.285,
	 XQ = 2.62,
	 PN = 1485.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___76_SM (
	 SNREF = SNREF, 
	 ur0 = 0.8757453946692707, 
	 ui0 = 0.3497413884412938, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = false, 
	 TX = 0, 
	 md = 0, 
	 XPQ = 0, 
	 TPQ0 = 0, 
	 mq = 0, 
	 snd = 0, 
	 snq = 0, 
	 init_theta = NaN, 
	 init_omega = NaN, 
	 init_efd = NaN, 
	 WLMDVPu = NaN, 
	 init_lambdaad = NaN, 
	 init_cm = NaN, 
	 init_lambdaq1 = NaN, 
	 init_lambdaq2 = NaN, 
	 init_iq = NaN, 
	 init_id = NaN, 
	 init_lambdaaq = NaN, 
	 init_lambdad = NaN, 
	 init_lambdaf = NaN,
	 PNALT = 228.0,
	 IENR = 3,
	 DIn = 0.0,
	 HIn = 2.748,
	 TSD0 = 0.096,
	 XPD = 0.31,
	 RTfoPu = 0.0,
	 TPD0 = 8.4,
	 XTfoPu = 0.1,
	 rStatIn = 0.004,
	 U1N = 18.0,
	 TSQ0 = 0.1,
	 SN = 250.0,
	 V1 = 18.0,
	 XSD = 0.275,
	 XD = 1.53,
	 U2N = 138.0,
	 SNtfo = 250.0,
	 lStatIn = 0.11,
	 XSQ = 0.346,
	 XQ = 0.99,
	 PN = 242.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___77_SM (
	 SNREF = SNREF, 
	 ur0 = 0.8985738254663534, 
	 ui0 = 0.4523286024713063, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.4663519561290741, 
	 init_omega = 1.0, 
	 init_efd = 0.3842592133098166, 
	 WLMDVPu = 0.6296504227510634, 
	 init_lambdaad = -1.00600004196167, 
	 init_cm = 0.0, 
	 init_lambdaq1 = 5.551115123125783e-17, 
	 init_lambdaq2 = 5.551115123125783e-17, 
	 init_iq = 0.0, 
	 init_id = 0.0, 
	 init_lambdaaq = 5.551115123125783e-17, 
	 init_lambdad = -1.00600004196167, 
	 init_lambdaf = -1.142988409364458,
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
	 U2N = 138.0,
	 SNtfo = 1211.0,
	 lStatIn = 0.202,
	 XSQ = 0.262,
	 snq = 6.995,
	 XQ = 2.22,
	 PN = 1090.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___80_SM (
	 SNREF = SNREF, 
	 ur0 = 0.909956355787249, 
	 ui0 = 0.5035668765635952, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 1.102118492835812, 
	 init_omega = 1.0, 
	 init_efd = 0.3934306321356991, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -0.9166294700923588, 
	 init_cm = 0.3214834192773156, 
	 init_lambdaq1 = 0.5030020892864908, 
	 init_lambdaq2 = 0.5030020892864908, 
	 init_iq = 3.794033122185931, 
	 init_id = 2.577138810307531, 
	 init_lambdaaq = 0.5030020892864908, 
	 init_lambdad = -0.9166294700923588, 
	 init_lambdaf = -1.078501358805748,
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
	 U2N = 138.0,
	 SNtfo = 1650.0,
	 lStatIn = 0.256,
	 XSQ = 0.377,
	 snq = 9.285,
	 XQ = 2.62,
	 PN = 1485.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___85_SM (
	 SNREF = SNREF, 
	 ur0 = 0.8306481559217389, 
	 ui0 = 0.5293850695134333, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = NaN, 
	 init_omega = NaN, 
	 init_efd = NaN, 
	 WLMDVPu = NaN, 
	 init_lambdaad = NaN, 
	 init_cm = NaN, 
	 init_lambdaq1 = NaN, 
	 init_lambdaq2 = NaN, 
	 init_iq = NaN, 
	 init_id = NaN, 
	 init_lambdaaq = NaN, 
	 init_lambdad = NaN, 
	 init_lambdaf = NaN,
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
	 U2N = 138.0,
	 SNtfo = 1650.0,
	 lStatIn = 0.256,
	 XSQ = 0.377,
	 snq = 9.285,
	 XQ = 2.62,
	 PN = 1485.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___87_SM (
	 SNREF = SNREF, 
	 ur0 = 0.8663541638256711, 
	 ui0 = 0.5288248063181304, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.5541100981839399, 
	 init_omega = 1.0, 
	 init_efd = 0.29642462393069, 
	 WLMDVPu = 0.7304917626978259, 
	 init_lambdaad = -1.014994023701989, 
	 init_cm = 0.002599109645385708, 
	 init_lambdaq1 = 0.005326733381830942, 
	 init_lambdaq2 = 0.005326733381830942, 
	 init_iq = 0.03940813530290183, 
	 init_id = 0.0002394759996178462, 
	 init_lambdaaq = 0.005326733381830942, 
	 init_lambdad = -1.014994023701989, 
	 init_lambdaf = -1.140534523026111,
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
	 U2N = 138.0,
	 SNtfo = 1710.0,
	 lStatIn = 0.265,
	 XSQ = 0.391,
	 snq = 9.285,
	 XQ = 2.72,
	 PN = 1539.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___89_SM (
	 SNREF = SNREF, 
	 ur0 = 0.7733586139729677, 
	 ui0 = 0.6418266468520791, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 1.454730774219803, 
	 init_omega = 1.0, 
	 init_efd = 0.5394841023042883, 
	 WLMDVPu = 0.6296504227510634, 
	 init_lambdaad = -0.8320613598090058, 
	 init_cm = 0.5576534355044376, 
	 init_lambdaq1 = 0.5858245733235349, 
	 init_lambdaq2 = 0.5858245733235349, 
	 init_iq = 4.369494412523375, 
	 init_id = 4.169737999514166, 
	 init_lambdaaq = 0.5858245733235349, 
	 init_lambdad = -0.8320613598090058, 
	 init_lambdaf = -1.024387383114627,
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
	 U2N = 138.0,
	 SNtfo = 1211.0,
	 lStatIn = 0.202,
	 XSQ = 0.262,
	 snq = 6.995,
	 XQ = 2.22,
	 PN = 1090.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___90_SM (
	 SNREF = SNREF, 
	 ur0 = 0.8233645809952534, 
	 ui0 = 0.540643762122933, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.4378071611367714, 
	 init_omega = 1.0, 
	 init_efd = 0.3705191929017553, 
	 WLMDVPu = 0.6296504227510634, 
	 init_lambdaad = -0.9777902987328571, 
	 init_cm = -0.07796587773499833, 
	 init_lambdaq1 = -0.1192547584918489, 
	 init_lambdaq2 = -0.1192547584918489, 
	 init_iq = -0.8541098454624693, 
	 init_id = 0.1231627697863596, 
	 init_lambdaaq = -0.1192547584918489, 
	 init_lambdad = -0.9777902987328571, 
	 init_lambdaf = -1.109880350367056,
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
	 U2N = 138.0,
	 SNtfo = 1211.0,
	 lStatIn = 0.202,
	 XSQ = 0.262,
	 snq = 6.995,
	 XQ = 2.22,
	 PN = 1090.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___91_SM (
	 SNREF = SNREF, 
	 ur0 = 0.8189972860041149, 
	 ui0 = 0.5381853610996191, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.5641518923612576, 
	 init_omega = 1.0, 
	 init_efd = 0.3628916116008083, 
	 WLMDVPu = 0.6296504227510634, 
	 init_lambdaad = -0.9798750235460794, 
	 init_cm = -0.009174091372900522, 
	 init_lambdaq1 = -0.01432741979700246, 
	 init_lambdaq2 = -0.01432741979700246, 
	 init_iq = -0.1020256904076731, 
	 init_id = 0.001756783457777217, 
	 init_lambdaaq = -0.01432741979700246, 
	 init_lambdad = -0.9798750235460794, 
	 init_lambdaf = -1.109245843283016,
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
	 U2N = 138.0,
	 SNtfo = 1211.0,
	 lStatIn = 0.202,
	 XSQ = 0.262,
	 snq = 6.995,
	 XQ = 2.22,
	 PN = 1090.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___92_SM (
	 SNREF = SNREF, 
	 ur0 = 0.8251675491403462, 
	 ui0 = 0.5524015369989487, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.5899212956428528, 
	 init_omega = 1.0, 
	 init_efd = 0.3732528855097978, 
	 WLMDVPu = 0.6296504227510634, 
	 init_lambdaad = -0.9929999709129333, 
	 init_cm = 0.0, 
	 init_lambdaq1 = 5.551115123125783e-17, 
	 init_lambdaq2 = 5.551115123125783e-17, 
	 init_iq = 0.0, 
	 init_id = 0.0, 
	 init_lambdaaq = 5.551115123125783e-17, 
	 init_lambdad = -0.9929999709129333, 
	 init_lambdaf = -1.126064583662092,
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
	 U2N = 138.0,
	 SNtfo = 1211.0,
	 lStatIn = 0.202,
	 XSQ = 0.262,
	 snq = 6.995,
	 XQ = 2.22,
	 PN = 1090.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN___99_SM (
	 SNREF = SNREF, 
	 ur0 = 0.8995962329884465, 
	 ui0 = 0.45915857645129066, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.4074321522448255, 
	 init_omega = 1.0, 
	 init_efd = 0.2949044891460149, 
	 WLMDVPu = 0.7304917626978259, 
	 init_lambdaad = -1.008392154747564, 
	 init_cm = -0.02728829639189495, 
	 init_lambdaq1 = -0.05624196105462055, 
	 init_lambdaq2 = -0.05624196105462055, 
	 init_iq = -0.4149767538874427, 
	 init_id = 0.02680522549864861, 
	 init_lambdaaq = -0.05624196105462055, 
	 init_lambdad = -1.008392154747564, 
	 init_lambdaf = -1.133288853035937,
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
	 U2N = 138.0,
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
	 ur0 = 0.9384878563516427, 
	 ui0 = 0.17682024137261235, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = NaN, 
	 init_omega = NaN, 
	 init_efd = NaN, 
	 WLMDVPu = NaN, 
	 init_lambdaad = NaN, 
	 init_cm = NaN, 
	 init_lambdaq1 = NaN, 
	 init_lambdaq2 = NaN, 
	 init_iq = NaN, 
	 init_id = NaN, 
	 init_lambdaaq = NaN, 
	 init_lambdad = NaN, 
	 init_lambdaf = NaN,
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
	 U2N = 138.0,
	 SNtfo = 1211.0,
	 lStatIn = 0.202,
	 XSQ = 0.262,
	 snq = 6.995,
	 XQ = 2.22,
	 PN = 1090.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN____4_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9627201422165105, 
	 ui0 = 0.2630092397524625, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.2466015612977915, 
	 init_omega = 1.0, 
	 init_efd = 0.32982382024355, 
	 WLMDVPu = 0.717262019773382, 
	 init_lambdaad = -0.9978215237087321, 
	 init_cm = -0.008928314262525673, 
	 init_lambdaq1 = -0.01747462219297455, 
	 init_lambdaq2 = -0.01747462219297455, 
	 init_iq = -0.09016217517743362, 
	 init_id = 0.001811126413426734, 
	 init_lambdaaq = -0.01747462219297455, 
	 init_lambdad = -0.9978215237087321, 
	 init_lambdaf = -1.098439344707885,
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
	 U2N = 138.0,
	 SNtfo = 1120.0,
	 lStatIn = 0.219,
	 XSQ = 0.301,
	 snq = 5.57,
	 XQ = 2.57,
	 PN = 1008.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN____6_SM (
	 SNREF = SNREF, 
	 ur0 = 0.9646263161589513, 
	 ui0 = 0.22270152904542032, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = NaN, 
	 init_omega = NaN, 
	 init_efd = NaN, 
	 WLMDVPu = NaN, 
	 init_lambdaad = NaN, 
	 init_cm = NaN, 
	 init_lambdaq1 = NaN, 
	 init_lambdaq2 = NaN, 
	 init_iq = NaN, 
	 init_id = NaN, 
	 init_lambdaaq = NaN, 
	 init_lambdad = NaN, 
	 init_lambdaf = NaN,
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
	 U2N = 138.0,
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
	 ur0 = 0.9490366356764796, 
	 ui0 = 0.35993670985950593, 
	 transformerIncluded = true, 
	 V2 = 345.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.3199891995375365, 
	 init_omega = 1.0, 
	 init_efd = 0.296698774178641, 
	 WLMDVPu = 0.7304917626978259, 
	 init_lambdaad = -1.014280258237544, 
	 init_cm = -0.01819268520563741, 
	 init_lambdaq1 = -0.03725522685687005, 
	 init_lambdaq2 = -0.03725522685687005, 
	 init_iq = -0.2756127883699735, 
	 init_id = 0.01172493607331142, 
	 init_lambdaaq = -0.03725522685687005, 
	 init_lambdad = -1.014280258237544, 
	 init_lambdaf = -1.13993686451176,
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
	 U2N = 345.0,
	 SNtfo = 1710.0,
	 lStatIn = 0.265,
	 XSQ = 0.391,
	 snq = 9.285,
	 XQ = 2.72,
	 PN = 1539.0,
	 IWLMDV = 3
	 ) annotation (Placement(transformation()));

// REGULATORS
  pssi3e2b reg_pssi3e2b__GEN__100_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_APREF = 0.1473684210526315,
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
  sexs reg_sexs__GEN__100_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_EFD = 0.3212865492524267,
	 init_VREF = 1.01870961986104,
	 init_YLL = 0.001606432746262133,
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
  gsteam0 reg_gsteam0__GEN__100_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_REF = 0.008190954876111206,
	 init_PMECH = 0.1638190975222241,
	 init_CM = 0.1638190975222241,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN__103_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_APREF = 0.02339181286549708,
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
  sexs reg_sexs__GEN__103_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_EFD = 0.2910939393773114,
	 init_VREF = 1.00245824411611,
	 init_YLL = 0.001455469696886557,
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
  gsteam0 reg_gsteam0__GEN__103_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_REF = 0.001299644516170423,
	 init_PMECH = 0.02599289032340846,
	 init_CM = 0.02599289032340846,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN__104_SM (
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
  sexs reg_sexs__GEN__104_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_EFD = 0.2866157015426111,
	 init_VREF = 0.9724330942433392,
	 init_YLL = 0.001433078507713055,
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
  gsteam0 reg_gsteam0__GEN__104_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_REF = 0.0,
	 init_PMECH = 0.0,
	 init_CM = 0.0,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN__105_SM (
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
  sexs reg_sexs__GEN__105_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_EFD = 0.2842607629535722,
	 init_VREF = 0.9664212775887242,
	 init_YLL = 0.001421303814767861,
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
  gsteam0 reg_gsteam0__GEN__105_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_REF = 0.0,
	 init_PMECH = 0.0,
	 init_CM = 0.0,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN__107_SM (
	 SNREF = 100.0,
	 SN = 250.0,
	 PN = 242.0,
	 PNALT = 228.0,
	 init_APREF = -0.08800000000000002,
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
  sexs reg_sexs__GEN__107_SM (
	 SNREF = 100.0,
	 SN = 250.0,
	 PN = 242.0,
	 PNALT = 228.0,
	 init_EFD = 0.452783545823648,
	 init_VREF = 0.954308815673775,
	 init_YLL = 0.00226391772911824,
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
  cmconst reg_cmconst__GEN__107_SM (
	 SNREF = 100.0,
	 SN = 250.0,
	 PN = 242.0,
	 PNALT = 228.0,
	 init_CM = -0.09087378266977451
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN__110_SM (
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
  sexs reg_sexs__GEN__110_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_EFD = 0.3182601408803978,
	 init_VREF = 0.9745912906908217,
	 init_YLL = 0.001591300704401989,
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
  gsteam0 reg_gsteam0__GEN__110_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_REF = 0.0,
	 init_PMECH = 0.0,
	 init_CM = 0.0,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN__111_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_APREF = 0.03214285714285715,
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
  sexs reg_sexs__GEN__111_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_EFD = 0.3226169107496577,
	 init_VREF = 0.9816185922008298,
	 init_YLL = 0.001613084553748289,
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
  gsteam0 reg_gsteam0__GEN__111_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_REF = 0.001785927645466795,
	 init_PMECH = 0.03571855290933589,
	 init_CM = 0.03571855290933589,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN__112_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_APREF = -0.02606060606060606,
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
  sexs reg_sexs__GEN__112_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_EFD = 0.2890264670431515,
	 init_VREF = 0.9764488199144254,
	 init_YLL = 0.001445132335215758,
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
  cmconst reg_cmconst__GEN__112_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_CM = -0.02895372051261449
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN__113_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_APREF = -0.003508771929824561,
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
  sexs reg_sexs__GEN__113_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_EFD = 0.2871019667937185,
	 init_VREF = 0.9944355436153245,
	 init_YLL = 0.001435509833968592,
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
  cmconst reg_cmconst__GEN__113_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_CM = -0.003898590043621592
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN__116_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_APREF = -0.1076023391812865,
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
  sexs reg_sexs__GEN__116_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_EFD = 0.3053621487743324,
	 init_VREF = 1.006583835917299,
	 init_YLL = 0.001526810743871662,
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
  cmconst reg_cmconst__GEN__116_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_CM = -0.1195164408126235
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___10_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_APREF = 0.2727272727272727,
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
  sexs reg_sexs__GEN___10_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_EFD = 0.3888908890213766,
	 init_VREF = 1.052265619589737,
	 init_YLL = 0.001944454445106883,
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
  gsteam0 reg_gsteam0__GEN___10_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_REF = 0.01516335900859058,
	 init_PMECH = 0.3032671801718116,
	 init_CM = 0.3032671801718116,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___12_SM (
	 SNREF = 100.0,
	 SN = 250.0,
	 PN = 242.0,
	 PNALT = 228.0,
	 init_APREF = 0.3400000000000001,
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
  sexs reg_sexs__GEN___12_SM (
	 SNREF = 100.0,
	 SN = 250.0,
	 PN = 242.0,
	 PNALT = 228.0,
	 init_EFD = 0.5294879219751814,
	 init_VREF = 0.993242903190928,
	 init_YLL = 0.002647439609875907,
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
  gsteam0 reg_gsteam0__GEN___12_SM (
	 SNREF = 100.0,
	 SN = 250.0,
	 PN = 242.0,
	 PNALT = 228.0,
	 init_REF = 0.0175863527190455,
	 init_PMECH = 0.3517270543809101,
	 init_CM = 0.3517270543809101,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___15_SM (
	 SNREF = 100.0,
	 SN = 270.0,
	 PN = 252.0,
	 PNALT = 245.7,
	 init_APREF = NaN,
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
  sexs reg_sexs__GEN___15_SM (
	 SNREF = 100.0,
	 SN = 270.0,
	 PN = 252.0,
	 PNALT = 245.7,
	 init_EFD = NaN,
	 init_VREF = NaN,
	 init_YLL = NaN,
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
  gsteam0 reg_gsteam0__GEN___15_SM (
	 SNREF = 100.0,
	 SN = 270.0,
	 PN = 252.0,
	 PNALT = 245.7,
	 init_REF = NaN,
	 init_PMECH = NaN,
	 init_CM = NaN,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___18_SM (
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
  sexs reg_sexs__GEN___18_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_EFD = 0.2874090816220181,
	 init_VREF = 0.9744370353945297,
	 init_YLL = 0.00143704540811009,
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
  gsteam0 reg_gsteam0__GEN___18_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_REF = 0.0,
	 init_PMECH = 0.0,
	 init_CM = 0.0,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___19_SM (
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
  sexs reg_sexs__GEN___19_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_EFD = 0.2834839784566625,
	 init_VREF = 0.964417419415446,
	 init_YLL = 0.001417419892283312,
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
  gsteam0 reg_gsteam0__GEN___19_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_REF = 0.0,
	 init_PMECH = 0.0,
	 init_CM = 0.0,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___24_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_APREF = -0.01160714285714286,
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
  sexs reg_sexs__GEN___24_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_EFD = 0.3270804825935426,
	 init_VREF = 0.9936360762583808,
	 init_YLL = 0.001635402412967713,
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
  cmconst reg_cmconst__GEN___24_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_CM = -0.01289628233029736
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___25_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_APREF = 0.1816680429397193,
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
  sexs reg_sexs__GEN___25_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_EFD = 0.4474582315510471,
	 init_VREF = 1.052379781180001,
	 init_YLL = 0.002237291157755236,
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
  gsteam0 reg_gsteam0__GEN___25_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_REF = 0.01009639258801817,
	 init_PMECH = 0.2019278517603634,
	 init_CM = 0.2019278517603634,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___26_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_APREF = 0.1836257309941521,
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
  sexs reg_sexs__GEN___26_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_EFD = 0.3331067854540082,
	 init_VREF = 1.016826734263978,
	 init_YLL = 0.001665533927270041,
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
  gsteam0 reg_gsteam0__GEN___26_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_REF = 0.0102073843904816,
	 init_PMECH = 0.2041476878096319,
	 init_CM = 0.2041476878096319,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___27_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_APREF = -0.008035714285714285,
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
  sexs reg_sexs__GEN___27_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_EFD = 0.3160829350816005,
	 init_VREF = 0.9695807653833431,
	 init_YLL = 0.001580414675408002,
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
  cmconst reg_cmconst__GEN___27_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_CM = -0.008928298075492282
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___31_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_APREF = 0.00625,
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
  sexs reg_sexs__GEN___31_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_EFD = 0.3156321676755371,
	 init_VREF = 0.9685783844661842,
	 init_YLL = 0.001578160838377686,
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
  gsteam0 reg_gsteam0__GEN___31_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_REF = 0.0003472305074182958,
	 init_PMECH = 0.006944610148365915,
	 init_CM = 0.006944610148365915,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___32_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
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
  sexs reg_sexs__GEN___32_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_EFD = 0.3508239020139175,
	 init_VREF = 0.9657541061586291,
	 init_YLL = 0.001754119510069587,
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
  gsteam0 reg_gsteam0__GEN___32_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_REF = 0.0,
	 init_PMECH = 0.0,
	 init_CM = 0.0,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___34_SM (
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
  sexs reg_sexs__GEN___34_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_EFD = 0.2926749980932662,
	 init_VREF = 0.987463316816333,
	 init_YLL = 0.001463374990466331,
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
  gsteam0 reg_gsteam0__GEN___34_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_REF = 0.0,
	 init_PMECH = 0.0,
	 init_CM = 0.0,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___36_SM (
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
  sexs reg_sexs__GEN___36_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_EFD = 0.3214282132403766,
	 init_VREF = 0.9816071601396881,
	 init_YLL = 0.001607141066201883,
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
  gsteam0 reg_gsteam0__GEN___36_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_REF = 0.0,
	 init_PMECH = 0.0,
	 init_CM = 0.0,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___40_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_APREF = -0.02690058479532164,
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
  sexs reg_sexs__GEN___40_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_EFD = 0.2789140189614809,
	 init_VREF = 0.9713985631036743,
	 init_YLL = 0.001394570094807404,
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
  cmconst reg_cmconst__GEN___40_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_CM = -0.02988674001047319
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___42_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_APREF = -0.03575757575757575,
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
  sexs reg_sexs__GEN___42_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_EFD = 0.29376822501874,
	 init_VREF = 0.9864754853621435,
	 init_YLL = 0.0014688411250937,
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
  cmconst reg_cmconst__GEN___42_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_CM = -0.03972601263701636
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___46_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_APREF = 0.01151515151515152,
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
  sexs reg_sexs__GEN___46_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_EFD = 0.3009122037838883,
	 init_VREF = 1.00650520939764,
	 init_YLL = 0.001504561018919442,
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
  gsteam0 reg_gsteam0__GEN___46_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_REF = 0.0006397536871217404,
	 init_PMECH = 0.01279507374243481,
	 init_CM = 0.01279507374243481,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___49_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_APREF = 0.1821428571428572,
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
  sexs reg_sexs__GEN___49_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_EFD = 0.3733486013773169,
	 init_VREF = 1.027020743768338,
	 init_YLL = 0.001866743006886585,
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
  gsteam0 reg_gsteam0__GEN___49_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_REF = 0.01012531047308249,
	 init_PMECH = 0.2025062094616498,
	 init_CM = 0.2025062094616498,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___54_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_APREF = 0.04285714285714286,
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
  sexs reg_sexs__GEN___54_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_EFD = 0.3125570666033807,
	 init_VREF = 0.9565732530057703,
	 init_YLL = 0.001562785333016904,
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
  gsteam0 reg_gsteam0__GEN___54_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_REF = 0.002381351806157272,
	 init_PMECH = 0.04762703612314544,
	 init_CM = 0.04762703612314544,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___55_SM (
	 SNREF = 100.0,
	 SN = 270.0,
	 PN = 252.0,
	 PNALT = 245.7,
	 init_APREF = NaN,
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
  sexs reg_sexs__GEN___55_SM (
	 SNREF = 100.0,
	 SN = 270.0,
	 PN = 252.0,
	 PNALT = 245.7,
	 init_EFD = NaN,
	 init_VREF = NaN,
	 init_YLL = NaN,
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
  gsteam0 reg_gsteam0__GEN___55_SM (
	 SNREF = 100.0,
	 SN = 270.0,
	 PN = 252.0,
	 PNALT = 245.7,
	 init_REF = NaN,
	 init_PMECH = NaN,
	 init_CM = NaN,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___56_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
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
  sexs reg_sexs__GEN___56_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_EFD = 0.3437011269583479,
	 init_VREF = 0.9557184422154497,
	 init_YLL = 0.001718505634791739,
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
  gsteam0 reg_gsteam0__GEN___56_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_REF = 0.0,
	 init_PMECH = 0.0,
	 init_CM = 0.0,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___59_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_APREF = 0.1279933938893476,
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
  sexs reg_sexs__GEN___59_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_EFD = 0.3798544569396177,
	 init_VREF = 0.9869849342407229,
	 init_YLL = 0.001899272284698089,
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
  gsteam0 reg_gsteam0__GEN___59_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_REF = 0.007112714315635467,
	 init_PMECH = 0.1422542863127093,
	 init_CM = 0.1422542863127093,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___61_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_APREF = 0.096969696969697,
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
  sexs reg_sexs__GEN___61_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_EFD = 0.3074438652148564,
	 init_VREF = 0.9965849509054791,
	 init_YLL = 0.001537219326074282,
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
  gsteam0 reg_gsteam0__GEN___61_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_REF = 0.00538887278986061,
	 init_PMECH = 0.1077774557972122,
	 init_CM = 0.1077774557972122,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___62_SM (
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
  sexs reg_sexs__GEN___62_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_EFD = 0.2977167213580232,
	 init_VREF = 0.999488549751352,
	 init_YLL = 0.001488583606790116,
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
  gsteam0 reg_gsteam0__GEN___62_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_REF = 0.0,
	 init_PMECH = 0.0,
	 init_CM = 0.0,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___65_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_APREF = 0.228654970760234,
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
  sexs reg_sexs__GEN___65_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_EFD = 0.3489532765299156,
	 init_VREF = 1.0070022617638,
	 init_YLL = 0.001744766382649578,
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
  gsteam0 reg_gsteam0__GEN___65_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_REF = 0.0127124721325377,
	 init_PMECH = 0.2542494426507539,
	 init_CM = 0.2542494426507539,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___66_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_APREF = 0.2292397660818714,
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
  sexs reg_sexs__GEN___66_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_EFD = 0.3629391779046922,
	 init_VREF = 1.052041600797593,
	 init_YLL = 0.001814695889523461,
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
  gsteam0 reg_gsteam0__GEN___66_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_REF = 0.0127442149771379,
	 init_PMECH = 0.254884299542758,
	 init_CM = 0.254884299542758,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___69_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_APREF = 0.4630000000000001,
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
  sexs reg_sexs__GEN___69_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_EFD = 0.468167259743469,
	 init_VREF = 1.031270575689488,
	 init_YLL = 0.002340836298717345,
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
  gsteam0 reg_gsteam0__GEN___69_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_REF = 0.02576289569653541,
	 init_PMECH = 0.5152579139307082,
	 init_CM = 0.5152579139307082,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___70_SM (
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
  sexs reg_sexs__GEN___70_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_EFD = 0.3232572624961278,
	 init_VREF = 0.9856163134921986,
	 init_YLL = 0.001616286312480639,
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
  gsteam0 reg_gsteam0__GEN___70_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_REF = 0.0,
	 init_PMECH = 0.0,
	 init_CM = 0.0,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___72_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_APREF = -0.009909165978530142,
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
  sexs reg_sexs__GEN___72_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_EFD = 0.3629122036180329,
	 init_VREF = 0.9818151017255179,
	 init_YLL = 0.001814561018090165,
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
  cmconst reg_cmconst__GEN___72_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_CM = -0.01100885671459143
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___73_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_APREF = -0.004954582989265072,
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
  sexs reg_sexs__GEN___73_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_EFD = 0.3716224721000666,
	 init_VREF = 0.9928582351366203,
	 init_YLL = 0.001858112360500333,
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
  cmconst reg_cmconst__GEN___73_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_CM = -0.005504509509492535
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___74_SM (
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
  sexs reg_sexs__GEN___74_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_EFD = 0.2815592094859837,
	 init_VREF = 0.9594077407343196,
	 init_YLL = 0.001407796047429919,
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
  gsteam0 reg_gsteam0__GEN___74_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_REF = 0.0,
	 init_PMECH = 0.0,
	 init_CM = 0.0,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___76_SM (
	 SNREF = 100.0,
	 SN = 250.0,
	 PN = 242.0,
	 PNALT = 228.0,
	 init_APREF = NaN,
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
  sexs reg_sexs__GEN___76_SM (
	 SNREF = 100.0,
	 SN = 250.0,
	 PN = 242.0,
	 PNALT = 228.0,
	 init_EFD = NaN,
	 init_VREF = NaN,
	 init_YLL = NaN,
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
  gsteam0 reg_gsteam0__GEN___76_SM (
	 SNREF = 100.0,
	 SN = 250.0,
	 PN = 242.0,
	 PNALT = 228.0,
	 init_REF = NaN,
	 init_PMECH = NaN,
	 init_CM = NaN,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___77_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
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
  sexs reg_sexs__GEN___77_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_EFD = 0.3842592133098166,
	 init_VREF = 1.007921338028219,
	 init_YLL = 0.001921296066549083,
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
  gsteam0 reg_gsteam0__GEN___77_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_REF = 0.0,
	 init_PMECH = 0.0,
	 init_CM = 0.0,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___80_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_APREF = 0.289090909090909,
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
  sexs reg_sexs__GEN___80_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_EFD = 0.3934306321356991,
	 init_VREF = 1.042338650738597,
	 init_YLL = 0.001967153160678496,
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
  gsteam0 reg_gsteam0__GEN___80_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_REF = 0.01607417096386578,
	 init_PMECH = 0.3214834192773156,
	 init_CM = 0.3214834192773156,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___85_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_APREF = NaN,
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
  sexs reg_sexs__GEN___85_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_EFD = NaN,
	 init_VREF = NaN,
	 init_YLL = NaN,
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
  gsteam0 reg_gsteam0__GEN___85_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_REF = NaN,
	 init_PMECH = NaN,
	 init_CM = NaN,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___87_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_APREF = 0.002339181286549708,
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
  sexs reg_sexs__GEN___87_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_EFD = 0.29642462393069,
	 init_VREF = 1.016482254187548,
	 init_YLL = 0.00148212311965345,
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
  gsteam0 reg_gsteam0__GEN___87_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_REF = 0.0001299554822692854,
	 init_PMECH = 0.002599109645385708,
	 init_CM = 0.002599109645385708,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___89_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_APREF = 0.5012386457473164,
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
  sexs reg_sexs__GEN___89_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_EFD = 0.5394841023042883,
	 init_VREF = 1.008934199506815,
	 init_YLL = 0.002697420511521442,
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
  gsteam0 reg_gsteam0__GEN___89_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_REF = 0.02788267177522188,
	 init_PMECH = 0.5576534355044376,
	 init_CM = 0.5576534355044376,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___90_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_APREF = -0.07018992568125514,
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
  sexs reg_sexs__GEN___90_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_EFD = 0.3705191929017553,
	 init_VREF = 0.9868783260555997,
	 init_YLL = 0.001852595964508777,
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
  cmconst reg_cmconst__GEN___90_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_CM = -0.07796587773499833
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___91_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_APREF = -0.008257638315441785,
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
  sexs reg_sexs__GEN___91_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_EFD = 0.3628916116008083,
	 init_VREF = 0.9818148393773124,
	 init_YLL = 0.001814458058004042,
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
  cmconst reg_cmconst__GEN___91_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_CM = -0.009174091372900522
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___92_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
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
  sexs reg_sexs__GEN___92_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_EFD = 0.3732528855097978,
	 init_VREF = 0.9948662353404824,
	 init_YLL = 0.001866264427548989,
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
  gsteam0 reg_gsteam0__GEN___92_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_REF = 0.0,
	 init_PMECH = 0.0,
	 init_CM = 0.0,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___99_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_APREF = -0.02456140350877193,
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
  sexs reg_sexs__GEN___99_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_EFD = 0.2949044891460149,
	 init_VREF = 1.011477440508228,
	 init_YLL = 0.001474522445730074,
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
  cmconst reg_cmconst__GEN___99_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_CM = -0.02728829639189495
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN____1_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_APREF = NaN,
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
	 init_EFD = NaN,
	 init_VREF = NaN,
	 init_YLL = NaN,
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
	 init_REF = NaN,
	 init_PMECH = NaN,
	 init_CM = NaN,
	 DT = 0.,
	 RR = 0.05,
	 VMIN = 0.,
	 VMAX = 1.,
	 T1 = 0.5,
	 T2 = 3.,
	 T3 = 10.
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN____4_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_APREF = -0.008035714285714285,
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
  sexs reg_sexs__GEN____4_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_EFD = 0.32982382024355,
	 init_VREF = 0.9996494100542359,
	 init_YLL = 0.00164911910121775,
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
  cmconst reg_cmconst__GEN____4_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_CM = -0.008928314262525673
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN____6_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_APREF = NaN,
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
  sexs reg_sexs__GEN____6_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_EFD = NaN,
	 init_VREF = NaN,
	 init_YLL = NaN,
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
  gsteam0 reg_gsteam0__GEN____6_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_REF = NaN,
	 init_PMECH = NaN,
	 init_CM = NaN,
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
	 init_APREF = -0.01637426900584795,
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
	 init_EFD = 0.296698774178641,
	 init_VREF = 1.016484761587736,
	 init_YLL = 0.001483493870893205,
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
  cmconst reg_cmconst__GEN____8_SM (
	 SNREF = 100.0,
	 SN = 1710.0,
	 PN = 1539.0,
	 PNALT = 1539.0,
	 init_CM = -0.01819268520563741
	 ) annotation (Placement(transformation()));

equation
  omegaRef = (gen_pwGeneratorM2S__GEN__100_SM.omega*gen_pwGeneratorM2S__GEN__100_SM.SN*gen_pwGeneratorM2S__GEN__100_SM.HIn + gen_pwGeneratorM2S__GEN__103_SM.omega*gen_pwGeneratorM2S__GEN__103_SM.SN*gen_pwGeneratorM2S__GEN__103_SM.HIn + gen_pwGeneratorM2S__GEN__104_SM.omega*gen_pwGeneratorM2S__GEN__104_SM.SN*gen_pwGeneratorM2S__GEN__104_SM.HIn + gen_pwGeneratorM2S__GEN__105_SM.omega*gen_pwGeneratorM2S__GEN__105_SM.SN*gen_pwGeneratorM2S__GEN__105_SM.HIn + gen_pwGeneratorM2S__GEN__107_SM.omega*gen_pwGeneratorM2S__GEN__107_SM.SN*gen_pwGeneratorM2S__GEN__107_SM.HIn + gen_pwGeneratorM2S__GEN__110_SM.omega*gen_pwGeneratorM2S__GEN__110_SM.SN*gen_pwGeneratorM2S__GEN__110_SM.HIn + gen_pwGeneratorM2S__GEN__111_SM.omega*gen_pwGeneratorM2S__GEN__111_SM.SN*gen_pwGeneratorM2S__GEN__111_SM.HIn + gen_pwGeneratorM2S__GEN__112_SM.omega*gen_pwGeneratorM2S__GEN__112_SM.SN*gen_pwGeneratorM2S__GEN__112_SM.HIn + gen_pwGeneratorM2S__GEN__113_SM.omega*gen_pwGeneratorM2S__GEN__113_SM.SN*gen_pwGeneratorM2S__GEN__113_SM.HIn + gen_pwGeneratorM2S__GEN__116_SM.omega*gen_pwGeneratorM2S__GEN__116_SM.SN*gen_pwGeneratorM2S__GEN__116_SM.HIn + gen_pwGeneratorM2S__GEN___10_SM.omega*gen_pwGeneratorM2S__GEN___10_SM.SN*gen_pwGeneratorM2S__GEN___10_SM.HIn + gen_pwGeneratorM2S__GEN___12_SM.omega*gen_pwGeneratorM2S__GEN___12_SM.SN*gen_pwGeneratorM2S__GEN___12_SM.HIn + gen_pwGeneratorM2S__GEN___15_SM.omega*gen_pwGeneratorM2S__GEN___15_SM.SN*gen_pwGeneratorM2S__GEN___15_SM.HIn + gen_pwGeneratorM2S__GEN___18_SM.omega*gen_pwGeneratorM2S__GEN___18_SM.SN*gen_pwGeneratorM2S__GEN___18_SM.HIn + gen_pwGeneratorM2S__GEN___19_SM.omega*gen_pwGeneratorM2S__GEN___19_SM.SN*gen_pwGeneratorM2S__GEN___19_SM.HIn + gen_pwGeneratorM2S__GEN___24_SM.omega*gen_pwGeneratorM2S__GEN___24_SM.SN*gen_pwGeneratorM2S__GEN___24_SM.HIn + gen_pwGeneratorM2S__GEN___25_SM.omega*gen_pwGeneratorM2S__GEN___25_SM.SN*gen_pwGeneratorM2S__GEN___25_SM.HIn + gen_pwGeneratorM2S__GEN___26_SM.omega*gen_pwGeneratorM2S__GEN___26_SM.SN*gen_pwGeneratorM2S__GEN___26_SM.HIn + gen_pwGeneratorM2S__GEN___27_SM.omega*gen_pwGeneratorM2S__GEN___27_SM.SN*gen_pwGeneratorM2S__GEN___27_SM.HIn + gen_pwGeneratorM2S__GEN___31_SM.omega*gen_pwGeneratorM2S__GEN___31_SM.SN*gen_pwGeneratorM2S__GEN___31_SM.HIn + gen_pwGeneratorM2S__GEN___32_SM.omega*gen_pwGeneratorM2S__GEN___32_SM.SN*gen_pwGeneratorM2S__GEN___32_SM.HIn + gen_pwGeneratorM2S__GEN___34_SM.omega*gen_pwGeneratorM2S__GEN___34_SM.SN*gen_pwGeneratorM2S__GEN___34_SM.HIn + gen_pwGeneratorM2S__GEN___36_SM.omega*gen_pwGeneratorM2S__GEN___36_SM.SN*gen_pwGeneratorM2S__GEN___36_SM.HIn + gen_pwGeneratorM2S__GEN___40_SM.omega*gen_pwGeneratorM2S__GEN___40_SM.SN*gen_pwGeneratorM2S__GEN___40_SM.HIn + gen_pwGeneratorM2S__GEN___42_SM.omega*gen_pwGeneratorM2S__GEN___42_SM.SN*gen_pwGeneratorM2S__GEN___42_SM.HIn + gen_pwGeneratorM2S__GEN___46_SM.omega*gen_pwGeneratorM2S__GEN___46_SM.SN*gen_pwGeneratorM2S__GEN___46_SM.HIn + gen_pwGeneratorM2S__GEN___49_SM.omega*gen_pwGeneratorM2S__GEN___49_SM.SN*gen_pwGeneratorM2S__GEN___49_SM.HIn + gen_pwGeneratorM2S__GEN___54_SM.omega*gen_pwGeneratorM2S__GEN___54_SM.SN*gen_pwGeneratorM2S__GEN___54_SM.HIn + gen_pwGeneratorM2S__GEN___55_SM.omega*gen_pwGeneratorM2S__GEN___55_SM.SN*gen_pwGeneratorM2S__GEN___55_SM.HIn + gen_pwGeneratorM2S__GEN___56_SM.omega*gen_pwGeneratorM2S__GEN___56_SM.SN*gen_pwGeneratorM2S__GEN___56_SM.HIn + gen_pwGeneratorM2S__GEN___59_SM.omega*gen_pwGeneratorM2S__GEN___59_SM.SN*gen_pwGeneratorM2S__GEN___59_SM.HIn + gen_pwGeneratorM2S__GEN___61_SM.omega*gen_pwGeneratorM2S__GEN___61_SM.SN*gen_pwGeneratorM2S__GEN___61_SM.HIn + gen_pwGeneratorM2S__GEN___62_SM.omega*gen_pwGeneratorM2S__GEN___62_SM.SN*gen_pwGeneratorM2S__GEN___62_SM.HIn + gen_pwGeneratorM2S__GEN___65_SM.omega*gen_pwGeneratorM2S__GEN___65_SM.SN*gen_pwGeneratorM2S__GEN___65_SM.HIn + gen_pwGeneratorM2S__GEN___66_SM.omega*gen_pwGeneratorM2S__GEN___66_SM.SN*gen_pwGeneratorM2S__GEN___66_SM.HIn + gen_pwGeneratorM2S__GEN___69_SM.omega*gen_pwGeneratorM2S__GEN___69_SM.SN*gen_pwGeneratorM2S__GEN___69_SM.HIn + gen_pwGeneratorM2S__GEN___70_SM.omega*gen_pwGeneratorM2S__GEN___70_SM.SN*gen_pwGeneratorM2S__GEN___70_SM.HIn + gen_pwGeneratorM2S__GEN___72_SM.omega*gen_pwGeneratorM2S__GEN___72_SM.SN*gen_pwGeneratorM2S__GEN___72_SM.HIn + gen_pwGeneratorM2S__GEN___73_SM.omega*gen_pwGeneratorM2S__GEN___73_SM.SN*gen_pwGeneratorM2S__GEN___73_SM.HIn + gen_pwGeneratorM2S__GEN___74_SM.omega*gen_pwGeneratorM2S__GEN___74_SM.SN*gen_pwGeneratorM2S__GEN___74_SM.HIn + gen_pwGeneratorM2S__GEN___76_SM.omega*gen_pwGeneratorM2S__GEN___76_SM.SN*gen_pwGeneratorM2S__GEN___76_SM.HIn + gen_pwGeneratorM2S__GEN___77_SM.omega*gen_pwGeneratorM2S__GEN___77_SM.SN*gen_pwGeneratorM2S__GEN___77_SM.HIn + gen_pwGeneratorM2S__GEN___80_SM.omega*gen_pwGeneratorM2S__GEN___80_SM.SN*gen_pwGeneratorM2S__GEN___80_SM.HIn + gen_pwGeneratorM2S__GEN___85_SM.omega*gen_pwGeneratorM2S__GEN___85_SM.SN*gen_pwGeneratorM2S__GEN___85_SM.HIn + gen_pwGeneratorM2S__GEN___87_SM.omega*gen_pwGeneratorM2S__GEN___87_SM.SN*gen_pwGeneratorM2S__GEN___87_SM.HIn + gen_pwGeneratorM2S__GEN___89_SM.omega*gen_pwGeneratorM2S__GEN___89_SM.SN*gen_pwGeneratorM2S__GEN___89_SM.HIn + gen_pwGeneratorM2S__GEN___90_SM.omega*gen_pwGeneratorM2S__GEN___90_SM.SN*gen_pwGeneratorM2S__GEN___90_SM.HIn + gen_pwGeneratorM2S__GEN___91_SM.omega*gen_pwGeneratorM2S__GEN___91_SM.SN*gen_pwGeneratorM2S__GEN___91_SM.HIn + gen_pwGeneratorM2S__GEN___92_SM.omega*gen_pwGeneratorM2S__GEN___92_SM.SN*gen_pwGeneratorM2S__GEN___92_SM.HIn + gen_pwGeneratorM2S__GEN___99_SM.omega*gen_pwGeneratorM2S__GEN___99_SM.SN*gen_pwGeneratorM2S__GEN___99_SM.HIn + gen_pwGeneratorM2S__GEN____1_SM.omega*gen_pwGeneratorM2S__GEN____1_SM.SN*gen_pwGeneratorM2S__GEN____1_SM.HIn + gen_pwGeneratorM2S__GEN____4_SM.omega*gen_pwGeneratorM2S__GEN____4_SM.SN*gen_pwGeneratorM2S__GEN____4_SM.HIn + gen_pwGeneratorM2S__GEN____6_SM.omega*gen_pwGeneratorM2S__GEN____6_SM.SN*gen_pwGeneratorM2S__GEN____6_SM.HIn + gen_pwGeneratorM2S__GEN____8_SM.omega*gen_pwGeneratorM2S__GEN____8_SM.SN*gen_pwGeneratorM2S__GEN____8_SM.HIn) / (gen_pwGeneratorM2S__GEN__100_SM.SN*gen_pwGeneratorM2S__GEN__100_SM.HIn + gen_pwGeneratorM2S__GEN__103_SM.SN*gen_pwGeneratorM2S__GEN__103_SM.HIn + gen_pwGeneratorM2S__GEN__104_SM.SN*gen_pwGeneratorM2S__GEN__104_SM.HIn + gen_pwGeneratorM2S__GEN__105_SM.SN*gen_pwGeneratorM2S__GEN__105_SM.HIn + gen_pwGeneratorM2S__GEN__107_SM.SN*gen_pwGeneratorM2S__GEN__107_SM.HIn + gen_pwGeneratorM2S__GEN__110_SM.SN*gen_pwGeneratorM2S__GEN__110_SM.HIn + gen_pwGeneratorM2S__GEN__111_SM.SN*gen_pwGeneratorM2S__GEN__111_SM.HIn + gen_pwGeneratorM2S__GEN__112_SM.SN*gen_pwGeneratorM2S__GEN__112_SM.HIn + gen_pwGeneratorM2S__GEN__113_SM.SN*gen_pwGeneratorM2S__GEN__113_SM.HIn + gen_pwGeneratorM2S__GEN__116_SM.SN*gen_pwGeneratorM2S__GEN__116_SM.HIn + gen_pwGeneratorM2S__GEN___10_SM.SN*gen_pwGeneratorM2S__GEN___10_SM.HIn + gen_pwGeneratorM2S__GEN___12_SM.SN*gen_pwGeneratorM2S__GEN___12_SM.HIn + gen_pwGeneratorM2S__GEN___15_SM.SN*gen_pwGeneratorM2S__GEN___15_SM.HIn + gen_pwGeneratorM2S__GEN___18_SM.SN*gen_pwGeneratorM2S__GEN___18_SM.HIn + gen_pwGeneratorM2S__GEN___19_SM.SN*gen_pwGeneratorM2S__GEN___19_SM.HIn + gen_pwGeneratorM2S__GEN___24_SM.SN*gen_pwGeneratorM2S__GEN___24_SM.HIn + gen_pwGeneratorM2S__GEN___25_SM.SN*gen_pwGeneratorM2S__GEN___25_SM.HIn + gen_pwGeneratorM2S__GEN___26_SM.SN*gen_pwGeneratorM2S__GEN___26_SM.HIn + gen_pwGeneratorM2S__GEN___27_SM.SN*gen_pwGeneratorM2S__GEN___27_SM.HIn + gen_pwGeneratorM2S__GEN___31_SM.SN*gen_pwGeneratorM2S__GEN___31_SM.HIn + gen_pwGeneratorM2S__GEN___32_SM.SN*gen_pwGeneratorM2S__GEN___32_SM.HIn + gen_pwGeneratorM2S__GEN___34_SM.SN*gen_pwGeneratorM2S__GEN___34_SM.HIn + gen_pwGeneratorM2S__GEN___36_SM.SN*gen_pwGeneratorM2S__GEN___36_SM.HIn + gen_pwGeneratorM2S__GEN___40_SM.SN*gen_pwGeneratorM2S__GEN___40_SM.HIn + gen_pwGeneratorM2S__GEN___42_SM.SN*gen_pwGeneratorM2S__GEN___42_SM.HIn + gen_pwGeneratorM2S__GEN___46_SM.SN*gen_pwGeneratorM2S__GEN___46_SM.HIn + gen_pwGeneratorM2S__GEN___49_SM.SN*gen_pwGeneratorM2S__GEN___49_SM.HIn + gen_pwGeneratorM2S__GEN___54_SM.SN*gen_pwGeneratorM2S__GEN___54_SM.HIn + gen_pwGeneratorM2S__GEN___55_SM.SN*gen_pwGeneratorM2S__GEN___55_SM.HIn + gen_pwGeneratorM2S__GEN___56_SM.SN*gen_pwGeneratorM2S__GEN___56_SM.HIn + gen_pwGeneratorM2S__GEN___59_SM.SN*gen_pwGeneratorM2S__GEN___59_SM.HIn + gen_pwGeneratorM2S__GEN___61_SM.SN*gen_pwGeneratorM2S__GEN___61_SM.HIn + gen_pwGeneratorM2S__GEN___62_SM.SN*gen_pwGeneratorM2S__GEN___62_SM.HIn + gen_pwGeneratorM2S__GEN___65_SM.SN*gen_pwGeneratorM2S__GEN___65_SM.HIn + gen_pwGeneratorM2S__GEN___66_SM.SN*gen_pwGeneratorM2S__GEN___66_SM.HIn + gen_pwGeneratorM2S__GEN___69_SM.SN*gen_pwGeneratorM2S__GEN___69_SM.HIn + gen_pwGeneratorM2S__GEN___70_SM.SN*gen_pwGeneratorM2S__GEN___70_SM.HIn + gen_pwGeneratorM2S__GEN___72_SM.SN*gen_pwGeneratorM2S__GEN___72_SM.HIn + gen_pwGeneratorM2S__GEN___73_SM.SN*gen_pwGeneratorM2S__GEN___73_SM.HIn + gen_pwGeneratorM2S__GEN___74_SM.SN*gen_pwGeneratorM2S__GEN___74_SM.HIn + gen_pwGeneratorM2S__GEN___76_SM.SN*gen_pwGeneratorM2S__GEN___76_SM.HIn + gen_pwGeneratorM2S__GEN___77_SM.SN*gen_pwGeneratorM2S__GEN___77_SM.HIn + gen_pwGeneratorM2S__GEN___80_SM.SN*gen_pwGeneratorM2S__GEN___80_SM.HIn + gen_pwGeneratorM2S__GEN___85_SM.SN*gen_pwGeneratorM2S__GEN___85_SM.HIn + gen_pwGeneratorM2S__GEN___87_SM.SN*gen_pwGeneratorM2S__GEN___87_SM.HIn + gen_pwGeneratorM2S__GEN___89_SM.SN*gen_pwGeneratorM2S__GEN___89_SM.HIn + gen_pwGeneratorM2S__GEN___90_SM.SN*gen_pwGeneratorM2S__GEN___90_SM.HIn + gen_pwGeneratorM2S__GEN___91_SM.SN*gen_pwGeneratorM2S__GEN___91_SM.HIn + gen_pwGeneratorM2S__GEN___92_SM.SN*gen_pwGeneratorM2S__GEN___92_SM.HIn + gen_pwGeneratorM2S__GEN___99_SM.SN*gen_pwGeneratorM2S__GEN___99_SM.HIn + gen_pwGeneratorM2S__GEN____1_SM.SN*gen_pwGeneratorM2S__GEN____1_SM.HIn + gen_pwGeneratorM2S__GEN____4_SM.SN*gen_pwGeneratorM2S__GEN____4_SM.HIn + gen_pwGeneratorM2S__GEN____6_SM.SN*gen_pwGeneratorM2S__GEN____6_SM.HIn + gen_pwGeneratorM2S__GEN____8_SM.SN*gen_pwGeneratorM2S__GEN____8_SM.HIn);

  connect(gen_pwGeneratorM2S__GEN__100_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN__103_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN__104_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN__105_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN__107_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN__110_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN__111_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN__112_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN__113_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN__116_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___10_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___12_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___15_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___18_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___19_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___24_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___25_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___26_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___27_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___31_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___32_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___34_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___36_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___40_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___42_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___46_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___49_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___54_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___55_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___56_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___59_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___61_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___62_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___65_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___66_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___69_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___70_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___72_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___73_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___74_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___76_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___77_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___80_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___85_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___87_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___89_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___90_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___91_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___92_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN___99_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN____1_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN____4_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN____6_SM.omegaRef, omegaRef);
  connect(gen_pwGeneratorM2S__GEN____8_SM.omegaRef, omegaRef);

// Connecting REGULATORS and MACHINES
  connect(reg_pssi3e2b__GEN__100_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN__100_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN__100_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN__100_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN__100_SM.pin_EFD, gen_pwGeneratorM2S__GEN__100_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN__100_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN__100_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN__100_SM.pin_CM, gen_pwGeneratorM2S__GEN__100_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN__100_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN__100_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN__103_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN__103_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN__103_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN__103_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN__103_SM.pin_EFD, gen_pwGeneratorM2S__GEN__103_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN__103_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN__103_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN__103_SM.pin_CM, gen_pwGeneratorM2S__GEN__103_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN__103_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN__103_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN__104_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN__104_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN__104_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN__104_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN__104_SM.pin_EFD, gen_pwGeneratorM2S__GEN__104_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN__104_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN__104_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN__104_SM.pin_CM, gen_pwGeneratorM2S__GEN__104_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN__104_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN__104_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN__105_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN__105_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN__105_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN__105_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN__105_SM.pin_EFD, gen_pwGeneratorM2S__GEN__105_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN__105_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN__105_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN__105_SM.pin_CM, gen_pwGeneratorM2S__GEN__105_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN__105_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN__105_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN__107_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN__107_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN__107_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN__107_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN__107_SM.pin_EFD, gen_pwGeneratorM2S__GEN__107_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN__107_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN__107_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_cmconst__GEN__107_SM.pin_CM, gen_pwGeneratorM2S__GEN__107_SM.pin_CM) annotation (Line());
  connect(reg_pssi3e2b__GEN__110_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN__110_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN__110_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN__110_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN__110_SM.pin_EFD, gen_pwGeneratorM2S__GEN__110_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN__110_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN__110_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN__110_SM.pin_CM, gen_pwGeneratorM2S__GEN__110_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN__110_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN__110_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN__111_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN__111_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN__111_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN__111_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN__111_SM.pin_EFD, gen_pwGeneratorM2S__GEN__111_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN__111_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN__111_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN__111_SM.pin_CM, gen_pwGeneratorM2S__GEN__111_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN__111_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN__111_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN__112_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN__112_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN__112_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN__112_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN__112_SM.pin_EFD, gen_pwGeneratorM2S__GEN__112_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN__112_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN__112_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_cmconst__GEN__112_SM.pin_CM, gen_pwGeneratorM2S__GEN__112_SM.pin_CM) annotation (Line());
  connect(reg_pssi3e2b__GEN__113_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN__113_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN__113_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN__113_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN__113_SM.pin_EFD, gen_pwGeneratorM2S__GEN__113_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN__113_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN__113_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_cmconst__GEN__113_SM.pin_CM, gen_pwGeneratorM2S__GEN__113_SM.pin_CM) annotation (Line());
  connect(reg_pssi3e2b__GEN__116_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN__116_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN__116_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN__116_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN__116_SM.pin_EFD, gen_pwGeneratorM2S__GEN__116_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN__116_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN__116_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_cmconst__GEN__116_SM.pin_CM, gen_pwGeneratorM2S__GEN__116_SM.pin_CM) annotation (Line());
  connect(reg_pssi3e2b__GEN___10_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___10_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___10_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___10_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___10_SM.pin_EFD, gen_pwGeneratorM2S__GEN___10_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___10_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___10_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___10_SM.pin_CM, gen_pwGeneratorM2S__GEN___10_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___10_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___10_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___12_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___12_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___12_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___12_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___12_SM.pin_EFD, gen_pwGeneratorM2S__GEN___12_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___12_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___12_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___12_SM.pin_CM, gen_pwGeneratorM2S__GEN___12_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___12_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___12_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___15_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___15_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___15_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___15_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___15_SM.pin_EFD, gen_pwGeneratorM2S__GEN___15_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___15_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___15_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___15_SM.pin_CM, gen_pwGeneratorM2S__GEN___15_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___15_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___15_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___18_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___18_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___18_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___18_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___18_SM.pin_EFD, gen_pwGeneratorM2S__GEN___18_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___18_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___18_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___18_SM.pin_CM, gen_pwGeneratorM2S__GEN___18_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___18_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___18_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___19_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___19_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___19_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___19_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___19_SM.pin_EFD, gen_pwGeneratorM2S__GEN___19_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___19_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___19_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___19_SM.pin_CM, gen_pwGeneratorM2S__GEN___19_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___19_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___19_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___24_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___24_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___24_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___24_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___24_SM.pin_EFD, gen_pwGeneratorM2S__GEN___24_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___24_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___24_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_cmconst__GEN___24_SM.pin_CM, gen_pwGeneratorM2S__GEN___24_SM.pin_CM) annotation (Line());
  connect(reg_pssi3e2b__GEN___25_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___25_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___25_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___25_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___25_SM.pin_EFD, gen_pwGeneratorM2S__GEN___25_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___25_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___25_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___25_SM.pin_CM, gen_pwGeneratorM2S__GEN___25_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___25_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___25_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___26_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___26_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___26_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___26_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___26_SM.pin_EFD, gen_pwGeneratorM2S__GEN___26_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___26_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___26_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___26_SM.pin_CM, gen_pwGeneratorM2S__GEN___26_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___26_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___26_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___27_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___27_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___27_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___27_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___27_SM.pin_EFD, gen_pwGeneratorM2S__GEN___27_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___27_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___27_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_cmconst__GEN___27_SM.pin_CM, gen_pwGeneratorM2S__GEN___27_SM.pin_CM) annotation (Line());
  connect(reg_pssi3e2b__GEN___31_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___31_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___31_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___31_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___31_SM.pin_EFD, gen_pwGeneratorM2S__GEN___31_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___31_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___31_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___31_SM.pin_CM, gen_pwGeneratorM2S__GEN___31_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___31_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___31_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___32_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___32_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___32_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___32_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___32_SM.pin_EFD, gen_pwGeneratorM2S__GEN___32_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___32_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___32_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___32_SM.pin_CM, gen_pwGeneratorM2S__GEN___32_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___32_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___32_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___34_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___34_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___34_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___34_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___34_SM.pin_EFD, gen_pwGeneratorM2S__GEN___34_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___34_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___34_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___34_SM.pin_CM, gen_pwGeneratorM2S__GEN___34_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___34_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___34_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___36_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___36_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___36_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___36_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___36_SM.pin_EFD, gen_pwGeneratorM2S__GEN___36_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___36_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___36_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___36_SM.pin_CM, gen_pwGeneratorM2S__GEN___36_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___36_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___36_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___40_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___40_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___40_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___40_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___40_SM.pin_EFD, gen_pwGeneratorM2S__GEN___40_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___40_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___40_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_cmconst__GEN___40_SM.pin_CM, gen_pwGeneratorM2S__GEN___40_SM.pin_CM) annotation (Line());
  connect(reg_pssi3e2b__GEN___42_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___42_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___42_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___42_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___42_SM.pin_EFD, gen_pwGeneratorM2S__GEN___42_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___42_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___42_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_cmconst__GEN___42_SM.pin_CM, gen_pwGeneratorM2S__GEN___42_SM.pin_CM) annotation (Line());
  connect(reg_pssi3e2b__GEN___46_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___46_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___46_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___46_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___46_SM.pin_EFD, gen_pwGeneratorM2S__GEN___46_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___46_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___46_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___46_SM.pin_CM, gen_pwGeneratorM2S__GEN___46_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___46_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___46_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___49_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___49_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___49_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___49_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___49_SM.pin_EFD, gen_pwGeneratorM2S__GEN___49_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___49_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___49_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___49_SM.pin_CM, gen_pwGeneratorM2S__GEN___49_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___49_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___49_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___54_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___54_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___54_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___54_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___54_SM.pin_EFD, gen_pwGeneratorM2S__GEN___54_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___54_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___54_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___54_SM.pin_CM, gen_pwGeneratorM2S__GEN___54_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___54_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___54_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___55_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___55_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___55_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___55_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___55_SM.pin_EFD, gen_pwGeneratorM2S__GEN___55_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___55_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___55_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___55_SM.pin_CM, gen_pwGeneratorM2S__GEN___55_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___55_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___55_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___56_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___56_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___56_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___56_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___56_SM.pin_EFD, gen_pwGeneratorM2S__GEN___56_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___56_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___56_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___56_SM.pin_CM, gen_pwGeneratorM2S__GEN___56_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___56_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___56_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___59_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___59_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___59_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___59_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___59_SM.pin_EFD, gen_pwGeneratorM2S__GEN___59_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___59_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___59_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___59_SM.pin_CM, gen_pwGeneratorM2S__GEN___59_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___59_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___59_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___61_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___61_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___61_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___61_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___61_SM.pin_EFD, gen_pwGeneratorM2S__GEN___61_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___61_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___61_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___61_SM.pin_CM, gen_pwGeneratorM2S__GEN___61_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___61_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___61_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___62_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___62_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___62_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___62_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___62_SM.pin_EFD, gen_pwGeneratorM2S__GEN___62_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___62_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___62_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___62_SM.pin_CM, gen_pwGeneratorM2S__GEN___62_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___62_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___62_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___65_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___65_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___65_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___65_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___65_SM.pin_EFD, gen_pwGeneratorM2S__GEN___65_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___65_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___65_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___65_SM.pin_CM, gen_pwGeneratorM2S__GEN___65_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___65_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___65_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___66_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___66_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___66_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___66_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___66_SM.pin_EFD, gen_pwGeneratorM2S__GEN___66_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___66_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___66_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___66_SM.pin_CM, gen_pwGeneratorM2S__GEN___66_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___66_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___66_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___69_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___69_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___69_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___69_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___69_SM.pin_EFD, gen_pwGeneratorM2S__GEN___69_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___69_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___69_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___69_SM.pin_CM, gen_pwGeneratorM2S__GEN___69_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___69_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___69_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___70_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___70_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___70_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___70_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___70_SM.pin_EFD, gen_pwGeneratorM2S__GEN___70_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___70_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___70_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___70_SM.pin_CM, gen_pwGeneratorM2S__GEN___70_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___70_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___70_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___72_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___72_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___72_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___72_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___72_SM.pin_EFD, gen_pwGeneratorM2S__GEN___72_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___72_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___72_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_cmconst__GEN___72_SM.pin_CM, gen_pwGeneratorM2S__GEN___72_SM.pin_CM) annotation (Line());
  connect(reg_pssi3e2b__GEN___73_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___73_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___73_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___73_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___73_SM.pin_EFD, gen_pwGeneratorM2S__GEN___73_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___73_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___73_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_cmconst__GEN___73_SM.pin_CM, gen_pwGeneratorM2S__GEN___73_SM.pin_CM) annotation (Line());
  connect(reg_pssi3e2b__GEN___74_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___74_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___74_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___74_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___74_SM.pin_EFD, gen_pwGeneratorM2S__GEN___74_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___74_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___74_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___74_SM.pin_CM, gen_pwGeneratorM2S__GEN___74_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___74_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___74_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___76_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___76_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___76_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___76_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___76_SM.pin_EFD, gen_pwGeneratorM2S__GEN___76_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___76_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___76_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___76_SM.pin_CM, gen_pwGeneratorM2S__GEN___76_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___76_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___76_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___77_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___77_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___77_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___77_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___77_SM.pin_EFD, gen_pwGeneratorM2S__GEN___77_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___77_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___77_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___77_SM.pin_CM, gen_pwGeneratorM2S__GEN___77_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___77_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___77_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___80_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___80_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___80_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___80_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___80_SM.pin_EFD, gen_pwGeneratorM2S__GEN___80_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___80_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___80_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___80_SM.pin_CM, gen_pwGeneratorM2S__GEN___80_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___80_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___80_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___85_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___85_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___85_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___85_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___85_SM.pin_EFD, gen_pwGeneratorM2S__GEN___85_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___85_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___85_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___85_SM.pin_CM, gen_pwGeneratorM2S__GEN___85_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___85_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___85_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___87_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___87_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___87_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___87_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___87_SM.pin_EFD, gen_pwGeneratorM2S__GEN___87_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___87_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___87_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___87_SM.pin_CM, gen_pwGeneratorM2S__GEN___87_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___87_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___87_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___89_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___89_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___89_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___89_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___89_SM.pin_EFD, gen_pwGeneratorM2S__GEN___89_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___89_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___89_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___89_SM.pin_CM, gen_pwGeneratorM2S__GEN___89_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___89_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___89_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___90_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___90_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___90_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___90_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___90_SM.pin_EFD, gen_pwGeneratorM2S__GEN___90_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___90_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___90_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_cmconst__GEN___90_SM.pin_CM, gen_pwGeneratorM2S__GEN___90_SM.pin_CM) annotation (Line());
  connect(reg_pssi3e2b__GEN___91_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___91_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___91_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___91_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___91_SM.pin_EFD, gen_pwGeneratorM2S__GEN___91_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___91_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___91_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_cmconst__GEN___91_SM.pin_CM, gen_pwGeneratorM2S__GEN___91_SM.pin_CM) annotation (Line());
  connect(reg_pssi3e2b__GEN___92_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___92_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___92_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___92_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___92_SM.pin_EFD, gen_pwGeneratorM2S__GEN___92_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___92_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___92_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN___92_SM.pin_CM, gen_pwGeneratorM2S__GEN___92_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN___92_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___92_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___99_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN___99_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN___99_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN___99_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN___99_SM.pin_EFD, gen_pwGeneratorM2S__GEN___99_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN___99_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN___99_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_cmconst__GEN___99_SM.pin_CM, gen_pwGeneratorM2S__GEN___99_SM.pin_CM) annotation (Line());
  connect(reg_pssi3e2b__GEN____1_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN____1_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN____1_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____1_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN____1_SM.pin_EFD, gen_pwGeneratorM2S__GEN____1_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN____1_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN____1_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_gsteam0__GEN____1_SM.pin_CM, gen_pwGeneratorM2S__GEN____1_SM.pin_CM) annotation (Line());
  connect(reg_gsteam0__GEN____1_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____1_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____4_SM.pin_ActivePowerSN, gen_pwGeneratorM2S__GEN____4_SM.pin_ActivePowerSN) annotation (Line());
  connect(reg_pssi3e2b__GEN____4_SM.pin_OMEGA, gen_pwGeneratorM2S__GEN____4_SM.pin_OMEGA) annotation (Line());
  connect(reg_sexs__GEN____4_SM.pin_EFD, gen_pwGeneratorM2S__GEN____4_SM.pin_EFD) annotation (Line());
  connect(reg_sexs__GEN____4_SM.pin_TerminalVoltage, gen_pwGeneratorM2S__GEN____4_SM.pin_TerminalVoltage) annotation (Line());
  connect(reg_cmconst__GEN____4_SM.pin_CM, gen_pwGeneratorM2S__GEN____4_SM.pin_CM) annotation (Line());
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
  connect(reg_cmconst__GEN____8_SM.pin_CM, gen_pwGeneratorM2S__GEN____8_SM.pin_CM) annotation (Line());

// Connecting REGULATORS and REGULATORS
  connect(reg_pssi3e2b__GEN__100_SM.pin_VS, reg_sexs__GEN__100_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN__100_SM.pin_OMEGA, reg_gsteam0__GEN__100_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN__103_SM.pin_VS, reg_sexs__GEN__103_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN__103_SM.pin_OMEGA, reg_gsteam0__GEN__103_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN__104_SM.pin_VS, reg_sexs__GEN__104_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN__104_SM.pin_OMEGA, reg_gsteam0__GEN__104_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN__105_SM.pin_VS, reg_sexs__GEN__105_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN__105_SM.pin_OMEGA, reg_gsteam0__GEN__105_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN__107_SM.pin_VS, reg_sexs__GEN__107_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN__110_SM.pin_VS, reg_sexs__GEN__110_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN__110_SM.pin_OMEGA, reg_gsteam0__GEN__110_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN__111_SM.pin_VS, reg_sexs__GEN__111_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN__111_SM.pin_OMEGA, reg_gsteam0__GEN__111_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN__112_SM.pin_VS, reg_sexs__GEN__112_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN__113_SM.pin_VS, reg_sexs__GEN__113_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN__116_SM.pin_VS, reg_sexs__GEN__116_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___10_SM.pin_VS, reg_sexs__GEN___10_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___10_SM.pin_OMEGA, reg_gsteam0__GEN___10_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___12_SM.pin_VS, reg_sexs__GEN___12_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___12_SM.pin_OMEGA, reg_gsteam0__GEN___12_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___15_SM.pin_VS, reg_sexs__GEN___15_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___15_SM.pin_OMEGA, reg_gsteam0__GEN___15_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___18_SM.pin_VS, reg_sexs__GEN___18_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___18_SM.pin_OMEGA, reg_gsteam0__GEN___18_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___19_SM.pin_VS, reg_sexs__GEN___19_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___19_SM.pin_OMEGA, reg_gsteam0__GEN___19_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___24_SM.pin_VS, reg_sexs__GEN___24_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___25_SM.pin_VS, reg_sexs__GEN___25_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___25_SM.pin_OMEGA, reg_gsteam0__GEN___25_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___26_SM.pin_VS, reg_sexs__GEN___26_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___26_SM.pin_OMEGA, reg_gsteam0__GEN___26_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___27_SM.pin_VS, reg_sexs__GEN___27_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___31_SM.pin_VS, reg_sexs__GEN___31_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___31_SM.pin_OMEGA, reg_gsteam0__GEN___31_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___32_SM.pin_VS, reg_sexs__GEN___32_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___32_SM.pin_OMEGA, reg_gsteam0__GEN___32_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___34_SM.pin_VS, reg_sexs__GEN___34_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___34_SM.pin_OMEGA, reg_gsteam0__GEN___34_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___36_SM.pin_VS, reg_sexs__GEN___36_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___36_SM.pin_OMEGA, reg_gsteam0__GEN___36_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___40_SM.pin_VS, reg_sexs__GEN___40_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___42_SM.pin_VS, reg_sexs__GEN___42_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___46_SM.pin_VS, reg_sexs__GEN___46_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___46_SM.pin_OMEGA, reg_gsteam0__GEN___46_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___49_SM.pin_VS, reg_sexs__GEN___49_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___49_SM.pin_OMEGA, reg_gsteam0__GEN___49_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___54_SM.pin_VS, reg_sexs__GEN___54_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___54_SM.pin_OMEGA, reg_gsteam0__GEN___54_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___55_SM.pin_VS, reg_sexs__GEN___55_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___55_SM.pin_OMEGA, reg_gsteam0__GEN___55_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___56_SM.pin_VS, reg_sexs__GEN___56_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___56_SM.pin_OMEGA, reg_gsteam0__GEN___56_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___59_SM.pin_VS, reg_sexs__GEN___59_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___59_SM.pin_OMEGA, reg_gsteam0__GEN___59_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___61_SM.pin_VS, reg_sexs__GEN___61_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___61_SM.pin_OMEGA, reg_gsteam0__GEN___61_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___62_SM.pin_VS, reg_sexs__GEN___62_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___62_SM.pin_OMEGA, reg_gsteam0__GEN___62_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___65_SM.pin_VS, reg_sexs__GEN___65_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___65_SM.pin_OMEGA, reg_gsteam0__GEN___65_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___66_SM.pin_VS, reg_sexs__GEN___66_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___66_SM.pin_OMEGA, reg_gsteam0__GEN___66_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___69_SM.pin_VS, reg_sexs__GEN___69_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___69_SM.pin_OMEGA, reg_gsteam0__GEN___69_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___70_SM.pin_VS, reg_sexs__GEN___70_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___70_SM.pin_OMEGA, reg_gsteam0__GEN___70_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___72_SM.pin_VS, reg_sexs__GEN___72_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___73_SM.pin_VS, reg_sexs__GEN___73_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___74_SM.pin_VS, reg_sexs__GEN___74_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___74_SM.pin_OMEGA, reg_gsteam0__GEN___74_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___76_SM.pin_VS, reg_sexs__GEN___76_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___76_SM.pin_OMEGA, reg_gsteam0__GEN___76_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___77_SM.pin_VS, reg_sexs__GEN___77_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___77_SM.pin_OMEGA, reg_gsteam0__GEN___77_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___80_SM.pin_VS, reg_sexs__GEN___80_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___80_SM.pin_OMEGA, reg_gsteam0__GEN___80_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___85_SM.pin_VS, reg_sexs__GEN___85_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___85_SM.pin_OMEGA, reg_gsteam0__GEN___85_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___87_SM.pin_VS, reg_sexs__GEN___87_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___87_SM.pin_OMEGA, reg_gsteam0__GEN___87_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___89_SM.pin_VS, reg_sexs__GEN___89_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___89_SM.pin_OMEGA, reg_gsteam0__GEN___89_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___90_SM.pin_VS, reg_sexs__GEN___90_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___91_SM.pin_VS, reg_sexs__GEN___91_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___92_SM.pin_VS, reg_sexs__GEN___92_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN___92_SM.pin_OMEGA, reg_gsteam0__GEN___92_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN___99_SM.pin_VS, reg_sexs__GEN___99_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN____1_SM.pin_VS, reg_sexs__GEN____1_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN____1_SM.pin_OMEGA, reg_gsteam0__GEN____1_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____4_SM.pin_VS, reg_sexs__GEN____4_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN____6_SM.pin_VS, reg_sexs__GEN____6_SM.pin_VS) annotation (Line());
  connect(reg_pssi3e2b__GEN____6_SM.pin_OMEGA, reg_gsteam0__GEN____6_SM.pin_OMEGA) annotation (Line());
  connect(reg_pssi3e2b__GEN____8_SM.pin_VS, reg_sexs__GEN____8_SM.pin_VS) annotation (Line());

// Connecting LINES
  connect(bus__ADAM__20_TN.p, line__ADAM__20_JAY___21_1_AC.p) annotation (Line());
  connect(line__ADAM__20_JAY___21_1_AC.n, bus__JAY___21_TN.p) annotation (Line());
  connect(bus__BAIL__96_TN.p, line__BAIL__96_SUND__97_1_AC.p) annotation (Line());
  connect(line__BAIL__96_SUND__97_1_AC.n, bus__SUND__97_TN.p) annotation (Line());
  connect(bus__BEAV__85_TN.p, line__BEAV__85_CLIN__89_1_AC.p) annotation (Line());
  connect(line__BEAV__85_CLIN__89_1_AC.n, bus__CLIN__89_TN.p) annotation (Line());
  connect(bus__BEAV__85_TN.p, line__BEAV__85_FREM__88_1_AC.p) annotation (Line());
  connect(line__BEAV__85_FREM__88_1_AC.n, bus__FREM__88_TN.p) annotation (Line());
  connect(bus__BEAV__85_TN.p, line__BEAV__85_HAZA__86_1_AC.p) annotation (Line());
  connect(line__BEAV__85_HAZA__86_1_AC.n, bus__HAZA__86_TN.p) annotation (Line());
  connect(bus__BELL__74_TN.p, line__BELL__74_STHP__75_1_AC.p) annotation (Line());
  connect(line__BELL__74_STHP__75_1_AC.n, bus__STHP__75_TN.p) annotation (Line());
  connect(bus__BEQU___9_TN.p, line__BEQU___9_BREE__10_1_AC.p) annotation (Line());
  connect(line__BEQU___9_BREE__10_1_AC.n, bus__BREE__10_TN.p) annotation (Line());
  connect(bus__BETS__84_TN.p, line__BETS__84_BEAV__85_1_AC.p) annotation (Line());
  connect(line__BETS__84_BEAV__85_1_AC.n, bus__BEAV__85_TN.p) annotation (Line());
  connect(bus__BLAI_108_TN.p, line__BLAI_108_FRAN_109_1_AC.p) annotation (Line());
  connect(line__BLAI_108_FRAN_109_1_AC.n, bus__FRAN_109_TN.p) annotation (Line());
  connect(bus__BRAD__98_TN.p, line__BRAD__98_GLEN_100_1_AC.p) annotation (Line());
  connect(line__BRAD__98_GLEN_100_1_AC.n, bus__GLEN_100_TN.p) annotation (Line());
  connect(bus__CABI__80_TN.p, line__CABI__80_BAIL__96_1_AC.p) annotation (Line());
  connect(line__CABI__80_BAIL__96_1_AC.n, bus__BAIL__96_TN.p) annotation (Line());
  connect(bus__CABI__80_TN.p, line__CABI__80_BRAD__98_1_AC.p) annotation (Line());
  connect(line__CABI__80_BRAD__98_1_AC.n, bus__BRAD__98_TN.p) annotation (Line());
  connect(bus__CABI__80_TN.p, line__CABI__80_HINT__99_1_AC.p) annotation (Line());
  connect(line__CABI__80_HINT__99_1_AC.n, bus__HINT__99_TN.p) annotation (Line());
  connect(bus__CABI__80_TN.p, line__CABI__80_SUND__97_1_AC.p) annotation (Line());
  connect(line__CABI__80_SUND__97_1_AC.n, bus__SUND__97_TN.p) annotation (Line());
  connect(bus__CALD__95_TN.p, line__CALD__95_BAIL__96_1_AC.p) annotation (Line());
  connect(line__CALD__95_BAIL__96_1_AC.n, bus__BAIL__96_TN.p) annotation (Line());
  connect(bus__CAPI__79_TN.p, line__CAPI__79_CABI__80_1_AC.p) annotation (Line());
  connect(line__CAPI__79_CABI__80_1_AC.n, bus__CABI__80_TN.p) annotation (Line());
  connect(bus__CHEM__78_TN.p, line__CHEM__78_CAPI__79_1_AC.p) annotation (Line());
  connect(line__CHEM__78_CAPI__79_1_AC.n, bus__CAPI__79_TN.p) annotation (Line());
  connect(bus__CLAY_103_TN.p, line__CLAY_103_FIEL_110_1_AC.p) annotation (Line());
  connect(line__CLAY_103_FIEL_110_1_AC.n, bus__FIEL_110_TN.p) annotation (Line());
  connect(bus__CLAY_103_TN.p, line__CLAY_103_HANC_104_1_AC.p) annotation (Line());
  connect(line__CLAY_103_HANC_104_1_AC.n, bus__HANC_104_TN.p) annotation (Line());
  connect(bus__CLAY_103_TN.p, line__CLAY_103_ROAN_105_1_AC.p) annotation (Line());
  connect(line__CLAY_103_ROAN_105_1_AC.n, bus__ROAN_105_TN.p) annotation (Line());
  connect(bus__CLIN__89_TN.p, line__CLIN__89_HOLS__90_1_AC.p) annotation (Line());
  connect(line__CLIN__89_HOLS__90_1_AC.n, bus__HOLS__90_TN.p) annotation (Line());
  connect(bus__CLIN__89_TN.p, line__CLIN__89_HOLS__90_2_AC.p) annotation (Line());
  connect(line__CLIN__89_HOLS__90_2_AC.n, bus__HOLS__90_TN.p) annotation (Line());
  connect(bus__CLIN__89_TN.p, line__CLIN__89_SALT__92_1_AC.p) annotation (Line());
  connect(line__CLIN__89_SALT__92_1_AC.n, bus__SALT__92_TN.p) annotation (Line());
  connect(bus__CLIN__89_TN.p, line__CLIN__89_SALT__92_2_AC.p) annotation (Line());
  connect(line__CLIN__89_SALT__92_2_AC.n, bus__SALT__92_TN.p) annotation (Line());
  connect(bus__CLOV_106_TN.p, line__CLOV_106_REUS_107_1_AC.p) annotation (Line());
  connect(line__CLOV_106_REUS_107_1_AC.n, bus__REUS_107_TN.p) annotation (Line());
  connect(bus__COLL__23_TN.p, line__COLL__23_DELA__32_1_AC.p) annotation (Line());
  connect(line__COLL__23_DELA__32_1_AC.n, bus__DELA__32_TN.p) annotation (Line());
  connect(bus__COLL__23_TN.p, line__COLL__23_TANN__25_1_AC.p) annotation (Line());
  connect(line__COLL__23_TANN__25_1_AC.n, bus__TANN__25_TN.p) annotation (Line());
  connect(bus__COLL__23_TN.p, line__COLL__23_TREN__24_1_AC.p) annotation (Line());
  connect(line__COLL__23_TREN__24_1_AC.n, bus__TREN__24_TN.p) annotation (Line());
  connect(bus__CONC__13_TN.p, line__CONC__13_FTWA__15_1_AC.p) annotation (Line());
  connect(line__CONC__13_FTWA__15_1_AC.n, bus__FTWA__15_TN.p) annotation (Line());
  connect(bus__CROO__47_TN.p, line__CROO__47_PHIL__49_1_AC.p) annotation (Line());
  connect(line__CROO__47_PHIL__49_1_AC.n, bus__PHIL__49_TN.p) annotation (Line());
  connect(bus__CROO__47_TN.p, line__CROO__47_SPOR__69_1_AC.p) annotation (Line());
  connect(line__CROO__47_SPOR__69_1_AC.n, bus__SPOR__69_TN.p) annotation (Line());
  connect(bus__DARR__76_TN.p, line__DARR__76_TURN__77_1_AC.p) annotation (Line());
  connect(line__DARR__76_TURN__77_1_AC.n, bus__TURN__77_TN.p) annotation (Line());
  connect(bus__DARR__76_TN.p, line__DARR__76_WHUN_118_1_AC.p) annotation (Line());
  connect(line__DARR__76_WHUN_118_1_AC.n, bus__WHUN_118_TN.p) annotation (Line());
  connect(bus__DEER__31_TN.p, line__DEER__31_DELA__32_1_AC.p) annotation (Line());
  connect(line__DEER__31_DELA__32_1_AC.n, bus__DELA__32_TN.p) annotation (Line());
  connect(bus__DELA__32_TN.p, line__DELA__32_DEER_113_1_AC.p) annotation (Line());
  connect(line__DELA__32_DEER_113_1_AC.n, bus__DEER_113_TN.p) annotation (Line());
  connect(bus__DELA__32_TN.p, line__DELA__32_WMED_114_1_AC.p) annotation (Line());
  connect(line__DELA__32_WMED_114_1_AC.n, bus__WMED_114_TN.p) annotation (Line());
  connect(bus__EAST__37_TN.p, line__EAST__37_NWLI__39_1_AC.p) annotation (Line());
  connect(line__EAST__37_NWLI__39_1_AC.n, bus__NWLI__39_TN.p) annotation (Line());
  connect(bus__EAST__37_TN.p, line__EAST__37_WEST__40_1_AC.p) annotation (Line());
  connect(line__EAST__37_WEST__40_1_AC.n, bus__WEST__40_TN.p) annotation (Line());
  connect(bus__EAST__38_TN.p, line__EAST__38_MUSK__65_1_AC.p) annotation (Line());
  connect(line__EAST__38_MUSK__65_1_AC.n, bus__MUSK__65_TN.p) annotation (Line());
  connect(bus__FIEL_110_TN.p, line__FIEL_110_DANR_111_1_AC.p) annotation (Line());
  connect(line__FIEL_110_DANR_111_1_AC.n, bus__DANR_111_TN.p) annotation (Line());
  connect(bus__FIEL_110_TN.p, line__FIEL_110_DANV_112_1_AC.p) annotation (Line());
  connect(line__FIEL_110_DANV_112_1_AC.n, bus__DANV_112_TN.p) annotation (Line());
  connect(bus__FRAN_109_TN.p, line__FRAN_109_FIEL_110_1_AC.p) annotation (Line());
  connect(line__FRAN_109_FIEL_110_1_AC.n, bus__FIEL_110_TN.p) annotation (Line());
  connect(bus__FREM__88_TN.p, line__FREM__88_CLIN__89_1_AC.p) annotation (Line());
  connect(line__FREM__88_CLIN__89_1_AC.n, bus__CLIN__89_TN.p) annotation (Line());
  connect(bus__FTWA__15_TN.p, line__FTWA__15_HAVI__33_1_AC.p) annotation (Line());
  connect(line__FTWA__15_HAVI__33_1_AC.n, bus__HAVI__33_TN.p) annotation (Line());
  connect(bus__FTWA__15_TN.p, line__FTWA__15_LINC__19_1_AC.p) annotation (Line());
  connect(line__FTWA__15_LINC__19_1_AC.n, bus__LINC__19_TN.p) annotation (Line());
  connect(bus__FTWA__15_TN.p, line__FTWA__15_SORE__17_1_AC.p) annotation (Line());
  connect(line__FTWA__15_SORE__17_1_AC.n, bus__SORE__17_TN.p) annotation (Line());
  connect(bus__GLEN_100_TN.p, line__GLEN_100_CLAY_103_1_AC.p) annotation (Line());
  connect(line__GLEN_100_CLAY_103_1_AC.n, bus__CLAY_103_TN.p) annotation (Line());
  connect(bus__GLEN_100_TN.p, line__GLEN_100_CLOV_106_1_AC.p) annotation (Line());
  connect(line__GLEN_100_CLOV_106_1_AC.n, bus__CLOV_106_TN.p) annotation (Line());
  connect(bus__GLEN_100_TN.p, line__GLEN_100_HANC_104_1_AC.p) annotation (Line());
  connect(line__GLEN_100_HANC_104_1_AC.n, bus__HANC_104_TN.p) annotation (Line());
  connect(bus__GLEN_100_TN.p, line__GLEN_100_WYTH_101_1_AC.p) annotation (Line());
  connect(line__GLEN_100_WYTH_101_1_AC.n, bus__WYTH_101_TN.p) annotation (Line());
  connect(bus__GOSH__14_TN.p, line__GOSH__14_FTWA__15_1_AC.p) annotation (Line());
  connect(line__GOSH__14_FTWA__15_1_AC.n, bus__FTWA__15_TN.p) annotation (Line());
  connect(bus__GRAN__29_TN.p, line__GRAN__29_DEER__31_1_AC.p) annotation (Line());
  connect(line__GRAN__29_DEER__31_1_AC.n, bus__DEER__31_TN.p) annotation (Line());
  connect(bus__HANC_104_TN.p, line__HANC_104_ROAN_105_1_AC.p) annotation (Line());
  connect(line__HANC_104_ROAN_105_1_AC.n, bus__ROAN_105_TN.p) annotation (Line());
  connect(bus__HAVI__33_TN.p, line__HAVI__33_EAST__37_1_AC.p) annotation (Line());
  connect(line__HAVI__33_EAST__37_1_AC.n, bus__EAST__37_TN.p) annotation (Line());
  connect(bus__HAZA__86_TN.p, line__HAZA__86_PINE__87_1_AC.p) annotation (Line());
  connect(line__HAZA__86_PINE__87_1_AC.n, bus__PINE__87_TN.p) annotation (Line());
  connect(bus__HICK___3_TN.p, line__HICK___3_OLIV___5_1_AC.p) annotation (Line());
  connect(line__HICK___3_OLIV___5_1_AC.n, bus__OLIV___5_TN.p) annotation (Line());
  connect(bus__HICK___3_TN.p, line__HICK___3_TWIN__12_1_AC.p) annotation (Line());
  connect(line__HICK___3_TWIN__12_1_AC.n, bus__TWIN__12_TN.p) annotation (Line());
  connect(bus__HINT__99_TN.p, line__HINT__99_GLEN_100_1_AC.p) annotation (Line());
  connect(line__HINT__99_GLEN_100_1_AC.n, bus__GLEN_100_TN.p) annotation (Line());
  connect(bus__HOLS__90_TN.p, line__HOLS__90_HOLS__91_1_AC.p) annotation (Line());
  connect(line__HOLS__90_HOLS__91_1_AC.n, bus__HOLS__91_TN.p) annotation (Line());
  connect(bus__HOLS__91_TN.p, line__HOLS__91_SALT__92_1_AC.p) annotation (Line());
  connect(line__HOLS__91_SALT__92_1_AC.n, bus__SALT__92_TN.p) annotation (Line());
  connect(bus__HOWA__42_TN.p, line__HOWA__42_PHIL__49_1_AC.p) annotation (Line());
  connect(line__HOWA__42_PHIL__49_1_AC.n, bus__PHIL__49_TN.p) annotation (Line());
  connect(bus__HOWA__42_TN.p, line__HOWA__42_PHIL__49_2_AC.p) annotation (Line());
  connect(line__HOWA__42_PHIL__49_2_AC.n, bus__PHIL__49_TN.p) annotation (Line());
  connect(bus__JACK___7_TN.p, line__JACK___7_TWIN__12_1_AC.p) annotation (Line());
  connect(line__JACK___7_TWIN__12_1_AC.n, bus__TWIN__12_TN.p) annotation (Line());
  connect(bus__JAY___21_TN.p, line__JAY___21_RAND__22_1_AC.p) annotation (Line());
  connect(line__JAY___21_RAND__22_1_AC.n, bus__RAND__22_TN.p) annotation (Line());
  connect(bus__KAMM__64_TN.p, line__KAMM__64_MUSK__65_1_AC.p) annotation (Line());
  connect(line__KAMM__64_MUSK__65_1_AC.n, bus__MUSK__65_TN.p) annotation (Line());
  connect(bus__KANK___6_TN.p, line__KANK___6_JACK___7_1_AC.p) annotation (Line());
  connect(line__KANK___6_JACK___7_1_AC.n, bus__JACK___7_TN.p) annotation (Line());
  connect(bus__LINC__19_TN.p, line__LINC__19_ADAM__20_1_AC.p) annotation (Line());
  connect(line__LINC__19_ADAM__20_1_AC.n, bus__ADAM__20_TN.p) annotation (Line());
  connect(bus__LINC__19_TN.p, line__LINC__19_ROCK__34_1_AC.p) annotation (Line());
  connect(line__LINC__19_ROCK__34_1_AC.n, bus__ROCK__34_TN.p) annotation (Line());
  connect(bus__LOGA__82_TN.p, line__LOGA__82_BAIL__96_1_AC.p) annotation (Line());
  connect(line__LOGA__82_BAIL__96_1_AC.n, bus__BAIL__96_TN.p) annotation (Line());
  connect(bus__LOGA__82_TN.p, line__LOGA__82_SPRI__83_1_AC.p) annotation (Line());
  connect(line__LOGA__82_SPRI__83_1_AC.n, bus__SPRI__83_TN.p) annotation (Line());
  connect(bus__MADI__27_TN.p, line__MADI__27_DELA__32_1_AC.p) annotation (Line());
  connect(line__MADI__27_DELA__32_1_AC.n, bus__DELA__32_TN.p) annotation (Line());
  connect(bus__MADI__27_TN.p, line__MADI__27_MEDF_115_1_AC.p) annotation (Line());
  connect(line__MADI__27_MEDF_115_1_AC.n, bus__MEDF_115_TN.p) annotation (Line());
  connect(bus__MADI__27_TN.p, line__MADI__27_MULL__28_1_AC.p) annotation (Line());
  connect(line__MADI__27_MULL__28_1_AC.n, bus__MULL__28_TN.p) annotation (Line());
  connect(bus__MCKI__18_TN.p, line__MCKI__18_LINC__19_1_AC.p) annotation (Line());
  connect(line__MCKI__18_LINC__19_1_AC.n, bus__LINC__19_TN.p) annotation (Line());
  connect(bus__MULL__28_TN.p, line__MULL__28_GRAN__29_1_AC.p) annotation (Line());
  connect(line__MULL__28_GRAN__29_1_AC.n, bus__GRAN__29_TN.p) annotation (Line());
  connect(bus__MUSK__65_TN.p, line__MUSK__65_SPOR__68_1_AC.p) annotation (Line());
  connect(line__MUSK__65_SPOR__68_1_AC.n, bus__SPOR__68_TN.p) annotation (Line());
  connect(bus__MUSK__66_TN.p, line__MUSK__66_SUMM__67_1_AC.p) annotation (Line());
  connect(line__MUSK__66_SUMM__67_1_AC.n, bus__SUMM__67_TN.p) annotation (Line());
  connect(bus__N_NE__45_TN.p, line__N_NE__45_PHIL__49_1_AC.p) annotation (Line());
  connect(line__N_NE__45_PHIL__49_1_AC.n, bus__PHIL__49_TN.p) annotation (Line());
  connect(bus__N_NE__45_TN.p, line__N_NE__45_W_LA__46_1_AC.p) annotation (Line());
  connect(line__N_NE__45_W_LA__46_1_AC.n, bus__W_LA__46_TN.p) annotation (Line());
  connect(bus__N__E__16_TN.p, line__N__E__16_SORE__17_1_AC.p) annotation (Line());
  connect(line__N__E__16_SORE__17_1_AC.n, bus__SORE__17_TN.p) annotation (Line());
  connect(bus__NATR__62_TN.p, line__NATR__62_MUSK__66_1_AC.p) annotation (Line());
  connect(line__NATR__62_MUSK__66_1_AC.n, bus__MUSK__66_TN.p) annotation (Line());
  connect(bus__NATR__62_TN.p, line__NATR__62_SUMM__67_1_AC.p) annotation (Line());
  connect(line__NATR__62_SUMM__67_1_AC.n, bus__SUMM__67_TN.p) annotation (Line());
  connect(bus__NEWC__51_TN.p, line__NEWC__51_SCOS__52_1_AC.p) annotation (Line());
  connect(line__NEWC__51_SCOS__52_1_AC.n, bus__SCOS__52_TN.p) annotation (Line());
  connect(bus__NEWC__51_TN.p, line__NEWC__51_WNWP__58_1_AC.p) annotation (Line());
  connect(line__NEWC__51_WNWP__58_1_AC.n, bus__WNWP__58_TN.p) annotation (Line());
  connect(bus__NPOR__71_TN.p, line__NPOR__71_HILL__72_1_AC.p) annotation (Line());
  connect(line__NPOR__71_HILL__72_1_AC.n, bus__HILL__72_TN.p) annotation (Line());
  connect(bus__NPOR__71_TN.p, line__NPOR__71_SARG__73_1_AC.p) annotation (Line());
  connect(line__NPOR__71_SARG__73_1_AC.n, bus__SARG__73_TN.p) annotation (Line());
  connect(bus__NWCA___4_TN.p, line__NWCA___4_OLIV___5_1_AC.p) annotation (Line());
  connect(line__NWCA___4_OLIV___5_1_AC.n, bus__OLIV___5_TN.p) annotation (Line());
  connect(bus__NWCA___4_TN.p, line__NWCA___4_SOUT__11_1_AC.p) annotation (Line());
  connect(line__NWCA___4_SOUT__11_1_AC.n, bus__SOUT__11_TN.p) annotation (Line());
  connect(bus__NWLI__39_TN.p, line__NWLI__39_WEST__40_1_AC.p) annotation (Line());
  connect(line__NWLI__39_WEST__40_1_AC.n, bus__WEST__40_TN.p) annotation (Line());
  connect(bus__OLIV___5_TN.p, line__OLIV___5_KANK___6_1_AC.p) annotation (Line());
  connect(line__OLIV___5_KANK___6_1_AC.n, bus__KANK___6_TN.p) annotation (Line());
  connect(bus__OLIV___5_TN.p, line__OLIV___5_SOUT__11_1_AC.p) annotation (Line());
  connect(line__OLIV___5_SOUT__11_1_AC.n, bus__SOUT__11_TN.p) annotation (Line());
  connect(bus__OLIV___8_TN.p, line__OLIV___8_BEQU___9_1_AC.p) annotation (Line());
  connect(line__OLIV___8_BEQU___9_1_AC.n, bus__BEQU___9_TN.p) annotation (Line());
  connect(bus__OLIV___8_TN.p, line__OLIV___8_SORE__30_1_AC.p) annotation (Line());
  connect(line__OLIV___8_SORE__30_1_AC.n, bus__SORE__30_TN.p) annotation (Line());
  connect(bus__PHIL__49_TN.p, line__PHIL__49_MUSK__66_1_AC.p) annotation (Line());
  connect(line__PHIL__49_MUSK__66_1_AC.n, bus__MUSK__66_TN.p) annotation (Line());
  connect(bus__PHIL__49_TN.p, line__PHIL__49_MUSK__66_2_AC.p) annotation (Line());
  connect(line__PHIL__49_MUSK__66_2_AC.n, bus__MUSK__66_TN.p) annotation (Line());
  connect(bus__PHIL__49_TN.p, line__PHIL__49_NEWC__51_1_AC.p) annotation (Line());
  connect(line__PHIL__49_NEWC__51_1_AC.n, bus__NEWC__51_TN.p) annotation (Line());
  connect(bus__PHIL__49_TN.p, line__PHIL__49_SPOR__69_1_AC.p) annotation (Line());
  connect(line__PHIL__49_SPOR__69_1_AC.n, bus__SPOR__69_TN.p) annotation (Line());
  connect(bus__PHIL__49_TN.p, line__PHIL__49_TORR__54_1_AC.p) annotation (Line());
  connect(line__PHIL__49_TORR__54_1_AC.n, bus__TORR__54_TN.p) annotation (Line());
  connect(bus__PHIL__49_TN.p, line__PHIL__49_TORR__54_2_AC.p) annotation (Line());
  connect(line__PHIL__49_TORR__54_2_AC.n, bus__TORR__54_TN.p) annotation (Line());
  connect(bus__PHIL__49_TN.p, line__PHIL__49_WCAM__50_1_AC.p) annotation (Line());
  connect(line__PHIL__49_WCAM__50_1_AC.n, bus__WCAM__50_TN.p) annotation (Line());
  connect(bus__POKA___2_TN.p, line__POKA___2_TWIN__12_1_AC.p) annotation (Line());
  connect(line__POKA___2_TWIN__12_1_AC.n, bus__TWIN__12_TN.p) annotation (Line());
  connect(bus__PORT__70_TN.p, line__PORT__70_BELL__74_1_AC.p) annotation (Line());
  connect(line__PORT__70_BELL__74_1_AC.n, bus__BELL__74_TN.p) annotation (Line());
  connect(bus__PORT__70_TN.p, line__PORT__70_NPOR__71_1_AC.p) annotation (Line());
  connect(line__PORT__70_NPOR__71_1_AC.n, bus__NPOR__71_TN.p) annotation (Line());
  connect(bus__PORT__70_TN.p, line__PORT__70_STHP__75_1_AC.p) annotation (Line());
  connect(line__PORT__70_STHP__75_1_AC.n, bus__STHP__75_TN.p) annotation (Line());
  connect(bus__RAND__22_TN.p, line__RAND__22_COLL__23_1_AC.p) annotation (Line());
  connect(line__RAND__22_COLL__23_1_AC.n, bus__COLL__23_TN.p) annotation (Line());
  connect(bus__RIVE___1_TN.p, line__RIVE___1_HICK___3_1_AC.p) annotation (Line());
  connect(line__RIVE___1_HICK___3_1_AC.n, bus__HICK___3_TN.p) annotation (Line());
  connect(bus__RIVE___1_TN.p, line__RIVE___1_POKA___2_1_AC.p) annotation (Line());
  connect(line__RIVE___1_POKA___2_1_AC.n, bus__POKA___2_TN.p) annotation (Line());
  connect(bus__ROAN_105_TN.p, line__ROAN_105_BLAI_108_1_AC.p) annotation (Line());
  connect(line__ROAN_105_BLAI_108_1_AC.n, bus__BLAI_108_TN.p) annotation (Line());
  connect(bus__ROAN_105_TN.p, line__ROAN_105_CLOV_106_1_AC.p) annotation (Line());
  connect(line__ROAN_105_CLOV_106_1_AC.n, bus__CLOV_106_TN.p) annotation (Line());
  connect(bus__ROAN_105_TN.p, line__ROAN_105_REUS_107_1_AC.p) annotation (Line());
  connect(line__ROAN_105_REUS_107_1_AC.n, bus__REUS_107_TN.p) annotation (Line());
  connect(bus__ROCK__34_TN.p, line__ROCK__34_EAST__37_1_AC.p) annotation (Line());
  connect(line__ROCK__34_EAST__37_1_AC.n, bus__EAST__37_TN.p) annotation (Line());
  connect(bus__ROCK__34_TN.p, line__ROCK__34_S_KE__43_1_AC.p) annotation (Line());
  connect(line__ROCK__34_S_KE__43_1_AC.n, bus__S_KE__43_TN.p) annotation (Line());
  connect(bus__ROCK__34_TN.p, line__ROCK__34_STER__36_1_AC.p) annotation (Line());
  connect(line__ROCK__34_STER__36_1_AC.n, bus__STER__36_TN.p) annotation (Line());
  connect(bus__S_KE__43_TN.p, line__S_KE__43_WMVE__44_1_AC.p) annotation (Line());
  connect(line__S_KE__43_WMVE__44_1_AC.n, bus__WMVE__44_TN.p) annotation (Line());
  connect(bus__S_TI__41_TN.p, line__S_TI__41_HOWA__42_1_AC.p) annotation (Line());
  connect(line__S_TI__41_HOWA__42_1_AC.n, bus__HOWA__42_TN.p) annotation (Line());
  connect(bus__SALT__92_TN.p, line__SALT__92_GLEN_100_1_AC.p) annotation (Line());
  connect(line__SALT__92_GLEN_100_1_AC.n, bus__GLEN_100_TN.p) annotation (Line());
  connect(bus__SALT__92_TN.p, line__SALT__92_SMYT_102_1_AC.p) annotation (Line());
  connect(line__SALT__92_SMYT_102_1_AC.n, bus__SMYT_102_TN.p) annotation (Line());
  connect(bus__SALT__92_TN.p, line__SALT__92_SWIT__94_1_AC.p) annotation (Line());
  connect(line__SALT__92_SWIT__94_1_AC.n, bus__SWIT__94_TN.p) annotation (Line());
  connect(bus__SALT__92_TN.p, line__SALT__92_TAZE__93_1_AC.p) annotation (Line());
  connect(line__SALT__92_TAZE__93_1_AC.n, bus__TAZE__93_TN.p) annotation (Line());
  connect(bus__SCOS__52_TN.p, line__SCOS__52_WOOS__53_1_AC.p) annotation (Line());
  connect(line__SCOS__52_WOOS__53_1_AC.n, bus__WOOS__53_TN.p) annotation (Line());
  connect(bus__SORE__17_TN.p, line__SORE__17_DEER_113_1_AC.p) annotation (Line());
  connect(line__SORE__17_DEER_113_1_AC.n, bus__DEER_113_TN.p) annotation (Line());
  connect(bus__SORE__17_TN.p, line__SORE__17_DEER__31_1_AC.p) annotation (Line());
  connect(line__SORE__17_DEER__31_1_AC.n, bus__DEER__31_TN.p) annotation (Line());
  connect(bus__SORE__17_TN.p, line__SORE__17_MCKI__18_1_AC.p) annotation (Line());
  connect(line__SORE__17_MCKI__18_1_AC.n, bus__MCKI__18_TN.p) annotation (Line());
  connect(bus__SORE__30_TN.p, line__SORE__30_EAST__38_1_AC.p) annotation (Line());
  connect(line__SORE__30_EAST__38_1_AC.n, bus__EAST__38_TN.p) annotation (Line());
  connect(bus__SOUT__11_TN.p, line__SOUT__11_CONC__13_1_AC.p) annotation (Line());
  connect(line__SOUT__11_CONC__13_1_AC.n, bus__CONC__13_TN.p) annotation (Line());
  connect(bus__SOUT__11_TN.p, line__SOUT__11_TWIN__12_1_AC.p) annotation (Line());
  connect(line__SOUT__11_TWIN__12_1_AC.n, bus__TWIN__12_TN.p) annotation (Line());
  connect(bus__SPOR__68_TN.p, line__SPOR__68_KANA__81_1_AC.p) annotation (Line());
  connect(line__SPOR__68_KANA__81_1_AC.n, bus__KANA__81_TN.p) annotation (Line());
  connect(bus__SPOR__68_TN.p, line__SPOR__68_KYGE_116_1_AC.p) annotation (Line());
  connect(line__SPOR__68_KYGE_116_1_AC.n, bus__KYGE_116_TN.p) annotation (Line());
  connect(bus__SPOR__69_TN.p, line__SPOR__69_PORT__70_1_AC.p) annotation (Line());
  connect(line__SPOR__69_PORT__70_1_AC.n, bus__PORT__70_TN.p) annotation (Line());
  connect(bus__SPOR__69_TN.p, line__SPOR__69_STHP__75_1_AC.p) annotation (Line());
  connect(line__SPOR__69_STHP__75_1_AC.n, bus__STHP__75_TN.p) annotation (Line());
  connect(bus__SPOR__69_TN.p, line__SPOR__69_TURN__77_1_AC.p) annotation (Line());
  connect(line__SPOR__69_TURN__77_1_AC.n, bus__TURN__77_TN.p) annotation (Line());
  connect(bus__SPRI__83_TN.p, line__SPRI__83_BEAV__85_1_AC.p) annotation (Line());
  connect(line__SPRI__83_BEAV__85_1_AC.n, bus__BEAV__85_TN.p) annotation (Line());
  connect(bus__SPRI__83_TN.p, line__SPRI__83_BETS__84_1_AC.p) annotation (Line());
  connect(line__SPRI__83_BETS__84_1_AC.n, bus__BETS__84_TN.p) annotation (Line());
  connect(bus__STHP__75_TN.p, line__STHP__75_TURN__77_1_AC.p) annotation (Line());
  connect(line__STHP__75_TURN__77_1_AC.n, bus__TURN__77_TN.p) annotation (Line());
  connect(bus__STHP__75_TN.p, line__STHP__75_WHUN_118_1_AC.p) annotation (Line());
  connect(line__STHP__75_WHUN_118_1_AC.n, bus__WHUN_118_TN.p) annotation (Line());
  connect(bus__SUNN__56_TN.p, line__SUNN__56_TIDD__59_1_AC.p) annotation (Line());
  connect(line__SUNN__56_TIDD__59_1_AC.n, bus__TIDD__59_TN.p) annotation (Line());
  connect(bus__SUNN__56_TN.p, line__SUNN__56_TIDD__59_2_AC.p) annotation (Line());
  connect(line__SUNN__56_TIDD__59_2_AC.n, bus__TIDD__59_TN.p) annotation (Line());
  connect(bus__SUNN__56_TN.p, line__SUNN__56_WNWP__57_1_AC.p) annotation (Line());
  connect(line__SUNN__56_WNWP__57_1_AC.n, bus__WNWP__57_TN.p) annotation (Line());
  connect(bus__SUNN__56_TN.p, line__SUNN__56_WNWP__58_1_AC.p) annotation (Line());
  connect(line__SUNN__56_WNWP__58_1_AC.n, bus__WNWP__58_TN.p) annotation (Line());
  connect(bus__SWIT__94_TN.p, line__SWIT__94_BAIL__96_1_AC.p) annotation (Line());
  connect(line__SWIT__94_BAIL__96_1_AC.n, bus__BAIL__96_TN.p) annotation (Line());
  connect(bus__SWIT__94_TN.p, line__SWIT__94_CALD__95_1_AC.p) annotation (Line());
  connect(line__SWIT__94_CALD__95_1_AC.n, bus__CALD__95_TN.p) annotation (Line());
  connect(bus__SWIT__94_TN.p, line__SWIT__94_GLEN_100_1_AC.p) annotation (Line());
  connect(line__SWIT__94_GLEN_100_1_AC.n, bus__GLEN_100_TN.p) annotation (Line());
  connect(bus__SWKA__60_TN.p, line__SWKA__60_NATR__62_1_AC.p) annotation (Line());
  connect(line__SWKA__60_NATR__62_1_AC.n, bus__NATR__62_TN.p) annotation (Line());
  connect(bus__SWKA__60_TN.p, line__SWKA__60_W_KA__61_1_AC.p) annotation (Line());
  connect(line__SWKA__60_W_KA__61_1_AC.n, bus__W_KA__61_TN.p) annotation (Line());
  connect(bus__TANN__25_TN.p, line__TANN__25_MADI__27_1_AC.p) annotation (Line());
  connect(line__TANN__25_MADI__27_1_AC.n, bus__MADI__27_TN.p) annotation (Line());
  connect(bus__TANN__26_TN.p, line__TANN__26_SORE__30_1_AC.p) annotation (Line());
  connect(line__TANN__26_SORE__30_1_AC.n, bus__SORE__30_TN.p) annotation (Line());
  connect(bus__TAZE__93_TN.p, line__TAZE__93_SWIT__94_1_AC.p) annotation (Line());
  connect(line__TAZE__93_SWIT__94_1_AC.n, bus__SWIT__94_TN.p) annotation (Line());
  connect(bus__TIDD__59_TN.p, line__TIDD__59_SWKA__60_1_AC.p) annotation (Line());
  connect(line__TIDD__59_SWKA__60_1_AC.n, bus__SWKA__60_TN.p) annotation (Line());
  connect(bus__TIDD__59_TN.p, line__TIDD__59_W_KA__61_1_AC.p) annotation (Line());
  connect(line__TIDD__59_W_KA__61_1_AC.n, bus__W_KA__61_TN.p) annotation (Line());
  connect(bus__TIDD__63_TN.p, line__TIDD__63_KAMM__64_1_AC.p) annotation (Line());
  connect(line__TIDD__63_KAMM__64_1_AC.n, bus__KAMM__64_TN.p) annotation (Line());
  connect(bus__TORR__54_TN.p, line__TORR__54_SUNN__56_1_AC.p) annotation (Line());
  connect(line__TORR__54_SUNN__56_1_AC.n, bus__SUNN__56_TN.p) annotation (Line());
  connect(bus__TORR__54_TN.p, line__TORR__54_TIDD__59_1_AC.p) annotation (Line());
  connect(line__TORR__54_TIDD__59_1_AC.n, bus__TIDD__59_TN.p) annotation (Line());
  connect(bus__TORR__54_TN.p, line__TORR__54_WAGE__55_1_AC.p) annotation (Line());
  connect(line__TORR__54_WAGE__55_1_AC.n, bus__WAGE__55_TN.p) annotation (Line());
  connect(bus__TREN__24_TN.p, line__TREN__24_HILL__72_1_AC.p) annotation (Line());
  connect(line__TREN__24_HILL__72_1_AC.n, bus__HILL__72_TN.p) annotation (Line());
  connect(bus__TREN__24_TN.p, line__TREN__24_PORT__70_1_AC.p) annotation (Line());
  connect(line__TREN__24_PORT__70_1_AC.n, bus__PORT__70_TN.p) annotation (Line());
  connect(bus__TURN__77_TN.p, line__TURN__77_CABI__80_1_AC.p) annotation (Line());
  connect(line__TURN__77_CABI__80_1_AC.n, bus__CABI__80_TN.p) annotation (Line());
  connect(bus__TURN__77_TN.p, line__TURN__77_CABI__80_2_AC.p) annotation (Line());
  connect(line__TURN__77_CABI__80_2_AC.n, bus__CABI__80_TN.p) annotation (Line());
  connect(bus__TURN__77_TN.p, line__TURN__77_CHEM__78_1_AC.p) annotation (Line());
  connect(line__TURN__77_CHEM__78_1_AC.n, bus__CHEM__78_TN.p) annotation (Line());
  connect(bus__TURN__77_TN.p, line__TURN__77_LOGA__82_1_AC.p) annotation (Line());
  connect(line__TURN__77_LOGA__82_1_AC.n, bus__LOGA__82_TN.p) annotation (Line());
  connect(bus__TWIN__12_TN.p, line__TWIN__12_CORE_117_1_AC.p) annotation (Line());
  connect(line__TWIN__12_CORE_117_1_AC.n, bus__CORE_117_TN.p) annotation (Line());
  connect(bus__TWIN__12_TN.p, line__TWIN__12_GOSH__14_1_AC.p) annotation (Line());
  connect(line__TWIN__12_GOSH__14_1_AC.n, bus__GOSH__14_TN.p) annotation (Line());
  connect(bus__TWIN__12_TN.p, line__TWIN__12_N__E__16_1_AC.p) annotation (Line());
  connect(line__TWIN__12_N__E__16_1_AC.n, bus__N__E__16_TN.p) annotation (Line());
  connect(bus__W_KA__61_TN.p, line__W_KA__61_NATR__62_1_AC.p) annotation (Line());
  connect(line__W_KA__61_NATR__62_1_AC.n, bus__NATR__62_TN.p) annotation (Line());
  connect(bus__W_LA__46_TN.p, line__W_LA__46_CROO__47_1_AC.p) annotation (Line());
  connect(line__W_LA__46_CROO__47_1_AC.n, bus__CROO__47_TN.p) annotation (Line());
  connect(bus__W_LA__46_TN.p, line__W_LA__46_ZANE__48_1_AC.p) annotation (Line());
  connect(line__W_LA__46_ZANE__48_1_AC.n, bus__ZANE__48_TN.p) annotation (Line());
  connect(bus__WAGE__55_TN.p, line__WAGE__55_SUNN__56_1_AC.p) annotation (Line());
  connect(line__WAGE__55_SUNN__56_1_AC.n, bus__SUNN__56_TN.p) annotation (Line());
  connect(bus__WAGE__55_TN.p, line__WAGE__55_TIDD__59_1_AC.p) annotation (Line());
  connect(line__WAGE__55_TIDD__59_1_AC.n, bus__TIDD__59_TN.p) annotation (Line());
  connect(bus__WCAM__50_TN.p, line__WCAM__50_WNWP__57_1_AC.p) annotation (Line());
  connect(line__WCAM__50_WNWP__57_1_AC.n, bus__WNWP__57_TN.p) annotation (Line());
  connect(bus__WEST__35_TN.p, line__WEST__35_EAST__37_1_AC.p) annotation (Line());
  connect(line__WEST__35_EAST__37_1_AC.n, bus__EAST__37_TN.p) annotation (Line());
  connect(bus__WEST__35_TN.p, line__WEST__35_STER__36_1_AC.p) annotation (Line());
  connect(line__WEST__35_STER__36_1_AC.n, bus__STER__36_TN.p) annotation (Line());
  connect(bus__WEST__40_TN.p, line__WEST__40_HOWA__42_1_AC.p) annotation (Line());
  connect(line__WEST__40_HOWA__42_1_AC.n, bus__HOWA__42_TN.p) annotation (Line());
  connect(bus__WEST__40_TN.p, line__WEST__40_S_TI__41_1_AC.p) annotation (Line());
  connect(line__WEST__40_S_TI__41_1_AC.n, bus__S_TI__41_TN.p) annotation (Line());
  connect(bus__WMED_114_TN.p, line__WMED_114_MEDF_115_1_AC.p) annotation (Line());
  connect(line__WMED_114_MEDF_115_1_AC.n, bus__MEDF_115_TN.p) annotation (Line());
  connect(bus__WMVE__44_TN.p, line__WMVE__44_N_NE__45_1_AC.p) annotation (Line());
  connect(line__WMVE__44_N_NE__45_1_AC.n, bus__N_NE__45_TN.p) annotation (Line());
  connect(bus__WOOS__53_TN.p, line__WOOS__53_TORR__54_1_AC.p) annotation (Line());
  connect(line__WOOS__53_TORR__54_1_AC.n, bus__TORR__54_TN.p) annotation (Line());
  connect(bus__WYTH_101_TN.p, line__WYTH_101_SMYT_102_1_AC.p) annotation (Line());
  connect(line__WYTH_101_SMYT_102_1_AC.n, bus__SMYT_102_TN.p) annotation (Line());
  connect(bus__ZANE__48_TN.p, line__ZANE__48_PHIL__49_1_AC.p) annotation (Line());
  connect(line__ZANE__48_PHIL__49_1_AC.n, bus__PHIL__49_TN.p) annotation (Line());

// COUPLING DEVICES

// Connecting LOADS
  connect(bus__ADAM__20_TN.p, load__ADAM__20_EC.p) annotation (Line());
  connect(bus__BAIL__96_TN.p, load__BAIL__96_EC.p) annotation (Line());
  connect(bus__BEAV__85_TN.p, load__BEAV__85_EC.p) annotation (Line());
  connect(bus__BELL__74_TN.p, load__BELL__74_EC.p) annotation (Line());
  connect(bus__BETS__84_TN.p, load__BETS__84_EC.p) annotation (Line());
  connect(bus__BLAI_108_TN.p, load__BLAI_108_EC.p) annotation (Line());
  connect(bus__BRAD__98_TN.p, load__BRAD__98_EC.p) annotation (Line());
  connect(bus__CABI__80_TN.p, load__CABI__80_EC.p) annotation (Line());
  connect(bus__CALD__95_TN.p, load__CALD__95_EC.p) annotation (Line());
  connect(bus__CAPI__79_TN.p, load__CAPI__79_EC.p) annotation (Line());
  connect(bus__CHEM__78_TN.p, load__CHEM__78_EC.p) annotation (Line());
  connect(bus__CLAY_103_TN.p, load__CLAY_103_EC.p) annotation (Line());
  connect(bus__CLOV_106_TN.p, load__CLOV_106_EC.p) annotation (Line());
  connect(bus__COLL__23_TN.p, load__COLL__23_EC.p) annotation (Line());
  connect(bus__CONC__13_TN.p, load__CONC__13_EC.p) annotation (Line());
  connect(bus__CORE_117_TN.p, load__CORE_117_EC.p) annotation (Line());
  connect(bus__CROO__47_TN.p, load__CROO__47_EC.p) annotation (Line());
  connect(bus__DANV_112_TN.p, load__DANV_112_EC.p) annotation (Line());
  connect(bus__DARR__76_TN.p, load__DARR__76_EC.p) annotation (Line());
  connect(bus__DEER__31_TN.p, load__DEER__31_EC.p) annotation (Line());
  connect(bus__DELA__32_TN.p, load__DELA__32_EC.p) annotation (Line());
  connect(bus__FIEL_110_TN.p, load__FIEL_110_EC.p) annotation (Line());
  connect(bus__FRAN_109_TN.p, load__FRAN_109_EC.p) annotation (Line());
  connect(bus__FREM__88_TN.p, load__FREM__88_EC.p) annotation (Line());
  connect(bus__FTWA__15_TN.p, load__FTWA__15_EC.p) annotation (Line());
  connect(bus__GLEN_100_TN.p, load__GLEN_100_EC.p) annotation (Line());
  connect(bus__GOSH__14_TN.p, load__GOSH__14_EC.p) annotation (Line());
  connect(bus__GRAN__29_TN.p, load__GRAN__29_EC.p) annotation (Line());
  connect(bus__HANC_104_TN.p, load__HANC_104_EC.p) annotation (Line());
  connect(bus__HAVI__33_TN.p, load__HAVI__33_EC.p) annotation (Line());
  connect(bus__HAZA__86_TN.p, load__HAZA__86_EC.p) annotation (Line());
  connect(bus__HICK___3_TN.p, load__HICK___3_EC.p) annotation (Line());
  connect(bus__HOLS__90_TN.p, load__HOLS__90_EC.p) annotation (Line());
  connect(bus__HOWA__42_TN.p, load__HOWA__42_EC.p) annotation (Line());
  connect(bus__JACK___7_TN.p, load__JACK___7_EC.p) annotation (Line());
  connect(bus__JAY___21_TN.p, load__JAY___21_EC.p) annotation (Line());
  connect(bus__KANK___6_TN.p, load__KANK___6_EC.p) annotation (Line());
  connect(bus__LINC__19_TN.p, load__LINC__19_EC.p) annotation (Line());
  connect(bus__LOGA__82_TN.p, load__LOGA__82_EC.p) annotation (Line());
  connect(bus__MADI__27_TN.p, load__MADI__27_EC.p) annotation (Line());
  connect(bus__MCKI__18_TN.p, load__MCKI__18_EC.p) annotation (Line());
  connect(bus__MEDF_115_TN.p, load__MEDF_115_EC.p) annotation (Line());
  connect(bus__MULL__28_TN.p, load__MULL__28_EC.p) annotation (Line());
  connect(bus__MUSK__66_TN.p, load__MUSK__66_EC.p) annotation (Line());
  connect(bus__N_NE__45_TN.p, load__N_NE__45_EC.p) annotation (Line());
  connect(bus__N__E__16_TN.p, load__N__E__16_EC.p) annotation (Line());
  connect(bus__NATR__62_TN.p, load__NATR__62_EC.p) annotation (Line());
  connect(bus__NEWC__51_TN.p, load__NEWC__51_EC.p) annotation (Line());
  connect(bus__NWCA___4_TN.p, load__NWCA___4_EC.p) annotation (Line());
  connect(bus__NWLI__39_TN.p, load__NWLI__39_EC.p) annotation (Line());
  connect(bus__PHIL__49_TN.p, load__PHIL__49_EC.p) annotation (Line());
  connect(bus__POKA___2_TN.p, load__POKA___2_EC.p) annotation (Line());
  connect(bus__PORT__70_TN.p, load__PORT__70_EC.p) annotation (Line());
  connect(bus__RAND__22_TN.p, load__RAND__22_EC.p) annotation (Line());
  connect(bus__REUS_107_TN.p, load__REUS_107_EC.p) annotation (Line());
  connect(bus__RIVE___1_TN.p, load__RIVE___1_EC.p) annotation (Line());
  connect(bus__ROAN_105_TN.p, load__ROAN_105_EC.p) annotation (Line());
  connect(bus__ROCK__34_TN.p, load__ROCK__34_EC.p) annotation (Line());
  connect(bus__S_KE__43_TN.p, load__S_KE__43_EC.p) annotation (Line());
  connect(bus__S_TI__41_TN.p, load__S_TI__41_EC.p) annotation (Line());
  connect(bus__SALT__92_TN.p, load__SALT__92_EC.p) annotation (Line());
  connect(bus__SCOS__52_TN.p, load__SCOS__52_EC.p) annotation (Line());
  connect(bus__SMYT_102_TN.p, load__SMYT_102_EC.p) annotation (Line());
  connect(bus__SORE__17_TN.p, load__SORE__17_EC.p) annotation (Line());
  connect(bus__SOUT__11_TN.p, load__SOUT__11_EC.p) annotation (Line());
  connect(bus__SPRI__83_TN.p, load__SPRI__83_EC.p) annotation (Line());
  connect(bus__STER__36_TN.p, load__STER__36_EC.p) annotation (Line());
  connect(bus__STHP__75_TN.p, load__STHP__75_EC.p) annotation (Line());
  connect(bus__SUMM__67_TN.p, load__SUMM__67_EC.p) annotation (Line());
  connect(bus__SUND__97_TN.p, load__SUND__97_EC.p) annotation (Line());
  connect(bus__SUNN__56_TN.p, load__SUNN__56_EC.p) annotation (Line());
  connect(bus__SWIT__94_TN.p, load__SWIT__94_EC.p) annotation (Line());
  connect(bus__SWKA__60_TN.p, load__SWKA__60_EC.p) annotation (Line());
  connect(bus__TAZE__93_TN.p, load__TAZE__93_EC.p) annotation (Line());
  connect(bus__TIDD__59_TN.p, load__TIDD__59_EC.p) annotation (Line());
  connect(bus__TORR__54_TN.p, load__TORR__54_EC.p) annotation (Line());
  connect(bus__TURN__77_TN.p, load__TURN__77_EC.p) annotation (Line());
  connect(bus__TWIN__12_TN.p, load__TWIN__12_EC.p) annotation (Line());
  connect(bus__W_LA__46_TN.p, load__W_LA__46_EC.p) annotation (Line());
  connect(bus__WAGE__55_TN.p, load__WAGE__55_EC.p) annotation (Line());
  connect(bus__WCAM__50_TN.p, load__WCAM__50_EC.p) annotation (Line());
  connect(bus__WEST__35_TN.p, load__WEST__35_EC.p) annotation (Line());
  connect(bus__WEST__40_TN.p, load__WEST__40_EC.p) annotation (Line());
  connect(bus__WHUN_118_TN.p, load__WHUN_118_EC.p) annotation (Line());
  connect(bus__WMED_114_TN.p, load__WMED_114_EC.p) annotation (Line());
  connect(bus__WMVE__44_TN.p, load__WMVE__44_EC.p) annotation (Line());
  connect(bus__WNWP__57_TN.p, load__WNWP__57_EC.p) annotation (Line());
  connect(bus__WNWP__58_TN.p, load__WNWP__58_EC.p) annotation (Line());
  connect(bus__WOOS__53_TN.p, load__WOOS__53_EC.p) annotation (Line());
  connect(bus__WYTH_101_TN.p, load__WYTH_101_EC.p) annotation (Line());
  connect(bus__ZANE__48_TN.p, load__ZANE__48_EC.p) annotation (Line());

// Connecting Capacitors
  connect(bus__BELL__74_TN.p, cap__BELL__74_SC.p) annotation (Line());
  connect(bus__CAPI__79_TN.p, cap__CAPI__79_SC.p) annotation (Line());
  connect(bus__EAST__37_TN.p, cap__EAST__37_SC.p) annotation (Line());
  connect(bus__FIEL_110_TN.p, cap__FIEL_110_SC.p) annotation (Line());
  connect(bus__LOGA__82_TN.p, cap__LOGA__82_SC.p) annotation (Line());
  connect(bus__N_NE__45_TN.p, cap__N_NE__45_SC.p) annotation (Line());
  connect(bus__OLIV___5_TN.p, cap__OLIV___5_SC.p) annotation (Line());
  connect(bus__REUS_107_TN.p, cap__REUS_107_SC.p) annotation (Line());
  connect(bus__ROAN_105_TN.p, cap__ROAN_105_SC.p) annotation (Line());
  connect(bus__ROCK__34_TN.p, cap__ROCK__34_SC.p) annotation (Line());
  connect(bus__SPRI__83_TN.p, cap__SPRI__83_SC.p) annotation (Line());
  connect(bus__W_LA__46_TN.p, cap__W_LA__46_SC.p) annotation (Line());
  connect(bus__WMVE__44_TN.p, cap__WMVE__44_SC.p) annotation (Line());
  connect(bus__ZANE__48_TN.p, cap__ZANE__48_SC.p) annotation (Line());

// Connecting GENERATORS
  connect(bus__GLEN_100_TN.p, gen_pwGeneratorM2S__GEN__100_SM.sortie) annotation (Line());
  connect(bus__CLAY_103_TN.p, gen_pwGeneratorM2S__GEN__103_SM.sortie) annotation (Line());
  connect(bus__HANC_104_TN.p, gen_pwGeneratorM2S__GEN__104_SM.sortie) annotation (Line());
  connect(bus__ROAN_105_TN.p, gen_pwGeneratorM2S__GEN__105_SM.sortie) annotation (Line());
  connect(bus__REUS_107_TN.p, gen_pwGeneratorM2S__GEN__107_SM.sortie) annotation (Line());
  connect(bus__FIEL_110_TN.p, gen_pwGeneratorM2S__GEN__110_SM.sortie) annotation (Line());
  connect(bus__DANR_111_TN.p, gen_pwGeneratorM2S__GEN__111_SM.sortie) annotation (Line());
  connect(bus__DANV_112_TN.p, gen_pwGeneratorM2S__GEN__112_SM.sortie) annotation (Line());
  connect(bus__DEER_113_TN.p, gen_pwGeneratorM2S__GEN__113_SM.sortie) annotation (Line());
  connect(bus__KYGE_116_TN.p, gen_pwGeneratorM2S__GEN__116_SM.sortie) annotation (Line());
  connect(bus__BREE__10_TN.p, gen_pwGeneratorM2S__GEN___10_SM.sortie) annotation (Line());
  connect(bus__TWIN__12_TN.p, gen_pwGeneratorM2S__GEN___12_SM.sortie) annotation (Line());
  connect(bus__FTWA__15_TN.p, gen_pwGeneratorM2S__GEN___15_SM.sortie) annotation (Line());
  connect(bus__MCKI__18_TN.p, gen_pwGeneratorM2S__GEN___18_SM.sortie) annotation (Line());
  connect(bus__LINC__19_TN.p, gen_pwGeneratorM2S__GEN___19_SM.sortie) annotation (Line());
  connect(bus__TREN__24_TN.p, gen_pwGeneratorM2S__GEN___24_SM.sortie) annotation (Line());
  connect(bus__TANN__25_TN.p, gen_pwGeneratorM2S__GEN___25_SM.sortie) annotation (Line());
  connect(bus__TANN__26_TN.p, gen_pwGeneratorM2S__GEN___26_SM.sortie) annotation (Line());
  connect(bus__MADI__27_TN.p, gen_pwGeneratorM2S__GEN___27_SM.sortie) annotation (Line());
  connect(bus__DEER__31_TN.p, gen_pwGeneratorM2S__GEN___31_SM.sortie) annotation (Line());
  connect(bus__DELA__32_TN.p, gen_pwGeneratorM2S__GEN___32_SM.sortie) annotation (Line());
  connect(bus__ROCK__34_TN.p, gen_pwGeneratorM2S__GEN___34_SM.sortie) annotation (Line());
  connect(bus__STER__36_TN.p, gen_pwGeneratorM2S__GEN___36_SM.sortie) annotation (Line());
  connect(bus__WEST__40_TN.p, gen_pwGeneratorM2S__GEN___40_SM.sortie) annotation (Line());
  connect(bus__HOWA__42_TN.p, gen_pwGeneratorM2S__GEN___42_SM.sortie) annotation (Line());
  connect(bus__W_LA__46_TN.p, gen_pwGeneratorM2S__GEN___46_SM.sortie) annotation (Line());
  connect(bus__PHIL__49_TN.p, gen_pwGeneratorM2S__GEN___49_SM.sortie) annotation (Line());
  connect(bus__TORR__54_TN.p, gen_pwGeneratorM2S__GEN___54_SM.sortie) annotation (Line());
  connect(bus__WAGE__55_TN.p, gen_pwGeneratorM2S__GEN___55_SM.sortie) annotation (Line());
  connect(bus__SUNN__56_TN.p, gen_pwGeneratorM2S__GEN___56_SM.sortie) annotation (Line());
  connect(bus__TIDD__59_TN.p, gen_pwGeneratorM2S__GEN___59_SM.sortie) annotation (Line());
  connect(bus__W_KA__61_TN.p, gen_pwGeneratorM2S__GEN___61_SM.sortie) annotation (Line());
  connect(bus__NATR__62_TN.p, gen_pwGeneratorM2S__GEN___62_SM.sortie) annotation (Line());
  connect(bus__MUSK__65_TN.p, gen_pwGeneratorM2S__GEN___65_SM.sortie) annotation (Line());
  connect(bus__MUSK__66_TN.p, gen_pwGeneratorM2S__GEN___66_SM.sortie) annotation (Line());
  connect(bus__SPOR__69_TN.p, gen_pwGeneratorM2S__GEN___69_SM.sortie) annotation (Line());
  connect(bus__PORT__70_TN.p, gen_pwGeneratorM2S__GEN___70_SM.sortie) annotation (Line());
  connect(bus__HILL__72_TN.p, gen_pwGeneratorM2S__GEN___72_SM.sortie) annotation (Line());
  connect(bus__SARG__73_TN.p, gen_pwGeneratorM2S__GEN___73_SM.sortie) annotation (Line());
  connect(bus__BELL__74_TN.p, gen_pwGeneratorM2S__GEN___74_SM.sortie) annotation (Line());
  connect(bus__DARR__76_TN.p, gen_pwGeneratorM2S__GEN___76_SM.sortie) annotation (Line());
  connect(bus__TURN__77_TN.p, gen_pwGeneratorM2S__GEN___77_SM.sortie) annotation (Line());
  connect(bus__CABI__80_TN.p, gen_pwGeneratorM2S__GEN___80_SM.sortie) annotation (Line());
  connect(bus__BEAV__85_TN.p, gen_pwGeneratorM2S__GEN___85_SM.sortie) annotation (Line());
  connect(bus__PINE__87_TN.p, gen_pwGeneratorM2S__GEN___87_SM.sortie) annotation (Line());
  connect(bus__CLIN__89_TN.p, gen_pwGeneratorM2S__GEN___89_SM.sortie) annotation (Line());
  connect(bus__HOLS__90_TN.p, gen_pwGeneratorM2S__GEN___90_SM.sortie) annotation (Line());
  connect(bus__HOLS__91_TN.p, gen_pwGeneratorM2S__GEN___91_SM.sortie) annotation (Line());
  connect(bus__SALT__92_TN.p, gen_pwGeneratorM2S__GEN___92_SM.sortie) annotation (Line());
  connect(bus__HINT__99_TN.p, gen_pwGeneratorM2S__GEN___99_SM.sortie) annotation (Line());
  connect(bus__RIVE___1_TN.p, gen_pwGeneratorM2S__GEN____1_SM.sortie) annotation (Line());
  connect(bus__NWCA___4_TN.p, gen_pwGeneratorM2S__GEN____4_SM.sortie) annotation (Line());
  connect(bus__KANK___6_TN.p, gen_pwGeneratorM2S__GEN____6_SM.sortie) annotation (Line());
  connect(bus__OLIV___8_TN.p, gen_pwGeneratorM2S__GEN____8_SM.sortie) annotation (Line());

// Connecting DETAILED TRANSFORMERS
  connect(bus__EAST__38_TN.p, trafo__EAST__38_EAST__37_1_PT.p) annotation (Line());
  connect(trafo__EAST__38_EAST__37_1_PT.n, bus__EAST__37_TN.p) annotation (Line());
  connect(bus__KAMM__64_TN.p, trafo__KAMM__64_W_KA__61_1_PT.p) annotation (Line());
  connect(trafo__KAMM__64_W_KA__61_1_PT.n, bus__W_KA__61_TN.p) annotation (Line());
  connect(bus__KANA__81_TN.p, trafo__KANA__81_CABI__80_1_PT.p) annotation (Line());
  connect(trafo__KANA__81_CABI__80_1_PT.n, bus__CABI__80_TN.p) annotation (Line());
  connect(bus__MUSK__65_TN.p, trafo__MUSK__65_MUSK__66_1_PT.p) annotation (Line());
  connect(trafo__MUSK__65_MUSK__66_1_PT.n, bus__MUSK__66_TN.p) annotation (Line());
  connect(bus__OLIV___8_TN.p, trafo__OLIV___8_OLIV___5_1_PT.p) annotation (Line());
  connect(trafo__OLIV___8_OLIV___5_1_PT.n, bus__OLIV___5_TN.p) annotation (Line());
  connect(bus__SORE__30_TN.p, trafo__SORE__30_SORE__17_1_PT.p) annotation (Line());
  connect(trafo__SORE__30_SORE__17_1_PT.n, bus__SORE__17_TN.p) annotation (Line());
  connect(bus__SPOR__68_TN.p, trafo__SPOR__68_SPOR__69_1_PT.p) annotation (Line());
  connect(trafo__SPOR__68_SPOR__69_1_PT.n, bus__SPOR__69_TN.p) annotation (Line());
  connect(bus__TANN__26_TN.p, trafo__TANN__26_TANN__25_1_PT.p) annotation (Line());
  connect(trafo__TANN__26_TANN__25_1_PT.n, bus__TANN__25_TN.p) annotation (Line());
  connect(bus__TIDD__63_TN.p, trafo__TIDD__63_TIDD__59_1_PT.p) annotation (Line());
  connect(trafo__TIDD__63_TIDD__59_1_PT.n, bus__TIDD__59_TN.p) annotation (Line());

// Connecting OTHERS

// Modelica version required
  annotation (uses(Modelica(version="3.2.1")));
end ieee118bus;
