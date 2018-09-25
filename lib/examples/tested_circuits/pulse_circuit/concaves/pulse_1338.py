import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [400.0831167604083,401.46886695711737,402.7576373869261,403.9471297112455,405.0405225257069,406.04255103616356,406.9584859895165,407.79372609597596,408.55360433063925,409.2432880544605,409.8677263433622,410.4316232973803,410.93942652919316,411.3953248735823,411.80325182222253,412.1668925340141,412.4896930499059,412.7748708133108,413.0254258947397,413.2441525128618,413.4336505735509,413.5963370368306,413.7344569830712,413.85009429313885,413.94518188810923,414.0215114963727,414.080742931992,414.12441287975133,414.15394319066576,414.1706486976627,414.1757445653162,414.17035319036523,414.15551067160504,414.13217286886254,414.101221071328,414.0634672956649,414.019659234161,413.9704848727974,413.91657679856525,413.85851621469567,413.7968366817277,413.7320276015494,413.66453746073,413.59477684863356,413.5231212649811,413.4499137307136,413.3754672152176,413.3000668922051,413.2239722357989,413.14741896766515,413.07062086535706,412.99377144138816,412.91704550194214,412.84060059354636,412.7645783454895,412.68910571524833,412.61429614370275,412.5402506264624,412.4670587072004,412.39479939848763,412.32354203524613,412.253347065587,412.18426678347174,412.1163460073249,412.0496227084435,411.98412859277676,411.91988963940236,411.85692659879214,411.7952554537426,411.73488784564313,411.67583146856856,411.6180904335041,411.561665604851,411.5065549112071,411.4527536322763,411.40025466362897,411.3490487609143,411.2991247650105,411.2504698094948,411.20306951171546,411.1569081486585,411.1119688187155,411.0682335903809,411.02568363883466,410.9842993712963,410.94406054197486,410.9049463573805,410.86693557270684,410.83000657994654,410.79413748835094,410.7593061978038]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1338.png', dpi=300)
