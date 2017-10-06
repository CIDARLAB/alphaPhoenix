/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.utils;

import hyness.stl.AlwaysNode;
import hyness.stl.ConjunctionNode;
import hyness.stl.DisjunctionNode;
import hyness.stl.LinearPredicateLeaf;
import hyness.stl.RelOperation;
import hyness.stl.TreeNode;
import java.util.concurrent.ThreadLocalRandom;
import org.json.JSONArray;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author prash
 */
public class SerializerTest {

    public SerializerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of stlToJSON method, of class Serializer.
     */
    
    @Test
    public void testgetlong(){
        System.out.println(ThreadLocalRandom.current().nextLong());
    }
    
    //@Test
    public void testStlToJSON() {

        AlwaysNode a1 = new AlwaysNode(new LinearPredicateLeaf(RelOperation.LE, "x", 6.0), 0.0, 7.0);
        AlwaysNode a2 = new AlwaysNode(new LinearPredicateLeaf(RelOperation.GT, "x", 0.0), 0.0, 1.0);
        AlwaysNode a3 = new AlwaysNode(new LinearPredicateLeaf(RelOperation.GT, "x", 4.0), 1.0, 5.0);
        AlwaysNode a4 = new AlwaysNode(new LinearPredicateLeaf(RelOperation.GT, "x", 3.0), 5.0, 6.0);
        AlwaysNode a5 = new AlwaysNode(new LinearPredicateLeaf(RelOperation.GT, "x", 0.0), 6.0, 7.0);

        ConjunctionNode c1 = new ConjunctionNode(a1, a2);
        ConjunctionNode c2 = new ConjunctionNode(a3, a4);
        ConjunctionNode c3 = new ConjunctionNode(c1, c2);
        ConjunctionNode c4 = new ConjunctionNode(c3, a5);

        AlwaysNode a6 = new AlwaysNode(new LinearPredicateLeaf(RelOperation.LE, "x", 2.0), 0.0, 7.0);
        AlwaysNode a7 = new AlwaysNode(new LinearPredicateLeaf(RelOperation.GT, "x", 0.0), 0.0, 1.0);
        AlwaysNode a8 = new AlwaysNode(new LinearPredicateLeaf(RelOperation.GT, "x", 1.0), 1.0, 5.0);
        AlwaysNode a9 = new AlwaysNode(new LinearPredicateLeaf(RelOperation.GT, "x", 0.0), 5.0, 7.0);

        ConjunctionNode c5 = new ConjunctionNode(a6, a7);
        ConjunctionNode c6 = new ConjunctionNode(a8, a9);
        ConjunctionNode c7 = new ConjunctionNode(c5, c6);

        DisjunctionNode stl = new DisjunctionNode(c4, c7);
        JSONArray arr = Serializer.stlToJSON(stl);
        System.out.println(arr.toString(1));

    }

}
