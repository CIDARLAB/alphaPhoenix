import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [232.06823074815145,235.38709320904775,238.18136526498108,240.53349642618662,242.51305218523638,244.1787152102287,245.57997777555747,246.75857268559187,247.7496827942407,248.58296313231017,249.28340448027498,249.8720628230477,250.36667538578584,250.7821807779172,251.131158082292,251.42419744561946,251.67021279353324,251.87670565622207,252.0499877038252,252.19536841666132,252.31731332149195,252.41957738393054,252.5053174354779,252.57718691169066,252.6374156688887,252.6878772163391,252.73014533694734,252.7655417619149,252.79517630491625,252.81998064176554,252.84073673606702,252.85810075470525,252.8726231847767,252.88476575191916,252.8949156457653,252.9033974787349,252.91048333729657,252.91640122824742,252.92134217483928,252.9254661773467,252.92890721875438,252.93177746765468,252.93417080633108,252.93616579183825,252.9378281405545,252.9392128124776,252.9403657593789,252.9413253906889,252.9421238024134,252.94278780715294,252.94333979721654,252.94379846770485,252.9441794221371,252.94449567957807,252.9447580991809,252.94497573550484,252.94515613582044,252.94530558880857,252.9454293325454,252.94553172839127,252.94561640633304,252.945686386432,252.9457441802765,252.94579187570668,252.94583120754757,252.94586361664352,252.9458902991129,252.94591224743004,252.94593028467915,252.945945093105,252.94595723790204,252.9459671870285,252.94597532770362,252.94598198013833,252.94598740895796,252.94599183270222,252.94599543172157,252.94599835473855,252.94600072429716,252.9460026412864,252.94600418869365,252.9460054347173,252.946006435347,252.94600723650055,252.9460078757939,252.94600838400513,252.9460087862854,252.9460091031594,252.9460093513519,252.94600954446952,252.946009693563]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/19/out0.png', dpi=300)
