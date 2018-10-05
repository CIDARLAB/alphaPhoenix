import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [313.4971139370574,340.3272236752687,362.242482002814,379.6161551001723,392.9398974507902,402.7380689193491,409.52421939888706,413.77921834749844,415.9410225968326,416.4008622525737,415.5030044693081,413.5465094081327,410.78807671790304,407.4454596241323,403.70113449546614,399.70603180900343,395.5832020631448,391.4313310941205,387.32804629978483,383.33297504425644,379.49053210556144,375.8324257373626,372.3798822259995,369.1455969717602,366.13542615569435,363.3498372751839,360.78513945986424,358.4345157071162,356.2888793798155,354.3375767088947,352.56895588176695,350.97082177193346,349.53079363358734,348.23658126978097,347.0761933737001,346.0380900028448,345.1112895168378,344.2854388156677,343.5508543682579,342.89854032395755,342.3201889480993,341.80816770917545,341.3554965589074,340.95581827566133,340.6033641739967,340.29291700649,340.019772486793,339.7797005344264,339.56890707228644,339.3839969884716,339.22193869703995,339.08003059086167,338.95586956792107,338.8473217251406,338.7524952466902,338.6697154631092,338.5975020202733,338.53454807068204,338.4797013815207,338.43194724267573,338.3903930518494,338.3542544519176,338.3228428967067,338.29555452463563,338.2718602245311,338.25129678387106,338.2334590163403,338.21799277258606,338.20458874519034,338.19297698596654,338.1829220605928,338.17421877222597,338.16668839203817,338.16017534053,338.1545442689936,338.1496774956101,338.14547275533477,338.14184122705814,338.1387058054798,338.1359995886149,338.13366455512886,338.13165040858695,338.1299135683138,338.1284162888996,338.12712589248264,338.12601409980607,338.12505644771,338.12423178220047,338.1235218175476,338.1229107530288,338.1223849399616]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/419/out0.png', dpi=300)
