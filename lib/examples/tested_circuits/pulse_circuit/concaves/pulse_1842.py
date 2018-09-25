import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [398.35081899232495,399.7077807954536,400.97928531832946,402.16251598979585,403.2592669385785,404.2729517167743,405.2076602276894,406.06774818169924,406.85762874154193,407.58165766527344,408.2440673792307,408.84892889915943,409.4001305603171,409.9013673213589,410.35613690828615,410.7677404577392,411.13928613804904,411.47369473016556,411.7737064720948,412.0418886822414,412.2806438202685,412.49221774313816,412.6787079837953,412.842071929929,412.98413481647844,413.1065974721001,413.2110437794148,413.2989478234127,413.37168071322975,413.4305170705913,413.4766411842146,413.5111528338991,413.5350727912694,413.5493480064624,413.55485649167565,413.55241191358107,413.542767907283,413.5266221248563,413.5046200316127,413.4773584631773,413.4453889562458,413.40922086558254,413.3693242794364,413.32613274510874,413.2800458159387,413.23143143047446,413.1806281340928,413.12794715282234,413.0736743286207,413.0180719248593,412.96138031028676,412.9038195292715,412.84559076567393,412.7868777072623,412.72784781717337,412.66865351852147,412.60943329788415,412.55031273303524,412.491405449957,412.4328140138459,412.3746307585218,412.31693855836863,412.25981154666607,412.2033157839201,412.1475098795638,412.0924455701792,412.03816825718155,411.9847175067133,411.93212751431196,411.88042753674586,411.8296422932516,411.7797923382567,411.73089440753205,411.6829617395884,411.63600437400817,411.5900294282902,411.545041354679,411.50104217835013,411.45803171823195,411.4160077916551,411.3749664039426,411.33490192397795,411.2958072467166,411.25767394354256,411.2204924013103,411.18425195085507,411.14894098570096,411.1145470716484,411.0810570478746,411.0484571201387,411.01673294664283]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1842.png', dpi=300)
