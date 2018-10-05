import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [221.83504816568626,221.75384157204667,221.43516817055044,220.85297327767748,220.01183005445418,218.92312111817017,217.60131625169058,216.06325053727528,214.3280995563005,212.41744676635173,210.3552278529731,208.16747226393085,205.88182991335634,203.5269140639446,201.13151962388034,198.72379104454072,196.33041613203457,193.97591305285488,191.68206078466403,189.46750225592282,187.34752837169572,185.3340331163656,183.4356167738935,181.6578065624803,180.00336120084376,178.47262706662622,177.0639174090296,175.77389132994264,174.5979149631963,173.53039275468728,172.56506154440012,171.6952440677717,170.91406148579748,170.2146066840751,169.59008147154597,169.0339016032344,168.53977388997995,168.10174966904748,167.71425870502725,167.3721272379209,167.070583490604,166.8052535026081,166.57214972511446,166.36765440522598,166.18849941856402,166.03174388337908,165.89475060780734,165.77516218307397,165.67087733613104,165.58002799136898,165.5009573582782,165.4321992559111,165.3724588016005,165.32059452688765,165.2756019346763,165.23659847536956,165.20280989368825,165.17355787992093,165.1482489477758,165.12636445435243,165.1074516748529,165.09111584455658,165.07701308252774,165.0648441149101,165.0543487200085,165.04530082230477,165.0375041678164,165.0307885185759,165.02500630932985,165.02002971471742,165.0157480801143,165.0120656739686,165.00889972378152,165.00617870188407,165.0038408308303,165.00183278157294,165.00010854062114,164.998628425122,164.9973582272658,164.99626847160795,164.9953337709205,164.99453226787497,164.9938451514531,164.99325623835253,164.99275161087382,164.99231930385048,164.99194903413024,164.9916319669486,164.991360514266,164.9911281607804,164.99092931388682]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/1056/out0.png', dpi=300)
