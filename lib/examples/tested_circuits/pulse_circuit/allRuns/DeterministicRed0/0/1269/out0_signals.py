import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [378.91389377064297,5377.9512169171685,9345.606364140896,12426.024212667855,14754.486982912435,16449.12988992375,17612.384481136585,18333.00095227872,18687.912170568306,18743.82627610377,18558.554444398895,18182.110750056625,17657.627352414456,17022.12588645719,16307.180071977864,15539.497436734411,14741.440952836803,13931.505048595083,13124.755263986475,12333.236891548255,11566.355229650953,10831.228403135081,10133.012854095809,9475.201340010084,8859.893386089609,8288.038446013195,7759.652406854558,7274.008439463739,6829.803501710317,6425.302030544782,6058.458511417946,5727.020701615841,5428.6153214601645,5160.818026209556,4921.209440422265,4707.418980714737,4517.158115399724,4348.244612878592,4198.619217732598,4066.356067851688,3949.668032031794,3846.9080099781063,3756.5671001480973,3677.2704093745415,3607.771154887503,3546.9435967312975,3493.775237095921,3447.3586349433112,3406.8831076426636,3371.626526599875,3340.9473599057787,3314.2770707265404,3291.1129443729606,3271.011388549953,3253.5817290937125,3238.4805065307405,3225.406266124247,3214.0948249065855,3204.3149928394787,3195.864721110163,3188.5676481704163,3182.2700130511944,3176.8379054072393,3172.1548223962627,3168.119503662854,3164.6440172100984,3161.6520706710817,3159.077524337767,3156.8630841905906,3154.9591550427676,3153.3228357288326,3151.9170399997424,3150.7097284190745,3149.6732380759704,3148.7836983355023,3148.020522135428,3147.365963512009,3146.804733101075,3146.323664319637,3145.9114237946187,3145.558260375922,3145.255787758241,3144.996796347044,3144.775090545974,3144.585348122168,3144.4229987290737,3144.284119038939,3144.1653422648233,3144.0637801395383,3143.976955670952,3143.9027452136365]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/1269/out0.png', dpi=300)
