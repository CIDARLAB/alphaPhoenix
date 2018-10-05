import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy = [232.4917257931941,235.78763616072638,238.20855980471111,239.87595201970362,240.89877437845635,241.36701256916598,241.35391347128888,240.91863997797324,240.10880187810213,238.96279474444853,237.51195406877807,235.78253722998124,233.7975327905728,231.57827863520782,229.14585384534882,226.52219854978452,223.73091497042574,220.7977136031565,217.75049061879807,214.61905297471776,211.43454080299782,208.22862567777122,205.03258239298174,201.8763372821233,198.78758754750964,195.79106624936333,192.90800122802295,190.15578849706455,187.5478759568846,185.09383447158189,182.79958152227812,180.66771745240683,178.69793449561473,176.88746268758146,175.23152280731125,173.72376334885467,172.35666524130482,171.1219040252066,170.01066416467654,169.01390404660444,168.1225730469252,167.32778395934997,166.62094524567667,165.99385814378755,165.4387838116641,164.9484855240706,164.51625057987724,164.1358961053311,163.8017624169529,163.50869707195105,163.25203223051565,163.0275574830809,162.8314898777802,162.66044251879444,162.5113927950232,162.3816510375304,162.26883018899804,162.17081689372074,162.0857442770787,162.0119665737002,161.94803567881186,161.89267963322106,161.84478300514976,161.80336909845724,161.76758389385645,161.7366816152152,161.71001180501247,161.68700778992306,161.66717641805306,161.65008895253774,161.6353730112327,161.622705448455,161.61180608168056,161.6024321734073,161.5943735857803,161.58744853284284,161.58149986228784,161.57639180523964,161.57200713884055,161.56824471221177,161.5650172916974,161.5622496861786,161.559877117682,161.5578438065141,161.55610174376326,161.5546096272449,161.55333193986064,161.55223815188387,161.5513020310244,161.55050104610092,161.54981585194662]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(0.0,900.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_819.png', dpi=300)
