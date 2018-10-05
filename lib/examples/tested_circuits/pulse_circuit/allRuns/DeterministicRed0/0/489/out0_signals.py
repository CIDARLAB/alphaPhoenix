import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [399.43570506393996,397.7231211989974,390.6925516774788,378.24189171300094,361.43315665594037,341.5344735412451,319.7523391131804,297.1267584317566,274.49435032603344,252.4876520720952,231.55415496426582,211.98462377840636,193.94395377680482,177.5005230415583,162.65194766817788,149.3464510793393,137.4998488684136,127.0085707587699,117.75931874560774,109.6359929590726,102.52447182493243,96.3157536524319,90.90787802655335,86.20696073861015,82.12760161161714,78.5928624388934,75.53396210724938,72.88979652989924,70.60636060911408,68.63612641119782,66.93741454950694,65.47378313897329,64.21344954225412,63.128753619975704,62.19566665591393,61.393347035088794,60.70374171433609,60.11123124474494,59.602315355971264,59.16533574784669,58.790232608522,58.46833142779848,58.19215682805135,57.95527034983311,57.75212937633355,57.577964639568926,57.42867400800985,57.300730501798775,57.19110271267802,57.09718601856591,57.01674317632508,56.94785305067756,56.88886639313657,56.83836772335271,56.79514248774359,56.758148778112755,56.72649298759909,56.69940886411468,56.67623949373513,56.65642180950963,56.63947327596295,56.62498044716559,56.612589137545505,56.60199598040133,56.592941180058986,56.58520229041234,56.57858887575189,56.57293792979332,56.56810994608831,56.5639855479009,56.560462598481166,56.557453723742924,56.55488418889277,56.5526900787748,56.55081673876877,56.54921743916661,56.54785223118901,56.546686967307345,56.54569246240831,56.544843775666756,56.54411959585573,56.543501715260426,56.542974579507096,56.5425249024036,56.542141336446015,56.54181419098706,56.54153519120476,56.5412972719923,56.54109440173269,56.540921431644406,56.54077396700383]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/489/out0.png', dpi=300)
