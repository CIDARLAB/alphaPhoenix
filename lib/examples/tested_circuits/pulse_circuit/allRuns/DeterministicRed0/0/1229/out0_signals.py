import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [378.91389377064297,5470.298426884875,9757.889302398651,13368.563502246101,16409.174009975108,18969.709530117892,21125.955694445245,22941.736397294022,24470.801487326902,25758.416610687415,26842.70219234723,27755.761132836065,28524.62855464847,29172.071674325267,29717.263447247846,30176.349901774513,30562.927937332028,30888.447714658192,31162.55153673181,31393.35924463518,31587.70856657803,31751.35752847175,31889.154915172643,32005.183824009076,32102.882557352335,32185.146430846347,32254.41350950023,32312.736808488742,32361.845095152243,32403.194091505487,32438.00959258841,32467.323776826925,32492.005783148128,32512.787459957097,32530.285048216396,32545.017440550266,32557.421556965714,32567.86529244528,32576.658419798314,32584.06177063291,32590.294966339374,32595.542928050523,32599.961358394525,32603.68135741266,32606.813309377245,32609.450155654566,32611.670150576367,32613.539181972083,32615.112725120143,32616.43748801877,32617.55279673286,32618.491761873596,32619.28226078348,32619.947764538992,32620.508034285343,32620.979707542956,32621.376791875202,32621.71108055147,32621.99250251748,32622.22941706536,32622.42886193667,32622.596762216235,32622.73810621213,32622.85709353863,32622.957259794486,32623.041581535126,32623.11256465285,32623.1723187872,32623.222619973247,32623.264963386682,32623.300607751025,32623.330612724643,32623.355870377265,32623.377131690228,32623.39502886702,32623.410094116407,32623.42277546578,32623.433450074157,32623.44243544013,32623.449998837576,32623.456365259273,32623.46172410439,32623.466234808366,32623.4700315825,32623.47322740395,32623.4759173747,32623.478181549268,32623.480087315173,32623.48169139689,32623.48304154281,32623.48417794539]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/1229/out0.png', dpi=300)
