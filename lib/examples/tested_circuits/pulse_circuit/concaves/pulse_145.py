import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [682.0251584058062,683.7881992548947,685.4471707977466,687.0069813643031,688.4723375143969,689.8477513844879,691.1375478072297,692.3458712078457,693.4766922816452,694.5338144572855,695.520880150586,696.441376813849,697.298642785739,698.095872946828,698.8361241859415,699.5223206824361,700.1572590095088,700.7436130635991,701.2839388248793,701.7806789537592,702.2361672282469,702.6526328269198,703.032204462158,703.3769143682024,703.6887021484864,703.96941848659,704.2208287250606,704.4446163162306,704.6423861490651,704.8156677559597,704.9659184033063,705.0945260695424,705.2028123142954,705.2920350421351,705.363391164349,705.418019162058,705.4570015538994,705.4813672714068,705.4920939451332,705.4901101044717,705.4762972940441,705.451492109447,705.4164881550598,705.3720379265433,705.3188546205797,705.2576138743304,705.1889554370135,705.1134847759344,705.0317746192304,704.9443664375272,704.8517718666343,704.754474073347,704.6529290663572,704.5475669542167,704.4387931522346,704.3269895401374,704.212515572259,704.0957093419768,703.9768886020559,703.8563517425104,703.7343787275406,703.6112319930584,703.4871573062587,703.362384588657,703.237128703958,703.1115902120838,702.985956090643,702.8604004250801,702.7350850687051,702.610160273763,702.4857652946633,702.3620289644538,702.239070245583,702.1169987559648,701.99591527132,701.8759122047367,701.757074064362,701.6394778901006,701.5231936701688,701.4082847383221,701.2948081525432,701.1828150559514,701.0723510206666,700.963456375333,700.8561665169844,700.7505122079059,700.6465198581229,700.5442117941269,700.4436065144222,700.3447189324571,700.2475606074822]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_145.png', dpi=300)
