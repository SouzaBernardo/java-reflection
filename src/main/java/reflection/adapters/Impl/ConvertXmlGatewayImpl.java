package reflection.adapters.Impl;

import reflection.adapters.ConvertXmlGateway;
import reflection.core.useCase.ConvertXmlUseCase;

public class ConvertXmlGatewayImpl implements ConvertXmlGateway {

    private final ConvertXmlUseCase convertXmlUseCase;

    public ConvertXmlGatewayImpl(ConvertXmlUseCase useCase) {
        this.convertXmlUseCase = useCase;
    }

    public String convertToXml(Object object) {
        return convertXmlUseCase.convertToXml(object);
    }
}
