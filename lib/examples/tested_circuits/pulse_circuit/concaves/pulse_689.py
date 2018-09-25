import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [643.9090983267836,645.2160564920752,646.3895899265926,647.4271993784176,648.3325053096357,649.1108086921149,649.7680468724935,650.3103891378901,650.7440434880899,651.0751527881763,651.309734895445,651.4536467633839,651.512562635436,651.4919610024655,651.3971172568009,651.23310017998,651.0047710821176,650.7167848148013,650.3735921290894,649.9794430099251,649.5383907235798,649.0542963859951,648.5308339092389,647.97149521817,647.379595654539,646.758279504164,646.1105255965301,645.4391529365229,644.7468263359456,644.0360620186418,643.3092331778985,642.5685754686768,641.8161924203387,641.0540607580865,640.2840356234367,639.5078556858009,638.72714813873,637.9434335756303,637.1581307408419,636.3725611528982,635.5879535975987,634.8054484892317,634.0261020989102,633.2508906495297,632.4807142773418,631.7164008605707,630.9587097158727,630.2083351637843,629.4659099645958,628.7320086263519,628.0071505869142,627.2918032722192,626.5863850330434,625.8912679627388,625.2067805985317,624.533210509086,623.8708067711252,623.2197823379814,622.5803163029947,621.9525560607333,621.3366193690366,620.7325963148984,620.1405511872175,619.5605242594421,618.9925334851208,618.4365761093572,617.8926301991319,617.3606560954311,616.8405977900771,616.3323842301099,615.8359305525242,615.3511392521126,614.8779012851068,614.4160971112511,613.9655976768819,613.5262653415192,613.0979547504146,612.6805136554306,612.2737836865592,611.8776010763191,611.4917973392013,611.116199908264,610.7506327309097,610.3949168258071,610.048870802852,609.7123113479968,609.3850536747075,609.066911943744,608.7576996528938,608.4572299982254,608.1653162083666]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_689.png', dpi=300)
