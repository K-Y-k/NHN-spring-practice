package org.example.conditional.beans.web;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnWebApplication
public class WebApplicationBean implements WebApplicationConditionBean {
}
