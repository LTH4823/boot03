package org.taerock.boot03.domain;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Member extends BaseEntity{
    //직접 입력하는 id 값
    //id == mid 소셜로그인하는 이메일 값이 id이니다.
    @Id
    private String mid;
    private String mpw;
    private String email;
    private boolean del;
    private boolean social;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

   public void changePassword(String mpw){
        this.mpw = mpw;
    }
    public void changeEmail(String email){
       this.email = email;
    }
    public void changeDel(Boolean del){
       this.del = del;
    }
    public void addRole(MemberRole memberRole){
        this.roleSet.add(memberRole);
    }

    public void clearRoles() {
        this.roleSet.clear();
    }

    public void changeSocial(boolean social){this.social = social;}

}


