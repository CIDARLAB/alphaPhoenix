import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [682.0251584058062,683.853839114852,685.5754372165433,687.1950405674114,688.7175231910014,690.1475534664315,691.4896020279057,692.7479493843006,693.926693267792,695.0297557203011,696.0608899263468,697.0236868006797,697.9215813388581,698.7578587387068,699.5356603003693,700.2579891124445,700.9277155314674,701.5475824617724,702.1202104425583,702.64810254875,703.133649112045,703.5791322683216,703.9867303373809,704.3585220407975,704.6964905634622,705.0025274642126,705.2784364407653,705.5259369539913,705.7466677164024,705.9421900495574,706.1139911149372,706.2634870226813,706.3920258224384,706.5008903804362,706.5913011467384,706.664418816529,706.7213468891307,706.7631341283462,706.7907769275877,706.8052215831491,706.8073664788633,706.7980641852795,706.7781234763912,706.7483112668504,706.7093544725016,706.6619417969795,706.6067254470238,706.5443227790753,706.4753178796369,706.4002630817989,706.319680420248,706.2340630270081,706.1438764700814,706.049560037089,705.9515279659418,705.850170624503,705.7458556411417,705.6389289880078,705.5297160188046,705.4185224627715,705.3056353765319,705.191324055406,705.0758409057364,704.9594222797182,704.8422892741753,704.7246484946775,704.6066927863436,704.4886019326267,704.370543323338,704.2526725931182,704.1351342315229,704.01806216585,703.9015803177949,703.7858031349824,703.670836098385,703.5567762066031,703.4437124379459,703.3317261912196,703.220891706093,703.1112764638834,703.0029415695725,702.8959421158298,702.7903275297978,702.6861419033589,702.5834243075814,702.4822090920145,702.3825261694757,702.284401286951,702.1878562832037,702.092909333665,701.9995751831584]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_138.png', dpi=300)
