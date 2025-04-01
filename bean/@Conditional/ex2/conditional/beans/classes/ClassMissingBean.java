package org.example.conditional.beans.classes;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingClass("org.example.conditional.beans.JustClass")
public class ClassMissingBean implements ClassConditionalBean {
}
