import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [660.5602124875392,673.5586758371837,685.8032587951789,697.2949446527792,708.0555808752243,718.1126031317185,727.4953524217356,736.2336505675702,744.3571283388968,751.894873062139,758.8752332419743,765.3257083822717,771.2728884157941,776.7424235602721,781.7590135618755,786.3464096374528,790.5274248900448,794.3239504354106,797.7569753821818,800.8466093872247,803.6121068891763,806.0718923807613,808.2435862583566,810.1440309124273,811.7893168119873,813.1948084012222,814.3751696741751,815.3443893288987,816.1158054291107,816.7021295215435,817.1154701725354,817.3673558991719,817.4687574793483,817.430109632128,817.2613320651909,816.9718498903707,816.5706134115446,816.0661172916575,815.4664191076048,814.7791573031866,814.0115685514679,813.1705045387185,812.2624481827124,811.2935292985901,810.2695397257654,809.1959479295178,808.0779130909702,806.9202986991457,805.7276856587159,804.504384926933,803.254449693074,801.9816871135295,800.6896696154502,799.3817457816288,798.0610508290415,796.7305166932101,795.3928817302785,794.0507000484226,792.7063504799391,791.3620452050812,790.019838038437,788.6816323883767,787.3491888998255,786.0241327903593,784.7079608893614,783.402048389732,782.1076553213923,780.8259327555917,779.5579287487924,778.304594034682,777.0667874726462,775.8452812608188,774.6407659216248,773.4538550675275,772.2850899544956,771.1349438305211,770.0038260863276,768.8920862152324,767.8000175889482,766.7278610559367,765.6758083687623,764.6440054467207,763.632555479863,762.6415218803738,761.6709310871009,760.7207752288867,759.7910146521945,758.8815803183744,757.9923760757674,757.1232808116958,756.2741504892504]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_44.png', dpi=300)
