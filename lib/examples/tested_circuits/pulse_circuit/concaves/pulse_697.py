import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [643.9090983267836,645.2160315571593,646.3894882870883,647.4269814152954,648.3321401105956,649.1102714595147,649.7673174216346,650.3094509372097,650.7428829964093,651.0737589700706,651.3080988511352,651.4517614363729,651.5104225752734,651.4895621680233,651.3944568502152,651.2301765044008,651.0015834183504,650.7133333132264,650.3698777141462,649.9754672950841,649.5341559351857,649.0498052947071,648.5260897680407,647.9665017061234,647.3743568255964,646.7527997404759,646.1048095657723,645.4332055528401,644.7406527241698,644.0296674814854,643.3026231658669,642.5617555524723,641.809168265556,641.0468381020218,640.2766202538493,639.5002534214858,638.7193648117685,637.935475015201,637.1500027584822,636.3642695291171,635.5795040697469,634.7968467405445,634.0173537486427,633.2420012441091,632.47168928247,631.7072456542088,630.9494295820497,630.1989352871708,629.4563954257894,628.7223843978247,627.9974215295733,627.281974132537,626.5764604407152,625.8812524288272,625.1966785140614,624.5230261440528,623.8605442738879,623.209445735003,622.5699094989053,621.9420828386869,621.3260833913354,620.7220011238594,620.1299002062611,619.549820794379,618.9817807256179,618.425777130563,617.8817879634453,617.3497734543965,616.8296774863895,616.3214288997161,615.8249427268086,615.3401213601522,614.8668556559866,614.4050259764276,613.9545031725855,613.5151495111859,613.0868195471392,612.6693609444336,612.2626152476597,611.8664186064075,611.480602454706,611.1049941476066,610.7394175569436,610.3836936282349,610.0376409006185,609.7010759916527,609.3738140487417,609.0556691688804,608.7464547883511,608.4459840439375,608.1540701071615]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_697.png', dpi=300)
