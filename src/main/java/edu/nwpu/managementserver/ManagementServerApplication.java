package edu.nwpu.managementserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ManagementServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagementServerApplication.class, args);
		System.out.println("""
				       ┌─┐       ┌─┐ + +
				    ┌──┘ ┴───────┘ ┴──┐++
				    │                 │
				    │       ───       │++ + + +
				    ███████───███████ │+
				    │                 │+
				    │       ─┴─       │
				    │                 │
				    └───┐         ┌───┘
				        │         │
				        │         │   + +
				        │         │
				        │         └──────────────┐
				        │                        │
				        │                        ├─┐
				        │                        ┌─┘
				        │                        │
				        └─┐  ┐  ┌───────┬──┐  ┌──┘  + + + +
				          │ ─┤ ─┤       │ ─┤ ─┤
				          └──┴──┘       └──┴──┘  + + + +
				                 神兽保佑
				                代码无BUG!
				              项目成功跑起来啦！
				\s""");

	}

}
