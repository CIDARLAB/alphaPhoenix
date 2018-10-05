import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [234.58778464900956,234.61362924473679,234.6353825177947,234.65369183942457,234.66910219497709,234.6820723693659,234.69298857506408,234.70217592655953,234.7099081014212,234.7164154744266,234.72189196597236,234.7265008078999,234.7303793977907,234.73364338575473,234.73639011503545,234.73870151858122,234.74064655745642,234.7422832736242,234.74366051803125,234.74481940532928,234.74579453845615,234.74661503946894,234.74730541726757,234.74788629800724,234.74837503991685,234.74878625080936,234.74913222367755,234.74942330333533,234.7496681950146,234.74987422410283,234.75004755475328,234.7501933738772,234.75031604599715,234.75041924357416,234.75050605669114,234.7505790853614,234.7506405172129,234.75069219286425,234.7507356609415,234.75077222437653,234.75080297936802,234.75082884816698,234.7508506066656,234.75086890761247,234.7508843001472,234.75089724623777,234.75090813451132,234.75091729189185,234.75092499339192,234.75093147035128,234.75093691736893,234.75094149813503,234.75094535033782,234.75094858979185,234.75095131391112,234.75095360463112,234.75095553086686,234.75095715058106,234.75095851252368,234.75095965769552,234.7509606205794,234.75096143017572,234.7509621108737,234.75096268318416,234.75096316435594,234.75096356889435,234.75096390899742,234.75096419492263,234.75096443529546,234.75096463736898,234.75096480724198,234.7509649500427,234.75096507008297,234.75096517098842,234.75096525580742,234.75096532710293,234.75096538702985,234.7509654373999,234.75096547973624,234.75096551531945,234.75096554522602,234.75096557036105,234.75096559148537,234.7509656092385,234.75096562415817,234.7509656366963,234.7509656472328,234.750965656087,234.75096566352738,234.7509656697795,234.75096567503303]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/585/out0.png', dpi=300)
