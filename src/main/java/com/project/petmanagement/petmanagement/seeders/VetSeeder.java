package com.project.petmanagement.petmanagement.seeders;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.petmanagement.petmanagement.models.entity.Vet;
import com.project.petmanagement.petmanagement.repositories.VetRepository;

@Component
public class VetSeeder {
    @Autowired
    private VetRepository repository;

    public void seed() {
        Vet v1 = Vet.builder().id(1L).fullName("John Cena").phoneNumber("0123456789").clinicAddress("Hanoi")
                .workAt("Hanoi").description("Good at dog").experience(1.0).email("john.cena@example.com").build();

        Vet v2 = Vet.builder().id(2L).fullName("Alice Smith").phoneNumber("0234567891")
                .clinicAddress("Ho Chi Minh City")
                .workAt("Ho Chi Minh City").description("Specializes in cats").experience(2.5)
                .email("alice.smith@example.com").build();

        Vet v3 = Vet.builder().id(3L).fullName("Bob Johnson").phoneNumber("0345678912").clinicAddress("Da Nang")
                .workAt("Da Nang").description("Veterinarian for small animals").experience(3.0)
                .email("bob.johnson@example.com").build();

        Vet v4 = Vet.builder().id(4L).fullName("Emily Brown").phoneNumber("0456789123").clinicAddress("Can Tho")
                .workAt("Can Tho").description("Skilled in exotic pets").experience(2.0)
                .email("emily.brown@example.com").build();

        Vet v5 = Vet.builder().id(5L).fullName("David Wilson").phoneNumber("0567891234").clinicAddress("Nha Trang")
                .workAt("Nha Trang").description("Expert in avian medicine").experience(4.0)
                .email("david.wilson@example.com").build();

        Vet v6 = Vet.builder().id(6L).fullName("Sophia Martinez").phoneNumber("0678912345").clinicAddress("Vung Tau")
                .workAt("Vung Tau").description("Passionate about animal welfare").experience(2.5)
                .email("sophia.martinez@example.com").build();

        Vet v7 = Vet.builder().id(7L).fullName("James Lee").phoneNumber("0789123456").clinicAddress("Ha Long City")
                .workAt("Ha Long City").description("Focuses on livestock").experience(3.5)
                .email("james.lee@example.com").build();

        Vet v8 = Vet.builder().id(8L).fullName("Emma Garcia").phoneNumber("0891234567").clinicAddress("Phu Quoc")
                .workAt("Phu Quoc").description("Well-versed in reptile care").experience(2.0)
                .email("emma.garcia@example.com").build();

        Vet v9 = Vet.builder().id(9L).fullName("Michael Nguyen").phoneNumber("0902345678").clinicAddress("Hue")
                .workAt("Hue").description("Specializes in veterinary surgery").experience(4.5)
                .email("michael.nguyen@example.com").build();

        Vet v10 = Vet.builder().id(10L).fullName("Olivia Kim").phoneNumber("0913456789").clinicAddress("Quy Nhon")
                .workAt("Quy Nhon").description("Experienced in geriatric care").experience(3.0)
                .email("olivia.kim@example.com").build();

        Vet v11 = Vet.builder().id(11L).fullName("Daniel Tran").phoneNumber("0924567890").clinicAddress("Hai Phong")
                .workAt("Hai Phong").description("Passionate about rehabilitation therapy").experience(2.0)
                .email("daniel.tran@example.com").build();

        Vet v12 = Vet.builder().id(12L).fullName("Ava Nguyen").phoneNumber("0935678901").clinicAddress("Mui Ne")
                .workAt("Mui Ne").description("Dedicated to holistic medicine").experience(3.5)
                .email("ava.nguyen@example.com").build();

        Vet v13 = Vet.builder().id(13L).fullName("William Le").phoneNumber("0946789012").clinicAddress("Bac Ninh")
                .workAt("Bac Ninh").description("Specializes in aquatic species").experience(2.5)
                .email("william.le@example.com").build();

        Vet v14 = Vet.builder().id(14L).fullName("Isabella Pham").phoneNumber("0957890123").clinicAddress("Sapa")
                .workAt("Sapa").description("Expert in wildlife rehabilitation").experience(4.0)
                .email("isabella.pham@example.com").build();

        Vet v15 = Vet.builder().id(15L).fullName("Jackson Do").phoneNumber("0968901234").clinicAddress("Mui Ne")
                .workAt("Mui Ne").description("Passionate about environmental conservation").experience(3.0)
                .email("jackson.do@example.com").build();

        Vet v16 = Vet.builder().id(16L).fullName("Mia Vo").phoneNumber("0979012345").clinicAddress("Dalat")
                .workAt("Dalat").description("Skilled in avian and exotic pet care").experience(2.0)
                .email("mia.vo@example.com").build();

        Vet v17 = Vet.builder().id(17L).fullName("Alexander Hoang").phoneNumber("0980123456").clinicAddress("Hoi An")
                .workAt("Hoi An").description("Expert in veterinary nutrition").experience(3.5)
                .email("alexander.hoang@example.com").build();

        Vet v18 = Vet.builder().id(18L).fullName("Ella Truong").phoneNumber("0991234567").clinicAddress("Can Tho")
                .workAt("Can Tho").description("Specializes in equine medicine").experience(4.0)
                .email("ella.truong@example.com").build();

        Vet v19 = Vet.builder().id(19L).fullName("Henry Phan").phoneNumber("0912345678").clinicAddress("Phan Thiet")
                .workAt("Phan Thiet").description("Good at large animal surgery").experience(3.0)
                .email("henry.phan@example.com").build();

        Vet v20 = Vet.builder().id(20L).fullName("Charlotte Vo").phoneNumber("0923456789").clinicAddress("Hue")
                .workAt("Hue").description("Skilled in emergency veterinary care").experience(2.5)
                .email("charlotte.vo@example.com").build();

        repository.saveAll(Arrays.asList(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13, v14, v15, v16, v17,
                v18, v19, v20));
    }
}
