package com.example.ebookbackend.websocket.encoder;

import com.example.ebookbackend.constant.common.CliAddOrderMul;

import javax.websocket.EndpointConfig;
import java.io.StringWriter;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
public class CliAddOrderMulEncoder implements Encoder.Text<CliAddOrderMul>{
    @Override
    public void init(EndpointConfig ec) { }

    @Override
    public void destroy() { }

    @Override
    public String encode(CliAddOrderMul object) throws EncodeException {
        JAXBContext jaxbContext = null;
        StringWriter st = null;
        try {
            jaxbContext = JAXBContext.newInstance(CliAddOrderMul.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            st = new StringWriter();
            marshaller.marshal(object, st);
            System.out.println("OutGoing XML " + st.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return st.toString();
    }
}
