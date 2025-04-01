package org.example.conditional.beans.classes;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnClass(name = "org.example.conditional.beans.JustClass")
public class ClassBean implements ClassConditionalBean {
}
