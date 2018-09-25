import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [695.4927126146227,698.6197316953997,701.4705613872509,704.0492512046735,706.36557974209,708.4303888271886,710.254760256259,711.8497260036655,713.2261399916758,714.3946145816815,715.3654875064766,716.1488046945885,716.7543120103996,717.1914522550111,717.4693653822623,717.5968907259727,717.5825705021275,717.4346541225931,717.1611030226474,716.769595808541,716.267533598202,715.6620454721393,714.9599939808719,714.1679806748949,713.2923516364463,712.3392030012272,711.3143864640974,710.2235147665257,709.0719671658645,707.8648948877579,706.6072265635106,705.3036736542645,703.9587358635055,702.5767065388836,701.1616780636657,699.7175472374036,698.2480206446611,696.7566200099135,695.2466875360531,693.721391223321,692.183730164937,690.6365398152386,689.0824972257624,687.5241262444027,685.963802672573,684.4037593751594,682.8460913379945,681.2927606675884,679.7456015279239,678.2063250092473,676.676523923961,675.1576775249403,673.6511561418516,672.1582257313269,670.6800523371612,669.2177064570199,667.7721673124804,666.3443270195824,664.9349946574032,663.5449002325341,662.1746985376728,660.8249729028923,659.4962388384781,658.188947568547,656.9034894549709,655.6401973114239,654.3993496076497,653.1811735643156,651.9858481390618,650.8135069045891,649.6642408198428,648.5381008955437,647.4351007555051,646.3552190953295,645.2984020402333,644.2645654038781,643.2535968502027,642.2653579603548,641.2996862069086,640.3563968376322,639.43528467113,638.536125806739,637.6586792510959,636.8026884638283,635.9678828248353,635.1539790256444,634.3606823873289,633.5876881074698,632.8346824386348,632.1013438008292,631.3873438303549]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1715.png', dpi=300)
