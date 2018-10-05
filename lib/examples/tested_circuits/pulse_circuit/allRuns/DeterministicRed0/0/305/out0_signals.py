import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [226.65793011164607,226.5521817134434,226.19418587914913,225.5880065653641,224.73939876719746,223.65318675701366,222.3339845477543,220.78715510199706,219.019770336735,217.04147456675676,214.86517384917613,212.50748211591824,209.98887007783568,207.33348811824544,204.56866775171156,201.72414241858826,198.83106062043078,195.92088638932614,193.02428987068265,190.17012376485152,187.3845621574703,184.69045165319585,182.1068961764146,179.64907103210504,177.32824195723524,175.1519522435435,173.12433533631418,171.2465103594591,169.5170220863093,167.93229326942708,166.48706453775105,165.17480423142044,163.9880769288117,162.91886470606255,161.95883927195635,161.0995861063788,160.33278374587894,159.65034258434605,159.04450817005852,158.50793414485838,158.0337298243896,157.61548707070034,157.24729064509447,156.9237157127092,156.6398156451443,156.39110276326053,156.173524197259,155.98343462357863,155.81756727622007,155.67300431765136,155.54714739314286,155.43768897603434,155.34258493529364,155.260028615498,155.18842660792328,155.12637630498722,155.07264526454856,155.02615236173529,154.9859506707833,154.95121199503927,154.9212129474915,154.89532247500938,154.87299071532536,154.85373907540227,154.83715142217306,154.8228662809094,154.81056994203283,154.79999038353958,154.79089192299006,154.7830705199366,154.7763496565246,154.7705767306516,154.76561990240972,154.76136534050244,154.75771482087978,154.75458363495986,154.75189876949736,154.74959732443168,154.74762513891287,154.7459355991871,154.74448860514903,154.74324967516108,154.7421891712295,154.74128162884162,154.74050517770763,154.7398410414173,154.73927310552358,154.73878754490974,154.73837250248476,154.73801781228534,154.73771476096854]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/305/out0.png', dpi=300)
