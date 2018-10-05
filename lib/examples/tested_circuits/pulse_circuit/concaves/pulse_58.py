import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy = [232.06823074815145,232.245820692488,232.22603781238286,232.0172974871259,231.62572154846194,231.05428319166703,230.30334628554695,229.37132913239543,228.25543417214286,226.95243673195577,225.4595244753396,223.77516660698518,221.8999756327579,219.83750839199593,217.59494095832062,215.18354770785268,212.61892148985692,209.92089064215068,207.11311805312866,204.22240315129986,201.27774308818087,198.30923754444476,195.34693713415592,192.4197358935458,189.55439489706407,186.77476051985423,184.10121261876037,181.5503502014444,179.13489901881837,176.86380917926456,174.7425018286374,172.7732213469709,170.95545183307416,169.28636212881668,167.76125068380102,166.37396893875064,165.11730877592956,163.98334547463094,162.96373232859892,162.0499466316328,161.23348923248068,160.50604146939716,159.85958420132664,159.2864840296007,158.77955180223069,158.3320782366889,157.93785108433948,157.59115776422604,157.28677686910112,157.01996142893375,156.78641633157847,156.5822718563033,156.40405488821912,156.24865904284943,156.1133146444927,155.99555926380367,155.89320932463212,155.80433313237702,155.72722555068464,155.6603844552546,155.6024890182479,155.55237982026566,155.5090407455421,155.47158258685326,155.43922826712722,155.4112995727353,155.38720528719517,155.3664306121098,155.34852776345514,155.33310763490243,155.319832425005,155.30840913122952,155.29858382054738,155.2901365932916,155.28287716399626,155.27664098978914,155.271285883488,155.26668905477356,155.26274452863075,155.259360895636,155.25645935361925,155.25397200474362,155.2518403761437,155.25001413596092,155.2484499799384,155.24711066671185,155.2459641825883,155.2449830189679,155.24414354764542,155.24342548109266,155.24281140646735]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(0.0,900.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_58.png', dpi=300)
