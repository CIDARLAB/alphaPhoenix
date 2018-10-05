import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy = [221.83504816568626,1569.833864597469,2676.5638447664146,3578.7265224874204,4307.84870910185,4890.6366680585,5349.699723987314,5704.1937518190425,5970.37091086108,6162.04470606641,6290.981806802382,6367.23127837156,6399.4005595069675,6394.886200253268,6360.066188369796,6300.459661001129,6220.85891657229,6125.437899615253,6017.840719674503,5901.253276506188,5778.460687769515,5651.892937554643,5523.660962632466,5395.585240317454,5269.218802449462,5145.866441557818,5026.601670711526,4912.282734700541,4803.568651406664,4700.935910159248,4604.696102214998,4515.014444282206,4431.928910339276,4355.369528212412,4285.177328423789,4221.122442949415,4162.920920918466,4110.249933642953,4062.761160736009,4020.092264985169,3981.8764643766144,3947.7502890659134,3917.3596675043777,3890.36452078631,3866.4420612042272,3845.28899258705,3826.622802840222,3810.1823238146785,3795.727715059865,3783.0400076711644,3771.9203240383467,3762.188869908706,3753.683777445308,3746.259862220786,3739.787343449398,3734.1505651832977,3729.2467465479785,3724.984781179682,3721.2840996445366,3718.0736035529294,3715.2906761302575,3712.8802709805664,3710.794078517022,3708.989767888071,3707.430301077063,3706.0833150918493,3704.9205677028463,3703.917441962105,3703.0525046842135,3702.3071141459136,3701.665072428105,3701.1123180524614,3700.6366548325254,3700.2275131486454,3699.87574015449,3699.573415720399,3699.3136912087266,3699.090648453753,3698.89917658053,3698.734864541359,3698.5939074745183,3698.4730251971273,3698.369391332991,3698.280571747535,3698.204471116414,3698.139286593138,3698.0834676651825,3698.035681399041,3697.994782371621,3697.9597866756053,3697.929849460647]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(0.0,900.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1078.png', dpi=300)
