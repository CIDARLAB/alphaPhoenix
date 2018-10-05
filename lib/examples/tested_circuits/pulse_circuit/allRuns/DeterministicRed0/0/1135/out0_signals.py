import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [210.93897812097362,2058.693635780456,3609.063899458942,4908.436451354707,5996.042886875901,6904.949267466706,7663.017486935317,8293.72670234656,8816.868362252098,9249.133008759478,9604.605407639392,9895.182245329908,10130.924422651897,10320.354015463327,10470.704279583413,10588.129643317146,10677.881444524208,10744.454220708227,10791.70663635713,10822.960609979755,10841.08184924559,10848.544767560232,10847.484580496946,10839.739206296772,10826.883373301423,10810.25704327941,10790.989895120163,10770.023204563351,10748.130043080537,10725.934344763178,10703.92908645102,10682.493609876234,10661.909985494112,10642.378263564347,10624.030459705293,10606.943158875363,10591.148675273324,10576.64476215943,10563.402916053858,10551.375359842705,10540.500816385196,10530.709200737727,10521.925365148309,10514.07202952745,10507.07202330922,10500.849954329899,10495.333408177265,10490.45376859658,10486.146736827566,10482.352615747935,10479.01641374422,10476.087813482183,10473.521042251,10471.274673273225,10469.311381223162,10467.597670070707,10466.10358714564,10464.802433878214,10463.67048090072,10462.6866929861,10461.832467559305,10461.091389162906,10460.449001219053,10459.892595645331,10459.411020301677,10458.99450382709,10458.63449713396,10458.323530635884,10458.05508616906,10457.823482509106,10457.623773369776,10457.451656785897,10457.303394820568,10457.175742589356,10457.065885656253,10456.971384923361,10456.890128205616,10456.820287751381,10456.760283035392,10456.708748218154,10456.66450372374,10456.626531445667,10456.593953142983,10456.566011636527,10456.542054459158,10456.521519653308,10456.503923444985,10456.488849555417,10456.475939940188,10456.464886771353,10456.455425500671]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/1135/out0.png', dpi=300)
