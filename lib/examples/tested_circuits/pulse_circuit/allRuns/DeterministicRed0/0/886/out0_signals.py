import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [234.56966085010865,1846.5480016619347,3184.6188237361134,4290.948028753815,5201.14326457049,5945.197971601137,6548.374910653773,7031.956631275654,7413.8794910643255,7709.2687190804245,7930.88987524747,8089.529789395297,8194.318037364355,8252.998265512651,8272.15718611453,8257.41780133571,8213.602341289778,8144.869495343208,8054.829753349497,7946.642036702324,7823.0942722669615,7686.6701305472825,7539.603798005316,7383.9243673190595,7221.491194545908,7054.021377365138,6883.1103469360205,6710.246435786429,6536.820188415263,6364.129125050919,6193.378656333189,6025.67987574757,5862.045019780201,5703.381463100075,5550.485182411236,5404.034647919605,5264.586059309448,5132.570718224314,5008.295122692465,4891.944100296677,4783.587000484521,4683.186683242636,4590.610809202773,4505.64478129756,4428.005620534018,4357.356072832405,4293.318323103005,4235.4868138890915,4183.439805774894,4136.749455802913,4094.990314497842,4057.7462438324164,4024.615834750793,3995.2164544918483,3969.1870843897723,3946.190121185064,3925.9123152371776,3908.0650098124243,3892.383830890258,3878.627959313625,3866.5790984446016,3856.0402321561764,3846.8342508593296,3838.802507825112,3831.8033545379258,3825.7106922468743,3820.4125671848424,3815.8098289467857,3811.814865070329,3808.3504197419593,3805.3485005629836,3802.749374266283,3800.5006500108416,3798.5564472494707,3796.876644039785,3795.426200942374,3794.174555234071,3793.095079984686,3792.1646025428645,3791.362977102572,3790.6727062380664,3790.0786065718808,3789.5675140541293,3789.128024664577,3788.7502666880378,3788.4257010490264,3788.146946516244,3787.9076268966323,3787.7022376293844,3787.52602946067,3787.3749071291672]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/886/out0.png', dpi=300)
