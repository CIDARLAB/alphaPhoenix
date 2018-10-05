import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [232.06823074815145,2125.7916049968676,3720.5906425156913,5063.64724410716,6194.696694283539,7147.202860010482,7949.347937814911,8624.86601285637,9193.745072960468,9672.818237074647,10076.261679492374,10416.013973524128,10702.129255038091,10943.074649588372,11145.980758870191,11316.852614286232,11460.747336453549,11581.923754974689,11683.96841336148,11769.901686960968,11842.267151467486,11903.206845489358,11954.524653998054,11997.739687278488,12034.131234388175,12064.776620935772,12090.583091117867,12112.314657200885,12130.614710776716,12146.025064754242,12159.001989467297,12169.92971736071,12179.131815830267,12186.880764725373,12193.40602190946,12198.900815542389,12203.527864077867,12207.424193243538,12210.705192553169,12213.468031399243,12215.794535824707,12217.753611113902,12219.403281903076,12220.792410192453,12221.962142110104,12222.94712625056,12223.776539651017,12224.474951774895,12225.063052078172,12225.55826269633,12225.975254389556,12226.326381020395,12226.622045426648,12226.871007521537,12227.080643743046,12227.257165533209,12227.405803318805,12227.530961440609,12227.636348613552,12227.725087786737,12227.799808654489,12227.862725558029,12227.915703084704,12227.960311307455,12227.997872300512,12228.029499308876,12228.056129731758,12228.078552896812,12228.097433447845,12228.113331038732,12228.126716916877,12228.137987887469,12228.147478072147,12228.155468810462,12228.1621969974,12228.167862104005,12228.172632089065,12228.176648377046,12228.180030049702,12228.182877375606,12228.18527478214,12228.187293358029,12228.188992960559,12228.190423989929,12228.191628883294,12228.192643372824,12228.193497544979,12228.194216732494,12228.194822265408,12228.19533210347,12228.195761368628]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/23/out0.png', dpi=300)
