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
part0 = {'type':'CDS', 'fwd':True, 'opts':{'linewidth':lw, 'color':(0.95, 0.30, 0.25), 'edge_color':(0.00, 0.00, 0.00),'x_extent':20}}
design = [part0]
# Arc definitions
fig = plt.figure(figsize=(0.2,0.2));
ax_dna = plt.subplot(gs[0])

# Create the DNAplotlib renderer
dr = dpl.DNARenderer()

start, end = dr.renderDNA(ax_dna, design, dr.SBOL_part_renderers())
ax_dna.set_xlim([start, end])
ax_dna.set_ylim([-15.0,15.0])
ax_dna.set_aspect('equal')
ax_dna.axis('off')

fig.savefig('/home/prash/cidar/phoenix/alpha/lib/dnaFigures/plots/lBtNmKzR.png', dpi=300)
plt.close('all')