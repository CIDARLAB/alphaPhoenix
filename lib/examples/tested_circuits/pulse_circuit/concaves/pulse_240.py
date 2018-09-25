import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [627.3464975390542,628.729157334649,630.0280326830832,631.2475993187408,632.3921798783616,633.4658876008831,634.4726300182522,635.4161169214752,636.2998690454986,637.1272267403281,637.9013584158463,638.6252686891054,639.301806210926,639.9336711675535,640.5234224613562,641.0734845783539,641.5861541521618,642.0636062347561,642.5079002847784,642.9209858841367,643.3047081935424,643.6608131574252,643.9909524684088,644.2966883012476,644.5794978258291,644.8407775085304,645.0818472109142,645.3039540944355,645.5082763395242,645.6959266871045,645.8679558103177,646.0253555239208,646.1690618385518,646.299957866774,646.4188765875418,646.5266034754719,646.6238790010427,646.7114010076065,646.7898269708522,646.8597761461293,646.9218316088188,646.9765421927218,647.0244243312218,647.0659638057836,647.1016174061506,647.1318145064153,647.1569585609591,647.1774285240833,647.1935801969829,647.2057475055543,647.2142437123767,647.2193625660518,647.2213793909481,647.2205521202559,647.2171222751286,647.2113158925587,647.2033444045127,647.1934054707415,647.1816837675578,647.1683517347778,647.1535702829143,647.1374894626133,647.1202490982326,647.1019793873686,647.082801468055,647.0628279552736,647.0421634483373,647.0209050106336,646.9991426231419,646.9769596130735,646.9544330589129,646.9316341730804,646.908628663377,646.8854770743104,646.8622351093537,646.8389539351318,646.8156804684832,646.7924576472972,646.7693246859828,646.7463173163808,646.7234680148922,646.7008062165563,646.678358516774,646.656148861338,646.6341987253988,646.6125272819605,646.5911515604731,646.5700865960578,646.5493455698742,646.5289399411139,646.5088795710764]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_240.png', dpi=300)
