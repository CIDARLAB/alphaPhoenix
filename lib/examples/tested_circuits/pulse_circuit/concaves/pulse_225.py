import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [627.3464975390542,628.7343021011405,630.0403455423408,631.2681787027936,632.421617972803,633.5044713203,634.5204474276943,635.4731202703151,636.3659151454247,637.2021043305983,637.9848075000291,638.7169946359628,639.401490286967,640.0409785495249,640.6380084162112,641.1949992779846,641.7142464502434,642.1979266410518,642.6481033099683,643.0667318849507,643.4556648172174,643.816656462182,644.1513677801629,644.461370854387,644.7481532264568,645.0131220512881,645.2576080748055,645.4828694385678,645.6900953160933,645.8804093860509,646.0548731477306,646.2144890843367,646.3602036797074,646.4929102940522,646.6134519042503,646.7226237141665,646.8211756403314,646.9098146782053,646.9892071541062,647.0599808677307,647.122727130044,647.178002701155,647.2263316326339,647.2682070185682,647.3040926594964,647.3344246431968,647.3596128461605,647.38004235942,647.3960748422602,647.4080498071929,647.4162858394368,647.4210817540059,647.4227176933802,647.4214561686035,647.4175430465292,647.4112084858157,647.4026678241601,647.3921224191464,647.3797604449762,647.3657576472528,647.3502780578845,647.3334746720839,647.3154900893456,647.2964571201994,647.2764993604507,647.255731734544,647.2342610096027,647.2121862816288,647.1895994352772,647.1665855785468,647.143223453672,647.1195858254322,647.0957398480411,647.0717474117212,647.047665470012,647.0235463488148,646.9994380381221,646.9753844673373,646.9514257650449,646.9275985040463,646.9039359324399,646.880468191483,646.8572225209356,646.8342234525537,646.8114929923645,646.789050792323,646.7669143119225,646.7450989702987,646.7236182893433,646.7024840283129,646.6817063103975]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_225.png', dpi=300)
