import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [534.7774278130823,561.3171519713353,583.6617489480228,602.4740527730086,618.3122139476683,631.646225474094,642.8718410631093,652.3222968683771,660.2781832359041,666.9757583224904,672.6139493911164,677.3602489313554,681.355679878823,684.7189769009063,687.5501074685646,689.9332369108747,691.9392252168643,693.6277295017285,695.048974391024,696.2452427525848,697.2521309316512,698.0996056757289,698.8128940661552,699.4132328298872,699.9184992415725,700.3437423194963,700.7016300659011,701.0028260151366,701.2563062586083,701.4696263516113,701.6491460217055,701.8002183473607,701.9273490221814,702.0343304329275,702.1243545325433,702.2001078603613,702.2638515319578,702.3174885751066,702.362620612704,702.4005955772899,702.4325478755134,702.4594321966744,702.4820519706935,702.501083321911,702.5170952312831,702.5305665068664,702.5419000676109,702.5514349655012,702.5594565043824,702.5662047561373,702.5718817281745,702.576657395729,702.580674778651,702.5840542139654,702.5868969515395,702.5892881800488,702.5912995734585,702.5929914339656,702.5944144953153,702.5956114402942,702.5966181776798,702.5974649167572,702.5981770714776,702.5987760212572,702.5992797511329,702.5997033903969,702.6000596658037,702.6003592828897,702.6006112468032,702.6008231322351,702.6010013105223,702.6011511407137,702.601277130316,702.6013830705275,702.6014721500061,702.6015470505789,702.6016100277567,702.6016629784644,702.6017074980186,702.6017449280541,702.6017763968414,702.6018028531985,702.6018250950166,702.6018437932538,702.6018595121163,702.6018727260332,702.6018838339331,702.6018931712524,702.6019010200342,702.6019076174222,702.6019131628036]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/971/out0.png', dpi=300)
