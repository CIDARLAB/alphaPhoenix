import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [201.27339577895944,201.94666110920323,202.58550877802745,203.19096697262418,203.76410352126734,204.30601317138297,204.81780691718097,205.30060305204123,205.75551967932793,206.18366846137937,206.58614942314333,206.96404665651,207.31842479544844,207.65032615178364,207.9607684177632,208.25074285513992,208.52121290187563,208.77311313715765,209.0073485535417,209.2247940919475,209.42629440114092,209.61266378840742,209.78468633248426,209.94311613359025,210.08867767865547,210.22206630268798,210.34394872968048,210.45496367861006,210.55572252196163,210.6468099858472,210.72878488223154,210.8021808650351,210.86750720299057,210.92524956310083,210.97587079939893,211.01981174246012,211.057491985775,211.08931066567126,211.1156472319814,211.13686220709917,211.15329793146162,211.1652792938365,211.17311444509733,211.1770954944318,211.17749918716135,211.1745875635498,211.16860859815554,211.15979681943344,211.14837390942577,211.13454928349464,211.11852065014662,211.1004745510852,211.08058688169768,211.05902339224357,211.03594017006304,211.01148410316605,210.9857933255976,210.9589976450027,210.9312189528375,210.90257161768935,210.87316286218262,210.84309312395493,210.81245640119428,210.7813405832308,210.74982776667557,210.71799455759833,210.68591236023033,210.65364765267392,210.6212622500934,210.58881355585308,210.55635480105923,210.52393527295388,210.49160053259646,210.45939262225943,210.42735026295182,210.3955090424736,210.36390159439182,210.3325577683169,210.30150479184653,210.27076742453178,210.2403681042082,210.21032708602345,210.1806625744811,210.15139084880778,210.12252638194147,210.09408195342579,210.0660687564855,210.03849649954728,210.01137350245997,209.98470678765793,209.9585021665017]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_354.png', dpi=300)
