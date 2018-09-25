import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [633.9804202411561,755.4595694156465,871.3614385115606,981.8611895323872,1087.1413624020984,1187.3879820525813,1282.7878962102636,1373.5269162551656,1459.7885015691866,1541.7528228181966,1619.5960959258157,1693.4901133105523,1763.6019212424292,1830.0936068778637,1893.1221684910292,1952.8394493245264,2009.3921203681869,2062.9217008959695,2113.564608170255,2161.4522296402024,2206.7110124050064,2249.462565813765,2289.823773921885,2327.9069151839494,2363.819787281092,2397.665835390876,2429.5442825344835,2459.550260898201,2487.7749432377705,2514.30567364587,2539.2260971029114,2562.6162873459093,2584.552872684276,2605.1091594689697,2624.355252985501,2642.358175594394,2659.181981986708,2674.8878714588036,2689.5342971409123,2703.1770721393177,2715.869472572941,2727.662337502515,2738.604165764948,2748.7412097373713,2758.1175660651493,2766.775263396139,2774.7543471699914,2782.09296151651,2788.827428321265,2794.9923235199108,2800.620550685139,2805.743411972056,2810.3906764890553,2814.590646162092,2818.370219160703,2821.754950954218,2824.769113066446,2827.435749596705,2829.776731574463,2831.8128092141087,2833.5636621354415,2835.0479476144865,2836.2833469281104,2837.286609854754,2838.0735973923433,2838.659322753161,2839.05799069414,2839.283035239679,2839.347155852731,2839.262352108507,2839.0399569237848,2838.690668393386,2838.224580284038,2837.651211234422,2836.9795327088755,2836.217995750828,2835.374556580736,2834.4567010819455,2833.4714682166004,2832.4254724124494,2831.324924960119,2830.1756544591913,2828.9831263502037,2827.752461568489,2826.4884543546164,2825.1955892550295,2823.878057345375,2822.53977170791,2821.184382193308,2819.8152894961495,2818.4356585723435]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_2077.png', dpi=300)
