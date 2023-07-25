package com.olus.olingo4.nnmrls.web;

import com.olus.olingo4.nnmrls.service.NnmrlsEntityCollectionProcessor;
import com.olus.olingo4.nnmrls.service.NnmrlsEntityPrimitiveProcessor;
import com.olus.olingo4.nnmrls.service.NnmrlsEntityProcessor;
import com.olus.olingo4.nnmrls.service.provider.NnmrlsEdmProvider;
import com.olus.olingo4.nnmrls.storage.Olingo4Storage;
import lombok.extern.slf4j.Slf4j;
import org.apache.olingo.server.api.OData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class represents a standard HttpServlet implementation.
 * It is used as main entry point for the web application that carries the OData service.
 * The implementation of this HttpServlet simply delegates the user requests to the ODataHttpHandler
 *
 * @author Oleksii Usatov
 */
@Slf4j
public class NmrlsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {

        try {
            var session = req.getSession(true);
            var storage = (Olingo4Storage) session.getAttribute(Olingo4Storage.class.getName());
            if (storage == null) {
                storage = new Olingo4Storage();
                session.setAttribute(Olingo4Storage.class.getName(), storage);
            }

            // Create odata handler and configure it with EdmProvider and Processor
            var odata = OData.newInstance();
            var edm = odata.createServiceMetadata(new NnmrlsEdmProvider(), new ArrayList<>());
            var handler = odata.createHandler(edm);
            handler.register(new NnmrlsEntityCollectionProcessor(storage));
            handler.register(new NnmrlsEntityProcessor(storage));
            handler.register(new NnmrlsEntityPrimitiveProcessor(storage));

            // Let the handler do the work
            handler.process(req, resp);
        } catch (RuntimeException e) {
            log.error("Server Error occurred in ExampleServlet", e);
            throw new ServletException(e);
        }
    }
}
