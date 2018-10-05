#!/usr/bin/env python
import dnaplotlib as dpl
from pylab import *
import matplotlib.pyplot as plt
from matplotlib import gridspec

gs = gridspec.GridSpec(1, 1)

# Function to generate a ligher colour
def lighten_color (col, fac):
	r = col[0] + (fac*(1.0-col[0]))
	g = col[1] + (fac*(1.0-col[1]))
	b = col[2] + (fac*(1.0-col[2]))
	return (r,g,b)

# Global line width
lw = 0.8

# How much to lighten OFF components
off_fac = 0.7

# Component definitions
part0 = {'type':'Promoter', 'fwd':True, 'opts':{'linewidth':lw, 'label':'pLacIq018', 'label_y_offset':-14, 'label_size':2,  'color':(0.95, 0.30, 0.25), 'edge_color':(0.00, 0.00, 0.00)}}
part1 = {'type':'RBS', 'fwd':True, 'opts':{'linewidth':lw, 'label':'RBS30', 'label_y_offset':-20, 'label_size':2,  'color':(0.00, 0.00, 0.00), 'edge_color':(0.00, 0.00, 0.00)}}
part2 = {'type':'CDS', 'fwd':True, 'opts':{'linewidth':lw, 'label':'RhlR', 'label_y_offset':-14, 'label_size':2,  'color':(0.38, 0.82, 0.32), 'edge_color':(0.00, 0.00, 0.00),'x_extent':20}}
part3 = {'type':'Terminator', 'fwd':True, 'opts':{'linewidth':lw, 'label':'Ter1', 'label_y_offset':-20, 'label_size':2,  'color':(0.00, 0.00, 0.00), 'edge_color':(0.00, 0.00, 0.00)}}
part4 = {'type':'Promoter', 'fwd':True, 'opts':{'linewidth':lw, 'label':'pRhl046', 'label_y_offset':-14, 'label_size':2,  'color':(0.38, 0.82, 0.32), 'edge_color':(0.00, 0.00, 0.00)}}
part5 = {'type':'RBS', 'fwd':True, 'opts':{'linewidth':lw, 'label':'RBS31', 'label_y_offset':-20, 'label_size':2,  'color':(0.00, 0.00, 0.00), 'edge_color':(0.00, 0.00, 0.00)}}
part6 = {'type':'CDS', 'fwd':True, 'opts':{'linewidth':lw, 'label':'PhlF', 'label_y_offset':-14, 'label_size':2,  'color':(0.38, 0.65, 0.87), 'edge_color':(0.00, 0.00, 0.00),'x_extent':20}}
part7 = {'type':'Terminator', 'fwd':True, 'opts':{'linewidth':lw, 'label':'Ter1', 'label_y_offset':-20, 'label_size':2,  'color':(0.00, 0.00, 0.00), 'edge_color':(0.00, 0.00, 0.00)}}
part8 = {'type':'Promoter', 'fwd':True, 'opts':{'linewidth':lw, 'label':'pPhl081', 'label_y_offset':-14, 'label_size':2,  'color':(0.38, 0.65, 0.87), 'edge_color':(0.00, 0.00, 0.00)}}
part9 = {'type':'RBS', 'fwd':True, 'opts':{'linewidth':lw, 'label':'RBS30', 'label_y_offset':-20, 'label_size':2,  'color':(0.00, 0.00, 0.00), 'edge_color':(0.00, 0.00, 0.00)}}
part10 = {'type':'CDS', 'fwd':True, 'opts':{'linewidth':lw, 'label':'LuxR', 'label_y_offset':-14, 'label_size':2,  'color':(1.00, 0.75, 0.17), 'edge_color':(0.00, 0.00, 0.00),'x_extent':20}}
part11 = {'type':'Terminator', 'fwd':True, 'opts':{'linewidth':lw, 'label':'Ter1', 'label_y_offset':-20, 'label_size':2,  'color':(0.00, 0.00, 0.00), 'edge_color':(0.00, 0.00, 0.00)}}
part12 = {'type':'Promoter', 'fwd':True, 'opts':{'linewidth':lw, 'label':'pLux050', 'label_y_offset':-14, 'label_size':2,  'color':(1.00, 0.75, 0.17), 'edge_color':(0.00, 0.00, 0.00)}}
part13 = {'type':'RBS', 'fwd':True, 'opts':{'linewidth':lw, 'label':'RBS30', 'label_y_offset':-20, 'label_size':2,  'color':(0.00, 0.00, 0.00), 'edge_color':(0.00, 0.00, 0.00)}}
part14 = {'type':'CDS', 'fwd':True, 'opts':{'linewidth':lw, 'label':'GFP', 'label_y_offset':-14, 'label_size':2,  'color':(1.00, 1.00, 1.00), 'edge_color':(0.00, 0.00, 0.00),'x_extent':20}}
part15 = {'type':'Terminator', 'fwd':True, 'opts':{'linewidth':lw, 'label':'Ter1', 'label_y_offset':-20, 'label_size':2,  'color':(0.00, 0.00, 0.00), 'edge_color':(0.00, 0.00, 0.00)}}
design = [part0,part1,part2,part3,part4,part5,part6,part7,part8,part9,part10,part11,part12,part13,part14,part15]
# Arc definitions
reg0 = {'type': 'Activation','from_part':part2, 'to_part':part4, 'opts':{'color':(0.0,0.0,0.0), 'linewidth':lw, 'arc_height':20.0}}
reg1 = {'type': 'Repression','from_part':part6, 'to_part':part8, 'opts':{'color':(0.0,0.0,0.0), 'linewidth':lw, 'arc_height':25.0}}
reg2 = {'type': 'Activation','from_part':part10, 'to_part':part12, 'opts':{'color':(0.0,0.0,0.0), 'linewidth':lw, 'arc_height':30.0}}
regDesign = [reg0,reg1,reg2]

fig = plt.figure(figsize=(3.2,0.9000000000000001));
ax_dna = plt.subplot(gs[0])

# Create the DNAplotlib renderer
dr = dpl.DNARenderer()

start, end = dr.renderDNA(ax_dna, design, dr.SBOL_part_renderers(), 
	                      regs=regDesign, reg_renderers=dr.std_reg_renderers())
ax_dna.set_xlim([start, end])
ax_dna.set_ylim([-35.0,35.0])
ax_dna.set_aspect('equal')
ax_dna.axis('off')

fig.savefig('/home/prash/cidar/phoenix/alpha/lib/examples/tested_circuits/pulse_circuit/allRuns/DeterministicRed0/0/185/circuit.png', dpi=1200)
plt.close('all')