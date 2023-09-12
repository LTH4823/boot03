package org.taerock.boot03.service;

import org.taerock.boot03.dto.MemberJoinDTO;

public interface MemberService {

    // 아이디 중복을 위한 설계계
   static class MidExistException extends Exception {

    }

    void join(MemberJoinDTO memberJoinDTO)throws MidExistException ;

}
