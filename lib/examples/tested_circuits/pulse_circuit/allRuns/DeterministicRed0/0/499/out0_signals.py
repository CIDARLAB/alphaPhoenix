import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [399.43570506393996,415.4598174263429,418.6310808529325,411.41046200770165,396.5989077876558,376.6576096682558,353.6122383613562,329.0518950901636,304.16851483760894,279.81489966972003,256.56803721076426,234.7894918043004,214.67851003710564,196.31618315964752,179.70067968028158,164.77444480716642,151.44462150337648,139.59799597385077,129.11165905755902,119.86039841252807,111.72164756986044,104.57864337792185,98.32229383046138,92.85213601452793,88.07666701308496,83.91325547249824,80.28778426121488,77.13413154026583,74.39356549645777,72.01410438392529,69.94987633612567,68.16050105528532,66.61050673186759,65.26878945337734,64.10811821490229,63.1046859024548,62.23770487856951,61.489044756408646,60.84290938321501,60.285549807450806,59.80500996404647,59.39090190099697,59.03420753526042,58.72710413152198,58.462810918722816,58.235454490451374,58.039950851557,57.87190218661205,57.72750662242313,57.60347943917694,57.49698435208272,57.40557363777623,57.32713601786697,57.259851336631804,57.20215118188123,57.15268469839204,57.11028893296188,57.07396313002395,57.04284646775725,57.01619878758562,56.993383925668844,56.97385530419094,56.95714348362391,56.942845415315034,56.93061516728296,56.92015592552924,56.91121309894703,56.90356837845853,56.89703462071643,56.891451443901374,56.886681438138666,56.882606906113146,56.879127060819336,56.87615561725621,56.87361872344918,56.87145318362064,56.86960493277941,56.868027727586785,56.866682023193405,56.865534009925696,56.8645547873178,56.86371965611087,56.86300751154011,56.86240032356313,56.861882691673785,56.86144146371131,56.86106540953955,56.86074494176235,56.8604718767517,56.86023923021559,56.86004104235126]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/499/out0.png', dpi=300)
