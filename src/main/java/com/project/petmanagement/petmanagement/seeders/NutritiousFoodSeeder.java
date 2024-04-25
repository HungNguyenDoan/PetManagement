package com.project.petmanagement.petmanagement.seeders;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.petmanagement.petmanagement.models.entity.FoodType;
import com.project.petmanagement.petmanagement.models.entity.NutritiousFood;
import com.project.petmanagement.petmanagement.repositories.FoodTypeRepository;
import com.project.petmanagement.petmanagement.repositories.NutritiousFoodRepository;

@Component
public class NutritiousFoodSeeder {
    @Autowired
    private NutritiousFoodRepository NutritiousFoodRepository;

    @Autowired
    private FoodTypeRepository foodTypeRepository;

    public void seed() {
        List<FoodType> listFoodTypes = foodTypeRepository.findAll();

        NutritiousFood nutri1 = NutritiousFood.builder()
                .id(1L)
                .name("Miếng Snack Sức Khỏe Acme")
                .description("Thức ăn khô cao cấp được bổ sung các loại vitamin và khoáng chất cho sức khỏe tổng thể.")
                .build();
        nutri1.setFoodType(listFoodTypes.get(0));

        NutritiousFood nutri2 = NutritiousFood.builder()
                .id(2L)
                .name("NutriPaws Món Gà Lựa Chọn")
                .description("Thức ăn ướt đóng hộp với các miếng gà thơm ngon trong nước sốt đậm đà.")
                .build();
        nutri2.setFoodType(listFoodTypes.get(1));

        NutritiousFood nutri3 = NutritiousFood.builder()
                .id(3L)
                .name("Bữa Tiệc Salmon Tự Nhiên")
                .description("Thức ăn sống kết hợp giữa cá hồi tươi và các chất dinh dưỡng cần thiết.")
                .build();
        nutri3.setFoodType(listFoodTypes.get(2));

        NutritiousFood nutri4 = NutritiousFood.builder()
                .id(4L)
                .name("Sự Kết Hợp Thịt Bò Hoàn Hảo")
                .description("Sự kết hợp ngon miệng giữa thịt bò, gạo và rau cải cho một chế độ ăn cân đối.")
                .build();
        nutri4.setFoodType(listFoodTypes.get(3));

        NutritiousFood nutri5 = NutritiousFood.builder()
                .id(5L)
                .name("WhiskerWell Hỗn Hợp Không Chứa Ngũ Cốc")
                .description("Thức ăn khô không chứa ngũ cốc với hỗn hợp thịt thật và rau cải.")
                .build();
        nutri5.setFoodType(listFoodTypes.get(5));

        NutritiousFood nutri6 = NutritiousFood.builder()
                .id(6L)
                .name("Chế Độ Ẩm Thực Được Chỉ Định Bởi Bác Sĩ Thú Y")
                .description("Chế độ ăn do bác sĩ thú y chỉ định cho các điều kiện sức khỏe cụ thể.")
                .build();
        nutri6.setFoodType(listFoodTypes.get(4));

        NutritiousFood nutri7 = NutritiousFood.builder()
                .id(7L)
                .name("Hạt Khô Hữu Cơ Chứa Gà và Quinoa Organic")
                .description("Thức ăn khô hữu cơ với gà và quinoa để có một bữa ăn bổ dưỡng.")
                .build();
        nutri7.setFoodType(listFoodTypes.get(6));

        NutritiousFood nutri8 = NutritiousFood.builder()
                .id(8L)
                .name("DentalDelish Hương Bạc Hà Thơm Mát")
                .description("Bánh cho sức khỏe răng miệng với hương vị bạc hà để tạo cảm giác sảng khoái.")
                .build();
        nutri8.setFoodType(listFoodTypes.get(8));

        NutritiousFood nutri9 = NutritiousFood.builder()
                .id(9L)
                .name("RAWvolution Thịt Bò Tươi Lạnh")
                .description("Thức ăn tươi lạnh đóng băng với thịt bò thật, hoàn hảo cho một chế độ ăn tự nhiên.")
                .build();
        nutri9.setFoodType(listFoodTypes.get(2));

        NutritiousFood nutri10 = NutritiousFood.builder()
                .id(10L)
                .name("TastyTreats Món Phô Mai Thơm Ngon")
                .description("Bánh thưởng ẩm với hương vị phô mai, lý tưởng cho các buổi tập huấn.")
                .build();
        nutri10.setFoodType(listFoodTypes.get(9));

        NutritiousFoodRepository.saveAll(
                Arrays.asList(nutri1, nutri2, nutri3, nutri4, nutri5, nutri6, nutri7, nutri8, nutri9, nutri10));
    }
}
