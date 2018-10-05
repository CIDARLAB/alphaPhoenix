import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [234.22818983610765,750.4955758900593,1158.526449977813,1476.226896520298,1718.7833265470729,1899.0038564792962,2027.6814021010928,2113.9047523474333,2165.3222062968684,2188.3645473019687,2188.4335788449634,2170.061595792989,2137.046350964678,2092.565358665571,2039.2727572967667,1979.381423690355,1914.7325826273022,1846.8547777138137,1777.0137577968599,1706.2545805898749,1635.4370359244974,1565.2653372842553,1496.3129123332565,1429.043030050881,1363.8259230637213,1300.9529901502601,1240.6485905424913,1183.0798674224686,1128.3649650862967,1076.5799365977375,1027.7645804372073,981.9273982679899,939.0498320626835,899.089915829745,861.9854616278701,827.6568875027266,796.0097825930385,766.9372893109149,740.3223632932595,716.0399496295553,693.9590909566517,673.9449622405067,655.8608110451854,639.5697724810856,624.9365252207314,611.8287581756055,600.1184250247479,589.6827738221784,580.4051495335945,572.1755771052525,564.8911406094319,558.456179705089,552.7823280673684,547.7884198127031,543.4002896944852,539.5504913147655,536.1779553470145,533.227607025552,530.6499592536984,528.4006948321223,526.4402486338921,524.7333981401034,523.2488686402826,521.9589576051907,520.8391812457387,519.8679450573054,519.02623918377,518.2973586872848,517.6666482455377,517.1212703871597,516.6499960900471,516.2430163819919,515.8917734768971,515.5888099349955,515.3276343369373,515.1026019971272,514.9088093013603,514.7420003297808,514.5984845122841,514.4750641549433,514.3689707692629,514.2778092283762,514.1995088637927,514.1322807016455,514.0745801177233,514.0250742653965,513.9826136996343,513.9462076836162,513.915002722107,513.8882639179808,513.8653587953528]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/125/out0.png', dpi=300)
