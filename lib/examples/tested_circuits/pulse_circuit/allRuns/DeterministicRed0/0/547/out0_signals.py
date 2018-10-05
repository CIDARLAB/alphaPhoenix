import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [232.50899787382718,235.9581785386777,238.8597579831729,241.29807462908207,243.34490707727952,245.06126384241284,246.49893654058613,247.70184239491942,248.707181081646,249.54642917850757,250.24619329650048,250.82894075325476,251.31362443952207,251.71621649116636,252.05016351808976,252.32677447318866,252.55555076478743,252.7444669135505,252.90020891462606,253.02837647154232,253.13365440414606,253.2199577834888,253.29055469807642,253.34816999570597,253.3950728619325,253.43315068004955,253.46397125950827,253.48883521219472,253.50881999214687,253.52481688819736,253.537562065501,253.54766258645262,253.5556182002093,253.56183956950827,253.56666350078203,253.57036565616937,253.57317115171372,253.57526338292894,253.57679136537132,253.57787583247705,253.57861429450435,253.57908522992295,253.579351553135,253.57946347923317,253.57946088695488,253.57937526452395,253.57923130921083,253.57904823978774,253.57884087126536,253.5786204930819,253.57839558502755,253.57817239941406,253.57795543318966,253.57774780963857,253.57755158594725,253.57736800012196,253.57719766838693,253.57704074224802,253.57689703278925,253.57676610842316,253.5766473712026,253.57654011587823,253.57644357512225,253.57635695370948,253.57627945392616,253.57621029404987,253.57614872139192,253.57609402110594,253.5760455217293,253.57600259823224,253.57596467319158,253.5759312165789,253.5759017445485,253.5758758175279,253.57585303784484,253.57583304707174,253.57581552322455,253.57580017791855,253.5757867535566,253.57577502060383,253.57576477498566,253.57575583563363,253.57574804219286,253.57574125289815,253.57573534261977,253.5757302010757,253.5757257312044,253.57572184769074,253.57571847563523,253.57571554935728,253.57571301132208]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/547/out0.png', dpi=300)
