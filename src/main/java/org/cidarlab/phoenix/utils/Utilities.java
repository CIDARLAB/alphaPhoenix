package org.cidarlab.phoenix.utils;

import com.opencsv.CSVReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cidarlab.gridtli.dom.Point;
import org.cidarlab.gridtli.dom.Signal;
import org.cidarlab.gridtli.dom.TLIException;
import org.json.JSONObject;

/**
 *
 * @author prash
 */
public class Utilities {
    
    
    public static void printDebugStatement(String message){
        System.out.println("#########################################");
        System.out.println("######################" + message);
        System.out.println("#########################################");
    }
    
    //<editor-fold desc="OS Check">
    public static boolean isSolaris() {
        String os = System.getProperty("os.name");
        return isSolaris(os);
    }

    public static boolean isSolaris(String os) {
        return os.toLowerCase().contains("sunos");
    }

    public static boolean isWindows() {
        String os = System.getProperty("os.name");
        return isWindows(os);
    }

    public static boolean isWindows(String os) {
        return os.toLowerCase().contains("win");
    }

    public static boolean isLinux() {
        String os = System.getProperty("os.name");
        return isLinux(os);
    }

    public static boolean isLinux(String os) {
        return (os.toLowerCase().contains("nix")) || (os.contains("nux")) || (os.indexOf("aix") > 0);
    }
    
    public static boolean isMac() {
        String os = System.getProperty("os.name");
        return isMac(os);
    }

    public static boolean isMac(String os) {
        return os.toLowerCase().contains("mac");
    }
    //</editor-fold>  
    
    //<editor-fold desc="File and Directory checks">
    public static boolean makeDirectory(String filepath){
        File file = new File(filepath);
        return file.mkdir();
    }
    
    public static boolean validFilepath(String filepath){
        File file = new File(filepath);
        return file.exists();
    }
    
    public static boolean isDirectory(String filepath){
        File file = new File(filepath);
        return file.isDirectory();
    }
    
    
    public static String getFilepath() {
        
        try {
            String path = Utilities.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            String _filepath = URLDecoder.decode(path, "UTF-8");
            if(_filepath.endsWith(".jar")){
                String sep = "" + Utilities.getSeparater();
                if(Utilities.isWindows()){
                    _filepath = new File(_filepath).getPath();
                }
                _filepath = _filepath.substring(0, _filepath.lastIndexOf(sep));
                _filepath += Utilities.getSeparater();
                return _filepath;
            } else {
                if (Utilities.isWindows()) {
                    try {
                        _filepath = URLDecoder.decode(_filepath, "utf-8");
                        _filepath = new File(_filepath).getPath();
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                char sep = getSeparater();
                if (_filepath.contains(sep + "target" + sep)) {
                    _filepath = _filepath.substring(0, _filepath.lastIndexOf(sep + "target" + sep));
                } else if (_filepath.contains(sep + "src" + sep)) {
                    _filepath = _filepath.substring(0, _filepath.lastIndexOf(sep + "src" + sep));
                } else if (_filepath.contains(sep + "build" + sep + "classes" + sep)) {
                    _filepath = _filepath.substring(0, _filepath.lastIndexOf(sep + "build" + sep + "classes" + sep));
                }
                return _filepath;
            }
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static String getResourcesFilepath(){
        String filepath = getFilepath();
        filepath += getSeparater() + "src" + getSeparater() + "main" + getSeparater() + "resources" + getSeparater();
        return filepath;
    }
    
    public static String getResultsFilepath(){
        String fp = (getFilepath() + "results" + getSeparater());
        if(!validFilepath(fp)){
            makeDirectory(fp);
            System.out.println("Created a Results folder at : " + fp);
        }
        
        JSONObject obj = new JSONObject();
        if(!validFilepath(fp + "users.json")){
            Utilities.writeToFile(fp + "users.json", obj.toString());
        }
        return fp;
    }
    
    
    public static String getLibFilepath(){
        String fp = getFilepath() + getSeparater() + "lib" + getSeparater() ;
        if(!validFilepath(fp)){
            makeDirectory(fp);
            System.out.println("Created a Lib folder at : " + fp);
        }
        return fp;
    }
    
    
    public static String getDnaFiguresFilepath(){
        String fp = getLibFilepath() + "dnaFigures" + getSeparater();
        if(!validFilepath(fp)){
            makeDirectory(fp);
            System.out.println("Created a DNA Figures folder at : " + fp);
        }
        return fp;
    }
    
    public static String getDnaFiguresScriptsFilepath(){
        String fp = getDnaFiguresFilepath() + "scripts" + getSeparater();
        if(!validFilepath(fp)){
            makeDirectory(fp);
            System.out.println("Created a DNA Figures Scripts folder at : " + fp);
        }
        return fp;
        
    }
    
    public static String getDnaFiguresPlotsFilepath(){
        String fp = getDnaFiguresFilepath() + "plots" + getSeparater();
        if(!validFilepath(fp)){
            makeDirectory(fp);
            System.out.println("Created a DNA Figures Plots folder at : " + fp);
        }
        return fp;
    }
    
    public static String getTestedCircuitsFilepath(){
        String fp = getLibFilepath() + "examples" + Utilities.getSeparater() + "tested_circuits" + Utilities.getSeparater();
        if(!validFilepath(fp)){
            makeDirectory(fp);
            System.out.println("Created a Lib folder at : " + fp);
        }
        return fp;
    }
    
    
    //</editor-fold>
    
    public static void writeSignalsToCSV(List<Signal> signals, String filepath){
        List<String> lines = new ArrayList<String>();
        String line0 = "";
        String line1 = "";
        int lim = signals.get(0).getPoints().size() - 1;
        for(int i=0;i<lim;i++){
            Point p = signals.get(0).getPoints().get(i);
            line0 += (p.getX() + ",");
            line1 += (p.getY() + ",");
        }
        line0 += signals.get(0).getPoints().get(lim).getX();
        line1 += signals.get(0).getPoints().get(lim).getY();
        
        lines.add(line0);
        lines.add(line1);
        for(int i=1;i<signals.size();i++){
            String line = "";
            for(int j=0;j<lim;j++){
                Point p = signals.get(i).getPoints().get(j);
                line += (p.getY() + ",");
            }
            line += signals.get(i).getPoints().get(lim).getY();
            lines.add(line);
        }
        writeToFile(filepath, lines);
    }
    
    public static List<Signal> readSignalsFromCSV(String filepath) throws TLIException{
        List<String[]> lines = getCSVFileContentAsList(filepath);
        List<Signal> signals = new ArrayList<>();
        File f = new File(filepath);
        String sig = f.getName();
        sig = sig.substring(0, sig.lastIndexOf(".csv"));
        for(int i=1;i<lines.size();i++){
            List<Point> points = new ArrayList<>();
            for(int j=0;j<lines.get(0).length;j++){
                points.add(new Point(Double.valueOf(lines.get(0)[j]),"t",Double.valueOf(lines.get(i)[j]),sig));   
            }
            signals.add(new Signal(points));
        }
        return signals;
    }
    
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static SecureRandom rnd = new SecureRandom();

    public static String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }


    
    
    
    //<editor-fold desc="File content">
    
    public static String getFileContentAsString(String filepath){
        String filecontent = "";
        
        File file = new File(filepath);
        try { 
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while((line=reader.readLine()) != null){
                filecontent += (line+"\n");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, "File at " + filepath + " not found.");
        } catch (IOException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filecontent;
    }
    
    public static List<String> getFileContentAsStringList(String filepath){
        List<String> filecontent = null;
        File file = new File(filepath);
        try { 
            BufferedReader reader = new BufferedReader(new FileReader(file));
            filecontent = new ArrayList<>();
            String line;
            while((line=reader.readLine()) != null){
                filecontent.add(line);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, "File at " + filepath + " not found.");
        } catch (IOException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filecontent;
    }
    
    
    public static List<String[]> getCSVFileContentAsList(String filepath){
        List<String[]> listPieces = new ArrayList<>();
        try {
            CSVReader reader = new CSVReader(new FileReader(filepath));
            String[] nextline;
            while( (nextline = reader.readNext()) != null ){
                listPieces.add(nextline);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listPieces;
    }
    
    public static void writeToFile(String filepath, String content){
        File file = new File(filepath);
        try {
            FileWriter fr = new FileWriter(file);
            try (BufferedWriter br = new BufferedWriter(fr)) {
                br.write(content);
                br.flush();
            }
        } catch (IOException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void writeToFile(String filepath, List<String> lines){
        File file = new File(filepath);
        try {
            FileWriter fr = new FileWriter(file);
            try (BufferedWriter br = new BufferedWriter(fr)) {
                for(String line:lines){
                    br.write(line);
                    br.newLine();
                }
                br.flush();
            }
            fr.close();
        } catch (IOException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static char getSeparater(){
        if(Utilities.isWindows()){
            return '\\';
        }
        return '/';
    }

    public static int getRandom(int min, int max) {
        if (min == max) {
            return min;
        }
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        return randomNum;
    }
}