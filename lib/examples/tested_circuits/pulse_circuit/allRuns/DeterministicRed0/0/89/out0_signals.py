import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [234.22818983610765,234.2517136811668,234.26850785984345,234.2776111267496,234.27892364108263,234.27287018299185,234.26018147104676,234.24174930439165,234.2185296660397,234.19147802589032,234.16150686334524,234.12945885530672,234.09609126913196,234.0620684193366,234.02795990591736,233.99424293384038,233.9613074231472,233.92946292106282,233.89894655902157,233.86993147972098,233.84253530574668,233.81682833980088,233.79284128224307,233.77057232812916,233.74999356605952,233.73105664733868,233.71369772839384,233.69784171407917,233.68340584621836,233.67030269211938,233.65844259324973,233.6477356359925,233.63809320542882,233.62942918023228,233.6216608226757,233.61470941295383,233.6085006718987,233.60296501099688,233.5980376436071,233.59365858655332,233.5897725769193,233.58632892493424,233.58328132033216,233.58058760648444,233.57820953392533,233.5761125025859,233.57426530009334,233.5726398418389,233.57121091713825,233.5699559446707,233.56885473944786,233.5678892928088,233.56704356633523,233.56630330010307,233.56565583531756,233.56508995109775,233.56459571496836,233.56416434646627,233.56378809316928,233.56346011839008,233.5631743997463,233.56292563780477,233.56270917401076,233.5625209171296,233.5623572774611,233.5622151081228,233.56209165274112,233.56198449893174,233.56189153699623,233.56181092330635,233.56174104788994,233.56168050577583,233.56162807169378,233.56158267776385,233.56154339384398,233.5615094102385,233.56148002249918,233.5614546180786,233.56143266462138,233.56141369970103,233.5613973218319,233.56138318260398,233.56137097980584,233.56136045141574,233.56135137035517,233.56134353991166,233.56133678974723,233.56133097242062,233.56132596035846,233.56132164321923,233.5613179256003]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/89/out0.png', dpi=300)
