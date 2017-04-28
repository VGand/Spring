package com.epam.spring.cinema.domain.jaxb;

import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

/**
 * Created by Andrey_Vaganov on 4/27/2017.
 */
public class CinemaSchemaOutputResolver extends SchemaOutputResolver {

    @Override
    public Result createOutput(String namespaceUri, String suggestedFileName) throws IOException {
        File file = new File(suggestedFileName);
        StreamResult result = new StreamResult(file);
        result.setSystemId(file.toURI().toURL().toString());
        return result;
    }
}
