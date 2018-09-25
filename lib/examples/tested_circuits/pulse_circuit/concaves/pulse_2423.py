import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [199.12098477987544,6860.050846403098,13209.166938342407,19253.805194142624,25004.245856984202,30471.455808351282,35666.486013907066,40600.237752790854,45283.35669161008,49726.18196825334,53938.722286966855,57930.64648869559,61711.28234710711,65289.62021746554,68674.31960332487,71873.71747640944,74895.83762194875,77748.40054126114,80438.83360367954,82974.2812422505,85361.61505484564,87607.44371754219,89718.12264819119,91699.76337980361,93558.24261878763,95299.21097412384,96928.10135156628,98450.13701277993,99870.33930356865,101193.53505842669,102424.36369086147,103567.2839804941,104626.58056900327,105606.3701776528,106510.6075595172,107343.0911996592,108107.4687764686,108807.24239718076,109445.77362028832,110026.28827716433,110551.88110475148,111025.52020065431,111450.05131141677,111828.20196418407,112162.5854513497,112455.70467718056,112709.95587480564,112927.63220135118,113110.92721841393,113261.9382644886,113382.66972540975,113475.03620833479,113540.86562428699,113581.90218379752,113599.80930973348,113596.17247097827,113572.50194023961,113530.23547890133,113470.74095150594,113395.31887215702,113305.20488486163,113201.57217959312,113085.53384564277,112958.14516364259,112820.40583748066,112673.26216719291,112517.60916379915,112354.29260695535,112184.11104621661,112007.81774664433,111826.12257944555,111639.69385829942,111449.16012200553,111255.11186407809,111058.10320990888,110858.6535421269,110657.2490747962,110454.34437710894,110250.36384725351,110045.70313716054,109840.73052885647,109635.78826318182,109431.1938216595,109227.24116232661,109024.20191037068,108822.32650443817,108621.84529950753,108422.96962724358,108225.89281477021,108030.79116281847,107837.82488422356]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_2423.png', dpi=300)
