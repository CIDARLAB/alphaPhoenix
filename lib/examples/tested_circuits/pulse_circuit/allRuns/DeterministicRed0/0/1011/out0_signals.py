import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [534.7774278130823,557.2829141914073,564.9193459522127,559.448476632667,543.5706376214845,519.9610557829662,491.0589984871121,458.9680123604025,425.4123860140133,391.7360754940597,358.9321795878545,327.69046950413497,298.4520726565357,271.46343973961723,246.82489532798994,224.53162548383813,204.50667956751573,186.62655512572047,170.74039676529733,156.68396863214647,144.2895048439695,133.39240182185716,123.83554968344106,115.47193800287127,108.16602860625656,101.79426927412113,96.24502687213183,91.41814388005406,87.22426515097759,83.58403865124417,80.42726191581278,77.69202248937017,75.32386366830907,73.2749947925424,71.50355689534213,69.97294873252858,68.6512143425889,67.5104907853468,66.5265131617045,65.67817313447806,64.94712673765991,64.31744712770066,63.775317990334244,63.308763494718384,62.907410938225546,62.56228251248223,62.26561291846949,62.01068986058447,61.79171473880137,61.60368113238295,61.442268924270415,61.30375215087239,61.18491887714783,61.08300159202507,60.995616795172666,60.920712604065336,60.85652335144953,60.8015302690488,60.754427465005996,60.714092501447574,60.67956096592618,60.65000450752761,60.624711876208266,60.60307256346536,60.58456269465198,60.56873286896575,60.555197683116546,60.543626709590185,60.533736730878545,60.525285057580035,60.51806378137034,60.51189483392624,60.50662574033292,60.502125970650155,60.49828380644839,60.49500365050967,60.492203717747245,60.489814053930104,60.487774836177685,60.486034915568304,60.484550567714365,60.483284421914085,60.482204543593625,60.481283648293484,60.48049842850703,60.479828977295796,60.47925829488546,60.47877186639818,60.47835730053267,60.478004020463274,60.477702999465045]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/1011/out0.png', dpi=300)
