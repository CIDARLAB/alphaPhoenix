import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [624.0035852921282,732.7248699121124,836.0907456377981,934.1996504931047,1027.2069064298535,1115.2917347206876,1198.6434653270437,1277.4546664756706,1351.917354275726,1422.220768065312,1488.5500185296241,1551.0852562189818,1610.001166442324,1665.4666769329922,1717.6448083676926,1766.6926229307462,1812.7612412290637,1855.9959073198538,1896.5360877297605,1934.5155944153257,1970.062724392657,2003.3004107014524,2034.3463807455837,2063.313319047948,2090.3090321877394,2115.436614231378,2138.7946113767453,2160.477184840747,2180.5742712580513,2199.1717400426187,2216.351547306414,2232.1918860413316,2246.767332357909,2260.1489876433598,2272.4046165557943,2283.598780814388,2293.792968779076,2303.045720839932,2311.4127506572327,2318.9470623094107,2325.6990634186004,2331.716674332977,2337.045433452195,2341.728598787364,2345.8072458505853,2349.3203619713754,2352.3049371386014,2354.796051467029,2356.826959387418,2358.429170658413,2359.632528297392,2360.465283526036,2360.9541678247424,2361.1244621881992,2361.0000636724617,2360.603549321869,2359.9562375620217,2359.078247142905,2357.988553714123,2356.705044112044,2355.244568436545,2353.622989992938,2351.8552331725923,2349.955329343729,2347.9364608218725,2345.811002987491,2343.590564616447,2341.2860264870087,2338.907578325362,2336.4647541497616,2333.9664660717426,2331.4210366111042,2328.836229579713,2326.2192795875694,2323.5769202229853,2320.9154109571678,2318.2405628220095,2315.5577629083928,2312.8719977308606,2310.187875503116,2307.5096473673975,2304.8412276194454,2302.18621296943,2299.547900877929,2296.9293070047665,2294.3331818072766,2291.762026323355,2289.2181071734617,2286.7034708145843,2284.219957078035,2281.7692120218444]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_2213.png', dpi=300)
