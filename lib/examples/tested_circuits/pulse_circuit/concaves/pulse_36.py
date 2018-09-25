import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [660.5602124875392,673.5933800613722,685.9792212524162,697.7232429277149,708.8384967862194,719.342186644534,729.2538781421338,738.5944576991155,747.3854858604653,755.6487765847406,763.4061144998111,770.6790606659931,777.4888173948968,783.8561337403922,789.8012397314645,795.3438013480123,800.5028907255901,805.2969676965336,809.7438698624663,813.8608091404748,817.6643732497433,821.1705309806173,824.3946403609409,827.3514590360411,830.0551563295516,832.5193265665581,834.7570033280908,836.7806743737265,838.6022970219553,840.233313819596,841.6846683645557,842.9668211725985,844.0897654999538,845.0630430507039,845.8957595117752,846.5965998696794,847.1738434724227,847.6353788076207,847.9887179741361,848.2410108297497,848.3990588016778,848.4693283503215,848.4579640796086,848.3708014897594,848.2133793703763,847.9909518334729,847.7084999874959,847.3707432545883,846.982150334336,846.5469498180674,846.0691404584605,845.5525010997735,845.0006002744834,844.416805472489,843.8042920893425,843.166052060212,842.5049021864723,841.8234921619593,841.12431230603,840.4097010106448,839.6818519087268,838.9428207710757,838.1945321391082,837.4387857006772,836.6772624161828,835.9115304021396,835.1430505792997,834.3731820923554,833.6031875081679,832.8342377993712,832.0674171201042,831.3037273805237,830.5440926266339,829.7893632318613,829.0403199066803,828.2976775324751,827.5620888256993,826.834147838267,826.1143932999789,825.4033118086576,824.7013408735337,824.0088718172896,823.3262525420362,822.6537901643632,821.9917535244663,821.3403755742254,820.6998556489696,820.0703616275341,819.4520319850815,818.8449777430262,818.2492843202742]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_36.png', dpi=300)
