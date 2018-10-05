import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [234.58778464900956,234.58873603871234,234.58952580635452,234.5901810716205,234.59072445102848,234.59117479887587,234.59154782725713,234.59185662475932,234.59211209024002,234.59232329550528,234.59249778844563,234.59264184632806,234.5927606873745,234.5928586474397,234.5929393274982,234.5930057167218,234.59306029515307,234.59310511932688,234.59314189364653,234.59317202986287,234.59319669662017,234.59321686071186,234.5932333214192,234.59324673908088,234.59325765885245,234.59326653045676,234.59327372459458,234.59327954657292,234.5932842476183,234.59328803426237,234.59329107612496,234.59329351236408,234.5932954570186,234.5932970034306,234.5932982279039,234.5932991927287,234.5932999486804,234.5933005370824,234.59330099150787,234.59330133918255,234.59330160213983,234.59330179817155,234.59330194160935,234.59330204396662,234.5933021144649,234.5933021604655,234.59330218782264,234.5933022011722,234.59330220416746,234.59330219967106,234.59330218991158,234.59330217661045,234.59330216108503,234.59330214433194,234.59330212709446,234.5933021099167,234.5933020931872,234.59330207717383,234.59330206205175,234.59330204792533,234.59330203484592,234.59330202282558,234.593302011848,234.59330200187685,234.5933019928623,234.59330198474618,234.59330197746542,234.59330197095517,234.59330196515083,234.5933019599894,234.5933019554105,234.59330195135718,234.59330194777618,234.5933019446182,234.59330194183786,234.59330193939383,234.5933019372485,234.59330193536786,234.5933019337213,234.59330193228138,234.5933019310235,234.59330192992576,234.5933019289687,234.59330192813505,234.59330192740953,234.59330192677862,234.59330192623037,234.5933019257543,234.59330192534122,234.59330192498302,234.59330192467263]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/576/out0.png', dpi=300)
