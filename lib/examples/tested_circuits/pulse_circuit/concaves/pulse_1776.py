import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [630.1716989542213,631.5976500250576,632.9316585374402,634.1787211746189,635.3436905285275,636.4311858540502,637.4455953756326,638.3910846361669,639.2716059554028,640.0909079890309,640.8525450934562,641.5598863941927,642.2161245212792,642.8242840009578,643.3872293041967,643.9076725579794,644.3881809279167,644.831183682039,645.2389789462454,645.6137401621273,645.9575222579126,646.2722675431813,646.5598113378296,646.8218873455446,647.0601327817994,647.2760932661214,647.4712274881045,647.646911656361,647.8044437393223,647.9450475065192,648.0698763786856,648.1800170947544,648.2764932035392,648.3602683876222,648.4322496267032,648.4932902074054,648.5441925862749,648.585711112464,648.6185546163413,648.6433888700358,648.66083892569,648.6714913369713,648.6758962691713,648.674569503011,648.6679943370606,648.6566233934877,648.6408803316478,648.621161473846,648.5978373474192,648.571254147109,648.5417351215282,648.5095818873604,648.4750756747735,648.4384785073777,648.400034319908,648.3599700166774,648.3184964737038,648.2758094872876,648.2320906716914,648.1875083084506,648.1422181497308,648.0963641780334,648.0500793244481,648.0034861475472,647.9566974749164,647.9098170092278,647.8629399006663,647.816153287438,647.7695368060043,647.7231630726081,647.6770981375834,647.6314019138649,647.5861285810515,647.5413269663014,647.4970409032866,647.4533095703628,647.4101678090608,647.3676464239484,647.3257724648593,647.2845694924355,647.2440578278846,647.2042547878046,647.165174904888,647.1268301352764,647.089230053295,647.0523820342619,647.0162914260297,646.9809617098837,646.9463946513888,646.9125904417485,646.8795478302044]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1776.png', dpi=300)
