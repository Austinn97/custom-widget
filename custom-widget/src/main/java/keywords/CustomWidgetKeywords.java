package keywords;

import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.swing.context.Context;
import org.netbeans.jemmy.operators.JComponentOperator;
import org.netbeans.jemmy.operators.ContainerOperator;
import org.netbeans.jemmy.ComponentChooser;
import java.awt.Component;
import java.awt.Shape;
import java.awt.Rectangle;
import edu.jsu.mcis.CustomWidget;

@RobotKeywords
public class CustomWidgetKeywords {
    @RobotKeyword("Clicks inside of the Hexagon in the custom widget.\n")
    @ArgumentNames({})
    public void clickHexagon() {
        ContainerOperator context = (ContainerOperator) Context.getContext();
        ComponentChooser chooser = new CustomWidgetChooser();
        JComponentOperator operator = new JComponentOperator(context, chooser);
		CustomWidget w = (CustomWidget)operator.getSource();
		Rectangle b = w.getShapes()[0].getBounds();
        operator.clickMouse(b.x + b.width/2, b.y + b.height/2, 1);
      
    }
    
    @RobotKeyword("Clicks inside of the Octagon in the custom widget .\n")
    @ArgumentNames({})
    public void clickOctagon() {
        ContainerOperator context = (ContainerOperator) Context.getContext();
        ComponentChooser chooser = new CustomWidgetChooser();
        JComponentOperator operator = new JComponentOperator(context, chooser);
        CustomWidget w = (CustomWidget)operator.getSource();
        Rectangle b = w.getShapes()[1].getBounds();
        operator.clickMouse(b.x + b.width/2, b.y + b.height/2, 1);
    }
    
    @RobotKeyword("Clicks outsdie of the shape in the custom widget.\n")
    @ArgumentNames({})
    public void clickOutside() {
        ContainerOperator context = (ContainerOperator) Context.getContext();
        ComponentChooser chooser = new CustomWidgetChooser();
        JComponentOperator operator = new JComponentOperator(context, chooser);
        CustomWidget w = (CustomWidget)operator.getSource();
        Rectangle b = w.getShapes()[0].getBounds();
        operator.clickMouse(b.x - 10, b.y - 10, 1);
		Rectangle b1 = w.getShapes()[1].getBounds();
        operator.clickMouse(b1.x - 10, b1.y - 10, 1);
    }

	
    class CustomWidgetChooser implements ComponentChooser {
        public CustomWidgetChooser() {}
        public boolean checkComponent(Component comp) {
            return (comp instanceof CustomWidget);
        }
        public String getDescription() {
            return "Any CustomWidget";
        }
    }
}