import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [378.91389377064297,8177.965632818932,14708.71431725281,20161.023178339718,24698.630877217398,28460.071720206957,31562.09928067558,34103.011246736685,36165.58954673286,37819.641246428204,39124.17675857349,40129.269089515336,40877.633897637534,41405.963537771706,41746.04185018323,41925.66139145577,41969.36163991965,41899.00556530069,41734.21259040012,41492.66780725196,41190.32950765041,40841.55874104706,40459.1949594898,40054.60042380073,39637.692934395156,39216.982021421354,38799.61864794122,38391.46347587886,37997.17443174804,37620.31107539698,37263.45125240217,36928.31461542348,36615.88760811947,36326.54513531376,36060.16512062445,35816.23325126062,35593.93626716121,35392.24306933584,35209.97365520243,35045.85642759576,34898.57478051828,34766.80408052784,34649.240250729374,34544.621170072,34451.74204569672,34369.46582362546,34296.729590308736,34232.547797022984,34176.013019522434,34126.294852329775,34082.63743389023,34044.35600712848,34010.83284018099,33981.51276390444,33955.89852539371,33933.54610913449,33914.060138416055,33897.0894380825,33882.32281450298,33869.48508878203,33858.33340380566,33848.653813935634,33840.25815734182,33832.98120452112,33826.67807200603,33821.22188721138,33816.50168847307,33812.42054332534,33808.89386772606,33805.847929095034,33803.2185165403,33800.94976240242,33798.993100160194,33797.30634474886,33795.85288239366,33794.60095812017,33793.52305014228,33792.59532132898,33791.79713890106,33791.11065439849,33790.52043678657,33790.01315233036,33789.577285565385,33789.202896318755,33788.88140832356,33788.605425474525,33788.368572239546,33788.16535515899,33787.991042734066,33787.84156133496,33787.713405051625]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/1262/out0.png', dpi=300)
