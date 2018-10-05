import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [339.15405275876367,4096.468423556041,7096.412981629963,9435.520936775727,11208.835264063237,12500.754410679407,13385.481947901966,13928.282058432202,14186.669440095116,14211.380559368752,14047.127588576803,13733.180967133936,13303.834770432799,12788.801480790553,12213.569696553031,11599.745670963035,10965.389699228714,10325.351470856918,9691.604000454354,9073.573042699973,8478.457542069278,7911.536330005856,7376.456670801973,6875.501106529914,6409.830124932655,5979.69929153469,5584.650515357027,5223.677983276142,4895.369969562066,4598.028204465433,4329.7667882732085,4088.5927935796253,3872.4707394280626,3679.3730753522045,3507.3187061701337,3354.4014402307125,3218.8100710701488,3098.8416178720254,2992.909063459358,2899.544746980181,2817.400397080605,2745.244633559308,2681.9586232163156,2626.530449653935,2578.0486472584757,2535.695255337295,2498.7386692151326,2466.526498689178,2438.478589704686,2414.080320822536,2392.8762505416703,2374.464163484763,2358.4895416365375,2344.6404701591964,2332.6429748551614,2322.2567792937853,2313.271463279548,2305.5030001399573,2298.7906477760935,2292.9941671575166,2287.991341639653,2283.6757708814634,2279.9549140419526,2276.748358176799,2273.986289217254,2271.6081444976835,2269.561427433926,2267.800666589122,2266.286502958435,2264.9848908326117,2263.8663990448763,2262.9056007556374,2262.080541179392,2261.372273806574,2260.764456721221,2260.2430015666214,2259.7957685702013,2259.4123018115024,2259.0835996091973,2258.801915521011,2258.5605860004334,2258.3538812423585,2258.176876182115,2258.025338994333,2257.895634774875,2257.7846423854317,2257.689682700782,2257.6084567270723,2257.538992259504,2257.479597922767,2257.428823590344]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/949/out0.png', dpi=300)
