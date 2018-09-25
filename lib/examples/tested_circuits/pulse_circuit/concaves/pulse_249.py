import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [627.3464975390542,628.7328736269238,630.0352865467477,631.2582231224648,632.406014361163,633.4827811579574,634.4924382348432,635.4387021898818,636.3251002239255,637.1549788387616,637.9315123023147,638.65771081281,639.3364283401185,639.9703701407227,640.5620999506662,641.1140468644905,641.62851190989,642.107674328579,642.5535975741585,642.9682350377865,643.3534355123363,643.7109484055104,644.0424287121226,644.3494417554741,644.6334677074443,644.8959058966069,645.1380789133731,645.3612365208471,645.5665593797753,645.7551625956634,645.9280990958397,646.086362843951,646.2308918990905,646.3625713264836,646.4822359663829,646.5906730675654,646.6886247915664,646.7767905935394,646.8558294853924,646.9263621866164,646.9889731680013,647.044212593214,647.0925981630068,647.1346168666215,647.1707266447576,647.2013579682875,647.2269153367204,647.2477787002384,647.2643048089672,647.2768284929739,647.2856638763385,647.2911055284852,647.2934295558291,647.2928946366434,647.2897430019304,647.2842013649466,647.2764818019114,647.2667825863163,647.2552889791343,647.2421739771254,647.2275990213302,647.2117146677479,647.1946612220967,647.1765693404695,647.1575605976079,647.1377480244378,647.1172366164304,647.0961238142776,647.0744999582982,647.0524487179247,647.0300474975517,647.0073678199698,646.9844756885425,646.9614319292332,646.9382925135299,646.9151088632675,646.8919281382956,646.8687935078927,646.8457444067856,646.8228167765863,646.8000432934198,646.777453582479,646.7550744202024,646.7329299247364,646.7110417353139,646.6894291811429,646.6681094403739,646.647097689681,646.6264072449706,646.6060496936972,646.5860350192493]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_249.png', dpi=300)
