import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [234.22818983610765,234.2285895759652,234.22877746229315,234.22871265911294,234.22838789082124,234.22781740241174,234.22702830068522,234.22605442864366,234.22493212804034,234.2236973991035,234.2223840841246,234.22102279182437,234.21964034873122,234.2182596171005,234.21689955983805,234.21557546427167,234.21429926058062,234.21307988891246,234.21192368298006,234.21083474824533,234.20981532044956,234.2088660958593,234.20798652864167,234.20717509364164,234.20642951479726,234.20574696072407,234.20512420980447,234.204557787562,234.20404407929246,234.2035794209363,234.20316017107214,234.20278276672977,234.20244376549715,234.20213987615,234.20186797978243,234.20162514317218,234.20140862588303,234.20121588239329,234.20104456034332,234.2008924958227,234.20075770646312,234.20063838296804,234.20053287959456,234.20043970400255,234.20035750680142,234.20028507105332,234.200221301931,234.2001652166798,234.20011593499171,234.20007266986624,234.2000347190068,234.20000145677864,234.19997232673884,234.19994683473553,234.1999245425635,234.19990506215632,234.1998880502905,234.19987320377294,234.19986025508175,234.19984896842885,234.19983913621337,234.19983057583443,234.19982312683365,234.19981664833875,234.19981101678047,234.19980612385785,234.19980187472774,234.19979818639615,234.1997949862917,234.19979221100158,234.1997898051536,234.19978772042847,234.1997859146881,234.19978435120734,234.1997829979975,234.19978182721132,234.1997808146202,234.19977993915506,234.19977918250382,234.19977852875851,234.19977796410632,234.1997774765592,234.19977705571765,234.199776692564,234.19977637928227,234.19977610910072,234.19977587615483,234.19977567536756,234.19977550234543,234.1997753532878,234.19977522490814]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/80/out0.png', dpi=300)
