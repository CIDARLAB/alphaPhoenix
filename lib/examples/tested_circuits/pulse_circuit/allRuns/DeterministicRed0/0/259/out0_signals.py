import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [226.65793011164607,229.041203855638,231.04773668854938,232.73707004894828,234.15933709383384,235.35674904977154,236.36484678830254,237.2135547001599,237.92806809264883,238.52960040378076,239.03601237451375,239.46234182383992,239.8212497269633,240.123395817264,240.37775484473673,240.5918828653071,240.77214145480465,240.9238864945682,241.0516271257556,241.15915958533657,241.24967989227991,241.32587872555848,241.3900213077176,241.44401466325158,241.48946424674,241.5277216205233,241.55992459630966,241.58703103163862,241.60984728396062,241.62905216665095,241.6452171178656,241.65882318081123,241.6702752994156,241.6799143537401,241.68802729241742,241.69485566293292,241.7006027930247,241.70543983644885,241.7095108626504,241.71293714147686,241.715820750291,241.71824761054793,241.72029004402856,241.72200892475206,241.72345549048214,241.72467286766042,241.72569735508955,241.7265595045227,241.72728503028162,241.72789557494806,241.72840935389425,241.7288416978198,241.72920550943005,241.72951164783944,241.72976925213501,241.729986013726,241.73016840558387,241.73032187519368,241.73045100696024,241.7305596589022,241.73065107770444,241.73072799555322,241.73079271163795,241.73084716074695,241.73089297100003,241.73093151243754,241.73096393791377,241.73099121751306,241.73101416751413,241.73103347476615,241.7310497172028,241.73106338110642,241.73107487563644,241.73108454505584,241.73109267901992,241.73109952123482,241.7311052767434,241.73111011805625,241.73111419031142,241.7311176156154,241.73112049669652,241.7311229199788,241.73112495816852,241.73112667243055,241.7311281142196,241.73112932682102,241.73113034664726,241.73113120432865,241.73113192563105,241.73113253222806,241.7311330423507]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/259/out0.png', dpi=300)
