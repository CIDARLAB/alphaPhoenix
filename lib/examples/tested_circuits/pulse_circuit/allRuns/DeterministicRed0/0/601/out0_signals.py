import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [234.58778464900956,234.6136142327425,234.6352767837726,234.65337726402998,234.66844403784444,234.68093629445542,234.6912513164558,234.6997314059206,234.7066703548776,234.71231939946435,234.71689263380983,234.720571884806,234.7235110656811,234.7258400369669,234.72766800975955,234.7290865293565,234.7301720783328,234.73098833758,234.73158814227608,234.73201516755515,234.73230537607262,234.7324882569128,234.73258788249805,234.7326238074285,234.732611830576,234.73256463931207,234.7324923524927,234.73240297676145,234.73230278887064,234.73219665504848,234.73208829695278,234.73198051243642,234.7318753581903,234.73177430031583,234.7316783379939,234.73158810465065,234.73150395035344,234.73142600859993,234.7313542501708,234.73128852629318,234.731228603002,234.73117418828213,234.73112495331105,234.73108054890164,234.73104061805896,234.7310048054062,234.7309727641043,234.73094416077805,234.73091867886896,234.7308960207582,234.73087590893851,234.73085808646044,234.73084231683444,234.73082838353423,234.73081608921655,234.73080525474825,234.73079571811186,234.73078733324442,234.7307799688511,234.73077350722565,234.73076784309976,234.73076288253785,234.7307585418887,234.73075474680033,234.730751431302,234.73074853695502,234.73074601207145,234.73074381099923,234.7307418934713,234.73074022401474,234.73073877141695,234.73073750824454,234.73073641041117,234.73073545679026,234.73073462886882,234.73073391043866,234.7307332873217,234.73073274712561,234.73073227902754,234.73073187358222,234.7307315225525,234.73073121875973,234.7307309559518,234.73073072868695,234.73073053223163,234.7307303624706,234.73073021582834,234.73073008919968,234.73072997988953,234.7307298855598,234.73072980418308]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/601/out0.png', dpi=300)
