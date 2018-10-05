import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy = [327.40838656527797,358.74626206961074,385.10109421182204,407.2371223228575,425.80600810002886,441.36256909689126,454.37849364021815,465.2542500358972,474.3293951316437,481.8914736258888,488.1836831196095,493.411462683628,497.74814575206943,501.3398020740189,504.3093786173567,506.7602356343808,508.77916205774295,510.4389434182029,511.8005458448697,512.9149711972664,513.8248309042391,514.5656795444229,515.1671434974226,515.6538750308794,516.0463578801506,516.3615866460348,516.613639111018,516.8141577924372,516.9727546551533,517.0973508466448,517.1944615495162,517.2694345312544,517.3266496744233,517.3696856624128,517.4014590502134,517.424340143727,517.4402494251035,517.4507376783671,517.4570524743987,517.4601932543513,517.4609568948124,517.4599753370011,517.4577466078728,517.4546603462192,517.451018765725,517.4470538343753,517.4429413212274,517.438812253664,517.4347622376558,517.4308590175197,517.4271485884127,517.4236601207069,517.4204099118193,517.4174045434178,517.414643391066,517.4121206075869,517.4098266799793,517.407749641916,517.4058760090833,517.4041914923947,517.402681533996,517.4013317026385,517.4001279781141,517.39905694879,517.398105941634,517.3972631003198,517.396517423883,517.3958587758669,517.3952778718317,517.3947662514236,517.3943162398506,517.3939209025169,517.3935739956954,517.3932699154141,517.3930036461805,517.3927707107223,517.3925671215802,517.3923893351191,517.3922342083129,517.3920989585005,517.3919811261926,517.3918785409144,517.3917892900117,517.3917116902983,517.3916442623942,517.391585707586,517.3915348870308,517.3914908031213,517.3914525828336,517.3914194628825,517.391390776517]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(0.0,900.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_155.png', dpi=300)
