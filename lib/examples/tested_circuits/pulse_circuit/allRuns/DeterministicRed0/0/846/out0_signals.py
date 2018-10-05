import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [234.56966085010865,1855.5495315471228,3220.658734668193,4370.286022708474,5338.444894936318,6153.779906677318,6840.414137480191,7418.662891273416,7905.634740989785,8315.737701385797,8661.105504533876,8951.956589378562,9196.896426112562,9403.172119703822,9576.88682509324,9723.180317616174,9846.381060905318,9950.134271284769,10037.509767273075,10111.092795914801,10173.060522028742,10225.246443543283,10269.19463917145,10306.205453171056,10337.373968824273,10363.622408920251,10385.72742185032,10404.343060611804,10420.020134587274,10433.222506650996,10444.340817778533,10453.704045225155,10461.589236242147,10468.229705320977,10473.82193749661,10478.531401958046,10482.497447973943,10485.837427989922,10488.650169888679,10491.018901147872,10493.013711414049,10494.69362635406,10496.108354144184,10497.299756271535,10498.30308616546,10499.148032307321,10499.859596682136,10500.458834563697,10500.963477521993,10501.388458086567,10501.746351589707,10502.047748262845,10502.301566595925,10502.515317231566,10502.695325202283,10502.846917085602,10502.974578617299,10503.082087425326,10503.172624808176,10503.248869869707,10503.313078794225,10503.367151607503,10503.412688399147,10503.451036669896,10503.483331204812,10503.510527652226,10503.533430801996,10503.552718399824,10503.568961202325,10503.58263986622,10503.594159171449,10503.603859999044,10503.6120294182,10503.618909181005,10503.6247028762,10503.629581953675,10503.633690797913,10503.637151000557,10503.640064958518,10503.64251890407,10503.644585456628,10503.646325771695,10503.647791350573,10503.649025564397,10503.65006493758,10503.650940228654,10503.651677340491,10503.652298086821,10503.652820837771,10503.65326106347,10503.653631791856]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/846/out0.png', dpi=300)
