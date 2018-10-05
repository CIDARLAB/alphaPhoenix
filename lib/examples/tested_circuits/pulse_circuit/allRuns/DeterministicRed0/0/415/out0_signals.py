import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [313.4971139370574,5719.338162085841,10271.506688096517,14104.509502748837,17331.70818726264,20048.634653267978,22335.7888428168,24260.998803836923,25881.411189675535,27245.169812062777,28392.830971384024,29358.556731947494,30171.120903123556,30854.757065320213,31429.873396416388,31913.65518277449,32320.572630473627,32662.80883401579,32950.62043177753,33192.64151689983,33396.13971246244,33567.231923176776,33711.06610168968,33831.97437043602,33933.6020029539,34019.016061843664,34090.796894538886,34151.11518543201,34201.796839044226,34244.37761151637,34280.1491063271,34310.19749604209,34335.43611766388,34356.63290854218,34374.43349755361,34389.38063792323,34401.93055989195,34412.46673026992,34421.31142909107,34428.73548884736,34434.96648723585,34440.19563839697,34444.583588908754,34448.26529219121,34451.354107507344,34453.94524661434,34456.11867163658,34457.94153132778,34459.47020907709,34460.752044384375,34461.82677974096,34462.72777661021,34463.48303726688,34464.11606341554,34464.64657759609,34465.0911292496,34465.463603834665,34465.775650486044,34466.037041182455,34466.25597238757,34466.439318349185,34466.5928437841,34466.7213824442,34466.82898702288,34466.91905498966,34466.994434208325,34467.05751157787,34467.110287417956,34467.15443788533,34467.19136734175,34467.2222522869,34467.248078210876,34467.269670504546,34467.28772038285,34467.3028066235,34467.315413794706,34467.32594753712,34467.334747374865,34467.34209745378,34467.34823554145,34467.353360569345,34467.35763895259,34467.36120988488,34467.36418977411,34467.366675957805,34467.36874981491,34467.37047937162,34467.37192148329,34467.37312366118,34467.37412560154,34467.37496046557]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/415/out0.png', dpi=300)
