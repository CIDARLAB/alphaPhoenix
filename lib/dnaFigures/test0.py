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

# Colour map
col_map = {}
col_map['black']   = (0.00, 0.00, 0.00)
col_map['white']   = (1.00, 1.00, 1.00)
col_map['red']     = (0.95, 0.30, 0.25)
col_map['green']   = (0.38, 0.82, 0.32)
col_map['blue']    = (0.38, 0.65, 0.87)
col_map['orange']  = (1.00, 0.75, 0.17)

# Global line width
lw = 0.8

# How much to lighten OFF components
off_fac = 0.7

# Component definitions
part0 = {'type':'Promoter', 'fwd':True, 'opts':{'linewidth':lw, 'color':col_map['black'], 'edge_color':col_map['black']}}
part1 = {'type':'RBS', 'fwd':True, 'opts':{'linewidth':lw, 'color':col_map['black'], 'edge_color':col_map['black']}}
part2 = {'type':'CDS', 'fwd':True, 'opts':{'linewidth':lw, 'color':col_map['black'], 'edge_color':col_map['black'],'x_extent':24}}
part3 = {'type':'Terminator', 'fwd':True, 'opts':{'linewidth':lw, 'color':col_map['black'], 'edge_color':col_map['black']}}
part4 = {'type':'Promoter', 'fwd':True, 'opts':{'linewidth':lw, 'color':col_map['black'], 'edge_color':col_map['black']}}
part5 = {'type':'RBS', 'fwd':True, 'opts':{'linewidth':lw, 'color':col_map['black'], 'edge_color':col_map['black']}}
part6 = {'type':'CDS', 'fwd':True, 'opts':{'linewidth':lw, 'color':col_map['black'], 'edge_color':col_map['black'],'x_extent':24}}
part7 = {'type':'Terminator', 'fwd':True, 'opts':{'linewidth':lw, 'color':col_map['black'], 'edge_color':col_map['black']}}
design = [part0,part1,part2,part3,part4,part5,part6,part7]
# Arc definitions
reg0 = {'type': 'Repression','from_part':part6, 'to_part':part4, 'opts':{'color':col_map['red'], 'linewidth':lw, 'arc_height':20.0}}
reg1 = {'type': 'Activation','from_part':part6, 'to_part':part0, 'opts':{'color':col_map['red'], 'linewidth':lw, 'arc_height':25.0}}
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

fig.savefig('test0.png', dpi=300)
plt.close('all')