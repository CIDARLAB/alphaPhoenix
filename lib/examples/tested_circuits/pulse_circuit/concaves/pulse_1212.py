import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [696.5840284521588,712.6418257204448,727.7117038690982,741.8091850109083,754.9694955978645,767.2316109475504,778.635202896096,789.219553473595,799.0230777182791,808.0830940019009,816.4357119939415,824.1157825248749,831.1568823497164,837.591319528212,843.4501513419151,848.7632099347263,853.5591326903854,857.8653954340167,861.70834720211,865.1132457417332,868.104293171279,870.7046714163873,872.9365771582567,874.8212561171591,876.3790365540501,877.6293619157383,878.5908225794593,879.2811866744283,879.7174299734947,879.915764859083,879.8916683753898,879.659909384192,879.2345748452242,878.6290952443871,877.8562691943798,876.9282872329742,875.8567548442662,874.6527147279801,873.3266683413898,871.8885967377418,870.3479807242593,868.71382036196,866.9946538286242,865.1985756653722,863.3332544264285,861.4059497508186,859.4235288739319,857.3924825961268,855.3189407248373,853.2086870059651,851.0671735597199,848.8995348354814,846.7106010997172,844.5049114704794,842.2867265115353,840.060040398741,837.8285926708592,835.5958795766276,833.3651650295253,831.1394911813319,828.9216886252508,826.7143862390537,824.5200206784043,822.3408455302377,820.1789401357931,818.0362180926394,815.9144354447799,813.8151985696786,811.7399717708164,809.6900845841628,807.6667388067316,805.6710152551786,803.7038802622048,801.7661919183283,799.8587060664132,797.98208205616,796.1368882655943,794.3236073964289,792.5426415500193,790.79431709048,789.0788893013885,787.3965468423651,785.7474160116842,784.1315648209471,782.5490068877176,780.9997051519109,779.4835754216031,778.000489753821,776.5502796757594,775.1327392517669,773.7476280013354]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1212.png', dpi=300)
