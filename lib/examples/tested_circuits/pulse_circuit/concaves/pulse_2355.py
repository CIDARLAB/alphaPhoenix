import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [393.74882749495697,397.1841405308254,400.4146310039,403.4364335732194,406.2545995439831,408.8770163024968,411.3126822218221,413.57099139956597,415.6613814229193,417.59314310916596,419.37531283668113,421.01661096987584,422.5254076990261,423.90970594447947,425.1771352259781,426.33495272481804,427.3900491139766,428.3489575484552,429.2178647231466,430.0026232409563,430.7087647584843,431.34151353052977,431.9058000823724,432.4062748154155,432.8473214071025,433.23306990644977,433.5674094563718,433.8540005961685,434.09628711413876,434.29750743274604,434.460705518123,434.58874131273757,434.68430069529643,434.7499049758658,434.7879199370469,434.80056443409717,434.7899185683174,434.7579314489753,434.7064285596061,434.6371187448162,434.55160083377507,434.45136991646757,434.3378232885398,434.2122660802277,434.0759165844472,433.9299112986604,433.7753096946299,433.61309872965273,433.4441971123256,433.269459335355,433.0896794873829,432.9055948552649,432.7178893277138,432.5271966107087,432.33410326457056,432.139151572126,431.94284224691444,431.7456369899459,431.54796090309054,431.3502047667662,431.15272718920005,430.95585663416455,430.759893333728,430.56511109222254,430.3717589873053,430.1800629736809,429.99022739476067,429.80243640725547,429.6168553234346,429.4336318755327,429.25289740654875,429.0747679914576,428.8993454926368,428.7267185531145,428.5569635310477,428.39014537866154,428.2263184687067,428.06552737132955,427.9078075840951,427.75318621775756,427.6016826402326,427.4533090810978,427.3080711988202,427.165968612794,427.02699540216065,426.8911405732766,426.7583884975951,426.62871932163375,426.5021093506095,426.37853140723854,426.2579551671176]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_2355.png', dpi=300)
