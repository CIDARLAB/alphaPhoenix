/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cidarlab.phoenix.adaptors.spring;

import org.springframework.stereotype.Controller;
/**
 *
 * @author prash
 */
@Controller
public class MainController {
    
    /*@RequestMapping(value="/test", method=RequestMethod.POST)
    public void test(HttpServletRequest request, HttpServletResponse response){
        String val = request.getParameter("bar");
        String resp = val + " from foo!";
        try {
            PrintWriter writer = response.getWriter();
            writer.print(resp);
            writer.flush();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }*/
}
