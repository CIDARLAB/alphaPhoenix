import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [339.15405275876367,5728.240449152631,10266.52317794627,14088.21102573407,17306.359926960828,20016.203991459002,22297.962510991023,24219.205265657245,25836.84554224799,27198.81940426294,28345.500556404793,29310.892398324566,30123.63232630942,30807.83783061903,31383.819290177602,31868.680449963358,32276.824265381892,32620.379015657807,32909.55724270467,33152.95809887219,33357.82201685339,33530.245213127295,33675.36035655293,33797.488734006794,33900.2684060385,33986.76213785934,34059.54829469247,34120.7973880837,34172.33653643715,34215.703746357,34252.19362085991,34282.895847333784,34308.72760481113,34330.460850420925,34348.74529349351,34364.127738260046,34377.06836865611,34387.95445824138,34397.111912018365,34404.814982723845,34411.29445008403,34416.74450597283,34421.32855004683,34425.18406811882,34428.42673831907,34431.153887175766,34433.44739844685,34435.3761612824,34436.9981306113,34438.36206112125,34439.508966497124,34440.47334741216,34441.28422488642,34441.96600983474,34442.53923474942,34443.02116935381,34443.42633862054,34443.766958618246,34444.053303203385,34444.294012530176,34444.49635259891,34444.66643360615,34444.809393630596,34444.9295531528,34445.03054503583,34445.115423860436,34445.186757891,34445.2467064292,34445.297084874954,34445.33941944648,34445.374993201534,34445.40488474149,34445.430000760505,34445.45110341779,34445.46883335553,34445.483729054504,34445.49624310959,34445.50675591496,34445.51558717052,34445.52300555658,34445.529236867675,34445.534470851046,34445.53886695565,34445.542559165195,34445.54566006101,34445.548264237324,34445.5504511721,34445.55228764012,34445.55382974128,34445.555124605286,34445.55621182446]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/926/out0.png', dpi=300)
