package cz.tul.config.readonlysetup;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Condition;

//because of that (implements condition) I can use this class in @Conditional()
@Component
public class ReadOnlyConditionDisabled implements Condition {

    //returns null, idk why - so I use .getProperty()
    @Value("${app.readOnlyMode}")
    private String mode;

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String property = conditionContext.getEnvironment().getProperty("app.readOnlyMode");
        //System.out.println(mode);
        //System.out.println(property);
        return !property.equals("true");

    }

}
