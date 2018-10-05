import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [234.22818983610765,234.18412424024058,234.04669136074224,233.81466053950254,233.48521551125646,233.05382113961923,232.5143745681746,231.85940262748818,231.08030998188593,230.16769265060006,229.11173132934587,227.9026748155399,226.53141617664303,224.99015298795916,223.27310837765555,221.37727303966156,219.3031122064786,217.05516919250837,214.64249231888576,212.078818033388,209.382461301049,206.57589359711702,203.68502484707045,200.73824197091605,197.7652862538441,194.79606891365486,191.85952623802137,188.9826032949946,186.18943223178596,183.50074290048016,180.9335153902961,178.50086021221125,176.21209479174038,174.07297520853484,172.0860390384482,170.251017180096,168.56527791172186,167.0242734914122,165.62196709677912,164.3512249196249,163.20416428142352,162.17245350488943,161.24756295573354,160.42096926805633,159.68431645639552,159.02953858321672,158.44894906944995,157.93530176216444,157.48182863520233,157.08225859601575,156.73082138098184,156.42223999710882,156.1517146482261,155.91490059340748,155.70788193851286,155.52714296546128,155.36953826225363,155.232262624772,155.1128214585927,155.0090022093797,154.9188471889078,154.84062803513865,154.77282194401616,154.71408973315417,154.6632557392459,154.61928950824282,154.5812892070515,154.54846666508325,154.520133941293,154.49569130558135,154.47461652114563,154.4564553153885,154.44081293038562,154.42734664894212,154.41575919835927,154.4057929407426,154.39722476568042,154.38986160815625,154.38353652145483,154.37810524144683,154.37344318491563,154.36944283046267,154.36601143597161,154.36306905161584,154.3605467919624,154.3583853348724,154.35653361864206,154.35494771219544,154.35358983615384,154.35242751529694,154.3514328453242]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/120/out0.png', dpi=300)
