/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.core.assignment;

import hyness.stl.TreeNode;
import java.util.List;
import java.util.Map;
import org.cidarlab.phoenix.dom.CandidateComponent;
import org.cidarlab.phoenix.dom.Component;
import org.cidarlab.phoenix.dom.Module;
import org.cidarlab.phoenix.dom.library.Library;
import org.cidarlab.phoenix.utils.Args;

/**
 *
 * @author prash
 */
public abstract class AbstractAssignment {
    
    abstract public void solve(List<Module> modules, Library library, TreeNode stl, Args args);

    
}
