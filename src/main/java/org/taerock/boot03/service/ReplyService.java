package org.taerock.boot03.service;


import org.taerock.boot03.dto.PageRequestDTO;
import org.taerock.boot03.dto.PageResponseDTO;
import org.taerock.boot03.dto.ReplyDTO;

public interface ReplyService {

    Long register(ReplyDTO replyDTO);

    ReplyDTO read(Long rno);

    void modify(ReplyDTO replyDTO);

    void remove(Long rno);

    PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO);

}
