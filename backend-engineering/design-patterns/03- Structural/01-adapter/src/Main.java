package designPattern.structural.adapter;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        String url = System.getenv("THIRD_PARTY_URL");
        String token = System.getenv("THIRD_PARTY_TOKEN");

        ThirdPartySystemConfigApi api;
        if (url == null || url.isBlank() || token == null || token.isBlank()) {
            api = new FakeThirdPartySystemConfigApi();
        } else {
            api = new HttpThirdPartySystemConfigApi(url, token);
        }

        ThirdPartyConfigMapper mapper = new BasicThirdPartyConfigMapper();
        SystemConfigurationAdapter adapter = new SystemConfigurationAdapter(api, mapper);

        SystemConfiguration configuration = adapter.getSystemConfiguration();
        new SaveSystemConfiguration(List.of(configuration)).save();
    }
}
