import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy = [234.56966085010865,288.7982103453711,330.7386826451303,362.53170588629354,385.9869097755204,402.61467061612086,413.67219559358324,420.203665287085,423.07438951087533,422.99973580798314,420.5696173370418,416.26924210849023,410.4967248738413,403.5780657907587,395.7799101918888,387.3204220317145,378.37853048884733,369.1017462634448,359.6126937017435,350.0144695907074,340.3949210231165,330.8299328444777,321.3858267901741,312.12099375371616,303.08690035686897,294.32862400712713,285.88507191680276,277.789027434601,270.0671432689836,262.739970045598,255.8220754900436,249.32227916875144,243.24400352919585,237.58572542638248,232.34150321751193,227.50155152330294,223.05283704574938,218.97967255680643,215.26429083688558,211.8873849459774,208.82860523431202,206.06700679288608,203.58144367555442,201.35090834598475,199.35481655728583,197.5732393491699,195.9870850803342,194.57823539845361,193.3296397708508,192.22537364730462,191.2506655120692,190.39189803328233,189.6365882767474,188.97335156751325,188.39185310656518,187.8827509199213,187.43763318703978,187.04895247092585,186.70995888946297,186.41463383174587,186.15762544153174,185.93418676363459,185.7401171762361,185.57170750868886,185.4256890652985,185.29918663516852,185.18967546081765,185.09494205858087,185.01304872686686,184.9423015398215,184.88122160006247,184.82851931169085,184.78307143106767,184.74390065565225,184.71015751873568,184.68110436872686,184.6561012246204,184.63459331351328,184.6161001108562,184.6002057190274,184.58655043442292,184.5748233673074,184.56475599198976,184.5561165173626,184.5487049794177,184.54234896799818,184.5368999097785,184.53222983830264,184.52822858989902,184.52480137147842,184.52186665266117]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(0.0,900.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_884.png', dpi=300)
