import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [399.43570506393996,5574.620617192242,9932.611629557166,13602.293830010967,16692.24946451384,19293.95227269542,21484.459880633272,23328.683017792642,24881.297975288948,26188.358326715548,27288.653141979215,28214.851518834246,28994.467008772175,29650.67024463963,30202.973633816582,30667.808233827214,31059.00976785739,31388.228073818293,31665.272034009835,31898.400142121172,32094.565263234475,32259.62079883893,32398.494337041688,32515.333909222143,32613.63116931588,32696.325132664213,32765.889538881547,32824.40642071454,32873.628054260706,32915.02912326226,32949.850641447214,32979.13693358224,33003.76677089032,33024.47958374944,33041.89752905232,33056.54406699336,33068.859598750714,33079.214629511356,33087.920847980786,33095.24045177027,33101.39399604127,33106.56699897771,33110.91550075786,33114.57074162136,33117.643098455046,33120.22539728061,33122.39570046701,33124.21965186103,33125.752449869266,33127.04050744364,33128.12284859297,33129.0322831876,33129.79639521159,33130.43837404911,33130.977713705244,33131.43080091145,33131.811409768474,33132.13111774039,33132.39965549684,33132.62520111259,33132.81462746127,33132.97371024054,33133.10730288535,33133.219483633184,33133.31367916925,33133.392768576356,33133.459170722715,33133.51491772298,33133.561716689124,33133.60100163526,33133.633977104466,33133.661654835814,33133.68488458063,33133.704380000156,33133.72074042871,33133.734469161456,33133.74598882103,33133.755654268956,33133.76376345365,33133.77056652426,33133.7762734872,33133.781060638015,33133.7850759643,33133.78844368395,33133.791268056964,33133.79363658688,33133.79562270947,33133.79728805069,33133.79868432279,33133.799854916426,33133.800836237555]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/469/out0.png', dpi=300)
