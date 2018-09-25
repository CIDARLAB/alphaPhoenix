import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [396.07523462490303,397.2865538789467,398.43438960176076,399.517747505269,400.5372139292573,401.49427241102916,402.3909381124304,403.22954166228493,404.01259271705004,404.7426899905548,405.4224601908366,406.0545158442405,406.6414259352636,407.18569550124283,407.68975162784005,408.1559341000223,408.58648948321036,408.9835677546949,409.349220841427,409.6854025855095,409.99396977686433,410.2766839786005,410.5352139343149,410.7711383943797,410.98594923462247,411.1810547687316,411.3577831773732,411.51738599393144,411.66104160010207,411.7898586951099,411.9048797106993,412.00708415073206,412.0973918395762,412.17666606775174,412.24571662673765,412.3053027275921,412.35613580024454,412.3988821720696,412.43416562574856,412.46256983752204,412.4846406977957,412.5008885167232,412.5117901178916,412.5177908236077,412.5193063355443,412.5167245146873,412.5104070646282,412.50069112229795,412.48789076024246,412.4722984045074,412.45418617213795,412.43380713221586,412.41139649425463,412.3871727276582,412.3613386158254,412.3340822483509,412.3055779546393,412.2759871821101,412.2454593220366,412.21413248592035,412.18213423517176,412.149582266731,412.11658505713564,412.08324246741466,412.04964631106606,412.01588088725765,411.98202348127774,411.94814483415246,411.9143095832434,411.88057667553795,411.84699975525194,411.8136275272711,411.78050409787284,411.74766929408867,411.71515896298746,411.68300525208883,411.65123687204346,411.61987934265267,411.5889552232365,411.5584843282997,411.5284839293903,411.4989689439916,411.4699521122398,411.4414441622102,411.41345396447366,411.3859886765807,411.35905387809265,411.33265369674115,411.30679092626406,411.2814671364306,411.2566827757397]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_296.png', dpi=300)
