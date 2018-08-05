package io.javabrains.springbootquickstart.CourseApiApp.topics;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {
	
	@Autowired
	private TopicService topicService;
		
	@RequestMapping("/topic")
	public List<Topics> getAllTopics() {
		
		return topicService.getAllTopics();
	}
	
	@RequestMapping("/topic/{id}")
	public Topics getTopic(@PathVariable String id)
	{
		return topicService.getTopic(id);
	}
	/*
	@RequestMapping("/topic/{food}")
	public Topics getTopics(@PathVariable("food") String id)
	{
		return topicService.getTopic(id);
	}*/
}
