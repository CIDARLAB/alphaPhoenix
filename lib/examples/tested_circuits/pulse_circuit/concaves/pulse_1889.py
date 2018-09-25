import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [203.36013275501313,203.9572832459774,204.5234513530573,205.0597100497286,205.56713524501185,206.04680001937794,206.49976973418163,206.92709790151574,207.32982271677835,207.70896416924143,208.06552165694745,208.40047204168252,208.71476808785943,209.00933723610973,209.28508066841098,209.54287262680737,209.78355995233935,210.00796181477884,210.2168696072528,210.41104698289797,210.59123001338162,210.75812745149466,210.91242108211713,211.05476614770686,211.18579183609876,211.3061018198543,211.41627483768593,211.5168653096238,211.60840397860525,211.691398572069,211.76633447793532,211.83367543006625,211.89386419893125,211.9473232837674,211.9944556030216,212.03564518030836,212.0712578235091,212.10164179499156,212.1271284712368,212.14803299043805,212.16465488687885,212.17727871111478,212.18617463517262,212.19159904214956,212.19379509974345,212.1929933173746,212.18941208667397,212.1832582052124,212.1747273834322,212.16400473481787,212.15126524940843,212.1366742508093,212.12038783691042,212.10255330455715,212.0833095584563,212.06278750462675,212.04111042872924,212.0183943596279,211.99474841855198,211.97027515423756,211.9450708644378,211.9192259041962,211.89282498128034,211.86594743917632,211.83866752804167,211.81105466401456,211.78317367727257,211.75508504923027,211.7268451392591,211.69850640130736,211.67011759079,211.64172396211146,211.61336745717583,211.5850868852306,211.5569180943811,211.52889413510454,211.50104541608187,211.47339985265822,211.4459830082321,211.41881822886418,211.39192677138865,211.36532792529832,211.3390391286684,211.31307607837243,211.28745283483633,211.26218192156745,211.23727441968646,211.2127400576826,211.18858729660317,211.1648234108813,211.1414545649978]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1889.png', dpi=300)
