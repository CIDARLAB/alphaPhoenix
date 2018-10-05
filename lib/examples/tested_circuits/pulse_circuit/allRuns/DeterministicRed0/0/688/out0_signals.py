import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [343.7241048112919,343.4444552600005,342.19363726370824,339.69796839658966,335.8073563143648,330.4197381151636,323.48688574265225,315.0322931463463,305.1652949758358,294.0830582774795,282.0571593045994,269.40705749513955,256.4671084831155,243.55500359314553,230.94766583566033,218.8672394510671,207.4766911764818,196.88269730956875,187.1429761276323,178.27558230610487,170.2683925080632,163.0877283777885,156.68562375977532,151.00561485923183,145.9871446739268,141.56877303557505,137.69041351638182,134.29480977802226,131.32843726057595,128.74198362325478,126.49052957265582,124.53352365440729,122.83462124451252,121.361439315684,120.08526405171708,118.98073735719758,118.02554007789645,117.20008370028474,116.48721792050529,115.87195836575393,115.3412365706404,114.88367282446627,114.48937151976246,114.14973800445591,113.85731556692247,113.60564098765639,113.38911701736033,113.20290014851403,113.04280210700381,112.9052035817313,112.78697881897138,112.68542982479578,112.59822903651136,112.52336943847679,112.45912120617933,112.4039940635393,112.35670463131478,112.31614812898901,112.2813738687768,112.25156404874517,112.22601541300277,112.20412340103837,112.18536845617068,112.16930420530291,112.15554725932326,112.14376841609672,112.13368507655285,112.12505470934832,112.11766922138823,112.11135011050285,112.10594429313558,112.10132051430108,112.09736625959273,112.09398509988696,112.09109440882287,112.08862340131016,112.08651144839999,112.08470662998415,112.0831644920916,112.08184698014136,112.08072152345898,112.07976024979979,112.07893931159597,112.07823830814979,112.07763979022651,112.07712883538817,112.07669268404396,112.0763204276004,112.07600274130637,112.07573165543214,112.07550035931888]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/688/out0.png', dpi=300)
