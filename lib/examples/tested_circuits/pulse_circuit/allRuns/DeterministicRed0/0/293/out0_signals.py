import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [226.65793011164607,568.6957213904311,845.7217964977781,1065.395770313197,1236.2615273186734,1366.1678887646412,1462.0085042761523,1529.7329989554507,1574.4309149109606,1600.4325356014615,1611.4078112395036,1610.4568291219662,1600.189901366432,1582.797236411522,1560.1089505689943,1533.6464823237834,1504.666551683092,1474.198773832624,1443.0779445826852,1411.9718925317106,1381.405656536416,1351.7826091219315,1323.4030164375065,1296.4804105565122,1271.156055109491,1247.5117124612527,1225.580869320391,1205.3585451363563,1186.8097899254667,1169.8769708400441,1154.4859456719914,1140.5512231094292,1127.9802114354895,1116.6766579147588,1106.5433796040281,1097.4843826213198,1089.4064613019743,1082.2203613312893,1075.8415830530892,1070.1908924195218,1065.194598456057,1060.7846477909968,1056.8985790120412,1053.4793725130876,1050.4752251594075,1047.8392735496068,1045.5292848689812,1043.5073302646695,1041.7394522659029,1040.1953349520736,1038.8479832646926,1037.6734159958885,1036.6503754997225,1035.7600560030637,1034.9858514864725,1034.3131234156729,1033.728988090372,1033.2221230051375,1032.7825913578815,1032.4016836712262,1032.071775390817,1031.7861992763155,1031.5391313922128,1031.3254895262285,1031.1408429045173,1030.9813321285637,1030.8435983234133,1030.7247205567433,1030.6221606602878,1030.53371465702,1030.4574700676835,1030.3917684376029,1030.3351724884415,1030.2864373592527,1030.2444854565372,1030.2083844840113,1030.177328269441,1030.150620048348,1030.127657902834,1030.1079220884317,1030.0909640130265,1030.0763966598054,1030.0638862709925,1030.0531451312866,1030.0439253098928,1030.0360132370115,1030.0292250063578,1030.0234023088306,1030.0184089144414,1030.0141276301588,1030.0104576706021]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/293/out0.png', dpi=300)
