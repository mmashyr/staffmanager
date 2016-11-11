package com.mmashyr.staffmanager.config;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Created by Mark
 */
@Order(2)
public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {

}
