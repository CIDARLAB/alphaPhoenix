import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [399.43570506393996,8222.806621428626,14811.255022757206,20359.71357365238,25032.344855051277,28967.398406523313,32281.30108703528,35072.10178068021,37422.37235141005,39401.65068661993,41068.498107612,42472.23201884082,43654.38505925533,44649.93392718487,45488.334235642666,46194.392016022466,46788.997655144034,47289.743980469764,47711.44677963988,48066.58315833353,48365.660702804205,48617.528368253086,48829.638294547934,49008.2662942865,49158.69753676914,49285.38292170355,49392.07076926662,49481.917722837046,49557.58214568064,49621.302774920194,49674.96495993062,49720.15644496101,49758.21434643077,49790.2647148237,49817.25585170462,49839.98636761541,49859.128811005634,49875.2495673126,49888.82561695037,49900.25864803109,49909.88694137648,49917.99537946513,49924.82387545434,49930.574471669024,49935.41731758384,49939.49570417178,49942.930303572844,49945.82273952398,49948.25859419054,49950.30994036403,49952.03747394774,49953.492309825415,49954.71749424855,49955.749278490126,49956.618191449525,49957.34994294105,49957.96618440029,49958.48514952011,49958.92219374392,49959.290248605816,49959.60020435196,49959.86123216425,49960.08105552,49960.26617871686,49960.422079324344,49960.553370256355,49960.66393626007,49960.75704885963,49960.835463155636,49960.9014993445,49960.95711136971,49961.00394473628,49961.04338519895,49961.07659976483,49961.10457122359,49961.12812722695,49961.14796477788,49961.16467085407,49961.17873977588,49961.19058783273,49961.20056560064,49961.20896831539,49961.216044608256,49961.22200386273,49961.22702240997,49961.23124874632,49961.23480792714,49961.237805267076,49961.240329456254,49961.24245518456,49961.24424535163]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/454/out0.png', dpi=300)
