package designPattern.structural.adapter;

public class FakeThirdPartySystemConfigApi implements ThirdPartySystemConfigApi {

    @Override
    public String fetchConfig() {
        return "{\"systemName\":101,\"organization\":202,\"scheduleEnable\":1.0,\"scheduleTime\":\"09:30\"}";
    }
}

