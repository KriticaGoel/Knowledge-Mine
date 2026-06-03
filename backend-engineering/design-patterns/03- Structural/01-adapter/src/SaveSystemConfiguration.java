package designPattern.structural.adapter;

import java.util.List;

public class SaveSystemConfiguration {

    List<SystemConfiguration> systemConfigurations;
    public SaveSystemConfiguration(List<SystemConfiguration> systemConfigurations) {
        this.systemConfigurations = systemConfigurations;
    }
    public void save(){
        for (SystemConfiguration systemConfiguration : systemConfigurations) {
            System.out.println("Save data in db");
            System.out.println("System Name: " + systemConfiguration.systemName);
            System.out.println("Organization: " + systemConfiguration.organization);
            System.out.println("Schedule Enable: " + systemConfiguration.scheduleEnable);
            System.out.println("Schedule Time: " + systemConfiguration.scheduleTime);
        }
    }
}
