package com.example.MrPot;

import org.junit.jupiter.api.Test;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(
        properties = {
                "spring.ai.model.chat=none",
                "spring.ai.model.embedding=none",
                "spring.ai.openai.api-key=test",
                "app.elastic.base-url=http://localhost:9200",
                "app.elastic.username=user",
                "app.elastic.password=pass"
        }
)
@ActiveProfiles("test")
class MrPotApplicationTests {

    @MockBean
    private EmbeddingModel embeddingModel;

    @MockBean
    private OpenAiChatModel openAiChatModel;

    @Test
    void contextLoads() {
    }
}
