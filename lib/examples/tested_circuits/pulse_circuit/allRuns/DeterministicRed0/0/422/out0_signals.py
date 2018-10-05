import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [313.4971139370574,5592.464079493353,10031.554359183277,13759.224553794247,16885.196784671884,19502.84382826609,21691.467495185698,23518.342072433643,25040.497476937595,26306.253974361134,27356.533252734982,28225.973877945908,28943.878371789076,29535.016742224474,30020.308333896453,30417.400856242366,30741.1626236297,31004.101513351885,31216.72195021961,31387.82935938311,31524.789968447072,31633.75254994694,31719.83764362987,31787.298922783128,31839.66067344649,31879.83476725286,31910.220027483254,31932.786484051474,31949.14667151751,31960.615832479547,31968.262637455027,31972.95181460613,31975.37989309037,31976.105098352906,31975.572293126297,31974.13373167452,31972.06628474561,31969.585696919217,31966.85835491441,31964.010973498243,31961.13854359098,31958.31083381117,31955.577690969625,31952.97334593847,31950.519898023507,31948.230122680674,31946.109723454283,31944.159128769592,31942.374917152705,31940.750940112193,31939.27919989429,31937.950529267535,31936.755112101007,31935.682876513958,31934.723786571925,31933.86805369533,31933.106284971596,31932.429582282773,31931.829603463735,31931.29859449254,31930.829399903636,31930.415457136583,31930.05077933081,31929.729930102214,31929.44799304989,31929.20053810758,31928.983586346807,31928.79357443394,31928.627319622126,31928.481985906797,31928.355051775623,31928.244279830466,31928.147688441542,31928.06352550503,31927.99024430939,31927.92648146733,31927.8710368364,31927.822855328217,31927.781010491806,31927.74468974866,31927.71318115386,31927.685861558417,31927.662186050922,31927.641678561802,31927.623923519575,31927.60855845558,31927.595267460947,31927.583775407064,31927.573842848207,31927.56526153226,31927.557850452118]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/422/out0.png', dpi=300)
