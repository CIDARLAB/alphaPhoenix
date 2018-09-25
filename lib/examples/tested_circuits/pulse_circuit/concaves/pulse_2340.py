import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [393.74882749495697,434.57041325153125,473.46079465648126,510.461210357116,545.6200434323237,578.9906504107585,610.6297586086174,640.5962616196242,668.9503016827341,695.752563636471,721.0637280227746,744.9440458980166,767.4530080304994,788.6490881682888,808.5895450164916,827.330271137974,844.9256796161736,861.4286212762726,876.8903267420407,891.3603687406858,904.8866409481536,917.5153503570758,929.291020695431,940.2565048598344,950.453004678216,959.9200966011772,968.6957621536427,976.816422169302,984.3169739880632,991.2308309277497,997.5899634505981,1003.4249415367564,1008.7649778540759,1013.6379713786029,1018.070551175307,1022.0881200954339,1025.7148981867435,1028.9739656469342,1031.8873051796434,1034.4758436373224,1036.7594928566411,1038.7571896103857,1040.4869346155442,1041.9658305507721,1043.2101190480146,1044.2352166330097,1045.0557495979165,1045.6855877966063,1046.1378773593854,1046.4250723292307,1046.5589652261242,1046.5507165498886,1046.4108832351394,1046.1494460746446,1045.775836129617,1045.2989601472868,1044.7272250075828,1044.0685612219286,1043.3304455080797,1042.5199224656044,1041.6436253771126,1040.7077961606435,1039.718304498797,1038.680666170229,1037.600060609066,1036.48134771762,1035.3290839575484,1034.1475377442753,1032.9407041691331,1031.712319073242,1030.465872496696,1029.2046215261225,1027.9316025631615,1026.6496430358566,1025.361372574405,1024.06923367213,1022.7754918519586,1021.4822453581068,1020.1914343920837,1018.9048499115377,1017.6241420098856,1016.3508278940848,1015.0862994773325,1013.8318306029148,1012.5885839148635,1011.3576173905392,1010.1398905497128,1008.9362703541992,1007.7475368115772,1006.5743882960312,1005.4174465988557]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_2340.png', dpi=300)
