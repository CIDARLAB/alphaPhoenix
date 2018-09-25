import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [633.9804202411561,635.7684774635719,637.4605916966361,639.0569497704491,640.558623609562,641.9673192376857,643.2851998902182,644.514757707791,645.6587185112462,646.719969825729,647.7015056812536,648.6063837937417,649.4376920595943,650.1985221735642,650.8919487734488,651.521012926702,652.0887090652893,652.597974685002,653.0516822792524,653.45263309176,653.8035523587849,654.1070857773997,654.365796987112,654.5821658918046,654.7585876801821,654.897372427741,655.0007451831574,655.0708464580545,655.1097330521656,655.1193791566072,655.1016776867923,655.0584418038122,654.9914065892153,654.9022308432062,654.7924989805941,654.6637230024538,654.5173445245546,654.354736846254,654.1772070458137,653.9859980900403,653.7822909478336,653.5672066986799,653.341808628388,653.107104305471,652.8640476325336,652.6135408678638,652.3564366131739,652.0935397640719,651.8256094204262,651.5533607542774,651.2774668334073,650.9985603990531,650.7172355966095,650.4340496584647,650.1495245383807,649.8641484970732,649.5783776388521,649.292637399367,649.007323984668,648.7228057619316,648.4394246023256,648.1574971765987,647.8773162040679,647.5991516557648,647.323251912565,647.0498448791894,646.7791390550095,646.5113245626343,646.2465741352871,645.9850440640087,645.7268751057412,645.4721933533643,645.221111068762,644.9737274800071,644.730129543748,644.4903926738835,644.2545814376017,644.022750219856,643.794943857336,643.5711982429798,643.3515409020605,643.1359915408601,642.9245625689304,642.7172595959153,642.5140819038971,642.3150228961989,642.1200705235584,641.9292076885696,641.7424126292561,641.5596592826264,641.3809176290317]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_2072.png', dpi=300)
