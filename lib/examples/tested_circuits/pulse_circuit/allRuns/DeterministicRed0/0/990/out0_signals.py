import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [534.7774278130823,8431.755513318596,15082.084241714623,20682.46645508895,25398.583784185477,29369.98632299338,32714.212034598073,35530.25713413105,37901.49962083189,39898.16208427599,41579.38634793764,42994.9810959858,44186.89400348107,45190.451778102986,46035.40468566908,46746.80637157721,47345.75493683923,47850.01713800267,48274.55413374469,48631.96430266529,48932.85620399837,49186.1626949881,49399.405486847885,49578.91795428692,49730.03278282179,49857.240000140475,49964.32006338364,50054.455937595674,50130.32748007074,50194.19092259194,50247.94580322728,50293.19132842346,50331.27383369181,50363.32674799493,50390.30424525556,50413.00957968049,50432.11894431185,50448.20155974453,50461.73658837059,50473.127375535354,50482.71343984007,50490.780568164635,50497.56931484141,50503.28215712857,50508.08951931172,50512.13484422757,50515.53886276242,50518.403188095916,50520.813341432804,50522.84129910038,50524.54763668741,50525.983333941505,50527.19129407175,50528.207622624424,50529.06270395939,50529.78210733949,50530.38734959827,50530.896537075685,50531.3249059056,50531.685276761185,50531.988437589316,50532.24346573145,50532.4579990253,50532.63846396385,50532.790267710494,50532.91795969287,50533.025367592774,50533.11571178679,50533.19170165071,50533.2556166003,50533.30937428652,50533.35458798003,50533.39261485808,50533.424596635174,50533.45149375093,50533.47411413617,50533.493137416655,50533.50913527766,50533.522588597996,50533.53390186577,50533.543415306805,50533.55141508852,50533.558141904476,50533.56379819642,50533.56855422996,50533.57255320581,50533.575915559464,50533.57874257826,50533.581119443996,50533.58311779242,50533.58479786611]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/990/out0.png', dpi=300)
