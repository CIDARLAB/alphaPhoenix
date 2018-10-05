import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [313.4971139370574,340.4641079955391,363.16701649275325,382.27631360209074,398.35764783950196,411.88813468489496,423.27013287779806,432.84289024198495,440.8923862572332,447.65964875370065,453.3477797673535,458.1278896545862,462.14410805889156,465.5178145070427,468.35120940731184,470.7303276778437,472.72758151399444,474.4039054299506,475.81056542727663,476.99068458157853,477.9805292456552,478.81059321931366,479.5065114423539,480.0898298667682,480.5786540201497,480.9881952686495,481.3312308262308,481.6184910540973,481.8589854794073,482.06027717591326,482.22871364033324,482.3696210241714,482.4874675050609,482.5860006737726,482.6683630468208,482.73718916811276,482.7946872177264,482.8427075859395,482.8828004827688,482.91626432627993,482.944186377294,482.96747685582517,482.98689757885643,483.0030859941752,483.0165753461118,483.0278115920753,483.0371675903154,483.0449549964542,483.05143423648576,483.0568228656777,483.0613025726132,483.06502504675217,483.0681168928001,483.0706837457095,483.0728137154942,483.07458027028986,483.0760446486544,483.0772578774573,483.07826245939873,483.0790937838688,483.07978130618176,483.0803495329347,483.08081884513126,483.08120618558047,483.08152563277804,483.0817888798706,483.0820056342756,483.08218395099334,483.08233051052173,483.0824508505026,483.08254955873423,483.0826304339362,483.0826966196043,483.0827507154184,483.08279486992944,483.08283085764145,483.0828601430884,483.08288393407656,483.08290322590534,483.0829188380771,483.0829314447576,483.0829416000385,483.0829497588768,483.08295629444285,483.08296151248413,483.0829656632109,483.08296895112625,483.082971543149,483.0829735753233,483.0829751583559,483.0829763821819]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/403/out0.png', dpi=300)
