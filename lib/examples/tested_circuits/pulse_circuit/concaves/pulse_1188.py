import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy = [308.35249040660136,735.8884579555219,1086.1208998034672,1366.1757521173834,1584.8407331965657,1751.0735553906902,1873.3228781664945,1959.2339848640333,2015.5481522599814,2048.1019803106437,2061.877667902833,2061.0775382866464,2049.2079496682964,2029.1642731304994,2003.3123526811967,1973.5640356564854,1941.4456460017745,1908.1590385948632,1874.6353293203001,1841.5816636437955,1809.5215366249838,1778.8292520861294,1749.7591343899492,1722.4701000212863,1697.0461693824418,1673.513459855537,1651.85415485796,1632.0178943331434,1613.93098275701,1597.503762385858,1582.6364551092884,1569.2237344944194,1557.158252204468,1546.3333095013093,1536.6448349923928,1527.9928039523636,1520.2822121638167,1513.4236979681482,1507.3338897721278,1501.9355422981134,1497.1575130897638,1492.9346209058897,1489.2074193925953,1485.921912584194,1483.0292331386074,1480.485299580272,1478.250465043589,1476.2891669446942,1474.5695845402654,1473.0633093578801,1471.7450319170239,1470.5922469304055,1469.5849782205182,1468.7055238550763,1467.9382214542456,1467.269233216963,1466.686349923688,1466.1788129745141,1465.7371533948358,1465.3530466696177,1465.0191822386003,1464.7291464879952,1464.47731810087,1464.2587746717847,1464.0692095460045,1463.904857905615,1463.7624311909096,1463.6390590130243,1463.5322377811658,1463.4397853335424,1463.3598009243058,1463.2906299788199,1463.2308330859576,1463.1791587486684,1463.1345194626888,1463.0959707379745,1463.0626927183607,1463.0339740921945,1463.0091980204932,1462.9878298397452,1462.9694063238376,1462.9535263144119,1462.9398425512757,1462.9280545538331,1462.9179024224252,1462.9091614440817,1462.901637401117,1462.895162493347,1462.8895917956404,1462.8848001821643,1462.880679657221]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(0.0,900.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1188.png', dpi=300)
