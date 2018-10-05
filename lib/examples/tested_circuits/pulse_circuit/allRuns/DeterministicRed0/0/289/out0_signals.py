import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [226.65793011164607,226.6274482962017,226.4584012937849,226.12066183315375,225.60943205197322,224.92704095151873,224.0789366522386,223.072600054004,221.91732713182046,220.62427653124627,219.206545908199,217.67916649932195,216.05896377370945,214.3642684638305,212.61448961015472,210.82958208140133,209.02945479151697,207.23337165213132,205.45939522738652,203.72391440280538,202.04128442847698,200.42359312930625,198.88055334202008,197.4195105141494,196.04554679974774,194.76165902751012,193.56898713249117,192.46707124656115,191.45411876536627,190.52726656385187,189.68282750295964,188.91651403832577,188.22363486918888,187.59926305614576,187.03837589152127,186.5359681003171,186.08714074081246,185.68716862471467,185.3315492178475,185.0160359374967,184.73665858632793,184.48973340971241,184.2718649727363,184.0799417528881,183.9111270529858,183.76284656786123,183.63277369402581,183.51881345669895,183.41908574341255,183.33190837654521,183.25578042630394,183.18936605809355,183.131479120988,183.08106861428033,183.03720511415966,182.99906820000362,182.96593488742664,182.93716905120496,182.91221180389755,182.89057278403757,182.87182230004086,182.85558427152495,182.84152990778315,182.82937206308543,182.81886020978004,182.80977597244487,182.80192916927078,182.79515431020522,182.78930750495388,182.78426373759063,182.7799144681528,182.7761655251275,182.7729352561095,182.77015290709824,182.76775720388082,182.7656951117058,182.76392075199664,182.76239445715368,182.76108194662376,182.75995360932973,182.7589838792394,182.7581506924214,182.75743501530997,182.7568204351327,182.75629280455203,182.75583993354377,182.75545132239907,182.75511793049716,182.75483197616958,182.75458676356854,182.75437653297288]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/289/out0.png', dpi=300)
