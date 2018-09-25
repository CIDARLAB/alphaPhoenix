import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [203.36013275501313,204.0489099557633,204.70246580143657,205.3220419985287,205.90887830896995,206.4642067302746,206.98924658288982,207.4852003858427,207.9532504181515,208.39455587725323,208.81025055738343,209.20144098080002,209.56920492327038,209.914590282576,210.23861424512435,210.54226271125086,210.82648994457128,211.0922184149113,211.340338807986,211.57171017819758,211.78716022372632,211.98748566556293,212.17345271430813,212.34579761048997,212.5052272258514,212.65241971456496,212.78802520466704,212.9126665211867,213.02693993349288,213.13141592031658,213.22663994673152,213.3131332481114,213.39139361673526,213.46189618729196,213.5250942180502,213.5814198649174,213.6312849460168,213.6750816947729,213.7131834998131,213.74594563027713,213.7737059453758,213.79678558726107,213.81548965646456,213.83010786933394,213.84091519704637,213.8481724859122,213.85212705879633,213.8530132975874,213.8510532067316,213.84645695792466,213.8394234161213,213.8301406470774,213.8187864066885,213.80552861242816,213.7905257972246,213.77392754614144,213.75587491625166,213.7365008401119,213.7159305132592,213.69428176616222,213.67166542106693,213.64818563418189,213.6239402236497,213.59902098375275,213.57351398579837,213.547499866127,213.52105410168068,213.49424727356515,213.46714531903095,213.43980977229182,213.41229799459092,213.3846633939154,213.356955634752,213.32922083826588,213.30150177327454,213.27383803838,213.24626623561048,213.21882013591366,213.19153083683287,213.16442691268637,213.13753455756122,213.11087772142128,213.08447823961993,213.05835595609773,213.03252884053504,213.00701309972072,212.98182328338808,212.95697238475992,212.93247193603594,212.9083320990468,212.88456175128997]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1890.png', dpi=300)
