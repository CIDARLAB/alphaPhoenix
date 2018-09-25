import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [398.35081899232495,1089.3806673176985,1741.3698097446968,2353.5567812102577,2926.659810361255,3461.918374898356,3960.7983098997183,4424.864988761234,4855.718916723285,5254.959656384371,5624.164375035409,5964.874646019691,6278.58822632243,6566.7539871922245,6830.76892029704,7071.976550616634,7291.6663239922145,7491.0736807229905,7671.380616956922,7833.716594432619,7979.159698510365,8108.737971463708,8223.430866970186,8324.170785306218,8411.844658622793,8487.295562971722,8551.324339214318,8604.69120908504,8648.117375855782,8682.286601501435,8707.846754183167,8725.41132137174,8735.560885124743,8738.844556979528,8735.78137068095,8726.861631570522,8712.548221951738,8693.277862139457,8669.462327217685,8641.489619784741,8609.725099168985,8574.512567761449,8536.175315241195,8495.017121570812,8451.323219718364,8405.361219121853,8357.381990956457,8307.62051629579,8256.296698278757,8203.616139404729,8149.770885083517,8094.94013456417,8039.290920358983,7982.978757267295,7926.148262088305,7868.933745094034,7811.459774313087,7753.841713653656,7696.18623587048,7638.591811355723,7581.14917370809,7523.9417630083435,7467.046147702816,7410.53242596972,7354.464607416275,7298.900975927895,7243.894434464103,7189.492832569488,7135.739277342062,7082.672428575731,7030.326778768441,6978.7329186628795,6927.917788962425,6877.904918841409,6828.714651845691,6780.364359757026,6732.86864497281,6686.239531931451,6640.486648092893,6595.617394963703,6551.637109636565,6508.54921729514,6466.355375116848,6425.055607988387,6384.648436431624,6345.130997120817,6306.499156356136,6268.747616842842,6231.87001811058,6195.8590308927105,6160.706445771729]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1845.png', dpi=300)
