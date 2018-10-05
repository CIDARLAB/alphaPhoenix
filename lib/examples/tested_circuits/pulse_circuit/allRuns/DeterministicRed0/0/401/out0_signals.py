import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [313.4971139370574,313.5476255223145,313.5873798395753,313.6170976247542,313.63776324163206,313.6504880742642,313.6564162686607,313.65666183270804,313.65226873109424,313.64418762886766,313.6332644878603,313.620237412738,313.60573905976196,313.5903026239286,313.5743699570572,313.5583007778117,313.5423822434653,313.526838384986,313.5118390793363,313.49750835947765,313.48393195421113,313.4711640150277,313.45923303211384,313.44814697161644,313.4378976851169,313.42846465300767,313.41981812839515,313.41192174902017,313.4047346827881,313.39821336883034,313.3923129112738,313.3869881776118,313.38219464810527,313.3778890572544,313.3740298632375,313.37057757641765,313.3674949736245,313.3647472209671,313.36230192440144,313.3601291241565,313.35820124643584,313.35649302344103,313.35498139075474,313.3536453693964,313.3524659384098,313.35142590261535,313.3505097591386,313.34970356547853,313.3489948111824,313.34837229462516,313.34782600593076,313.3473470167063,313.3469273769654,313.34656001939254,313.3462386709226,313.3459577714806,313.3457123996273,313.3454982047927,313.34531134572944,313.3451484347961,313.34500648766397,313.34488287804106,313.34477529701286,313.34468171661143,313.34460035724163,313.34452965861357,313.3444682538494,313.3444149464589,313.34436868989684,313.34432856944056,313.3442937861474,313.3442636426709,313.3442375307373,313.34421492010006,313.3441953488099,313.3441784146522,313.34416376761965,313.3441511033006,313.34414015707847,313.3441306990462,313.3441225295513,313.34411547529703,313.3441093859323,313.3441041310721,313.3440995976946,313.3440956878703,313.3440923167813,313.34408941099474,313.34408690695915,313.3440847496953,313.34408289165725]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/401/out0.png', dpi=300)
