import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [399.43570506393996,904.0971066670164,1329.0644829530772,1686.922214953624,1988.2675868986837,2242.024539161434,2455.7078833520513,2635.645800308886,2787.167207640548,2914.7595434387204,3022.20163697367,3112.6755998175167,3188.861049823477,3253.0144574159904,3307.0359632385353,3352.525645328281,3390.8309016626367,3423.0863508872817,3450.247432539997,3473.1187019495887,3492.3776568361313,3508.5948016669695,3522.250543684139,3533.7494208868557,3543.4320832605126,3551.5853820234047,3558.4508656450166,3564.2319342169385,3569.099864032929,3573.198880782295,3576.6504315903744,3579.5567824174796,3582.004047350921,3584.064739502308,3585.7999190560954,3587.2610020860207,3588.4912837103757,3589.5272206976106,3590.3995115101156,3591.134005775218,3591.7524701208818,3592.273233059732,3592.711728022881,3593.080950628543,3593.3918437302846,3593.653621650715,3593.874043205209,3594.0596416034227,3594.215918039117,3594.3475047032302,3594.458302049432,3594.551594378699,3594.63014716724,3594.6962890212526,3594.7519806866317,3594.798873157526,3594.8383566084162,3594.871601594689,3594.899593745963,3594.923162979207,3594.943008097249,3594.9597175015947,3594.9737866333085,3594.985632658795,3594.9956068356673,3595.0040049251656,3595.011075959691,3595.0170296252904,3595.0220424778863,3595.0262631774754,3595.0298168954296,3595.032809025525,3595.035328308694,3595.0374494641087,3595.0392354045925,3595.040739102017,3595.042005157992,3595.043071126393,3595.0439686269397,3595.044724282836,3595.045360510262,3595.045896183131,3595.0463471928088,3595.0467269194037,3595.0470466285833,3595.047315805696,3595.047542437096,3595.0477332470186,3595.047893897025,3595.0480291539316,3595.0481430312057]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/452/out0.png', dpi=300)
