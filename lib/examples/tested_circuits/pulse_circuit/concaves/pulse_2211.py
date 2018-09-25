import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [624.0035852921282,626.0865955401121,628.0378429550444,629.8544530697137,631.5376624918209,633.0906559399056,634.5176506096202,635.8234312555039,637.0130864864644,638.091847791757,639.0649861853688,639.9377435012245,640.7152856737551,641.402670572942,642.0048258078599,642.526533548925,642.972420404383,643.3469510035326,643.6544243386332,643.8989721836296,644.0845590897786,644.2149835854328,644.2938802979015,644.3247227811202,644.3108268813901,644.2553545097692,644.1613177172337,644.0315829898691,643.8688756977529,643.6757846440507,643.4547666710332,643.2081512878436,642.938145291392,642.6468373570532,642.3362025801825,642.0081069530156,641.6643117644641,641.3064779127635,640.9361701229656,640.5548610629806,640.1639353533146,639.764693466858,639.3583555161177,638.946064926148,638.5288919921859,638.1078373216216,637.683835160472,637.2577566049844,636.83041269938,636.4025574210797,635.974890555027,635.5480604589602,635.1226667216769,634.6992627164962,634.2783580522569,633.8604209242928,633.4458803679137,633.0351284169824,632.6285221702262,632.2263857679532,631.8290122818587,631.4366655206221,631.0495817539811,630.6679713579648,630.2920203839438,629.9218920541304,629.557728186125,629.1996505490737,628.8477621539492,628.5021484804322,628.1628786428099,627.8300064972666,627.503571692876,627.1836006685568,626.870107598193,626.5630952860587,626.2625560146308,625.9684723468129,625.68081788453,625.399557985598,625.1246504407069,624.8560461123012,624.5936895370771,624.3375194937614,624.0874695377763,623.8434685043377,623.6054409814793,623.3733077544388,623.1469862227868,622.9263907916312,622.7114332381699]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_2211.png', dpi=300)
