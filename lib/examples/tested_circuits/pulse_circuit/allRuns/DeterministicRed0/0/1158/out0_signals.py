import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [308.35249040660136,5568.795967751561,9998.865824389046,13729.638138755918,16871.498438124676,19517.407722780048,21745.65294569569,23622.163330829302,25202.461054540316,26533.30401220778,27654.069272612152,28597.918152664566,29392.777383838835,30062.165400349473,30625.88819671821,31100.62534330444,31500.423498460525,31837.112019057902,32120.65296554927,32359.435859734534,32560.525914074628,32729.873076355678,32872.48807698601,32992.59068687255,33093.73457247989,33178.91244224517,33250.644595392274,33311.053493106556,33361.92655846712,33404.76906325058,33440.84866641399,33471.23292205766,33496.82086665084,33518.369620122816,33536.516787892404,33551.79932666854,33564.669432224655,33575.50791923639,33584.635489067245,33592.32221889567,33598.79555295021,33604.247032299194,33608.83796231845,33612.70418552759,33615.96010101569,33618.702049384636,33621.0111633654,33622.955768452724,33624.59340458938,33625.972528718834,33627.13394858242,33628.112030185126,33628.9357146573,33629.62937460004,33630.2135352522,33630.705481815596,33631.11977091352,33631.468661318824,33631.762476678065,33632.00991098269,33632.21828581985,33632.393767014815,33632.54154707516,33632.66599883511,33632.77080484621,33632.85906634295,33632.933395007494,33632.995990248804,33633.04870428276,33633.09309693899,33633.13048181608,33633.161965150895,33633.188478552125,33633.210806566625,33633.2296098942,33633.2454449378,33633.258780267555,33633.27001048591,33633.279467903936,33633.28743237457,33633.29413957353,33633.29978797305,33633.30454471475,33633.308550555455,33633.31192403223,33633.31476496997,33633.3171574353,33633.31917222411,33633.320868956514,33633.322297841,33633.32350116016]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/1158/out0.png', dpi=300)
