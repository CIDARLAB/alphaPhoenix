import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [660.5602124875392,662.4474163709424,664.155692293295,665.6826440228201,667.0333829598253,668.2148295655779,669.2345493143051,670.1003209823431,670.8199354928979,671.4010896128946,671.8513253814965,672.1779940766753,672.3882344084675,672.4889594593456,672.4868492547932,672.3883470930905,672.1996584608697,671.9267517711929,671.5753604121553,671.1509857532984,670.6589008610827,670.1041547442576,669.4915769975621,668.8257827453684,668.1111778104012,667.3519640495828,666.5521448114046,665.7155304783428,664.8457440646631,663.9462268451291,663.0202439940961,662.0708902175626,661.1010953631851,660.1136299952233,659.1111109229748,658.0960066725935,657.0706428933063,656.0372076900186,654.9977568751531,653.9542191333313,652.9084010932025,651.8619923013599,650.8165700938789,649.7736043615641,648.7344622055153,647.7004124801111,646.6726302209794,645.6522009559641,644.6401248975145,643.6373210153231,642.6446309884094,641.6628230362026,640.6925956285058,639.734581074537,638.7893489915309,637.8574096536561,636.9392172222517,636.0351728586174,635.1456277208005,634.2708858460159,633.41120692051,632.5668089388314,631.7378707546136,630.9245345250959,630.1269080517152,629.3450670191945,628.5790571356295,627.8288961761432,627.0945759327258,626.37606407292,625.6733059100428,624.9862260876481,624.3147301809511,623.6587062179302,623.0180261228197,622.3925470846862,621.7821128537679,621.1865549682198,620.6056939138821,620.0393402196468,619.4872954909564,618.9493533839235,618.4253005225094,617.9149173611457,617.4179789951329,616.934255921086,616.4635147496451,616.0055188726043,615.5600290865507,615.1268041750476,614.7056014513284]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_58.png', dpi=300)
