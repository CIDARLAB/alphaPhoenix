import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [400.0831167604083,1124.9724376342717,1806.6255189310318,2444.4894704181406,3039.6705133901464,3593.756814723218,4108.518652181578,4585.789286539701,5027.407525351912,5435.186923743494,5810.8982638869975,6156.259334454154,6472.929015273713,6762.50403663124,7026.517465926236,7266.438342149251,7483.672088424883,7679.5614583972065,7855.387850433631,8012.372874036273,8151.680086324587,8274.416839258485,8381.636194161585,8474.338871397676,8553.47521122155,8619.947127825153,8674.610043068213,8718.27478975041,8751.7094768485,8775.641311115196,8790.758370966907,8797.711329781945,8797.11512666878,8789.550583503573,8775.565967621586,8755.67850001099,8730.375809225727,8700.11733152554,8665.33565798169,8626.437829467657,8583.806580595072,8537.80153376342,8488.760344573817,8436.999799917128,8382.816870088856,8326.489716310633,8268.278655053486,8208.427080563488,8147.162346987553,8084.696611487717,8021.227639717142,7956.939575011756,7892.003672628221,7826.579000333067,7760.813106619474,7694.842657798187,7628.794045177635,7562.783963515942,7496.9199618944285,7431.300968128783,7366.017787800291,7301.153578955932,7236.7843034924545,7172.979156206342,7109.800972458614,7047.306615370907,6985.547343437416,6924.569159405891,6864.4131412502,6805.115756026971,6746.709157379507,6689.221467423586,6632.677043721949,6577.0967320271975,6522.498105446483,6468.895690655878,6416.301181767454,6364.723642428127,6314.169696705997,6264.643709297375,6216.147955565885,6168.682781903929,6122.246756886375,6076.836813666682,6032.448384046607,5989.075524632291,5946.711035471804,5905.346571552154,5864.972747517287,5825.579235952725,5787.154859567246]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1341.png', dpi=300)
