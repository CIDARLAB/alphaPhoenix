import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [204.9380114401431,205.65453003925214,206.33557914696843,206.98278175453004,207.59769213445495,208.18179844093038,208.7365252344357,209.26323593072044,209.76323517452164,210.23777113863162,210.6880377491238,211.1151768377121,211.52028022236422,211.90439171740994,212.26850907448875,212.61358585576392,212.94053324090055,213.25022176935823,213.5434830195927,213.82111122679154,214.0838648407902,214.33246802582892,214.56761210381526,214.78995694275656,215.0001322920195,215.19873906606176,215.38635057826477,215.5635137264755,215.7307501318429,215.88855723250737,216.03740933367337,216.17775861556555,216.31003610073523,216.4346525821519,216.5519995134796,216.6624498629028,216.76635893183067,216.86406513977303,216.95589077664428,217.0421427237162,217.12311314440356,217.19908014603092,217.2703084136938,217.33704981729082,217.39954399277016,217.45801889859882,217.512691348429,217.56376752090404,217.611443447513,217.6559054793721,217.69733073378003,217.7358875213639,217.77173575460358,217.80502733849363,217.83590654407337,217.86451036553,217.89096886155235,217.9154054815877,217.93793737762996,217.9586757021424,217.97772589269618,217.9951879438833,218.01115666703973,218.02572193829525,218.0389689354452,218.0509783641202,218.06182667371024,218.07158626348314,218.08032567931753,218.08810980145526,218.0950000236611,218.10105442416176,218.10632792872175,218.1108724661983,218.11473711690348,218.11796825408916,218.12060967885603,218.12270274877645,218.12428650050836,218.12539776666588,218.12607128720146,218.12633981554336,218.12623421972236,218.12578357871115,218.1250152741909,218.12395507795048,218.12262723511424,218.121054543387,218.1192584284958,218.11725901600127,218.1150751996427]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1370.png', dpi=300)
