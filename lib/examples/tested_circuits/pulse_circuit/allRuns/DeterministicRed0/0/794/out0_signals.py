import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [232.4917257931941,232.7521681740023,232.97058316741564,233.15300045282586,233.3047120515291,233.43033465153937,233.5338748782432,233.61879383821392,233.6880687702349,233.7442505952884,233.7895167682136,233.82571922333545,233.85442745252666,233.87696690637472,233.89445299925055,233.9078210479253,233.9178524947287,233.9251977693334,233.93039613437386,233.93389284359492,233.93605392008882,233.9371788385266,233.9375113705993,233.93724882818594,233.9365499147611,233.93554137271929,233.93432359291552,233.9329753329727,233.9315576728566,233.9301173198747,233.92868936057056,233.92729954388437,233.92596616833697,233.92470163575135,233.92351372504538,233.92240663178487,233.92138181236808,233.92043866580963,233.91957508099847,233.91878787292808,233.91807312764615,233.91742647247025,233.91684328528922,233.91631885445804,233.91584849883574,233.91542765586541,233.91505194420608,233.9147172062605,233.91441953496937,233.9141552884291,233.91392109521595,233.91371385273794,233.91353072047448,233.913369109584,233.91322667004704,233.9131012762577,233.91299101176892,233.91289415373035,233.9128091574212,233.9127346411745,233.9126693719013,233.91261225135733,233.91256230324132,233.91251866117327,233.9124805575697,233.9124473134105,233.91241832887425,233.91239307480694,233.9123710849815,233.91235194909927,233.91233530648344,233.9123208404116,233.91230827303704,233.91229736084784,233.91228789061608,233.91227967579164,233.91227255329758,233.91226638068784,233.9122610336297,233.91225640367787,233.91225239630887,233.91224892918763,233.91224593064086,233.91224333831408,233.91224109799134,233.9122391625593,233.91223749109875,233.91223604808866,233.91223480270932,233.912233728233,233.9122328014916]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/794/out0.png', dpi=300)
