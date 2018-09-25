import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [668.3963041221473,670.462933862485,672.4295336180251,674.300623270312,676.0805301858965,677.7733969203516,679.3831886394881,680.913700266393,682.3685633628244,683.7512527533867,685.0650929007725,686.3132640402127,687.4988080811154,688.6246342837003,689.6935247182626,690.70813951451,691.6710219082348,692.5846030923868,693.4512068794246,694.2730541816289,695.0522673158703,695.7908741391311,696.4908120208963,697.1539316583358,697.7820007400217,698.3767074637416,698.9396639137892,699.4724093029424,699.9764130841677,700.4530779369226,700.9037426327654,701.3296847848259,701.7321234855297,702.1122218368272,702.4710893770243,702.8097844081747,703.1293162278517,703.4306472689898,703.7146951513448,703.9823346480092,704.2343995702848,704.4716845741034,704.6949468910682,704.9049079870783,705.1022551513909,705.2876430188699,705.4616950280724,705.6250048177229,705.7781375640321,705.9216312612273,706.0559979475714,706.1817248790635,706.2992756529289,706.4090912829326,706.5115912284665,706.6071743792913,706.6962199977426,706.7790886201349,706.8561229190423,706.9276485280575,706.9939748305792,707.0553957141108,707.1121902914998,707.1646235904911,707.2129472129103,707.2573999647467,707.2982084583527,707.3355876879275,707.3697415794095,707.4008635158555,707.4291368393415,707.4547353303806,707.4778236658107,707.4985578560701,707.5170856627386,707.5335469971889,707.5480743011568,707.5607929100095,707.5718213994535,707.5812719164,707.5892504946723,707.5958573562122,707.6011871984163,707.6053294682074,707.608368623419,707.6103843820493,707.6114519599172,707.6116422972302,707.6110222745534,707.6096549186475,707.6075995986259]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1544.png', dpi=300)
