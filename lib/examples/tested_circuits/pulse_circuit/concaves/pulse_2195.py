import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [624.0035852921282,626.0903112850098,628.0625831839268,629.9260557201127,631.6861253013849,633.3479409444079,634.9164147869271,636.3962321935787,637.7918614686464,639.1075631888746,640.3473991691736,641.5152410737669,642.6147786850264,643.6495278419485,644.6228380599065,645.5378998430102,646.3977517000909,647.2052868750203,647.9632598017637,648.6742922942616,649.3408794809312,649.965395493284,650.5500989178557,651.0971380203661,651.6085557507298,652.0862945372764,652.5322008782523,652.9480297384204,653.3354487583096,653.6960422834127,654.0313152203857,654.342696727056,654.631543742817,654.8991443657534,655.1467210826219,655.3754338575928,655.5863830854494,655.7806124147362,655.9591114461487,656.1228183112662,656.2726221365386,656.4093653972612,656.533846166091,656.646820260492,656.7490032933287,656.8410726306698,656.9236692607061,656.9973995775407,657.0628370834636,657.1205240131803,657.1709728833337,657.2146679705228,657.2520667208995,657.2836010943008,657.3096788457569,657.3306847471008,657.3469817512981,657.3589121020083,657.3667983907857,657.3709445642332,657.3716368833268,657.3691448370329,657.3637220122617,657.355606922106,657.3450237942399,657.3321833212713,657.3172833747667,657.3005096845947,657.2820364851643,657.2620271300699,657.2406346765846,657.2180024413898,657.1942645288616,657.1695463331833,657.1439650154955,657.1176299572421,657.0906431908247,657.0630998086223,657.0350883513927,657.0066911770247,656.9779848105671,656.9490402764208,656.91992341354,656.8906951744518,656.8614119088654,656.8321256326091,656.8028842826003,656.7737319585209,656.7447091518396,656.715852962796,656.6871973059292]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_2195.png', dpi=300)
