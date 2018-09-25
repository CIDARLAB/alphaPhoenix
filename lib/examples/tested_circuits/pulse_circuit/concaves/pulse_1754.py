import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [630.1716989542213,631.6536167535187,633.05223363603,634.3716768383923,635.615893142938,636.7886550499644,637.8935669357243,638.934071160913,639.913454101701,640.8348520812493,641.7012571844375,642.5155229424415,643.2803698770022,643.9983908968571,644.6720565409715,645.3037200649907,645.8956223688047,646.4498967643349,646.9685735836447,647.4535846283073,647.9067674616299,648.3298695458882,648.7245522271716,649.0923945707929,649.4348970505072,649.7534850950022,650.0495124952957,650.3242646767992,650.578961839894,650.8147619729242,651.0327637415386,651.2340092583187,651.4194867366199,651.5901330325211,651.7468360787382,651.8904372143,652.0217334137261,652.1414794193715,652.2503897805304,652.3491408028057,652.4383724111678,652.5186899300392,652.5906657836439,652.6548411197799,652.711727360068,652.7618076796462,652.8055384191792,652.8433504319664,652.8756503688325,652.9028219033987,652.9252269002435,652.9432065283686,652.9570823223054,652.9671571931086,652.9737163914023,652.9770284245602,652.9773459300293,652.9749065067207,652.9699335063245,652.9626367863274,652.9532134264443,652.9418484101068,652.9287152725831,652.9139767172421,652.8977852014132,652.8802834932286,652.8616052007839,652.841875274892,652.8212104866535,652.7997198810145,652.777505207433,652.7546613287266,652.731276609128,652.707433282531,652.6832078018643,652.6586711704936,652.6338892565072,652.6089230907095,652.5838291491008,652.5586596205974,652.5334626607032,652.5082826318178,652.4831603308319,652.4581332046329,652.4332355541135,652.4084987272514,652.3839513017984,652.3596192580973,652.3355261425145,652.3116932219613,652.2881396299466]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1754.png', dpi=300)
