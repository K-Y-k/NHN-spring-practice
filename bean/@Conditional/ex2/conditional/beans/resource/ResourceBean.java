package org.example.conditional.beans.resource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnResource(resources = "classpath:justfile")
public class ResourceBean implements ResourceConditionBean {
}
