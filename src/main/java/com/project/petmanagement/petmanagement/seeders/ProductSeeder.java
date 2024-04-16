package com.project.petmanagement.petmanagement.seeders;

import com.project.petmanagement.petmanagement.models.entity.Category;
import com.project.petmanagement.petmanagement.models.entity.Product;
import com.project.petmanagement.petmanagement.models.enums.ProductStatusEnum;
import com.project.petmanagement.petmanagement.repositories.CategoryRepository;
import com.project.petmanagement.petmanagement.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ProductSeeder {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    public void seed() {
        List<Category> categories = categoryRepository.findAll();
        Category category1 = categories.get(0);
        Category category2 = categories.get(1);
        Category category3 = categories.get(2);
        //food
        Product product1 = Product.builder().id(1L).name("Thức ăn cho chó con cỡ nhỏ ROYAL CANIN Mini Puppy").category(category1).price(215000d).status(ProductStatusEnum.AVAILABLE).isActive(true).image("")
                .description("Thức ăn cho chó con cỡ nhỏ ROYAL CANIN Mini Puppy dành cho các giống chó con dưới 10 tháng tuổi. Với công thức đặc chế riêng cho nhu cầu dinh dưỡng của chó con thuộc các giống cỡ nhỏ. Thức ăn cho chó con (các giống chó cỡ nhỏ) được nghiên cứu để cung cấp dinh dưỡng theo nhu cầu thực tế của chó con.").build();
        Product product2 = Product.builder().id(2L).name("Thức ăn cho chó Poodle con ROYAL CANIN Poodle Puppy").category(category1).price(175000d).status(ProductStatusEnum.AVAILABLE).isActive(true).image("")
                .description("Thức ăn cho chó Poodle con ROYAL CANIN Poodle Puppy dành riêng cho tất cả các giống chó Teacup, Tiny Poodle, Toy Poodle, Standard Poodle dưới 10 tháng tuổi.").build();
        Product product3 = Product.builder().id(3L).name("Thức ăn cho chó Poodle MKB All Life Stages Formula Nutrition").category(category1).price(315000d).status(ProductStatusEnum.AVAILABLE).isActive(true).image("")
                .description("Thức ăn cho chó Poodle MKB All Life Stages Formula Nutrition giúp bổ sung tỷ lệ cân đối giữa các chất dinh dưỡng. Đảm bảo sự phát triển của cún con qua tất cả các giai đoạn phát triển. Bao gồm cả những chú chó con, chó trưởng thành, chó mang thai, chó cho con bú và chó già.").build();
        Product product4 = Product.builder().id(4L).name("Thức ăn cho chó Poodle trưởng thành ROYAL CANIN Poodle Adult").category(category1).price(150000d).status(ProductStatusEnum.AVAILABLE).isActive(true).image("")
                .description("Thức ăn cho chó Poodle trưởng thành ROYAL CANIN Poodle Adult dành riêng cho tất cả các giống chó Teacup, Tiny Poodle, Toy Poodle, Standard Poodle từ 10 tháng tuổi trở lên. ROYAL CANIN Poodle Adult với công thức đặc chế riêng cho nhu cầu dinh dưỡng của chó Poodle, đặc trị các vấn đề về răng và lông ở chó Poodle.").build();
        Product product5 = Product.builder().id(5L).name("Thức ăn cho chó con hạt mềm ZENITH Puppy Chicken Potato").category(category1).price(220000d).status(ProductStatusEnum.AVAILABLE).isActive(true).image("")
                .description("Thức ăn cho chó con hạt mềm ZENITH Puppy Chicken Potato được chế biến từ thịt cừu tươi, thịt nạc gà rút xương, gạo lứt, yến mạch và dầu cá hồi. Với các thành phần tươi sạch, giàu dinh dưỡng, Zenith Puppy hạt mềm, cung cấp độ ẩm cao và lượng muối thấp, thơm ngon, dễ nhai, dễ tiêu hóa và tốt cho sức khỏe chó con.").build();
        Product product6 = Product.builder().id(6L).name("Pate cho mèo vị cá ngừ nguyên chất CAT SEA FISH Pure Tuna Meat").category(category1).price(30000d).status(ProductStatusEnum.AVAILABLE).isActive(true).image("")
                .description("Pate cho mèo vị cá ngừ nguyên chất CAT SEA FISH Pure Tuna Meat là thức ăn dinh dưỡng cho mèo phù hợp với tất cả các giống và độ tuổi. CAT SEA FISH Pure Tuna Meat là một sản phẩm cho sức khỏe. Với dưỡng chất Axit béo trong cá ngừ là các axit béo không bão hòa. Chứa đầy đủ các axit amin đối với cơ thể. Với 8 loại axit amin cần thiết, vitamin, chất sắt, kali, canxi, iốt và các nguyên tố vi lượng khác.").build();
        Product product7 = Product.builder().id(7L).name("Sữa bột cho mèo con BBN Kitten Milk").category(category1).price(320000d).status(ProductStatusEnum.AVAILABLE).isActive(true).image("")
                .description("Sữa bột cho mèo con BBN Kitten Milk là sản phẩm dành cho mèo con khi mèo mẹ bị mất sữa, kém sữa hoặc thiếu sữa.").build();
        //toys
        Product product8 = Product.builder().id(8L).name("Đồ chơi cho chó mèo dạng bóng thừng BOBO").category(category3).price(30000d).status(ProductStatusEnum.AVAILABLE).isActive(true).image("")
                .description("Đồ chơi cho chó mèo dạng bóng thừng BOBO 1030 size S là sản phẩm dành cho tất cả giống chó và mèo.").build();
        Product product9 = Product.builder().id(9L).name("Bóng đồ chơi cho chó mèo có chuông nhựa BOBO").category(category3).price(45000d).status(ProductStatusEnum.AVAILABLE).isActive(true).image("")
                .description("Bóng đồ chơi cho chó mèo có chuông nhựa BOBO 1010 size S là sản phẩm dành cho tất cả giống chó và mèo.").build();
        Product product10 = Product.builder().id(10L).name("Đồ chơi cần câu cho mèo PAW vui nhộn").category(category3).price(75000d).status(ProductStatusEnum.AVAILABLE).isActive(true).image("")
                .description("Đồ chơi cần câu cho mèo PAW có thể hỗ trợ chú mèo tích cực tập thể dục mỗi ngày, không chỉ tăng cường mối quan hệ với chủ nhân mà còn duy trì một cơ thể hoàn hảo, tránh việc thiếu vận động trong nhà dẫn đến béo phì. Ưu điểm của cần câu mèo này là dây thép dẻo dai, siêu bền, bạn có thể lắc đi lắc lại, như một con cim lông vũ bay trên trời, sẽ gợi nhớ đến ký ức đuổi bắt các loại chim trong thế giới tự nhiên, khiến mèo cảm thấy vui vẻ.").build();
        Product product11 = Product.builder().id(11L).name("Đồ chơi dây thừng cho chó mèo PAW loại dài").category(category3).price(50000d).status(ProductStatusEnum.AVAILABLE).isActive(true).image("")
                .description("Đồ chơi dây thừng cho chó mèo PAW loại dài là sản phẩm dành cho tất cả giống chó và mèo.").build();
        Product product12 = Product.builder().id(12L).name("Đồ chơi cho chó mèo Paw hình con gà").category(category3).price(50000d).status(ProductStatusEnum.AVAILABLE).isActive(true).image("")
                .description("Đồ chơi cho chó mèo Paw hình con gà là sản phẩm dành cho tất cả giống chó. Sản phẩm được làm từ nhựa dẻo, rất an toàn với môi trường, khỏe mạnh, không hề có độc tố, không làm lọt hơi ra ngoài… chắc chắn thú cưng của bạn sẽ thích khi nghe tiếng kêu của sản phẩm này đấy.").build();
        Product product13 = Product.builder().id(13L).name("Roi huấn luyện chó mèo PAW").category(category3).price(500000d).status(ProductStatusEnum.AVAILABLE).isActive(true).image("")
                .description("Roi huấn luyện chó mèo PAW được dùng để huấn luyện, hướng dẫn, đào tạo thú cưng. Đặc biệt phù hợp để huấn luyện những chú chó, mèo bướng bỉnh. Bạn có thể dễ dàng dạy bảo thú cưng ngoan ngoãn nghe lời hơn. Đặc biệt trong việc huấn luyện đi vệ sinh, làm trò, nhặt bóng… Việc sử dụng roi huấn luyện cải thiện nhanh chóng thái độ của thú cưng với mệnh lệnh của bạn. Đệm cao su đàn hồi không lo cún cưng bị đau, bị thương.").build();
        Product product14 = Product.builder().id(14L).name("Đồ chơi cho chó kêu chút chít bằng cao su ELITE hình động vật").category(category3).price(50000d).status(ProductStatusEnum.AVAILABLE).isActive(true).image("")
                .description("Đồ chơi cho chó kêu chút chít bằng cao su ELITE hình động vật là sản phẩm dành cho tất cả giống chó. Đồ chơi cho chó kêu chút chít bằng cao su ELITE hình động vật được sản xuất từ cao su mềm không gây độc hại cho thú cưng. Sản phẩm có kích thước 19 x 8 x5.5 cm màu sắc như thật, âm thanh chút chít đáng yêu sẽ khiến cho cún cưng không cảm thấy cô đơn khi ở nhà một mình").build();
        //device
        Product product15 = Product.builder().id(15L).name("Máy lọc nước cho chó mèo tự động tuần hoàn PAW Pet Radius Water Dispenser").category(category2).price(345000d).status(ProductStatusEnum.AVAILABLE).isActive(true).image("")
                .description("Máy lọc nước cho chó mèo tự động tuần hoàn PAW Pet Radius Water Dispenser là một sản phẩm tiện ích và thông minh dành cho thú cưng của bạn. Sản phẩm có thiết kế đẹp mắt, nhỏ gọn và dễ sử dụng. PAW Pet Radius Water Dispenser có 02 màu sắc: Purplist Blue (Đen) và Classic White (Trắng) để bạn lựa chọn phù hợp với nhu cầu sở thích của bạn và thú cưng. Cung cấp nước sạch và tươi cho thú cưng: Sản phẩm có hệ thống lọc nước 3 lớp với than hoạt tính, maifan và nano bọt, giúp loại bỏ các tạp chất, mùi hôi, vi khuẩn và kim loại nặng trong nước.Cung cấp nước sạch và tươi cho thú cưng: Sản phẩm có hệ thống lọc nước 3 lớp với than hoạt tính, maifan và nano bọt, giúp loại bỏ các tạp chất, mùi hôi, vi khuẩn và kim loại nặng trong nước.").build();
        Product product16 = Product.builder().id(16L).name("Máy lọc nước cho mèo tự động PAW Ceramic Green Flowers").category(category2).price(455000d).status(ProductStatusEnum.AVAILABLE).isActive(true).image("")
                .description("Máy lọc nước cho mèo tự động PAW Ceramic Green Flowers (Automatic Cat Water Fountains) với thiết kế phong cách hoa lá xanh mát, đây là một thiết bị tiện lợi và an toàn cho thú cưng của bạn. Sản phẩm dùng để cung cấp nước sạch tươi cho mèo uống mỗi ngày, giúp chúng khỏe mạnh và tránh được các bệnh về sỏi thận và đường tiết niệu. Đài phun nước cho mèo này dùng được cho cả các giống chó nhỏ.").build();
        Product product17 = Product.builder().id(17L).name("Kềm cắt bấm móng chó mèo PAW Safety Guard Clipper").category(category2).price(70000d).status(ProductStatusEnum.AVAILABLE).isActive(true).image("")
                .description("Kềm cắt bấm móng chó mèo PAW Safety Guard Clipper được sử dụng để cắt móng chân cho tất cả giống chó và mèo. Màu sắc và thiết kế của sản phẩm có thể thay đổi khác nhau theo từng lô hàng, tuy nhiên kích thước và tính năng vẫn giống nhau.").build();
        Product product18 = Product.builder().id(18L).name("Kềm cắt bấm móng chó mèo PAW Pet Nail Clipper").category(category2).price(70000d).status(ProductStatusEnum.AVAILABLE).isActive(true).image("")
                .description("Kềm cắt bấm móng chó mèo PAW Pet Nail Clipper làm bằng vật liệu hợp kim chất lượng cao, bền, lưỡi kìm sắc nhọn. Hoạt động theo thiết kế vòng cung động cơ đẩy, dễ dàng sử dụng.").build();
        Product product19 = Product.builder().id(19L).name("Lược chải lông chó mèo đầu lưỡi mềm BOBO Pet Comb").category(category2).price(85000d).status(ProductStatusEnum.AVAILABLE).isActive(true).image("")
                .description("Lược chải lông chó mèo đầu lưỡi mềm BOBO Pet Comb giúp cho thú cưng nhà bạn có bộ lông đẹp ngay tại nhà. Với phần cán bằng inox chắc chắn. Tay cầm vừa vặn có lớp nhựa chống trơn trượt. Bạn có thể dễ dàng sử dụng, chải từng lớp lông rối, rụng của thú cưng một cách hiệu quả. Loại bỏ hầu hết các phần lông chết trên cơ thể của cún cưng. Không gây kích ứng da và cảm giác khó chịu. Phần răng lược mềm mại tạo cảm giác thoải mái nhất cho những người bạn 4 chân.").build();
        Product product20 = Product.builder().id(20L).name("Lược chải lông chó mèo 2 lưỡi KUDI 2-Sided Comb").category(category2).price(100000d).status(ProductStatusEnum.AVAILABLE).isActive(true).image("")
                .description("Lược chải lông chó mèo 2 lưỡi KUDI 2-Sided Comb gồm có 2 đầu. Đầu chải lông rồi, đầu còn lại vừa chỉa và cắt lông rối. Với 2 lưỡi dao 2 bên lược cắt tỉa cho chó mèo cưng dễ dàng hơn. Phần tay nắm được thiết kế thuận tiện, chống trơn trượt khi sử dụng. Việc chải lông cho chó mèo thường xuyên sẽ giúp loại bỏ lông rụng và rối của thú cưng. Đặc biệt không gây kích ứng da của chúng. Sau khi chải, bạn có thể ngâm lược vào nước xà phòng để loại bỏ bụi bẩn, xác và trứng của bọ chét, ve rận bám trên lược nếu có.").build();
        productRepository.saveAll(Arrays.asList(product1, product2, product3, product4, product5, product6, product7, product8, product9, product10, product11, product12, product13, product14, product15, product16, product17, product18, product19, product20));
    }
}
