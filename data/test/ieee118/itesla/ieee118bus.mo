within ;
model ieee118bus
  parameter Real SNREF = 100.0;
  Modelica.Blocks.Interfaces.RealOutput omegaRef;


// BUSES
  iPSL.Electrical.Buses.Bus bus__ADAM__20_TN (
	 V_0 = 0.95801985,
	 angle_0 = 12.195883
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BAIL__96_TN (
	 V_0 = 0.99267226,
	 angle_0 = 27.535324
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BEAV__85_TN (
	 V_0 = 0.98499995,
	 angle_0 = 32.539337
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BELL__74_TN (
	 V_0 = 0.95799994,
	 angle_0 = 21.66834
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BEQU___9_TN (
	 V_0 = 1.0429182,
	 angle_0 = 28.299475
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BETS__84_TN (
	 V_0 = 0.9797527,
	 angle_0 = 30.98569
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BLAI_108_TN (
	 V_0 = 0.9675249,
	 angle_0 = 19.434359
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BRAD__98_TN (
	 V_0 = 1.0235091,
	 angle_0 = 27.430397
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__BREE__10_TN (
	 V_0 = 1.05,
	 angle_0 = 35.880375
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__CABI__80_TN (
	 V_0 = 1.0400001,
	 angle_0 = 28.984232
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__CALD__95_TN (
	 V_0 = 0.98092586,
	 angle_0 = 27.701435
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__CAPI__79_TN (
	 V_0 = 1.0092232,
	 angle_0 = 26.740734
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__CHEM__78_TN (
	 V_0 = 1.0034236,
	 angle_0 = 26.442318
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__CLAY_103_TN (
	 V_0 = 1.01,
	 angle_0 = 24.322319
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__CLIN__89_TN (
	 V_0 = 1.005,
	 angle_0 = 39.724552
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__CLOV_106_TN (
	 V_0 = 0.96257627,
	 angle_0 = 20.369125
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__COLL__23_TN (
	 V_0 = 0.9996551,
	 angle_0 = 21.255276
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__CONC__13_TN (
	 V_0 = 0.9684391,
	 angle_0 = 11.667593
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__CORE_117_TN (
	 V_0 = 0.97382444,
	 angle_0 = 10.990468
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__CROO__47_TN (
	 V_0 = 1.017052,
	 angle_0 = 20.799376
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__DANR_111_TN (
	 V_0 = 0.98,
	 angle_0 = 19.80394
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__DANV_112_TN (
	 V_0 = 0.975,
	 angle_0 = 15.059636
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__DARR__76_TN (
	 V_0 = 0.943,
	 angle_0 = 21.796701
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__DEER_113_TN (
	 V_0 = 0.993,
	 angle_0 = 14.01519
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__DEER__31_TN (
	 V_0 = 0.967,
	 angle_0 = 13.021424
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__DELA__32_TN (
	 V_0 = 0.96358764,
	 angle_0 = 15.067634
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__EAST__37_TN (
	 V_0 = 0.99115634,
	 angle_0 = 11.97247
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__EAST__38_TN (
	 V_0 = 0.9646022,
	 angle_0 = 17.119888
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__FIEL_110_TN (
	 V_0 = 0.973,
	 angle_0 = 18.158861
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__FRAN_109_TN (
	 V_0 = 0.9679819,
	 angle_0 = 18.98888
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__FREM__88_TN (
	 V_0 = 0.9874573,
	 angle_0 = 35.66962
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__FTWA__15_TN (
	 V_0 = 0.97,
	 angle_0 = 11.501194
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__GLEN_100_TN (
	 V_0 = 1.017,
	 angle_0 = 28.06078
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__GOSH__14_TN (
	 V_0 = 0.9835902,
	 angle_0 = 11.809951
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__GRAN__29_TN (
	 V_0 = 0.96321595,
	 angle_0 = 12.90428
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__HANC_104_TN (
	 V_0 = 0.971,
	 angle_0 = 21.750654
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__HAVI__33_TN (
	 V_0 = 0.97116864,
	 angle_0 = 10.871123
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__HAZA__86_TN (
	 V_0 = 0.9866907,
	 angle_0 = 31.16989
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__HICK___3_TN (
	 V_0 = 0.96793175,
	 angle_0 = 11.897829
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__HILL__72_TN (
	 V_0 = 0.98,
	 angle_0 = 21.113306
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__HINT__99_TN (
	 V_0 = 1.01,
	 angle_0 = 27.066473
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__HOLS__90_TN (
	 V_0 = 0.98499995,
	 angle_0 = 33.319588
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__HOLS__91_TN (
	 V_0 = 0.98,
	 angle_0 = 33.338398
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__HOWA__42_TN (
	 V_0 = 0.98499995,
	 angle_0 = 8.661391
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__JACK___7_TN (
	 V_0 = 0.9893279,
	 angle_0 = 12.890942
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__JAY___21_TN (
	 V_0 = 0.95859516,
	 angle_0 = 13.782877
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__KAMM__64_TN (
	 V_0 = 0.98156136,
	 angle_0 = 24.596266
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__KANA__81_TN (
	 V_0 = 0.99937993,
	 angle_0 = 28.131752
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__KANK___6_TN (
	 V_0 = 0.98999995,
	 angle_0 = 13.336111
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__KYGE_116_TN (
	 V_0 = 1.005,
	 angle_0 = 27.161371
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__LINC__19_TN (
	 V_0 = 0.9633706,
	 angle_0 = 11.317402
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__LOGA__82_TN (
	 V_0 = 0.9887433,
	 angle_0 = 27.263538
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__MADI__27_TN (
	 V_0 = 0.968,
	 angle_0 = 15.619753
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__MCKI__18_TN (
	 V_0 = 0.973,
	 angle_0 = 11.802565
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__MEDF_115_TN (
	 V_0 = 0.96032363,
	 angle_0 = 14.72951
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__MULL__28_TN (
	 V_0 = 0.9615682,
	 angle_0 = 13.895864
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__MUSK__65_TN (
	 V_0 = 1.005,
	 angle_0 = 27.717419
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__MUSK__66_TN (
	 V_0 = 1.05,
	 angle_0 = 27.557606
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N_NE__45_TN (
	 V_0 = 0.9865676,
	 angle_0 = 15.771421
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__N__E__16_TN (
	 V_0 = 0.9839673,
	 angle_0 = 12.222748
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__NATR__62_TN (
	 V_0 = 0.99799997,
	 angle_0 = 23.505695
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__NEWC__51_TN (
	 V_0 = 0.96687484,
	 angle_0 = 16.3627
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__NPOR__71_TN (
	 V_0 = 0.9868447,
	 angle_0 = 22.208725
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__NWCA___4_TN (
	 V_0 = 0.99799997,
	 angle_0 = 15.6175785
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__NWLI__39_TN (
	 V_0 = 0.97014475,
	 angle_0 = 8.587872
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__OLIV___5_TN (
	 V_0 = 1.0029731,
	 angle_0 = 16.049782
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__OLIV___8_TN (
	 V_0 = 1.015,
	 angle_0 = 21.045376
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__PHIL__49_TN (
	 V_0 = 1.025,
	 angle_0 = 21.021715
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__PINE__87_TN (
	 V_0 = 1.0150001,
	 angle_0 = 31.429102
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__POKA___2_TN (
	 V_0 = 0.9713931,
	 angle_0 = 11.556178
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__PORT__70_TN (
	 V_0 = 0.984,
	 angle_0 = 22.619165
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__RAND__22_TN (
	 V_0 = 0.96963227,
	 angle_0 = 16.33631
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__REUS_107_TN (
	 V_0 = 0.952,
	 angle_0 = 17.598299
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__RIVE___1_TN (
	 V_0 = 0.9549999,
	 angle_0 = 11.018172
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__ROAN_105_TN (
	 V_0 = 0.9671728,
	 angle_0 = 20.615025
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__ROCK__34_TN (
	 V_0 = 0.98526025,
	 angle_0 = 11.505611
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__S_KE__43_TN (
	 V_0 = 0.9780427,
	 angle_0 = 11.457416
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__S_TI__41_TN (
	 V_0 = 0.96683294,
	 angle_0 = 7.063605
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SALT__92_TN (
	 V_0 = 0.99227476,
	 angle_0 = 33.837193
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SARG__73_TN (
	 V_0 = 0.991,
	 angle_0 = 21.997234
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SCOS__52_TN (
	 V_0 = 0.95681596,
	 angle_0 = 15.409134
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SMYT_102_TN (
	 V_0 = 0.9909982,
	 angle_0 = 32.33335
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SORE__17_TN (
	 V_0 = 0.9953003,
	 angle_0 = 14.014746
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SORE__30_TN (
	 V_0 = 0.98621386,
	 angle_0 = 19.045132
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SOUT__11_TN (
	 V_0 = 0.9852648,
	 angle_0 = 13.0464115
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SPOR__68_TN (
	 V_0 = 1.0040141,
	 angle_0 = 27.592361
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SPOR__69_TN (
	 V_0 = 1.035,
	 angle_0 = 30.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SPRI__83_TN (
	 V_0 = 0.9845215,
	 angle_0 = 28.453094
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__STER__36_TN (
	 V_0 = 0.98,
	 angle_0 = 11.07225
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__STHP__75_TN (
	 V_0 = 0.96733195,
	 angle_0 = 22.929527
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SUMM__67_TN (
	 V_0 = 1.0196823,
	 angle_0 = 24.918766
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SUND__97_TN (
	 V_0 = 1.0113673,
	 angle_0 = 27.909279
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SUNN__56_TN (
	 V_0 = 0.95399994,
	 angle_0 = 15.241966
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SWIT__94_TN (
	 V_0 = 0.9905738,
	 angle_0 = 28.672297
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__SWKA__60_TN (
	 V_0 = 0.9931558,
	 angle_0 = 23.231045
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__TANN__25_TN (
	 V_0 = 1.05,
	 angle_0 = 28.188683
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__TANN__26_TN (
	 V_0 = 1.015,
	 angle_0 = 29.968342
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__TAZE__93_TN (
	 V_0 = 0.9869051,
	 angle_0 = 30.826227
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__TIDD__59_TN (
	 V_0 = 0.98499995,
	 angle_0 = 19.443115
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__TIDD__63_TN (
	 V_0 = 0.96733904,
	 angle_0 = 22.82232
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__TORR__54_TN (
	 V_0 = 0.9549999,
	 angle_0 = 15.345243
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__TREN__24_TN (
	 V_0 = 0.992,
	 angle_0 = 21.121763
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__TURN__77_TN (
	 V_0 = 1.006,
	 angle_0 = 26.746555
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__TWIN__12_TN (
	 V_0 = 0.98999995,
	 angle_0 = 12.531468
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__W_KA__61_TN (
	 V_0 = 0.995,
	 angle_0 = 24.123001
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__W_LA__46_TN (
	 V_0 = 1.005,
	 angle_0 = 18.576406
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__WAGE__55_TN (
	 V_0 = 0.952,
	 angle_0 = 15.055186
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__WCAM__50_TN (
	 V_0 = 1.0010817,
	 angle_0 = 18.982258
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__WEST__35_TN (
	 V_0 = 0.9805373,
	 angle_0 = 11.070006
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__WEST__40_TN (
	 V_0 = 0.97,
	 angle_0 = 7.5088577
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__WHUN_118_TN (
	 V_0 = 0.94943744,
	 angle_0 = 21.940535
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__WMED_114_TN (
	 V_0 = 0.96043575,
	 angle_0 = 14.737192
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__WMVE__44_TN (
	 V_0 = 0.9848248,
	 angle_0 = 13.94143
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__WNWP__57_TN (
	 V_0 = 0.9705814,
	 angle_0 = 16.447317
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__WNWP__58_TN (
	 V_0 = 0.9590374,
	 angle_0 = 15.5904
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__WOOS__53_TN (
	 V_0 = 0.9459818,
	 angle_0 = 14.433802
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__WYTH_101_TN (
	 V_0 = 0.99243355,
	 angle_0 = 29.634571
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__ZANE__48_TN (
	 V_0 = 1.0206336,
	 angle_0 = 20.018724
	 ) annotation (Placement(transformation()));

// LOADS
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__ADAM__20_EC (
	 V_0 = 0.95801985,
	 P_0 = 18.0,
	 Q_0 = 3.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 12.195883
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BAIL__96_EC (
	 V_0 = 0.99267226,
	 P_0 = 38.0,
	 Q_0 = 15.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 27.535324
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BEAV__85_EC (
	 V_0 = 0.98499995,
	 P_0 = 24.0,
	 Q_0 = 15.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 32.539337
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BELL__74_EC (
	 V_0 = 0.95799994,
	 P_0 = 68.0,
	 Q_0 = 27.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 21.66834
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BETS__84_EC (
	 V_0 = 0.9797527,
	 P_0 = 11.0,
	 Q_0 = 7.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 30.98569
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BLAI_108_EC (
	 V_0 = 0.9675249,
	 P_0 = 2.0,
	 Q_0 = 1.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 19.434359
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__BRAD__98_EC (
	 V_0 = 1.0235091,
	 P_0 = 34.0,
	 Q_0 = 8.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 27.430397
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__CABI__80_EC (
	 V_0 = 1.0400001,
	 P_0 = 130.0,
	 Q_0 = 26.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 28.984232
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__CALD__95_EC (
	 V_0 = 0.98092586,
	 P_0 = 42.0,
	 Q_0 = 31.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 27.701435
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__CAPI__79_EC (
	 V_0 = 1.0092232,
	 P_0 = 39.0,
	 Q_0 = 32.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 26.740734
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__CHEM__78_EC (
	 V_0 = 1.0034236,
	 P_0 = 71.0,
	 Q_0 = 26.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 26.442318
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__CLAY_103_EC (
	 V_0 = 1.01,
	 P_0 = 23.0,
	 Q_0 = 16.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 24.322319
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__CLOV_106_EC (
	 V_0 = 0.96257627,
	 P_0 = 43.0,
	 Q_0 = 16.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 20.369125
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__COLL__23_EC (
	 V_0 = 0.9996551,
	 P_0 = 7.0,
	 Q_0 = 3.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 21.255276
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__CONC__13_EC (
	 V_0 = 0.9684391,
	 P_0 = 34.0,
	 Q_0 = 16.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 11.667593
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__CORE_117_EC (
	 V_0 = 0.97382444,
	 P_0 = 20.0,
	 Q_0 = 8.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 10.990468
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__CROO__47_EC (
	 V_0 = 1.017052,
	 P_0 = 34.0,
	 Q_0 = 0.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 20.799376
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__DANV_112_EC (
	 V_0 = 0.975,
	 P_0 = 25.0,
	 Q_0 = 13.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 15.059636
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__DARR__76_EC (
	 V_0 = 0.943,
	 P_0 = 68.0,
	 Q_0 = 36.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 21.796701
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__DEER__31_EC (
	 V_0 = 0.967,
	 P_0 = 43.0,
	 Q_0 = 27.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 13.021424
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__DELA__32_EC (
	 V_0 = 0.96358764,
	 P_0 = 59.0,
	 Q_0 = 23.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 15.067634
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__FIEL_110_EC (
	 V_0 = 0.973,
	 P_0 = 39.0,
	 Q_0 = 30.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 18.158861
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__FRAN_109_EC (
	 V_0 = 0.9679819,
	 P_0 = 8.0,
	 Q_0 = 3.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 18.98888
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__FREM__88_EC (
	 V_0 = 0.9874573,
	 P_0 = 48.0,
	 Q_0 = 10.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 35.66962
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__FTWA__15_EC (
	 V_0 = 0.97,
	 P_0 = 90.0,
	 Q_0 = 30.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 11.501194
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__GLEN_100_EC (
	 V_0 = 1.017,
	 P_0 = 37.0,
	 Q_0 = 18.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 28.06078
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__GOSH__14_EC (
	 V_0 = 0.9835902,
	 P_0 = 14.0,
	 Q_0 = 1.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 11.809951
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__GRAN__29_EC (
	 V_0 = 0.96321595,
	 P_0 = 24.0,
	 Q_0 = 4.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 12.90428
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__HANC_104_EC (
	 V_0 = 0.971,
	 P_0 = 38.0,
	 Q_0 = 25.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 21.750654
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__HAVI__33_EC (
	 V_0 = 0.97116864,
	 P_0 = 23.0,
	 Q_0 = 9.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 10.871123
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__HAZA__86_EC (
	 V_0 = 0.9866907,
	 P_0 = 21.0,
	 Q_0 = 10.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 31.16989
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__HICK___3_EC (
	 V_0 = 0.96793175,
	 P_0 = 39.0,
	 Q_0 = 10.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 11.897829
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__HOLS__90_EC (
	 V_0 = 0.98499995,
	 P_0 = 78.0,
	 Q_0 = 42.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 33.319588
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__HOWA__42_EC (
	 V_0 = 0.98499995,
	 P_0 = 37.0,
	 Q_0 = 23.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 8.661391
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__JACK___7_EC (
	 V_0 = 0.9893279,
	 P_0 = 19.0,
	 Q_0 = 2.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 12.890942
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__JAY___21_EC (
	 V_0 = 0.95859516,
	 P_0 = 14.0,
	 Q_0 = 8.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 13.782877
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__KANK___6_EC (
	 V_0 = 0.98999995,
	 P_0 = 52.0,
	 Q_0 = 22.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 13.336111
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LINC__19_EC (
	 V_0 = 0.9633706,
	 P_0 = 45.0,
	 Q_0 = 25.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 11.317402
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__LOGA__82_EC (
	 V_0 = 0.9887433,
	 P_0 = 54.0,
	 Q_0 = 27.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 27.263538
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__MADI__27_EC (
	 V_0 = 0.968,
	 P_0 = 62.0,
	 Q_0 = 13.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 15.619753
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__MCKI__18_EC (
	 V_0 = 0.973,
	 P_0 = 60.0,
	 Q_0 = 34.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 11.802565
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__MEDF_115_EC (
	 V_0 = 0.96032363,
	 P_0 = 22.0,
	 Q_0 = 7.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 14.72951
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__MULL__28_EC (
	 V_0 = 0.9615682,
	 P_0 = 17.0,
	 Q_0 = 7.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 13.895864
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__MUSK__66_EC (
	 V_0 = 1.05,
	 P_0 = 39.0,
	 Q_0 = 18.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 27.557606
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__N_NE__45_EC (
	 V_0 = 0.9865676,
	 P_0 = 53.0,
	 Q_0 = 22.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 15.771421
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__N__E__16_EC (
	 V_0 = 0.9839673,
	 P_0 = 25.0,
	 Q_0 = 10.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 12.222748
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__NATR__62_EC (
	 V_0 = 0.99799997,
	 P_0 = 77.0,
	 Q_0 = 14.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 23.505695
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__NEWC__51_EC (
	 V_0 = 0.96687484,
	 P_0 = 17.0,
	 Q_0 = 8.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 16.3627
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__NWCA___4_EC (
	 V_0 = 0.99799997,
	 P_0 = 30.0,
	 Q_0 = 12.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 15.6175785
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__NWLI__39_EC (
	 V_0 = 0.97014475,
	 P_0 = 27.0,
	 Q_0 = 11.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 8.587872
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__PHIL__49_EC (
	 V_0 = 1.025,
	 P_0 = 87.0,
	 Q_0 = 30.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 21.021715
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__POKA___2_EC (
	 V_0 = 0.9713931,
	 P_0 = 20.0,
	 Q_0 = 9.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 11.556178
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__PORT__70_EC (
	 V_0 = 0.984,
	 P_0 = 66.0,
	 Q_0 = 20.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 22.619165
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__RAND__22_EC (
	 V_0 = 0.96963227,
	 P_0 = 10.0,
	 Q_0 = 5.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 16.33631
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__REUS_107_EC (
	 V_0 = 0.952,
	 P_0 = 28.0,
	 Q_0 = 12.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 17.598299
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__RIVE___1_EC (
	 V_0 = 0.9549999,
	 P_0 = 51.0,
	 Q_0 = 27.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 11.018172
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__ROAN_105_EC (
	 V_0 = 0.9671728,
	 P_0 = 31.0,
	 Q_0 = 26.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 20.615025
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__ROCK__34_EC (
	 V_0 = 0.98526025,
	 P_0 = 59.0,
	 Q_0 = 26.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 11.505611
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__S_KE__43_EC (
	 V_0 = 0.9780427,
	 P_0 = 18.0,
	 Q_0 = 7.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 11.457416
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__S_TI__41_EC (
	 V_0 = 0.96683294,
	 P_0 = 37.0,
	 Q_0 = 10.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 7.063605
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__SALT__92_EC (
	 V_0 = 0.99227476,
	 P_0 = 65.0,
	 Q_0 = 10.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 33.837193
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__SCOS__52_EC (
	 V_0 = 0.95681596,
	 P_0 = 18.0,
	 Q_0 = 5.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 15.409134
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__SMYT_102_EC (
	 V_0 = 0.9909982,
	 P_0 = 5.0,
	 Q_0 = 3.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 32.33335
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__SORE__17_EC (
	 V_0 = 0.9953003,
	 P_0 = 11.0,
	 Q_0 = 3.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 14.014746
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__SOUT__11_EC (
	 V_0 = 0.9852648,
	 P_0 = 70.0,
	 Q_0 = 23.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 13.0464115
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__SPRI__83_EC (
	 V_0 = 0.9845215,
	 P_0 = 20.0,
	 Q_0 = 10.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 28.453094
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__STER__36_EC (
	 V_0 = 0.98,
	 P_0 = 31.0,
	 Q_0 = 17.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 11.07225
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__STHP__75_EC (
	 V_0 = 0.96733195,
	 P_0 = 47.0,
	 Q_0 = 11.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 22.929527
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__SUMM__67_EC (
	 V_0 = 1.0196823,
	 P_0 = 28.0,
	 Q_0 = 7.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 24.918766
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__SUND__97_EC (
	 V_0 = 1.0113673,
	 P_0 = 15.0,
	 Q_0 = 9.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 27.909279
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__SUNN__56_EC (
	 V_0 = 0.95399994,
	 P_0 = 84.0,
	 Q_0 = 18.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 15.241966
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__SWIT__94_EC (
	 V_0 = 0.9905738,
	 P_0 = 30.0,
	 Q_0 = 16.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 28.672297
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__SWKA__60_EC (
	 V_0 = 0.9931558,
	 P_0 = 78.0,
	 Q_0 = 3.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 23.231045
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__TAZE__93_EC (
	 V_0 = 0.9869051,
	 P_0 = 12.0,
	 Q_0 = 7.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 30.826227
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__TIDD__59_EC (
	 V_0 = 0.98499995,
	 P_0 = 277.0,
	 Q_0 = 113.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 19.443115
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__TORR__54_EC (
	 V_0 = 0.9549999,
	 P_0 = 113.0,
	 Q_0 = 32.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 15.345243
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__TURN__77_EC (
	 V_0 = 1.006,
	 P_0 = 61.0,
	 Q_0 = 28.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 26.746555
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__TWIN__12_EC (
	 V_0 = 0.98999995,
	 P_0 = 47.0,
	 Q_0 = 10.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 12.531468
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__W_LA__46_EC (
	 V_0 = 1.005,
	 P_0 = 28.0,
	 Q_0 = 10.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 18.576406
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__WAGE__55_EC (
	 V_0 = 0.952,
	 P_0 = 63.0,
	 Q_0 = 22.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 15.055186
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__WCAM__50_EC (
	 V_0 = 1.0010817,
	 P_0 = 17.0,
	 Q_0 = 4.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 18.982258
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__WEST__35_EC (
	 V_0 = 0.9805373,
	 P_0 = 33.0,
	 Q_0 = 9.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 11.070006
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__WEST__40_EC (
	 V_0 = 0.97,
	 P_0 = 20.0,
	 Q_0 = 23.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 7.5088577
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__WHUN_118_EC (
	 V_0 = 0.94943744,
	 P_0 = 33.0,
	 Q_0 = 15.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 21.940535
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__WMED_114_EC (
	 V_0 = 0.96043575,
	 P_0 = 8.0,
	 Q_0 = 3.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 14.737192
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__WMVE__44_EC (
	 V_0 = 0.9848248,
	 P_0 = 16.0,
	 Q_0 = 8.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 13.94143
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__WNWP__57_EC (
	 V_0 = 0.9705814,
	 P_0 = 12.0,
	 Q_0 = 3.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 16.447317
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__WNWP__58_EC (
	 V_0 = 0.9590374,
	 P_0 = 12.0,
	 Q_0 = 3.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 15.5904
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__WOOS__53_EC (
	 V_0 = 0.9459818,
	 P_0 = 23.0,
	 Q_0 = 11.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 14.433802
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__WYTH_101_EC (
	 V_0 = 0.99243355,
	 P_0 = 22.0,
	 Q_0 = 15.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 29.634571
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.Eurostag.PwLoadVoltageDependence load__ZANE__48_EC (
	 V_0 = 1.0206336,
	 P_0 = 20.0,
	 Q_0 = 11.0,
	 alpha = 1,
	 beta = 2,
	 angle_0 = 20.018724
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
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__BELL__74_SC (
	 B = 0.12000005091307685,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__CAPI__79_SC (
	 B = 0.20000008116010576,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__EAST__37_SC (
	 B = -0.25000011807773265,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__FIEL_110_SC (
	 B = 0.060000025456538424,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__LOGA__82_SC (
	 B = 0.20000008116010576,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__N_NE__45_SC (
	 B = 0.10000004058005288,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__OLIV___5_SC (
	 B = -0.4000001623202115,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__REUS_107_SC (
	 B = 0.060000025456538424,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__ROAN_105_SC (
	 B = 0.20000008116010576,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__ROCK__34_SC (
	 B = 0.14000006124610082,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__SPRI__83_SC (
	 B = 0.10000004058005288,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__W_LA__46_SC (
	 B = 0.10000004058005288,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__WMVE__44_SC (
	 B = 0.10000004058005288,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__ZANE__48_SC (
	 B = 0.1500000664126128,
	 nsteps = 1
	 ) annotation (Placement(transformation()));

// FIXED INJECTIONS

// GENERATORS
  iPSL.Electrical.Machines.Eurostag.PwGeneratorM2S gen_pwGeneratorM2S__GEN__100_SM (
	 SNREF = SNREF, 
	 ur0 = 0.8974506714651584, 
	 ui0 = 0.4784048607782413, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.8095073834644267, 
	 init_omega = 1.0, 
	 init_efd = 0.3658153092734412, 
	 WLMDVPu = 0.7304917626978259, 
	 init_lambdaad = -1.000803692059064, 
	 init_cm = 0.1638292825249425, 
	 init_lambdaq1 = 0.2758511069314369, 
	 init_lambdaq2 = 0.2758511069314369, 
	 init_iq = 2.06790986962809, 
	 init_id = 1.637698070945138, 
	 init_lambdaaq = 0.2758511069314369, 
	 init_lambdad = -1.000803692059064, 
	 init_lambdaf = -1.155732240091342,
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
	 ur0 = 0.9203553265271546, 
	 ui0 = 0.4159880451033099, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.4791735555061875, 
	 init_omega = 1.0, 
	 init_efd = 0.3331104115534083, 
	 WLMDVPu = 0.7304917626978259, 
	 init_lambdaad = -1.02465214787903, 
	 init_cm = 0.02599954868345899, 
	 init_lambdaq1 = 0.04774713461981205, 
	 init_lambdaq2 = 0.04774713461981205, 
	 init_iq = 0.3553735396152188, 
	 init_id = 0.7539466788733598, 
	 init_lambdaaq = 0.04774713461981205, 
	 init_lambdad = -1.02465214787903, 
	 init_lambdaf = -1.165729656359758,
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
	 ur0 = 0.9018699849659615, 
	 ui0 = 0.35982156796957393, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.3796269852108433, 
	 init_omega = 1.0, 
	 init_efd = 0.2849031880034207, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -0.9702968044997399, 
	 init_cm = 1.369986238065869e-08, 
	 init_lambdaq1 = 2.899235187068358e-08, 
	 init_lambdaq2 = 2.899235187068358e-08, 
	 init_iq = 2.106447152781799e-07, 
	 init_id = -0.03259265652571904, 
	 init_lambdaaq = 2.899235187068358e-08, 
	 init_lambdad = -0.9702968044997399, 
	 init_lambdaf = -1.087516495534557,
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
	 ur0 = 0.9052420629069824, 
	 ui0 = 0.3405290523506339, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.3598166460894224, 
	 init_omega = 1.0, 
	 init_efd = 0.280771277980983, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -0.9653881561057837, 
	 init_cm = 8.82365479193915e-08, 
	 init_lambdaq1 = 1.893896360820252e-07, 
	 init_lambdaq2 = 1.893896360820252e-07, 
	 init_iq = 1.373518155413367e-06, 
	 init_id = -0.08271531195391794, 
	 init_lambdaaq = 1.893896360820252e-07, 
	 init_lambdad = -0.9653881561057837, 
	 init_lambdaf = -1.080907826794714,
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
	 ur0 = 0.9074460856872083, 
	 ui0 = 0.28782919124763917, 
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
	 init_theta = 0.2038779253349559, 
	 init_omega = 1.0, 
	 init_efd = 0.4676498708340942, 
	 WLMDVPu = 0.6682075874254081, 
	 init_lambdaad = -0.9526530804633786, 
	 init_cm = -0.09087220334714943, 
	 init_lambdaq1 = -0.07913766988061792, 
	 init_lambdaq2 = -0.07913766988061792, 
	 init_iq = -0.22482292579721, 
	 init_id = 0.07243679639857473, 
	 init_lambdaaq = -0.07913766988061792, 
	 init_lambdad = -0.9526530804633786, 
	 init_lambdaf = -1.119024248773158,
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
	 ur0 = 0.9245407661079427, 
	 ui0 = 0.3032381115857819, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.3169350830083989, 
	 init_omega = 1.0, 
	 init_efd = 0.317515946028974, 
	 WLMDVPu = 0.717262019773382, 
	 init_lambdaad = -0.9727241587302201, 
	 init_cm = 2.965727953211057e-09, 
	 init_lambdaq1 = 6.029566809756001e-09, 
	 init_lambdaq2 = 6.029566809756001e-09, 
	 init_iq = 3.079283061044671e-08, 
	 init_id = -0.009684357756348882, 
	 init_lambdaaq = 6.029566809756001e-09, 
	 init_lambdad = -0.9727241587302201, 
	 init_lambdaf = -1.069587273705751,
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
	 ur0 = 0.9220403384090419, 
	 ui0 = 0.33202658286735587, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.429680468958431, 
	 init_omega = 1.0, 
	 init_efd = 0.3211731610827278, 
	 WLMDVPu = 0.717262019773382, 
	 init_lambdaad = -0.9770030358443417, 
	 init_cm = 0.03571856410283861, 
	 init_lambdaq1 = 0.0717918409212504, 
	 init_lambdaq2 = 0.0717918409212504, 
	 init_iq = 0.3676297962963507, 
	 init_id = 0.01208631924055936, 
	 init_lambdaaq = 0.0717918409212504, 
	 init_lambdad = -0.9770030358443417, 
	 init_lambdaf = -1.074981840430654,
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
	 ur0 = 0.9415145356881303, 
	 ui0 = 0.25332869079436676, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.1958215963510303, 
	 init_omega = 1.0, 
	 init_efd = 0.3114292208333293, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -0.9825351346921078, 
	 init_cm = -0.02895138271708184, 
	 init_lambdaq1 = -0.05632834188760898, 
	 init_lambdaq2 = -0.05632834188760898, 
	 init_iq = -0.4115230606908455, 
	 init_id = 0.4543390880374323, 
	 init_lambdaaq = -0.05632834188760898, 
	 init_lambdad = -0.9825351346921078, 
	 init_lambdaf = -1.110668615054772,
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
	 ur0 = 0.9634399039535391, 
	 ui0 = 0.24048387410194877, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.23507925917931, 
	 init_omega = 1.0, 
	 init_efd = 0.2900895436651084, 
	 WLMDVPu = 0.7304917626978259, 
	 init_lambdaad = -0.9941946486930661, 
	 init_cm = -0.00389854811238405, 
	 init_lambdaq1 = -0.008176003831468064, 
	 init_lambdaq2 = -0.008176003831468064, 
	 init_iq = -0.05986692329954033, 
	 init_id = 0.05862040690644414, 
	 init_lambdaaq = -0.008176003831468064, 
	 init_lambdad = -0.9941946486930661, 
	 init_lambdaf = -1.117052141695873,
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
	 ur0 = 0.894172967121437, 
	 ui0 = 0.4587806614111134, 
	 transformerIncluded = true, 
	 V2 = 345.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.208439643051559, 
	 init_omega = 1.0, 
	 init_efd = 0.3213887337327676, 
	 WLMDVPu = 0.7304917626978259, 
	 init_lambdaad = -0.9863190516400927, 
	 init_cm = -0.1195151524819059, 
	 init_lambdaq1 = -0.2277582996221833, 
	 init_lambdaq2 = -0.2277582996221833, 
	 init_iq = -1.682178147267388, 
	 init_id = 0.791074758166685, 
	 init_lambdaaq = -0.2277582996221833, 
	 init_lambdad = -0.9863190516400927, 
	 init_lambdaf = -1.122432244852384,
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
	 ur0 = 0.8507545354027166, 
	 ui0 = 0.6153995615499792, 
	 transformerIncluded = true, 
	 V2 = 345.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 1.221738657787347, 
	 init_omega = 1.0, 
	 init_efd = 0.3668413077787545, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -0.9131733248554612, 
	 init_cm = 0.303270227793447, 
	 init_lambdaq1 = 0.5069253995923174, 
	 init_lambdaq2 = 0.5069253995923174, 
	 init_iq = 3.820665416363197, 
	 init_id = 2.001543308202798, 
	 init_lambdaaq = 0.5069253995923174, 
	 init_lambdad = -0.9131733248554612, 
	 init_lambdaf = -1.064105383769866,
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
	 ur0 = 0.9664151664299986, 
	 ui0 = 0.21480602170245455, 
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
	 init_theta = 0.4810773628149866, 
	 init_omega = 1.0, 
	 init_efd = 0.7885856690531964, 
	 WLMDVPu = 0.6682075874254081, 
	 init_lambdaad = -1.04966975932864, 
	 init_cm = 0.3522755830241594, 
	 init_lambdaq1 = 0.2087235461188205, 
	 init_lambdaq2 = 0.2087235461188205, 
	 init_iq = 0.59296461965574, 
	 init_id = 1.102366673969035, 
	 init_lambdaaq = 0.2087235461188205, 
	 init_lambdad = -1.04966975932864, 
	 init_lambdaf = -1.330217081706226,
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
	 ur0 = 0.9505229606691412, 
	 ui0 = 0.19340671328735298, 
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
	 init_theta = 0.2006656712009653, 
	 init_omega = 1.0, 
	 init_efd = 0.4517906410142541, 
	 WLMDVPu = 0.6571100460316972, 
	 init_lambdaad = -0.9731674307083588, 
	 init_cm = 8.400162459078357e-07, 
	 init_lambdaq1 = 6.225397639036067e-07, 
	 init_lambdaq2 = 6.225397639036067e-07, 
	 init_iq = 2.15494533653722e-06, 
	 init_id = 0.03167399796153898, 
	 init_lambdaaq = 6.225397639036067e-07, 
	 init_lambdad = -0.9731674307083588, 
	 init_lambdaf = -1.137572157178055,
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
	 ur0 = 0.9524290532184392, 
	 ui0 = 0.19901728341779867, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.2059444800738092, 
	 init_omega = 1.0, 
	 init_efd = 0.3011117598906949, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -0.9786138127320823, 
	 init_cm = 8.730955462480041e-07, 
	 init_lambdaq1 = 1.753635103482301e-06, 
	 init_lambdaq2 = 1.753635103482301e-06, 
	 init_iq = 1.278255869753553e-05, 
	 init_id = 0.2601911637203851, 
	 init_lambdaaq = 1.753635103482301e-06, 
	 init_lambdad = -0.9786138127320823, 
	 init_lambdaf = -1.102502308710982,
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
	 ur0 = 0.9446379776693558, 
	 ui0 = 0.1890556662585267, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.1975426572891792, 
	 init_omega = 1.0, 
	 init_efd = 0.279279369583136, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -0.9615789322724252, 
	 init_cm = 8.893441705824292e-08, 
	 init_lambdaq1 = 1.919258113860302e-07, 
	 init_lambdaq2 = 1.919258113860302e-07, 
	 init_iq = 1.390020004838483e-06, 
	 init_id = -0.08304176837859033, 
	 init_lambdaaq = 1.919258113860302e-07, 
	 init_lambdad = -0.9615789322724252, 
	 init_lambdaf = -1.076484776760696,
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
	 ur0 = 0.9253541721304244, 
	 ui0 = 0.3574683537815478, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.3381679109609978, 
	 init_omega = 1.0, 
	 init_efd = 0.3151885649356987, 
	 WLMDVPu = 0.717262019773382, 
	 init_lambdaad = -0.9872260819567668, 
	 init_cm = -0.01289553254572094, 
	 init_lambdaq1 = -0.02641126320529968, 
	 init_lambdaq2 = -0.02641126320529968, 
	 init_iq = -0.1356796647833596, 
	 init_id = -0.1499184561388962, 
	 init_lambdaaq = -0.02641126320529968, 
	 init_lambdad = -0.9872260819567668, 
	 init_lambdaf = -1.083379193591523,
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
	 ur0 = 0.9254665737031603, 
	 ui0 = 0.495995484679379, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.7653282346285806, 
	 init_omega = 1.0, 
	 init_efd = 0.4837650691761805, 
	 WLMDVPu = 0.6296504227510634, 
	 init_lambdaad = -1.036947151429635, 
	 init_cm = 0.2019326161342831, 
	 init_lambdaq1 = 0.2365668131750353, 
	 init_lambdaq2 = 0.2365668131750353, 
	 init_iq = 1.889421823817238, 
	 init_id = 1.022269077228862, 
	 init_lambdaaq = 0.2365668131750353, 
	 init_lambdad = -1.036947151429635, 
	 init_lambdaf = -1.209409345535857,
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
	 ur0 = 0.8792960424088699, 
	 ui0 = 0.5070142411853102, 
	 transformerIncluded = true, 
	 V2 = 345.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.96198479533285, 
	 init_omega = 1.0, 
	 init_efd = 0.3372873181626198, 
	 WLMDVPu = 0.7304917626978259, 
	 init_lambdaad = -0.9491026782099237, 
	 init_cm = 0.2041477875468751, 
	 init_lambdaq1 = 0.3726597537130298, 
	 init_lambdaq2 = 0.3726597537130298, 
	 init_iq = 2.762287423250258, 
	 init_id = 1.395750557369118, 
	 init_lambdaaq = 0.3726597537130298, 
	 init_lambdad = -0.9491026782099237, 
	 init_lambdaf = -1.091949172459773,
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
	 ur0 = 0.9322515595478243, 
	 ui0 = 0.2606357987037182, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.251183215730529, 
	 init_omega = 1.0, 
	 init_efd = 0.3183240542637268, 
	 WLMDVPu = 0.717262019773382, 
	 init_lambdaad = -0.9686367512237999, 
	 init_cm = -0.008928271095658885, 
	 init_lambdaq1 = -0.01810582237202206, 
	 init_lambdaq2 = -0.01810582237202206, 
	 init_iq = -0.09232785554238264, 
	 init_id = 0.03119541286788135, 
	 init_lambdaaq = -0.01810582237202206, 
	 init_lambdad = -0.9686367512237999, 
	 init_lambdaf = -1.065746392025308,
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
	 ur0 = 0.9421344549345725, 
	 ui0 = 0.21787997517070026, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.2427440835454664, 
	 init_omega = 1.0, 
	 init_efd = 0.3409471722756659, 
	 WLMDVPu = 0.717262019773382, 
	 init_lambdaad = -0.9763239260191267, 
	 init_cm = 0.006948045292008529, 
	 init_lambdaq1 = 0.01315515265817849, 
	 init_lambdaq2 = 0.01315515265817849, 
	 init_iq = 0.06727914084461388, 
	 init_id = 0.3306735944363495, 
	 init_lambdaaq = 0.01315515265817849, 
	 init_lambdad = -0.9763239260191267, 
	 init_lambdaf = -1.080335096361124,
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
	 ur0 = 0.9304591505769098, 
	 ui0 = 0.2504933379862714, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.2630155252218895, 
	 init_omega = 1.0, 
	 init_efd = 0.3403599785716129, 
	 WLMDVPu = 0.6296504227510634, 
	 init_lambdaad = -0.9599643813459688, 
	 init_cm = 4.471365324393196e-07, 
	 init_lambdaq1 = 7.445322614155201e-07, 
	 init_lambdaq2 = 7.445322614155201e-07, 
	 init_iq = 5.189735852922928e-06, 
	 init_id = -0.1452903647263141, 
	 init_lambdaaq = 7.445322614155201e-07, 
	 init_lambdad = -0.9599643813459688, 
	 init_lambdaf = -1.081302676379069,
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
	 ur0 = 0.9654616145669901, 
	 ui0 = 0.196523859746597, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.200826796898643, 
	 init_omega = 1.0, 
	 init_efd = 0.2880767009338198, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -0.9835083653815292, 
	 init_cm = 8.502658510234592e-08, 
	 init_lambdaq1 = 1.777808984436363e-07, 
	 init_lambdaq2 = 1.777808984436363e-07, 
	 init_iq = 1.298494326541777e-06, 
	 init_id = -0.08119682097923588, 
	 init_lambdaaq = 1.777808984436363e-07, 
	 init_lambdad = -0.9835083653815292, 
	 init_lambdaf = -1.10203375681164,
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
	 ur0 = 0.9617580936786156, 
	 ui0 = 0.18820575609611093, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.1932396277125667, 
	 init_omega = 1.0, 
	 init_efd = 0.3232379065027537, 
	 WLMDVPu = 0.717262019773382, 
	 init_lambdaad = -0.9806692108137293, 
	 init_cm = 1.745602869643736e-08, 
	 init_lambdaq1 = 3.486129423753776e-08, 
	 init_lambdaq2 = 3.486129423753776e-08, 
	 init_iq = 1.785899838725041e-07, 
	 init_id = 0.02349513219510281, 
	 init_lambdaaq = 3.486129423753776e-08, 
	 init_lambdad = -0.9806692108137293, 
	 init_lambdaf = -1.079277897695771,
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
	 ur0 = 0.961681958031471, 
	 ui0 = 0.12675909080062828, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.05690366521929102, 
	 init_omega = 1.0, 
	 init_efd = 0.2935114148948189, 
	 WLMDVPu = 0.7304917626978259, 
	 init_lambdaad = -0.9741137518998684, 
	 init_cm = -0.02988571275557734, 
	 init_lambdaq1 = -0.06215837124362143, 
	 init_lambdaq2 = -0.06215837124362143, 
	 init_iq = -0.4516389584855139, 
	 init_id = 0.3216525554619349, 
	 init_lambdaaq = -0.06215837124362143, 
	 init_lambdad = -0.9741137518998684, 
	 init_lambdaf = -1.098420461290653,
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
	 ur0 = 0.9737666113156932, 
	 ui0 = 0.14833575916372788, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.06133286892528349, 
	 init_omega = 1.0, 
	 init_efd = 0.315737559423014, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -0.9910278366824207, 
	 init_cm = -0.03972377662817821, 
	 init_lambdaq1 = -0.07621523498382951, 
	 init_lambdaq2 = -0.07621523498382951, 
	 init_iq = -0.5592125807532935, 
	 init_id = 0.4684476638626307, 
	 init_lambdaaq = -0.07621523498382951, 
	 init_lambdad = -0.9910278366824207, 
	 init_lambdaf = -1.120933926617815,
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
	 ur0 = 0.9526391631469449, 
	 ui0 = 0.3201618579004404, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.354034047725368, 
	 init_omega = 1.0, 
	 init_efd = 0.2981667251553087, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -1.003602869895108, 
	 init_cm = 0.01279510781855496, 
	 init_lambdaq1 = 0.02584020830979318, 
	 init_lambdaq2 = 0.02584020830979318, 
	 init_iq = 0.190503033043062, 
	 init_id = -0.04574419702919052, 
	 init_lambdaaq = 0.02584020830979318, 
	 init_lambdad = -1.003602869895108, 
	 init_lambdaf = -1.12627966984425,
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
	 ur0 = 0.9567806268636188, 
	 ui0 = 0.3676897920566365, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.6938497087342388, 
	 init_omega = 1.0, 
	 init_efd = 0.4565727498799821, 
	 WLMDVPu = 0.717262019773382, 
	 init_lambdaad = -1.019845370055179, 
	 init_cm = 0.2025465178216596, 
	 init_lambdaq1 = 0.2863750236038312, 
	 init_lambdaq2 = 0.2863750236038312, 
	 init_iq = 1.52221807167182, 
	 init_id = 1.70839565852053, 
	 init_lambdaaq = 0.2863750236038312, 
	 init_lambdad = -1.019845370055179, 
	 init_lambdaf = -1.159129898342409,
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
	 ur0 = 0.9209529801066794, 
	 ui0 = 0.25272606258792646, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.3847921304178252, 
	 init_omega = 1.0, 
	 init_efd = 0.315661074149361, 
	 WLMDVPu = 0.717262019773382, 
	 init_lambdaad = -0.9514592584736205, 
	 init_cm = 0.04762708897638036, 
	 init_lambdaq1 = 0.09739874298553852, 
	 init_lambdaq2 = 0.09739874298553852, 
	 init_iq = 0.4944124769870572, 
	 init_id = 0.09925927020575701, 
	 init_lambdaaq = 0.09739874298553852, 
	 init_lambdad = -0.9514592584736205, 
	 init_lambdaf = -1.047756516299654,
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
	 ur0 = 0.9193236557472583, 
	 ui0 = 0.2472813331952146, 
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
	 init_theta = 0.2626560996981504, 
	 init_omega = 1.0, 
	 init_efd = 0.4485847571536813, 
	 WLMDVPu = 0.6571100460316972, 
	 init_lambdaad = -0.9569002566081004, 
	 init_cm = 2.010549095573684e-06, 
	 init_lambdaq1 = 1.507306506760142e-06, 
	 init_lambdaq2 = 1.507306506760142e-06, 
	 init_iq = 5.21759944663476e-06, 
	 init_id = 0.0490022906099956, 
	 init_lambdaaq = 1.507306506760142e-06, 
	 init_lambdad = -0.9569002566081004, 
	 init_lambdaf = -1.120138375336636,
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
	 ur0 = 0.9204422192279813, 
	 ui0 = 0.2508027114258701, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.2660283275080639, 
	 init_omega = 1.0, 
	 init_efd = 0.3420386992136589, 
	 WLMDVPu = 0.6296504227510634, 
	 init_lambdaad = -0.9534023537700924, 
	 init_cm = 1.216290018621408e-08, 
	 init_lambdaq1 = 2.015318767750467e-08, 
	 init_lambdaq2 = 2.015318767750467e-08, 
	 init_iq = 1.395617871113169e-07, 
	 init_id = -0.02396267559676038, 
	 init_lambdaaq = 2.015318767750467e-08, 
	 init_lambdad = -0.9534023537700924, 
	 init_lambdaf = -1.075339112527974,
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
	 ur0 = 0.9288278089931333, 
	 ui0 = 0.32787773941050524, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.5621682298763558, 
	 init_omega = 1.0, 
	 init_efd = 0.437797616896519, 
	 WLMDVPu = 0.6296504227510634, 
	 init_lambdaad = -0.9895345016397322, 
	 init_cm = 0.1422684688132389, 
	 init_lambdaq1 = 0.1841692494667091, 
	 init_lambdaq2 = 0.1841692494667091, 
	 init_iq = 1.353879133846621, 
	 init_id = 1.145773231329259, 
	 init_lambdaaq = 0.1841692494667091, 
	 init_lambdad = -0.9895345016397322, 
	 init_lambdaf = -1.14560930404956,
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
	 ur0 = 0.9081068397471724, 
	 ui0 = 0.4066533869199452, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.6929837812328681, 
	 init_omega = 1.0, 
	 init_efd = 0.2812497768191802, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -0.9574022297388068, 
	 init_cm = 0.1077808558074337, 
	 init_lambdaq1 = 0.2308675633024178, 
	 init_lambdaq2 = 0.2308675633024178, 
	 init_iq = 1.686862605296943, 
	 init_id = -0.06263465784049094, 
	 init_lambdaaq = 0.2308675633024178, 
	 init_lambdad = -0.9574022297388068, 
	 init_lambdaf = -1.073118772517401,
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
	 ur0 = 0.9151863653777188, 
	 ui0 = 0.3980425216622804, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.4102493533366517, 
	 init_omega = 1.0, 
	 init_efd = 0.2983876805000537, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -0.9982718185402553, 
	 init_cm = 2.047438648775762e-09, 
	 init_lambdaq1 = 4.135486290374069e-09, 
	 init_lambdaq2 = 4.135486290374069e-09, 
	 init_iq = 3.040490240829863e-08, 
	 init_id = 0.01259990022699252, 
	 init_lambdaaq = 4.135486290374069e-09, 
	 init_lambdad = -0.9982718185402553, 
	 init_lambdaf = -1.121039527676568,
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
	 ur0 = 0.8896785276267223, 
	 ui0 = 0.4674367421325807, 
	 transformerIncluded = true, 
	 V2 = 345.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.9862476158770365, 
	 init_omega = 1.0, 
	 init_efd = 0.3771455501597529, 
	 WLMDVPu = 0.7304917626978259, 
	 init_lambdaad = -0.9332057872035852, 
	 init_cm = 0.2542544342553851, 
	 init_lambdaq1 = 0.4182121798199289, 
	 init_lambdaq2 = 0.4182121798199289, 
	 init_iq = 3.104608952235915, 
	 init_id = 2.428759254207065, 
	 init_lambdaaq = 0.4182121798199289, 
	 init_lambdad = -0.9332057872035852, 
	 init_lambdaf = -1.092932870794308,
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
	 ur0 = 0.930873405511233, 
	 ui0 = 0.4857721716773396, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.9710981701549485, 
	 init_omega = 1.0, 
	 init_efd = 0.3692744846585141, 
	 WLMDVPu = 0.7304917626978259, 
	 init_lambdaad = -0.9670778456046608, 
	 init_cm = 0.2548845295863396, 
	 init_lambdaq1 = 0.4256940406624998, 
	 init_lambdaq2 = 0.4256940406624998, 
	 init_iq = 3.229816882198069, 
	 init_id = 1.87737783915493, 
	 init_lambdaaq = 0.4256940406624998, 
	 init_lambdad = -0.9670778456046608, 
	 init_lambdaf = -1.123471408922556,
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
	 init_theta = 1.409571055746637, 
	 init_omega = 1.0, 
	 init_efd = 0.4715368003601067, 
	 WLMDVPu = 0.717262019773382, 
	 init_lambdaad = -0.753349878201679, 
	 init_cm = 0.510348783052183, 
	 init_lambdaq1 = 0.6986695816258903, 
	 init_lambdaq2 = 0.6986695816258903, 
	 init_iq = 3.65353673865633, 
	 init_id = 3.423541455071789, 
	 init_lambdaaq = 0.6986695816258903, 
	 init_lambdad = -0.753349878201679, 
	 init_lambdaf = -0.8971994198694452,
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
	 ur0 = 0.9083123392530212, 
	 ui0 = 0.37845045626923235, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.3947478067990945, 
	 init_omega = 1.0, 
	 init_efd = 0.3308291864780229, 
	 WLMDVPu = 0.717262019773382, 
	 init_lambdaad = -0.9867954506373858, 
	 init_cm = 3.046066616244313e-07, 
	 init_lambdaq1 = 5.943686907536881e-07, 
	 init_lambdaq2 = 5.943686907536881e-07, 
	 init_iq = 3.052405575749984e-06, 
	 init_id = 0.0981465114692066, 
	 init_lambdaaq = 5.943686907536881e-07, 
	 init_lambdad = -0.9867954506373858, 
	 init_lambdaf = -1.087719974044339,
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
	 ur0 = 0.9142125223611456, 
	 ui0 = 0.3530092085797555, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.347402680531461, 
	 init_omega = 1.0, 
	 init_efd = 0.3548191054748525, 
	 WLMDVPu = 0.6296504227510634, 
	 init_lambdaad = -0.9769858189922849, 
	 init_cm = -0.01100858347107196, 
	 init_lambdaq1 = -0.01758353932928036, 
	 init_lambdaq2 = -0.01758353932928036, 
	 init_iq = -0.1248173625295641, 
	 init_id = -0.110969315345374, 
	 init_lambdaaq = -0.01758353932928036, 
	 init_lambdad = -0.9769858189922849, 
	 init_lambdaf = -1.10347879118064,
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
	 ur0 = 0.9188571185752236, 
	 ui0 = 0.3711907717441248, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.3740890560811435, 
	 init_omega = 1.0, 
	 init_efd = 0.3786766821660669, 
	 WLMDVPu = 0.6296504227510634, 
	 init_lambdaad = -0.9933816658235344, 
	 init_cm = -0.005504308618182346, 
	 init_lambdaq1 = -0.008237891486033518, 
	 init_lambdaq2 = -0.008237891486033518, 
	 init_iq = -0.05958418876350079, 
	 init_id = 0.09797692151269921, 
	 init_lambdaaq = -0.008237891486033518, 
	 init_lambdad = -0.9933816658235344, 
	 init_lambdaf = -1.128379861485819,
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
	 ur0 = 0.8903045377726346, 
	 ui0 = 0.35372549249600177, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.3781957568166165, 
	 init_omega = 1.0, 
	 init_efd = 0.2784906237110345, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -0.9567329601160298, 
	 init_cm = 4.447206285416276e-08, 
	 init_lambdaq1 = 9.628584438447128e-08, 
	 init_lambdaq2 = 9.628584438447128e-08, 
	 init_iq = 6.961862050562651e-07, 
	 init_id = -0.05872260255133226, 
	 init_lambdaaq = 9.628584438447128e-08, 
	 init_lambdad = -0.9567329601160298, 
	 init_lambdaf = -1.071314285429628,
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
	 ur0 = 0.8755823134783353, 
	 ui0 = 0.3501494643680137, 
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
	 init_theta = 0.3803318298477893, 
	 init_omega = 1.0, 
	 init_efd = 0.4608808673292581, 
	 WLMDVPu = 0.6682075874254081, 
	 init_lambdaad = -0.9476911946767913, 
	 init_cm = 2.062095304381932e-06, 
	 init_lambdaq1 = 1.815864251297179e-06, 
	 init_lambdaq2 = 1.815864251297179e-06, 
	 init_iq = 5.158705259331736e-06, 
	 init_id = 0.05584728392020083, 
	 init_lambdaaq = 1.815864251297179e-06, 
	 init_lambdad = -0.9476911946767913, 
	 init_lambdaf = -1.111654221572175,
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
	 ur0 = 0.8983640811115053, 
	 ui0 = 0.4527450300064733, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.4667887265464018, 
	 init_omega = 1.0, 
	 init_efd = 0.3930202397755059, 
	 WLMDVPu = 0.6296504227510634, 
	 init_lambdaad = -1.008957859906453, 
	 init_cm = 2.979776536772406e-07, 
	 init_lambdaq1 = 4.296854363359341e-07, 
	 init_lambdaq2 = 4.296854363359341e-07, 
	 init_iq = 3.168608973001807e-06, 
	 init_id = 0.1186065258255397, 
	 init_lambdaaq = 4.296854363359341e-07, 
	 init_lambdad = -1.008957859906453, 
	 init_lambdaf = -1.149069532283427,
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
	 ur0 = 0.9097432884959314, 
	 ui0 = 0.503951701699909, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 1.029366062011954, 
	 init_omega = 1.0, 
	 init_efd = 0.4439144812935756, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -0.9712087577163463, 
	 init_cm = 0.321498732854305, 
	 init_lambdaq1 = 0.4465751305644376, 
	 init_lambdaq2 = 0.4465751305644376, 
	 init_iq = 3.427550634619408, 
	 init_id = 3.236608781149491, 
	 init_lambdaaq = 0.4465751305644376, 
	 init_lambdad = -0.9712087577163463, 
	 init_lambdaf = -1.153851565990185,
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
	 ur0 = 0.8303769682585921, 
	 ui0 = 0.5298103446947757, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.567930099735424, 
	 init_omega = 1.0, 
	 init_efd = 0.2891647099430403, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -0.9837357300910398, 
	 init_cm = 4.427852051784405e-08, 
	 init_lambdaq1 = 9.225723757013538e-08, 
	 init_lambdaq2 = 9.225723757013538e-08, 
	 init_iq = 6.73902342866739e-07, 
	 init_id = -0.05859468289390296, 
	 init_lambdaaq = 9.225723757013538e-08, 
	 init_lambdad = -0.9837357300910398, 
	 init_lambdaf = -1.102708768600138,
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
	 ur0 = 0.8660854350971534, 
	 ui0 = 0.5292648033527833, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.5544914482149732, 
	 init_omega = 1.0, 
	 init_efd = 0.3020853751405309, 
	 WLMDVPu = 0.7304917626978259, 
	 init_lambdaad = -1.017312329285703, 
	 init_cm = 0.002599256381335572, 
	 init_lambdaq1 = 0.005232821879956301, 
	 init_lambdaq2 = 0.005232821879956301, 
	 init_iq = 0.03876205799536502, 
	 init_id = 0.1088201002528538, 
	 init_lambdaaq = 0.005232821879956301, 
	 init_lambdad = -1.017312329285703, 
	 init_lambdaf = -1.145250245949887,
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
	 ur0 = 0.7729714006759986, 
	 ui0 = 0.6422929270609774, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 1.466349552819796, 
	 init_omega = 1.0, 
	 init_efd = 0.533052914713819, 
	 WLMDVPu = 0.6296504227510634, 
	 init_lambdaad = -0.8234830249618436, 
	 init_cm = 0.5576537268803513, 
	 init_lambdaq1 = 0.5928927512769253, 
	 init_lambdaq2 = 0.5928927512769253, 
	 init_iq = 4.405198444021836, 
	 init_id = 4.133664102786111, 
	 init_lambdaaq = 0.5928927512769253, 
	 init_lambdad = -0.8234830249618436, 
	 init_lambdaf = -1.013516330596778,
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
	 ur0 = 0.8230852748635046, 
	 ui0 = 0.5410688875390979, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.4529002794828349, 
	 init_omega = 1.0, 
	 init_efd = 0.4137737854098854, 
	 WLMDVPu = 0.6296504227510634, 
	 init_lambdaad = -0.9943318923896803, 
	 init_cm = -0.0779582005962368, 
	 init_lambdaq1 = -0.1067777309789209, 
	 init_lambdaq2 = -0.1067777309789209, 
	 init_iq = -0.7785851712795077, 
	 init_id = 0.7077535649170483, 
	 init_lambdaaq = -0.1067777309789209, 
	 init_lambdad = -0.9943318923896803, 
	 init_lambdaf = -1.141842201509245,
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
	 ur0 = 0.8187304539715552, 
	 ui0 = 0.538591200377025, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.5641677796586039, 
	 init_omega = 1.0, 
	 init_efd = 0.3521036240655027, 
	 WLMDVPu = 0.6296504227510634, 
	 init_lambdaad = -0.9760906482757954, 
	 init_cm = -0.009173605393208446, 
	 init_lambdaq1 = -0.0147656106953149, 
	 init_lambdaq2 = -0.0147656106953149, 
	 init_iq = -0.1047052636643547, 
	 init_id = -0.1496402653198114, 
	 init_lambdaaq = -0.0147656106953149, 
	 init_lambdad = -0.9760906482757954, 
	 init_lambdaf = -1.101615551639527,
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
	 ur0 = 0.824206438476406, 
	 ui0 = 0.552533210303651, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.5905774602243669, 
	 init_omega = 1.0, 
	 init_efd = 0.3704680166940723, 
	 WLMDVPu = 0.6296504227510634, 
	 init_lambdaad = -0.9915207946210846, 
	 init_cm = 1.936177394354103e-08, 
	 init_lambdaq1 = 2.961940160488425e-08, 
	 init_lambdaq2 = 2.961940160488425e-08, 
	 init_iq = 2.137512769614242e-07, 
	 init_id = -0.03023356148147054, 
	 init_lambdaaq = 2.961940160488425e-08, 
	 init_lambdad = -0.9915207946210846, 
	 init_lambdaf = -1.123592601942857,
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
	 ur0 = 0.8993839940519539, 
	 ui0 = 0.45957416374175547, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.4060341451201344, 
	 init_omega = 1.0, 
	 init_efd = 0.2859172918148464, 
	 WLMDVPu = 0.7304917626978259, 
	 init_lambdaad = -1.004585698926996, 
	 init_cm = -0.02728792124077176, 
	 init_lambdaq1 = -0.05790491316244399, 
	 init_lambdaq2 = -0.05790491316244399, 
	 init_iq = -0.4264404023647196, 
	 init_id = -0.1456667716801663, 
	 init_lambdaaq = -0.05790491316244399, 
	 init_lambdad = -1.004585698926996, 
	 init_lambdaf = -1.125676177524446,
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
	 ur0 = 0.9373960423239315, 
	 ui0 = 0.18251990059716888, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.1923126852205123, 
	 init_omega = 1.0, 
	 init_efd = 0.3417486687952813, 
	 WLMDVPu = 0.6296504227510634, 
	 init_lambdaad = -0.9540475320753473, 
	 init_cm = 3.089385502130133e-08, 
	 init_lambdaq1 = 5.123268614594967e-08, 
	 init_lambdaq2 = 5.123268614594967e-08, 
	 init_iq = 3.550133496846991e-07, 
	 init_id = -0.03819027530063973, 
	 init_lambdaaq = 5.123268614594967e-08, 
	 init_lambdad = -0.9540475320753473, 
	 init_lambdaf = -1.075880895020885,
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
	 ur0 = 0.9611538197893917, 
	 ui0 = 0.26867688238627435, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.2512011902809225, 
	 init_omega = 1.0, 
	 init_efd = 0.3083963751384893, 
	 WLMDVPu = 0.717262019773382, 
	 init_lambdaad = -0.9899084571246248, 
	 init_cm = -0.00892588792802146, 
	 init_lambdaq1 = -0.01868368381626946, 
	 init_lambdaq2 = -0.01868368381626946, 
	 init_iq = -0.09608074462775126, 
	 init_id = -0.2750095436164341, 
	 init_lambdaaq = -0.01868368381626946, 
	 init_lambdad = -0.9899084571246248, 
	 init_lambdaf = -1.083989506963774,
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
	 ur0 = 0.963303304336268, 
	 ui0 = 0.22835639846604838, 
	 transformerIncluded = true, 
	 V2 = 138.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.2327321058903032, 
	 init_omega = 1.0, 
	 init_efd = 0.3018926089664479, 
	 WLMDVPu = 0.7262486573072185, 
	 init_lambdaad = -0.9930726042237502, 
	 init_cm = 2.615603268120701e-07, 
	 init_lambdaq1 = 5.230430127651261e-07, 
	 init_lambdaq2 = 5.230430127651261e-07, 
	 init_iq = 3.836258358771993e-06, 
	 init_id = 0.1424123303829629, 
	 init_lambdaaq = 5.230430127651261e-07, 
	 init_lambdad = -0.9930726042237502, 
	 init_lambdaf = -1.117282370343732,
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
	 ur0 = 0.9472957567582964, 
	 ui0 = 0.3644937862136249, 
	 transformerIncluded = true, 
	 V2 = 345.0, 
	 Saturated = true, 
	 TX = 0, 
	 init_theta = 0.329463609210038, 
	 init_omega = 1.0, 
	 init_efd = 0.3371361546332883, 
	 WLMDVPu = 0.7304917626978259, 
	 init_lambdaad = -1.030932780793413, 
	 init_cm = -0.01818525272777948, 
	 init_lambdaq1 = -0.03299622905357995, 
	 init_lambdaq2 = -0.03299622905357995, 
	 init_iq = -0.2464222001885148, 
	 init_id = 0.7827050584784239, 
	 init_lambdaaq = -0.03299622905357995, 
	 init_lambdad = -1.030932780793413, 
	 init_lambdaf = -1.17371525490654,
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
	 init_APREF = 0.1473684210526316,
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
	 init_EFD = 0.3658153092734412,
	 init_VREF = 1.024222222903179,
	 init_YLL = 0.001829076546367206,
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
	 init_REF = 0.008191464126247125,
	 init_PMECH = 0.1638292825249425,
	 init_CM = 0.1638292825249425,
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
	 init_APREF = 0.02339181286549707,
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
	 init_EFD = 0.3331104115534083,
	 init_VREF = 1.01595708762663,
	 init_YLL = 0.001665552057767041,
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
	 init_REF = 0.001299977434172949,
	 init_PMECH = 0.02599954868345899,
	 init_CM = 0.02599954868345899,
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
	 init_APREF = -2.10269512239613e-19,
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
	 init_EFD = 0.2849031880034207,
	 init_VREF = 0.9722270004239681,
	 init_YLL = 0.001424515940017104,
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
	 init_REF = 6.849931190329343e-10,
	 init_PMECH = 1.369986238065869e-08,
	 init_CM = 1.369986238065869e-08,
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
	 init_EFD = 0.280771277980983,
	 init_VREF = 0.9680753529635014,
	 init_YLL = 0.001403856389904915,
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
	 init_REF = 4.411827395969575e-09,
	 init_PMECH = 8.82365479193915e-08,
	 init_CM = 8.82365479193915e-08,
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
	 init_EFD = 0.4676498708340942,
	 init_VREF = 0.9563380389050932,
	 init_YLL = 0.002338249354170471,
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
	 init_CM = -0.09087220334714943
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN__110_SM (
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
  sexs reg_sexs__GEN__110_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_EFD = 0.317515946028974,
	 init_VREF = 0.9745011022365967,
	 init_YLL = 0.00158757973014487,
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
	 init_REF = 1.482863976605529e-10,
	 init_PMECH = 2.965727953211057e-09,
	 init_CM = 2.965727953211057e-09,
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
	 init_APREF = 0.03214285714285714,
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
	 init_EFD = 0.3211731610827278,
	 init_VREF = 0.9814433895297517,
	 init_YLL = 0.001605865805413639,
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
	 init_REF = 0.001785928205141931,
	 init_PMECH = 0.03571856410283861,
	 init_CM = 0.03571856410283861,
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
	 init_EFD = 0.3114292208333293,
	 init_VREF = 0.9791411880212116,
	 init_YLL = 0.001557146104166647,
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
	 init_CM = -0.02895138271708184
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
	 init_EFD = 0.2900895436651084,
	 init_VREF = 0.9947899382232985,
	 init_YLL = 0.001450447718325542,
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
	 init_CM = -0.00389854811238405
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
	 init_EFD = 0.3213887337327676,
	 init_VREF = 1.008545470847787,
	 init_YLL = 0.001606943668663838,
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
	 init_CM = -0.1195151524819059
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
	 init_EFD = 0.3668413077787545,
	 init_VREF = 1.049210102278681,
	 init_YLL = 0.001834206538893773,
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
	 init_REF = 0.01516351138967235,
	 init_PMECH = 0.303270227793447,
	 init_CM = 0.303270227793447,
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
	 init_EFD = 0.7885856690531964,
	 init_VREF = 1.030951306394843,
	 init_YLL = 0.003942928345265982,
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
	 init_REF = 0.01761377915120797,
	 init_PMECH = 0.3522755830241594,
	 init_CM = 0.3522755830241594,
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
	 init_APREF = -3.212450881438531e-19,
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
	 init_EFD = 0.4517906410142541,
	 init_VREF = 0.9734320928536285,
	 init_YLL = 0.00225895320507127,
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
	 init_REF = 4.200081229539179e-08,
	 init_PMECH = 8.400162459078357e-07,
	 init_CM = 8.400162459078357e-07,
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
	 init_APREF = -4.20539024479226e-19,
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
	 init_EFD = 0.3011117598906949,
	 init_VREF = 0.9760824649315361,
	 init_YLL = 0.001505558799453475,
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
	 init_REF = 4.365477731240021e-08,
	 init_PMECH = 8.730955462480041e-07,
	 init_CM = 8.730955462480041e-07,
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
	 init_EFD = 0.279279369583136,
	 init_VREF = 0.9642637346075028,
	 init_YLL = 0.00139639684791568,
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
	 init_REF = 4.446720852912146e-09,
	 init_PMECH = 8.893441705824292e-08,
	 init_CM = 8.893441705824292e-08,
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
	 init_EFD = 0.3151885649356987,
	 init_VREF = 0.9922017673749113,
	 init_YLL = 0.001575942824678494,
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
	 init_CM = -0.01289553254572094
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
	 init_EFD = 0.4837650691761805,
	 init_VREF = 1.056477081814413,
	 init_YLL = 0.002418825345880903,
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
	 init_REF = 0.01009663080671416,
	 init_PMECH = 0.2019326161342831,
	 init_CM = 0.2019326161342831,
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
	 init_EFD = 0.3372873181626198,
	 init_VREF = 1.017371087095469,
	 init_YLL = 0.001686436590813099,
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
	 init_REF = 0.01020738937734376,
	 init_PMECH = 0.2041477875468751,
	 init_CM = 0.2041477875468751,
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
	 init_APREF = -0.008035714285714283,
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
	 init_EFD = 0.3183240542637268,
	 init_VREF = 0.9698527703977785,
	 init_YLL = 0.001591620271318634,
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
	 init_CM = -0.008928271095658885
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___31_SM (
	 SNREF = 100.0,
	 SN = 1120.0,
	 PN = 1008.0,
	 PNALT = 1008.0,
	 init_APREF = 0.006249999999999996,
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
	 init_EFD = 0.3409471722756659,
	 init_VREF = 0.9716477510436048,
	 init_YLL = 0.00170473586137833,
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
	 init_REF = 0.0003474022646004265,
	 init_PMECH = 0.006948045292008529,
	 init_CM = 0.006948045292008529,
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
	 init_EFD = 0.3403599785716129,
	 init_VREF = 0.9640896863254674,
	 init_YLL = 0.001701799892858065,
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
	 init_REF = 2.235682662196598e-08,
	 init_PMECH = 4.471365324393196e-07,
	 init_CM = 4.471365324393196e-07,
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
	 init_APREF = 4.20539024479226e-19,
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
	 init_EFD = 0.2880767009338198,
	 init_VREF = 0.9862085297434816,
	 init_YLL = 0.001440383504669099,
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
	 init_REF = 4.251329255117296e-09,
	 init_PMECH = 8.502658510234592e-08,
	 init_CM = 8.502658510234592e-08,
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
	 init_APREF = -7.744301232039318e-20,
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
	 init_EFD = 0.3232379065027537,
	 init_VREF = 0.9818259865720339,
	 init_YLL = 0.001616189532513769,
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
	 init_REF = 8.728014348218681e-10,
	 init_PMECH = 1.745602869643736e-08,
	 init_CM = 1.745602869643736e-08,
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
	 init_EFD = 0.2935114148948189,
	 init_VREF = 0.9731517189791185,
	 init_YLL = 0.001467557074474095,
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
	 init_CM = -0.02988571275557734
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
	 init_EFD = 0.315737559423014,
	 init_VREF = 0.9891088797994612,
	 init_YLL = 0.00157868779711507,
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
	 init_CM = -0.03972377662817821
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
	 init_EFD = 0.2981667251553087,
	 init_VREF = 1.006179950120516,
	 init_YLL = 0.001490833625776544,
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
	 init_REF = 0.0006397553909277478,
	 init_PMECH = 0.01279510781855496,
	 init_CM = 0.01279510781855496,
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
	 init_EFD = 0.4565727498799821,
	 init_VREF = 1.037515925910535,
	 init_YLL = 0.002282863749399911,
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
	 init_REF = 0.01012732589108298,
	 init_PMECH = 0.2025465178216596,
	 init_CM = 0.2025465178216596,
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
	 init_EFD = 0.315661074149361,
	 init_VREF = 0.956953794287155,
	 init_YLL = 0.001578305370746805,
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
	 init_REF = 0.002381354448819018,
	 init_PMECH = 0.04762708897638036,
	 init_CM = 0.04762708897638036,
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
	 init_APREF = -6.424901762877063e-19,
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
	 init_EFD = 0.4485847571536813,
	 init_VREF = 0.9560578453827877,
	 init_YLL = 0.002242923785768407,
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
	 init_REF = 1.005274547786842e-07,
	 init_PMECH = 2.010549095573684e-06,
	 init_CM = 2.010549095573684e-06,
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
	 init_APREF = -7.162359520961219e-20,
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
	 init_EFD = 0.3420386992136589,
	 init_VREF = 0.9555122549685746,
	 init_YLL = 0.001710193496068294,
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
	 init_REF = 6.08145009310704e-10,
	 init_PMECH = 1.216290018621408e-08,
	 init_CM = 1.216290018621408e-08,
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
	 init_EFD = 0.437797616896519,
	 init_VREF = 0.9940309895992775,
	 init_YLL = 0.002188988084482595,
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
	 init_REF = 0.007113423440661944,
	 init_PMECH = 0.1422684688132389,
	 init_CM = 0.1422684688132389,
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
	 init_APREF = 0.09696969696969698,
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
	 init_EFD = 0.2812497768191802,
	 init_VREF = 0.993342286135372,
	 init_YLL = 0.001406248884095901,
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
	 init_REF = 0.005389042790371688,
	 init_PMECH = 0.1077808558074337,
	 init_CM = 0.1077808558074337,
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
	 init_APREF = 5.256737805990325e-20,
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
	 init_EFD = 0.2983876805000537,
	 init_VREF = 0.999568267578741,
	 init_YLL = 0.001491938402500268,
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
	 init_REF = 1.023719324387881e-10,
	 init_PMECH = 2.047438648775762e-09,
	 init_CM = 2.047438648775762e-09,
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
	 init_APREF = 0.2286549707602339,
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
	 init_EFD = 0.3771455501597529,
	 init_VREF = 1.010845976965046,
	 init_YLL = 0.001885727750798765,
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
	 init_REF = 0.01271272171276925,
	 init_PMECH = 0.2542544342553851,
	 init_CM = 0.2542544342553851,
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
	 init_APREF = 0.2292397660818713,
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
	 init_EFD = 0.3692744846585141,
	 init_VREF = 1.05286820364499,
	 init_YLL = 0.001846372423292571,
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
	 init_REF = 0.01274422647931698,
	 init_PMECH = 0.2548845295863396,
	 init_CM = 0.2548845295863396,
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
	 init_APREF = 0.4586004464285715,
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
	 init_EFD = 0.4715368003601067,
	 init_VREF = 1.032380246350808,
	 init_YLL = 0.002357684001800534,
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
	 init_REF = 0.02551743915260915,
	 init_PMECH = 0.510348783052183,
	 init_CM = 0.510348783052183,
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
	 init_APREF = 6.195440985631454e-19,
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
	 init_EFD = 0.3308291864780229,
	 init_VREF = 0.9865304812506498,
	 init_YLL = 0.001654145932390114,
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
	 init_REF = 1.523033308122157e-08,
	 init_PMECH = 3.046066616244313e-07,
	 init_CM = 3.046066616244313e-07,
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
	 init_APREF = -0.00990916597853014,
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
	 init_EFD = 0.3548191054748525,
	 init_VREF = 0.9808367561851621,
	 init_YLL = 0.001774095527374262,
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
	 init_CM = -0.01100858347107196
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___73_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_APREF = -0.004954582989265071,
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
	 init_EFD = 0.3786766821660669,
	 init_VREF = 0.9936976859022683,
	 init_YLL = 0.001893383410830334,
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
	 init_CM = -0.005504308618182346
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___74_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_APREF = 2.10269512239613e-19,
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
	 init_EFD = 0.2784906237110345,
	 init_VREF = 0.9590365032445026,
	 init_YLL = 0.001392453118555173,
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
	 init_REF = 2.223603142708138e-09,
	 init_PMECH = 4.447206285416276e-08,
	 init_CM = 4.447206285416276e-08,
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
	 init_APREF = 1.387778780781446e-18,
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
	 init_EFD = 0.4608808673292581,
	 init_VREF = 0.9475383142996339,
	 init_YLL = 0.002304404336646291,
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
	 init_REF = 1.031047652190966e-07,
	 init_PMECH = 2.062095304381932e-06,
	 init_CM = 2.062095304381932e-06,
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
	 init_APREF = -5.729887616768975e-19,
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
	 init_EFD = 0.3930202397755059,
	 init_VREF = 1.008944552953015,
	 init_YLL = 0.00196510119887753,
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
	 init_REF = 1.489888268386203e-08,
	 init_PMECH = 2.979776536772406e-07,
	 init_CM = 2.979776536772406e-07,
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
	 init_EFD = 0.4439144812935756,
	 init_VREF = 1.049192863583952,
	 init_YLL = 0.002219572406467878,
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
	 init_REF = 0.01607493664271525,
	 init_PMECH = 0.321498732854305,
	 init_CM = 0.321498732854305,
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
	 init_APREF = -2.10269512239613e-19,
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
	 init_EFD = 0.2891647099430403,
	 init_VREF = 0.9860906589598957,
	 init_YLL = 0.001445823549715201,
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
	 init_REF = 2.213926025892202e-09,
	 init_PMECH = 4.427852051784405e-08,
	 init_CM = 4.427852051784405e-08,
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
	 init_APREF = 0.002339181286549707,
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
	 init_EFD = 0.3020853751405309,
	 init_VREF = 1.017145572755164,
	 init_YLL = 0.001510426875702654,
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
	 init_REF = 0.0001299628190667786,
	 init_PMECH = 0.002599256381335572,
	 init_CM = 0.002599256381335572,
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
	 init_APREF = 0.5012386457473165,
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
	 init_EFD = 0.533052914713819,
	 init_VREF = 1.007934735576952,
	 init_YLL = 0.002665264573569095,
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
	 init_REF = 0.02788268634401757,
	 init_PMECH = 0.5576537268803513,
	 init_CM = 0.5576537268803513,
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
	 init_APREF = -0.07018992568125516,
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
	 init_EFD = 0.4137737854098854,
	 init_VREF = 0.992065796108251,
	 init_YLL = 0.002068868927049427,
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
	 init_CM = -0.0779582005962368
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___91_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_APREF = -0.008257638315441787,
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
	 init_EFD = 0.3521036240655027,
	 init_VREF = 0.9805101178105928,
	 init_YLL = 0.001760518120327513,
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
	 init_CM = -0.009173605393208446
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN___92_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_APREF = 1.432471904192244e-19,
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
	 init_EFD = 0.3704680166940723,
	 init_VREF = 0.9938774434677137,
	 init_YLL = 0.001852340083470362,
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
	 init_REF = 9.680886971770515e-10,
	 init_PMECH = 1.936177394354103e-08,
	 init_CM = 1.936177394354103e-08,
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
	 init_EFD = 0.2859172918148464,
	 init_VREF = 1.010417150559217,
	 init_YLL = 0.001429586459074232,
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
	 init_CM = -0.02728792124077176
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN____1_SM (
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
  sexs reg_sexs__GEN____1_SM (
	 SNREF = 100.0,
	 SN = 1211.0,
	 PN = 1090.0,
	 PNALT = 1090.0,
	 init_EFD = 0.3417486687952813,
	 init_VREF = 0.9563933055694177,
	 init_YLL = 0.001708743343976407,
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
	 init_REF = 1.544692751065066e-09,
	 init_PMECH = 3.089385502130133e-08,
	 init_CM = 3.089385502130133e-08,
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
	 init_EFD = 0.3083963751384893,
	 init_VREF = 0.9970690551506547,
	 init_YLL = 0.001541981875692447,
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
	 init_CM = -0.00892588792802146
	 ) annotation (Placement(transformation()));
  pssi3e2b reg_pssi3e2b__GEN____6_SM (
	 SNREF = 100.0,
	 SN = 1650.0,
	 PN = 1485.0,
	 PNALT = 1485.0,
	 init_APREF = 4.20539024479226e-19,
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
	 init_EFD = 0.3018926089664479,
	 init_VREF = 0.9923725180098677,
	 init_YLL = 0.00150946304483224,
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
	 init_REF = 1.30780163406035e-08,
	 init_PMECH = 2.615603268120701e-07,
	 init_CM = 2.615603268120701e-07,
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
	 init_EFD = 0.3371361546332883,
	 init_VREF = 1.021206359803787,
	 init_YLL = 0.001685680773166441,
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
	 init_CM = -0.01818525272777948
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
  connect(bus__BELL__74_TN.p, cap_pwCapacitorBank__BELL__74_SC.p) annotation (Line());
  connect(bus__CAPI__79_TN.p, cap_pwCapacitorBank__CAPI__79_SC.p) annotation (Line());
  connect(bus__EAST__37_TN.p, cap_pwCapacitorBank__EAST__37_SC.p) annotation (Line());
  connect(bus__FIEL_110_TN.p, cap_pwCapacitorBank__FIEL_110_SC.p) annotation (Line());
  connect(bus__LOGA__82_TN.p, cap_pwCapacitorBank__LOGA__82_SC.p) annotation (Line());
  connect(bus__N_NE__45_TN.p, cap_pwCapacitorBank__N_NE__45_SC.p) annotation (Line());
  connect(bus__OLIV___5_TN.p, cap_pwCapacitorBank__OLIV___5_SC.p) annotation (Line());
  connect(bus__REUS_107_TN.p, cap_pwCapacitorBank__REUS_107_SC.p) annotation (Line());
  connect(bus__ROAN_105_TN.p, cap_pwCapacitorBank__ROAN_105_SC.p) annotation (Line());
  connect(bus__ROCK__34_TN.p, cap_pwCapacitorBank__ROCK__34_SC.p) annotation (Line());
  connect(bus__SPRI__83_TN.p, cap_pwCapacitorBank__SPRI__83_SC.p) annotation (Line());
  connect(bus__W_LA__46_TN.p, cap_pwCapacitorBank__W_LA__46_SC.p) annotation (Line());
  connect(bus__WMVE__44_TN.p, cap_pwCapacitorBank__WMVE__44_SC.p) annotation (Line());
  connect(bus__ZANE__48_TN.p, cap_pwCapacitorBank__ZANE__48_SC.p) annotation (Line());

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
end ieee118bus;

