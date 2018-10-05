import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [464.64026548145284,464.3545580750224,463.4655555150828,461.6603016480388,458.8419402254716,455.04200535864334,450.3653748246161,444.95485807463916,438.9682078946122,432.5633197053265,425.888985670869,419.0794803447355,412.2517994304126,405.504718870506,398.91907347166705,392.5588178564378,386.47255222923565,380.69528505527575,375.25027251175857,370.15082580176727,365.40201582937533,361.00223308350724,356.94458117679835,353.2180969605974,349.8088000135462,346.7005806933731,343.8759397664412,341.31659460161853,339.00396758529536,336.91957220120565,335.0453114343041,333.3637020310051,331.8580368426654,330.5124961100336,329.31221719118923,328.24333094451976,327.29297178184373,326.4492673209198,325.7013125973894,325.03913294302504,324.4536388949784,323.936575861845,323.48047072765496,323.0785771146376,322.72482064006647,322.41374518231316,322.1404609076721,321.9005945945548,321.69024261806936,321.5059268193834,321.34455337496985,321.2033746959471,321.0799543229968,320.9721347341509,320.8780079479792,320.7958887807763,320.72429060104423,320.6619034160672,320.6075741221584,320.56028875098326,320.5191565482059,320.483395726738,320.452320744434,320.42533096463455,320.40190056710424,320.3815695863071,320.36393596337416,320.3486485073499,320.33540067022375,320.3239250487701,320.3139885342597,320.30538803864385,320.2979467328192,320.2915107390603,320.285946225663,320.28113685729795,320.2769815595279,320.27339256043217,320.27029367645883,320.267618813175,320.26531065497244,320.26331952073957,320.2616023651692,320.2601219077467,320.25884587358064,320.25774633212166,320.2567991214884,320.2559833476056,320.25528094867025,320.2546763166285,320.25415596836785]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/224/out0.png', dpi=300)
