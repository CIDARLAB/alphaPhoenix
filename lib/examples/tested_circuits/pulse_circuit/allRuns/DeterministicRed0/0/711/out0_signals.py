import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [567.2580884956493,8530.02518727482,15235.883042985677,20883.23253229806,25639.15732561784,29644.36724632549,33017.36132983088,35857.93375748227,38250.126379412504,40264.71519030178,41961.30431978819,43390.0894905739,44593.34311809191,45606.66499005481,46460.03552863141,47178.702797189915,47783.92949469824,48293.6220384543,48722.86034620757,49084.343995642506,49388.76795760905,49645.13901882832,49861.04225892602,50042.86546453571,50195.98812002771,50324.94056637858,50433.538037085986,50524.993536742026,50602.01290191384,50666.87485682257,50721.49843236569,50767.49974315681,50806.23980240169,50838.864789272724,50866.33996014227,50889.478206981046,50908.96410785776,50925.374181104344,50939.193942392056,50950.83226937382,50960.63349888918,50968.88761464323,50975.83882677535,50981.6927971553,50986.62272417672,50990.77446707443,50994.27086137548,50997.21535316216,50999.69505967147,51001.78334678318,51003.54199965477,51005.023050724456,51006.27031916638,51007.320707344676,51008.205292623985,51008.95024683549,51009.577610611064,51010.10594549782,51010.5508831206,51010.92558766717,51011.24114537052,51011.50689251194,51011.73069164914,51011.919164242034,51012.07788655882,51012.2115546585,51012.32412333109,51012.41892310621,51012.49875879206,51012.56599246003,51012.62261333037,51012.6702966264,51012.71045313866,51012.7442709654,51012.7727506644,51012.796734856085,51012.81693315378,51012.833943158825,51012.848268141555,51012.8603319314,51012.870491456546,51012.8790473043,51012.886252614386,51012.89232056862,51012.89743069814,51012.90173419519,51012.90535838641,51012.90841049996,51012.9109808381,51012.91314544894,51012.91496837639]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/711/out0.png', dpi=300)
