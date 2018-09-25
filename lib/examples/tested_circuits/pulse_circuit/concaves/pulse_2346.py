import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [393.74882749495697,395.1019304444298,396.38134148456544,397.58530319399796,398.7148855665075,399.7723873187135,400.7607053235379,401.6830262387672,402.54265673032023,403.3429231460745,404.08710972894573,404.77841981922285,405.41995151223443,406.01468276631937,406.56546286764956,407.07500825799843,407.5459013959343,407.9805917401402,408.3813982160873,408.75051271017657,409.09000426131956,409.40182370843115,409.68780861569974,409.94968834364033,410.1890891679683,410.4075393737264,410.60647427122865,410.7872410949014,410.95110375719656,411.09924743825485,411.2327829985409,411.35275120670974,411.4601267788384,411.5558222281299,411.64069152646096,411.71553358085987,411.7810955292778,411.8380758609513,411.8871273673184,411.92885992989846,411.96384315182934,411.99260883990166,412.0156533439763,412.0334397606349,412.04640000781393,412.05493677702793,412.0594253696075,412.06021542316785,412.0576325343023,412.0519797832554,412.04353916608744,412.03257293959604,412.01932488401235,412.00402148824537,411.9868730622078,411.96807478052335,411.947807661687,411.92623948653033,411.9035256596331,411.87981001711853,411.8552255840758,411.8298952846689,411.80393260781227,411.77744223112796,411.75052060573836,411.723256504298,411.69573153452416,411.66802062035237,411.6401924527127,411.61230991180406,411.5844304626294,411.55660652544697,411.5288858226916,411.5013117038267,411.4739234494949,411.44675655625554,411.41984300311225,411.3932115009663,411.36688772605504,411.3408945383742,411.31525218601803,411.2899784963148,411.2650890545804,411.24059737126254,411.2165150381988,411.1928518746691,411.16961606387827,411.14681428046794,411.12445180961737,411.1025326582598,411.081059658908]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_2346.png', dpi=300)
