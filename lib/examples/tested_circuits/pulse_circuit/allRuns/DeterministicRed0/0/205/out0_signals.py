import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [464.64026548145284,5890.800611286531,10460.315688230057,14308.41814446359,17548.993738461028,20277.950800875602,22576.05788166754,24511.33351973712,26141.058813893225,27513.472333637164,28669.19750394256,29642.444687976607,30462.02352784028,31152.19549118864,31733.39184506644,32222.81829768976,32634.964196509103,32982.03134756639,33274.29514267142,33520.40868181851,33727.65888647281,33902.18218147699,34049.14612952803,34172.902391983334,34277.11554226893,34364.87154371453,34438.769101955855,34500.996595329576,34553.39685994919,34597.52174677041,34634.67806529968,34665.966273717,34692.313060533896,34714.49878213924,34733.180568351265,34748.91177989077,34762.15839372404,34773.31280130066,34782.705428141024,34790.61451874548,34797.27437649286,34802.88230246535,34807.60443862456,34811.580688331516,34814.92885989112,34817.74815580143,34820.12211101859,34822.12106723708,34823.804256448646,34825.22155547576,34826.41496343417,34827.41984587583,34828.26598245541,34828.978449145805,34829.57836112974,34830.08349836573,34830.508832363266,34830.866969767434,34831.16852587665,34831.422439173344,34831.63623617838,34831.81625447459,34831.96783050547,34832.09545771237,34832.20291969446,34832.29340233641,34832.36958822548,34832.433736155304,34832.487748071544,34832.53322544324,34832.571516729695,34832.603757349534,34832.6309033361,34832.65375967653,34832.6730041741,34832.689207541174,34832.702850317946,34832.71433711851,34832.724008626414,34832.73215169514,34832.73900785295,34832.74478046407,34832.74964075859,34832.75373290967,34832.75717830858,34832.7600791644,34832.76252153493,34832.764577878756,34832.766309204075,34832.76776687802,34832.76899415013]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/205/out0.png', dpi=300)
