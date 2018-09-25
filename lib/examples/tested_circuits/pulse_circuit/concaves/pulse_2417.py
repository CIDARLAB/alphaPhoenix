import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [199.12098477987544,199.72171513651128,200.2771997823211,200.78524585580408,201.24683287073177,201.6639649616,202.0390546488704,202.3746656858755,202.6733861760202,202.9377602459388,203.1702497380796,203.37321270786626,203.5488919177373,203.69940953422912,203.8267657823315,203.93284016377163,204.01939434223186,204.08807610105472,204.14042397054794,204.17787224721508,204.20175621130215,204.2133174067741,204.2137088882262,204.2040003679442,204.1851832169938,204.15817528924705,204.1238255482636,204.08291848504882,204.0361783206914,203.98427299229118,203.92781792382522,203.86737958595415,203.8034788504605,203.73659414619357,203.66716442419244,203.5955919401622,203.5222448627579,203.44745971623948,203.3715436660404,203.29477665567654,203.2174134032325,203.13968526542212,203.0618019769422,202.98395327253567,202.9063103988621,202.82902752294706,202.75224304365122,202.67608081227377,202.60065126807933,202.52605249422393,202.4523711992466,202.37968362899895,202.30805641359802,202.23754735371674,202.16820615026379,202.10007508125784,202.03318962946398,201.9675790641372,201.90326698000544,201.84027179642507,201.7786072194521,201.7182826693953,201.6593036762485,201.6016722452428,201.54538719461084,201.4904444675156,201.43683741996705,201.38455708642573,201.33359242467944,201.2839305414709,201.23555690025358,201.18845551235964,201.14260911277546,201.0979993216385,201.05460679249146,201.01241134825992,200.97139210585047,200.93152759020583,200.89279583859394,200.85517449585424,200.81864090127326,200.78317216771495,200.74874525358675,200.71533702818107,200.68292433089462,200.65148402479127,200.6209930449417,200.591428441942,200.5627674209845,200.53498737682764,200.50806592498688]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_2417.png', dpi=300)
