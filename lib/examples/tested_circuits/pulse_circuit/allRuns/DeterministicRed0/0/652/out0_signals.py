import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [343.7241048112919,979.9922744445813,1515.800535627169,1967.0096862795535,2346.976508738687,2666.94907153622,2936.399629047037,3163.3049691574934,3354.3825043640477,3515.2890927162766,3650.788471824186,3764.892260611369,3860.978701393994,3941.8926562146294,4010.029816673971,4067.407619372598,4115.724965679714,4156.412513247392,4190.675027528955,4219.527047426672,4243.822919580313,4264.282090985551,4281.510408380291,4296.0180548154385,4308.234654317463,4318.52199173656,4327.18472429159,4334.479401882647,4340.622063185336,4345.794632386233,4350.150305919252,4353.818088667105,4356.906613916196,4359.507360151837,4361.697359926496,4363.541480998155,4365.094347274116,4366.401956432722,4367.503042116101,4368.430221025308,4369.210958881316,4369.868383852842,4370.421971536104,4370.888121768732,4371.28064435761,4371.611168103581,4371.889485234935,4372.123841449123,4372.321180151659,4372.487348124959,4372.6272687177825,4372.745087684213,4372.844295991211,4372.927833231786,4372.998174706483,4373.057404751617,4373.107278488731,4373.149273820287,4373.184635213757,4373.21441057095,4373.239482274371,4373.260593330241,4373.27836938254,4373.29333725017,4373.305940536348,4373.316552772617,4373.325488486846,4373.333012523098,4373.339347889454,4373.344682366303,4373.349174070834,4373.352956142642,4373.3561406892095,4373.358822108198,4373.361079884962,4373.36298094818,4373.364581653394,4373.3659294532345,4373.367064303802,4373.368019848918,4373.368824417283,4373.369501862131,4373.370072268244,4373.370552547285,4373.370956939077,4373.371297433702,4373.371584126925,4373.371825519452,4373.372028768933,4373.3721999021445,4373.372343993652]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/652/out0.png', dpi=300)
