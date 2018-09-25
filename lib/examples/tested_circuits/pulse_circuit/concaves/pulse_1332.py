import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [400.0831167604083,448.7586862805092,494.7950529736282,538.1187062488069,578.7539619706776,616.7678961551419,652.2490334019406,685.2970451048184,716.0170985766158,744.5165000899678,770.902594352866,795.2814063322967,817.7567473255244,838.4296243370503,857.3978544774425,874.7558216739288,890.594334208895,905.0005548018269,918.0579834523757,929.8464789071194,940.4423084603056,949.9182184825793,958.3435199817552,965.78418488364,972.3029497396188,977.9594243281969,982.810203192493,986.9089785949873,990.3066537098532,993.0514551371235,995.1890440299292,996.7626252894598,997.8130544120131,998.3789416759595,998.4967534392637,998.200910384688,997.5238826032707,996.4962814497142,995.1469481379548,993.5030390730321,991.5901079376792,989.4321845698686,987.0518506816566,984.4703124807586,981.7074702648847,978.7819850654112,975.7113424218282,972.511913371862,969.1990127444934,965.7869548444656,962.2891066174653,958.7179383851242,955.0850722384218,951.4013281770871,947.6767680812678,943.9207376001432,940.1419060403341,936.3483043349923,932.547361172336,928.7459373602046,924.9503585009359,921.1664460485645,917.3995468180151,913.6545610136287,909.9359688420369,906.2478557720972,902.5939365023246,898.9775776940185,895.4018195260832,891.8693961253954,888.3827549244709,884.9440749961366,881.555284412925,878.2180766769753,874.9339262643514,871.7041033258674,868.529687584758,865.4115814698254,862.3505225210578,859.3470951031255,856.4017414606344,853.5147721475413,850.6863758617164,847.9166287142733,845.2055029619692,842.5528752297155,839.9585342490224,837.422188137032,834.9434712396754,832.5219505614089,830.1571318029511]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1332.png', dpi=300)
