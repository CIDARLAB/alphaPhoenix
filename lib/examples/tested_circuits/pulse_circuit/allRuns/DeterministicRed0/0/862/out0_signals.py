import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [234.56966085010865,1855.5154127245382,3220.4190291599184,4369.574210021968,5336.957709691547,6151.215369388597,6836.495195510114,7413.150923321085,7898.3360172345465,8306.505052233402,8649.83683720829,8938.591438600144,9181.41138882831,9385.575773583209,9557.214544390192,9701.489262121951,9822.745512322066,9924.64141761818,10010.255983116218,10082.180429431268,10142.595174791373,10193.334712935371,10235.942283665612,10271.715935932676,10301.747333609817,10326.95444307189,10348.109063537779,10365.86001075347,10380.752637666004,10393.245268617928,10403.72303319592,10412.509509592906,10419.876522991197,10426.052390187959,10431.22885589465,10435.566927524273,10439.201782715036,10442.246896381537,10444.797510936867,10446.933553817023,10448.722089994326,10450.21938330852,10451.472628767871,10452.521408135688,10453.398912831624,10454.132971198438,10454.746911307964,10455.260285531826,10455.689478936516,10456.048220055876,10456.348009642546,10456.59848051639,10456.807699537967,10456.982420977118,10457.128299067628,10457.2500662946,10457.3516829166,10457.436462346035,10457.507176263167,10457.56614273019,10457.61530004138,10457.656268608176,10457.690402809065,10457.71883442417,10457.742509014131,10457.762216384073,10457.778616089852,10457.792258789566,10457.803604113797,10457.813035619398,10457.820873300438,10457.827384053307,10457.832790428834,10457.837277950304,10457.841001231132,10457.844089088012,10457.846648813553,10457.848769745804,10457.850526249711,10457.85198020681,10457.853183093783,10457.854177717378,10457.854999662122,10457.855678498083,10457.856238788201,10457.856700928216,10457.857081846825,10457.857395589179,10457.857653802997,10457.857866143448,10457.858040610274]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/862/out0.png', dpi=300)
