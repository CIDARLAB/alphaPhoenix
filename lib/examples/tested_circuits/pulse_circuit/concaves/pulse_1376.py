import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [204.9380114401431,205.55446248369418,206.13983918387393,206.69542805752513,207.22247734061114,207.7221968559995,208.1957580466492,208.6442941528735,209.06890051459533,209.47063498152284,209.85051841598113,210.20953527475615,210.54863425776702,210.86872901269302,211.17069888586164,211.45538971076712,211.72361462654277,211.97615491957293,212.21376088220464,212.43715268321628,212.64702124532997,212.8440291256182,213.02881139516566,213.20197651480322,213.36410720414327,213.51576130151517,213.65747261273108,213.78975174691055,213.9130869378585,214.02794484973163,214.13477136593983,214.2339923604227,214.32601445060922,214.4112257315223,214.48999649062313,214.56267990311107,214.6296127075003,214.69111586138712,214.74749517740548,214.79904193943932,214.84603349922347,214.88873385352028,214.9273942021055,214.96225348683916,214.9935389121304,215.02146644713554,215.04624131005377,215.06805843490372,215.08710292118212,215.103550466818,215.1175677848462,215.1293130042307,215.1389360552745,215.14657904005358,215.1523765883149,215.15645619927693,215.15893856976922,215.159937909143,215.1595622413822,215.15791369483622,215.15508877999176,215.15117865569158,215.1462693842027,215.14044217552643,215.1337736213353,215.12633591891245,215.11819708546,215.10942116313402,215.1000684151533,215.09019551332045,215.0798557172841,215.06909904586075,215.05797244072616,215.0465199227758,215.03478274144487,215.02279951726948,215.0106063779603,214.9982370882519,214.98572317378196,214.97309403924515,214.96037708105874,214.94759779476826,214.93477987741335,214.92194532506593,214.90911452574545,214.8963063479079,214.88353822469796,214.87082623414724,214.8581851754937,214.84562864179088,214.8331690889694]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1376.png', dpi=300)
