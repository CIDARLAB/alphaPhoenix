import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [234.58778464900956,238.80791871663646,242.0093833159877,244.34094796542016,245.93979583635996,246.92063259145354,247.37773250537313,247.3877632033623,247.0124893688171,246.30122424250212,245.29303778624822,244.01876019204755,242.50281974147953,240.76494343437162,238.8217329943113,236.68811063041298,234.37861088405953,231.9084800837423,229.29453673337687,226.55574750598643,223.71348592822952,220.79146352482064,217.8153527313203,214.81215175714004,211.8093675159814,208.834108445634,205.91218166708228,203.0672787333216,200.32031417757867,197.6889557885643,195.1873598434695,192.8261023217815,190.6122807668504,188.54975171557183,186.63946496700876,184.87985712815936,183.26727129577833,181.7963759184104,180.460562623366,179.25230923298636,178.16349981465382,177.18569816692732,176.3103745961934,175.5290882669971,174.8336289648746,174.21612296789425,173.66910805389094,173.1855826275265,172.75903366469828,172.3834477279049,172.05330880682595,171.7635862038835,171.50971517251793,171.28757253960322,171.09344911606553,170.9240203262204,170.7763161667719,170.64769133803983,170.53579616831416,170.43854877199306,170.35410873783476,170.2808525299012,170.21735069564025,170.16234690859048,170.1147388234929,170.0735606858012,170.03796761284642,170.00722144785752,169.9806780786987,169.95777610895408,169.93802676857334,169.9210049536458,169.90634128917895,169.89371511437122,169.88284829629444,169.8734997847563,169.86546082811577,169.8585507767644,169.8526134077162,169.84751371016608,169.84313507791512,169.83937686018606,169.8361522275397,169.83338631435979,169.83101460370034,169.82898152421063,169.82723923238527,169.82574655655574,169.8244680818508,169.82337335794946,169.8224362136099]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/627/out0.png', dpi=300)
