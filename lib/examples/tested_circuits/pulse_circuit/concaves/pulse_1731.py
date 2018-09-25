import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [630.1716989542213,632.2738779024774,634.2643042709149,636.1485770557982,637.9320279953356,639.6197337148598,641.2165273525577,642.7270096861228,644.155559780005,645.5063451722581,646.7833316193215,647.990292416439,649.1308173107861,650.2083210237787,651.2260513984421,652.1870971871516,653.0943954944967,653.9507388894862,654.7587822007853,655.52104900817,656.2399378428914,656.9177281091654,657.5565857385432,658.1585685884675,658.7256315958872,659.2596316963832,659.7623325188542,660.235408865415,660.6804509857823,661.0989686550562,661.4923950634515,661.862090526186,662.2093460214106,662.5353865637393,662.8413744206318,663.1284121785884,663.3975456658236,663.6497667378153,663.8860159318575,664.1071849964893,664.3141193014262,664.5076201333836,664.6884468829533,664.8573191274745,665.0149186146303,665.1618911512967,665.2988484019774,665.426369600971,665.5450031822331,665.6552683307299,665.757656458908,665.8526326117485,665.940636803723,666.0220852908161,666.0973717806477,666.1668685835841,666.2309277076071,666.2898818995777,666.3440456354209,666.3937160616373,666.4391738904434,666.4806842507367,666.5184974969804,666.5528499780125,666.5839647676827,666.6120523591464,666.63731132455,666.6599289417677,666.6800817897725,666.6979363141505,666.7136493641977,666.7273687029718,666.7392334916093,666.7493747491532,666.7579157890833,666.7649726336808,666.7706544073104,666.7750637096466,666.7782969698287,666.780444782478,666.7815922264671,666.781819167293,666.7812005438589,666.7798066404374,666.777703344547,666.7749523914396,666.7716115958665,666.7677350717522,666.7633734403823,666.7585740276767,666.753381051095]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1731.png', dpi=300)
