import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [643.9090983267836,764.0413937833333,877.6758262121004,984.954392972079,1086.1133050937533,1181.4089046786698,1271.1005316250462,1355.444111775746,1434.6892752215783,1509.0779596031398,1578.8437360687694,1644.2115222595244,1705.3975165646643,1762.6092645707317,1816.0458066273738,1865.89787572193,1912.3481263185222,1955.5713816212697,1995.7348909252744,2032.998591406371,2067.51537046887,2099.4313259631153,2128.886022406255,2156.012741915968,2180.9387289773854,2203.7854284596656,2224.668716514615,2243.6991241484425,2260.982053375029,2276.6179859460194,2290.702684717488,2303.327387760516,2314.578995357881,2324.540250054195,2333.2899099445717,2340.902915398862,2347.4505494260516,2353.0005918874826,2357.6174677689964,2361.3623897213733,2364.293495076186,2366.465977540616,2367.9322137703098,2368.741885014139,2368.942094019034,2368.5774773769394,2367.6903134896716,2366.3206263209545,2364.5062850984327,2362.2831001219643,2359.6849148280753,2356.7436942541603,2353.4896100398732,2349.9511220971835,2346.1550570748154,2342.1266837372614,2337.889785373235,2333.466729343394,2328.878533872317,2324.1449321851633,2319.2844340851057,2314.3143850635356,2309.2510230311914,2304.1095327547127,2298.904098079736,2293.647952018408,2288.353424776222,2283.0319897902273,2277.694307848029,2272.3502693545124,2267.0090348108824,2261.6790735684194,2256.368200917287,2251.0836135687755,2245.8319235875215,2240.619190828509,2235.450953931988,2230.3322599278745,2225.2676924996936,2220.2613989566726,2215.317115961215,2210.438194057643,2205.6276210468054,2200.8880442498985,2196.221791703632,2191.6308923276783,2187.1170951042022,2182.6818873081193,2178.3265118256454,2174.051983597588,2169.8591052227903]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_693.png', dpi=300)
