import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [226.65793011164607,226.66452242585444,226.66974902919134,226.67386934845774,226.6770960349465,226.67960310667442,226.68153270918722,226.6830007246974,226.68410142172073,226.6849113058453,226.685492305836,226.6858944071323,226.6861578262502,226.68631480407686,226.68639108306013,226.68640712243487,226.68637909655487,226.6863197138181,226.68623888734606,226.68614428329823,226.68604176829942,226.68593577379886,226.6858295921119,226.68572561635432,226.68562553437957,226.68553048505913,226.68544118379154,226.68535802291458,226.6852811516923,226.6852105397163,226.68514602687443,226.6850873624696,226.68503423560347,226.6849862985513,226.68494318453554,226.68490452104297,226.6848699396144,226.68483908285899,226.6848116092998,226.68478719653874,226.68476554313258,226.68474636949185,226.68472941805095,226.6847144529063,226.68470125907535,226.68468964149807,226.68467942387258,226.68467044739666,226.68466256946857,226.6846556623872,226.68464961207982,226.68464431687872,226.68463968635973,226.68463564025137,226.68463210741956,226.68462902492928,226.68462633718283,226.68462399513274,226.68462195556617,226.68462018045705,226.6846186363814,226.68461729399183,226.68461612754604,226.6846151144854,226.68461423505892,226.68461347198834,226.6846128101707,226.68461223641455,226.68461173920628,226.6846113085037,226.6846109355537,226.68461061273163,226.68461033339963,226.68461009178245,226.68460988285787,226.68460970226104,226.68460954620016,226.68460941138304,226.68460929495237,226.68460919442958,226.6846091076655,226.6846090327973,226.68460896821114,226.6846089125093,226.68460886448173,226.68460882308125,226.68460878740188,226.6846087566602,226.6846087301789,226.68460870737255,226.68460868773548]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/257/out0.png', dpi=300)
