import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy = [228.19294289854744,585.4954882293525,866.3813117102305,1083.4190389967046,1247.5184658845499,1367.8948232406747,1452.3048993471307,1507.2674829858356,1538.2546792368564,1549.8552807528145,1545.9134878203924,1529.6463644457972,1503.7430882277035,1470.4486559755715,1431.63433962743,1388.8568705401906,1343.4080633454241,1296.3563638460462,1248.581608010925,1200.8041022280556,1153.608973519268,1107.4665910567671,1062.7497283246726,1019.748020824874,978.6801789304228,939.7043389273597,902.9268756108903,868.409952804974,836.1780494352214,806.223663890154,778.5123651989866,752.9873246697159,729.573426563531,708.1810230406818,688.7093695224639,671.0497540271759,655.088319258095,640.7085693046066,627.7935526762597,616.2277181792479,605.898447752377,596.6972788522396,588.5208368006947,581.2715037136768,574.8578547690852,569.1948946032475,564.2041267782863,559.8134879552313,555.9571759377696,552.5753977358818,549.6140603663864,547.0244236379506,544.7627308326121,542.7898301134803,541.0707967336904,539.5745637257708,538.2735667149584,537.1434068068581,536.1625341197198,535.311953429051,534.5749525299755,533.9368532641347,533.3847846689188,532.9074773566819,532.4950779933398,532.1389825961758,531.8316872901879,531.5666551345844,531.3381976427022,531.1413696588683,530.971876315873,530.8259908699946,530.7004822915703,530.5925515739452,530.4997758091808,530.4200591629699,530.3515899622091,530.2928031855606,530.2423477194105,530.1990578085614,530.1619281926028,530.1300924752413,530.1028043250718,530.0794211525547,530.059389949633,530.0422350157592,530.0275473274664,530.0149753383159,530.0042170224217,529.9950129981066,529.9871405888732]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(0.0,900.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_365.png', dpi=300)
