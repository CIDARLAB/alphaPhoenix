import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [232.50899787382718,232.77004495894008,232.989850163849,233.17492841297934,233.33076559215368,233.46198106802345,233.57246454315643,233.66549129912198,233.74381924013304,233.80977061101822,233.8653008095669,233.91205633118963,233.95142356205886,233.98456986591788,234.01247818155048,234.03597615573761,234.05576067469846,234.07241852073943,234.08644376607347,234.09825241913398,234.1081947573272,234.1165657116371,234.12361361078797,234.12954754407485,234.13454356104816,234.13874989178112,234.1422913424224,234.14527299630018,234.1477833303901,234.1498968392899,234.15167624466253,234.1531743555907,234.15443563499213,234.15549751853277,234.15639152514083,234.1571441920462,234.15777786206965,234.15831134650588,234.15876048325617,234.15913860676258,234.15945694367875,234.1597249460123,234.15995057161894,234.16014052036786,234.16030043298287,234.16043505845786,234.1605483950124,234.16064380876898,234.16072413367277,234.16079175561836,234.1608486832788,234.16089660773952,234.16093695270573,234.16097091677344,234.1609995090186,234.16102357896023,234.16104384178686,234.16106089959536,234.16107525927166,234.16108734754508,234.16109752366236,234.16110609005824,234.16111330133856,234.16111937184345,234.16112448201463,234.16112878375588,234.16113240494585,234.16113545323753,234.16113801925687,234.16114017929547,234.1611419975777,234.16114352816902,234.1611448165829,234.1611459011332,234.16114681407308,234.16114758255355,234.16114822943052,234.16114877394406,234.16114923229046,234.1611496181037,234.1611499428608,234.16115021622286,234.16115044632258,234.16115064000581,234.1611508030353,234.1611509402619,234.161151055769,234.16115115299385,234.16115123482976,234.16115130371222,234.1611513616913]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/522/out0.png', dpi=300)
