import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [393.74882749495697,1009.8471848778603,1589.8972262667469,2133.351777896471,2641.0592114770852,3114.2988219786384,3554.5200714670996,3963.236532752129,4341.973973852042,4692.242165806488,5015.5186380497225,5313.239053306232,5586.791511427719,5837.513307810774,6066.689285375354,6275.551251015364,6465.278117668928,6636.99654744593,6791.781942695695,6930.659678098919,7054.606497630326,7164.552021248198,7261.380320838798,7345.931535392144,7419.003502956351,7481.3533924879675,7533.699322869906,7576.72195950414,7611.066081278009,7637.342112544163,7656.127616182928,7667.968744932013,7673.381649045359,7672.853839034817,7666.845502795973,7655.790776853847,7640.098971808763,7620.155752335858,7596.324272307568,7568.946265778125,7538.343094701421,7504.816754355581,7468.6508375247695,7430.111458545705,7389.448138366685,7346.8946517936365,7302.669838113262,7256.978376289642,7210.011525929496,7161.947835203926,7112.953816902086,7063.184593775732,7012.784514313865,6961.887740064299,6910.618805594513,6859.093152158137,6807.417636106121,6755.6910130535525,6704.004398784301,6652.441707846641,6601.080070763708,6549.990230753358,6499.236920822927,6448.879222075429,6398.970904035274,6349.56074777336,6300.692852583845,6252.406926937729,6204.738564411875,6157.719505266136,6111.377884316002,6065.738465723452,6020.822865304786,5976.649760930833,5933.235091572259,5890.592245520796,5848.732238295795,5807.663880724949,5767.393937667986,5727.927277832799,5689.267015114797,5651.4146418721975,5614.370154532472,5578.132171908393,5542.698046585804,5508.063969729597,5474.225069639267,5441.175504370853,5408.908548728063,5377.416675911857,5346.691634104826]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_2365.png', dpi=300)
