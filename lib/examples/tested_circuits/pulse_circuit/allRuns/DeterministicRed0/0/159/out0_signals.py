import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [327.40838656527797,5768.635752862061,10350.78878379804,14209.352800004928,17458.468938091006,20194.292740607056,22497.825057701535,24437.297957507595,26070.185444793995,27444.89789740266,28602.209901641298,29576.463383639955,30396.58136123067,31086.922095673264,31667.99874544642,32157.08568031969,32568.729288642986,32915.17830748671,33206.746341504375,33452.11724757898,33658.602378938514,33832.357268588145,33978.56414252288,34101.58564478472,34205.09431022553,34292.18160690378,34365.44976837864,34427.0891291271,34478.94324902704,34522.56375278733,34559.25650679837,34590.12050021771,34616.08058168713,34637.91502157354,34656.278716701905,34671.72272570842,34684.71071459857,34695.63280065212,34704.81720578592,34712.54006559574,34719.03368563935,34724.493490483044,34729.083872256284,34732.943112798115,34736.18752597262,34738.914943565236,34741.207648663076,34743.13484399462,34744.754728870175,34746.11624671638,34747.26055538991,34748.22226419755,34749.03047459726,34749.70965570122,34750.280380774064,34750.75994676874,34751.16289545873,34751.50145178099,34751.785892511485,34752.024856346936,34752.22560469047,34752.39424096899,34752.5358950685,34752.65487842897,34752.754814460786,34752.83874820486,34752.909238536085,34752.96843568572,34753.018146417424,34753.05988882096,34753.09493837549,34753.12436667174,34753.14907396162,34753.16981651801,34753.187229631265,34753.201846937285,34753.21411666181,34753.22441527216,34753.233058949794,34753.240313231014,34753.24640110808,34753.2515098361,34753.255796652316,34753.25939358141,34753.26241147245,34753.2649433905,34753.26706746561,34753.26884928614,34753.27034390902,34753.27159754828,34753.272648993225]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/159/out0.png', dpi=300)
