import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [624.0035852921282,625.4363327239867,626.7514912313143,627.9454842045674,629.0206318457597,629.9811062522682,630.8318468421966,631.5781194993642,632.2252996513504,632.7787530683257,633.2437657600565,633.6255010653529,633.9289729134566,634.1590292395376,634.3203420491008,634.4174019816578,634.4545159981872,634.4358072804466,634.3652167190384,634.2465055533345,634.0832588500151,633.8788895912161,633.6366432020137,633.3596023887648,633.0506921900999,632.7126851646826,632.348206656541,631.9597400914429,631.5496322675212,631.1200986109191,630.6732283731836,630.2109897518619,629.7352349195455,629.247704949682,628.750034629973,628.2437571562385,627.7303087013277,627.2110328550756,626.6871849324916,626.1599361483596,625.6303776572736,625.0995244588423,624.5683191684013,624.0376356540776,623.5082825414942,622.9810065877614,622.4564959267177,621.9353831876427,621.4182484898841,620.9056223160239,620.3979882663539,619.8957856975583,619.3994122485898,618.9092262568034,618.4255490674653,617.9486672397891,617.4788346526743,617.0162745133309,616.5611812719665,616.1137224457001,615.6740403548428,615.2422537746506,614.8184595056204,614.4027338653475,613.9951341049207,613.595699752766,613.2044538888001,612.8214043516839,612.4465448819054,612.0798562033526,611.7213070459674,611.3708551119986,611.0284479883039,610.6940240070753,610.3675130572898,610.0488373491158,609.7379121334312,609.4346463785373,609.1389434060804,608.8507014881242,608.5698144072416,608.2961719814303,608.0296605555861,607.7701634611991,607.51756144588,607.2717330742513,607.0325551016815,606.7999028222781,606.5736503924959,606.3536711316608,606.1398378006501]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_2216.png', dpi=300)
