import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [201.27339577895944,201.8571826257303,202.41135536027988,202.93730893779195,203.43637672360336,203.9098329989242,204.35889537967805,204.7847271501246,205.1884395130474,205.57109375838624,205.93370335227172,206.27723594847845,206.60261532435646,206.91072324333092,207.2024012460773,207.47845237248683,207.73964281653414,207.98670351615013,208.22033168018487,208.44119225452332,208.6499193293882,208.84711748983284,209.0333631113894,209.2092056028012,209.37516859772438,209.5317510972424,209.6794285649913,209.8186539766474,209.94985882548298,210.07345408564717,210.18983113478208,210.29936263753618,210.40240339148883,210.49929113695305,210.59034733207613,210.6758778946113,210.75617391168765,210.83151231886094,210.90215654968307,210.96835715698487,211.03035240702462,211.0883688476132,211.14262185128578,211.1933161345515,211.24064625421389,211.2847970817168,211.32594425643583,211.36425461879912,211.39988662408723,211.43299073773008,211.4637098128852,211.49217945105244,211.51852834644933,211.54287861484275,211.56534610750487,211.58604071093427,211.60506663295752,211.62252267580092,211.6385024966989,211.6530948565812,211.66638385736027,211.67844916831692,211.68936624206273,211.6992065205381,211.70803763148436,211.71592357581116,211.72292490626216,211.72909889776514,211.73449970983592,211.73917854139088,211.74318377830625,211.7465611340492,211.74935378369105,211.75160249160038,211.75334573309996,211.75461981036005,211.75545896278885,211.75589547216867,211.75595976277683,211.75568049671884,211.7550846646924,211.75419767239023,211.75304342274188,211.75164439418444,211.75002171514484,211.74819523490817,211.74618359103812,211.74400427350943,211.74167368570363,211.7392072024144,211.73661922500094]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_320.png', dpi=300)
