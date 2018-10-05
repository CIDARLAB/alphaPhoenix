import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [234.56966085010865,234.89499582861626,235.14741787257665,235.32607209359463,235.43503093482641,235.4802726853739,235.46841911533923,235.40617962423096,235.30010633719976,235.15649082452487,234.98132316719145,234.78027456826877,234.5586848848613,234.321547344994,234.07348882312928,233.81874736577916,233.56115016654826,233.30409547046852,233.05054136644466,232.80300345537376,232.56356225077616,232.33388010009605,232.1152265528114,231.90851051565795,231.71431723748424,231.5329481108099,231.36446142055914,231.20871243580322,231.06539156891182,230.93405967205555,230.8141798664154,230.70514558349618,230.60630473012185,230.51698006682588,230.43648601677717,230.3641422055853,230.29928407916583,230.24127096531265,230.1894919421027,230.14336985912317,230.10236383098535,230.06597049076666,230.03372425697114,230.00519683352044,229.97999612967314,229.95776475655967,229.9381782297327,229.9209429829879,229.90579427770962,229.8924940740088,229.88082891471893,229.87060786063853,229.86166050496226,229.85383508634456,229.84699671321533,229.8410257065634,229.83581606419438,229.83127404624844,229.8273168793607,229.82387157510257,229.820873857135,229.81826719071756,229.81600190776626,229.81403442045658,229.8123265163691,229.81084472832055,229.80955977227288,229.80844604703455,229.80748118983863,229.80664568227542,229.80592250146864,229.80529681177998,229.8047556927413,229.80428789928646,229.80388365071892,229.80353444519716,229.80323289683844,229.80297259283736,229.80274796826754,229.80255419648353,229.80238709326642,229.8022430330628,229.80211887585116,229.8020119033377,229.80191976333273,229.80184042129392,229.80177211814106,229.80171333355509,229.80166275406813,229.8016192453359,229.8015818280578]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/866/out0.png', dpi=300)
