package designPattern.structural.adapter;

public interface ThirdPartyConfigMapper {
    SystemConfiguration mapToSystemConfiguration(String rawPayload);
}

