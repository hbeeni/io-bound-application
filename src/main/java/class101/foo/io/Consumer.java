package class101.foo.io;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class Consumer {

	private final PostRepository postRepository;
	private final ObjectMapper objectMapper;

	@RabbitListener(queues = "CREATE_POST_QUEUE")
	public void handler(String message) throws JsonProcessingException {
		Post post = objectMapper.readValue(message, Post.class);
		postRepository.save(post);
	}
}
