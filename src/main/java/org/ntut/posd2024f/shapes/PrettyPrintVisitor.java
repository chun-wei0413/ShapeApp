package org.ntut.posd2024f.shapes;

import java.util.Iterator;

public class PrettyPrintVisitor implements Visitor<String> {
    private StringBuilder sb;
    private int indentLevel;

    public PrettyPrintVisitor(){
        this.sb = new StringBuilder();
        this.indentLevel = 0;
    }

    @Override
    public void visitCircle(Circle circle) {
        sb.append("Circle ").append(circle.getRadius());
    }

    @Override
    public void visitRectangle(Rectangle rectangle) {
        sb.append("Rectangle ").append(rectangle.getLength()).append(" ").append(rectangle.getWidth());
    }

    @Override
    public void visitTriangle(Triangle triangle) {
        sb.append("Triangle ");
        int vectorSize = triangle.getVectors().size();
        for(int i = 0 ; i < vectorSize; i++){
            sb.append(triangle.getVectors().get(i).toString());
            if(i != (vectorSize - 1)){
                sb.append(" ");
            }
        }
    }

    @Override
    public void visitConvexPolygon(ConvexPolygon convexPolygon) {
        sb.append("ConvexPolygon ");

        int vectorSize = convexPolygon.getVectors().size();
        for(int i = 0 ; i < vectorSize; i++){
            sb.append(convexPolygon.getVectors().get(i).toString());
            if(i != (vectorSize - 1)){
                sb.append(" ");
            }
        }
    }

    @Override
    public void visitCompoundShape(CompoundShape compoundShape) {
        sb.append("CompoundShape {");
        indentLevel++;
        Iterator<Shape> compoundShapes = compoundShape.iterator();

        if(compoundShapes.hasNext()){
            sb.append("\n");
            while (compoundShapes.hasNext()) {
                indent();
                Shape shape = compoundShapes.next();
                shape.accept(this);
                sb.append("\n");
            }
        }

        indentLevel--;
        indent();
        sb.append("}");
    }

    @Override
    public void visitTextedShape(TextedShape textedShape) {
        //ex. Circle 1.0, text: Hello
        textedShape.getShape().accept(this);  // 直接訪問內部形狀
        sb.append(", text: ").append(textedShape.getText());
    }

    @Override
    public void visitColoredShape(ColoredShape coloredShape) {
        String color = coloredShape.getColor();
        String colorCode = "";
        switch (color) {
            case "RED":
                colorCode = "\033[0;31m";
                break;
            case "GREEN":
                colorCode = "\033[0;32m";
                break;
            case "BLUE":
                colorCode = "\033[0;34m";
                break;
        }
        sb.append(colorCode);

        coloredShape.getShape().accept(this);
        sb.append("\033[0m");
    }

    @Override
    public String getResult() {
        return sb.toString();
    }

    public void indent(){
        for(int i=0 ; i < indentLevel; i++){
            sb.append("  ");
        }
    }
}
