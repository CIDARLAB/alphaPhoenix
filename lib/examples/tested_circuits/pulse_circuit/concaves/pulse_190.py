import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [682.0251584058062,1741.186908624821,2747.0697753433906,3701.4929748162635,4606.537716879402,5464.280632744004,6276.747520931541,7045.898847493787,7773.624860369938,8461.744507319927,9112.00609321368,9726.088799634816,10305.604646668793,10852.100677528253,11367.06124408053,11851.910322314536,12308.01381511121,12736.681816209351,13139.170819293862,13516.685862429604,13870.38260212074,14201.369313947089,14510.708818517285,14799.42033269071,15068.481246847441,15318.828829551225,15551.361861335576,15766.942199601344,15966.396276782763,16150.51653404333,16320.062792820787,16475.76356656488,16618.317315011438,16748.393643318243,16866.634448357257,16973.655014416963,17070.045060521283,17156.369741519025,17233.17060504165,17300.96650636862,17360.25448317926,17411.51059210836,17455.190708960446,17491.731294374575,17521.55012666841,17545.047003526808,17562.604414136684,17574.588183306616,17581.348089046223,17583.21845501732,17580.518719205986,17573.553980102297,17562.615521612246,17547.981317865062,17529.916519018134,17508.673919101726,17484.494406886417,17457.607400698078,17428.231268048192,17396.57373089165,17362.83225727,17327.194440045554,17289.83836338098,17250.93295757009,17210.638342778755,17169.10616221005,17126.47990516541,17082.895220433325,17038.480220399375,16993.355776236196,16947.63580449901,16901.4275454221,16854.83183318369,16807.94335838125,16760.850922936243,16713.637687626608,16666.38141242688,16619.154689819447,16572.02517122645,16525.05578669937,16478.30495799307,16431.826805142304,16385.671346651514,16339.88469340313,16294.50923638513,16249.583828335384,16205.143959398196,16161.221926887069,16117.84699924734,16075.04557431243,16032.841331948199]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_190.png', dpi=300)
