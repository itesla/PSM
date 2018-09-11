within ;
model N44_h21
  parameter Real SNREF = 100.0;


// BUSES
  iPSL.Electrical.Buses.Bus bus__f17695ab_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 angle_0 = -1.4502187
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f17695b9_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 0.99625874,
	 angle_0 = -2.0914793
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f17695bf_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.006532,
	 angle_0 = 3.8220153
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f17695c9_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 angle_0 = 17.937197
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f17695d3_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0062045,
	 angle_0 = -3.126339
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f17695d9_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 0.99587077,
	 angle_0 = -2.3843284
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f17695e0_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 angle_0 = -1.3845582
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f17695e6_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 angle_0 = 28.005104
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f17695ec_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 angle_0 = 0.016703438
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f17695f2_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 angle_0 = -9.703579
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f17695f8_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 0.9999768,
	 angle_0 = -9.705682
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f17695ff_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0083345,
	 angle_0 = 25.08945
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f1769605_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 angle_0 = -6.963138
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f176960f_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.007533,
	 angle_0 = -6.511325
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f1769615_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0179502,
	 angle_0 = -3.5715857
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f176961f_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0173043,
	 angle_0 = -2.8766682
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f1769625_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 angle_0 = 12.155354
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f176962b_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0064234,
	 angle_0 = 5.7849493
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f1769631_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0172424,
	 angle_0 = 1.1330074
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f1769637_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0214252,
	 angle_0 = 1.7263682
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f176963d_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.007,
	 angle_0 = -5.2827387
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f1769643_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0078619,
	 angle_0 = -5.9883447
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f1769649_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0061611,
	 angle_0 = -4.184584
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f176964f_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0040001,
	 angle_0 = -9.141229
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f1769655_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 0.99879783,
	 angle_0 = -8.792417
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f176965b_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.01,
	 angle_0 = -11.407857
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f1769665_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0064805,
	 angle_0 = -3.6237621
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f176966b_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.003957,
	 angle_0 = -11.265177
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f1769671_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0030867,
	 angle_0 = -11.894847
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f1769677_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0023856,
	 angle_0 = -12.171218
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f176967d_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0075927,
	 angle_0 = -11.818858
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f1769683_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.005,
	 angle_0 = -2.3064342
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f1769689_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0063494,
	 angle_0 = -3.4500523
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f176968f_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 angle_0 = 18.584608
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f1769695_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 angle_0 = -12.373396
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f176969f_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.02,
	 angle_0 = 13.573054
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f17696a9_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 0.99658376,
	 angle_0 = 13.758685
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f17696af_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 angle_0 = 20.4655
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f17696b9_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0001773,
	 angle_0 = 20.496433
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f17696bf_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 0.99782425,
	 angle_0 = 20.089966
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f17696c5_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 angle_0 = 20.622072
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f17696cb_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.02,
	 angle_0 = -14.291729
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f17696d5_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0183055,
	 angle_0 = -14.579127
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Buses.Bus bus__f17696db_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0180327,
	 angle_0 = -14.625002
	 ) annotation (Placement(transformation()));

// LOADS
  iPSL.Electrical.Loads.PSSE.Load load_load__f17696e0_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=2.63322, im=0.89094),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0,
	 PQBRAK = 0.7,
	 angle_0 = -1.4502187
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f17696e6_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=2.63322, im=0.89094),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0,
	 PQBRAK = 0.7,
	 angle_0 = -1.4502187
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f17696ec_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=2.63322, im=0.89094),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0,
	 PQBRAK = 0.7,
	 angle_0 = -1.4502187
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f17696f2_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=11.15, im=3.66483),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 0.99625874,
	 PQBRAK = 0.7,
	 angle_0 = -2.0914793
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f17696f8_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=0.90092003, im=0.94289),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.006532,
	 PQBRAK = 0.7,
	 angle_0 = 3.8220153
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f17696fe_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=9.6, im=3.1553698),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0,
	 PQBRAK = 0.7,
	 angle_0 = 17.937197
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f1769704_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=18.919079, im=5.57162),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0,
	 PQBRAK = 0.7,
	 angle_0 = 28.005104
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f176970a_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=11.522111, im=3.20953),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0,
	 PQBRAK = 0.7,
	 angle_0 = 0.016703438
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f1769710_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=11.522111, im=3.20953),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0,
	 PQBRAK = 0.7,
	 angle_0 = 0.016703438
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f1769716_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=13.82652, im=4.81428),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0,
	 PQBRAK = 0.7,
	 angle_0 = -9.703579
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f176971c_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=13.82652, im=4.81428),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0,
	 PQBRAK = 0.7,
	 angle_0 = -9.703579
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f1769722_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=13.82652, im=4.81428),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0,
	 PQBRAK = 0.7,
	 angle_0 = -9.703579
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f1769728_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=13.82652, im=4.81428),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0,
	 PQBRAK = 0.7,
	 angle_0 = -9.703579
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f176972e_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=0.02, im=0.00657),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 0.9999768,
	 PQBRAK = 0.7,
	 angle_0 = -9.705682
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f1769734_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=5.3016996, im=1.47856),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0,
	 PQBRAK = 0.7,
	 angle_0 = -6.963138
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f176973a_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=18.7, im=6.14639),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0,
	 PQBRAK = 0.7,
	 angle_0 = 12.155354
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f1769740_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=11.085401, im=2.11221),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.007,
	 PQBRAK = 0.7,
	 angle_0 = -5.2827387
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f1769746_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=10.12145, im=4.22446),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0040001,
	 PQBRAK = 0.7,
	 angle_0 = -9.141229
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f176974c_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=10.12145, im=4.22446),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0040001,
	 PQBRAK = 0.7,
	 angle_0 = -9.141229
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f1769752_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=12.612629, im=2.56985),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.01,
	 PQBRAK = 0.7,
	 angle_0 = -11.407857
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f1769758_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=12.612629, im=2.56985),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.01,
	 PQBRAK = 0.7,
	 angle_0 = -11.407857
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f176975e_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=4.85, im=0.69109),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0023856,
	 PQBRAK = 0.7,
	 angle_0 = -12.171218
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f1769764_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=7.3, im=2.39939),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0075927,
	 PQBRAK = 0.7,
	 angle_0 = -11.818858
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f176976a_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=6.44238, im=2.3627698),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0,
	 PQBRAK = 0.7,
	 angle_0 = 18.584608
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f1769770_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=6.44238, im=2.3627698),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0,
	 PQBRAK = 0.7,
	 angle_0 = 18.584608
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f1769776_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=8.52333, im=2.80148),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0,
	 PQBRAK = 0.7,
	 angle_0 = -12.373396
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f176977c_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=8.52333, im=2.80148),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0,
	 PQBRAK = 0.7,
	 angle_0 = -12.373396
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f1769782_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=8.52333, im=2.80148),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0,
	 PQBRAK = 0.7,
	 angle_0 = -12.373396
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f1769788_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=21.55, im=7.0831404),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.02,
	 PQBRAK = 0.7,
	 angle_0 = 13.573054
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f176978e_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=0.39, im=0.12819),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 0.99658376,
	 PQBRAK = 0.7,
	 angle_0 = 13.758685
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f1769794_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=-0.36, im=-0.11833),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 0.99658376,
	 PQBRAK = 0.7,
	 angle_0 = 13.758685
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f176979a_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=13.33113, im=2.77964),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0,
	 PQBRAK = 0.7,
	 angle_0 = 20.4655
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f17697a0_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=13.33113, im=2.77964),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0,
	 PQBRAK = 0.7,
	 angle_0 = 20.4655
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f17697a6_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=13.33113, im=2.77964),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0,
	 PQBRAK = 0.7,
	 angle_0 = 20.4655
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f17697ac_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=13.33113, im=2.77964),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0,
	 PQBRAK = 0.7,
	 angle_0 = 20.4655
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f17697b2_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=13.33113, im=2.77964),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0,
	 PQBRAK = 0.7,
	 angle_0 = 20.4655
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f17697b8_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=-11.15, im=-3.66483),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0,
	 PQBRAK = 0.7,
	 angle_0 = 20.4655
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f17697be_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=-0.54, im=-0.17749001),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0001773,
	 PQBRAK = 0.7,
	 angle_0 = 20.496433
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f17697c4_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=6.54, im=2.14959),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 0.99782425,
	 PQBRAK = 0.7,
	 angle_0 = 20.089966
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f17697ca_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=11.9771805, im=7.94194),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0,
	 PQBRAK = 0.7,
	 angle_0 = 20.622072
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f17697d0_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=11.9771805, im=7.94194),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0,
	 PQBRAK = 0.7,
	 angle_0 = 20.622072
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f17697d6_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=-0.39, im=-0.12819),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0,
	 PQBRAK = 0.7,
	 angle_0 = 20.622072
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f17697dc_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=8.17667, im=2.68754),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.02,
	 PQBRAK = 0.7,
	 angle_0 = -14.291729
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f17697e2_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=8.17667, im=2.68754),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.02,
	 PQBRAK = 0.7,
	 angle_0 = -14.291729
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f17697e8_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=8.17667, im=2.68754),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.02,
	 PQBRAK = 0.7,
	 angle_0 = -14.291729
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f17697ee_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=8.8, im=2.89242),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.02,
	 PQBRAK = 0.7,
	 angle_0 = -14.291729
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f17697f4_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=5.21, im=1.71244),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0183055,
	 PQBRAK = 0.7,
	 angle_0 = -14.579127
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Loads.PSSE.Load load_load__f17697fa_9aeb_11e5_91da_b8763fd99c5f (
	 S_p(re=6.04, im=1.98525),
	 S_i(re=0, im=0),
	 S_y(re=0, im=0),
	 a(re=1, im=0),
	 b(re=0, im=1),
	 V_0 = 1.0180327,
	 PQBRAK = 0.7,
	 angle_0 = -14.625002
	 ) annotation (Placement(transformation()));

// TAP CHANGER TRANSFORMERS
  iPSL.Electrical.Branches.PwPhaseTransformer trafo_PwPhaseTransformer__f17695e0_9aeb_11e5_91da_b8763fd99c5f__f17695d9_9aeb_11e5_91da_b8763fd99c5f_1 (
	 r = 1.0,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 5.0E-4,
	 X = 0.002
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwPhaseTransformer trafo_PwPhaseTransformer__f17695e6_9aeb_11e5_91da_b8763fd99c5f__f17695ff_9aeb_11e5_91da_b8763fd99c5f_1 (
	 r = 1.0,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 0.002,
	 X = 0.049999994574652776
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwPhaseTransformer trafo_PwPhaseTransformer__f17695f8_9aeb_11e5_91da_b8763fd99c5f__f17695f2_9aeb_11e5_91da_b8763fd99c5f_1 (
	 r = 1.0,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 5.000000259503215E-4,
	 X = 0.002000000103801286
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwPhaseTransformer trafo_PwPhaseTransformer__f1769605_9aeb_11e5_91da_b8763fd99c5f__f176960f_9aeb_11e5_91da_b8763fd99c5f_1 (
	 r = 1.0250001,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 8.000000263828269E-5,
	 X = 0.003050000110721372
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwPhaseTransformer trafo_PwPhaseTransformer__f176962b_9aeb_11e5_91da_b8763fd99c5f__f1769625_9aeb_11e5_91da_b8763fd99c5f_1 (
	 r = 1.0,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 1.5999999576144747E-4,
	 X = 0.006099773491753472
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwPhaseTransformer trafo_PwPhaseTransformer__f1769643_9aeb_11e5_91da_b8763fd99c5f__f176963d_9aeb_11e5_91da_b8763fd99c5f_1 (
	 r = 1.0,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 3.1999999152289494E-4,
	 X = 0.011999998643663195
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwPhaseTransformer trafo_PwPhaseTransformer__f1769649_9aeb_11e5_91da_b8763fd99c5f__f176963d_9aeb_11e5_91da_b8763fd99c5f_1 (
	 r = 1.0,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 3.999999894036187E-5,
	 X = 0.0014999998304578994
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwPhaseTransformer trafo_PwPhaseTransformer__f1769655_9aeb_11e5_91da_b8763fd99c5f__f176964f_9aeb_11e5_91da_b8763fd99c5f_1 (
	 r = 1.025,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 3.999999894036187E-5,
	 X = 0.0014999998304578994
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwPhaseTransformer trafo_PwPhaseTransformer__f1769689_9aeb_11e5_91da_b8763fd99c5f__f1769665_9aeb_11e5_91da_b8763fd99c5f_1 (
	 r = 1.0,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 1.9999999470180934E-5,
	 X = 7.59977298312717E-4
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwPhaseTransformer trafo_PwPhaseTransformer__f176966b_9aeb_11e5_91da_b8763fd99c5f__f1769671_9aeb_11e5_91da_b8763fd99c5f_1 (
	 r = 1.0,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 7.999999788072374E-5,
	 X = 0.00305
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwPhaseTransformer trafo_PwPhaseTransformer__f1769689_9aeb_11e5_91da_b8763fd99c5f__f1769683_9aeb_11e5_91da_b8763fd99c5f_1 (
	 r = 1.0,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 3.999999894036187E-5,
	 X = 0.0014999998304578994
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwPhaseTransformer trafo_PwPhaseTransformer__f17696a9_9aeb_11e5_91da_b8763fd99c5f__f176969f_9aeb_11e5_91da_b8763fd99c5f_1 (
	 r = 1.025,
	 B0 = 0.0,
	 G0 = 0.0,
	 theta = 0.0,
	 R = 5.0E-4,
	 X = 0.002
	 ) annotation (Placement(transformation()));

// LINES
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f17695b9_9aeb_11e5_91da_b8763fd99c5f__f17695ab_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.0,
	 X = 0.001,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f17695c9_9aeb_11e5_91da_b8763fd99c5f__f17695ab_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.0075,
	 X = 0.089999996,
	 G = 0.0,
	 B = 2.4999998
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f17695e0_9aeb_11e5_91da_b8763fd99c5f__f17695ab_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 8.0000004E-4,
	 X = 0.011999999,
	 G = 0.0,
	 B = 0.24999999
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f17695e0_9aeb_11e5_91da_b8763fd99c5f__f17695ab_9aeb_11e5_91da_b8763fd99c5f_2 (
	 R = 0.0018,
	 X = 0.02,
	 G = 0.0,
	 B = 0.24999999
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f17695ec_9aeb_11e5_91da_b8763fd99c5f__f17695ab_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 6.0E-4,
	 X = 0.008,
	 G = 0.0,
	 B = 0.14999998
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f17695ec_9aeb_11e5_91da_b8763fd99c5f__f17695ab_9aeb_11e5_91da_b8763fd99c5f_2 (
	 R = 9.0E-4,
	 X = 0.01,
	 G = 0.0,
	 B = 0.12500004
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f17695c9_9aeb_11e5_91da_b8763fd99c5f__f17695bf_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.0014999999,
	 X = 0.02,
	 G = 0.0,
	 B = 0.99999994
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f17695d3_9aeb_11e5_91da_b8763fd99c5f__f17695bf_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.004,
	 X = 0.023999998,
	 G = 0.0,
	 B = 0.99999994
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f17695d3_9aeb_11e5_91da_b8763fd99c5f__f17695bf_9aeb_11e5_91da_b8763fd99c5f_2 (
	 R = 0.004,
	 X = 0.023999998,
	 G = 0.0,
	 B = 0.99999994
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f17695d3_9aeb_11e5_91da_b8763fd99c5f__f17695bf_9aeb_11e5_91da_b8763fd99c5f_3 (
	 R = 0.004,
	 X = 0.023999998,
	 G = 0.0,
	 B = 0.99999994
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f17695e6_9aeb_11e5_91da_b8763fd99c5f__f17695bf_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.0014999999,
	 X = 0.021499999,
	 G = 0.0,
	 B = 0.99999994
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f17695f2_9aeb_11e5_91da_b8763fd99c5f__f17695bf_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.008,
	 X = 0.049999997,
	 G = 0.0,
	 B = 1.25
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f17695f2_9aeb_11e5_91da_b8763fd99c5f__f17695bf_9aeb_11e5_91da_b8763fd99c5f_2 (
	 R = 0.004,
	 X = 0.023,
	 G = 0.0,
	 B = 1.2
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f17695e0_9aeb_11e5_91da_b8763fd99c5f__f17695c9_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.0045000003,
	 X = 0.049999997,
	 G = 0.0,
	 B = 0.7
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f17695e6_9aeb_11e5_91da_b8763fd99c5f__f17695c9_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.0014999999,
	 X = 0.02,
	 G = 0.0,
	 B = 0.39999995
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f17696a9_9aeb_11e5_91da_b8763fd99c5f__f17695c9_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.004,
	 X = 0.04,
	 G = 0.0,
	 B = 0.49999997
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f17696c5_9aeb_11e5_91da_b8763fd99c5f__f17695c9_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.004,
	 X = 0.012999999,
	 G = 0.0,
	 B = 0.65000004
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f17695ec_9aeb_11e5_91da_b8763fd99c5f__f17695d3_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.002,
	 X = 0.02,
	 G = 0.0,
	 B = 0.30000004
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f17695f2_9aeb_11e5_91da_b8763fd99c5f__f17695d3_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.001,
	 X = 0.02,
	 G = 0.0,
	 B = 0.35
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f17696cb_9aeb_11e5_91da_b8763fd99c5f__f17695d3_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.001,
	 X = 0.017,
	 G = 0.0,
	 B = 0.30000004
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f1769695_9aeb_11e5_91da_b8763fd99c5f__f17695d9_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 9.999999E-4,
	 X = 0.02,
	 G = 0.0,
	 B = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f17696c5_9aeb_11e5_91da_b8763fd99c5f__f17695e6_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.002,
	 X = 0.0075,
	 G = 0.0,
	 B = 0.39000005
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f17696cb_9aeb_11e5_91da_b8763fd99c5f__f17695ec_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.002,
	 X = 0.023,
	 G = 0.0,
	 B = 0.30000004
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f17696cb_9aeb_11e5_91da_b8763fd99c5f__f17695ec_9aeb_11e5_91da_b8763fd99c5f_2 (
	 R = 0.0012,
	 X = 0.026999999,
	 G = 0.0,
	 B = 0.49999997
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f176960f_9aeb_11e5_91da_b8763fd99c5f__f17695f2_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.0016000001,
	 X = 0.025999999,
	 G = 0.0,
	 B = 0.45000002
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f176960f_9aeb_11e5_91da_b8763fd99c5f__f17695f2_9aeb_11e5_91da_b8763fd99c5f_2 (
	 R = 0.002,
	 X = 0.022,
	 G = 0.0,
	 B = 0.30000004
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f17696cb_9aeb_11e5_91da_b8763fd99c5f__f17695f2_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.0012,
	 X = 0.026999999,
	 G = 0.0,
	 B = 0.49999997
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f17696cb_9aeb_11e5_91da_b8763fd99c5f__f17695f2_9aeb_11e5_91da_b8763fd99c5f_2 (
	 R = 0.0025,
	 X = 0.032,
	 G = 0.0,
	 B = 0.45000002
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f176969f_9aeb_11e5_91da_b8763fd99c5f__f17695ff_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.025,
	 X = 0.2,
	 G = 0.0,
	 B = 0.14999999
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f176964f_9aeb_11e5_91da_b8763fd99c5f__f1769605_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.0027,
	 X = 0.025999999,
	 G = 0.0,
	 B = 0.22
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f1769695_9aeb_11e5_91da_b8763fd99c5f__f1769605_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.007999999,
	 X = 0.09,
	 G = 0.0,
	 B = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f1769615_9aeb_11e5_91da_b8763fd99c5f__f176960f_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 8.0000004E-4,
	 X = 0.01,
	 G = 0.0,
	 B = 0.45000002
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f176961f_9aeb_11e5_91da_b8763fd99c5f__f176960f_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.001,
	 X = 0.0139999995,
	 G = 0.0,
	 B = 0.20000003
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f1769655_9aeb_11e5_91da_b8763fd99c5f__f176960f_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.001,
	 X = 0.015,
	 G = 0.0,
	 B = 2.75
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f176961f_9aeb_11e5_91da_b8763fd99c5f__f1769615_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 4.0000002E-4,
	 X = 0.0069999998,
	 G = 0.0,
	 B = 0.14999998
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f1769631_9aeb_11e5_91da_b8763fd99c5f__f1769615_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.0017,
	 X = 0.023999998,
	 G = 0.0,
	 B = 0.35
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f1769689_9aeb_11e5_91da_b8763fd99c5f__f1769615_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.0029999998,
	 X = 0.046,
	 G = 0.0,
	 B = 0.65000004
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f1769631_9aeb_11e5_91da_b8763fd99c5f__f176961f_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.002,
	 X = 0.024999999,
	 G = 0.0,
	 B = 0.35
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f1769631_9aeb_11e5_91da_b8763fd99c5f__f176961f_9aeb_11e5_91da_b8763fd99c5f_2 (
	 R = 0.0013,
	 X = 0.02,
	 G = 0.0,
	 B = 0.30000004
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f176968f_9aeb_11e5_91da_b8763fd99c5f__f1769625_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.0021,
	 X = 0.022,
	 G = 0.0,
	 B = 0.049999993
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f1769631_9aeb_11e5_91da_b8763fd99c5f__f176962b_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.001,
	 X = 0.02,
	 G = 0.0,
	 B = 0.30000004
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f1769637_9aeb_11e5_91da_b8763fd99c5f__f176962b_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 7.0E-4,
	 X = 0.011999999,
	 G = 0.0,
	 B = 0.15500003
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f1769689_9aeb_11e5_91da_b8763fd99c5f__f176962b_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.0013,
	 X = 0.02,
	 G = 0.0,
	 B = 0.24999999
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f1769637_9aeb_11e5_91da_b8763fd99c5f__f1769631_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.001,
	 X = 0.015,
	 G = 0.0,
	 B = 0.24999999
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f1769637_9aeb_11e5_91da_b8763fd99c5f__f1769631_9aeb_11e5_91da_b8763fd99c5f_2 (
	 R = 0.0013,
	 X = 0.0017,
	 G = 0.0,
	 B = 0.20000003
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f176964f_9aeb_11e5_91da_b8763fd99c5f__f176963d_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 9.0E-4,
	 X = 0.0094,
	 G = 0.0,
	 B = 0.25000003
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f1769683_9aeb_11e5_91da_b8763fd99c5f__f176963d_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.0033,
	 X = 0.036000002,
	 G = 0.0,
	 B = 0.12500001
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f1769655_9aeb_11e5_91da_b8763fd99c5f__f1769643_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.0017499999,
	 X = 0.026999999,
	 G = 0.0,
	 B = 0.39999995
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f176966b_9aeb_11e5_91da_b8763fd99c5f__f1769643_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.0016000001,
	 X = 0.0255,
	 G = 0.0,
	 B = 0.45000002
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f1769689_9aeb_11e5_91da_b8763fd99c5f__f1769643_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 6.4E-4,
	 X = 0.01,
	 G = 0.0,
	 B = 0.14000003
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f1769689_9aeb_11e5_91da_b8763fd99c5f__f1769649_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 7.0E-5,
	 X = 0.001,
	 G = 0.0,
	 B = 0.014999997
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f1769671_9aeb_11e5_91da_b8763fd99c5f__f176964f_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.005,
	 X = 0.06,
	 G = 0.0,
	 B = 0.25000003
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f1769665_9aeb_11e5_91da_b8763fd99c5f__f176965b_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.003,
	 X = 0.034,
	 G = 0.0,
	 B = 0.09999999
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f1769671_9aeb_11e5_91da_b8763fd99c5f__f176965b_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.0019999999,
	 X = 0.022,
	 G = 0.0,
	 B = 0.09999999
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f176967d_9aeb_11e5_91da_b8763fd99c5f__f176965b_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.0,
	 X = 9.999999E-4,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f1769683_9aeb_11e5_91da_b8763fd99c5f__f176965b_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.0019999999,
	 X = 0.02,
	 G = 0.0,
	 B = 0.35000002
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f1769677_9aeb_11e5_91da_b8763fd99c5f__f1769671_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.0,
	 X = 9.999999E-4,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f176968f_9aeb_11e5_91da_b8763fd99c5f__f1769683_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.0034,
	 X = 0.042,
	 G = 0.0,
	 B = 0.14999999
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f176969f_9aeb_11e5_91da_b8763fd99c5f__f1769695_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.017,
	 X = 0.18,
	 G = 0.0,
	 B = 0.5
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f176969f_9aeb_11e5_91da_b8763fd99c5f__f1769695_9aeb_11e5_91da_b8763fd99c5f_2 (
	 R = 0.01,
	 X = 0.13,
	 G = 0.0,
	 B = 0.59999996
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f17696b9_9aeb_11e5_91da_b8763fd99c5f__f17696af_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.0,
	 X = 0.001,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f17696bf_9aeb_11e5_91da_b8763fd99c5f__f17696af_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.0,
	 X = 0.001,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f17696c5_9aeb_11e5_91da_b8763fd99c5f__f17696af_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.004,
	 X = 0.011999999,
	 G = 0.0,
	 B = 0.65000004
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f17696c5_9aeb_11e5_91da_b8763fd99c5f__f17696af_9aeb_11e5_91da_b8763fd99c5f_2 (
	 R = 0.004,
	 X = 0.011999999,
	 G = 0.0,
	 B = 0.65000004
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f17696c5_9aeb_11e5_91da_b8763fd99c5f__f17696af_9aeb_11e5_91da_b8763fd99c5f_3 (
	 R = 0.004,
	 X = 0.0139999995,
	 G = 0.0,
	 B = 0.65000004
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f17696d5_9aeb_11e5_91da_b8763fd99c5f__f17696cb_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.0,
	 X = 0.001,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Branches.PwLine_2 line_PwLine_2__f17696db_9aeb_11e5_91da_b8763fd99c5f__f17696cb_9aeb_11e5_91da_b8763fd99c5f_1 (
	 R = 0.0,
	 X = 0.001,
	 G = 0.0,
	 B = 0.0
	 ) annotation (Placement(transformation()));

// CAPACITORS
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__f1769bd8_9aeb_11e5_91da_b8763fd99c5f (
	 B = -9.744000306352973,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__f1769bdb_9aeb_11e5_91da_b8763fd99c5f (
	 B = 9.744000306352973,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__f1769c02_9aeb_11e5_91da_b8763fd99c5f (
	 B = 0.0010000115730690595,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__f1769c05_9aeb_11e5_91da_b8763fd99c5f (
	 B = -0.0010000115730690595,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__f1769ca4_9aeb_11e5_91da_b8763fd99c5f (
	 B = -0.005000058065888879,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__f1769ca7_9aeb_11e5_91da_b8763fd99c5f (
	 B = 0.005000058065888879,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__f1769cc2_9aeb_11e5_91da_b8763fd99c5f (
	 B = 0.01299996019952232,
	 nsteps = 1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Banks.PwCapacitorBank cap_pwCapacitorBank__f1769cc5_9aeb_11e5_91da_b8763fd99c5f (
	 B = -0.01299996019952232,
	 nsteps = 1
	 ) annotation (Placement(transformation()));

// GENERATORS
  iPSL.Electrical.Machines.PSSE.GENROU.GENROU gen_gENROU__f1769804_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = -1.4502187, 
	 P_0 = 319.048, 
	 Q_0 = 156.146, 
	 M_b = 1300.0,
	 Tpd0 = 5.0,
	 Tppd0 = 0.05,
	 Tpq0 = 1.0,
	 Tppq0 = 0.05,
	 H = 5.97,
	 D = 0.0,
	 Xd = 2.22,
	 Xq = 2.13,
	 Xpd = 0.36,
	 Xpq = 0.468,
	 Xppd = 0.225,
	 Xppq = 0.225,
	 Xl = 0.16875,
	 S10 = 0.1089,
	 S12 = 0.37795
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENROU.GENROU gen_gENROU__f176980a_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = -1.4502187, 
	 P_0 = 319.048, 
	 Q_0 = 156.146, 
	 M_b = 1300.0,
	 Tpd0 = 5.0,
	 Tppd0 = 0.05,
	 Tpq0 = 1.0,
	 Tppq0 = 0.05,
	 H = 5.97,
	 D = 0.0,
	 Xd = 2.22,
	 Xq = 2.13,
	 Xpd = 0.36,
	 Xpq = 0.468,
	 Xppd = 0.225,
	 Xppq = 0.225,
	 Xl = 0.16875,
	 S10 = 0.1089,
	 S12 = 0.37795
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENROU.GENROU gen_gENROU__f1769810_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = -1.4502187, 
	 P_0 = 319.048, 
	 Q_0 = 156.146, 
	 M_b = 1300.0,
	 Tpd0 = 5.0,
	 Tppd0 = 0.05,
	 Tpq0 = 1.0,
	 Tppq0 = 0.05,
	 H = 5.97,
	 D = 0.0,
	 Xd = 2.22,
	 Xq = 2.13,
	 Xpd = 0.36,
	 Xpq = 0.468,
	 Xppd = 0.225,
	 Xppq = 0.225,
	 Xl = 0.16875,
	 S10 = 0.1089,
	 S12 = 0.37795
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f1769818_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 17.937197, 
	 P_0 = 443.671, 
	 Q_0 = 20.02446, 
	 M_b = 1100.0,
	 Tpd0 = 7.57,
	 Tppd0 = 0.045,
	 Tppq0 = 0.1,
	 H = 4.741,
	 D = 0.0,
	 Xd = 0.946,
	 Xq = 0.565,
	 Xpd = 0.29,
	 Xppd = 0.23,
	 Xppq = 0.23,
	 Xl = 0.11077,
	 S10 = 0.10239,
	 S12 = 0.2742
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f176981f_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 17.937197, 
	 P_0 = 443.671, 
	 Q_0 = 20.02446, 
	 M_b = 1100.0,
	 Tpd0 = 7.57,
	 Tppd0 = 0.045,
	 Tppq0 = 0.1,
	 H = 4.741,
	 D = 0.0,
	 Xd = 0.946,
	 Xq = 0.565,
	 Xpd = 0.29,
	 Xppd = 0.23,
	 Xppq = 0.23,
	 Xl = 0.11077,
	 S10 = 0.10239,
	 S12 = 0.2742
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f1769826_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 17.937197, 
	 P_0 = 443.671, 
	 Q_0 = 20.02446, 
	 M_b = 1100.0,
	 Tpd0 = 7.57,
	 Tppd0 = 0.045,
	 Tppq0 = 0.1,
	 H = 4.741,
	 D = 0.0,
	 Xd = 0.946,
	 Xq = 0.565,
	 Xpd = 0.29,
	 Xppd = 0.23,
	 Xppq = 0.23,
	 Xl = 0.11077,
	 S10 = 0.10239,
	 S12 = 0.2742
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f176982d_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 17.937197, 
	 P_0 = 443.671, 
	 Q_0 = 20.02446, 
	 M_b = 1100.0,
	 Tpd0 = 7.57,
	 Tppd0 = 0.045,
	 Tppq0 = 0.1,
	 H = 4.741,
	 D = 0.0,
	 Xd = 0.946,
	 Xq = 0.565,
	 Xpd = 0.29,
	 Xppd = 0.23,
	 Xppq = 0.23,
	 Xl = 0.11077,
	 S10 = 0.10239,
	 S12 = 0.2742
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f1769834_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 17.937197, 
	 P_0 = 443.671, 
	 Q_0 = 20.02446, 
	 M_b = 1100.0,
	 Tpd0 = 7.57,
	 Tppd0 = 0.045,
	 Tppq0 = 0.1,
	 H = 4.741,
	 D = 0.0,
	 Xd = 0.946,
	 Xq = 0.565,
	 Xpd = 0.29,
	 Xppd = 0.23,
	 Xppq = 0.23,
	 Xl = 0.1108,
	 S10 = 0.1024,
	 S12 = 0.2742
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f176983d_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = -1.3845582, 
	 P_0 = 236.894, 
	 Q_0 = 46.7108, 
	 M_b = 950.0,
	 Tpd0 = 5.0,
	 Tppd0 = 0.06,
	 Tppq0 = 0.1,
	 H = 3.3,
	 D = 0.0,
	 Xd = 0.75,
	 Xq = 0.5,
	 Xpd = 0.25,
	 Xppd = 0.15385,
	 Xppq = 0.15385,
	 Xl = 0.11538,
	 S10 = 0.10239,
	 S12 = 0.2742
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f1769845_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 28.005104, 
	 P_0 = 803.845, 
	 Q_0 = 36.68943, 
	 M_b = 1357.0,
	 Tpd0 = 10.13,
	 Tppd0 = 0.06,
	 Tppq0 = 0.1,
	 H = 4.543,
	 D = 0.0,
	 Xd = 1.036,
	 Xq = 0.63,
	 Xpd = 0.28,
	 Xppd = 0.21,
	 Xppq = 0.21,
	 Xl = 0.11538,
	 S10 = 0.10239,
	 S12 = 0.2742
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f176984c_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 28.005104, 
	 P_0 = 803.845, 
	 Q_0 = 36.68943, 
	 M_b = 1357.0,
	 Tpd0 = 10.13,
	 Tppd0 = 0.06,
	 Tppq0 = 0.1,
	 H = 4.543,
	 D = 0.0,
	 Xd = 1.036,
	 Xq = 0.63,
	 Xpd = 0.28,
	 Xppd = 0.21,
	 Xppq = 0.21,
	 Xl = 0.11538,
	 S10 = 0.10239,
	 S12 = 0.2742
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f1769853_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 28.005104, 
	 P_0 = 803.845, 
	 Q_0 = 36.68943, 
	 M_b = 1357.0,
	 Tpd0 = 10.13,
	 Tppd0 = 0.06,
	 Tppq0 = 0.1,
	 H = 4.543,
	 D = 0.0,
	 Xd = 1.036,
	 Xq = 0.63,
	 Xpd = 0.28,
	 Xppd = 0.21,
	 Xppq = 0.21,
	 Xl = 0.11538,
	 S10 = 0.10239,
	 S12 = 0.2742
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f176985a_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 28.005104, 
	 P_0 = 803.845, 
	 Q_0 = 36.68943, 
	 M_b = 1357.0,
	 Tpd0 = 10.13,
	 Tppd0 = 0.06,
	 Tppq0 = 0.1,
	 H = 4.543,
	 D = 0.0,
	 Xd = 1.036,
	 Xq = 0.63,
	 Xpd = 0.28,
	 Xppd = 0.21,
	 Xppq = 0.21,
	 Xl = 0.11538,
	 S10 = 0.10239,
	 S12 = 0.2742
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f1769861_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 28.005104, 
	 P_0 = 803.845, 
	 Q_0 = 36.68943, 
	 M_b = 1357.0,
	 Tpd0 = 10.13,
	 Tppd0 = 0.06,
	 Tppq0 = 0.1,
	 H = 4.543,
	 D = 0.0,
	 Xd = 1.036,
	 Xq = 0.63,
	 Xpd = 0.28,
	 Xppd = 0.21,
	 Xppq = 0.21,
	 Xl = 0.11538,
	 S10 = 0.10239,
	 S12 = 0.2742
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f1769868_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 28.005104, 
	 P_0 = 803.845, 
	 Q_0 = 36.68943, 
	 M_b = 1357.0,
	 Tpd0 = 10.13,
	 Tppd0 = 0.06,
	 Tppq0 = 0.1,
	 H = 4.543,
	 D = 0.0,
	 Xd = 1.036,
	 Xq = 0.63,
	 Xpd = 0.28,
	 Xppd = 0.21,
	 Xppq = 0.21,
	 Xl = 0.11538,
	 S10 = 0.10239,
	 S12 = 0.2742
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f176986f_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 28.005104, 
	 P_0 = 803.845, 
	 Q_0 = 36.68943, 
	 M_b = 1357.0,
	 Tpd0 = 10.13,
	 Tppd0 = 0.06,
	 Tppq0 = 0.1,
	 H = 4.543,
	 D = 0.0,
	 Xd = 1.036,
	 Xq = 0.63,
	 Xpd = 0.28,
	 Xppd = 0.21,
	 Xppq = 0.21,
	 Xl = 0.11538,
	 S10 = 0.10239,
	 S12 = 0.2742
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f1769876_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 28.005104, 
	 P_0 = 803.845, 
	 Q_0 = 36.68943, 
	 M_b = 1357.0,
	 Tpd0 = 10.13,
	 Tppd0 = 0.06,
	 Tppq0 = 0.1,
	 H = 4.543,
	 D = 0.0,
	 Xd = 1.036,
	 Xq = 0.63,
	 Xpd = 0.28,
	 Xppd = 0.21,
	 Xppq = 0.21,
	 Xl = 0.11538,
	 S10 = 0.10239,
	 S12 = 0.2742
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENROU.GENROU gen_gENROU__f176987f_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 0.016703438, 
	 P_0 = 862.307, 
	 Q_0 = 61.955997, 
	 M_b = 1100.0,
	 Tpd0 = 10.8,
	 Tppd0 = 0.05,
	 Tpq0 = 1.0,
	 Tppq0 = 0.05,
	 H = 6.0,
	 D = 0.0,
	 Xd = 2.42,
	 Xq = 2.0,
	 Xpd = 0.23,
	 Xpq = 0.4108,
	 Xppd = 0.16,
	 Xppq = 0.16,
	 Xl = 0.14812,
	 S10 = 0.1089,
	 S12 = 0.37795
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENROU.GENROU gen_gENROU__f1769886_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 0.016703438, 
	 P_0 = 862.307, 
	 Q_0 = 61.955997, 
	 M_b = 1100.0,
	 Tpd0 = 10.8,
	 Tppd0 = 0.05,
	 Tpq0 = 1.0,
	 Tppq0 = 0.05,
	 H = 6.0,
	 D = 0.0,
	 Xd = 2.42,
	 Xq = 2.0,
	 Xpd = 0.23,
	 Xpq = 0.4108,
	 Xppd = 0.16,
	 Xppq = 0.16,
	 Xl = 0.14812,
	 S10 = 0.1089,
	 S12 = 0.37795
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENROU.GENROU gen_gENROU__f176988d_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 0.016703438, 
	 P_0 = 862.307, 
	 Q_0 = 61.955997, 
	 M_b = 1100.0,
	 Tpd0 = 10.8,
	 Tppd0 = 0.05,
	 Tpq0 = 1.0,
	 Tppq0 = 0.05,
	 H = 6.0,
	 D = 0.0,
	 Xd = 2.42,
	 Xq = 2.0,
	 Xpd = 0.23,
	 Xpq = 0.4108,
	 Xppd = 0.16,
	 Xppq = 0.16,
	 Xl = 0.14812,
	 S10 = 0.1089,
	 S12 = 0.37795
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENROU.GENROU gen_gENROU__f1769894_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 0.016703438, 
	 P_0 = 862.307, 
	 Q_0 = 61.955997, 
	 M_b = 1100.0,
	 Tpd0 = 10.8,
	 Tppd0 = 0.05,
	 Tpq0 = 1.0,
	 Tppq0 = 0.05,
	 H = 6.0,
	 D = 0.0,
	 Xd = 2.42,
	 Xq = 2.0,
	 Xpd = 0.23,
	 Xpq = 0.4108,
	 Xppd = 0.16,
	 Xppq = 0.16,
	 Xl = 0.14812,
	 S10 = 0.1089,
	 S12 = 0.37795
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENROU.GENROU gen_gENROU__f176989b_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 0.016703438, 
	 P_0 = 862.307, 
	 Q_0 = 61.955997, 
	 M_b = 1100.0,
	 Tpd0 = 10.8,
	 Tppd0 = 0.05,
	 Tpq0 = 1.0,
	 Tppq0 = 0.05,
	 H = 6.0,
	 D = 0.0,
	 Xd = 2.42,
	 Xq = 2.0,
	 Xpd = 0.23,
	 Xpq = 0.4108,
	 Xppd = 0.16,
	 Xppq = 0.16,
	 Xl = 0.14812,
	 S10 = 0.1089,
	 S12 = 0.37795
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENROU.GENROU gen_gENROU__f17698a2_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 0.016703438, 
	 P_0 = 862.307, 
	 Q_0 = 61.955997, 
	 M_b = 1100.0,
	 Tpd0 = 10.8,
	 Tppd0 = 0.05,
	 Tpq0 = 1.0,
	 Tppq0 = 0.05,
	 H = 6.0,
	 D = 0.0,
	 Xd = 2.42,
	 Xq = 2.0,
	 Xpd = 0.23,
	 Xpq = 0.4108,
	 Xppd = 0.16,
	 Xppq = 0.16,
	 Xl = 0.1481,
	 S10 = 0.1089,
	 S12 = 0.378
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENROU.GENROU gen_gENROU__f17698ab_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = -9.703579, 
	 P_0 = 600.696, 
	 Q_0 = 287.46432, 
	 M_b = 1350.0,
	 Tpd0 = 4.75,
	 Tppd0 = 0.05,
	 Tpq0 = 1.0,
	 Tppq0 = 0.05,
	 H = 4.82,
	 D = 0.0,
	 Xd = 2.13,
	 Xq = 2.03,
	 Xpd = 0.31,
	 Xpq = 0.403,
	 Xppd = 0.1937,
	 Xppq = 0.1937,
	 Xl = 0.14531,
	 S10 = 0.1089,
	 S12 = 0.37795
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENROU.GENROU gen_gENROU__f17698b1_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = -9.703579, 
	 P_0 = 600.696, 
	 Q_0 = 287.46432, 
	 M_b = 1350.0,
	 Tpd0 = 4.75,
	 Tppd0 = 0.05,
	 Tpq0 = 1.0,
	 Tppq0 = 0.05,
	 H = 4.82,
	 D = 0.0,
	 Xd = 2.13,
	 Xq = 2.03,
	 Xpd = 0.31,
	 Xpq = 0.403,
	 Xppd = 0.1937,
	 Xppq = 0.1937,
	 Xl = 0.14531,
	 S10 = 0.1089,
	 S12 = 0.37795
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENROU.GENROU gen_gENROU__f17698b7_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = -9.703579, 
	 P_0 = 600.696, 
	 Q_0 = 287.46432, 
	 M_b = 1350.0,
	 Tpd0 = 4.75,
	 Tppd0 = 0.05,
	 Tpq0 = 1.0,
	 Tppq0 = 0.05,
	 H = 4.82,
	 D = 0.0,
	 Xd = 2.13,
	 Xq = 2.03,
	 Xpd = 0.31,
	 Xpq = 0.403,
	 Xppd = 0.1937,
	 Xppq = 0.1937,
	 Xl = 0.14531,
	 S10 = 0.1089,
	 S12 = 0.37795
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENROU.GENROU gen_gENROU__f17698bd_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = -9.703579, 
	 P_0 = 600.696, 
	 Q_0 = 287.46432, 
	 M_b = 1350.0,
	 Tpd0 = 4.75,
	 Tppd0 = 0.05,
	 Tpq0 = 1.0,
	 Tppq0 = 0.05,
	 H = 4.82,
	 D = 0.0,
	 Xd = 2.13,
	 Xq = 2.03,
	 Xpd = 0.31,
	 Xpq = 0.403,
	 Xppd = 0.1937,
	 Xppq = 0.1937,
	 Xl = 0.14531,
	 S10 = 0.1089,
	 S12 = 0.37795
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENROU.GENROU gen_gENROU__f17698c3_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = -9.703579, 
	 P_0 = 600.696, 
	 Q_0 = 287.46432, 
	 M_b = 1350.0,
	 Tpd0 = 4.75,
	 Tppd0 = 0.05,
	 Tpq0 = 1.0,
	 Tppq0 = 0.05,
	 H = 4.82,
	 D = 0.0,
	 Xd = 2.13,
	 Xq = 2.03,
	 Xpd = 0.31,
	 Xpq = 0.403,
	 Xppd = 0.1937,
	 Xppq = 0.1937,
	 Xl = 0.14531,
	 S10 = 0.1089,
	 S12 = 0.37795
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENROU.GENROU gen_gENROU__f17698c9_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = -9.703579, 
	 P_0 = 600.696, 
	 Q_0 = 287.46432, 
	 M_b = 1350.0,
	 Tpd0 = 4.75,
	 Tppd0 = 0.05,
	 Tpq0 = 1.0,
	 Tppq0 = 0.05,
	 H = 4.82,
	 D = 0.0,
	 Xd = 2.13,
	 Xq = 2.03,
	 Xpd = 0.31,
	 Xpq = 0.403,
	 Xppd = 0.1937,
	 Xppq = 0.1937,
	 Xl = 0.14531,
	 S10 = 0.1089,
	 S12 = 0.37795
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f17698d1_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = -6.963138, 
	 P_0 = 263.522, 
	 Q_0 = 329.32285, 
	 M_b = 1200.0,
	 Tpd0 = 4.9629,
	 Tppd0 = 0.05,
	 Tppq0 = 0.15,
	 H = 3.9871,
	 D = 0.0,
	 Xd = 1.1332,
	 Xq = 0.68315,
	 Xpd = 0.24302,
	 Xppd = 0.15135,
	 Xppq = 0.15135,
	 Xl = 0.13405,
	 S10 = 0.1,
	 S12 = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f17698d7_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = -6.963138, 
	 P_0 = 263.522, 
	 Q_0 = 329.32285, 
	 M_b = 1200.0,
	 Tpd0 = 4.9629,
	 Tppd0 = 0.05,
	 Tppq0 = 0.15,
	 H = 3.9871,
	 D = 0.0,
	 Xd = 1.1332,
	 Xq = 0.6832,
	 Xpd = 0.243,
	 Xppd = 0.1514,
	 Xppq = 0.1514,
	 Xl = 0.1341,
	 S10 = 0.1,
	 S12 = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f17698df_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 12.155354, 
	 P_0 = 532.944, 
	 Q_0 = 105.78371, 
	 M_b = 1200.0,
	 Tpd0 = 6.4,
	 Tppd0 = 0.05,
	 Tppq0 = 0.15,
	 H = 3.5,
	 D = 0.0,
	 Xd = 1.14,
	 Xq = 0.84,
	 Xpd = 0.34,
	 Xppd = 0.26,
	 Xppq = 0.26,
	 Xl = 0.2,
	 S10 = 0.1,
	 S12 = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f17698e6_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 12.155354, 
	 P_0 = 532.944, 
	 Q_0 = 105.78371, 
	 M_b = 1200.0,
	 Tpd0 = 6.4,
	 Tppd0 = 0.05,
	 Tppq0 = 0.15,
	 H = 3.5,
	 D = 0.0,
	 Xd = 1.14,
	 Xq = 0.84,
	 Xpd = 0.34,
	 Xppd = 0.26,
	 Xppq = 0.26,
	 Xl = 0.2,
	 S10 = 0.1,
	 S12 = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f17698ed_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 12.155354, 
	 P_0 = 532.944, 
	 Q_0 = 105.78371, 
	 M_b = 1200.0,
	 Tpd0 = 6.4,
	 Tppd0 = 0.05,
	 Tppq0 = 0.15,
	 H = 3.5,
	 D = 0.0,
	 Xd = 1.14,
	 Xq = 0.84,
	 Xpd = 0.34,
	 Xppd = 0.26,
	 Xppq = 0.26,
	 Xl = 0.2,
	 S10 = 0.1,
	 S12 = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f17698f4_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 12.155354, 
	 P_0 = 532.944, 
	 Q_0 = 105.78371, 
	 M_b = 1200.0,
	 Tpd0 = 6.4,
	 Tppd0 = 0.05,
	 Tppq0 = 0.15,
	 H = 3.5,
	 D = 0.0,
	 Xd = 1.14,
	 Xq = 0.84,
	 Xpd = 0.34,
	 Xppd = 0.26,
	 Xppq = 0.26,
	 Xl = 0.2,
	 S10 = 0.1,
	 S12 = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f17698fb_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 12.155354, 
	 P_0 = 532.944, 
	 Q_0 = 105.78371, 
	 M_b = 1200.0,
	 Tpd0 = 6.4,
	 Tppd0 = 0.05,
	 Tppq0 = 0.15,
	 H = 3.5,
	 D = 0.0,
	 Xd = 1.14,
	 Xq = 0.84,
	 Xpd = 0.34,
	 Xppd = 0.26,
	 Xppq = 0.26,
	 Xl = 0.2,
	 S10 = 0.1,
	 S12 = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f1769902_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 12.155354, 
	 P_0 = 532.944, 
	 Q_0 = 105.78371, 
	 M_b = 1200.0,
	 Tpd0 = 6.4,
	 Tppd0 = 0.05,
	 Tppq0 = 0.15,
	 H = 3.5,
	 D = 0.0,
	 Xd = 1.14,
	 Xq = 0.84,
	 Xpd = 0.34,
	 Xppd = 0.26,
	 Xppq = 0.26,
	 Xl = 0.2,
	 S10 = 0.1,
	 S12 = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f176990b_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.007, 
	 angle_0 = -5.2827387, 
	 P_0 = 249.653, 
	 Q_0 = 138.45888, 
	 M_b = 1225.0,
	 Tpd0 = 6.5,
	 Tppd0 = 0.05,
	 Tppq0 = 0.15,
	 H = 4.1,
	 D = 0.0,
	 Xd = 1.02,
	 Xq = 0.63,
	 Xpd = 0.25,
	 Xppd = 0.16,
	 Xppq = 0.16,
	 Xl = 0.13,
	 S10 = 0.1,
	 S12 = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f1769911_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.007, 
	 angle_0 = -5.2827387, 
	 P_0 = 249.653, 
	 Q_0 = 138.45888, 
	 M_b = 1225.0,
	 Tpd0 = 6.5,
	 Tppd0 = 0.05,
	 Tppq0 = 0.15,
	 H = 4.1,
	 D = 0.0,
	 Xd = 1.02,
	 Xq = 0.63,
	 Xpd = 0.25,
	 Xppd = 0.16,
	 Xppq = 0.16,
	 Xl = 0.13,
	 S10 = 0.1,
	 S12 = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f1769919_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0040001, 
	 angle_0 = -9.141229, 
	 P_0 = 395.659, 
	 Q_0 = -223.91315, 
	 M_b = 1450.0,
	 Tpd0 = 7.198,
	 Tppd0 = 0.05,
	 Tppq0 = 0.15,
	 H = 3.0,
	 D = 0.0,
	 Xd = 1.2364,
	 Xq = 0.65567,
	 Xpd = 0.37415,
	 Xppd = 0.22825,
	 Xppq = 0.22825,
	 Xl = 0.16194,
	 S10 = 0.1,
	 S12 = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f1769920_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0040001, 
	 angle_0 = -9.141229, 
	 P_0 = 395.659, 
	 Q_0 = -223.91315, 
	 M_b = 1450.0,
	 Tpd0 = 7.198,
	 Tppd0 = 0.05,
	 Tppq0 = 0.15,
	 H = 3.0,
	 D = 0.0,
	 Xd = 1.2364,
	 Xq = 0.6557,
	 Xpd = 0.3741,
	 Xppd = 0.2283,
	 Xppq = 0.2283,
	 Xl = 0.1619,
	 S10 = 0.1,
	 S12 = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f1769929_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.01, 
	 angle_0 = -11.407857, 
	 P_0 = 527.423, 
	 Q_0 = 243.16295, 
	 M_b = 1650.0,
	 Tpd0 = 7.85,
	 Tppd0 = 0.05,
	 Tppq0 = 0.15,
	 H = 3.5,
	 D = 0.0,
	 Xd = 1.0,
	 Xq = 0.51325,
	 Xpd = 0.38,
	 Xppd = 0.28,
	 Xppq = 0.28,
	 Xl = 0.21,
	 S10 = 0.1,
	 S12 = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f1769930_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.01, 
	 angle_0 = -11.407857, 
	 P_0 = 527.423, 
	 Q_0 = 243.16295, 
	 M_b = 1650.0,
	 Tpd0 = 7.85,
	 Tppd0 = 0.05,
	 Tppq0 = 0.15,
	 H = 3.5,
	 D = 0.0,
	 Xd = 1.0,
	 Xq = 0.51325,
	 Xpd = 0.38,
	 Xppd = 0.28,
	 Xppq = 0.28,
	 Xl = 0.21,
	 S10 = 0.1,
	 S12 = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f1769937_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.01, 
	 angle_0 = -11.407857, 
	 P_0 = 527.423, 
	 Q_0 = 243.16295, 
	 M_b = 1650.0,
	 Tpd0 = 7.85,
	 Tppd0 = 0.05,
	 Tppq0 = 0.15,
	 H = 3.5,
	 D = 0.0,
	 Xd = 1.0,
	 Xq = 0.51325,
	 Xpd = 0.38,
	 Xppd = 0.28,
	 Xppq = 0.28,
	 Xl = 0.21,
	 S10 = 0.1,
	 S12 = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f176993e_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.01, 
	 angle_0 = -11.407857, 
	 P_0 = 527.423, 
	 Q_0 = 243.16295, 
	 M_b = 1650.0,
	 Tpd0 = 7.85,
	 Tppd0 = 0.05,
	 Tppq0 = 0.15,
	 H = 3.5,
	 D = 0.0,
	 Xd = 1.0,
	 Xq = 0.51325,
	 Xpd = 0.38,
	 Xppd = 0.28,
	 Xppq = 0.28,
	 Xl = 0.21,
	 S10 = 0.1,
	 S12 = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f1769947_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.005, 
	 angle_0 = -2.3064342, 
	 P_0 = 362.974, 
	 Q_0 = 1.1844115, 
	 M_b = 680.0,
	 Tpd0 = 9.7,
	 Tppd0 = 0.05,
	 Tppq0 = 0.15,
	 H = 3.5,
	 D = 0.0,
	 Xd = 1.28,
	 Xq = 0.94,
	 Xpd = 0.37,
	 Xppd = 0.28,
	 Xppq = 0.28,
	 Xl = 0.2,
	 S10 = 0.1,
	 S12 = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f176994d_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.005, 
	 angle_0 = -2.3064342, 
	 P_0 = 362.974, 
	 Q_0 = 1.1844115, 
	 M_b = 680.0,
	 Tpd0 = 9.7,
	 Tppd0 = 0.05,
	 Tppq0 = 0.15,
	 H = 3.5,
	 D = 0.0,
	 Xd = 1.28,
	 Xq = 0.94,
	 Xpd = 0.37,
	 Xppd = 0.28,
	 Xppq = 0.28,
	 Xl = 0.2,
	 S10 = 0.1,
	 S12 = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f1769953_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.005, 
	 angle_0 = -2.3064342, 
	 P_0 = 362.974, 
	 Q_0 = 1.1844115, 
	 M_b = 680.0,
	 Tpd0 = 9.7,
	 Tppd0 = 0.05,
	 Tppq0 = 0.15,
	 H = 3.5,
	 D = 0.0,
	 Xd = 1.28,
	 Xq = 0.94,
	 Xpd = 0.37,
	 Xppd = 0.28,
	 Xppq = 0.28,
	 Xl = 0.2,
	 S10 = 0.1,
	 S12 = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f1769959_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.005, 
	 angle_0 = -2.3064342, 
	 P_0 = 362.974, 
	 Q_0 = 1.1844115, 
	 M_b = 680.0,
	 Tpd0 = 9.7,
	 Tppd0 = 0.05,
	 Tppq0 = 0.15,
	 H = 3.5,
	 D = 0.0,
	 Xd = 1.28,
	 Xq = 0.94,
	 Xpd = 0.37,
	 Xppd = 0.28,
	 Xppq = 0.28,
	 Xl = 0.2,
	 S10 = 0.1,
	 S12 = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f1769961_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 18.584608, 
	 P_0 = 530.996, 
	 Q_0 = 101.71327, 
	 M_b = 1240.0,
	 Tpd0 = 9.9,
	 Tppd0 = 0.05,
	 Tppq0 = 0.15,
	 H = 3.0,
	 D = 0.0,
	 Xd = 1.2,
	 Xq = 0.73,
	 Xpd = 0.37,
	 Xppd = 0.18,
	 Xppq = 0.18,
	 Xl = 0.15,
	 S10 = 0.1,
	 S12 = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f1769967_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 18.584608, 
	 P_0 = 530.996, 
	 Q_0 = 101.71327, 
	 M_b = 1240.0,
	 Tpd0 = 9.9,
	 Tppd0 = 0.05,
	 Tppq0 = 0.15,
	 H = 3.0,
	 D = 0.0,
	 Xd = 1.2,
	 Xq = 0.73,
	 Xpd = 0.37,
	 Xppd = 0.18,
	 Xppq = 0.18,
	 Xl = 0.15,
	 S10 = 0.1,
	 S12 = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f176996d_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 18.584608, 
	 P_0 = 530.996, 
	 Q_0 = 101.71327, 
	 M_b = 1240.0,
	 Tpd0 = 9.9,
	 Tppd0 = 0.05,
	 Tppq0 = 0.15,
	 H = 3.0,
	 D = 0.0,
	 Xd = 1.2,
	 Xq = 0.73,
	 Xpd = 0.37,
	 Xppd = 0.18,
	 Xppq = 0.18,
	 Xl = 0.15,
	 S10 = 0.1,
	 S12 = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f1769973_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 18.584608, 
	 P_0 = 530.996, 
	 Q_0 = 101.71327, 
	 M_b = 1240.0,
	 Tpd0 = 9.9,
	 Tppd0 = 0.05,
	 Tppq0 = 0.15,
	 H = 3.0,
	 D = 0.0,
	 Xd = 1.2,
	 Xq = 0.73,
	 Xpd = 0.37,
	 Xppd = 0.18,
	 Xppq = 0.18,
	 Xl = 0.15,
	 S10 = 0.1,
	 S12 = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f1769979_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 18.584608, 
	 P_0 = 530.996, 
	 Q_0 = 101.71327, 
	 M_b = 1240.0,
	 Tpd0 = 9.9,
	 Tppd0 = 0.05,
	 Tppq0 = 0.15,
	 H = 3.0,
	 D = 0.0,
	 Xd = 1.2,
	 Xq = 0.73,
	 Xpd = 0.37,
	 Xppd = 0.18,
	 Xppq = 0.18,
	 Xl = 0.15,
	 S10 = 0.1,
	 S12 = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f1769981_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = -12.373396, 
	 P_0 = 254.721, 
	 Q_0 = 245.48756, 
	 M_b = 1100.0,
	 Tpd0 = 5.4855,
	 Tppd0 = 0.05,
	 Tppq0 = 0.15,
	 H = 3.558,
	 D = 0.0,
	 Xd = 1.0679,
	 Xq = 0.642,
	 Xpd = 0.23865,
	 Xppd = 0.15802,
	 Xppq = 0.15802,
	 Xl = 0.13514,
	 S10 = 0.1,
	 S12 = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f1769988_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = -12.373396, 
	 P_0 = 254.721, 
	 Q_0 = 245.48756, 
	 M_b = 1100.0,
	 Tpd0 = 5.4855,
	 Tppd0 = 0.05,
	 Tppq0 = 0.15,
	 H = 3.558,
	 D = 0.0,
	 Xd = 1.0679,
	 Xq = 0.642,
	 Xpd = 0.23865,
	 Xppd = 0.15802,
	 Xppq = 0.15802,
	 Xl = 0.13514,
	 S10 = 0.1,
	 S12 = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f176998f_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = -12.373396, 
	 P_0 = 254.721, 
	 Q_0 = 245.48756, 
	 M_b = 1100.0,
	 Tpd0 = 5.4855,
	 Tppd0 = 0.05,
	 Tppq0 = 0.15,
	 H = 3.558,
	 D = 0.0,
	 Xd = 1.0679,
	 Xq = 0.642,
	 Xpd = 0.23865,
	 Xppd = 0.15802,
	 Xppq = 0.15802,
	 Xl = 0.13514,
	 S10 = 0.1,
	 S12 = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f1769996_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = -12.373396, 
	 P_0 = 254.721, 
	 Q_0 = 245.48756, 
	 M_b = 1100.0,
	 Tpd0 = 5.4855,
	 Tppd0 = 0.05,
	 Tppq0 = 0.15,
	 H = 3.558,
	 D = 0.0,
	 Xd = 1.0679,
	 Xq = 0.642,
	 Xpd = 0.23865,
	 Xppd = 0.15802,
	 Xppq = 0.15802,
	 Xl = 0.13514,
	 S10 = 0.1,
	 S12 = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f176999f_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.02, 
	 angle_0 = 13.573054, 
	 P_0 = 619.954, 
	 Q_0 = 171.90251, 
	 M_b = 1200.0,
	 Tpd0 = 5.24,
	 Tppd0 = 0.05,
	 Tppq0 = 0.15,
	 H = 3.592,
	 D = 0.0,
	 Xd = 1.1044,
	 Xq = 0.66186,
	 Xpd = 0.25484,
	 Xppd = 0.17062,
	 Xppq = 0.17062,
	 Xl = 0.14737,
	 S10 = 0.1,
	 S12 = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f17699a6_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.02, 
	 angle_0 = 13.573054, 
	 P_0 = 619.954, 
	 Q_0 = 171.90251, 
	 M_b = 1200.0,
	 Tpd0 = 5.24,
	 Tppd0 = 0.05,
	 Tppq0 = 0.15,
	 H = 3.592,
	 D = 0.0,
	 Xd = 1.1044,
	 Xq = 0.66186,
	 Xpd = 0.25484,
	 Xppd = 0.17062,
	 Xppq = 0.17062,
	 Xl = 0.14737,
	 S10 = 0.1,
	 S12 = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f17699ad_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.02, 
	 angle_0 = 13.573054, 
	 P_0 = 619.954, 
	 Q_0 = 171.90251, 
	 M_b = 1200.0,
	 Tpd0 = 5.24,
	 Tppd0 = 0.05,
	 Tppq0 = 0.15,
	 H = 3.592,
	 D = 0.0,
	 Xd = 1.1044,
	 Xq = 0.6619,
	 Xpd = 0.2548,
	 Xppd = 0.1706,
	 Xppq = 0.1706,
	 Xl = 0.1474,
	 S10 = 0.1,
	 S12 = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f17699b4_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.02, 
	 angle_0 = 13.573054, 
	 P_0 = 619.954, 
	 Q_0 = 171.90251, 
	 M_b = 1200.0,
	 Tpd0 = 5.24,
	 Tppd0 = 0.05,
	 Tppq0 = 0.15,
	 H = 3.592,
	 D = 0.0,
	 Xd = 1.1044,
	 Xq = 0.6619,
	 Xpd = 0.2548,
	 Xppd = 0.1706,
	 Xppq = 0.1706,
	 Xl = 0.1474,
	 S10 = 0.1,
	 S12 = 0.3
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENROU.GENROU gen_gENROU__f17699bd_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 20.4655, 
	 P_0 = 676.83936, 
	 Q_0 = 116.58216, 
	 M_b = 1278.0,
	 Tpd0 = 10.0,
	 Tppd0 = 0.05,
	 Tpq0 = 1.0,
	 Tppq0 = 0.05,
	 H = 5.5,
	 D = 0.0,
	 Xd = 2.22,
	 Xq = 2.13,
	 Xpd = 0.36,
	 Xpq = 0.468,
	 Xppd = 0.225,
	 Xppq = 0.225,
	 Xl = 0.16875,
	 S10 = 0.1089,
	 S12 = 0.37795
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENROU.GENROU gen_gENROU__f17699c4_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 20.4655, 
	 P_0 = 676.83936, 
	 Q_0 = 116.58216, 
	 M_b = 1278.0,
	 Tpd0 = 10.0,
	 Tppd0 = 0.05,
	 Tpq0 = 1.0,
	 Tppq0 = 0.05,
	 H = 5.5,
	 D = 0.0,
	 Xd = 2.22,
	 Xq = 2.13,
	 Xpd = 0.36,
	 Xpq = 0.468,
	 Xppd = 0.225,
	 Xppq = 0.225,
	 Xl = 0.16875,
	 S10 = 0.1089,
	 S12 = 0.37795
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENROU.GENROU gen_gENROU__f17699cb_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 20.4655, 
	 P_0 = 676.83936, 
	 Q_0 = 116.58216, 
	 M_b = 1278.0,
	 Tpd0 = 10.0,
	 Tppd0 = 0.05,
	 Tpq0 = 1.0,
	 Tppq0 = 0.05,
	 H = 5.5,
	 D = 0.0,
	 Xd = 2.22,
	 Xq = 2.13,
	 Xpd = 0.36,
	 Xpq = 0.468,
	 Xppd = 0.225,
	 Xppq = 0.225,
	 Xl = 0.16875,
	 S10 = 0.1089,
	 S12 = 0.37795
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENROU.GENROU gen_gENROU__f17699d2_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 20.4655, 
	 P_0 = 676.83936, 
	 Q_0 = 116.58216, 
	 M_b = 1278.0,
	 Tpd0 = 10.0,
	 Tppd0 = 0.05,
	 Tpq0 = 1.0,
	 Tppq0 = 0.05,
	 H = 5.5,
	 D = 0.0,
	 Xd = 2.22,
	 Xq = 2.13,
	 Xpd = 0.36,
	 Xpq = 0.468,
	 Xppd = 0.225,
	 Xppq = 0.225,
	 Xl = 0.16875,
	 S10 = 0.1089,
	 S12 = 0.37795
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENROU.GENROU gen_gENROU__f17699d9_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 20.4655, 
	 P_0 = 676.83936, 
	 Q_0 = 116.58216, 
	 M_b = 1278.0,
	 Tpd0 = 10.0,
	 Tppd0 = 0.05,
	 Tpq0 = 1.0,
	 Tppq0 = 0.05,
	 H = 5.5,
	 D = 0.0,
	 Xd = 2.22,
	 Xq = 2.13,
	 Xpd = 0.36,
	 Xpq = 0.468,
	 Xppd = 0.225,
	 Xppq = 0.225,
	 Xl = 0.16875,
	 S10 = 0.1089,
	 S12 = 0.37795
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENROU.GENROU gen_gENROU__f17699e0_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 20.4655, 
	 P_0 = 676.83936, 
	 Q_0 = 116.58216, 
	 M_b = 1278.0,
	 Tpd0 = 10.0,
	 Tppd0 = 0.05,
	 Tpq0 = 1.0,
	 Tppq0 = 0.05,
	 H = 5.5,
	 D = 0.0,
	 Xd = 2.22,
	 Xq = 2.13,
	 Xpd = 0.36,
	 Xpq = 0.468,
	 Xppd = 0.225,
	 Xppq = 0.225,
	 Xl = 0.16875,
	 S10 = 0.1089,
	 S12 = 0.37795
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENROU.GENROU gen_gENROU__f17699e7_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 20.4655, 
	 P_0 = 676.83936, 
	 Q_0 = 116.58216, 
	 M_b = 1278.0,
	 Tpd0 = 10.0,
	 Tppd0 = 0.05,
	 Tpq0 = 1.0,
	 Tppq0 = 0.05,
	 H = 5.5,
	 D = 0.0,
	 Xd = 2.22,
	 Xq = 2.13,
	 Xpd = 0.36,
	 Xpq = 0.468,
	 Xppd = 0.225,
	 Xppq = 0.225,
	 Xl = 0.16875,
	 S10 = 0.1089,
	 S12 = 0.37795
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENROU.GENROU gen_gENROU__f17699ee_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 20.4655, 
	 P_0 = 676.83936, 
	 Q_0 = 116.58216, 
	 M_b = 1278.0,
	 Tpd0 = 10.0,
	 Tppd0 = 0.05,
	 Tpq0 = 1.0,
	 Tppq0 = 0.05,
	 H = 5.5,
	 D = 0.0,
	 Xd = 2.22,
	 Xq = 2.13,
	 Xpd = 0.36,
	 Xpq = 0.468,
	 Xppd = 0.225,
	 Xppq = 0.225,
	 Xl = 0.16875,
	 S10 = 0.1089,
	 S12 = 0.37795
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENROU.GENROU gen_gENROU__f17699f5_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 20.4655, 
	 P_0 = 676.83936, 
	 Q_0 = 116.58216, 
	 M_b = 1278.0,
	 Tpd0 = 10.0,
	 Tppd0 = 0.05,
	 Tpq0 = 1.0,
	 Tppq0 = 0.05,
	 H = 5.5,
	 D = 0.0,
	 Xd = 2.22,
	 Xq = 2.13,
	 Xpd = 0.36,
	 Xpq = 0.468,
	 Xppd = 0.225,
	 Xppq = 0.225,
	 Xl = 0.16875,
	 S10 = 0.1089,
	 S12 = 0.37795
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f17699fe_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 20.622072, 
	 P_0 = 391.65, 
	 Q_0 = 564.6762, 
	 M_b = 1000.0,
	 Tpd0 = 5.0,
	 Tppd0 = 0.06,
	 Tppq0 = 0.1,
	 H = 3.2,
	 D = 0.0,
	 Xd = 0.75,
	 Xq = 0.5,
	 Xpd = 0.25,
	 Xppd = 0.15385,
	 Xppq = 0.15385,
	 Xl = 0.11538,
	 S10 = 0.10239,
	 S12 = 0.2742
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f1769a04_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 20.622072, 
	 P_0 = 391.65, 
	 Q_0 = 564.6762, 
	 M_b = 1000.0,
	 Tpd0 = 5.0,
	 Tppd0 = 0.06,
	 Tppq0 = 0.1,
	 H = 3.2,
	 D = 0.0,
	 Xd = 0.75,
	 Xq = 0.5,
	 Xpd = 0.25,
	 Xppd = 0.15385,
	 Xppq = 0.15385,
	 Xl = 0.11538,
	 S10 = 0.10239,
	 S12 = 0.2742
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENSAL.GENSAL gen_gENSAL__f1769a0a_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0, 
	 angle_0 = 20.622072, 
	 P_0 = 391.65, 
	 Q_0 = 564.6762, 
	 M_b = 1000.0,
	 Tpd0 = 5.0,
	 Tppd0 = 0.06,
	 Tppq0 = 0.1,
	 H = 3.2,
	 D = 0.0,
	 Xd = 0.75,
	 Xq = 0.5,
	 Xpd = 0.25,
	 Xppd = 0.15385,
	 Xppq = 0.15385,
	 Xl = 0.11538,
	 S10 = 0.10239,
	 S12 = 0.2742
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENROU.GENROU gen_gENROU__f1769a12_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.02, 
	 angle_0 = -14.291729, 
	 P_0 = 127.934, 
	 Q_0 = 378.47202, 
	 M_b = 1300.0,
	 Tpd0 = 10.0,
	 Tppd0 = 0.05,
	 Tpq0 = 1.0,
	 Tppq0 = 0.05,
	 H = 7.0,
	 D = 0.0,
	 Xd = 2.42,
	 Xq = 2.0,
	 Xpd = 0.23,
	 Xpq = 0.4108,
	 Xppd = 0.17062,
	 Xppq = 0.17062,
	 Xl = 0.14812,
	 S10 = 0.1089,
	 S12 = 0.37795
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENROU.GENROU gen_gENROU__f1769a19_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.02, 
	 angle_0 = -14.291729, 
	 P_0 = 127.934, 
	 Q_0 = 378.47202, 
	 M_b = 1300.0,
	 Tpd0 = 10.0,
	 Tppd0 = 0.05,
	 Tpq0 = 1.0,
	 Tppq0 = 0.05,
	 H = 7.0,
	 D = 0.0,
	 Xd = 2.42,
	 Xq = 2.0,
	 Xpd = 0.23,
	 Xpq = 0.4108,
	 Xppd = 0.17062,
	 Xppq = 0.17062,
	 Xl = 0.14812,
	 S10 = 0.1089,
	 S12 = 0.37795
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENROU.GENROU gen_gENROU__f1769a20_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.02, 
	 angle_0 = -14.291729, 
	 P_0 = 127.934, 
	 Q_0 = 378.47202, 
	 M_b = 1300.0,
	 Tpd0 = 10.0,
	 Tppd0 = 0.05,
	 Tpq0 = 1.0,
	 Tppq0 = 0.05,
	 H = 7.0,
	 D = 0.0,
	 Xd = 2.42,
	 Xq = 2.0,
	 Xpd = 0.23,
	 Xpq = 0.4108,
	 Xppd = 0.17062,
	 Xppq = 0.17062,
	 Xl = 0.14812,
	 S10 = 0.1089,
	 S12 = 0.37795
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENROU.GENROU gen_gENROU__f1769a27_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.02, 
	 angle_0 = -14.291729, 
	 P_0 = 127.934, 
	 Q_0 = 378.47202, 
	 M_b = 1300.0,
	 Tpd0 = 10.0,
	 Tppd0 = 0.05,
	 Tpq0 = 1.0,
	 Tppq0 = 0.05,
	 H = 7.0,
	 D = 0.0,
	 Xd = 2.42,
	 Xq = 2.0,
	 Xpd = 0.23,
	 Xpq = 0.4108,
	 Xppd = 0.17062,
	 Xppq = 0.17062,
	 Xl = 0.14812,
	 S10 = 0.1089,
	 S12 = 0.37795
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENROU.GENROU gen_gENROU__f1769a2e_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.02, 
	 angle_0 = -14.291729, 
	 P_0 = 127.934, 
	 Q_0 = 378.47202, 
	 M_b = 1300.0,
	 Tpd0 = 10.0,
	 Tppd0 = 0.05,
	 Tpq0 = 1.0,
	 Tppq0 = 0.05,
	 H = 7.0,
	 D = 0.0,
	 Xd = 2.42,
	 Xq = 2.0,
	 Xpd = 0.23,
	 Xpq = 0.4108,
	 Xppd = 0.17062,
	 Xppq = 0.17062,
	 Xl = 0.14812,
	 S10 = 0.1089,
	 S12 = 0.37795
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Machines.PSSE.GENROU.GENROU gen_gENROU__f1769a35_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.02, 
	 angle_0 = -14.291729, 
	 P_0 = 127.934, 
	 Q_0 = 378.47202, 
	 M_b = 1300.0,
	 Tpd0 = 10.0,
	 Tppd0 = 0.05,
	 Tpq0 = 1.0,
	 Tppq0 = 0.05,
	 H = 7.0,
	 D = 0.0,
	 Xd = 2.42,
	 Xq = 2.0,
	 Xpd = 0.23,
	 Xpq = 0.4108,
	 Xppd = 0.17062,
	 Xppq = 0.17062,
	 Xl = 0.14812,
	 S10 = 0.1089,
	 S12 = 0.37795
	 ) annotation (Placement(transformation()));

// REGULATORS
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f1769804_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 2.0,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.IEEET2.IEEET2 reg_ieeet2__f1769804_9aeb_11e5_91da_b8763fd99c5f (
	 T_R = 0.0,
	 K_A = 729.0,
	 T_A = 0.04,
	 V_RMAX = 5.32,
	 V_RMIN = -4.05,
	 K_E = 1.0,
	 T_E = 0.44,
	 K_F = 0.0667,
	 T_F1 = 2.0,
	 T_F2 = 0.44,
	 E_1 = 6.5,
	 S_EE_1 = 0.054,
	 E_2 = 8.0,
	 S_EE_2 = 0.202
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.IEESGO reg_ieesgo__f1769804_9aeb_11e5_91da_b8763fd99c5f (
	 T_1 = 0.01,
	 T_2 = 0.0,
	 T_3 = 0.15,
	 T_4 = 0.3,
	 T_5 = 8.0,
	 T_6 = 0.4,
	 K_1 = 0.0,
	 K_2 = 0.7,
	 K_3 = 0.43,
	 P_MAX = 1.0,
	 P_MIN = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f176980a_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 2.0,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.IEEET2.IEEET2 reg_ieeet2__f176980a_9aeb_11e5_91da_b8763fd99c5f (
	 T_R = 0.0,
	 K_A = 729.0,
	 T_A = 0.04,
	 V_RMAX = 5.32,
	 V_RMIN = -4.05,
	 K_E = 1.0,
	 T_E = 0.44,
	 K_F = 0.0667,
	 T_F1 = 2.0,
	 T_F2 = 0.44,
	 E_1 = 6.5,
	 S_EE_1 = 0.054,
	 E_2 = 8.0,
	 S_EE_2 = 0.202
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.IEESGO reg_ieesgo__f176980a_9aeb_11e5_91da_b8763fd99c5f (
	 T_1 = 0.01,
	 T_2 = 0.0,
	 T_3 = 0.15,
	 T_4 = 0.3,
	 T_5 = 8.0,
	 T_6 = 0.4,
	 K_1 = 0.0,
	 K_2 = 0.7,
	 K_3 = 0.43,
	 P_MAX = 1.0,
	 P_MIN = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f1769810_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 2.0,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.IEEET2.IEEET2 reg_ieeet2__f1769810_9aeb_11e5_91da_b8763fd99c5f (
	 T_R = 0.0,
	 K_A = 729.0,
	 T_A = 0.04,
	 V_RMAX = 5.32,
	 V_RMIN = -4.05,
	 K_E = 1.0,
	 T_E = 0.44,
	 K_F = 0.0667,
	 T_F1 = 2.0,
	 T_F2 = 0.44,
	 E_1 = 6.5,
	 S_EE_1 = 0.054,
	 E_2 = 8.0,
	 S_EE_2 = 0.202
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.IEESGO reg_ieesgo__f1769810_9aeb_11e5_91da_b8763fd99c5f (
	 T_1 = 0.01,
	 T_2 = 0.0,
	 T_3 = 0.15,
	 T_4 = 0.3,
	 T_5 = 8.0,
	 T_6 = 0.4,
	 K_1 = 0.0,
	 K_2 = 0.7,
	 K_3 = 0.43,
	 P_MAX = 1.0,
	 P_MIN = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f1769818_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.87,
	 T_3 = 2.0,
	 K_4 = 0.087,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.04
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f1769818_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 31.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f1769818_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.1,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.0577,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f176981f_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.87,
	 T_3 = 2.0,
	 K_4 = 0.087,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.04
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f176981f_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 31.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f176981f_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.1,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.0577,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f1769826_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.87,
	 T_3 = 2.0,
	 K_4 = 0.087,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.04
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f1769826_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 31.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f1769826_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.1,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.0577,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f176982d_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.87,
	 T_3 = 2.0,
	 K_4 = 0.087,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.04
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f176982d_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 31.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f176982d_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.1,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.0577,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f1769834_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.87,
	 T_3 = 2.0,
	 K_4 = 0.087,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.04
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f1769834_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.2539,
	 T_B = 13.0,
	 K = 31.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f1769834_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.1,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.0577,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f176983d_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 31.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f176983d_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.1,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.01,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f1769845_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 31.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f1769845_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.1,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f176984c_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 31.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f176984c_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.1,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f1769853_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 31.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f1769853_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.1,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f176985a_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 31.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f176985a_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.1,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f1769861_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 31.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f1769861_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.1,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f1769868_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 31.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f1769868_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.1,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f176986f_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 31.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f176986f_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.1,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f1769876_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 31.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f1769876_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.1,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f176987f_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f176987f_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.0,
	 T_B = 0.04,
	 K = 10.0,
	 T_E = 0.04,
	 E_MIN = 0.0,
	 E_MAX = 5.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.IEESGO reg_ieesgo__f176987f_9aeb_11e5_91da_b8763fd99c5f (
	 T_1 = 0.01,
	 T_2 = 0.0,
	 T_3 = 0.15,
	 T_4 = 0.3,
	 T_5 = 8.0,
	 T_6 = 0.4,
	 K_1 = 0.0,
	 K_2 = 0.7,
	 K_3 = 0.43,
	 P_MAX = 1.0,
	 P_MIN = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f1769886_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f1769886_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.0,
	 T_B = 0.04,
	 K = 10.0,
	 T_E = 0.04,
	 E_MIN = 0.0,
	 E_MAX = 5.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.IEESGO reg_ieesgo__f1769886_9aeb_11e5_91da_b8763fd99c5f (
	 T_1 = 0.01,
	 T_2 = 0.0,
	 T_3 = 0.15,
	 T_4 = 0.3,
	 T_5 = 8.0,
	 T_6 = 0.4,
	 K_1 = 0.0,
	 K_2 = 0.7,
	 K_3 = 0.43,
	 P_MAX = 1.0,
	 P_MIN = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f176988d_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f176988d_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.0,
	 T_B = 0.04,
	 K = 10.0,
	 T_E = 0.04,
	 E_MIN = 0.0,
	 E_MAX = 5.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.IEESGO reg_ieesgo__f176988d_9aeb_11e5_91da_b8763fd99c5f (
	 T_1 = 0.01,
	 T_2 = 0.0,
	 T_3 = 0.15,
	 T_4 = 0.3,
	 T_5 = 8.0,
	 T_6 = 0.4,
	 K_1 = 0.0,
	 K_2 = 0.7,
	 K_3 = 0.43,
	 P_MAX = 1.0,
	 P_MIN = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f1769894_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f1769894_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.0,
	 T_B = 0.04,
	 K = 10.0,
	 T_E = 0.04,
	 E_MIN = 0.0,
	 E_MAX = 5.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.IEESGO reg_ieesgo__f1769894_9aeb_11e5_91da_b8763fd99c5f (
	 T_1 = 0.01,
	 T_2 = 0.0,
	 T_3 = 0.15,
	 T_4 = 0.3,
	 T_5 = 8.0,
	 T_6 = 0.4,
	 K_1 = 0.0,
	 K_2 = 0.7,
	 K_3 = 0.43,
	 P_MAX = 1.0,
	 P_MIN = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f176989b_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f176989b_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.0,
	 T_B = 0.04,
	 K = 10.0,
	 T_E = 0.04,
	 E_MIN = 0.0,
	 E_MAX = 5.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.IEESGO reg_ieesgo__f176989b_9aeb_11e5_91da_b8763fd99c5f (
	 T_1 = 0.01,
	 T_2 = 0.0,
	 T_3 = 0.15,
	 T_4 = 0.3,
	 T_5 = 8.0,
	 T_6 = 0.4,
	 K_1 = 0.0,
	 K_2 = 0.7,
	 K_3 = 0.43,
	 P_MAX = 1.0,
	 P_MIN = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f17698a2_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f17698a2_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.0,
	 T_B = 0.04,
	 K = 10.0,
	 T_E = 0.04,
	 E_MIN = 0.0,
	 E_MAX = 5.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.IEESGO reg_ieesgo__f17698a2_9aeb_11e5_91da_b8763fd99c5f (
	 T_1 = 0.01,
	 T_2 = 0.0,
	 T_3 = 0.15,
	 T_4 = 0.3,
	 T_5 = 8.0,
	 T_6 = 0.4,
	 K_1 = 0.0,
	 K_2 = 0.7,
	 K_3 = 0.43,
	 P_MAX = 1.0,
	 P_MIN = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f17698ab_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.68,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f17698ab_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.2,
	 T_B = 10.0,
	 K = 165.0,
	 T_E = 0.04,
	 E_MIN = 0.0,
	 E_MAX = 5.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.IEESGO reg_ieesgo__f17698ab_9aeb_11e5_91da_b8763fd99c5f (
	 T_1 = 0.01,
	 T_2 = 0.0,
	 T_3 = 0.15,
	 T_4 = 0.3,
	 T_5 = 8.0,
	 T_6 = 0.4,
	 K_1 = 0.0,
	 K_2 = 0.7,
	 K_3 = 0.43,
	 P_MAX = 1.0,
	 P_MIN = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f17698b1_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.68,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f17698b1_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.2,
	 T_B = 10.0,
	 K = 165.0,
	 T_E = 0.04,
	 E_MIN = 0.0,
	 E_MAX = 5.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.IEESGO reg_ieesgo__f17698b1_9aeb_11e5_91da_b8763fd99c5f (
	 T_1 = 0.01,
	 T_2 = 0.0,
	 T_3 = 0.15,
	 T_4 = 0.3,
	 T_5 = 8.0,
	 T_6 = 0.4,
	 K_1 = 0.0,
	 K_2 = 0.7,
	 K_3 = 0.43,
	 P_MAX = 1.0,
	 P_MIN = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f17698b7_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.68,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f17698b7_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.2,
	 T_B = 10.0,
	 K = 165.0,
	 T_E = 0.04,
	 E_MIN = 0.0,
	 E_MAX = 5.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.IEESGO reg_ieesgo__f17698b7_9aeb_11e5_91da_b8763fd99c5f (
	 T_1 = 0.01,
	 T_2 = 0.0,
	 T_3 = 0.15,
	 T_4 = 0.3,
	 T_5 = 8.0,
	 T_6 = 0.4,
	 K_1 = 0.0,
	 K_2 = 0.7,
	 K_3 = 0.43,
	 P_MAX = 1.0,
	 P_MIN = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f17698bd_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.68,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f17698bd_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.2,
	 T_B = 10.0,
	 K = 165.0,
	 T_E = 0.04,
	 E_MIN = 0.0,
	 E_MAX = 5.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.IEESGO reg_ieesgo__f17698bd_9aeb_11e5_91da_b8763fd99c5f (
	 T_1 = 0.01,
	 T_2 = 0.0,
	 T_3 = 0.15,
	 T_4 = 0.3,
	 T_5 = 8.0,
	 T_6 = 0.4,
	 K_1 = 0.0,
	 K_2 = 0.7,
	 K_3 = 0.43,
	 P_MAX = 1.0,
	 P_MIN = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f17698c3_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.68,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f17698c3_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.2,
	 T_B = 10.0,
	 K = 165.0,
	 T_E = 0.04,
	 E_MIN = 0.0,
	 E_MAX = 5.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.IEESGO reg_ieesgo__f17698c3_9aeb_11e5_91da_b8763fd99c5f (
	 T_1 = 0.01,
	 T_2 = 0.0,
	 T_3 = 0.15,
	 T_4 = 0.3,
	 T_5 = 8.0,
	 T_6 = 0.4,
	 K_1 = 0.0,
	 K_2 = 0.7,
	 K_3 = 0.43,
	 P_MAX = 1.0,
	 P_MIN = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f17698c9_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.68,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f17698c9_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.2,
	 T_B = 10.0,
	 K = 165.0,
	 T_E = 0.04,
	 E_MIN = 0.0,
	 E_MAX = 5.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.IEESGO reg_ieesgo__f17698c9_9aeb_11e5_91da_b8763fd99c5f (
	 T_1 = 0.01,
	 T_2 = 0.0,
	 T_3 = 0.15,
	 T_4 = 0.3,
	 T_5 = 8.0,
	 T_6 = 0.4,
	 K_1 = 0.0,
	 K_2 = 0.7,
	 K_3 = 0.43,
	 P_MAX = 1.0,
	 P_MIN = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SEXS.SEXS reg_sexs__f17698d1_9aeb_11e5_91da_b8763fd99c5f (
	 Ec0 = 1.0,
	 T_AT_B = 0.05,
	 T_B = 100.0,
	 K = 200.0,
	 T_E = 0.5,
	 E_MIN = 0.0,
	 E_MAX = 4.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f17698d1_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.2,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SEXS.SEXS reg_sexs__f17698d7_9aeb_11e5_91da_b8763fd99c5f (
	 Ec0 = 1.0,
	 T_AT_B = 0.05,
	 T_B = 100.0,
	 K = 200.0,
	 T_E = 0.5,
	 E_MIN = 0.0,
	 E_MAX = 4.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f17698d7_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.2,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f17698df_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f17698df_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 61.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f17698df_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.2,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f17698e6_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f17698e6_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 61.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f17698e6_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.2,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f17698ed_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f17698ed_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 61.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f17698ed_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.2,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f17698f4_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f17698f4_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 61.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f17698f4_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.2,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f17698fb_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f17698fb_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 61.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f17698fb_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.2,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f1769902_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f1769902_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 61.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f1769902_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.2,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SEXS.SEXS reg_sexs__f176990b_9aeb_11e5_91da_b8763fd99c5f (
	 Ec0 = 1.007,
	 T_AT_B = 0.05,
	 T_B = 100.0,
	 K = 200.0,
	 T_E = 0.5,
	 E_MIN = 0.0,
	 E_MAX = 4.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f176990b_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.2,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SEXS.SEXS reg_sexs__f1769911_9aeb_11e5_91da_b8763fd99c5f (
	 Ec0 = 1.007,
	 T_AT_B = 0.05,
	 T_B = 100.0,
	 K = 200.0,
	 T_E = 0.5,
	 E_MIN = 0.0,
	 E_MAX = 4.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f1769911_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.2,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SEXS.SEXS reg_sexs__f1769919_9aeb_11e5_91da_b8763fd99c5f (
	 Ec0 = 1.0040001,
	 T_AT_B = 0.05,
	 T_B = 100.0,
	 K = 200.0,
	 T_E = 0.5,
	 E_MIN = 0.0,
	 E_MAX = 4.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f1769919_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.2,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SEXS.SEXS reg_sexs__f1769920_9aeb_11e5_91da_b8763fd99c5f (
	 Ec0 = 1.0040001,
	 T_AT_B = 0.05,
	 T_B = 100.0,
	 K = 200.0,
	 T_E = 0.5,
	 E_MIN = 0.0,
	 E_MAX = 4.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f1769920_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.2,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f1769929_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0099999904632568,
	 V_c0 = 1.0099999904632568,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 61.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f1769929_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.3,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.2,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f1769930_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0099999904632568,
	 V_c0 = 1.0099999904632568,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 61.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f1769930_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.3,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.2,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f1769937_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0099999904632568,
	 V_c0 = 1.0099999904632568,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 61.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f1769937_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.3,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.2,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f176993e_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0099999904632568,
	 V_c0 = 1.0099999904632568,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 61.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f176993e_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.3,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.2,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SEXS.SEXS reg_sexs__f1769947_9aeb_11e5_91da_b8763fd99c5f (
	 Ec0 = 1.005,
	 T_AT_B = 1.0,
	 T_B = 0.1,
	 K = 20.0,
	 T_E = 0.1,
	 E_MIN = -4.0,
	 E_MAX = 4.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f1769947_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.3,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.2,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SEXS.SEXS reg_sexs__f176994d_9aeb_11e5_91da_b8763fd99c5f (
	 Ec0 = 1.005,
	 T_AT_B = 1.0,
	 T_B = 0.1,
	 K = 20.0,
	 T_E = 0.1,
	 E_MIN = -4.0,
	 E_MAX = 4.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f176994d_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.3,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.2,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SEXS.SEXS reg_sexs__f1769953_9aeb_11e5_91da_b8763fd99c5f (
	 Ec0 = 1.005,
	 T_AT_B = 1.0,
	 T_B = 0.1,
	 K = 20.0,
	 T_E = 0.1,
	 E_MIN = -4.0,
	 E_MAX = 4.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f1769953_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.3,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.2,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SEXS.SEXS reg_sexs__f1769959_9aeb_11e5_91da_b8763fd99c5f (
	 Ec0 = 1.005,
	 T_AT_B = 1.0,
	 T_B = 0.1,
	 K = 20.0,
	 T_E = 0.1,
	 E_MIN = -4.0,
	 E_MAX = 4.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f1769959_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.3,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.2,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f1769961_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f1769961_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 61.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f1769961_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.2,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f1769967_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f1769967_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 61.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f1769967_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.2,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f176996d_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f176996d_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 61.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f176996d_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.2,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f1769973_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f1769973_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 61.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f1769973_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.2,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f1769979_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f1769979_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 61.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f1769979_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.2,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SEXS.SEXS reg_sexs__f1769981_9aeb_11e5_91da_b8763fd99c5f (
	 Ec0 = 1.0,
	 T_AT_B = 0.05,
	 T_B = 100.0,
	 K = 200.0,
	 T_E = 0.5,
	 E_MIN = 0.0,
	 E_MAX = 4.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f1769981_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.2,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SEXS.SEXS reg_sexs__f1769988_9aeb_11e5_91da_b8763fd99c5f (
	 Ec0 = 1.0,
	 T_AT_B = 0.05,
	 T_B = 100.0,
	 K = 200.0,
	 T_E = 0.5,
	 E_MIN = 0.0,
	 E_MAX = 4.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f1769988_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.2,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SEXS.SEXS reg_sexs__f176998f_9aeb_11e5_91da_b8763fd99c5f (
	 Ec0 = 1.0,
	 T_AT_B = 0.05,
	 T_B = 100.0,
	 K = 200.0,
	 T_E = 0.5,
	 E_MIN = 0.0,
	 E_MAX = 4.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f176998f_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.2,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SEXS.SEXS reg_sexs__f1769996_9aeb_11e5_91da_b8763fd99c5f (
	 Ec0 = 1.0,
	 T_AT_B = 0.05,
	 T_B = 100.0,
	 K = 200.0,
	 T_E = 0.5,
	 E_MIN = 0.0,
	 E_MAX = 4.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f1769996_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.2,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f176999f_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f176999f_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0199999809265137,
	 V_c0 = 1.0199999809265137,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 61.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f176999f_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.2,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f17699a6_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f17699a6_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0199999809265137,
	 V_c0 = 1.0199999809265137,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 61.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f17699a6_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.2,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f17699ad_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f17699ad_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0199999809265137,
	 V_c0 = 1.0199999809265137,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 61.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f17699ad_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.2,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f17699b4_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f17699b4_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0199999809265137,
	 V_c0 = 1.0199999809265137,
	 T_AT_B = 0.2539,
	 T_B = 13.0,
	 K = 61.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f17699b4_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.2,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.1,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f17699bd_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 1.0,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.IEEET2.IEEET2 reg_ieeet2__f17699bd_9aeb_11e5_91da_b8763fd99c5f (
	 T_R = 0.0,
	 K_A = 800.0,
	 T_A = 0.04,
	 V_RMAX = 5.32,
	 V_RMIN = -4.05,
	 K_E = 1.0,
	 T_E = 0.44,
	 K_F = 0.0667,
	 T_F1 = 2.0,
	 T_F2 = 0.44,
	 E_1 = 6.5,
	 S_EE_1 = 0.054,
	 E_2 = 8.0,
	 S_EE_2 = 0.202
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.IEESGO reg_ieesgo__f17699bd_9aeb_11e5_91da_b8763fd99c5f (
	 T_1 = 0.01,
	 T_2 = 0.0,
	 T_3 = 0.15,
	 T_4 = 0.3,
	 T_5 = 8.0,
	 T_6 = 0.4,
	 K_1 = 0.0,
	 K_2 = 0.7,
	 K_3 = 0.43,
	 P_MAX = 1.0,
	 P_MIN = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f17699c4_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 1.0,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.IEEET2.IEEET2 reg_ieeet2__f17699c4_9aeb_11e5_91da_b8763fd99c5f (
	 T_R = 0.0,
	 K_A = 800.0,
	 T_A = 0.04,
	 V_RMAX = 5.32,
	 V_RMIN = -4.05,
	 K_E = 1.0,
	 T_E = 0.44,
	 K_F = 0.0667,
	 T_F1 = 2.0,
	 T_F2 = 0.44,
	 E_1 = 6.5,
	 S_EE_1 = 0.054,
	 E_2 = 8.0,
	 S_EE_2 = 0.202
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.IEESGO reg_ieesgo__f17699c4_9aeb_11e5_91da_b8763fd99c5f (
	 T_1 = 0.01,
	 T_2 = 0.0,
	 T_3 = 0.15,
	 T_4 = 0.3,
	 T_5 = 8.0,
	 T_6 = 0.4,
	 K_1 = 0.0,
	 K_2 = 0.7,
	 K_3 = 0.43,
	 P_MAX = 1.0,
	 P_MIN = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f17699cb_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 1.0,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.IEEET2.IEEET2 reg_ieeet2__f17699cb_9aeb_11e5_91da_b8763fd99c5f (
	 T_R = 0.0,
	 K_A = 800.0,
	 T_A = 0.04,
	 V_RMAX = 5.32,
	 V_RMIN = -4.05,
	 K_E = 1.0,
	 T_E = 0.44,
	 K_F = 0.0667,
	 T_F1 = 2.0,
	 T_F2 = 0.44,
	 E_1 = 6.5,
	 S_EE_1 = 0.054,
	 E_2 = 8.0,
	 S_EE_2 = 0.202
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.IEESGO reg_ieesgo__f17699cb_9aeb_11e5_91da_b8763fd99c5f (
	 T_1 = 0.01,
	 T_2 = 0.0,
	 T_3 = 0.15,
	 T_4 = 0.3,
	 T_5 = 8.0,
	 T_6 = 0.4,
	 K_1 = 0.0,
	 K_2 = 0.7,
	 K_3 = 0.43,
	 P_MAX = 1.0,
	 P_MIN = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f17699d2_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 1.0,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.IEEET2.IEEET2 reg_ieeet2__f17699d2_9aeb_11e5_91da_b8763fd99c5f (
	 T_R = 0.0,
	 K_A = 800.0,
	 T_A = 0.04,
	 V_RMAX = 5.32,
	 V_RMIN = -4.05,
	 K_E = 1.0,
	 T_E = 0.44,
	 K_F = 0.0667,
	 T_F1 = 2.0,
	 T_F2 = 0.44,
	 E_1 = 6.5,
	 S_EE_1 = 0.054,
	 E_2 = 8.0,
	 S_EE_2 = 0.202
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.IEESGO reg_ieesgo__f17699d2_9aeb_11e5_91da_b8763fd99c5f (
	 T_1 = 0.01,
	 T_2 = 0.0,
	 T_3 = 0.15,
	 T_4 = 0.3,
	 T_5 = 8.0,
	 T_6 = 0.4,
	 K_1 = 0.0,
	 K_2 = 0.7,
	 K_3 = 0.43,
	 P_MAX = 1.0,
	 P_MIN = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f17699d9_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 1.0,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.IEEET2.IEEET2 reg_ieeet2__f17699d9_9aeb_11e5_91da_b8763fd99c5f (
	 T_R = 0.0,
	 K_A = 800.0,
	 T_A = 0.04,
	 V_RMAX = 5.32,
	 V_RMIN = -4.05,
	 K_E = 1.0,
	 T_E = 0.44,
	 K_F = 0.0667,
	 T_F1 = 2.0,
	 T_F2 = 0.44,
	 E_1 = 6.5,
	 S_EE_1 = 0.054,
	 E_2 = 8.0,
	 S_EE_2 = 0.202
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.IEESGO reg_ieesgo__f17699d9_9aeb_11e5_91da_b8763fd99c5f (
	 T_1 = 0.01,
	 T_2 = 0.0,
	 T_3 = 0.15,
	 T_4 = 0.3,
	 T_5 = 8.0,
	 T_6 = 0.4,
	 K_1 = 0.0,
	 K_2 = 0.7,
	 K_3 = 0.43,
	 P_MAX = 1.0,
	 P_MIN = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f17699e0_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 1.0,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.IEEET2.IEEET2 reg_ieeet2__f17699e0_9aeb_11e5_91da_b8763fd99c5f (
	 T_R = 0.0,
	 K_A = 800.0,
	 T_A = 0.04,
	 V_RMAX = 5.32,
	 V_RMIN = -4.05,
	 K_E = 1.0,
	 T_E = 0.44,
	 K_F = 0.0667,
	 T_F1 = 2.0,
	 T_F2 = 0.44,
	 E_1 = 6.5,
	 S_EE_1 = 0.054,
	 E_2 = 8.0,
	 S_EE_2 = 0.202
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.IEESGO reg_ieesgo__f17699e0_9aeb_11e5_91da_b8763fd99c5f (
	 T_1 = 0.01,
	 T_2 = 0.0,
	 T_3 = 0.15,
	 T_4 = 0.3,
	 T_5 = 8.0,
	 T_6 = 0.4,
	 K_1 = 0.0,
	 K_2 = 0.7,
	 K_3 = 0.43,
	 P_MAX = 1.0,
	 P_MIN = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f17699e7_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 1.0,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.IEEET2.IEEET2 reg_ieeet2__f17699e7_9aeb_11e5_91da_b8763fd99c5f (
	 T_R = 0.0,
	 K_A = 800.0,
	 T_A = 0.04,
	 V_RMAX = 5.32,
	 V_RMIN = -4.05,
	 K_E = 1.0,
	 T_E = 0.44,
	 K_F = 0.0667,
	 T_F1 = 2.0,
	 T_F2 = 0.44,
	 E_1 = 6.5,
	 S_EE_1 = 0.054,
	 E_2 = 8.0,
	 S_EE_2 = 0.202
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.IEESGO reg_ieesgo__f17699e7_9aeb_11e5_91da_b8763fd99c5f (
	 T_1 = 0.01,
	 T_2 = 0.0,
	 T_3 = 0.15,
	 T_4 = 0.3,
	 T_5 = 8.0,
	 T_6 = 0.4,
	 K_1 = 0.0,
	 K_2 = 0.7,
	 K_3 = 0.43,
	 P_MAX = 1.0,
	 P_MIN = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f17699ee_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 1.0,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.IEEET2.IEEET2 reg_ieeet2__f17699ee_9aeb_11e5_91da_b8763fd99c5f (
	 T_R = 0.0,
	 K_A = 800.0,
	 T_A = 0.04,
	 V_RMAX = 5.32,
	 V_RMIN = -4.05,
	 K_E = 1.0,
	 T_E = 0.44,
	 K_F = 0.0667,
	 T_F1 = 2.0,
	 T_F2 = 0.44,
	 E_1 = 6.5,
	 S_EE_1 = 0.054,
	 E_2 = 8.0,
	 S_EE_2 = 0.202
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.IEESGO reg_ieesgo__f17699ee_9aeb_11e5_91da_b8763fd99c5f (
	 T_1 = 0.01,
	 T_2 = 0.0,
	 T_3 = 0.15,
	 T_4 = 0.3,
	 T_5 = 8.0,
	 T_6 = 0.4,
	 K_1 = 0.0,
	 K_2 = 0.7,
	 K_3 = 0.43,
	 P_MAX = 1.0,
	 P_MIN = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f17699f5_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 1.0,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.IEEET2.IEEET2 reg_ieeet2__f17699f5_9aeb_11e5_91da_b8763fd99c5f (
	 T_R = 0.0,
	 K_A = 800.0,
	 T_A = 0.04,
	 V_RMAX = 5.32,
	 V_RMIN = -4.05,
	 K_E = 1.0,
	 T_E = 0.44,
	 K_F = 0.0667,
	 T_F1 = 2.0,
	 T_F2 = 0.44,
	 E_1 = 6.5,
	 S_EE_1 = 0.054,
	 E_2 = 8.0,
	 S_EE_2 = 0.202
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.IEESGO reg_ieesgo__f17699f5_9aeb_11e5_91da_b8763fd99c5f (
	 T_1 = 0.01,
	 T_2 = 0.0,
	 T_3 = 0.15,
	 T_4 = 0.3,
	 T_5 = 8.0,
	 T_6 = 0.4,
	 K_1 = 0.0,
	 K_2 = 0.7,
	 K_3 = 0.43,
	 P_MAX = 1.0,
	 P_MIN = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f17699fe_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f17699fe_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 61.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f17699fe_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.1,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.01,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f1769a04_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f1769a04_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 61.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f1769a04_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.1,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.01,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f1769a0a_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f1769a0a_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0,
	 V_c0 = 1.0,
	 T_AT_B = 0.25385,
	 T_B = 13.0,
	 K = 61.0,
	 T_E = 0.05,
	 E_MIN = 0.0,
	 E_MAX = 4.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.HYGOV reg_hygov__f1769a0a_9aeb_11e5_91da_b8763fd99c5f (
	 R = 0.06,
	 r = 0.4,
	 T_r = 5.0,
	 T_f = 0.05,
	 T_g = 0.2,
	 VELM = 0.1,
	 G_MAX = 1.0,
	 G_MIN = 0.0,
	 T_w = 1.0,
	 A_t = 1.01,
	 D_turb = 0.5,
	 q_NL = 0.1
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f1769a12_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f1769a12_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0199999809265137,
	 V_c0 = 1.0199999809265137,
	 T_AT_B = 0.0,
	 T_B = 0.04,
	 K = 10.0,
	 T_E = 0.04,
	 E_MIN = 0.0,
	 E_MAX = 5.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.IEESGO reg_ieesgo__f1769a12_9aeb_11e5_91da_b8763fd99c5f (
	 T_1 = 0.01,
	 T_2 = 0.0,
	 T_3 = 0.15,
	 T_4 = 0.3,
	 T_5 = 8.0,
	 T_6 = 0.4,
	 K_1 = 0.0,
	 K_2 = 0.7,
	 K_3 = 0.43,
	 P_MAX = 1.0,
	 P_MIN = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f1769a19_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f1769a19_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0199999809265137,
	 V_c0 = 1.0199999809265137,
	 T_AT_B = 0.0,
	 T_B = 0.04,
	 K = 10.0,
	 T_E = 0.04,
	 E_MIN = 0.0,
	 E_MAX = 5.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.IEESGO reg_ieesgo__f1769a19_9aeb_11e5_91da_b8763fd99c5f (
	 T_1 = 0.01,
	 T_2 = 0.0,
	 T_3 = 0.15,
	 T_4 = 0.3,
	 T_5 = 8.0,
	 T_6 = 0.4,
	 K_1 = 0.0,
	 K_2 = 0.7,
	 K_3 = 0.43,
	 P_MAX = 1.0,
	 P_MIN = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f1769a20_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f1769a20_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0199999809265137,
	 V_c0 = 1.0199999809265137,
	 T_AT_B = 0.0,
	 T_B = 0.04,
	 K = 10.0,
	 T_E = 0.04,
	 E_MIN = 0.0,
	 E_MAX = 5.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.IEESGO reg_ieesgo__f1769a20_9aeb_11e5_91da_b8763fd99c5f (
	 T_1 = 0.01,
	 T_2 = 0.0,
	 T_3 = 0.15,
	 T_4 = 0.3,
	 T_5 = 8.0,
	 T_6 = 0.4,
	 K_1 = 0.0,
	 K_2 = 0.7,
	 K_3 = 0.43,
	 P_MAX = 1.0,
	 P_MIN = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f1769a27_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f1769a27_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0199999809265137,
	 V_c0 = 1.0199999809265137,
	 T_AT_B = 0.0,
	 T_B = 0.04,
	 K = 10.0,
	 T_E = 0.04,
	 E_MIN = 0.0,
	 E_MAX = 5.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.IEESGO reg_ieesgo__f1769a27_9aeb_11e5_91da_b8763fd99c5f (
	 T_1 = 0.01,
	 T_2 = 0.0,
	 T_3 = 0.15,
	 T_4 = 0.3,
	 T_5 = 8.0,
	 T_6 = 0.4,
	 K_1 = 0.0,
	 K_2 = 0.7,
	 K_3 = 0.43,
	 P_MAX = 1.0,
	 P_MIN = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f1769a2e_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f1769a2e_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0199999809265137,
	 V_c0 = 1.0199999809265137,
	 T_AT_B = 0.0,
	 T_B = 0.04,
	 K = 10.0,
	 T_E = 0.04,
	 E_MIN = 0.0,
	 E_MAX = 5.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.IEESGO reg_ieesgo__f1769a2e_9aeb_11e5_91da_b8763fd99c5f (
	 T_1 = 0.01,
	 T_2 = 0.0,
	 T_3 = 0.15,
	 T_4 = 0.3,
	 T_5 = 8.0,
	 T_6 = 0.4,
	 K_1 = 0.0,
	 K_2 = 0.7,
	 K_3 = 0.43,
	 P_MAX = 1.0,
	 P_MIN = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.PSS.STAB2A.STAB2A reg_stab2a__f1769a35_9aeb_11e5_91da_b8763fd99c5f (
	 K_2 = 1.0,
	 T_2 = 4.5,
	 K_3 = 0.0,
	 T_3 = 2.0,
	 K_4 = 0.55,
	 K_5 = 1.0,
	 T_5 = 0.01,
	 H_LIM = 0.03
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.ES.SCRX.SCRX reg_scrx__f1769a35_9aeb_11e5_91da_b8763fd99c5f (
	 V_0 = 1.0199999809265137,
	 V_c0 = 1.0199999809265137,
	 T_AT_B = 0.0,
	 T_B = 0.04,
	 K = 10.0,
	 T_E = 0.04,
	 E_MIN = 0.0,
	 E_MAX = 5.0,
	 C_SWITCH = false,
	 r_cr_fd = 0.0
	 ) annotation (Placement(transformation()));
  iPSL.Electrical.Controls.PSSE.TG.IEESGO reg_ieesgo__f1769a35_9aeb_11e5_91da_b8763fd99c5f (
	 T_1 = 0.01,
	 T_2 = 0.0,
	 T_3 = 0.15,
	 T_4 = 0.3,
	 T_5 = 8.0,
	 T_6 = 0.4,
	 K_1 = 0.0,
	 K_2 = 0.7,
	 K_3 = 0.43,
	 P_MAX = 1.0,
	 P_MIN = 0.0
	 ) annotation (Placement(transformation()));

	Modelica.Blocks.Sources.Constant const(k=0);

	Modelica.Blocks.Sources.Constant const1(k=-9999);

	Modelica.Blocks.Sources.Constant const2(k=9999);

equation

// Connecting REGULATORS and MACHINES
  connect(reg_stab2a__f1769804_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENROU__f1769804_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_ieeet2__f1769804_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENROU__f1769804_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_ieeet2__f1769804_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENROU__f1769804_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_ieeet2__f1769804_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENROU__f1769804_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_ieesgo__f1769804_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENROU__f1769804_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_ieesgo__f1769804_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENROU__f1769804_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_ieesgo__f1769804_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENROU__f1769804_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f176980a_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENROU__f176980a_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_ieeet2__f176980a_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENROU__f176980a_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_ieeet2__f176980a_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENROU__f176980a_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_ieeet2__f176980a_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENROU__f176980a_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_ieesgo__f176980a_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENROU__f176980a_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_ieesgo__f176980a_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENROU__f176980a_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_ieesgo__f176980a_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENROU__f176980a_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f1769810_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENROU__f1769810_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_ieeet2__f1769810_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENROU__f1769810_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_ieeet2__f1769810_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENROU__f1769810_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_ieeet2__f1769810_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENROU__f1769810_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_ieesgo__f1769810_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENROU__f1769810_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_ieesgo__f1769810_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENROU__f1769810_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_ieesgo__f1769810_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENROU__f1769810_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f1769818_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENSAL__f1769818_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f1769818_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f1769818_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f1769818_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f1769818_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f1769818_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f1769818_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f1769818_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f1769818_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f1769818_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f1769818_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f1769818_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f1769818_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f1769818_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f1769818_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f1769818_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f1769818_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f176981f_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENSAL__f176981f_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f176981f_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f176981f_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f176981f_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f176981f_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f176981f_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f176981f_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f176981f_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f176981f_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f176981f_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f176981f_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f176981f_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f176981f_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f176981f_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f176981f_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f176981f_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f176981f_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f1769826_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENSAL__f1769826_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f1769826_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f1769826_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f1769826_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f1769826_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f1769826_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f1769826_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f1769826_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f1769826_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f1769826_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f1769826_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f1769826_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f1769826_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f1769826_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f1769826_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f1769826_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f1769826_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f176982d_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENSAL__f176982d_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f176982d_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f176982d_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f176982d_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f176982d_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f176982d_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f176982d_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f176982d_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f176982d_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f176982d_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f176982d_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f176982d_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f176982d_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f176982d_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f176982d_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f176982d_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f176982d_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f1769834_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENSAL__f1769834_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f1769834_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f1769834_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f1769834_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f1769834_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f1769834_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f1769834_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f1769834_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f1769834_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f1769834_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f1769834_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f1769834_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f1769834_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f1769834_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f1769834_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f1769834_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f1769834_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_scrx__f176983d_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f176983d_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f176983d_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f176983d_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f176983d_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f176983d_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f176983d_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f176983d_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f176983d_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f176983d_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f176983d_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f176983d_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f176983d_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f176983d_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f176983d_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f176983d_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_scrx__f1769845_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f1769845_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f1769845_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f1769845_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f1769845_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f1769845_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f1769845_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f1769845_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f1769845_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f1769845_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f1769845_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f1769845_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f1769845_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f1769845_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f1769845_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f1769845_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_scrx__f176984c_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f176984c_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f176984c_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f176984c_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f176984c_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f176984c_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f176984c_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f176984c_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f176984c_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f176984c_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f176984c_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f176984c_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f176984c_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f176984c_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f176984c_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f176984c_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_scrx__f1769853_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f1769853_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f1769853_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f1769853_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f1769853_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f1769853_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f1769853_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f1769853_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f1769853_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f1769853_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f1769853_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f1769853_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f1769853_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f1769853_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f1769853_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f1769853_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_scrx__f176985a_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f176985a_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f176985a_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f176985a_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f176985a_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f176985a_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f176985a_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f176985a_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f176985a_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f176985a_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f176985a_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f176985a_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f176985a_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f176985a_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f176985a_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f176985a_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_scrx__f1769861_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f1769861_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f1769861_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f1769861_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f1769861_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f1769861_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f1769861_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f1769861_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f1769861_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f1769861_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f1769861_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f1769861_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f1769861_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f1769861_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f1769861_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f1769861_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_scrx__f1769868_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f1769868_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f1769868_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f1769868_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f1769868_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f1769868_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f1769868_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f1769868_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f1769868_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f1769868_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f1769868_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f1769868_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f1769868_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f1769868_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f1769868_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f1769868_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_scrx__f176986f_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f176986f_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f176986f_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f176986f_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f176986f_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f176986f_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f176986f_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f176986f_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f176986f_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f176986f_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f176986f_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f176986f_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f176986f_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f176986f_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f176986f_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f176986f_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_scrx__f1769876_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f1769876_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f1769876_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f1769876_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f1769876_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f1769876_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f1769876_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f1769876_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f1769876_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f1769876_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f1769876_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f1769876_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f1769876_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f1769876_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f1769876_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f1769876_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f176987f_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENROU__f176987f_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f176987f_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENROU__f176987f_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f176987f_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENROU__f176987f_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f176987f_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENROU__f176987f_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f176987f_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENROU__f176987f_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f176987f_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENROU__f176987f_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_ieesgo__f176987f_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENROU__f176987f_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_ieesgo__f176987f_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENROU__f176987f_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_ieesgo__f176987f_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENROU__f176987f_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f1769886_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENROU__f1769886_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f1769886_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENROU__f1769886_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f1769886_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENROU__f1769886_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f1769886_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENROU__f1769886_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f1769886_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENROU__f1769886_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f1769886_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENROU__f1769886_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_ieesgo__f1769886_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENROU__f1769886_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_ieesgo__f1769886_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENROU__f1769886_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_ieesgo__f1769886_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENROU__f1769886_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f176988d_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENROU__f176988d_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f176988d_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENROU__f176988d_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f176988d_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENROU__f176988d_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f176988d_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENROU__f176988d_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f176988d_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENROU__f176988d_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f176988d_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENROU__f176988d_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_ieesgo__f176988d_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENROU__f176988d_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_ieesgo__f176988d_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENROU__f176988d_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_ieesgo__f176988d_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENROU__f176988d_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f1769894_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENROU__f1769894_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f1769894_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENROU__f1769894_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f1769894_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENROU__f1769894_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f1769894_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENROU__f1769894_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f1769894_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENROU__f1769894_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f1769894_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENROU__f1769894_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_ieesgo__f1769894_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENROU__f1769894_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_ieesgo__f1769894_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENROU__f1769894_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_ieesgo__f1769894_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENROU__f1769894_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f176989b_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENROU__f176989b_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f176989b_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENROU__f176989b_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f176989b_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENROU__f176989b_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f176989b_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENROU__f176989b_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f176989b_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENROU__f176989b_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f176989b_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENROU__f176989b_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_ieesgo__f176989b_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENROU__f176989b_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_ieesgo__f176989b_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENROU__f176989b_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_ieesgo__f176989b_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENROU__f176989b_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f17698a2_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENROU__f17698a2_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f17698a2_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENROU__f17698a2_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f17698a2_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENROU__f17698a2_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f17698a2_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENROU__f17698a2_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f17698a2_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENROU__f17698a2_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f17698a2_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENROU__f17698a2_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_ieesgo__f17698a2_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENROU__f17698a2_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_ieesgo__f17698a2_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENROU__f17698a2_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_ieesgo__f17698a2_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENROU__f17698a2_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f17698ab_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENROU__f17698ab_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f17698ab_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENROU__f17698ab_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f17698ab_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENROU__f17698ab_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f17698ab_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENROU__f17698ab_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f17698ab_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENROU__f17698ab_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f17698ab_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENROU__f17698ab_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_ieesgo__f17698ab_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENROU__f17698ab_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_ieesgo__f17698ab_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENROU__f17698ab_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_ieesgo__f17698ab_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENROU__f17698ab_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f17698b1_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENROU__f17698b1_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f17698b1_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENROU__f17698b1_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f17698b1_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENROU__f17698b1_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f17698b1_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENROU__f17698b1_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f17698b1_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENROU__f17698b1_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f17698b1_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENROU__f17698b1_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_ieesgo__f17698b1_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENROU__f17698b1_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_ieesgo__f17698b1_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENROU__f17698b1_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_ieesgo__f17698b1_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENROU__f17698b1_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f17698b7_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENROU__f17698b7_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f17698b7_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENROU__f17698b7_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f17698b7_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENROU__f17698b7_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f17698b7_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENROU__f17698b7_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f17698b7_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENROU__f17698b7_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f17698b7_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENROU__f17698b7_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_ieesgo__f17698b7_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENROU__f17698b7_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_ieesgo__f17698b7_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENROU__f17698b7_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_ieesgo__f17698b7_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENROU__f17698b7_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f17698bd_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENROU__f17698bd_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f17698bd_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENROU__f17698bd_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f17698bd_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENROU__f17698bd_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f17698bd_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENROU__f17698bd_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f17698bd_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENROU__f17698bd_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f17698bd_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENROU__f17698bd_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_ieesgo__f17698bd_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENROU__f17698bd_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_ieesgo__f17698bd_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENROU__f17698bd_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_ieesgo__f17698bd_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENROU__f17698bd_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f17698c3_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENROU__f17698c3_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f17698c3_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENROU__f17698c3_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f17698c3_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENROU__f17698c3_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f17698c3_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENROU__f17698c3_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f17698c3_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENROU__f17698c3_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f17698c3_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENROU__f17698c3_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_ieesgo__f17698c3_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENROU__f17698c3_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_ieesgo__f17698c3_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENROU__f17698c3_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_ieesgo__f17698c3_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENROU__f17698c3_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f17698c9_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENROU__f17698c9_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f17698c9_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENROU__f17698c9_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f17698c9_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENROU__f17698c9_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f17698c9_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENROU__f17698c9_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f17698c9_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENROU__f17698c9_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f17698c9_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENROU__f17698c9_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_ieesgo__f17698c9_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENROU__f17698c9_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_ieesgo__f17698c9_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENROU__f17698c9_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_ieesgo__f17698c9_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENROU__f17698c9_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_sexs__f17698d1_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f17698d1_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_sexs__f17698d1_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f17698d1_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_sexs__f17698d1_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f17698d1_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f17698d1_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f17698d1_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f17698d1_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f17698d1_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f17698d1_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f17698d1_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_sexs__f17698d7_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f17698d7_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_sexs__f17698d7_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f17698d7_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_sexs__f17698d7_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f17698d7_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f17698d7_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f17698d7_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f17698d7_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f17698d7_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f17698d7_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f17698d7_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f17698df_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENSAL__f17698df_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f17698df_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f17698df_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f17698df_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f17698df_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f17698df_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f17698df_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f17698df_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f17698df_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f17698df_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f17698df_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f17698df_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f17698df_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f17698df_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f17698df_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f17698df_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f17698df_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f17698e6_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENSAL__f17698e6_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f17698e6_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f17698e6_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f17698e6_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f17698e6_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f17698e6_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f17698e6_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f17698e6_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f17698e6_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f17698e6_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f17698e6_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f17698e6_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f17698e6_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f17698e6_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f17698e6_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f17698e6_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f17698e6_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f17698ed_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENSAL__f17698ed_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f17698ed_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f17698ed_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f17698ed_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f17698ed_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f17698ed_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f17698ed_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f17698ed_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f17698ed_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f17698ed_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f17698ed_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f17698ed_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f17698ed_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f17698ed_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f17698ed_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f17698ed_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f17698ed_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f17698f4_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENSAL__f17698f4_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f17698f4_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f17698f4_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f17698f4_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f17698f4_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f17698f4_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f17698f4_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f17698f4_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f17698f4_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f17698f4_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f17698f4_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f17698f4_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f17698f4_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f17698f4_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f17698f4_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f17698f4_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f17698f4_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f17698fb_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENSAL__f17698fb_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f17698fb_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f17698fb_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f17698fb_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f17698fb_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f17698fb_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f17698fb_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f17698fb_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f17698fb_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f17698fb_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f17698fb_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f17698fb_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f17698fb_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f17698fb_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f17698fb_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f17698fb_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f17698fb_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f1769902_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENSAL__f1769902_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f1769902_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f1769902_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f1769902_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f1769902_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f1769902_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f1769902_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f1769902_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f1769902_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f1769902_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f1769902_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f1769902_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f1769902_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f1769902_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f1769902_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f1769902_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f1769902_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_sexs__f176990b_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f176990b_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_sexs__f176990b_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f176990b_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_sexs__f176990b_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f176990b_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f176990b_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f176990b_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f176990b_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f176990b_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f176990b_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f176990b_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_sexs__f1769911_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f1769911_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_sexs__f1769911_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f1769911_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_sexs__f1769911_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f1769911_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f1769911_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f1769911_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f1769911_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f1769911_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f1769911_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f1769911_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_sexs__f1769919_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f1769919_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_sexs__f1769919_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f1769919_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_sexs__f1769919_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f1769919_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f1769919_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f1769919_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f1769919_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f1769919_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f1769919_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f1769919_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_sexs__f1769920_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f1769920_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_sexs__f1769920_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f1769920_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_sexs__f1769920_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f1769920_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f1769920_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f1769920_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f1769920_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f1769920_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f1769920_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f1769920_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_scrx__f1769929_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f1769929_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f1769929_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f1769929_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f1769929_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f1769929_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f1769929_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f1769929_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f1769929_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f1769929_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f1769929_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f1769929_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f1769929_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f1769929_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f1769929_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f1769929_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_scrx__f1769930_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f1769930_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f1769930_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f1769930_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f1769930_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f1769930_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f1769930_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f1769930_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f1769930_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f1769930_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f1769930_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f1769930_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f1769930_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f1769930_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f1769930_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f1769930_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_scrx__f1769937_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f1769937_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f1769937_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f1769937_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f1769937_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f1769937_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f1769937_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f1769937_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f1769937_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f1769937_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f1769937_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f1769937_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f1769937_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f1769937_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f1769937_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f1769937_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_scrx__f176993e_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f176993e_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f176993e_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f176993e_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f176993e_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f176993e_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f176993e_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f176993e_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f176993e_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f176993e_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f176993e_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f176993e_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f176993e_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f176993e_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f176993e_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f176993e_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_sexs__f1769947_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f1769947_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_sexs__f1769947_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f1769947_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_sexs__f1769947_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f1769947_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f1769947_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f1769947_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f1769947_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f1769947_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f1769947_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f1769947_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_sexs__f176994d_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f176994d_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_sexs__f176994d_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f176994d_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_sexs__f176994d_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f176994d_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f176994d_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f176994d_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f176994d_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f176994d_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f176994d_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f176994d_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_sexs__f1769953_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f1769953_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_sexs__f1769953_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f1769953_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_sexs__f1769953_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f1769953_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f1769953_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f1769953_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f1769953_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f1769953_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f1769953_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f1769953_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_sexs__f1769959_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f1769959_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_sexs__f1769959_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f1769959_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_sexs__f1769959_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f1769959_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f1769959_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f1769959_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f1769959_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f1769959_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f1769959_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f1769959_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f1769961_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENSAL__f1769961_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f1769961_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f1769961_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f1769961_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f1769961_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f1769961_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f1769961_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f1769961_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f1769961_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f1769961_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f1769961_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f1769961_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f1769961_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f1769961_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f1769961_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f1769961_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f1769961_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f1769967_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENSAL__f1769967_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f1769967_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f1769967_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f1769967_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f1769967_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f1769967_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f1769967_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f1769967_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f1769967_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f1769967_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f1769967_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f1769967_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f1769967_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f1769967_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f1769967_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f1769967_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f1769967_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f176996d_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENSAL__f176996d_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f176996d_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f176996d_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f176996d_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f176996d_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f176996d_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f176996d_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f176996d_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f176996d_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f176996d_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f176996d_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f176996d_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f176996d_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f176996d_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f176996d_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f176996d_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f176996d_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f1769973_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENSAL__f1769973_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f1769973_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f1769973_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f1769973_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f1769973_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f1769973_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f1769973_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f1769973_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f1769973_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f1769973_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f1769973_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f1769973_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f1769973_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f1769973_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f1769973_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f1769973_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f1769973_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f1769979_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENSAL__f1769979_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f1769979_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f1769979_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f1769979_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f1769979_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f1769979_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f1769979_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f1769979_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f1769979_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f1769979_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f1769979_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f1769979_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f1769979_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f1769979_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f1769979_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f1769979_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f1769979_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_sexs__f1769981_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f1769981_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_sexs__f1769981_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f1769981_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_sexs__f1769981_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f1769981_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f1769981_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f1769981_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f1769981_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f1769981_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f1769981_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f1769981_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_sexs__f1769988_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f1769988_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_sexs__f1769988_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f1769988_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_sexs__f1769988_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f1769988_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f1769988_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f1769988_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f1769988_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f1769988_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f1769988_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f1769988_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_sexs__f176998f_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f176998f_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_sexs__f176998f_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f176998f_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_sexs__f176998f_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f176998f_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f176998f_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f176998f_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f176998f_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f176998f_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f176998f_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f176998f_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_sexs__f1769996_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f1769996_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_sexs__f1769996_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f1769996_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_sexs__f1769996_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f1769996_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f1769996_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f1769996_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f1769996_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f1769996_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f1769996_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f1769996_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f176999f_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENSAL__f176999f_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f176999f_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f176999f_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f176999f_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f176999f_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f176999f_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f176999f_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f176999f_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f176999f_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f176999f_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f176999f_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f176999f_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f176999f_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f176999f_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f176999f_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f176999f_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f176999f_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f17699a6_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENSAL__f17699a6_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f17699a6_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f17699a6_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f17699a6_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f17699a6_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f17699a6_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f17699a6_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f17699a6_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f17699a6_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f17699a6_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f17699a6_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f17699a6_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f17699a6_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f17699a6_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f17699a6_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f17699a6_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f17699a6_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f17699ad_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENSAL__f17699ad_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f17699ad_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f17699ad_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f17699ad_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f17699ad_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f17699ad_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f17699ad_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f17699ad_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f17699ad_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f17699ad_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f17699ad_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f17699ad_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f17699ad_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f17699ad_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f17699ad_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f17699ad_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f17699ad_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f17699b4_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENSAL__f17699b4_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f17699b4_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f17699b4_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f17699b4_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f17699b4_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f17699b4_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f17699b4_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f17699b4_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f17699b4_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f17699b4_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f17699b4_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f17699b4_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f17699b4_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f17699b4_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f17699b4_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f17699b4_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f17699b4_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f17699bd_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENROU__f17699bd_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_ieeet2__f17699bd_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENROU__f17699bd_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_ieeet2__f17699bd_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENROU__f17699bd_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_ieeet2__f17699bd_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENROU__f17699bd_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_ieesgo__f17699bd_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENROU__f17699bd_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_ieesgo__f17699bd_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENROU__f17699bd_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_ieesgo__f17699bd_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENROU__f17699bd_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f17699c4_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENROU__f17699c4_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_ieeet2__f17699c4_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENROU__f17699c4_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_ieeet2__f17699c4_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENROU__f17699c4_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_ieeet2__f17699c4_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENROU__f17699c4_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_ieesgo__f17699c4_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENROU__f17699c4_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_ieesgo__f17699c4_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENROU__f17699c4_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_ieesgo__f17699c4_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENROU__f17699c4_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f17699cb_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENROU__f17699cb_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_ieeet2__f17699cb_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENROU__f17699cb_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_ieeet2__f17699cb_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENROU__f17699cb_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_ieeet2__f17699cb_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENROU__f17699cb_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_ieesgo__f17699cb_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENROU__f17699cb_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_ieesgo__f17699cb_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENROU__f17699cb_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_ieesgo__f17699cb_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENROU__f17699cb_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f17699d2_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENROU__f17699d2_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_ieeet2__f17699d2_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENROU__f17699d2_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_ieeet2__f17699d2_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENROU__f17699d2_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_ieeet2__f17699d2_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENROU__f17699d2_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_ieesgo__f17699d2_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENROU__f17699d2_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_ieesgo__f17699d2_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENROU__f17699d2_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_ieesgo__f17699d2_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENROU__f17699d2_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f17699d9_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENROU__f17699d9_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_ieeet2__f17699d9_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENROU__f17699d9_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_ieeet2__f17699d9_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENROU__f17699d9_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_ieeet2__f17699d9_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENROU__f17699d9_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_ieesgo__f17699d9_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENROU__f17699d9_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_ieesgo__f17699d9_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENROU__f17699d9_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_ieesgo__f17699d9_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENROU__f17699d9_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f17699e0_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENROU__f17699e0_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_ieeet2__f17699e0_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENROU__f17699e0_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_ieeet2__f17699e0_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENROU__f17699e0_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_ieeet2__f17699e0_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENROU__f17699e0_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_ieesgo__f17699e0_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENROU__f17699e0_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_ieesgo__f17699e0_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENROU__f17699e0_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_ieesgo__f17699e0_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENROU__f17699e0_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f17699e7_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENROU__f17699e7_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_ieeet2__f17699e7_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENROU__f17699e7_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_ieeet2__f17699e7_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENROU__f17699e7_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_ieeet2__f17699e7_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENROU__f17699e7_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_ieesgo__f17699e7_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENROU__f17699e7_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_ieesgo__f17699e7_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENROU__f17699e7_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_ieesgo__f17699e7_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENROU__f17699e7_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f17699ee_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENROU__f17699ee_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_ieeet2__f17699ee_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENROU__f17699ee_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_ieeet2__f17699ee_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENROU__f17699ee_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_ieeet2__f17699ee_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENROU__f17699ee_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_ieesgo__f17699ee_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENROU__f17699ee_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_ieesgo__f17699ee_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENROU__f17699ee_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_ieesgo__f17699ee_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENROU__f17699ee_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f17699f5_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENROU__f17699f5_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_ieeet2__f17699f5_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENROU__f17699f5_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_ieeet2__f17699f5_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENROU__f17699f5_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_ieeet2__f17699f5_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENROU__f17699f5_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_ieesgo__f17699f5_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENROU__f17699f5_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_ieesgo__f17699f5_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENROU__f17699f5_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_ieesgo__f17699f5_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENROU__f17699f5_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f17699fe_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENSAL__f17699fe_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f17699fe_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f17699fe_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f17699fe_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f17699fe_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f17699fe_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f17699fe_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f17699fe_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f17699fe_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f17699fe_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f17699fe_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f17699fe_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f17699fe_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f17699fe_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f17699fe_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f17699fe_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f17699fe_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f1769a04_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENSAL__f1769a04_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f1769a04_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f1769a04_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f1769a04_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f1769a04_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f1769a04_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f1769a04_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f1769a04_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f1769a04_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f1769a04_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f1769a04_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f1769a04_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f1769a04_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f1769a04_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f1769a04_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f1769a04_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f1769a04_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f1769a0a_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENSAL__f1769a0a_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f1769a0a_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENSAL__f1769a0a_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f1769a0a_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENSAL__f1769a0a_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f1769a0a_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENSAL__f1769a0a_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f1769a0a_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENSAL__f1769a0a_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f1769a0a_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENSAL__f1769a0a_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_hygov__f1769a0a_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENSAL__f1769a0a_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_hygov__f1769a0a_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENSAL__f1769a0a_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_hygov__f1769a0a_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENSAL__f1769a0a_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f1769a12_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENROU__f1769a12_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f1769a12_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENROU__f1769a12_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f1769a12_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENROU__f1769a12_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f1769a12_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENROU__f1769a12_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f1769a12_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENROU__f1769a12_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f1769a12_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENROU__f1769a12_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_ieesgo__f1769a12_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENROU__f1769a12_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_ieesgo__f1769a12_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENROU__f1769a12_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_ieesgo__f1769a12_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENROU__f1769a12_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f1769a19_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENROU__f1769a19_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f1769a19_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENROU__f1769a19_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f1769a19_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENROU__f1769a19_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f1769a19_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENROU__f1769a19_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f1769a19_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENROU__f1769a19_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f1769a19_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENROU__f1769a19_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_ieesgo__f1769a19_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENROU__f1769a19_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_ieesgo__f1769a19_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENROU__f1769a19_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_ieesgo__f1769a19_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENROU__f1769a19_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f1769a20_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENROU__f1769a20_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f1769a20_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENROU__f1769a20_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f1769a20_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENROU__f1769a20_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f1769a20_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENROU__f1769a20_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f1769a20_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENROU__f1769a20_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f1769a20_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENROU__f1769a20_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_ieesgo__f1769a20_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENROU__f1769a20_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_ieesgo__f1769a20_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENROU__f1769a20_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_ieesgo__f1769a20_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENROU__f1769a20_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f1769a27_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENROU__f1769a27_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f1769a27_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENROU__f1769a27_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f1769a27_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENROU__f1769a27_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f1769a27_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENROU__f1769a27_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f1769a27_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENROU__f1769a27_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f1769a27_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENROU__f1769a27_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_ieesgo__f1769a27_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENROU__f1769a27_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_ieesgo__f1769a27_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENROU__f1769a27_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_ieesgo__f1769a27_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENROU__f1769a27_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f1769a2e_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENROU__f1769a2e_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f1769a2e_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENROU__f1769a2e_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f1769a2e_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENROU__f1769a2e_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f1769a2e_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENROU__f1769a2e_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f1769a2e_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENROU__f1769a2e_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f1769a2e_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENROU__f1769a2e_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_ieesgo__f1769a2e_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENROU__f1769a2e_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_ieesgo__f1769a2e_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENROU__f1769a2e_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_ieesgo__f1769a2e_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENROU__f1769a2e_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());
  connect(reg_stab2a__f1769a35_9aeb_11e5_91da_b8763fd99c5f.PELEC, gen_gENROU__f1769a35_9aeb_11e5_91da_b8763fd99c5f.PELEC) annotation (Line());
  connect(reg_scrx__f1769a35_9aeb_11e5_91da_b8763fd99c5f.EFD, gen_gENROU__f1769a35_9aeb_11e5_91da_b8763fd99c5f.EFD) annotation (Line());
  connect(reg_scrx__f1769a35_9aeb_11e5_91da_b8763fd99c5f.ETERM, gen_gENROU__f1769a35_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_scrx__f1769a35_9aeb_11e5_91da_b8763fd99c5f.EFD0, gen_gENROU__f1769a35_9aeb_11e5_91da_b8763fd99c5f.EFD0) annotation (Line());
  connect(reg_scrx__f1769a35_9aeb_11e5_91da_b8763fd99c5f.XADIFD, gen_gENROU__f1769a35_9aeb_11e5_91da_b8763fd99c5f.XADIFD) annotation (Line());
  connect(reg_scrx__f1769a35_9aeb_11e5_91da_b8763fd99c5f.ECOMP, gen_gENROU__f1769a35_9aeb_11e5_91da_b8763fd99c5f.ETERM) annotation (Line());
  connect(reg_ieesgo__f1769a35_9aeb_11e5_91da_b8763fd99c5f.PMECH, gen_gENROU__f1769a35_9aeb_11e5_91da_b8763fd99c5f.PMECH) annotation (Line());
  connect(reg_ieesgo__f1769a35_9aeb_11e5_91da_b8763fd99c5f.PMECH0, gen_gENROU__f1769a35_9aeb_11e5_91da_b8763fd99c5f.PMECH0) annotation (Line());
  connect(reg_ieesgo__f1769a35_9aeb_11e5_91da_b8763fd99c5f.SPEED, gen_gENROU__f1769a35_9aeb_11e5_91da_b8763fd99c5f.SPEED) annotation (Line());

// Connecting REGULATORS and REGULATORS
  connect(reg_stab2a__f1769804_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_ieeet2__f1769804_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f176980a_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_ieeet2__f176980a_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f1769810_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_ieeet2__f1769810_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f1769818_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f1769818_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f176981f_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f176981f_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f1769826_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f1769826_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f176982d_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f176982d_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f1769834_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f1769834_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f176987f_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f176987f_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f1769886_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f1769886_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f176988d_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f176988d_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f1769894_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f1769894_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f176989b_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f176989b_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f17698a2_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f17698a2_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f17698ab_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f17698ab_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f17698b1_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f17698b1_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f17698b7_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f17698b7_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f17698bd_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f17698bd_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f17698c3_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f17698c3_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f17698c9_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f17698c9_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f17698df_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f17698df_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f17698e6_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f17698e6_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f17698ed_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f17698ed_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f17698f4_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f17698f4_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f17698fb_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f17698fb_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f1769902_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f1769902_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f1769961_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f1769961_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f1769967_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f1769967_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f176996d_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f176996d_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f1769973_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f1769973_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f1769979_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f1769979_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f176999f_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f176999f_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f17699a6_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f17699a6_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f17699ad_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f17699ad_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f17699b4_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f17699b4_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f17699bd_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_ieeet2__f17699bd_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f17699c4_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_ieeet2__f17699c4_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f17699cb_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_ieeet2__f17699cb_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f17699d2_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_ieeet2__f17699d2_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f17699d9_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_ieeet2__f17699d9_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f17699e0_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_ieeet2__f17699e0_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f17699e7_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_ieeet2__f17699e7_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f17699ee_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_ieeet2__f17699ee_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f17699f5_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_ieeet2__f17699f5_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f17699fe_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f17699fe_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f1769a04_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f1769a04_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f1769a0a_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f1769a0a_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f1769a12_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f1769a12_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f1769a19_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f1769a19_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f1769a20_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f1769a20_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f1769a27_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f1769a27_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f1769a2e_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f1769a2e_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());
  connect(reg_stab2a__f1769a35_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, reg_scrx__f1769a35_9aeb_11e5_91da_b8763fd99c5f.VOTHSG) annotation (Line());

// Connecting REGULATORS and CONSTANTS
  connect(reg_ieeet2__f1769804_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_ieeet2__f1769804_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_ieeet2__f176980a_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_ieeet2__f176980a_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_ieeet2__f1769810_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_ieeet2__f1769810_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f1769818_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f1769818_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f176981f_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f176981f_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f1769826_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f1769826_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f176982d_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f176982d_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f1769834_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f1769834_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f176983d_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f176983d_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f1769845_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f1769845_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f176984c_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f176984c_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f1769853_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f1769853_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f176985a_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f176985a_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f1769861_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f1769861_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f1769868_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f1769868_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f176986f_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f176986f_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f1769876_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f1769876_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f176987f_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f176987f_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f1769886_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f1769886_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f176988d_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f176988d_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f1769894_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f1769894_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f176989b_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f176989b_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f17698a2_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f17698a2_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f17698ab_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f17698ab_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f17698b1_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f17698b1_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f17698b7_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f17698b7_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f17698bd_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f17698bd_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f17698c3_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f17698c3_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f17698c9_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f17698c9_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_sexs__f17698d1_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_sexs__f17698d1_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_sexs__f17698d7_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_sexs__f17698d7_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f17698df_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f17698df_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f17698e6_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f17698e6_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f17698ed_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f17698ed_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f17698f4_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f17698f4_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f17698fb_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f17698fb_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f1769902_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f1769902_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_sexs__f176990b_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_sexs__f176990b_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_sexs__f1769911_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_sexs__f1769911_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_sexs__f1769919_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_sexs__f1769919_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_sexs__f1769920_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_sexs__f1769920_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f1769929_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f1769929_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f1769930_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f1769930_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f1769937_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f1769937_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f176993e_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f176993e_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_sexs__f1769947_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_sexs__f1769947_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_sexs__f176994d_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_sexs__f176994d_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_sexs__f1769953_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_sexs__f1769953_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_sexs__f1769959_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_sexs__f1769959_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f1769961_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f1769961_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f1769967_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f1769967_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f176996d_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f176996d_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f1769973_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f1769973_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f1769979_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f1769979_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_sexs__f1769981_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_sexs__f1769981_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_sexs__f1769988_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_sexs__f1769988_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_sexs__f176998f_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_sexs__f176998f_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_sexs__f1769996_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_sexs__f1769996_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f176999f_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f176999f_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f17699a6_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f17699a6_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f17699ad_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f17699ad_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f17699b4_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f17699b4_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_ieeet2__f17699bd_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_ieeet2__f17699bd_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_ieeet2__f17699c4_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_ieeet2__f17699c4_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_ieeet2__f17699cb_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_ieeet2__f17699cb_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_ieeet2__f17699d2_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_ieeet2__f17699d2_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_ieeet2__f17699d9_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_ieeet2__f17699d9_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_ieeet2__f17699e0_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_ieeet2__f17699e0_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_ieeet2__f17699e7_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_ieeet2__f17699e7_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_ieeet2__f17699ee_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_ieeet2__f17699ee_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_ieeet2__f17699f5_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_ieeet2__f17699f5_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f17699fe_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f17699fe_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f1769a04_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f1769a04_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f1769a0a_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f1769a0a_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f1769a12_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f1769a12_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f1769a19_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f1769a19_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f1769a20_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f1769a20_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f1769a27_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f1769a27_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f1769a2e_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f1769a2e_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f1769a35_9aeb_11e5_91da_b8763fd99c5f.VOEL, const.y) annotation (Line());
  connect(reg_scrx__f1769a35_9aeb_11e5_91da_b8763fd99c5f.VUEL, const.y) annotation (Line());
  connect(reg_scrx__f1769876_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, const.y) annotation (Line());
  connect(reg_sexs__f176994d_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, const.y) annotation (Line());
  connect(reg_scrx__f1769868_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, const.y) annotation (Line());
  connect(reg_scrx__f176983d_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, const.y) annotation (Line());
  connect(reg_scrx__f1769853_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, const.y) annotation (Line());
  connect(reg_sexs__f17698d7_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, const.y) annotation (Line());
  connect(reg_scrx__f1769937_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, const.y) annotation (Line());
  connect(reg_sexs__f1769959_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, const.y) annotation (Line());
  connect(reg_scrx__f176984c_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, const.y) annotation (Line());
  connect(reg_sexs__f1769996_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, const.y) annotation (Line());
  connect(reg_scrx__f1769845_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, const.y) annotation (Line());
  connect(reg_sexs__f1769988_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, const.y) annotation (Line());
  connect(reg_sexs__f1769911_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, const.y) annotation (Line());
  connect(reg_scrx__f176985a_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, const.y) annotation (Line());
  connect(reg_scrx__f176993e_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, const.y) annotation (Line());
  connect(reg_scrx__f1769861_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, const.y) annotation (Line());
  connect(reg_sexs__f1769919_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, const.y) annotation (Line());
  connect(reg_scrx__f176986f_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, const.y) annotation (Line());
  connect(reg_sexs__f17698d1_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, const.y) annotation (Line());
  connect(reg_scrx__f1769930_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, const.y) annotation (Line());
  connect(reg_sexs__f176990b_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, const.y) annotation (Line());
  connect(reg_scrx__f1769929_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, const.y) annotation (Line());
  connect(reg_sexs__f1769947_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, const.y) annotation (Line());
  connect(reg_sexs__f1769920_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, const.y) annotation (Line());
  connect(reg_sexs__f1769953_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, const.y) annotation (Line());
  connect(reg_sexs__f176998f_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, const.y) annotation (Line());
  connect(reg_sexs__f1769981_9aeb_11e5_91da_b8763fd99c5f.VOTHSG, const.y) annotation (Line());

// Connecting LINES

  connect(bus__f17695b9_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f17695b9_9aeb_11e5_91da_b8763fd99c5f__f17695ab_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f17695b9_9aeb_11e5_91da_b8763fd99c5f__f17695ab_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f17695ab_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695c9_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f17695c9_9aeb_11e5_91da_b8763fd99c5f__f17695ab_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f17695c9_9aeb_11e5_91da_b8763fd99c5f__f17695ab_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f17695ab_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695e0_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f17695e0_9aeb_11e5_91da_b8763fd99c5f__f17695ab_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f17695e0_9aeb_11e5_91da_b8763fd99c5f__f17695ab_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f17695ab_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695e0_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f17695e0_9aeb_11e5_91da_b8763fd99c5f__f17695ab_9aeb_11e5_91da_b8763fd99c5f_2.p) annotation (Line());
  connect(line_PwLine_2__f17695e0_9aeb_11e5_91da_b8763fd99c5f__f17695ab_9aeb_11e5_91da_b8763fd99c5f_2.n, bus__f17695ab_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695ec_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f17695ec_9aeb_11e5_91da_b8763fd99c5f__f17695ab_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f17695ec_9aeb_11e5_91da_b8763fd99c5f__f17695ab_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f17695ab_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695ec_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f17695ec_9aeb_11e5_91da_b8763fd99c5f__f17695ab_9aeb_11e5_91da_b8763fd99c5f_2.p) annotation (Line());
  connect(line_PwLine_2__f17695ec_9aeb_11e5_91da_b8763fd99c5f__f17695ab_9aeb_11e5_91da_b8763fd99c5f_2.n, bus__f17695ab_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695c9_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f17695c9_9aeb_11e5_91da_b8763fd99c5f__f17695bf_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f17695c9_9aeb_11e5_91da_b8763fd99c5f__f17695bf_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f17695bf_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695d3_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f17695d3_9aeb_11e5_91da_b8763fd99c5f__f17695bf_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f17695d3_9aeb_11e5_91da_b8763fd99c5f__f17695bf_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f17695bf_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695d3_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f17695d3_9aeb_11e5_91da_b8763fd99c5f__f17695bf_9aeb_11e5_91da_b8763fd99c5f_2.p) annotation (Line());
  connect(line_PwLine_2__f17695d3_9aeb_11e5_91da_b8763fd99c5f__f17695bf_9aeb_11e5_91da_b8763fd99c5f_2.n, bus__f17695bf_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695d3_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f17695d3_9aeb_11e5_91da_b8763fd99c5f__f17695bf_9aeb_11e5_91da_b8763fd99c5f_3.p) annotation (Line());
  connect(line_PwLine_2__f17695d3_9aeb_11e5_91da_b8763fd99c5f__f17695bf_9aeb_11e5_91da_b8763fd99c5f_3.n, bus__f17695bf_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695e6_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f17695e6_9aeb_11e5_91da_b8763fd99c5f__f17695bf_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f17695e6_9aeb_11e5_91da_b8763fd99c5f__f17695bf_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f17695bf_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695f2_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f17695f2_9aeb_11e5_91da_b8763fd99c5f__f17695bf_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f17695f2_9aeb_11e5_91da_b8763fd99c5f__f17695bf_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f17695bf_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695f2_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f17695f2_9aeb_11e5_91da_b8763fd99c5f__f17695bf_9aeb_11e5_91da_b8763fd99c5f_2.p) annotation (Line());
  connect(line_PwLine_2__f17695f2_9aeb_11e5_91da_b8763fd99c5f__f17695bf_9aeb_11e5_91da_b8763fd99c5f_2.n, bus__f17695bf_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695e0_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f17695e0_9aeb_11e5_91da_b8763fd99c5f__f17695c9_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f17695e0_9aeb_11e5_91da_b8763fd99c5f__f17695c9_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f17695c9_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695e6_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f17695e6_9aeb_11e5_91da_b8763fd99c5f__f17695c9_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f17695e6_9aeb_11e5_91da_b8763fd99c5f__f17695c9_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f17695c9_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696a9_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f17696a9_9aeb_11e5_91da_b8763fd99c5f__f17695c9_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f17696a9_9aeb_11e5_91da_b8763fd99c5f__f17695c9_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f17695c9_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696c5_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f17696c5_9aeb_11e5_91da_b8763fd99c5f__f17695c9_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f17696c5_9aeb_11e5_91da_b8763fd99c5f__f17695c9_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f17695c9_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695ec_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f17695ec_9aeb_11e5_91da_b8763fd99c5f__f17695d3_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f17695ec_9aeb_11e5_91da_b8763fd99c5f__f17695d3_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f17695d3_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695f2_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f17695f2_9aeb_11e5_91da_b8763fd99c5f__f17695d3_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f17695f2_9aeb_11e5_91da_b8763fd99c5f__f17695d3_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f17695d3_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696cb_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f17696cb_9aeb_11e5_91da_b8763fd99c5f__f17695d3_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f17696cb_9aeb_11e5_91da_b8763fd99c5f__f17695d3_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f17695d3_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769695_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f1769695_9aeb_11e5_91da_b8763fd99c5f__f17695d9_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f1769695_9aeb_11e5_91da_b8763fd99c5f__f17695d9_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f17695d9_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696c5_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f17696c5_9aeb_11e5_91da_b8763fd99c5f__f17695e6_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f17696c5_9aeb_11e5_91da_b8763fd99c5f__f17695e6_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f17695e6_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696cb_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f17696cb_9aeb_11e5_91da_b8763fd99c5f__f17695ec_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f17696cb_9aeb_11e5_91da_b8763fd99c5f__f17695ec_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f17695ec_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696cb_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f17696cb_9aeb_11e5_91da_b8763fd99c5f__f17695ec_9aeb_11e5_91da_b8763fd99c5f_2.p) annotation (Line());
  connect(line_PwLine_2__f17696cb_9aeb_11e5_91da_b8763fd99c5f__f17695ec_9aeb_11e5_91da_b8763fd99c5f_2.n, bus__f17695ec_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176960f_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f176960f_9aeb_11e5_91da_b8763fd99c5f__f17695f2_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f176960f_9aeb_11e5_91da_b8763fd99c5f__f17695f2_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f17695f2_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176960f_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f176960f_9aeb_11e5_91da_b8763fd99c5f__f17695f2_9aeb_11e5_91da_b8763fd99c5f_2.p) annotation (Line());
  connect(line_PwLine_2__f176960f_9aeb_11e5_91da_b8763fd99c5f__f17695f2_9aeb_11e5_91da_b8763fd99c5f_2.n, bus__f17695f2_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696cb_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f17696cb_9aeb_11e5_91da_b8763fd99c5f__f17695f2_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f17696cb_9aeb_11e5_91da_b8763fd99c5f__f17695f2_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f17695f2_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696cb_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f17696cb_9aeb_11e5_91da_b8763fd99c5f__f17695f2_9aeb_11e5_91da_b8763fd99c5f_2.p) annotation (Line());
  connect(line_PwLine_2__f17696cb_9aeb_11e5_91da_b8763fd99c5f__f17695f2_9aeb_11e5_91da_b8763fd99c5f_2.n, bus__f17695f2_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176969f_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f176969f_9aeb_11e5_91da_b8763fd99c5f__f17695ff_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f176969f_9aeb_11e5_91da_b8763fd99c5f__f17695ff_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f17695ff_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176964f_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f176964f_9aeb_11e5_91da_b8763fd99c5f__f1769605_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f176964f_9aeb_11e5_91da_b8763fd99c5f__f1769605_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f1769605_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769695_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f1769695_9aeb_11e5_91da_b8763fd99c5f__f1769605_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f1769695_9aeb_11e5_91da_b8763fd99c5f__f1769605_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f1769605_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769615_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f1769615_9aeb_11e5_91da_b8763fd99c5f__f176960f_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f1769615_9aeb_11e5_91da_b8763fd99c5f__f176960f_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f176960f_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176961f_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f176961f_9aeb_11e5_91da_b8763fd99c5f__f176960f_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f176961f_9aeb_11e5_91da_b8763fd99c5f__f176960f_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f176960f_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769655_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f1769655_9aeb_11e5_91da_b8763fd99c5f__f176960f_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f1769655_9aeb_11e5_91da_b8763fd99c5f__f176960f_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f176960f_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176961f_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f176961f_9aeb_11e5_91da_b8763fd99c5f__f1769615_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f176961f_9aeb_11e5_91da_b8763fd99c5f__f1769615_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f1769615_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769631_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f1769631_9aeb_11e5_91da_b8763fd99c5f__f1769615_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f1769631_9aeb_11e5_91da_b8763fd99c5f__f1769615_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f1769615_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769689_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f1769689_9aeb_11e5_91da_b8763fd99c5f__f1769615_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f1769689_9aeb_11e5_91da_b8763fd99c5f__f1769615_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f1769615_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769631_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f1769631_9aeb_11e5_91da_b8763fd99c5f__f176961f_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f1769631_9aeb_11e5_91da_b8763fd99c5f__f176961f_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f176961f_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769631_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f1769631_9aeb_11e5_91da_b8763fd99c5f__f176961f_9aeb_11e5_91da_b8763fd99c5f_2.p) annotation (Line());
  connect(line_PwLine_2__f1769631_9aeb_11e5_91da_b8763fd99c5f__f176961f_9aeb_11e5_91da_b8763fd99c5f_2.n, bus__f176961f_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176968f_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f176968f_9aeb_11e5_91da_b8763fd99c5f__f1769625_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f176968f_9aeb_11e5_91da_b8763fd99c5f__f1769625_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f1769625_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769631_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f1769631_9aeb_11e5_91da_b8763fd99c5f__f176962b_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f1769631_9aeb_11e5_91da_b8763fd99c5f__f176962b_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f176962b_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769637_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f1769637_9aeb_11e5_91da_b8763fd99c5f__f176962b_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f1769637_9aeb_11e5_91da_b8763fd99c5f__f176962b_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f176962b_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769689_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f1769689_9aeb_11e5_91da_b8763fd99c5f__f176962b_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f1769689_9aeb_11e5_91da_b8763fd99c5f__f176962b_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f176962b_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769637_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f1769637_9aeb_11e5_91da_b8763fd99c5f__f1769631_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f1769637_9aeb_11e5_91da_b8763fd99c5f__f1769631_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f1769631_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769637_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f1769637_9aeb_11e5_91da_b8763fd99c5f__f1769631_9aeb_11e5_91da_b8763fd99c5f_2.p) annotation (Line());
  connect(line_PwLine_2__f1769637_9aeb_11e5_91da_b8763fd99c5f__f1769631_9aeb_11e5_91da_b8763fd99c5f_2.n, bus__f1769631_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176964f_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f176964f_9aeb_11e5_91da_b8763fd99c5f__f176963d_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f176964f_9aeb_11e5_91da_b8763fd99c5f__f176963d_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f176963d_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769683_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f1769683_9aeb_11e5_91da_b8763fd99c5f__f176963d_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f1769683_9aeb_11e5_91da_b8763fd99c5f__f176963d_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f176963d_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769655_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f1769655_9aeb_11e5_91da_b8763fd99c5f__f1769643_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f1769655_9aeb_11e5_91da_b8763fd99c5f__f1769643_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f1769643_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176966b_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f176966b_9aeb_11e5_91da_b8763fd99c5f__f1769643_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f176966b_9aeb_11e5_91da_b8763fd99c5f__f1769643_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f1769643_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769689_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f1769689_9aeb_11e5_91da_b8763fd99c5f__f1769643_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f1769689_9aeb_11e5_91da_b8763fd99c5f__f1769643_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f1769643_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769689_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f1769689_9aeb_11e5_91da_b8763fd99c5f__f1769649_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f1769689_9aeb_11e5_91da_b8763fd99c5f__f1769649_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f1769649_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769671_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f1769671_9aeb_11e5_91da_b8763fd99c5f__f176964f_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f1769671_9aeb_11e5_91da_b8763fd99c5f__f176964f_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f176964f_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769665_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f1769665_9aeb_11e5_91da_b8763fd99c5f__f176965b_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f1769665_9aeb_11e5_91da_b8763fd99c5f__f176965b_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f176965b_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769671_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f1769671_9aeb_11e5_91da_b8763fd99c5f__f176965b_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f1769671_9aeb_11e5_91da_b8763fd99c5f__f176965b_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f176965b_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176967d_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f176967d_9aeb_11e5_91da_b8763fd99c5f__f176965b_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f176967d_9aeb_11e5_91da_b8763fd99c5f__f176965b_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f176965b_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769683_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f1769683_9aeb_11e5_91da_b8763fd99c5f__f176965b_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f1769683_9aeb_11e5_91da_b8763fd99c5f__f176965b_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f176965b_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769677_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f1769677_9aeb_11e5_91da_b8763fd99c5f__f1769671_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f1769677_9aeb_11e5_91da_b8763fd99c5f__f1769671_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f1769671_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176968f_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f176968f_9aeb_11e5_91da_b8763fd99c5f__f1769683_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f176968f_9aeb_11e5_91da_b8763fd99c5f__f1769683_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f1769683_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176969f_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f176969f_9aeb_11e5_91da_b8763fd99c5f__f1769695_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f176969f_9aeb_11e5_91da_b8763fd99c5f__f1769695_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f1769695_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176969f_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f176969f_9aeb_11e5_91da_b8763fd99c5f__f1769695_9aeb_11e5_91da_b8763fd99c5f_2.p) annotation (Line());
  connect(line_PwLine_2__f176969f_9aeb_11e5_91da_b8763fd99c5f__f1769695_9aeb_11e5_91da_b8763fd99c5f_2.n, bus__f1769695_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696b9_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f17696b9_9aeb_11e5_91da_b8763fd99c5f__f17696af_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f17696b9_9aeb_11e5_91da_b8763fd99c5f__f17696af_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f17696af_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696bf_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f17696bf_9aeb_11e5_91da_b8763fd99c5f__f17696af_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f17696bf_9aeb_11e5_91da_b8763fd99c5f__f17696af_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f17696af_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696c5_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f17696c5_9aeb_11e5_91da_b8763fd99c5f__f17696af_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f17696c5_9aeb_11e5_91da_b8763fd99c5f__f17696af_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f17696af_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696c5_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f17696c5_9aeb_11e5_91da_b8763fd99c5f__f17696af_9aeb_11e5_91da_b8763fd99c5f_2.p) annotation (Line());
  connect(line_PwLine_2__f17696c5_9aeb_11e5_91da_b8763fd99c5f__f17696af_9aeb_11e5_91da_b8763fd99c5f_2.n, bus__f17696af_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696c5_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f17696c5_9aeb_11e5_91da_b8763fd99c5f__f17696af_9aeb_11e5_91da_b8763fd99c5f_3.p) annotation (Line());
  connect(line_PwLine_2__f17696c5_9aeb_11e5_91da_b8763fd99c5f__f17696af_9aeb_11e5_91da_b8763fd99c5f_3.n, bus__f17696af_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696d5_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f17696d5_9aeb_11e5_91da_b8763fd99c5f__f17696cb_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f17696d5_9aeb_11e5_91da_b8763fd99c5f__f17696cb_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f17696cb_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696db_9aeb_11e5_91da_b8763fd99c5f.p, line_PwLine_2__f17696db_9aeb_11e5_91da_b8763fd99c5f__f17696cb_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(line_PwLine_2__f17696db_9aeb_11e5_91da_b8763fd99c5f__f17696cb_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f17696cb_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());

// COUPLING DEVICES

// Connecting LOADS
  connect(bus__f17695ab_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f17696e0_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695ab_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f17696e6_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695ab_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f17696ec_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695b9_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f17696f2_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695bf_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f17696f8_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695c9_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f17696fe_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695e6_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f1769704_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695ec_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f176970a_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695ec_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f1769710_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695f2_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f1769716_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695f2_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f176971c_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695f2_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f1769722_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695f2_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f1769728_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695f8_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f176972e_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769605_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f1769734_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769625_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f176973a_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176963d_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f1769740_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176964f_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f1769746_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176964f_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f176974c_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176965b_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f1769752_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176965b_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f1769758_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769677_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f176975e_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176967d_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f1769764_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176968f_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f176976a_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176968f_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f1769770_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769695_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f1769776_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769695_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f176977c_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769695_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f1769782_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176969f_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f1769788_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696a9_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f176978e_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696a9_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f1769794_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696af_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f176979a_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696af_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f17697a0_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696af_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f17697a6_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696af_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f17697ac_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696af_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f17697b2_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696af_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f17697b8_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696b9_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f17697be_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696bf_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f17697c4_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696c5_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f17697ca_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696c5_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f17697d0_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696c5_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f17697d6_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696cb_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f17697dc_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696cb_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f17697e2_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696cb_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f17697e8_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696cb_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f17697ee_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696d5_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f17697f4_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696db_9aeb_11e5_91da_b8763fd99c5f.p, load_load__f17697fa_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());

// Connecting Capacitors
  connect(bus__f176960f_9aeb_11e5_91da_b8763fd99c5f.p, cap_pwCapacitorBank__f1769bd8_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769655_9aeb_11e5_91da_b8763fd99c5f.p, cap_pwCapacitorBank__f1769bdb_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769615_9aeb_11e5_91da_b8763fd99c5f.p, cap_pwCapacitorBank__f1769c02_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769689_9aeb_11e5_91da_b8763fd99c5f.p, cap_pwCapacitorBank__f1769c05_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769643_9aeb_11e5_91da_b8763fd99c5f.p, cap_pwCapacitorBank__f1769ca4_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769689_9aeb_11e5_91da_b8763fd99c5f.p, cap_pwCapacitorBank__f1769ca7_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176964f_9aeb_11e5_91da_b8763fd99c5f.p, cap_pwCapacitorBank__f1769cc2_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769671_9aeb_11e5_91da_b8763fd99c5f.p, cap_pwCapacitorBank__f1769cc5_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());

// Connecting GENERATORS
  connect(bus__f17695ab_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENROU__f1769804_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695ab_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENROU__f176980a_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695ab_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENROU__f1769810_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695c9_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f1769818_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695c9_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f176981f_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695c9_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f1769826_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695c9_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f176982d_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695c9_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f1769834_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695e0_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f176983d_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695e6_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f1769845_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695e6_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f176984c_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695e6_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f1769853_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695e6_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f176985a_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695e6_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f1769861_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695e6_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f1769868_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695e6_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f176986f_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695e6_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f1769876_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695ec_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENROU__f176987f_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695ec_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENROU__f1769886_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695ec_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENROU__f176988d_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695ec_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENROU__f1769894_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695ec_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENROU__f176989b_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695ec_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENROU__f17698a2_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695f2_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENROU__f17698ab_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695f2_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENROU__f17698b1_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695f2_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENROU__f17698b7_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695f2_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENROU__f17698bd_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695f2_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENROU__f17698c3_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695f2_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENROU__f17698c9_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769605_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f17698d1_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769605_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f17698d7_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769625_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f17698df_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769625_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f17698e6_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769625_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f17698ed_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769625_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f17698f4_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769625_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f17698fb_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769625_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f1769902_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176963d_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f176990b_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176963d_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f1769911_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176964f_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f1769919_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176964f_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f1769920_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176965b_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f1769929_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176965b_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f1769930_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176965b_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f1769937_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176965b_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f176993e_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769683_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f1769947_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769683_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f176994d_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769683_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f1769953_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769683_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f1769959_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176968f_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f1769961_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176968f_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f1769967_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176968f_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f176996d_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176968f_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f1769973_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176968f_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f1769979_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769695_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f1769981_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769695_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f1769988_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769695_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f176998f_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769695_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f1769996_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176969f_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f176999f_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176969f_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f17699a6_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176969f_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f17699ad_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176969f_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f17699b4_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696af_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENROU__f17699bd_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696af_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENROU__f17699c4_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696af_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENROU__f17699cb_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696af_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENROU__f17699d2_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696af_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENROU__f17699d9_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696af_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENROU__f17699e0_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696af_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENROU__f17699e7_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696af_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENROU__f17699ee_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696af_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENROU__f17699f5_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696c5_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f17699fe_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696c5_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f1769a04_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696c5_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENSAL__f1769a0a_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696cb_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENROU__f1769a12_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696cb_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENROU__f1769a19_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696cb_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENROU__f1769a20_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696cb_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENROU__f1769a27_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696cb_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENROU__f1769a2e_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696cb_9aeb_11e5_91da_b8763fd99c5f.p, gen_gENROU__f1769a35_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());

// Connecting DETAILED TRANSFORMERS
  connect(bus__f17695e0_9aeb_11e5_91da_b8763fd99c5f.p, trafo_PwPhaseTransformer__f17695e0_9aeb_11e5_91da_b8763fd99c5f__f17695d9_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(trafo_PwPhaseTransformer__f17695e0_9aeb_11e5_91da_b8763fd99c5f__f17695d9_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f17695d9_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695e6_9aeb_11e5_91da_b8763fd99c5f.p, trafo_PwPhaseTransformer__f17695e6_9aeb_11e5_91da_b8763fd99c5f__f17695ff_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(trafo_PwPhaseTransformer__f17695e6_9aeb_11e5_91da_b8763fd99c5f__f17695ff_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f17695ff_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17695f8_9aeb_11e5_91da_b8763fd99c5f.p, trafo_PwPhaseTransformer__f17695f8_9aeb_11e5_91da_b8763fd99c5f__f17695f2_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(trafo_PwPhaseTransformer__f17695f8_9aeb_11e5_91da_b8763fd99c5f__f17695f2_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f17695f2_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769605_9aeb_11e5_91da_b8763fd99c5f.p, trafo_PwPhaseTransformer__f1769605_9aeb_11e5_91da_b8763fd99c5f__f176960f_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(trafo_PwPhaseTransformer__f1769605_9aeb_11e5_91da_b8763fd99c5f__f176960f_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f176960f_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176962b_9aeb_11e5_91da_b8763fd99c5f.p, trafo_PwPhaseTransformer__f176962b_9aeb_11e5_91da_b8763fd99c5f__f1769625_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(trafo_PwPhaseTransformer__f176962b_9aeb_11e5_91da_b8763fd99c5f__f1769625_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f1769625_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769643_9aeb_11e5_91da_b8763fd99c5f.p, trafo_PwPhaseTransformer__f1769643_9aeb_11e5_91da_b8763fd99c5f__f176963d_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(trafo_PwPhaseTransformer__f1769643_9aeb_11e5_91da_b8763fd99c5f__f176963d_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f176963d_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769649_9aeb_11e5_91da_b8763fd99c5f.p, trafo_PwPhaseTransformer__f1769649_9aeb_11e5_91da_b8763fd99c5f__f176963d_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(trafo_PwPhaseTransformer__f1769649_9aeb_11e5_91da_b8763fd99c5f__f176963d_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f176963d_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769655_9aeb_11e5_91da_b8763fd99c5f.p, trafo_PwPhaseTransformer__f1769655_9aeb_11e5_91da_b8763fd99c5f__f176964f_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(trafo_PwPhaseTransformer__f1769655_9aeb_11e5_91da_b8763fd99c5f__f176964f_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f176964f_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769689_9aeb_11e5_91da_b8763fd99c5f.p, trafo_PwPhaseTransformer__f1769689_9aeb_11e5_91da_b8763fd99c5f__f1769665_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(trafo_PwPhaseTransformer__f1769689_9aeb_11e5_91da_b8763fd99c5f__f1769665_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f1769665_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f176966b_9aeb_11e5_91da_b8763fd99c5f.p, trafo_PwPhaseTransformer__f176966b_9aeb_11e5_91da_b8763fd99c5f__f1769671_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(trafo_PwPhaseTransformer__f176966b_9aeb_11e5_91da_b8763fd99c5f__f1769671_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f1769671_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f1769689_9aeb_11e5_91da_b8763fd99c5f.p, trafo_PwPhaseTransformer__f1769689_9aeb_11e5_91da_b8763fd99c5f__f1769683_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(trafo_PwPhaseTransformer__f1769689_9aeb_11e5_91da_b8763fd99c5f__f1769683_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f1769683_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());
  connect(bus__f17696a9_9aeb_11e5_91da_b8763fd99c5f.p, trafo_PwPhaseTransformer__f17696a9_9aeb_11e5_91da_b8763fd99c5f__f176969f_9aeb_11e5_91da_b8763fd99c5f_1.p) annotation (Line());
  connect(trafo_PwPhaseTransformer__f17696a9_9aeb_11e5_91da_b8763fd99c5f__f176969f_9aeb_11e5_91da_b8763fd99c5f_1.n, bus__f176969f_9aeb_11e5_91da_b8763fd99c5f.p) annotation (Line());

// Connecting OTHERS
end N44_h21;

