import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [624.0035852921282,1492.308340971049,2319.9413174785072,3108.018884980127,3857.890423350805,4570.987515502854,5248.762022496274,5892.655972970262,6504.085501863382,7084.4318675454415,7635.03637329997,8157.197587583932,8652.16998381907,9121.163488030375,9565.34362149521,9985.832039423769,10383.707334935105,10760.006020110244,11115.723623279744,11451.815859834996,11769.199846159774,12068.755334810745,12351.325955101562,12617.720447569442,12868.713883949846,13105.048866602014,13327.436703051624,13536.558552609467,13733.066543000903,13917.58485568111,14090.710779074288,14253.015729403913,14405.046239107338,14547.324913075116,14680.3513531414,14804.603051389859,14920.536252940487,15028.586788954397,15129.170880642494,15222.685915094697,15309.511193763248,15390.008654439342,15464.523567559732,15533.385207670693,15596.90750086263,15655.389648970864,15709.116731317714,15758.360284748744,15803.378862692816,15844.418573951496,15881.713601899277,15915.486704752027,15945.949697537402,15973.303916377876,15997.740665674666,16019.441648759284,16038.57938255871,16055.317596800463,16069.811618265006,16082.20874057499,16092.64857999389,16101.263417690485,16108.178528910394,16113.512499481436,16117.37753006599,16119.8797285605,16121.119391030099,16121.191271554615,16120.184841351185,16118.184537528114,16115.270001814584,16111.516309601173,16106.994189616846,16101.770234559244,16095.907102986524,16089.463712770645,16082.495426404064,16075.05422844396,16067.188895370487,16058.945158128245,16050.365857612831,16041.491093357361,16032.358365666823,16023.00271144142,16013.456833923305,16003.751226594584,15993.914291448058,15983.972451845697,15973.950260173742,15963.870500497065,15953.754286409454]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_2214.png', dpi=300)
