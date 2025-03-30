package org.glue.glue_be.user.entity;

import jakarta.persistence.*;
import java.time.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "uuid", nullable = false, unique = true, length = 36)
    private String uuid;

    @Column(name = "oauth_id", nullable = false, unique = true)
    private String oauthId;

    @Column(name = "user_name", nullable = false, length = 20)
    private String userName;

    @Column(name = "nickname", nullable = false, length = 10, unique = true)
    private String nickname;

    @Column(name = "gender", nullable = false)
    private Integer gender;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    // 언어로 할 거면 변경 필요
    @Column(name = "nation", nullable = false)
    private String nation;

    @Column(name = "description", length = 50)
    private String description;

    @Column(name = "certified", nullable = false)
    private Integer certified = 0;

    @Builder
    public User(String uuid, String oauthId, String userName, String nickname, Integer gender, LocalDate birth,
                String nation, String description, Integer certified) {
        this.uuid = uuid;
        this.oauthId = oauthId;
        this.userName = userName;
        this.nickname = nickname;
        this.gender = gender;
        this.birthDate = birth;
        this.nation = nation;
        this.description = description;
        this.certified = certified;
    }
}
