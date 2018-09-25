import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [203.36013275501313,6445.109006522093,12392.488386535808,18051.277752984948,23430.536888460727,28540.26123264022,33390.69015478139,37992.023919709434,42354.2889367102,46487.268687257325,50400.46763309869,54103.09316163804,57604.04800305556,60911.9289837225,64035.02971736689,66981.34577553856,69758.58141669571,72374.15727505971,74835.21861045348,77148.6438488144,79321.05322789749,81358.81742004551,83268.06604349498,85054.69600150306,86724.3796084107,88282.57247607138,89734.52114450278,91085.27044826963,92339.6706157362,93502.38410247421,94577.89216314917,95570.50116841115,96484.34867488385,97323.40925742351,98091.50011352044,98792.28645012228,99429.2866633352,100005.8773214565,100525.29796165007,100990.65571032466,101404.92973694122,101770.97555057786,102091.52914813836,102369.21102261514,102606.53003932131,102805.88718750098,102969.57921421666,103099.80214690749,103198.65471051585,103268.14164459778,103310.17692536798,103326.58689718702,103319.1133175773,103289.41631945882,103239.07729392634,103169.60169654625,103082.42177983637,102978.8992543033,102860.32788015058,102727.93599153576,102582.8889550449,102426.29156386819,102259.19036899865,102082.57594863581,101897.38511685836,101704.50307252911,101504.7654893144,101298.9605476342,101087.83090930818,100872.07563562568,100652.35204954102,100429.27754268082,100203.43132784289,99975.35613766752,99745.55987017021,99514.51718183732,99282.6710290039,99050.43415825385,98818.19054660524,98586.29679226935,98355.08345679719,98124.85635945389,97895.89782468723,97668.46788358173,97442.80543021431,97219.12933384965,96997.63950793492,96778.51793687227,96561.92966156425,96348.0237247421,96136.93407709879]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1910.png', dpi=300)
