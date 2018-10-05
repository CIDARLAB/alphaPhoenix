import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [234.56966085010865,234.84938172816084,234.97258767481242,234.95052030472223,234.79118558926692,234.49920110253075,234.07616981209202,233.5210689491762,232.83064811204574,231.99985358413764,231.02229836832,229.8907949328518,228.59796172609245,227.13690507740804,225.50196516027555,223.6894989158774,221.6986558932424,219.53208762588207,217.1965210255271,214.70312501224902,212.0676098941401,209.31002137880174,206.45422316386404,203.5270986427941,200.55753652001707,197.57529034256396,194.6098134437638,191.68916713163935,188.8390831437088,186.08223590353816,183.43775146533736,180.9209532490739,178.54332330294963,176.3126435908587,174.23327474235938,172.30652872614783,170.53109535258832,168.9034886331774,167.418486316436,166.06954325370793,164.84916589829325,163.74924082486552,162.76131454737435,161.87682515487666,161.08728851261958,160.38444316744062,159.76035883494038,159.20751359917452,158.7188448679002,158.28777881473758,157.90824259490262,157.57466311118273,157.28195557978603,157.02550463459838,156.80114023298802,156.60511019806043,156.43405085851393,156.28495692409115,156.15515146337657,156.04225662558142,155.94416556426023,155.85901587330375,155.7851647288267,155.72116583983063,155.66574824133696,155.61779691214016,155.57633516204274,155.5405087075123,155.50957133773065,155.48287206297644,155.4598436325497,155.4399923086798,155.42288878498255,155.40816014220366,155.3954827395332,155.38457594618896,155.37519662484615,155.3671342855457,155.3602068357198,155.35425685878454,155.34914836024785,155.34476392640659,155.341002246412,155.33777595375253,155.33500974803223,155.33263876232056,155.33060714533195,155.3288668312838,155.3273764735013,155.3261005207201,155.32500841760222]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/882/out0.png', dpi=300)
