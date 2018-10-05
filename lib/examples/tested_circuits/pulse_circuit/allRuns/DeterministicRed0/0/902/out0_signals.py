import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [339.15405275876367,5728.265661182159,10266.700654170383,14088.738832211426,17307.463903943,20018.109284971117,22300.87576345754,24223.304402226717,25842.274874853727,27205.68836699308,28353.884832163614,29320.836533997368,30135.153188015236,30820.928441851782,31398.452738585354,31884.813654550224,32294.401473748258,32639.33495723967,32929.819904010576,33174.45111453128,33380.4666887732,33553.96218195677,33700.07095644105,33823.11606495165,33926.73815893238,34014.00320646241,34087.49320680702,34149.38258559123,34201.50253091755,34245.39517395731,34282.35921707223,34313.488359480136,34339.703657378384,34361.780775976426,34380.372939754656,34396.03025998896,34409.2160113939,34420.32033946962,34429.6718041194,34437.54710108544,34444.17924883671,34449.76448314,34454.468063308064,34458.42916191867,34461.76498268025,34464.574228282625,34466.94002083892,34468.93236132825,34470.61020080888,34472.02318468476,34473.21312163461,34474.21522066635,34475.05913289873,34475.7698288945,34476.36833750388,34476.87236807669,34477.296834459325,34477.654296282686,34477.95533057992,34478.208844747875,34478.42234010649,34478.60213385425,34478.753545987165,34478.88105671176,34478.98843900988,34479.07887027747,34479.1550263408,34479.219160631714,34479.27317086479,34479.3186551892,34479.35695947663,34479.38921714472,34479.4163826941,34479.43925995144,34479.458525854185,34479.474750480804,34479.48841391908,34479.49992047171,34479.50961061942,34479.51777109569,34479.524643371085,34479.53043079831,34479.53530462935,34479.53940908282,34479.54286561131,34479.54577649521,34479.54822786914,34479.55029227073,34479.55203078692,34479.55349486161,34479.55472781786]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/902/out0.png', dpi=300)
