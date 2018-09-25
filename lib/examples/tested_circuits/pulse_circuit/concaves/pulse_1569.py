import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()
sx = [600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0,910.0,920.0,930.0,940.0,950.0,960.0,970.0,980.0,990.0,1000.0,1010.0,1020.0,1030.0,1040.0,1050.0,1060.0,1070.0,1080.0,1090.0,1100.0,1110.0,1120.0,1130.0,1140.0,1150.0,1160.0,1170.0,1180.0,1190.0,1200.0,1210.0,1220.0,1230.0,1240.0,1250.0,1260.0,1270.0,1280.0,1290.0,1300.0,1310.0,1320.0,1330.0,1340.0,1350.0,1360.0,1370.0,1380.0,1390.0,1400.0,1410.0,1420.0,1430.0,1440.0,1450.0,1460.0,1470.0,1480.0,1490.0,1500.0]
sy = [668.3963041221473,670.4668162628891,672.4300053857888,674.2850365310358,676.0322887357936,677.6730170077589,679.2091159888225,680.642949597253,681.9772251770931,683.2148986601991,684.359101932166,685.4130864668948,686.3801791200426,687.2637471674199,688.0671704789759,688.7938192732385,689.4470362868659,690.0301224732774,690.5463255478163,690.9988308474018,691.3907540854678,691.7251356686415,692.0049363073771,692.2330337037758,692.4122201397756,692.545200820464,692.6345928524157,692.6829247571438,692.6926364360675,692.6660795166866,692.6055180205232,692.5131293023525,692.3910052176686,692.2411534815132,692.0654991869679,691.8658864559758,691.6440801988296,691.4017679617999,691.1405618450432,690.8620004752158,690.5675510191924,690.258611226984,689.9365114934336,689.6025169295498,689.257829435465,688.903589767993,688.5408795966317,688.1707235426244,687.7940911963733,687.4118991091113,687.0250127552702,686.6342484624734,686.2403753065123,685.8441169690531,685.4461535561691,685.0471233761144,684.6476246750321,684.248217329548,683.8494244954356,683.4517342117423,683.0556009599583,682.661447177978,682.26966472876,681.8806163237272,681.494636901074,681.1120349592603,680.7330938460697,680.3580730037017,679.9872091704444,679.6207175395444,679.2587928759559,678.9016105917009,678.5493277806222,678.2020842133508,677.8600032933429,677.523192974874,677.1917466438947,676.8657439626802,676.5452516792134,676.2303244022569,675.9210053430736,675.6173270247612,675.3193119601654,675.0269732993365,674.7403154474878,674.4593346544086,674.1840195762768,673.9143518108034,673.6503064066311,673.3918523478956,673.1389530148417]
plt.plot(sx,sy,color='black',linestyle='solid')
plt.xlabel("time")
plt.ylabel("out0")
plt.xlim(600.0,1500.0)
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/concaves/pulse_1569.png', dpi=300)
