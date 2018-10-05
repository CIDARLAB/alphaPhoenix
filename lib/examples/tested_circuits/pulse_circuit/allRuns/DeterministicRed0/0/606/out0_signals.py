import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [234.58778464900956,1856.2477319687207,3221.9119819727616,4371.976494313466,5340.465236416955,6156.034897067914,6842.821245203352,7421.15208179452,7908.147801923623,8318.227278266,8663.533985579317,8954.294936269507,9199.123016895719,9405.271647055459,9578.849276403293,9725.000051255465,9848.055984471472,9951.665121715312,10038.89948879065,10112.346009303677,10174.18307735325,10226.245047773058,10270.076550076948,10306.978231104547,10338.045278484624,10364.199863819686,10386.218464890193,10404.754874884515,10420.35957922,10433.496073172235,10444.554603107323,10453.863737947897,10461.700113350234,10468.296637033984,10473.849398192448,10478.523485576887,10482.457886560514,10485.76961229369,10488.557171157903,10490.903493435824,10492.878393868255,10494.540645085921,10495.939723380558,10497.117278574853,10498.10837157763,10498.94251632769,10499.64455703315,10500.235406731723,10500.732669086578,10501.151161870794,10501.503357678208,10501.799754943804,10502.049190289617,10502.259101471524,10502.43574873661,10502.584401165523,10502.709493539725,10502.814758391614,10502.903337160615,10502.977873762748,10503.040593354064,10503.093368629903,10503.137775631632,10503.175140720767,10503.206580117847,10503.233033182521,10503.25529042516,10503.274017083682,10503.28977296748,10503.303029159184,10503.314182071646,10503.323565278779,10503.331459472627,10503.33810084336,10503.343688131852,10503.348388565028,10503.352342850909,10503.355669382252,10503.358467774107,10503.36082184083,10503.362802101303,10503.364467887111,10503.365869116584,10503.367047787619,10503.36803923388,10503.368873181824,10503.36957464016,10503.370164648251,10503.37066090586,10503.37107830302,10503.37142936587]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/606/out0.png', dpi=300)
