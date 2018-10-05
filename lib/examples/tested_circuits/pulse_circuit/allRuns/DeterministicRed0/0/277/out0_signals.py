import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [226.65793011164607,571.6849496491402,862.1465994470552,1106.6062221192728,1312.2938886402662,1485.3121874147712,1630.8105819588664,1753.1330659216862,1855.9431520836013,1942.3296347460546,2014.8960521078643,2075.8363364013308,2126.998764764966,2169.940004303513,2205.970772736638,2236.1944046465564,2261.5394166844235,2282.7869980785654,2300.594210926791,2315.5135649506246,2328.009528340054,2338.472450941761,2347.230302141725,2354.5585638214166,2360.6885661665,2365.8145095683844,2370.0993781573393,2373.679918600806,2376.6708308083057,2379.1682943548503,2381.252935131398,2382.9923204134425,2384.4430567493196,2385.652553419676,2386.6605043796417,2387.5001332864813,2388.1992392015663,2388.7810746361542,2389.2650826162826,2389.6675152295065,2390.0019525639605,2390.279737955485,2390.510342934595,2390.701673138342,2390.860324660665,2390.991798806113,2391.1006819416225,2391.1907960718663,2391.265324864007,2391.326919090818,2391.3777848245636,2391.419757178791,2391.454361945252,2391.4828670944376,2391.506325793864,2391.525612324502,2391.5414520588133,2391.5544464722566,2391.565094002842,2391.5738074409596,2391.580928420618,2391.586739490095,2391.591474161908,2391.595325276588,2391.598451959906,2391.6009854073013,2391.603033690801,2391.6046857515535,2391.6060147141566,2391.607080636461,2391.607932789671,2391.6086115478324,2391.6091499526383,2391.609575008495,2391.609908753604,2391.6101691451595,2391.6103707903776,2391.6105255497173,2391.610643034232,2391.610731015264,2391.6107957616255,2391.6108423168234,2391.6108747267504,2391.6108962264907,2391.6109093933974,2391.610916272374,2391.610918478272,2391.6109172794604,2391.610913665924,2391.6109084046616,2391.610902084673]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/277/out0.png', dpi=300)
