package org.example.conditional.beans.resource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(ResourceBean.class)
public class ResourceDefaultBean implements ResourceConditionBean {
}
