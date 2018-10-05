import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [232.4917257931941,2127.483625556969,3723.3399181878817,5067.267768179083,6199.026583854598,7152.10267226196,7954.6987016063495,8630.567149520499,9199.712321411818,9678.981657537113,10082.563781624402,10422.407994769626,10708.577601144632,10949.54749968292,11152.454831054487,11323.31008354429,11467.174894127163,11588.311797700628,11690.310348845927,11776.19334397732,11848.506282012806,11909.392707639923,11960.657664919134,12003.821136769657,12040.163050305937,12070.761178780645,12096.52306097751,12118.212882087937,12136.474111180212,12151.848564932172,12164.792461644518,12175.68994056258,12184.864446581585,12192.58831728095,12199.090856062252,12204.565130382658,12209.173696358035,12213.053419243217,12216.319532542304,12219.069055969092,12221.38367350038,12223.332156782544,12224.972405691527,12226.353166510677,12227.515478644122,12228.493892743743,12229.31749635699,12230.010777501064,12230.594351767168,12231.085574514998,12231.499056312356,12231.847096907295,12232.140050605452,12232.386633891769,12232.59418442352,12232.768879079618,12232.915917535278,12233.03967681895,12233.143841425008,12233.231512855242,12233.305301838582,12233.367405967338,12233.419675055527,12233.463666160447,12233.500689901834,12233.531849454634,12233.558073373873,12233.580143227002,12233.598716854865,12233.614347952662,12233.627502552914,12233.638572900443,12233.647889131902,12233.655729107133,12233.662326684735,12233.667878687957,12233.672550768135,12233.676482340103,12233.679790736409,12233.682574703958,12233.684917347131,12233.686888604994,12233.688547336316,12233.689943074474,12233.691117504499,12233.692105706243,12233.692937200682,12233.69363683054,12233.694225501431,12233.694720805632,12233.695137547065]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/799/out0.png', dpi=300)
