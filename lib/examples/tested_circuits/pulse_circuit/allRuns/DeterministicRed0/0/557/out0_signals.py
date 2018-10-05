import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [232.50899787382718,693.4330162382763,1078.4274300531874,1397.6435200754897,1660.4713454708806,1875.3296379995902,2049.634844364634,2189.8477943621065,2301.552556261555,2389.5459708430317,2457.9274567178454,2510.184136980473,2549.269119008504,2577.6721990022115,2597.483002129577,2610.4469338448002,2618.0144807885517,2621.3844548433217,2621.5417734973385,2619.2903424011447,2615.2815673928253,2610.0389813192164,2603.979429488653,2597.431218031783,2590.649592790609,2583.829880930292,2577.11859581273,2570.62277494392,2564.4177923783727,2558.5538604036274,2553.0614106248954,2547.9555217520738,2543.239540467503,2538.9080227147288,2534.949105563591,2531.346404416767,2528.080516645151,2525.130200672286,2522.4732889557004,2520.0873841108305,2517.9503794632506,2516.0408384696048,2514.338261593457,2512.823264242085,2511.4776851549027,2510.284641083685,2509.2285406283927,2508.2950676090245,2507.471142291576,2506.74486708156,2506.105461896374,2505.5431932802735,2505.0493003914526,2504.615920234513,2504.2360139032075,2503.903295112236,2503.6121619114674,2503.3576321731007,2503.1352832067278,2502.9411956764043,2502.771901857035,2502.6243381660315,2502.4958018329935,2502.3839115193196,2502.286571666235,2502.2019403299173,2502.1284002529305,2502.064532919574,2502.009095346904,2501.9609993715076,2501.9192932033034,2501.883145030689,2501.8518284755983,2501.8247097113044,2501.801236071187,2501.7809259905794,2501.763360137851,2501.7481736042073,2501.7350490341464,2501.723710590171,2501.7139186561303,2501.7054651935227,2501.698169674176,2501.6918755210168,2501.686446996156,2501.6817664823125,2501.6777321097256,2501.674255686201,2501.6712608928556,2501.668681712536,2501.666461061797]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/557/out0.png', dpi=300)
