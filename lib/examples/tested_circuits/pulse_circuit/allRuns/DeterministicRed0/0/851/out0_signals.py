import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [234.56966085010865,238.91986312829357,242.58319374280464,245.66798409914986,248.26549911441526,250.45262280431155,252.29412177111172,253.84455283178417,255.14987066879198,256.2487826292364,257.17389039074317,257.9526519849996,258.6081924135073,259.1599866576782,259.6244351479286,260.01534860510816,260.3443565106295,260.62125122159875,260.8542778587432,261.0503785028919,261.21539789369274,261.3542566929545,261.4710974214106,261.5694073739392,261.65212214081913,261.72171279163837,261.7802592972673,261.8295123597794,261.87094547845476,261.905798792017,261.93511599458463,261.95977541834185,261.98051620364276,261.99796033210464,262.0126311759423,262.02496911376056,262.0353446762196,262.04406961186214,262.0514062017945,262.05757510002655,262.06276193256735,262.06712285156505,262.0707892097425,262.07387149439,262.07646263800586,262.07864080420757,262.0804717320569,262.0820107086896,262.0833042291164,262.08439139274697,262.08530507835115,262.08607293256875,262.0867182015207,262.0872604303966,262.08771605195443,262.0880988815509,262.0884205335313,262.08869077145675,262.0889178026694,262.08910852603225,262.08926874027776,262.08940331922145,262.08951635910387,262.0896113024893,262.0896910424466,262.08975801014645,262.08981424851225,262.089861474141,262.08990112936164,262.08993442599854,262.08996238216093,262.08998585316823,262.0900055575445,262.0900220988673,262.0900359841316,262.0900476391837,262.090057421692,262.0900656320469,262.09007252252104,262.0900783049662,262.0900831572805,262.0900872288426,262.0900906450772,262.09009351129066,262.0900959158933,262.09009793310645,262.0900996252362,262.09010104458326,262.09010223504754,262.09010323347525,262.0901040707907]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/851/out0.png', dpi=300)
