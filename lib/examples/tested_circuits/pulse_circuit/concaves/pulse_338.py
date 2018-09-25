import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [201.27339577895944,201.94677906710658,202.58640761863975,203.19385803642024,203.7706378275248,204.3181881912279,204.83788671159274,205.33104995639505,205.7989359842471,206.24274676190748,206.66363049385558,207.0626838662846,207.44095420772157,207.79944156852306,208.13910072152072,208.46084308610466,208.76553857803512,209.0540173872671,209.32707168605717,209.58545726960136,209.82989513142556,210.06107297571666,210.27964666874735,210.48624163150646,210.68145417560356,210.86585278447185,211.0399793418445,211.20435030943213,211.35945785567824,211.50577093741828,211.64373633621736,211.77377965111006,211.89630624941336,212.0117021772336,212.12033503123598,212.22255479319645,212.31869462880348,212.40907165213102,212.49398765715293,212.57372981762404,212.64857135660515,212.71877218686464,212.78457952334472,212.84622846883767,212.9039425739747,212.95793437258973,213.0084058934803,213.05554914954917,213.099546605273,213.140571623408,213.17878889180702,213.21435483118907,213.24741798466883,213.27811938982202,213.30659293403204,213.33296569383276,213.35735825893494,213.37988504159466,213.40065457195672,213.4197697799794,213.43732826452285,213.4534225501588,213.46814033223689,213.48156471072105,213.4937744132867,213.50484400815128,213.51484410708863,213.52384155906068,213.5318996348806,213.53907820330443,213.54543389893175,213.55102028227952,213.5558879923774,213.56008489221873,213.5636562073868,213.56664465816215,213.5690905854035,213.57103207048291,213.57250504954294,213.57354342233225,213.57417915586544,213.57444238314142,213.5743614971451,213.57396324034704,213.5732727899064,213.57231383877382,213.57110867288148,213.56967824460048,213.5680422426367,213.56621915852912,213.5642263499081]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_338.png', dpi=300)
