import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy = [232.4917257931941,235.77915858594423,238.18656874694005,239.83933391968688,240.84702877308115,241.29965871362705,241.2702696417157,240.81771790537542,239.98924975459357,238.82286611149894,237.34949446040872,235.59498644318424,233.58194336886717,231.33135220061516,228.8639968483735,226.20159805534985,223.3676334487996,220.38779961313364,217.2901001229014,214.10457423999114,210.86271480989313,207.59665395204908,204.33821522002702,201.11793719373068,197.96416541948992,194.9022898846297,191.95417853040445,189.13782899426315,186.46723539323938,183.9524474948647,181.5997872667806,179.41218225107227,177.38957521050247,175.52937336771615,173.8269066543848,172.27587133739013,170.86874221099245,169.59714263135623,168.45216672430396,167.4246520400695,166.50540381956898,165.6853740087744,164.95579937407553,164.30830369660885,163.73496920808176,163.22838230410062,162.78165823945216,162.388449056453,162.04293848483462,161.73982702718612,161.4743099309711,161.2420502801457,161.03914901203686,160.86211329310015,160.7078243674351,160.57350572248828,160.45669219314695,160.35520044343335,160.26710111896688,160.19069284807466,160.12447818002602,160.06714148092158,160.01752875738498,159.97462934199416,159.9375593494785,159.9055467966717,159.87791827005037,159.85408702075685,159.83354236697483,159.81584028632773,159.80059508575982,159.78747204247082,159.77618091639957,159.76647024209552,159.75812231528874,159.75094879685338,159.7447868640066,159.73949584538815,159.7349542830593,159.73105737040902,159.72771472043723,159.72484842390588,159.72239136141516,159.72028573759414,159.718481809317,159.71693678319355,159.71561386056646,159.7144814109131,159.71351225686652,159.71268305625148,159.71197376828476]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(0.0,900.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_827.png', dpi=300)
