package com.aaron.elasticsearchplugin.action;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.rest.RestChannel;
import org.elasticsearch.rest.RestController;
import org.elasticsearch.rest.RestRequest;
import static org.elasticsearch.rest.RestStatus.OK;
import org.elasticsearch.rest.*;

import static org.elasticsearch.rest.RestRequest.Method.GET;

public class HelloRestAction  extends BaseRestHandler{

    @Inject
    public HelloRestAction(Settings settings, Client client,
                            RestController controller) {
        super(settings, controller, client);
        controller.registerHandler(GET, "/_hello", this);
    }

    @Override
    protected void handleRequest(RestRequest request, RestChannel channel, Client client) throws Exception {
        logger.info("HelloRestAction.handleRequest called");
        String clustername = client.settings().get("cluster.name");
        channel.sendResponse(new BytesRestResponse(RestStatus.OK, XContentFactory.jsonBuilder().startObject().field("hello", "This is cluster –"+clustername).endObject()));
    }
}
