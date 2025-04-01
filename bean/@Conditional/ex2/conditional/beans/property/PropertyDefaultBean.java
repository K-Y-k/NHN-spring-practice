package org.example.conditional.beans.property;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(PropertyBean.class)
public class PropertyDefaultBean implements PropertyConditionalBean {
}
