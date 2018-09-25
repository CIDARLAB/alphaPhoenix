import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [695.4927126146227,698.6379121139506,701.533367903924,704.1744862490341,706.5670275865244,708.7193355954045,710.6407221562688,712.3408680567466,713.8295442248208,715.1164646626837,716.2112026524405,717.1231407217796,717.8614400261016,718.4350215421633,718.8525547609373,719.1224513030191,719.2528618510764,719.2516753645477,719.1265198910795,718.8847645103668,718.5335220901735,718.0796526304804,717.5297670371945,716.8902312121671,716.1671703780447,715.3664735789747,714.4937983142278,713.5545752732777,712.5540131490956,711.4971035122795,710.3886257327982,709.2331519390361,708.035052005831,706.7984985645352,705.5274720289956,704.2257656318687,702.8969904659738,701.5445805255074,700.1717977419685,698.781737009598,697.377331195076,695.9613561261416,694.5364355537512,693.1050460823438,691.6695220627885,690.2320604426142,688.7947255681942,687.3594539336627,685.928058871483,684.502235179764,683.0835636816317,681.6735157121918,680.2734575288827,678.8846546412962,677.5082760568366,676.1453984388959,674.7970101745403,673.4640153490229,672.1472376247596,670.8474240227301,669.565248604584,668.3013160540436,667.0561651565035,665.8302721760239,664.6240541291952,663.4378719556329,662.2720335851145,661.1267969016224,660.0023726047853,658.8989269694324,657.816584504171,656.7554305100929,655.7155135408817,654.6968477657545,653.6994152368129,652.7231680625083,651.7680304890404,650.8339008916123,649.9206536775521,649.0281411033899,648.1561950080431,647.3046284643189,646.473237350984,645.6618018476904,644.8700878550642,644.0978483422876,643.344824624508,642.61074757241,641.8953387562815,641.1983115268906,640.5193720354741]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1707.png', dpi=300)
