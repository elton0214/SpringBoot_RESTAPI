package idv.Healthcare;

import idv.Healthcare.Model.ApplicationUser;
import idv.Healthcare.Model.Appointment;
import idv.Healthcare.Model.Patient;
import idv.Healthcare.service.ApplicationUserService;
import idv.Healthcare.service.IApplicationUserService;
import idv.Healthcare.service.IAppointmentService;
import idv.Healthcare.service.IPatientService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;


@SpringBootApplication
public class HealthcareApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthcareApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	//58:40 go~

	@Bean
	CommandLineRunner run(IPatientService patService, ApplicationUserService appuserService, IAppointmentService appmtService) {

		return args -> {
			Patient patient1 = new Patient("1","John","john@gmail.com","0988111", new Date());
			Patient patient2 =new Patient("2","Sean","sean@gmail.com","0988222",new Date());
			patService.savePatient(patient1);
			patService.savePatient(patient2);
			patService.savePatient(new Patient("3","David","david@gmail.com","0988333",new Date()));

//			appuserService.saveApplicationUser(new ApplicationUser(
//					"sean",
//					"sean@gmail.com",
//					"1234",
//					"0988111",
//					"Taipei")
//			);


			appmtService.saveAppointment(new Appointment(
					"1",
					"disease1",
					new Date(),
					"1",
					"1",
					new Date()
					)
			);

			appmtService.saveAppointment(new Appointment(
							"2",
							"disease2",
							new Date(),
							"1",
							"2",
							new Date()
					)
			);

			appmtService.saveAppointment(new Appointment(
							"3",
							"disease3",
							new Date(),
							"1",
							"1",
							new Date()
					)
			);

			appmtService.saveAppointment(new Appointment(
							"4",
							"disease3",
							new Date(),
							"1",
							"1",
							new Date()
					)
			);


			appmtService.addPatientToAppointment("1","1");

			//test patient
			String id2 = "3";
			var val = patService.findById(id2);

			if (val.isPresent()) {
				System.out.println(val.get());
			} else {
				System.out.printf("No patient found with id %s%n", id2);
			}

			//test appointment
			String id3 = "1";
			var val3 = appmtService.findByBookingid(id3);

			if (val3.isPresent()) {
				System.out.println(val3.get());
			} else {
				System.out.printf("No appointment found with id %s%n", id3);
			}
//
		};


	}

}
