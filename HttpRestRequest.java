public class HttpRestRequest {

    protected MuleMessage muleMessage;
    protected Constraints validationConstraints;

    public HttpRestRequest(MuleMessage muleMessage) {
        this.muleMessage = muleMessage;
    }

    public void validate(Constraints validationConstraints) throws InvalidHeaderException {
        this.validationConstraints = validationConstraints;

        for (Constraint constraint : validationConstraints.getHeaderConstraints())
        {
            String headerName = constraint.getHeaderName();
            String headerValue = muleMessage.getHeader(headerName);
            checkExeptions(headerName, headerValue);
        }
    }

    public MuleMessage setMassage()
    {
        for (Constraint constraint : validationConstraints.getHeaderConstraints())
        {
            String headerName = constraint.getHeaderName();
            String headerValue = muleMessage.getHeader(headerName);
            setHeaderValues(headerName, HeaderValues);
        }
        return MuleMessage;
    }

    public void checkExeptions(String headerName, String HeaderValues)
    {
        checkHeaderReq(headerName, HeaderValues);
        checkHeaderFormat( headerName, HeaderValues);   
    }

    public void checkHeaderReq(String headerName, String HeaderValues) throws InvalidHeaderException
    {
        if (headerValue == null && constraint.isHeaderRequired()) {
                throw new InvalidHeaderException("Required header " + headerName + " not specified");
            }
    }

    public void setHeaderValues(String headerName, String HeaderValues)
    {
        if (headerValue == null && constraint.getDefaultValue() != null) {
                muleMessage.setHeader(headerName, constraint.getDefaultValue());
            }
    }

    public void checkHeaderFormat(String headerName, String HeaderValues) throws InvalidHeaderException
    {
        if (headerValue != null) {
                if (!constraint.validate(headerValue)) {
                    throw new InvalidHeaderException(MessageFormat.format("Invalid value format for header {0}.", headerName));
                }
            }
    }


  
    
}