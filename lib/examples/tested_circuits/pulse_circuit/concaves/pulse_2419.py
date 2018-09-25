import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [199.12098477987544,201.95853659667353,204.62210586448336,207.1074777561004,209.4185567088348,211.5618527402824,213.54491986740408,215.37570858429297,217.06224628998987,218.61246381824878,220.03409639262335,221.3346261092099,222.52124913565893,223.6008583235411,224.58003575814428,225.46505186148127,226.26186887375616,226.97614727101111,227.61325413780523,228.17827281375506,228.6760133334348,229.11102331661692,229.48759906197947,229.80979666583292,230.08144303685586,230.30614671400014,230.48730842147364,230.62813131465865,230.73163088580208,230.80064450961592,230.83784061747625,230.84572749538424,230.82666170574686,230.78285613671892,230.71638768559893,230.62920458479857,230.52313338037146,230.3998855741147,230.26106394094236,230.1081685336483,229.9426023873881,229.76567693625702,229.57861715426634,229.3825664328476,229.1785912067701,228.96768534005767,228.7507742831518,228.5287190122022,228.30231976098028,228.07231955551532,227.83940756115186,227.6042222513251,227.36735440695293,227.12934995495138,226.89071265399514,226.6519066352726,226.41335880562028,226.1754611200721,225.93857273051995,225.70302201685735,225.46910850666518,225.23710468920018,225.00725772916044,224.77979108542877,224.5549060397347,224.33278313992656,224.11358356230886,223.89745039727484,223.6845098622493,223.47487244575396,223.26863398621234,223.06587668892928,222.86667008450337,222.67107193176622,222.4791290681841,222.2908782105081,222.10634670831726,221.92555325296397,221.74850854430355,221.57521591746803,221.40567193182943,221.23986692418794,221.07778552811692,220.91940716129844,220.76470648258825,220.6136538204618,220.46621557440722,220.32235459075218,220.18203051433406,220.04520011735195,219.91181760666933]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_2419.png', dpi=300)
