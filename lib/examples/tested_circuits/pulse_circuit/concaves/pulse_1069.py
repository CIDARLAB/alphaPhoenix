import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [668.8841388539614,819.7045312147591,963.6505342964623,1100.8977279039768,1231.6442717664222,1356.101473279081,1474.4877001723346,1587.0243001215347,1693.9327908215535,1795.4328890816705,1891.7411132274021,1983.069788295874,2069.6263407381193,2151.6128051289006,2229.225488519841,2302.654753465653,2372.084891258285,2437.6940642335703,2499.6543012311195,2558.131534065983,2613.2856656500817,2665.270662475486,2714.234665739218,2760.3201165877235,2803.6638918850394,2844.39744763074,2882.6469677216674,2918.5335162015913,2952.173191502234,2983.6772814676747,3013.1524181872146,3040.700731850997,3066.4200029969493,3090.40381264389,3112.741689909374,3133.519256796267,3152.8183699025853,3170.717258867472,3187.290661414528,3202.609954893745,3216.743284256547,3229.7556864259664,3241.7092110468,3252.6630376194626,3262.6735890368063,3271.7946415559863,3280.0774312479107,3287.5707569753476,3294.3210799576323,3300.3726199854163,3305.767448353241,3310.545577581058,3314.745047998351,3318.402011266351,3321.550810915069,3324.2240599726365,3326.4527157647794,3328.266151962252,3329.6922279537584,3330.757355621363,3331.4865635946476,3331.9035590589915,3332.0307871922905,3331.8894883033067,3331.499752743594,3330.8805736636336,3330.0498976824533,3329.024673538583,3327.8208987887638,3326.4536646193546,3324.9371988338917,3323.2849070787574,3321.50941236742,3319.6225929621924,3317.635618670977,3315.5589856149504,3313.4025495216774,3311.1755575966695,3308.8866790249513,3306.5440341527515,3304.1552223980266,3301.7273489371237,3299.2670502134956,3296.780518313039,3294.2735242492745,3291.7514402002857,3289.2192607380316,3286.6816230893837,3284.1428264669994,3281.6068505069143,3279.0773728485524]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1069.png', dpi=300)
