package ebookshop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import ebookshop.entity.Candidate;
import ebookshop.entity.Voter;

@Configuration
@EnableRedisRepositories
class RedisConfig {
	@Value("${spring.data.redis.contactPoints}")
	private String CONTACT_POINTS;

	@Value("${spring.data.redis.port}")
	private int PORT;

	@Bean
	public LettuceConnectionFactory redisConnectionFactory() {

		return new LettuceConnectionFactory(new RedisStandaloneConfiguration(CONTACT_POINTS, PORT));
	}

	@Bean
	public ReactiveRedisTemplate<String, String> reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {
		return new ReactiveRedisTemplate<>(factory, RedisSerializationContext.string());
	}

	@Bean
	public ReactiveRedisTemplate<String, Candidate> reactiveCandidateRedisTemplate(ReactiveRedisConnectionFactory factory) {
		RedisSerializationContext<String, Candidate> serializationContext = RedisSerializationContext
				.<String, Candidate>newSerializationContext(new StringRedisSerializer())
				.hashKey(new StringRedisSerializer())
				.hashValue(new Jackson2JsonRedisSerializer<>(Candidate.class))
				.build();

		return new ReactiveRedisTemplate<>(factory, serializationContext);
	}

	@Bean
	public ReactiveRedisTemplate<String, Voter> reactiveVoterRedisTemplate(ReactiveRedisConnectionFactory factory) {
		RedisSerializationContext<String, Voter> serializationContext = RedisSerializationContext
				.<String, Voter>newSerializationContext(new StringRedisSerializer())
				.hashKey(new StringRedisSerializer())
				.hashValue(new Jackson2JsonRedisSerializer<>(Voter.class))
				.build();

		return new ReactiveRedisTemplate<>(factory, serializationContext);
	}

}

