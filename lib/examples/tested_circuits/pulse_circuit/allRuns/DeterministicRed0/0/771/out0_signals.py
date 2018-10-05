import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [232.4917257931941,235.93608374918443,238.8366625008003,241.2793098593473,243.33632085374234,245.06857728245726,246.52734948715303,247.75581369209746,248.79032981089284,249.66151753120553,250.3951625219506,251.01297958040888,251.53325530332512,251.97138930088602,252.3403499701197,252.65105831584728,252.9127111780432,253.13305343131205,253.31860721211,253.47486495764215,253.60645196942087,253.71726331258904,253.8105791026007,253.88916159124216,253.95533692533644,254.01106399786684,254.05799242925627,254.0975113948442,254.1307907436938,254.15881562571926,254.18241565199594,254.20228945132203,254.21902534984528,254.23311878582592,254.24498697497768,254.25498126045443,254.2633975130229,254.27048488925197,254.27645320695086,254.28147915616282,254.2857115295545,254.28927562698834,254.29227696476372,254.2948043991885,254.2969327569623,254.29872505028425,254.30023434222804,254.3015053176008,254.3025756057851,254.30347689472148,254.3042358690073,254.30487499988118,254.30541321047812,254.30586643604803,254.30624809572174,254.30656948978984,254.3068401342549,254.30706804256099,254.30725996284016,254.3074215777,254.30755767246575,254.3076722768583,254.3077687843023,254.3078500523958,254.30791848751642,254.30797611606874,254.3080246444818,254.3080655097333,254.30809992189606,254.3081288999667,254.3081533020368,254.30817385070023,254.3081911544486,254.3082057256885,254.3082179959136,254.30822832848116,254.30823702937118,254.3082443562467,254.308250526083,254.30825572159287,254.3082600966367,254.30826378077856,254.30826688312274,254.30826949554464,254.3082716954113,254.30827354787232,254.3082751077894,254.30827642136072,254.30827752748894,254.3082784589335,254.30827924328048]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/771/out0.png', dpi=300)
