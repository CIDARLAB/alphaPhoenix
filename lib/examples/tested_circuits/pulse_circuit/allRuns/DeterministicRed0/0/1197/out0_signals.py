import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [308.35249040660136,3599.5318836671963,6226.209217633188,8264.055599994042,9797.194401613218,10903.101557756569,11650.900043402902,12101.625753958502,12308.878084061393,12319.498244133754,12174.193805344645,11908.103009400606,11551.310858995626,11129.32996520068,10663.555376840344,10171.698446298635,9668.201446877103,9164.632373658642,8670.058051733467,8191.393173667656,7733.722992212996,7300.59790187712,6894.298870548971,6516.073476565376,6166.3430618108205,5844.88215964937,5550.97186520677,5283.529177180765,5041.214563592037,4822.520106331215,4625.840582511258,4449.52976658206,4291.9441066532445,4151.47575990872,4026.576781002722,3915.7760566210222,3817.690378888131,3731.030857339532,3654.6056887905734,3587.3201400483217,3528.1744509782225,3476.2602361307754,3430.7558516023632,3390.921098004057,3356.0915500977458,3325.6727367345497,3299.1343396752195,3276.004535096904,3255.8645655970477,3238.343601921981,3223.113931248011,3209.8864915488766,3198.4067584632808,3188.450981334181,3179.82275805623,3172.3499334783,3165.881802900393,3160.286600297875,3155.4492499913413,3151.2693603081634,3147.659438152437,3144.543304155797,3141.854689098672,3139.535993474022,3137.5371933401034,3135.8148769200184,3134.331397712879,3133.054131155014,3131.954823089245,3131.0090194524737,3130.1955676684765,3129.4961812298484,3128.8950598694196,3128.3785585583096,3127.9348993277954,3127.553920599095,3127.226859323415,3126.946161789,3126.7053194473547,3126.4987265523096,3126.321556797911,3126.1696564889285,3126.0394520854134,3125.927870234333,3125.8322686406,3125.7503763403024,3125.6802421238226,3125.6201900186265,3125.5687808835078,3125.524779290419,3125.487124977641]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/1197/out0.png', dpi=300)
