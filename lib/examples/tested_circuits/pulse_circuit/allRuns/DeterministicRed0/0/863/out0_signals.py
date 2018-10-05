import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [234.56966085010865,2136.6275860752094,3738.431264704001,5087.36190375592,6223.326365669023,7179.935783766168,7985.498426529855,8663.85605507774,9235.088422185887,9716.106690593586,10121.15327608979,10462.222865271724,10749.417034493325,10991.242938542304,11194.864887767437,11366.316242359748,11510.677881444024,11632.228518113963,11734.571300247244,11820.740437997425,11893.291007451413,11954.374583797684,12005.802939918902,12049.101692777238,12085.555483378877,12116.24602601652,12142.084151831163,12163.836794291283,12182.149714723013,12197.566640128127,12210.545379482373,12221.471395385302,12230.669232697628,12238.412142432755,12244.930185794317,12250.417058295887,12255.035836034582,12258.923814299193,12262.196581833487,12264.95145145327,12267.270348663009,12269.22224387164,12270.865200293001,12272.248098235117,12273.41208689747,12274.391806723306,12275.216418556063,12275.91047012423,12276.494625557898,12276.986279580427,12277.400074599836,12277.748336045488,12278.041438871374,12278.28811610576,12278.495718607952,12278.670433745388,12278.817469484386,12278.941209365854,12279.045342966974,12279.132975723342,12279.206721378467,12279.268779806902,12279.321002524182,12279.3649478311,12279.401927231913,12279.433044506779,12279.45922860053,12279.481261306039,12279.499800565796,12279.515400084978,12279.528525839693,12279.539569971725,12279.548862483387,12279.556681080656,12279.563259457682,12279.568794269378,12279.57345099979,12279.577368901015,12279.580665149875,12279.583438346159,12279.585771456701,12279.587734293063,12279.589385596668,12279.590774793547,12279.59194347106,12279.592926620588,12279.593753683306,12279.5944494302,12279.595034702608,12279.595527035379,12279.595941181258]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/863/out0.png', dpi=300)
