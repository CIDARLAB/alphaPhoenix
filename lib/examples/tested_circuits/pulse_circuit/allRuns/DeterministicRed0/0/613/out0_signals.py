import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [234.58778464900956,783.4627000101001,1244.9807617791548,1632.4489202596485,1957.2519420602787,2229.1067971813072,2456.2903192023145,2645.840227122338,2803.7310528427906,2935.027158209942,3044.0152307231483,3134.3186232453736,3208.9957638298592,3270.624670446721,3321.37539705494,3363.072031148039,3397.2456684057283,3425.1796119169385,3447.9478830775593,3466.4479887707353,3481.428763595303,3493.5139948430165,3503.222442071702,3510.9847782726474,3517.1579063626505,3522.0370409765965,3525.8658902469297,3528.8452243843794,3531.140076489201,3532.885785300483,3534.193058805874,3535.152211144879,3535.836702482231,3536.3060920087914,3536.608497511853,3536.782640664459,3536.859544982182,3536.863942995779,3536.8154403368903,3536.7294769120394,3536.618118957452,3536.490710358928,3536.3544070449475,3536.2146143948116,3536.0753443413273,3535.9395060988336,3535.80914213451,3535.6856190574804,3535.56978146945,3535.462075454033,3535.3626472384126,3535.27142160534,3535.1881638360983,3535.112528300597,3535.0440962579864,3534.9824049719145,3534.9269698636626,3534.877301111018,3534.832915839995,3534.793346841419,3534.7581485671562,3534.7269010154087,3534.6992119946894,3534.6747181585138,3534.653085123334,3534.6340069170637,3534.617204952888,3534.602426680478,3534.5894440323077,3534.578051755225,3534.5680656953336,3534.5593210867182,3534.55167088066,3534.5449841411046,3534.5391445236382,3534.5340488486822,3534.5296057746164,3534.5257345727646,3534.5223640034183,3534.5194312900553,3534.5168811875365,3534.514665139121,3534.512740516618,3534.5110699376905,3534.5096206542685,3534.5083640061134,3534.5072749337646,3534.5063315453644,3534.505514732181,3534.5048078279838,3534.504196307781]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/613/out0.png', dpi=300)
