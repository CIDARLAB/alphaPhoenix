import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy = [343.7241048112919,380.63654568936863,405.4005027162091,419.8984364215378,425.8925007488356,424.8539109531103,418.0393433950817,406.57311000367724,391.50402380880877,373.8266352830399,354.4703231433654,334.2702918365348,313.9375640270289,294.04040103446806,275.0016143128027,257.1096822186158,240.53851418579166,225.37059745287038,211.61964715317052,199.25054207282838,188.1956558919581,178.36753978711164,169.66835221631834,161.99659916206025,155.25176024759423,149.33731381790327,144.16258546230273,139.64375483744195,135.70427634669278,132.27490388957557,129.29345837236033,126.7044372352424,124.45853571443952,122.51212780922326,120.82673914284905,119.36853261024072,118.10781973003125,117.01860507147573,116.07816733499367,115.26667812848768,114.56685783271084,113.96366692084275,113.44403049806463,112.99659351948172,112.61150403271,112.2802218071148,111.99534980622374,111.75048610907317,111.54009405204376,111.35938854808677,111.2042367237458,111.0710711937768,110.95681446389253,110.85881311191358,110.77478054523553,110.70274726762621,110.64101771106516,110.58813279907491,110.54283750742064,110.50405277693918,110.47085121241989,110.44243607173658,110.41812311164028,110.3973249115552,110.37953734511763,110.36432791175105,110.35132567791757,110.3402126104082,110.33071611266267,110.32260260011826,110.31567197240841,110.30975285924725,110.30469853338839,110.30038339844025,110.29669997182354,110.2935562940098,110.2908737045898,110.28858493387622,110.28663246580457,110.28496713400521,110.28354691820068,110.28233591264598,110.28130344226992,110.28042330557756,110.27967312630811,110.2790337983487,110.27848901064147,110.27802484063066,110.277629406449,110.27729256942355,110.27700567967766]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(0.0,900.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_699.png', dpi=300)
