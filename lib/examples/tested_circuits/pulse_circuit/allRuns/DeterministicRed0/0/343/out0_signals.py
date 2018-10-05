import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [228.19294289854744,2111.8025325948365,3698.07439948891,5033.937918567623,6158.9166086756,7106.296663121573,7904.111134522979,8575.968850973208,9141.752552345833,9618.206878420206,10019.433584122908,10357.308617414252,10641.833386903494,10881.430601845395,11083.193429381472,11253.095334401607,11396.166805490644,11516.644191781104,11618.095050983464,11703.523715876741,11775.460199703557,11836.035069581796,11887.042502785367,11929.993390495925,11966.160059656406,11996.61393572846,12022.257260426379,12043.84980270218,12062.031353194978,12077.34066765742,12090.23141984704,12101.085635917167,12110.225007845895,12117.920420700711,12124.399975695917,12129.855746498037,12134.449468755205,12138.317331262027,12141.574010588405,12144.316068613138,12146.624813548775,12148.568709165762,12150.205403551554,12151.583437478726,12152.743682972125,12153.720554678042,12154.543029912193,12155.235507598827,12155.818531543093,12156.309399461517,12156.722675812623,12157.070623620642,12157.36356808633,12157.61020275845,12157.817847338227,12157.992664755484,12158.139843951734,12158.263753787634,12158.36807263057,12158.455897469605,12158.529835790203,12158.59208293228,12158.644487224932,12158.688604828978,12158.725745913347,12158.757013534534,12158.78333637204,12158.805496290548,12158.82415154627,12158.839856325738,12158.853077196572,12158.864206958202,12158.873576303413,12158.88146363668,12158.888103340583,12158.893692735537,12158.898397939418,12158.90235880088,12158.90569305282,12158.908499809224,12158.910862509205,12158.91285139559,12158.91452560165,12158.9159349079,12158.917121221135,12158.918119819607,12158.918960401325,12158.91966796658,12158.92026356093,12158.920764900691,12158.921186899503]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/343/out0.png', dpi=300)
