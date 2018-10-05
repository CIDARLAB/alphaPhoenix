import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [308.35249040660136,5701.561446390644,10242.848202450796,14066.275614563157,17284.89088188483,19994.004358239832,22273.961090314744,24192.481964766586,25806.64037144042,27164.53059300341,28306.675672051642,29267.215235148942,30074.907534221027,30753.974671936707,31324.815484765662,31804.606751762145,32207.81017800288,32546.599881072812,32831.22281062324,33070.30259318341,33271.09565436539,33439.70708668805,33581.27257006937,33700.11166327984,33799.8569546936,33883.56285925908,33953.79725659085,34012.71866539621,34062.141227700915,34103.58942038363,34138.344111119455,34167.481322319036,34191.90485272956,34212.37372587049,34229.525282205366,34243.89460350286,34255.930849504155,34266.01099565148,34274.45138359711,34281.51743126594,34287.43179449843,34292.38122616058,34296.52233972532,34299.986451569974,34302.88364863668,34305.30620485722,34307.331450167716,34309.02417945455,34310.43867489519,34311.620403475856,34312.60744163566,34313.43167071302,34314.119779908906,34314.69410762435,34315.173347104246,34315.57313817564,34315.90656338457,34316.1845639075,34316.41628816056,34316.60938392789,34316.77024313228,34316.90420688545,34317.01573723298,34317.108560977584,34317.185790097974,34317.250022553155,34317.30342665103,34317.347811647516,34317.38468681189,34317.41531083263,34317.44073313516,34317.46182842842,34317.47932558369,34317.493831770465,34317.50585262381,34317.51580909193,34317.52405150723,34317.530871335635,34317.53651098493,34317.541171990706,34317.54502184662,34317.54819970192,34317.550821112934,34317.552982004396,34317.55476197125,34317.55622702977,34317.5574319093,34317.558421960704,34317.559234745066,34317.559901355904,34317.560447519114]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/1183/out0.png', dpi=300)
