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
part0 = {'type':'Promoter', 'fwd':True, 'opts':{'linewidth':lw, 'label':'p1', 'label_y_offset':-14, 'label_size':4,  'color':(0.95, 0.30, 0.25), 'edge_color':(0.00, 0.00, 0.00)}}
part1 = {'type':'RBS', 'fwd':True, 'opts':{'linewidth':lw, 'label':'r1', 'label_y_offset':-14, 'label_size':4,  'color':(0.0,0.0,0.0), 'edge_color':(0.00, 0.00, 0.00)}}
part2 = {'type':'CDS', 'fwd':True, 'opts':{'linewidth':lw, 'label':'c1', 'label_y_offset':-14, 'label_size':4,  'color':(0.38, 0.82, 0.32), 'edge_color':(0.00, 0.00, 0.00),'x_extent':20}}
part3 = {'type':'Terminator', 'fwd':True, 'opts':{'linewidth':lw, 'label':'t1', 'label_y_offset':-14, 'label_size':4,  'color':(0.0,0.0,0.0), 'edge_color':(0.00, 0.00, 0.00)}}
part4 = {'type':'Promoter', 'fwd':True, 'opts':{'linewidth':lw, 'label':'p2', 'label_y_offset':-14, 'label_size':4,  'color':(0.95, 0.30, 0.25), 'edge_color':(0.00, 0.00, 0.00)}}
part5 = {'type':'RBS', 'fwd':True, 'opts':{'linewidth':lw, 'label':'r2', 'label_y_offset':-14, 'label_size':4,  'color':(0.0,0.0,0.0), 'edge_color':(0.00, 0.00, 0.00)}}
part6 = {'type':'CDS', 'fwd':True, 'opts':{'linewidth':lw, 'label':'c21', 'label_y_offset':-14, 'label_size':4,  'color':(0.95, 0.30, 0.25), 'edge_color':(0.00, 0.00, 0.00),'x_extent':20}}
part7 = {'type':'Terminator', 'fwd':True, 'opts':{'linewidth':lw, 'label':'t2', 'label_y_offset':-14, 'label_size':4,  'color':(0.0,0.0,0.0), 'edge_color':(0.00, 0.00, 0.00)}}
design = [part0,part1,part2,part3,part4,part5,part6,part7]
# Arc definitions
reg0 = {'type': 'Activation','from_part':part6, 'to_part':part4, 'opts':{'color':(0.0,0.0,0.0), 'linewidth':lw, 'arc_height':20.0}}
reg1 = {'type': 'Repression','from_part':part6, 'to_part':part0, 'opts':{'color':(0.0,0.0,0.0), 'linewidth':lw, 'arc_height':25.0}}
regDesign = [reg0,reg1]

fig = plt.figure(figsize=(1.6,0.6000000000000001));
ax_dna = plt.subplot(gs[0])

# Create the DNAplotlib renderer
dr = dpl.DNARenderer()

start, end = dr.renderDNA(ax_dna, design, dr.SBOL_part_renderers(), 
	                      regs=regDesign, reg_renderers=dr.std_reg_renderers())
ax_dna.set_xlim([start, end])
ax_dna.set_ylim([-25.0,25.0])
ax_dna.set_aspect('equal')
ax_dna.axis('off')

fig.savefig('/home/prash/cidar/phoenix/alpha/lib/dnaFigures/scripts/testFigure.png', dpi=300)
plt.close('all')