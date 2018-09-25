import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [627.3464975390542,628.7803368930508,630.1320066323719,631.405699619796,632.605419914375,633.7349890094906,634.7980522164121,635.7980851063967,636.7383999458161,637.6221520749046,638.4523461929338,639.2318425219772,639.9633628286424,640.649496288754,641.2927051843558,641.8953304258396,642.4595968947173,642.9876186046989,643.4814036804281,643.942859154568,644.373795584986,644.7759314946161,645.1508976372244,645.5002410928038,645.8254291967053,646.1278533068985,646.408832413954,646.6696165984855,646.9113903408722,647.1352756881258,647.3423352827767,647.5335752586284,647.7099480081883,647.872354826518,648.0216484361626,648.1586353977367,648.284078410631,648.3986985082053,648.5031771517127,648.598158227085,648.6842499485825,648.762026673196,648.8320306295568,648.8947735649917,648.9507383142328,649.0003802931711,649.0441289209219,649.0823889733479,649.115541871073,649.1439469049021,649.1679424014495,649.1878468316711,649.2039598648894,649.2165633707928,649.2259223717947,649.2322859480379,649.2358880972345,649.2369485514428,649.2356735527919,649.2322565900782,649.2268790980814,649.2197111213591,649.2109119442113,649.2006306884266,649.1890068803528,649.1761709887662,649.1622449349483,649.1473425763162,649.1315701648906,649.115026781828,649.0978047491898,649.0799900200633,649.0616625481039,649.0428966375113,649.0237612744145,649.0043204405864,648.9846334103715,648.9647550316669,648.9447359917555,648.924623068758,648.904459369427,648.8842845539752,648.8641350486013,648.844044246334,648.8240426967988,648.8041582854698,648.7844164029527,648.7648401048093,648.7454502624172,648.7262657053269,648.7073033555622]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_218.png', dpi=300)
