import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [226.65793011164607,562.8664125948965,826.8837574702017,1030.6344539913914,1184.4514915815594,1297.057843508046,1375.7927591065366,1426.8218196825567,1455.3189309287065,1465.6215709602734,1461.3624774885031,1445.5810151187907,1420.8171485455011,1389.1905782770214,1352.4672541314771,1312.1151848625743,1269.3512063561093,1225.1801468929687,1180.4276276716218,1135.7675549717105,1091.7451954371916,1048.796578955215,1007.2648464394993,967.4140538469281,929.4408585004135,893.4844470491091,859.6350121282089,827.9410421119242,798.4156507204244,771.0421370689594,745.7789302499249,722.5640358199192,701.3190662649655,681.9529060697089,664.3650367600055,648.4485297377228,634.0927051920035,621.1854529835315,609.6152144447559,599.2726304386256,590.0518687681962,581.851651514578,574.5760090154325,568.1347914213876,562.44397095636,557.425768328811,553.0086355655268,549.1271251928948,545.7216726903135,542.7383157505392,540.1283703760279,537.8480804612644,535.85825436708,534.1238991685931,532.6138607901474,531.3004761341009,530.1592415504157,529.1685005528138,528.3091525301437,527.5643832913998,526.9194175828395,526.3612931912994,525.8786558678511,525.461574042741,525.1013721322995,524.7904811409957,524.5223052201868,524.2911028455088,524.0918813060113,523.9203032510294,523.7726041082782,523.6455192631659,523.5362199705954,523.4422570533259,523.3615115229122,523.2921513386552,523.2325935956744,523.1814715044383,523.1376055903828,523.0999786034421,523.0677136833768,523.0400553778217,523.0163531561853,522.9960471041878,522.9786555211898,522.9637641758927,522.9510170057702,522.9401080720555,522.9307746055621,522.9227909993496,522.9159636225789]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/309/out0.png', dpi=300)
