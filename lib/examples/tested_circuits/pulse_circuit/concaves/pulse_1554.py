import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [668.3963041221473,670.5315513649143,672.5636832859635,674.4973339640593,676.336941935268,678.0867578909229,679.7508521026414,681.3331215818213,682.8372969810848,684.2669492451348,685.6254960184593,686.9162078172619,688.1422139729121,689.306508354113,690.411954874867,691.4612927951944,692.4571418214215,693.4020070127106,694.2982835003517,695.1482610261836,695.9541283063498,696.7179772264341,697.4418068738604,698.1275274132781,698.776963810492,699.3918594103347,699.9738793737197,700.5246139789575,701.0455817922573,701.5382327121895,702.0039508927283,702.4440575493498,702.8598136525155,703.2524225127297,703.6230322612221,703.9727382301706,704.3025852362499,704.6135697711625,704.906642102685,705.1827082896394,705.4426321140844,705.6872369339042,705.9173074588635,706.1335914530889,706.3368013668297,706.5276159002547,706.7066815019374,706.8746138045917,707.0319990005232,707.1793951591766,707.3173334890676,707.4463195463087,707.566834391855,707.6793356995162,707.7842588167088,707.8820177798449,707.9730062861872,708.057598623927,708.1361505621801,708.2090002025277,708.276468793671,708.3388615107049,708.3964682004616,708.4495640943203,708.4984104898186,708.5432554023606,708.584334188255,708.6218701402784,708.656075056905,708.6871497863052,708.7152847461666,708.7406604203544,708.7634478333853,708.7838090036516,708.8018973762939,708.8178582365854,708.8318291046587,708.8439401123671,708.8543143630468,708.863068274912,708.8703119087855,708.8761492808393,708.8806786609941,708.8839928575964,708.8861794889688,708.8873212424074,708.8874961211707,708.8867776799841,708.8852352495682,708.8829341506683,708.8799358980506]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1554.png', dpi=300)
