import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [313.4971139370574,313.4643800357212,313.43567110986345,313.41051328728855,313.3884848944904,313.369211340889,313.35236038609213,313.33763778604254,313.32478330736683,313.31356709458385,313.3037863716894,313.2952624576751,313.2878380745019,313.2813749257148,313.2757515240817,313.2708612472236,313.26661060106153,313.26291767196483,313.2597107495974,313.25692710377774,313.2545118998312,313.25241723818124,313.25060130513185,313.2490276229453,313.2476643884151,313.24648389016403,313.24546199585535,313.2445777013928,313.24381273500205,313.24315120983294,313.2425793194045,313.2420850708312,313.2416580513296,313.24128922400945,313.24097074940494,313.2406958296101,313.2404585722457,313.24025387181007,313.2400773062549,313.23992504688596,313.23979377991566,313.2396806381974,313.2395831418521,313.2394991466535,313.23942679918076,313.2393644978697,313.23931085920054,313.23926468835964,313.2392249537924,313.23919076514125,313.2391613541252,313.23913605797594,313.2391143050932,313.2390956026273,313.23907952573273,313.23906570827245,313.2390538347782,313.23904363350044,313.23903487040184,313.2390273439672,313.2390208807215,313.239015331359,313.2390105674019,313.23900647831726,313.23900296902895,313.23899995777185,313.23899737424153,313.2389951579989,313.23899325709505,313.23899162688684,313.2389902290159,313.2389890305293,313.2389880031228,313.23898712248905,313.23898636775624,313.23898572100563,313.2389851668561,313.23898469210656,313.23898428542896,313.2389839371037,313.23898363879243,313.23898338334317,313.2389831646219,313.2389829773687,313.23898281707363,313.2389826798703,313.23898256244456,313.23898246195614,313.2389823759707,313.23898230240263,313.238982239465]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/384/out0.png', dpi=300)
