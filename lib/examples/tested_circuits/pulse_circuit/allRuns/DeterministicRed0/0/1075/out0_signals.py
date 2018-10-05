import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [221.83504816568626,223.6069504109344,224.62443022966445,224.97202781731363,224.73014930396067,223.96916729020433,222.75141219503203,221.13339084651847,219.16768627982464,216.90440853129599,214.39215817617747,211.67851053941865,208.81006675145423,205.83215123167923,202.78825975270814,199.71937378375728,196.66325360707523,193.65380620592944,190.72059821595897,187.8885546171504,185.1778554362155,182.60401931645296,180.17814639947335,177.9072838615353,175.79487472955532,173.84125267533733,172.044150598829,170.3991973982807,168.90038418864918,167.54048757682136,166.31144300005786,165.20466542235158,164.21131788254866,163.32253061971724,162.52957493011507,161.8239967069255,161.1977149351687,160.64309039340162,160.1529695597774,159.72070831839142,159.3401795780449,159.00576839811595,158.7123576996286,158.45530714838486,158.23042734265167,158.0339510340131,157.86250275469922,157.71306791987845,157.5829622166735,157.46980187853796,157.3714752695538,157.28611606322835,157.2120781896193,157.14791263852513,157.09234614088743,157.04426170177476,157.00268092314806,156.96674803030933,156.9357155001993,156.9089311806203,156.88582678544182,156.8659076506167,156.8487436383693,156.8339610813938,156.82123566468005,156.81028614917008,156.80086884845673,156.7927727768864,156.78581539450687,156.7798388811627,156.77470687857976,156.7703016454325,156.76652157611423,156.7632790392153,156.7604984965527,156.75811486800143,156.7560721113641,156.75432199011027,156.75282300503952,156.75153946880516,156.75044070480388,156.74950035422452,156.748695777035,156.74800753454974,156.74741894271574,156.74691568668572,156.74648548845607,156.746117820417,156.74580365859902,156.74553527021845,156.74530603083988]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/1075/out0.png', dpi=300)
