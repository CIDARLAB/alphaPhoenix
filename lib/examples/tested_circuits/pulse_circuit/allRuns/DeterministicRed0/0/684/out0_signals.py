import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [343.7241048112919,977.7330607876647,1500.7318990071878,1924.1873964026877,2260.822961950658,2523.178105101776,2722.9287897939985,2870.5934417191675,2975.4427020273683,3045.5180532289482,3087.707248268158,3107.84700652659,3110.835944808393,3100.7479295743883,3080.940322396834,3054.1541811151487,3022.6050578984245,2988.063995724338,2951.9289006709846,2915.286801623731,2878.967690218643,2843.590717965993,2809.6035501557803,2777.3156603230454,2746.9263097433986,2718.547903503828,2692.2253550093187,2667.952028290715,2645.682765304352,2625.344446417362,2606.8444759890685,2590.0775334914397,2574.9308837494077,2561.2884978065294,2549.034198537289,2538.054012214018,2528.237878494635,2519.4808463826525,2511.6838622588466,2504.7542377286354,2498.6058694156,2493.15926962434,2488.3414556842845,2484.0857364868716,2480.331426986817,2477.02351502707,2474.1122995665337,2471.5530150647032,2469.3054532562087,2467.333590699806,2465.6052281982957,2464.091646362317,2462.7672801506765,2461.6094140941173,2460.597899040468,2459.714890598639,2458.9446089666253,2458.2731194711396,2457.6881328961504,2457.178824511694,2456.735670614305,2456.3503013412,2456.0153685095834,2455.7244272504104,2455.471830244638,2455.252633423241,2455.062512055103,2454.8976862155055,2454.7548546993917,2454.631136515586,2454.524019169154,2454.431313007778,2454.3511109736432,2454.2817531643127,2454.2217956640397,2454.1699831608394,2454.12522491434,2454.086573685018,2454.0532072771794,2454.0244123852503,2453.9995704684893,2453.978145408938,2453.9596727357707,2453.9437502241394,2453.9300296989213,2453.9182098937304,2453.9080302333186,2453.8992654233043,2453.8917207451827,2453.8852279670145,2453.8796417911612]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/684/out0.png', dpi=300)
