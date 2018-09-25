import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [393.74882749495697,394.98800079825907,396.16830270423117,397.2909610488439,398.3574531573084,399.3694332235347,400.32867754051017,401.2370425746567,402.0964325281787,402.9087740624559,403.6759965222686,404.4000164470991,405.0827254634073,405.72598086916537,406.33159837897773,406.9013466137904,407.4369430058985,407.94005085596166,408.4122773296691,408.85517222149593,409.2702273444266,409.65887642960524,410.0224954400565,410.36240321899504,410.6798624066207,410.9760805702885,411.25221150203464,411.5093566449886,411.74856661649824,411.9708428010727,412.17713899067576,412.36836305363744,412.54537861660515,412.7090067466219,412.8600276226815,412.9991821880287,413.12717377609937,413.2446697043822,413.3523028316562,413.450673075056,413.5403488842653,413.621868670853,413.6957421913746,413.762451883373,413.8224541538406,413.8761806200685,413.9240393031039,413.96641577429114,414.00367425557124,414.03615867438515,414.0641936741576,414.08808558144636,414.10812333092247,414.1245793494106,414.1377104002614,414.14775838936004,414.15495113408895,414.159503096574,414.16161608253634,414.1614799070668,414.1592730286203,414.15516315251097,414.1493078051592,414.14185488031774,414.13294315846997,414.1227028005623,414.1112558171978,414.0987165143827,414.08519191688055,414.07078217019335,414.05558092215244,414.0396756850635,414.0231481793166,414.0060746593338,413.9885262226945,413.970569103241,413.9522649489373,413.9336710852162,413.9148407645242,413.8958234027361,413.87666480308803,413.85740736824266,413.8380903010767,413.81874979475236,413.7994192126084,413.7801292583819,413.7609081372478,413.7417817081404,413.7227736277997,413.703905486962,413.68519693909775]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_2337.png', dpi=300)
