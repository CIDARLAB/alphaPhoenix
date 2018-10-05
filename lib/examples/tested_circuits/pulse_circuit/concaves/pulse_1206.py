import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy = [308.35249040660136,5546.050532186213,9894.154237002405,13482.02101343404,16420.639097168307,18803.329966471665,20708.344075103312,22201.296207236992,23337.319332209132,24162.945412781693,24717.738396547305,25035.702631606448,25146.484013408353,25076.37525812404,24849.132566579137,24486.60955995615,24009.216073690153,23436.21385465822,22785.867437836518,22075.475041430836,21321.309649978517,20538.50325766064,19740.906813139696,18940.95476873635,18149.556976349537,17376.03309607425,16628.0968970931,15911.890882249541,15232.066248354638,14591.89961071027,13993.436132852854,13437.648432386346,12924.601481665517,12453.61526754321,12023.418844466185,11632.291320357275,11278.187058271084,10958.843846851605,10671.873944853794,10414.838742910628,10185.30834226924,9980.907672970165,9799.350914500594,9638.465989129692,9496.2108140122,9370.682858996859,9260.12338335212,9162.917545895865,9077.59140209167,9002.806632962156,8937.353697569783,8880.143965544992,8830.201269304021,8786.65321655082,8748.722521061467,8715.718541903345,8687.029166308026,8662.113127572313,8640.49281488686,8621.747605322693,8605.507727957292,8591.448655091175,8579.286004666126,8568.770930483008,8559.685971910776,8551.841331887319,8545.07155065471,8539.232542457414,8534.198963051382,8529.861877079018,8526.126695967505,8522.911358857413,8520.144731051692,8517.76519650714,8515.719422908873,8513.96127982889,8512.45089234156,8511.153814232352,8510.040306579353,8509.084709004865,8508.264892285728,8507.561782278326,8506.95894626234,8506.442233842406,8505.999465476181,8505.620162528588,8505.295313493216,8505.017171680603,8504.779080257245,8504.575321035749,8504.400983872376]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(0.0,900.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1206.png', dpi=300)
