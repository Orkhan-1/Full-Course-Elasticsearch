import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.Refresh;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;

public class ESInsertExample {
    public static void main(String[] args) throws Exception {
        RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200))
                .build();
        ElasticsearchClient client = new ElasticsearchClient(
                new RestClientTransport(restClient, new JacksonJsonpMapper()));

        IndexResponse response = client.index(i -> i
                .index("products")
                .document(new Product("Monitor_1", 1200))
                .refresh(Refresh.True)
        );

        System.out.println("Indexed with ID: " + response.id());
        restClient.close();
    }

    record Product(String name, double price) {}
}
