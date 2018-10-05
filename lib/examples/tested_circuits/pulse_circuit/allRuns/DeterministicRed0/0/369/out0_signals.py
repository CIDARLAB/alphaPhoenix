import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [228.19294289854744,228.09285971502766,227.76539252495317,227.21436165374078,226.44296832349727,225.45294180575473,224.2453383699098,222.82147570622024,221.1838900584715,219.33725488321627,217.28919731467022,215.05094307419674,212.6377222705089,210.0688816410943,207.36767353278134,204.56072539307158,201.67723010387365,198.74793014503095,195.80399091048784,192.87586648439594,189.9922541738285,187.17921474919646,184.4595085078408,181.85216847229148,179.3723060405065,177.03112438251367,174.83610214516102,172.79130433848127,170.89777736507259,169.15398929825844,167.55628299733405,166.09931703002607,164.7764766096651,163.58024319816582,162.50251675470403,161.53488874144068,160.66886700100028,159.89605564636744,159.20829433447395,158.59776191165437,158.05704958604753,157.57920863692902,157.15777732456237,156.7867912015108,156.4607805102133,156.17475782628057,155.9241986018903,155.70501679800776,155.51353737501105,155.34646704777978,155.2008643978363,155.07411017212928,154.96387838046522,154.86810862628542,154.78497996325066,154.71288645780538,154.6504145507607,154.59632224464553,154.54952009432037,154.50905394284308,154.47408932002514,154.44389740517533,154.41784244625683,154.3953705234924,154.37599954505484,154.35931036484263,154.3449389166475,154.33256926463002,154.321927476434,154.3127762321148,154.3049100890438,154.29815132987622,154.29234632738252,154.2873623663429,154.28308486872604,154.27941497397708,154.27626743141263,154.27356876645499,154.2712556867493,154.2692736981087,154.2675759037469,154.2661219634107,154.2648771918447,154.2638117785287,154.26290011286446,154.26212020095286,154.26145316185588,154.26088279279344,154.26039519404912,154.25997844557065,154.2596223282895]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/369/out0.png', dpi=300)
