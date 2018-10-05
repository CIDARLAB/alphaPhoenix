import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [343.7241048112919,4245.271447655005,7506.845832622379,10215.08195310976,12448.867285467948,14278.258256265584,15764.66914423178,16961.530489414337,17915.091875299415,18665.231730407442,19246.2175057122,19687.396095258253,20013.81105324135,20246.75043977243,20404.231823930408,20501.431465231235,20551.064204957824,20563.71976075645,20548.16024924784,20511.583004389788,20459.852159322803,20397.702011249516,20328.91486465188,20256.475813614517,20182.706746817275,20109.381717532186,20037.82568568756,19968.99851835845,19903.56600454122,19841.95950759535,19784.42574309029,19731.0680326456,19681.88024845294,19636.774531078583,19595.60373708465,19558.179454700537,19524.286316411588,19493.69323759409,19466.162120461722,19441.454482495286,19419.336397836036,19399.582078278356,19381.97636682589,19366.31637053942,19352.412419845896,19340.088507848486,19329.182334760895,19319.545058717402,19311.04083426879,19303.546203309736,19296.949389499692,19291.14953600076,19286.055917179263,19281.5871474719,19277.67040461589,19274.240679640898,19271.240062210596,19268.61706690685,19266.326003721275,19264.32639423293,19262.582433602893,19261.06249752097,19259.7386925244,19258.58644761569,19257.58414478747,19256.712785878248,19255.955693102005,19255.29824059101,19254.727614345094,19254.232598075087,19253.803382550675,19253.43139620339,19253.1091548861,19252.830128845537,19252.588625119497,19252.379683718213,19252.19898610354,19252.042774608824,19251.90778157734,19251.79116711752,19251.69046448531,19251.603532207417,19251.52851215347,19251.463792851086,19251.407977415907,19251.359855538918,19251.318379036817,19251.2826405281,19251.251854848488,19251.22534286473,19251.202517386533]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/685/out0.png', dpi=300)
