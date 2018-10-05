import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy = [232.50899787382718,232.5092726628975,232.509414697011,232.50940904190375,232.50925667203333,232.5089685697501,232.5085614931049,232.50805499809331,232.50746939690887,232.50682440811818,232.5061383123858,232.50542747215098,232.50470610833867,232.5039862539825,232.5032778252737,232.50258876637398,232.50192523640658,232.50129181619428,232.5006917192049,232.5001269963032,232.49959872770063,232.49910719825021,232.49865205420747,232.49823244095467,232.497847122117,232.49749458110233,232.49717310645514,232.49688086260153,232.49661594762574,232.49637643969757,232.4961604336946,232.49596606945235,232.49579155294776,232.49563517158555,232.4954953046181,232.49537042960145,232.49525912566483,232.49516007425876,232.49507205794478,232.4949939576984,232.49492474911736,232.4948634978578,232.49480935455978,232.4947615494731,232.4947193869508,232.49468223993966,232.4946495445684,232.4946207949072,232.49459553795236,232.49457336887252,232.49455392653988,232.49453688935822,232.49452197139212,232.49450891879454,232.4944975065253,232.49448753534966,232.494478829103,232.49447123220747,232.49446460742323,232.49445883381895,232.49445380494436,232.49444942718898,232.49444561831123,232.49444230612286,232.49443942731503,232.49443692641194,232.49443475484026,232.4944328701029,232.49443123504608,232.49442981721026,232.49442858825643,232.49442752345905,232.49442660125894,232.4944258028692,232.49442511192822,232.49442451419478,232.4944239972799,232.4944235504117,232.4944231642291,232.494422830601,232.49442254246813,232.49442229370464,232.49442207899693,232.49442189373812,232.49442173393558,232.4944215961306,232.49442147732813,232.49442137493563,232.4944212867098,232.49442121071027,232.49442114525937]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(0.0,900.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_536.png', dpi=300)
