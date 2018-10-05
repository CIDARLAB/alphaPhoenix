import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [234.22818983610765,238.37758755105912,241.87135563768246,244.81265526348503,247.28848412409815,249.37219785374984,251.12563999839202,252.60094094799263,253.84203701944878,254.88595301203912,255.76388488531026,256.50211356509675,257.1227760989339,257.6445163309485,258.08303383722324,258.45154696094477,258.76118333187577,259.02130917846415,259.2398069851959,259.4233095634153,259.577397349056,259.70676468022083,259.8153599113738,259.9065034637301,259.98298727177496,260.047158545566,260.1009903121967,260.1461408145206,260.18400351995945,260.2157492176394,260.24236145035013,260.2646663322496,260.28335763822344,260.29901791159176,260.3121362194287,260.3231230857197,260.3323230490674,260.340025221238,260.3464721634806,260.3518673475132,260.35638142590165,260.3601575010232,260.3633155518609,260.36595615264713,260.3681635962747,260.37000851713157,260.37155009338477,260.3728378958123,260.3739134396598,260.37481148701306,260.37556113961546,260.37618675569604,260.37670871902066,260.3771440838762,260.37750711590957,260.3778097455593,260.3780619481389,260.37827206237984,260.37844705735034,260.37859275607536,260.378714022846,260.37881492008523,260.37889883969274,260.3789686129991,260.37902660279445,260.37907478033924,260.3791147897936,260.3791480021102,260.3791755601032,260.37919841613086,260.3792173635946,260.37923306326337,260.3792460652684,260.379256827476,260.37926573083166,260.3792730921702,260.37927917490936,260.3792841979731,260.3792883432362,260.37929176173316,260.37929457883524,260.37929689856605,260.3792988071977,260.37930037624665,260.37930166496847,260.3793027224352,260.37930358926405,260.37930429905555,260.37930487958965,260.37930535382026,260.37930574070185]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/83/out0.png', dpi=300)
