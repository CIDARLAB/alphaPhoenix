import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [534.7774278130823,6213.427553208497,10995.615134505248,15022.855620624408,18414.325630497235,21270.389113171284,23675.56692557357,25701.03768211102,27406.74385183064,28843.16541731783,30052.813565827608,31071.48860341364,31929.339307864622,32651.755062183343,33260.11716339652,33772.431535331445,34203.86156540849,34567.17683069105,34873.13098921346,35130.78002055513,35347.750228948804,35530.46393867128,35684.32956196466,35813.90166268987,35923.01575204381,36014.90180504244,36092.279856852176,36157.440507814536,36212.31271946816,36258.52090781583,36297.43302338884,36330.201040954635,36357.79505710805,36381.03200483445,36400.59983483967,36417.07787929273,36430.95400065506,36442.639033130334,36452.47894414835,36460.76507582294,36467.742769502096,36473.618628676224,36478.56663521241,36482.7332999459,36486.24200008075,36489.19663178387,36491.68468608873,36493.779839155985,36495.544133564756,36497.02981520308,36498.280880132676,36499.33437721802,36500.22150508118,36500.968535855136,36501.597593082086,36502.12730678264,36502.573365096476,36502.948978824534,36503.26527261001,36503.531614356994,36503.75589263357,36503.94475027254,36504.10378108582,36504.23769551687,36504.3504601357,36504.44541510671,36504.525373107535,36504.59270262764,36504.649398113324,36504.697139036056,36504.73733963313,36504.77119079368,36504.799695330265,36504.823697680484,36504.84390891827,36504.8609278154,36504.87525857702,36504.887325776326,36504.897486930786,36504.90604309227,36504.91324776475,36504.91931441366,36504.924422789336,36504.92872425183,36504.93234625476,36504.93539612102,36504.937964222234,36504.94012665601,36504.9419475004,36504.94348071233,36504.94477172611]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/973/out0.png', dpi=300)
