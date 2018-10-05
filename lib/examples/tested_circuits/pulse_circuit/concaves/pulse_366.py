import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy = [228.19294289854744,1676.5635257497615,2870.563885913995,3848.982467002907,4644.914298307953,5286.32754338372,5796.8495502242495,6196.452993345133,6502.039707839277,6727.934458687422,6886.301530109125,6987.495653375337,7040.357180570251,7052.4599207203855,7030.31875848849,6979.563074057847,6905.081054274125,6811.13920337624,6701.480702858261,6579.4057158518135,6447.83627018653,6309.367979959156,6166.310575080769,6020.718998711392,5874.4166953957065,5729.012634172184,5585.91356711013,5446.332984925946,5311.298165609532,5181.656591595079,5058.082820014685,4941.086630048806,4831.022960550228,4728.103823288182,4632.412071554253,4543.916655923456,4462.488832354069,4387.918710535568,4319.931535428663,4258.203164392804,4202.374313094884,4152.063272603252,4106.876928629631,4066.420028399248,4030.3027335481347,3998.1465661300276,3969.588900116666,3944.286176155812,3921.9160255856063,3902.178487134868,3884.7964880647573,3869.5157450896086,3856.104221502089,3844.3512573173475,3834.066470223961,3825.0785074816904,3817.2337130792343,3810.394760645461,3804.4392908062955,3799.258581795639,3794.75627399625,3790.8471625073266,3787.456066602343,3784.5167808517067,3781.9711095525126,3779.7679837635173,3777.8626585395136,3776.2159867684322,3774.7937652291375,3773.566148018242,3772.5071222664374,3771.594041018606,3770.8072082388503,3770.129511082982,3769.5460948265295,3769.04407612244,3768.6122905711477,3768.2410709030814,3767.9220523898393,3767.6480024079915,3767.412671373798,3767.2106625447227,3767.0373184426194,3766.8886218928333,3766.7611098930547,3766.65179872586,3766.5581189102422,3766.4778587509663,3766.4091153914533,3766.350252407288,3766.2998630947936]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(0.0,900.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_366.png', dpi=300)
