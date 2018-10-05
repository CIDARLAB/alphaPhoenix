import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [343.7241048112919,4250.191617925178,7539.964683802822,10310.386782803249,12643.437733200652,14608.158255241031,16262.691953521013,17656.005082802665,18829.334930836496,19817.40964214191,20649.475544816774,21350.162354381642,21940.21183649422,22437.091474190416,22855.5112858688,23207.859077094487,23504.566997858095,23754.42024593208,23964.817045866264,24141.987594930575,24291.178450048348,24416.80780961735,24522.5962850572,24611.67703000504,24686.68848519715,24749.85248290733,24803.040021780165,24847.82665819711,24885.539153164365,24917.294755032806,24944.03428050379,24966.549972898687,24985.508962150154,25001.473020839137,25014.915201006275,25026.233844167942,25035.76437923625,25043.78925758002,25050.546319333815,25056.235838633915,25061.026456360236,25065.060176035287,25068.456570800903,25071.316326040458,25073.724222547833,25075.75164858233,25077.458715201363,25078.89603751714,25080.106234632196,25081.125192678945,25081.9831283738,25082.705484589107,25083.313684471763,25083.825766448103,25084.25691892711,25084.6199305412,25084.925569269908,25085.18290167792,25085.39956171678,25085.58197706701,25085.735559723948,25085.864866475247,25085.97373402595,25086.06539277588,25086.1425626216,25086.207533622455,25086.262233921836,25086.30828693698,25086.34705951274,25086.379702466795,25086.407184728367,25086.430322082673,25086.44980137327,25086.46620088001,25086.480007476817,25086.491631078126,25086.501416802344,25086.5096552131,25086.516590942032,25086.522429948815,25086.527345633807,25086.53148398463,25086.534967909305,25086.537900884567,25086.540370027506,25086.542448681725,25086.544198594715,25086.545671751057,25086.546911915873,25086.54795593427,25086.548834825382]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/661/out0.png', dpi=300)
