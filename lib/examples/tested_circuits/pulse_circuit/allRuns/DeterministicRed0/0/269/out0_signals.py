import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [226.65793011164607,571.6989360694558,862.2449852772459,1106.898606386827,1312.905007068918,1486.3661300898586,1632.4209681385353,1755.3974532238692,1858.9403995835007,1946.1192737163349,2019.518970640464,2081.3162747609326,2133.3442617940414,2177.1465426951095,2214.0229510184927,2245.0680227909047,2271.203405376586,2293.205152695702,2311.7267131842573,2327.3182902046506,2340.4431465297657,2351.491335387632,2360.7912639572187,2368.6194313112633,2375.20862886814,2380.754845982358,2385.4230850328736,2389.352258131543,2392.6593104199655,2395.4426920506526,2397.7852816830355,2399.756848097837,2401.416122865732,2402.8125454945794,2403.987732783877,2404.976715949156,2405.8089822014954,2406.509351675133,2407.098715717972,2407.594658451541,2408.0119800471675,2408.363137251476,2408.6586142406522,2408.9072348166505,2409.1164252185044,2409.2924353566705,2409.4405250445066,2409.565120762013,2409.6699476121485,2409.758140393361,2409.832337091723,2409.894757573812,2409.947269821727,2409.9914456813945,2410.0286077835963,2410.0598690342645,2410.0861658519857,2410.1082861395134,2410.1268928249833,2410.142543673681,2410.155707960876,2410.1667805027773,2410.17609346399,2410.183926293648,2410.1905140866284,2410.1960546193536,2410.2007142701564,2410.2046330009516,2410.2079285489617,2410.2106999536945,2410.213030524524,2410.214990337553,2410.216638336376,2410.218024099551,2410.219189327616,2410.2201690941356,2410.2209928981974,2410.2216855498496,2410.2222679149827,2410.2227575419583,2410.223169188736,2410.2235152663025,2410.223806211672,2410.2240508016494,2410.224256416745,2410.224429263167,2410.224574559542,2410.2246966939624,2410.224799356077,2410.224885648188,2410.2249581786855]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/269/out0.png', dpi=300)
