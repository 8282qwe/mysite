package mysite.service;

import jakarta.servlet.http.Cookie;
import mysite.repository.BoardRepository;
import mysite.service.impl.BoardServiceImpl;
import mysite.vo.BoardVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BoardService implements BoardServiceImpl {
    private final BoardRepository boardRepository;

    //한 페이지에 나오는 게시글의 수
    private final int PER_PAGE = 5;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public void addContents(BoardVo boardVo) {
        if (boardVo.getG_no() != 0 && boardVo.getO_no() != 0) {
            boardRepository.insertReply(boardVo.getG_no(),boardVo.getO_no(),boardVo.getDepth(), boardVo.getUser_id(), boardVo.getTitle(), boardVo.getContents());
        }
        else boardRepository.insertBoard(boardVo.getTitle(), boardVo.getContents(), boardVo.getUser_id());
    }

    @Override
    public BoardVo getContents(Long id) {
        return getContents(id, null);
    }

    @Override
    public BoardVo getContents(Long id, Long userId) {
        return boardRepository.findById(id);
    }

    @Override
    public void updateContents(BoardVo boardVo) {
        boardRepository.updateBoard(boardVo);
    }

    @Override
    public void deleteContents(Long id, Long userId) {
        boardRepository.deleteBoardById(getContents(id),userId);
    }

    @Override
    public Map<String, Object> getContentsList(int page, String keyword) {
        int currentPage = Optional.of(page).orElse(1);
        int boardCount = boardRepository.countBoard(keyword);
        int totalPage = (int) Math.ceil((double) boardCount / PER_PAGE);
        int prevPage = ((currentPage - 1) / 5) * 5;
        int endPage = Math.min((prevPage + 5), totalPage);

        Map<String, Integer> pagingData = Map.of(
                "currentPage", currentPage,
                "totalPage", totalPage,
                "prevPage", prevPage,
                "endPage", endPage,
                "totalCount",boardCount,
                "perPage",PER_PAGE);

        List<BoardVo> list = boardRepository.findAllBoard(currentPage, PER_PAGE, keyword);

        return Map.of("pagingData",pagingData,
                "list",list);
    }

    @Override
    public Cookie countView(Long id, String viewData) {
        if (viewData == null || viewData.isBlank()) {
            boardRepository.updateViewById(id);
            return makeCookie(Long.toString(id));
        }
        else {
            if (!List.of(viewData.split("_")).contains(id.toString())) {
                boardRepository.updateViewById(id);
                return makeCookie(viewData + "_" + id);
            }
            return makeCookie(viewData);
        }
    }

    private Cookie makeCookie(String value) {
        Cookie cookie = new Cookie("viewPage", value);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24);
        return cookie;
    }
}
