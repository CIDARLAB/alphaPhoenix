import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [396.07523462490303,397.4132387195754,398.66885659559335,399.8401855554425,400.92934073359356,401.93977389312033,402.8754642733846,403.7405740358217,404.53927643927375,405.2756628245455,405.95369042132177,406.57715318141504,407.1496663889424,407.6746598556567,408.15537660969716,408.59487514973057,408.9960340178149,409.3615578616927,409.693984422669,409.9956920594517,410.26890753574503,410.5157138802862,410.73805818480514,410.93775924586276,411.1165149857352,411.2759096087939,411.4174204654522,411.5424246072579,411.6522050252111,411.74795656965,411.8307915546402,411.90174505314116,411.9617798916041,412.011791354318,412.0526116089242,412.08501386521067,412.1097162796578,412.1273856183347,412.1386406906795,412.14405556650127,412.1441625882386,412.1394551901395,412.13039053560107,412.1173919834518,412.10085139347876,412.08113128101706,412.0585668299248,412.03346777278614,412.00612014670185,411.9767879325678,411.94571458528657,411.9131244619285,411.8792241544391,411.844203733094,411.80823790652494,411.7714871037784,411.7340984835296,411.6962068752508,411.65793565682856,411.6193975728364,411.58069549740026,411.541923145337,411.5031657350095,411.4645006061145,411.42599779541064,411.38772057319443,411.34972594314917,411.3120651080145,411.27478390336677,411.2379232016433,411.201519288407,411.1656042127088,411.1302061132883,411.0953495222307,411.06105564759383,411.0273426364166,410.994225819426,410.96171793867177,410.92982935923493,410.8985682660814,410.8679408470579,410.83795146296336,410.80860280556345,410.77989604436135,410.75183096288055,410.7244060851668,410.6976187931684,410.67146543561006,410.64594142893463,410.6210413508493,410.59675902697523]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_306.png', dpi=300)
