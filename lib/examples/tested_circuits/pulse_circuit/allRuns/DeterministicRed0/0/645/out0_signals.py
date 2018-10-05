import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [343.7241048112919,4250.193897323955,7539.980742164065,10310.434565883963,12643.53771276884,14608.330838016564,16262.955857523413,17656.376406773114,18829.82671017773,19818.03173721913,20650.234747750048,21351.062631280933,21941.254645518362,22438.276117495472,22856.83526674574,23209.318443400425,23506.156660019464,23756.13426296747,23966.64887652817,24143.93031357981,24293.224928536783,24418.950867501186,24524.828811054034,24613.99207788028,24689.07934731549,24752.312744586485,24805.56359939069,24850.407823703947,24888.17254753037,24919.97539262209,24946.75754634634,24969.311614416332,24988.3050767025,25004.30004022953,25017.769873892976,25029.113217154318,25038.66577726487,25046.710264127614,25053.484756795053,25059.189749190275,25063.994083553735,25068.039947203655,25071.44708047914,25074.316320392216,25076.732584856705,25078.767385807372,25080.48094558098,25081.923979190542,25083.13919523679,25084.162559874323,25085.024361238266,25085.75010583183,25086.361273402712,25086.875952648174,25087.309376562058,25087.674373265265,25087.981745666566,25088.240591189813,25088.45857101819,25088.642136836268,25088.796721776176,25088.926901218958,25089.036528210057,25089.12884749659,25089.206591561386,25089.272061495925,25089.327195105743,25089.373624263844,25089.41272320959,25089.445649222507,25089.4733768748,25089.496726876274,25089.516390365432,25089.532949365635,25089.546894011753,25089.558637057176,25089.568526090527,25089.576853823648,25089.583866755416,25089.589772467698,25089.594745769475,25089.598933870966,25089.602460740854,25089.60543077563,25089.607931889634,25089.61003811722,25089.61181180413,25089.613305452884,25089.614563276828,25089.615622508816,25089.61651450331]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/645/out0.png', dpi=300)
