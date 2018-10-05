import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy = [228.19294289854744,260.55983670387093,285.39595895647386,303.9858367834301,317.42077727901216,326.61215158953723,332.3193942330636,335.1749207075273,335.7055217346412,334.3505579913901,331.47733775049744,327.3940170293581,322.36031044106,316.5962623483386,310.2893065759992,303.5998365191662,296.6655099557251,289.60451658414524,282.5180341700073,275.49208654412143,268.598992375254,261.89855983294217,255.4391434077808,249.25864044883733,243.38547087270584,237.83955683449574,232.6333009525741,227.7725513804909,223.2575380503234,219.0837647855448,215.24284478660505,211.72327070056537,208.51111408140304,205.59065200968698,202.94492083519123,200.55619853754615,198.4064182686111,196.47751643855165,194.751719365289,193.21177307928545,191.84112136047094,190.62403745047328,189.54571509723888,188.59232463053937,187.75103963594188,187.0100395105169,186.35849277726902,185.78652554816458,185.28517898486697,184.84635906282958,184.46278140862455,184.12791347915257,183.83591589726115,183.58158435675256,183.36029316330638,183.16794118527235,183.00090074616534,182.8559697942348,182.73032752826305,182.62149353720628,182.5272904190536,182.4458097763675,182.3753814379249,182.31454572379124,182.26202855168395,182.21671917279025,182.17765032294972,182.14398057837704,182.11497871232908,182.09000985907903,182.06852330327368,182.050041725466,182.03415174776177,182.02049563666617,182.00876403305847,181.99868959153537,181.99004142301135,181.98262024534705,181.9762541568609,181.97079495683914,181.96611494561472,181.96210414445574,181.95866788243075,181.95572470364408,181.95320455380914,181.95104721010406,181.94920092268762,181.94762124013573,181.94626999461585,181.94511442561688,181.9441264237689]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(0.0,900.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_372.png', dpi=300)
