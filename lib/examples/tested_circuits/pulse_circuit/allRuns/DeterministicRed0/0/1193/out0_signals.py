import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [308.35249040660136,307.8671681094577,305.5763594186997,301.2026997307617,294.77496044587997,286.4641836852961,276.55184252750627,265.4018552154514,253.42337467339223,241.02934921533083,228.59961320600723,216.4550048509113,204.84460454646324,193.9444481097062,183.86419276033544,174.65803033258533,166.3369106010211,158.88017373446118,152.24559935115235,146.37752229579525,141.2130510095624,136.6866174791295,132.73315716397275,129.29021708568317,126.29925659011947,123.70635975150725,121.46253253626912,119.52371704549469,117.85062126263935,116.40843579262095,115.16648832062947,114.09787090143848,113.17906366921311,112.3895702080961,111.7115738760975,111.12962021535957,110.63032773446915,110.20212745156444,109.83503036471075,109.5204212810444,109.25087702165378,109.02000683613089,108.8223128266683,108.65306824214606,108.50821162156963,108.3842549171968,108.27820389316008,108.18748926373375,108.10990719919874,108.04356798203182,107.98685173940618,107.93837030861465,107.89693440978328,107.861525405475,107.83127102017328,107.80542447506498,107.78334656597,107.76449027567737,107.74838756729838,107.73463805344088,107.72289927788376,107.71287838275084,107.7043249656375,107.69702495834972,107.69079538242036,107.6854798568551,107.68094475105748,107.67707589096047,107.67377573937661,107.6709609827557,107.66856046615283,107.6665134264761,107.6647679811886,107.66327983574246,107.66201117826529,107.66092973451899,107.66000796001364,107.65922234947566,107.65855284670002,107.65798234026636,107.65749623269586,107.65708207239072,107.65672923925189,107.65642867618102,107.65617265979834,107.65595460467243,107.65576889618409,107.65561074785244,107.65547607955739,107.65536141360921,107.65526378605963]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/1193/out0.png', dpi=300)
