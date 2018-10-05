import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [232.4917257931941,232.48599332977247,232.3892280110664,232.18596150456128,231.87224936180894,231.44613031117854,230.90601369882643,230.2504315004258,229.4782090225074,228.58880482880213,227.58272172152064,226.46193061921352,225.23025956567736,223.8937031189379,222.46061127688392,220.94172544942967,219.3500428054748,217.7005085868994,216.0095560412953,214.29453192431689,212.5730586044817,210.86238930258472,209.17881027557794,207.53713396387198,205.9503127300209,204.42918682332987,202.98236534922592,201.61622724267323,200.3350214742834,199.1410419778007,198.0348524608825,197.01553840939033,196.08096723071344,195.22804174539817,194.4529364852896,193.7513100760974,193.11849015699912,192.54962975229594,192.03983579861494,191.5842717162029,191.17823662732005,190.8172241694411,190.49696392147354,190.21344835521776,189.96294800331003,189.74201725147105,189.5474928539787,189.37648696216797,189.22637616264643,189.09478775427158,188.9795842554206,188.87884692703838,188.79085892157357,188.7140885211013,188.6471728068753,188.58890200416081,188.53820466736428,188.4941338082363,188.4558540215783,188.42262962601077,188.39381380986114,188.36883875228605,188.34720667583304,188.3284817774838,188.3122829797499,188.2982774407448,188.2861747616307,188.27572183086914,188.26669824684402,188.25891226331967,188.25219720557206,188.24640830867557,188.24141993318304,188.23712311718506,188.23342342739005,188.23023907536984,188.2274992684286,188.22514276764937,188.2231166285433,188.22137510237386,188.21987867859093,188.2185932511015,188.21748939298098,188.21654172606307,188.21572837343726,188.21503048431208,188.2144318219764,188.21391840672416,188.21347820661236,188.21310086981035,188.21277749308385]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/809/out0.png', dpi=300)
