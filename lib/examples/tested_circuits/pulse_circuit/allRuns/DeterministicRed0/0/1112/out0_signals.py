import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [210.93897812097362,210.9084704255076,210.85518394939498,210.76548959692772,210.63448617696602,210.46253684024256,210.25311669664353,210.0114297219999,209.74350181401107,209.45558192669222,209.15374993774677,208.8436672532814,208.53042794118178,208.21848139521444,207.91160587011646,207.61291772662426,207.324905027705,207.04947687658688,206.7880219676508,206.5414714393982,206.31036240374857,206.0948995543937,205.8950130780922,205.71041174165674,205.5406305313702,205.38507260383668,205.24304558827447,205.1137924782986,204.99651748261988,204.89040728309544,204.79464818749494,204.70843967353102,204.63100480864395,204.56159800355616,204.49951052208533,204.44407412921552,204.39466321701968,204.35069570588763,204.31163297813472,204.2769790634161,204.2462792610086,204.21911835320003,204.19511853679523,204.17393717599563,204.15526445943703,204.1388210267124,204.12435561497676,204.11164276392327,204.10048060724517,204.09068877037066,204.08210638752192,204.07459024576735,204.0680130594949,204.06226187544965,204.05723660598667,204.0528486863466,204.0490198504511,204.04568101882856,204.0427712917371,204.04023704027247,204.03803108818005,204.03611197717655,204.03444330879074,204.03299315602203,204.03173353845747,204.03063995486613,204.02969096771025,204.0288678343944,204.0281541804944,204.02753571060023,204.02699995278644,204.02653603308605,204.02613447668284,204.02578703285582,204.02548652100353,204.02522669534824,204.02500212616908,204.0248080956416,204.02464050656732,204.02449580246633,204.0243708976737,204.02426311623472,204.02417013853122,204.02408995469307,204.02402082396068,204.0239612392607,204.02390989634605,204.0238656669284,204.0238275752997,204.02379477800156,204.02376654615455]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/1112/out0.png', dpi=300)
