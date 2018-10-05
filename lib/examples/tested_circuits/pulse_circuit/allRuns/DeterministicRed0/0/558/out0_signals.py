import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [232.50899787382718,1787.9061696246529,3095.042407100039,4191.4621625763375,5109.462424025142,5876.657427629623,6516.5777187125705,7049.232350334384,7491.611785133902,7858.127570374105,8160.9920995671055,8410.5444270239,8615.528771281206,8783.332140739207,8920.18694493819,9031.343758334926,9121.21869776515,9193.519216673289,9251.351533509422,9297.312405943894,9333.56753467029,9361.918523347133,9383.8600259122,9400.628468027458,9413.243526829116,9422.543386628682,9429.21464652241,9433.817637071146,9436.807801558674,9438.553709780444,9439.352196371985,9439.44104948345,9439.009617715641,9438.207652543148,9437.152659073528,9435.935989192427,9434.627877295625,9433.281589359476,9431.936830558909,9430.62253456207,9429.359138608384,9428.160432144581,9427.035052817495,9425.987691702414,9425.020059510947,9424.131656931724,9423.32038499703,9422.583025249645,9421.91561434187,9421.313733388251,9420.772728789905,9420.287878243338,9419.85451314717,9419.4681065464,9419.12433403766,9418.81911364241,9418.54862948878,9418.309343185509,9418.097995987542,9417.911604213343,9417.747449853503,9417.603067888434,9417.476231492186,9417.364936025355,9417.267382500675,9417.181961030064,9417.107234623518,9417.041923601402,9416.984890796757,9416.93512765829,9416.891741315003,9416.853942625226,9416.82103520483,9416.792405409275,9416.767513229888,9416.745884055474,9416.727101244498,9416.710799450204,9416.696658640116,9416.684398752035,9416.673774930403,9416.66457328945,9416.656607152514,9416.649713720299,9416.643751124235,9416.638595824605,9416.634140316522,9416.630291110161,9416.626966954724,9416.62409727866,9416.621620821355]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/558/out0.png', dpi=300)
