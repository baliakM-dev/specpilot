package sk.martin.specpilot;

import org.springframework.boot.SpringApplication;

public class TestSpecPilotApplication {

	public static void main(String[] args) {
		SpringApplication.from(SpecPilotApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
