import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [399.43570506393996,8221.725040323088,14803.907528002344,20338.405786927113,24988.54674614268,28892.67675802017,32167.848676840946,34913.0317881624,37211.868655141625,39135.02585315553,40742.1902606098,42083.760383194334,43202.27748293515,44133.635872605686,44908.106395508556,45551.20217004436,46084.411265548304,46525.817121816974,46890.62421253738,47191.603640808644,47439.470988837085,47643.20677050981,47810.328186554965,47947.11951573418,48058.82734478644,48149.825889933825,48223.75687330702,48283.64775386935,48332.011552465585,48370.931036097834,48402.12962277205,48427.03102357262,48446.80934363674,48462.43111083867,48474.690484054605,48484.238706728465,48491.608711788125,48497.23564711783,48501.4739736467,48504.61168797048,48506.88213594733,48508.4738108719,48509.53846786929,48510.19783352967,48510.54914519175,48510.66971652309,48510.620694137106,48510.45014306581,48510.19557622918,48509.88602396617,48509.54372367002,48509.1854961306,48508.823863929836,48508.46795781924,48508.124249141365,48507.79713979345,48507.489435761985,48507.202725705065,48506.93768227656,48506.69430074551,48506.472086861126,48506.270203758664,48506.087585919595,48505.923026728626,48505.77524495761,48505.64293450828,48505.5248009255,48505.41958752074,48505.326093393436,48505.24318518717,48505.169804052464,48505.10496898743,48505.047777485146,48504.997404220325,48504.95309834904,48504.91417986771,48504.88003537502,48504.850113498724,48504.82392018419,48504.80101398998,48504.78100149535,48504.76353289291,48504.74829781511,48504.73502142431,48504.72346078218,48504.71340150317,48504.70465468926,48504.697054137425,48504.69045380754,48504.684725535815,48504.67975697762]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/486/out0.png', dpi=300)
