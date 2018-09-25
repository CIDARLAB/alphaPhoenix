import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [627.3464975390542,628.7331115646224,630.0359076019388,631.2592555384228,632.4074592659606,633.4846281929534,634.494671228866,635.44130184636,636.3280455909413,637.1582481882865,637.9350836874376,638.6615624344,639.3405387935816,639.9747185836956,640.5666662166408,641.118811538394,641.6334563762882,642.1127807998432,642.5588491037753,642.9736155225577,643.3589296862256,643.7165418272057,644.0481077478846,644.3551935584771,644.6392801945477,644.9017677232924,645.1439794474247,645.3671658152332,645.5725081450969,645.7611221724585,645.934061426974,646.0923204472788,646.2368378405304,646.368499193624,646.4881398427049,646.5965475073522,646.6944647955488,646.7825915853161,646.8615872886486,646.9320730031579,646.9946335566123,647.0498194493383,647.0981486992516,647.1401085940719,647.1761573550926,647.2067257166836,647.2322184255225,647.2530156633826,647.2694743971338,647.2819296594502,647.2906957635678,647.2960674552819,647.2983210052345,647.2977152444016,647.2944925455598,647.2888797533843,647.2810890657091,647.271318868364,647.2597545258899,647.2465691303303,647.2319242101885,647.2159704015489,647.198848083262,647.1806879780034,647.1616117209345,647.1417323976052,647.1211550526652,647.099977170873,647.0782891318186,647.0561746397116,647.0337111295166,647.0109701506584,646.9880177294568,646.9649147113983,646.9417170842923,646.9184762833139,646.8952394788778,646.8720498482511,646.8489468317576,646.8259663743903,646.8031411536061,646.7805007940365,646.7580720698127,646.7358790951689,646.7139435039509,646.6922846186286,646.6709196093793,646.6498636437793,646.6291300276143,646.6087303372926,646.588674544321]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_233.png', dpi=300)
