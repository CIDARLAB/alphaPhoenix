import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [234.58778464900956,1849.3580458867855,3191.368820450169,4301.918557484052,5216.463934889789,5964.967605524316,6572.705813482521,7060.996632215388,7447.825657289453,7748.3777903394985,7975.48763477791,8140.02045611828,8251.19422455621,8316.851741897752,8343.690458181258,8337.456350550976,8303.107168828186,8244.949437693173,8166.752832133132,8071.8449004693075,7963.188589835464,7843.444625883495,7715.020505712534,7580.107672948707,7440.708347550363,7298.653466367728,7155.613233913101,7013.101859175663,6872.478128726724,6734.943499611486,6601.539350506993,6473.144880234349,6350.476882211338,6234.092268813448,6124.393809458806,6021.639132085843,5925.9526716594555,5837.339972167159,5755.703581938304,5680.859726964515,5612.554987059076,5550.482308707171,5494.295836846555,5443.6242088686395,5398.082107321684,5357.279999908007,5320.832099898733,5288.3626557276875,5259.51072795589,5233.933637871427,5211.309281719266,5191.337500799677,5173.740685456159,5158.263773736978,5144.673785740752,5132.759014282907,5122.327972829511,5113.2081834353185,5105.2448711279185,5098.299616973552,5092.249009941055,5086.983327535795,5082.405266828897,5078.428740752067,5074.977749150379,5071.985329877309,5069.392591985817,5067.147830644216,5065.205721635008,5063.52659204922,5062.0757629584705,5060.8229593405795,5059.741782276761,5058.809238367873,5058.005321384514,5057.312641331397,5056.716096339544,5056.202583076047,5055.760741662058,5055.380731401155,5055.054033931984,5054.773280723553,5054.532102123542,5054.32499544605,5054.147209843166,5053.994645943636,5053.863768461282,5053.751530176046,5053.655305872155,5053.572834981909,5053.502171831045]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/630/out0.png', dpi=300)
