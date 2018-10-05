import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy = [228.19294289854744,228.19593638681488,228.18523337000437,228.1546251101703,228.10192378903295,228.0273648859907,227.93263279499885,227.8202400783055,227.69311940291948,227.55435004096742,227.40697348453054,227.25387052725986,227.0976822131329,226.94076288748124,226.78515706968432,226.63259402286724,226.48449530042203,226.3419915242189,226.20594538433025,226.07697843989195,225.95549979759855,225.84173517288443,225.73575520950774,225.63750225047045,225.54681501948673,225.46345089019042,225.38710559346134,225.317430346111,225.25404648182123,225.19655773309717,225.14456035643403,225.0976513169353,225.05543475776244,225.01752697794274,224.9835601324681,224.95318485392093,224.92607197713858,224.90191352925845,224.88042312802833,224.86133591235102,224.8444081111975,224.82941634061535,224.81615670374686,224.80444375562163,224.79410938297477,224.78500163938963,224.7769835675575,224.769932033258,224.76373658965258,224.7582983855069,224.7535291268874,224.74935009858365,224.74569124887714,224.74249033920725,224.7396921586838,224.73724780218242,224.73511400986555,224.7332525653383,224.73162974922383,224.73021584468566,224.7289846912952,224.72791328361896,224.72698141094816,224.72617133470317,224.7254675001868,224.72485627954154,224.72432574295888,224.72386545538166,224.72346629615126,224.72312029925112,224.72282051199528,224.7225608701987,224.72233608804623,224.72214156104482,224.72197328060085,224.72182775891068,224.72170196298578,224.72159325675761,224.72149935031925,224.72141825546316,224.72134824676618,224.72128782755743,224.7212357001787,224.72119074001506,224.7211519728337,224.72111855502285,224.7210897563707,224.72106494506707,224.72104357464914,224.7210251726453,224.72100933070215]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(0.0,900.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_345.png', dpi=300)
