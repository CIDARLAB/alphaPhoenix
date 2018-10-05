import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [464.64026548145284,8402.155065839515,15086.738680720158,20716.16513806852,25456.990062059354,29449.478455556527,32811.75463860297,35643.29713084273,38027.88186535971,40036.06082324376,41727.24941909778,43151.48439556983,44350.90423656227,45360.995899127345,46211.64475029835,46928.01877267154,47531.313199181146,48039.37760812605,48467.244030901085,48827.571700875036,49131.021598474625,49386.57187288848,49601.78347586062,49783.02386542676,49935.65539820653,50064.19398412498,50172.44269760847,50263.60429835043,50340.37599075125,50405.02922564148,50459.47690535084,50505.329980494374,50543.94511298487,50576.464815461026,50603.8512547239,50626.914719313565,50646.337593488344,50662.694546918225,50676.46953743999,50688.07012993081,50697.83955494968,50706.06686392349,50712.99548133752,50718.830406962705,50723.744281210915,50727.88249307344,50731.36748177058,50734.302359385045,50736.77396166175,50738.85541723802,50740.60831131986,50742.084507821164,50743.32768387727,50744.37462213499,50745.25629905376,50745.99880141457,50746.624098161454,50747.15069041608,50747.59415887009,50747.967624778496,50748.28213818478,50748.54700486442,50748.770061659896,50748.957908353936,50749.116102941196,50749.249326076104,50749.36151956246,50749.45600298222,50749.535571914275,50749.60258064928,50749.65901184784,50749.70653520296,50749.74655684267,50749.78026093424,50749.80864472118,50749.83254802954,50749.852678116615,50749.86963059722,50749.88390706673,50749.895929942264,50749.906054961175,50749.91458170661,50749.921762471604,50749.92780972396,50749.93290239276,50749.93719116256,50749.94080293184,50749.94384456774,50749.94640606804,50749.948563224076,50749.950379863214]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/199/out0.png', dpi=300)
