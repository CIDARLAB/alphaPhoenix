import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [682.0251584058062,683.788207809574,685.4472368761961,687.0071967238067,688.4728305441324,689.8486815427235,691.1391006003083,692.348253663995,693.4801288768974,694.5385434545925,695.5271503166372,696.4494444811737,697.3087692304528,698.108322054891,698.8511603830697,699.540207104866,700.1782558946924,700.7679763416094,701.3119188928653,701.8125196172074,702.2721047941079,702.6928953348479,703.0770110412072,703.4264747073206,703.7432160700758,704.0290756132499,704.2858082304091,704.5150867514279,704.7185053373225,704.8975827479371,705.0537654868683,705.1884308278704,705.30288972684,705.3983896233432,705.476117135519,705.5372006520638,705.5827128248795,705.6136729658517,705.6310493511094,705.6357614360061,705.62868198396,705.6106391121838,705.5824182572397,705.544764063256,705.4983821955526,705.4439410823293,705.3820735869899,705.3133786135845,705.2384226477773,705.1577412356646,705.0718404026974,704.9811980148803,704.8862650843583,704.7874670214242,704.6852048349191,704.5798562829317,704.4717769756355,704.3613014320505,704.2487440924473,704.1344002880616,704.0185471697263,703.9014445969797,703.7833359891503,703.6644491398719,703.5449969964324,703.4251784053108,703.3051788252131,703.1851710088698,703.0653156548154,702.94576203033,702.826648566678,702.7081034277423,702.5902450531129,702.4731826766509,702.3570168215136,702.2418397725899,702.1277360272616,702.0147827253758,701.903050059276,701.792601664714,701.6834949934325,701.5757816681777,701.4695078208756,701.3647144146785,701.2614375505583,701.1597087591027,701.0595552781422,700.9610003168121,700.8640633066346,700.7687601401759,700.6751033978214]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_137.png', dpi=300)
