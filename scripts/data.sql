INSERT INTO address (country, city, street)
VALUES
('Viet Nam', 'Ha Noi', 'Pham Hung'),
('Viet Nam', 'Bac Giang', 'Le Trong Tan');

INSERT INTO student (name, birthday, phone, email, addressid)
VALUES
('Nguyen Huu Vu', '2000-09-15', '0987654599', 'huuvu@gmail.com', 1),
('Nguyen Thu Huong', '2001-05-10', '0812345678', 'huongnguyen@gmail.com', 2);

INSERT INTO teacher (name, teachertype) VALUES
('Nguyen Dang Phu', 'Tien sy'),
('Le Thanh Ha', 'Giao su'),
('Nguyen Thi Hien', 'Thac sy');

 INSERT INTO course (name, credits, description, teacherId)
 VALUES
 ('Lập trình hướng đối tượng', 4, 'Lập trình hướng đối tượng là một mẫu hình lập trình dựa trên khái niệm "đối tượng", mà trong đó, đối tượng chứa đựng các
 dữ liệu, trên các trường, thường được gọi là các thuộc tính; và mã nguồn, được tổ chức thành các phương thức.', 1),
 ('Giải tích', 3, 'Giải tích toán học là phân nhánh của toán học làm việc với hàm liên tục, giới hạn và các lý thuyết liên quan như đạo hàm, tích phân, đo l
 ường, chuỗi vô hạn và các hàm giải tích. Những lý thuyết này thường được nghiên cứu trong trường số thực và số phức.', 2),
 ('Lập trình hệ thống', 3, 'Lập trình hệ thống là công việc viết những phần mềm cho hệ thống.', 1),
 ('Đại số', 5, 'Đại số là một phân nhánh lớn của toán học, cùng với lý thuyết số, hình học và giải tích. Theo nghĩa chung nhất, đại số là việc nghiên cứu về
  ký hiệu toán học và các quy tắc cho các thao tác các ký hiệu trên; nó là một chủ đề thống nhất của hầu hết tất cả lĩnh vực của toán học.', 3);

INSERT INTO student_course (studentid, courseid) VALUES (1, 1), (1, 2), (1, 3), (2, 1), (2, 3), (2, 4);
INSERT INTO student_course (studentid, courseid) VALUES (1, 1), (1, 2), (1, 3), (2, 1), (2, 3), (2, 4);