import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [641.5134645558595,766.5445273697393,885.4721961604866,998.3733185370814,1105.4061355132762,1206.7637671104021,1302.655111349176,1393.2954026136758,1478.9010112075396,1559.686379676772,1635.862142919135,1707.6339513157113,1775.2017337293607,1838.759247039876,1898.493818208145,1954.5862188078017,2007.2106323153157,2056.5346871422566,2102.719536580036,2145.919972262947,2186.2845614564117,2223.9558010537635,2259.0702829935417,2291.7588671297162,2322.146858555289,2350.3541870990366,2376.495587256039,2400.6807772236803,2423.014636030034,2443.597377985263,2462.52472387637,2479.8880684741985,2495.774644038429,2510.2676795985894,2523.4465558621364,2535.3869556585637,2546.161009874412,2555.8374388703955,2564.481689400603,2572.1560670762838,2578.9198644343815,2584.8294846845843,2589.9385612190817,2594.2980729769542,2597.956455760788,2600.959709607007,2603.3515023139403,2605.1732692330447,2606.464309429187,2607.261878315672,2607.601276868905,2607.515937526312,2607.0375068695776,2606.1959251933763,2605.019503057739,2603.534994919989,2601.7676699399167,2599.741380049504,2597.478625376147,2595.0006171059517,2592.327337871319,2589.4775997447105,2586.469099918183,2583.3184741460454,2580.0413480258076,2576.652386190438,2573.1653394828886,2569.593090181812,2565.9476953454455,2562.2404283387327,2558.4818186069074,2554.6816897569647,2550.849196006712,2546.9928570593997,2543.1205914602747,2539.2397484898156,2535.357138646837,2531.4790627731336,2527.6113398698526,2523.759333654333,2519.9279779047447,2516.121800638464,2512.344947168778,2508.601202083197,2504.894010185327,2501.226496441022,2497.601484968272,2494.0215171090567,2490.488868620227,2487.0055660192743,2483.573402119731]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_549.png', dpi=300)
