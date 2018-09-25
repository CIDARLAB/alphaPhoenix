import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [630.4649612852767,631.9551490806181,633.3621995647094,634.6903804004461,635.9437537743416,637.1261856768639,638.2413547967232,639.2927610431212,640.2837337096053,641.217439292804,642.0968889789558,642.9249458107672,643.7043315467744,644.4376332250156,645.1273094424606,645.7756963612838,646.38501345272,646.9573689888884,647.4947652926343,647.9991037550986,648.4721896304013,648.9157366164975,649.3313712309556,649.720636990095,650.0849983996236,650.4258447646215,650.7444938264308,651.0421952337362,651.3201338548492,651.579432937946,651.8211571257544,652.0463153309393,652.2558634781915,652.4507071187976,652.6317039232376,652.7996660571431,652.9553624457341,653.0995209316493,653.2328303308886,653.3559423913921,653.469473658599,653.5740072521506,653.6700945577278,653.7582568378516,653.8389867653133,653.9127498827456,653.9799859917032,654.0411104744711,654.0965155516906,654.1465714787498,654.1916276837712,654.2320138498951,654.2680409444481,654.3000021974703,654.3281740319653,654.3528169481358,654.3741763637659,654.3924834128159,654.4079557042047,654.4207980426646,654.431203113469,654.439352132757,654.4454154650929,654.4495532098337,654.4519157577973,654.4526443196658,654.4518714274806,654.4497214105378,654.4463108469197,654.4417489918488,654.4361381839941,654.4295742308044,654.4221467738986,654.4139396354878,654.4050311467666,654.3954944591602,654.3853978392746,654.3748049483598,654.3637751070542,654.352363546141,654.3406216440188,654.3285971515486,654.3163344049105,654.3038745270737,654.2912556184535,654.2785129373013,654.2656790703488,654.2527840941993,654.2398557279391,654.2269194774155,654.2139987716088]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1242.png', dpi=300)
