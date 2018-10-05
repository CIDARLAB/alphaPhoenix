import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [234.58778464900956,289.2663190395819,331.7356380546584,364.0104560374113,387.8853790673952,404.87233675490756,416.23510808701724,423.02605530771797,426.1189851031072,426.2375397198371,423.97946685307915,419.83729587507,414.2159415636353,407.44769844670327,399.80501739915206,391.511384122522,382.7505522135874,373.67432536494573,364.409037070808,355.06084472677,345.7199395787443,336.4637737325559,327.3594168312559,318.4651721698131,309.8315980068805,301.50208837714825,293.5131649261744,285.89461638194274,278.6695975419277,271.85476929051543,265.4605299113142,259.4913598375636,253.9462795585105,248.81940466102665,244.10057265819506,239.77601229654965,235.82902610214288,232.2406597174581,228.99033599132488,226.05643695229887,223.41682208797016,221.04927632143637,218.93188542773538,217.04334019998203,215.3631733946094,213.87193537783736,212.55131554166715,211.38421707460736,210.3547926926756,209.44844859574317,208.65182332524492,207.95274746934658,207.34018937681924,206.80419124172573,206.33579916606004,205.92699011438432,205.57059805852413,205.260241076615,204.99025071831412,204.7556045719667,204.5518626628627,204.37510806627483,204.22189192631856,204.08918292370456,203.974321124643,203.8749760627853,203.78910885025482,203.71493807748647,203.65090924049622,203.5956674248066,203.54803297465128,203.50697988192297,203.47161663973284,203.44116931892592,203.41496664129005,203.39242683961692,203.3730461115474,203.3563884907708,203.34207697529004,203.32978576786837,203.31923349827954,203.31017731049965,203.30240771046013,203.29574408142275,203.2900307844589,203.28513377095575,203.280937642583,203.2773431017941,203.27426474277115,203.27162913881583,203.2693731876164]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/636/out0.png', dpi=300)
