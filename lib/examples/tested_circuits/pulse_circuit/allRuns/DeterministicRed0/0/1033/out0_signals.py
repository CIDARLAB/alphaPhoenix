import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [221.83504816568626,221.83958636149555,221.84300199129927,221.84540372915978,221.8469147957822,221.84766235943243,221.84777044870745,221.84735544452204,221.84652344175265,221.84536894335466,221.84397448283153,221.84241087344995,221.84073786133186,221.83900501979682,221.837252768178,221.83551343301517,221.83381229555658,221.83216858884836,221.83059642190278,221.82910561872308,221.8277024672606,221.8263903784286,221.82517045866024,221.8240420016161,221.8230029058584,221.82205002586713,221.82117946387908,221.82038680982586,221.8196673362425,221.81901615449593,221.81842833809688,221.8178990182542,221.81742345623238,221.81699709650616,221.81661560416813,221.81627488956212,221.81597112267568,221.81570073943243,221.81546044167962,221.8152471923653,221.81505820713798,221.81489094337795,221.8147430874774,221.8146125410251,221.81449740641386,221.8143959722771,221.81430669906425,221.8142282049884,221.81415925251596,221.8140987355159,221.81404566714608,221.81399916852075,221.81395845817795,221.81392284234602,221.8138917059936,221.81386450463648,221.81384075686702,221.81382003756673,221.8138019717595,221.81378622906132,221.81377251868236,221.81376058493703,221.81375020322014,221.81374117640823,221.81373333164723,221.8137265174906,221.81372060135374,221.81371546725347,221.8137110138039,221.8137071524415,221.81370380585605,221.8137009066046,221.81369839588922,221.81369622247973,221.81369434176588,221.81369271492375,221.81369130818362,221.81369009218744,221.81368904142548,221.8136881337428,221.81368734990738,221.81368667323193,221.81368608924387,221.81368558539674,221.81368515081854,221.81368477609197,221.81368445306313,221.8136841746747,221.81368393482083,221.8136837282206,221.8136835503085]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/1033/out0.png', dpi=300)
