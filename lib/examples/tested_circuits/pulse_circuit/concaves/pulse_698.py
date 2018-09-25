import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [643.9090983267836,645.2661530041892,646.4870152345277,647.5692577121016,648.5166091073763,649.3344844536756,650.0289348725162,650.6062409764079,651.0727187241512,651.4346151651338,651.6980483997731,651.8689716438372,651.9531514534215,651.9561547547964,651.8833415951487,651.739861741276,651.5306539379023,651.26044704351,650.9337625126304,650.5549178541552,650.1280308011007,649.657023998903,649.1456300689227,648.5973969389137,648.0156933574616,647.4037145279108,646.7644878110696,646.1008784563966,645.415595329347,644.7111966087518,643.9900954329777,643.2545654774999,642.5067464496499,641.7486494888565,640.9821624628012,640.2090551516753,639.4309843141901,638.649498630266,637.8660435163866,637.0819658105473,636.2985183245245,635.5168642619057,634.7380815009348,633.9631667417758,633.1930395182773,632.4285460747476,631.6704631086286,630.9195013802871,630.1763091914381,629.441475733977,628.7155343112202,627.9989654337564,627.2921997922854,626.5956211099627,625.9095688769069,625.2343409696211,624.5701961581796,623.9173565040917,623.2760096518235,622.6463110169846,622.0283858742305,621.4223313479383,620.8282183087247,620.2460931788677,619.6759796496843,619.1178803138938,618.5717782159678,618.037638323435,617.5154089220673,617.0050229378295,616.5063991884233,616.0194435672033,615.5440501621854,615.0801023128079,614.6274736070418,614.1860288213828,613.7556248061916,613.33611131878,612.9273318065731,612.5291241426071,612.1413213155546,611.7637520763968,611.3962415437956,611.0386117701458,610.6906822702229,610.3522705142676,610.0231923872905,609.7032626163028,609.3922951671238,609.0901036123436,608.7965014719636]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_698.png', dpi=300)
