package com.hdu.drools;

import com.hdu.drools.models.ApplicationModel;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/3/25
 * @Time 10:44 AM
 */
public class Main {
    private static ApplicationContext context;

    public static void main(String[] args) throws InterruptedException {
        initSpring();
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession("my-ssesion");
        ApplicationModel applicationModel = new ApplicationModel();
        applicationModel.setAge(17);
        applicationModel.setName("st");
        applicationModel.setValid(true);
        System.out.println(applicationModel.toString());
        kieSession.insert(applicationModel);
        kieSession.fireAllRules();
        System.out.println("==================>>>>>>>>>>>");
        System.out.println(applicationModel.toString());
        applicationModel.setValid(true);
        kieSession.insert(applicationModel);
        kieSession.fireAllRules();
        System.out.println(applicationModel.toString());
    }

    private static void initSpring() {
        context = new AnnotationConfigApplicationContext("com.hdu.drools");

    }

}
