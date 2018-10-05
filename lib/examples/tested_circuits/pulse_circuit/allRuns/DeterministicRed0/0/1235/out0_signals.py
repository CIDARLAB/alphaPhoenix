import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [378.91389377064297,398.23000617177786,414.47856755457553,428.1351170623021,439.6033406763949,449.2256113527073,457.29204027203616,464.04823833079536,469.7019627947878,474.4288021734717,478.377032783268,481.67176314524374,484.4184668312953,486.7059909912316,488.6091158705359,490.1907302725991,491.50367889785775,492.5923296495258,493.4939021951939,494.23959318864183,494.8555284727259,495.36356820031125,495.7819870354729,496.1260483516223,496.40848855641735,496.6399252834882,496.82920114409336,496.98367298062783,497.10945506736846,497.21162342627537,497.29438733614694,497.36123318518156,497.4150450269067,497.45820552755623,497.49268042213356,497.52008911181804,497.54176362437533,497.55879781092784,497.57208835752056,497.58236894038373,497.5902386428473,497.5961855736472,497.6006064756693,497.60382298899543,497.60609512152695,497.6076323943685,497.60860305127517,497.6091416582136,497.60935536556207,497.6093290605377,497.6091295997425,497.6088092801228,497.6084086801716,497.6079589810584,497.6074838588511,497.60700102352496,497.6065234675403,497.6060604759999,497.6056184414242,497.6052015187134,497.6048121496522,497.604451481155,497.60411969716375,497.60381628056274,497.60354021853516,497.60329016235244,497.60306455058316,497.6028617030489,497.6026798914912,497.60251739179193,497.6023725216663,497.60224366699475,497.6021292993388,497.602027986684,497.60193839903883,497.6018593101864,497.60178959661226,497.6017282344139,497.60167429481965,497.6016269388024,497.6015854111598,497.6015490343429,497.60151720224076,497.60148937407416,497.60146506850623,497.6014438580415,497.60142536376156,497.60140925042225,497.60139522192213,497.60138301714323,497.6013724061536]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/1235/out0.png', dpi=300)
