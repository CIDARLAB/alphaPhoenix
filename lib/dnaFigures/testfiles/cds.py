#!/usr/bin/env python

import dnaplotlib as dpl
from pylab import *
import matplotlib.pyplot as plt
from matplotlib import gridspec


fig = plt.figure(figsize=(0.2,0.2))
gs = gridspec.GridSpec(1, 1)

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

cdsTemplate = {'type':'CDS', 'fwd':True, 'opts':{'linewidth':lw, 'color':col_map['black'], 'edge_color':col_map['black'],'x_extent':24}} 

design1 = [cdsTemplate]
ax_dna1 = plt.subplot(gs[0])

# Create the DNAplotlib renderer
dr = dpl.DNARenderer()

# Redender the DNA to axis
start, end = dr.renderDNA(ax_dna1, design1, dr.SBOL_part_renderers())
ax_dna1.set_xlim([start, end])
ax_dna1.set_ylim([-15,15])
ax_dna1.set_aspect('equal')
ax_dna1.axis('off')



fig.savefig('cds.png', dpi=300)
plt.close('all')
