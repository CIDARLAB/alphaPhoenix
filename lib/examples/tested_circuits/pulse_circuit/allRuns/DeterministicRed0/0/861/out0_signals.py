import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [234.56966085010865,782.4952072246086,1243.6599674979366,1631.5751814766832,1957.6853110938455,2231.6773671587,2461.7454380904232,2654.816490777748,2816.7428356826067,2952.465987065221,3066.156034356912,3161.330085495421,3240.9528500529896,3307.521997380508,3363.1405483609838,3409.578233281107,3448.3234671222453,3480.6273517626228,3507.5409069927027,3529.946555002852,3548.5847299507086,3564.0763550746105,3576.94181857144,3587.6169846478333,3596.4666954265736,3603.7961505507024,3609.860492698222,3614.872877315892,3619.0112624283847,3622.424118287372,3625.235225959188,3627.547707909291,3629.447411542187,3631.0057479115326,3632.282071926677,3633.3256769218106,3634.1774650578013,3634.8713453837504,3635.4354032304605,3635.8928777156107,3636.2629783184275,3636.5615665665873,3636.801724731035,3636.9942299273584,3637.1479490750526,3637.2701676834527,3637.3668633431575,3637.4429330433136,3637.502381956445,3637.548480089735,3637.5838921578,3637.6107851556344,3637.630917375164,3637.6457119913266,3637.6563178319498,3637.6636595020827,3637.668478682855,3637.6713681155434,3637.672799529547,3637.6731465612597,3637.6727035340737,3637.6717008222226,3637.6703173981946,3637.668691060921,3637.6669267566126,3637.6651033330704,3637.6632790092617,3637.661495792874,3637.659783037824,3637.6581602999186,3637.656639620871,3637.655227347675,3637.6539255751786,3637.652733283844,3637.6516472316084,3637.6506626479622,3637.6497737694904,3637.648974248812,3637.6482574628476,3637.6476167414257,3637.6470455332064,3637.6465375226007,3637.6460867086753,3637.645687454834,3637.6453345162963,3637.6450230509254,3637.6447486178145,3637.6445071670723,3637.6442950234964,3637.6441088662164,3637.6439457059005]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/861/out0.png', dpi=300)
