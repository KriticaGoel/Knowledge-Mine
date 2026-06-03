package designPattern.structural.adapter;

public class SystemConfigurationAdapter {

    private final ThirdPartySystemConfigApi thirdPartyApi;
    private final ThirdPartyConfigMapper mapper;

    SystemConfigurationAdapter(ThirdPartySystemConfigApi thirdPartyApi, ThirdPartyConfigMapper mapper) {
        this.thirdPartyApi = thirdPartyApi;
        this.mapper = mapper;
    }

    public SystemConfiguration getSystemConfiguration(){
        String rawPayload = thirdPartyApi.fetchConfig();
        return mapper.mapToSystemConfiguration(rawPayload);
    }
}
