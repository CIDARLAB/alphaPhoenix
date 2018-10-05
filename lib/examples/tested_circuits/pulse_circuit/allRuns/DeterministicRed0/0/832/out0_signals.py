import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [234.56966085010865,234.57058607946885,234.57135263493075,234.57198732269745,234.57251247627423,234.57294669490193,234.5733054611944,234.5736016575885,234.5738459980351,234.57404738872026,234.57421322939038,234.57434966497112,234.5744617956056,234.57455385191932,234.5746293412144,234.57469116936792,234.57474174243225,234.57478305128248,234.5748167421104,234.57484417510588,234.5748664732836,234.57488456309054,234.57489920816235,234.5749110373702,234.57492056811174,234.57492822564228,234.57493435911067,234.5749392548537,234.5749431474107,234.57494622864448,234.57494865528787,234.57495055518459,234.57495203244557,234.57495317170657,234.57495404164067,234.57495469785363,234.57495518526827,234.57495554008662,234.57495579140243,234.5749559625254,234.57495607206712,234.57495613483079,234.5749561625389,234.574956164428,234.57495614773356,234.5749561180854,234.57495607982855,234.57495603628414,234.5749559899604,234.57495594272325,234.57495589593387,234.57495585055912,234.5749558072603,234.5749557664636,234.57495572841663,234.57495569323294,234.57495566092712,234.57495563144244,234.57495560467223,234.57495558047648,234.57495555869454,234.5749555391546,234.57495552168092,234.57495550609912,234.57495549223967,234.5749554799407,234.5749554690495,234.5749554594235,234.57495545093084,234.57495544345028,234.57495543687116,234.574955431093,234.5749554260249,234.57495542158503,234.57495541769993,234.5749554143039,234.57495541133838,234.57495540875126,234.5749554064962,234.57495540453226,234.57495540282326,234.57495540133718,234.57495540004587,234.57495539892457,234.57495539795156,234.5749553971077,234.5749553963763,234.57495539574276,234.57495539519422,234.57495539471958,234.57495539430906]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/832/out0.png', dpi=300)
