import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [668.3963041221473,670.433927568505,672.2861561478495,673.9489062457884,675.4266954367316,676.7261683248963,677.854767050347,678.820237308489,679.6303974747156,680.2930159081773,680.8157403600767,681.2060553060975,681.4712554459984,681.6184291294956,681.654448163055,681.5859618730274,681.4193940955817,681.1609422311133,680.8165777867647,680.3920480118095,679.8928783487198,679.3243755016215,678.6916309776701,677.9995249942749,677.252730671485,676.4557184477197,675.6127606706751,674.7279363252209,673.8051358674688,672.8480661396837,671.8602553448353,670.8450580627275,669.8056602920509,668.7450845045868,667.6661946992754,666.5717014450673,665.4641669024674,664.3460098145193,663.2195104587099,662.0868155519182,660.9499431011246,659.8107871931451,658.6711227171726,657.5326100144022,656.3967994494968,655.2651358991159,654.1389631531774,653.0195282249737,651.9079855666848,650.8054011872606,649.7127566700432,648.6309530879066,647.5608148140652,646.5030932270745,645.4584703088989,644.4275621352599,643.4109222577956,642.4090449778669,641.4223685121311,640.4512780502716,639.4961087055183,638.5571483588305,637.634640397824,636.72878635172,635.8397484237757,634.9676519228135,634.1125875956166,633.2746138620846,632.4537589551594,631.6500229676316,630.8633798080224,630.0937790678109,629.3411478023369,628.6053922277551,627.886399336461,627.1840384334266,626.4981625959138,625.8286100590286,625.1752055295945,624.5377614308042,623.9160790801029,623.3099498027339,622.7191559833504,622.1434720580694,621.5826654493069,621.0364974456925,620.5047240293228,619.9870966525624,619.4833629665544,618.9932675035539,618.5165523151396]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1584.png', dpi=300)
