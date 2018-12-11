package ebookshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ebookshop.domain.Book;

@Service
public class WebBookService {

    @Autowired
    @LoadBalanced // load balancer converts BOOKS_SERVICE to actual hostname
    protected RestTemplate restTemplate;

    protected String serviceUrl;

    @Autowired
    public WebBookService(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
    }

    public Book getByNumber(String bookNumber) {
        Book book = restTemplate.getForObject(serviceUrl + "/api/books/{number}", Book.class, bookNumber);
        return book;
    }
}