import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [232.4917257931941,232.5116271876773,232.52831096142233,232.5422595875909,232.5538893018221,232.5635580568373,232.571572766179,232.57819583830417,232.58365102413435,232.58812861513312,232.591790037247,232.59477189023343,232.597189483217,232.59913991665178,232.60070475888438,232.6019523626892,232.60293986383965,232.60371490023954,232.6043170865471,232.60477927570457,232.6051286354228,232.60538756451282,232.6055744710375,232.60570443158676,232.6057897485621,232.60584042018496,232.6058645360051,232.6058686089676,232.60585785357904,232.60583641838312,232.60580757978832,232.60577390328632,232.60573737719616,232.6056995233305,232.60566148830964,232.60562411868526,232.60558802254957,232.60555361989015,232.60552118359462,232.60549087270678,232.60546275927834,232.60543684994136,232.60541310314252,232.60539144282274,232.60537176919453,232.60535396715863,232.60533791280764,232.605323478387,232.6053105360178,232.60529896043147,232.60528863092134,232.6052794326778,232.6052712576429,232.6052640049942,232.60525758134642,232.60525190074162,232.6052468844847,232.605242460869,232.60523856482683,232.6052351375328,232.60523212598056,232.6052294825496,232.60522716457317,232.6052251339171,232.60522335657473,232.60522180228287,232.60522044416106,232.6052192583755,232.60521822382836,232.60521732187212,232.6052165360486,232.6052158518512,232.6052152565094,232.60521473879442,232.60521428884385,232.60521389800462,232.60521355869213,232.6052132642646,232.60521300891102,232.60521278755164,232.60521259574938,232.60521242963168,232.60521228582104,232.6052121613738,232.60521205372623,232.60521196064684,232.6052118801945,232.60521181068182,232.60521175064258,232.60521169880369,232.60521165406007]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/785/out0.png', dpi=300)
