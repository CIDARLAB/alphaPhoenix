import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [399.43570506393996,399.8437388016914,399.1299147224082,396.8566562949042,392.9438959183346,387.5058654540969,380.75685321542767,372.9544630826893,364.3649362600646,355.2422627365299,345.816266095152,336.2866410024851,326.82093328931455,317.5550716724067,308.5954706678457,300.0220133598766,291.89143376312455,284.24077471900125,277.0907124910311,270.4486229652191,264.31132381092914,258.6674678548193,253.49958990319877,248.78582605199412,244.50133404181688,240.61944766018823,237.1125993774521,233.95304434946783,231.1134166464899,228.56714561891286,226.28875709947846,224.2540809284594,222.44038323229006,220.8264390762165,219.3925585898876,218.12057744292576,216.99382061781574,215.99704677121647,215.1163790674574,214.33922718263244,213.65420418732361,213.05104119475087,212.52050198519927,212.05429926536243,211.64501377362967,211.2860170825625,210.97139866330906,210.69589755098787,210.45483877446085,210.24407457935433,210.0599303720432,209.89915523818237,209.7588768369093,209.6365604366127,209.52997183650416,209.43714390712645,209.35634647992563,209.28605931910352,209.22494791653875,209.1718418613258,209.12571554840218,209.0856710050089,209.0509226287248,209.02078364605183,208.99465411563673,208.9720103149336,208.95239536324016,208.93541094745498,208.92071002951883,208.907990426269,208.89698916334504,208.8874775148305,208.87925664952883,208.87215381317694,208.86601898354195,208.86072194226838,208.85614971359266,208.85220432566615,208.84880085528224,208.84586572133512,208.84333519633452,208.84115410899685,208.8392747140704,208.8376557084224,208.83626137495654,208.8350608381667,208.83402741711672,208.8331380633866,208.8323728730719,208.831714663284,208.83114860479796]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/482/out0.png', dpi=300)
