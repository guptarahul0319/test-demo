package io.javabrains.springbootquickstart.CourseApiApp.topics;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TopicService {
	
	private List<Topics> topics= Arrays.asList(
			new Topics("Spring","SpringFrameWork","This is Springgfktfktcy"),
			new Topics("Java","SpringFrameWork","This is Springhvkjvh"),
			new Topics("Angular","SpringFrameWork","This is Springvuyvuv"),
			new Topics("PHP","SpringFrameWork","This is Springhvlvkj"),
			new Topics("Maven","SpringFrameWork","This is Springjftkvkhc"));
	
			public List<Topics> getAllTopics()
			{
				return topics;
			}
			
			public Topics getTopic(String id)
			{
				return topics.stream().filter(t -> t.getId().equals(id)).findFirst().get();
			}

}
