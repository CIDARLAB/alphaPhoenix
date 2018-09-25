import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [696.5840284521588,869.1518147506519,1032.0883167920072,1185.6155026650897,1330.0962056760509,1465.9161967005336,1593.4617354436255,1713.1116966765846,1825.2342158234007,1930.1851701635362,2028.3075305780023,2119.9311691488315,2205.372921525579,2284.9367975725,2358.9142800142286,2427.584675148995,2491.215493332715,2550.0628449585543,2604.3718425735315,2654.3770028935064,2700.3026445158434,2742.3632784935644,2780.7639898676894,2815.7008089034434,2847.361071234122,2875.9237664434404,2901.5598748528323,2924.4326924508164,2944.698144025592,2962.505084652073,2977.995589749618,2991.3052339732185,3002.5633592335357,3011.893332163339,3019.4127913621805,3025.233884759399,3029.4634974392407,3032.2034702721144,3033.55080969357,3033.597888968137,3032.4326412692308,3030.1387448992227,3026.795800965849,3022.4795038225816,3017.261804571602,3011.2110679187413,3004.3922226602613,2996.866906071801,2988.693602460159,2979.9277761289945,2970.6219989999563,2960.826073121275,2950.587148286478,2939.949834976659,2928.956312830623,2917.6464348383256,2906.0578274442587,2894.22598673889,2882.1843709079144,2869.964489100939,2857.595986873316,2845.106728347177,2832.5228752302905,2819.8689628242205,2807.167973146339,2794.441405283656,2781.709343090051,2768.990520332463,2756.3023833858088,2743.6611515709274,2731.0818752246664,2718.5784915863355,2706.1638785801524,2693.849906568998,2681.6474881507597,2669.56662606481,2657.616459272653,2645.805307273585,2634.140712713203,2622.629482339887,2611.27772636186,2600.090896255128,2589.0738210705163,2578.230742286106,2567.5653472496224,2557.080801253776,2546.7797782860953,2536.66449049351,2526.736716400746,2516.9978279205243,2507.4488161925733]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1213.png', dpi=300)
