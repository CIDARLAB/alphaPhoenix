import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [210.93897812097362,437.03614411663887,626.5971579795711,784.8718671736164,916.494151555702,1025.5105447258197,1115.4278178646482,1189.2673213108683,1249.619885746384,1298.6981731520284,1338.3850130686603,1370.2771530178513,1395.7243377266325,1415.863890669835,1431.651101847596,1443.8857829326391,1453.2353666413048,1460.2549198099375,1465.4044199511197,1469.063619287502,1471.5447921943805,1473.1036334432133,1473.94854699206,1474.2485392109013,1474.1399060362132,1473.7318813073132,1473.1113932366877,1472.3470576041775,1471.4925197594835,1470.589242765138,1469.6688258958352,1468.7549261061158,1467.8648448606154,1467.0108337641539,1466.2011646107903,1465.4410026746214,1464.7331161800116,1464.0784498118967,1463.4765857623734,1462.9261120707888,1462.4249148217757,1461.9704080479628,1461.5597128777927,1461.1897955175084,1460.8575720101026,1460.5599863291116,1460.2940672033692,1460.0569680970202,1459.845993958458,1459.6586176776163,1459.4924886319027,1459.3454352387655,1459.2154630518721,1459.100749624894,1458.9996371106872,1458.9106233547377,1458.832352072046,1458.7636025594438,1458.7032792849336,1458.6504016073377,1458.6040938093097,1458.5635755713292,1458.5281529709127,1458.4972100576408,1458.4702010288777,1458.4466430116372,1458.4261094417257,1458.4082240209798,1458.3926552261416,1458.3791113384536,1458.3673359601119,1458.35710398244,1458.3482179704652,1458.3405049291603,1458.3338134178061,1458.3280109804973,1458.322981862656,1458.318624985411,1458.314852151749,1458.31158646041,1458.308760905517,1458.3063171418821,1458.3042043977837,1458.3023785187515,1458.300801127529,1458.299438886887,1458.2982628533478,1458.2972479111547,1458.29637227697,1458.2956170668388,1458.2949659179026]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/1117/out0.png', dpi=300)
