import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [232.4917257931941,277.14735725247243,314.7336417567246,346.3529539431492,372.9385145482844,395.2798599412475,414.04457458452305,429.79681101702454,443.0130553779435,454.0955358532889,463.3836172603309,471.16347720986033,477.67631751525727,483.12532822220055,487.6815902377801,491.4890754552561,494.66888008702176,497.3228067602355,499.5363940059002,501.3814770372418,502.9183512092342,504.19759886444217,505.26163114971956,506.1459886119098,506.88043775509914,507.4898951006011,507.99520549054773,508.4137972938445,508.7602337039157,509.0466763708761,509.28327510923265,509.47849529986684,509.6393928054122,509.7718446929711,509.88074276627134,509.9701558157232,510.04346556949565,510.10348054618544,510.1525313482266,510.19255037641165,510.2251384740863,510.2516206114007,510.2730923841326,510.2904588184315,510.30446673421727,510.315731718994,510.32476059465415,510.3319701174948,510.3377025319492,510.3422384979049,510.3458078268721,510.34859839152733,510.35076351323056,510.3524280821252,510.35369362291533,510.354642483954,510.35534129795315,510.35584383800136,510.3561933719765,510.35642460121005,510.3565652548677,510.35663739948313,510.3566585130482,510.35664236468773,510.35659973396787,510.35653899807073,510.3564666102271,510.3563874887678,510.3563053328069,510.35622287778585,510.356142101796,510.3560643916836,510.3559906763478,510.3559215333316,510.3558572737132,510.35579800940747,510.3557437062436,510.35569422557126,510.3556493566448,510.35560884161504,510.3555723946215,510.35553971619265,510.3555105039356,510.35548446030475,510.35546129808847,510.355440744124,510.355422541651,510.35540645163076,510.35539225328785,510.3553797440814,510.35536873926463]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/796/out0.png', dpi=300)
