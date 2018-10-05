import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [313.4971139370574,3645.4839345229598,6263.286708123663,8264.015128619489,9741.596530905192,10779.22823511847,11449.9587101837,11817.787419848504,11938.673476032509,11861.382726125143,11628.199454543623,11275.542223122517,10834.516235195197,10331.424130625352,9788.248237611922,9223.110809932654,8650.714317252898,8082.761000477103,7528.349332468609,6994.344468344383,6485.719962955734,6005.868711753845,5556.881995898207,5139.7964917596955,4754.810003958619,4401.467420990841,4078.8189405631983,3785.5529662626527,3520.1062602492702,3280.7539791977874,3065.682157315739,2873.0450633536548,2701.0096755880227,2547.789311307852,2411.668231056497,2291.0188235044893,2184.3127712702176,2090.1274054492683,2007.148279405948,1934.1688316609348,1870.087863821503,1813.9054322456304,1764.7176409171382,1721.7107269536807,1684.1547481787227,1651.3971130317886,1622.8561354365086,1598.0147497635621,1576.4144824748707,1557.649746015068,1541.3624960296343,1527.2372739130385,1514.9966421098813,1504.3970086935356,1495.2248298285695,1487.2931731886367,1480.4386217490219,1474.518495186063,1469.40836505165,1464.999839671278,1461.1985951143058,1457.922629426302,1455.1007184548578,1452.6710529322768,1450.5800379170817,1448.7812371777115,1447.234446579185,1445.9048819730033,1444.7624684686266,1443.7812192659972,1442.9386934434824,1442.2155232196133,1441.5950022389418,1441.0627273736338,1440.6062873861417,1440.2149925688993,1439.879640169532,1439.5923110301337,1439.3461934224622,1439.1354305531136,1438.9549886494472,1438.8005429235068,1438.6683790524742,1438.5553081149183,1438.4585931866493,1438.3758860322355,1438.3051725318664,1438.244725661486,1438.1930649999213,1438.1489218727684,1438.111209361405]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/437/out0.png', dpi=300)
