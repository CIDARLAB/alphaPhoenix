import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [210.93897812097362,210.94298468912945,210.65216054092372,210.0318731325446,209.09894984189998,207.8836960527539,206.42145605755772,204.7496770482225,202.90649964401473,200.92982529009447,198.85653657181663,196.72178304820628,194.55833222431215,192.39601430485888,190.2612919849129,188.17697680917263,186.16209935438513,184.23192637731074,182.39810680981768,180.66892123089278,179.04960625801942,177.54272554626613,176.14856181147732,174.8655085099508,173.69044464639725,172.6190809904238,171.64627032828076,170.76627802656458,169.97301205494134,169.26021372669896,168.62161185226176,168.05104387480213,167.5425479890285,167.09043034699977,166.68931132957607,166.33415458263013,166.0202821557235,165.74337868027487,165.49948711394487,165.28499818621412,165.09663531749814,164.93143645862315,164.7867340119919,164.66013375016604,164.54949343981662,164.4529017058451,164.36865752820205,164.2952506486121,164.23134307223293,164.17575177666163,164.12743268441977,164.0854659122393,164.04904227864236,164.0174510283364,163.9900687160301,163.96634918191447,163.94581454500832,163.92804713781592,163.9126823054724,163.89940199408755,163.8879290558239,163.87802220193757,163.869471539256,163.86209463011195,163.8557330204135,163.85024918516814,163.84552384529158,163.84145361385413,163.83794893399846,163.834932274579,163.83233655310667,163.83010375883558,163.82818375179627,163.82653321628325,163.82511474974478,163.82389607023163,163.82284932749727,163.8219505046904,163.8211788990696,163.82051667161858,163.8199484566726,163.8194610237694,163.8190429849108,163.81868454127897,163.81837726420827,163.8181139058767,163.81788823576562,163.81769489944682,163.81752929670378,163.81738747638647,163.81726604573979]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/1122/out0.png', dpi=300)
