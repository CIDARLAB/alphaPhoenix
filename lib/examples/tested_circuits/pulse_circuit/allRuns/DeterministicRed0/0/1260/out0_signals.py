import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [378.91389377064297,849.365314297281,1200.9836254266559,1448.5136114695927,1611.087796277922,1706.216901314831,1748.9054473954,1751.6505137490026,1724.677646167717,1676.2398566807049,1612.9182449752577,1539.8998102752762,1461.2232284823672,1379.9905196305785,1298.5460451578651,1218.6259109626576,1141.4814598302848,1067.9805829138481,998.6903365687655,933.9439770826547,873.8951125886357,818.5612671466804,767.8587812350062,721.6306456083953,679.668583930754,641.7304619200339,607.5539030614616,576.866828452987,549.3955059805478,524.8705868335039,503.0315207854096,483.6296716379743,466.430397331706,451.2143126761027,437.7779142508799,425.9337150983374,415.5100101039188,406.3503705053228,398.31294706952576,391.26964559639475,385.1052250822104,379.7163577867293,375.01068127783384,370.9058649746666,367.3287075844785,364.2142768948513,361.50509945951063,359.1504046376749,357.10542506942727,355.3307538677834,353.79175747451205,352.4580421700023,351.30297157067184,350.3032320264466,349.43844259273885,348.69080615274265,348.04479827157274,347.48689044554976,347.0053045453042,346.5897954226645,346.2314588447745,345.92256212403214,345.65639502132694,345.4271387068365,345.2297507631241,345.059864406576,344.91370028343897,344.78798936477136,344.6799056199874,344.5870072912771,344.5071857212756,344.43862080439357,344.37974223883,344.32919585218553,344.2858143595328,344.2485919895773,344.2166624829247,344.1892800272238,344.1658027477938,344.14567841996114,344.12843211135373,344.1136554994214,344.1009976420117,344.09015700742077,344.0808745954095,344.0729280026272,344.0661263050852,344.06030564709556,344.05532544073725,344.0510650926118,344.0474211858277]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/1260/out0.png', dpi=300)
