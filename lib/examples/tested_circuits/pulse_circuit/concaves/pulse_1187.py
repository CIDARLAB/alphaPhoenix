import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy = [308.35249040660136,333.81782623229844,354.24433762251203,369.8713976781344,381.167585816564,388.6794963113656,392.9617028565253,394.54331539430467,393.9122838863016,391.50867923565403,387.72265962053734,382.89497898043925,377.31895591912576,371.24333898223307,364.87574489992824,358.3864515051821,351.9123729018757,345.56106993282,339.4146712223448,333.5336043277012,327.9600625514612,322.7211586061725,317.8317394653816,313.2968560073684,309.11389591882835,305.27439887796027,301.7655798383694,298.5715898523012,295.6745452043214,293.0553552326487,290.69437763871167,288.5719278335429,286.66866624140886,284.96588472429846,283.44571057037183,282.09124391129944,280.88664206008224,279.817162123631,278.86917135270767,278.0301330410991,277.28857436252935,276.6340413191656,276.05704494860527,275.54900207533353,275.10217317675784,274.7095993433181,274.3650398287378,274.06291129399733,273.79822953271065,273.56655421371937,273.36393697803027,273.18687307240094,273.03225658300425,272.89733924297514,272.7796927216424,272.67717425617974,272.5878954543709,272.51019407697265,272.4426085971219,272.383855330234,272.3328079291271,272.2884790442818,272.25000395709054,272.21662600377493,272.1876836186615,272.1625988371662,272.1408671107333,272.122048297801,272.1057587063885,272.0916640749709,272.07947338880155,272.0689334387081,272.0598240385721,272.05195382620616,272.0451565801569,272.039287992118,272.0342228411474,272.02985252179644,272.0260828836058,272.0228323441803,272.02003024248086,272.01761540276016,272.0155348830441,272.0137428851619,272.0121998060676,272.01087141263577,272.00972812427625,272.00874438962796,272.00789814528525,272.0071703460027,272.00654455714334]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(0.0,900.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1187.png', dpi=300)
