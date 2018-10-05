import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [232.50899787382718,232.52876373309923,232.54433036053328,232.5556402980856,232.56289961204698,232.56647136567665,232.56680181637154,232.5643698722581,232.55965320440436,232.5531063219245,232.54514722153885,232.53615014019073,232.526442593308,232.51630535755916,232.5059744086621,232.49564408746522,232.48547096518976,232.4755780280917,232.46605891473789,232.4569820242316,232.44839437749334,232.44032516098127,232.43278891677343,232.42578836765864,232.41931688308392,232.41336060329405,232.4079002461887,232.402912625433,232.39837191005896,232.394250655858,232.3905206378154,232.38715351105822,232.3841213255822,232.38139691759872,232.3789541978603,232.3767683548862,232.37481598869087,232.37307518846544,232.37152556569444,232.37014825242346,232.36892587282318,232.36784249481826,232.3668835673503,232.3660358478128,232.36528732331362,232.36462712867265,232.3640454634344,232.3635335096504,232.36308335175266,232.36268789948323,232.36234081455586,232.36203644149293,232.36176974289464,232.36153623925327,232.3613319533132,232.36115335889056,232.36099733400562,232.36086111813555,232.3607422733654,232.3606386491964,232.3605483507626,232.36046971020244,232.36040126093744,232.36034171461543,232.36028994048618,232.36024494698995,232.36020586535082,232.3601719349827,232.36014249052806,232.3601169503641,232.36009480642525,232.3600756152029,232.3600589897962,232.36004459290007,232.3600321306267,232.36002134706715,232.36001201950955,232.36000395423886,232.3599969828505,232.3599909590182,232.3599857556623,232.3599812624712,232.359977383733,232.35997403644075,232.3599711486373,232.35996865797068,232.3599665104341,232.35996465926786,232.35996306400233,232.35996168962507,232.35996050585604]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/545/out0.png', dpi=300)
