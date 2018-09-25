import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [398.35081899232495,1090.8337770489613,1749.6027519322859,2374.8489729430353,2967.1340679958694,3527.250128219777,4056.135305759878,4554.819644648754,5024.388716942316,5465.958392121567,5880.656903117136,6269.611877892806,6633.940855950962,6974.744313239802,7293.100530829539,7590.061842629861,7866.651929545387,8123.8639171594095,8362.659096374407,8583.96613067585,8788.680645667635,8977.665120036887,9151.749014648327,9311.72908973703,9458.369870328499,9592.404227882442,9714.534052310888,9825.43099338068,9925.737254379854,10016.066424032771,10097.004335158692,10169.109940610124,10232.916198697998,10288.93096168518,10337.637862066063,10379.497192293462,10414.9467744005,10444.402816623035,10468.260754680103,10486.896075834205,10500.665124244726,10509.905886458357,10514.938756159967,10516.067277544043,10513.578866867132,10507.74551191135,10498.824449232568,10487.058819188058,10472.67829884065,10455.899712922383,10436.927623112544,10415.954895945028,10393.16324970947,10368.723780751396,10342.797469609546,10315.535667455062,10287.080563317691,10257.565632600019,10227.116067392024,10195.849189106098,10163.874843957077,10131.295781813724,10098.208018947465,10064.701185201651,10030.85885610018,9996.758870408647,9962.47363365403,9928.070408100837,9893.611589672682,9859.1549722985,9824.75400015229,9790.458008244514,9756.312451812044,9722.359124942153,9688.636368854297,9655.179270251678,9622.01985014264,9589.18724352008,9556.707870275075,9524.605597709142,9492.901894997816,9461.615979946508,9430.764958368245,9400.36395640152,9370.426246075385,9340.963364418008,9311.985226394285,9283.500231947553,9255.515367410311,9228.036301538868,9201.06747641714]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1837.png', dpi=300)
