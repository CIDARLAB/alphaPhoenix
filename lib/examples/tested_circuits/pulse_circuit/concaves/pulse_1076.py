import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy = [221.83504816568626,246.84740449084245,265.9215635107253,280.02797086536043,290.0195894097395,296.6156113052404,300.4203774732004,301.9419764084562,301.60844625340803,299.7814332194356,296.76749252651786,292.82729444980055,288.1830186767827,283.02422515975,277.5124852634301,271.78504211615723,265.9577427268612,260.1274481009901,254.3740851003198,248.76246008890865,243.3439143057371,238.15786776107467,233.2332739560532,228.58999180611087,224.24007253610242,220.18895610580063,216.4365719286462,212.97834056260953,209.80607545872004,206.90878603753578,204.2733850379487,201.88530424132057,199.72902345035277,197.78851815355426,196.04763174684103,194.49037856342372,193.101184277563,191.8650704639412,190.7677901754631,189.79592132472092,188.93692441503586,188.1791707822736,187.5119470082859,186.92544059098765,186.41071133308776,185.95965228883284,185.56494350263827,185.22000120610682,184.9189246272878,184.6564421122035,184.42785786620533,184.22900029009523,184.05617260928696,183.90610626830812,183.77591738171859,183.66306638994362,183.56532095871427,183.48072207832175,183.40755325882876,183.3443126754455,183.28968809077563,183.242534364445,183.2018533531353,183.16677600312053,183.13654644129954,183.11050787804743,183.08809014485922,183.06879870087718,183.0522049543077,183.03793775695044,183.02567594220358,183.0151417887138,183.00609530311755,182.998329225956,182.99166467476084,182.9859473474754,182.98104421778032,182.97684066155534,182.9732379606509,182.97015113640586,182.9675070709712,182.96524287953054,182.9633045010013,182.96164547876475,182.96022590653877,182.95901151764147,182.9579728986115,182.9570848106148,182.95632560419637,182.95567671480956,182.95512222819892]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(0.0,900.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1076.png', dpi=300)
