import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [633.9804202411561,635.7694632070105,637.4679355835757,639.0801093325028,640.6100664452723,642.0617068992194,643.4387563047685,644.7447732543918,645.9831563835685,647.1571511537702,648.2698563672474,649.3242304231287,650.3230973240892,651.2691524425794,652.1649680553504,653.0129986547482,653.8155860449974,654.5749642314387,655.2932641104383,655.9725179674379,656.6146637903785,657.2215494054891,657.7949364422057,658.3365041337563,658.8478529597303,659.3305081367314,659.7859229630088,660.2154820227513,660.6205042555351,661.0022458962221,661.3619032904136,661.7006155903874,662.0194673362682,662.3194909270046,662.601668985567,662.8669366226143,663.1161836027217,663.3502564171127,663.5699602666892,663.7760609590125,663.9692867227523,664.1503299429866,664.3198488206073,664.478468958961,664.6267848807397,664.7653614780127,664.894735398186,665.0154163685642,665.1278884620858,665.2326113067044,665.330021240787,665.4205324168149,665.5045378555714,665.582410452926,665.6545039412296,665.7211538072628,665.7826781685976,665.8393786101581,665.8915409826946,665.9394361648166,665.9833207901605,666.0234379412075,666.0600178112006,666.0932783355548,666.123425794093,666.1506553853885,666.1751517744389,666.1970896148468,666.2166340466351,666.2339411707744,666.2491585014557,666.2624253971021,666.2738734710638,666.2836269829082,666.2918032111738,666.2985128084196,666.3038601393699,666.3079436029163,666.3108559387067,666.3126845190228,666.3135116266126,666.3134147191198,666.312466680722,666.3107360615627,666.3082873055384,666.3051809669765,666.3014739167155,666.2972195380789,666.2924679132094,666.2872660002128,666.2816578015381]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_2048.png', dpi=300)
