import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [622.4396731201457,623.8646829378993,625.2108925480643,626.4823565851808,627.6829652169041,628.8164221490631,629.8862490772118,630.8957929861599,631.8482339184814,632.7465926782771,633.5937383099086,634.3923952980434,635.145150472715,635.8544596180807,636.5226537901137,637.1519453514745,637.7444337331774,638.3021109332497,638.8268667627307,639.3204938492989,639.7846924086226,640.2210747932716,640.6311698287321,641.0164269457483,641.3782201178894,641.7178516129168,642.0365555661951,642.3355013840767,642.6157969848723,642.8784918847178,643.1245801353471,643.3550031204959,643.5706522173828,643.7723713294403,643.9609592962145,644.137172186095,644.3017254772981,644.4552961322928,644.5985245706335,644.7320165449456,644.8563449246091,644.9720513914748,645.0796480517685,645.1796189681414,645.27242161566,645.3584882653497,645.4382272987483,645.5120244567686,645.5802440260198,645.6432299655928,645.701306977177,645.7547815212482,645.8039427819363,645.8490635830625,645.8904012577234,645.9281984736848,645.962684016743,645.9940735341146,646.0225702398133,646.0483655838838,646.0716398872745,646.0925629440438,646.1112945925186,646.1279852569419,646.1427764610776,646.1558013151672,646.1671849775687,646.1770450923398,646.1854922039747,646.1926301504369,646.1985564355801,646.2033625819964,646.2071344652765,646.2099526306248,646.2118925927225,646.2130251196858,646.2134165019323,646.2131288067213,646.2122201190998,646.2107447699506,646.2087535518012,646.2062939230242,646.203410201023,646.2001437449743,646.1965331286628,646.1926143039235,646.1884207551759,646.1839836455154,646.1793319547986,646.1744926101425,646.1694906092306]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_753.png', dpi=300)
