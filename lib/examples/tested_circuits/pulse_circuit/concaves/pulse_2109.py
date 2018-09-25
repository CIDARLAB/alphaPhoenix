import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [633.9804202411561,755.1853472381739,869.9576334346742,978.3950462056305,1080.7163520391189,1177.168070158552,1268.003277357661,1353.4736548565133,1433.8258648581088,1509.2997433750663,1580.1273767469256,1646.5326525098694,1708.731082800106,1766.9297919549194,1821.3276062035513,1872.1152079649967,1919.4753311825223,1963.582982376125,2004.6056771903325,2042.7036854719997,2078.030280056413,2110.7319858866917,2140.9488270890974,2168.8145703277864,2194.4569632628595,2217.9979672976474,2239.553984066123,2259.236075307043,2277.150175916988,2293.397300083266,2308.0737404791776,2321.271260565189,2333.07728008517,2343.575053880591,2352.8438441703,2360.959086461136,2367.9925492668494,2374.0124878207635,2379.083791972208,2383.2681284587775,2386.6240777464354,2389.207265627882,2391.0704897667424,2392.2638413713653,2392.8348221775063,2392.828456914171,2392.2874014214776,2391.252046583784,2389.760618235513,2387.8492731912847,2385.552191546095,2382.901665385504,2379.92818404011,2376.6605160130252,2373.1257877036937,2369.3495590461766,2365.3558961750414,2361.1674412271846,2356.805479383366,2352.2900032488533,2347.6397746684625,2342.872384067364,2338.0043074053106,2333.0509608284774,2328.0267530997885,2322.9451358855235,2317.8186519730775,2312.6589814920026,2307.476986207883,2302.282751956165,2297.0856292807784,2291.8942723402197,2286.716676141744,2281.560212162376,2276.431662413627,2271.337252005062,2266.282680260227,2261.273150436839,2256.3133981016576,2251.4077182089914,2246.559990930392,2241.773706281751,2237.0519875926975,2232.3976138619296,2227.813041040888,2223.300422286962,2218.8616272262575,2214.498260264798,2210.21167798592,2206.003005670494,2201.8731529755482]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_2109.png', dpi=300)
