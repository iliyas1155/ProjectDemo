package org.skyal.demo;

import lombok.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
@SpringBootApplication
public class DemoApplication {
	public static PageMapper page;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	public static String out;
	@Bean
	CommandLineRunner test (PageMapper page) {
		return args -> {
			List<Page> pages = Arrays.asList(
					new Page(null, "/1", "Hello, World"),
					new Page(null, "/2", "Hello, World2")
			);

			pages.forEach(page::insert);
			page.selectAll().forEach(System.out::println);
			DemoApplication.page = page;
		};
	}
}

@Mapper
interface PageMapper {
	@Options(useGeneratedKeys = true)
	@Insert("INSERT INTO page(url, title) values(#{url}, #{title})")
	void insert(Page page);

	@Select("SELECT * FROM page")
	Collection<Page> selectAll();

	@Select("SELECT * FROM page WHERE url = #{url}")
	void get(String url);
	
	@Select("SELECT title FROM page WHERE id = 1")
	String getText();
	
	@Update("UPDATE page SET title = #{text} WHERE id = 1")
	void update(String text);
}


@Data
class Page {
	private Long id;
	private String url;
	private String title;

	Page(Long i, String url, String title) {
		this.id = i;
		this.url = url;
		this.title = title;
	}
	public String toString(){
		return title;
	}
}