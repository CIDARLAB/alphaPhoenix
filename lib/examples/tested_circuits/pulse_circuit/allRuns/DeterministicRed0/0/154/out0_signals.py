import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [327.40838656527797,329.1290850024523,330.5659023250078,331.75610171604075,332.73374902857717,333.5295417187488,334.1708230068851,334.68171281891733,335.083309062265,335.3939275899044,335.629359317944,335.803129973232,335.92675286166775,336.0099685186451,336.0609675631786,336.08659481872485,336.092533987382,336.0834730109562,336.06325082307933,336.034986564472,336.00119255270414,335.96387240889896,335.9246057772235,335.88462105180054,335.84485746729507,335.8060178270491,335.7686130460826,335.7329995828213,335.69941072831034,335.667982618645,335.638775737934,335.6117925869133,335.5869921073086,335.5643013746237,335.54362500228655,335.52485263782756,335.5078648766321,335.49253787033354,335.47874686455964,335.4663688639579,335.4552845906413,335.44537987487485,335.43654659344446,335.42868325124687,335.4216952847502,335.4154951517285,335.4100022596868,335.4051427753626,335.4008493493334,335.3970607828168,335.3937216580269,335.3907819487397,335.38819662386834,335.3859252537274,335.3839316261362,335.38218337748293,335.38065164226083,335.3793107233178,335.378137784069,335.37711256316084,335.3762171115008,335.37543555113797,335.3747538551749,335.374159647681,335.3736420224408,335.3731913792937,335.37279927678986,335.37245829988876,335.3721619414539,335.37190449634295,335.37168096694944,335.3714869791199,335.37131870743866,335.37117280894716,335.3710463644362,335.3709368265216,335.3708419737812,335.370759870298,335.3706888300156,335.3706273853677,335.3705742597012,335.37052834305683,335.3704886709208,335.37045440559893,335.3704248199049,335.37039928288664,335.3703772473464,335.3703582389368,335.3703418466418,335.3703277144721,335.3703155342247]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/154/out0.png', dpi=300)
