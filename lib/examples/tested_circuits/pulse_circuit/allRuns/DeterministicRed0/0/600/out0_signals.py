import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [234.58778464900956,234.58872134066903,234.58942228349608,234.58987307371626,234.59008005264116,234.59006246840121,234.58984686982944,234.58946317221748,234.58894196736654,234.5883127519064,234.58760282584703,234.58683667335185,234.58603568394932,234.58521810798203,234.58439916743092,234.58359126419424,234.58280424387715,234.58204568526554,234.5813211947897,234.58063469210373,234.57998867794552,234.5793844791176,234.57882246806227,234.57830225634842,234.5778228626409,234.57738285653377,234.5769804801195,234.57661374941955,234.5762805378937,234.57597864422132,234.57570584644884,234.57545994445405,234.57523879250553,234.5750403235141,234.57486256639032,234.57470365774583,234.5745618490089,234.57443550987415,234.57432312886624,234.57422331167447,234.57413477780608,234.57405635601202,234.57398697885515,234.57392567672147,234.5738715715143,234.5738238702208,234.57378185849782,234.57374489438922,234.57371240225638,234.5736838669821,234.57365882848669,234.57363687658113,234.5736176461696,234.57360081280376,234.5735860885854,234.5735732184065,234.57356197651407,234.57355216338294,234.5735436028784,234.57353613968988,234.57352963701646,234.5735239744842,234.57351904627726,234.57351475946396,234.57351103250102,234.57350779389887,234.57350498103355,234.57350253909019,234.57350042012544,234.5734985822364,234.57349698882504,234.57349560794808,234.57349441174284,234.57349337592106,234.5734924793232,234.57349170352592,234.57349103249726,234.57349045229375,234.57348995079474,234.57348951746926,234.5734891431722,234.57348881996558,234.5734885409624,234.57348830019006,234.5734880924714,234.57348791332043,234.57348775885174,234.57348762570138,234.57348751095805,234.57348741210302,234.57348732695817]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/600/out0.png', dpi=300)
