package Lab5.Application.Config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Map;

@Configuration
public class KafkaConfig {

    @Bean
    NewTopic createCatTopic() {
        return TopicBuilder.name("cat-add-events-topic")
                .partitions(3)
                .replicas(3)
                .configs(Map.of("min.insync.replicas", "2"))
                .build();
    }
    @Bean
    NewTopic getCatTopic() {
        return TopicBuilder.name("cat-get-events-topic")
                .partitions(3)
                .replicas(3)
                .configs(Map.of("min.insync.replicas", "2"))
                .build();
    }
    @Bean
    NewTopic deleteCatTopic() {
        return TopicBuilder.name("cat-delete-events-topic")
                .partitions(3)
                .replicas(3)
                .configs(Map.of("min.insync.replicas", "2"))
                .build();
    }
    @Bean
    NewTopic updateCatTopic() {
        return TopicBuilder.name("cat-update-events-topic")
                .partitions(3)
                .replicas(3)
                .configs(Map.of("min.insync.replicas", "2"))
                .build();
    }

    @Bean
    NewTopic createOwnerTopic() {
        return TopicBuilder.name("owner-add-events-topic")
                .partitions(3)
                .replicas(3)
                .configs(Map.of("min.insync.replicas", "2"))
                .build();
    }
    @Bean
    NewTopic updateOwnerTopic() {
        return TopicBuilder.name("owner-update-events-topic")
                .partitions(3)
                .replicas(3)
                .configs(Map.of("min.insync.replicas", "2"))
                .build();
    }
    @Bean
    NewTopic getOwnerTopic() {
        return TopicBuilder.name("owner-get-events-topic")
                .partitions(3)
                .replicas(3)
                .configs(Map.of("min.insync.replicas", "2"))
                .build();
    }
    @Bean
    NewTopic deleteOwnerTopic() {
        return TopicBuilder.name("owner-delete-events-topic")
                .partitions(3)
                .replicas(3)
                .configs(Map.of("min.insync.replicas", "2"))
                .build();
    }
    @Bean
    NewTopic resultCatTopic() {
        return TopicBuilder.name("cat-result-topic")
                .partitions(3)
                .replicas(3)
                .configs(Map.of("min.insync.replicas", "2"))
                .build();
    }
}
