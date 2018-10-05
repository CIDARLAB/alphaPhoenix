import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [327.40838656527797,835.4189784611794,1231.7083154820457,1525.7959376646975,1733.0806353907694,1868.9939044980008,1947.4861499297601,1980.6579235151435,1978.7747865875274,1950.417164769579,1902.6704274210401,1841.3174415613707,1771.019478968919,1695.4812111581793,1617.5992033343214,1539.5943622840834,1463.1289515944732,1389.4088178000043,1319.2715891855885,1253.261797953599,1191.6940613279123,1134.7055831648224,1082.2992854567883,1034.3788553242052,990.7769162747436,951.2774232038369,915.6332561714695,883.5798609924498,854.84566317321,829.1598702316475,806.2581779891102,785.8868094870129,767.8052402896518,751.7879001263788,737.6250870120361,725.1232847434667,714.1050371212748,704.4085011614676,695.8867758875891,688.407082341649,681.8498533842965,676.1077780448725,671.0848340781356,666.6953335167618,662.8629989796824,659.5200829715094,656.6065381077086,654.0692428905699,651.8612851490243,649.9413033813514,648.2728848727018,646.8240184919528,645.5665994174749,644.475982628284,643.5305817689159,642.7115099076051,642.0022587217021,641.3884127330637,640.8573953568615,640.398243702401,640.0014092606461,639.6585818199585,639.362534161446,639.1069852926432,638.8864801790169,638.6962841242582,638.53229013071,638.3909377395468,638.2691420060197,638.164231408128,638.0738936177503,637.996128182012,637.9292052700911,637.8716297374812,637.8221098466482,637.7795300608522,637.7429273973617,637.7114708881483,637.6844437510894,637.6612279234197,637.6412906522592,637.6241728751087,637.6094791567566,637.5968689785825,637.5860492022149,637.5767675523201,637.5688069832092,637.561980811539,637.5561285127969,637.5511120924155,637.5468129542169]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/172/out0.png', dpi=300)
