import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy = [226.65793011164607,226.7140523175478,226.4815165222307,225.97069844799017,225.19279727162757,224.15719684142144,222.87231347611865,221.34667342895432,219.58997502122213,217.61403884733417,215.4335703982828,213.0666679182294,210.53502321795125,207.86378817411202,205.0811127824746,202.2173966326839,199.3043277376332,196.37380449358903,193.45684421127575,190.58257452022562,187.77738464694755,185.06428688009643,182.4625099252264,179.98732003229924,177.65004587598452,175.4582704856864,173.41614781734853,171.52480157933687,169.78276797010625,168.18645035639182,166.7305611932998,165.40853362813672,164.2128915940244,163.13557246769338,162.16820045449873,161.30231183903598,160.52953524898544,159.84173130166806,159.23109661605466,158.69023733917496,158.2122171915728,157.79058469141606,157.4193837556558,157.09315136175601,156.80690542915298,156.5561255754848,156.33672893714603,156.1450428252113,155.97777562468656,155.83198703124552,155.70505845694333,155.59466421873486,155.49874394625726,155.41547650301067,155.34325560266782,155.28066721498803,155.22646878938966,155.17957027485093,155.13901687917505,155.10397348598306,155.07371063174205,155.0475919357587,155.02506287178247,155.00564076937528,154.98890593549314,154.974493790961,154.96208792207253,154.95141395390903,154.94223415876942,154.93434272005385,154.92756157883332,154.92173679702364,154.9167353774574,154.9124424871502,154.90875903564364,154.90559956546758,154.90289041648717,154.90056813020308,154.89857806396637,154.8968731885789,154.89541304589858,154.89416284588376,154.89309268501876,154.89217687029557,154.89139333488083,154.89072313337704,154.89015000610283,154.8896600031708,154.88924116034067,154.88888321966618,154.8885773888693]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(0.0,900.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_306.png', dpi=300)
