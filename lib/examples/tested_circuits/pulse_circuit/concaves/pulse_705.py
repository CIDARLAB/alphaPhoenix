import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [622.4396731201457,623.8648457459474,625.2116676392328,626.4841979630886,627.6862954396263,628.8216277778569,629.8936806844692,630.9057664743366,631.8610322969995,632.7624679948093,633.6129136078692,634.4150665403739,635.1714884024228,635.8846115408824,636.5567452723717,637.1900818309648,637.7867020427407,638.34858073885,638.8775919183328,639.3755136714869,639.8440328741772,640.2847496630644,640.699181701352,641.08876824426,641.4548740130768,641.7987928862816,642.1217514158872,642.4249121768231,642.7093769568588,642.9761897942547,643.226339870036,643.4607642614915,643.6803505632237,643.8859393818126,644.0783267098918,644.2582661851933,644.4264712398757,644.5836171452216,644.7303429565701,644.8672533631362,644.9949204471676,645.1138853566902,645.2246598959094,645.3277280371493,645.4235473580432,645.5125504075191,645.5951460039656,645.6717204688114,645.7426387986043,645.8082457785359,645.8688670402233,645.9248100664304,645.9763651452879,646.0238062764548,646.0673920315486,646.1073663710656,646.1439594199087,646.1773882035404,646.2078573466868,646.2355597364242,646.2606771513996,646.2833808588455,646.3038321809798,646.3221830322999,646.338576429211,646.3531469733601,646.3660213099807,646.3773185624914,646.3871507445317,646.3956231505622,646.4028347261018,646.4088784186209,646.413841510064,646.4178059319245,646.4208485637507,646.4230415159224,646.424452397489,646.4251445698318,646.4251773868648,646.4246064224632,646.4234836857673,646.421857824983,646.4197743202661,646.4172756662499,646.4144015447481,646.4111889881389,646.4076725339091,646.4038843708163,646.3998544771016,646.3956107511657,646.3911791350992]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_705.png', dpi=300)
