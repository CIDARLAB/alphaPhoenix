import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy = [221.83504816568626,509.52692615574,735.3796821464873,909.409583151111,1040.5073210055782,1136.2087270020297,1202.8634384954823,1245.809211133835,1269.5268292597589,1277.7733956425395,1273.6957024128271,1259.92612124939,1238.6634374270086,1211.740843131754,1180.683052713361,1146.7542510912506,1110.998349551042,1074.272805139475,1037.2770635008874,1000.5765126497643,964.6226883310479,929.7703502328791,896.2919499754561,864.3899330019188,834.2072517443706,805.8364118766083,779.327322782687,754.6941749938766,731.9215206243236,710.9696888010224,691.7796287790478,674.277241034278,658.3772326560564,643.9865182250343,631.0071802791857,619.339002761981,608.8815943794486,599.5361244443865,591.2066997815767,583.8014163548762,577.2331227472058,571.419934233404,566.2855360213597,561.759312610114,557.7763374613413,554.277253723546,551.2080729770851,548.5199150457477,546.168708162599,544.1148652768519,542.3229491366964,540.7613360193267,539.4018856103215,538.2196225443791,537.1924334786601,536.3007822417329,535.5274445455163,534.8572629251136,534.2769219446993,533.7747432426622,533.3404996559347,532.965247435776,532.6411754230305,532.36146997144,532.1201943775833,531.9121815828923,531.7329389468188,531.5785639425588,531.4456696914242,531.3313193240723,531.2329682326251,531.1484133543928,531.0757487034277,531.0133264390053,530.9597228293584,530.9137085339738,530.8742226881196,530.840350328888,530.811302752914,530.7864004421803,530.7650582361623,530.7467724662301,530.7311098020039,530.7176975895356,530.7062154880723,530.6963882360194,530.6879793978741,530.6807859626067,530.6746336802989,530.6693730386005,530.6648757931293]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(0.0,900.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1069.png', dpi=300)
