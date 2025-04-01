package org.example.conditional.beans.property;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "justproperty", havingValue = "true")
public class PropertyBean implements PropertyConditionalBean {
}
