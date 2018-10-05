import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [234.22818983610765,238.37768404757696,241.87203515230104,244.81467650649464,247.29271208745578,249.37949437583657,251.13679530947937,252.616634497657,253.86281882881752,254.91223902857863,255.79596153418268,256.54014799721017,257.1668296256749,257.69455928111046,258.1389606288124,258.51319059298834,258.82832880305176,259.09370555676236,259.31717800643946,259.50536274221787,259.66383165593925,259.7972768825945,259.9096497010973,260.0042775054987,260.0839623087426,260.15106369450496,260.2075686723857,260.2551505041076,260.29521824195365,260.32895844578337,260.3573703134711,260.381295264662,260.4014418535653,260.41840674825147,260.43269239748986,260.4447219081147,260.45485157333997,260.4633814229088,260.4705641074077,260.47661237976365,260.4817053954175,260.48599401769485,260.4896052854138,260.49264617513256,260.495206769258,260.4973629238774,260.4991785153375,260.5007073320687,260.5019946676701,260.50307866242787,260.5039914329895,260.504760023646,260.5054072073909,260.5059521604777,260.5064110304523,260.5067974144799,260.5071227621346,260.50739671457836,260.5076273901747,260.5078216249966,260.50798517535105,260.5081228883188,260.5082388453603,260.508336483242,260.50841869586327,260.50848792000164,260.5085462075153,260.50859528614177,260.50863661069457,260.50867140617254,260.5087007040613,260.50872537290024,260.5087461440219,260.5087636332257,260.5087783590282,260.50879075803033,260.5088011978577,260.5088099880569,260.5088173892702,260.5088236209613,260.50882886792084,260.50883328574355,260.5088370054411,260.5088401373256,260.50884277427986,260.508844994511,260.50884686386917,260.5088484377998,260.50884976298755,260.5088508787401,260.50885181815426]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/67/out0.png', dpi=300)
