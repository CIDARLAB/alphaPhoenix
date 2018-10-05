import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [232.50899787382718,232.5092838057627,232.5094931897786,232.50964258832227,232.5097453182221,232.50981205063297,232.50985130435754,232.5098698509514,232.5098730469154,232.50986510568083,232.5098493199511,232.50982824312408,232.50980383705934,232.50977759218384,232.50975062489763,232.50972375637716,232.50969757615815,232.5096724932866,232.50964877733244,232.50962659115487,232.50960601696832,232.50958707698155,232.509569749651,232.50955398239924,232.50953970149382,232.50952681965273,232.50951524183623,232.50950486959889,232.50949560430388,232.50948734944353,232.5094800122625,232.5094735048411,232.50946774476475,232.50946265547955,232.50945816641405,232.50945421292923,232.50945073614614,232.50944768268926,232.509445004375,232.50944265786808,232.50944060432224,232.50943880901838,232.50943724100884,232.5094358727744,232.50943467989848,232.50943364076093,232.50943273625313,232.50943194951515,232.50943126569453,232.50943067172642,232.5094301561341,232.50942970884867,232.50942932104692,232.50942898500585,232.50942869397244,232.50942844204772,232.50942822408362,232.50942803559116,232.50942787265942,232.50942773188373,232.50942761030248,232.5094275053411,232.5094274147632,232.50942733662725,232.50942726924876,232.50942721116718,232.5094271611168,232.50942711800133,232.50942708087175,232.5094270489069,232.50942702139668,232.50942699772713,232.50942697736787,232.5094269598607,232.5094269448101,232.50942693187469,232.50942692076006,232.50942691121222,232.50942690301227,232.50942689597156,232.5094268899276,232.5094268847404,232.50942688028945,232.50942687647108,232.50942687319602,232.50942687038756,232.50942686797967,232.5094268659156,232.5094268641466,232.50942686263076,232.5094268613321]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/512/out0.png', dpi=300)
