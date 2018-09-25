import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [394.41585225180125,3405.8862234711373,6281.113018327353,9024.97809693072,11642.32073636437,14137.911252891368,16516.431730269323,18782.46202415232,20940.469737280786,22994.803217090892,24949.68687520873,26809.218303771548,28577.366789950807,30257.972922586396,31854.7490535253,33371.28042789476,34811.02683680903,36177.32467618856,37473.38931878437,38702.31772482571,39867.09123116233,40970.578470256376,42015.53837956156,43004.62326921357,43940.381921929446,44825.26270386844,45661.61666916907,46451.70064412406,47197.68027962513,47901.633062711626,48565.55127987865,49191.344926310645,49780.84455646048,50335.804072438004,50857.9034475413,51348.75138298866,51809.887896514214,52242.786841994064,52648.85835969089,53029.45125705495,53385.85532031115,53719.30355730361,54030.97437226959,54321.99367337964,54593.43691401621,54846.33106887333,55081.65654604895,55300.34903637257,55503.30130126695,55691.36490048504,55865.35186109575,56026.036289114454,56174.15592518904,56310.41364576024,56435.47891111724,56549.98916176718,56654.55116453038,56749.742309763096,56836.111861096535,56914.18215906512,56984.449779979725,57047.386651381814,57103.44112539373,57153.03901125811,57196.584568336395,57234.46146081241,57267.0336753222,57294.646402706334,57317.62688505511,57336.28522919149,57350.915187709994,57361.79490866417,57369.18765496839,57373.342494553835,57374.49496229204,57372.86769467345,57368.67103820228,57362.10363244333,57353.35296863087,57342.59592472432,57329.9992777705,57315.7201944076,57299.90670032167,57282.698129442295,57264.22555364073,57244.61219367036,57223.9738120666,57202.41908870106,57180.04997966249,57156.962060115875,57133.24485176966]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_806.png', dpi=300)
