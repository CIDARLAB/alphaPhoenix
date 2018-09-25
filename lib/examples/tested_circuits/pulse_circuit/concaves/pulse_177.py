import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [682.0251584058062,683.754453460787,685.2916825161544,686.6378489482054,687.7995357463303,688.7844966642596,689.6008392517111,690.2567304354727,690.7602622528591,691.1193823739975,691.3418554420509,691.4352407413219,691.406879213633,691.2638861472433,691.0131474676673,690.6613184008386,690.2148237471572,689.6798592785433,689.0623939370304,688.3681726180675,687.6027193892867,686.7713410401462,685.8791308878804,684.9309727856614,683.9315452929873,682.8853259781009,681.796595829075,680.6694437549571,679.5077711616996,678.3152965899047,677.0955604030212,675.8519295157305,674.5876021530274,673.3056126310303,672.0088361509369,670.699993597827,669.3816563362461,668.0562509947147,666.7260642315061,665.3932474742574,664.0598216262016,662.7276817320707,661.3986015969887,660.074238351987,658.7561369600926,657.4457346572923,656.1443653230406,654.8532637753581,653.573569985961,652.3063332112583,651.0525160354613,649.8129983224469,648.5885810734247,647.3799901878483,646.1878801254024,645.0128374672744,643.8553843752832,642.7159819477928,641.5950334716716,640.4928875698804,639.4098412445734,638.3461428158836,637.3019947568272,636.2775564250135,635.2729466920731,634.2882464719299,633.3235011492353,632.3787229094564,631.4538929722711,630.5489637300598,629.6638607934117,628.7984849456708,627.9527140086426,627.1264046216615,626.3193939362885,625.5315012289608,624.7625294339608,624.0122665991015,623.2804872665494,622.5669537812176,621.8714175291665,621.1936201084427,620.5332944347809,619.8901657845704,619.2639527774644,618.6543683009868,618.0611203794479,617.4839129894497,616.9224468242156,616.3764200089344,615.8455287692616]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_177.png', dpi=300)
