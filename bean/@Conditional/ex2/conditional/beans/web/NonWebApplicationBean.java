package org.example.conditional.beans.web;

import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnNotWebApplication
public class NonWebApplicationBean implements WebApplicationConditionBean {
}
