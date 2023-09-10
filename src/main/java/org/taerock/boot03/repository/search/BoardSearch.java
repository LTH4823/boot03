package org.taerock.boot03.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.taerock.boot03.domain.Board;
import org.taerock.boot03.dto.BoardListAllDTO;
import org.taerock.boot03.dto.BoardListReplyCountDTO;

public interface BoardSearch {

    Page<Board> search1(Pageable pageable);

    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);

    Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types,
                                                      String keyword,
                                                      Pageable pageable);

    Page<BoardListAllDTO> searchWithAll(String[] types,
                                        String keyword,
                                        Pageable pageable);
}
