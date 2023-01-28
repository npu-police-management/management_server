package com.nwpu.managementserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
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
