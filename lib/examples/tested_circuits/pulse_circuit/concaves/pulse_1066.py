import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [668.8841388539614,671.0348472493698,673.0732346610873,674.9971385023563,676.8061924212028,678.5012693158914,680.0841167224194,681.5571073624109,682.923062509266,684.1851234531266,685.3466558970847,686.4111775828435,687.3823027201014,688.2636988357007,689.0590529740624,689.7720450527843,690.4063267705491,690.9655048770468,691.453127907229,691.8726756934567,692.2275511240371,692.5210737319405,692.7564747843774,692.9368936102131,693.0653749533094,693.1448671796877,693.178221197705,693.1681899752267,693.1174285576049,693.0284945062166,692.9038486902442,692.7458563749237,692.5567885581473,692.3388235144475,692.0940485113304,691.8244616678757,691.531973929684,691.2184111377566,690.8855161718694,690.534951151537,690.16829967983,689.7870691171763,689.3926928738894,688.9865327115641,688.569881044701,688.1439632349861,687.709939871595,687.2689090317112,686.8219085161887,686.3699180559352,685.9138614851732,685.4546088782605,684.9929786472067,684.5297395974527,684.065612939846,683.6012742570912,683.1373554232587,682.6744464752124,682.2130974350691,681.7538200830277,681.2970896801149,680.843346640577,680.3929981538181,679.9464197559355,679.5039568510417,679.0659261826838,678.6326172557841,678.2042937096232,677.7811946424753,677.3635358885857,676.9515112482477,676.5452936717969,676.1450363983977,675.750874050536,675.3629236851775,674.9812858025796,674.6060453137744,674.2372724677606,673.8750237394611,673.5193426795128,673.1702607269654,672.8277979859721,672.4919639675526,672.1627582975095,671.8401713915755,671.5241850988587,671.2147733146448,670.9119025636066,670.6155325544499,670.3256167070198,670.0421026528663]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1066.png', dpi=300)
