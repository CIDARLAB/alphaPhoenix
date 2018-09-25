import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [660.5602124875392,662.4143461416717,664.168590777399,665.8275550145108,667.3956572657271,668.8771327290196,670.2760401686301,671.5962684873456,672.8415430930703,674.0154320631144,675.1213521099386,676.1625743523371,677.1422298962353,678.0633152294283,678.9286974346874,679.7411192257409,680.5032038106748,681.2174595873223,681.886284675208,682.5119712885962,683.096709955159,683.6425935847342,684.1516213925913,684.6257026815545,685.0666604872673,685.4762350908028,685.8560874027451,686.2078022227835,686.5328913787723,686.832796749124,687.108893172309,687.3624912471475,687.5948400274872,687.8071296147665,688.0004936518753,688.1760117216321,688.3347116531075,688.477571738936,688.6055228666655,688.7194505671163,688.8201969826257,688.9085627579788,688.9853088567381,689.0511583056086,689.106797869391,689.152879659002,689.1900226749634,689.2188142886869,689.2398116638103,689.253543119769,689.260509439718,689.261185124853,689.2560195971109,689.2454383521697,689.2298440646015,689.2096176469747,689.1851192646399,689.1566893078774,689.1246493230288,689.0893029041791,689.0509365469028,689.009820465539,688.9662093754049,688.9203432413135,688.8724479937121,688.8227362137103,688.7714077882264,688.7186505364325,688.6646408086415,688.609544058734,688.5535153911881,688.4967000837333,688.4392340866154,688.3812444994203,688.3228500263732,688.2641614109954,688.2052818509641,688.1463073939964,688.0873273155414,688.0284244790392,687.9696756794749,687.9111519709282,687.8529189787945,687.7950371973245,687.7375622731073,687.6805452750959,687.6240329517511,687.5680679758593,687.5126891775558,687.4579317660633,687.4038275406406]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_17.png', dpi=300)
