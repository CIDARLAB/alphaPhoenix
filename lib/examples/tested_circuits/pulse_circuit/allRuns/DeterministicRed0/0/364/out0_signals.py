import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [228.19294289854744,260.63549141693704,285.57087222425866,304.25497652735095,317.7752644891406,327.04285815450214,332.8180560930932,335.7345056874044,336.3203016005679,335.016047116584,332.1901692300546,328.15179151957864,323.16143732670815,317.43980518094173,311.17484045399686,304.5273222081421,297.63518722193123,290.61681714809004,283.57351287954776,276.5913679419694,269.7427289278613,263.0873977803922,256.67369245935015,250.53944433223666,244.712976860232,239.21408365555268,234.0550057584457,229.24139747467382,224.77326578914216,220.64586829627504,216.8505569223903,213.37555801020062,210.20668264471058,207.32796394690456,204.72222031935974,202.37154536916978,200.25772660108132,198.3625960936348,196.66831731495623,195.157613019768,193.81393977824501,192.6216150944344,191.56590326067388,190.63306606726522,189.81038426545408,189.08615530258672,188.44967235614112,187.89118913008218,187.40187428202722,186.97375876571698,186.5996788087611,186.27321673079157,185.98864134619535,185.7408492937408,185.52530829271106,185.3380030387646,185.175384217998,185.03432092895258,184.91205665360232,184.80616880377087,184.71453178340045,184.63528344458294,184.5667947718067,184.50764260061126,184.456585160497,184.41254022476704,184.37456564972223,184.34184209044886,184.31365768885385,184.28939454044507,184.26851675871015,184.25055996910862,184.23512207813525,184.2218551762345,184.2104584462751,184.2006719616256,184.19227126948778,184.18506266596617,184.17887907934386,184.17357648719602,184.1690308013151,184.16513516197963,184.16179758991137,184.15893895038306,184.1564911894087,184.15439580682798,184.1526025354329,184.15106819908385,184.1497557262696,184.14863329843737,184.14767361512773]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/364/out0.png', dpi=300)
