import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [232.4917257931941,235.92860123368126,238.7858342748339,241.13229973622222,243.0354752636435,244.55819200773178,245.75735490460545,246.68362428740537,247.38157354994178,247.89007535066588,248.2427858762162,248.46865765659345,248.59244440477715,248.63517954408894,248.6146201359004,248.54565337729304,248.4406656852436,248.30987578503542,248.16163379709633,248.002688533395,247.83842522974447,247.67307589038552,247.50990436127182,247.3513681920732,247.19925929593353,247.0548253645854,246.91887393773325,246.79186095470862,246.67396553100892,246.56515260270666,246.46522497000083,246.37386615064707,246.29067532839204,246.21519555453693,246.14693623566882,246.0853908201501,246.0300504822208,245.98041449698835,245.93599790302392,245.89633696213818,245.8609928481785,245.82955392808114,245.801636938434,245.77688730883128,245.75497883862377,245.73561289555914,245.71851727252957,245.70344481150306,245.69017188104206,245.67849677500715,245.6682380845508,245.65923308283467,245.6513361516181,245.6444172705864,245.63836058368148,245.63306305148325,245.6284331946204,245.62438993006228,245.62086149977813,245.61778448950727,245.6151029341329,245.61276750529836,245.61073477636225,245.60896655948378,245.60742930951486,245.60609358939732,245.60493359189036,245.603926712649,245.60305316992486,245.6022956664532,245.60163908937153,245.60107024433304,245.60057762028018,245.60015118164276,245.59978218501047,245.5994630176023,245.59918705511134,245.59894853674191,245.59874245547635,245.59856446181297,245.59841077940183,245.59827813117525,245.5981636747231,245.5980649458028,245.5979798089983,245.59790641465634,245.5978431613276,245.59778866303213,245.5977417207478,245.59770129759275,245.59766649723628]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/803/out0.png', dpi=300)
