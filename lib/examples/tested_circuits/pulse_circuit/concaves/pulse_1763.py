import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [630.1716989542213,632.2730464755198,634.2587202944787,636.1323891379898,637.8985225075215,639.5618775051993,641.1272718959334,642.59947137471,643.9831297294181,645.2827561813918,646.5026977869436,647.6471306687752,648.7200566447733,649.7253032563659,650.6665259801105,651.5472118539509,652.3706840173094,653.140106830208,653.8584913428763,654.5287009571979,655.1534571684435,655.7353453081535,656.2768202317477,656.7802119106397,657.2477309003248,657.6814736644883,658.0834277414998,658.4554767443992,658.799405189019,659.1169031475766,659.4095707271013,659.6789223735997,659.9263910040374,660.1533319690906,660.3610268502837,660.5506870956159,660.7234574981268,660.880419522101,661.0225944817673,661.1509465774452,661.26638579413,661.3697706675107,661.4619109223767,661.5435699883142,661.6154673975096,661.6782810693833,661.7326494866678,661.7791737674273,661.8184196373904,661.8509193068404,661.8771732561736,661.8976519341024,661.9127973723465,661.9230247205177,661.9287237047753,661.9302600136914,661.9279766146384,661.9221950038827,661.913216393444,661.9013228376558,661.8867783022447,661.8698296786322,661.8507077460454,661.8296280839202,661.8067919369703,661.7823870351949,661.7565883710006,661.7295589355155,661.7014504160838,661.6724038568412,661.6425502841821,661.6120112988543,661.580899636334,661.5493196970581,661.5173680480207,661.4851338971698,661.4526995419735,661.4201407934606,661.3875273769801,661.3549233108653,661.3223872641306,661.289972894278,661.2577291662354,661.2257006534032,661.193927821735,661.1624472977362,661.1312921212212,661.100491983626,661.0700734526356,661.040060183852,661.0104731201847]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1763.png', dpi=300)
