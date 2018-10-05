import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [221.83504816568626,516.6801594299582,764.970781362625,974.0574021405948,1150.1299747697153,1298.4011436163444,1423.2605438158134,1528.404740145869,1616.9466513450363,1691.5076986440622,1754.295405889292,1807.1687481007898,1851.693182700645,1889.1869922839953,1920.7603106497627,1947.3479872495882,1969.7372628430865,1988.59107555859,2004.467687205258,2017.837211034685,2029.0955297396883,2038.5760160431319,2046.559402726863,2053.282094276679,2058.94316619026,2063.710259148736,2067.724542539209,2071.1048942669604,2073.951420596783,2076.3484202263253,2078.3668803421883,2080.0665785548795,2081.49785294147,2082.703092599612,2083.71799284263,2084.5726121977486,2085.292262502009,2085.898258449258,2086.4085487806133,2086.8382478067842,2087.200082999821,2087.504771907006,2087.761339547049,2087.9773856865736,2088.1593099109973,2088.3125011542525,2088.4414972995005,2088.550119576821,2088.641585737625,2088.718605357127,2088.7834600870274,2088.8380712349185,2088.8840566716794,2088.9227787520963,2088.955384667842,2088.9828404274776,2089.005959471434,2089.025426766799,2089.041819097338,2089.0556221492197,2089.0672448984405,2089.0770317260753,2089.0852726201792,2089.092211766493,2089.098054782417,2089.102974808495,2089.1071176378555,2089.1106060355282,2089.113543375591,2089.116016703872,2089.118099316936,2089.119852933744,2089.1213295243215,2089.122572849602,2089.1236197580643,2089.12450127757,2089.1252435347533,2089.1258685291946,2089.126394785313,2089.126837901297,2089.1272110113277,2089.127525174794,2089.1277897040295,2089.1280124402842,2089.128199986101,2089.1283579009914,2089.128490866197,2089.1286028234304,2089.1286970916976,2089.1287764656686,2089.128843298506]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/1029/out0.png', dpi=300)
