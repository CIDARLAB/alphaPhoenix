import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [668.3963041221473,818.0503025390117,959.4502354230556,1092.7674278424633,1218.3062428599344,1336.3949982331283,1447.3644264953548,1551.540001856957,1649.2386088080364,1740.7669854127525,1826.4210130356118,1906.4854520776344,1981.2339289309296,2050.9290707123955,2115.8227290541577,2176.1562578428498,2232.1608230447027,2284.0577305581505,2332.058762827504,2376.3665179966197,2417.174747374415,2454.6686883211796,2489.0253905809973,2520.4140347249286,2548.9962418225623,2574.9263737843535,2598.3518240525227,2619.4132984899597,2638.245086442526,2654.9753220426014,2669.726235889585,2682.6143972925192,2693.7509472957067,2703.2418227334283,2711.1879715771415,2717.685559849747,2722.826170387997,2726.6969937369995,2729.381011460836,2730.9571721512334,2731.500560412427,2731.0825590953027,2729.7710050478063,2727.6303386418067,2724.721747329168,2721.1033034719912,2716.8300966838497,2711.9543609105335,2706.5255964703883,2700.5906872658434,2694.194013369251,2687.3775591777326,2680.1810173234026,2672.6418885171315,2664.7955774959732,2656.675485236507,2648.313097588716,2639.738070477557,2630.978311812227,2622.0600602361706,2613.0079608442193,2603.84513798687,2594.5932652755846,2585.2726328972035,2575.902212340018,2566.4997186288183,2557.0816701612935,2547.663446233513,2538.2593423378207,2528.882623312413,2519.545574418019,2510.2595504135456,2501.035022699245,2491.881624592873,2482.808194801455,2473.8228191486683,2464.93287061538,2456.145047748675,2447.465411492602,2438.899420491993,2430.451964918925,2422.1273988697844,2413.929571379385,2405.861856097205,2397.9271796695066,2390.128048869901,2382.466576519797,2374.944506239097,2367.5632360665168,2360.323840987933,2353.227094410275]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1597.png', dpi=300)
