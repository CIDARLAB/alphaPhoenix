import matplotlib
matplotlib.use('agg',warn=False, force=True)
from matplotlib import pyplot as plt
from matplotlib import patches as patches

fig = plt.figure()

sx0 = [0.0,10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0,200.0,210.0,220.0,230.0,240.0,250.0,260.0,270.0,280.0,290.0,300.0,310.0,320.0,330.0,340.0,350.0,360.0,370.0,380.0,390.0,400.0,410.0,420.0,430.0,440.0,450.0,460.0,470.0,480.0,490.0,500.0,510.0,520.0,530.0,540.0,550.0,560.0,570.0,580.0,590.0,600.0,610.0,620.0,630.0,640.0,650.0,660.0,670.0,680.0,690.0,700.0,710.0,720.0,730.0,740.0,750.0,760.0,770.0,780.0,790.0,800.0,810.0,820.0,830.0,840.0,850.0,860.0,870.0,880.0,890.0,900.0]
sy0 = [221.83504816568626,1581.3453955675536,2722.53496740435,3677.690043026106,4474.933829805028,5138.514171126974,5689.220070720866,6144.813111105298,6520.431884801162,6828.955274250503,7081.321863309732,7286.807559441524,7453.26537366741,7587.331827227151,7694.60435334457,7779.793689492778,7846.854785559562,7899.099275012332,7939.292112620126,7969.734591651949,7992.335620097413,8008.672856324955,8020.045072981379,8027.516927703264,8031.957157592519,8034.071084062236,8034.428200167554,8033.485515907725,8031.607252737082,8029.081404606328,8026.133617548411,8022.938781881851,8019.630679616014,8016.309983911249,8013.050866912706,8009.906436463386,8006.9131906829425,8004.0946517785715,8001.464316359829,7999.028038605897,7996.785944546007,7994.733960145587,7992.865022547599,7991.170032429051,7989.638595748286,7988.259594954905,7987.021622809548,7985.913306136001,7984.923541944975,7984.041664288699,7983.2575568070915,7982.561723105058,7981.945324765865,7981.4001948799905,7980.918833386115,7980.4943892244355,7980.1206332444235,7979.791924949048,7979.503175461203,7979.249808537047,7979.027721001107,7978.833243619399,7978.663103142703,7978.514386028758,7978.384504177936,7978.271162882561,7978.172331087612,7978.086213983679,7978.011227896557,7977.9459773974595,7977.88923452962,7977.8399200300855,7977.797086413906,7977.75990278376,7977.727641227554,7977.69966466914,7977.675416042245,7977.6544086641015,7977.636217692617,7977.62047255886,7977.6068502747385,7977.595069523872,7977.584885451554,7977.576085077324,7977.568483260852,7977.561919158621,7977.556253115181,7977.551363938531,7977.547146514558,7977.5435097202935,7977.5403746001875]
plt.plot(sx0,sy0,color='#000000',linestyle='solid')


plt.xlabel("time")
plt.ylabel("MEFL")
plt.xlim(0.0,900.0)
plt.ylim(0.0,1000000.0)
plt.yscale('symlog')
fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/1054/out0.png', dpi=300)
