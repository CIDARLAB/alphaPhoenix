import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [624.0035852921282,625.4393725085192,626.7558558713112,627.9497085384107,629.0235040015239,629.9816102101173,630.8291221174519,631.5714329922052,632.2140251162949,632.7623555097184,633.2217891594582,633.5975584582685,633.8947381834339,634.1182302068586,634.2727545614308,634.3628447979996,634.3928463134615,634.366916776413,634.2890280542626,634.162969224322,633.992350369853,633.7806069426924,633.5310045302308,633.2466439044555,632.9304662596811,632.5852585668855,632.2136589884909,631.8181623095079,631.4011253502343,630.9647723329149,630.5112001804399,630.0423837296661,629.5601808455554,629.0663374252571,628.5624922836381,628.0501819137352,627.5308451172215,627.0058275013323,626.476385839829,625.9436922965269,625.4088385107168,624.8728395444906,624.3366376925495,623.8011061555701,623.2670525786076,622.7352224563729,622.2063024075073,621.6809233202355,621.1596633719754,620.643050925661,620.13156730567,619.6256494563621,619.1256924863202,618.6320521014538,618.1450469301735,617.6649607438688,617.1920445759489,616.7265187426976,616.2685747691916,615.8183772235097,615.3760654624346,614.9417552918093,614.5155405446714,614.0974945802379,613.6876717067599,613.286108531206,612.892825238675,612.5078268043716,612.1311041409075,611.7626351836267,611.4023859165763,611.0503113416761,610.7063563935608,610.3704568025016,610.042539907732,609.7225254234323,609.4103261595545,609.1058486995894,608.808994037311,608.5196581744578,608.2377326812393,607.9631052214867,607.6956600441977,607.4352784431582,607.1818391862562,606.935218916039,606.6952925230034,606.4619334930443,606.2350142304309,606.0144063576158,605.7999809931337]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_2225.png', dpi=300)
