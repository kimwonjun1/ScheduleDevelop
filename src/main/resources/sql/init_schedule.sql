CREATE TABLE user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      created_at DATETIME,
                      updated_at DATETIME
);

CREATE TABLE schedule (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          title VARCHAR(255) NOT NULL,
                          content LONGTEXT,
                          user_id BIGINT,
                          created_at DATETIME,
                          updated_at DATETIME,
                          CONSTRAINT fk_schedule_user FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE SET NULL
);

CREATE TABLE comment (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         content LONGTEXT NOT NULL,
                         user_id BIGINT,
                         schedule_id BIGINT,
                         created_at DATETIME,
                         updated_at DATETIME,
                         CONSTRAINT fk_comment_user FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
                         CONSTRAINT fk_comment_schedule FOREIGN KEY (schedule_id) REFERENCES schedule(id) ON DELETE CASCADE
);