package designPattern.structural.adapter;

public class SystemConfiguration {

    String systemName;
    String organization;
    String scheduleEnable;
    String scheduleTime;

    public SystemConfiguration(int systemName, int organization, double scheduleEnable, String scheduleTime) {
        this.systemName = String.valueOf(systemName);
        this.organization = String.valueOf(organization);
        this.scheduleEnable = String.valueOf(scheduleEnable);
        this.scheduleTime = scheduleTime;
    }


}
