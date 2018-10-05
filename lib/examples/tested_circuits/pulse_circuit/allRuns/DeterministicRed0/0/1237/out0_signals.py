import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [378.91389377064297,5470.246227472706,9757.521706526852,13367.470038028401,16406.886660256532,18965.761924851573,21119.920148743655,22933.24511426878,24459.556662555224,25744.192962391433,26825.344514889635,27735.178765576136,28500.788123491366,29144.989060525953,29686.99563694339,30142.987146764954,30526.586494474297,30849.263313616393,31120.67364267329,31348.94612512731,31540.92313400188,31702.363905309256,31838.1156559598,31952.257721446378,32048.222958843606,32128.89999392405,32196.719328954227,32253.725853647185,32301.639902020936,32341.90866090455,32375.749451723863,32404.186167700514,32428.07994672088,32448.154989976636,32465.02029307255,32479.187935442424,32491.088472071777,32501.08388571133,32509.47848546065,32516.528076684335,32522.44767590368,32527.41800107584,32531.59093125772,32535.094098980888,32538.0347528321,32540.50300598,32542.5745680694,32544.31304247922,32545.771857952543,32546.995892672334,32548.022839651163,32548.884354553855,32549.607020549465,32550.213159298564,32550.72151256135,32551.147815017062,32551.505275646326,32551.804982205267,32552.05624107457,32552.26686278261,32552.44340186753,32552.59135836459,32552.71534704474,32552.81923955466,32552.906283787637,32552.979204124487,32553.04028560333,32553.09144458903,32553.134288102672,32553.17016362657,32553.20020091032,32553.225347059488,32553.2463959838,32553.264013109347,32553.27875611463,32553.29109232867,32553.301413327194,32553.310047177,32553.3172687065,32553.32330811983,32553.32835822101,32553.332580471797,32553.336110071145,32553.33906021373,32553.341525660006,32553.343585728697,32553.345306804946,32553.346744442315,32553.347945124176,32553.348947739574,32553.34978481968]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/1237/out0.png', dpi=300)
