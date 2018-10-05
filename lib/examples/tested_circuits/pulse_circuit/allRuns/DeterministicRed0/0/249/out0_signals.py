import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [464.64026548145284,460.62521890509294,447.9691694958879,428.3451745010251,403.76009592493176,376.0819125133939,346.9159284223514,317.553485640743,288.96774571740525,261.8407997295189,236.60782656256148,213.50696228988426,192.6273322169918,173.95113185460383,157.3881633159318,142.80280363785602,130.0342051143713,118.91085397278418,109.26064974361181,100.91755753202162,93.72571788004439,87.54172317618827,82.2356095274546,77.69097786485354,73.80454919425044,70.48537402211367,67.65385138174481,65.24066465944178,63.18570601848365,61.43703563738358,59.94990378836438,58.68585107575224,57.611893466415836,56.69979296667993,55.92541110378457,55.26814014230413,54.71040575817552,54.2372343792486,53.83587834714235,53.495492293759256,53.20685453969332,52.962127829680064,52.754654267026034,52.57877985823448,52.42970460798661,52.30335459823107,52.1962729392366,52.105526889597044,52.028628804850854,51.96346889786729,51.908258076254725,51.86147936837135,51.8218466632929,51.78826967489827,51.75982419948435,51.73572687319093,51.715313752890125,51.69802214466868,51.683375189906556,51.67096879226063,51.660460531370504,51.651560262357506,51.64402214552395,51.63763788922947,51.632231021718574,51.62765203554775,51.62377427194312,51.62049043253323,51.617709622979376,51.61535484752323,51.613360885774675,51.61167249350281,51.610242878050435,51.60903240650569,51.608007511137416,51.60713976200555,51.60640508124118,51.60578307737693,51.60525648140401,51.60481066902528,51.60443325594246,51.60411375502305,51.60384328589463,51.60361432895784,51.603420517021554,51.603256458825136,51.60311758956248,51.60300004428226,51.60290055066647,51.60281633822328,51.60274506138355]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/249/out0.png', dpi=300)
