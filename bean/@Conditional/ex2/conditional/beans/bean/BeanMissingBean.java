package org.example.conditional.beans.bean;

import org.example.conditional.beans.classes.ClassBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnMissingBean(ClassBean.class)
public class BeanMissingBean implements BeanConditionBean {
}
