import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy = [327.40838656527797,356.64372336071796,375.65440826965306,385.7542362131541,388.38857073587485,384.86723589278904,376.3922246557972,364.0888713753194,349.0111758788159,332.12371230339596,314.27372330285704,296.1677558397963,278.3614517652467,261.263956572142,245.15353480519119,230.19929286112426,216.48446870491813,204.02822141007118,192.80432573242467,182.7562611496757,173.80883569143342,165.87679989624303,158.87100665901397,152.70265588332933,147.28609297959636,142.54054448807042,138.39109135290835,134.76910866468197,131.6123422072882,128.86474625674435,126.47617201968501,124.40196983749951,122.602548917163,121.04292426127734,119.69227034594205,118.52349390843194,117.51283317060359,116.63948734630699,115.88527792207353,115.23434163711289,114.67285408643414,114.18878226047624,113.77166399519142,113.4124121498744,113.10314130038621,112.83701478395557,112.60811003021689,112.41130024411156,112.24215065114267,112.0968276664162,111.97201949903783,111.86486684850829,111.77290248712777,111.6939986505589,111.6263212768753,111.5682902424364,111.51854484094291,111.47591384043776,111.43938953237009,111.40810525776013,111.38131595865725,111.35838135914358,111.3387514297594,111.32195383304386,111.30758308649041,111.29529121316104,111.28477967999765,111.27579244997486,111.26810999708073,111.26154415306945,111.25593367234411,111.25114041650642,111.24704607332424,111.24354933636066,111.24056348149354,111.2380142852235,111.23583823718657,111.23398100580405,111.23239612164508,111.2310438479613,111.22989021207579,111.22890617495892,111.22806691948239,111.22735124052912,111.2267410225614,111.22622079219143,111.22577733508795,111.22539936804645,111.22507725834441,111.22480278361763,111.22456892645157]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(0.0,900.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_179.png', dpi=300)
