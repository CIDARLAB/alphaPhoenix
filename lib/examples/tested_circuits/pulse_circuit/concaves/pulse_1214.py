import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [696.5840284521588,1818.6477804635638,2885.2621323101785,3898.1244510066017,4859.323082370162,5770.975755323664,6635.163022281985,7453.906222758188,8229.159094002722,8962.804842171312,9656.655749260988,10312.454067746696,10931.873602499909,11516.521664987784,12067.941223472171,12587.613145659701,13076.958470751115,13537.340671518927,13970.067881459483,14376.395071142724,14757.526163758883,15114.616083754376,15448.772735076687,15761.05890734858,16052.494109538884,16324.05633156713,16576.683734887964,16811.276273525706,17028.697247323416,17229.774789370087,17415.303289700525,17586.04475744339,17742.730123636615,17886.060486946284,18016.708304521337,18135.31853019813,18242.509702239193,18338.87498275276,18424.983150895845,18501.379551915197,18568.587004029123,18627.106665099054,18677.41886098432,18719.98387741662,18755.242717173103,18783.6178242686,18805.51377682886,18821.317950247612,18831.401152171024,18836.11823079374,18835.80865789146,18830.797087955292,18821.393894734178,18807.89568643217,18790.585800748624,18769.73478089061,18745.600833628723,18718.430270409794,18688.457932482976,18655.907600939605,18620.992392511926,18583.915141921727,18544.868771517064,18504.036648883837,18461.592933069067,18417.702910004664,18372.523317674077,18326.202661519972,18278.881520548766,18230.69284454784,18181.76224279352,18132.20826459228,18082.14267196483,18031.67070475178,17980.891338391568,17929.8975345953,17878.776485119797,17827.609848818873,17776.47398213404,17725.44016316907,17674.57480947825,17623.93968968544,17573.59212904039,17523.585209009678,17473.967960992308,17424.78555424404,17376.079478090112,17327.887718502738,17280.244929117507,17233.182596761784,17186.729201567694]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1214.png', dpi=300)
