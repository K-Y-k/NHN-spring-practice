package org.example.conditional.beans.bean;

import org.example.conditional.beans.classes.ClassBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnBean(ClassBean.class)
public class BeanBean implements BeanConditionBean {
}
