package org.ntut.posd2024f.shapes;

import java.util.Iterator;

public class PrettyPrintVisitor implements Visitor<String> {
    private StringBuilder sb = new StringBuilder();

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
        for(TwoDimensionalVector v: triangle.getVectors()){
            sb.append(v.toString());
        }
    }

    @Override
    public void visitConvexPolygon(ConvexPolygon convexPolygon) {
        sb.append("ConvexPolygon ");
        
        for(TwoDimensionalVector v: convexPolygon.getVectors()){
            sb.append(v.toString());
        }
    }

    @Override
    public void visitCompoundShape(CompoundShape compoundShape) {
        sb.append("CompoundShape {");
        Iterator<Shape> compoundShapes = compoundShape.iterator();

        if(compoundShapes.hasNext()){
            sb.append("\n");
            while (compoundShapes.hasNext()) {
                Shape shape = compoundShapes.next();
                shape.accept(this);
                sb.append("\n");
            }
        }
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
            case "Red":
                colorCode = "\033[0;31m";
                break;
            case "Green":
                colorCode = "\033[0;32m";
                break;
            case "Blue":
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
}
