import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [682.0251584058062,683.8536048722798,685.5736417990843,687.1892315352909,688.7043156363943,690.1227976277287,691.4485290932167,692.6852984837905,693.836822163503,694.9067373064775,695.898596331761,696.8158626211248,697.661907310711,698.4400069840045,699.1533421229797,699.8049961980307,700.3979552966397,700.9351082065655,701.4192468823767,701.8530672349412,702.2391701924594,702.5800629891239,702.8781606437831,703.1357875962833,703.3551794736528,703.5384849620953,703.6877677640075,703.8050086220086,703.8921073943485,703.9508851681152,703.983086398423,703.9903810633008,703.9743668253266,703.9365711922135,703.8784536695636,703.8014078998935,703.7067637828087,703.5957895718939,703.4696939444847,703.3296280410212,703.1766874711607,703.0119142842376,702.836298902041,702.650782012206,702.4562564208159,702.2535688630727,702.0435217711359,701.8268749984348,701.6043474999553,701.3766189681689,701.1443314244248,700.9080907657619,700.6684682672219,700.4260020398499,700.1811984446705,699.9345334630116,699.6864540236281,699.4373792871432,699.1877018883879,698.9377891372715,698.6879841788652,698.4386071134168,698.1899560770532,697.9423082839585,697.6959210308347,697.451032664481,697.2078635133372,696.9666167838566,696.7274794225785,696.4906229447809,696.2562042305965,696.02436628948,695.7952389939074,695.5689397831937,695.3455743383045,695.1252372285337,694.9080125309116,694.6939744231961,694.4831877512926,694.2757085719337,694.0715846714389,693.8708560613595,693.6735554518021,693.4797087032043,693.2893352573277,693.1024485482101,692.9190563938072,692.7391613690352,692.5627611609088,692.3898489064533,692.2204135140493]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_154.png', dpi=300)
