package cz.tul.Config.ReadOnlySetup;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Condition;

@Component
public class ReadOnlyConditionEnabled implements Condition {

    //hází null nevim proč proto .getProperty()
    @Value("${app.readOnlyMode}")
    private String mode;

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String property = conditionContext.getEnvironment().getProperty("app.readOnlyMode");
        //System.out.println(mode);
        System.out.println(property);
        return property.equals("true");

    }

}
