package spring.boot.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sample.mybatis.mapper.HotelMapper;
import sample.mybatis.util.mapper.MyMapper;

@SpringBootApplication
@RestController
@MapperScan(basePackages = "sample.mybatis.mapper", markerInterface = MyMapper.class)
public class App {
	
	@Autowired
	private HotelMapper hotelMapper;
	
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @RequestMapping(value = "/")
    String hello() {
    	
    	System.out.println(this.hotelMapper.selectByCityId(1));
        return "Hello World!";
    }
}