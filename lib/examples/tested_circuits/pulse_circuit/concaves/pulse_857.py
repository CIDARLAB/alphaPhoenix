import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [199.74050889931402,200.33682707685503,200.90365392756948,201.44228300006154,201.95395938058016,202.43988090179178,202.90119938187257,203.3390218851647,203.75441199671516,204.14839110397122,204.5219396797612,204.87599856145042,205.21147022184098,205.52922002799153,205.83007748467193,206.1148374596517,206.384261388448,206.63907845654342,206.87998675742233,207.10765442507707,207.32272073990362,207.52579720714328,207.71746860723826,207.89829401765303,208.06880780587855,208.22952059347867,208.3809201911652,208.52347250499705,208.6576224138951,208.78379461874735,208.90239446344987,209.01380872829026,209.11840639613237,209.2165393919047,209.3085432959308,209.39473803167047,209.4754285284634,209.55090535988748,209.621445358356,209.68731220658984,209.74875700660667,209.80601882687222,209.8593252282599,209.9088927694629,209.9549274924987,209.9976253889408,210.03717284750428,210.0737470836039,210.1075165514926,210.1386413395782,210.16727354950345,210.19355765956374,210.2176308730215,210.23962345186533,210.25965903654622,210.2778549522108,210.294322501936,210.30916724745688,210.3224892778635,210.33438346672938,210.3449397181203,210.354243201917,210.3623745788728,210.36941021581197,210.3754223913629,210.38047949260516,210.38464620299794,210.38798368194355,210.39054973632804,210.39239898436807,210.3935830120821,210.39415052269135,210.3941474792459,210.39361724075954,210.39260069212628,210.391136368082,210.38926057146324,210.3870074860071,210.38440928392532,210.38149622847746,210.37829677175887,210.37483764791048,210.37114396195014,210.36723927441548,210.36314568200294,210.35888389437787,210.35447330732512,210.34993207240223,210.34527716324973,210.34052443870848,210.3356887028861]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_857.png', dpi=300)
