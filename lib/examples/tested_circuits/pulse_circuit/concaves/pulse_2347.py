import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [393.74882749495697,397.1902456506271,400.4450603300645,403.5086648027323,406.38330487079315,409.07420970995554,411.58808338450405,413.9323724581609,416.114863825873,418.1434464550262,420.0259633748781,421.77011719155234,423.3834091457728,424.87310008612087,426.246186221945,427.5093850838556,428.6691286574425,429.73156161692646,430.7025432075078,431.5876517400334,432.39219094551913,433.1211976357713,433.77945025815046,434.37147803545776,434.90157045775146,435.3737869494939,435.79196657813316,436.1597377027646,436.4805274865104,436.75757121559167,436.9939213831003,437.192456507226,437.3558896628926,437.4867767129848,437.5875242310141,437.6603971115257,437.7075258680269,437.7309136209278,437.73244278008144,437.71388142810923,437.6768894119075,437.6230241506168,437.5537461689696,437.4704243653606,437.37434102424527,437.2666965826045,437.14861416023865,437.02114386359614,436.8852668727196,436.7418993207168,436.5918959749495,436.4360537288917,436.27511491333996,436.10977043538173,435.94066275323206,435.7683886947577,435.5935021272034,435.41651648534156,435.2379071649653,435.0581137883589,434.87754234808966,434.6965672351874,434.51553315750834,434.3347569538154,434.15452930885124,433.9751163744392,433.7967613014054,433.6196856868926,433.444090941414,433.27015957979023,433.09805643990654,432.9279298330412,432.75991262932786,432.5941232817415,432.4306667918296,432.26963562025077,432.11111054502885,431.9551614702884,431.80184818809477,431.65122109589487,431.50332187192373,431.35818411082755,431.2158339216358,431.07629049010933,430.9395666073873,430.80566916675724,430.6745996302804,430.54635446691515,430.42092556369676,430.29830061145185,430.17846346645047]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_2347.png', dpi=300)
