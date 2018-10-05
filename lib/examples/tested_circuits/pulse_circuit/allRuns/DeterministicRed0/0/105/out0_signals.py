import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [234.22818983610765,234.2123085464118,234.10061348891645,233.8914495754161,233.58275279483686,233.17082037584498,232.65037234788238,232.01473780820783,231.2561085347135,230.36586119103413,229.33495797770883,228.15443372098557,226.81597042143702,225.3125492895647,223.6391560564719,221.7934993384906,219.77668653261082,217.59379048183632,215.25423663209082,212.77194757336915,210.16520085548134,207.45618523214216,204.67027561944442,201.83508166500562,198.9793521545152,196.13183234609886,193.32017145488558,190.56996406129286,187.90398605935508,185.34165814899671,182.8987429412697,180.58725937304297,178.41558243942075,176.38868767303444,174.50849749596327,172.77428901748843,171.1831283385802,169.73030340600525,168.4097347346899,167.2143500768148,166.1364149017101,165.16781517623764,164.30029240208503,163.52563328716067,162.83581797053392,162.22313156685098,161.68024412074394,161.20026401658302,160.7767695992497,160.4038233254863,160.07597225581608,159.78823816811274,159.53610005454993,159.31547128676226,159.1226733005406,158.95440727286797,158.80772493951062,158.6799994280025,158.56889675450694,158.47234844852247,158.3885256212706,158.31581467652748,158.25279477167507,158.198217067359,158.15098575241097,158.11014079321066,158.07484233053646,158.04435662976243,158.01804347999456,157.9953449327625,157.97577526986478,157.9589120918394,157.9443884224561,157.9318857299351,157.92112777178082,157.9118751767825,157.9039206845841,157.89708497004656,157.8912129862605,157.8861707664093,157.88184263066293,157.87812874985923,157.8749430228801,157.87221122935674,157.8698694236401,157.86786253987412,157.86614318152357,157.86467057186562,157.8634096447776,157.86233025767635,157.86140651066287]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/105/out0.png', dpi=300)
