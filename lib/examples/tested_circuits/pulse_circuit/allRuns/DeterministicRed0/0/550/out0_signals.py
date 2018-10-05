import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [232.50899787382718,1788.4310290247738,3098.5708829461237,4201.6021688877345,5130.137679843294,5911.674895325856,6569.3949272328955,7122.837354144054,7588.470047031716,7980.170211043069,8309.630395833778,8586.701119974852,8819.679962199467,9015.555450512837,9180.212790418238,9318.607381513337,9434.911147852576,9532.635926283274,9614.73749644908,9683.70327918648,9741.626257052832,9790.267273316302,9831.107530009254,9865.392820838117,9894.170795137696,9918.32234654115,9938.588049053536,9955.590418871148,9969.852658442825,9981.814436426948,9991.845170404808,10000.2552059713,10007.305224028727,10013.21415597709,10018.165842519393,10022.31463471185,10025.790104612164,10028.701006507386,10031.138607470019,10033.179487250032,10034.887891715403,10036.317710743077,10037.514140247209,10038.515078583152,10039.352299607062,10040.052437968057,10040.637816565428,10041.127141350822,10041.536084654446,10041.877774846527,10042.16320731096,10042.401589322926,10042.600629415567,10042.766780132417,10042.905441642399,10043.02113249861,10043.117632823598,10043.198104349402,10043.265191037566,10043.321103408234,10043.367689203138,10043.40649258691,10043.438803737105,10043.465700375893,10043.488082546597,10043.506701728435,10043.522185206668,10043.535056467485,10043.545752262718,10043.554636885377,10043.562014109464,10043.56813717419,10043.573217131167,10043.577429821475,10043.580921706254,10043.583814738111,10043.586210430225,10043.588193254485,10043.589833478643,10043.591189534502,10043.592309994166,10043.593235218814,10043.593998733884,10043.594628375762,10043.595147247694,10043.595574516406,10043.595926075834,10043.596215099882,10043.596452502738,10043.596647321963,10043.596807037307]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/550/out0.png', dpi=300)
