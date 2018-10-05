import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [308.35249040660136,308.71441210366964,306.2312371629611,300.951307361397,293.1243741791617,283.12410402815885,271.4263609974047,258.5678351631385,245.09182569261475,231.49665010295047,218.19891859406837,205.51615457056434,193.66644720425361,182.77948889997643,172.91314466395588,164.07113252152863,156.21916857724676,149.29837520122393,143.2356908615541,137.951526109579,133.3651211365671,129.39810048542276,125.97667656773683,123.03287766276236,120.50509568651236,118.3381768556583,116.48321879397761,114.89719088371929,113.54245923123834,112.38627143718709,111.40023741729475,110.55982906282307,109.84391214428355,109.23431744582624,108.71545383555012,108.27396320767296,107.89841552079734,107.57904117278119,107.30749746082782,107.07666570797319,106.88047567682933,106.71375405700934,106.57209404870628,106.45174333464054,106.34950800972562,106.26267031329185,106.18891826345268,106.12628553142281,106.07310010873246,106.02794051250818,105.98959844437377,105.95704696831393,105.92941340373876,105.90595624382675,105.88604550784706,105.8691460213355,105.85480319136775,105.84263090723985,105.83230125098311,105.82353574850916,105.81609793186283,105.8097870169858,105.8044325303719,105.79988974272946,105.796035788866,105.79276637099962,105.78999295803156,105.78764040637462,105.78564493905266,105.78395242925461,105.7825169425848,105.78129949910802,105.78026702212134,105.77939144554678,105.77864895605863,105.77801934964776,105.77748548537593,105.77703282166685,105.77664902268529,105.77632362422936,105.77604775015317,105.77581387169192,105.77561560320586,105.77544752884302,105.77530505545091,105.77518428776453,105.7750819225033,105.77499515851667,105.77492162054986,105.77485929456829,105.77480647289049]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/1210/out0.png', dpi=300)
