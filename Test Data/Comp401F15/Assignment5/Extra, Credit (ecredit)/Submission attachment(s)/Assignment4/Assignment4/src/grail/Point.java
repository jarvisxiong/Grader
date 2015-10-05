package grail;
import util.annotations.WebDocuments;
import util.annotations.Explanation;
//@StructurePattern(StructurePatternNames.POINT_PATTERN)
//@Explanation("Location in Java coordinate System.")
//@WebDocuments({"Lectures/Graphics.pptx", "Lectures/Graphics.pdf", "Videos/Graphics.avi"})
public interface Point {
    public int getX(); 
    public int getY();  
    public double getAngle(); 
    public double getRadius(); 
}