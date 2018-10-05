import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [567.2580884956493,566.889392288488,565.2463365785111,561.7439360885352,556.2278186519029,548.7789356850908,539.6082011350113,528.9939928172759,517.242518572945,504.66176922116574,491.54437690565544,478.1567634120615,464.73292253894823,451.4716506192759,438.5362990694121,426.05629757125655,414.1298380034612,402.8272351367862,392.1945935816579,382.25751006042054,373.02462401195953,364.4908972604933,356.64055576984015,349.4496650937936,342.8883382373453,336.9225925883881,331.51588352805743,326.63034800402244,322.2277936007057,318.2704682536124,314.721643900887,311.54604459583965,308.7101463955603,306.18237300569905,303.93320790332564,301.93524060256505,300.1631619397937,298.5937207667694,297.2056522561876,295.97958613388914,294.8979415349791,293.9448138122418,293.1058574773218,292.36816850110534,291.72016841374733,291.1514920029478,290.6528798897697,290.216076845285,289.8337363817533,289.4993318942442,289.2070744300022,288.95183701268854,288.72908533781566,288.5348145765639,288.36549197134707,288.2180048726543,288.08961384846566,287.9779104913058,287.8807795508514,287.79636502955225,287.72303989307545,287.65937906500307,287.6041353949269,287.5562183099427,287.51467488084273,287.4786730554965,287.4474868325921,287.42048316880323,287.3971104313463,287.37688822568094,287.3593984447067,287.3442774011942,287.33120891936835,287.31991827455164,287.31016688163214,287.30174764389386,287.294480883499,287.2882107837141,287.28280228089386,287.27813835134043,287.2741176445359,287.27065241985713,287.2676667490772,287.2650949513575,287.26288023146213,287.2609734954869,287.25933232153136,287.2579200655157,287.2567050847976,287.25566006440306,287.25476143258805]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/744/out0.png', dpi=300)
