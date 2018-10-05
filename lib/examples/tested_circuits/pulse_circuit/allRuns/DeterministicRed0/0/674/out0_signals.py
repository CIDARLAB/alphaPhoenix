import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [343.7241048112919,345.9175731929016,347.74538647292417,349.2522796575192,350.48049699267557,351.4690023733156,352.25311911434306,352.8644411808572,353.3309145348842,353.67702237090197,353.92403038304616,354.0902627331059,354.19138906562034,354.2407094645227,354.24942876105956,354.22691476287423,354.1809372091163,354.1178858450232,354.04296713607954,353.96037993179834,353.8734709298518,353.7848711456612,353.6966148072589,353.6102422056246,353.52688806405587,353.44735696775035,353.37218733330803,353.30170531030114,353.236069903466,353.1753104921176,353.11935780881834,353.0680693263262,353.02124989335437,352.97866835763995,352.9400708204479,352.9051910805349,352.8737587479484,352.8455054387072,352.82016940005104,352.7974988620679,352.77725436452766,352.7592102670614,352.7431556157902,352.7288945095159,352.7162460830538,352.7050442036657,352.69513695833336,352.6863859943497,352.67866576297234,352.6718627053353,352.6658744111072,352.6606087732534,352.6559831564459,352.65192359196794,352.6483640081834,352.64524550264844,352.64251565955686,352.6401279144087,352.63804096634965,352.63621823758143,352.63462737846373,352.63323981637427,352.63203034602105,352.6309767586685,352.6300595076175,352.629261407242,352.62856736291025,352.6279641291925,352.62744009386046,352.62698508531605,352.62659020122624,352.62624765629306,352.6259506472411,352.62569323325545,352.62547023025274,352.62527711750926,352.6251099553055,352.6249653123737,352.6248402020529,352.62473202616644,352.6246385257374,352.6245577377505,352.6244879572531,352.62442770416476,352.624375694235,352.6243308136504,352.62429209684944,352.6242587071543,352.62422991987313,352.62420510756806,352.6241837272191]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/674/out0.png', dpi=300)
