import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [343.7241048112919,383.23968634283466,416.51562383845203,444.53714524797186,468.1339180736039,488.00461043910656,504.7375743978701,518.8282636902084,530.6939015091657,540.6858324412162,549.0999242046453,556.1853270857149,562.1518503627084,567.176174060223,571.4070799547692,574.9698566455828,577.9700090763738,580.4963823624591,582.6237923839133,584.4152410209092,585.923781612048,587.1940898622698,588.2637867074529,589.164552299957,589.9230640958174,590.5617868170206,591.0996376771544,591.5525465659416,591.9339277784053,592.2550772556572,592.5255070989978,592.7532272619152,592.9449827606793,593.1064534272617,593.2424221192873,593.3569163677938,593.4533276571211,593.5345118689637,593.602873864908,593.6604387121248,593.7089116613896,593.749728653557,593.7840988501576,593.8130404476077,593.837410835639,593.8579319930825,593.8752118730974,593.8897624111827,593.9020146892906,593.9123317051473,593.9210191249252,593.9283343378315,593.9344940808181,593.939680858935,593.944048351802,593.947725966229,593.9508226698318,593.9534302191934,593.9556258781871,593.9574747069811,593.9590314895261,593.960342356621,593.9614461526339,593.9623755863648,593.9631582001396,593.9638171858446,593.9643720720745,593.9648393027499,593.9652327243446,593.9655639961591,593.9658429357906,593.9660778100389,593.9662755798624,593.9664421066452,593.9665823258819,593.9667003934305,593.9667998086635,593.966883518166,593.9669540030561,593.9670133525104,593.9670633256769,593.9671054038084,593.9671408341596,593.9671706669518,593.9671957864975,593.9672169374094,593.9672347466678,593.9672497422046,593.9672623685487,593.9672730000026,593.9672819517356]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/643/out0.png', dpi=300)
