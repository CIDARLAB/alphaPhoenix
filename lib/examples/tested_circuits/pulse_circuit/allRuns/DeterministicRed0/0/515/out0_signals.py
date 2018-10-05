import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [232.50899787382718,235.9587698383063,238.8639113539111,241.31040417154816,243.37065617088393,245.105644234519,246.5667188206463,247.79712365998475,248.8332755485634,249.7058421055294,250.4406493897795,251.05944623445518,251.58054791835636,252.0193782226601,252.38892591431994,252.70012916519832,252.96219928340798,253.18289333741407,253.36874374102766,253.52525159375358,253.65704949835026,253.768038674178,253.8615044242247,253.94021337309565,254.0064953537778,254.06231236667892,254.10931665184867,254.14889959309605,254.18223290138556,254.2103032963988,254.23394171272219,254.2538478950753,254.27061111052757,254.2847275907307,254.2966152204131,254.30662590688337,254.31505599665442,254.32215504750067,254.32813321558706,254.333167476318,254.3374068630354,254.34097687859597,254.3439832105169,254.3465148595278,254.3486467741517,254.35044206935254,254.3519538948951,254.3532270087206,254.354299101911,254.3552019144615,254.35596217488853,254.35660239148723,254.35714151865992,254.35759551804006,254.3579778310221,254.35829977668428,254.3585708868841,254.3587991884463,254.3589914407959,254.3591533360716,254.35928966764345,254.35940447202276,254.35950114836584,254.35958255910919,254.35965111471492,254.3597088450358,254.35975745941192,254.35979839727867,254.359832870784,254.35986190067663,254.3598863465276,254.35990693218,254.35992426718045,254.35993886482638,254.3599511573638,254.35996150878546,254.359970225608,254.35997756594767,254.35998374716272,254.35998895228929,254.35999333546098,254.35999702647263,254.36000013462353,254.36000275195374,254.3600049559695,254.36000681193798,254.36000837482027,254.3600096908984,254.36001079914604,254.36001173238253,254.36001251824464]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/515/out0.png', dpi=300)
