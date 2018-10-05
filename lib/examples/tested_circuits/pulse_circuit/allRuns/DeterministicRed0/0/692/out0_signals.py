import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [343.7241048112919,964.1274409196842,1434.2235685256362,1771.4371179868172,1999.029966432552,2138.5122080411857,2208.4614558968597,2224.5076268416624,2199.62030648637,2144.4617150891186,2067.7251437684567,1976.4332776914412,1876.193733553721,1771.4181363565256,1665.512562760433,1561.045198081603,1459.8944274828111,1363.3787739892807,1272.3693869028127,1187.385834819005,1108.6763191428631,1036.283783578726,970.0996141083695,909.9066815282777,855.4134093482485,806.2804017716126,762.1409811305656,722.6167907375477,687.3294352314781,655.9089650643774,627.9998680388526,603.2651087130491,581.3886542911156,562.0768408831929,545.058864201018,530.0866214722219,516.9340845109704,505.3963456860403,495.2884474787813,486.44408118526883,478.7142200187038,471.96573561510894,466.0800339624952,460.95173648932195,456.4874239774461,452.6044546853267,449.2298632403928,446.2993432021787,443.7563134763617,441.55106678190646,439.639996981645,437.9849011524186,436.55235169030544,435.3131334355531,434.24174069339665,433.3159290675069,432.5163171701139,431.8260334938424,431.2304039993046,430.71667626988705,430.2737763960662,429.8920950649599,429.56329963864624,429.2801693014069,429.03645063767596,428.8267312667032,428.6463294054661,428.4911974576847,428.35783793399503,428.24323019693696,428.1447666952184,428.0601975057266,427.98758214008626,427.92524769637737,427.87175254710826,427.8258548528531,427.78648527822503,427.75272336413957,427.7237770786191,427.6989651286311,427.6777016685017,427.6594830870762,427.6438765967315,427.63051038322885,427.6190651068028,427.6092665723508,427.60087941057395,427.5937016328481,427.58755994086727,427.58230568789594,427.5778114024072]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/692/out0.png', dpi=300)
