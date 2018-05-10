/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.core.assignment;

import hyness.stl.TreeNode;
import java.util.List;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.library.Library;

/**
 *
 * @author prash
 */
public abstract class AbstractAssignment {
    
    abstract public void solve(List<Module> modules, Library library, TreeNode stl, int runCount, double confidence, double threshold);
}
