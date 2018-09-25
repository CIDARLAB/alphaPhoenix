import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [641.5134645558595,643.1929241521484,644.7235018181713,646.1012002193191,647.329548901802,648.41410695701,649.3612149638873,650.1775166162386,650.869731189284,651.4445313559562,651.9084725619375,652.2679504040101,652.5291744136393,652.6981520206207,652.780679122733,652.7823350966459,652.7084808800578,652.5642592255755,652.3545965170416,652.0842057243069,651.7575901943458,651.3790480589225,650.9526770958926,650.4823799213227,649.9718694184323,649.4246743303953,648.8441449596669,648.2334589282516,647.5956269623027,646.9334986713885,646.2497682981947,645.5469804187485,644.8275355767138,644.0936958381196,643.3475902552019,642.5912202299575,641.8264647696305,641.0550856277209,640.278732325279,639.4989470482739,638.7171694176976,637.9347411298554,637.1529104649655,636.3728366628101,635.5955941647081,634.8221767215719,634.0535013682302,633.2904122645938,632.533684404576,631.7840271939939,631.0420878989441,630.3084549663956,629.5836612189528,628.8681869259357,628.1624627530864,627.4668725933604,626.7817562813782,626.1074121942236,625.4440997413618,624.7920417465208,624.151426724436,623.5224110554059,622.9051210606312,622.299654981334,621.7060848646595,621.1244583593643,620.5548004242812,619.9971149525367,619.4513863144714,618.9175808221788,618.3956481185452,617.8855224936256,617.387124131149,616.9003602878885,616.425126408582,615.961307179029,615.5087775199283,615.0674035239604,614.6370433385504,614.2175479966869,613.8087621981005,613.410525043039,613.0226707208111,612.6450291551982,612.2774266087694,611.9196862480612,611.5716286715249,611.2330724020659,610.9038343459446,610.5837302197339,610.2725749469705]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_568.png', dpi=300)
