import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [630.1716989542213,631.603005471505,632.9535446454084,634.2275025628495,635.428861593563,636.5614097242349,637.6287494950744,638.634306554033,639.5813378433955,640.4729394330005,641.3120540138794,642.1014780656574,642.8438687106118,643.5417502668553,644.197520512685,644.8134566737324,645.3917211441398,645.9343669526031,646.4433429837337,646.9204989648242,647.3675902277369,647.7862822552829,648.1781550211172,648.5447071318396,648.8873597796716,649.2074605137587,649.5062868378506,649.785049641806,650.0448964740939,650.286914662172,650.5121342873654,650.7215310206003,650.9160288251005,651.0965025319064,651.2637802938439,651.4186459233424,651.5618411192798,651.6940675878222,651.8159890620191,651.9282332247202,652.0313935391879,652.1260309915964,652.2126757494314,652.2918287396355,652.3639631501806,652.4295258585881,652.4889387907726,652.5426002134307,652.5908859630639,652.6341506145881,652.672728592347,652.7069352262355,652.7370677555045,652.7634062827175,652.7862146802114,652.8057414513137,652.8222205484626,652.8358721502856,652.8469033995946,652.8555091041687,652.8618724021121,652.8661653934902,652.868549739872,652.8691772333299,652.8681903363771,652.8657226942576,652.8618996209314,652.8568385600436,652.8506495220983,652.8434354990077,652.8352928571265,652.8263117098344,652.8165762706753,652.8061651880183,652.7951518621549,652.7836047457088,652.7715876281902,652.7591599054853,652.7463768350383,652.733289777443,652.7199464251283,652.7063910187893,652.6926645521831,652.6788049658802,652.6648473305315,652.6508240201862,652.6367648761668,652.6226973619878,652.6086467097738,652.5946360586169,652.5806865852877]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1736.png', dpi=300)
