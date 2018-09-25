import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [400.0831167604083,3665.3989580171065,6772.653319478357,9722.538161656203,12518.487019422228,15165.05316694977,17667.301187939298,20030.521528824203,22260.078487184095,24361.32257919737,26339.537401965837,28199.90643248039,29947.492022145012,31587.222183772865,33123.88252973551,34562.11170615845,35906.39924843827,37161.085139193565,38330.36057568606,39418.26960154896,40428.71135688365,41365.44276891246,42232.08155307138,43032.10942839417,43768.87547558955,44445.59958418589,45065.375948421046,45631.17658149377,46145.85482527652,46612.148838259694,47032.685048814295,47409.98156415867,47746.45152794063,48044.40642128143,48306.05930361017,48533.52799074971,48728.83816857694,48893.92644123357,49030.64331335378,49140.75610614048,49225.95180739032,49287.839855761624,49327.954859717414,49347.75925167023,49348.64587791916,49331.94052500949,49298.904383169225,49250.73644748933,49188.57585752005,49113.50417595674,49026.54760708848,48928.67915568175,48820.82072697183,48703.8451684369,48578.57825403386,48445.80061158221,48306.24959399154,48160.621095040195,48009.571310427076,47853.71844483461,47693.64436575906,47529.89620488361,47362.98790779,47193.40173282544,47021.58969996289,46847.97499051377,46672.953298573324,46496.89413509869,46320.14208553937,46143.01802195755,45965.82027059275,45788.825735840546,45612.29098162853,45436.45327118477,45261.53156620376,45087.72748642334,44915.22623063181,44744.19746012894,44574.79614566678,44407.16337889654,44241.42714934619,44077.7030879503,43916.09517814819,43756.6964355601,43599.5895572422,43444.84754151183,43292.53427932266,43142.70511815699,42995.40739938857,42850.68097005406,42708.55866995574]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1334.png', dpi=300)
