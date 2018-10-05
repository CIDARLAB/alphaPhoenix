import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy = [308.35249040660136,332.7353189864901,348.9985496680717,357.7029287607833,359.93367871672655,356.8087399890113,349.40626908304495,338.7434950919225,325.75724803942853,311.28179442748393,296.0298744053011,280.58259379492176,265.39014199346906,250.78183734388244,236.9822183088966,224.12973477741818,212.2953503667106,201.4993784429984,191.72575503214927,182.93356679823592,175.06601250739112,168.0571468081898,161.83680481102886,156.33408878048243,151.47975019391998,147.20774277080793,143.45616610954394,140.1677703668409,137.29015157557816,134.77573453374845,132.5816147350583,130.66931133118084,129.00446842265882,127.55653101934327,126.29841392045051,125.20617584206924,124.25870682287908,123.43743385086222,122.72604745714993,122.11025048693683,121.57752919929287,121.11694613699669,120.71895375288014,120.3752275035663,120.0785169747695,119.82251354556632,119.60173310405246,119.41141237304478,119.24741747709692,119.10616347037977,118.98454364132185,118.87986750896843,118.78980652412857,118.71234658308005,118.64574655133134,118.58850207877147,118.5393140650108,118.49706120468863,118.46077610707947,118.42962454270413,118.40288742217355,118.37994515954755,118.36026411449339,118.34338484490053,118.32891193476682,118.3165051915171,118.30587203282946,118.29676090588067,118.28895560201146,118.28227034745281,118.27654556622436,118.27164422485966,118.26744868045859,118.26385796391276,118.26078543917602,118.25815678731932,118.25590827095921,118.25398524060839,118.25234084967627,118.2509349493486,118.24973313847,118.248705946937,118.24782813408193,118.24707808600893,118.24643729807987,118.2458899306421,118.24542242773389,118.24502318992491,118.24468229367626,118.24439125066381,118.24414280142283]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(0.0,900.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1195.png', dpi=300)
