import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [204.9380114401431,205.65413382943956,206.33260665777706,206.97335801793136,207.57667769859583,208.1431338022997,208.6735078917526,209.1687438749747,209.62990730562836,210.05815272566952,210.45469731427195,210.82079954582397,211.15774186982154,211.46681664935886,211.74931475976874,212.00651637247864,212.23968354314238,212.4500542956378,212.63883795019822,212.80721148873346,212.95631678615334,213.08725856531296,213.20110295660152,213.2988765623473,213.38156594199108,213.45011744705963,213.50543734586998,213.54839218702506,213.57980935843742,213.60047780510268,213.61114887433686,213.6125372618605,213.60532203609245,213.59014772141416,213.56762542407492,213.53833398690267,213.502821161128,213.46160478546793,213.41517396420244,213.36399023733816,213.30848873712938,213.2490793262369,213.18614771367567,213.1200565454517,213.05114646743198,212.97973715854502,212.90612833288242,212.83060070967844,212.75341695049045,212.67482256319744,212.5950467726839,212.5143033582868,212.43279145826082,212.350696341664,212.26819014818906,212.18543259656488,212.10257166223334,212.0197442250725,211.93707668798535,211.85468556721278,211.77267805525506,211.69115255730532,211.61019920210848,211.52990032816223,211.45033094617563,211.37155917869345,211.2936466777839,211.21664902167396,211.14061609119898,211.06559242691532,210.99161756770323,210.91872637166603,210.8469493201078,210.77631280534848,210.70683940311093,210.6385481301894,210.57145468808432,210.50557169326382,210.4409088946872,210.37747337920175,210.3152697653996,210.25430038649887,210.19456546278906,210.13606326415845,210.07879026320015,210.0227412793705,209.96790961465456,209.9142871811717,209.86186462113602,209.81063141956713,209.76057601012872]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1386.png', dpi=300)
