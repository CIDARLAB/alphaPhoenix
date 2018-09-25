import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [203.36013275501313,203.95647856841526,204.5176962136115,205.04221077628964,205.5295345664429,205.97989221963476,206.39398763075638,206.77285022443297,207.1177291905781,207.43001847129415,207.71120237857053,207.96281555860747,208.18641322286513,208.38354889741373,208.5557577812012,208.70454435178928,208.83137322612023,208.93766253910775,209.02477928354793,209.09403618542572,209.14668978477283,209.18393946406522,209.2069272206176,209.21673802125767,209.21440061003636,209.200888665197,209.17712222176846,209.14396929220743,209.1022476304009,209.0527265947474,208.9961290744792,208.9331334502722,208.8643755658229,208.79045069169425,208.7119154665368,208.6292898039274,208.54305875565493,208.45367432442072,208.361557220687,208.26709855986292,208.17066149721907,208.07258279890897,207.97317434828435,207.8727245873534,207.77149989376477,207.66974589413374,207.56768871487026,207.46553617193948,207.3634789011959,207.26169143109016,207.16033319966328,207.05954951782357,206.9594724809512,206.8602218309024,206.76190577048916,206.66462173250173,206.56845710531428,206.47348991708125,206.37978948048675,206.28741699995896,206.1964261432066,206.1068635788735,206.01876948204696,205.93217800928977,205.84711774480172,205.76361211925104,205.6816798027506,205.6013350733895,205.5225881626664,205.4454455791104,205.36991041131174,205.29598261152805,205.22365926097308,205.15293481783985,205.08380134905659,205.01624874672268,204.950264930122,204.88583603416407,204.8229465850581,204.7615796639811,204.7017170594606,204.64333940915267,204.58642633165834,204.5309565489859,204.47690800023247,204.42425794702567,204.37298307123643,204.3230595654435,204.2744632166037,204.22716948335594,204.18115356736098]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1897.png', dpi=300)
