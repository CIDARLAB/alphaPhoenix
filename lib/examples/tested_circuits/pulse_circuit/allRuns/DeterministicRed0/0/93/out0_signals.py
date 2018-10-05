import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [234.22818983610765,763.6820227491689,1208.6238427059263,1581.775857824247,1894.08557469645,2154.939111754326,2372.3604009308247,2553.1919732992596,2703.2562133451825,2827.497585250324,2930.10711702234,3014.630758283623,3084.06331295325,3140.929604227361,3187.3544209325282,3225.1226579263853,3255.7309190184264,3280.4317098256925,3300.2712156193766,3316.12153791117,3328.708154867134,3338.6332717121113,3346.395642138645,3352.407364524153,3357.0080896482636,3360.4770175600015,3363.0430095505944,3364.893096026294,3366.179621704509,3367.0262353053613,3367.5329011764697,3367.7800845305455,3367.832239709281,3367.7407116807194,3367.5461444456832,3367.2804758294355,3366.9685859639594,3366.629656355634,3366.278287545965,3365.9254158014146,3365.579062829969,3365.244947057633,3364.9269803687866,3364.627670299816,3364.3484443711386,3364.0899104586183,3363.852064763498,3363.6344569737166,3363.4363205613154,3363.2566747816936,3363.0944037886807,3362.948317319187,3362.8171966020823,3362.6998284822434,3362.595030200486,3362.5016668148883,3362.4186628732364,3362.3450096367537,3362.2797689009153,3362.2220742506024,3362.171130416406,3362.126211259906,3362.0866568028428,3362.0518696236427,3362.0213108710677,3361.994496085282,3361.9709909695443,3361.95040721787,3361.932398474285,3361.9166564759266,3361.902907414085,3361.890908533278,3361.880444977833,3361.871326887461,3361.863386737498,3361.8564769153304,3361.850467521651,3361.8452443834008,3361.8407072641908,3361.8367682575486,3361.833350348332,3361.830386127963,3361.827816649685,3361.8255904107245,3361.823662449063,3361.8219935433285,3361.820549505232,3361.8193005547896,3361.818220769452,3361.8172875990435,3361.8164814391835]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/93/out0.png', dpi=300)
