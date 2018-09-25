import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [622.4396731201457,623.8612226873914,625.2045877695065,626.4738195274671,627.6727690904697,628.8050969611061,629.8742820048566,630.8836300406258,631.8362820484689,632.7352220101105,633.5832843973105,634.3831613226057,635.1374093664406,635.8484560941962,636.518606276137,637.1500478228203,637.7448574480482,638.3050060709888,638.8323639686604,639.3287056895385,639.7957147386431,640.2349880440522,640.6480402144063,641.0363075965892,641.4011521424064,641.7438650927305,642.0656704872391,642.3677285075458,642.6511386612005,642.9169428137326,643.1661280756086,643.3996295506946,643.6183329525327,643.8230770944771,644.0146562594788,644.1938224550586,644.361287558773,644.5177253592475,644.6637734976307,644.8000353141131,644.9270816039498,645.045452287231,645.1556579964571,645.2581815857944,645.353479565716,645.4419834665645,645.5241011344162,645.6002179624733,645.6706980610628,645.7358853691852,645.7961047104156,645.8516627958383,645.9028491765663,645.9499371482868,645.9931846101526,646.0328348802385,646.0691174696767,646.1022488174851,646.1324329880092,646.1598623328085,646.1847181187329,646.2071711238501,646.2273822028083,646.2455028231435,646.2616755739678,646.2760346484077,646.2887063010955,646.2998092819549,646.3094552474624,646.3177491505099,646.3247896099374,646.330669260756,646.3354750860315,646.3392887313491,646.3421868027398,646.3442411489004,646.3455191285062,646.3460838633667,646.3459944781474,646.3453063273389,646.3440712101238,646.3423375737597,646.3401507060647,646.3375529175661,646.3345837138394,646.331279958547,646.3276760276522,646.3238039552646,646.3196935715532,646.3153726331341,646.3108669463264]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_720.png', dpi=300)
