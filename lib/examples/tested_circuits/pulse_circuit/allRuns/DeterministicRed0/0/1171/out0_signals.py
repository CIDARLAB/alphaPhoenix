import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [308.35249040660136,334.029822055174,355.6444041481641,373.8330219257793,389.13361367466274,402.00045704985564,412.81704924308076,421.9070262045914,429.54341550282805,435.956473944978,441.34032384649254,445.8585699887214,449.64905208516325,452.82786442198585,455.4927544320512,457.7259951929893,459.59681246553333,461.16343465808467,462.47482372385554,463.57213616818717,464.48995584279345,465.2573338337891,465.89866534315235,466.4344288751086,466.8818091472582,467.2552218466413,467.5667555543158,467.8265437926114,468.04307814248057,468.22347167939444,468.37368053832955,468.4986902018727,468.602672076597,468.6891150530006,468.76093600910417,468.82057259665703,468.8700611242632,468.9111019087827,468.9451140924764,468.9732816078878,468.9965917063538,469.01586724164395,469.0317937110832,469.044941897112,469.0557868179644,469.0647235830598,469.07208065350187,469.07813092785136,469.0831010064401,469.0871789298339,469.09052064017925,469.09325537391095,469.0954901605157,469.09731357382003,469.0987988585242,469.10000653476715,469.1009865667808,469.1017801676599,469.1024213005035,469.10293792632416,469.10335304085027,469.10368553542617,469.10395091141396,469.1041618726479,469.10432881643163,469.10446024016954,469.1045630778861,469.1046429785094,469.1047045358151,469.1047514782691,469.1047868256248,469.10481301797756,469.10483202201755,469.1048454184188,469.1048544736346,469.10486019881427,469.1048633980905,469.1048647081032,469.1048646303042,469.1048635573216,469.1048617944406,469.10485957707385,469.10485708494434,469.1048544535733,469.10485178356515,469.10484914809314,469.10484659891665,469.1048441712042,469.1048418873851,469.10483976021374,469.1048377951973]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/1171/out0.png', dpi=300)
