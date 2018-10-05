import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [232.50899787382718,277.15308661263816,314.3424103139926,345.0204123346111,370.0915736276962,390.38404693670094,406.63716812189665,419.50059892584636,429.53894561311705,437.23880446072536,443.0166671650841,447.22687169396903,450.1691830815915,452.09580599601213,453.2177506526174,453.71053928145903,453.7192754069222,453.36311584113054,452.7391930763933,451.92603805787945,450.9865526515472,449.97057892156016,448.9171094847812,447.85617986929276,446.81048066723883,445.7967239593865,444.82679535200316,443.90871993824663,443.0474676067542,442.2456203854576,441.5039219417835,440.8217269718855,440.19736600889723,439.6284391672727,439.1120505171021,438.64499314522556,438.2238935021407,437.84532234566615,437.5058784627265,437.2022503668884,436.93126031820384,436.6898942801918,436.4753208033882,436.2849012933861,436.11619367202456,435.9669510626382,435.8351168143234,435.7188169172467,435.61635064336747,435.5261800677163,435.44691897860054,435.37732156560287,435.31627117755215,435.2627693649354,435.2159253592403,435.1749460926999,435.1391268235294,435.10784240203986,435.0805391903483,435.05672763142496,435.03597545078213,435.01790146530135,435.0021699677394,434.98848565172904,434.97658904008574,434.9662523785417,434.95727595731057,434.94948482388924,434.94272585200594,434.9368651334677,434.9317856617214,434.9273852780793,434.9235748537885,434.92027668332844,434.9174230663743,434.91495505794177,434.91282136813925,434.91097739475515,434.909384373584,434.9080086329406,434.9068209402346,434.9057959297726,434.9049116021372,434.9041488865595,434.9034912586672,434.9029244068578,434.90243594132494,434.90201514046316,434.9016527299974,434.90134069073787,434.9010720913545]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/556/out0.png', dpi=300)
