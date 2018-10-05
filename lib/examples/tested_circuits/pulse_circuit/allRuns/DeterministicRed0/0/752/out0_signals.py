import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [567.2580884956493,565.2148550092434,556.7344752562229,541.4026042264852,520.083001295927,494.01643059071256,464.5600764813121,433.040360103736,400.6549107869253,368.4134318627362,337.1125798388907,307.33766478296377,279.4823927088046,253.7783149652478,230.32743279586222,209.13357710178144,190.1301140651769,173.20295928705514,158.2088054512945,144.98898176958704,133.37959272168555,123.21863724985559,114.35076694070146,106.63025483581403,99.92264641389795,94.10546776020891,89.06828081393678,84.71230448751025,80.9497632312866,77.70307980994653,74.90399472721808,72.4926689182674,70.41680724155233,68.63082642452125,67.09508120529459,65.77515547331446,64.64122047161437,63.66745898746825,62.83155247675802,62.1142269041815,61.49885248728102,60.97109232165424,60.51859492409569,60.13072592913554,59.798334488176515,59.513550275745125,59.26960738315124,59.06069175404287,58.881809175937796,58.7286711790619,58.597596504611566,58.485426087233336,58.389449751051444,58.307343045980694,58.237112852962404,58.17705056511485,58.125691808695215,58.081781805339425,58.04424559732187,58.01216246248491,57.984743936807895,57.96131494194232,57.94129758389946,57.924197248752954,57.9095906728783,57.897115709929054,57.88646255535476,57.87736622260002,57.86960009388632,57.86297039328134,57.85731145113673,57.852481647389475,57.84835993707668,57.84484287505925,57.841842068688834,57.83928199724821,57.837098145674055,57.83523540753001,57.83364671860578,57.83229188802204,57.8311365984464,57.830151551082544,57.82931173457696,57.828595799975275,57.827985526403005,57.827465364394634,57.827022045618186,57.826644249391336,57.8263223177616,57.82604801210924,57.82581430524708]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/752/out0.png', dpi=300)
