import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy = [234.56966085010865,234.57058061046382,234.57131410907934,234.57187269107993,234.57227263074182,234.57253267989006,234.57267236597954,234.57271084773137,234.57266618123333,234.572554884348,234.57239171435143,234.572189594683,234.5719596428653,234.57171126410336,234.57145228461098,234.57118910598163,234.57092686742845,234.5706696068563,234.57042041480784,234.5701815775942,234.56995470756385,234.56974085963242,234.5695406340099,234.5693542656037,234.56918170092274,234.56902266350846,234.56887670901423,234.56874327107934,234.56862169911653,234.5685112890739,234.56841130815346,234.56832101437968,234.5682396718202,234.5681665621667,234.5681009932986,234.56804230536878,234.56798987487517,234.56794311711408,234.5679014873505,234.56786448098612,234.56783163296012,234.56780251657588,234.56777674191264,234.5677539539506,234.56773383051336,234.56771608010928,234.56770043973643,234.56768667270052,234.5676745664829,234.56766393068614,234.5676545950764,234.56764640773483,234.56763923332645,234.56763295148878,234.5676274553419,234.5676226501167,234.5676184518987,234.5676147864816,234.56761158832504,234.5676087996104,234.56760636938785,234.5676042528076,234.56760241042952,234.5676008076039,234.5675994139175,234.56759820269897,234.56759715057808,234.56759623709357,234.56759544434493,234.56759475668346,234.56759416043872,234.5675936436766,234.56759319598558,234.56759280828834,234.5675924726755,234.56759218225946,234.5675919310458,234.56759171382026,234.56759152604965,234.56759136379492,234.567591223635,234.56759110260003,234.56759099811313,234.5675909079394,234.56759083014128,234.56759076303985,234.56759070518078,234.5675906553049,234.56759061232233,234.56759057529015,234.56759054339284]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(0.0,900.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_848.png', dpi=300)
