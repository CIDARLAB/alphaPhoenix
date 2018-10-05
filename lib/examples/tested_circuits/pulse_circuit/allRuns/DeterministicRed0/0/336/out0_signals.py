import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [228.19294289854744,228.18368058668275,228.17492947716133,228.16631470059679,228.15763670346217,228.14881403517012,228.13984178741032,228.13076175666941,228.12164136445756,228.11255908892417,228.1035947036114,228.09482303077894,228.08631023109234,228.0781118923171,228.07027236447138,228.0628249306692,228.0557925114569,228.0491886833001,228.04301885480064,228.03728149168995,228.0319693171509,228.0270704402992,228.0225693848418,228.01844800369895,228.0146862750365,228.01126298172267,228.0081562804901,228.00534416966266,228.0028048656615,228.0005170989996,227.9984603403734,227.99661496696902,227.99496237836868,227.99348507057726,227.99216667576664,227.99099197441373,227.98994688561785,227.98901844055166,227.98819474323736,227.98746492215477,227.9868190755794,227.98624821301607,227.98574419463299,227.9852996702065,227.98490801875064,227.9845632897256,227.98426014648396,227.98399381242226,227.9837600201463,227.98355496383465,227.98337525488256,227.9832178808312,227.98308016752677,227.98295974440913,227.9828545127973,227.98276261701673,227.9826824181994,227.98261247057994,227.98255150010866,227.9824983852033,227.9824521394661,227.98241189619904,227.98237689455775,227.98234646719425,227.9823200292474,227.98229706855054,227.98227713693484,227.98225984251752,227.98224484287226,227.98223183898907,227.98222056993873,227.98221080816546,227.98220235533807,227.9821950386974,227.98218870784376,227.9821832319144,227.98217849710545,227.9821744044986,227.98217086815654,227.98216781345502,227.98216517562366,227.98216289846994,227.98216093326454,227.98215923776786,227.98215777538084,227.98215651440452,227.98215542739433,227.98215449059833,227.98215368346777,227.98215298823152,227.98215238952594]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/336/out0.png', dpi=300)
