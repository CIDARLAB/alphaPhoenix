import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [399.43570506393996,399.3171239551802,399.21202365472044,399.11859073276383,399.0353288176764,398.96099104707497,398.8945279297293,398.83504712395376,398.78178240655757,398.73406971148415,398.6913285926845,398.65304783843624,398.61877425571754,398.58810387046475,398.56067496621006,398.53616252052234,398.5142737044331,398.4947441914259,398.4773350849502,398.4618303210381,398.44803443876907,398.43577063866184,398.42487906961804,398.415215300385,398.40664894289455,398.39906240322546,398.39234974206835,398.3864156310208,398.3811743942345,398.3765491271987,398.3724708860809,398.36887794213345,398.36571509647575,398.3629330511012,398.36048783234185,398.35834026329593,398.3564554819302,398.3548025017312,398.35335381192044,398.35208501437364,398.35097449451047,398.3500031235418,398.3491539895879,398.3484121553074,398.3477644398086,398.3471992227434,398.34670626861816,398.34627656948396,398.3459022043006,398.3455762133901,398.34529248652075,398.3450456632771,398.34483104448276,398.3446445135493,398.34448246672355,398.3443417512974,398.34421961093364,398.3441136373392,398.3440217275935,398.3439420465071,398.34387299345076,398.3438131731492,398.3437613699898,398.34371652544206,398.3436777182273,398.34364414691817,398.34361511468154,398.3435900159106,398.3435683245207,398.3435495837092,398.3435333970021,398.3435194204303,398.34350735569825,398.3434969442218,398.3434879619283,398.34348021472374,398.3434735345435,398.343467775914,398.34346281296007,398.34345853680156,398.34345485329027,398.34345168104295,398.3434489497331,398.34344659860744,398.3434445751986,398.34344283420864,398.34344133654054,398.34344004845883,398.3434389408625,398.343437988655,398.343437170199]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/457/out0.png', dpi=300)
