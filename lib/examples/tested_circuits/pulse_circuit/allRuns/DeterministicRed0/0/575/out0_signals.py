import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [232.50899787382718,2127.0516991127806,3720.7815395471534,5060.713675036366,6186.657673976161,7132.1905303545245,7925.609376347419,8590.756179243832,9147.718947905509,9613.425015824147,10002.142103488271,10325.90112763818,10594.85275574981,10817.5678756548,11001.290550001188,11152.150662755294,11275.34232593884,11375.273178363874,11455.688949942916,11519.777057963724,11570.252515420776,11609.429034190362,11639.277866323057,11661.476618044297,11677.449974027997,11688.403975023108,11695.35520089828,11699.15593217068,11700.51610820304,11700.022680835875,11698.156785617446,11695.309020897208,11691.793034615792,11687.857562777515,11683.697033747214,11679.460840110893,11675.26137751969,11671.18095207895,11667.277660901584,11663.590351666266,11660.142766353702,11656.946970928038,11654.006167115609,11651.316975277463,11648.871269172612,11646.65763473767,11644.662516316253,11642.871105395781,11641.26801908076,11639.83780838952,11638.565330072794,11637.436010030617,11636.436021524802,11635.552397199612,11634.773090371535,11634.086998062045,11633.48395575619,11632.954711808346,11632.49088772256,11632.084929153056,11631.73005135054,11631.420181878762,11631.14990270574,11630.914393202987,11630.709375137041,11630.531060387568,11630.376101856482,11630.24154782698,11630.124799877234,11630.023574340055,11629.93586721842,11629.859922410447,11629.794203060283,11629.737365829222,11629.688237870198,11629.645796286084,11629.609149855361,11629.5775228163,11629.550240510654,11629.526716700773,11629.50644238655,11629.488975962266,11629.473934566964,11629.460986495133,11629.449844547065,11629.440260210147,11629.432018573447,11629.42493388824,11629.41884569656,11629.413615458472,11629.409123616597]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/575/out0.png', dpi=300)
