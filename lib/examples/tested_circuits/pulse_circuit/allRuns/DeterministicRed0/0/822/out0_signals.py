import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [232.4917257931941,1779.2181593189923,3060.4049039709453,4116.330162470361,4981.489536711863,5685.029701572641,6251.549013239975,6701.809513070136,7053.346227470297,7320.984045027002,7517.274906209028,7652.86710808556,7736.816996668606,7776.851795483602,7779.590947976153,7750.732162511139,7695.207337027351,7617.3126908135555,7520.816725520908,7409.049058287444,7284.972699600542,7151.241972469427,7010.247976257093,6864.153280462748,6714.917385753256,6564.314405245962,6413.944389914357,6265.239729071975,6119.46807509486,5977.733238050098,5840.975437861191,5709.972164021812,5585.340666478294,5467.542797348756,5356.892572123893,5253.566463733982,5157.616127982539,5068.983020157975,4987.514219962039,4912.978737006553,4845.083608516756,4783.489201503889,4727.823267396997,4677.693443989705,4632.698038983459,4592.435049338016,4556.509465116551,4524.538974353359,4496.158228956434,4471.0218542024695,4448.806390883849,4429.211354429815,4411.9595822096535,4396.797022897161,4383.492102244041,4371.834779705501,4361.6353912630475,4352.723356221913,4344.94581010847,4338.166212199357,4332.262964659123,4327.128070625403,4322.665850680495,4318.791730780377,4315.431109665804,4312.5183098528405,4309.995613305177,4307.812380661371,4305.92425128119,4304.292420261873,4302.8829878523175,4301.6663762735525,4300.616808765534,4299.711845664891,4298.931972428979,4298.260234721118,4297.681915931065,4297.184252800842,4296.756185141751,4296.388135950807,4296.07181855424,4295.800067715504,4295.56669194057,4295.366344491285,4295.194410876366,4295.046910828496,4294.920412994826,4294.811960767422,4294.719007860576,4294.639362404478,4294.571138470593]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/822/out0.png', dpi=300)
