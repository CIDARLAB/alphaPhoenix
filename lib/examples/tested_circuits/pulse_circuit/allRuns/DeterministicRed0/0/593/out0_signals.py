import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [234.58778464900956,234.61362869706778,234.63537865960845,234.65368035914247,234.66907817414423,234.6820309051665,234.69292517040705,234.70208671364952,234.7097899484412,234.71626601237054,234.72170956298584,234.72628451108187,234.73012885677906,234.73335876818527,234.73607202074933,234.73835089708066,234.74026463149647,234.74187147050358,234.74322040927007,234.74435265480545,234.74530285870782,234.7461001556052,234.74676903778806,234.74733009176458,234.7478006184454,234.7481951552658,234.74852591568668,234.74880315909326,234.74903550206773,234.74923018028926,234.74939326885942,234.74952986762503,234.74964425703584,234.74974002920203,234.7498201980812,234.74988729210406,234.74994343202607,234.7499903963513,234.75002967630402,234.7500625220105,234.7500899812906,234.75011293223662,234.75013211056972,234.75014813260793,234.75016151454597,234.75017268863712,234.7501820167721,234.75018980187218,234.75019629744656,234.75020171560837,234.750206233797,234.7502100004141,234.7502131395486,234.7502157549364,234.75021793327912,234.7502197470244,234.7502212566947,234.7502225128381,234.75022355766154,234.75022442639835,234.7502251484527,234.7502257483575,234.75022624657578,234.75022666017105,234.75022700336794,234.75022728802105,234.7502275240068,234.7502277195509,234.7502278815022,234.75022801556105,234.75022812647038,234.75022821817504,234.75022829395505,234.7502283565364,234.75022840818403,234.75022845077868,234.75022848588176,234.7502285147888,234.75022853857425,234.75022855812887,234.75022857419083,234.75022858737134,234.7502285981764,234.75022860702452,234.75022861426183,234.75022862017434,234.75022862499821,234.7502286289283,234.75022863212533,234.75022863472176,234.75022863682665]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/593/out0.png', dpi=300)
